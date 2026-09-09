import { prisma } from "@/lib/prisma";
import { DEFAULT_MAX_ASSIGN_DISTANCE_KM, haversineKm } from "@/lib/geo";

export type OfficerLocation = {
  lat: number;
  lng: number;
  source: "gps_ping" | "booker_last";
};

export type CityValidationResult =
  | { ok: true }
  | { ok: false; error: string; reason: "no_officer_city" | "city_mismatch" };

export type DistanceValidationResult =
  | { ok: true; distanceKm: number }
  | {
      ok: false;
      error: string;
      reason: "no_officer_gps" | "no_customer_coords" | "too_far";
      distanceKm?: number;
    };

/** Latest GPS ping, falling back to booker's last known coordinates. */
export async function getOfficerLocation(
  bookerId: number,
): Promise<OfficerLocation | null> {
  const ping = await prisma.gpsPing.findFirst({
    where: { bookerId },
    orderBy: { createdAt: "desc" },
    select: { latitude: true, longitude: true },
  });

  if (ping) {
    return {
      lat: Number(ping.latitude),
      lng: Number(ping.longitude),
      source: "gps_ping",
    };
  }

  const booker = await prisma.booker.findUnique({
    where: { id: bookerId },
    select: { lastLatitude: true, lastLongitude: true },
  });

  if (booker?.lastLatitude != null && booker?.lastLongitude != null) {
    return {
      lat: Number(booker.lastLatitude),
      lng: Number(booker.lastLongitude),
      source: "booker_last",
    };
  }

  return null;
}

/** Strict city match — officer and customer must share the same cityId. */
export async function validateCityMatch(
  bookerCityId: number | null | undefined,
  customerCityId: number,
): Promise<CityValidationResult> {
  if (!bookerCityId) {
    return {
      ok: false,
      reason: "no_officer_city",
      error: "Officer has no assigned city",
    };
  }

  if (bookerCityId !== customerCityId) {
    const [officerCity, customerCity] = await Promise.all([
      prisma.city.findUnique({
        where: { id: bookerCityId },
        select: { name: true },
      }),
      prisma.city.findUnique({
        where: { id: customerCityId },
        select: { name: true },
      }),
    ]);

    return {
      ok: false,
      reason: "city_mismatch",
      error: `Customer is in ${customerCity?.name ?? customerCityId}, officer is in ${officerCity?.name ?? bookerCityId}`,
    };
  }

  return { ok: true };
}

export function checkDistanceWithinRange(
  officerLat: number,
  officerLng: number,
  customerLat: number | null | undefined,
  customerLng: number | null | undefined,
  maxKm: number = DEFAULT_MAX_ASSIGN_DISTANCE_KM,
): DistanceValidationResult {
  if (customerLat == null || customerLng == null) {
    return {
      ok: false,
      reason: "no_customer_coords",
      error: "Customer has no GPS coordinates",
    };
  }

  const distanceKm = haversineKm(
    officerLat,
    officerLng,
    Number(customerLat),
    Number(customerLng),
  );

  if (distanceKm > maxKm) {
    return {
      ok: false,
      reason: "too_far",
      distanceKm,
      error: `Officer is ${distanceKm.toFixed(1)}km away (max ${maxKm}km)`,
    };
  }

  return { ok: true, distanceKm };
}
