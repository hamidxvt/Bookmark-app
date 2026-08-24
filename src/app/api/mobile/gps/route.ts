import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

export async function POST(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const body = await req.json();
    const { lat, lng, accuracy, isMock, speed_kmh, altitude, timestamp } = body;

    if (lat === undefined || lng === undefined) {
      return NextResponse.json({ success: false, error: "Missing lat/lng" }, { status: 400 });
    }

    // Determine activity based on speed
    let activity = "STATIONARY";
    if (speed_kmh && speed_kmh > 0) {
      activity = speed_kmh < 5 ? "MOVING_SLOW" : speed_kmh < 20 ? "MOVING" : "MOVING_FAST";
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

    // Record every ping in gps_pings with speed and activity for real-time tracking
    await prisma.gpsPing.create({
      data: {
        bookerId: user.id,
        latitude: lat,
        longitude: lng,
        accuracy: accuracy ?? null,
        speed_kmh: speed_kmh ?? 0,
        activity: activity,
        altitude: altitude ?? null,
        timestamp: timestamp ? new Date(timestamp) : new Date(),
      },
    }).catch(() => {}); // non-fatal

    return NextResponse.json({
      success: true,
      message: "GPS ping recorded",
      data: { activity, speed_kmh },
    });
  } catch (err) {
    console.error("[gps]", err);
    return NextResponse.json({ success: false, error: "Failed to record GPS" }, { status: 500 });
  }
}
