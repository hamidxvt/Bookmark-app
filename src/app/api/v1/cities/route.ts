import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

// GET /api/v1/cities — list all cities with stats
export async function GET() {
  try {
    const cities = await prisma.city.findMany({
      orderBy: { name: "asc" },
      include: {
        _count: { select: { bookers: true, customers: true, areas: true, regions: true } },
      },
    });
    return NextResponse.json({ success: true, data: cities });
  } catch (err) {
    console.error("[v1/cities GET]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}

// POST /api/v1/cities — create a new city
export async function POST(req: Request) {
  try {
    const body = await req.json();
    const { name, latitude, longitude, geofenceRadius } = body;

    if (!name?.trim()) {
      return NextResponse.json({ success: false, error: "City name is required" }, { status: 400 });
    }

    const city = await prisma.city.create({
      data: {
        name: name.trim().toUpperCase(),
        latitude: latitude ? parseFloat(latitude) : null,
        longitude: longitude ? parseFloat(longitude) : null,
        geofenceRadius: geofenceRadius ? parseInt(geofenceRadius) : 5000,
      },
    });

    return NextResponse.json({ success: true, data: city }, { status: 201 });
  } catch (err: any) {
    if (err?.code === "P2002") {
      return NextResponse.json({ success: false, error: "City already exists" }, { status: 409 });
    }
    console.error("[v1/cities POST]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
