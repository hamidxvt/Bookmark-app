/**
 * POST /api/v1/seed-data?type=customers|products|all
 * One-time data seed from XLSX-derived JSON files.
 * Uses upsert so it is safe to call multiple times (idempotent).
 */
import { NextRequest, NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import path from "path";
import fs from "fs";

// ─── Type definitions ─────────────────────────────────────────────────────────

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

async function getOrCreateCity(name: string): Promise<number> {
  const city = await prisma.city.upsert({
    where: { name: name.toUpperCase() },
    update: {},
    create: { name: name.toUpperCase() },
  });
  return city.id;
}

// ─── Seed Customers ───────────────────────────────────────────────────────────

async function seedCustomers() {
  const data = readJson<SeedCustomer[]>("seed-customers.json");
  const cityCache = new Map<string, number>();
  let created = 0;
  let updated = 0;
  let skipped = 0;

  // Batch in chunks to avoid timeout
  const CHUNK = 200;
  for (let i = 0; i < data.length; i += CHUNK) {
    const chunk = data.slice(i, i + CHUNK);

    for (const row of chunk) {
      if (!row.externalCode) { skipped++; continue; }

      const cityKey = row.cityName.toUpperCase();
      if (!cityCache.has(cityKey)) {
        cityCache.set(cityKey, await getOrCreateCity(cityKey));
      }
      const cityId = cityCache.get(cityKey)!;

      // Build display name (combine name + branch if distinct)
      const fullName = row.branchName && row.branchName !== row.name
        ? row.name
        : row.name;

      try {
        const existing = await prisma.customer.findFirst({
          where: { name: fullName, cityId },
          select: { id: true },
        });

        const payload = {
          name: fullName,
          customerType: row.customerType as "SCHOOL" | "COLLEGE" | "RETAILER" | "SELF" | "OTHER",
          category: row.category ?? undefined,
          ownerName: row.ownerName ?? undefined,
          ownerPhone: row.phone,
          email: row.email ?? undefined,
          website: row.website ?? undefined,
          address: row.address ?? undefined,
          zone: row.zone ?? undefined,
          examinationBoard: row.examinationBoard ?? undefined,
          offeredProgramme: row.offeredProgramme ?? undefined,
          totalStudents: row.totalStudents ?? undefined,
          workingPriority: row.workingPriority ?? 3,
          approvalStatus: "APPROVED" as const,
          cityId,
        };

        if (existing) {
          await prisma.customer.update({ where: { id: existing.id }, data: payload });
          updated++;
        } else {
          await prisma.customer.create({ data: payload });
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

async function seedProducts() {
  const data = readJson<SeedProduct[]>("seed-products.json");
  const brandCache   = new Map<string, number>();
  const subjectCache = new Map<string, number>();
  const seriesCache  = new Map<string, number>();
  let created = 0;
  let updated = 0;
  let skipped = 0;

  for (const row of data) {
    if (!row.name) { skipped++; continue; }

    // Upsert Brand
    const brandKey = row.brand.toUpperCase();
    if (!brandCache.has(brandKey)) {
      const b = await prisma.brand.upsert({
        where: { name: row.brand },
        update: {},
        create: { name: row.brand },
      });
      brandCache.set(brandKey, b.id);
    }
    const brandId = brandCache.get(brandKey)!;

    // Upsert Subject
    let subjectId: number | undefined;
    if (row.subject) {
      const sk = row.subject.toUpperCase();
      if (!subjectCache.has(sk)) {
        const s = await prisma.subject.upsert({
          where: { name: row.subject },
          update: {},
          create: { name: row.subject },
        });
        subjectCache.set(sk, s.id);
      }
      subjectId = subjectCache.get(sk);
    }

    // Upsert Series
    let seriesId: number | undefined;
    if (row.series) {
      const sk = row.series.toUpperCase();
      if (!seriesCache.has(sk)) {
        const s = await prisma.series.upsert({
          where: { name: row.series },
          update: {},
          create: { name: row.series },
        });
        seriesCache.set(sk, s.id);
      }
      seriesId = seriesCache.get(sk);
    }

    try {
      const existing = await prisma.product.findFirst({
        where: { name: row.name, brandId },
        select: { id: true },
      });

      const payload = {
        name: row.name,
        brandId,
        subjectId: subjectId ?? null,
        seriesId: seriesId ?? null,
        isbn: row.isbn ?? null,
        segment: row.segment ?? null,
        grade: row.grade ? String(row.grade) : null,
        description: row.description ?? null,
        retailPrice: row.retailPrice,
      };

      if (existing) {
        await prisma.product.update({ where: { id: existing.id }, data: payload });
        updated++;
      } else {
        await prisma.product.create({ data: payload });
        created++;
      }
    } catch {
      skipped++;
    }
  }

  return { created, updated, skipped, total: data.length };
}

// ─── Route Handler ────────────────────────────────────────────────────────────

export async function POST(req: NextRequest) {
  const type = req.nextUrl.searchParams.get("type") ?? "all";

  try {
    const results: Record<string, unknown> = {};

    if (type === "customers" || type === "all") {
      console.log("[seed] Starting customer seed…");
      results.customers = await seedCustomers();
      console.log("[seed] Customers done:", results.customers);
    }

    if (type === "products" || type === "all") {
      console.log("[seed] Starting product seed…");
      results.products = await seedProducts();
      console.log("[seed] Products done:", results.products);
    }

    return NextResponse.json({ success: true, data: results });
  } catch (err: any) {
    console.error("[seed-data]", err);
    return NextResponse.json({ success: false, error: err.message }, { status: 500 });
  }
}

export async function GET() {
  const [customers, products] = await Promise.all([
    prisma.customer.count(),
    prisma.product.count(),
  ]);
  return NextResponse.json({ success: true, data: { customers, products } });
}
