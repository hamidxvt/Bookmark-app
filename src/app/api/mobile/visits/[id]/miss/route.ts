import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// POST /api/mobile/visits/[id]/miss
export async function POST(req: Request, { params }: { params: Promise<{ id: string }> }) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const { id } = await params;
    const visitId = parseInt(id);
    const body = await req.json();
    const { reason } = body;

    if (!reason?.trim()) {
      return NextResponse.json({ success: false, error: "Reason is required" }, { status: 400 });
    }

    const visit = await prisma.visit.findUnique({
      where: { id: visitId },
      select: { id: true, bookerId: true, status: true },
    });

    if (!visit) {
      return NextResponse.json({ success: false, error: "Visit not found" }, { status: 404 });
    }

    if (visit.bookerId !== user.id) {
      return NextResponse.json({ success: false, error: "Not your visit" }, { status: 403 });
    }

    await prisma.$transaction([
      prisma.visit.update({
        where: { id: visitId },
        data: { status: "CANCELLED" },
      }),
      prisma.missedVisitReason.upsert({
        where: { visitId },
        create: { visitId, bookerId: user.id, reason, status: "pending" },
        update: { reason, status: "pending" },
      }),
    ]);

    return NextResponse.json({ success: true, message: "Missed visit recorded, pending admin review" });
  } catch (err) {
    console.error("[mobile/visits/miss]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
