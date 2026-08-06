"use client";

import { useEffect, useRef, useState } from "react";
import { MapPin, RefreshCw, Users, Clock, Navigation } from "lucide-react";

interface Officer {
  id: number;
  name: string;
  email: string;
  gpsStatus: string;
  lastLatitude: number | null;
  lastLongitude: number | null;
  lastSeenAt: string | null;
  city: { id: number; name: string } | null;
}

function GpsStatusDot({ status }: { status: string }) {
  const s = status?.toUpperCase();
  if (s === "ACTIVE")  return <span className="h-2.5 w-2.5 rounded-full bg-emerald-500 animate-pulse inline-block" />;
  if (s === "IDLE")    return <span className="h-2.5 w-2.5 rounded-full bg-amber-400 inline-block" />;
  if (s === "MOCK")    return <span className="h-2.5 w-2.5 rounded-full bg-orange-500 inline-block" />;
  return <span className="h-2.5 w-2.5 rounded-full bg-slate-300 inline-block" />;
}

function statusLabel(s: string) {
  const u = s?.toUpperCase();
  if (u === "ACTIVE")  return { text: "Active",  cls: "text-emerald-700 bg-emerald-50 border-emerald-200" };
  if (u === "IDLE")    return { text: "Idle",    cls: "text-amber-700  bg-amber-50  border-amber-200"  };
  if (u === "MOCK")    return { text: "Mock GPS", cls: "text-orange-700 bg-orange-50 border-orange-200" };
  return                      { text: "Offline",  cls: "text-slate-500  bg-slate-50  border-slate-200"  };
}

declare global {
  interface Window {
    L: any; // Leaflet loaded from CDN
  }
}

