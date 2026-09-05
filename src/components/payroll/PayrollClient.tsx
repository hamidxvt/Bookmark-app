"use client";

import { useEffect, useState } from "react";
import { DollarSign, RefreshCw, Download, TrendingUp, Wallet } from "lucide-react";
import { SectionCard, StatCard, EmptyState, TableSkeleton } from "@/components/shared/ui-bits";
import { Button } from "@/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { cn } from "@/lib/utils";

interface PayrollRecord {
  bookerId: number;
  bookerName: string;
  presentDays: number;
  basicSalary: number;
  ratesPerVisit: number;
  completedVisits: number;
  performanceEarned: number;
  totalPay: number;
}

function pkr(n: number) {
  return `Rs. ${Math.round(n).toLocaleString()}`;
}

function ExportMenu({ rows, month, year }: { rows: PayrollRecord[]; month: number; year: number }) {
  function exportCSV() {
    const headers = ["Officer", "Present Days", "Basic Salary", "Rate/Visit", "Completed Visits", "Performance", "Total Pay"];
    const csv = [headers.join(","), ...rows.map(r => [
      `"${r.bookerName}"`, r.presentDays, r.basicSalary, r.ratesPerVisit, r.completedVisits,
      r.performanceEarned, r.totalPay,
    ].join(","))].join("\n");
    const blob = new Blob([csv], { type: "text/csv" });
    const a = document.createElement("a"); a.href = URL.createObjectURL(blob);
    a.download = `payroll_${year}_${month}.csv`; a.click();
  }
  function exportPDF() {
    const win = window.open("", "_blank"); if (!win) return;
    win.document.write(`<html><head><title>Payroll</title>
      <style>body{font-family:Arial;font-size:12px}h1{color:#C8102E;font-size:16px}
      table{width:100%;border-collapse:collapse}th{background:#f8f9fa;padding:6px;border-bottom:2px solid #dee2e6;font-size:11px;text-align:left}
      td{padding:6px;border-bottom:1px solid #f0f0f0;font-size:11px}tfoot td{font-weight:bold;background:#f8f9fa}</style></head>
      <body><h1>Payroll Report — Month ${month}/${year}</h1><p style="color:#666">Exported: ${new Date().toLocaleString()}</p>
      <table><thead><tr><th>Officer</th><th>Days</th><th>Basic</th><th>Visits</th><th>Performance</th><th>Total</th></tr></thead>
      <tbody>${rows.map(r => `<tr><td>${r.bookerName}</td><td>${r.presentDays}</td><td>Rs.${r.basicSalary.toLocaleString()}</td><td>${r.completedVisits}</td><td>Rs.${r.performanceEarned.toLocaleString()}</td><td>Rs.${r.totalPay.toLocaleString()}</td></tr>`).join("")}</tbody>
      </table></body></html>`);
    win.document.close(); win.focus(); win.print();
  }
  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <Button variant="outline" className="rounded-xl">
          <Download className="mr-2 h-4 w-4" /> Export
        </Button>
      </DropdownMenuTrigger>
      <DropdownMenuContent align="end" className="rounded-xl">
        <DropdownMenuItem onClick={exportCSV}>Export CSV</DropdownMenuItem>
        <DropdownMenuItem onClick={exportPDF}>Export PDF</DropdownMenuItem>
      </DropdownMenuContent>
    </DropdownMenu>
  );
}

const MONTHS = ["January","February","March","April","May","June","July","August","September","October","November","December"];

