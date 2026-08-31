"use client";

import { useEffect, useRef, useState, useCallback } from "react";
import {
  RefreshCw, Users, Clock, Navigation, Building2, ChevronDown,
  ExternalLink, CheckCircle2, Circle, Calendar, Activity,
  AlertCircle, Search, MapPin, TrendingUp, Zap, Route,
} from "lucide-react";

interface City { id: number; name: string; latitude: number | null; longitude: number | null; geofenceRadius: number | null; }
interface Officer {
  id: number; name: string; email: string; phone: string; profilePhoto: string | null;
  gpsStatus: string; lastLatitude: number | null; lastLongitude: number | null;
  lastSeenAt: string | null; lastPingAt: string | null;
  city: { id: number; name: string } | null;
  lastSpeedKmh?: number | null; lastActivity?: string | null; lastHeading?: number | null;
}
interface ETAData { visitId: number; customerName: string | null; eta_minutes: number; eta_walk_minutes: number | null; distance_km: number | null; navigating_since: string | null; }
interface OfficerVisit { id: number; sequence: number; customerName: string; address: string; status: string; latitude: number | null; longitude: number | null; }
interface TrailPoint { lat: number; lng: number; speed?: number; heading?: number; time: string; }

function stripHtml(s: string) {
  return (s || "")
    .replace(/<[^>]*>/g, "")
    .replace(/&amp;/gi,  "&")
    .replace(/&lt;/gi,   "<")
    .replace(/&gt;/gi,   ">")
    .replace(/&quot;/gi, '"')
    .replace(/&#39;/gi,  "'")
    .replace(/&nbsp;/gi, " ")
    .replace(/\s+/g, " ")
    .trim();
}
function isValidPakCoord(lat: number, lng: number) { return lat >= 20 && lat <= 40 && lng >= 55 && lng <= 80; }

function statusLabel(s: string) {
  const u = s?.toUpperCase();
  if (u === "ACTIVE") return { text: "Active",   cls: "text-emerald-700 bg-emerald-50 border border-emerald-200" };
  if (u === "IDLE")   return { text: "Idle",     cls: "text-amber-700  bg-amber-50  border border-amber-200"  };
  if (u === "MOCK")   return { text: "Mock GPS", cls: "text-orange-700 bg-orange-50 border border-orange-200" };
  return                     { text: "Offline",  cls: "text-slate-500  bg-slate-50  border border-slate-200"  };
}

function activityIcon(a: string | null | undefined) {
  switch ((a ?? "").toUpperCase()) {
    case "MOVING_FAST": return "🚗";
    case "MOVING":      return "🚶";
    case "MOVING_SLOW": return "🐢";
    case "STATIONARY":  return "🅿️";
    default:            return "—";
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
  if (diff < 60)   return `${Math.floor(diff)}s ago`;
  if (diff < 3600) return `${Math.floor(diff / 60)}m ago`;
  return `${Math.floor(diff / 3600)}h ago`;
}

const GMAP_API_KEY = process.env.NEXT_PUBLIC_GOOGLE_MAPS_API_KEY ?? "";

// ── Smooth animation helpers (Uber-style) ─────────────────────────────────────
function lerp(a: number, b: number, t: number) { return a + (b - a) * t; }
function easeInOut(t: number) { return t < 0.5 ? 2 * t * t : -1 + (4 - 2 * t) * t; }

function calcBearing(lat1: number, lng1: number, lat2: number, lng2: number): number {
  const dLng = (lng2 - lng1) * Math.PI / 180;
  const la1  = lat1 * Math.PI / 180;
  const la2  = lat2 * Math.PI / 180;
  const y = Math.sin(dLng) * Math.cos(la2);
  const x = Math.cos(la1) * Math.sin(la2) - Math.sin(la1) * Math.cos(la2) * Math.cos(dLng);
  return ((Math.atan2(y, x) * 180 / Math.PI) + 360) % 360;
}

function animateMarkerTo(
  marker: any,
  officerId: number,
  fromLat: number, fromLng: number,
  toLat: number,   toLng: number,
  durationMs: number,
  frameMap: Record<number, number>,
) {
  if (frameMap[officerId]) { cancelAnimationFrame(frameMap[officerId]); }
  const start = performance.now();
  const step = (now: number) => {
    const t = Math.min((now - start) / durationMs, 1);
    const e = easeInOut(t);
    marker.position = { lat: lerp(fromLat, toLat, e), lng: lerp(fromLng, toLng, e) };
    if (t < 1) { frameMap[officerId] = requestAnimationFrame(step); }
    else { delete frameMap[officerId]; }
  };
  frameMap[officerId] = requestAnimationFrame(step);
}

// ── Google Maps sub-component ─────────────────────────────────────────────────
function LiveMap({
  officers, trailPoints, onSelect,
  focusOfficerId, navRoute,
}: {
  officers: Officer[];
  trailPoints: TrailPoint[];
  onSelect: (o: Officer) => void;
  focusOfficerId?: number | null;
  navRoute?: Array<{ lat: number; lng: number }>;
}) {
  const mapRef       = useRef<HTMLDivElement>(null);
  const gmap         = useRef<any>(null);
  const markers      = useRef<Record<number, any>>({});
  const trailPoly    = useRef<any>(null);
  const navPoly      = useRef<any>(null);
  const infoWindow   = useRef<any>(null);
  const prevPos      = useRef<Record<number, { lat: number; lng: number }>>({});
  const animFrames   = useRef<Record<number, number>>({});
  const [ready, setReady] = useState(false);
  const onSelectRef = useRef(onSelect);
  useEffect(() => { onSelectRef.current = onSelect; }, [onSelect]);

  const buildPinEl = useCallback((o: Officer) => {
    const isActive = o.gpsStatus?.toUpperCase() === "ACTIVE";
    const isMock   = o.gpsStatus?.toUpperCase() === "MOCK";
    const initials = stripHtml(o.name).split(" ").map(w => w[0]).join("").toUpperCase().slice(0, 2) || "?";
    const pinColor = isActive ? "#C8102E" : isMock ? "#f97316" : "#94a3b8";
    const dotColor = isActive ? "#22c55e" : isMock ? "#f97316" : "#94a3b8";
    const heading  = o.lastHeading;
    const arrowHtml = (heading != null && isActive)
      ? `<div style="position:absolute;top:-12px;left:50%;transform:translateX(-50%) rotate(${heading}deg);font-size:16px;filter:drop-shadow(0 1px 2px rgba(0,0,0,.5));">▲</div>`
      : "";
    const speedHtml = (o.lastSpeedKmh != null && Number(o.lastSpeedKmh) > 0)
      ? `<div style="position:absolute;top:-26px;left:50%;transform:translateX(-50%);background:#1e293b;color:white;font-size:8px;font-weight:800;border-radius:6px;padding:2px 5px;white-space:nowrap;box-shadow:0 2px 6px rgba(0,0,0,.3);">${Number(o.lastSpeedKmh).toFixed(0)} km/h</div>`
      : "";
    const el = document.createElement("div");
    el.style.cssText = "position:relative;width:52px;height:80px;cursor:pointer;";
    el.innerHTML = `
      ${arrowHtml}${speedHtml}
      <div style="width:48px;height:48px;border-radius:50%;background:${pinColor};border:3px solid white;box-shadow:0 4px 14px rgba(0,0,0,.35);display:flex;align-items:center;justify-content:center;overflow:hidden;">
        ${o.profilePhoto
          ? `<img src="${o.profilePhoto}" style="width:100%;height:100%;object-fit:cover;border-radius:50%;"/>`
          : `<span style="font-size:14px;font-weight:800;color:white;">${initials}</span>`}
      </div>
      <span style="position:absolute;bottom:24px;right:1px;width:12px;height:12px;border-radius:50%;border:2px solid white;background:${dotColor};box-shadow:0 0 0 2px ${dotColor}33;${isActive ? "animation:gm-pulse 1.5s infinite;" : ""}"></span>
      <div style="position:absolute;bottom:4px;left:50%;transform:translateX(-50%);background:rgba(10,20,50,0.88);color:white;font-size:9px;font-weight:700;border-radius:5px;padding:2px 6px;white-space:nowrap;max-width:80px;overflow:hidden;text-overflow:ellipsis;letter-spacing:.3px;">${initials}</div>
    `;
    return el;
  }, []);

  useEffect(() => {
    if (typeof window === "undefined" || !GMAP_API_KEY) return;
    if ((window as any).google?.maps?.Map) { setReady(true); return; }
    const script = document.createElement("script");
    script.src = `https://maps.googleapis.com/maps/api/js?key=${GMAP_API_KEY}&libraries=places,marker`;
    script.async = true; script.defer = true;
    script.onload = () => {
      if (!mapRef.current) return;
      const gmaps = (window as any).google;
      const map = new gmaps.maps.Map(mapRef.current, {
        center: { lat: 34.3512, lng: 72.0189 }, zoom: 13,
        mapId: "bookmark_livemap",
        zoomControl: true, streetViewControl: false, mapTypeControl: false, fullscreenControl: true,
      });
      new gmaps.maps.TrafficLayer().setMap(map);
      infoWindow.current = new gmaps.maps.InfoWindow();
      gmap.current = map;
      if (!document.querySelector("#gm-pulse-style")) {
        const s = document.createElement("style");
        s.id = "gm-pulse-style";
        s.textContent = `@keyframes gm-pulse{0%,100%{opacity:1;transform:scale(1)}50%{opacity:.5;transform:scale(1.5)}}`;
        document.head.appendChild(s);
      }
      setReady(true);
    };
    document.head.appendChild(script);
  }, []);

  useEffect(() => {
    if (!ready || !gmap.current) return;
    const validOfficers = officers.filter(o => { const lt = Number(o.lastLatitude), lg = Number(o.lastLongitude); return lt && lg && isValidPakCoord(lt, lg); });
    const currentIds = new Set(validOfficers.map(o => o.id));
    Object.keys(markers.current).forEach(idStr => {
      const id = Number(idStr);
      if (!currentIds.has(id)) {
        // Cancel any ongoing animation and remove marker
        if (animFrames.current[id]) { cancelAnimationFrame(animFrames.current[id]); delete animFrames.current[id]; }
        markers.current[id].map = null;
        delete markers.current[id];
        delete prevPos.current[id];
      }
    });
    try {
      const gmaps = (window as any).google;
      const AdvancedMarkerElement = gmaps.maps.marker.AdvancedMarkerElement;
      const bounds = new gmaps.maps.LatLngBounds();
      validOfficers.forEach(o => {
        const pos = { lat: Number(o.lastLatitude!), lng: Number(o.lastLongitude!) };
        bounds.extend(pos);

        // Calculate bearing from movement if server didn't send heading
        const prev = prevPos.current[o.id];
        const movedFar = prev &&
          (Math.abs(prev.lat - pos.lat) > 0.000015 ||
           Math.abs(prev.lng - pos.lng) > 0.000015);
        const effectiveHeading = o.lastHeading != null
          ? o.lastHeading
          : (movedFar ? calcBearing(prev.lat, prev.lng, pos.lat, pos.lng) : undefined);
        const oWithHeading = effectiveHeading != null
          ? { ...o, lastHeading: effectiveHeading }
          : o;
        const el = buildPinEl(oWithHeading);

        if (markers.current[o.id]) {
          // Smooth Uber-style animation to new position
          if (movedFar) {
            animateMarkerTo(
              markers.current[o.id], o.id,
              prev.lat, prev.lng,
              pos.lat, pos.lng,
              1400,                 // 1.4 s smooth glide
              animFrames.current,
            );
          } else {
            markers.current[o.id].position = pos;
          }
          markers.current[o.id].content = el;
        } else {
          const m = new AdvancedMarkerElement({ map: gmap.current!, position: pos, content: el });
          m.addListener("click", () => {
            const speed = o.lastSpeedKmh != null ? `${Number(o.lastSpeedKmh).toFixed(1)} km/h` : "—";
            const lastSeen = secondsAgo(o.lastPingAt ?? o.lastSeenAt);
            const actIcon = activityIcon(o.lastActivity);
            const sl = statusLabel(o.gpsStatus);
            infoWindow.current?.setContent(`
              <div style="font-family:'Inter',system-ui,sans-serif;padding:0;min-width:200px;border-radius:12px;overflow:hidden;">
                <div style="background:linear-gradient(135deg,#C8102E,#8B0000);padding:12px 14px;">
                  <p style="font-weight:800;margin:0;font-size:14px;color:white;">${stripHtml(o.name)}</p>
                  <p style="font-size:11px;color:rgba(255,255,255,.7);margin:2px 0 0;">${o.city?.name ?? "Unknown"}</p>
                </div>
                <div style="padding:10px 14px;background:white;">
                  <div style="display:flex;gap:8px;margin-bottom:8px;">
                    <span style="font-size:10px;font-weight:700;padding:2px 8px;border-radius:20px;background:${o.gpsStatus?.toUpperCase()==='ACTIVE'?'#dcfce7':'#f1f5f9'};color:${o.gpsStatus?.toUpperCase()==='ACTIVE'?'#15803d':'#64748b'};">${sl.text}</span>
                    <span style="font-size:10px;color:#94a3b8;">${lastSeen}</span>
                  </div>
                  <p style="font-size:11px;color:#475569;margin:0 0 4px;">📍 ${Number(o.lastLatitude).toFixed(4)}, ${Number(o.lastLongitude).toFixed(4)}</p>
                  <p style="font-size:11px;color:#C8102E;font-weight:700;margin:0;">${actIcon} ${speed}</p>
                </div>
              </div>
            `);
            infoWindow.current?.open({ map: gmap.current!, anchor: m });
            onSelectRef.current(o);
          });
          markers.current[o.id] = m;
        }
        // Remember position for next frame's animation
        prevPos.current[o.id] = pos;
      });
      // Single officer → zoom to street level; multiple → fit all
      if (validOfficers.length === 1 && !bounds.isEmpty()) {
        const o = validOfficers[0];
        gmap.current?.setCenter({ lat: Number(o.lastLatitude!), lng: Number(o.lastLongitude!) });
        if ((gmap.current?.getZoom() ?? 0) < 15) gmap.current?.setZoom(16);
      } else if (!bounds.isEmpty()) {
        gmap.current?.fitBounds(bounds, 80);
      }
    } catch (e) { console.error("Marker error:", e); }
  }, [officers, ready, buildPinEl]);

  // Zoom to focused officer at street level
  useEffect(() => {
    if (!ready || !gmap.current || !focusOfficerId) return;
    const o = officers.find(x => x.id === focusOfficerId);
    if (!o) return;
    const lat = Number(o.lastLatitude), lng = Number(o.lastLongitude);
    if (!lat || !lng) return;
    gmap.current.panTo({ lat, lng });
    if ((gmap.current.getZoom() ?? 0) < 15) gmap.current.setZoom(16);
  }, [focusOfficerId, ready]);

  // Draw navigation route polyline
  useEffect(() => {
    if (!ready || !gmap.current) return;
    try {
      const gmaps = (window as any).google;
      if (navPoly.current) { navPoly.current.setMap(null); navPoly.current = null; }
      if (!navRoute || navRoute.length < 2) return;
      navPoly.current = new gmaps.maps.Polyline({
        path: navRoute,
        geodesic: true,
        strokeColor: "#1D4ED8",
        strokeOpacity: 0.9,
        strokeWeight: 6,
        icons: [{
          icon: { path: gmaps.maps.SymbolPath.FORWARD_CLOSED_ARROW, scale: 3, strokeColor: "#1D4ED8" },
          offset: "100%", repeat: "100px",
        }],
        map: gmap.current,
      });
    } catch (e) { console.error("navRoute error:", e); }
  }, [navRoute, ready]);

  useEffect(() => {
    if (!ready || !gmap.current) return;
    try {
      const gmaps = (window as any).google;
      if (trailPoly.current) { trailPoly.current.setMap(null); trailPoly.current = null; }
      if (trailPoints.length < 2) return;
      const path = trailPoints.map(p => ({ lat: p.lat, lng: p.lng }));
      trailPoly.current = new gmaps.maps.Polyline({
        path, geodesic: true, strokeColor: "#C8102E", strokeOpacity: 0.8, strokeWeight: 5,
        icons: [{ icon: { path: gmaps.maps.SymbolPath.FORWARD_CLOSED_ARROW, scale: 3, strokeColor: "#C8102E" }, offset: "100%", repeat: "80px" }],
        map: gmap.current,
      });
      const bounds = new gmaps.maps.LatLngBounds();
      path.forEach((p: any) => bounds.extend(p));
      gmap.current?.fitBounds(bounds, 80);
    } catch (e) { console.error("Trail error:", e); }
  }, [trailPoints, ready]);

  return (
    <div className="relative w-full h-[500px]">
      <div ref={mapRef} className="absolute inset-0" style={{ minHeight: 500 }} />
      {!ready && (
        <div className="absolute inset-0 flex items-center justify-center bg-slate-50">
          <div className="flex flex-col items-center gap-3 text-slate-500">
            <RefreshCw className="h-6 w-6 animate-spin text-[#C8102E]" />
            <span className="text-sm font-medium">Loading Google Maps…</span>
          </div>
        </div>
      )}
      {!GMAP_API_KEY && (
        <div className="absolute inset-0 flex flex-col items-center justify-center bg-amber-50 gap-2">
          <p className="text-sm font-semibold text-amber-800">Google Maps API key missing</p>
          <p className="text-xs text-amber-600">Set <code>NEXT_PUBLIC_GOOGLE_MAPS_API_KEY</code> in Railway env</p>
        </div>
      )}
    </div>
  );
}

// ── Status dot for visit ───────────────────────────────────────────────────────
function VisitStatusDot({ status }: { status: string }) {
  const u = status?.toUpperCase();
  if (u === "COMPLETED") return <CheckCircle2 className="h-4 w-4 text-emerald-500 shrink-0" />;
  if (u === "IN_PROGRESS") return <div className="h-4 w-4 rounded-full border-2 border-[#C8102E] bg-red-100 shrink-0 animate-pulse" />;
  if (["CANCELLED","MISSED"].includes(u)) return <Circle className="h-4 w-4 text-red-400 shrink-0" />;
  return <Circle className="h-4 w-4 text-slate-300 shrink-0" />;
}

// ── Decode Google Maps encoded polyline ───────────────────────────────────────
function decodePolyline(encoded: string): Array<{ lat: number; lng: number }> {
  const result: Array<{ lat: number; lng: number }> = [];
  let index = 0, lat = 0, lng = 0;
  while (index < encoded.length) {
    let b, shift = 0, res = 0;
    do { b = encoded.charCodeAt(index++) - 63; res |= (b & 0x1f) << shift; shift += 5; } while (b >= 0x20);
    lat += (res & 1) ? ~(res >> 1) : (res >> 1);
    shift = 0; res = 0;
    do { b = encoded.charCodeAt(index++) - 63; res |= (b & 0x1f) << shift; shift += 5; } while (b >= 0x20);
    lng += (res & 1) ? ~(res >> 1) : (res >> 1);
    result.push({ lat: lat / 1e5, lng: lng / 1e5 });
  }
  return result;
}

interface ActivityEvent { id: string; icon: string; text: string; time: string; color: string; }

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
  const [navRoute,      setNavRoute]      = useState<Array<{ lat: number; lng: number }>>([]);
  const [activityFeed,  setActivityFeed]  = useState<ActivityEvent[]>([]);
  const prevOfficers    = useRef<Record<number, Officer>>({});

  useEffect(() => {
    fetch("/api/v1/cities").then(r => r.json()).then(d => { if (d.success) setCities(d.data ?? []); }).catch(() => {});
  }, []);

  const addEvent = useCallback((ev: Omit<ActivityEvent, "id" | "time">) => {
    setActivityFeed(prev => [{
      ...ev, id: `${Date.now()}-${Math.random()}`,
      time: new Date().toLocaleTimeString([], { hour: "2-digit", minute: "2-digit", second: "2-digit" }),
    }, ...prev].slice(0, 20));
  }, []);

  const load = useCallback(async () => {
    try {
      const params = selCity ? `?cityId=${selCity.id}` : "";
      const res = await fetch(`/api/v1/location${params}`).then(r => r.json());
      if (res.success) {
        const fresh: Officer[] = res.data?.bookers ?? [];
        setLastUpdate(new Date());

        // Detect state changes for activity feed
        fresh.forEach(o => {
          const prev = prevOfficers.current[o.id];
          const name = stripHtml(o.name);
          if (!prev) {
            if (o.gpsStatus === "ACTIVE") addEvent({ icon: "🟢", text: `${name} came online`, color: "text-emerald-700 bg-emerald-50" });
          } else {
            if (prev.gpsStatus !== "ACTIVE" && o.gpsStatus === "ACTIVE")
              addEvent({ icon: "🟢", text: `${name} is now active`, color: "text-emerald-700 bg-emerald-50" });
            if (prev.gpsStatus === "ACTIVE" && o.gpsStatus !== "ACTIVE")
              addEvent({ icon: "🔴", text: `${name} went ${o.gpsStatus?.toLowerCase()}`, color: "text-slate-600 bg-slate-50" });
            const prevSpd = Number(prev.lastSpeedKmh ?? 0);
            const curSpd  = Number(o.lastSpeedKmh ?? 0);
            if (prevSpd < 2 && curSpd > 5)
              addEvent({ icon: "🚗", text: `${name} started moving (${curSpd.toFixed(0)} km/h)`, color: "text-blue-700 bg-blue-50" });
            if (prevSpd > 5 && curSpd < 1)
              addEvent({ icon: "🅿️", text: `${name} stopped`, color: "text-amber-700 bg-amber-50" });
          }
          prevOfficers.current[o.id] = o;
        });

        setOfficers(fresh);
        if (selected) {
          const updated = fresh.find((o: Officer) => o.id === selected.id);
          if (updated) setSelected(updated);
        }
      }
    } catch { /* silent */ } finally { setLoading(false); }
  }, [selCity, selected, addEvent]);

  useEffect(() => { load(); const t = setInterval(load, 1_500); return () => clearInterval(t); }, [load]);

  // Fetch & draw navigation route when ETA is active
  useEffect(() => {
    if (!etaData || !selected) { setNavRoute([]); return; }
    const o = selected;
    const destVisit = officerVisits.find(v => v.id === etaData.visitId);
    if (!destVisit?.latitude || !destVisit?.longitude) { setNavRoute([]); return; }
    if (!o.lastLatitude || !o.lastLongitude) { setNavRoute([]); return; }

    fetch("/api/mobile/directions", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        originLat: Number(o.lastLatitude), originLng: Number(o.lastLongitude),
        destLat: destVisit.latitude, destLng: destVisit.longitude,
      }),
    })
      .then(r => r.json())
      .then(d => {
        const polyline = d?.data?.polyline ?? d?.polyline ?? "";
        if (polyline) setNavRoute(decodePolyline(polyline));
        else setNavRoute([]);
      })
      .catch(() => setNavRoute([]));
  }, [etaData?.visitId, selected?.id]);

  // Load visits + ETA + trail when officer is selected
  useEffect(() => {
    if (!selected) { setOfficerVisits([]); setEtaData(null); setTrailPoints([]); return; }
    setVisitsLoading(true);
    const today = new Date().toISOString().slice(0, 10);

    Promise.all([
      // Try multiple endpoints to get today's visits
      fetch(`/api/v1/visits?bookerId=${selected.id}&length=50`).then(r => r.json()).catch(() => null),
      fetch(`/api/v1/officers/${selected.id}/eta`).then(r => r.json()).catch(() => null),
      fetch(`/api/v1/gps-trail?bookerId=${selected.id}&date=${today}`).then(r => r.json()).catch(() => null),
    ]).then(([visitsRes, etaRes, trailRes]) => {
      // Handle different API response shapes: { data: [...] } or { data: { data: [...] } }
      let raw: any[] = [];
      if (visitsRes?.success) {
        const d = visitsRes.data;
        if (Array.isArray(d))                raw = d;
        else if (Array.isArray(d?.data))     raw = d.data;
        else if (Array.isArray(d?.visits))   raw = d.visits;
        else if (Array.isArray(d?.items))    raw = d.items;
      }

      // Filter to only today's visits
      const todayVisits = raw.filter((v: any) => {
        if (!v.scheduledDate && !v.createdAt && !v.date) return true; // include if no date field
        const vDate = (v.scheduledDate ?? v.date ?? v.createdAt ?? "").slice(0, 10);
        return !vDate || vDate === today;
      });

      setOfficerVisits(
        (todayVisits.length > 0 ? todayVisits : raw).map((v: any) => ({
          id: v.id,
          sequence: v.sequence ?? v.dailySequence ?? 0,
          customerName: v.customer?.name ?? v.customerName ?? v.locationName ?? "Unknown",
          address: v.customer?.address ?? v.address ?? "",
          status: v.status ?? "PENDING",
          latitude: v.customer?.latitude  ? Number(v.customer.latitude)  : null,
          longitude: v.customer?.longitude ? Number(v.customer.longitude) : null,
        }))
      );

      if (etaRes?.success && etaRes.data) setEtaData(etaRes.data);
      else setEtaData(null);
      if (trailRes?.success && trailRes.data?.trail?.length > 0) setTrailPoints(trailRes.data.trail as TrailPoint[]);
      else setTrailPoints([]);
    })
    .catch(() => { setOfficerVisits([]); setEtaData(null); setTrailPoints([]); })
    .finally(() => setVisitsLoading(false));
  }, [selected?.id]);

  const filteredOfficers = officers.filter(o => {
    const matchName = !officerSearch || stripHtml(o.name).toLowerCase().includes(officerSearch.toLowerCase());
    const matchGps  = gpsFilter === "all" || o.gpsStatus?.toUpperCase() === gpsFilter;
    return matchName && matchGps;
  });
  const withLoc = filteredOfficers.filter(o => { const lt = Number(o.lastLatitude), lg = Number(o.lastLongitude); return lt && lg && isValidPakCoord(lt, lg); });
  const cleanOfficers = filteredOfficers.map(o => ({ ...o, name: stripHtml(o.name) }));
  const active = officers.filter(o => o.gpsStatus === "ACTIVE").length;

  return (
    <div className="space-y-5">

      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h2 className="text-xl font-bold text-slate-900 flex items-center gap-2">
            <span className="h-2 w-2 rounded-full bg-emerald-500 animate-pulse inline-block" />
            Live GPS Tracking
          </h2>
          <p className="text-sm text-slate-500 mt-0.5">Real-time officer positions · live marker animation</p>
        </div>
        <div className="flex items-center gap-2">
          {/* City filter */}
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
              <div className="absolute right-0 mt-1 w-52 rounded-xl border border-slate-200 bg-white shadow-xl z-50 overflow-hidden max-h-60 overflow-y-auto">
                <button onClick={() => { setSelCity(null); setDropOpen(false); }}
                  className={`w-full px-3 py-2.5 text-xs text-left hover:bg-slate-50 ${!selCity ? "text-[#C8102E] font-semibold bg-red-50" : "text-slate-700"}`}>All Cities</button>
                {cities.map(c => (
                  <button key={c.id} onClick={() => { setSelCity(c); setDropOpen(false); }}
                    className={`w-full px-3 py-2.5 text-xs text-left hover:bg-slate-50 ${selCity?.id === c.id ? "text-[#C8102E] font-semibold bg-red-50" : "text-slate-700"}`}>{c.name}</button>
                ))}
              </div>
            )}
          </div>
          <button onClick={load} disabled={loading}
            className="flex items-center gap-1.5 rounded-xl border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50 shadow-sm disabled:opacity-50">
            <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin text-[#C8102E]" : ""}`} />
            Refresh
          </button>
        </div>
      </div>

      {/* Filters */}
      <div className="flex flex-wrap items-center gap-2">
        <div className="relative flex-1 min-w-48 max-w-sm">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
          <input value={officerSearch} onChange={e => setOfficerSearch(e.target.value)}
            placeholder="Search officer…"
            className="w-full rounded-xl border border-slate-200 bg-white pl-9 pr-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-red-500/20 focus:border-[#C8102E] transition" />
        </div>
        <select value={gpsFilter} onChange={e => setGpsFilter(e.target.value)}
          className="rounded-xl border border-slate-200 bg-white px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-red-500/20 cursor-pointer">
          <option value="all">All Status</option>
          <option value="ACTIVE">Active</option>
          <option value="IDLE">Idle</option>
          <option value="OFFLINE">Offline</option>
        </select>
      </div>

      {/* KPI cards */}
      <div className="grid grid-cols-2 sm:grid-cols-4 gap-3">
        {[
          { label: "Officers",    value: String(officers.length), icon: Users,      color: "bg-slate-100 text-slate-600" },
          { label: "Active GPS",  value: String(active),          icon: Navigation, color: "bg-emerald-50 text-emerald-600" },
          { label: "On Map",      value: String(withLoc.length),  icon: MapPin,     color: "bg-red-50 text-[#C8102E]" },
          { label: "Last Update", value: lastUpdate ? lastUpdate.toLocaleTimeString() : "Waiting…", icon: Clock, color: "bg-slate-50 text-slate-500" },
        ].map(s => (
          <div key={s.label} className="rounded-2xl border border-slate-200 bg-white p-4 shadow-sm">
            <div className={`inline-flex h-8 w-8 items-center justify-center rounded-xl ${s.color} mb-3`}>
              <s.icon className="h-4 w-4" />
            </div>
            <p className="text-2xl font-bold text-slate-900 leading-none mb-1">{s.value}</p>
            <p className="text-xs text-slate-500 font-medium">{s.label}</p>
          </div>
        ))}
      </div>

      {/* Map card */}
      <div className="rounded-2xl border border-slate-200 bg-white overflow-hidden shadow-sm">
        <div className="px-5 py-3.5 border-b border-slate-100 flex items-center justify-between bg-white">
          <div className="flex items-center gap-2">
            <Route className="h-4 w-4 text-[#C8102E]" />
            <h3 className="text-sm font-semibold text-slate-800">
              {selCity ? `${selCity.name} — Live Tracking` : "All Officers — Live Tracking"}
            </h3>
            {withLoc.length > 0 && (
              <span className="text-xs text-slate-400 bg-slate-100 px-2 py-0.5 rounded-full font-medium">{withLoc.length} on map</span>
            )}
            {selected && trailPoints.length > 0 && (
              <span className="text-xs text-[#C8102E] bg-red-50 px-2 py-0.5 rounded-full font-medium">{trailPoints.length} trail pts</span>
            )}
          </div>
          <div className="flex items-center gap-2">
            {selected && (
              <button onClick={() => { setSelected(null); setTrailPoints([]); }}
                className="text-xs text-slate-400 hover:text-slate-700 px-2 py-1 rounded-lg hover:bg-slate-100 transition-colors">
                Clear ×
              </button>
            )}
            <a href={`https://maps.google.com/maps?q=34.3512,72.0189`} target="_blank" rel="noreferrer"
              className="flex items-center gap-1 text-xs text-slate-400 hover:text-[#C8102E] transition-colors">
              <ExternalLink className="h-3.5 w-3.5" /> Google Maps
            </a>
          </div>
        </div>

        {withLoc.length === 0 ? (
          <div className="flex flex-col items-center justify-center h-64 bg-slate-50">
            <Navigation className="h-12 w-12 text-slate-200 mb-3" />
            <p className="text-sm font-semibold text-slate-400">No active officers on map</p>
            <p className="text-xs text-slate-300 mt-1">Officers appear when the app is open and GPS is pinging</p>
          </div>
        ) : (
          <div className="relative">
            <LiveMap
            officers={withLoc}
            trailPoints={trailPoints}
            onSelect={setSelected}
            focusOfficerId={selected?.id}
            navRoute={navRoute}
          />

            {/* Selected officer detail panel */}
            {selected && (() => {
              const lat      = Number(selected.lastLatitude);
              const lng      = Number(selected.lastLongitude);
              const done     = officerVisits.filter(v => v.status?.toUpperCase() === "COMPLETED").length;
              const inProg   = officerVisits.filter(v => v.status?.toUpperCase() === "IN_PROGRESS").length;
              const total    = officerVisits.length;
              const speed    = selected.lastSpeedKmh != null ? Number(selected.lastSpeedKmh) : null;
              const lastSeen = selected.lastPingAt ?? selected.lastSeenAt;
              const sl       = statusLabel(selected.gpsStatus);

              return (
                <div className="absolute bottom-4 right-4 w-[300px] bg-white rounded-2xl shadow-2xl border border-slate-100 overflow-hidden flex flex-col z-[2000] max-h-[500px]"
                  style={{ backdropFilter: "blur(8px)" }}>

                  {/* Header */}
                  <div className="relative p-4 bg-gradient-to-br from-[#C8102E] to-[#7B0000]">
                    <button onClick={() => { setSelected(null); setTrailPoints([]); }}
                      className="absolute top-3 right-3 text-white/50 hover:text-white text-lg leading-none w-6 h-6 flex items-center justify-center rounded-full hover:bg-white/10 transition-colors">×</button>
                    <div className="flex items-center gap-3">
                      {selected.profilePhoto ? (
                        <img src={selected.profilePhoto} alt={selected.name} className="h-11 w-11 rounded-full object-cover border-2 border-white/30 shrink-0" />
                      ) : (
                        <div className="h-11 w-11 rounded-full bg-white/20 flex items-center justify-center text-white font-bold text-sm border-2 border-white/30 shrink-0">
                          {stripHtml(selected.name).split(" ").map(w => w[0]).join("").toUpperCase().slice(0, 2)}
                        </div>
                      )}
                      <div className="min-w-0">
                        <p className="font-bold text-white text-sm truncate">{stripHtml(selected.name)}</p>
                        <p className="text-white/60 text-xs">{selected.city?.name ?? "Unknown City"}</p>
                      </div>
                    </div>
                    <div className="mt-3 flex items-center gap-2 flex-wrap">
                      <span className="text-[10px] font-bold px-2 py-0.5 rounded-full bg-white/20 text-white border border-white/20">
                        {sl.text}
                      </span>
                      {(() => {
                        const diff = lastSeen ? (Date.now() - new Date(lastSeen).getTime()) / 1000 : 9999;
                        const fresh = diff < 15;
                        return (
                          <span className={`text-[10px] font-semibold px-1.5 py-0.5 rounded-full ${fresh ? "bg-emerald-400/30 text-emerald-100" : "bg-red-400/30 text-red-200"}`}>
                            {fresh ? "🔴 LIVE" : `⚠ ${secondsAgo(lastSeen)}`}
                          </span>
                        );
                      })()}
                      <a href={`https://maps.google.com/?q=${lat},${lng}`} target="_blank" rel="noreferrer"
                        className="ml-auto flex items-center gap-1 text-[10px] text-white/70 hover:text-white">
                        <ExternalLink className="h-3 w-3" /> Open Maps
                      </a>
                    </div>
                  </div>

                  {/* Live movement */}
                  <div className="px-4 py-3 border-b border-slate-100">
                    <div className="flex items-center gap-1.5 mb-2">
                      <Activity className="h-3.5 w-3.5 text-[#C8102E]" />
                      <span className="text-xs font-semibold text-slate-700">Live Movement</span>
                    </div>
                    <div className="grid grid-cols-3 gap-2">
                      <div className="bg-slate-50 rounded-xl p-2.5 text-center border border-slate-100">
                        <p className="text-sm font-bold text-slate-900">{speed != null ? speed.toFixed(0) : "—"}</p>
                        <p className="text-[9px] text-slate-400 font-semibold mt-0.5">KM/H</p>
                      </div>
                      <div className="bg-slate-50 rounded-xl p-2.5 text-center border border-slate-100">
                        <p className="text-sm font-bold text-slate-900">{activityIcon(selected.lastActivity)}</p>
                        <p className="text-[9px] text-slate-400 font-semibold mt-0.5">STATUS</p>
                      </div>
                      <div className="bg-slate-50 rounded-xl p-2.5 text-center border border-slate-100">
                        <p className="text-sm font-bold text-slate-900">{trailPoints.length}</p>
                        <p className="text-[9px] text-slate-400 font-semibold mt-0.5">PINGS</p>
                      </div>
                    </div>
                    {selected.lastHeading != null && (
                      <div className="mt-2 flex items-center gap-1.5 text-[10px] text-slate-400">
                        <TrendingUp className="h-3 w-3" />
                        <span>Heading {Math.round(selected.lastHeading)}° · {lat.toFixed(4)}, {lng.toFixed(4)}</span>
                      </div>
                    )}
                    {etaData && (
                      <div className="mt-2.5 bg-amber-50 rounded-xl p-2.5 border border-amber-100">
                        <div className="flex items-center gap-1 mb-1">
                          <Zap className="h-3 w-3 text-amber-600" />
                          <span className="text-[10px] font-bold text-amber-800">Navigating</span>
                        </div>
                        <p className="text-xs font-semibold text-slate-700">
                          ETA: {etaData.eta_minutes} min{etaData.distance_km ? ` · ${Number(etaData.distance_km).toFixed(1)} km` : ""}
                        </p>
                        {etaData.customerName && (
                          <p className="text-[10px] text-slate-500 truncate mt-0.5">→ {etaData.customerName}</p>
                        )}
                      </div>
                    )}
                  </div>

                  {/* Today's visits */}
                  <div className="flex-1 overflow-y-auto">
                    <div className="px-4 py-2.5 flex items-center justify-between sticky top-0 bg-white border-b border-slate-100 z-10">
                      <div className="flex items-center gap-1.5">
                        <Calendar className="h-3.5 w-3.5 text-[#C8102E]" />
                        <span className="text-xs font-semibold text-slate-700">Today's Schedule</span>
                      </div>
                      {total > 0 && (
                        <span className="text-[10px] font-semibold text-slate-500">
                          {done}/{total} done
                          {inProg > 0 && <span className="ml-1 text-[#C8102E]">· {inProg} active</span>}
                        </span>
                      )}
                    </div>

                    {visitsLoading ? (
                      <div className="flex items-center gap-2 px-4 py-4 text-xs text-slate-400">
                        <RefreshCw className="h-3.5 w-3.5 animate-spin" /> Loading visits…
                      </div>
                    ) : officerVisits.length === 0 ? (
                      <div className="px-4 py-5 text-center">
                        <AlertCircle className="h-7 w-7 text-slate-200 mx-auto mb-2" />
                        <p className="text-xs font-medium text-slate-400">No visits found</p>
                        <p className="text-[10px] text-slate-300 mt-0.5">Run the scheduler to plan today's visits</p>
                      </div>
                    ) : (
                      <div>
                        {officerVisits.map((v, idx) => {
                          const isDone   = v.status?.toUpperCase() === "COMPLETED";
                          const isActive = v.status?.toUpperCase() === "IN_PROGRESS";
                          const isMissed = ["CANCELLED","MISSED"].includes(v.status?.toUpperCase());
                          return (
                            <div key={v.id} className={`flex items-start gap-2.5 px-4 py-2.5 border-b border-slate-50 last:border-0 ${isActive ? "bg-red-50" : "hover:bg-slate-50"}`}>
                              <VisitStatusDot status={v.status} />
                              <div className="min-w-0 flex-1">
                                <p className={`text-xs font-semibold truncate ${isDone ? "line-through text-slate-400" : isActive ? "text-[#C8102E]" : "text-slate-700"}`}>
                                  <span className="text-slate-400 mr-1 font-normal">#{v.sequence || idx + 1}</span>
                                  {v.customerName}
                                </p>
                                {v.address && <p className="text-[10px] text-slate-400 truncate mt-0.5">{v.address}</p>}
                                {v.latitude && v.longitude && (
                                  <a href={`https://maps.google.com/?q=${v.latitude},${v.longitude}`}
                                    target="_blank" rel="noreferrer"
                                    className="text-[10px] text-[#C8102E] hover:underline mt-0.5 inline-block">
                                    Navigate ↗
                                  </a>
                                )}
                              </div>
                              <span className={`shrink-0 text-[9px] px-1.5 py-0.5 rounded-full font-bold ${
                                isDone   ? "bg-emerald-100 text-emerald-700" :
                                isActive ? "bg-red-100 text-[#C8102E]" :
                                isMissed ? "bg-red-50 text-red-500" :
                                "bg-slate-100 text-slate-500"
                              }`}>
                                {isDone ? "Done" : isActive ? "Active" : isMissed ? "Missed" : "Pending"}
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

      {/* Live Activity Feed */}
      {activityFeed.length > 0 && (
        <div className="rounded-2xl border border-slate-200 bg-white shadow-sm overflow-hidden">
          <div className="px-5 py-3.5 border-b border-slate-100 flex items-center justify-between">
            <div className="flex items-center gap-2">
              <span className="h-2 w-2 rounded-full bg-emerald-500 animate-pulse" />
              <h3 className="text-sm font-semibold text-slate-800">Live Activity Feed</h3>
            </div>
            <button onClick={() => setActivityFeed([])}
              className="text-xs text-slate-400 hover:text-slate-600 px-2 py-1 rounded-lg hover:bg-slate-100">
              Clear
            </button>
          </div>
          <div className="divide-y divide-slate-50 max-h-48 overflow-y-auto">
            {activityFeed.map(ev => (
              <div key={ev.id} className={`flex items-center gap-3 px-5 py-2.5 ${ev.color}`}>
                <span className="text-base shrink-0">{ev.icon}</span>
                <p className="text-xs font-medium flex-1 min-w-0">{ev.text}</p>
                <span className="text-[10px] text-slate-400 shrink-0 font-mono">{ev.time}</span>
              </div>
            ))}
          </div>
        </div>
      )}

      {/* Officer list */}
      {cleanOfficers.length > 0 && (
        <div className="rounded-2xl border border-slate-200 bg-white shadow-sm overflow-hidden">
          <div className="px-5 py-4 border-b border-slate-100 flex items-center justify-between">
            <div className="flex items-center gap-2">
              <Users className="h-4 w-4 text-[#C8102E]" />
              <h3 className="text-sm font-semibold text-slate-800">Field Officers ({cleanOfficers.length})</h3>
            </div>
            <span className="text-xs text-slate-400">Click to view trail</span>
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
                  className={`flex items-center gap-4 px-5 py-3.5 transition-all ${valid ? "cursor-pointer hover:bg-slate-50" : "opacity-60"} ${isSelected ? "bg-red-50 border-l-4 border-[#C8102E]" : "border-l-4 border-transparent"}`}>
                  <div className="relative shrink-0">
                    {o.profilePhoto ? (
                      <img src={o.profilePhoto} alt={o.name} className="h-10 w-10 rounded-full object-cover border-2 border-[#C8102E]/30" />
                    ) : (
                      <div className={`flex h-10 w-10 shrink-0 items-center justify-center rounded-full text-sm font-bold text-white ${isSelected ? "bg-[#C8102E]" : valid ? "bg-[#C8102E]/80" : "bg-slate-300"}`}>
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
                  <div className="shrink-0 text-right space-y-1">
                    {speed != null && speed > 0 && (
                      <p className="text-xs font-bold text-[#C8102E]">{activityIcon(o.lastActivity)} {speed.toFixed(0)} km/h</p>
                    )}
                    <span className={`text-[10px] font-semibold rounded-full px-2 py-0.5 ${sl.cls}`}>{sl.text}</span>
                    <p className="text-[10px] text-slate-400">{secondsAgo(o.lastPingAt ?? o.lastSeenAt)}</p>
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
