"use client";

import { useCallback, useEffect, useState } from "react";
import {
  Activity, AlertTriangle, Car, CheckCircle, Clock, MapPin,
  RefreshCw, User, Wifi, WifiOff, Zap,
} from "lucide-react";
import { cn } from "@/lib/utils";

interface ActiveVisit {
  id: number;
  customerName: string;
  address: string | null;
  checkInAt: string | null;
}

interface OfficerActivity {
  id: number;
  name: string;
  phone: string;
  designation: string | null;
  profilePhoto: string | null;
  city: string;
  gpsStatus: string;
  isOffline: boolean;
  isIdle: boolean;
  idleAlert: boolean;
  activityLabel: string;
  activityColor: "green" | "blue" | "amber" | "gray";
  lastSeenText: string;
  lastSeenAt: string | null;
  latitude: number | null;
  longitude: number | null;
  speed_kmh: number;
  heading: number | null;
  activeVisit: ActiveVisit | null;
  stats: { completedToday: number; pendingToday: number; totalToday: number };
}

interface Summary {
  total: number;
  active: number;
  idle: number;
  offline: number;
  alerts: number;
}

const COLOR_CLASSES: Record<string, string> = {
  green: "bg-emerald-100 text-emerald-700 border-emerald-200",
  blue:  "bg-blue-100 text-blue-700 border-blue-200",
  amber: "bg-amber-100 text-amber-700 border-amber-200",
  gray:  "bg-slate-100 text-slate-500 border-slate-200",
};

const DOT_CLASSES: Record<string, string> = {
  green: "bg-emerald-500 animate-pulse",
  blue:  "bg-blue-500 animate-pulse",
  amber: "bg-amber-400 animate-pulse",
  gray:  "bg-slate-300",
};

function StatCard({ label, value, color, icon: Icon }: {
  label: string; value: number; color: string; icon: React.ElementType;
}) {
  return (
    <div className="rounded-xl border border-slate-200 bg-white p-4 shadow-xs flex items-center gap-3">
      <div className={cn("flex h-10 w-10 items-center justify-center rounded-full", color)}>
        <Icon className="h-5 w-5" />
      </div>
      <div>
        <p className="text-2xl font-bold text-slate-800 tabular-nums">{value}</p>
        <p className="text-xs text-slate-500">{label}</p>
      </div>
    </div>
  );
}

