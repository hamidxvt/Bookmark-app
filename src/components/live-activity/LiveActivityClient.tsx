"use client";

import { useCallback, useEffect, useState } from "react";
import {
  Activity, AlertTriangle, Car, CheckCircle, Clock, MapPin,
  RefreshCw, User, Wifi, WifiOff, Zap, X, Phone, Mail,
  Circle, ChevronRight,
} from "lucide-react";
import { cn } from "@/lib/utils";

// ── Types ──────────────────────────────────────────────────────────────────────
interface ActiveVisit { id: number; customerName: string; address: string | null; checkInAt: string | null; }
interface OfficerActivity {
  id: number; name: string; phone: string; designation: string | null; profilePhoto: string | null;
  city: string; gpsStatus: string; isOffline: boolean; isIdle: boolean; idleAlert: boolean;
  activityLabel: string; activityColor: "green" | "blue" | "amber" | "gray";
  lastSeenText: string; lastSeenAt: string | null; latitude: number | null; longitude: number | null;
  speed_kmh: number; heading: number | null; activeVisit: ActiveVisit | null;
  stats: { completedToday: number; pendingToday: number; totalToday: number };
}
interface Summary { total: number; active: number; idle: number; offline: number; alerts: number; }

interface VisitDetail {
  id: number; sequence: number; status: string; customerName: string; address: string;
  phone: string; lat: number | null; lng: number | null;
  checkInAt: string | null; checkOutAt: string | null; missedReason: string | null;
}
interface TrailPoint { lat: number; lng: number; speed: number; activity: string | null; heading: number | null; time: string; }
interface OfficerDetail {
  officer: {
    id: number; name: string; phone: string; email: string; designation: string | null;
    profilePhoto: string | null; city: string; gpsStatus: string; isOffline: boolean; isIdle: boolean;
    lastSeenAt: string | null; currentLat: number | null; currentLng: number | null;
    activityLabel: string; currentSpeed: number; heading: number | null;
  };
  stats: { totalVisits: number; completed: number; inProgress: number; pending: number; missed: number; targetVisits: number; distanceKm: number; fieldSince: string | null; lastPingAt: string | null; };
  visits: VisitDetail[];
  trail: TrailPoint[];
}

// ── Color helpers ──────────────────────────────────────────────────────────────
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

// ── Visit status badge ─────────────────────────────────────────────────────────
function VisitStatusBadge({ status }: { status: string }) {
  const map: Record<string, { label: string; cls: string }> = {
    completed:   { label: "Done",        cls: "bg-emerald-100 text-emerald-700" },
    in_progress: { label: "Active",      cls: "bg-blue-100 text-blue-700 animate-pulse" },
    pending:     { label: "Upcoming",    cls: "bg-slate-100 text-slate-500" },
    missed:      { label: "Missed",      cls: "bg-red-100 text-red-600" },
  };
  const s = map[status] ?? { label: status, cls: "bg-slate-100 text-slate-500" };
  return <span className={cn("inline-flex items-center rounded-full px-2 py-0.5 text-[10px] font-semibold", s.cls)}>{s.label}</span>;
}

// ── Stat card ─────────────────────────────────────────────────────────────────
function StatCard({ label, value, color, icon: Icon }: { label: string; value: number | string; color: string; icon: React.ElementType }) {
  return (
    <div className="rounded-xl border border-slate-200 bg-white p-4 shadow-xs flex items-center gap-3">
      <div className={cn("flex h-10 w-10 items-center justify-center rounded-full", color)}>
        <Icon className="h-5 w-5" />
      </div>
      <div>
        <p className="text-xl font-bold text-slate-800 tabular-nums">{value}</p>
        <p className="text-xs text-slate-500">{label}</p>
      </div>
    </div>
  );
}

