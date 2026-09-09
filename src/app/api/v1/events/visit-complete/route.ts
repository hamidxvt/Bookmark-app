import { NextResponse } from "next/server";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";
import { createEvent } from "@/lib/events";
import { prisma } from "@/lib/prisma";

export async function POST(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const body = await req.json();
    const visitId = body.visitId ? Number(body.visitId) : undefined;

    const booker = await prisma.booker.findUnique({
      where: { id: user.id },
      select: { id: true, name: true },
    });

    const event = await createEvent("visit-complete", {
      visitId,
      bookerId: user.id,
      bookerName: booker?.name ?? "Officer",
      customerName: body.customerName,
      message: body.message ?? "Visit marked complete",
    });

    return NextResponse.json({ success: true, data: { eventId: event.id } });
  } catch (err) {
    console.error("[events/visit-complete]", err);
    return NextResponse.json({ success: false, error: "Failed to record event" }, { status: 500 });
  }
}
