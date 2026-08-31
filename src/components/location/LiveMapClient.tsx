"use client";

import { useEffect, useRef, useState, useCallback } from "react";
import {
  RefreshCw, Users, Clock, Navigation, Building2, ChevronDown,
  ExternalLink, CheckCircle2, Circle, Calendar, Activity,
  AlertCircle, Search, Zap, MapPin, TrendingUp
} from "lucide-react";

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
  lastPingAt: string | null;
  city: { id: number; name: string } | null;
  lastSpeedKmh?: number | null;
  lastActivity?: string | null;
  lastHeading?: number | null;
}

interface ETAData {
  visitId: number;
  customerName: string | null;
  eta_minutes: number;
  eta_walk_minutes: number | null;
  distance_km: number | null;
  navigating_since: string | null;
}

interface OfficerVisit {
  id: number; sequence: number;
  customerName: string; address: string;
  status: string;
  latitude: number | null; longitude: number | null;
}

interface TrailPoint { lat: number; lng: number; speed?: number; heading?: number; time: string; }

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
  if (u === "MOCK")   return { text: "Mock GPS", cls: "text-orange-700 bg-orange-50 border-orange-200" };
  return                     { text: "Offline", cls: "text-slate-500  bg-slate-50  border-slate-200"  };
}

function activityIcon(activity: string | null | undefined) {
  if (!activity) return "—";
  switch (activity.toUpperCase()) {
    case "MOVING_FAST": return "🚗";
    case "MOVING": return "🚶";
    case "MOVING_SLOW": return "🐢";
    case "STATIONARY": return "🅿️";
    default: return "—";
  }
}

function GpsDot({ s }: { s: string }) {
  const u = s?.toUpperCase();
  if (u === "ACTIVE") return <span className="h-2.5 w-2.5 rounded-full bg-emerald-500 animate-pulse inline-block" />;
  if (u === "IDLE")   return <span className="h-2.5 w-2.5 rounded-full bg-amber-400 inline-block" />;
  if (u === "MOCK")   return <span className="h-2.5 w-2.5 rounded-full bg-orange-400 inline-block" />;
  return <span className="h-2.5 w-2.5 rounded-full bg-slate-300 inline-block" />;
}

function secondsAgo(dateStr: string | null): string {
  if (!dateStr) return "Never";
  const diff = (Date.now() - new Date(dateStr).getTime()) / 1000;
  if (diff < 60) return `${Math.floor(diff)}s ago`;
  if (diff < 3600) return `${Math.floor(diff / 60)}m ago`;
  return `${Math.floor(diff / 3600)}h ago`;
}

const GMAP_API_KEY = process.env.NEXT_PUBLIC_GOOGLE_MAPS_API_KEY ?? "";

