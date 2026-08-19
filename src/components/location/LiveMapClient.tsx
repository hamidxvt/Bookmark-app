"use client";

import { useEffect, useRef, useState, useCallback } from "react";
import { MapPin, RefreshCw, Users, Clock, Navigation, Building2, ChevronDown, ExternalLink, CheckCircle2, Circle, Calendar } from "lucide-react";

interface City {
  id: number; name: string;
  latitude: number | null; longitude: number | null;
  geofenceRadius: number | null;
}

interface Officer {
  id: number; name: string; email: string; phone: string;
  profilePhoto: string | null;
  gpsStatus: string;
  lastLatitude: number | null; lastLongitude: number | null;
  lastSeenAt: string | null;
  city: { id: number; name: string } | null;
}

interface OfficerVisit {
  id: number;
  sequence: number;
  customerName: string;
  address: string;
  status: string;
  latitude: number | null;
  longitude: number | null;
}

function stripHtml(s: string): string {
  return (s || "").replace(/<[^>]*>/g, "").replace(/\s+/g, " ").trim();
}

function isValidPakCoord(lat: number, lng: number): boolean {
  return lat >= 20 && lat <= 40 && lng >= 55 && lng <= 80;
}

function GpsDot({ s }: { s: string }) {
  const u = s?.toUpperCase();
  if (u === "ACTIVE") return <span className="h-2.5 w-2.5 rounded-full bg-emerald-500 animate-pulse inline-block" />;
  if (u === "IDLE")   return <span className="h-2.5 w-2.5 rounded-full bg-amber-400 inline-block" />;
  return <span className="h-2.5 w-2.5 rounded-full bg-slate-300 inline-block" />;
}

function statusLabel(s: string) {
  const u = s?.toUpperCase();
  if (u === "ACTIVE") return { text: "Active",   cls: "text-emerald-700 bg-emerald-50 border-emerald-200" };
  if (u === "IDLE")   return { text: "Idle",     cls: "text-amber-700  bg-amber-50  border-amber-200"  };
  return                     { text: "Offline",  cls: "text-slate-500  bg-slate-50  border-slate-200"  };
}

