import { NextResponse } from "next/server";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";
import { createEvent } from "@/lib/events";
import { prisma } from "@/lib/prisma";

export async function POST(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const body = await req.json();
    const leaveId = body.leaveId ? Number(body.leaveId) : undefined;

    const booker = await prisma.booker.findUnique({
      where: { id: user.id },
      select: { id: true, name: true },
    });

    const event = await createEvent("leave-request", {
      leaveId,
      bookerId: user.id,
      bookerName: booker?.name ?? "Officer",
      message: body.message ?? "New leave request submitted",
    });

    return NextResponse.json({ success: true, data: { eventId: event.id } });
  } catch (err) {
    console.error("[events/leave-request]", err);
    return NextResponse.json({ success: false, error: "Failed to record event" }, { status: 500 });
  }
}
