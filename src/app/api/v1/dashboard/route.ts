import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET() {
  try {
    const today = new Date();
    today.setHours(0, 0, 0, 0);
    const tomorrow = new Date(today.getTime() + 24 * 60 * 60 * 1000);

    const [
      totalBookers,
      totalCustomers,
      totalVisits,
      visitsToday,
      totalProducts,
      pendingRequests,
      pendingLeaves,
      pendingMissedVisits,
    ] = await Promise.all([
      // Match the same criteria as live-activity: approved + not deleted
      prisma.booker.count({ where: { adminApproved: "APPROVED", deletedAt: null } }),
      prisma.customer.count({ where: { deletedAt: null } }),
      prisma.visit.count(),
      // Use a proper date range so all visits within today are counted
      prisma.visit.count({ where: { visitDate: { gte: today, lt: tomorrow } } }),
      prisma.product.count(),
      prisma.request.count({ where: { status: "PENDING" } }),
      prisma.leaveRequest.count({ where: { status: "pending" } }),
      prisma.missedVisitReason.count({ where: { status: "pending" } }),
    ]);

    return NextResponse.json({
      success: true,
      data: {
        totalBookers,
        totalCustomers,
        totalVisits,
        visitsToday,
        totalProducts,
        pendingRequests,
        pendingLeaves,
        pendingMissedVisits,
      },
    });
  } catch (err) {
    console.error("[api/v1/dashboard]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch stats" }, { status: 500 });
  }
}
