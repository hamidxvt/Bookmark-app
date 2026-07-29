import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// POST /api/mobile/gps  — location ping from mobile app
export async function POST(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const { lat, lng, accuracy, batteryLevel } = await req.json();

    if (!lat || !lng) {
      return NextResponse.json({ success: false, error: "lat and lng are required" }, { status: 400 });
    }

    await prisma.$transaction([
      prisma.gpsPing.create({
        data: {
          bookerId: user.id,
          latitude: lat,
          longitude: lng,
          accuracy: accuracy ?? null,
          batteryLevel: batteryLevel ?? null,
        },
      }),
      prisma.booker.update({
        where: { id: user.id },
        data: {
          lastLatitude: lat,
          lastLongitude: lng,
          lastSeenAt: new Date(),
          gpsStatus: "ACTIVE",
        },
      }),
    ]);

    return NextResponse.json({ success: true });
  } catch (err) {
    console.error("[mobile/gps]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
