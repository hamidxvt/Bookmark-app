import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser } from "@/lib/auth-mobile";
import { unauthorized } from "@/lib/responses";

export async function POST(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const body = await req.json();
    const { lat, lng, accuracy, isMock } = body;

    if (lat === undefined || lng === undefined) {
      return NextResponse.json(
        { success: false, error: "Missing lat/lng" },
        { status: 400 }
      );
    }

    // Update booker's last known location
    await prisma.booker.update({
      where: { id: user.id },
      data: {
        lastLatitude: lat,
        lastLongitude: lng,
        gpsStatus: isMock ? "MOCK" : "ACTIVE",
        lastSeenAt: new Date(),
      },
    });

    // Log GPS ping for audit trail
    await prisma.gpsLog.create({
      data: {
        bookerId: user.id,
        latitude: lat,
        longitude: lng,
        accuracy: accuracy ?? 0,
        isMocked: isMock ?? false,
      },
    });

    return NextResponse.json({ success: true, message: "GPS ping recorded" });
  } catch (err) {
    console.error("[gps]", err);
    return NextResponse.json(
      { success: false, error: "Failed to record GPS" },
      { status: 500 }
    );
  }
}