function OfficerCard({ officer }: { officer: OfficerActivity }) {
  const mapUrl = officer.latitude && officer.longitude
    ? `https://www.openstreetmap.org/export/embed.html?bbox=${officer.longitude - 0.005},${officer.latitude - 0.005},${officer.longitude + 0.005},${officer.latitude + 0.005}&layer=mapnik&marker=${officer.latitude},${officer.longitude}`
    : null;

  return (
    <div className={cn(
      "rounded-2xl border bg-white p-4 shadow-xs transition-all",
      officer.idleAlert ? "border-amber-300 shadow-amber-100 shadow-md" : "border-slate-200",
    )}>
      {/* Alert banner */}
      {officer.idleAlert && (
        <div className="flex items-center gap-2 rounded-lg bg-amber-50 border border-amber-200 px-3 py-2 mb-3 text-xs text-amber-700 font-medium">
          <AlertTriangle className="h-3.5 w-3.5 shrink-0" />
          Stationary for 5+ minutes — officer may need assistance
        </div>
      )}

      <div className="flex items-start gap-3">
        {/* Avatar */}
        <div className="relative shrink-0">
          {officer.profilePhoto
            ? <img src={officer.profilePhoto} alt={officer.name} className="h-11 w-11 rounded-full object-cover border-2 border-slate-200" />
            : (
              <div className="h-11 w-11 rounded-full bg-gradient-to-br from-[#9B0B22] to-[#C8102E] flex items-center justify-center text-white text-sm font-bold">
                {officer.name[0].toUpperCase()}
              </div>
            )
          }
          {/* GPS dot */}
          <span className={cn(
            "absolute -bottom-0.5 -right-0.5 h-3.5 w-3.5 rounded-full border-2 border-white",
            DOT_CLASSES[officer.activityColor],
          )} />
        </div>

        {/* Info */}
        <div className="flex-1 min-w-0">
          <div className="flex items-center gap-2 flex-wrap">
            <p className="text-sm font-semibold text-slate-800 truncate">{officer.name}</p>
            <span className={cn(
              "inline-flex items-center gap-1 rounded-full border px-2 py-0.5 text-[10px] font-semibold",
              COLOR_CLASSES[officer.activityColor],
            )}>
              <span className={cn("h-1.5 w-1.5 rounded-full", DOT_CLASSES[officer.activityColor])} />
              {officer.activityLabel}
            </span>
          </div>
          <p className="text-xs text-slate-400 mt-0.5">
            {officer.designation ?? "Officer"} · {officer.city}
          </p>
        </div>

        {/* Last seen */}
        <div className="shrink-0 text-right">
          {officer.isOffline
            ? <WifiOff className="h-4 w-4 text-slate-300 ml-auto" />
            : <Wifi className="h-4 w-4 text-emerald-500 ml-auto" />}
          <p className="text-[10px] text-slate-400 mt-1">{officer.lastSeenText}</p>
        </div>
      </div>

      {/* Active visit */}
      {officer.activeVisit && (
        <div className="mt-3 rounded-lg bg-emerald-50 border border-emerald-100 px-3 py-2 flex items-start gap-2">
          <MapPin className="h-3.5 w-3.5 text-emerald-600 mt-0.5 shrink-0" />
          <div className="min-w-0">
            <p className="text-xs font-semibold text-emerald-700 truncate">{officer.activeVisit.customerName}</p>
            {officer.activeVisit.address && (
              <p className="text-[10px] text-emerald-500 truncate">{officer.activeVisit.address}</p>
            )}
            {officer.activeVisit.checkInAt && (
              <p className="text-[10px] text-emerald-400">
                Check-in: {new Date(officer.activeVisit.checkInAt).toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })}
              </p>
            )}
          </div>
        </div>
      )}

      {/* Speed */}
      {!officer.isOffline && officer.speed_kmh > 3 && (
        <div className="mt-2 flex items-center gap-1.5 text-xs text-blue-600">
          <Car className="h-3.5 w-3.5" />
          <span>{Math.round(officer.speed_kmh)} km/h</span>
          {officer.heading != null && (
            <span className="text-blue-400">· heading {Math.round(officer.heading)}°</span>
          )}
        </div>
      )}

      {/* Today's stats */}
      <div className="mt-3 grid grid-cols-3 gap-2 text-center">
        {[
          { label: "Done", value: officer.stats.completedToday, color: "text-emerald-600" },
          { label: "Left",  value: officer.stats.pendingToday,   color: "text-amber-600" },
          { label: "Total", value: officer.stats.totalToday,     color: "text-slate-600" },
        ].map(s => (
          <div key={s.label} className="rounded-lg bg-slate-50 py-1.5">
            <p className={cn("text-base font-bold tabular-nums", s.color)}>{s.value}</p>
            <p className="text-[9px] text-slate-400 font-medium uppercase">{s.label}</p>
          </div>
        ))}
      </div>

      {/* Map preview */}
      {mapUrl && (
        <div className="mt-3 overflow-hidden rounded-xl border border-slate-100 h-28">
          <iframe src={mapUrl} className="w-full h-full" title={`${officer.name} location`} />
        </div>
      )}
    </div>
  );
}

