import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// GET /api/mobile/payroll — shifts, attendance, salary for current month
export async function GET(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const now = new Date();
    const monthStart = new Date(now.getFullYear(), now.getMonth(), 1);
    const monthEnd = new Date(now.getFullYear(), now.getMonth() + 1, 0);

    // Get booker info for rate
    const booker = await prisma.booker.findUnique({
      where: { id: user.id },
      select: { ratesPerVisit: true, visitTargets: true, name: true },
    });

    const ratePerVisit = booker?.ratesPerVisit ? Number(booker.ratesPerVisit) : 500;

    // Attendance this month
    const attendance = await prisma.attendance.findMany({
      where: {
        bookerId: user.id,
        date: { gte: monthStart, lte: monthEnd },
      },
      orderBy: { date: "desc" },
    });

    // Completed visits this month
    const visits = await prisma.visit.findMany({
      where: {
        bookerId: user.id,
        visitDate: { gte: monthStart, lte: monthEnd },
        status: "COMPLETED",
      },
      select: { id: true, visitDate: true },
    });

    const totalShifts = attendance.filter(a => a.startAt != null).length;
    const presentDays = attendance.filter(a => a.status === "present").length;
    const absentDays = attendance.filter(a => a.status === "absent").length;
    const cannotWorkDays = attendance.filter(a => a.status === "cannot_work").length;

    const completedVisits = visits.length;
    const earnedFromVisits = completedVisits * ratePerVisit;

    // Build daily breakdown
    const dailyShifts = attendance.map(a => {
      const startAt = a.startAt ? new Date(a.startAt) : null;
      const endAt = a.endAt ? new Date(a.endAt) : null;
      const hoursWorked = startAt && endAt
        ? ((endAt.getTime() - startAt.getTime()) / 3600000).toFixed(1)
        : null;
      return {
        date: a.date,
        status: a.status,
        startAt: a.startAt,
        endAt: a.endAt,
        hoursWorked,
      };
    });

    return NextResponse.json({
      success: true,
      data: {
        month: now.toLocaleString("en-PK", { month: "long", year: "numeric" }),
        summary: {
          totalShifts,
          presentDays,
          absentDays,
          cannotWorkDays,
          completedVisits,
          ratePerVisit,
          earnedFromVisits,
          totalEarned: earnedFromVisits,
        },
        dailyShifts,
      },
    });
  } catch (err) {
    console.error("[mobile/payroll]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
