import { createFileRoute, Link, useNavigate } from "@tanstack/react-router";
import {
  Activity,
  Boxes,
  Building2,
  CalendarCheck,
  CalendarPlus,
  CheckCircle2,
  Clock,
  Download,
  FileText,
  MapPinned,
  Package,
  Route as RouteIcon,
  UserPlus,
  UserRound,
  Users,
} from "lucide-react";
import { useState } from "react";
import {
  CartesianGrid,
  Cell,
  Legend,
  Line,
  LineChart,
  Pie,
  PieChart,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis,
} from "recharts";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { EmptyState, SectionCard, StatCard, StatusPill } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Progress } from "@/components/ui/progress";
import {
  cityDistribution,
  dashboardStats,
  initials,
  officers,
  recentActivity,
  visitTrend,
} from "@/lib/mock-data";
import { cn } from "@/lib/utils";

export const Route = createFileRoute("/dashboard")({
  head: () => ({
    meta: [
      { title: "Dashboard — Bookmark Field Force Manager" },
      { name: "description", content: "Real-time overview of Bookmark field operations, visits and officers." },
      { property: "og:title", content: "Dashboard — Bookmark Field Force Manager" },
      { property: "og:description", content: "Real-time overview of field operations across Pakistan." },
    ],
  }),
  component: DashboardPage,
});

const iconMap: Record<string, React.ElementType> = {
  users: Users,
  activity: Activity,
  building: Building2,
  route: RouteIcon,
  calendar: CalendarCheck,
  package: Package,
  clock: Clock,
  check: CheckCircle2,
};

const activityIcon: Record<string, React.ElementType> = {
  customer: UserRound,
  visit: RouteIcon,
  sample: Package,
  request: CheckCircle2,
  attendance: CalendarCheck,
};

const ranges = ["Today", "Week", "Month", "Year"] as const;
const pieColors = [
  "var(--color-chart-1)",
  "var(--color-chart-2)",
  "var(--color-chart-3)",
  "var(--color-chart-4)",
  "var(--color-chart-5)",
];

