import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

/**
 * DELETE /api/v1/cleanup-test-data
 * 
 * Removes test/dummy officers from the database.
 * Use this to clean up any test data that was created for development/debugging.
 * 
 * Headers:
 *   Authorization: Bearer <admin-secret-key>
 */

export async function DELETE(req: Request) {
  try {
    // Simple auth check - in production use proper JWT
    const auth = req.headers.get("authorization");
    const adminSecret = process.env.ADMIN_SECRET || "bookmark-admin-secret";
    
    if (!auth || !auth.includes(adminSecret)) {
      return NextResponse.json(
        { success: false, error: "Unauthorized" },
        { status: 401 }
      );
    }

    // Find and delete test officers
    const testOfficers = await prisma.booker.findMany({
      where: {
        OR: [
          { name: { contains: "Test", mode: "insensitive" } },
          { email: { contains: "test@", mode: "insensitive" } },
          { phone: "0000000000" },
          // Specific coordinates for Mountain View, CA test user
          { AND: [{ lastLatitude: { equals: 37.42200 } }, { lastLongitude: { equals: -122.08400 } }] },
        ],
      },
      select: { id: true, name: true, email: true },
    });

    const deletedIds = testOfficers.map(t => t.id);

    if (deletedIds.length > 0) {
      // Delete in order (respect foreign keys)
      await prisma.visit.deleteMany({
        where: { bookerId: { in: deletedIds } },
      });

      await prisma.attendance.deleteMany({
        where: { bookerId: { in: deletedIds } },
      });

      await prisma.leaveRequest.deleteMany({
        where: { bookerId: { in: deletedIds } },
      });

      await prisma.booker.deleteMany({
        where: { id: { in: deletedIds } },
      });
    }

    return NextResponse.json({
      success: true,
      message: `Cleaned up ${deletedIds.length} test officer(s)`,
      deleted: testOfficers.map(t => ({ id: t.id, name: t.name, email: t.email })),
    });
  } catch (error) {
    console.error("[cleanup-test-data]", error);
    return NextResponse.json(
      { success: false, error: String(error) },
      { status: 500 }
    );
  }
}
