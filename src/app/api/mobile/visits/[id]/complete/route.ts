import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// POST /api/mobile/visits/[id]/complete
export async function POST(req: Request, { params }: { params: Promise<{ id: string }> }) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const { id } = await params;
    const visitId = parseInt(id);
    const body = await req.json();
    const { contactPerson, phone, visitType, notes, lat, lng } = body;

    const visit = await prisma.visit.findUnique({
      where: { id: visitId },
      select: { id: true, bookerId: true, status: true, isAdhoc: true },
    });

    if (!visit) {
      return NextResponse.json({ success: false, error: "Visit not found" }, { status: 404 });
    }

    if (visit.bookerId !== user.id) {
      return NextResponse.json({ success: false, error: "Not your visit" }, { status: 403 });
    }

    if (visit.status === "COMPLETED") {
      return NextResponse.json({ success: false, error: "Visit already completed" }, { status: 409 });
    }

    const updated = await prisma.visit.update({
      where: { id: visitId },
      data: {
        status: "COMPLETED",
        checkOutAt: new Date(),
        checkInLat: lat ?? null,
        checkInLng: lng ?? null,
        notes: notes ?? "",
        visitReport: [
          contactPerson ? `Contact: ${contactPerson}` : "",
          phone ? `Phone: ${phone}` : "",
          visitType ? `Type: ${visitType}` : "",
          notes ?? "",
        ].filter(Boolean).join("\n"),
      },
    });

    // Award reward points for completing an ad-hoc (new discovery) visit
    let rewardPointsEarned = 0;
    if (visit.isAdhoc) {
      rewardPointsEarned = 5; // 5 points per ad-hoc discovery
      await prisma.booker.update({
        where: { id: user.id },
        data: { rewardPoints: { increment: rewardPointsEarned } },
      }).catch(() => {});
    }

    return NextResponse.json({
      success: true,
      data: updated,
      message: "Visit completed",
      rewardPointsEarned,
    });
  } catch (err) {
    console.error("[mobile/visits/complete]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
