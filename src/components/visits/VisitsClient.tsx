"use client";

import { useEffect, useState } from "react";
import Link from "next/link";
import { Plus, Search, RefreshCw, Eye } from "lucide-react";

function stripHtml(s: string) {
  return (s ?? "").replace(/<[^>]+>/g, " ").replace(/\s+/g, " ").trim();
}

function VisitBadge({ html }: { html: string }) {
  const text = stripHtml(html).toLowerCase();
  const isCompleted = text.includes("completed");
  const isCancelled = text.includes("cancel");
  const style = isCompleted
    ? "bg-emerald-100 text-emerald-700"
    : isCancelled
    ? "bg-red-100 text-red-700"
    : "bg-blue-100 text-blue-700";
  const label = isCompleted ? "Completed" : isCancelled ? "Cancelled" : "Pending";
  return <span className={`rounded-full px-2 py-0.5 text-xs font-medium ${style}`}>{label}</span>;
}

export default function VisitsClient() {
  const [rows, setRows] = useState<any[]>([]);
  const [total, setTotal] = useState(0);
  const [completed, setCompleted] = useState(0);
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState("");

  async function load() {
    setLoading(true);
    try {
      const res = await fetch("/api/v1/visits?length=100").then(r => r.json());
      if (res.success) {
        const data: any[] = res.data?.data ?? [];
        setRows(data);
        setTotal(res.data?.recordsTotal ?? 0);
        const done = data.filter(r => stripHtml(r[4] ?? "").toLowerCase().includes("completed")).length;
        setCompleted(done);
      }
    } catch (e) { console.error(e); }
    finally { setLoading(false); }
  }

  useEffect(() => { load(); }, []);

  const filtered = rows.filter(r => {
    const customer = stripHtml(r[2] ?? "").toLowerCase();
    return !search || customer.includes(search.toLowerCase());
  });

  return (
    <div className="space-y-4">
      <div className="flex flex-col gap-3 sm:flex-row sm:items-center sm:justify-between">
        <div className="relative flex-1 max-w-sm">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
          <input value={search} onChange={e => setSearch(e.target.value)} placeholder="Search by customer…"
            className="w-full rounded-lg border border-slate-200 bg-white pl-9 pr-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-teal-500/20 focus:border-teal-500 transition" />
        </div>
        <div className="flex gap-2">
          <button onClick={load} disabled={loading} className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50">
            <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} />
          </button>
          <Link href="/visits/add" className="flex items-center gap-2 rounded-lg bg-[#0f1e3c] px-4 py-2 text-sm font-semibold text-white hover:bg-[#1a3060] transition-colors">
            <Plus className="h-4 w-4" /> Schedule Visit
          </Link>
        </div>
      </div>

      <div className="grid grid-cols-3 gap-3">
        {[
          { label: "Total Visits", value: total, color: "text-blue-700" },
          { label: "Completed", value: completed, color: "text-emerald-700" },
          { label: "Pending", value: total - completed, color: "text-amber-700" },
        ].map(s => (
          <div key={s.label} className="rounded-xl bg-white border border-slate-200 p-4 shadow-xs">
            <p className={`text-2xl font-bold ${s.color} tabular-nums`}>{s.value.toLocaleString()}</p>
            <p className="text-xs text-slate-500 mt-0.5">{s.label}</p>
          </div>
        ))}
      </div>

      <div className="rounded-2xl bg-white border border-slate-200 shadow-xs overflow-hidden">
        <table className="w-full text-sm">
          <thead>
            <tr className="border-b border-slate-100 bg-slate-50/50">
              {["#", "Customer", "Date / Time", "Status", "Report", "Actions"].map(h => (
                <th key={h} className="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wide text-slate-500">{h}</th>
              ))}
            </tr>
          </thead>
          <tbody className="divide-y divide-slate-50">
            {loading && [...Array(6)].map((_, i) => (
              <tr key={i}>{[...Array(6)].map((_, j) => (
                <td key={j} className="px-4 py-3"><div className="h-4 rounded bg-slate-100 animate-pulse" style={{ width: j === 1 ? "150px" : "80px" }} /></td>
              ))}</tr>
            ))}
            {!loading && filtered.map((r: any, i: number) => {
              // columns: 0=avatar, 1=booker, 2=customer, 3=date, 4=status, 5=report, 6=action
              const customer = stripHtml(r[2] ?? "");
              const date = stripHtml(r[3] ?? "");
              const statusHtml = r[4] ?? "";
              const report = stripHtml(r[5] ?? "").slice(0, 60);

              return (
                <tr key={i} className="hover:bg-slate-50/50 transition-colors">
                  <td className="px-4 py-3 text-slate-400 text-xs">{i + 1}</td>
                  <td className="px-4 py-3 font-medium text-slate-800 text-xs max-w-[180px] truncate">{customer}</td>
                  <td className="px-4 py-3 text-slate-500 text-xs">{date}</td>
                  <td className="px-4 py-3"><VisitBadge html={statusHtml} /></td>
                  <td className="px-4 py-3 text-slate-400 text-xs max-w-[160px] truncate">{report || "—"}</td>
                  <td className="px-4 py-3">
                    <button className="p-1.5 rounded-lg text-slate-400 hover:text-teal-600 hover:bg-teal-50 transition-colors"><Eye className="h-4 w-4" /></button>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
        <div className="border-t border-slate-100 px-4 py-3">
          <p className="text-xs text-slate-500">Showing {filtered.length} of {total.toLocaleString()} visits · Live from staging</p>
        </div>
      </div>
    </div>
  );
}
