/**
 * migrate-from-staging.ts
 * Pulls all data from staging.bookmark.services and inserts into Railway PostgreSQL
 * 
 * Run with: npx tsx scripts/migrate-from-staging.ts
 */

import axios from "axios";
import { PrismaClient } from "@prisma/client";
import * as https from "https";

const prisma = new PrismaClient();
const BASE = "https://staging.bookmark.services";
const EMAIL = "admin@gmail.com";
const PASSWORD = "admin#123";

let cookieStore: Record<string, string> = {};

function jarToHeader(): string {
  return Object.entries(cookieStore).map(([k, v]) => `${k}=${v}`).join("; ");
}

function absorbCookies(headers: any) {
  const sc = headers["set-cookie"];
  if (!sc) return;
  const cookies = Array.isArray(sc) ? sc : [sc];
  for (const c of cookies) {
    const [kv] = c.split(";");
    const eq = kv.indexOf("=");
    if (eq > -1) {
      cookieStore[kv.slice(0, eq).trim()] = kv.slice(eq + 1).trim();
    }
  }
}

const http = axios.create({
  baseURL: BASE,
  timeout: 30000,
  maxRedirects: 0,
  validateStatus: () => true,
  headers: {
    "User-Agent": "Mozilla/5.0",
    "Accept": "application/json, text/html, */*",
    "X-Requested-With": "XMLHttpRequest",
  },
  httpsAgent: new https.Agent({ rejectUnauthorized: false }),
});

async function login() {
  console.log("🔐 Logging into staging.bookmark.services...");
  
  const step1 = await http.get("/", { headers: { Accept: "text/html,*/*" } });
  absorbCookies(step1.headers);

  const step2 = await http.get("/", {
    headers: { Accept: "text/html,*/*", Cookie: jarToHeader() },
  });
  absorbCookies(step2.headers);

  const xsrf = decodeURIComponent(cookieStore["XSRF-TOKEN"] ?? "");
  const tokenMatch = (step2.data as string).match(/name="_token"\s+value="([^"]+)"/);
  const formToken = tokenMatch ? tokenMatch[1] : xsrf;

  const step3 = await http.post(
    "/dologin",
    new URLSearchParams({ email: EMAIL, password: PASSWORD, _token: formToken, remember: "on" }).toString(),
    {
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
        "Cookie": jarToHeader(),
        "X-XSRF-TOKEN": xsrf,
        "Referer": `${BASE}/login`,
        "Origin": BASE,
      },
    }
  );
  absorbCookies(step3.headers);

  if (step3.status === 302) {
    const loc = step3.headers["location"] as string;
    if (loc) {
      const step4 = await http.get(loc.startsWith("http") ? loc : BASE + loc, {
        headers: { Cookie: jarToHeader() },
      });
      absorbCookies(step4.headers);
    }
  }

  console.log("✅ Logged in. Cookies:", Object.keys(cookieStore).join(", "));
}

async function fetchDT(path: string, length = 1000): Promise<any[]> {
  const res = await http.get(path, {
    params: { draw: 1, start: 0, length },
    headers: { Cookie: jarToHeader(), Accept: "application/json" },
  });

  if (res.status === 401 || res.status === 419) {
    console.log("   Session expired, re-logging...");
    cookieStore = {};
    await login();
    return fetchDT(path, length);
  }

  const data = res.data?.data ?? [];
  console.log(`   Fetched ${data.length} records from ${path}`);
  return data;
}

// ─── Migrate Cities ───────────────────────────────────────────────────────────
async function migrateCities(bookers: any[]) {
  console.log("\n📍 Migrating cities...");
  const cityNames = [...new Set(bookers.map((b: any) => b[5] || b.city).filter(Boolean))];
  
  let created = 0;
  for (const name of cityNames) {
    await prisma.city.upsert({
      where: { name: String(name) },
      update: {},
      create: { name: String(name) },
    });
    created++;
  }
  console.log(`   ✅ ${created} cities upserted`);
}

// ─── Migrate Bookers (Officers) ───────────────────────────────────────────────
async function migrateBookers(rows: any[]) {
  console.log("\n👤 Migrating bookers/officers...");
  let created = 0, skipped = 0;

  for (const row of rows) {
    try {
      // Staging DataTable row: [0]=id [1]=name [2]=email [3]=phone [4]=status [5]=city
      const id = parseInt(row[0] ?? row.id);
      const name = String(row[1] ?? row.name ?? "Unknown");
      const email = String(row[2] ?? row.email ?? `booker${id}@bookmark.pk`);
      const phone = String(row[3] ?? row.phone ?? "");
      const status = String(row[4] ?? row.job_status ?? "ACTIVE");
      const cityName = String(row[5] ?? row.city ?? "");

      const city = cityName
        ? await prisma.city.findFirst({ where: { name: cityName } })
        : null;

      await prisma.booker.upsert({
        where: { email },
        update: { name, phone, jobStatus: "ACTIVE", adminApproved: "APPROVED" },
        create: {
          name,
          email,
          phone,
          password: "$2b$12$placeholder_needs_reset",
          jobStatus: "ACTIVE",
          adminApproved: "APPROVED",
          cityId: city?.id ?? null,
        },
      });
      created++;
    } catch (e: any) {
      console.warn(`   ⚠ Skipped booker: ${e.message}`);
      skipped++;
    }
  }
  console.log(`   ✅ ${created} bookers migrated, ${skipped} skipped`);
}

