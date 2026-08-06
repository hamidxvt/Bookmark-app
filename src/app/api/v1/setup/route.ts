import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

// POST /api/v1/setup — run DB migrations for new columns + seed Thana Malakand test data
export async function POST() {
  const log: string[] = [];
  try {
    // ── 1. Add new columns to cities table (IF NOT EXISTS) ─────────────────
    await prisma.$executeRawUnsafe(`
      ALTER TABLE cities
        ADD COLUMN IF NOT EXISTS latitude        DOUBLE PRECISION,
        ADD COLUMN IF NOT EXISTS longitude       DOUBLE PRECISION,
        ADD COLUMN IF NOT EXISTS geofence_radius INTEGER DEFAULT 5000,
        ADD COLUMN IF NOT EXISTS is_active       BOOLEAN DEFAULT TRUE;
    `);
    log.push("✓ cities columns added");

    // ── 2. Add new columns to areas table (IF NOT EXISTS) ──────────────────
    await prisma.$executeRawUnsafe(`
      ALTER TABLE areas
        ADD COLUMN IF NOT EXISTS latitude        DOUBLE PRECISION,
        ADD COLUMN IF NOT EXISTS longitude       DOUBLE PRECISION,
        ADD COLUMN IF NOT EXISTS geofence_radius INTEGER DEFAULT 500,
        ADD COLUMN IF NOT EXISTS address         TEXT;
    `);
    log.push("✓ areas columns added");

    // ── 3. Upsert Thana Malakand city ──────────────────────────────────────
    let thanaCity = await prisma.city.findFirst({ where: { name: { contains: "THANA" } } });
    if (!thanaCity) {
      thanaCity = await prisma.city.create({
        data: {
          name: "THANA MALAKAND",
          latitude: 34.3512,
          longitude: 72.0189,
          geofenceRadius: 10000,
        },
      });
      log.push(`✓ Created city: THANA MALAKAND (id=${thanaCity.id})`);
    } else {
      await prisma.city.update({
        where: { id: thanaCity.id },
        data: { latitude: 34.3512, longitude: 72.0189, geofenceRadius: 10000 },
      });
      log.push(`✓ Updated city: THANA MALAKAND (id=${thanaCity.id})`);
    }

    // ── 4. Upsert default region for Thana ─────────────────────────────────
    let thanaRegion = await prisma.region.findFirst({ where: { cityId: thanaCity.id } });
    if (!thanaRegion) {
      thanaRegion = await prisma.region.create({
        data: { cityId: thanaCity.id, name: "Thana Central" },
      });
      log.push(`✓ Created region: Thana Central (id=${thanaRegion.id})`);
    }

    // ── 5. Seed 10 real Thana Malakand locations as Areas ──────────────────
    const locations = [
      { name: "Thana Town Center",         lat: 34.3512, lng: 72.0189, address: "Main Bazaar, Thana Malakand" },
      { name: "Govt Degree College Thana", lat: 34.3488, lng: 72.0165, address: "College Road, Thana Malakand" },
      { name: "District Hospital Thana",   lat: 34.3535, lng: 72.0210, address: "Hospital Chowk, Thana Malakand" },
      { name: "Thana Police Station",      lat: 34.3498, lng: 72.0175, address: "Station Road, Thana Malakand" },
      { name: "GPO Thana Malakand",        lat: 34.3505, lng: 72.0182, address: "Post Office Road, Thana" },
      { name: "Boys High School Thana",    lat: 34.3475, lng: 72.0155, address: "School Chowk, Thana Malakand" },
      { name: "Girls School Thana",        lat: 34.3522, lng: 72.0198, address: "Girls School Road, Thana" },
      { name: "Malakand University Campus",lat: 34.3550, lng: 72.0225, address: "University Road, Malakand" },
      { name: "Thana Fruit & Grain Market",lat: 34.3490, lng: 72.0172, address: "Sabzi Mandi, Thana Malakand" },
      { name: "Malakand Road Junction",    lat: 34.3560, lng: 72.0240, address: "Dir Road, Malakand" },
    ];

    let seeded = 0;
    for (const loc of locations) {
      const existing = await prisma.area.findFirst({
        where: { cityId: thanaCity.id, name: loc.name },
      });
      if (!existing) {
        await prisma.area.create({
          data: {
            cityId:         thanaCity.id,
            regionId:       thanaRegion.id,
            name:           loc.name,
            latitude:       loc.lat,
            longitude:      loc.lng,
            address:        loc.address,
            geofenceRadius: 300,
          },
        });
        seeded++;
      }
    }
    log.push(`✓ Seeded ${seeded} Thana Malakand locations`);

    // ── 6. Seed Lahore & Karachi (for production) ──────────────────────────
    const prodCities = [
      { name: "LAHORE",   lat: 31.5204, lng: 74.3587, radius: 15000 },
      { name: "KARACHI",  lat: 24.8607, lng: 67.0011, radius: 20000 },
      { name: "ISLAMABAD",lat: 33.6844, lng: 73.0479, radius: 12000 },
      { name: "MULTAN",   lat: 30.1575, lng: 71.5249, radius: 10000 },
    ];
    for (const c of prodCities) {
      const existing = await prisma.city.findFirst({ where: { name: c.name } });
      if (!existing) {
        await prisma.city.create({
          data: { name: c.name, latitude: c.lat, longitude: c.lng, geofenceRadius: c.radius },
        });
        log.push(`✓ Created city: ${c.name}`);
      } else {
        await prisma.city.update({
          where: { id: existing.id },
          data: { latitude: c.lat, longitude: c.lng, geofenceRadius: c.radius },
        });
      }
    }

    return NextResponse.json({ success: true, log });
  } catch (err) {
    console.error("[setup]", err);
    return NextResponse.json({ success: false, error: String(err), log }, { status: 500 });
  }
}
