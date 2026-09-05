"use client";
import { useState } from "react";
import { Download, FileText } from "lucide-react";
import { Button } from "@/components/ui/button";
import { SectionCard } from "@/components/shared/ui-bits";

const REPORT_TYPES = [
  { type: "visits", label: "Visits Report", description: "All visit records with officer, customer, status, notes" },
  { type: "bookers", label: "Officers Report", description: "All sales officers with contact info and status" },
  { type: "customers", label: "Customers Report", description: "All schools/shops with city and priority" },
  { type: "attendance", label: "Attendance Report", description: "Day start/end records and absent entries" },
  { type: "leaves", label: "Leave Requests", description: "All leave applications with approval status" },
];

export default function ReportsPage() {
  const [from, setFrom] = useState(() => {
    const d = new Date();
    d.setDate(1);
    return d.toISOString().split("T")[0];
  });
  const [to, setTo] = useState(() => new Date().toISOString().split("T")[0]);
  const [loading, setLoading] = useState<string | null>(null);

  function download(type: string) {
    setLoading(type);
    const params = new URLSearchParams({ type, from, to });
    const url = `/api/v1/reports?${params}`;

    // Create anchor and trigger download
    const a = document.createElement("a");
    a.href = url;
    a.download = `${type}-export.csv`;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);

    setTimeout(() => setLoading(null), 2000);
  }

  return (
    <div className="max-w-3xl space-y-6 px-6 py-6 lg:px-8">
      <div>
        <h1 className="text-xl font-bold text-foreground">Export Data</h1>
        <p className="text-sm text-muted-foreground">Download CSV files for any report below. Use the date range to filter.</p>
      </div>

      <SectionCard title="Date Range" description="Applies to every report below">
        <div className="flex gap-4">
          <div>
            <label className="text-xs font-semibold text-muted-foreground block mb-1">From</label>
            <input
              type="date"
              value={from}
              onChange={(e) => setFrom(e.target.value)}
              className="rounded-xl border border-border bg-card px-3 py-2 text-sm text-foreground focus:outline-none focus:ring-2 focus:ring-primary/20 focus:border-primary"
            />
          </div>
          <div>
            <label className="text-xs font-semibold text-muted-foreground block mb-1">To</label>
            <input
              type="date"
              value={to}
              onChange={(e) => setTo(e.target.value)}
              className="rounded-xl border border-border bg-card px-3 py-2 text-sm text-foreground focus:outline-none focus:ring-2 focus:ring-primary/20 focus:border-primary"
            />
          </div>
        </div>
      </SectionCard>

      <div className="space-y-3">
        {REPORT_TYPES.map(({ type, label, description }) => (
          <div
            key={type}
            className="surface flex items-center justify-between p-4 transition-all duration-200 hover:-translate-y-0.5 hover:shadow-elevated"
          >
            <div className="flex items-center gap-3">
              <span className="flex h-10 w-10 shrink-0 items-center justify-center rounded-xl bg-primary-soft text-primary">
                <FileText className="h-4 w-4" />
              </span>
              <div>
                <p className="font-semibold text-foreground text-sm">{label}</p>
                <p className="text-sm text-muted-foreground">{description}</p>
              </div>
            </div>
            <Button onClick={() => download(type)} disabled={loading === type} className="rounded-xl">
              <Download className="h-3.5 w-3.5" />
              {loading === type ? "Downloading…" : "Download CSV"}
            </Button>
          </div>
        ))}
      </div>
    </div>
  );
}
