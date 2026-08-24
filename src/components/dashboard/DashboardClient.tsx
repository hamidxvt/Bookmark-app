"use client";

import { useEffect, useState } from "react";
import {
  Users, UserCheck, ClipboardList, Package,
  AlertCircle, Activity, MapPin, RefreshCw,
  CalendarX, FileText, TrendingUp, ArrowUpRight,
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

const STATUS_STYLES: Record<string, { bg: string; text: string; dot: string }> = {
  COMPLETED:   { bg: "bg-emerald-50",  text: "text-emerald-700",  dot: "bg-emerald-500"  },
  PENDING:     { bg: "bg-blue-50",     text: "text-blue-700",     dot: "bg-blue-500"     },
  CANCELLED:   { bg: "bg-red-50",      text: "text-red-700",      dot: "bg-red-500"      },
  IN_PROGRESS: { bg: "bg-amber-50",    text: "text-amber-700",    dot: "bg-amber-500"    },
};

function VisitBadge({ status }: { status: string }) {
  const key = Object.keys(STATUS_STYLES).find(k => status?.toUpperCase().includes(k)) ?? "PENDING";
  const s = STATUS_STYLES[key];
  const label = key.replace("_", " ").charAt(0) + key.replace("_", " ").slice(1).toLowerCase();
  return (
    <span className={`inline-flex items-center gap-1.5 rounded-full px-2.5 py-0.5 text-xs font-medium ${s.bg} ${s.text}`}>
      <span className={`h-1.5 w-1.5 rounded-full ${s.dot}`} />
      {label}
    </span>
  );
}

function KpiCard({ icon: Icon, label, value, sub, href }: {
  icon: React.ElementType; label: string; value: number | string; sub?: string; href?: string;
}) {
  const formatted = typeof value === "number" ? value.toLocaleString() : value;
  const content = (
    <div className="group relative overflow-hidden rounded-2xl bg-white border border-slate-100 p-5 shadow-sm hover:shadow-md hover:border-red-100 transition-all duration-200">
      <div className="flex items-start justify-between">
        <div className="flex h-10 w-10 items-center justify-center rounded-xl bg-red-50 text-[#C8102E]">
          <Icon className="h-5 w-5" />
        </div>
        {href && (
          <ArrowUpRight className="h-4 w-4 text-slate-300 group-hover:text-[#C8102E] transition-colors" />
        )}
      </div>
      <p className="mt-4 text-2xl font-bold text-slate-900 tabular-nums tracking-tight">{formatted}</p>
      <p className="mt-0.5 text-sm font-medium text-slate-500">{label}</p>
      {sub && <p className="mt-0.5 text-xs text-slate-400">{sub}</p>}
      {/* Brand accent bottom bar */}
      <div className="absolute bottom-0 left-0 h-0.5 w-0 bg-[#C8102E] group-hover:w-full transition-all duration-300" />
    </div>
  );
  return href ? <a href={href}>{content}</a> : content;
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
    { icon: UserCheck,   label: "Field Officers",    value: stats?.totalBookers      ?? "—", sub: "Approved & active",   href: "/bookers"       },
    { icon: Users,       label: "Total Customers",   value: stats?.totalCustomers    ?? "—", sub: "Schools & bookshops", href: "/customers"     },
    { icon: ClipboardList,label:"Total Visits",      value: stats?.totalVisits       ?? "—", sub: "All time",            href: "/visits"        },
    { icon: Activity,    label: "Today's Visits",    value: stats?.visitsToday       ?? "—", sub: "Scheduled today",     href: "/visits"        },
    { icon: Package,     label: "Products",          value: stats?.totalProducts     ?? "—", sub: "In catalog",          href: "/products"      },
    { icon: AlertCircle, label: "Pending Requests",  value: stats?.pendingRequests   ?? "—", sub: "Need attention",      href: "/requests"      },
    { icon: CalendarX,   label: "Pending Leaves",    value: stats?.pendingLeaves     ?? "—", sub: "Awaiting review",     href: "/leaves"        },
    { icon: FileText,    label: "Missed Reviews",    value: stats?.pendingMissedVisits?? "—", sub: "Pending approval",   href: "/missed-visits" },
  ];

  return (
    <div className="space-y-6">

      {/* ── Page Header ─────────────────────────────────────────── */}
      <div className="flex items-center justify-between">
        <div>
          <div className="flex items-center gap-2.5">
            <TrendingUp className="h-5 w-5 text-[#C8102E]" />
            <h2 className="text-xl font-bold text-slate-900 tracking-tight">Dashboard</h2>
          </div>
          <p className="text-sm text-slate-400 mt-0.5 ml-7">
            Live data · Last refreshed {timeAgo(lastRefresh)}
          </p>
        </div>
        <button
          onClick={load}
          disabled={loading}
          className="flex items-center gap-2 rounded-xl border border-slate-200 bg-white px-4 py-2 text-xs font-semibold text-slate-600 hover:bg-slate-50 hover:border-slate-300 transition-all disabled:opacity-50 shadow-sm"
        >
          <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin text-[#C8102E]" : ""}`} />
          Refresh
        </button>
      </div>

      {/* ── KPI Grid ─────────────────────────────────────────────── */}
      {loading && !stats ? (
        <div className="grid grid-cols-2 gap-4 sm:grid-cols-4 lg:grid-cols-4">
          {[...Array(8)].map((_, i) => (
            <div key={i} className="rounded-2xl bg-white border border-slate-100 p-5 animate-pulse">
              <div className="h-10 w-10 rounded-xl bg-slate-100" />
              <div className="mt-4 h-7 w-16 rounded-md bg-slate-100" />
              <div className="mt-1 h-4 w-24 rounded bg-slate-100" />
            </div>
          ))}
        </div>
      ) : (
        <div className="grid grid-cols-2 gap-4 sm:grid-cols-4 lg:grid-cols-4">
          {KPI_CARDS.map((card) => (
            <KpiCard key={card.label} {...card} />
          ))}
        </div>
      )}

      {/* ── Charts ───────────────────────────────────────────────── */}
      <div className="grid grid-cols-1 gap-4 lg:grid-cols-3">
        {/* Visit Trend */}
        <div className="col-span-2 rounded-2xl bg-white border border-slate-100 p-5 shadow-sm">
          <div className="mb-4 flex items-start justify-between">
            <div>
              <h3 className="text-sm font-semibold text-slate-800">Visit Trend</h3>
              <p className="text-xs text-slate-400 mt-0.5">6-month completed vs pending</p>
            </div>
            <span className="rounded-full bg-red-50 px-2.5 py-1 text-[10px] font-semibold text-[#C8102E] uppercase tracking-wider">
              Live
            </span>
          </div>
          {trend.length === 0 && !loading ? (
            <p className="py-16 text-center text-sm text-slate-400">No visit data yet</p>
          ) : (
            <ResponsiveContainer width="100%" height={220}>
              <AreaChart data={trend}>
                <defs>
                  <linearGradient id="cg" x1="0" y1="0" x2="0" y2="1">
                    <stop offset="5%" stopColor="#C8102E" stopOpacity={0.15} />
                    <stop offset="95%" stopColor="#C8102E" stopOpacity={0} />
                  </linearGradient>
                  <linearGradient id="pg" x1="0" y1="0" x2="0" y2="1">
                    <stop offset="5%" stopColor="#9B0B22" stopOpacity={0.12} />
                    <stop offset="95%" stopColor="#9B0B22" stopOpacity={0} />
                  </linearGradient>
                </defs>
                <XAxis dataKey="month" tick={{ fontSize: 11, fill: "#94a3b8" }} axisLine={false} tickLine={false} />
                <YAxis tick={{ fontSize: 11, fill: "#94a3b8" }} axisLine={false} tickLine={false} />
                <Tooltip
                  contentStyle={{ borderRadius: "10px", border: "1px solid #fecdd3", fontSize: 12, boxShadow: "0 4px 12px rgba(0,0,0,0.08)" }}
                  cursor={{ stroke: "#C8102E", strokeWidth: 1, strokeDasharray: "4 4" }}
                />
                <Legend iconType="circle" iconSize={8} wrapperStyle={{ fontSize: 11 }} />
                <Area type="monotone" dataKey="completed" name="Completed" stroke="#C8102E" strokeWidth={2.5} fill="url(#cg)" dot={false} activeDot={{ r: 4, fill: "#C8102E" }} />
                <Area type="monotone" dataKey="pending"   name="Pending"   stroke="#9B0B22" strokeWidth={2} fill="url(#pg)"   dot={false} activeDot={{ r: 4, fill: "#9B0B22" }} />
              </AreaChart>
            </ResponsiveContainer>
          )}
        </div>

        {/* City Breakdown */}
        <div className="rounded-2xl bg-white border border-slate-100 p-5 shadow-sm">
          <div className="mb-2">
            <h3 className="text-sm font-semibold text-slate-800">Customers by City</h3>
            <p className="text-xs text-slate-400 mt-0.5">
              {(stats?.totalCustomers ?? 0).toLocaleString()} total
            </p>
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
                  <Tooltip
                    formatter={(v) => [Number(v).toLocaleString(), "Customers"]}
                    contentStyle={{ borderRadius: "10px", fontSize: 12 }}
                  />
                </PieChart>
              </ResponsiveContainer>
              <div className="mt-3 space-y-2">
                {cityData.map((c) => (
                  <div key={c.name} className="flex items-center justify-between">
                    <div className="flex items-center gap-2">
                      <span className="h-2 w-2 rounded-full shrink-0" style={{ backgroundColor: c.color }} />
                      <span className="text-xs text-slate-500 truncate max-w-[100px]">{c.name}</span>
                    </div>
                    <span className="text-xs font-bold text-slate-800">{c.value.toLocaleString()}</span>
                  </div>
                ))}
              </div>
            </>
          )}
        </div>
      </div>

      {/* ── Recent Visits ─────────────────────────────────────────── */}
      <div className="rounded-2xl bg-white border border-slate-100 shadow-sm overflow-hidden">
        <div className="flex items-center justify-between px-5 py-4 border-b border-slate-50">
          <h3 className="text-sm font-semibold text-slate-800">Recent Visits</h3>
          <a href="/visits" className="flex items-center gap-1 text-xs font-semibold text-[#C8102E] hover:text-[#9B0B22] transition-colors">
            View all <ArrowUpRight className="h-3 w-3" />
          </a>
        </div>
        {loading ? (
          <div className="divide-y divide-slate-50">
            {[...Array(5)].map((_, i) => (
              <div key={i} className="flex items-center gap-4 px-5 py-3.5">
                <div className="h-9 w-9 rounded-full bg-slate-100 animate-pulse" />
                <div className="flex-1 space-y-2">
                  <div className="h-3 w-36 rounded bg-slate-100 animate-pulse" />
                  <div className="h-2.5 w-24 rounded bg-slate-100 animate-pulse" />
                </div>
                <div className="h-5 w-20 rounded-full bg-slate-100 animate-pulse" />
              </div>
            ))}
          </div>
        ) : (
          <div className="divide-y divide-slate-50">
            {visits.length === 0 && (
              <p className="px-5 py-10 text-center text-sm text-slate-400">No visits found</p>
            )}
            {visits.map((v) => (
              <div key={v.id} className="flex items-center gap-4 px-5 py-3.5 hover:bg-slate-50/60 transition-colors">
                <div className="flex h-9 w-9 shrink-0 items-center justify-center rounded-full bg-red-50 text-sm font-bold text-[#C8102E] border border-red-100">
                  {(v.customer?.name ?? "?")[0].toUpperCase()}
                </div>
                <div className="min-w-0 flex-1">
                  <p className="text-sm font-medium text-slate-800 truncate">{v.customer?.name ?? "Unknown"}</p>
                  <p className="text-xs text-slate-400 truncate">
                    {v.booker?.name} · {new Date(v.visitDate).toLocaleDateString("en-PK")}
                  </p>
                </div>
                <VisitBadge status={v.status} />
              </div>
            ))}
          </div>
        )}
      </div>

      {/* ── Quick Links ───────────────────────────────────────────── */}
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-4">
        <div className="rounded-2xl bg-gradient-to-br from-[#C8102E]/5 to-[#9B0B22]/8 border border-red-100 p-5">
          <h3 className="text-sm font-semibold text-slate-900 mb-3 flex items-center gap-2">
            <MapPin className="h-4 w-4 text-[#C8102E]" />
            Field Operations
          </h3>
          <div className="grid grid-cols-2 gap-2">
            {[
              { label: "Live Location",  icon: MapPin,      href: "/location"      },
              { label: "Attendance",     icon: Activity,    href: "/attendance"    },
              { label: "Missed Visits",  icon: FileText,    href: "/missed-visits" },
              { label: "Payroll",        icon: Users,       href: "/payroll"       },
            ].map((a) => (
              <a key={a.label} href={a.href}
                className="flex items-center gap-2.5 rounded-xl bg-white px-3 py-2.5 text-xs font-medium text-slate-700 hover:text-[#C8102E] hover:shadow-sm border border-white hover:border-red-100 transition-all shadow-sm">
                <a.icon className="h-3.5 w-3.5 text-[#C8102E]" />
                {a.label}
              </a>
            ))}
          </div>
        </div>

        <div className="rounded-2xl bg-slate-50/80 border border-slate-100 p-5">
          <h3 className="text-sm font-semibold text-slate-900 mb-3 flex items-center gap-2">
            <UserCheck className="h-4 w-4 text-slate-600" />
            Management
          </h3>
          <div className="grid grid-cols-2 gap-2">
            {[
              { label: "Officers",     icon: UserCheck,    href: "/bookers"          },
              { label: "Cities",       icon: MapPin,       href: "/locations/cities" },
              { label: "Customers",    icon: Users,        href: "/customers"        },
              { label: "Products",     icon: Package,      href: "/products"         },
            ].map((a) => (
              <a key={a.label} href={a.href}
                className="flex items-center gap-2.5 rounded-xl bg-white px-3 py-2.5 text-xs font-medium text-slate-600 hover:text-slate-900 hover:shadow-sm border border-slate-100 hover:border-slate-200 transition-all shadow-sm">
                <a.icon className="h-3.5 w-3.5 text-slate-400" />
                {a.label}
              </a>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
