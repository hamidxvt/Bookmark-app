"use client";

import { useEffect, useRef, useState } from "react";
import { MapPin, Wifi, WifiOff, RefreshCw, Users, Battery } from "lucide-react";
import { formatDistanceToNow } from "date-fns";

const BACKEND_URL = process.env.NEXT_PUBLIC_API_URL ?? "https://bookmark-api.up.railway.app";

interface BookerPin {
  bookerId: number;
  name: string;
  lat: number;
  lng: number;
  batteryLevel?: number;
  ts: string;
  city?: string;
  status?: string;
}

function BatteryIcon({ level }: { level?: number }) {
  if (level == null) return null;
  const color = level > 50 ? "text-emerald-500" : level > 20 ? "text-amber-500" : "text-red-500";
  return (
    <span className={`flex items-center gap-0.5 text-xs ${color}`}>
      <Battery className="h-3 w-3" />
      {level}%
    </span>
  );
}

export default function LiveMapClient() {
  const [pins, setPins] = useState<Map<number, BookerPin>>(new Map());
  const [connected, setConnected] = useState(false);
  const [lastUpdate, setLastUpdate] = useState<Date | null>(null);
  const socketRef = useRef<ReturnType<typeof import("socket.io-client")["io"]> | null>(null);

  async function loadInitial() {
    try {
      const res = await fetch(`${BACKEND_URL}/api/v1/tracking/live`).then(r => r.json());
      if (res.success) {
        const m = new Map<number, BookerPin>();
        for (const b of res.data) {
          if (b.lat && b.lng) {
            m.set(b.id, { bookerId: b.id, name: b.name, lat: b.lat, lng: b.lng, ts: b.lastSeen, city: b.city, status: b.status });
          }
        }
        setPins(m);
      }
    } catch (e) {
      console.error("[LiveMap] Initial load error:", e);
    }
  }

  useEffect(() => {
    loadInitial();

    // Dynamically import socket.io-client to avoid SSR issues
    import("socket.io-client").then(({ io }) => {
      const socket = io(BACKEND_URL, {
        transports: ["websocket", "polling"],
        reconnectionDelay: 2000,
      });

      socketRef.current = socket;

      socket.on("connect", () => {
        setConnected(true);
        socket.emit("watch:all");
      });

      socket.on("disconnect", () => setConnected(false));

      socket.on("booker:location", (data: BookerPin) => {
        setPins(prev => {
          const next = new Map(prev);
          next.set(data.bookerId, data);
          return next;
        });
        setLastUpdate(new Date());
      });

      return () => socket.disconnect();
    });
  }, []);

  const pinList = Array.from(pins.values());

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h2 className="text-xl font-bold text-slate-900">Live GPS Map</h2>
          <p className="text-sm text-slate-500 mt-0.5">Real-time field officer positions</p>
        </div>
        <div className="flex items-center gap-2">
          <div className={`flex items-center gap-1.5 rounded-full px-3 py-1.5 text-xs font-medium ${
            connected ? "bg-emerald-50 text-emerald-700 border border-emerald-200" : "bg-slate-100 text-slate-500"
          }`}>
            {connected ? <Wifi className="h-3.5 w-3.5" /> : <WifiOff className="h-3.5 w-3.5" />}
            {connected ? "Live" : "Offline"}
          </div>
          <button onClick={loadInitial}
            className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50 transition-colors">
            <RefreshCw className="h-3.5 w-3.5" />
            Refresh
          </button>
        </div>
      </div>

      {/* Stats */}
      <div className="grid grid-cols-2 gap-4 sm:grid-cols-3">
        <div className="rounded-2xl border border-slate-200 bg-white p-4">
          <p className="text-2xl font-bold text-slate-900">{pinList.length}</p>
          <p className="text-xs text-slate-500 mt-0.5 flex items-center gap-1"><Users className="h-3 w-3" />Active in field</p>
        </div>
        <div className="rounded-2xl border border-slate-200 bg-white p-4">
          <p className="text-2xl font-bold text-emerald-600">{pinList.filter(p => p.status === "ACTIVE").length}</p>
          <p className="text-xs text-slate-500 mt-0.5">Tracking live</p>
        </div>
        <div className="rounded-2xl border border-slate-200 bg-white p-4">
          <p className="text-xs text-slate-400 mt-0.5">Last update</p>
          <p className="text-sm font-semibold text-slate-700 mt-1">
            {lastUpdate ? formatDistanceToNow(lastUpdate, { addSuffix: true }) : "Waiting..."}
          </p>
        </div>
      </div>

      {/* Google Maps embed — replace YOUR_MAPS_KEY with key or use OpenStreetMap */}
      <div className="rounded-2xl border border-slate-200 bg-white overflow-hidden shadow-xs">
        <div className="px-5 py-4 border-b border-slate-100 flex items-center justify-between">
          <h3 className="text-sm font-semibold text-slate-800">Officer Locations</h3>
          {!connected && (
            <span className="text-xs text-amber-600 bg-amber-50 rounded-full px-2 py-0.5 border border-amber-200">
              Socket disconnected — polling fallback active
            </span>
          )}
        </div>

        {/* OpenStreetMap via iframe — no API key needed, truly free */}
        {pinList.length > 0 ? (
          <div className="relative">
            <iframe
              src={`https://www.openstreetmap.org/export/embed.html?bbox=${
                Math.min(...pinList.map(p => p.lng)) - 0.05
              },${
                Math.min(...pinList.map(p => p.lat)) - 0.05
              },${
                Math.max(...pinList.map(p => p.lng)) + 0.05
              },${
                Math.max(...pinList.map(p => p.lat)) + 0.05
              }&layer=mapnik`}
              className="w-full h-[420px] border-0"
              title="Live GPS Map"
            />
            <div className="absolute top-3 right-3 bg-white/90 backdrop-blur rounded-xl p-2 shadow-sm border border-slate-100 text-xs text-slate-500">
              Pins auto-update via Socket.io
            </div>
          </div>
        ) : (
          <div className="flex flex-col items-center justify-center h-64 bg-slate-50">
            <MapPin className="h-10 w-10 text-slate-200" />
            <p className="mt-3 text-sm text-slate-400">No active officers in field</p>
            <p className="text-xs text-slate-300 mt-1">Locations appear when bookers send GPS pings</p>
          </div>
        )}
      </div>

      {/* Booker list */}
      {pinList.length > 0 && (
        <div className="rounded-2xl border border-slate-200 bg-white shadow-xs overflow-hidden">
          <div className="px-5 py-4 border-b border-slate-100">
            <h3 className="text-sm font-semibold text-slate-800">Field Officers ({pinList.length})</h3>
          </div>
          <div className="divide-y divide-slate-50">
            {pinList.map(pin => (
              <div key={pin.bookerId} className="flex items-center gap-4 px-5 py-3 hover:bg-slate-50/50 transition-colors">
                <div className="relative">
                  <div className="flex h-9 w-9 shrink-0 items-center justify-center rounded-full bg-teal-100 text-sm font-bold text-teal-700">
                    {pin.name[0]}
                  </div>
                  {pin.status === "ACTIVE" && (
                    <span className="absolute -bottom-0.5 -right-0.5 h-2.5 w-2.5 rounded-full bg-emerald-500 border-2 border-white" />
                  )}
                </div>
                <div className="flex-1 min-w-0">
                  <p className="text-sm font-semibold text-slate-800">{pin.name}</p>
                  <p className="text-xs text-slate-400 truncate">
                    {pin.lat.toFixed(4)}, {pin.lng.toFixed(4)}
                    {pin.city ? ` · ${pin.city}` : ""}
                  </p>
                </div>
                <div className="text-right space-y-0.5">
                  <BatteryIcon level={pin.batteryLevel} />
                  <p className="text-xs text-slate-400">
                    {pin.ts ? formatDistanceToNow(new Date(pin.ts), { addSuffix: true }) : ""}
                  </p>
                  <a
                    href={`https://maps.google.com/?q=${pin.lat},${pin.lng}`}
                    target="_blank"
                    rel="noreferrer"
                    className="text-xs text-teal-600 hover:underline"
                  >
                    Open Maps ↗
                  </a>
                </div>
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
}
