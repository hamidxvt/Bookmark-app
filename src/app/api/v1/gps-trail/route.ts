import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

// GET /api/v1/gps-trail?bookerId=X&date=YYYY-MM-DD
// Returns full GPS breadcrumb trail for an officer on a specific day
export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const bookerId  = parseInt(searchParams.get("bookerId") ?? "0");
    const dateStr   = searchParams.get("date") ?? new Date().toISOString().slice(0, 10);

    if (!bookerId) {
      return NextResponse.json({ success: false, error: "bookerId required" }, { status: 400 });
    }

    const dayStart = new Date(`${dateStr}T00:00:00.000Z`);
    const dayEnd   = new Date(`${dateStr}T23:59:59.999Z`);

    const pings = await prisma.gpsPing.findMany({
      where: {
        bookerId,
        createdAt: { gte: dayStart, lte: dayEnd },
      },
      orderBy: { createdAt: "asc" },
      select: {
        id: true,
        latitude: true,
        longitude: true,
        accuracy: true,
        speed_kmh: true,
        activity: true,
        heading: true,
        batteryLevel: true,
        createdAt: true,
      },
    });

    return NextResponse.json({
      success: true,
      data: {
        bookerId,
        date: dateStr,
        totalPings: pings.length,
        trail: pings.map(p => ({
          lat:      Number(p.latitude),
          lng:      Number(p.longitude),
          accuracy: p.accuracy,
          speed:    p.speed_kmh,
          activity: p.activity,
          heading:  p.heading,
          battery:  p.batteryLevel,
          time:     p.createdAt,
        })),
      },
    });
  } catch (err) {
    console.error("[v1/gps-trail]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
