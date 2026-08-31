import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

const CACHE_TTL = 10000; // 10 seconds for location data (frequent updates)
interface CachedData {
  timestamp: number;
  data: any;
}
const memoryCache = new Map<string, CachedData>();

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const cityId = searchParams.get("cityId");
    const cacheKey = `location:${cityId || 'all'}`;

    // Check cache first (60 second TTL for real-time data)
    const cached = memoryCache.get(cacheKey);
    if (cached && Date.now() - cached.timestamp < CACHE_TTL) {
      return NextResponse.json({
        ...cached.data,
        cached: true,
        cacheAge: Math.round((Date.now() - cached.timestamp) / 1000)
      });
    }

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
        // Latest ping for speed, activity, heading
        gps_pings: {
          orderBy: { createdAt: "desc" },
          take: 1,
          select: {
            speed_kmh: true,
            activity: true,
            heading: true,
            createdAt: true,
          },
        },
      },
      orderBy: { lastSeenAt: "desc" },
    });

    type Booker = typeof bookers[number];

    const enriched = bookers.map((b: Booker) => {
      const latestPing = b.gps_pings?.[0] ?? null;
      const { gps_pings: _pings, ...rest } = b as any;
      return {
        ...rest,
        lastSpeedKmh:  latestPing?.speed_kmh  ?? null,
        lastActivity:  latestPing?.activity   ?? null,
        lastHeading:   latestPing?.heading    ?? null,
        lastPingAt:    latestPing?.createdAt  ?? null,
      };
    });

    const counts = {
      total:   enriched.length,
      active:  enriched.filter((b: any) => b.gpsStatus === "ACTIVE").length,
      idle:    enriched.filter((b: any) => b.gpsStatus === "IDLE").length,
      offline: enriched.filter((b: any) => b.gpsStatus === "OFFLINE").length,
    };

    const response = {
      success: true,
      data: { bookers: enriched, counts },
    };

    // Cache the result
    memoryCache.set(cacheKey, {
      timestamp: Date.now(),
      data: response
    });

    return NextResponse.json(response);
  } catch (err) {
    console.error("[api/v1/location]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch locations" }, { status: 500 });
  }
}
