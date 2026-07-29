"use client";

import { useEffect, useState } from "react";
import {
  Users, UserCheck, ClipboardList, Package,
  AlertCircle, Activity, MapPin, RefreshCw,
  CalendarX, FileText,
} from "lucide-react";
import { timeAgo } from "@/lib/utils";
import {
  AreaChart, Area, PieChart, Pie, Cell,
  XAxis, YAxis, Tooltip, ResponsiveContainer, Legend,
} from "recharts";

interface Stats {
  totalBookers: number;
  totalCustomers: number;
  totalVisits: number;
  visitsToday: number;
  totalProducts: number;
  pendingRequests: number;
  pendingLeaves: number;
  pendingMissedVisits: number;
}

interface TrendPoint { month: string; completed: number; pending: number; }
interface CityPoint { name: string; value: number; color: string; }

interface Visit {
  id: number;
  visitDate: string;
  status: string;
  booker: { name: string };
  customer: { name: string; customerType: string };
}

function StatCard({ icon: Icon, label, value, sub, color }: {
  icon: React.ElementType; label: string; value: number | string;
  sub?: string; color: string;
}) {
  return (
    <div className="rounded-2xl bg-white border border-slate-200 p-5 shadow-xs hover:shadow-md transition-shadow">
      <div className={`flex h-10 w-10 items-center justify-center rounded-xl ${color}`}>
        <Icon className="h-5 w-5" />
      </div>
      <p className="mt-4 text-2xl font-bold text-slate-900 tabular-nums">
        {typeof value === "number" ? value.toLocaleString() : value}
      </p>
      <p className="text-sm text-slate-500 mt-0.5">{label}</p>
      {sub && <p className="text-xs text-slate-400 mt-1">{sub}</p>}
    </div>
  );
}

function VisitBadge({ status }: { status: string }) {
  const s = (status ?? "").toUpperCase();
  const styles: Record<string, string> = {
    COMPLETED: "bg-emerald-100 text-emerald-700",
    PENDING: "bg-blue-100 text-blue-700",
    CANCELLED: "bg-red-100 text-red-700",
    IN_PROGRESS: "bg-amber-100 text-amber-700",
  };
  const key = Object.keys(styles).find(k => s.includes(k)) ?? "PENDING";
  const label = key.replace("_", " ").charAt(0) + key.replace("_", " ").slice(1).toLowerCase();
  return (
    <span className={`inline-flex rounded-full px-2 py-0.5 text-xs font-medium ${styles[key]}`}>
      {label}
    </span>
  );
}

