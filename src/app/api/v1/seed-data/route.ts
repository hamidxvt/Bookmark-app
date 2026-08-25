/**
 * POST /api/v1/seed-data?type=customers|products|all
 * One-time data seed from XLSX-derived JSON files.
 * Idempotent: finds existing records by name and UPDATES them (city + category included).
 * This fixes old imports that assigned wrong cities.
 */
import { NextRequest, NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import path from "path";
import fs from "fs";

// ─── Types ────────────────────────────────────────────────────────────────────

interface SeedCustomer {
  externalCode: number | null;
  name: string;
  branchName: string | null;
  cityName: string;
  region: string | null;
  area: string | null;
  customerType: "SCHOOL" | "COLLEGE" | "RETAILER" | "SELF" | "OTHER";
  category: string | null;
  phone: string;
  email: string | null;
  address: string | null;
  ownerName: string | null;
  website: string | null;
  zone: string | null;
  examinationBoard: string | null;
  offeredProgramme: string | null;
  totalStudents: number | null;
  workingPriority: number;
}

interface SeedProduct {
  productCode: string | null;
  brand: string;
  subject: string | null;
  series: string | null;
  name: string;
  retailPrice: number;
  isbn: string | null;
  segment: string | null;
  grade: string | null;
  description: string | null;
}

// ─── Helpers ──────────────────────────────────────────────────────────────────

function readJson<T>(filename: string): T {
  const filePath = path.join(process.cwd(), "src", "data", filename);
  return JSON.parse(fs.readFileSync(filePath, "utf-8")) as T;
}

// City cache: name → id (creates city if missing)
const _cityCache = new Map<string, number>();
async function getCityId(rawName: string): Promise<number> {
  const key = rawName.trim().toUpperCase();
  if (_cityCache.has(key)) return _cityCache.get(key)!;
  const city = await prisma.city.upsert({
    where: { name: key },
    update: {},
    create: { name: key },
  });
  _cityCache.set(key, city.id);
  return city.id;
}

// ─── Seed Customers ───────────────────────────────────────────────────────────
// Looks up by name alone so it can FIX records that were imported with wrong city.

export async function seedCustomers(): Promise<{ created: number; updated: number; skipped: number; total: number }> {
  _cityCache.clear();
  const data = readJson<SeedCustomer[]>("seed-customers.json");
  let created = 0, updated = 0, skipped = 0;

  const CHUNK = 150;
  for (let i = 0; i < data.length; i += CHUNK) {
    const chunk = data.slice(i, i + CHUNK);
    for (const row of chunk) {
      if (!row.name) { skipped++; continue; }

      let cityId: number;
      try {
        cityId = await getCityId(row.cityName || "KARACHI");
      } catch {
        skipped++;
        continue;
      }

      const payload = {
        customerType: row.customerType,
        category:          row.category    ?? null,
        ownerName:         row.ownerName   ?? null,
        ownerPhone:        row.phone,
        email:             row.email       ?? null,
        website:           row.website     ?? null,
        address:           row.address     ?? null,
        zone:              row.zone        ?? null,
        examinationBoard:  row.examinationBoard  ?? null,
        offeredProgramme:  row.offeredProgramme  ?? null,
        totalStudents:     row.totalStudents      ?? null,
        workingPriority:   row.workingPriority    ?? 3,
        approvalStatus:    "APPROVED" as const,
        cityId,
      };

      try {
        // Look up by name only — catches records imported with wrong city
        const existing = await prisma.customer.findFirst({
          where: { name: row.name },
          select: { id: true },
        });

        if (existing) {
          await prisma.customer.update({ where: { id: existing.id }, data: payload });
          updated++;
        } else {
          await prisma.customer.create({ data: { name: row.name, ...payload } });
          created++;
        }
      } catch {
        skipped++;
      }
    }
  }

  return { created, updated, skipped, total: data.length };
}

// ─── Seed Products ────────────────────────────────────────────────────────────

async function seedProducts(): Promise<{ created: number; updated: number; skipped: number; total: number }> {
  const data = readJson<SeedProduct[]>("seed-products.json");
  const brandCache   = new Map<string, number>();
  const subjectCache = new Map<string, number>();
  const seriesCache  = new Map<string, number>();
  let created = 0, updated = 0, skipped = 0;

  for (const row of data) {
    if (!row.name) { skipped++; continue; }

    const brandKey = row.brand.toUpperCase();
    if (!brandCache.has(brandKey)) {
      const b = await prisma.brand.upsert({
        where: { name: row.brand }, update: {}, create: { name: row.brand },
      });
      brandCache.set(brandKey, b.id);
    }
    const brandId = brandCache.get(brandKey)!;

    let subjectId: number | null = null;
    if (row.subject) {
      const sk = row.subject.toUpperCase();
      if (!subjectCache.has(sk)) {
        const s = await prisma.subject.upsert({
          where: { name: row.subject }, update: {}, create: { name: row.subject },
        });
        subjectCache.set(sk, s.id);
      }
      subjectId = subjectCache.get(sk)!;
    }

    let seriesId: number | null = null;
    if (row.series) {
      const sk = row.series.toUpperCase();
      if (!seriesCache.has(sk)) {
        const s = await prisma.series.upsert({
          where: { name: row.series }, update: {}, create: { name: row.series },
        });
        seriesCache.set(sk, s.id);
      }
      seriesId = seriesCache.get(sk)!;
    }

    try {
      const existing = await prisma.product.findFirst({
        where: { name: row.name, brandId },
        select: { id: true },
      });

      const payload = {
        brandId,
        subjectId,
        seriesId,
        isbn:        row.isbn        ?? null,
        segment:     row.segment     ?? null,
        grade:       row.grade ? String(row.grade) : null,
        description: row.description ?? null,
        retailPrice: row.retailPrice,
      };

      if (existing) {
        await prisma.product.update({ where: { id: existing.id }, data: payload });
        updated++;
      } else {
        await prisma.product.create({ data: { name: row.name, ...payload } });
        created++;
      }
    } catch {
      skipped++;
    }
  }

  return { created, updated, skipped, total: data.length };
}

// ─── Route Handlers ───────────────────────────────────────────────────────────

export async function POST(req: NextRequest) {
  const type = req.nextUrl.searchParams.get("type") ?? "all";

  try {
    const results: Record<string, unknown> = {};

    if (type === "customers" || type === "all") {
      console.log("[seed] Seeding customers…");
      results.customers = await seedCustomers();
      console.log("[seed] Customers:", results.customers);
    }

    if (type === "products" || type === "all") {
      console.log("[seed] Seeding products…");
      results.products = await seedProducts();
      console.log("[seed] Products:", results.products);
    }

    return NextResponse.json({ success: true, data: results });
  } catch (err: any) {
    console.error("[seed-data]", err);
    return NextResponse.json({ success: false, error: err.message }, { status: 500 });
  }
}

export async function GET() {
  const [customers, products, aplus] = await Promise.all([
    prisma.customer.count(),
    prisma.product.count(),
    prisma.customer.count({ where: { category: "A+" } }),
  ]);
  return NextResponse.json({ success: true, data: { customers, products, aplusCount: aplus } });
}