function DashboardPage() {
  const navigate = useNavigate();
  const [range, setRange] = useState<(typeof ranges)[number]>("Week");
  const [seed, setSeed] = useState(0);
  const liveOfficers = officers.slice(0, 8);

  const quickActions = [
    { label: "Add Customer", icon: UserPlus, action: () => navigate({ to: "/add-customer" }) },
    { label: "Add Officer", icon: Users, action: () => navigate({ to: "/sales-team" }) },
    { label: "Create Visit", icon: CalendarPlus, action: () => navigate({ to: "/visits" }) },
    { label: "View Map", icon: MapPinned, action: () => navigate({ to: "/live-location" }) },
    { label: "Reports", icon: FileText, action: () => navigate({ to: "/export-data" }) },
    { label: "Attendance", icon: CalendarCheck, action: () => navigate({ to: "/attendance" }) },
  ];

  return (
    <AppShell
      title="Dashboard"
      subtitle="Real-time overview of field operations"
      onRefresh={() => setSeed((s) => s + 1)}
    >
      <div key={seed} className="space-y-6">
        <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-4">
          {dashboardStats.map((s) => {
            const Icon = iconMap[s.icon] ?? Boxes;
            return (
              <StatCard
                key={s.key}
                label={s.label}
                value={s.value}
                trend={s.trend}
                to={s.to}
                icon={<Icon className="h-5 w-5" />}
              />
            );
          })}
        </div>

        <div className="grid gap-6 xl:grid-cols-[1.6fr_1fr]">
          <SectionCard
            title="Visit Performance"
            description="Completed vs pending visits"
            action={
              <div className="flex rounded-xl bg-muted p-1">
                {ranges.map((r) => (
                  <button
                    key={r}
                    onClick={() => setRange(r)}
                    className={cn(
                      "rounded-lg px-3 py-1.5 text-xs font-semibold transition-all duration-200",
                      range === r ? "bg-card text-foreground shadow-card" : "text-muted-foreground hover:text-foreground",
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
                <LineChart data={visitTrend[range]} margin={{ left: -18, right: 8, top: 8 }}>
                  <CartesianGrid strokeDasharray="4 4" stroke="var(--color-border)" vertical={false} />
                  <XAxis dataKey="label" tickLine={false} axisLine={false} fontSize={12} stroke="var(--color-muted-foreground)" />
                  <YAxis tickLine={false} axisLine={false} fontSize={12} stroke="var(--color-muted-foreground)" />
                  <Tooltip
                    contentStyle={{
                      borderRadius: 14,
                      border: "1px solid var(--color-border)",
                      background: "var(--color-card)",
                      fontSize: 12,
                    }}
                  />
                  <Legend iconType="circle" wrapperStyle={{ fontSize: 12, paddingTop: 8 }} />
                  <Line
                    type="monotone"
                    dataKey="completed"
                    name="Completed Visits"
                    stroke="var(--color-chart-1)"
                    strokeWidth={3}
                    dot={{ r: 3 }}
                    activeDot={{ r: 5 }}
                  />
                  <Line
                    type="monotone"
                    dataKey="pending"
                    name="Pending Visits"
                    stroke="var(--color-chart-3)"
                    strokeWidth={3}
                    strokeDasharray="6 4"
                    dot={{ r: 3 }}
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
                    data={cityDistribution}
                    dataKey="customers"
                    nameKey="city"
                    innerRadius={62}
                    outerRadius={92}
                    paddingAngle={3}
                    stroke="none"
                  >
                    {cityDistribution.map((_, i) => (
                      <Cell key={i} fill={pieColors[i % pieColors.length]} />
                    ))}
                  </Pie>
                  <Tooltip
                    contentStyle={{
                      borderRadius: 14,
                      border: "1px solid var(--color-border)",
                      background: "var(--color-card)",
                      fontSize: 12,
                    }}
                  />
                </PieChart>
              </ResponsiveContainer>
            </div>
            <ul className="mt-4 space-y-2">
              {cityDistribution.map((c, i) => (
                <li key={c.city} className="flex items-center gap-2 text-sm">
                  <span className="h-2.5 w-2.5 rounded-full" style={{ background: pieColors[i % pieColors.length] }} />
                  <span className="flex-1 text-muted-foreground">{c.city}</span>
                  <span className="font-semibold">{c.customers.toLocaleString()}</span>
                </li>
              ))}
            </ul>
          </SectionCard>
        </div>

        <SectionCard
          title="Live Field Officers"
          description="Officers currently in the field"
          action={
            <Button asChild className="rounded-xl">
              <Link to="/live-location">
                <MapPinned className="mr-2 h-4 w-4" /> Open Live Tracking
              </Link>
            </Button>
          }
        >
          <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-4">
            {liveOfficers.map((o) => (
              <Link
                key={o.id}
                to="/officer/$id"
                params={{ id: o.id }}
                className="rounded-2xl border border-border/70 p-4 transition-all duration-300 hover:-translate-y-1 hover:border-transparent hover:shadow-elevated"
              >
                <div className="flex items-center gap-3">
                  <span className="relative flex h-11 w-11 items-center justify-center rounded-full bg-navy text-sm font-bold text-navy-foreground">
                    {initials(o.name)}
                    <span
                      className={cn(
                        "absolute -bottom-0.5 -right-0.5 h-3.5 w-3.5 rounded-full border-2 border-card",
                        o.status === "active" ? "bg-success" : o.status === "idle" ? "bg-warning" : "bg-muted-foreground",
                      )}
                    />
                  </span>
                  <div className="min-w-0">
                    <p className="truncate text-sm font-semibold">{o.name}</p>
                    <p className="text-xs text-muted-foreground">{o.city}</p>
                  </div>
                </div>
                <div className="mt-3 flex items-center justify-between">
                  <StatusPill value={o.status} />
                  <span className="text-xs font-medium text-muted-foreground">
                    {o.todayVisits}/{o.targetVisits} visits
                  </span>
                </div>
                <Progress value={(o.todayVisits / o.targetVisits) * 100} className="mt-3 h-1.5" />
              </Link>
            ))}
          </div>
        </SectionCard>

        <div className="grid gap-6 xl:grid-cols-[1.4fr_1fr]">
          <SectionCard title="Recent Activity" description="Latest events across the field force">
            {recentActivity.length === 0 ? (
              <EmptyState title="No activity yet" />
            ) : (
              <ol className="relative space-y-5 pl-7">
                <span className="absolute left-[11px] top-2 h-[calc(100%-1rem)] w-px bg-border" />
                {recentActivity.map((a, i) => {
                  const Icon = activityIcon[a.type] ?? Activity;
                  return (
                    <li key={i} className="relative">
                      <span className="absolute -left-7 flex h-6 w-6 items-center justify-center rounded-full bg-primary-soft text-primary ring-4 ring-card">
                        <Icon className="h-3 w-3" />
                      </span>
                      <p className="text-sm font-semibold text-foreground">{a.title}</p>
                      <p className="text-sm text-muted-foreground">{a.detail}</p>
                      <p className="mt-0.5 text-xs text-muted-foreground/80">
                        {a.by} · {a.time}
                      </p>
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
                  <span className="text-sm font-medium">{q.label}</span>
                </button>
              ))}
            </div>
            <Button
              variant="outline"
              className="mt-4 w-full rounded-xl"
              onClick={() => toast.success("Export queued", { description: "Daily summary will arrive by email." })}
            >
              <Download className="mr-2 h-4 w-4" /> Export daily summary
            </Button>
          </SectionCard>
        </div>
      </div>
    </AppShell>
  );
}
