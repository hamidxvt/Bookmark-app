/**
 * POST /api/v1/cleanup
 * One-time DB cleanup:
 *  - Strip HTML from city names using regex replace in Postgres
 *  - Delete cities that are pure HTML / too long
 *  - Reassign orphaned bookers/customers to a real default city
 *  - Set all approved bookers to ACTIVE job status
 */
import { NextResponse } from "next/server";
import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";
import { prisma } from "@/lib/prisma";

export async function POST() {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ error: "Unauthorized" }, { status: 401 });

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

    const customerFixed = await prisma.customer.updateMany({
      where: { cityId: null as any },
      data: { cityId: defaultCity.id },
    });
    log.push(`✅ Assigned ${customerFixed.count} city-less customers to DEFAULT`);

    // Step 6: Activate all approved bookers
    const activated = await prisma.booker.updateMany({
      where: { adminApproved: "APPROVED" },
      data: { jobStatus: "ACTIVE" },
    });
    log.push(`✅ Activated ${activated.count} approved bookers`);

    // Step 7: Print final stats
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
