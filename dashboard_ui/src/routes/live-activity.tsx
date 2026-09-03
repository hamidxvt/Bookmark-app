import { createFileRoute, useNavigate } from "@tanstack/react-router";
import { Activity, AlertTriangle, MapPin, PauseCircle, PowerOff, Users } from "lucide-react";
import { useState } from "react";

import { AppShell } from "@/components/app/AppShell";
import { SectionCard, StatCard, StatusPill, TableSkeleton } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Progress } from "@/components/ui/progress";
import { toLatLng } from "@/lib/geo";
import { initials, officers } from "@/lib/mock-data";


export const Route = createFileRoute("/live-activity")({
  head: () => ({
    meta: [
      { title: "Live Activity — Bookmark Field Force Manager" },
      { name: "description", content: "Live monitoring of every Bookmark field officer and their daily progress." },
      { property: "og:title", content: "Live Activity — Bookmark Field Force Manager" },
      { property: "og:description", content: "Live monitoring of officers, progress and alerts." },
    ],
  }),
  component: LiveActivityPage,
});

function LiveActivityPage() {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);

  const counts = {
    total: officers.length,
    active: officers.filter((o) => o.status === "active").length,
    idle: officers.filter((o) => o.status === "idle").length,
    offline: officers.filter((o) => o.status === "offline").length,
  };

  const refresh = () => {
    setLoading(true);
    setTimeout(() => setLoading(false), 600);
  };

  return (
    <AppShell title="Live Activity" subtitle="Everything happening in the field, right now" onRefresh={refresh}>
      <div className="space-y-6">
        <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-5">
          <StatCard label="Total Officers" value={String(counts.total)} icon={<Users className="h-5 w-5" />} to="/sales-team" />
          <StatCard label="Active" value={String(counts.active)} icon={<Activity className="h-5 w-5" />} onClick={refresh} />
          <StatCard label="Idle" value={String(counts.idle)} icon={<PauseCircle className="h-5 w-5" />} onClick={refresh} />
          <StatCard label="Offline" value={String(counts.offline)} icon={<PowerOff className="h-5 w-5" />} onClick={refresh} />
          <StatCard label="Alerts" value="2" icon={<AlertTriangle className="h-5 w-5" />} to="/support-tickets" />
        </div>

        <SectionCard
          title="Officer Feed"
          description="Click any officer to open their full profile"
          action={
            <Button variant="outline" className="rounded-xl" onClick={() => navigate({ to: "/live-location" })}>
              Open Live Location
            </Button>
          }
        >
          {loading ? (
            <TableSkeleton rows={5} />
          ) : (
            <div className="grid gap-4 md:grid-cols-2 xl:grid-cols-3">
              {officers.map((o) => (
                <button
                  key={o.id}
                  onClick={() => navigate({ to: "/officer/$id", params: { id: o.id } })}
                  className="rounded-2xl border border-border/70 p-4 text-left transition-all duration-300 hover:-translate-y-1 hover:border-transparent hover:shadow-elevated"
                >
                  <div className="flex items-start gap-3">
                    <span className="flex h-11 w-11 items-center justify-center rounded-xl bg-navy text-sm font-bold text-navy-foreground">
                      {initials(o.name)}
                    </span>
                    <div className="min-w-0 flex-1">
                      <p className="truncate text-sm font-semibold">{o.name}</p>
                      <p className="truncate text-xs text-muted-foreground">{o.location}</p>
                    </div>
                    <StatusPill value={o.status} />
                  </div>

                  <div className="mt-3 flex items-center justify-between text-xs text-muted-foreground">
                    <span>Today's progress</span>
                    <span className="font-semibold text-foreground">
                      {o.todayVisits}/{o.targetVisits}
                    </span>
                  </div>
                  <Progress value={(o.todayVisits / o.targetVisits) * 100} className="mt-2 h-1.5" />

                  <div className="mt-4 rounded-xl bg-muted/60 p-3 text-xs">
                    <p className="flex items-center gap-1.5 font-medium">
                      <MapPin className="h-3.5 w-3.5 text-primary" /> {o.location}
                    </p>
                    <p className="mt-1 font-mono text-[11px] text-muted-foreground">
                      {toLatLng(o.city, o.pos).lat.toFixed(4)}, {toLatLng(o.city, o.pos).lng.toFixed(4)}
                    </p>
                  </div>
                  <p className="mt-2 text-[11px] text-muted-foreground">Last update {o.lastSeen} · open live map →</p>

                </button>
              ))}
            </div>
          )}
        </SectionCard>
      </div>
    </AppShell>
  );
}
