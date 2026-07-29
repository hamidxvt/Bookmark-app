import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET() {
  try {
    // Last 6 months of visit data grouped by month
    const sixMonthsAgo = new Date();
    sixMonthsAgo.setMonth(sixMonthsAgo.getMonth() - 5);
    sixMonthsAgo.setDate(1);
    sixMonthsAgo.setHours(0, 0, 0, 0);

    const visits = await prisma.visit.findMany({
      where: { visitDate: { gte: sixMonthsAgo } },
      select: { visitDate: true, status: true },
    });

    // Group by year-month
    const grouped: Record<string, { completed: number; pending: number }> = {};

    for (const v of visits) {
      const d = new Date(v.visitDate);
      const key = d.toLocaleString("en-US", { month: "short", year: "2-digit" });
      if (!grouped[key]) grouped[key] = { completed: 0, pending: 0 };
      if (v.status === "COMPLETED") grouped[key].completed++;
      else grouped[key].pending++;
    }

    // Build sorted array for the last 6 months
    const months: Array<{ month: string; completed: number; pending: number }> = [];
    for (let i = 5; i >= 0; i--) {
      const d = new Date();
      d.setMonth(d.getMonth() - i);
      const key = d.toLocaleString("en-US", { month: "short", year: "2-digit" });
      const label = d.toLocaleString("en-US", { month: "short" });
      months.push({
        month: label,
        completed: grouped[key]?.completed ?? 0,
        pending: grouped[key]?.pending ?? 0,
      });
    }

    // City breakdown
    const cityBreakdown = await prisma.customer.groupBy({
      by: ["cityId"],
      where: { deletedAt: null },
      _count: { id: true },
    });

    type CityGroup = typeof cityBreakdown[number];
    const cities = await prisma.city.findMany({
      where: { id: { in: cityBreakdown.map((c: CityGroup) => c.cityId) } },
      select: { id: true, name: true },
    });

    const cityMap = Object.fromEntries(cities.map((c: typeof cities[number]) => [c.id, c.name]));
    const COLORS = ["#14b8a6", "#6366f1", "#f59e0b", "#ef4444", "#8b5cf6", "#ec4899"];

    const cityData = cityBreakdown
      .sort((a: CityGroup, b: CityGroup) => b._count.id - a._count.id)
      .slice(0, 6)
      .map((c: CityGroup, i: number) => ({
        name: cityMap[c.cityId] ?? `City ${c.cityId}`,
        value: c._count.id,
        color: COLORS[i % COLORS.length],
      }));

    return NextResponse.json({ success: true, data: { trend: months, cityBreakdown: cityData } });
  } catch (err) {
    console.error("[api/v1/visits/trend]", err);
    return NextResponse.json({ success: false, error: "Failed" }, { status: 500 });
  }
}
