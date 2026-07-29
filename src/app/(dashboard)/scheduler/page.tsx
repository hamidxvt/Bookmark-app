"use client";
import { useState } from "react";
import { Zap } from "lucide-react";

const JOBS = [
  { job: "plan_visits", label: "Plan Tomorrow's Visits", description: "Assigns 7 customers to each active officer for tomorrow" },
  { job: "mark_absent", label: "Mark Absent (No Day Start)", description: "Auto-marks officers who didn't start their day as absent" },
  { job: "payroll", label: "Process Payroll Deductions", description: "Calculates and logs performance deductions for today" },
  { job: "sample_reminders", label: "Sample Recovery Reminders", description: "Sends reminders for samples distributed 10 or 20 days ago" },
  { job: "all", label: "Run All Jobs", description: "Runs all 4 scheduled jobs at once" },
];

export default function SchedulerPage() {
  const [loading, setLoading] = useState<string | null>(null);
  const [results, setResults] = useState<Record<string, string>>({});

  async function runJob(job: string) {
    if (!confirm(`Run job: ${job}?`)) return;
    setLoading(job);

    try {
      const res = await fetch(`/api/v1/scheduler?job=${job}`, { method: "POST" });
      const json = await res.json();
      setResults((prev) => ({ ...prev, [job]: json.success ? "✅ Done" : `❌ ${json.error}` }));
    } catch (e: any) {
      setResults((prev) => ({ ...prev, [job]: `❌ ${e.message}` }));
    } finally {
      setLoading(null);
    }
  }

  return (
    <div className="p-8 max-w-2xl mx-auto">
      <h1 className="text-2xl font-bold mb-1">Scheduled Jobs</h1>
      <p className="text-sm text-gray-500 mb-2">
        These jobs run automatically on schedule. Use this panel to trigger them manually if needed.
      </p>
      <div className="mb-6 p-3 bg-blue-50 border border-blue-200 rounded-lg text-sm text-blue-700">
        <strong>Auto schedule:</strong> Visit planning @ 12:00 AM · Absent marking @ 11:00 PM · Payroll @ 11:59 PM · Sample reminders @ 8:00 AM
      </div>

      <div className="space-y-3">
        {JOBS.map(({ job, label, description }) => (
          <div
            key={job}
            className={`flex items-center justify-between p-4 rounded-xl border transition ${
              job === "all" ? "bg-blue-50 border-blue-200" : "bg-white border-gray-200"
            }`}
          >
            <div>
              <p className="font-semibold text-gray-800">{label}</p>
              <p className="text-sm text-gray-500">{description}</p>
              {results[job] && <p className="text-sm mt-1 font-medium">{results[job]}</p>}
            </div>
            <button
              onClick={() => runJob(job)}
              disabled={loading !== null}
              className={`flex items-center gap-2 px-4 py-2 text-white text-sm font-medium rounded-lg disabled:opacity-50 transition ${
                job === "all" ? "bg-blue-600 hover:bg-blue-700" : "bg-gray-700 hover:bg-gray-800"
              }`}
            >
              <Zap size={14} />
              {loading === job ? "Running..." : "Run"}
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}
