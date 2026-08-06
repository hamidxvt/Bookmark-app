"use client";

import { useEffect, useRef, useState, useCallback, useMemo } from "react";
import { MapPin, RefreshCw, Users, Clock, Navigation, AlertTriangle, Building2, ChevronDown } from "lucide-react";

interface City {
  id: number;
  name: string;
  latitude: number | null;
  longitude: number | null;
  geofenceRadius: number | null;
}

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

declare global {
  interface Window { L: any; }
}

// Haversine distance in metres
function distanceMetres(lat1: number, lng1: number, lat2: number, lng2: number): number {
  const R = 6371000;
  const dLat = (lat2 - lat1) * Math.PI / 180;
  const dLng = (lng2 - lng1) * Math.PI / 180;
  const a = Math.sin(dLat / 2) ** 2 +
    Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) * Math.sin(dLng / 2) ** 2;
  return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
}

function isOutOfZone(officer: Officer, city: City | null): boolean {
  if (!city?.latitude || !city?.longitude || !city?.geofenceRadius) return false;
  const lat = Number(officer.lastLatitude);
  const lng = Number(officer.lastLongitude);
  if (!lat || !lng) return false;
  return distanceMetres(lat, lng, city.latitude, city.longitude) > city.geofenceRadius;
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
  if (u === "ACTIVE")  return { text: "Active",   cls: "text-emerald-700 bg-emerald-50 border-emerald-200" };
  if (u === "IDLE")    return { text: "Idle",     cls: "text-amber-700  bg-amber-50  border-amber-200"  };
  if (u === "MOCK")    return { text: "Mock GPS", cls: "text-orange-700 bg-orange-50 border-orange-200" };
  return                      { text: "Offline",  cls: "text-slate-500  bg-slate-50  border-slate-200"  };
}