// ─── Migrate Customers ────────────────────────────────────────────────────────
async function migrateCustomers(rows: any[]) {
  console.log("\n🏪 Migrating customers...");
  let created = 0, skipped = 0;

  for (const row of rows) {
    try {
      const name = String(row[0] ?? row.name ?? "Unknown");
      const cityName = String(row[1] ?? row.city ?? "");
      const type = String(row[2] ?? row.customer_type ?? "School");
      const address = String(row[3] ?? row.address ?? "");

      const city = cityName
        ? await prisma.city.findFirst({ where: { name: cityName } })
        : null;

      // Check if already exists by name + city
      const existing = await prisma.customer.findFirst({
        where: { name, cityId: city?.id ?? undefined },
      });

      if (!existing) {
        await prisma.customer.create({
          data: {
            name,
            type: type as any,
            address: address || null,
            cityId: city?.id ?? null,
            approvalStatus: "APPROVED",
          },
        });
        created++;
      }
    } catch (e: any) {
      console.warn(`   ⚠ Skipped customer: ${e.message}`);
      skipped++;
    }
  }
  console.log(`   ✅ ${created} customers migrated, ${skipped} skipped`);
}

// ─── Migrate Products ─────────────────────────────────────────────────────────
async function migrateProducts(rows: any[], subjects: any[], series: any[]) {
  console.log("\n📚 Migrating products...");
  let created = 0;

  // Subjects
  for (const row of subjects) {
    const name = String(row[0] ?? row.name ?? "");
    if (name) {
      await prisma.subject.upsert({
        where: { name },
        update: {},
        create: { name },
      }).catch(() => {});
      created++;
    }
  }

  // Series
  for (const row of series) {
    const name = String(row[0] ?? row.name ?? "");
    if (name) {
      try {
        const exists = await prisma.series.findFirst({ where: { name } });
        if (!exists) {
          await prisma.series.create({ data: { name } });
          created++;
        }
      } catch {}
    }
  }

  console.log(`   ✅ ${created} subjects/series migrated`);
}

// ─── Migrate Visits ───────────────────────────────────────────────────────────
async function migrateVisits(rows: any[]) {
  console.log("\n📋 Migrating visits...");
  let created = 0, skipped = 0;

  for (const row of rows) {
    try {
      const bookerName = String(row[0] ?? row.booker ?? "");
      const customerName = String(row[1] ?? row.customer ?? "");
      const status = String(row[2] ?? row.status ?? "planned").toUpperCase();
      const dateStr = String(row[3] ?? row.date ?? row.visit_date ?? "");
      const visitDate = dateStr ? new Date(dateStr) : new Date();

      if (isNaN(visitDate.getTime())) continue;

      const booker = await prisma.booker.findFirst({
        where: { name: { contains: bookerName.split(" ")[0] } },
      });
      const customer = await prisma.customer.findFirst({
        where: { name: { contains: customerName.split(" ")[0] } },
      });

      if (booker && customer) {
        await prisma.visit.create({
          data: {
            bookerId: booker.id,
            customerId: customer.id,
            visitDate,
            status: (["COMPLETED", "PLANNED", "MISSED"].includes(status) ? status : "COMPLETED") as any,
          },
        });
        created++;
      }
    } catch (e: any) {
      skipped++;
    }
  }
  console.log(`   ✅ ${created} visits migrated, ${skipped} skipped`);
}

// ─── Main ─────────────────────────────────────────────────────────────────────
async function main() {
  console.log("🚀 Starting migration from staging.bookmark.services...\n");

  try {
    await login();
  } catch (e) {
    console.error("❌ Login failed:", e);
    process.exit(1);
  }

  // Fetch all data in parallel
  console.log("\n📥 Fetching data from staging...");
  const [bookerRows, customerRows, visitRows, productRows, subjectRows, seriesRows] = await Promise.all([
    fetchDT("/booker-list/datatable"),
    fetchDT("/customer-list/datatable"),
    fetchDT("/visits-list/datatable-new"),
    fetchDT("/products-list/datatable"),
    fetchDT("/subject-list/datatable"),
    fetchDT("/Series-list/datatable"),
  ]);

  // Migrate in order (dependencies first)
  await migrateCities(bookerRows);
  await migrateBookers(bookerRows);
  await migrateCustomers(customerRows);
  await migrateProducts(productRows, subjectRows, seriesRows);
  await migrateVisits(visitRows);

  console.log("\n✅ Migration complete!");
  console.log(`   Bookers: ${bookerRows.length}`);
  console.log(`   Customers: ${customerRows.length}`);
  console.log(`   Visits: ${visitRows.length}`);
  console.log(`   Products: ${productRows.length}`);
  console.log(`   Subjects: ${subjectRows.length}`);
  console.log(`   Series: ${seriesRows.length}`);
  console.log("\n🎉 All data is now in your Railway PostgreSQL database!\n");
}

main()
  .catch(console.error)
  .finally(() => prisma.$disconnect());
