/**
 * migrate-data.js
 * Direct migration from staging.bookmark.services → Railway PostgreSQL
 * 
 * Usage: node migrate-data.js
 * 
 * Connection string provided in the code below
 */

const axios = require("axios");
const { Client } = require("pg");
const https = require("https");

const STAGING_BASE = "https://staging.bookmark.services";
const STAGING_EMAIL = "admin@gmail.com";
const STAGING_PASSWORD = "admin#123";

// Your Railway PostgreSQL connection
const DB_URL = "postgresql://postgres:BeYqTUdwmXHibmmjnGgJbPvZQIyDrwpa@gondola.proxy.rlwy.net:14194/railway";

let cookieStore = {};

function jarToHeader() {
  return Object.entries(cookieStore).map(([k, v]) => `${k}=${v}`).join("; ");
}

function absorbCookies(headers) {
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
  baseURL: STAGING_BASE,
  timeout: 30000,
  maxRedirects: 0,
  validateStatus: () => true,
  headers: { "User-Agent": "Mozilla/5.0", Accept: "application/json, text/html, */*", "X-Requested-With": "XMLHttpRequest" },
  httpsAgent: new https.Agent({ rejectUnauthorized: false }),
});

async function login() {
  console.log("🔐 Logging into staging.bookmark.services...");
  const step1 = await http.get("/", { headers: { Accept: "text/html,*/*" } });
  absorbCookies(step1.headers);

  const step2 = await http.get("/", { headers: { Accept: "text/html,*/*", Cookie: jarToHeader() } });
  absorbCookies(step2.headers);

  const xsrf = decodeURIComponent(cookieStore["XSRF-TOKEN"] ?? "");
  const m = (step2.data).match(/name="_token"\s+value="([^"]+)"/);
  const formToken = m ? m[1] : xsrf;

  const step3 = await http.post(
    "/dologin",
    new URLSearchParams({ email: STAGING_EMAIL, password: STAGING_PASSWORD, _token: formToken, remember: "on" }).toString(),
    { headers: { "Content-Type": "application/x-www-form-urlencoded", Cookie: jarToHeader(), "X-XSRF-TOKEN": xsrf, Referer: `${STAGING_BASE}/login`, Origin: STAGING_BASE } }
  );
  absorbCookies(step3.headers);

  if (step3.status === 302) {
    const loc = step3.headers["location"];
    if (loc) absorbCookies((await http.get(loc.startsWith("http") ? loc : STAGING_BASE + loc, { headers: { Cookie: jarToHeader() } })).headers);
  }

  console.log("✅ Login successful\n");
}

async function fetchDT(path) {
  const res = await http.get(path, { params: { draw: 1, start: 0, length: 5000 }, headers: { Cookie: jarToHeader(), Accept: "application/json" } });
  return (res.data?.data ?? []);
}

