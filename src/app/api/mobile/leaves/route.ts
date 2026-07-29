import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

type LeaveRecord = Awaited<ReturnType<typeof prisma.leaveRequest.findMany>>[number];

// GET /api/mobile/leaves — leave balance + history
export async function GET(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const currentYear = new Date().getFullYear();
    const yearStart = new Date(`${currentYear}-01-01`);

    const leaves = await prisma.leaveRequest.findMany({
      where: { bookerId: user.id, fromDate: { gte: yearStart } },
      orderBy: { createdAt: "desc" },
      take: 30,
    });

    const approved = leaves.filter((l: LeaveRecord) => l.status === "approved");
    const sickTaken = approved
      .filter((l: LeaveRecord) => l.leaveType === "sick")
      .reduce((sum: number, l: LeaveRecord) => {
        const days = Math.ceil((l.toDate.getTime() - l.fromDate.getTime()) / 86400000) + 1;
        return sum + days;
      }, 0);
    const casualTaken = approved
      .filter((l: LeaveRecord) => l.leaveType === "casual")
      .reduce((sum: number, l: LeaveRecord) => {
        const days = Math.ceil((l.toDate.getTime() - l.fromDate.getTime()) / 86400000) + 1;
        return sum + days;
      }, 0);

    return NextResponse.json({
      success: true,
      data: {
        balance: {
          sickTotal: 10,
          casualTotal: 18,
          sickTaken,
          casualTaken,
          sickRemaining: Math.max(0, 10 - sickTaken),
          casualRemaining: Math.max(0, 18 - casualTaken),
        },
        history: leaves.map((l: LeaveRecord) => ({
          id: l.id,
          type: l.leaveType,
          fromDate: l.fromDate,
          toDate: l.toDate,
          reason: l.reason,
          status: l.status,
          adminNotes: l.adminNotes,
          appliedAt: l.createdAt,
        })),
      },
    });
  } catch (err) {
    console.error("[mobile/leaves GET]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}

// POST /api/mobile/leaves — apply for leave
export async function POST(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const { leaveType, fromDate, toDate, reason } = await req.json();

    if (!leaveType || !fromDate || !toDate || !reason) {
      return NextResponse.json({ success: false, error: "All fields are required" }, { status: 400 });
    }

    if (!["sick", "casual"].includes(leaveType)) {
      return NextResponse.json({ success: false, error: "Invalid leave type" }, { status: 400 });
    }

    const from = new Date(fromDate);
    const to = new Date(toDate);
    if (from > to) {
      return NextResponse.json({ success: false, error: "From date must be before to date" }, { status: 400 });
    }

    const leave = await prisma.leaveRequest.create({
      data: {
        bookerId: user.id,
        leaveType,
        fromDate: from,
        toDate: to,
        reason,
        status: "pending",
      },
    });

    return NextResponse.json({ success: true, data: leave, message: "Leave request submitted" });
  } catch (err) {
    console.error("[mobile/leaves POST]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
