import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

function haversineKm(lat1: number, lng1: number, lat2: number, lng2: number): number {
  const R = 6371;
  const dLat = ((lat2 - lat1) * Math.PI) / 180;
  const dLng = ((lng2 - lng1) * Math.PI) / 180;
  const a =
    Math.sin(dLat / 2) ** 2 +
    Math.cos((lat1 * Math.PI) / 180) *
      Math.cos((lat2 * Math.PI) / 180) *
      Math.sin(dLng / 2) ** 2;
  return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
}

// GET /api/v1/route-optimize?bookerId=xx&lat=xx&lng=xx
export async function GET(req: Request) {
  const { searchParams } = new URL(req.url);
  const bookerId = parseInt(searchParams.get("bookerId") ?? "0");
  const curLat   = parseFloat(searchParams.get("lat") ?? "0");
  const curLng   = parseFloat(searchParams.get("lng") ?? "0");

  if (!bookerId) {
    return NextResponse.json({ success: false, error: "bookerId required" }, { status: 400 });
  }

  try {
    const today    = new Date(); today.setHours(0, 0, 0, 0);
    const tomorrow = new Date(today); tomorrow.setDate(tomorrow.getDate() + 1);

    const visits = await prisma.visit.findMany({
      where: {
        bookerId,
        visitDate: { gte: today, lt: tomorrow },
        status: { in: ["PENDING", "IN_PROGRESS"] },
      },
      include: {
        customer: {
          select: {
            id: true, name: true, address: true,
            latitude: true, longitude: true,
          },
        },
      },
    });

    const withCoords    = visits.filter(v => Number(v.customer.latitude) && Number(v.customer.longitude));
    const withoutCoords = visits.filter(v => !(Number(v.customer.latitude) && Number(v.customer.longitude)));

    // Nearest-neighbour TSP
    const sorted: typeof withCoords = [];
    let remaining = [...withCoords];
    let cLat = curLat || 30.3753;
    let cLng = curLng || 69.3451;

    while (remaining.length > 0) {
      let nearestIdx = 0;
      let nearestDist = Infinity;
      remaining.forEach((v, i) => {
        const d = haversineKm(cLat, cLng, Number(v.customer.latitude), Number(v.customer.longitude));
        if (d < nearestDist) { nearestDist = d; nearestIdx = i; }
      });
      const next = remaining.splice(nearestIdx, 1)[0];
      sorted.push(next);
      cLat = Number(next.customer.latitude);
      cLng = Number(next.customer.longitude);
    }

    let cumulative = 0;
    const stops = [...sorted, ...withoutCoords].map((v, i) => {
      const lat = Number(v.customer.latitude) || null;
      const lng = Number(v.customer.longitude) || null;
      const pLat = i === 0 ? curLat : (Number(sorted[i - 1]?.customer.latitude) || curLat);
      const pLng = i === 0 ? curLng : (Number(sorted[i - 1]?.customer.longitude) || curLng);
      const dist = lat && lng ? haversineKm(pLat, pLng, lat, lng) : 0;
      cumulative += dist;
      return {
        sequence: i + 1,
        visitId: v.id,
        status: v.status,
        customer: {
          id: v.customer.id,
          name: v.customer.name,
          address: v.customer.address,
          latitude: lat,
          longitude: lng,
        },
        distanceKm: parseFloat(dist.toFixed(2)),
        cumulativeKm: parseFloat(cumulative.toFixed(2)),
        googleMapsUrl: lat && lng
          ? `https://maps.google.com/maps/dir/?api=1&destination=${lat},${lng}`
          : null,
      };
    });

    return NextResponse.json({
      success: true,
      data: {
        bookerId,
        totalStops: stops.length,
        totalDistanceKm: parseFloat(cumulative.toFixed(2)),
        stops,
      },
    });
  } catch (err) {
    console.error("[v1/route-optimize]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