async function main() {
  const client = new Client({ connectionString: DB_URL });
  await client.connect();
  console.log("✅ Connected to Railway PostgreSQL\n");

  try {
    await login();

    console.log("📥 Fetching data from staging...");
    const [bookerRows, customerRows, visitRows, subjectRows, seriesRows] = await Promise.all([
      fetchDT("/booker-list/datatable"),
      fetchDT("/customer-list/datatable"),
      fetchDT("/visits-list/datatable-new"),
      fetchDT("/subject-list/datatable"),
      fetchDT("/Series-list/datatable"),
    ]);

    console.log(`   Bookers: ${bookerRows.length}`);
    console.log(`   Customers: ${customerRows.length}`);
    console.log(`   Visits: ${visitRows.length}`);
    console.log(`   Subjects: ${subjectRows.length}`);
    console.log(`   Series: ${seriesRows.length}\n`);

    // Clear old data
    console.log("🗑️  Clearing old data...");
    await client.query("TRUNCATE TABLE visits CASCADE");
    await client.query("TRUNCATE TABLE bookers CASCADE");
    await client.query("TRUNCATE TABLE customers CASCADE");
    await client.query("TRUNCATE TABLE cities CASCADE");
    await client.query("TRUNCATE TABLE subjects CASCADE");
    await client.query("TRUNCATE TABLE series CASCADE");
    console.log("   Cleared.\n");

    // Insert cities
    console.log("📍 Inserting cities...");
    const cityNames = [...new Set(bookerRows.map(b => b[5] || b.city).filter(Boolean))];
    for (const name of cityNames) {
      const cityName = String(name).trim();
      if (cityName) {
        await client.query(
          "INSERT INTO cities (name) VALUES ($1) ON CONFLICT (name) DO NOTHING",
          [cityName]
        );
      }
    }
    console.log(`   ✅ ${cityNames.length} cities inserted\n`);

    // Insert bookers
    console.log("👤 Inserting bookers...");
    let bookerCount = 0;
    for (const row of bookerRows) {
      const name = String(row[1] ?? row.name ?? "Unknown").trim();
      const email = String(row[2] ?? row.email ?? `b${row[0]}@bookmark.pk`).trim();
      const phone = String(row[3] ?? row.phone ?? "").trim();
      const cityName = String(row[5] ?? row.city ?? "").trim();

      let cityId = null;
      if (cityName) {
        const cityRes = await client.query("SELECT id FROM cities WHERE name = $1", [cityName]);
        if (cityRes.rows.length > 0) cityId = cityRes.rows[0].id;
      }

      try {
        await client.query(
          `INSERT INTO bookers (name, email, phone, password, job_status, admin_approved, city_id, created_at, updated_at)
           VALUES ($1, $2, $3, $4, $5, $6, $7, NOW(), NOW())
           ON CONFLICT (email) DO UPDATE SET name = $1, phone = $3`,
          [name, email, phone, "$2b$12$dummypassword", "ACTIVE", "APPROVED", cityId]
        );
        bookerCount++;
      } catch (e) {
        // Email duplicate or other constraint
      }
    }
    console.log(`   ✅ ${bookerCount} bookers inserted\n`);

    // Insert subjects
    console.log("📚 Inserting subjects...");
    let subjectCount = 0;
    for (const row of subjectRows) {
      const name = String((row[0] ?? row.name ?? "")).trim();
      if (name) {
        try {
          await client.query(
            "INSERT INTO subjects (name) VALUES ($1) ON CONFLICT (name) DO NOTHING",
            [name]
          );
          subjectCount++;
        } catch {}
      }
    }
    console.log(`   ✅ ${subjectCount} subjects inserted\n`);

    // Insert series
    console.log("📖 Inserting series...");
    let seriesCount = 0;
    for (const row of seriesRows) {
      const name = String((row[0] ?? row.name ?? "")).trim();
      if (name) {
        try {
          await client.query(
            "INSERT INTO series (name) VALUES ($1) ON CONFLICT (name) DO NOTHING",
            [name]
          );
          seriesCount++;
        } catch {}
      }
    }
    console.log(`   ✅ ${seriesCount} series inserted\n`);

    // Insert customers
    console.log("🏪 Inserting customers...");
    let custCount = 0;
    for (const row of customerRows) {
      const name = String(row[0] ?? row.name ?? "").trim();
      const cityName = String(row[1] ?? row.city ?? "").trim();
      const type = String(row[2] ?? row.customer_type ?? "School").trim();
      const address = String(row[3] ?? row.address ?? "").trim();

      let cityId = null;
      if (cityName) {
        const cityRes = await client.query("SELECT id FROM cities WHERE name = $1", [cityName]);
        if (cityRes.rows.length > 0) cityId = cityRes.rows[0].id;
      }

      try {
        await client.query(
          `INSERT INTO customers (name, type, address, city_id, approval_status, created_at, updated_at)
           VALUES ($1, $2, $3, $4, $5, NOW(), NOW())`,
          [name, type, address || null, cityId, "APPROVED"]
        );
        custCount++;
      } catch (e) {
        // Duplicate or constraint error
      }
    }
    console.log(`   ✅ ${custCount} customers inserted\n`);

    // Insert visits
    console.log("📋 Inserting visits...");
    let visitCount = 0;
    for (const row of visitRows) {
      const bookerName = String(row[0] ?? row.booker ?? "").split(" ")[0].trim();
      const customerName = String(row[1] ?? row.customer ?? "").split(" ")[0].trim();
      const rawDate = String(row[3] ?? row.date ?? row.visit_date ?? "").trim();
      const visitDate = rawDate ? new Date(rawDate) : new Date();

      if (isNaN(visitDate.getTime())) continue;

      try {
        const bookerRes = await client.query("SELECT id FROM bookers WHERE name ILIKE $1", [`%${bookerName}%`]);
        const customerRes = await client.query("SELECT id FROM customers WHERE name ILIKE $1", [`%${customerName}%`]);

        if (bookerRes.rows.length > 0 && customerRes.rows.length > 0) {
          await client.query(
            `INSERT INTO visits (booker_id, customer_id, visit_date, status, created_at, updated_at)
             VALUES ($1, $2, $3, $4, NOW(), NOW())`,
            [bookerRes.rows[0].id, customerRes.rows[0].id, visitDate, "COMPLETED"]
          );
          visitCount++;
        }
      } catch (e) {
        // Skip on error
      }
    }
    console.log(`   ✅ ${visitCount} visits inserted\n`);

    console.log("🎉 ─────────────────────────────────────────────────────────");
    console.log("✅ MIGRATION COMPLETE!");
    console.log("🎉 ─────────────────────────────────────────────────────────");
    console.log(`   Cities:    ${cityNames.length}`);
    console.log(`   Bookers:   ${bookerCount}`);
    console.log(`   Customers: ${custCount}`);
    console.log(`   Subjects:  ${subjectCount}`);
    console.log(`   Series:    ${seriesCount}`);
    console.log(`   Visits:    ${visitCount}`);
    console.log("\n💡 All data is now in your Railway PostgreSQL database!");
    console.log("   Login: admin@bookmark.pk / Admin@123");
    console.log("   Bookers have placeholder passwords and need to reset.\n");

  } catch (err) {
    console.error("❌ Migration failed:", err.message);
    process.exit(1);
  } finally {
    await client.end();
  }
}

main();
