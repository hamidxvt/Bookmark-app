import { NextResponse } from "next/server";
import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";
import { prisma } from "@/lib/prisma";

// GET /api/v1/officer-activity — Smart activity feed for admin dashboard
// Shows: who's moving, who's stuck, who's late, who's visiting, etc.
export async function GET(req: Request) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ success: false, error: "Unauthorized" }, { status: 401 });

  const now = new Date();
  const todayStart = new Date(now);
  todayStart.setHours(0, 0, 0, 0);

  try {
    // Get all active officers with their latest GPS and visit status
    const officers = await prisma.booker.findMany({
      where: { jobStatus: "ACTIVE", adminApproved: "APPROVED", deletedAt: null },
      select: {
        id: true,
        name: true,
        designation: true,
        city: { select: { name: true } },
        lastSeenAt: true,
        gpsStatus: true,
        lastLatitude: true,
        lastLongitude: true,
        gps_pings: {
          orderBy: { createdAt: "desc" },
          take: 1,
          select: { speed_kmh: true, createdAt: true },
        },
        visits: {
          where: { visitDate: todayStart },
          select: {
            id: true,
            status: true,
            customer: { select: { name: true, address: true } },
          },
          orderBy: { createdAt: "asc" },
        },
      },
      orderBy: { name: "asc" },
    });

    const activities: Array<{
      id: string;
      type: "active" | "idle" | "late" | "completed" | "pending";
      officer: string;
      designation?: string | null;
      city?: string | null;
      title: string;
      description: string;
      timestamp: Date;
      severity: "info" | "warning" | "critical";
      speed?: number;
      lat?: number;
      lng?: number;
    }> = [];

    for (const officer of officers) {
      const lastSeen = officer.lastSeenAt ? new Date(officer.lastSeenAt) : null;
      const minSincePing = lastSeen ? (now.getTime() - lastSeen.getTime()) / 60000 : Infinity;
      const lastPing = officer.gps_pings?.[0];
      const speed = lastPing ? (Number(lastPing.speed_kmh ?? 0) < 3 ? 0 : Number(lastPing.speed_kmh ?? 0)) : 0;

      // ── Check GPS status ────────────────────────────────────────────
      if (minSincePing > 15) {
        // Format time since last ping: convert to hours if > 60 mins
        let pingTimeStr: string;
        if (!lastSeen) {
          pingTimeStr = "never";
        } else if (minSincePing >= 60) {
          const hrs = Math.floor(minSincePing / 60);
          pingTimeStr = `${hrs} hour${hrs > 1 ? 's' : ''}`;
        } else {
          pingTimeStr = `${Math.round(minSincePing)} minute${Math.round(minSincePing) !== 1 ? 's' : ''}`;
        }

        activities.push({
          id: `gps_${officer.id}`,
          type: "idle",
          officer: officer.name,
          designation: officer.designation,
          city: officer.city?.name,
          title: "GPS Inactive",
          description: `No GPS ping for ${pingTimeStr}`,
          timestamp: lastSeen || now,
          severity: minSincePing > 30 ? "critical" : "warning",
          lat: officer.lastLatitude ? Number(officer.lastLatitude) : undefined,
          lng: officer.lastLongitude ? Number(officer.lastLongitude) : undefined,
        });
      } else if (speed > 10) {
        activities.push({
          id: `moving_${officer.id}`,
          type: "active",
          officer: officer.name,
          designation: officer.designation,
          city: officer.city?.name,
          title: "Moving",
          description: `${speed.toFixed(0)} km/h — heading to next visit`,
          timestamp: lastSeen || now,
          severity: "info",
          speed,
          lat: officer.lastLatitude ? Number(officer.lastLatitude) : undefined,
          lng: officer.lastLongitude ? Number(officer.lastLongitude) : undefined,
        });
      } else if (speed > 0) {
        activities.push({
          id: `slow_${officer.id}`,
          type: "active",
          officer: officer.name,
          designation: officer.designation,
          city: officer.city?.name,
          title: "Slow Movement",
          description: `${speed.toFixed(0)} km/h — may be at location`,
          timestamp: lastSeen || now,
          severity: "info",
          speed,
          lat: officer.lastLatitude ? Number(officer.lastLatitude) : undefined,
          lng: officer.lastLongitude ? Number(officer.lastLongitude) : undefined,
        });
      }

      // ── Check visit status ──────────────────────────────────────────
      const inProgress = officer.visits.find((v) => v.status === "IN_PROGRESS");
      const completed = officer.visits.filter((v) => v.status === "COMPLETED").length;
      const pending = officer.visits.filter((v) => v.status === "PENDING").length;

      if (inProgress) {
        activities.push({
          id: `visit_${inProgress.id}`,
          type: "active",
          officer: officer.name,
          designation: officer.designation,
          city: officer.city?.name,
          title: "Visiting",
          description: `Currently at ${inProgress.customer.name}`,
          timestamp: now,
          severity: "info",
        });
      }

      if (completed > 0) {
        activities.push({
          id: `completed_${officer.id}`,
          type: "completed",
          officer: officer.name,
          designation: officer.designation,
          city: officer.city?.name,
          title: "Completed Visits",
          description: `${completed}/${officer.visits.length} visits done today`,
          timestamp: now,
          severity: "info",
        });
      }

      // ── Late warning ────────────────────────────────────────────────
      if (pending > 0 && new Date().getHours() >= 15) {
        activities.push({
          id: `late_${officer.id}`,
          type: "late",
          officer: officer.name,
          designation: officer.designation,
          city: officer.city?.name,
          title: "Running Behind",
          description: `${pending} visit(s) remaining at ${new Date().getHours()}:${new Date().getMinutes().toString().padStart(2, "0")}`,
          timestamp: now,
          severity: "warning",
        });
      }
    }

    // Sort by timestamp (most recent first)
    activities.sort((a, b) => b.timestamp.getTime() - a.timestamp.getTime());

    return NextResponse.json({
      success: true,
      data: {
        totalOfficers: officers.length,
        activeActivities: activities,
        summary: {
          moving: activities.filter((a) => a.speed && a.speed > 10).length,
          visiting: activities.filter((a) => a.type === "active" && a.title === "Visiting").length,
          idle: activities.filter((a) => a.type === "idle").length,
          late: activities.filter((a) => a.type === "late").length,
        },
      },
    });
  } catch (err) {
    console.error("[officer-activity]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
