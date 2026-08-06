import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

/**
 * POST /api/v1/fix-all-corrupted-data
 * 
 * Nuclear option: clean up ALL corrupted cities, officer assignments, etc.
 * - Delete all cities with HTML/corrupted names
 * - Reassign all officers to real cities (THANA MALAKAND, KARACHI, etc)
 */
export async function POST() {
  const log: string[] = [];

  try {
    // 1. Find all real cities (clean names, no HTML)
    const realCities = await prisma.city.findMany({
      where: {
        name: {
          in: ["THANA MALAKAND", "LAHORE", "KARACHI", "ISLAMABAD", "MULTAN", "DEFAULT"],
        },
      },
      select: { id: true, name: true },
    });
    log.push(`✅ Found ${realCities.length} real cities`);

    const thanaMalakand = realCities.find(c => c.name === "THANA MALAKAND");
    if (!thanaMalakand) {
      throw new Error("THANA MALAKAND not found!");
    }

    // 2. Find all corrupted cities (have HTML, long names, etc.)
    const corruptedCities = await prisma.city.findMany({
      where: {
        OR: [
          { name: { contains: "<" } },
          { name: { contains: "HEADWAY" } },
          { name: { contains: "MEDIA" } },
          { name: { contains: "\r\n" } },
          { name: { contains: "\n" } },
          { name: { gt: "ZZZZZZZZZZZZZZZ" } }, // names longer than typical city names
        ],
      },
      select: { id: true, name: true },
    });
    log.push(`✅ Found ${corruptedCities.length} corrupted cities`);

    const corruptedIds = corruptedCities.map(c => c.id);

    // 3. Reassign all officers with corrupted cities to THANA MALAKAND
    if (corruptedIds.length > 0) {
      const reassigned = await prisma.booker.updateMany({
        where: { cityId: { in: corruptedIds } },
        data: { cityId: thanaMalakand.id },
      });
      log.push(`✅ Reassigned ${reassigned.count} officers to THANA MALAKAND`);

      // 4. Reassign all customers with corrupted cities
      const customerReassigned = await prisma.customer.updateMany({
        where: { cityId: { in: corruptedIds } },
        data: { cityId: thanaMalakand.id },
      });
      log.push(`✅ Reassigned ${customerReassigned.count} customers to THANA MALAKAND`);

      // 5. Delete the corrupted cities
      const deleted = await prisma.city.deleteMany({
        where: { id: { in: corruptedIds } },
      });
      log.push(`✅ Deleted ${deleted.count} corrupted cities`);
    }

    // 6. Ensure all approved bookers are ACTIVE
    const activated = await prisma.booker.updateMany({
      where: { adminApproved: "APPROVED" },
      data: { jobStatus: "ACTIVE" },
    });
    log.push(`✅ Activated ${activated.count} approved bookers`);

    // 7. Final stats
    const [cities, officers, customers] = await Promise.all([
      prisma.city.count(),
      prisma.booker.count({ where: { jobStatus: "ACTIVE" } }),
      prisma.customer.count({ where: { approvalStatus: "APPROVED" } }),
    ]);
    log.push(`📊 Final: ${cities} clean cities · ${officers} active officers · ${customers} approved customers`);

    return NextResponse.json({ success: true, log });
  } catch (error) {
    console.error("[fix-all-corrupted-data]", error);
    return NextResponse.json(
      { success: false, error: String(error), log },
      { status: 500 }
    );
  }
}
