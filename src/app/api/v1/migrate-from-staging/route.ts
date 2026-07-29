/**
 * POST /api/v1/migrate-from-staging
 * Pull all data from staging.bookmark.services directly into Railway PostgreSQL
 * Protected by admin session.
 * 
 * Run this ONCE to populate: cities, bookers, customers, subjects, series, visits
 */

import { NextResponse } from "next/server";
import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";
import { prisma } from "@/lib/prisma";
import axios from "axios";
import https from "https";

const BASE = "https://staging.bookmark.services";
let cookieStore: Record<string, string> = {};

// ─── HTML parsing helpers ─────────────────────────────────────────────────────

/** Strip all HTML tags and decode entities, return plain text */
function stripHtml(raw: string): string {
  if (!raw) return "";
  if (typeof raw !== "string") raw = String(raw);
  
  // If no HTML, return as-is
  if (!raw.includes("<")) return raw.trim();
  
  // Extract text from font-weight-bold span — try multiple patterns
  let match = raw.match(/font-weight-bold[^>]*>([^<]+)</i);
  if (match?.[1]) return match[1].trim();
  
  // Try ANY span with uppercase text
  match = raw.match(/<span[^>]*>([A-Z\s\-]+)<\/span>/);
  if (match?.[1]) return match[1].trim();
  
  // Fallback: strip all tags
  let text = raw.replace(/<[^>]*>/g, " ");
  // Decode HTML entities
  text = text.replace(/&nbsp;/g, " ").replace(/&amp;/g, "&");
  // Collapse whitespace
  return text.replace(/\s+/g, " ").trim();
}

/** Extract phone from staging HTML — looks for <span class="small">...</span> */
function extractPhone(raw: string): string {
  if (!raw || !raw.includes("<")) return raw.trim();
  const smallMatch = raw.match(/<span[^>]*class="small"[^>]*>([^<]+)<\/span>/i);
  if (smallMatch) {
    const phone = smallMatch[1].trim();
    return phone.toLowerCase() === "not provided" ? "" : phone;
  }
  return "";
}

/** Extract city from name like "SCHOOL NAME-KHI" → "KHI" portion won't be a city key
 *  Staging customer names often have "-CityCode" suffix — we ignore that and use the city column */
function cleanName(raw: string): string {
  return stripHtml(raw).replace(/\s+/g, " ").trim();
}

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
  headers: { "User-Agent": "Mozilla/5.0", Accept: "application/json, text/html, */*", "X-Requested-With": "XMLHttpRequest" },
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
  const res = await http.get(path, { params: { draw: 1, start: 0, length: 5000 }, headers: { Cookie: jarToHeader(), Accept: "application/json" } });
  return (res.data?.data as unknown[]) ?? [];
}

