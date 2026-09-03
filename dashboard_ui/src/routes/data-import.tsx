import { createFileRoute } from "@tanstack/react-router";
import { CheckCircle2, FileSpreadsheet, Upload, XCircle } from "lucide-react";
import { useRef, useState } from "react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { SectionCard, StatCard, StatusPill } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Progress } from "@/components/ui/progress";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";

export const Route = createFileRoute("/data-import")({
  head: () => ({
    meta: [
      { title: "Data Import — Bookmark Field Force Manager" },
      { name: "description", content: "Bulk import customers, officers, products and visit plans from CSV files." },
      { property: "og:title", content: "Data Import — Bookmark Field Force Manager" },
      { property: "og:description", content: "Bulk CSV import for customers, officers, products and visits." },
    ],
  }),
  component: DataImportPage,
});

type Job = { id: string; file: string; dataset: string; rows: number; status: "processing" | "completed" | "failed" };

function DataImportPage() {
  const [dataset, setDataset] = useState("customers");
  const [progress, setProgress] = useState(0);
  const [busy, setBusy] = useState(false);
  const [jobs, setJobs] = useState<Job[]>([
    { id: "IMP-1042", file: "customers-karachi.csv", dataset: "Customers", rows: 482, status: "completed" },
    { id: "IMP-1041", file: "products-2026.csv", dataset: "Products", rows: 128, status: "completed" },
    { id: "IMP-1040", file: "officers-lahore.csv", dataset: "Officers", rows: 16, status: "failed" },
  ]);
  const inputRef = useRef<HTMLInputElement>(null);

  const runImport = (file: File) => {
    setBusy(true);
    setProgress(8);
    const id = `IMP-${1043 + jobs.length}`;
    const timer = setInterval(() => {
      setProgress((p) => {
        if (p >= 100) {
          clearInterval(timer);
          setBusy(false);
          setJobs((prev) => [
            { id, file: file.name, dataset, rows: Math.max(1, Math.round(file.size / 120)), status: "completed" },
            ...prev,
          ]);
          toast.success("Import completed", { description: `${file.name} processed into ${dataset}.` });
          return 100;
        }
        return p + 12;
      });
    }, 250);
  };

  return (
    <AppShell title="Data Import" subtitle="Bulk load records from CSV">
      <div className="space-y-6">
        <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-3">
          <StatCard label="Imports Completed" value={String(jobs.filter((j) => j.status === "completed").length)} icon={<CheckCircle2 className="h-5 w-5" />} onClick={() => {}} />
          <StatCard label="Failed" value={String(jobs.filter((j) => j.status === "failed").length)} icon={<XCircle className="h-5 w-5" />} onClick={() => {}} />
          <StatCard label="Rows Processed" value={String(jobs.reduce((a, j) => a + j.rows, 0))} icon={<FileSpreadsheet className="h-5 w-5" />} onClick={() => {}} />
        </div>

        <SectionCard title="Upload File" description="CSV files up to 5 MB, first row must be a header">
          <div className="grid gap-4 sm:grid-cols-[220px_1fr]">
            <Select value={dataset} onValueChange={setDataset}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue /></SelectTrigger>
              <SelectContent>
                <SelectItem value="customers">Customers</SelectItem>
                <SelectItem value="officers">Officers</SelectItem>
                <SelectItem value="products">Products</SelectItem>
                <SelectItem value="visits">Visit plans</SelectItem>
              </SelectContent>
            </Select>
            <div
              onClick={() => inputRef.current?.click()}
              className="flex cursor-pointer flex-col items-center justify-center gap-2 rounded-2xl border border-dashed border-border p-8 text-center transition-colors hover:border-primary hover:bg-primary/[0.03]"
            >
              <Upload className="h-6 w-6 text-primary" />
              <p className="text-sm font-medium">Click to choose a CSV file</p>
              <p className="text-xs text-muted-foreground">Target dataset: {dataset}</p>
              <input
                ref={inputRef}
                type="file"
                accept=".csv"
                className="hidden"
                onChange={(e) => {
                  const file = e.target.files?.[0];
                  if (file) runImport(file);
                  e.target.value = "";
                }}
              />
            </div>
          </div>
          {busy || progress === 100 ? (
            <div className="mt-5">
              <Progress value={progress} className="h-2" />
              <p className="mt-2 text-xs text-muted-foreground">{busy ? `Importing… ${progress}%` : "Import finished"}</p>
            </div>
          ) : null}
          <div className="mt-5 flex gap-2">
            <Button
              variant="outline"
              className="rounded-xl"
              onClick={() => {
                const blob = new Blob(["name,city,area,phone,email\n"], { type: "text/csv" });
                const url = URL.createObjectURL(blob);
                const a = document.createElement("a");
                a.href = url;
                a.download = `${dataset}-template.csv`;
                a.click();
                URL.revokeObjectURL(url);
                toast.success("Template downloaded");
              }}
            >
              Download template
            </Button>
          </div>
        </SectionCard>

        <SectionCard title="Import History" description={`${jobs.length} jobs`}>
          <div className="overflow-x-auto">
            <Table>
              <TableHeader>
                <TableRow>
                  {["Job", "File", "Dataset", "Rows", "Status", ""].map((h) => <TableHead key={h}>{h}</TableHead>)}
                </TableRow>
              </TableHeader>
              <TableBody>
                {jobs.map((j) => (
                  <TableRow key={j.id} className="transition-colors hover:bg-muted/50">
                    <TableCell className="font-medium">{j.id}</TableCell>
                    <TableCell>{j.file}</TableCell>
                    <TableCell>{j.dataset}</TableCell>
                    <TableCell>{j.rows}</TableCell>
                    <TableCell><StatusPill value={j.status === "processing" ? "in-progress" : j.status === "failed" ? "missed" : "completed"} /></TableCell>
                    <TableCell className="text-right">
                      <Button
                        variant="ghost"
                        className="rounded-xl text-xs"
                        onClick={() => toast.info(`${j.file}: ${j.rows} rows · ${j.status}`)}
                      >
                        View log
                      </Button>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </div>
        </SectionCard>
      </div>
    </AppShell>
  );
}
