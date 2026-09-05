"use client";
import { useState } from "react";
import { Clock, Loader2, Play, RefreshCw } from "lucide-react";
import { SectionCard } from "@/components/shared/ui-bits";
import { Button } from "@/components/ui/button";
import { cn } from "@/lib/utils";

const JOBS = [
  { job: "plan_visits", label: "Plan Tomorrow's Visits", description: "Assigns 7 customers to each active officer for tomorrow", cron: "Daily · 12:00 AM" },
  { job: "mark_absent", label: "Mark Absent (No Day Start)", description: "Auto-marks officers who didn't start their day as absent", cron: "Daily · 11:00 PM" },
  { job: "payroll", label: "Process Payroll Deductions", description: "Calculates and logs performance deductions for today", cron: "Daily · 11:59 PM" },
  { job: "sample_reminders", label: "Sample Recovery Reminders", description: "Sends reminders for samples distributed 10 or 20 days ago", cron: "Daily · 8:00 AM" },
  { job: "all", label: "Run All Jobs", description: "Runs all 4 scheduled jobs at once", cron: "On demand" },
];

export default function SchedulerPage() {
  const [loading, setLoading] = useState<string | null>(null);
  const [results, setResults] = useState<Record<string, { ok: boolean; text: string }>>({});

  async function runJob(job: string) {
    if (!confirm(`Run job: ${job}?`)) return;
    setLoading(job);

    try {
      const res = await fetch(`/api/v1/scheduler?job=${job}`, { method: "POST" });
      const json = await res.json();
      setResults((prev) => ({ ...prev, [job]: json.success ? { ok: true, text: "Done" } : { ok: false, text: json.error ?? "Failed" } }));
    } catch (e: any) {
      setResults((prev) => ({ ...prev, [job]: { ok: false, text: e.message } }));
    } finally {
      setLoading(null);
    }
  }

  return (
    <div className="space-y-6 px-6 py-6 lg:px-8">
      <SectionCard
        title="Scheduled Jobs"
        description={`${JOBS.length - 1} jobs registered — these run automatically, use this panel to trigger them manually`}
        action={
          <Button variant="outline" className="rounded-xl" onClick={() => runJob("all")} disabled={loading !== null}>
            <RefreshCw className="mr-2 h-4 w-4" /> Run all
          </Button>
        }
      >
        <div className="mb-6 flex items-start gap-2 rounded-xl border border-info/25 bg-info/10 px-4 py-3 text-sm text-info-foreground">
          <Clock className="mt-0.5 h-4 w-4 shrink-0 text-info" />
          <p>
            <strong className="font-semibold">Auto schedule:</strong> Visit planning @ 12:00 AM · Absent marking @ 11:00 PM ·
            Payroll @ 11:59 PM · Sample reminders @ 8:00 AM
          </p>
        </div>

        <ul className="grid gap-4 md:grid-cols-2">
          {JOBS.map(({ job, label, description, cron }) => {
            const result = results[job];
            return (
              <li
                key={job}
                className={cn(
                  "rounded-2xl border p-5 transition-shadow hover:shadow-card",
                  job === "all" ? "border-primary/30 bg-primary-soft" : "border-border/70 bg-card",
                )}
              >
                <div className="flex items-start justify-between gap-3">
                  <div>
                    <p className="font-semibold text-foreground">{label}</p>
                    <p className="mt-1 flex items-center gap-1.5 text-xs text-muted-foreground">
                      <Clock className="h-3.5 w-3.5" /> {cron}
                    </p>
                    <p className="mt-1 text-xs text-muted-foreground">{description}</p>
                  </div>
                </div>
                <div className="mt-4 flex items-center justify-between">
                  <span
                    className={cn(
                      "text-xs font-medium",
                      result ? (result.ok ? "text-success" : "text-destructive") : "text-muted-foreground",
                    )}
                  >
                    {result ? result.text : "Not run this session"}
                  </span>
                  <Button
                    size="sm"
                    className="rounded-xl"
                    disabled={loading !== null}
                    onClick={() => runJob(job)}
                  >
                    {loading === job ? (
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
            );
          })}
        </ul>
      </SectionCard>
    </div>
  );
}
