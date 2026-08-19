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
    const { contactPerson, phone, visitType, notes, lat, lng, followUpDate, contactPhone } = body;

    const visit = await prisma.visit.findUnique({
      where: { id: visitId },
      select: { id: true, bookerId: true, status: true, isAdhoc: true, customerId: true },
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
          (phone || contactPhone) ? `Phone: ${phone ?? contactPhone}` : "",
          visitType ? `Type: ${visitType}` : "",
          notes ?? "",
        ].filter(Boolean).join("\n"),
      },
    });

    // Award reward points for completing an ad-hoc (new discovery) visit
    let rewardPointsEarned = 0;
    if (visit.isAdhoc) {
      rewardPointsEarned = 5;
      await prisma.booker.update({
        where: { id: user.id },
        data: { rewardPoints: { increment: rewardPointsEarned } },
      }).catch(() => {});
    }

    // Create a follow-up visit if a follow-up date was specified
    let followUpCreated = false;
    if (followUpDate && visit.customerId) {
      try {
        const fuDate = new Date(followUpDate);
        fuDate.setHours(9, 0, 0, 0); // schedule for 9am on that day
        await prisma.visit.create({
          data: {
            bookerId: user.id,
            customerId: visit.customerId,
            scheduledDate: fuDate,
            status: "PENDING",
            dailySequence: 99,
            visitType: "follow_up",
            notes: `Follow-up from visit #${visitId} on ${new Date().toLocaleDateString()}`,
            isAdhoc: false,
          },
        });
        followUpCreated = true;
      } catch (e) {
        console.error("[follow-up visit creation]", e);
      }
    }

    return NextResponse.json({
      success: true,
      data: updated,
      message: followUpCreated
        ? "Visit completed! Follow-up scheduled."
        : "Visit completed",
      rewardPointsEarned,
      followUpCreated,
    });
  } catch (err) {
    console.error("[mobile/visits/complete]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