// ── Officer Detail Drawer ─────────────────────────────────────────────────────
function OfficerDetailDrawer({ officerId, onClose }: { officerId: number; onClose: () => void }) {
  const [detail, setDetail] = useState<OfficerDetail | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`/api/v1/live-activity/${officerId}`)
      .then(r => r.json())
      .then(d => { if (d.success) setDetail(d.data); })
      .finally(() => setLoading(false));
  }, [officerId]);

  const mapUrl = detail?.officer.currentLat && detail.officer.currentLng
    ? `https://www.openstreetmap.org/export/embed.html?bbox=${detail.officer.currentLng - 0.008},${detail.officer.currentLat - 0.008},${detail.officer.currentLng + 0.008},${detail.officer.currentLat + 0.008}&layer=mapnik&marker=${detail.officer.currentLat},${detail.officer.currentLng}`
    : null;

  return (
    <div className="fixed inset-0 z-50 flex items-end sm:items-center justify-center bg-black/50 backdrop-blur-sm p-0 sm:p-4" onClick={onClose}>
      <div
        className="w-full sm:max-w-2xl bg-white rounded-t-3xl sm:rounded-2xl shadow-2xl max-h-[92vh] overflow-y-auto"
        onClick={e => e.stopPropagation()}
      >
        {/* Handle */}
        <div className="sticky top-0 bg-white z-10 pt-3 pb-2 px-5 border-b border-slate-100">
          <div className="w-10 h-1 rounded-full bg-slate-200 mx-auto mb-3 sm:hidden" />
          <div className="flex items-center justify-between">
            <p className="text-sm font-semibold text-slate-700">Officer Details</p>
            <button onClick={onClose} className="rounded-lg p-1.5 hover:bg-slate-100"><X className="h-4 w-4 text-slate-500" /></button>
          </div>
        </div>

        {loading ? (
          <div className="p-8 flex items-center justify-center">
            <div className="h-8 w-8 rounded-full border-2 border-[#C8102E] border-t-transparent animate-spin" />
          </div>
        ) : !detail ? (
          <div className="p-8 text-center text-slate-400 text-sm">Failed to load officer data</div>
        ) : (
          <div className="p-5 space-y-5">
            {/* Officer header */}
            <div className="flex items-center gap-4">
              <div className="relative shrink-0">
                {detail.officer.profilePhoto
                  ? <img src={detail.officer.profilePhoto} alt={detail.officer.name} className="h-16 w-16 rounded-full object-cover border-2 border-slate-200" />
                  : <div className="h-16 w-16 rounded-full bg-gradient-to-br from-[#9B0B22] to-[#C8102E] flex items-center justify-center text-white text-xl font-bold">{detail.officer.name[0]}</div>}
                <span className={cn("absolute -bottom-0.5 -right-0.5 h-4 w-4 rounded-full border-2 border-white",
                  detail.officer.isOffline ? "bg-slate-300" : detail.officer.isIdle ? "bg-amber-400 animate-pulse" : "bg-emerald-500 animate-pulse")} />
              </div>
              <div className="flex-1">
                <h2 className="text-lg font-bold text-slate-800">{detail.officer.name}</h2>
                <p className="text-xs text-slate-500">{detail.officer.designation ?? "Officer"} · {detail.officer.city}</p>
                <div className="flex items-center gap-3 mt-1">
                  <a href={`tel:${detail.officer.phone}`} className="flex items-center gap-1 text-xs text-blue-600 hover:underline">
                    <Phone className="h-3 w-3" />{detail.officer.phone}
                  </a>
                  <span className="text-slate-200">|</span>
                  <span className="flex items-center gap-1 text-xs text-slate-500">
                    <Mail className="h-3 w-3" />{detail.officer.email}
                  </span>
                </div>
              </div>
              <div className="text-right shrink-0">
                <p className={cn("text-xs font-semibold px-2 py-1 rounded-full border",
                  detail.officer.isOffline ? "bg-slate-100 text-slate-400 border-slate-200"
                  : detail.officer.isIdle ? "bg-amber-100 text-amber-700 border-amber-200"
                  : "bg-emerald-100 text-emerald-700 border-emerald-200")}>
                  {detail.officer.activityLabel}
                </p>
                {detail.officer.currentSpeed > 3 && (
                  <p className="text-xs text-blue-500 mt-1 flex items-center gap-1 justify-end">
                    <Car className="h-3 w-3" />{detail.officer.currentSpeed} km/h
                  </p>
                )}
              </div>
            </div>

            {/* Today stats bar */}
            <div className="grid grid-cols-2 gap-3 sm:grid-cols-4">
              {[
                { label: "Completed",   value: detail.stats.completed,   color: "bg-emerald-50 text-emerald-600", icon: CheckCircle },
                { label: "In Progress", value: detail.stats.inProgress,  color: "bg-blue-50 text-blue-600",    icon: Activity },
                { label: "Remaining",   value: detail.stats.pending,     color: "bg-slate-50 text-slate-500",  icon: Clock },
                { label: "Missed",      value: detail.stats.missed,      color: "bg-red-50 text-red-500",      icon: AlertTriangle },
              ].map(s => <StatCard key={s.label} {...s} />)}
            </div>

            {/* Distance + field time */}
            <div className="grid grid-cols-2 gap-3">
              <div className="rounded-xl border border-slate-200 bg-slate-50 p-3">
                <p className="text-[10px] text-slate-400 font-medium uppercase tracking-wide">Distance Today</p>
                <p className="text-xl font-bold text-slate-700 mt-0.5 tabular-nums">{detail.stats.distanceKm} km</p>
              </div>
              <div className="rounded-xl border border-slate-200 bg-slate-50 p-3">
                <p className="text-[10px] text-slate-400 font-medium uppercase tracking-wide">In Field Since</p>
                <p className="text-xl font-bold text-slate-700 mt-0.5">
                  {detail.stats.fieldSince ? new Date(detail.stats.fieldSince).toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" }) : "—"}
                </p>
              </div>
            </div>

            {/* Live map */}
            {mapUrl && (
              <div>
                <p className="text-xs font-semibold text-slate-500 mb-2 uppercase tracking-wide">Current Location</p>
                <div className="overflow-hidden rounded-xl border border-slate-200 h-44">
                  <iframe src={mapUrl} className="w-full h-full" title="officer location" />
                </div>
                <a
                  href={`https://www.openstreetmap.org/?mlat=${detail.officer.currentLat}&mlon=${detail.officer.currentLng}`}
                  target="_blank" rel="noopener noreferrer"
                  className="mt-1 flex items-center gap-1 text-xs text-[#C8102E] hover:underline"
                >
                  <Navigation className="h-3 w-3" /> Open full map
                </a>
              </div>
            )}

            {/* GPS Trail summary */}
            {detail.trail.length > 0 && (
              <div>
                <p className="text-xs font-semibold text-slate-500 mb-2 uppercase tracking-wide">
                  GPS Trail (last 2 hrs · {detail.trail.length} pings)
                </p>
                <div className="rounded-xl border border-slate-200 bg-slate-50 p-3 max-h-32 overflow-y-auto">
                  {detail.trail.slice(-20).reverse().map((p, i) => (
                    <div key={i} className="flex items-center gap-3 text-xs py-1 border-b border-slate-100 last:border-0">
                      <span className="text-slate-400 w-14 shrink-0">{new Date(p.time).toLocaleTimeString([], { hour: "2-digit", minute: "2-digit", second: "2-digit" })}</span>
                      <span className={cn("w-2 h-2 rounded-full shrink-0",
                        p.activity === "STATIONARY" ? "bg-amber-400" : p.speed > 15 ? "bg-blue-500" : "bg-emerald-500")} />
                      <span className="text-slate-600">{p.activity ?? "—"}</span>
                      {p.speed > 0 && <span className="text-slate-400">{Math.round(p.speed)} km/h</span>}
                      {p.heading != null && <span className="text-slate-300">{Math.round(p.heading)}°</span>}
                    </div>
                  ))}
                </div>
              </div>
            )}

            {/* Today's visits timeline */}
            <div>
              <p className="text-xs font-semibold text-slate-500 mb-3 uppercase tracking-wide">
                Today's Schedule ({detail.stats.totalVisits} visits · Target: {detail.stats.targetVisits})
              </p>
              <div className="space-y-2">
                {detail.visits.map((v, i) => (
                  <div key={v.id} className={cn(
                    "flex items-start gap-3 rounded-xl border p-3",
                    v.status === "completed"   ? "bg-emerald-50 border-emerald-100" :
                    v.status === "in_progress" ? "bg-blue-50 border-blue-200 shadow-sm" :
                    v.status === "missed"      ? "bg-red-50 border-red-100" :
                    "bg-white border-slate-100"
                  )}>
                    {/* Sequence */}
                    <div className={cn(
                      "h-7 w-7 rounded-full flex items-center justify-center text-xs font-bold shrink-0 mt-0.5",
                      v.status === "completed"   ? "bg-emerald-500 text-white" :
                      v.status === "in_progress" ? "bg-blue-500 text-white" :
                      v.status === "missed"      ? "bg-red-400 text-white" :
                      "bg-slate-200 text-slate-600"
                    )}>{v.sequence}</div>

                    <div className="flex-1 min-w-0">
                      <div className="flex items-center gap-2">
                        <p className="text-sm font-semibold text-slate-800 truncate">{v.customerName}</p>
                        <VisitStatusBadge status={v.status} />
                      </div>
                      {v.address && <p className="text-xs text-slate-400 truncate mt-0.5">{v.address}</p>}
                      {v.phone && <p className="text-xs text-slate-400">{v.phone}</p>}
                      <div className="flex items-center gap-3 mt-1 text-[10px] text-slate-400">
                        {v.checkInAt && <span>In: {new Date(v.checkInAt).toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })}</span>}
                        {v.checkOutAt && <span>Out: {new Date(v.checkOutAt).toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })}</span>}
                        {v.missedReason && <span className="text-red-400">"{v.missedReason}"</span>}
                        {v.lat && v.lng && (
                          <a href={`https://www.openstreetmap.org/?mlat=${v.lat}&mlon=${v.lng}`} target="_blank" rel="noopener noreferrer"
                            className="text-blue-400 hover:underline flex items-center gap-0.5">
                            <MapPin className="h-2.5 w-2.5" />Map
                          </a>
                        )}
                      </div>
                    </div>

                    {v.status === "in_progress" && (
                      <div className="flex items-center gap-1 text-xs text-blue-600 font-medium shrink-0">
                        <Circle className="h-2 w-2 fill-blue-500 text-blue-500 animate-pulse" /> Live
                      </div>
                    )}
                  </div>
                ))}
                {detail.visits.length === 0 && (
                  <p className="text-center text-sm text-slate-400 py-4">No visits scheduled today</p>
                )}
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

