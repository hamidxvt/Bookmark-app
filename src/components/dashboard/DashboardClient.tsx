"use client";

import { useCallback, useEffect, useState } from "react";
import Link from "next/link";
import { useRouter } from "next/navigation";
import axios from "axios";
import {
  CartesianGrid, Cell, Legend, Line, LineChart,
  Pie, PieChart, ResponsiveContainer, Tooltip, XAxis, YAxis,
} from "recharts";
import {
  Activity, Boxes, Building2, CalendarCheck, CalendarPlus,
  CheckCircle2, Clock, Download, FileText, MapPinned, Package,
  Route as RouteIcon, UserPlus, UserRound, Users,
} from "lucide-react";
import { Progress } from "@/components/ui/progress";
import { SectionCard, StatCard, StatusPill, EmptyState } from "@/components/shared/ui-bits";
import { cn } from "@/lib/utils";

// ─── types ────────────────────────────────────────────────────────────────────
interface DashSummary {
  totalBookers: number;
  totalCustomers: number;
  totalVisits: number;
  visitsToday: number;
  totalProducts: number;
  pendingRequests: number;
  pendingLeaves: number;
  missedVisits: number;
  monthlyVisits: { month: string; completed: number; pending: number }[];
  customersByCity: { city: string; count: number }[];
  recentVisits: {
    id: string;
    bookerName: string;
    customerName: string;
    status: string;
    visitDate: string;
    city?: string;
  }[];
}

interface Officer {
  id: string;
  name: string;
  city?: string;
  isOffline: boolean;
  isIdle: boolean;
  visitsDone: number;
  visitsTotal: number;
}

// ─── constants ─────────────────────────────────────────────────────────────────
const RANGES = ["Today", "Week", "Month", "Year"] as const;

const PIE_COLORS = [
  "var(--color-chart-1)",
  "var(--color-chart-2)",
  "var(--color-chart-3)",
  "var(--color-chart-4)",
  "var(--color-chart-5)",
];

// ─── helpers ──────────────────────────────────────────────────────────────────
function initials(name: string) {
  return name
    .split(" ")
    .map((w) => w[0])
    .join("")
    .toUpperCase()
    .slice(0, 2);
}

function officerStatus(o: Officer) {
  if (o.isOffline) return "offline";
  if (o.isIdle)    return "idle";
  return "active";
}

