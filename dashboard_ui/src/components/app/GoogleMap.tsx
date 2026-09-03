import { KeyRound, Loader2 } from "lucide-react";
import { useEffect, useRef, useState } from "react";

import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { PAKISTAN_CENTER, type LatLng } from "@/lib/geo";
import { cn } from "@/lib/utils";

export type MapMarker = {
  id: string;
  name: string;
  position: LatLng;
  kind: "officer" | "customer";
  status?: string;
  detail?: string;
};

const STORAGE_KEY = "bookmark_gmaps_key";

export const getMapsKey = (): string => {
  const envKey = import.meta.env['VITE_GOOGLE_MAPS_API_KEY'] as string | undefined;
  if (envKey) return envKey;
  if (typeof window === "undefined") return "";
  return localStorage.getItem(STORAGE_KEY) ?? "";
};

let loaderPromise: Promise<void> | null = null;

const loadMaps = (key: string): Promise<void> => {
  if (typeof window === "undefined") return Promise.resolve();
  const w = window as unknown as { google?: { maps?: unknown } };
  if (w.google?.maps) return Promise.resolve();
  if (loaderPromise) return loaderPromise;
  loaderPromise = new Promise<void>((resolve, reject) => {
    const script = document.createElement("script");
    script.src = `https://maps.googleapis.com/maps/api/js?key=${encodeURIComponent(key)}&libraries=marker`;
    script.async = true;
    script.onload = () => resolve();
    script.onerror = () => {
      loaderPromise = null;
      reject(new Error("Failed to load Google Maps"));
    };
    document.head.appendChild(script);
  });
  return loaderPromise;
};

const pinColor = (m: MapMarker) => {
  if (m.kind === "customer") return "#1E2A47";
  if (m.status === "offline") return "#94A3B8";
  if (m.status === "idle") return "#F59E0B";
  return "#B20D2F";
};

export function GoogleMapView({
  markers = [],
  route,
  center,
  zoom = 12,
  className,
  onSelect,
}: {
  markers?: MapMarker[];
  route?: LatLng[];
  center?: LatLng;
  zoom?: number;
  className?: string;
  onSelect?: (id: string) => void;
}) {
  const ref = useRef<HTMLDivElement | null>(null);
  const mapRef = useRef<any>(null);
  const overlaysRef = useRef<any[]>([]);
  const [keyValue, setKeyValue] = useState<string>("");
  const [draftKey, setDraftKey] = useState("");
  const [state, setState] = useState<"idle" | "loading" | "ready" | "error">("idle");

  useEffect(() => setKeyValue(getMapsKey()), []);

  useEffect(() => {
    if (!keyValue || !ref.current) return;
    let cancelled = false;
    setState("loading");
    loadMaps(keyValue)
      .then(() => {
        if (cancelled || !ref.current) return;
        const g = (window as any).google;
        mapRef.current = new g.maps.Map(ref.current, {
          center: center ?? PAKISTAN_CENTER,
          zoom,
          disableDefaultUI: false,
          mapTypeControl: false,
          streetViewControl: false,
          styles: [
            { featureType: "poi", stylers: [{ visibility: "off" }] },
            { featureType: "transit", stylers: [{ visibility: "off" }] },
          ],
        });
        setState("ready");
      })
      .catch(() => !cancelled && setState("error"));
    return () => {
      cancelled = true;
    };
  }, [keyValue]);

  // draw markers + route
  useEffect(() => {
    const g = (window as any).google;
    if (state !== "ready" || !mapRef.current || !g) return;
    overlaysRef.current.forEach((o) => o.setMap(null));
    overlaysRef.current = [];

    const info = new g.maps.InfoWindow();
    markers.forEach((m) => {
      const marker = new g.maps.Marker({
        map: mapRef.current,
        position: m.position,
        title: m.name,
        icon: {
          path: g.maps.SymbolPath.CIRCLE,
          scale: m.kind === "officer" ? 9 : 7,
          fillColor: pinColor(m),
          fillOpacity: 1,
          strokeColor: "#ffffff",
          strokeWeight: 2.5,
        },
      });
      marker.addListener("click", () => {
        info.setContent(
          `<div style="font:600 13px Inter,sans-serif;color:#0F172A">${m.name}` +
            (m.detail ? `<div style="font-weight:400;color:#64748B;margin-top:2px">${m.detail}</div>` : "") +
            `</div>`,
        );
        info.open({ anchor: marker, map: mapRef.current });
        onSelect?.(m.id);
      });
      overlaysRef.current.push(marker);
    });

    if (route && route.length > 1) {
      const line = new g.maps.Polyline({
        map: mapRef.current,
        path: route,
        strokeColor: "#B20D2F",
        strokeOpacity: 0.85,
        strokeWeight: 4,
      });
      overlaysRef.current.push(line);
    }

    if (markers.length > 1) {
      const bounds = new g.maps.LatLngBounds();
      markers.forEach((m) => bounds.extend(m.position));
      mapRef.current.fitBounds(bounds, 60);
    } else if (markers.length === 1) {
      mapRef.current.setCenter(markers[0].position);
      mapRef.current.setZoom(zoom);
    }
  }, [markers, route, state, zoom, onSelect]);

  if (!keyValue) {
    return (
      <div
        className={cn(
          "flex flex-col items-center justify-center gap-3 rounded-2xl border border-dashed border-border bg-muted/40 p-8 text-center",
          className,
        )}
      >
        <span className="flex h-12 w-12 items-center justify-center rounded-2xl bg-primary-soft text-primary">
          <KeyRound className="h-5 w-5" />
        </span>
        <div>
          <p className="text-sm font-semibold">Google Maps key required</p>
          <p className="mx-auto max-w-sm text-xs text-muted-foreground">
            Paste a Google Maps JavaScript API key to activate live mapping. It is stored locally in this browser.
          </p>
        </div>
        <div className="flex w-full max-w-sm gap-2">
          <Input
            value={draftKey}
            onChange={(e) => setDraftKey(e.target.value)}
            placeholder="AIza…"
            className="h-10 rounded-xl"
          />
          <Button
            className="rounded-xl"
            onClick={() => {
              const k = draftKey.trim();
              if (!k) return;
              localStorage.setItem(STORAGE_KEY, k);
              setKeyValue(k);
            }}
          >
            Activate
          </Button>
        </div>
      </div>
    );
  }

  return (
    <div className={cn("relative overflow-hidden rounded-2xl border border-border/70", className)}>
      <div ref={ref} className="h-full w-full" />
      {state === "loading" && (
        <div className="absolute inset-0 flex items-center justify-center bg-muted/50 text-sm text-muted-foreground">
          <Loader2 className="mr-2 h-4 w-4 animate-spin" /> Loading Google Maps…
        </div>
      )}
      {state === "error" && (
        <div className="absolute inset-0 flex flex-col items-center justify-center gap-2 bg-muted/70 p-6 text-center">
          <p className="text-sm font-semibold text-destructive">Google Maps failed to load</p>
          <p className="text-xs text-muted-foreground">Check that the API key is valid and Maps JavaScript API is enabled.</p>
          <Button
            variant="outline"
            className="rounded-xl"
            onClick={() => {
              localStorage.removeItem(STORAGE_KEY);
              setKeyValue("");
              setState("idle");
            }}
          >
            Change key
          </Button>
        </div>
      )}
    </div>
  );
}
