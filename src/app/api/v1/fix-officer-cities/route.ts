import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

/**
 * POST /api/v1/fix-officer-cities
 * 
 * Resets invalid officer city assignments (where cityId points to invalid/null city)
 * and ensures all officers have a valid city from the cities table.
 */
export async function POST() {
  try {
    // Find officers with null or non-existent city
    const allOfficers = await prisma.booker.findMany({
      select: { id: true, name: true, cityId: true, city: { select: { name: true } } },
    });

    let reset = 0;
    let assigned = 0;

    // Get a default city (Thana Malakand)
    const defaultCity = await prisma.city.findFirst({
      where: { name: { contains: "THANA", mode: "insensitive" } },
    });

    for (const officer of allOfficers) {
      let needsUpdate = false;
      let newCityId = officer.cityId;

      // If cityId is invalid or null, reset to default city
      if (!officer.cityId) {
        newCityId = defaultCity?.id || null;
        needsUpdate = true;
      } else if (!officer.city) {
        // cityId exists but city doesn't (corrupted)
        newCityId = defaultCity?.id || null;
        needsUpdate = true;
      }

      if (needsUpdate) {
        await prisma.booker.update({
          where: { id: officer.id },
          data: { cityId: newCityId },
        });
        reset++;
      }
    }

    // Also, assign Thana customers to Thana officers if they don't have customers
    const thanaCity = defaultCity;
    if (thanaCity) {
      const thanaBoosters = await prisma.booker.findMany({
        where: { cityId: thanaCity.id },
        select: { id: true },
      });

      // Just return a report
      assigned = thanaBoosters.length;
    }

    return NextResponse.json({
      success: true,
      reset_invalid_cities: reset,
      officers_in_thana: assigned,
      message: `Reset ${reset} officers to valid city assignment`,
    });
  } catch (error) {
    console.error("[fix-officer-cities]", error);
    return NextResponse.json(
      { success: false, error: String(error) },
      { status: 500 }
    );
  }
}
