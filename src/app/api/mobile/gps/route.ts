import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

export async function POST(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const body = await req.json();
    const { lat, lng, accuracy, isMock } = body;

    if (lat === undefined || lng === undefined) {
      return NextResponse.json({ success: false, error: "Missing lat/lng" }, { status: 400 });
    }

    // Update booker's last-seen location (for Live Map)
    await prisma.booker.updateMany({
      where: { id: user.id, deletedAt: null },
      data: {
        lastLatitude: lat,
        lastLongitude: lng,
        gpsStatus: isMock ? "MOCK" : "ACTIVE",
        lastSeenAt: new Date(),
      },
    });

    // Record every ping in gps_pings for full-day trail tracking
    await prisma.gpsPing.create({
      data: {
        bookerId: user.id,
        latitude: lat,
        longitude: lng,
        accuracy: accuracy ?? null,
      },
    }).catch(() => {}); // non-fatal

    return NextResponse.json({ success: true, message: "GPS ping recorded" });
  } catch (err) {
    console.error("[gps]", err);
    return NextResponse.json({ success: false, error: "Failed to record GPS" }, { status: 500 });
  }
}
