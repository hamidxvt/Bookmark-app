"use client";

import { useCallback, useEffect, useState } from "react";
import {
  Activity, AlertTriangle, Car, CheckCircle, Clock, MapPin,
  RefreshCw, User, Wifi, WifiOff, Zap, X, Phone, Mail,
  Circle, ChevronRight,
} from "lucide-react";
import { cn } from "@/lib/utils";
import { SectionCard, StatCard, EmptyState, TableSkeleton } from "@/components/shared/ui-bits";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";

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

// ── Color helpers — mapped onto the app's semantic tokens ───────────────────────
const COLOR_CLASSES: Record<string, string> = {
  green: "bg-success/15 text-success border-success/25",
  blue:  "bg-info/15 text-info-foreground border-info/25",
  amber: "bg-warning/20 text-warning-foreground border-warning/30",
  gray:  "bg-muted text-muted-foreground border-border",
};
const DOT_CLASSES: Record<string, string> = {
  green: "bg-success pulse-dot",
  blue:  "bg-info pulse-dot",
  amber: "bg-warning pulse-dot",
  gray:  "bg-muted-foreground",
};

// ── Visit status badge ─────────────────────────────────────────────────────────
function VisitStatusBadge({ status }: { status: string }) {
  const map: Record<string, { label: string; cls: string }> = {
    completed:   { label: "Done",     cls: "bg-success/15 text-success" },
    in_progress: { label: "Active",   cls: "bg-info/15 text-info-foreground pulse-dot" },
    pending:     { label: "Upcoming", cls: "bg-muted text-muted-foreground" },
    missed:      { label: "Missed",   cls: "bg-destructive/15 text-destructive" },
  };
  const s = map[status] ?? { label: status, cls: "bg-muted text-muted-foreground" };
  return <span className={cn("inline-flex items-center rounded-full px-2 py-0.5 text-[10px] font-semibold", s.cls)}>{s.label}</span>;
}

