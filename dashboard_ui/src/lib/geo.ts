export type LatLng = { lat: number; lng: number };

export const cityCenters: Record<string, LatLng> = {
  Karachi: { lat: 24.8607, lng: 67.0011 },
  Lahore: { lat: 31.5204, lng: 74.3587 },
  Rawalpindi: { lat: 33.5651, lng: 73.0169 },
  Islamabad: { lat: 33.6844, lng: 73.0479 },
  Multan: { lat: 30.1575, lng: 71.5249 },
};

export const PAKISTAN_CENTER: LatLng = { lat: 30.3753, lng: 69.3451 };

/** Deterministically spread a mock {x,y} position around its city centre. */
export const toLatLng = (city: string, pos: { x: number; y: number }): LatLng => {
  const base = cityCenters[city] ?? PAKISTAN_CENTER;
  return {
    lat: base.lat + (pos.y - 50) / 900,
    lng: base.lng + (pos.x - 50) / 900,
  };
};

export const jitter = (p: LatLng, seed: number, amount = 0.01): LatLng => ({
  lat: p.lat + Math.sin(seed) * amount,
  lng: p.lng + Math.cos(seed) * amount,
});

/** Simple interpolated path between two points, for drawing a route. */
export const buildRoute = (from: LatLng, to: LatLng, steps = 5): LatLng[] => {
  const out: LatLng[] = [];
  for (let i = 0; i <= steps; i++) {
    const t = i / steps;
    out.push({
      lat: from.lat + (to.lat - from.lat) * t + Math.sin(t * Math.PI) * 0.004,
      lng: from.lng + (to.lng - from.lng) * t,
    });
  }
  return out;
};