// ─── main component ────────────────────────────────────────────────────────────
export default function DashboardClient() {
  const router = useRouter();
  const [summary, setSummary] = useState<DashSummary | null>(null);
  const [officers, setOfficers] = useState<Officer[]>([]);
  const [loading, setLoading] = useState(true);
  const [range, setRange] = useState<(typeof RANGES)[number]>("Week");
  const [seed, setSeed] = useState(0);

  const load = useCallback(async () => {
    try {
      const [sRes, aRes] = await Promise.all([
        axios.get("/api/v1/dashboard"),
        axios.get("/api/v1/live-activity"),
      ]);
      setSummary(sRes.data?.data ?? sRes.data);
      const raw: Record<string, unknown>[] = aRes.data?.data ?? aRes.data ?? [];
      setOfficers(
        raw.map((o) => ({
          id:          String(o.id),
          name:        String(o.name),
          city:        o.city ? String(o.city) : undefined,
          isOffline:   Boolean(o.isOffline),
          isIdle:      Boolean(o.isIdle),
          visitsDone:  Number(o.visitsDone  ?? 0),
          visitsTotal: Number(o.visitsTotal ?? 0),
        })),
      );
    } catch (e) {
      console.error(e);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => { load(); }, [load, seed]);
  useEffect(() => {
    const t = setInterval(load, 60_000);
    return () => clearInterval(t);
  }, [load]);

  // ── quick actions ────────────────────────────────────────────────────────────
  const quickActions = [
    { label: "Add Customer", icon: UserPlus,     action: () => router.push("/customers/add") },
    { label: "Add Officer",  icon: Users,         action: () => router.push("/bookers") },
    { label: "Create Visit", icon: CalendarPlus,  action: () => router.push("/visits") },
    { label: "View Map",     icon: MapPinned,     action: () => router.push("/location") },
    { label: "Reports",      icon: FileText,      action: () => router.push("/reports") },
    { label: "Attendance",   icon: CalendarCheck, action: () => router.push("/attendance") },
  ];

  // ── recentActivity (from recentVisits) ───────────────────────────────────────
  const recentActivity = (summary?.recentVisits ?? []).slice(0, 6).map((v) => ({
    type:   v.status === "COMPLETED" ? "visit" : "visit",
    title:  v.customerName,
    detail: `${v.bookerName} · ${v.status}`,
    by:     v.bookerName,
    time:   new Date(v.visitDate).toLocaleDateString("en-GB", { day: "2-digit", month: "short" }),
  }));

  const activityIconMap: Record<string, React.ElementType> = {
    customer: UserRound, visit: RouteIcon, sample: Package, request: CheckCircle2, attendance: CalendarCheck,
  };

  // ── chart data (use monthlyVisits, filter by range) ──────────────────────────
  const chartData = (() => {
    const all = summary?.monthlyVisits ?? [];
    if (range === "Today") return all.slice(-1);
    if (range === "Week")  return all.slice(-2);
    if (range === "Month") return all.slice(-4);
    return all;
  })().map((m) => ({ label: m.month, completed: m.completed, pending: m.pending }));

  if (loading) {
    return (
      <div className="flex min-h-[60vh] items-center justify-center">
        <div className="flex flex-col items-center gap-3">
          <div className="h-8 w-8 animate-spin rounded-full border-2 border-primary border-t-transparent" />
          <p className="text-sm font-medium text-muted-foreground">Loading dashboard…</p>
        </div>
      </div>
    );
  }

  const s = summary!;
  const liveOfficers = officers.slice(0, 8);

  return (
    <div key={seed} className="space-y-6 px-6 py-6 lg:px-8">

      {/* ── KPI row ── */}
      <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-4">
        <StatCard
          label="Today's Visits"
          value={s.visitsToday}
          trend="Scheduled today"
          href="/visits"
          icon={<Activity className="h-5 w-5" />}
        />
        <StatCard
          label="Field Officers"
          value={s.totalBookers}
          href="/bookers"
          icon={<Users className="h-5 w-5" />}
        />
        <StatCard
          label="Customers"
          value={s.totalCustomers}
          href="/customers"
          icon={<Building2 className="h-5 w-5" />}
        />
        <StatCard
          label="Total Visits"
          value={s.totalVisits}
          href="/visits"
          icon={<RouteIcon className="h-5 w-5" />}
        />
      </div>

      {/* ── Charts ── */}
      <div className="grid gap-6 xl:grid-cols-[1.6fr_1fr]">
        <SectionCard
          title="Visit Performance"
          description="Completed vs pending visits"
          action={
            <div className="flex rounded-xl bg-muted p-1">
              {RANGES.map((r) => (
                <button
                  key={r}
                  onClick={() => setRange(r)}
                  className={cn(
                    "rounded-lg px-3 py-1.5 text-xs font-semibold transition-all duration-200",
                    range === r
                      ? "bg-card text-foreground shadow-card"
                      : "text-muted-foreground hover:text-foreground",
                  )}
                >
                  {r}
                </button>
              ))}
            </div>
          }
        >
          <div className="h-[300px]">
            <ResponsiveContainer width="100%" height="100%">
              <LineChart data={chartData} margin={{ left: -18, right: 8, top: 8 }}>
                <CartesianGrid strokeDasharray="4 4" stroke="var(--color-border)" vertical={false} />
                <XAxis dataKey="label" tickLine={false} axisLine={false} fontSize={12} stroke="var(--color-muted-foreground)" />
                <YAxis tickLine={false} axisLine={false} fontSize={12} stroke="var(--color-muted-foreground)" />
                <Tooltip
                  contentStyle={{
                    borderRadius: 14, border: "1px solid var(--color-border)",
                    background: "var(--color-card)", fontSize: 12,
                  }}
                />
                <Legend iconType="circle" wrapperStyle={{ fontSize: 12, paddingTop: 8 }} />
                <Line
                  type="monotone" dataKey="completed" name="Completed Visits"
                  stroke="var(--color-chart-1)" strokeWidth={3}
                  dot={{ r: 3 }} activeDot={{ r: 5 }}
                />
                <Line
                  type="monotone" dataKey="pending" name="Pending Visits"
                  stroke="var(--color-chart-3)" strokeWidth={3}
                  strokeDasharray="6 4" dot={{ r: 3 }}
                />
              </LineChart>
            </ResponsiveContainer>
          </div>
        </SectionCard>

        <SectionCard title="Customer Distribution" description="Customers by city">
          <div className="h-[220px]">
            <ResponsiveContainer width="100%" height="100%">
              <PieChart>
                <Pie
                  data={s.customersByCity.slice(0, 5)}
                  dataKey="count"
                  nameKey="city"
                  innerRadius={62}
                  outerRadius={92}
                  paddingAngle={3}
                  stroke="none"
                >
                  {s.customersByCity.slice(0, 5).map((_, i) => (
                    <Cell key={i} fill={PIE_COLORS[i % PIE_COLORS.length]} />
                  ))}
                </Pie>
                <Tooltip
                  contentStyle={{
                    borderRadius: 14, border: "1px solid var(--color-border)",
                    background: "var(--color-card)", fontSize: 12,
                  }}
                />
              </PieChart>
            </ResponsiveContainer>
          </div>
          <ul className="mt-4 space-y-2">
            {s.customersByCity.slice(0, 5).map((c, i) => (
              <li key={c.city} className="flex items-center gap-2 text-sm">
                <span className="h-2.5 w-2.5 rounded-full" style={{ background: PIE_COLORS[i % PIE_COLORS.length] }} />
                <span className="flex-1 text-muted-foreground">{c.city}</span>
                <span className="font-semibold">{c.count.toLocaleString()}</span>
              </li>
            ))}
          </ul>
        </SectionCard>
      </div>

      {/* ── Live officers ── */}
      <SectionCard
        title="Live Field Officers"
        description="Officers currently in the field"
        action={
          <Link
            href="/live-activity"
            className="flex items-center gap-2 rounded-xl bg-primary px-4 py-2 text-sm font-semibold text-primary-foreground shadow-brand transition hover:opacity-90"
          >
            <MapPinned className="h-4 w-4" /> Open Live Tracking
          </Link>
        }
      >
        {liveOfficers.length === 0 ? (
          <EmptyState title="No officers with GPS data right now" />
        ) : (
          <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-4">
            {liveOfficers.map((o) => {
              const status = officerStatus(o);
              const pct = o.visitsTotal > 0 ? (o.visitsDone / o.visitsTotal) * 100 : 0;
              return (
                <Link
                  key={o.id}
                  href={`/bookers`}
                  className="rounded-2xl border border-border/70 p-4 transition-all duration-300 hover:-translate-y-1 hover:border-transparent hover:shadow-elevated"
                >
                  <div className="flex items-center gap-3">
                    <span className="relative flex h-11 w-11 items-center justify-center rounded-full bg-navy text-sm font-bold text-navy-foreground">
                      {initials(o.name)}
                      <span
                        className={cn(
                          "absolute -bottom-0.5 -right-0.5 h-3.5 w-3.5 rounded-full border-2 border-card",
                          status === "active"  ? "bg-success"
                          : status === "idle" ? "bg-warning"
                          : "bg-muted-foreground",
                        )}
                      />
                    </span>
                    <div className="min-w-0">
                      <p className="truncate text-sm font-semibold text-foreground">{o.name}</p>
                      <p className="text-xs text-muted-foreground">{o.city ?? "—"}</p>
                    </div>
                  </div>
                  <div className="mt-3 flex items-center justify-between">
                    <StatusPill value={status} />
                    <span className="text-xs font-medium text-muted-foreground">
                      {o.visitsDone}/{o.visitsTotal} visits
                    </span>
                  </div>
                  <Progress value={pct} className="mt-3 h-1.5" />
                </Link>
              );
            })}
          </div>
        )}
      </SectionCard>

      {/* ── Activity + Quick Actions ── */}
      <div className="grid gap-6 xl:grid-cols-[1.4fr_1fr]">
        <SectionCard title="Recent Activity" description="Latest events across the field force">
          {recentActivity.length === 0 ? (
            <EmptyState title="No activity yet" />
          ) : (
            <ol className="relative space-y-5 pl-7">
              <span className="absolute left-[11px] top-2 h-[calc(100%-1rem)] w-px bg-border" />
              {recentActivity.map((a, i) => {
                const Icon = activityIconMap[a.type] ?? Activity;
                return (
                  <li key={i} className="relative">
                    <span className="absolute -left-7 flex h-6 w-6 items-center justify-center rounded-full bg-primary-soft text-primary ring-4 ring-card">
                      <Icon className="h-3 w-3" />
                    </span>
                    <p className="text-sm font-semibold text-foreground">{a.title}</p>
                    <p className="text-sm text-muted-foreground">{a.detail}</p>
                    <p className="mt-0.5 text-xs text-muted-foreground/80">{a.by} · {a.time}</p>
                  </li>
                );
              })}
            </ol>
          )}
        </SectionCard>

        <SectionCard title="Quick Actions" description="Jump straight into common tasks">
          <div className="grid gap-3 sm:grid-cols-2">
            {quickActions.map((q) => (
              <button
                key={q.label}
                onClick={q.action}
                className="group flex items-center gap-3 rounded-2xl border border-border/70 p-3.5 text-left transition-all duration-200 hover:-translate-y-0.5 hover:border-primary/40 hover:bg-primary-soft"
              >
                <span className="flex h-9 w-9 items-center justify-center rounded-xl bg-primary-soft text-primary transition-colors group-hover:bg-primary group-hover:text-primary-foreground">
                  <q.icon className="h-4 w-4" />
                </span>
                <span className="text-sm font-medium text-foreground">{q.label}</span>
              </button>
            ))}
          </div>
          <button
            className="mt-4 flex w-full items-center justify-center gap-2 rounded-xl border border-border py-2.5 text-sm font-medium text-muted-foreground transition hover:bg-muted/50 hover:text-foreground"
            onClick={() => router.push("/reports")}
          >
            <Download className="h-4 w-4" /> Export daily summary
          </button>
        </SectionCard>
      </div>

      {/* ── Pending alerts row ── */}
      <div className="grid gap-4 sm:grid-cols-3">
        {[
          { label: "Pending Sample Requests", value: s.pendingRequests, href: "/requests",    icon: Package },
          { label: "Pending Leave Requests",  value: s.pendingLeaves,   href: "/attendance",  icon: CalendarCheck },
          { label: "Missed Visit Reviews",    value: s.missedVisits,    href: "/missed-visits", icon: Clock },
        ].map(({ label, value, href, icon: Icon }) => (
          <Link
            key={label}
            href={href}
            className={cn(
              "surface flex items-center gap-4 p-5 transition-all duration-300 hover:-translate-y-0.5 hover:shadow-elevated",
              value > 0 && "border border-destructive/25 bg-destructive/5",
            )}
          >
            <span
              className={cn(
                "flex h-11 w-11 shrink-0 items-center justify-center rounded-xl",
                value > 0 ? "bg-destructive/15 text-destructive" : "bg-primary-soft text-primary",
              )}
            >
              <Icon className="h-5 w-5" />
            </span>
            <div>
              <p className={cn("text-2xl font-bold", value > 0 ? "text-destructive" : "text-foreground")}>
                {value}
              </p>
              <p className="text-sm text-muted-foreground">{label}</p>
            </div>
          </Link>
        ))}
      </div>
    </div>
  );
}
