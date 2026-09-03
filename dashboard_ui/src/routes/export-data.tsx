import { createFileRoute } from "@tanstack/react-router";
import { Download, FileSpreadsheet, FileText, Loader2 } from "lucide-react";
import { useState } from "react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { SectionCard } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";

export const Route = createFileRoute("/export-data")({
  head: () => ({
    meta: [
      { title: "Export Data — Bookmark Field Force Manager" },
      { name: "description", content: "Export visits, customers, attendance and payroll data as CSV or Excel." },
      { property: "og:title", content: "Export Data — Bookmark Field Force Manager" },
      { property: "og:description", content: "Export operational data as CSV or Excel." },
    ],
  }),
  component: ExportPage,
});

const datasets = ["Visits", "Customers", "Officers", "Attendance", "Samples", "Payroll", "Products"];

function ExportPage() {
  const [dataset, setDataset] = useState("Visits");
  const [range, setRange] = useState("This month");
  const [busy, setBusy] = useState<string | null>(null);

  const run = (format: string) => {
    setBusy(format);
    setTimeout(() => {
      setBusy(null);
      toast.success(`${dataset} exported`, { description: `${range} · ${format.toUpperCase()} ready to download.` });
    }, 900);
  };

  return (
    <AppShell title="Export Data" subtitle="Generate operational data extracts">
      <div className="mx-auto max-w-3xl space-y-6">
        <SectionCard title="Build an export" description="Choose the dataset and reporting window">
          <div className="grid gap-5 sm:grid-cols-2">
            <div className="space-y-2">
              <p className="text-sm font-medium">Dataset</p>
              <Select value={dataset} onValueChange={setDataset}>
                <SelectTrigger className="h-11 rounded-xl">
                  <SelectValue />
                </SelectTrigger>
                <SelectContent>
                  {datasets.map((d) => (
                    <SelectItem key={d} value={d}>
                      {d}
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>
            <div className="space-y-2">
              <p className="text-sm font-medium">Date range</p>
              <Select value={range} onValueChange={setRange}>
                <SelectTrigger className="h-11 rounded-xl">
                  <SelectValue />
                </SelectTrigger>
                <SelectContent>
                  {["Today", "This week", "This month", "This quarter", "This year"].map((r) => (
                    <SelectItem key={r} value={r}>
                      {r}
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>
          </div>

          <div className="mt-7 flex flex-wrap gap-3">
            <Button className="rounded-xl" onClick={() => run("csv")} disabled={busy !== null}>
              {busy === "csv" ? <Loader2 className="mr-2 h-4 w-4 animate-spin" /> : <FileText className="mr-2 h-4 w-4" />}
              Export CSV
            </Button>
            <Button variant="outline" className="rounded-xl" onClick={() => run("xlsx")} disabled={busy !== null}>
              {busy === "xlsx" ? (
                <Loader2 className="mr-2 h-4 w-4 animate-spin" />
              ) : (
                <FileSpreadsheet className="mr-2 h-4 w-4" />
              )}
              Export Excel
            </Button>
          </div>
        </SectionCard>

        <SectionCard title="Recent exports">
          <ul className="space-y-3">
            {[
              ["Visits — This month", "CSV · 1.2 MB", "10 min ago"],
              ["Attendance — This week", "XLSX · 340 KB", "Yesterday"],
              ["Customers — This year", "CSV · 4.8 MB", "2 days ago"],
            ].map(([a, b, c]) => (
              <li key={a} className="flex items-center justify-between rounded-xl border border-border/70 p-4">
                <div>
                  <p className="text-sm font-medium">{a}</p>
                  <p className="text-xs text-muted-foreground">{b}</p>
                </div>
                <div className="flex items-center gap-3">
                  <span className="text-xs text-muted-foreground">{c}</span>
                  <Button
                    size="icon"
                    variant="ghost"
                    className="rounded-xl"
                    onClick={() => toast.success("Download started")}
                  >
                    <Download className="h-4 w-4" />
                  </Button>
                </div>
              </li>
            ))}
          </ul>
        </SectionCard>
      </div>
    </AppShell>
  );
}
