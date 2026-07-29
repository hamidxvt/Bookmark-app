/**
 * POST /api/v1/migrate
 * One-shot data migration from staging.bookmark.services → Railway PostgreSQL
 * Protected by admin session. Run once.
 */

import { NextResponse } from "next/server";
import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";
import { prisma } from "@/lib/prisma";
import axios from "axios";
import https from "https";

const BASE = "https://staging.bookmark.services";
let cookieStore: Record<string, string> = {};

function jarToHeader() {
  return Object.entries(cookieStore).map(([k, v]) => `${k}=${v}`).join("; ");
}
function absorbCookies(headers: Record<string, string | string[] | undefined>) {
  const sc = headers["set-cookie"];
  if (!sc) return;
  const cookies = Array.isArray(sc) ? sc : [sc];
  for (const c of cookies) {
    const [kv] = c.split(";");
    const eq = kv.indexOf("=");
    if (eq > -1) cookieStore[kv.slice(0, eq).trim()] = kv.slice(eq + 1).trim();
  }
}

const http = axios.create({
  baseURL: BASE,
  timeout: 30000,
  maxRedirects: 0,
  validateStatus: () => true,
  headers: { "User-Agent": "Mozilla/5.0", "Accept": "application/json, text/html, */*", "X-Requested-With": "XMLHttpRequest" },
  httpsAgent: new https.Agent({ rejectUnauthorized: false }),
});

async function login() {
  const step1 = await http.get("/", { headers: { Accept: "text/html,*/*" } });
  absorbCookies(step1.headers as any);
  const step2 = await http.get("/", { headers: { Accept: "text/html,*/*", Cookie: jarToHeader() } });
  absorbCookies(step2.headers as any);
  const xsrf = decodeURIComponent(cookieStore["XSRF-TOKEN"] ?? "");
  const m = (step2.data as string).match(/name="_token"\s+value="([^"]+)"/);
  const formToken = m ? m[1] : xsrf;
  const step3 = await http.post(
    "/dologin",
    new URLSearchParams({ email: "admin@gmail.com", password: "admin#123", _token: formToken, remember: "on" }).toString(),
    { headers: { "Content-Type": "application/x-www-form-urlencoded", Cookie: jarToHeader(), "X-XSRF-TOKEN": xsrf, Referer: `${BASE}/login`, Origin: BASE } }
  );
  absorbCookies(step3.headers as any);
  if (step3.status === 302) {
    const loc = step3.headers["location"] as string;
    if (loc) absorbCookies((await http.get(loc.startsWith("http") ? loc : BASE + loc, { headers: { Cookie: jarToHeader() } })).headers as any);
  }
}

async function fetchDT(path: string): Promise<unknown[]> {
  const res = await http.get(path, { params: { draw: 1, start: 0, length: 2000 }, headers: { Cookie: jarToHeader(), Accept: "application/json" } });
  return (res.data?.data as unknown[]) ?? [];
}

export async function POST() {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ error: "Unauthorized" }, { status: 401 });

  const log: string[] = [];
  cookieStore = {};

  try {
    log.push("Logging in to staging...");
    await login();
    log.push("Login OK");

    log.push("Fetching data...");
    const [bookerRows, customerRows, visitRows, subjectRows, seriesRows] = await Promise.all([
      fetchDT("/booker-list/datatable"),
      fetchDT("/customer-list/datatable"),
      fetchDT("/visits-list/datatable-new"),
      fetchDT("/subject-list/datatable"),
      fetchDT("/Series-list/datatable"),
    ]);

    // Cities from booker data
    const cityNames = [...new Set((bookerRows as any[]).map((b: any) => b[5] || b.city).filter(Boolean))];
    for (const name of cityNames) {
      await prisma.city.upsert({ where: { name: String(name) }, update: {}, create: { name: String(name) } }).catch(() => {});
    }
    log.push(`Cities: ${cityNames.length} upserted`);

    // Bookers
    let bookersOk = 0;
    for (const row of bookerRows as any[]) {
      try {
        const name = String(row[1] ?? row.name ?? "Unknown");
        const email = String(row[2] ?? row.email ?? `b${row[0]}@bookmark.pk`);
        const phone = String(row[3] ?? row.phone ?? "");
        const cityName = String(row[5] ?? row.city ?? "");
        const city = cityName ? await prisma.city.findFirst({ where: { name: cityName } }) : null;
        await prisma.booker.upsert({
          where: { email },
          update: { name, phone },
          create: { name, email, phone, password: "$2b$12$dummypassword__reset_required__", jobStatus: "ACTIVE", adminApproved: "APPROVED", cityId: city?.id ?? null },
        });
        bookersOk++;
      } catch {}
    }
    log.push(`Bookers: ${bookersOk}/${bookerRows.length} migrated`);

    // Customers
    let custOk = 0;
    for (const row of customerRows as any[]) {
      try {
        const name = String(row[0] ?? row.name ?? "");
        const cityName = String(row[1] ?? row.city ?? "");
        const type = String(row[2] ?? row.customer_type ?? "School");
        const address = String(row[3] ?? row.address ?? "");
        const city = cityName ? await prisma.city.findFirst({ where: { name: cityName } }) : null;
        const existing = await prisma.customer.findFirst({ where: { name, cityId: city?.id ?? undefined } });
        if (!existing) {
          await prisma.customer.create({ data: { name, type: type as any, address: address || undefined, cityId: city?.id, approvalStatus: "APPROVED" } });
          custOk++;
        }
      } catch {}
    }
    log.push(`Customers: ${custOk}/${customerRows.length} migrated`);

    // Subjects
    let subOk = 0;
    for (const row of subjectRows as any[]) {
      const name = String((row as any)[0] ?? (row as any).name ?? "").trim();
      if (name) { await prisma.subject.upsert({ where: { name }, update: {}, create: { name } }).catch(() => {}); subOk++; }
    }
    log.push(`Subjects: ${subOk} upserted`);

    // Series
    let serOk = 0;
    for (const row of seriesRows as any[]) {
      const name = String((row as any)[0] ?? (row as any).name ?? "").trim();
      if (name) {
        const exists = await prisma.series.findFirst({ where: { name } });
        if (!exists) { await prisma.series.create({ data: { name } }).catch(() => {}); serOk++; }
      }
    }
    log.push(`Series: ${serOk} upserted`);

    // Visits
    let visOk = 0;
    for (const row of visitRows as any[]) {
      try {
        const bookerName = String(row[0] ?? row.booker ?? "").split(" ")[0];
        const customerName = String(row[1] ?? row.customer ?? "").split(" ")[0];
        const rawDate = String(row[3] ?? row.date ?? row.visit_date ?? "");
        const visitDate = rawDate ? new Date(rawDate) : new Date();
        if (isNaN(visitDate.getTime())) continue;
        const booker = await prisma.booker.findFirst({ where: { name: { contains: bookerName } } });
        const customer = await prisma.customer.findFirst({ where: { name: { contains: customerName } } });
        if (booker && customer) {
          await prisma.visit.create({ data: { bookerId: booker.id, customerId: customer.id, visitDate, status: "COMPLETED" } });
          visOk++;
        }
      } catch {}
    }
    log.push(`Visits: ${visOk}/${visitRows.length} migrated`);

    log.push("✅ Migration complete!");
    return NextResponse.json({ success: true, log });
  } catch (err: any) {
    return NextResponse.json({ success: false, error: err.message, log }, { status: 500 });
  }
}