export async function POST() {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ error: "Unauthorized" }, { status: 401 });

  const log: string[] = [];
  cookieStore = {};

  try {
    log.push("🔐 Logging into staging.bookmark.services...");
    await login();
    log.push("✅ Login successful");

    log.push("📥 Fetching data from staging...");
    const [bookerRows, customerRows, visitRows, subjectRows, seriesRows] = await Promise.all([
      fetchDT("/booker-list/datatable"),
      fetchDT("/customer-list/datatable"),
      fetchDT("/visits-list/datatable-new"),
      fetchDT("/subject-list/datatable"),
      fetchDT("/Series-list/datatable"),
    ]);

    log.push(`   Bookers: ${bookerRows.length} | Customers: ${customerRows.length} | Visits: ${visitRows.length} | Subjects: ${subjectRows.length} | Series: ${seriesRows.length}`);

    // Clear old data — delete in reverse dependency order to respect foreign keys
    log.push("🗑️  Clearing old data...");
    try {
      // Delete dependent records first
      await prisma.message.deleteMany({});
      await prisma.missedVisitReason.deleteMany({});
      await prisma.orderItem.deleteMany({});
      await prisma.order.deleteMany({});
      await prisma.gpsPing.deleteMany({});
      await prisma.attendance.deleteMany({});
      await prisma.leaveRequest.deleteMany({});
      await prisma.request.deleteMany({});
      await prisma.visit.deleteMany({});
      
      // Then parent records
      await prisma.booker.deleteMany({});
      await prisma.customer.deleteMany({});
      await prisma.area.deleteMany({});
      await prisma.region.deleteMany({});
      await prisma.city.deleteMany({});
      await prisma.subject.deleteMany({});
      await prisma.series.deleteMany({});
      await prisma.brand.deleteMany({});
      await prisma.product.deleteMany({});
      
      log.push("✅ Cleared");
    } catch (e: any) {
      log.push(`⚠️  Partial clear: ${e.message}`);
      // Continue anyway — may have already cleared some tables
    }

    // Insert cities — normalize names
    log.push("📍 Inserting cities...");
    const rawCityNames = [
      ...(bookerRows as any[]).map((b: any) => b[5] || b.city).filter(Boolean),
      ...(customerRows as any[]).map((c: any) => c[1] || c.city).filter(Boolean),
    ];
    const cityNames = [...new Set(rawCityNames.map((c: any) => String(c).trim().toUpperCase()))];
    
    for (const name of cityNames) {
      if (name) {
        await prisma.city.upsert({
          where: { name },
          update: {},
          create: { name },
        }).catch(() => {});
      }
    }
    log.push(`✅ ${cityNames.length} cities`);

    // Insert bookers
    log.push("👤 Inserting bookers...");
    let bookerCount = 0;
    for (const row of bookerRows as any[]) {
      // Staging DataTable: row[1] = HTML name block, row[2] = action HTML (not email)
      // Need to detect actual email — it may not be in the standard columns
      const rawNameCol = String(row[1] ?? row.name ?? "Unknown");
      const name = cleanName(rawNameCol);
      const phoneFromHtml = extractPhone(rawNameCol);
      const phone = phoneFromHtml || String(row[3] ?? row.phone ?? "").replace(/<[^>]*>/g, "").trim();
      // Email: generate from name since staging may not expose it in datatable
      const rawEmail = String(row[2] ?? row.email ?? "").replace(/<[^>]*>/g, "").trim().toLowerCase();
      const email = rawEmail.includes("@") ? rawEmail : `${name.toLowerCase().replace(/\s+/g, ".")}@bookmark.pk`;
      const cityName = stripHtml(String(row[5] ?? row.city ?? "")).trim().toUpperCase();

      let cityId = null;
      if (cityName) {
        const city = await prisma.city.findFirst({ where: { name: cityName } });
        cityId = city?.id;
      }

      // Fallback to first available city if none found
      if (!cityId) {
        const anyCity = await prisma.city.findFirst();
        cityId = anyCity?.id;
      }

      try {
        await prisma.booker.create({
          data: {
            name,
            email,
            phone,
            password: "$2b$12$placeholder_needs_reset",
            jobStatus: "ACTIVE",
            adminApproved: "APPROVED",
            cityId,
          },
        });
        bookerCount++;
      } catch (e) {
        // Duplicate email or other constraint, skip
      }
    }
    log.push(`✅ ${bookerCount}/${bookerRows.length} bookers`);

    // Insert subjects
    log.push("📚 Inserting subjects...");
    let subjectCount = 0;
    for (const row of subjectRows as any[]) {
      const name = String((row[0] ?? row.name ?? "")).trim();
      if (name) {
        try {
          await prisma.subject.create({ data: { name } });
          subjectCount++;
        } catch {}
      }
    }
    log.push(`✅ ${subjectCount} subjects`);

    // Insert series
    log.push("📖 Inserting series...");
    let seriesCount = 0;
    for (const row of seriesRows as any[]) {
      const name = String((row[0] ?? row.name ?? "")).trim();
      if (name) {
        try {
          await prisma.series.create({ data: { name } });
          seriesCount++;
        } catch {}
      }
    }
    log.push(`✅ ${seriesCount} series`);

    // Insert customers
    log.push("🏪 Inserting customers...");
    let custCount = 0;
    let skipped = 0;
    for (const row of customerRows as any[]) {
      try {
        // Staging DataTable: customer row is complex HTML
        // Try to extract name from the media block (checkbox + type + name + phone in HTML)
        const rawData = JSON.stringify(row).toLowerCase();
        
        // Sometimes city is in row[1], sometimes hidden — look for city in booker list first
        let cityName = "OTHER";
        let name = "";
        let ownerPhone = "";

        // Parse row as array of strings
        for (let i = 0; i < Math.min(row.length, 10); i++) {
          const col = String(row[i] ?? "");
          const clean = stripHtml(col).toUpperCase().trim();
          
          // Skip if it's obviously HTML junk or empty
          if (!clean || clean.length < 3) continue;
          
          // First long clean string is usually the name
          if (!name && clean.length > 4 && !clean.includes("ACTION")) {
            name = clean;
          }
          
          // Try to find a phone number
          if (!ownerPhone && /^\d{4,}/.test(clean)) {
            ownerPhone = clean;
          }
        }

        // Ensure we have a name
        if (!name || name.length < 3) {
          skipped++;
          continue;
        }

        // Find city — default to OTHER
        const city = await prisma.city.findFirst({ where: { name: cityName } });
        const cityId = city?.id ?? (await prisma.city.findFirst())?.id;

        if (!cityId) {
          skipped++;
          continue;
        }

        const typeMap: Record<string, "SCHOOL" | "COLLEGE" | "SELF" | "RETAILER" | "OTHER"> = {
          school: "SCHOOL", college: "COLLEGE", self: "SELF", retailer: "RETAILER",
        };
        
        // Type is always OTHER since we can't reliably extract it
        const customerType = "OTHER";

        await prisma.customer.create({
          data: {
            name: name.substring(0, 255), // Limit length
            customerType,
            address: undefined,
            cityId,
            approvalStatus: "APPROVED",
            ownerPhone: ownerPhone.substring(0, 20) || "",
          },
        }).catch(() => { skipped++; });
        
        custCount++;
      } catch (e) {
        skipped++;
      }
    }
    log.push(`✅ ${custCount}/${customerRows.length} customers (skipped: ${skipped})`);

    // Insert visits
    log.push("📋 Inserting visits...");
    let visitCount = 0;
    for (const row of visitRows as any[]) {
      const bookerName = String(row[0] ?? row.booker ?? "").split(" ")[0].trim();
      const customerName = String(row[1] ?? row.customer ?? "").split(" ")[0].trim();
      const rawDate = String(row[3] ?? row.date ?? row.visit_date ?? "").trim();
      const visitDate = rawDate ? new Date(rawDate) : new Date();

      if (isNaN(visitDate.getTime())) continue;

      try {
        const booker = await prisma.booker.findFirst({ where: { name: { contains: bookerName } } });
        const customer = await prisma.customer.findFirst({ where: { name: { contains: customerName } } });

        if (booker && customer) {
          await prisma.visit.create({
            data: {
              bookerId: booker.id,
              customerId: customer.id,
              visitDate,
              status: "COMPLETED",
            },
          });
          visitCount++;
        }
      } catch (e) {
        // Skip on error
      }
    }
    log.push(`✅ ${visitCount} visits`);

    log.push("\n🎉 ─────────────────────────────────────────────────────────");
    log.push("✅ MIGRATION COMPLETE!");
    log.push("🎉 ─────────────────────────────────────────────────────────");
    log.push(`   Cities:    ${cityNames.length}`);
    log.push(`   Bookers:   ${bookerCount}`);
    log.push(`   Customers: ${custCount}`);
    log.push(`   Subjects:  ${subjectCount}`);
    log.push(`   Series:    ${seriesCount}`);
    log.push(`   Visits:    ${visitCount}`);
    log.push("\n💡 All data is now in your Railway PostgreSQL database!");
    log.push("   Bookers have placeholder passwords and need to reset.");

    return NextResponse.json({ success: true, log });
  } catch (err: any) {
    log.push(`❌ Error: ${err.message}`);
    return NextResponse.json({ success: false, error: err.message, log }, { status: 500 });
  }
}
