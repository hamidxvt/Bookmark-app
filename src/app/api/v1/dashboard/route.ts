import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET() {
  try {
    const today = new Date();
    today.setHours(0, 0, 0, 0);
    const tomorrow = new Date(today.getTime() + 24 * 60 * 60 * 1000);

    const sixMonthsAgo = new Date(today);
    sixMonthsAgo.setMonth(sixMonthsAgo.getMonth() - 5);
    sixMonthsAgo.setDate(1);

    const [
      totalBookers,
      totalCustomers,
      totalVisits,
      visitsToday,
      totalProducts,
      pendingRequests,
      pendingLeaves,
      missedVisits,
      customersByCityRaw,
      recentVisitsRaw,
      monthlyVisitsRaw,
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
      prisma.customer.groupBy({
        by: ["cityId"],
        where: { deletedAt: null },
        _count: { _all: true },
        orderBy: { _count: { cityId: "desc" } },
        take: 5,
      }),
      prisma.visit.findMany({
        take: 6,
        orderBy: { visitDate: "desc" },
        select: {
          id: true,
          status: true,
          visitDate: true,
          booker: { select: { name: true } },
          customer: { select: { name: true, city: { select: { name: true } } } },
        },
      }),
      prisma.visit.findMany({
        where: { visitDate: { gte: sixMonthsAgo } },
        select: { visitDate: true, status: true },
      }),
    ]);

    const cityIds = customersByCityRaw.map((c) => c.cityId);
    const cities = await prisma.city.findMany({
      where: { id: { in: cityIds } },
      select: { id: true, name: true },
    });
    const cityNameById = new Map(cities.map((c) => [c.id, c.name]));
    const customersByCity = customersByCityRaw.map((c) => ({
      city: cityNameById.get(c.cityId) ?? "Unknown",
      count: c._count._all,
    }));

    const recentVisits = recentVisitsRaw.map((v) => ({
      id: String(v.id),
      bookerName: v.booker?.name ?? "—",
      customerName: v.customer?.name ?? "—",
      status: v.status,
      visitDate: v.visitDate.toISOString(),
      city: v.customer?.city?.name,
    }));

    const monthBuckets = new Map<string, { completed: number; pending: number }>();
    for (let i = 5; i >= 0; i--) {
      const d = new Date(today.getFullYear(), today.getMonth() - i, 1);
      const key = d.toLocaleDateString("en-US", { month: "short" });
      monthBuckets.set(key, { completed: 0, pending: 0 });
    }
    for (const v of monthlyVisitsRaw) {
      const key = v.visitDate.toLocaleDateString("en-US", { month: "short" });
      const bucket = monthBuckets.get(key);
      if (!bucket) continue;
      if (v.status === "COMPLETED") bucket.completed++;
      else bucket.pending++;
    }
    const monthlyVisits = Array.from(monthBuckets.entries()).map(([month, counts]) => ({
      month,
      ...counts,
    }));

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
        missedVisits,
        monthlyVisits,
        customersByCity,
        recentVisits,
      },
    });
  } catch (err) {
    console.error("[api/v1/dashboard]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch stats" }, { status: 500 });
  }
}
