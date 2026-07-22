"use client";

import { useEffect, useState } from "react";
import Link from "next/link";
import { Plus, Search, Eye, Pencil, MapPin, RefreshCw } from "lucide-react";

function stripHtml(s: string) {
  return (s ?? "").replace(/<[^>]+>/g, " ").replace(/\s+/g, " ").trim();
}

function StatusDot({ html }: { html: string }) {
  const text = stripHtml(html).toLowerCase();
  const isActive = text.includes("active") && !text.includes("not active");
  const isIdle = text.includes("idle");
  const color = isActive ? "bg-emerald-500" : isIdle ? "bg-amber-500" : "bg-slate-400";
  const label = isActive ? "Active" : isIdle ? "Idle" : "Offline";
  const textColor = isActive ? "text-emerald-600" : isIdle ? "text-amber-600" : "text-slate-500";
  return (
    <span className={`flex items-center gap-1.5 text-xs font-medium ${textColor}`}>
      <span className={`h-2 w-2 rounded-full ${color}`} />
      {label}
    </span>
  );
}

function ApprovalBadge({ html }: { html: string }) {
  const text = stripHtml(html).toLowerCase();
  const isApproved = text.includes("approved") && !text.includes("not");
  const isPending = text.includes("pending");
  const style = isApproved
    ? "bg-emerald-100 text-emerald-800 border-emerald-200"
    : isPending
    ? "bg-amber-100 text-amber-800 border-amber-200"
    : "bg-red-100 text-red-800 border-red-200";
  const label = isApproved ? "Approved" : isPending ? "Pending" : "Not Approved";
  return <span className={`inline-flex rounded-full border px-2 py-0.5 text-xs font-medium ${style}`}>{label}</span>;
}

export default function BookersClient() {
  const [rows, setRows] = useState<any[]>([]);
  const [total, setTotal] = useState(0);
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState("");

  async function load() {
    setLoading(true);
    try {
      const res = await fetch("/api/v1/bookers?length=100").then(r => r.json());
      if (res.success) {
        setRows(res.data?.data ?? []);
        setTotal(res.data?.recordsTotal ?? 0);
      }
    } catch (e) {
      console.error(e);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => { load(); }, []);

  const filtered = rows.filter(r => {
    const name = stripHtml(r[1] ?? r.name ?? "").toLowerCase();
    return !search || name.includes(search.toLowerCase());
  });

  return (
    <div className="space-y-4">
      <div className="flex flex-col gap-3 sm:flex-row sm:items-center sm:justify-between">
        <div className="relative flex-1 max-w-sm">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
          <input
            value={search}
            onChange={e => setSearch(e.target.value)}
            placeholder="Search bookers…"
            className="w-full rounded-lg border border-slate-200 bg-white pl-9 pr-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-teal-500/20 focus:border-teal-500 transition"
          />
        </div>
        <div className="flex gap-2">
          <button onClick={load} disabled={loading} className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50 transition-colors">
            <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} />
          </button>
          <Link href="/bookers/add" className="flex items-center gap-2 rounded-lg bg-[#0f1e3c] px-4 py-2 text-sm font-semibold text-white hover:bg-[#1a3060] transition-colors">
            <Plus className="h-4 w-4" /> Add Booker
          </Link>
        </div>
      </div>

      <div className="rounded-2xl bg-white border border-slate-200 shadow-xs overflow-hidden">
        <table className="w-full text-sm">
          <thead>
            <tr className="border-b border-slate-100 bg-slate-50/50">
              {["#", "Booker", "GPS Status", "Approval", "Actions"].map(h => (
                <th key={h} className="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wide text-slate-500">{h}</th>
              ))}
            </tr>
          </thead>
          <tbody className="divide-y divide-slate-50">
            {loading && [...Array(5)].map((_, i) => (
              <tr key={i}>
                {[...Array(5)].map((_, j) => (
                  <td key={j} className="px-4 py-3">
                    <div className="h-4 rounded bg-slate-100 animate-pulse" style={{ width: j === 1 ? "160px" : "80px" }} />
                  </td>
                ))}
              </tr>
            ))}
            {!loading && filtered.map((r, i) => {
              // DataTable columns: 0=DT_RowIndex, 1=name/HTML, 2=action HTML
              // The name cell is HTML — parse it
              const nameHtml = r[1] ?? "";
              const name = stripHtml(nameHtml).split(" ").slice(0, 4).join(" ");
              const gpsHtml = r.gps_status ?? nameHtml; // sometimes GPS is embedded in name cell
              const approvalHtml = r.admin_approved ?? r[2] ?? "";

              return (
                <tr key={i} className="hover:bg-slate-50/50 transition-colors">
                  <td className="px-4 py-3 text-slate-400 text-xs">{i + 1}</td>
                  <td className="px-4 py-3">
                    <div className="flex items-center gap-3">
                      <div className="flex h-8 w-8 shrink-0 items-center justify-center rounded-full bg-gradient-to-br from-teal-400 to-cyan-500 text-xs font-bold text-white">
                        {name[0] ?? "B"}
                      </div>
                      <span
                        className="text-xs font-medium text-slate-800"
                        dangerouslySetInnerHTML={{ __html: nameHtml }}
                      />
                    </div>
                  </td>
                  <td className="px-4 py-3"><StatusDot html={gpsHtml} /></td>
                  <td className="px-4 py-3"><ApprovalBadge html={approvalHtml} /></td>
                  <td className="px-4 py-3">
                    <div className="flex items-center gap-1">
                      <button className="p-1.5 rounded-lg text-slate-400 hover:text-teal-600 hover:bg-teal-50 transition-colors" title="View"><Eye className="h-4 w-4" /></button>
                      <button className="p-1.5 rounded-lg text-slate-400 hover:text-blue-600 hover:bg-blue-50 transition-colors" title="Edit"><Pencil className="h-4 w-4" /></button>
                      <button className="p-1.5 rounded-lg text-slate-400 hover:text-violet-600 hover:bg-violet-50 transition-colors" title="Track"><MapPin className="h-4 w-4" /></button>
                    </div>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
        <div className="flex items-center justify-between border-t border-slate-100 px-4 py-3">
          <p className="text-xs text-slate-500">
            Showing {filtered.length} of {total} bookers · Live from staging
          </p>
        </div>
      </div>
    </div>
  );
}
