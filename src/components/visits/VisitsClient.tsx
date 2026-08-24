"use client";

import { useEffect, useState } from "react";
import { Search, RefreshCw, Eye } from "lucide-react";

interface Visit {
  id: number;
  visitDate: string;
  status: string;
  notes: string | null;
  visitReport: string | null;
  booker: { id: number; name: string; email: string };
  customer: { id: number; name: string; customerType: string; address: string | null };
}

function VisitBadge({ status }: { status: string }) {
  const s = (status ?? "").toUpperCase();
  const map: Record<string, { label: string; style: string }> = {
    COMPLETED: { label: "Completed", style: "bg-emerald-100 text-emerald-700" },
    PENDING: { label: "Pending", style: "bg-blue-100 text-blue-700" },
    CANCELLED: { label: "Cancelled", style: "bg-red-100 text-red-700" },
    IN_PROGRESS: { label: "In Progress", style: "bg-amber-100 text-amber-700" },
  };
  const key = Object.keys(map).find(k => s.includes(k)) ?? "PENDING";
  return <span className={`rounded-full px-2 py-0.5 text-xs font-medium ${map[key].style}`}>{map[key].label}</span>;
}

export default function VisitsClient() {
  const [rows, setRows] = useState<Visit[]>([]);
  const [total, setTotal] = useState(0);
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState("");

  async function load() {
    setLoading(true);
    try {
      const res = await fetch("/api/v1/visits?length=100").then(r => r.json());
      if (res.success) {
        setRows(res.data?.data ?? []);
        setTotal(res.data?.recordsTotal ?? 0);
      }
    } catch (e) { console.error(e); }
    finally { setLoading(false); }
  }

  useEffect(() => { load(); }, []);

  const filtered = rows.filter(r =>
    !search ||
    r.customer?.name?.toLowerCase().includes(search.toLowerCase()) ||
    r.booker?.name?.toLowerCase().includes(search.toLowerCase())
  );

  const completed = rows.filter(r => r.status === "COMPLETED").length;

  return (
    <div className="space-y-4">
      <div className="flex flex-col gap-3 sm:flex-row sm:items-center sm:justify-between">
        <div className="relative flex-1 max-w-sm">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
          <input value={search} onChange={e => setSearch(e.target.value)} placeholder="Search by customer or booker…"
            className="w-full rounded-lg border border-slate-200 bg-white pl-9 pr-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-[#C8102E]/20 focus:border-[#C8102E] transition" />
        </div>
        <button onClick={load} disabled={loading} className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50">
          <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} />
          Refresh
        </button>
      </div>

      <div className="grid grid-cols-3 gap-3">
        {[
          { label: "Total Visits", value: total, color: "text-blue-700" },
          { label: "Completed", value: completed, color: "text-emerald-700" },
          { label: "Pending / Other", value: total - completed, color: "text-amber-700" },
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
              {["#", "Customer", "Booker", "Date", "Status", "Notes", ""].map(h => (
                <th key={h} className="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wide text-slate-500">{h}</th>
              ))}
            </tr>
          </thead>
          <tbody className="divide-y divide-slate-50">
            {loading && [...Array(6)].map((_, i) => (
              <tr key={i}>{[...Array(7)].map((_, j) => (
                <td key={j} className="px-4 py-3"><div className="h-4 rounded bg-slate-100 animate-pulse" style={{ width: j === 1 ? "140px" : "80px" }} /></td>
              ))}</tr>
            ))}
            {!loading && filtered.length === 0 && (
              <tr><td colSpan={7} className="px-4 py-8 text-center text-sm text-slate-400">No visits found</td></tr>
            )}
            {!loading && filtered.map((r, i) => (
              <tr key={r.id} className="hover:bg-slate-50/50 transition-colors">
                <td className="px-4 py-3 text-slate-400 text-xs">{i + 1}</td>
                <td className="px-4 py-3 text-xs font-medium text-slate-800 max-w-[160px] truncate">
                  {r.customer?.name ?? "—"}
                  <span className="ml-1 text-slate-400">{r.customer?.customerType === "SCHOOL" ? "🏫" : "📚"}</span>
                </td>
                <td className="px-4 py-3 text-xs text-slate-500 max-w-[120px] truncate">{r.booker?.name ?? "—"}</td>
                <td className="px-4 py-3 text-xs text-slate-500 whitespace-nowrap">{new Date(r.visitDate).toLocaleDateString()}</td>
                <td className="px-4 py-3"><VisitBadge status={r.status} /></td>
                <td className="px-4 py-3 text-xs text-slate-400 max-w-[140px] truncate">{r.notes ?? r.visitReport ?? "—"}</td>
                <td className="px-4 py-3">
                  <button className="p-1.5 rounded-lg text-slate-400 hover:text-[#C8102E] hover:bg-red-50 transition-colors"><Eye className="h-4 w-4" /></button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="border-t border-slate-100 px-4 py-3">
          <p className="text-xs text-slate-500">Showing {filtered.length} of {total.toLocaleString()} visits · Live data</p>
        </div>
      </div>
    </div>
  );
}
