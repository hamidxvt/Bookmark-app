import { NextResponse } from "next/server";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

const GMAP_KEY = process.env.GOOGLE_MAPS_API_KEY ?? "";

// POST /api/mobile/directions
// Body: { originLat, originLng, destLat, destLng }
// Returns encoded polyline + car/walk ETA from Google Directions API
export async function POST(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  if (!GMAP_KEY) {
    // Gracefully return empty polyline instead of 503 — allow app to function without directions
    return NextResponse.json({
      success: true,
      data: {
        polyline: "",
        distanceM: 0,
        distanceText: "Unknown",
        durationSec: 0,
        durationText: "Unknown",
        walkDurationSec: null,
        walkDurationText: null,
        steps: [],
      },
    });
  }

  try {
    const { originLat, originLng, destLat, destLng } = await req.json();
    if (!originLat || !originLng || !destLat || !destLng) {
      return NextResponse.json({ success: false, error: "Missing coordinates" }, { status: 400 });
    }

    const origin = `${originLat},${originLng}`;
    const dest   = `${destLat},${destLng}`;
    const base   = "https://maps.googleapis.com/maps/api/directions/json";

    const [carRes, walkRes] = await Promise.all([
      fetch(`${base}?origin=${origin}&destination=${dest}&mode=driving&key=${GMAP_KEY}`),
      fetch(`${base}?origin=${origin}&destination=${dest}&mode=walking&key=${GMAP_KEY}`),
    ]);

    const [carData, walkData] = await Promise.all([carRes.json(), walkRes.json()]);

    const carRoute  = carData.routes?.[0];
    const walkRoute = walkData.routes?.[0];

    if (!carRoute) {
      return NextResponse.json({ success: false, error: "No route found" }, { status: 404 });
    }

    const carLeg  = carRoute.legs?.[0];
    const walkLeg = walkRoute?.legs?.[0];

    return NextResponse.json({
      success: true,
      data: {
        polyline: carRoute.overview_polyline?.points ?? "",
        distanceM:      carLeg?.distance?.value   ?? 0,
        distanceText:   carLeg?.distance?.text    ?? "",
        durationSec:    carLeg?.duration?.value   ?? 0,
        durationText:   carLeg?.duration?.text    ?? "",
        walkDurationSec:  walkLeg?.duration?.value  ?? null,
        walkDurationText: walkLeg?.duration?.text   ?? null,
        steps: (carLeg?.steps ?? []).map((s: any) => ({
          instruction: s.html_instructions?.replace(/<[^>]+>/g, "") ?? "",
          distanceM: s.distance?.value ?? 0,
          durationSec: s.duration?.value ?? 0,
          startLat: s.start_location?.lat,
          startLng: s.start_location?.lng,
        })),
      },
    });
  } catch (err) {
    console.error("[directions]", err);
    return NextResponse.json({ success: false, error: "Directions fetch failed" }, { status: 500 });
  }
}
