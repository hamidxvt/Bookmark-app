"use client";

import { useEffect, useState } from "react";
import { MapPin, Clock, User, Search, RefreshCw, ChevronDown } from "lucide-react";

interface AdHocVisit {
  id: number;
  visitDate: string;
  status: string;
  notes: string | null;
  checkInAt: string | null;
  checkInLat: number | null;
  checkInLng: number | null;
  booker: { id: number; name: string; email: string };
  customer: {
    id: number; name: string; ownerPhone?: string; address: string | null;
    city: { name: string } | null;
  } | null;
}

function StatusBadge({ s }: { s: string }) {
  const u = s?.toUpperCase();
  if (u === "COMPLETED")   return <span className="rounded-full bg-emerald-100 text-emerald-700 px-2 py-0.5 text-xs font-medium">Completed</span>;
  if (u === "IN_PROGRESS") return <span className="rounded-full bg-amber-100 text-amber-700 px-2 py-0.5 text-xs font-medium">In Progress</span>;
  if (u === "CANCELLED")   return <span className="rounded-full bg-red-100 text-red-700 px-2 py-0.5 text-xs font-medium">Cancelled</span>;
  return <span className="rounded-full bg-blue-100 text-blue-700 px-2 py-0.5 text-xs font-medium">Planned</span>;
}

export default function AdHocVisitsPage() {
  const [visits,  setVisits]  = useState<AdHocVisit[]>([]);
  const [loading, setLoading] = useState(true);
  const [search,  setSearch]  = useState("");
  const [filter,  setFilter]  = useState("all");

  async function load() {
    setLoading(true);
    try {
      const params = new URLSearchParams();
      if (filter !== "all") params.set("filter", filter);
      const res = await fetch(`/api/v1/adhoc-visits?${params}`).then(r => r.json());
      setVisits(res.data ?? []);
    } catch (err) { console.error(err); }
    finally { setLoading(false); }
  }

  useEffect(() => { load(); }, [filter]);

  const filtered = visits.filter(v =>
    !search ||
    v.booker?.name?.toLowerCase().includes(search.toLowerCase()) ||
    v.customer?.name?.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="space-y-5">
      <div>
        <h1 className="text-2xl font-bold text-slate-900">Ad-hoc Visits</h1>
        <p className="text-sm text-slate-500 mt-0.5">Unplanned visits created by officers in the field</p>
      </div>

      <div className="flex flex-wrap items-center gap-2">
        <div className="relative flex-1 min-w-48 max-w-sm">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
          <input value={search} onChange={e => setSearch(e.target.value)} placeholder="Search officer or customer…"
            className="w-full rounded-xl border border-slate-200 bg-white pl-9 pr-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-red-500/20 focus:border-red-500 transition" />
        </div>
        <div className="flex rounded-xl border border-slate-200 overflow-hidden text-xs font-medium">
          {["all", "today"].map(opt => (
            <button key={opt} onClick={() => setFilter(opt)}
              className={`px-3 py-2 ${filter === opt ? "bg-[#C8102E] text-white" : "bg-white text-slate-600 hover:bg-slate-50"}`}>
              {opt === "all" ? "All" : "Today"}
            </button>
          ))}
        </div>
        <button onClick={load} disabled={loading}
          className="flex items-center gap-1.5 rounded-xl border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50">
          <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} /> Refresh
        </button>
      </div>

      {/* Stats */}
      <div className="grid grid-cols-3 gap-3">
        {[
          { label: "Total Ad-hoc",  value: filtered.length },
          { label: "Completed",     value: filtered.filter(v => v.status === "COMPLETED").length },
          { label: "In Progress",   value: filtered.filter(v => v.status === "IN_PROGRESS").length },
        ].map(s => (
          <div key={s.label} className="rounded-xl bg-white border border-slate-200 p-4 shadow-xs">
            <p className="text-2xl font-bold text-slate-900 tabular-nums">{s.value}</p>
            <p className="text-xs text-slate-500 mt-0.5">{s.label}</p>
          </div>
        ))}
      </div>

      <div className="rounded-2xl bg-white border border-slate-200 shadow-xs overflow-hidden">
        <table className="w-full text-sm">
          <thead>
            <tr className="border-b border-slate-100 bg-slate-50/50">
              {["#", "Customer", "Officer", "City", "Date", "Status", "GPS"].map(h => (
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
            {!loading && filtered.length === 0 && (
              <tr><td colSpan={7} className="px-4 py-10 text-center text-sm text-slate-400">
                No ad-hoc visits found. Officers create these from the mobile app during field visits.
              </td></tr>
            )}
            {!loading && filtered.map((v, i) => (
              <tr key={v.id} className="hover:bg-slate-50/50 transition-colors">
                <td className="px-4 py-3 text-xs text-slate-400">{i + 1}</td>
                <td className="px-4 py-3">
                  <p className="text-xs font-semibold text-slate-800">{v.customer?.name ?? "Unknown"}</p>
                  {v.customer?.ownerPhone && <p className="text-xs text-slate-400">{v.customer.ownerPhone}</p>}
                </td>
                <td className="px-4 py-3 text-xs text-slate-600">{v.booker?.name ?? "—"}</td>
                <td className="px-4 py-3 text-xs text-slate-500">{v.customer?.city?.name ?? "—"}</td>
                <td className="px-4 py-3 text-xs text-slate-500 whitespace-nowrap">
                  {new Date(v.visitDate).toLocaleDateString("en-PK", { day: "2-digit", month: "short", year: "numeric" })}
                </td>
                <td className="px-4 py-3"><StatusBadge s={v.status} /></td>
                <td className="px-4 py-3">
                  {v.checkInLat && v.checkInLng ? (
                    <a href={`https://maps.google.com/?q=${v.checkInLat},${v.checkInLng}`} target="_blank" rel="noreferrer"
                      className="flex items-center gap-1 text-xs text-red-600 hover:underline">
                      <MapPin className="h-3 w-3" /> Map
                    </a>
                  ) : <span className="text-xs text-slate-300">—</span>}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="border-t border-slate-100 px-4 py-3">
          <p className="text-xs text-slate-500">Showing {filtered.length} ad-hoc visits</p>
        </div>
      </div>
    </div>
  );
}
