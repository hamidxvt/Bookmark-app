import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const cityId = searchParams.get("cityId");

    const where: Record<string, unknown> = {};
    if (cityId) where.cityId = parseInt(cityId);

    const bookers = await prisma.booker.findMany({
      where: {
        ...where,
        adminApproved: "APPROVED",
        lastLatitude: { not: null },
        lastLongitude: { not: null },
      },
      select: {
        id: true,
        name: true,
        email: true,
        phone: true,
        profilePhoto: true,
        gpsStatus: true,
        lastLatitude: true,
        lastLongitude: true,
        lastSeenAt: true,
        city: { select: { id: true, name: true } },
      },
      orderBy: { lastSeenAt: "desc" },
    });

    type Booker = typeof bookers[number];
    const counts = {
      total: bookers.length,
      active: bookers.filter((b: Booker) => b.gpsStatus === "ACTIVE").length,
      idle: bookers.filter((b: Booker) => b.gpsStatus === "IDLE").length,
      offline: bookers.filter((b: Booker) => b.gpsStatus === "OFFLINE").length,
    };

    return NextResponse.json({
      success: true,
      data: { bookers, counts },
    });
  } catch (err) {
    console.error("[api/v1/location]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch locations" }, { status: 500 });
  }
}
