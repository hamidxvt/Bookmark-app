import { NextResponse } from "next/server";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";
import { createEvent } from "@/lib/events";
import { prisma } from "@/lib/prisma";

export async function POST(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const body = await req.json();
    const sampleId = body.sampleId ? Number(body.sampleId) : undefined;

    const booker = await prisma.booker.findUnique({
      where: { id: user.id },
      select: { id: true, name: true },
    });

    const event = await createEvent("sample-request", {
      sampleId,
      bookerId: user.id,
      bookerName: booker?.name ?? "Officer",
      productName: body.productName,
      message: body.message ?? "New sample request submitted",
    });

    return NextResponse.json({ success: true, data: { eventId: event.id } });
  } catch (err) {
    console.error("[events/sample-request]", err);
    return NextResponse.json({ success: false, error: "Failed to record event" }, { status: 500 });
  }
}
