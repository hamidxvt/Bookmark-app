import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// POST /api/mobile/visits/[id]/start
// Marks a visit as IN_PROGRESS and records GPS check-in time
export async function POST(
  req: Request,
  { params }: { params: Promise<{ id: string }> }
) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const { id } = await params;
    const visitId = parseInt(id);

    const body = await req.json().catch(() => ({}));
    const { lat, lng } = body;

    const visit = await prisma.visit.findUnique({
      where: { id: visitId },
      select: { id: true, bookerId: true, status: true },
    });

    if (!visit) {
      return NextResponse.json(
        { success: false, error: "Visit not found" },
        { status: 404 }
      );
    }

    if (visit.bookerId !== user.id) {
      return NextResponse.json(
        { success: false, error: "Not your visit" },
        { status: 403 }
      );
    }

    // Already started or completed — return current state without error
    if (visit.status === "IN_PROGRESS" || visit.status === "COMPLETED") {
      return NextResponse.json({
        success: true,
        data: { id: visit.id, status: visit.status.toLowerCase() },
        message: "Visit already in progress",
      });
    }

    const updated = await prisma.visit.update({
      where: { id: visitId },
      data: {
        status: "IN_PROGRESS",
        checkInAt: new Date(),
        checkInLat: lat ?? null,
        checkInLng: lng ?? null,
      },
      select: { id: true, status: true, checkInAt: true },
    });

    return NextResponse.json({
      success: true,
      data: {
        id: updated.id,
        status: updated.status.toLowerCase(),
        checkInAt: updated.checkInAt,
      },
      message: "Visit started",
    });
  } catch (err) {
    console.error("[mobile/visits/start]", err);
    return NextResponse.json(
      { success: false, error: "Server error" },
      { status: 500 }
    );
  }
}