export default function PayrollClient() {
  const [records, setRecords] = useState<PayrollRecord[]>([]);
  const [loading, setLoading] = useState(true);
  const [meta,    setMeta]    = useState<{ monthName: string } | null>(null);
  const now = new Date();
  const [month, setMonth] = useState(now.getMonth() + 1);
  const [year,  setYear]  = useState(now.getFullYear());

  async function load() {
    setLoading(true);
    try {
      const res = await fetch(`/api/v1/payroll?month=${month}&year=${year}`).then(r => r.json());
      if (res.success) { setRecords(res.data ?? []); setMeta(res.meta ?? null); }
    } catch (e) { console.error("[Payroll]", e); }
    finally { setLoading(false); }
  }

  useEffect(() => { load(); }, [month, year]);

  const totalPayroll = records.reduce((s, r) => s + r.totalPay, 0);
  const totalBasic   = records.reduce((s, r) => s + r.basicSalary, 0);
  const totalPerf    = records.reduce((s, r) => s + r.performanceEarned, 0);

  return (
    <div className="space-y-6 px-6 py-6 lg:px-8">
      {/* KPI row */}
      <div className="grid gap-4 sm:grid-cols-3">
        <StatCard label="Total Payroll" value={pkr(totalPayroll)} icon={<Wallet className="h-5 w-5" />} />
        <StatCard label="Base Salaries" value={pkr(totalBasic)} icon={<DollarSign className="h-5 w-5" />} />
        <StatCard label="Performance Bonus" value={pkr(totalPerf)} icon={<TrendingUp className="h-5 w-5" />} />
      </div>

      <SectionCard
        title={`${meta?.monthName ?? MONTHS[month - 1]} ${year} — Officer Breakdown`}
        description={`${records.length} officers`}
        action={
          <div className="flex flex-wrap items-center gap-2">
            <Select value={String(month)} onValueChange={v => setMonth(Number(v))}>
              <SelectTrigger className="h-10 w-36 rounded-xl"><SelectValue /></SelectTrigger>
              <SelectContent>
                {MONTHS.map((m, i) => <SelectItem key={m} value={String(i + 1)}>{m}</SelectItem>)}
              </SelectContent>
            </Select>
            <Select value={String(year)} onValueChange={v => setYear(Number(v))}>
              <SelectTrigger className="h-10 w-24 rounded-xl"><SelectValue /></SelectTrigger>
              <SelectContent>
                {[2024, 2025, 2026].map(y => <SelectItem key={y} value={String(y)}>{y}</SelectItem>)}
              </SelectContent>
            </Select>
            <button
              onClick={load}
              disabled={loading}
              className="flex h-10 w-10 items-center justify-center rounded-xl border border-border bg-card shadow-card transition hover:shadow-elevated"
              aria-label="Recalculate"
            >
              <RefreshCw className={cn("h-4 w-4 text-muted-foreground", loading && "animate-spin")} />
            </button>
            <ExportMenu rows={records} month={month} year={year} />
          </div>
        }
      >
        {loading ? (
          <TableSkeleton />
        ) : records.length === 0 ? (
          <EmptyState title="No active officers with salary data" />
        ) : (
          <div className="overflow-x-auto">
            <Table>
              <TableHeader>
                <TableRow>
                  {["#", "Officer", "Present Days", "Basic Salary", "Visits Done", "Performance", "Total Pay"].map(h => (
                    <TableHead key={h}>{h}</TableHead>
                  ))}
                </TableRow>
              </TableHeader>
              <TableBody>
                {records.map((r, i) => (
                  <TableRow key={r.bookerId} className="transition-colors hover:bg-muted/50">
                    <TableCell className="text-muted-foreground">{i + 1}</TableCell>
                    <TableCell className="font-medium">{r.bookerName}</TableCell>
                    <TableCell className="text-muted-foreground">{r.presentDays} days</TableCell>
                    <TableCell className="text-muted-foreground">{pkr(r.basicSalary)}</TableCell>
                    <TableCell className="text-muted-foreground">{r.completedVisits}</TableCell>
                    <TableCell className="font-medium text-success">{pkr(r.performanceEarned)}</TableCell>
                    <TableCell className="font-bold text-primary">{pkr(r.totalPay)}</TableCell>
                  </TableRow>
                ))}
                <TableRow className="bg-muted/40">
                  <TableCell colSpan={2} className="font-bold text-foreground">TOTAL</TableCell>
                  <TableCell />
                  <TableCell className="font-bold text-foreground">{pkr(totalBasic)}</TableCell>
                  <TableCell />
                  <TableCell className="font-bold text-success">{pkr(totalPerf)}</TableCell>
                  <TableCell className="font-bold text-primary">{pkr(totalPayroll)}</TableCell>
                </TableRow>
              </TableBody>
            </Table>
          </div>
        )}
      </SectionCard>
    </div>
  );
}
