"use client";

import { useEffect, useState } from "react";
import { MapPin, RefreshCw, Users, Clock } from "lucide-react";

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
  if (s === "ACTIVE") return <span className="h-2.5 w-2.5 rounded-full bg-emerald-500 animate-pulse inline-block" />;
  if (s === "IDLE") return <span className="h-2.5 w-2.5 rounded-full bg-amber-400 inline-block" />;
  return <span className="h-2.5 w-2.5 rounded-full bg-slate-300 inline-block" />;
}

export default function LiveMapClient() {
  const [officers, setOfficers] = useState<Officer[]>([]);
  const [loading, setLoading] = useState(true);
  const [lastUpdate, setLastUpdate] = useState<Date | null>(null);

  async function load() {
    try {
      const res = await fetch("/api/v1/location").then(r => r.json());
      if (res.success) {
        setOfficers(res.data?.bookers ?? []);
        setLastUpdate(new Date());
      }
    } catch (e) {
      console.error("[LiveMap]", e);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    load();
    // Poll every 10 seconds
    const t = setInterval(load, 10000);
    return () => clearInterval(t);
  }, []);

  const active = officers.filter(o => o.gpsStatus === "ACTIVE").length;
  const withLocation = officers.filter(o => o.lastLatitude && o.lastLongitude);

  // Build OpenStreetMap URL centered on officers, or default Pakistan view
  const mapSrc = withLocation.length > 0
    ? (() => {
        const lats = withLocation.map(o => o.lastLatitude!);
        const lngs = withLocation.map(o => o.lastLongitude!);
        const minLat = Math.min(...lats) - 0.1;
        const maxLat = Math.max(...lats) + 0.1;
        const minLng = Math.min(...lngs) - 0.1;
        const maxLng = Math.max(...lngs) + 0.1;
        return `https://www.openstreetmap.org/export/embed.html?bbox=${minLng},${minLat},${maxLng},${maxLat}&layer=mapnik`;
      })()
    : "https://www.openstreetmap.org/export/embed.html?bbox=60.8,23.5,77.8,37.1&layer=mapnik";

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h2 className="text-xl font-bold text-slate-900">Live GPS Map</h2>
          <p className="text-sm text-slate-500 mt-0.5">Real-time field officer positions</p>
        </div>
        <button onClick={load} disabled={loading}
          className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50 transition-colors">
          <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} />
          Refresh
        </button>
      </div>

      {/* Stats */}
      <div className="grid grid-cols-3 gap-4">
        <div className="rounded-2xl border border-slate-200 bg-white p-4">
          <p className="text-2xl font-bold text-slate-900">{officers.length}</p>
          <p className="text-xs text-slate-500 mt-0.5 flex items-center gap-1">
            <Users className="h-3 w-3" /> Total Officers
          </p>
        </div>
        <div className="rounded-2xl border border-slate-200 bg-white p-4">
          <p className="text-2xl font-bold text-emerald-600">{active}</p>
          <p className="text-xs text-slate-500 mt-0.5">Active GPS</p>
        </div>
        <div className="rounded-2xl border border-slate-200 bg-white p-4">
          <p className="text-xs text-slate-400">Last update</p>
          <p className="text-sm font-semibold text-slate-700 mt-1 flex items-center gap-1">
            <Clock className="h-3.5 w-3.5 text-slate-400" />
            {lastUpdate ? lastUpdate.toLocaleTimeString() : "Waiting..."}
          </p>
        </div>
      </div>

      {/* Map */}
      <div className="rounded-2xl border border-slate-200 bg-white overflow-hidden shadow-sm">
        <div className="px-5 py-4 border-b border-slate-100 flex items-center justify-between">
          <h3 className="text-sm font-semibold text-slate-800">Officer Locations</h3>
          <span className="text-xs text-teal-600 bg-teal-50 rounded-full px-2 py-0.5 border border-teal-200">
            Auto-refreshes every 10s
          </span>
        </div>
        <div className="relative">
          <iframe
            src={mapSrc}
            className="w-full h-[400px] border-0"
            title="Live GPS Map"
            loading="lazy"
          />
          {/* Officer pin labels overlaid on map */}
          {withLocation.length > 0 && (
            <div className="absolute top-3 left-3 flex flex-col gap-1.5 z-10">
              {withLocation.map(o => (
                <a key={o.id}
                  href={`https://maps.google.com/?q=${o.lastLatitude},${o.lastLongitude}`}
                  target="_blank" rel="noreferrer"
                  className="flex items-center gap-2 rounded-lg bg-white/95 backdrop-blur px-3 py-1.5 shadow-md border border-slate-200 hover:bg-teal-50 transition-colors text-xs">
                  <GpsStatusDot status={o.gpsStatus} />
                  <span className="font-semibold text-slate-800">{o.name}</span>
                  <span className="text-slate-400">{o.lastLatitude?.toFixed(3)}, {o.lastLongitude?.toFixed(3)}</span>
                  <span className="text-teal-600">↗</span>
                </a>
              ))}
            </div>
          )}
        </div>
        {withLocation.length === 0 && (
          <div className="flex flex-col items-center justify-center h-64 bg-slate-50">
            <MapPin className="h-10 w-10 text-slate-200" />
            <p className="mt-3 text-sm font-medium text-slate-400">No active officers in field</p>
            <p className="text-xs text-slate-300 mt-1">Locations appear when officers open the app and GPS pings are sent</p>
          </div>
        )}
      </div>

      {/* Officer list */}
      {officers.length > 0 && (
        <div className="rounded-2xl border border-slate-200 bg-white shadow-sm overflow-hidden">
          <div className="px-5 py-4 border-b border-slate-100">
            <h3 className="text-sm font-semibold text-slate-800">Field Officers ({officers.length})</h3>
          </div>
          <div className="divide-y divide-slate-50">
            {officers.map(o => (
              <div key={o.id} className="flex items-center gap-4 px-5 py-3 hover:bg-slate-50/50 transition-colors">
                <div className="relative">
                  <div className="flex h-9 w-9 shrink-0 items-center justify-center rounded-full bg-teal-100 text-sm font-bold text-teal-700">
                    {o.name[0]}
                  </div>
                  <span className="absolute -bottom-0.5 -right-0.5">
                    <GpsStatusDot status={o.gpsStatus} />
                  </span>
                </div>
                <div className="flex-1 min-w-0">
                  <p className="text-sm font-semibold text-slate-800">{o.name}</p>
                  <p className="text-xs text-slate-400 truncate">
                    {o.lastLatitude && o.lastLongitude
                      ? `${o.lastLatitude.toFixed(4)}, ${o.lastLongitude.toFixed(4)}`
                      : "No location yet"}
                    {o.city ? ` · ${o.city.name}` : ""}
                  </p>
                </div>
                <div className="text-right space-y-1">
                  <p className="text-xs text-slate-400">
                    {o.lastSeenAt ? new Date(o.lastSeenAt).toLocaleTimeString() : "Never"}
                  </p>
                  {o.lastLatitude && o.lastLongitude && (
                    <a href={`https://maps.google.com/?q=${o.lastLatitude},${o.lastLongitude}`}
                      target="_blank" rel="noreferrer"
                      className="text-xs text-teal-600 hover:underline">
                      Open in Maps ↗
                    </a>
                  )}
                </div>
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
}
