"use client";

import { useEffect, useRef, useState } from "react";
import { DollarSign, RefreshCw, Download, ChevronDown } from "lucide-react";

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
  const [open, setOpen] = useState(false);
  const ref = useRef<HTMLDivElement>(null);
  useEffect(() => {
    const h = (e: MouseEvent) => { if (ref.current && !ref.current.contains(e.target as Node)) setOpen(false); };
    document.addEventListener("mousedown", h); return () => document.removeEventListener("mousedown", h);
  }, []);
  function exportCSV() {
    const headers = ["Officer", "Present Days", "Basic Salary", "Rate/Visit", "Completed Visits", "Performance", "Total Pay"];
    const csv = [headers.join(","), ...rows.map(r => [
      `"${r.bookerName}"`, r.presentDays, r.basicSalary, r.ratesPerVisit, r.completedVisits,
      r.performanceEarned, r.totalPay,
    ].join(","))].join("\n");
    const blob = new Blob([csv], { type: "text/csv" });
    const a = document.createElement("a"); a.href = URL.createObjectURL(blob);
    a.download = `payroll_${year}_${month}.csv`; a.click(); setOpen(false);
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
    win.document.close(); win.focus(); win.print(); setOpen(false);
  }
  return (
    <div className="relative" ref={ref}>
      <button onClick={() => setOpen(!open)} className="flex items-center gap-1.5 rounded-xl border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50">
        <Download className="h-3.5 w-3.5" /> Export <ChevronDown className="h-3 w-3" />
      </button>
      {open && (
        <div className="absolute right-0 top-full mt-1 w-36 rounded-xl border border-slate-200 bg-white shadow-lg z-10">
          <button onClick={exportCSV} className="w-full px-3 py-2 text-left text-xs text-slate-700 hover:bg-slate-50 rounded-t-xl">Export CSV</button>
          <button onClick={exportPDF} className="w-full px-3 py-2 text-left text-xs text-slate-700 hover:bg-slate-50 rounded-b-xl">Export PDF</button>
        </div>
      )}
    </div>
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

  const totalPayroll   = records.reduce((s, r) => s + r.totalPay, 0);
  const totalBasic     = records.reduce((s, r) => s + r.basicSalary, 0);
  const totalPerf      = records.reduce((s, r) => s + r.performanceEarned, 0);

  return (
    <div className="space-y-5">
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-3">
        <div>
          <h2 className="text-xl font-bold text-slate-900">Payroll</h2>
          <p className="text-sm text-slate-500 mt-0.5">Basic salary + performance-based earnings per officer</p>
        </div>
        <div className="flex items-center gap-2 flex-wrap">
          <div className="relative">
            <select value={month} onChange={e => setMonth(Number(e.target.value))}
              className="rounded-xl border border-slate-200 bg-white px-3 pr-8 py-2 text-sm appearance-none cursor-pointer focus:outline-none">
              {MONTHS.map((m, i) => <option key={m} value={i + 1}>{m}</option>)}
            </select>
            <ChevronDown className="pointer-events-none absolute right-2.5 top-1/2 -translate-y-1/2 h-3.5 w-3.5 text-slate-400" />
          </div>
          <div className="relative">
            <select value={year} onChange={e => setYear(Number(e.target.value))}
              className="rounded-xl border border-slate-200 bg-white px-3 pr-8 py-2 text-sm appearance-none cursor-pointer focus:outline-none">
              {[2024, 2025, 2026].map(y => <option key={y} value={y}>{y}</option>)}
            </select>
            <ChevronDown className="pointer-events-none absolute right-2.5 top-1/2 -translate-y-1/2 h-3.5 w-3.5 text-slate-400" />
          </div>
          <button onClick={load} disabled={loading} className="flex items-center gap-1.5 rounded-xl border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50">
            <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} /> Recalculate
          </button>
          <ExportMenu rows={records} month={month} year={year} />
        </div>
      </div>

      {/* Summary KPIs */}
      <div className="grid grid-cols-3 gap-4">
        {[
          { label: "Total Payroll",     value: pkr(totalPayroll), color: "text-[#C8102E]" },
          { label: "Base Salaries",     value: pkr(totalBasic),   color: "text-slate-700" },
          { label: "Performance Bonus", value: pkr(totalPerf),    color: "text-emerald-700" },
        ].map(s => (
          <div key={s.label} className="rounded-2xl border border-slate-200 bg-white p-4 shadow-xs">
            <DollarSign className="h-4 w-4 text-slate-300 mb-2" />
            <p className={`text-xl font-bold tabular-nums ${s.color}`}>{s.value}</p>
            <p className="text-xs text-slate-500 mt-0.5">{s.label}</p>
          </div>
        ))}
      </div>

      <div className="rounded-2xl border border-slate-200 bg-white shadow-xs overflow-hidden">
        <div className="px-5 py-3 border-b border-slate-100">
          <p className="text-sm font-semibold text-slate-800">
            {meta?.monthName ?? MONTHS[month - 1]} {year} — Officer Breakdown
          </p>
        </div>
        <table className="w-full text-sm">
          <thead>
            <tr className="border-b border-slate-100 bg-slate-50/50">
              {["#", "Officer", "Present Days", "Basic Salary", "Visits Done", "Performance", "Total Pay"].map(h => (
                <th key={h} className="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wide text-slate-500">{h}</th>
              ))}
            </tr>
          </thead>
          <tbody className="divide-y divide-slate-50">
            {loading && [...Array(5)].map((_, i) => (
              <tr key={i}>{[...Array(7)].map((_, j) => (
                <td key={j} className="px-4 py-3"><div className="h-4 rounded bg-slate-100 animate-pulse" style={{ width: j === 1 ? "140px" : "80px" }} /></td>
              ))}</tr>
            ))}
            {!loading && records.length === 0 && (
              <tr><td colSpan={7} className="px-4 py-10 text-center text-sm text-slate-400">No active officers with salary data</td></tr>
            )}
            {!loading && records.map((r, i) => (
              <tr key={r.bookerId} className="hover:bg-slate-50/50 transition-colors">
                <td className="px-4 py-3 text-xs text-slate-400">{i + 1}</td>
                <td className="px-4 py-3 text-xs font-semibold text-slate-800">{r.bookerName}</td>
                <td className="px-4 py-3 text-xs text-slate-600">{r.presentDays} days</td>
                <td className="px-4 py-3 text-xs text-slate-600">{pkr(r.basicSalary)}</td>
                <td className="px-4 py-3 text-xs text-slate-600">{r.completedVisits}</td>
                <td className="px-4 py-3 text-xs text-emerald-700 font-medium">{pkr(r.performanceEarned)}</td>
                <td className="px-4 py-3 text-xs font-bold text-[#C8102E]">{pkr(r.totalPay)}</td>
              </tr>
            ))}
            {!loading && records.length > 0 && (
              <tr className="bg-slate-50 border-t-2 border-slate-200">
                <td colSpan={2} className="px-4 py-3 text-xs font-bold text-slate-700">TOTAL</td>
                <td className="px-4 py-3" />
                <td className="px-4 py-3 text-xs font-bold text-slate-700">{pkr(totalBasic)}</td>
                <td className="px-4 py-3" />
                <td className="px-4 py-3 text-xs font-bold text-emerald-700">{pkr(totalPerf)}</td>
                <td className="px-4 py-3 text-xs font-bold text-[#C8102E]">{pkr(totalPayroll)}</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}