// ── Google Maps sub-component ─────────────────────────────────────────────────
function LiveMap({
  officers,
  trailPoints,
  onSelect,
}: {
  officers: Officer[];
  trailPoints: TrailPoint[];
  onSelect: (o: Officer) => void;
}) {
  const mapRef     = useRef<HTMLDivElement>(null);
  const gmap       = useRef<any>(null);
  const markers    = useRef<Record<number, any>>({});
  const trailPoly  = useRef<any>(null);
  const infoWindow = useRef<any>(null);
  const [ready, setReady] = useState(false);
  const onSelectRef = useRef(onSelect);
  useEffect(() => { onSelectRef.current = onSelect; }, [onSelect]);

  const buildPinEl = useCallback((o: Officer) => {
    const isActive  = o.gpsStatus?.toUpperCase() === "ACTIVE";
    const isMock    = o.gpsStatus?.toUpperCase() === "MOCK";
    const initials  = stripHtml(o.name).split(" ").map(w => w[0]).join("").toUpperCase().slice(0, 2) || "?";
    const pinColor  = isActive ? "#C8102E" : isMock ? "#f97316" : "#94a3b8";
    const dotColor  = isActive ? "#22c55e" : isMock ? "#f97316" : "#94a3b8";
    // Show heading arrow if available and moving
    const heading   = o.lastHeading;
    const arrowHtml = (heading != null && isActive)
      ? `<div style="position:absolute;top:-10px;left:50%;transform:translateX(-50%) rotate(${heading}deg);
           font-size:14px;line-height:1;">▲</div>`
      : "";
    const speedHtml = (o.lastSpeedKmh != null && Number(o.lastSpeedKmh) > 0)
      ? `<div style="position:absolute;top:-22px;left:50%;transform:translateX(-50%);
           background:#1e293b;color:white;font-size:8px;font-weight:700;
           border-radius:4px;padding:1px 4px;white-space:nowrap;">
           ${Number(o.lastSpeedKmh).toFixed(0)} km/h</div>`
      : "";

    const el = document.createElement("div");
    el.style.cssText = "position:relative;width:48px;height:72px;cursor:pointer;";
    el.innerHTML = `
      ${arrowHtml}
      ${speedHtml}
      <div style="width:44px;height:44px;border-radius:50%;
        background:${pinColor};border:3px solid white;
        box-shadow:0 3px 10px rgba(0,0,0,0.4);
        display:flex;align-items:center;justify-content:center;overflow:hidden;">
        ${o.profilePhoto
          ? `<img src="${o.profilePhoto}" style="width:100%;height:100%;object-fit:cover;border-radius:50%;"/>`
          : `<span style="font-size:13px;font-weight:700;color:white;">${initials}</span>`
        }
      </div>
      <span style="position:absolute;bottom:22px;right:1px;
        width:10px;height:10px;border-radius:50%;border:2px solid white;
        background:${dotColor};
        ${isActive ? "animation:gm-pulse 1.5s infinite;" : ""}"></span>
      <div style="position:absolute;bottom:4px;left:50%;transform:translateX(-50%);
        background:rgba(15,30,60,0.85);color:white;font-size:9px;font-weight:600;
        border-radius:4px;padding:1px 5px;white-space:nowrap;max-width:80px;
        overflow:hidden;text-overflow:ellipsis;">${initials}</div>
    `;
    return el;
  }, []);

  // Load Google Maps
  useEffect(() => {
    if (typeof window === "undefined" || !GMAP_API_KEY) return;
    if ((window as any).google?.maps?.Map) { setReady(true); return; }

    const script = document.createElement("script");
    script.src = `https://maps.googleapis.com/maps/api/js?key=${GMAP_API_KEY}&libraries=places,marker`;
    script.async = true;
    script.defer = true;
    script.onload = () => {
      if (!mapRef.current) return;
      const gmaps = (window as any).google;
      const map = new gmaps.maps.Map(mapRef.current, {
        center: { lat: 34.3512, lng: 72.0189 },
        zoom: 13,
        mapId: "bookmark_livemap",
        zoomControl: true,
        streetViewControl: false,
        mapTypeControl: false,
        fullscreenControl: true,
      });
      new gmaps.maps.TrafficLayer().setMap(map);
      infoWindow.current = new gmaps.maps.InfoWindow();
      gmap.current = map;

      if (!document.querySelector("#gm-pulse-style")) {
        const s = document.createElement("style");
        s.id = "gm-pulse-style";
        s.textContent = `@keyframes gm-pulse{0%,100%{opacity:1;transform:scale(1)}50%{opacity:.6;transform:scale(1.4)}}`;
        document.head.appendChild(s);
      }
      setReady(true);
    };
    script.onerror = () => console.error("Failed to load Google Maps");
    document.head.appendChild(script);
  }, []);

  // Update markers when officers data changes
  useEffect(() => {
    if (!ready || !gmap.current) return;

    const validOfficers = officers.filter(o => {
      const lt = Number(o.lastLatitude), lg = Number(o.lastLongitude);
      return lt && lg && isValidPakCoord(lt, lg);
    });
    const currentIds = new Set(validOfficers.map(o => o.id));

    Object.keys(markers.current).forEach(idStr => {
      const id = Number(idStr);
      if (!currentIds.has(id)) {
        markers.current[id].map = null;
        delete markers.current[id];
      }
    });

    try {
      const gmaps = (window as any).google;
      const AdvancedMarkerElement = gmaps.maps.marker.AdvancedMarkerElement;
      const LatLngBounds = gmaps.maps.LatLngBounds;
      const bounds = new LatLngBounds();

      validOfficers.forEach(o => {
        const pos = { lat: Number(o.lastLatitude!), lng: Number(o.lastLongitude!) };
        bounds.extend(pos);
        const el = buildPinEl(o);

        if (markers.current[o.id]) {
          markers.current[o.id].position = pos;
          markers.current[o.id].content = el;
        } else {
          const m = new AdvancedMarkerElement({ map: gmap.current!, position: pos, content: el });
          m.addListener("click", () => {
            const speed = o.lastSpeedKmh != null ? `${Number(o.lastSpeedKmh).toFixed(1)} km/h` : "—";
            const activity = o.lastActivity ?? "—";
            infoWindow.current?.setContent(`
              <div style="font-family:sans-serif;padding:4px 6px;min-width:140px;">
                <p style="font-weight:800;margin:0 0 4px;font-size:13px;">${stripHtml(o.name)}</p>
                <p style="font-size:11px;color:#64748b;margin:0;">${o.city?.name ?? "Unknown"}</p>
                <p style="font-size:11px;color:#64748b;margin:2px 0 0;">📍 ${Number(o.lastLatitude).toFixed(4)}, ${Number(o.lastLongitude).toFixed(4)}</p>
                <p style="font-size:11px;color:#C8102E;margin:2px 0 0;font-weight:600;">🚗 ${speed} · ${activity}</p>
                <p style="font-size:10px;color:#94a3b8;margin:4px 0 0;">${secondsAgo(o.lastPingAt ?? o.lastSeenAt)}</p>
              </div>
            `);
            infoWindow.current?.open({ map: gmap.current!, anchor: m });
            onSelectRef.current(o);
          });
          markers.current[o.id] = m;
        }
      });

      if (validOfficers.length === 1 && !bounds.isEmpty()) {
        gmap.current?.fitBounds(bounds, 100);
      }
    } catch (e) {
      console.error("Marker error:", e);
    }
  }, [officers, ready, buildPinEl]);

  // Draw breadcrumb trail polyline
  useEffect(() => {
    if (!ready || !gmap.current) return;
    try {
      const gmaps = (window as any).google;

      // Remove old trail
      if (trailPoly.current) {
        trailPoly.current.setMap(null);
        trailPoly.current = null;
      }

      if (trailPoints.length < 2) return;

      const path = trailPoints.map(p => ({ lat: p.lat, lng: p.lng }));
      trailPoly.current = new gmaps.maps.Polyline({
        path,
        geodesic: true,
        strokeColor: "#C8102E",
        strokeOpacity: 0.7,
        strokeWeight: 4,
        icons: [{
          icon: { path: gmaps.maps.SymbolPath.FORWARD_CLOSED_ARROW, scale: 3, strokeColor: "#C8102E" },
          offset: "100%",
          repeat: "80px",
        }],
        map: gmap.current,
      });

      // Fit map to trail
      const bounds = new gmaps.maps.LatLngBounds();
      path.forEach((p: any) => bounds.extend(p));
      gmap.current?.fitBounds(bounds, 60);
    } catch (e) {
      console.error("Trail error:", e);
    }
  }, [trailPoints, ready]);

  return (
    <div className="relative w-full h-[480px]">
      <div ref={mapRef} className="absolute inset-0 rounded-b-2xl" style={{ minHeight: 480 }} />
      {!ready && (
        <div className="absolute inset-0 flex items-center justify-center bg-slate-50 rounded-b-2xl">
          <div className="flex items-center gap-2 text-sm text-slate-500">
            <RefreshCw className="h-4 w-4 animate-spin" />
            Loading Google Maps…
          </div>
        </div>
      )}
      {!GMAP_API_KEY && (
        <div className="absolute inset-0 flex flex-col items-center justify-center bg-amber-50 rounded-b-2xl gap-2">
          <p className="text-sm font-semibold text-amber-800">Google Maps API key missing</p>
          <p className="text-xs text-amber-600">Set <code>NEXT_PUBLIC_GOOGLE_MAPS_API_KEY</code> in Railway env</p>
        </div>
      )}
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
  const [officerSearch, setOfficerSearch] = useState("");
  const [gpsFilter,     setGpsFilter]     = useState("all");
  const [officerVisits, setOfficerVisits] = useState<OfficerVisit[]>([]);
  const [visitsLoading, setVisitsLoading] = useState(false);
  const [etaData,       setEtaData]       = useState<ETAData | null>(null);
  const [trailPoints,   setTrailPoints]   = useState<TrailPoint[]>([]);
  const [trailLoading,  setTrailLoading]  = useState(false);

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
        // Keep selected officer data fresh
        if (selected) {
          const updated = (res.data?.bookers ?? []).find((o: Officer) => o.id === selected.id);
          if (updated) setSelected(updated);
        }
      }
    } catch { /* silent */ }
    finally { setLoading(false); }
  }, [selCity, selected]);

  // Poll every 2 seconds for near-real-time updates
  useEffect(() => {
    load();
    const t = setInterval(load, 2_000);
    return () => clearInterval(t);
  }, [load]);

  // Load today's visits + ETA + trail for selected officer
  useEffect(() => {
    if (!selected) {
      setOfficerVisits([]);
      setEtaData(null);
      setTrailPoints([]);
      return;
    }

    setVisitsLoading(true);
    setTrailLoading(true);

    const today = new Date().toISOString().slice(0, 10);

    Promise.all([
      fetch(`/api/v1/visits?bookerId=${selected.id}&length=20&today=1`).then(r => r.json()),
      fetch(`/api/v1/officers/${selected.id}/eta`).then(r => r.json()).catch(() => null),
      fetch(`/api/v1/gps-trail?bookerId=${selected.id}&date=${today}`).then(r => r.json()).catch(() => null),
    ]).then(([visitsRes, etaRes, trailRes]) => {
      const raw = visitsRes.data?.data ?? visitsRes.data ?? [];
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
      if (etaRes?.success && etaRes.data) setEtaData(etaRes.data);
      else setEtaData(null);

      if (trailRes?.success && trailRes.data?.trail?.length > 0) {
        setTrailPoints(trailRes.data.trail as TrailPoint[]);
      } else {
        setTrailPoints([]);
      }
    })
    .catch(() => { setOfficerVisits([]); setEtaData(null); setTrailPoints([]); })
    .finally(() => { setVisitsLoading(false); setTrailLoading(false); });
  }, [selected?.id]);

  const filteredOfficers = officers.filter(o => {
    const matchName = !officerSearch || stripHtml(o.name).toLowerCase().includes(officerSearch.toLowerCase());
    const matchGps = gpsFilter === "all" || o.gpsStatus?.toUpperCase() === gpsFilter;
    return matchName && matchGps;
  });
  const withLoc = filteredOfficers.filter(o => {
    const lt = Number(o.lastLatitude), lg = Number(o.lastLongitude);
    return lt && lg && isValidPakCoord(lt, lg);
  });
  const cleanOfficers = filteredOfficers.map(o => ({ ...o, name: stripHtml(o.name) }));
  const active = officers.filter(o => o.gpsStatus === "ACTIVE").length;

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h2 className="text-xl font-bold text-slate-900">Live GPS Tracking</h2>
          <p className="text-sm text-slate-500 mt-0.5">
            Real-time officer positions · updates every 2s
          </p>
        </div>
        <div className="flex items-center gap-2">
          <div className="relative">
            <button onClick={() => setDropOpen(!dropOpen)}
              className="flex items-center gap-2 rounded-xl border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-700 hover:bg-slate-50 transition-colors shadow-sm min-w-[140px] justify-between">
              <span className="flex items-center gap-1.5">
                <Building2 className="h-3.5 w-3.5 text-[#C8102E]" />
                {selCity ? selCity.name : "All Cities"}
              </span>
              <ChevronDown className={`h-3.5 w-3.5 text-slate-400 transition-transform ${dropOpen ? "rotate-180" : ""}`} />
            </button>
            {dropOpen && (
              <div className="absolute right-0 mt-1 w-52 rounded-xl border border-slate-200 bg-white shadow-lg z-50 overflow-hidden max-h-60 overflow-y-auto">
                <button onClick={() => { setSelCity(null); setDropOpen(false); }}
                  className={`w-full px-3 py-2 text-xs text-left hover:bg-slate-50 ${!selCity ? "text-[#C8102E] font-semibold bg-red-50" : "text-slate-700"}`}>
                  All Cities
                </button>
                {cities.map(c => (
                  <button key={c.id} onClick={() => { setSelCity(c); setDropOpen(false); }}
                    className={`w-full px-3 py-2 text-xs text-left hover:bg-slate-50 ${selCity?.id === c.id ? "text-[#C8102E] font-semibold bg-red-50" : "text-slate-700"}`}>
                    {c.name}
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

      {/* Filters */}
      <div className="flex flex-wrap items-center gap-2">
        <div className="relative flex-1 min-w-48 max-w-xs">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
          <input value={officerSearch} onChange={e => setOfficerSearch(e.target.value)}
            placeholder="Search officer name…"
            className="w-full rounded-xl border border-slate-200 bg-white pl-9 pr-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-red-500/20 focus:border-red-500 transition" />
        </div>
        <div className="relative">
          <select value={gpsFilter} onChange={e => setGpsFilter(e.target.value)}
            className="rounded-xl border border-slate-200 bg-white px-3 pr-8 py-2 text-sm appearance-none cursor-pointer focus:outline-none">
            <option value="all">All GPS Status</option>
            <option value="ACTIVE">Active</option>
            <option value="IDLE">Idle</option>
            <option value="OFFLINE">Offline</option>
          </select>
          <ChevronDown className="pointer-events-none absolute right-2.5 top-1/2 -translate-y-1/2 h-3.5 w-3.5 text-slate-400" />
        </div>
      </div>

      {/* KPI cards */}
      <div className="grid grid-cols-2 sm:grid-cols-4 gap-4">
        {[
          { label: "Officers",    value: officers.length, icon: Users,       cls: "text-slate-600 bg-slate-100" },
          { label: "Active GPS",  value: active,          icon: Navigation,  cls: "text-emerald-600 bg-emerald-50" },
          { label: "On Map",      value: withLoc.length,  icon: MapPin,      cls: "text-[#C8102E] bg-red-50" },
          { label: "Last Update", value: null,            icon: Clock,       cls: "text-slate-500 bg-slate-50" },
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
            {selected && trailPoints.length > 0 && (
              <span className="ml-2 text-[#C8102E] font-normal text-xs">
                · {trailPoints.length} trail points
              </span>
            )}
          </h3>
          {selected && (
            <button onClick={() => { setSelected(null); setTrailPoints([]); }}
              className="text-xs text-slate-500 hover:text-slate-800">
              Clear selection ×
            </button>
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
            <LiveMap officers={withLoc} trailPoints={trailPoints} onSelect={setSelected} />

            {/* Selected officer detail panel */}
            {selected && (() => {
              const lat = Number(selected.lastLatitude), lng = Number(selected.lastLongitude);
              const done  = officerVisits.filter(v => v.status?.toUpperCase() === "COMPLETED").length;
              const total = officerVisits.length;
              const speed = selected.lastSpeedKmh != null ? Number(selected.lastSpeedKmh) : null;
              const lastSeen = selected.lastPingAt ?? selected.lastSeenAt;

              return (
                <div className="absolute bottom-4 right-4 w-80 bg-white rounded-2xl shadow-2xl border border-slate-200 overflow-hidden flex flex-col z-[2000] max-h-[480px]">
                  {/* Officer header */}
                  <div className="p-4 border-b border-slate-100 bg-gradient-to-br from-[#C8102E] to-[#8B0000]">
                    <div className="flex items-start justify-between">
                      <div className="flex items-center gap-3">
                        {selected.profilePhoto ? (
                          <img src={selected.profilePhoto} alt={selected.name}
                            className="h-12 w-12 rounded-full object-cover border-2 border-white/40" />
                        ) : (
                          <div className="h-12 w-12 rounded-full bg-white/20 flex items-center justify-center text-white text-base font-bold border-2 border-white/40">
                            {stripHtml(selected.name).split(" ").map(w => w[0]).join("").toUpperCase().slice(0, 2)}
                          </div>
                        )}
                        <div>
                          <p className="text-sm font-bold text-white">{stripHtml(selected.name)}</p>
                          <p className="text-xs text-white/70">{selected.city?.name ?? "Unknown City"}</p>
                          <p className="text-[10px] text-white/50 mt-0.5">{selected.email}</p>
                        </div>
                      </div>
                      <button onClick={() => { setSelected(null); setTrailPoints([]); }}
                        className="text-white/60 hover:text-white text-xl leading-none mt-0.5">×</button>
                    </div>

                    {/* Status row */}
                    <div className="mt-3 flex items-center gap-2 flex-wrap">
                      <span className="text-[11px] font-semibold px-2 py-0.5 rounded-full bg-white/20 text-white">
                        {statusLabel(selected.gpsStatus).text}
                      </span>
                      <span className="text-[11px] text-white/70">{secondsAgo(lastSeen)}</span>
                      <a href={`https://maps.google.com/?q=${lat},${lng}`}
                        target="_blank" rel="noreferrer"
                        className="flex items-center gap-0.5 text-[11px] text-white/80 hover:text-white ml-auto">
                        <ExternalLink className="h-3 w-3" /> Maps
                      </a>
                    </div>
                  </div>

                  {/* Live movement stats */}
                  <div className="px-4 py-3 bg-slate-50 border-b border-slate-100">
                    <div className="flex items-center gap-1.5 mb-2">
                      <Activity className="h-3.5 w-3.5 text-[#C8102E]" />
                      <span className="text-xs font-semibold text-slate-700">Live Movement</span>
                      {trailLoading && <RefreshCw className="h-3 w-3 text-slate-400 animate-spin ml-auto" />}
                    </div>
                    <div className="grid grid-cols-3 gap-2">
                      <div className="bg-white rounded-xl p-2.5 border border-slate-100 text-center">
                        <p className="text-base font-bold text-slate-900">
                          {speed != null ? `${speed.toFixed(0)}` : "—"}
                        </p>
                        <p className="text-[9px] text-slate-400 font-medium">km/h</p>
                      </div>
                      <div className="bg-white rounded-xl p-2.5 border border-slate-100 text-center">
                        <p className="text-base font-bold text-slate-900">
                          {activityIcon(selected.lastActivity)}
                        </p>
                        <p className="text-[9px] text-slate-400 font-medium">Activity</p>
                      </div>
                      <div className="bg-white rounded-xl p-2.5 border border-slate-100 text-center">
                        <p className="text-base font-bold text-slate-900">
                          {trailPoints.length}
                        </p>
                        <p className="text-[9px] text-slate-400 font-medium">Pings today</p>
                      </div>
                    </div>

                    {/* Heading indicator */}
                    {selected.lastHeading != null && (
                      <div className="mt-2 flex items-center gap-2 text-xs text-slate-500">
                        <TrendingUp className="h-3 w-3" />
                        <span>Heading: {Math.round(selected.lastHeading)}° · {lat.toFixed(5)}, {lng.toFixed(5)}</span>
                      </div>
                    )}

                    {/* ETA section */}
                    {etaData && (
                      <div className="mt-2.5 bg-amber-50 rounded-xl p-2.5 border border-amber-100">
                        <div className="flex items-center gap-1.5 mb-1">
                          <Clock className="h-3 w-3 text-amber-600" />
                          <span className="text-[11px] font-semibold text-amber-800">Navigating to customer</span>
                        </div>
                        <p className="text-xs text-slate-700 font-medium">
                          ETA: {etaData.eta_minutes} min
                          {etaData.distance_km ? ` · ${Number(etaData.distance_km).toFixed(1)} km` : ""}
                        </p>
                        {etaData.customerName && (
                          <p className="text-[10px] text-slate-500 truncate mt-0.5">→ {etaData.customerName}</p>
                        )}
                      </div>
                    )}
                  </div>

                  {/* Today's visits */}
                  <div className="flex-1 overflow-y-auto">
                    <div className="px-4 py-2.5 bg-slate-50 border-b border-slate-100 flex items-center justify-between">
                      <div className="flex items-center gap-1.5">
                        <Calendar className="h-3.5 w-3.5 text-[#C8102E]" />
                        <span className="text-xs font-semibold text-slate-700">Today's Schedule</span>
                      </div>
                      {total > 0 && (
                        <span className="text-xs font-semibold text-slate-500">
                          {done}/{total} done
                          <span className="ml-1 text-emerald-600">
                            {total > 0 ? `(${Math.round(done/total*100)}%)` : ""}
                          </span>
                        </span>
                      )}
                    </div>
                    {visitsLoading ? (
                      <div className="px-4 py-3 text-xs text-slate-400">Loading visits…</div>
                    ) : officerVisits.length === 0 ? (
                      <div className="px-4 py-4 text-center">
                        <AlertCircle className="h-6 w-6 text-slate-200 mx-auto mb-1" />
                        <p className="text-xs text-slate-400">No visits planned today</p>
                      </div>
                    ) : (
                      <div className="divide-y divide-slate-50">
                        {officerVisits.slice(0, 15).map(v => {
                          const isDone   = v.status?.toUpperCase() === "COMPLETED";
                          const isMissed = ["CANCELLED", "MISSED"].includes(v.status?.toUpperCase());
                          return (
                            <div key={v.id} className="flex items-start gap-2.5 px-4 py-2.5 hover:bg-slate-50">
                              {isDone
                                ? <CheckCircle2 className="h-4 w-4 text-emerald-500 mt-0.5 shrink-0" />
                                : isMissed
                                  ? <Circle className="h-4 w-4 text-red-400 mt-0.5 shrink-0" />
                                  : <Circle className="h-4 w-4 text-slate-300 mt-0.5 shrink-0" />}
                              <div className="min-w-0 flex-1">
                                <p className={`text-xs font-medium truncate ${isDone ? "text-slate-400 line-through" : "text-slate-700"}`}>
                                  <span className="text-slate-400 mr-1">#{v.sequence}</span>
                                  {v.customerName}
                                </p>
                                {v.address && <p className="text-[10px] text-slate-400 truncate">{v.address}</p>}
                                {v.latitude && v.longitude && (
                                  <a href={`https://maps.google.com/?q=${v.latitude},${v.longitude}`}
                                    target="_blank" rel="noreferrer"
                                    className="text-[10px] text-[#C8102E] hover:underline">Navigate ↗</a>
                                )}
                              </div>
                              <span className={`text-[9px] px-1.5 py-0.5 rounded-full font-semibold shrink-0 ${
                                isDone ? "bg-emerald-100 text-emerald-700" :
                                isMissed ? "bg-red-100 text-red-600" :
                                "bg-slate-100 text-slate-500"
                              }`}>
                                {isDone ? "Done" : isMissed ? "Missed" : "Pending"}
                              </span>
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
          <div className="px-5 py-4 border-b border-slate-100 flex items-center justify-between">
            <h3 className="text-sm font-semibold text-slate-800">
              Field Officers ({cleanOfficers.length})
            </h3>
            <span className="text-xs text-slate-400">Click to view trail on map</span>
          </div>
          <div className="divide-y divide-slate-50">
            {cleanOfficers.map(o => {
              const lat   = Number(o.lastLatitude), lng = Number(o.lastLongitude);
              const valid = lat && lng && isValidPakCoord(lat, lng);
              const sl    = statusLabel(o.gpsStatus);
              const speed = o.lastSpeedKmh != null ? Number(o.lastSpeedKmh) : null;
              const isSelected = selected?.id === o.id;
              return (
                <div key={o.id} onClick={() => valid && setSelected(o as any)}
                  className={`flex items-center gap-4 px-5 py-3 transition-colors ${valid ? "cursor-pointer hover:bg-slate-50" : ""} ${isSelected ? "bg-red-50 border-l-4 border-[#C8102E]" : ""}`}>
                  <div className="relative shrink-0">
                    {o.profilePhoto ? (
                      <img src={o.profilePhoto} alt={o.name} className="h-10 w-10 rounded-full object-cover border-2 border-[#C8102E]" />
                    ) : (
                      <div className={`flex h-10 w-10 shrink-0 items-center justify-center rounded-full text-sm font-bold text-white ${valid ? "bg-[#C8102E]" : "bg-slate-300"}`}>
                        {(o.name || "?").split(" ").map((w: string) => w[0]).join("").toUpperCase().slice(0, 2)}
                      </div>
                    )}
                    <span className="absolute -bottom-0.5 -right-0.5"><GpsDot s={o.gpsStatus} /></span>
                  </div>
                  <div className="flex-1 min-w-0">
                    <p className="text-sm font-semibold text-slate-800">{o.name || "Unknown"}</p>
                    <p className="text-xs text-slate-400 truncate">
                      {valid ? `${lat.toFixed(4)}, ${lng.toFixed(4)}` : "Waiting for GPS…"}
                      {o.city ? ` · ${o.city.name}` : ""}
                    </p>
                  </div>
                  {/* Speed + Activity */}
                  <div className="shrink-0 text-right space-y-0.5">
                    {speed != null && speed > 0 && (
                      <p className="text-xs font-bold text-[#C8102E]">
                        {activityIcon(o.lastActivity)} {speed.toFixed(0)} km/h
                      </p>
                    )}
                    <span className={`text-[10px] font-semibold rounded-full px-2 py-0.5 border ${sl.cls}`}>{sl.text}</span>
                    <p className="text-[10px] text-slate-400">
                      {secondsAgo(o.lastPingAt ?? o.lastSeenAt)}
                    </p>
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