export default function LiveActivityClient() {
  const [data, setData] = useState<{ activities: OfficerActivity[]; summary: Summary } | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [filter, setFilter] = useState<"all" | "active" | "idle" | "offline" | "alerts">("all");
  const [lastRefresh, setLastRefresh] = useState<Date | null>(null);

  const load = useCallback(async () => {
    try {
      const res = await fetch("/api/v1/live-activity").then(r => r.json());
      if (res.success) {
        setData(res.data);
        setLastRefresh(new Date());
        setError("");
      } else {
        setError(res.error ?? "Failed to load");
      }
    } catch (e) {
      setError(String(e));
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    load();
    const interval = setInterval(load, 30_000); // auto-refresh every 30s
    return () => clearInterval(interval);
  }, [load]);

  const filtered = data?.activities.filter(a => {
    if (filter === "active")  return !a.isOffline && !a.isIdle;
    if (filter === "idle")    return a.isIdle && !a.isOffline;
    if (filter === "offline") return a.isOffline;
    if (filter === "alerts")  return a.idleAlert;
    return true;
  }) ?? [];

  return (
    <div className="space-y-4">
      {/* Header */}
      <div className="flex flex-col gap-3 sm:flex-row sm:items-center sm:justify-between">
        <div>
          <h1 className="text-lg font-bold text-slate-800 flex items-center gap-2">
            <Activity className="h-5 w-5 text-[#C8102E]" /> Live Activity
          </h1>
          <p className="text-xs text-slate-400 mt-0.5">
            Auto-refreshes every 30s · {lastRefresh ? `Last: ${lastRefresh.toLocaleTimeString()}` : "Loading…"}
          </p>
        </div>
        <button onClick={load} disabled={loading}
          className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50">
          <RefreshCw className={cn("h-3.5 w-3.5", loading && "animate-spin")} /> Refresh
        </button>
      </div>

      {/* Summary cards */}
      {data && (
        <div className="grid grid-cols-2 gap-3 sm:grid-cols-5">
          <StatCard label="Total Officers" value={data.summary.total}   color="bg-blue-50 text-blue-600"    icon={User} />
          <StatCard label="Active Now"     value={data.summary.active}  color="bg-emerald-50 text-emerald-600" icon={Zap} />
          <StatCard label="Idle / Paused"  value={data.summary.idle}    color="bg-amber-50 text-amber-600"  icon={Clock} />
          <StatCard label="Offline"        value={data.summary.offline} color="bg-slate-100 text-slate-500" icon={WifiOff} />
          <StatCard label="⚠ Idle Alerts" value={data.summary.alerts}  color="bg-red-50 text-red-500"      icon={AlertTriangle} />
        </div>
      )}

      {/* Filter tabs */}
      <div className="flex gap-2 flex-wrap">
        {(["all", "active", "idle", "offline", "alerts"] as const).map(f => (
          <button key={f} onClick={() => setFilter(f)}
            className={cn(
              "rounded-full border px-3 py-1 text-xs font-medium transition-colors capitalize",
              filter === f
                ? "bg-[#C8102E] text-white border-[#C8102E]"
                : "bg-white text-slate-600 border-slate-200 hover:border-[#C8102E] hover:text-[#C8102E]"
            )}>
            {f === "alerts" ? "⚠ Alerts" : f.charAt(0).toUpperCase() + f.slice(1)}
            {data && f !== "all" && (
              <span className="ml-1 opacity-70">
                ({f === "active" ? data.summary.active
                  : f === "idle" ? data.summary.idle
                  : f === "offline" ? data.summary.offline
                  : data.summary.alerts})
              </span>
            )}
          </button>
        ))}
      </div>

      {/* Error */}
      {error && (
        <div className="rounded-xl border border-red-200 bg-red-50 px-4 py-3 text-sm text-red-600">
          {error}
        </div>
      )}

      {/* Loading skeleton */}
      {loading && !data && (
        <div className="grid grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-3">
          {[...Array(6)].map((_, i) => (
            <div key={i} className="rounded-2xl border border-slate-200 bg-white p-4 space-y-3">
              <div className="flex gap-3">
                <div className="h-11 w-11 rounded-full bg-slate-100 animate-pulse" />
                <div className="flex-1 space-y-2">
                  <div className="h-3 w-32 rounded bg-slate-100 animate-pulse" />
                  <div className="h-3 w-20 rounded bg-slate-100 animate-pulse" />
                </div>
              </div>
              <div className="h-16 rounded-lg bg-slate-100 animate-pulse" />
            </div>
          ))}
        </div>
      )}

      {/* Officer cards */}
      {!loading && filtered.length === 0 && (
        <div className="py-16 text-center text-slate-400">
          <CheckCircle className="h-10 w-10 mx-auto mb-3 text-slate-200" />
          <p className="text-sm">No officers match this filter</p>
        </div>
      )}

      <div className="grid grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-3">
        {filtered.map(officer => (
          <OfficerCard key={officer.id} officer={officer} />
        ))}
      </div>
    </div>
  );
}