// ── Officer Card (summary) ─────────────────────────────────────────────────────
function OfficerCard({ officer, onSelect }: { officer: OfficerActivity; onSelect: () => void }) {
  const progressPct = officer.stats.totalToday > 0
    ? Math.round((officer.stats.completedToday / officer.stats.totalToday) * 100)
    : 0;

  return (
    <div
      className={cn(
        "rounded-2xl border bg-white p-4 shadow-xs transition-all cursor-pointer hover:shadow-md",
        officer.idleAlert ? "border-amber-300 shadow-amber-100 shadow-md" :
        !officer.isOffline && !officer.isIdle ? "border-emerald-200" : "border-slate-200",
      )}
      onClick={onSelect}
    >
      {officer.idleAlert && (
        <div className="flex items-center gap-2 rounded-lg bg-amber-50 border border-amber-200 px-3 py-2 mb-3 text-xs text-amber-700 font-medium">
          <AlertTriangle className="h-3.5 w-3.5 shrink-0" />
          Stationary 5+ min — tap for full details
        </div>
      )}

      {/* Header row */}
      <div className="flex items-start gap-3">
        <div className="relative shrink-0">
          {officer.profilePhoto
            ? <img src={officer.profilePhoto} alt={officer.name} className="h-11 w-11 rounded-full object-cover border-2 border-slate-200" />
            : <div className="h-11 w-11 rounded-full bg-gradient-to-br from-[#9B0B22] to-[#C8102E] flex items-center justify-center text-white text-sm font-bold">{officer.name[0].toUpperCase()}</div>}
          <span className={cn("absolute -bottom-0.5 -right-0.5 h-3.5 w-3.5 rounded-full border-2 border-white", DOT_CLASSES[officer.activityColor])} />
        </div>

        <div className="flex-1 min-w-0">
          <div className="flex items-center gap-2 flex-wrap">
            <p className="text-sm font-semibold text-slate-800 truncate">{officer.name}</p>
            <span className={cn("inline-flex items-center gap-1 rounded-full border px-2 py-0.5 text-[10px] font-semibold", COLOR_CLASSES[officer.activityColor])}>
              <span className={cn("h-1.5 w-1.5 rounded-full", DOT_CLASSES[officer.activityColor])} />
              {officer.activityLabel}
            </span>
          </div>
          <p className="text-xs text-slate-400 mt-0.5">{officer.designation ?? "Officer"} · {officer.city}</p>
        </div>

        <div className="shrink-0 flex items-center gap-1.5">
          {officer.isOffline ? <WifiOff className="h-4 w-4 text-slate-300" /> : <Wifi className="h-4 w-4 text-emerald-500" />}
          <ChevronRight className="h-4 w-4 text-slate-300" />
        </div>
      </div>

      {/* Active visit banner */}
      {officer.activeVisit && (
        <div className="mt-3 rounded-lg bg-emerald-50 border border-emerald-100 px-3 py-2 flex items-start gap-2">
          <MapPin className="h-3.5 w-3.5 text-emerald-600 mt-0.5 shrink-0" />
          <div className="min-w-0">
            <p className="text-xs font-semibold text-emerald-700 truncate">{officer.activeVisit.customerName}</p>
            {officer.activeVisit.address && <p className="text-[10px] text-emerald-500 truncate">{officer.activeVisit.address}</p>}
            {officer.activeVisit.checkInAt && (
              <p className="text-[10px] text-emerald-400">
                Check-in: {new Date(officer.activeVisit.checkInAt).toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })}
              </p>
            )}
          </div>
          <span className="ml-auto flex items-center gap-1 text-[10px] text-emerald-600 font-semibold shrink-0">
            <Circle className="h-2 w-2 fill-emerald-500 text-emerald-500 animate-pulse" /> LIVE
          </span>
        </div>
      )}

      {/* Speed / movement */}
      {!officer.isOffline && officer.speed_kmh > 3 && (
        <div className="mt-2 flex items-center gap-2 text-xs text-blue-600 bg-blue-50 rounded-lg px-3 py-1.5">
          <Car className="h-3.5 w-3.5 shrink-0" />
          <span className="font-semibold">{Math.round(officer.speed_kmh)} km/h</span>
          {officer.heading != null && (
            <span className="text-blue-400">· {headingLabel(officer.heading)}</span>
          )}
          <span className="text-blue-300 ml-auto">En route</span>
        </div>
      )}

      {/* Visit progress bar */}
      {officer.stats.totalToday > 0 && (
        <div className="mt-3">
          <div className="flex justify-between items-center mb-1">
            <p className="text-[10px] text-slate-400 font-medium uppercase tracking-wide">Visit Progress</p>
            <p className="text-[10px] font-semibold text-slate-600">{officer.stats.completedToday}/{officer.stats.totalToday} · {progressPct}%</p>
          </div>
          <div className="h-1.5 rounded-full bg-slate-100 overflow-hidden">
            <div
              className={cn("h-full rounded-full transition-all", progressPct === 100 ? "bg-emerald-500" : "bg-[#C8102E]")}
              style={{ width: `${progressPct}%` }}
            />
          </div>
        </div>
      )}

      {/* Stats row */}
      <div className="mt-3 grid grid-cols-3 gap-2 text-center">
        {[
          { label: "Done",  value: officer.stats.completedToday, color: "text-emerald-600" },
          { label: "Left",  value: officer.stats.pendingToday,   color: "text-amber-600" },
          { label: "Total", value: officer.stats.totalToday,     color: "text-slate-600" },
        ].map(s => (
          <div key={s.label} className="rounded-lg bg-slate-50 py-1.5">
            <p className={cn("text-base font-bold tabular-nums", s.color)}>{s.value}</p>
            <p className="text-[9px] text-slate-400 font-medium uppercase">{s.label}</p>
          </div>
        ))}
      </div>

      {/* Last seen footer */}
      <div className="mt-2 flex items-center justify-between">
        <span className="text-[10px] text-slate-300">{officer.lastSeenText}</span>
        <span className="text-[10px] text-[#C8102E] font-medium">Tap for full details →</span>
      </div>
    </div>
  );
}

