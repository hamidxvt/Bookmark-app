import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const status = searchParams.get("status") ?? undefined;

    const leaves = await prisma.leaveRequest.findMany({
      where: status ? { status } : {},
      include: {
        booker: { select: { id: true, name: true, email: true, phone: true } },
      },
      orderBy: { createdAt: "desc" },
      take: 100,
    });

    return NextResponse.json({ success: true, data: leaves });
  } catch (err) {
    console.error("[api/v1/leaves]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch leaves" }, { status: 500 });
  }
}

export async function PATCH(req: Request) {
  try {
    const { id, status, adminNotes } = await req.json();

    if (!id || !["approved", "rejected"].includes(status)) {
      return NextResponse.json({ success: false, error: "Invalid request" }, { status: 400 });
    }

    const leave = await prisma.leaveRequest.update({
      where: { id },
      data: { status, adminNotes: adminNotes ?? null, reviewedAt: new Date() },
    });

    return NextResponse.json({ success: true, data: leave });
  } catch (err) {
    console.error("[api/v1/leaves PATCH]", err);
    return NextResponse.json({ success: false, error: "Failed to update leave" }, { status: 500 });
  }
}