// ── Live Map using OpenStreetMap iframe + SVG overlay ────────────────────────
// Uses OSM static tile iframe for the real map background,
// with an SVG overlay layer for officer pins that updates every 10s.
function LiveMap({ officers, selected, onSelect }: {
  officers: Officer[];
  selected: Officer | null;
  onSelect: (o: Officer) => void;
}) {
  const containerRef = useRef<HTMLDivElement>(null);
  const [size, setSize] = useState({ w: 900, h: 440 });

  const pts = officers.filter(o => {
    const lt = Number(o.lastLatitude), lg = Number(o.lastLongitude);
    return lt && lg && isValidPakCoord(lt, lg);
  });

  // Track container size for responsive overlay
  useEffect(() => {
    if (!containerRef.current) return;
    const ro = new ResizeObserver(entries => {
      const e = entries[0];
      setSize({ w: e.contentRect.width, h: e.contentRect.height });
    });
    ro.observe(containerRef.current);
    return () => ro.disconnect();
  }, []);

  if (pts.length === 0) return null;

  // Compute map bounds centered on officers
  const lats = pts.map(o => Number(o.lastLatitude));
  const lngs = pts.map(o => Number(o.lastLongitude));
  const centerLat = (Math.min(...lats) + Math.max(...lats)) / 2;
  const centerLng = (Math.min(...lngs) + Math.max(...lngs)) / 2;
  const zoom = pts.length === 1 ? 15 : 13;

  // For a single officer — show their exact location with OSM marker
  // For multiple officers — show bounding box of all
  const isSingle = pts.length === 1;

  // Use embeddable OSM URL for both single and multiple officers
  // The non-embed URL (openstreetmap.org/?) blocks iframes via X-Frame-Options
  const pad = 0.015;
  const osmUrl = isSingle
    ? `https://www.openstreetmap.org/export/embed.html?bbox=${Number(pts[0].lastLongitude)-pad},${Number(pts[0].lastLatitude)-pad},${Number(pts[0].lastLongitude)+pad},${Number(pts[0].lastLatitude)+pad}&layer=mapnik&marker=${Number(pts[0].lastLatitude)},${Number(pts[0].lastLongitude)}`
    : `https://www.openstreetmap.org/export/embed.html?bbox=${Math.min(...pts.map(o => Number(o.lastLongitude)))-0.03},${Math.min(...pts.map(o => Number(o.lastLatitude)))-0.02},${Math.max(...pts.map(o => Number(o.lastLongitude)))+0.03},${Math.max(...pts.map(o => Number(o.lastLatitude)))+0.02}&layer=mapnik`;

  return (
    <div ref={containerRef} className="relative w-full h-[440px] overflow-hidden rounded-b-2xl">
      {/* Real OpenStreetMap — marker is built into the URL, no SVG overlay needed */}
      <iframe
        key={`${centerLat.toFixed(4)}-${centerLng.toFixed(4)}`}
        src={osmUrl}
        className="absolute inset-0 w-full h-full border-0"
        title="Live GPS Map"
        loading="eager"
        sandbox="allow-scripts allow-same-origin"
      />

      {/* Officer info cards for multiple officers */}
      {!isSingle && (
        <div className="absolute bottom-3 left-3 flex gap-2 flex-wrap max-w-full">
          {pts.map(o => {
            const lat = Number(o.lastLatitude), lng = Number(o.lastLongitude);
            const isActive = o.gpsStatus?.toUpperCase() === "ACTIVE";
            return (
              <a key={o.id}
                href={`https://maps.google.com/?q=${lat},${lng}`}
                target="_blank" rel="noreferrer"
                className="flex items-center gap-2 bg-white/95 backdrop-blur rounded-lg px-2.5 py-1.5 text-xs font-semibold shadow border border-slate-200 hover:border-teal-400 transition-colors">
                {o.profilePhoto ? (
                  <img src={o.profilePhoto} alt={o.name} className="h-5 w-5 rounded-full object-cover" />
                ) : (
                  <span className={`h-5 w-5 rounded-full flex items-center justify-center text-white text-[10px] font-bold ${isActive ? "bg-emerald-500" : "bg-slate-300"}`}>
                    {stripHtml(o.name)[0]}
                  </span>
                )}
                <span className={`h-2 w-2 rounded-full ${isActive ? "bg-emerald-500 animate-pulse" : "bg-slate-300"}`} />
                {stripHtml(o.name)} ↗
              </a>
            );
          })}
        </div>
      )}

      <div className="absolute top-2 right-2 bg-white/90 backdrop-blur-sm rounded-lg px-2 py-1 text-[10px] text-slate-500 border border-slate-200 shadow">
        Live · updates every 10s
      </div>
    </div>
  );
}

