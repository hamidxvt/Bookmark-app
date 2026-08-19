"use client";

import { useEffect, useRef, useState, useCallback } from "react";
import { RefreshCw, Users, Clock, Navigation, Building2, ChevronDown, ExternalLink, CheckCircle2, Circle, Calendar } from "lucide-react";

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
  id: number; sequence: number;
  customerName: string; address: string;
  status: string;
  latitude: number | null; longitude: number | null;
}

function stripHtml(s: string) {
  return (s || "").replace(/<[^>]*>/g, "").replace(/\s+/g, " ").trim();
}

function isValidPakCoord(lat: number, lng: number) {
  return lat >= 20 && lat <= 40 && lng >= 55 && lng <= 80;
}

function statusLabel(s: string) {
  const u = s?.toUpperCase();
  if (u === "ACTIVE") return { text: "Active",  cls: "text-emerald-700 bg-emerald-50 border-emerald-200" };
  if (u === "IDLE")   return { text: "Idle",    cls: "text-amber-700  bg-amber-50  border-amber-200"  };
  return                     { text: "Offline", cls: "text-slate-500  bg-slate-50  border-slate-200"  };
}

function GpsDot({ s }: { s: string }) {
  const u = s?.toUpperCase();
  if (u === "ACTIVE") return <span className="h-2.5 w-2.5 rounded-full bg-emerald-500 animate-pulse inline-block" />;
  if (u === "IDLE")   return <span className="h-2.5 w-2.5 rounded-full bg-amber-400 inline-block" />;
  return <span className="h-2.5 w-2.5 rounded-full bg-slate-300 inline-block" />;
}

// ── Leaflet Map with real-time avatar markers ─────────────────────────────────
function LiveMap({ officers, onSelect }: {
  officers: Officer[];
  onSelect: (o: Officer) => void;
}) {
  const mapRef   = useRef<HTMLDivElement>(null);
  const lMap     = useRef<any>(null);
  const markers  = useRef<Record<number, any>>({});
  const [ready, setReady] = useState(false);

  // Build custom HTML marker icon
  const buildIcon = useCallback((o: Officer) => {
    const L = (window as any).L;
    const isActive = o.gpsStatus?.toUpperCase() === "ACTIVE";
    const initials = stripHtml(o.name).split(" ").map(w => w[0]).join("").toUpperCase().slice(0, 2) || "?";
    const dot = isActive
      ? `<span style="position:absolute;bottom:2px;right:2px;width:9px;height:9px;background:#10b981;border-radius:50%;border:1.5px solid white;animation:pulse 1.5s infinite;"></span>`
      : `<span style="position:absolute;bottom:2px;right:2px;width:9px;height:9px;background:#94a3b8;border-radius:50%;border:1.5px solid white;"></span>`;

    const inner = o.profilePhoto
      ? `<img src="${o.profilePhoto}" style="width:100%;height:100%;object-fit:cover;border-radius:50%;" />`
      : `<span style="font-size:13px;font-weight:700;color:white;">${initials}</span>`;

    const html = `
      <div style="position:relative;width:46px;height:46px;">
        <div style="width:44px;height:44px;border-radius:50%;background:${isActive ? "#0d9488" : "#94a3b8"};
          border:3px solid white;box-shadow:0 2px 8px rgba(0,0,0,0.35);
          display:flex;align-items:center;justify-content:center;overflow:hidden;cursor:pointer;">
          ${inner}
        </div>
        ${dot}
        <div style="position:absolute;bottom:-18px;left:50%;transform:translateX(-50%);
          background:rgba(15,30,60,0.85);color:white;font-size:9px;font-weight:600;
          border-radius:4px;padding:1px 5px;white-space:nowrap;max-width:80px;
          overflow:hidden;text-overflow:ellipsis;">
          ${initials}
        </div>
      </div>
    `;

    return L.divIcon({
      html,
      iconSize: [46, 64],
      iconAnchor: [23, 50],
      className: "",
    });
  }, []);

  // Initialise Leaflet once
  useEffect(() => {
    if (typeof window === "undefined") return;

    const initMap = () => {
      const L = (window as any).L;
      if (!L || !mapRef.current || lMap.current) return;

      const map = L.map(mapRef.current, {
        center: [34.3512, 72.0189],
        zoom: 13,
        zoomControl: true,
      });

      L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
        attribution: "© OpenStreetMap contributors",
        maxZoom: 19,
      }).addTo(map);

      // Add pulse animation CSS
      const style = document.createElement("style");
      style.textContent = `@keyframes pulse{0%,100%{opacity:1;transform:scale(1)}50%{opacity:.7;transform:scale(1.3)}}`;
      document.head.appendChild(style);

      lMap.current = map;
      setReady(true);
    };

    if ((window as any).L) {
      initMap();
      return;
    }

    // Load Leaflet CSS
    if (!document.querySelector("#leaflet-css")) {
      const link = document.createElement("link");
      link.id = "leaflet-css";
      link.rel = "stylesheet";
      link.href = "https://unpkg.com/leaflet@1.9.4/dist/leaflet.css";
      document.head.appendChild(link);
    }

    // Load Leaflet JS
    if (!document.querySelector("#leaflet-js")) {
      const script = document.createElement("script");
      script.id = "leaflet-js";
      script.src = "https://unpkg.com/leaflet@1.9.4/dist/leaflet.js";
      script.onload = initMap;
      document.head.appendChild(script);
    }
  }, []);

  // Update markers whenever officers data changes
  useEffect(() => {
    const L = (window as any).L;
    if (!ready || !lMap.current || !L) return;

    const map = lMap.current;
    const validOfficers = officers.filter(o => {
      const lt = Number(o.lastLatitude), lg = Number(o.lastLongitude);
      return lt && lg && isValidPakCoord(lt, lg);
    });

    const currentIds = new Set(validOfficers.map(o => o.id));

    // Remove markers for officers no longer present
    Object.keys(markers.current).forEach(id => {
      if (!currentIds.has(Number(id))) {
        markers.current[Number(id)].remove();
        delete markers.current[Number(id)];
      }
    });

    validOfficers.forEach(o => {
      const lat = Number(o.lastLatitude);
      const lng = Number(o.lastLongitude);
      const icon = buildIcon(o);

      if (markers.current[o.id]) {
        // Smooth position update — no map flash
        markers.current[o.id].setLatLng([lat, lng]);
        markers.current[o.id].setIcon(icon);
      } else {
        // New marker
        const m = L.marker([lat, lng], { icon })
          .addTo(map)
          .on("click", () => onSelect(o));
        markers.current[o.id] = m;
      }
    });

    // Pan to show all officers on first load
    if (validOfficers.length > 0 && Object.keys(markers.current).length === validOfficers.length) {
      const bounds = L.latLngBounds(validOfficers.map(o => [Number(o.lastLatitude), Number(o.lastLongitude)]));
      map.fitBounds(bounds, { padding: [60, 60], maxZoom: 15 });
    }
  }, [officers, ready, buildIcon, onSelect]);

  return (
    <div className="relative w-full h-[480px]">
      <div ref={mapRef} className="absolute inset-0 rounded-b-2xl" />
      {!ready && (
        <div className="absolute inset-0 flex items-center justify-center bg-slate-50 rounded-b-2xl">
          <div className="flex items-center gap-2 text-sm text-slate-500">
            <RefreshCw className="h-4 w-4 animate-spin" />
            Loading map…
          </div>
        </div>
      )}
      <div className="absolute top-3 right-3 bg-white/90 backdrop-blur-sm rounded-lg px-2.5 py-1 text-[11px] font-medium text-emerald-600 border border-emerald-200 shadow z-[1000] flex items-center gap-1.5">
        <span className="h-2 w-2 bg-emerald-500 rounded-full animate-pulse inline-block" />
        Live · updates every 5s
      </div>
    </div>
  );
}