export default function LiveMapClient() {
  const mapRef      = useRef<HTMLDivElement>(null);
  const leafletRef  = useRef<any>(null); // Leaflet map instance
  const markersRef  = useRef<Map<number, any>>(new Map()); // Leaflet markers

  const [officers,   setOfficers]   = useState<Officer[]>([]);
  const [loading,    setLoading]    = useState(true);
  const [lastUpdate, setLastUpdate] = useState<Date | null>(null);
  const [selected,   setSelected]   = useState<Officer | null>(null);
  const [leafletLoaded, setLeafletLoaded] = useState(false);

  // Lazy-load Leaflet CSS + JS (no bundler needed, plain CDN)
  useEffect(() => {
    if (document.querySelector("#leaflet-css")) { setLeafletLoaded(true); return; }
    const link    = document.createElement("link");
    link.id       = "leaflet-css";
    link.rel      = "stylesheet";
    link.href     = "https://unpkg.com/leaflet@1.9.4/dist/leaflet.css";
    document.head.appendChild(link);

    const script  = document.createElement("script");
    script.src    = "https://unpkg.com/leaflet@1.9.4/dist/leaflet.js";
    script.onload = () => setLeafletLoaded(true);
    document.head.appendChild(script);
  }, []);

  // Init map once Leaflet loaded
  useEffect(() => {
    if (!leafletLoaded || !mapRef.current || leafletRef.current) return;
    const L = window.L;
    const map = L.map(mapRef.current, { zoomControl: true }).setView([30.3753, 69.3451], 6);
    L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
      attribution: "© OpenStreetMap contributors",
      maxZoom: 19,
    }).addTo(map);
    leafletRef.current = map;
  }, [leafletLoaded]);

  async function load() {
    try {
      const res = await fetch("/api/v1/location").then(r => r.json());
      if (res.success) {
        const list: Officer[] = res.data?.bookers ?? [];
        setOfficers(list);
        setLastUpdate(new Date());
        updateMarkers(list);
      }
    } catch (e) {
      console.error("[LiveMap]", e);
    } finally {
      setLoading(false);
    }
  }

  function updateMarkers(list: Officer[]) {
    const map = leafletRef.current;
    const L   = window.L;
    if (!map || !L) return;

    const seen = new Set<number>();

    list.forEach(o => {
      const lat = Number(o.lastLatitude);
      const lng = Number(o.lastLongitude);
      if (!lat || !lng || isNaN(lat) || isNaN(lng)) return;
      seen.add(o.id);

      const color  = o.gpsStatus === "ACTIVE" ? "#10b981" : o.gpsStatus === "IDLE" ? "#f59e0b" : "#94a3b8";
      const initials = o.name.split(" ").map(w => w[0]).join("").toUpperCase().slice(0, 2);
      const icon   = L.divIcon({
        className: "",
        html: `<div style="
          width:36px;height:36px;border-radius:50%;background:${color};
          border:3px solid white;box-shadow:0 2px 8px rgba(0,0,0,0.3);
          display:flex;align-items:center;justify-content:center;
          color:white;font-size:11px;font-weight:700;cursor:pointer;
          font-family:system-ui,sans-serif;
        ">${initials}</div>`,
        iconSize: [36, 36],
        iconAnchor: [18, 18],
      });

      const popup = `
        <div style="font-family:system-ui,sans-serif;min-width:160px;">
          <div style="font-weight:700;font-size:13px;color:#1e293b;">${o.name}</div>
          <div style="font-size:11px;color:#64748b;margin-top:2px;">${o.city?.name ?? "Unknown City"}</div>
          <div style="font-size:11px;color:${color};font-weight:600;margin-top:4px;">${o.gpsStatus}</div>
          <div style="font-size:10px;color:#94a3b8;margin-top:2px;">${lat.toFixed(5)}, ${lng.toFixed(5)}</div>
          <a href="https://maps.google.com/?q=${lat},${lng}" target="_blank"
             style="display:inline-block;margin-top:6px;font-size:11px;color:#0d9488;text-decoration:underline;">
            Open in Google Maps ↗
          </a>
        </div>
      `;

      if (markersRef.current.has(o.id)) {
        markersRef.current.get(o.id)!.setLatLng([lat, lng]).setIcon(icon).bindPopup(popup);
      } else {
        const m = L.marker([lat, lng], { icon })
          .addTo(map)
          .bindPopup(popup)
          .on("click", () => setSelected(o));
        markersRef.current.set(o.id, m);
      }
    });

    // Remove markers for officers no longer in list
    markersRef.current.forEach((m, id) => {
      if (!seen.has(id)) { m.remove(); markersRef.current.delete(id); }
    });

    // Fit bounds if officers present
    if (seen.size > 0) {
      const bounds = L.latLngBounds(
        [...markersRef.current.values()].map(m => m.getLatLng())
      );
      if (bounds.isValid()) map.fitBounds(bounds, { padding: [50, 50], maxZoom: 14 });
    }
  }

  useEffect(() => {
    load();
    const t = setInterval(load, 10_000);
    return () => clearInterval(t);
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [leafletLoaded]);

  const active       = officers.filter(o => o.gpsStatus === "ACTIVE").length;
  const withLocation = officers.filter(o => Number(o.lastLatitude) && Number(o.lastLongitude));

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div>
          <h2 className="text-xl font-bold text-slate-900">Live GPS Tracking</h2>
          <p className="text-sm text-slate-500 mt-0.5">Real-time field officer positions · refreshes every 10s</p>
        </div>
        <button onClick={load} disabled={loading}
          className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50 transition-colors shadow-sm">
          <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} />
          Refresh
        </button>
      </div>

      {/* Stats */}
      <div className="grid grid-cols-3 gap-4">
        <div className="rounded-2xl border border-slate-200 bg-white p-4 shadow-sm">
          <p className="text-2xl font-bold text-slate-900">{officers.length}</p>
          <p className="text-xs text-slate-500 mt-0.5 flex items-center gap-1">
            <Users className="h-3 w-3" /> Total Officers
          </p>
        </div>
        <div className="rounded-2xl border border-slate-200 bg-white p-4 shadow-sm">
          <p className="text-2xl font-bold text-emerald-600">{active}</p>
          <p className="text-xs text-slate-500 mt-0.5 flex items-center gap-1">
            <Navigation className="h-3 w-3" /> Active GPS
          </p>
        </div>
        <div className="rounded-2xl border border-slate-200 bg-white p-4 shadow-sm">
          <p className="text-xs text-slate-400 mb-1">Last update</p>
          <p className="text-sm font-semibold text-slate-700 flex items-center gap-1">
            <Clock className="h-3.5 w-3.5 text-slate-400" />
            {lastUpdate ? lastUpdate.toLocaleTimeString() : "Waiting…"}
          </p>
        </div>
      </div>

      {/* Map */}
      <div className="rounded-2xl border border-slate-200 bg-white overflow-hidden shadow-sm">
        <div className="px-5 py-4 border-b border-slate-100 flex items-center justify-between">
          <h3 className="text-sm font-semibold text-slate-800">
            Officer Locations{withLocation.length > 0 ? ` (${withLocation.length} on map)` : ""}
          </h3>
          <span className="text-xs text-teal-600 bg-teal-50 rounded-full px-2 py-0.5 border border-teal-200">
            Interactive · Leaflet + OpenStreetMap
          </span>
        </div>

        {withLocation.length === 0 ? (
          <div className="flex flex-col items-center justify-center h-64 bg-slate-50">
            <MapPin className="h-10 w-10 text-slate-200" />
            <p className="mt-3 text-sm font-medium text-slate-400">No active officers in field</p>
            <p className="text-xs text-slate-300 mt-1">Locations appear when officers open the app</p>
          </div>
        ) : (
          <div ref={mapRef} className="w-full h-[420px]" />
        )}
      </div>

      {/* Officer list */}
      {officers.length > 0 && (
        <div className="rounded-2xl border border-slate-200 bg-white shadow-sm overflow-hidden">
          <div className="px-5 py-4 border-b border-slate-100">
            <h3 className="text-sm font-semibold text-slate-800">Field Officers ({officers.length})</h3>
          </div>
          <div className="divide-y divide-slate-50">
            {officers.map(o => {
              const lat = Number(o.lastLatitude);
              const lng = Number(o.lastLongitude);
              const hasLoc = !isNaN(lat) && !isNaN(lng) && lat && lng;
              const sl = statusLabel(o.gpsStatus);
              return (
                <div key={o.id}
                  onClick={() => {
                    setSelected(o);
                    if (hasLoc && leafletRef.current && window.L) {
                      leafletRef.current.flyTo([lat, lng], 15, { duration: 1.5 });
                      markersRef.current.get(o.id)?.openPopup();
                    }
                  }}
                  className={`flex items-center gap-4 px-5 py-3 cursor-pointer transition-colors ${
                    selected?.id === o.id ? "bg-teal-50 border-l-2 border-teal-500" : "hover:bg-slate-50/50"
                  }`}
                >
                  <div className="relative">
                    <div className="flex h-9 w-9 shrink-0 items-center justify-center rounded-full bg-teal-100 text-sm font-bold text-teal-700">
                      {o.name.split(" ").map(w => w[0]).join("").toUpperCase().slice(0, 2)}
                    </div>
                    <span className="absolute -bottom-0.5 -right-0.5">
                      <GpsStatusDot status={o.gpsStatus} />
                    </span>
                  </div>
                  <div className="flex-1 min-w-0">
                    <p className="text-sm font-semibold text-slate-800">{o.name}</p>
                    <p className="text-xs text-slate-400 truncate">
                      {hasLoc ? `${lat.toFixed(5)}, ${lng.toFixed(5)}` : "No location yet"}
                      {o.city ? ` · ${o.city.name}` : ""}
                    </p>
                  </div>
                  <div className="text-right space-y-1">
                    <span className={`text-[10px] font-semibold rounded-full px-2 py-0.5 border ${sl.cls}`}>
                      {sl.text}
                    </span>
                    <p className="text-[10px] text-slate-400">
                      {o.lastSeenAt ? new Date(o.lastSeenAt).toLocaleTimeString() : "Never"}
                    </p>
                    {hasLoc && (
                      <a href={`https://maps.google.com/?q=${lat},${lng}`}
                        target="_blank" rel="noreferrer"
                        onClick={e => e.stopPropagation()}
                        className="text-xs text-teal-600 hover:underline">
                        Maps ↗
                      </a>
                    )}
                  </div>
                </div>
              );
            })}
          </div>
        </div>
      )}
    </div>
  );
}