// ── Main component ────────────────────────────────────────────────────────────
export default function LiveMapClient() {
  const [officers,     setOfficers]     = useState<Officer[]>([]);
  const [cities,       setCities]       = useState<City[]>([]);
  const [selCity,      setSelCity]      = useState<City | null>(null);
  const [loading,      setLoading]      = useState(true);
  const [lastUpdate,   setLastUpdate]   = useState<Date | null>(null);
  const [selected,     setSelected]     = useState<Officer | null>(null);
  const [dropOpen,     setDropOpen]     = useState(false);
  const [officerVisits, setOfficerVisits] = useState<OfficerVisit[]>([]);
  const [visitsLoading, setVisitsLoading] = useState(false);

  // Load cities
  useEffect(() => {
    fetch("/api/v1/cities").then(r => r.json()).then(d => {
      if (d.success) setCities(d.data ?? []);
    }).catch(() => {});
  }, []);

  const load = useCallback(async () => {
    try {
      const params = selCity ? `?cityId=${selCity.id}` : "";
      const res = await fetch(`/api/v1/location${params}`).then(r => r.json());
      if (res.success) {
        setOfficers(res.data?.bookers ?? []);
        setLastUpdate(new Date());
      }
    } catch { /* silent */ }
    finally { setLoading(false); }
  }, [selCity]);

  useEffect(() => {
    load();
    const t = setInterval(load, 3_000); // Real-time: every 3 seconds
    return () => clearInterval(t);
  }, [load]);

  // Load today's visits for selected officer
  useEffect(() => {
    if (!selected) { setOfficerVisits([]); return; }
    setVisitsLoading(true);
    const today = new Date(); today.setHours(0,0,0,0);
    fetch(`/api/v1/visits?bookerId=${selected.id}&length=20&today=1`)
      .then(r => r.json())
      .then(d => {
        const raw = d.data?.data ?? d.data ?? [];
        setOfficerVisits(
          (Array.isArray(raw) ? raw : []).map((v: any) => ({
            id: v.id,
            sequence: v.sequence ?? 0,
            customerName: v.customer?.name ?? v.customerName ?? "Unknown",
            address: v.customer?.address ?? v.address ?? "",
            status: v.status ?? "PENDING",
            latitude: v.customer?.latitude ? Number(v.customer.latitude) : null,
            longitude: v.customer?.longitude ? Number(v.customer.longitude) : null,
          }))
        );
      })
      .catch(() => setOfficerVisits([]))
      .finally(() => setVisitsLoading(false));
  }, [selected]);

  const withLoc = officers.filter(o => {
    const lt = Number(o.lastLatitude), lg = Number(o.lastLongitude);
    return lt && lg && isValidPakCoord(lt, lg);
  });
  const cleanOfficers = officers.map(o => ({ ...o, name: stripHtml(o.name) }));
  const active = withLoc.filter(o => o.gpsStatus === "ACTIVE").length;

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h2 className="text-xl font-bold text-slate-900">Live GPS Tracking</h2>
          <p className="text-sm text-slate-500 mt-0.5">Real-time officer positions · auto-refreshes every 10s</p>
        </div>
        <div className="flex items-center gap-2">
          {/* City filter */}
          <div className="relative">
            <button onClick={() => setDropOpen(!dropOpen)}
              className="flex items-center gap-2 rounded-xl border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-700 hover:bg-slate-50 transition-colors shadow-sm min-w-[140px] justify-between">
              <span className="flex items-center gap-1.5">
                <Building2 className="h-3.5 w-3.5 text-teal-500" />
                {selCity ? selCity.name : "All Cities"}
              </span>
              <ChevronDown className={`h-3.5 w-3.5 text-slate-400 transition-transform ${dropOpen ? "rotate-180" : ""}`} />
            </button>
            {dropOpen && (
              <div className="absolute right-0 mt-1 w-52 rounded-xl border border-slate-200 bg-white shadow-lg z-50 overflow-hidden max-h-60 overflow-y-auto">
                <button onClick={() => { setSelCity(null); setDropOpen(false); }}
                  className={`w-full px-3 py-2 text-xs text-left hover:bg-slate-50 ${!selCity ? "text-teal-600 font-semibold bg-teal-50" : "text-slate-700"}`}>
                  All Cities
                </button>
                {cities.map(c => (
                  <button key={c.id} onClick={() => { setSelCity(c); setDropOpen(false); }}
                    className={`w-full px-3 py-2 text-xs text-left hover:bg-slate-50 ${selCity?.id === c.id ? "text-teal-600 font-semibold bg-teal-50" : "text-slate-700"}`}>
                    {c.name}
                    {c.geofenceRadius && <span className="ml-1 text-slate-400">· {c.geofenceRadius >= 1000 ? `${(c.geofenceRadius / 1000).toFixed(0)}km` : `${c.geofenceRadius}m`}</span>}
                  </button>
                ))}
              </div>
            )}
          </div>
          <button onClick={load} disabled={loading}
            className="flex items-center gap-1.5 rounded-xl border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50 shadow-sm">
            <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} />
            Refresh
          </button>
        </div>
      </div>

      {/* KPI cards */}
      <div className="grid grid-cols-4 gap-4">
        {[
          { label: "Officers",    value: officers.length,       icon: Users,      cls: "text-slate-600 bg-slate-100" },
          { label: "Active GPS",  value: active,                icon: Navigation, cls: "text-emerald-600 bg-emerald-50" },
          { label: "On Map",      value: withLoc.length,        icon: MapPin,     cls: "text-teal-600 bg-teal-50" },
          { label: "Last Update", value: null,                  icon: Clock,      cls: "text-slate-500 bg-slate-50" },
        ].map(s => (
          <div key={s.label} className="rounded-2xl border border-slate-200 bg-white p-4 shadow-sm">
            <div className={`inline-flex h-7 w-7 items-center justify-center rounded-lg ${s.cls} mb-2`}>
              <s.icon className="h-3.5 w-3.5" />
            </div>
            {s.value !== null
              ? <p className="text-2xl font-bold text-slate-900">{s.value}</p>
              : <p className="text-sm font-semibold text-slate-700">{lastUpdate ? lastUpdate.toLocaleTimeString() : "Waiting…"}</p>}
            <p className="text-xs text-slate-500">{s.label}</p>
          </div>
        ))}
      </div>

      {/* Map */}
      <div className="rounded-2xl border border-slate-200 bg-white overflow-hidden shadow-sm">
        <div className="px-5 py-3 border-b border-slate-100 flex items-center justify-between">
          <h3 className="text-sm font-semibold text-slate-800">
            {selCity ? `${selCity.name} — Live Tracking` : "All Officers — Live Tracking"}
            {withLoc.length > 0 && <span className="ml-2 text-slate-400 font-normal">({withLoc.length} on map)</span>}
          </h3>
          {withLoc.length > 0 && (
            <a href={`https://maps.google.com/?q=${Number(withLoc[0]?.lastLatitude)},${Number(withLoc[0]?.lastLongitude)}`}
              target="_blank" rel="noreferrer"
              className="flex items-center gap-1 text-xs text-teal-600 hover:underline">
              <ExternalLink className="h-3 w-3" /> Open in Google Maps
            </a>
          )}
        </div>

        {withLoc.length === 0 ? (
          <div className="flex flex-col items-center justify-center h-64 bg-slate-50">
            <MapPin className="h-10 w-10 text-slate-200" />
            <p className="mt-3 text-sm font-medium text-slate-400">No officers with location data</p>
            <p className="text-xs text-slate-300 mt-1">Officers appear when they open the app and GPS pings</p>
          </div>
        ) : (
          <div className="w-full h-[440px] relative overflow-hidden bg-gradient-to-br from-blue-50 to-teal-50">
            <LiveMap officers={withLoc} selected={selected} onSelect={setSelected} />
            {/* Selected officer detail card */}
            {selected && (() => {
              const lat = Number(selected.lastLatitude), lng = Number(selected.lastLongitude);
              const done = officerVisits.filter(v => v.status?.toUpperCase() === "COMPLETED").length;
              const total = officerVisits.length;
              return (
                <div className="absolute bottom-4 left-4 right-4 sm:left-auto sm:right-4 sm:w-80 bg-white rounded-xl shadow-xl border border-slate-200 overflow-hidden max-h-[calc(100%-32px)] flex flex-col">
                  {/* Officer header */}
                  <div className="p-4 border-b border-slate-100">
                    <div className="flex items-start justify-between">
                      <div className="flex items-center gap-3">
                        {selected.profilePhoto ? (
                          <img src={selected.profilePhoto} alt={selected.name} className="h-10 w-10 rounded-full object-cover border-2 border-teal-200" />
                        ) : (
                          <div className="h-10 w-10 rounded-full bg-teal-500 flex items-center justify-center text-white text-sm font-bold">
                            {stripHtml(selected.name).split(" ").map(w => w[0]).join("").toUpperCase().slice(0,2)}
                          </div>
                        )}
                        <div>
                          <p className="text-sm font-bold text-slate-900">{stripHtml(selected.name)}</p>
                          <p className="text-xs text-slate-500">{selected.city?.name ?? "Unknown City"}</p>
                        </div>
                      </div>
                      <button onClick={() => setSelected(null)} className="text-slate-400 hover:text-slate-600 text-xl leading-none mt-0.5">×</button>
                    </div>
                    <div className="mt-2 flex items-center gap-3">
                      <div className="flex items-center gap-1.5">
                        <GpsDot s={selected.gpsStatus} />
                        <span className="text-xs text-slate-600">{statusLabel(selected.gpsStatus).text}</span>
                      </div>
                      <span className="text-xs text-slate-400">{lat.toFixed(4)}, {lng.toFixed(4)}</span>
                    </div>
                    <a href={`https://maps.google.com/?q=${lat},${lng}`}
                      target="_blank" rel="noreferrer"
                      className="mt-2 flex items-center gap-1 text-xs font-medium text-teal-600 hover:underline">
                      <ExternalLink className="h-3 w-3" /> Open in Google Maps
                    </a>
                  </div>
                  {/* Today's visits */}
                  <div className="flex-1 overflow-y-auto">
                    <div className="px-4 py-2.5 bg-slate-50 border-b border-slate-100 flex items-center justify-between">
                      <div className="flex items-center gap-1.5">
                        <Calendar className="h-3.5 w-3.5 text-teal-500" />
                        <span className="text-xs font-semibold text-slate-700">Today's Schedule</span>
                      </div>
                      {total > 0 && (
                        <span className="text-xs text-slate-500">{done}/{total} done</span>
                      )}
                    </div>
                    {visitsLoading ? (
                      <div className="px-4 py-3 text-xs text-slate-400">Loading visits…</div>
                    ) : officerVisits.length === 0 ? (
                      <div className="px-4 py-3 text-xs text-slate-400">No visits planned today</div>
                    ) : (
                      <div className="divide-y divide-slate-50 max-h-52 overflow-y-auto">
                        {officerVisits.slice(0, 15).map(v => {
                          const isDone = v.status?.toUpperCase() === "COMPLETED";
                          const isMissed = v.status?.toUpperCase() === "CANCELLED" || v.status?.toUpperCase() === "MISSED";
                          return (
                            <div key={v.id} className="flex items-start gap-2.5 px-4 py-2">
                              {isDone
                                ? <CheckCircle2 className="h-4 w-4 text-emerald-500 mt-0.5 shrink-0" />
                                : isMissed
                                  ? <Circle className="h-4 w-4 text-red-400 mt-0.5 shrink-0" />
                                  : <Circle className="h-4 w-4 text-slate-300 mt-0.5 shrink-0" />}
                              <div className="min-w-0 flex-1">
                                <p className={`text-xs font-medium truncate ${isDone ? "text-slate-400 line-through" : "text-slate-700"}`}>
                                  {v.customerName}
                                </p>
                                {v.address && (
                                  <p className="text-[10px] text-slate-400 truncate">{v.address}</p>
                                )}
                                {v.latitude && v.longitude && (
                                  <a href={`https://maps.google.com/?q=${v.latitude},${v.longitude}`}
                                    target="_blank" rel="noreferrer"
                                    className="text-[10px] text-teal-500 hover:underline">
                                    Navigate ↗
                                  </a>
                                )}
                              </div>
                            </div>
                          );
                        })}
                      </div>
                    )}
                  </div>
                </div>
              );
            })()}
            <div className="absolute top-2 right-2 bg-white/80 backdrop-blur-sm rounded-lg px-2 py-1 text-[10px] text-slate-400 border border-slate-200">
              Live map · click officer for details
            </div>
          </div>
        )}
      </div>

      {/* Officer list */}
      {cleanOfficers.length > 0 && (
        <div className="rounded-2xl border border-slate-200 bg-white shadow-sm overflow-hidden">
          <div className="px-5 py-4 border-b border-slate-100">
            <h3 className="text-sm font-semibold text-slate-800">Field Officers ({cleanOfficers.length})</h3>
          </div>
          <div className="divide-y divide-slate-50">
            {cleanOfficers.map(o => {
              const lat = Number(o.lastLatitude), lng = Number(o.lastLongitude);
              const valid = lat && lng && isValidPakCoord(lat, lng);
              const sl    = statusLabel(o.gpsStatus);
              return (
                <div key={o.id} onClick={() => valid && setSelected(o)}
                  className={`flex items-center gap-4 px-5 py-3 transition-colors ${valid ? "cursor-pointer hover:bg-slate-50" : ""} ${selected?.id === o.id ? "bg-teal-50 border-l-4 border-teal-500" : ""}`}>
                  <div className="relative">
                    {o.profilePhoto ? (
                      <img src={o.profilePhoto} alt={o.name} className="h-9 w-9 rounded-full object-cover border-2 border-teal-200" />
                    ) : (
                      <div className={`flex h-9 w-9 shrink-0 items-center justify-center rounded-full text-sm font-bold text-white ${valid ? "bg-teal-500" : "bg-slate-300"}`}>
                        {(o.name || "?").split(" ").map((w: string) => w[0]).join("").toUpperCase().slice(0, 2)}
                      </div>
                    )}
                    <span className="absolute -bottom-0.5 -right-0.5"><GpsDot s={o.gpsStatus} /></span>
                  </div>
                  <div className="flex-1 min-w-0">
                    <p className="text-sm font-semibold text-slate-800">{o.name || "Unknown"}</p>
                    <p className="text-xs text-slate-400 truncate">
                      {valid ? `${lat.toFixed(5)}, ${lng.toFixed(5)}` : "Waiting for GPS ping…"}
                      {o.city ? ` · ${o.city.name}` : ""}
                    </p>
                  </div>
                  <div className="text-right space-y-1">
                    <span className={`text-[10px] font-semibold rounded-full px-2 py-0.5 border ${sl.cls}`}>{sl.text}</span>
                    <p className="text-[10px] text-slate-400">
                      {o.lastSeenAt ? new Date(o.lastSeenAt).toLocaleTimeString() : "Never"}
                    </p>
                    {valid && (
                      <a href={`https://maps.google.com/?q=${lat},${lng}`} target="_blank" rel="noreferrer"
                        onClick={e => e.stopPropagation()} className="text-xs text-teal-600 hover:underline">
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