// ── Mini stat tile (used inside the drawer) ─────────────────────────────────────
function MiniStat({ label, value, color, icon: Icon }: { label: string; value: number | string; color: string; icon: React.ElementType }) {
  return (
    <div className="flex items-center gap-3 rounded-xl border border-border/70 bg-card p-4 shadow-card">
      <div className={cn("flex h-10 w-10 items-center justify-center rounded-full", color)}>
        <Icon className="h-5 w-5" />
      </div>
      <div>
        <p className="text-xl font-bold text-foreground tabular-nums">{value}</p>
        <p className="text-xs text-muted-foreground">{label}</p>
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
    <div className="fixed inset-0 z-50 flex items-end sm:items-center justify-center bg-foreground/40 backdrop-blur-sm p-0 sm:p-4" onClick={onClose}>
      <div
        className="w-full sm:max-w-2xl surface rounded-t-3xl sm:rounded-2xl max-h-[92vh] overflow-y-auto shadow-elevated"
        onClick={e => e.stopPropagation()}
      >
        {/* Handle */}
        <div className="sticky top-0 bg-card z-10 pt-3 pb-2 px-5 border-b border-border">
          <div className="w-10 h-1 rounded-full bg-muted mx-auto mb-3 sm:hidden" />
          <div className="flex items-center justify-between">
            <p className="text-sm font-semibold text-foreground">Officer Details</p>
            <button onClick={onClose} className="rounded-lg p-1.5 hover:bg-muted"><X className="h-4 w-4 text-muted-foreground" /></button>
          </div>
        </div>

        {loading ? (
          <div className="p-8">
            <TableSkeleton rows={4} />
          </div>
        ) : !detail ? (
          <div className="p-8 text-center text-muted-foreground text-sm">Failed to load officer data</div>
        ) : (
          <div className="p-5 space-y-5">
            {/* Officer header */}
            <div className="flex items-center gap-4">
              <div className="relative shrink-0">
                {detail.officer.profilePhoto
                  ? <img src={detail.officer.profilePhoto} alt={detail.officer.name} className="h-16 w-16 rounded-full object-cover border-2 border-border" />
                  : <div className="h-16 w-16 rounded-full bg-navy flex items-center justify-center text-navy-foreground text-xl font-bold">{detail.officer.name[0]}</div>}
                <span className={cn("absolute -bottom-0.5 -right-0.5 h-4 w-4 rounded-full border-2 border-card",
                  detail.officer.isOffline ? "bg-muted-foreground" : detail.officer.isIdle ? "bg-warning pulse-dot" : "bg-success pulse-dot")} />
              </div>
              <div className="flex-1">
                <h2 className="text-lg font-bold text-foreground">{detail.officer.name}</h2>
                <p className="text-xs text-muted-foreground">{detail.officer.designation ?? "Officer"} · {detail.officer.city}</p>
                <div className="flex items-center gap-3 mt-1">
                  <a href={`tel:${detail.officer.phone}`} className="flex items-center gap-1 text-xs text-info hover:underline">
                    <Phone className="h-3 w-3" />{detail.officer.phone}
                  </a>
                  <span className="text-border">|</span>
                  <span className="flex items-center gap-1 text-xs text-muted-foreground">
                    <Mail className="h-3 w-3" />{detail.officer.email}
                  </span>
                </div>
              </div>
              <div className="text-right shrink-0">
                <p className={cn("text-xs font-semibold px-2 py-1 rounded-full border",
                  detail.officer.isOffline ? "bg-muted text-muted-foreground border-border"
                  : detail.officer.isIdle ? "bg-warning/20 text-warning-foreground border-warning/30"
                  : "bg-success/15 text-success border-success/25")}>
                  {detail.officer.activityLabel}
                </p>
                {detail.officer.currentSpeed > 3 && (
                  <p className="text-xs text-info mt-1 flex items-center gap-1 justify-end">
                    <Car className="h-3 w-3" />{detail.officer.currentSpeed} km/h
                  </p>
                )}
              </div>
            </div>

            {/* Today stats bar */}
            <div className="grid grid-cols-2 gap-3 sm:grid-cols-4">
              {[
                { label: "Completed",   value: detail.stats.completed,   color: "bg-success/15 text-success",   icon: CheckCircle },
                { label: "In Progress", value: detail.stats.inProgress,  color: "bg-info/15 text-info-foreground", icon: Activity },
                { label: "Remaining",   value: detail.stats.pending,     color: "bg-muted text-muted-foreground", icon: Clock },
                { label: "Missed",      value: detail.stats.missed,      color: "bg-destructive/15 text-destructive", icon: AlertTriangle },
              ].map(s => <MiniStat key={s.label} {...s} />)}
            </div>

            {/* Distance + field time */}
            <div className="grid grid-cols-2 gap-3">
              <div className="rounded-xl border border-border/70 bg-muted/50 p-3">
                <p className="text-[10px] text-muted-foreground font-medium uppercase tracking-wide">Distance Today</p>
                <p className="text-xl font-bold text-foreground mt-0.5 tabular-nums">{detail.stats.distanceKm} km</p>
              </div>
              <div className="rounded-xl border border-border/70 bg-muted/50 p-3">
                <p className="text-[10px] text-muted-foreground font-medium uppercase tracking-wide">In Field Since</p>
                <p className="text-xl font-bold text-foreground mt-0.5">
                  {detail.stats.fieldSince ? new Date(detail.stats.fieldSince).toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" }) : "—"}
                </p>
              </div>
            </div>

            {/* Live map */}
            {mapUrl && (
              <div>
                <p className="text-xs font-semibold text-muted-foreground mb-2 uppercase tracking-wide">Current Location</p>
                <div className="overflow-hidden rounded-xl border border-border/70 h-44">
                  <iframe src={mapUrl} className="w-full h-full" title="officer location" />
                </div>
                <a
                  href={`https://www.openstreetmap.org/?mlat=${detail.officer.currentLat}&mlon=${detail.officer.currentLng}`}
                  target="_blank" rel="noopener noreferrer"
                  className="mt-1 flex items-center gap-1 text-xs text-primary hover:underline"
                >
                  <MapPin className="h-3 w-3" /> Open full map
                </a>
              </div>
            )}

            {/* GPS Trail summary */}
            {detail.trail.length > 0 && (
              <div>
                <p className="text-xs font-semibold text-muted-foreground mb-2 uppercase tracking-wide">
                  GPS Trail (last 2 hrs · {detail.trail.length} pings)
                </p>
                <div className="rounded-xl border border-border/70 bg-muted/50 p-3 max-h-32 overflow-y-auto">
                  {detail.trail.slice(-20).reverse().map((p, i) => (
                    <div key={i} className="flex items-center gap-3 text-xs py-1 border-b border-border/60 last:border-0">
                      <span className="text-muted-foreground w-14 shrink-0">{new Date(p.time).toLocaleTimeString([], { hour: "2-digit", minute: "2-digit", second: "2-digit" })}</span>
                      <span className={cn("w-2 h-2 rounded-full shrink-0",
                        p.activity === "STATIONARY" ? "bg-warning" : p.speed > 15 ? "bg-info" : "bg-success")} />
                      <span className="text-foreground/80">{p.activity ?? "—"}</span>
                      {p.speed > 0 && <span className="text-muted-foreground">{Math.round(p.speed)} km/h</span>}
                      {p.heading != null && <span className="text-muted-foreground/70">{Math.round(p.heading)}°</span>}
                    </div>
                  ))}
                </div>
              </div>
            )}

            {/* Today's visits timeline */}
            <div>
              <p className="text-xs font-semibold text-muted-foreground mb-3 uppercase tracking-wide">
                Today's Schedule ({detail.stats.totalVisits} visits · Target: {detail.stats.targetVisits})
              </p>
              <div className="space-y-2">
                {detail.visits.map((v) => (
                  <div key={v.id} className={cn(
                    "flex items-start gap-3 rounded-xl border p-3",
                    v.status === "completed"   ? "bg-success/10 border-success/20" :
                    v.status === "in_progress" ? "bg-info/10 border-info/25 shadow-card" :
                    v.status === "missed"      ? "bg-destructive/10 border-destructive/20" :
                    "bg-card border-border/70"
                  )}>
                    {/* Sequence */}
                    <div className={cn(
                      "h-7 w-7 rounded-full flex items-center justify-center text-xs font-bold shrink-0 mt-0.5",
                      v.status === "completed"   ? "bg-success text-success-foreground" :
                      v.status === "in_progress" ? "bg-info text-info-foreground" :
                      v.status === "missed"      ? "bg-destructive text-destructive-foreground" :
                      "bg-muted text-muted-foreground"
                    )}>{v.sequence}</div>

                    <div className="flex-1 min-w-0">
                      <div className="flex items-center gap-2">
                        <p className="text-sm font-semibold text-foreground truncate">{v.customerName}</p>
                        <VisitStatusBadge status={v.status} />
                      </div>
                      {v.address && <p className="text-xs text-muted-foreground truncate mt-0.5">{v.address}</p>}
                      {v.phone && <p className="text-xs text-muted-foreground">{v.phone}</p>}
                      <div className="flex items-center gap-3 mt-1 text-[10px] text-muted-foreground">
                        {v.checkInAt && <span>In: {new Date(v.checkInAt).toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })}</span>}
                        {v.checkOutAt && <span>Out: {new Date(v.checkOutAt).toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })}</span>}
                        {v.missedReason && <span className="text-destructive">"{v.missedReason}"</span>}
                        {v.lat && v.lng && (
                          <a href={`https://www.openstreetmap.org/?mlat=${v.lat}&mlon=${v.lng}`} target="_blank" rel="noopener noreferrer"
                            className="text-info hover:underline flex items-center gap-0.5">
                            <MapPin className="h-2.5 w-2.5" />Map
                          </a>
                        )}
                      </div>
                    </div>

                    {v.status === "in_progress" && (
                      <div className="flex items-center gap-1 text-xs text-info font-medium shrink-0">
                        <Circle className="h-2 w-2 fill-info text-info pulse-dot" /> Live
                      </div>
                    )}
                  </div>
                ))}
                {detail.visits.length === 0 && (
                  <p className="text-center text-sm text-muted-foreground py-4">No visits scheduled today</p>
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
        "surface p-4 transition-all duration-300 cursor-pointer hover:-translate-y-1 hover:shadow-elevated",
        officer.idleAlert && "ring-1 ring-warning/50",
      )}
      onClick={onSelect}
    >
      {officer.idleAlert && (
        <div className="flex items-center gap-2 rounded-lg bg-warning/15 border border-warning/30 px-3 py-2 mb-3 text-xs text-warning-foreground font-medium">
          <AlertTriangle className="h-3.5 w-3.5 shrink-0" />
          Stationary 5+ min — tap for full details
        </div>
      )}

      {/* Header row */}
      <div className="flex items-start gap-3">
        <div className="relative shrink-0">
          {officer.profilePhoto
            ? <img src={officer.profilePhoto} alt={officer.name} className="h-11 w-11 rounded-full object-cover border-2 border-border" />
            : <div className="h-11 w-11 rounded-full bg-navy flex items-center justify-center text-navy-foreground text-sm font-bold">{officer.name[0].toUpperCase()}</div>}
          <span className={cn("absolute -bottom-0.5 -right-0.5 h-3.5 w-3.5 rounded-full border-2 border-card", DOT_CLASSES[officer.activityColor])} />
        </div>

        <div className="flex-1 min-w-0">
          <div className="flex items-center gap-2 flex-wrap">
            <p className="text-sm font-semibold text-foreground truncate">{officer.name}</p>
            <span className={cn("inline-flex items-center gap-1 rounded-full border px-2 py-0.5 text-[10px] font-semibold", COLOR_CLASSES[officer.activityColor])}>
              <span className={cn("h-1.5 w-1.5 rounded-full", DOT_CLASSES[officer.activityColor])} />
              {officer.activityLabel}
            </span>
          </div>
          <p className="text-xs text-muted-foreground mt-0.5">{officer.designation ?? "Officer"} · {officer.city}</p>
        </div>

        <div className="shrink-0 flex items-center gap-1.5">
          {officer.isOffline ? <WifiOff className="h-4 w-4 text-muted-foreground/60" /> : <Wifi className="h-4 w-4 text-success" />}
          <ChevronRight className="h-4 w-4 text-muted-foreground/60" />
        </div>
      </div>

      {/* Active visit banner */}
      {officer.activeVisit && (
        <div className="mt-3 rounded-lg bg-success/10 border border-success/20 px-3 py-2 flex items-start gap-2">
          <MapPin className="h-3.5 w-3.5 text-success mt-0.5 shrink-0" />
          <div className="min-w-0">
            <p className="text-xs font-semibold text-success truncate">{officer.activeVisit.customerName}</p>
            {officer.activeVisit.address && <p className="text-[10px] text-success/80 truncate">{officer.activeVisit.address}</p>}
            {officer.activeVisit.checkInAt && (
              <p className="text-[10px] text-success/70">
                Check-in: {new Date(officer.activeVisit.checkInAt).toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })}
              </p>
            )}
          </div>
          <span className="ml-auto flex items-center gap-1 text-[10px] text-success font-semibold shrink-0">
            <Circle className="h-2 w-2 fill-success text-success pulse-dot" /> LIVE
          </span>
        </div>
      )}

      {/* Speed / movement */}
      {!officer.isOffline && officer.speed_kmh > 3 && (
        <div className="mt-2 flex items-center gap-2 text-xs text-info bg-info/10 rounded-lg px-3 py-1.5">
          <Car className="h-3.5 w-3.5 shrink-0" />
          <span className="font-semibold">{Math.round(officer.speed_kmh)} km/h</span>
          {officer.heading != null && (
            <span className="text-info/70">· {headingLabel(officer.heading)}</span>
          )}
          <span className="text-info/60 ml-auto">En route</span>
        </div>
      )}

      {/* Visit progress bar */}
      {officer.stats.totalToday > 0 && (
        <div className="mt-3">
          <div className="flex justify-between items-center mb-1">
            <p className="text-[10px] text-muted-foreground font-medium uppercase tracking-wide">Visit Progress</p>
            <p className="text-[10px] font-semibold text-foreground">{officer.stats.completedToday}/{officer.stats.totalToday} · {progressPct}%</p>
          </div>
          <div className="h-1.5 rounded-full bg-muted overflow-hidden">
            <div
              className={cn("h-full rounded-full transition-all", progressPct === 100 ? "bg-success" : "bg-primary")}
              style={{ width: `${progressPct}%` }}
            />
          </div>
        </div>
      )}

      {/* Stats row */}
      <div className="mt-3 grid grid-cols-3 gap-2 text-center">
        {[
          { label: "Done",  value: officer.stats.completedToday, color: "text-success" },
          { label: "Left",  value: officer.stats.pendingToday,   color: "text-warning-foreground" },
          { label: "Total", value: officer.stats.totalToday,     color: "text-foreground" },
        ].map(s => (
          <div key={s.label} className="rounded-lg bg-muted/60 py-1.5">
            <p className={cn("text-base font-bold tabular-nums", s.color)}>{s.value}</p>
            <p className="text-[9px] text-muted-foreground font-medium uppercase">{s.label}</p>
          </div>
        ))}
      </div>

      {/* Last seen footer */}
      <div className="mt-2 flex items-center justify-between">
        <span className="text-[10px] text-muted-foreground/70">{officer.lastSeenText}</span>
        <span className="text-[10px] text-primary font-medium">Tap for full details →</span>
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
  const [selectedCity, setSelectedCity] = useState("all");
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

  const cities = Array.from(new Set(data?.activities.map(a => a.city).filter(Boolean) ?? [])).sort();

  const filtered = data?.activities.filter(a => {
    if (selectedCity !== "all" && a.city !== selectedCity) return false;
    if (filter === "active")  return !a.isOffline && !a.isIdle;
    if (filter === "idle")    return a.isIdle && !a.isOffline;
    if (filter === "offline") return a.isOffline;
    if (filter === "alerts")  return a.idleAlert;
    return true;
  }) ?? [];

  return (
    <div className="space-y-6 px-6 py-6 lg:px-8">
      {selectedId !== null && (
        <OfficerDetailDrawer officerId={selectedId} onClose={() => setSelectedId(null)} />
      )}

      <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-5">
        <StatCard label="Total Officers" value={data?.summary.total ?? 0} icon={<User className="h-5 w-5" />} href="/bookers" />
        <StatCard label="Active Now" value={data?.summary.active ?? 0} icon={<Zap className="h-5 w-5" />} />
        <StatCard label="Idle / Paused" value={data?.summary.idle ?? 0} icon={<Clock className="h-5 w-5" />} />
        <StatCard label="Offline" value={data?.summary.offline ?? 0} icon={<WifiOff className="h-5 w-5" />} />
        <StatCard label="Idle Alerts" value={data?.summary.alerts ?? 0} icon={<AlertTriangle className="h-5 w-5" />} />
      </div>

      <SectionCard
        title="Officer Feed"
        description={lastRefresh ? `Auto-refreshes every 30s · Last: ${lastRefresh.toLocaleTimeString()}` : "Loading…"}
        action={
          <button onClick={load} disabled={loading}
            className="flex items-center gap-1.5 rounded-xl border border-border bg-card px-3 py-2 text-xs font-medium text-muted-foreground shadow-card hover:shadow-elevated">
            <RefreshCw className={cn("h-3.5 w-3.5", loading && "animate-spin")} /> Refresh
          </button>
        }
      >
        <div className="mb-5 flex flex-wrap items-center gap-3">
          <Select value={selectedCity} onValueChange={setSelectedCity}>
            <SelectTrigger className="h-10 w-44 rounded-xl">
              <SelectValue placeholder="City" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="all">All Cities</SelectItem>
              {cities.map(city => (
                <SelectItem key={city} value={city}>{city}</SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>

        <div className="mb-5 flex gap-2 flex-wrap">
          {(["all", "active", "idle", "offline", "alerts"] as const).map(f => (
            <button key={f} onClick={() => setFilter(f)}
              className={cn(
                "rounded-full border px-3 py-1 text-xs font-medium transition-colors",
                filter === f ? "bg-primary text-primary-foreground border-primary" : "bg-card text-muted-foreground border-border hover:border-primary/40 hover:text-primary"
              )}>
              {f === "alerts" ? "Alerts" : f.charAt(0).toUpperCase() + f.slice(1)}
              {data && f !== "all" && (
                <span className="ml-1 opacity-70">
                  ({f === "active" ? data.summary.active : f === "idle" ? data.summary.idle : f === "offline" ? data.summary.offline : data.summary.alerts})
                </span>
              )}
            </button>
          ))}
        </div>

        {error && <div className="mb-5 rounded-xl border border-destructive/25 bg-destructive/10 px-4 py-3 text-sm text-destructive">{error}</div>}

        {loading && !data ? (
          <div className="grid grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-3">
            {[...Array(6)].map((_, i) => (
              <div key={i} className="surface p-4">
                <TableSkeleton rows={2} />
              </div>
            ))}
          </div>
        ) : filtered.length === 0 ? (
          <EmptyState title="No officers match this filter" />
        ) : (
          <div className="grid grid-cols-1 gap-4 sm:grid-cols-2 lg:grid-cols-3">
            {filtered.map(officer => (
              <OfficerCard key={officer.id} officer={officer} onSelect={() => setSelectedId(officer.id)} />
            ))}
          </div>
        )}
      </SectionCard>
    </div>
  );
}
