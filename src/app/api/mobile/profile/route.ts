import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// GET /api/mobile/profile — full officer profile with salary + stats
export async function GET(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const now        = new Date();
    const monthStart = new Date(now.getFullYear(), now.getMonth(), 1);
    const monthEnd   = new Date(now.getFullYear(), now.getMonth() + 1, 0);

    const booker = await prisma.booker.findUnique({
      where: { id: user.id },
      select: {
        id: true,
        name: true,
        email: true,
        phone: true,
        designation: true,
        profilePhoto: true,
        jobStatus: true,
        basicSalary: true,
        ratesPerVisit: true,
        securityDepositPct: true,
        rewardPoints: true,
        city: { select: { id: true, name: true } },
      },
    });

    if (!booker) {
      return NextResponse.json({ success: false, error: "Officer not found" }, { status: 404 });
    }

    // Monthly stats
    const [totalVisits, completedVisits, cancelledVisits, attendance] = await Promise.all([
      prisma.visit.count({
        where: { bookerId: user.id, visitDate: { gte: monthStart, lte: monthEnd } },
      }),
      prisma.visit.count({
        where: { bookerId: user.id, visitDate: { gte: monthStart, lte: monthEnd }, status: "COMPLETED" },
      }),
      prisma.visit.count({
        where: { bookerId: user.id, visitDate: { gte: monthStart, lte: monthEnd }, status: "CANCELLED" },
      }),
      prisma.attendance.findMany({
        where: { bookerId: user.id, date: { gte: monthStart, lte: monthEnd } },
      }),
    ]);

    const shiftsWorked    = attendance.filter(a => a.startAt != null).length;
    const basicSalary     = Number(booker.basicSalary)        || 0;
    const ratePerVisit    = Number(booker.ratesPerVisit)       || 500;
    const secDepositPct   = Number(booker.securityDepositPct)  || 10;
    const runningPay      = completedVisits * ratePerVisit;
    const secDeposit      = Math.round(basicSalary * secDepositPct / 100);
    const netSalary       = basicSalary + runningPay - secDeposit;

    return NextResponse.json({
      success: true,
      data: {
        id: booker.id,
        name: booker.name,
        email: booker.email,
        phone: booker.phone,
        designation: booker.designation ?? "Sales Officer",
        profilePhoto: booker.profilePhoto,
        jobStatus: booker.jobStatus,
        city: booker.city?.name ?? null,
        stats: {
          totalVisits,
          completedVisits,
          cancelledVisits,
          shiftsWorked,
          month: now.toLocaleString("en-PK", { month: "long", year: "numeric" }),
        },
        salary: {
          basicSalary,
          ratePerVisit,
          runningPay,
          securityDepositHeld: secDeposit,
          netSalary,
          rewardPoints: booker.rewardPoints ?? 0,
          rewardValue: (booker.rewardPoints ?? 0) * 10,
        },
      },
    });
  } catch (err) {
    console.error("[mobile/profile]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}

// PATCH /api/mobile/profile — update profile photo (base64 data URL)
export async function PATCH(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();
  try {
    const { profilePhoto } = await req.json();
    if (!profilePhoto) {
      return NextResponse.json({ success: false, error: "profilePhoto required" }, { status: 400 });
    }
    await prisma.booker.update({
      where: { id: user.id },
      data: { profilePhoto: String(profilePhoto) },
    });
    return NextResponse.json({ success: true });
  } catch (err) {
    console.error("[mobile/profile PATCH]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
