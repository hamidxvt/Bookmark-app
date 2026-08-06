import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

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

// GET /api/mobile/route?lat=xx&lng=xx
// Returns today's visits sorted by nearest-neighbour (TSP greedy)
export async function GET(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const { searchParams } = new URL(req.url);
    const curLat = parseFloat(searchParams.get("lat") ?? "0");
    const curLng = parseFloat(searchParams.get("lng") ?? "0");

    const today    = new Date(); today.setHours(0, 0, 0, 0);
    const tomorrow = new Date(today); tomorrow.setDate(tomorrow.getDate() + 1);

    const visits = await prisma.visit.findMany({
      where: {
        bookerId: user.id,
        visitDate: { gte: today, lt: tomorrow },
        status: { in: ["PENDING", "IN_PROGRESS"] },
      },
      include: {
        customer: {
          select: {
            id: true, name: true, address: true,
            latitude: true, longitude: true,
            ownerPhone: true,
          },
        },
      },
    });

    // Filter visits that have coordinates
    const withCoords = visits.filter(v => {
      const lat = Number(v.customer.latitude);
      const lng = Number(v.customer.longitude);
      return !isNaN(lat) && !isNaN(lng) && lat && lng;
    });

    const withoutCoords = visits.filter(v => {
      const lat = Number(v.customer.latitude);
      const lng = Number(v.customer.longitude);
      return !((!isNaN(lat) && !isNaN(lng) && lat && lng));
    });

    // Greedy nearest-neighbour TSP
    const sorted: typeof withCoords = [];
    let remaining = [...withCoords];
    let cLat = curLat || 30.3753;
    let cLng = curLng || 69.3451;

    while (remaining.length > 0) {
      let nearestIdx = 0;
      let nearestDist = Infinity;
      remaining.forEach((v, i) => {
        const lat = Number(v.customer.latitude);
        const lng = Number(v.customer.longitude);
        const d   = haversineKm(cLat, cLng, lat, lng);
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
      const prevLat = i === 0 ? curLat : (Number(sorted[i - 1]?.customer.latitude) || curLat);
      const prevLng = i === 0 ? curLng : (Number(sorted[i - 1]?.customer.longitude) || curLng);
      const dist = lat && lng && prevLat && prevLng ? haversineKm(prevLat, prevLng, lat, lng) : 0;
      cumulative += dist;
      return {
        sequence: i + 1,
        visitId: v.id,
        status: v.status,
        customer: {
          id: v.customer.id,
          name: v.customer.name,
          address: v.customer.address,
          phone: v.customer.ownerPhone,
          latitude: lat,
          longitude: lng,
        },
        distanceKm: parseFloat(dist.toFixed(2)),
        cumulativeKm: parseFloat(cumulative.toFixed(2)),
        googleMapsUrl: lat && lng ? `https://maps.google.com/?q=${lat},${lng}` : null,
      };
    });

    const totalDistanceKm = stops.reduce((s, st) => s + st.distanceKm, 0);

    return NextResponse.json({
      success: true,
      data: {
        totalStops: stops.length,
        totalDistanceKm: parseFloat(totalDistanceKm.toFixed(2)),
        stops,
      },
    });
  } catch (err) {
    console.error("[mobile/route]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
