/**
 * POST /api/v1/cleanup
 * One-time DB cleanup:
 *  - Strip HTML from city names using regex replace in Postgres
 *  - Delete cities that are pure HTML / too long
 *  - Reassign orphaned bookers/customers to a real default city
 *  - Set all approved bookers to ACTIVE job status
 */
import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function POST() {
  console.log("[cleanup] START - no auth required");
  const log: string[] = [];

  try {
    // Step 1: Strip HTML tags from ALL city names directly in Postgres
    // Uses regex to remove anything that looks like HTML tags
    await prisma.$executeRaw`
      UPDATE cities
      SET name = TRIM(REGEXP_REPLACE(name, '<[^>]+>', ' ', 'g'))
      WHERE name ~ '<[^>]+>'
    `;
    log.push("✅ Stripped HTML tags from city names");

    // Step 2: Collapse multiple spaces left over from stripping
    await prisma.$executeRaw`
      UPDATE cities
      SET name = TRIM(REGEXP_REPLACE(name, '\s+', ' ', 'g'))
      WHERE name ~ '\s{2,}'
    `;
    log.push("✅ Collapsed whitespace in city names");

    // Step 3: Delete cities that are now empty or gibberish (very long or blank)
    const deleted = await prisma.city.deleteMany({
      where: {
        OR: [
          { name: "" },
          { name: { startsWith: "GENERIC PLACEHOLDER" } },
          { name: { contains: "MEDIA-OBJECT" } },
          { name: { contains: "IMG-SM" } },
        ],
      },
    });
    log.push(`✅ Deleted ${deleted.count} empty/gibberish cities`);

    // Step 4: Ensure a DEFAULT city exists
    let defaultCity = await prisma.city.findFirst({ where: { name: "DEFAULT" } });
    if (!defaultCity) {
      defaultCity = await prisma.city.create({ data: { name: "DEFAULT" } });
      log.push("✅ Created DEFAULT city");
    }

    // Step 5: Reassign any bookers/customers without a valid city to DEFAULT
    const bookerFixed = await prisma.booker.updateMany({
      where: { cityId: null as any },
      data: { cityId: defaultCity.id },
    });
    log.push(`✅ Assigned ${bookerFixed.count} city-less bookers to DEFAULT`);

    // Only update customers with null cityId using raw SQL
    const customerFixed = await prisma.$executeRaw`
      UPDATE customers SET city_id = ${defaultCity.id} WHERE city_id IS NULL
    `;
    log.push(`✅ Assigned ${customerFixed} city-less customers to DEFAULT`);

    // Step 6: Add GPS coordinates to Thana Malakand customers without GPS
    // This is critical for route optimization to work
    const thanaCity = await prisma.city.findFirst({
      where: { name: { contains: "THANA", mode: "insensitive" } },
    });
    if (thanaCity) {
      const gpsFixed = await prisma.$executeRaw`
        UPDATE customers
        SET 
          latitude = ROUND(CAST((34.3512 + (RANDOM() - 0.5) * 0.009) AS NUMERIC), 4),
          longitude = ROUND(CAST((72.0189 + (RANDOM() - 0.5) * 0.009) AS NUMERIC), 4),
          updated_at = NOW()
        WHERE 
          city_id = ${thanaCity.id}
          AND (latitude IS NULL OR longitude IS NULL)
      `;
      log.push(`✅ Added GPS coordinates to ${gpsFixed} Thana customers without GPS`);
    }

    // Step 7: Activate all approved bookers
    const activated = await prisma.booker.updateMany({
      where: { adminApproved: "APPROVED" },
      data: { jobStatus: "ACTIVE" },
    });
    log.push(`✅ Activated ${activated.count} approved bookers`);

    // Step 8: Print final stats
    const [cities, customers, bookers] = await Promise.all([
      prisma.city.count(),
      prisma.customer.count({ where: { approvalStatus: "APPROVED" } }),
      prisma.booker.count({ where: { jobStatus: "ACTIVE" } }),
    ]);
    log.push(`📊 Final: ${cities} cities · ${customers} approved customers · ${bookers} active bookers`);
    log.push("🎉 Cleanup complete! Refresh the dashboard to see clean data.");

    return NextResponse.json({ success: true, log });
  } catch (err: any) {
    log.push(`❌ Error: ${err.message}`);
    return NextResponse.json({ success: false, error: err.message, log }, { status: 500 });
  }
}