export default function DashboardClient() {
  const [stats, setStats] = useState<Stats | null>(null);
  const [visits, setVisits] = useState<Visit[]>([]);
  const [trend, setTrend] = useState<TrendPoint[]>([]);
  const [cityData, setCityData] = useState<CityPoint[]>([]);
  const [loading, setLoading] = useState(true);
  const [lastRefresh, setLastRefresh] = useState(new Date());

  async function load() {
    setLoading(true);
    try {
      const [statsRes, visitsRes, trendRes] = await Promise.all([
        fetch("/api/v1/dashboard").then(r => r.json()),
        fetch("/api/v1/visits?length=8").then(r => r.json()),
        fetch("/api/v1/visits/trend").then(r => r.json()),
      ]);

      if (statsRes.success) setStats(statsRes.data);
      if (visitsRes.success) setVisits(visitsRes.data?.data ?? []);
      if (trendRes.success) {
        setTrend(trendRes.data.trend ?? []);
        setCityData(trendRes.data.cityBreakdown ?? []);
      }

      setLastRefresh(new Date());
    } catch (err) {
      console.error("Dashboard load error:", err);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    load();
    const interval = setInterval(load, 60000);
    return () => clearInterval(interval);
  }, []);

  const KPI_CARDS = [
    { icon: UserCheck, label: "Approved Bookers", value: stats?.totalBookers ?? "—", sub: "Active field officers", color: "bg-blue-100 text-blue-600" },
    { icon: Users, label: "Total Customers", value: stats?.totalCustomers ?? "—", sub: "Schools & bookshops", color: "bg-violet-100 text-violet-600" },
    { icon: ClipboardList, label: "Total Visits", value: stats?.totalVisits ?? "—", sub: "All time", color: "bg-teal-100 text-teal-600" },
    { icon: Activity, label: "Today's Visits", value: stats?.visitsToday ?? "—", sub: "Scheduled today", color: "bg-cyan-100 text-cyan-600" },
    { icon: Package, label: "Total Products", value: stats?.totalProducts ?? "—", sub: "In catalog", color: "bg-amber-100 text-amber-600" },
    { icon: AlertCircle, label: "Pending Requests", value: stats?.pendingRequests ?? "—", sub: "Need attention", color: "bg-red-100 text-red-600" },
    { icon: CalendarX, label: "Pending Leaves", value: stats?.pendingLeaves ?? "—", sub: "Awaiting review", color: "bg-orange-100 text-orange-600" },
    { icon: FileText, label: "Missed Reviews", value: stats?.pendingMissedVisits ?? "—", sub: "Pending approval", color: "bg-rose-100 text-rose-600" },
  ];

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div>
          <h2 className="text-xl font-bold text-slate-900">Dashboard</h2>
          <p className="text-sm text-slate-500 mt-0.5">
            Live data · Last refreshed {timeAgo(lastRefresh)}
          </p>
        </div>
        <button
          onClick={load}
          disabled={loading}
          className="flex items-center gap-2 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50 transition-colors disabled:opacity-50"
        >
          <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} />
          Refresh
        </button>
      </div>

      {/* KPI Grid */}
      <div className="grid grid-cols-2 gap-4 sm:grid-cols-4 lg:grid-cols-8">
        {KPI_CARDS.map((card) => (
          <StatCard key={card.label} {...card} />
        ))}
      </div>

      {/* Charts */}
      <div className="grid grid-cols-1 gap-4 lg:grid-cols-3">
        <div className="col-span-2 rounded-2xl bg-white border border-slate-200 p-5 shadow-xs">
          <div className="mb-4">
            <h3 className="text-sm font-semibold text-slate-800">Visit Trend (6 months)</h3>
            <p className="text-xs text-slate-400">Completed vs pending — real data</p>
          </div>
          {trend.length === 0 && !loading ? (
            <p className="py-16 text-center text-sm text-slate-400">No visit data yet</p>
          ) : (
            <ResponsiveContainer width="100%" height={220}>
              <AreaChart data={trend}>
                <defs>
                  <linearGradient id="cg" x1="0" y1="0" x2="0" y2="1">
                    <stop offset="5%" stopColor="#14b8a6" stopOpacity={0.15} />
                    <stop offset="95%" stopColor="#14b8a6" stopOpacity={0} />
                  </linearGradient>
                  <linearGradient id="pg" x1="0" y1="0" x2="0" y2="1">
                    <stop offset="5%" stopColor="#6366f1" stopOpacity={0.15} />
                    <stop offset="95%" stopColor="#6366f1" stopOpacity={0} />
                  </linearGradient>
                </defs>
                <XAxis dataKey="month" tick={{ fontSize: 11, fill: "#94a3b8" }} axisLine={false} tickLine={false} />
                <YAxis tick={{ fontSize: 11, fill: "#94a3b8" }} axisLine={false} tickLine={false} />
                <Tooltip contentStyle={{ borderRadius: "8px", border: "1px solid #e2e8f0", fontSize: 12 }} />
                <Legend iconType="circle" iconSize={8} wrapperStyle={{ fontSize: 11 }} />
                <Area type="monotone" dataKey="completed" name="Completed" stroke="#14b8a6" strokeWidth={2} fill="url(#cg)" />
                <Area type="monotone" dataKey="pending" name="Pending" stroke="#6366f1" strokeWidth={2} fill="url(#pg)" />
              </AreaChart>
            </ResponsiveContainer>
          )}
        </div>

        <div className="rounded-2xl bg-white border border-slate-200 p-5 shadow-xs">
          <div className="mb-2">
            <h3 className="text-sm font-semibold text-slate-800">Customers by City</h3>
            <p className="text-xs text-slate-400">Total: {(stats?.totalCustomers ?? 0).toLocaleString()}</p>
          </div>
          {cityData.length === 0 ? (
            <p className="py-10 text-center text-sm text-slate-400">No city data yet</p>
          ) : (
            <>
              <ResponsiveContainer width="100%" height={160}>
                <PieChart>
                  <Pie data={cityData} cx="50%" cy="50%" innerRadius={45} outerRadius={70} paddingAngle={3} dataKey="value">
                    {cityData.map((e) => <Cell key={e.name} fill={e.color} />)}
                  </Pie>
                  <Tooltip formatter={(v) => [Number(v).toLocaleString(), "Customers"]} contentStyle={{ borderRadius: "8px", fontSize: 12 }} />
                </PieChart>
              </ResponsiveContainer>
              <div className="mt-3 space-y-1.5">
                {cityData.map((c) => (
                  <div key={c.name} className="flex items-center justify-between text-xs">
                    <div className="flex items-center gap-2">
                      <span className="h-2 w-2 rounded-full" style={{ backgroundColor: c.color }} />
                      <span className="text-slate-600">{c.name}</span>
                    </div>
                    <span className="font-semibold text-slate-800">{c.value.toLocaleString()}</span>
                  </div>
                ))}
              </div>
            </>
          )}
        </div>
      </div>

      {/* Recent Visits */}
      <div className="rounded-2xl bg-white border border-slate-200 shadow-xs overflow-hidden">
        <div className="flex items-center justify-between px-5 py-4 border-b border-slate-100">
          <h3 className="text-sm font-semibold text-slate-800">Recent Visits</h3>
          <a href="/visits" className="text-xs font-medium text-teal-600 hover:text-teal-700">View all →</a>
        </div>
        {loading ? (
          <div className="divide-y divide-slate-50">
            {[...Array(5)].map((_, i) => (
              <div key={i} className="flex items-center gap-4 px-5 py-3">
                <div className="h-8 w-8 rounded-full bg-slate-100 animate-pulse" />
                <div className="flex-1 space-y-1.5">
                  <div className="h-3 w-40 rounded bg-slate-100 animate-pulse" />
                  <div className="h-2.5 w-28 rounded bg-slate-100 animate-pulse" />
                </div>
                <div className="h-5 w-16 rounded-full bg-slate-100 animate-pulse" />
              </div>
            ))}
          </div>
        ) : (
          <div className="divide-y divide-slate-50">
            {visits.length === 0 && (
              <p className="px-5 py-8 text-center text-sm text-slate-400">No visits found</p>
            )}
            {visits.map((v) => (
              <div key={v.id} className="flex items-center gap-4 px-5 py-3 hover:bg-slate-50/50 transition-colors">
                <div className="flex h-8 w-8 shrink-0 items-center justify-center rounded-full bg-teal-100 text-xs font-bold text-teal-700">
                  {(v.customer?.name ?? "?")[0].toUpperCase()}
                </div>
                <div className="min-w-0 flex-1">
                  <p className="text-xs font-medium text-slate-800 truncate">{v.customer?.name ?? "Unknown"}</p>
                  <p className="text-xs text-slate-400 truncate">
                    {v.booker?.name} · {new Date(v.visitDate).toLocaleDateString()}
                  </p>
                </div>
                <VisitBadge status={v.status} />
              </div>
            ))}
          </div>
        )}
      </div>

      {/* Quick Actions */}
      <div className="rounded-2xl bg-gradient-to-br from-[#0f1e3c] to-[#1a3060] p-5">
        <h3 className="text-sm font-semibold text-white mb-4">Quick Actions</h3>
        <div className="grid grid-cols-2 sm:grid-cols-4 gap-3">
          {[
            { label: "Attendance", icon: Activity, href: "/attendance" },
            { label: "Leave Requests", icon: CalendarX, href: "/leaves" },
            { label: "Missed Visits", icon: FileText, href: "/missed-visits" },
            { label: "Live Map", icon: MapPin, href: "/location" },
          ].map((a) => (
            <a key={a.label} href={a.href}
              className="flex items-center gap-2.5 rounded-xl bg-white/10 px-4 py-3 text-sm font-medium text-white hover:bg-white/20 transition-colors">
              <a.icon className="h-4 w-4 text-teal-400" />
              {a.label}
            </a>
          ))}
        </div>
      </div>
    </div>
  );
}