export default function LiveMapClient() {
  const mapRef     = useRef<HTMLDivElement>(null);
  const leafletRef = useRef<any>(null);
  const markersRef = useRef<Map<number, any>>(new Map());
  const geoCircRef = useRef<any>(null);

  const [officers,       setOfficers]      = useState<Officer[]>([]);
  const [cities,         setCities]        = useState<City[]>([]);
  const [selectedCity,   setSelectedCity]  = useState<City | null>(null);
  const [loading,        setLoading]       = useState(true);
  const [lastUpdate,     setLastUpdate]    = useState<Date | null>(null);
  const [selected,       setSelected]      = useState<Officer | null>(null);
  const [leafletLoaded,  setLeafletLoaded] = useState(false);
  const [cityDropOpen,   setCityDropOpen]  = useState(false);
  const [isClient,       setIsClient]      = useState(false);
  const [mapError,       setMapError]      = useState<string | null>(null);

  // Ensure we only render map on client side
  useEffect(() => {
    setIsClient(true);
  }, []);

  // Load Leaflet from CDN
  useEffect(() => {
    if (document.querySelector("#leaflet-css")) { 
      setLeafletLoaded(true); 
      return; 
    }
    const link = document.createElement("link");
    link.id = "leaflet-css";
    link.rel = "stylesheet";
    link.href = "https://unpkg.com/leaflet@1.9.4/dist/leaflet.css";
    link.onerror = () => console.warn("Failed to load Leaflet CSS");
    document.head.appendChild(link);

    const script = document.createElement("script");
    script.src = "https://unpkg.com/leaflet@1.9.4/dist/leaflet.js";
    script.onload = () => {
      console.log("Leaflet loaded successfully");
      setLeafletLoaded(true);
    };
    script.onerror = () => {
      console.error("Failed to load Leaflet JS from CDN");
      setLeafletLoaded(true); // Still set to true so we can show fallback UI
    };
    document.head.appendChild(script);
  }, []);

  // Init Leaflet map
  useEffect(() => {
    if (!leafletLoaded || !mapRef.current || leafletRef.current) return;
    
    // Check if window.L is available
    const L = (window as any).L;
    if (!L || !L.map) {
      const msg = "Leaflet library not loaded from CDN";
      console.error(msg);
      setMapError(msg);
      return;
    }

    try {
      if (mapRef.current.childNodes.length > 0) {
        mapRef.current.innerHTML = "";
      }
      
      const map = L.map(mapRef.current, { 
        zoomControl: true,
        attributionControl: true,
      }).setView([30.3753, 69.3451], 6);
      
      L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
        attribution: "© OpenStreetMap",
        maxZoom: 19,
      }).addTo(map);
      
      leafletRef.current = map;
      console.log("✓ Map initialized successfully");
      setMapError(null);
    } catch (err) {
      const errMsg = `Map init error: ${String(err)}`;
      console.error(errMsg);
      setMapError(errMsg);
    }
  }, [leafletLoaded]);

  // Load cities for filter
  useEffect(() => {
    fetch("/api/v1/cities").then(r => r.json()).then(d => {
      if (d.success) setCities(d.data ?? []);
    }).catch(() => {});
  }, []);

  // Draw geofence circle for selected city
  useEffect(() => {
    const map = leafletRef.current;
    const L   = window.L;
    if (!map || !L) return;
    if (geoCircRef.current) { geoCircRef.current.remove(); geoCircRef.current = null; }
    if (selectedCity?.latitude && selectedCity?.longitude && selectedCity?.geofenceRadius) {
      geoCircRef.current = L.circle(
        [selectedCity.latitude, selectedCity.longitude],
        {
          radius: selectedCity.geofenceRadius,
          color: "#0D9488",
          fillColor: "#0D9488",
          fillOpacity: 0.07,
          weight: 2,
          dashArray: "6 4",
        }
      ).addTo(map);
      map.flyTo([selectedCity.latitude, selectedCity.longitude], 11, { duration: 1.2 });
    }
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [selectedCity?.id, leafletLoaded]);

  const updateMarkers = useCallback((list: Officer[]) => {
    const map = leafletRef.current;
    const L   = window.L;
    if (!map || !L) return;

    const seen = new Set<number>();
    list.forEach(o => {
      const lat = Number(o.lastLatitude);
      const lng = Number(o.lastLongitude);
      if (!lat || !lng || isNaN(lat) || isNaN(lng)) return;
      // Skip invalid coordinates (test data from outside Pakistan)
      if (lat < 20 || lat > 40 || lng < 55 || lng > 80) return;
      seen.add(o.id);

      const outOfZone = isOutOfZone(o, selectedCity);
      const color = outOfZone ? "#ef4444"
        : o.gpsStatus === "ACTIVE" ? "#10b981"
        : o.gpsStatus === "IDLE"   ? "#f59e0b"
        : "#94a3b8";

      const initials = o.name.split(" ").map((w: string) => w[0]).join("").toUpperCase().slice(0, 2);
      const icon = L.divIcon({
        className: "",
        html: `<div style="
          width:38px;height:38px;border-radius:50%;background:${color};
          border:3px solid white;box-shadow:0 2px 10px rgba(0,0,0,0.3);
          display:flex;align-items:center;justify-content:center;
          color:white;font-size:11px;font-weight:700;cursor:pointer;
          font-family:system-ui,sans-serif;position:relative;
        ">
          ${initials}
          ${outOfZone ? `<div style="position:absolute;top:-6px;right:-6px;width:16px;height:16px;background:#ef4444;border-radius:50%;border:2px solid white;display:flex;align-items:center;justify-content:center;">
            <span style="color:white;font-size:9px;font-weight:900;">!</span>
          </div>` : ""}
        </div>`,
        iconSize: [38, 38],
        iconAnchor: [19, 19],
      });

      const popup = `
        <div style="font-family:system-ui,sans-serif;min-width:180px;">
          <div style="font-weight:700;font-size:13px;color:#1e293b;">${o.name}</div>
          <div style="font-size:11px;color:#64748b;margin-top:2px;">${o.city?.name ?? "Unknown City"}</div>
          ${outOfZone ? `<div style="margin-top:6px;padding:4px 8px;background:#fef2f2;border:1px solid #fecaca;border-radius:6px;font-size:11px;color:#dc2626;font-weight:600;">
            ⚠ OUT OF ZONE
          </div>` : ""}
          <div style="font-size:11px;color:${color};font-weight:600;margin-top:4px;">${o.gpsStatus}</div>
          <div style="font-size:10px;color:#94a3b8;margin-top:2px;">${lat.toFixed(5)}, ${lng.toFixed(5)}</div>
          <a href="https://maps.google.com/?q=${lat},${lng}" target="_blank"
             style="display:inline-block;margin-top:6px;font-size:11px;color:#0d9488;text-decoration:underline;">
            Open in Google Maps ↗
          </a>
        </div>`;

      if (markersRef.current.has(o.id)) {
        markersRef.current.get(o.id)!.setLatLng([lat, lng]).setIcon(icon).bindPopup(popup);
      } else {
        const m = L.marker([lat, lng], { icon })
          .addTo(map).bindPopup(popup)
          .on("click", () => setSelected(o));
        markersRef.current.set(o.id, m);
      }
    });

    markersRef.current.forEach((m, id) => {
      if (!seen.has(id)) { m.remove(); markersRef.current.delete(id); }
    });

    if (seen.size > 0 && !selectedCity) {
      const bounds = L.latLngBounds([...markersRef.current.values()].map((m: any) => m.getLatLng()));
      if (bounds.isValid()) map.fitBounds(bounds, { padding: [50, 50], maxZoom: 14 });
    }
  }, [selectedCity]);

  const load = useCallback(async () => {
    try {
      const params = selectedCity ? `?cityId=${selectedCity.id}` : "";
      const res    = await fetch(`/api/v1/location${params}`).then(r => r.json());
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
  }, [selectedCity, updateMarkers]);

  useEffect(() => {
    load();
    const t = setInterval(load, 10_000);
    return () => clearInterval(t);
  }, [load]);

  // Strip HTML tags from any string (migration data can have HTML in names)
  function stripHtml(str: string): string {
    if (!str) return "";
    return str.replace(/<[^>]*>/g, "").replace(/&[a-z]+;/gi, " ").replace(/\s+/g, " ").trim();
  }

  // Valid Pakistan bounds: lat 20-40, lng 55-80
  function isValidPakistanCoord(lat: number, lng: number): boolean {
    return lat >= 20 && lat <= 40 && lng >= 55 && lng <= 80;
  }

  // Filter valid locations — exclude California and other invalid coordinates
  const withLocation = officers.filter(o => {
    const lat = Number(o.lastLatitude);
    const lng = Number(o.lastLongitude);
    if (!lat || !lng || isNaN(lat) || isNaN(lng)) return false;
    return isValidPakistanCoord(lat, lng);
  });

  // All officers with clean names (for the list — show even those without GPS yet)
  const cleanOfficers = officers.map(o => ({ ...o, name: stripHtml(o.name) }));

  const active        = withLocation.filter(o => o.gpsStatus === "ACTIVE").length;
  const outOfZoneList = withLocation.filter(o => isOutOfZone(o, selectedCity));

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h2 className="text-xl font-bold text-slate-900">Live GPS Tracking</h2>
          <p className="text-sm text-slate-500 mt-0.5">Real-time officer positions · auto-refreshes every 10s</p>
        </div>
        <div className="flex items-center gap-2">
          {/* City filter dropdown */}
          <div className="relative">
            <button
              onClick={() => setCityDropOpen(!cityDropOpen)}
              className="flex items-center gap-2 rounded-xl border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-700 hover:bg-slate-50 transition-colors shadow-sm min-w-[140px] justify-between"
            >
              <span className="flex items-center gap-1.5">
                <Building2 className="h-3.5 w-3.5 text-teal-500" />
                {selectedCity ? selectedCity.name : "All Cities"}
              </span>
              <ChevronDown className={`h-3.5 w-3.5 text-slate-400 transition-transform ${cityDropOpen ? "rotate-180" : ""}`} />
            </button>
            {cityDropOpen && (
              <div className="absolute right-0 mt-1 w-48 rounded-xl border border-slate-200 bg-white shadow-lg z-50 overflow-hidden">
                <button
                  onClick={() => { setSelectedCity(null); setCityDropOpen(false); }}
                  className={`w-full px-3 py-2 text-xs text-left hover:bg-slate-50 transition-colors ${!selectedCity ? "text-teal-600 font-semibold bg-teal-50" : "text-slate-700"}`}
                >
                  All Cities
                </button>
                {cities.map(c => (
                  <button key={c.id}
                    onClick={() => { setSelectedCity(c); setCityDropOpen(false); }}
                    className={`w-full px-3 py-2 text-xs text-left hover:bg-slate-50 transition-colors ${selectedCity?.id === c.id ? "text-teal-600 font-semibold bg-teal-50" : "text-slate-700"}`}
                  >
                    {c.name}
                    {c.geofenceRadius && (
                      <span className="ml-1 text-slate-400">
                        · {c.geofenceRadius >= 1000 ? `${(c.geofenceRadius/1000).toFixed(0)}km` : `${c.geofenceRadius}m`} fence
                      </span>
                    )}
                  </button>
                ))}
              </div>
            )}
          </div>

          <button onClick={() => load()} disabled={loading}
            className="flex items-center gap-1.5 rounded-xl border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50 transition-colors shadow-sm">
            <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} />
            Refresh
          </button>
        </div>
      </div>

      {/* KPI Stats */}
      <div className="grid grid-cols-4 gap-4">
        {[
          { label: "Officers",     value: officers.length,       icon: Users,          cls: "text-slate-600 bg-slate-100" },
          { label: "Active GPS",   value: active,                icon: Navigation,     cls: "text-emerald-600 bg-emerald-50" },
          { label: "Out of Zone",  value: outOfZoneList.length,  icon: AlertTriangle,  cls: outOfZoneList.length > 0 ? "text-red-600 bg-red-50" : "text-slate-400 bg-slate-50" },
          { label: "Last Update",  value: null,                  icon: Clock,          cls: "text-slate-500 bg-slate-50" },
        ].map(s => (
          <div key={s.label} className="rounded-2xl border border-slate-200 bg-white p-4 shadow-sm">
            <div className={`inline-flex h-7 w-7 items-center justify-center rounded-lg ${s.cls} mb-2`}>
              <s.icon className="h-3.5 w-3.5" />
            </div>
            {s.value !== null ? (
              <p className={`text-2xl font-bold ${s.label === "Out of Zone" && s.value > 0 ? "text-red-600" : "text-slate-900"}`}>
                {s.value}
              </p>
            ) : (
              <p className="text-sm font-semibold text-slate-700">
                {lastUpdate ? lastUpdate.toLocaleTimeString() : "Waiting…"}
              </p>
            )}
            <p className="text-xs text-slate-500">{s.label}</p>
          </div>
        ))}
      </div>

      {/* Out of zone alert */}
      {outOfZoneList.length > 0 && (
        <div className="rounded-xl border border-red-200 bg-red-50 px-4 py-3 flex items-start gap-3">
          <AlertTriangle className="h-4 w-4 text-red-500 mt-0.5 shrink-0" />
          <div>
            <p className="text-sm font-semibold text-red-700">
              {outOfZoneList.length} Officer{outOfZoneList.length > 1 ? "s" : ""} Out of Zone
            </p>
            <p className="text-xs text-red-600 mt-0.5">
              {outOfZoneList.map(o => o.name).join(", ")} — outside {selectedCity?.name} geofence boundary
            </p>
          </div>
        </div>
      )}

      {/* Stats */}
      <div className="grid grid-cols-4 gap-4">
        {[
          { label: "Officers",     value: officers.length,       icon: Users,          cls: "text-slate-600 bg-slate-100" },
          { label: "Active GPS",   value: active,                icon: Navigation,     cls: "text-emerald-600 bg-emerald-50" },
          { label: "Out of Zone",  value: outOfZoneList.length,  icon: AlertTriangle,  cls: outOfZoneList.length > 0 ? "text-red-600 bg-red-50" : "text-slate-400 bg-slate-50" },
          { label: "Last Update",  value: null,                  icon: Clock,          cls: "text-slate-500 bg-slate-50" },
        ].map(s => (
          <div key={s.label} className="rounded-2xl border border-slate-200 bg-white p-4 shadow-sm">
            <div className={`inline-flex h-7 w-7 items-center justify-center rounded-lg ${s.cls} mb-2`}>
              <s.icon className="h-3.5 w-3.5" />
            </div>
            {s.value !== null ? (
              <p className={`text-2xl font-bold ${s.label === "Out of Zone" && s.value > 0 ? "text-red-600" : "text-slate-900"}`}>
                {s.value}
              </p>
            ) : (
              <p className="text-sm font-semibold text-slate-700">
                {lastUpdate ? lastUpdate.toLocaleTimeString() : "Waiting…"}
              </p>
            )}
            <p className="text-xs text-slate-500">{s.label}</p>
          </div>
        ))}
      </div>

      {/* Map */}
      <div className="rounded-2xl border border-slate-200 bg-white overflow-hidden shadow-sm">
        <div className="px-5 py-4 border-b border-slate-100 flex items-center justify-between">
          <h3 className="text-sm font-semibold text-slate-800">
            {selectedCity ? `${selectedCity.name} — Live Tracking` : "All Officers — Live Tracking"}
            {withLocation.length > 0 && <span className="ml-2 text-slate-400 font-normal">({withLocation.length} on map)</span>}
          </h3>
          <div className="flex items-center gap-2">
            {selectedCity?.geofenceRadius && (
              <span className="text-xs text-teal-600 bg-teal-50 rounded-full px-2 py-0.5 border border-teal-200">
                Geofence: {selectedCity.geofenceRadius >= 1000
                  ? `${(selectedCity.geofenceRadius/1000).toFixed(1)} km`
                  : `${selectedCity.geofenceRadius} m`}
              </span>
            )}
            <span className="text-xs text-slate-400 bg-slate-50 rounded-full px-2 py-0.5 border border-slate-200">
              Leaflet + OSM
            </span>
          </div>
        </div>

        {mapError ? (
          <div className="flex flex-col items-center justify-center h-64 bg-red-50 border border-red-200 rounded-b-2xl">
            <AlertTriangle className="h-10 w-10 text-red-400" />
            <p className="mt-3 text-sm font-medium text-red-700">Map Failed to Load</p>
            <p className="text-xs text-red-600 mt-1">{mapError}</p>
            <button onClick={() => window.location.reload()} className="mt-3 px-3 py-1.5 text-xs font-medium text-white bg-red-500 rounded-lg hover:bg-red-600">
              Reload Page
            </button>
          </div>
        ) : !isClient || withLocation.length === 0 ? (
          <div className="flex flex-col items-center justify-center h-64 bg-slate-50">
            <MapPin className="h-10 w-10 text-slate-200" />
            <p className="mt-3 text-sm font-medium text-slate-400">
              {!isClient ? "Loading map…" : "No officers with location data"}
            </p>
            <p className="text-xs text-slate-300 mt-1">
              {!isClient ? "Please wait" : "Officers appear when they open the app and GPS pings"}
            </p>
          </div>
        ) : (
          <div ref={mapRef} className="w-full h-[440px] bg-slate-100 border-b border-slate-200" />
        )}
      </div>

      {/* Officer list */}
      {cleanOfficers.length > 0 && (
        <div className="rounded-2xl border border-slate-200 bg-white shadow-sm overflow-hidden">
          <div className="px-5 py-4 border-b border-slate-100 flex items-center justify-between">
            <h3 className="text-sm font-semibold text-slate-800">Field Officers ({cleanOfficers.length})</h3>
            {outOfZoneList.length > 0 && (
              <span className="text-xs font-semibold text-red-600 bg-red-50 rounded-full px-2.5 py-0.5 border border-red-200">
                {outOfZoneList.length} Out of Zone
              </span>
            )}
          </div>
          <div className="divide-y divide-slate-50">
            {cleanOfficers.map(o => {
              const lat      = Number(o.lastLatitude);
              const lng      = Number(o.lastLongitude);
              const validPak = !isNaN(lat) && !isNaN(lng) && lat && lng && isValidPakistanCoord(lat, lng);
              const sl       = statusLabel(o.gpsStatus);
              const outZone  = validPak && isOutOfZone(o, selectedCity);

              return (
                <div key={o.id}
                  onClick={() => {
                    setSelected(o);
                    if (validPak && leafletRef.current && window.L) {
                      leafletRef.current.flyTo([lat, lng], 15, { duration: 1.5 });
                      markersRef.current.get(o.id)?.openPopup();
                    }
                  }}
                  className={`flex items-center gap-4 px-5 py-3 cursor-pointer transition-colors ${
                    outZone ? "bg-red-50/60 border-l-4 border-red-400"
                    : selected?.id === o.id ? "bg-teal-50 border-l-4 border-teal-500"
                    : "hover:bg-slate-50/50"
                  }`}
                >
                  <div className="relative">
                    <div className={`flex h-9 w-9 shrink-0 items-center justify-center rounded-full text-sm font-bold text-white ${
                      outZone ? "bg-red-400" : validPak ? "bg-teal-500" : "bg-slate-300"
                    }`}>
                      {(o.name || "?").split(" ").map((w: string) => w[0]).join("").toUpperCase().slice(0, 2)}
                    </div>
                    <span className="absolute -bottom-0.5 -right-0.5">
                      <GpsStatusDot status={o.gpsStatus} />
                    </span>
                  </div>

                  <div className="flex-1 min-w-0">
                    <div className="flex items-center gap-2">
                      <p className="text-sm font-semibold text-slate-800">{o.name || "Unknown"}</p>
                      {outZone && (
                        <span className="flex items-center gap-1 text-[10px] font-bold text-red-600 bg-red-100 rounded-full px-1.5 py-0.5">
                          <AlertTriangle className="h-2.5 w-2.5" /> OUT OF ZONE
                        </span>
                      )}
                      {!validPak && (
                        <span className="text-[10px] text-slate-400 bg-slate-100 rounded-full px-1.5 py-0.5">
                          No GPS yet
                        </span>
                      )}
                    </div>
                    <p className="text-xs text-slate-400 truncate">
                      {validPak ? `${lat.toFixed(5)}, ${lng.toFixed(5)}` : "Waiting for GPS ping…"}
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
                    {validPak && (
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