function headingLabel(deg: number): string {
  const dirs = ["N", "NE", "E", "SE", "S", "SW", "W", "NW"];
  return dirs[Math.round(deg / 45) % 8];
}

// ── Main Component ─────────────────────────────────────────────────────────────
export default function LiveActivityClient() {
  const [data, setData] = useState<{ activities: OfficerActivity[]; summary: Summary } | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [filter, setFilter] = useState<"all" | "active" | "idle" | "offline" | "alerts">("all");
  const [lastRefresh, setLastRefresh] = useState<Date | null>(null);
  const [selectedId, setSelectedId] = useState<number | null>(null);

  const load = useCallback(async () => {
    try {
      const res = await fetch("/api/v1/live-activity").then(r => r.json());
      if (res.success) { setData(res.data); setLastRefresh(new Date()); setError(""); }
      else setError(res.error ?? "Failed to load");
    } catch (e) { setError(String(e)); }
    finally { setLoading(false); }
  }, []);

  useEffect(() => {
    load();
    const interval = setInterval(load, 30_000);
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
      {selectedId !== null && (
        <OfficerDetailDrawer officerId={selectedId} onClose={() => setSelectedId(null)} />
      )}

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

      {data && (
        <div className="grid grid-cols-2 gap-3 sm:grid-cols-5">
          <StatCard label="Total Officers" value={data.summary.total}   color="bg-blue-50 text-blue-600"       icon={User} />
          <StatCard label="Active Now"     value={data.summary.active}  color="bg-emerald-50 text-emerald-600" icon={Zap} />
          <StatCard label="Idle / Paused"  value={data.summary.idle}    color="bg-amber-50 text-amber-600"     icon={Clock} />
          <StatCard label="Offline"        value={data.summary.offline} color="bg-slate-100 text-slate-500"    icon={WifiOff} />
          <StatCard label="⚠ Idle Alerts" value={data.summary.alerts}  color="bg-red-50 text-red-500"         icon={AlertTriangle} />
        </div>
      )}

      <div className="flex gap-2 flex-wrap">
        {(["all", "active", "idle", "offline", "alerts"] as const).map(f => (
          <button key={f} onClick={() => setFilter(f)}
            className={cn(
              "rounded-full border px-3 py-1 text-xs font-medium transition-colors",
              filter === f ? "bg-[#C8102E] text-white border-[#C8102E]" : "bg-white text-slate-600 border-slate-200 hover:border-[#C8102E] hover:text-[#C8102E]"
            )}>
            {f === "alerts" ? "⚠ Alerts" : f.charAt(0).toUpperCase() + f.slice(1)}
            {data && f !== "all" && (
              <span className="ml-1 opacity-70">
                ({f === "active" ? data.summary.active : f === "idle" ? data.summary.idle : f === "offline" ? data.summary.offline : data.summary.alerts})
              </span>
            )}
          </button>
        ))}
      </div>

      {error && <div className="rounded-xl border border-red-200 bg-red-50 px-4 py-3 text-sm text-red-600">{error}</div>}

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

      {!loading && filtered.length === 0 && (
        <div className="py-16 text-center text-slate-400">
          <CheckCircle className="h-10 w-10 mx-auto mb-3 text-slate-200" />
          <p className="text-sm">No officers match this filter</p>
        </div>
      )}

      <div className="grid grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-3">
        {filtered.map(officer => (
          <OfficerCard key={officer.id} officer={officer} onSelect={() => setSelectedId(officer.id)} />
        ))}
      </div>
    </div>
  );
}
