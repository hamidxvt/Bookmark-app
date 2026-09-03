import { createFileRoute } from "@tanstack/react-router";
import { Clock, Loader2, Play, RefreshCw } from "lucide-react";
import { useState } from "react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { SectionCard, StatusPill } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";

export const Route = createFileRoute("/run-schedulers")({
  head: () => ({
    meta: [
      { title: "Run Schedulers — Bookmark Field Force Manager" },
      { name: "description", content: "Trigger nightly jobs for visit planning, payroll sync and attendance rollups." },
      { property: "og:title", content: "Run Schedulers — Bookmark Field Force Manager" },
      { property: "og:description", content: "Trigger and monitor background scheduler jobs." },
    ],
  }),
  component: SchedulersPage,
});

const jobs = [
  { id: "visit-plan", name: "Visit Plan Generator", cron: "Daily · 02:00 PKT", last: "Today 02:00", status: "active" },
  { id: "attendance", name: "Attendance Rollup", cron: "Daily · 23:30 PKT", last: "Yesterday 23:30", status: "active" },
  { id: "payroll", name: "Payroll Sync", cron: "Monthly · 1st 04:00", last: "01 Mar 04:00", status: "idle" },
  { id: "geofence", name: "Geofence Audit", cron: "Hourly", last: "35 min ago", status: "active" },
  { id: "notify", name: "Notification Digest", cron: "Daily · 08:00 PKT", last: "Today 08:00", status: "active" },
  { id: "cleanup", name: "Stale GPS Cleanup", cron: "Weekly · Sun 01:00", last: "Sun 01:00", status: "offline" },
];

function SchedulersPage() {
  const [running, setRunning] = useState<string | null>(null);

  const run = (id: string, name: string) => {
    setRunning(id);
    setTimeout(() => {
      setRunning(null);
      toast.success(`${name} completed`, { description: "Job finished without errors." });
    }, 1100);
  };

  return (
    <AppShell title="Run Schedulers" subtitle="Background jobs powering the field operations engine">
      <SectionCard
        title="Scheduled jobs"
        description="6 jobs registered"
        action={
          <Button
            variant="outline"
            className="rounded-xl"
            onClick={() => toast.success("All jobs queued", { description: "Running in sequence." })}
          >
            <RefreshCw className="mr-2 h-4 w-4" /> Run all
          </Button>
        }
      >
        <ul className="grid gap-4 md:grid-cols-2">
          {jobs.map((j) => (
            <li key={j.id} className="rounded-2xl border border-border/70 p-5 transition-shadow hover:shadow-card">
              <div className="flex items-start justify-between gap-3">
                <div>
                  <p className="font-semibold">{j.name}</p>
                  <p className="mt-1 flex items-center gap-1.5 text-xs text-muted-foreground">
                    <Clock className="h-3.5 w-3.5" /> {j.cron}
                  </p>
                </div>
                <StatusPill value={j.status} />
              </div>
              <div className="mt-4 flex items-center justify-between">
                <span className="text-xs text-muted-foreground">Last run: {j.last}</span>
                <Button
                  size="sm"
                  className="rounded-xl"
                  disabled={running !== null}
                  onClick={() => run(j.id, j.name)}
                >
                  {running === j.id ? (
                    <>
                      <Loader2 className="mr-2 h-3.5 w-3.5 animate-spin" /> Running
                    </>
                  ) : (
                    <>
                      <Play className="mr-2 h-3.5 w-3.5" /> Run now
                    </>
                  )}
                </Button>
              </div>
            </li>
          ))}
        </ul>
      </SectionCard>
    </AppShell>
  );
}
