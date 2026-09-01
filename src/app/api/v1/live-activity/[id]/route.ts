import { NextResponse } from "next/server";
import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";
import { prisma } from "@/lib/prisma";

// GET /api/v1/live-activity/[id]
// Returns detailed tracking data for a single officer
export async function GET(
  _req: Request,
  { params }: { params: Promise<{ id: string }> }
) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ success: false, error: "Unauthorized" }, { status: 401 });

  const { id } = await params;
  const bookerId = parseInt(id);
  if (isNaN(bookerId)) return NextResponse.json({ success: false, error: "Invalid ID" }, { status: 400 });

  try {
    const now = new Date();
    const todayStart = new Date(); todayStart.setHours(0, 0, 0, 0);
    const twoHoursAgo = new Date(now.getTime() - 2 * 60 * 60 * 1000);

    const [booker, todayVisits, recentPings] = await Promise.all([
      prisma.booker.findUnique({
        where: { id: bookerId },
        select: {
          id: true, name: true, phone: true, email: true,
          designation: true, profilePhoto: true,
          gpsStatus: true, lastLatitude: true, lastLongitude: true, lastSeenAt: true,
          city: { select: { name: true } },
          visitTargets: true,
        },
      }),

      // All of today's visits
      prisma.visit.findMany({
        where: { bookerId, visitDate: { gte: todayStart } },
        orderBy: { id: "asc" },
        include: {
          customer: {
            select: {
              id: true, name: true, address: true,
              latitude: true, longitude: true, ownerPhone: true,
            },
          },
          missedReason: { select: { reason: true } },
        },
      }),

      // GPS trail for last 2 hours (max 300 pings)
      prisma.gpsPing.findMany({
        where: { bookerId, timestamp: { gte: twoHoursAgo } },
        orderBy: { timestamp: "asc" },
        take: 300,
        select: {
          latitude: true, longitude: true,
          speed_kmh: true, activity: true,
          heading: true, timestamp: true,
        },
      }),
    ]);

    if (!booker) return NextResponse.json({ success: false, error: "Officer not found" }, { status: 404 });

    // Summarise today's visits
    const visitSummary = todayVisits.map((v, idx) => ({
      id: v.id,
      sequence: idx + 1,  // Calculate sequence from position in sorted list
      status: v.status === "CANCELLED" ? "missed" : v.status.toLowerCase(),
      customerName: v.customer?.name ?? "Unknown",
      address: v.customer?.address ?? "",
      phone: v.customer?.ownerPhone ?? "",
      lat: v.customer?.latitude ? Number(v.customer.latitude) : null,
      lng: v.customer?.longitude ? Number(v.customer.longitude) : null,
      checkInAt: v.checkInAt,
      checkOutAt: v.checkOutAt,
      missedReason: v.missedReason?.reason ?? null,
      visitDate: v.visitDate,
    }));

    // GPS trail (compressed — keep every point, lat/lng/speed/heading)
    const trail = recentPings.map(p => ({
      lat: Number(p.latitude),
      lng: Number(p.longitude),
      speed: p.speed_kmh ?? 0,
      activity: p.activity,
      heading: p.heading,
      time: p.timestamp,
    }));

    // Compute today's stats
    const stats = {
      totalVisits: todayVisits.length,
      completed: todayVisits.filter(v => v.status === "COMPLETED").length,
      inProgress: todayVisits.filter(v => v.status === "IN_PROGRESS").length,
      pending: todayVisits.filter(v => v.status === "PENDING").length,
      missed: todayVisits.filter(v => v.status === "CANCELLED").length,
      targetVisits: booker.visitTargets ?? 0,
    };

    // Compute total distance from GPS trail
    let distanceKm = 0;
    for (let i = 1; i < trail.length; i++) {
      distanceKm += haversineKm(
        trail[i - 1].lat, trail[i - 1].lng,
        trail[i].lat, trail[i].lng,
      );
    }

    // Time in field: first ping of today
    const firstPing = recentPings[0];
    const lastPing = recentPings[recentPings.length - 1];

    // Current activity
    const lastSeenMs = booker.lastSeenAt
      ? now.getTime() - new Date(booker.lastSeenAt).getTime()
      : null;
    const isOffline = !booker.lastSeenAt || (lastSeenMs ?? 0) > 30 * 60 * 1000;
    const isIdle = !isOffline && (lastSeenMs ?? 0) > 5 * 60 * 1000;
    const currentSpeed = lastPing?.speed_kmh ?? 0;

    let activityLabel = "Offline";
    const activeVisit = todayVisits.find(v => v.status === "IN_PROGRESS");
    if (!isOffline) {
      if (activeVisit) activityLabel = `At: ${activeVisit.customer?.name}`;
      else if (currentSpeed > 15) activityLabel = `Driving (${Math.round(currentSpeed)} km/h)`;
      else if (currentSpeed > 3) activityLabel = `Moving slowly`;
      else if (isIdle) activityLabel = `Stationary ${Math.round((lastSeenMs ?? 0) / 60000)}m`;
      else activityLabel = "Active";
    }

    return NextResponse.json({
      success: true,
      data: {
        officer: {
          id: booker.id,
          name: booker.name,
          phone: booker.phone,
          email: booker.email,
          designation: booker.designation,
          profilePhoto: booker.profilePhoto,
          city: booker.city?.name ?? "—",
          gpsStatus: booker.gpsStatus,
          isOffline,
          isIdle,
          lastSeenAt: booker.lastSeenAt,
          currentLat: booker.lastLatitude ? Number(booker.lastLatitude) : null,
          currentLng: booker.lastLongitude ? Number(booker.lastLongitude) : null,
          activityLabel,
          currentSpeed: Math.round(currentSpeed),
          heading: lastPing?.heading ?? null,
        },
        stats: {
          ...stats,
          distanceKm: Math.round(distanceKm * 10) / 10,
          fieldSince: firstPing?.timestamp ?? null,
          lastPingAt: lastPing?.timestamp ?? null,
        },
        visits: visitSummary,
        trail,
      },
    });
  } catch (err) {
    console.error("[live-activity/[id]]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}

function haversineKm(lat1: number, lng1: number, lat2: number, lng2: number): number {
  const R = 6371;
  const dLat = ((lat2 - lat1) * Math.PI) / 180;
  const dLng = ((lng2 - lng1) * Math.PI) / 180;
  const a =
    Math.sin(dLat / 2) ** 2 +
    Math.cos((lat1 * Math.PI) / 180) * Math.cos((lat2 * Math.PI) / 180) * Math.sin(dLng / 2) ** 2;
  return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
}
