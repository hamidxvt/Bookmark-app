/**
 * POST /api/v1/cleanup
 * One-time DB cleanup:
 *  - Removes cities with HTML names (created by bad migration)
 *  - Reassigns orphaned customers to a default city
 *  - Sets all bookers to ACTIVE job status
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
    // 1. Find and delete HTML city names
    const allCities = await prisma.city.findMany({ select: { id: true, name: true } });
    const htmlCities = allCities.filter(c => c.name.includes("<") || c.name.includes(">") || c.name.length > 100);
    log.push(`Found ${htmlCities.length} HTML city names to clean`);

    // Get or create a DEFAULT fallback city
    let defaultCity = await prisma.city.findFirst({ where: { name: "DEFAULT" } });
    if (!defaultCity) {
      defaultCity = await prisma.city.create({ data: { name: "DEFAULT" } });
    }

    // Reassign customers from HTML cities to DEFAULT
    if (htmlCities.length > 0) {
      const htmlCityIds = htmlCities.map(c => c.id);
      const reassigned = await prisma.customer.updateMany({
        where: { cityId: { in: htmlCityIds } },
        data: { cityId: defaultCity.id },
      });
      log.push(`Reassigned ${reassigned.count} customers from HTML cities to DEFAULT`);

      // Reassign bookers too
      await prisma.booker.updateMany({
        where: { cityId: { in: htmlCityIds } },
        data: { cityId: defaultCity.id },
      });

      // Now safely delete HTML cities
      await prisma.city.deleteMany({ where: { id: { in: htmlCityIds } } });
      log.push(`Deleted ${htmlCities.length} HTML cities`);
    }

    // 2. Fix city names — trim and uppercase
    const cleanCities = await prisma.city.findMany({ select: { id: true, name: true } });
    let fixedNames = 0;
    for (const city of cleanCities) {
      const clean = city.name.trim().toUpperCase().substring(0, 100);
      if (clean !== city.name) {
        await prisma.city.update({ where: { id: city.id }, data: { name: clean } }).catch(() => {});
        fixedNames++;
      }
    }
    log.push(`Fixed ${fixedNames} city names`);

    // 3. Set all migrated bookers to ACTIVE (they came in as NOT_ACTIVE from migration)
    const activated = await prisma.booker.updateMany({
      where: { adminApproved: "APPROVED" },
      data: { jobStatus: "ACTIVE" },
    });
    log.push(`Activated ${activated.count} bookers`);

    // 4. Count final stats
    const [cities, customers, bookers] = await Promise.all([
      prisma.city.count(),
      prisma.customer.count(),
      prisma.booker.count({ where: { jobStatus: "ACTIVE" } }),
    ]);

    log.push(`✅ Final: ${cities} cities, ${customers} customers, ${bookers} active bookers`);

    return NextResponse.json({ success: true, log });
  } catch (err: any) {
    return NextResponse.json({ success: false, error: err.message, log }, { status: 500 });
  }
}
