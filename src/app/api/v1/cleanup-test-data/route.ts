import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

/**
 * POST /api/v1/cleanup-test-data
 * 
 * - Deletes "Test Officer" accounts
 * - Resets invalid coordinates (outside Pakistan bounds) to NULL for real officers
 * - Strips HTML from officer names
 * 
 * No auth required — internal admin endpoint
 */
export async function POST() {
  try {
    // 1. Delete known test officers (name contains "Test" or "test@" email)
    const testOfficers = await prisma.booker.findMany({
      where: {
        OR: [
          { name: { contains: "Test Officer", mode: "insensitive" } },
          { email: { contains: "test@example", mode: "insensitive" } },
          { email: "test@test.com" },
        ],
      },
      select: { id: true, name: true, email: true },
    });

    const deleteIds = testOfficers.map(t => t.id);
    if (deleteIds.length > 0) {
      await prisma.visit.deleteMany({ where: { bookerId: { in: deleteIds } } });
      await prisma.attendance.deleteMany({ where: { bookerId: { in: deleteIds } } });
      await prisma.leaveRequest.deleteMany({ where: { bookerId: { in: deleteIds } } });
      await prisma.booker.deleteMany({ where: { id: { in: deleteIds } } });
    }

    // 2. Reset invalid coordinates to NULL for real officers
    // Pakistan valid bounds: lat 20-40, lng 55-80
    // Any coordinates outside this (e.g., California -122 longitude) are reset
    const allBookers = await prisma.booker.findMany({
      where: {
        lastLatitude: { not: null },
        lastLongitude: { not: null },
      },
      select: { id: true, name: true, lastLatitude: true, lastLongitude: true },
    });

    const invalidCoordIds = allBookers
      .filter(b => {
        const lat = Number(b.lastLatitude);
        const lng = Number(b.lastLongitude);
        return !(lat >= 20 && lat <= 40 && lng >= 55 && lng <= 80);
      })
      .map(b => b.id);

    if (invalidCoordIds.length > 0) {
      await prisma.booker.updateMany({
        where: { id: { in: invalidCoordIds } },
        data: { lastLatitude: null, lastLongitude: null, gpsStatus: "OFFLINE" },
      });
    }

    // 3. Strip HTML from officer names
    const bookers = await prisma.booker.findMany({
      select: { id: true, name: true },
    });

    let strippedCount = 0;
    for (const b of bookers) {
      const clean = b.name?.replace(/<[^>]*>/g, "").replace(/&[a-z]+;/gi, " ").replace(/\s+/g, " ").trim();
      if (clean !== b.name && clean) {
        await prisma.booker.update({ where: { id: b.id }, data: { name: clean } });
        strippedCount++;
      }
    }

    return NextResponse.json({
      success: true,
      deleted_test_officers: testOfficers.length,
      reset_invalid_coords: invalidCoordIds.length,
      stripped_html_names: strippedCount,
      message: `Done: removed ${testOfficers.length} test officers, reset ${invalidCoordIds.length} invalid coordinates, cleaned ${strippedCount} names`,
    });
  } catch (error) {
    console.error("[cleanup-test-data]", error);
    return NextResponse.json(
      { success: false, error: String(error) },
      { status: 500 }
    );
  }
}
