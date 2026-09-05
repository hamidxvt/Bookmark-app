"use client";

import { useEffect, useState } from "react";
import { MapPin, Clock, RefreshCw } from "lucide-react";
import { getGpsStatusColor, timeAgo, cn } from "@/lib/utils";

interface BookerLoc {
  id: number;
  name: string;
  city?: { id: number; name: string } | null;
  lastLatitude: number | null;
  lastLongitude: number | null;
  gpsStatus: string;
  lastSeenAt: string | null;
}

interface Counts {
  total: number;
  active: number;
  idle: number;
  offline: number;
}

export default function LocationClient({ defaultCity }: { defaultCity?: string }) {
  const [bookers, setBookers] = useState<BookerLoc[]>([]);
  const [counts, setCounts] = useState<Counts>({ total: 0, active: 0, idle: 0, offline: 0 });
  const [loading, setLoading] = useState(true);
  const [selected, setSelected] = useState<number | null>(null);
  void defaultCity; // not using city filter — show all

  async function load() {
    setLoading(true);
    try {
      const res = await fetch(`/api/v1/location`).then(r => r.json());
      if (res.success) {
        setBookers(res.data?.bookers ?? []);
        setCounts(res.data?.counts ?? { total: 0, active: 0, idle: 0, offline: 0 });
      }
    } catch (e) { console.error(e); }
    finally { setLoading(false); }
  }

  useEffect(() => { load(); }, []);

  // Auto-refresh every 10s
  useEffect(() => {
    const t = setInterval(load, 10000);
    return () => clearInterval(t);
  // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const selectedBooker = bookers.find(b => b.id === selected);

  const statusBar = [
    { label: "Active", count: counts.active, color: "bg-success" },
    { label: "Idle", count: counts.idle, color: "bg-warning" },
    { label: "Offline", count: counts.offline, color: "bg-muted-foreground" },
  ];

  return (
    <div className="flex h-[calc(100vh-73px)]">
      {/* Map area */}
      <div className="map-grid relative flex-1 overflow-hidden">
        {/* Live indicator */}
        <div className="absolute top-4 left-1/2 -translate-x-1/2 z-10 flex items-center gap-2 rounded-xl bg-card/90 backdrop-blur-sm px-4 py-2 shadow-elevated border border-border/70">
          <span className="h-2 w-2 rounded-full bg-success pulse-dot" />
          <span className="text-xs font-semibold text-foreground">Live Officer Tracking — All Regions</span>
          <span className="text-xs text-muted-foreground">Updates every 10s</span>
        </div>

        {/* Status counts */}
        <div className="absolute top-16 left-4 z-10 flex gap-2">
          {statusBar.map(s => (
            <div key={s.label} className="flex items-center gap-1.5 rounded-lg bg-card/90 backdrop-blur-sm px-3 py-1.5 shadow-card border border-border/70">
              <span className={cn("h-2 w-2 rounded-full", s.color)} />
              <span className="text-xs font-semibold text-foreground">{s.count} {s.label}</span>
            </div>
          ))}
        </div>

        {/* Refresh button */}
        <button onClick={load} disabled={loading}
          className="absolute top-4 right-4 z-10 flex items-center gap-1.5 rounded-lg bg-card/90 backdrop-blur-sm px-3 py-1.5 shadow-card border border-border/70 text-xs font-medium text-muted-foreground hover:bg-card">
          <RefreshCw className={cn("h-3.5 w-3.5", loading && "animate-spin")} /> Refresh
        </button>

        {/* OpenStreetMap — Pakistan-wide view, no API key */}
        <iframe
          className="absolute inset-0 w-full h-full border-0"
          src="https://www.openstreetmap.org/export/embed.html?bbox=60.8,23.5,77.8,37.1&layer=mapnik"
          loading="lazy"
          title="Officer Locations"
        />

        {/* Booker pins overlaid on map */}
        {bookers.map((b, i) => {
          const gps = getGpsStatusColor(b.gpsStatus ?? "OFFLINE");
          // If we have real coords, skip (no pixel math without real map SDK)
          const top = 25 + (i * 18) % 50;
          const left = 15 + (i * 22) % 65;
          return (
            <button key={b.id} onClick={() => setSelected(b.id === selected ? null : b.id)}
              style={{ top: `${top}%`, left: `${left}%`, zIndex: 10 }}
              className="absolute group"
              title={`${b.name} — ${(b.city as any)?.name ?? ""}`}>
              <div className="flex h-10 w-10 items-center justify-center rounded-full border-2 border-card shadow-elevated transition-transform group-hover:scale-110 text-white text-xs font-bold"
                style={{ backgroundColor: gps.hex }}>
                {(b.name ?? "?")[0]}
              </div>
              <div className="pointer-events-none absolute left-1/2 -translate-x-1/2 mt-1 hidden group-hover:block z-20 bg-navy text-navy-foreground text-[10px] rounded-lg px-2 py-1 whitespace-nowrap shadow-elevated">
                {b.name} · {gps.label}
              </div>
            </button>
          );
        })}

        {bookers.length === 0 && (
          <div className="absolute inset-0 flex items-end justify-center pb-8 pointer-events-none z-10">
            <div className="bg-card/90 backdrop-blur-sm rounded-xl px-4 py-2.5 shadow-elevated border border-border/70 text-center">
              <p className="text-sm font-semibold text-foreground">No active officers in field</p>
              <p className="text-xs text-muted-foreground mt-0.5">Locations appear when bookers send GPS pings</p>
            </div>
          </div>
        )}
      </div>

      {/* Side panel */}
      <div className="w-72 shrink-0 border-l border-border bg-card flex flex-col overflow-y-auto">
        <div className="p-4 border-b border-border">
          <p className="text-sm font-semibold text-foreground">Bookers in Field</p>
          <p className="text-xs text-muted-foreground">{bookers.length} members · Live data</p>
        </div>

        <div className="flex-1 divide-y divide-border/60">
          {loading && [...Array(4)].map((_, i) => (
            <div key={i} className="flex items-center gap-3 px-4 py-3.5">
              <div className="h-9 w-9 rounded-full bg-muted animate-pulse shrink-0" />
              <div className="flex-1 space-y-1.5">
                <div className="h-3 w-24 rounded bg-muted animate-pulse" />
                <div className="h-2.5 w-16 rounded bg-muted animate-pulse" />
              </div>
            </div>
          ))}

          {!loading && bookers.map(b => {
            const gps = getGpsStatusColor(b.gpsStatus ?? "OFFLINE");
            const isSelected = selected === b.id;
            return (
              <button key={b.id} onClick={() => setSelected(b.id === selected ? null : b.id)}
                className={cn("w-full flex items-start gap-3 px-4 py-3.5 text-left transition-colors hover:bg-muted/50", isSelected && "bg-primary-soft")}>
                <div className="relative shrink-0">
                  <div className="flex h-9 w-9 items-center justify-center rounded-full text-xs font-bold text-white"
                    style={{ backgroundColor: gps.hex }}>
                    {(b.name ?? "?")[0]}
                  </div>
                </div>
                <div className="min-w-0 flex-1">
                  <p className="text-xs font-semibold text-foreground truncate">{b.name}</p>
                  <p className="text-xs text-muted-foreground">{(b.city as any)?.name ?? "Location tracking"}</p>
                  {b.lastSeenAt && (
                    <div className="flex items-center gap-1 mt-0.5">
                      <Clock className="h-3 w-3 text-muted-foreground" />
                      <span className="text-[10px] text-muted-foreground">{timeAgo(b.lastSeenAt)}</span>
                    </div>
                  )}
                </div>
                <span className={cn("text-xs font-medium shrink-0", gps.text)}>{gps.label}</span>
              </button>
            );
          })}

          {!loading && bookers.length === 0 && (
            <p className="px-5 py-8 text-center text-sm text-muted-foreground">No bookers in field yet</p>
          )}
        </div>

        {/* Detail panel for selected booker */}
        {selectedBooker && (
          <div className="border-t border-border p-4 bg-muted/40">
            <p className="text-xs font-semibold text-foreground mb-2 flex items-center gap-1">
              <MapPin className="h-3.5 w-3.5 text-primary" /> {selectedBooker.name}
            </p>
            <div className="space-y-1 text-xs text-muted-foreground">
              <p>Status: <span className={cn("font-medium", getGpsStatusColor(selectedBooker.gpsStatus ?? "OFFLINE").text)}>
                {getGpsStatusColor(selectedBooker.gpsStatus ?? "OFFLINE").label}
              </span></p>
              {selectedBooker.lastSeenAt && (
                <p>Last seen: <span className="font-medium text-foreground">{timeAgo(selectedBooker.lastSeenAt)}</span></p>
              )}
              {selectedBooker.lastLatitude && selectedBooker.lastLongitude && (
                <p className="text-[10px] font-mono text-muted-foreground/80 mt-1">
                  {selectedBooker.lastLatitude}, {selectedBooker.lastLongitude}
                </p>
              )}
            </div>
          </div>
        )}
      </div>
    </div>
  );
}
