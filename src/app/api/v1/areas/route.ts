import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

// GET /api/v1/areas?cityId=x
export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const cityId = searchParams.get("cityId");

    const areas = await prisma.area.findMany({
      where: cityId ? { cityId: parseInt(cityId) } : undefined,
      orderBy: { name: "asc" },
      include: {
        city:   { select: { id: true, name: true } },
        region: { select: { id: true, name: true } },
      },
    });
    return NextResponse.json({ success: true, data: areas });
  } catch (err) {
    console.error("[v1/areas GET]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}

// POST /api/v1/areas — create area with geofencing
export async function POST(req: Request) {
  try {
    const body = await req.json();
    const { name, cityId, regionId, latitude, longitude, geofenceRadius, address } = body;

    if (!name?.trim() || !cityId || !regionId) {
      return NextResponse.json(
        { success: false, error: "name, cityId and regionId are required" },
        { status: 400 }
      );
    }

    const area = await prisma.area.create({
      data: {
        name: name.trim(),
        cityId: parseInt(cityId),
        regionId: parseInt(regionId),
        latitude:       latitude       ? parseFloat(latitude)       : null,
        longitude:      longitude      ? parseFloat(longitude)      : null,
        geofenceRadius: geofenceRadius ? parseInt(geofenceRadius)   : 500,
        address:        address?.trim() ?? null,
      },
    });

    return NextResponse.json({ success: true, data: area }, { status: 201 });
  } catch (err) {
    console.error("[v1/areas POST]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