// ── Main component ────────────────────────────────────────────────────────────
export default function LiveMapClient() {
  const [officers,      setOfficers]      = useState<Officer[]>([]);
  const [cities,        setCities]        = useState<City[]>([]);
  const [selCity,       setSelCity]       = useState<City | null>(null);
  const [loading,       setLoading]       = useState(true);
  const [lastUpdate,    setLastUpdate]    = useState<Date | null>(null);
  const [selected,      setSelected]      = useState<Officer | null>(null);
  const [dropOpen,      setDropOpen]      = useState(false);
  const [officerVisits, setOfficerVisits] = useState<OfficerVisit[]>([]);
  const [visitsLoading, setVisitsLoading] = useState(false);

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
    const t = setInterval(load, 5_000); // Every 5 seconds
    return () => clearInterval(t);
  }, [load]);

  // Load today's visits for selected officer
  useEffect(() => {
    if (!selected) { setOfficerVisits([]); return; }
    setVisitsLoading(true);
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
          <p className="text-sm text-slate-500 mt-0.5">Real-time officer positions · auto-refreshes every 5s</p>
        </div>
        <div className="flex items-center gap-2">
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
                    {c.geofenceRadius && <span className="ml-1 text-slate-400">· {c.geofenceRadius >= 1000 ? `${(c.geofenceRadius/1000).toFixed(0)}km` : `${c.geofenceRadius}m`}</span>}
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
          { label: "Officers",    value: officers.length, icon: Users,      cls: "text-slate-600 bg-slate-100" },
          { label: "Active GPS",  value: active,          icon: Navigation, cls: "text-emerald-600 bg-emerald-50" },
          { label: "On Map",      value: withLoc.length,  icon: Navigation, cls: "text-teal-600 bg-teal-50" },
          { label: "Last Update", value: null,            icon: Clock,      cls: "text-slate-500 bg-slate-50" },
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
            <Navigation className="h-10 w-10 text-slate-200" />
            <p className="mt-3 text-sm font-medium text-slate-400">No active officers on map</p>
            <p className="text-xs text-slate-300 mt-1">Officers appear when the app is open and GPS pings</p>
          </div>
        ) : (
          <div className="relative">
            <LiveMap officers={withLoc} onSelect={setSelected} />

            {/* Selected officer detail panel */}
            {selected && (() => {
              const lat = Number(selected.lastLatitude), lng = Number(selected.lastLongitude);
              const done  = officerVisits.filter(v => v.status?.toUpperCase() === "COMPLETED").length;
              const total = officerVisits.length;
              return (
                <div className="absolute bottom-4 right-4 w-80 bg-white rounded-2xl shadow-2xl border border-slate-200 overflow-hidden flex flex-col z-[2000] max-h-[420px]">
                  {/* Officer header */}
                  <div className="p-4 border-b border-slate-100 bg-gradient-to-r from-[#1A3A5C] to-[#0d9488]">
                    <div className="flex items-start justify-between">
                      <div className="flex items-center gap-3">
                        {selected.profilePhoto ? (
                          <img src={selected.profilePhoto} alt={selected.name} className="h-11 w-11 rounded-full object-cover border-2 border-white/40" />
                        ) : (
                          <div className="h-11 w-11 rounded-full bg-white/20 flex items-center justify-center text-white text-base font-bold border-2 border-white/40">
                            {stripHtml(selected.name).split(" ").map(w => w[0]).join("").toUpperCase().slice(0,2)}
                          </div>
                        )}
                        <div>
                          <p className="text-sm font-bold text-white">{stripHtml(selected.name)}</p>
                          <p className="text-xs text-white/70">{selected.city?.name ?? "Unknown City"}</p>
                        </div>
                      </div>
                      <button onClick={() => setSelected(null)} className="text-white/60 hover:text-white text-xl leading-none mt-0.5">×</button>
                    </div>
                    <div className="mt-3 flex items-center gap-3">
                      <span className={`text-[11px] font-semibold px-2 py-0.5 rounded-full bg-white/20 text-white`}>
                        {statusLabel(selected.gpsStatus).text}
                      </span>
                      <span className="text-[11px] text-white/70">{lat.toFixed(4)}, {lng.toFixed(4)}</span>
                    </div>
                    <a href={`https://maps.google.com/?q=${lat},${lng}`}
                      target="_blank" rel="noreferrer"
                      className="mt-2 flex items-center gap-1 text-xs text-white/80 hover:text-white">
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
                        <span className="text-xs font-medium text-slate-500">{done}/{total} done</span>
                      )}
                    </div>
                    {visitsLoading ? (
                      <div className="px-4 py-3 text-xs text-slate-400">Loading visits…</div>
                    ) : officerVisits.length === 0 ? (
                      <div className="px-4 py-3 text-xs text-slate-400">No visits planned today</div>
                    ) : (
                      <div className="divide-y divide-slate-50">
                        {officerVisits.slice(0, 15).map(v => {
                          const isDone   = v.status?.toUpperCase() === "COMPLETED";
                          const isMissed = ["CANCELLED","MISSED"].includes(v.status?.toUpperCase());
                          return (
                            <div key={v.id} className="flex items-start gap-2.5 px-4 py-2.5">
                              {isDone
                                ? <CheckCircle2 className="h-4 w-4 text-emerald-500 mt-0.5 shrink-0" />
                                : isMissed
                                  ? <Circle className="h-4 w-4 text-red-400 mt-0.5 shrink-0" />
                                  : <Circle className="h-4 w-4 text-slate-300 mt-0.5 shrink-0" />}
                              <div className="min-w-0 flex-1">
                                <p className={`text-xs font-medium truncate ${isDone ? "text-slate-400 line-through" : "text-slate-700"}`}>
                                  {v.customerName}
                                </p>
                                {v.address && <p className="text-[10px] text-slate-400 truncate">{v.address}</p>}
                                {v.latitude && v.longitude && (
                                  <a href={`https://maps.google.com/?q=${v.latitude},${v.longitude}`}
                                    target="_blank" rel="noreferrer"
                                    className="text-[10px] text-teal-500 hover:underline">Navigate ↗</a>
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
              const lat   = Number(o.lastLatitude), lng = Number(o.lastLongitude);
              const valid = lat && lng && isValidPakCoord(lat, lng);
              const sl    = statusLabel(o.gpsStatus);
              return (
                <div key={o.id} onClick={() => valid && setSelected(o as any)}
                  className={`flex items-center gap-4 px-5 py-3 transition-colors ${valid ? "cursor-pointer hover:bg-slate-50" : ""} ${selected?.id === o.id ? "bg-teal-50 border-l-4 border-teal-500" : ""}`}>
                  <div className="relative">
                    {o.profilePhoto ? (
                      <img src={o.profilePhoto} alt={o.name} className="h-10 w-10 rounded-full object-cover border-2 border-teal-200" />
                    ) : (
                      <div className={`flex h-10 w-10 shrink-0 items-center justify-center rounded-full text-sm font-bold text-white ${valid ? "bg-teal-500" : "bg-slate-300"}`}>
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
