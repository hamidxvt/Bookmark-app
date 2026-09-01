import { NextResponse } from "next/server";
import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";
import { prisma } from "@/lib/prisma";

const IDLE_THRESHOLD_MS = 5 * 60 * 1000; // 5 minutes

// GET /api/v1/live-activity
// Returns all officers with their real-time activity, GPS, and visit status
export async function GET() {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ success: false, error: "Unauthorized" }, { status: 401 });

  try {
    const now = new Date();
    const todayStart = new Date();
    todayStart.setHours(0, 0, 0, 0);

    // Get all non-deleted bookers with their city and latest GPS data
    const bookers = await prisma.booker.findMany({
      where: { deletedAt: null },
      select: {
        id: true,
        name: true,
        phone: true,
        designation: true,
        profilePhoto: true,
        gpsStatus: true,
        lastLatitude: true,
        lastLongitude: true,
        lastSeenAt: true,
        city: { select: { id: true, name: true } },
      },
      orderBy: [{ gpsStatus: "asc" }, { lastSeenAt: "desc" }],
    });

    // Get today's visits for all bookers (batch query)
    const bookerIds = bookers.map(b => b.id);

    const [todayVisits, latestPings] = await Promise.all([
      prisma.visit.findMany({
        where: {
          bookerId: { in: bookerIds },
          visitDate: { gte: todayStart },
        },
        select: {
          id: true,
          bookerId: true,
          status: true,
          checkInAt: true,
          checkOutAt: true,
          customer: { select: { id: true, name: true, address: true } },
        },
        orderBy: { checkInAt: "desc" },
      }),
      // Latest GPS ping per booker (last 15 min window)
      prisma.gpsPing.findMany({
        where: {
          bookerId: { in: bookerIds },
          timestamp: { gte: new Date(now.getTime() - 15 * 60 * 1000) },
        },
        orderBy: { timestamp: "desc" },
        distinct: ["bookerId"],
        select: {
          bookerId: true,
          latitude: true,
          longitude: true,
          speed_kmh: true,
          activity: true,
          heading: true,
          timestamp: true,
        },
      }),
    ]);

    // Build lookup maps
    const visitsByBooker = new Map<number, typeof todayVisits>();
    for (const v of todayVisits) {
      if (!visitsByBooker.has(v.bookerId)) visitsByBooker.set(v.bookerId, []);
      visitsByBooker.get(v.bookerId)!.push(v);
    }

    const pingByBooker = new Map<number, typeof latestPings[0]>();
    for (const p of latestPings) pingByBooker.set(p.bookerId, p);

    // Assemble activity records
    const activities = bookers.map(booker => {
      const ping = pingByBooker.get(booker.id);
      const visits = visitsByBooker.get(booker.id) ?? [];

      const activeVisit = visits.find(v => v.status === "IN_PROGRESS");
      const completedToday = visits.filter(v => v.status === "COMPLETED").length;
      const pendingToday = visits.filter(v => v.status === "PENDING").length;
      const totalToday = visits.length;

      // Determine real-time status
      const lastSeenMs = booker.lastSeenAt ? now.getTime() - new Date(booker.lastSeenAt).getTime() : null;
      const isOffline = !booker.lastSeenAt || lastSeenMs! > 30 * 60 * 1000; // 30+ min = offline
      const isIdle = !isOffline && lastSeenMs! > IDLE_THRESHOLD_MS; // 5+ min no ping = idle

      let activityLabel = "Offline";
      let activityColor = "gray";

      if (!isOffline) {
        const speed = ping?.speed_kmh ?? 0;
        if (activeVisit) {
          activityLabel = `At visit: ${activeVisit.customer.name}`;
          activityColor = "green";
        } else if (speed > 15) {
          activityLabel = `Driving (${Math.round(speed)} km/h)`;
          activityColor = "blue";
        } else if (speed > 3) {
          activityLabel = `Moving slowly`;
          activityColor = "blue";
        } else if (isIdle) {
          activityLabel = `Stationary (${Math.round(lastSeenMs! / 60000)}m)`;
          activityColor = "amber";
        } else {
          activityLabel = "Active";
          activityColor = "green";
        }
      }

      // Idle alert: stationary for 5+ minutes during work hours
      const workHoursNow = now.getHours() >= 8 && now.getHours() <= 18;
      const idleAlert = isIdle && workHoursNow && !isOffline && !activeVisit;

      const lastSeenText = booker.lastSeenAt
        ? formatTimeAgo(new Date(booker.lastSeenAt), now)
        : "Never";

      return {
        id: booker.id,
        name: booker.name,
        phone: booker.phone,
        designation: booker.designation,
        profilePhoto: booker.profilePhoto,
        city: booker.city?.name ?? "—",
        gpsStatus: booker.gpsStatus,
        isOffline,
        isIdle,
        idleAlert,
        activityLabel,
        activityColor,
        lastSeenText,
        lastSeenAt: booker.lastSeenAt,
        latitude: booker.lastLatitude ? Number(booker.lastLatitude) : null,
        longitude: booker.lastLongitude ? Number(booker.lastLongitude) : null,
        speed_kmh: ping?.speed_kmh ?? 0,
        heading: ping?.heading ?? null,
        activeVisit: activeVisit
          ? {
              id: activeVisit.id,
              customerName: activeVisit.customer.name,
              address: activeVisit.customer.address,
              checkInAt: activeVisit.checkInAt,
            }
          : null,
        stats: {
          completedToday,
          pendingToday,
          totalToday,
        },
      };
    });

    // Sort: idle alerts first, then active, then offline
    activities.sort((a, b) => {
      if (a.idleAlert && !b.idleAlert) return -1;
      if (!a.idleAlert && b.idleAlert) return 1;
      if (!a.isOffline && b.isOffline) return -1;
      if (a.isOffline && !b.isOffline) return 1;
      return 0;
    });

    const summary = {
      total: activities.length,
      active: activities.filter(a => !a.isOffline).length,
      idle: activities.filter(a => a.isIdle && !a.isOffline).length,
      offline: activities.filter(a => a.isOffline).length,
      alerts: activities.filter(a => a.idleAlert).length,
    };

    return NextResponse.json({ success: true, data: { activities, summary } });
  } catch (err) {
    console.error("[live-activity]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}

function formatTimeAgo(date: Date, now: Date): string {
  const secs = Math.floor((now.getTime() - date.getTime()) / 1000);
  if (secs < 60) return `${secs}s ago`;
  const mins = Math.floor(secs / 60);
  if (mins < 60) return `${mins}m ago`;
  const hrs = Math.floor(mins / 60);
  return `${hrs}h ago`;
}
