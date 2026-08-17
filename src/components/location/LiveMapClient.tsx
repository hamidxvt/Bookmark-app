"use client";

import { useEffect, useRef, useState, useCallback } from "react";
import { MapPin, RefreshCw, Users, Clock, Navigation, AlertTriangle, Building2, ChevronDown, ExternalLink } from "lucide-react";

interface City {
  id: number; name: string;
  latitude: number | null; longitude: number | null;
  geofenceRadius: number | null;
}

interface Officer {
  id: number; name: string; email: string;
  gpsStatus: string;
  lastLatitude: number | null; lastLongitude: number | null;
  lastSeenAt: string | null;
  city: { id: number; name: string } | null;
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

// ── Simple SVG map canvas ─────────────────────────────────────────────────────
// Projects lat/lng to pixel using equirectangular projection.
// Pans / zooms to fit all officers automatically.
function SvgMap({ officers, selected, onSelect }: {
  officers: Officer[];
  selected: Officer | null;
  onSelect: (o: Officer) => void;
}) {
  const W = 900, H = 440;
  const pts = officers.filter(o => {
    const lt = Number(o.lastLatitude), lg = Number(o.lastLongitude);
    return lt && lg && isValidPakCoord(lt, lg);
  });

  if (pts.length === 0) return null;

  // Compute bounding box with padding
  const lats = pts.map(o => Number(o.lastLatitude));
  const lngs = pts.map(o => Number(o.lastLongitude));
  const pad = 0.02;
  const minLat = Math.min(...lats) - pad, maxLat = Math.max(...lats) + pad;
  const minLng = Math.min(...lngs) - pad, maxLng = Math.max(...lngs) + pad;

  function toXY(lat: number, lng: number): [number, number] {
    const x = ((lng - minLng) / (maxLng - minLng)) * W;
    const y = H - ((lat - minLat) / (maxLat - minLat)) * H;
    return [x, y];
  }

  return (
    <svg viewBox={`0 0 ${W} ${H}`} className="w-full h-full"
      style={{ background: "linear-gradient(135deg,#e8f4f8 0%,#d4edda 100%)" }}>
      {/* Grid lines */}
      {[0.25, 0.5, 0.75].map(t => (
        <g key={t}>
          <line x1={W * t} y1={0} x2={W * t} y2={H} stroke="#c8dce8" strokeWidth="1" strokeDasharray="4 4" />
          <line x1={0} y1={H * t} x2={W} y2={H * t} stroke="#c8dce8" strokeWidth="1" strokeDasharray="4 4" />
        </g>
      ))}

      {/* Officer markers */}
      {pts.map(o => {
        const lat = Number(o.lastLatitude), lng = Number(o.lastLongitude);
        const [x, y] = toXY(lat, lng);
        const initials = stripHtml(o.name).split(" ").map(w => w[0]).join("").toUpperCase().slice(0, 2) || "?";
        const isActive = o.gpsStatus?.toUpperCase() === "ACTIVE";
        const isSel    = selected?.id === o.id;
        const fill     = isActive ? "#0D9488" : "#94a3b8";

        return (
          <g key={o.id} onClick={() => onSelect(o)} style={{ cursor: "pointer" }}>
            {isSel && <circle cx={x} cy={y} r={26} fill={fill} opacity={0.2} />}
            {isActive && (
              <circle cx={x} cy={y} r={22} fill={fill} opacity={0.15}>
                <animate attributeName="r" from="18" to="28" dur="1.5s" repeatCount="indefinite" />
                <animate attributeName="opacity" from="0.2" to="0" dur="1.5s" repeatCount="indefinite" />
              </circle>
            )}
            <circle cx={x} cy={y} r={18} fill={fill} stroke="white" strokeWidth="3"
              filter="drop-shadow(0 2px 4px rgba(0,0,0,0.25))" />
            <text x={x} y={y + 4} textAnchor="middle" fontSize="9" fontWeight="700"
              fill="white" fontFamily="system-ui,sans-serif">{initials}</text>
            <text x={x} y={y + 32} textAnchor="middle" fontSize="9" fontWeight="600"
              fill="#1e293b" fontFamily="system-ui,sans-serif"
              style={{ textShadow: "0 1px 2px white" }}>
              {stripHtml(o.name).slice(0, 14)}
            </text>
          </g>
        );
      })}
    </svg>
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
    const t = setInterval(load, 10_000);
    return () => clearInterval(t);
  }, [load]);

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
            <SvgMap officers={withLoc} selected={selected} onSelect={setSelected} />
            {/* Selected officer detail card */}
            {selected && (() => {
              const lat = Number(selected.lastLatitude), lng = Number(selected.lastLongitude);
              return (
                <div className="absolute bottom-4 left-4 right-4 sm:left-auto sm:right-4 sm:w-72 bg-white rounded-xl shadow-xl border border-slate-200 p-4">
                  <div className="flex items-start justify-between">
                    <div>
                      <p className="text-sm font-bold text-slate-900">{stripHtml(selected.name)}</p>
                      <p className="text-xs text-slate-500 mt-0.5">{selected.city?.name ?? "Unknown City"}</p>
                    </div>
                    <button onClick={() => setSelected(null)} className="text-slate-400 hover:text-slate-600 text-lg leading-none">×</button>
                  </div>
                  <div className="mt-3 space-y-1.5">
                    <div className="flex items-center gap-2">
                      <GpsDot s={selected.gpsStatus} />
                      <span className="text-xs text-slate-600">{statusLabel(selected.gpsStatus).text}</span>
                    </div>
                    <p className="text-xs text-slate-500">{lat.toFixed(5)}, {lng.toFixed(5)}</p>
                    <p className="text-xs text-slate-400">
                      Last seen: {selected.lastSeenAt ? new Date(selected.lastSeenAt).toLocaleTimeString() : "Never"}
                    </p>
                  </div>
                  <a href={`https://maps.google.com/?q=${lat},${lng}`}
                    target="_blank" rel="noreferrer"
                    className="mt-3 flex items-center gap-1.5 text-xs font-medium text-teal-600 hover:text-teal-700">
                    <ExternalLink className="h-3 w-3" /> Open exact location in Google Maps
                  </a>
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
                    <div className={`flex h-9 w-9 shrink-0 items-center justify-center rounded-full text-sm font-bold text-white ${valid ? "bg-teal-500" : "bg-slate-300"}`}>
                      {(o.name || "?").split(" ").map((w: string) => w[0]).join("").toUpperCase().slice(0, 2)}
                    </div>
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
