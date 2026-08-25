import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const month = parseInt(searchParams.get("month") ?? String(new Date().getMonth() + 1));
    const year  = parseInt(searchParams.get("year")  ?? String(new Date().getFullYear()));

    const monthStart = new Date(year, month - 1, 1);
    const monthEnd   = new Date(year, month,     1);

    // Fetch all active bookers with salary info
    const bookers = await prisma.booker.findMany({
      where: { deletedAt: null, jobStatus: "ACTIVE" },
      select: {
        id: true, name: true,
        basicSalary: true, ratesPerVisit: true, visitTargets: true,
      },
    });

    // Fetch attendance and visits for the month in a single batch
    const [attendanceRecords, visitRecords] = await Promise.all([
      prisma.attendance.findMany({
        where: { date: { gte: monthStart, lt: monthEnd }, status: "present" },
        select: { bookerId: true },
      }),
      prisma.visit.findMany({
        where: { visitDate: { gte: monthStart, lt: monthEnd }, status: "COMPLETED" },
        select: { bookerId: true },
      }),
    ]);

    // Group counts per booker
    const presentByBooker: Record<number, number> = {};
    attendanceRecords.forEach(a => { presentByBooker[a.bookerId] = (presentByBooker[a.bookerId] ?? 0) + 1; });

    const visitsByBooker: Record<number, number> = {};
    visitRecords.forEach(v => { visitsByBooker[v.bookerId] = (visitsByBooker[v.bookerId] ?? 0) + 1; });

    const records = bookers.map(b => {
      const basic        = Number(b.basicSalary ?? 0);
      const rate         = Number(b.ratesPerVisit ?? 0);
      const presentDays  = presentByBooker[b.id] ?? 0;
      const completedVisits = visitsByBooker[b.id] ?? 0;
      const performance  = completedVisits * rate;
      const totalPay     = basic + performance;
      return {
        bookerId: b.id,
        bookerName: b.name,
        presentDays,
        basicSalary: basic,
        ratesPerVisit: rate,
        completedVisits,
        performanceEarned: performance,
        totalPay,
      };
    });

    return NextResponse.json({
      success: true,
      data: records,
      meta: { month, year, monthName: monthStart.toLocaleString("en", { month: "long" }) },
    });
  } catch (err) {
    console.error("[api/v1/payroll]", err);
    return NextResponse.json({ success: false, error: "Failed to calculate payroll" }, { status: 500 });
  }
}
