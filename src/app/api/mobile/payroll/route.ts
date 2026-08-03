import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// GET /api/mobile/payroll — full salary breakdown for current month
export async function GET(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const now = new Date();
    const monthStart = new Date(now.getFullYear(), now.getMonth(), 1);
    const monthEnd = new Date(now.getFullYear(), now.getMonth() + 1, 0);
    const daysInMonth = monthEnd.getDate();

    const booker = await prisma.booker.findUnique({
      where: { id: user.id },
      select: {
        ratesPerVisit: true,
        visitTargets: true,
        name: true,
        basicSalary: true,
        securityDepositPct: true,
        rewardPoints: true,
      },
    });

    const ratePerVisit    = booker?.ratesPerVisit     ? Number(booker.ratesPerVisit)     : 500;
    const basicSalary     = booker?.basicSalary        ? Number(booker.basicSalary)        : 0;
    const secDepositPct   = booker?.securityDepositPct ? Number(booker.securityDepositPct) : 10;
    const rewardPoints    = booker?.rewardPoints ?? 0;

    // Attendance
    const attendance = await prisma.attendance.findMany({
      where: { bookerId: user.id, date: { gte: monthStart, lte: monthEnd } },
      orderBy: { date: "desc" },
    });

    // Completed visits this month
    const completedVisits = await prisma.visit.count({
      where: {
        bookerId: user.id,
        visitDate: { gte: monthStart, lte: monthEnd },
        status: "COMPLETED",
      },
    });

    // Ad-hoc (bonus) visits this month
    const adhocVisits = await prisma.visit.count({
      where: {
        bookerId: user.id,
        visitDate: { gte: monthStart, lte: monthEnd },
        status: "COMPLETED",
        isAdhoc: true,
      },
    });

    const presentDays    = attendance.filter(a => a.status === "present").length;
    const absentDays     = attendance.filter(a => a.status === "absent").length;
    const cannotWorkDays = attendance.filter(a => a.status === "cannot_work").length;
    const totalShifts    = attendance.filter(a => a.startAt != null).length;

    // Salary components
    const runningPay        = completedVisits * ratePerVisit;          // per-visit performance pay
    const adhocBonus        = adhocVisits * Math.round(ratePerVisit * 0.5); // 50% bonus per adhoc
    const dailyBasic        = basicSalary / daysInMonth;
    const earnedBasic       = Math.round(dailyBasic * presentDays);   // basic proportional to attendance
    const securityDeposit   = Math.round(basicSalary * secDepositPct / 100); // monthly portion held
    const grossSalary       = earnedBasic + runningPay + adhocBonus;
    const netSalary         = grossSalary - securityDeposit;
    const rewardValue       = rewardPoints * 10; // 10 PKR per point

    // Daily shift breakdown
    const dailyShifts = attendance.map(a => ({
      date: a.date,
      status: a.status,
      startAt: a.startAt,
      endAt: a.endAt,
      hoursWorked: a.startAt && a.endAt
        ? ((new Date(a.endAt).getTime() - new Date(a.startAt).getTime()) / 3600000).toFixed(1)
        : null,
    }));

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
          adhocVisits,
          ratePerVisit,
        },
        salaryBreakdown: {
          basicSalary,
          earnedBasic,
          runningPay,
          adhocBonus,
          grossSalary,
          securityDepositHeld: securityDeposit,
          netSalary,
          rewardPoints,
          rewardValue,
          totalEarned: netSalary + rewardValue,
        },
        dailyShifts,
      },
    });
  } catch (err) {
    console.error("[mobile/payroll]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
