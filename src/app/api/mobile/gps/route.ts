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

    // updateMany silently does nothing if booker not found (avoids P2025 crash)
    await prisma.booker.updateMany({
      where: { id: user.id, deletedAt: null },
      data: {
        lastLatitude: lat,
        lastLongitude: lng,
        gpsStatus: isMock ? "MOCK" : "ACTIVE",
        lastSeenAt: new Date(),
      },
    });

    return NextResponse.json({ success: true, message: "GPS ping recorded" });
  } catch (err) {
    console.error("[gps]", err);
    return NextResponse.json({ success: false, error: "Failed to record GPS" }, { status: 500 });
  }
}
