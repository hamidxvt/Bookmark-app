"use client";

import { useEffect, useState } from "react";
import Link from "next/link";
import { Plus, Search, Download, Eye, Pencil, RefreshCw, Filter, ChevronDown } from "lucide-react";

function stripHtml(s: string | null | undefined) {
  if (!s) return "";
  return s.replace(/<[^>]+>/g, " ").replace(/&[a-z]+;/gi, " ").replace(/\s+/g, " ").trim();
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

export default function CustomersClient() {
  const [rows, setRows] = useState<any[]>([]);
  const [total, setTotal] = useState(0);
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState("");
  const [typeFilter, setTypeFilter] = useState("all");
  const [statusFilter, setStatusFilter] = useState("all");
  const [page, setPage] = useState(0);
  const PER_PAGE = 50;

  async function load(p = 0) {
    setLoading(true);
    try {
      const res = await fetch(`/api/v1/customers?start=${p * PER_PAGE}&length=${PER_PAGE}`).then(r => r.json());
      if (res.success) {
        setRows(res.data?.data ?? []);
        setTotal(res.data?.recordsTotal ?? 0);
      }
    } catch (e) { console.error(e); }
    finally { setLoading(false); }
  }

  useEffect(() => { load(page); }, [page]);

  const filtered = rows.filter(r => {
    const name = (r.name ?? "").toLowerCase();
    const matchSearch = !search || name.includes(search.toLowerCase()) ||
      (r.city?.name ?? "").toLowerCase().includes(search.toLowerCase());
    const matchType = typeFilter === "all" || r.customerType === typeFilter;
    const matchStatus = statusFilter === "all" ||
      (statusFilter === "approved" && (r.approvalStatus ?? "").toLowerCase().includes("approved") && !(r.approvalStatus ?? "").toLowerCase().includes("not")) ||
      (statusFilter === "pending" && (r.approvalStatus ?? "").toLowerCase().includes("pending")) ||
      (statusFilter === "not_approved" && (r.approvalStatus ?? "").toLowerCase().includes("not"));
    return matchSearch && matchType && matchStatus;
  });

  const totalPages = Math.ceil(total / PER_PAGE);

  return (
    <div className="space-y-4">
      <div className="flex flex-col gap-3 sm:flex-row sm:items-center sm:justify-between flex-wrap">
        <div className="relative flex-1 min-w-48">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
          <input
            value={search}
            onChange={e => setSearch(e.target.value)}
            placeholder="Search by name or city…"
            className="w-full rounded-lg border border-slate-200 bg-white pl-9 pr-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-[#C8102E]/20 focus:border-[#C8102E] transition"
          />
        </div>
        <div className="relative">
          <Filter className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
          <select
            value={typeFilter}
            onChange={e => setTypeFilter(e.target.value)}
            className="pl-9 pr-7 py-2 rounded-lg border border-slate-200 bg-white text-sm focus:outline-none focus:ring-2 focus:ring-[#C8102E]/20 appearance-none cursor-pointer"
          >
            <option value="all">All Types</option>
            <option value="SCHOOL">School</option>
            <option value="COLLEGE">College</option>
            <option value="BOOKSHOP">Bookshop</option>
            <option value="UNIVERSITY">University</option>
          </select>
          <ChevronDown className="absolute right-2 top-1/2 -translate-y-1/2 h-3.5 w-3.5 text-slate-400 pointer-events-none" />
        </div>
        <div className="relative">
          <select
            value={statusFilter}
            onChange={e => setStatusFilter(e.target.value)}
            className="px-3 pr-7 py-2 rounded-lg border border-slate-200 bg-white text-sm focus:outline-none focus:ring-2 focus:ring-[#C8102E]/20 appearance-none cursor-pointer"
          >
            <option value="all">All Status</option>
            <option value="approved">Approved</option>
            <option value="pending">Pending</option>
            <option value="not_approved">Not Approved</option>
          </select>
          <ChevronDown className="absolute right-2 top-1/2 -translate-y-1/2 h-3.5 w-3.5 text-slate-400 pointer-events-none" />
        </div>
        <div className="flex gap-2">
          <button onClick={() => load(page)} disabled={loading} className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50 transition-colors">
            <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} />
          </button>
          <button className="flex items-center gap-2 rounded-lg border border-slate-200 bg-white px-4 py-2 text-sm font-medium text-slate-600 hover:bg-slate-50">
            <Download className="h-4 w-4" /> Export CSV
          </button>
          <Link href="/customers/add" className="flex items-center gap-2 rounded-lg bg-[#0f1e3c] px-4 py-2 text-sm font-semibold text-white hover:bg-[#1a3060] transition-colors">
            <Plus className="h-4 w-4" /> Add Customer
          </Link>
        </div>
      </div>

      <div className="rounded-2xl bg-white border border-slate-200 shadow-xs overflow-hidden">
        <table className="w-full text-sm">
          <thead>
            <tr className="border-b border-slate-100 bg-slate-50/50">
              {["#", "Customer", "City", "Status", "Joined", "Actions"].map(h => (
                <th key={h} className="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wide text-slate-500">{h}</th>
              ))}
            </tr>
          </thead>
          <tbody className="divide-y divide-slate-50">
            {loading && [...Array(8)].map((_, i) => (
              <tr key={i}>
                {[...Array(6)].map((_, j) => (
                  <td key={j} className="px-4 py-3">
                    <div className="h-4 rounded bg-slate-100 animate-pulse" style={{ width: j === 1 ? "180px" : "90px" }} />
                  </td>
                ))}
              </tr>
            ))}
            {!loading && filtered.map((r: any, i: number) => (
              <tr key={r.id ?? i} className="hover:bg-slate-50/50 transition-colors">
                <td className="px-4 py-3 text-slate-400 text-xs">{page * PER_PAGE + i + 1}</td>
                <td className="px-4 py-3">
                  <div className="flex items-center gap-3">
                    <div className="flex h-7 w-7 shrink-0 items-center justify-center rounded-full bg-slate-100 text-xs font-bold text-slate-600">
                      {(r.name ?? "C")[0].toUpperCase()}
                    </div>
                    <div>
                      <p className="text-xs font-medium text-slate-800">{stripHtml(r.name)}</p>
                      <p className="text-xs text-slate-400">{r.customerType === "SCHOOL" ? "School" : r.customerType === "BOOKSHOP" ? "Bookshop" : (r.customerType ?? "Other")}</p>
                    </div>
                  </div>
                </td>
                <td className="px-4 py-3 text-slate-600 text-xs">{stripHtml(r.city?.name) || "—"}</td>
                <td className="px-4 py-3"><ApprovalBadge html={r.approvalStatus ?? ""} /></td>
                <td className="px-4 py-3 text-slate-500 text-xs">{r.createdAt ? new Date(r.createdAt).toLocaleDateString() : "—"}</td>
                <td className="px-4 py-3">
                  <div className="flex items-center gap-1">
                    <button className="p-1.5 rounded-lg text-slate-400 hover:text-[#C8102E] hover:bg-red-50 transition-colors"><Eye className="h-4 w-4" /></button>
                    <button className="p-1.5 rounded-lg text-slate-400 hover:text-blue-600 hover:bg-blue-50 transition-colors"><Pencil className="h-4 w-4" /></button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="flex items-center justify-between border-t border-slate-100 px-4 py-3">
          <p className="text-xs text-slate-500">
            Showing {page * PER_PAGE + 1}–{Math.min((page + 1) * PER_PAGE, total)} of {total.toLocaleString()} customers · Live data
          </p>
          <div className="flex items-center gap-1 text-xs text-slate-500">
            <button onClick={() => setPage(p => Math.max(0, p - 1))} disabled={page === 0} className="rounded px-2 py-1 hover:bg-slate-100 disabled:opacity-40">← Prev</button>
            <span className="rounded bg-[#C8102E] px-2 py-1 text-white font-medium">{page + 1}</span>
            <span className="px-1">of {totalPages}</span>
            <button onClick={() => setPage(p => Math.min(totalPages - 1, p + 1))} disabled={page >= totalPages - 1} className="rounded px-2 py-1 hover:bg-slate-100 disabled:opacity-40">Next →</button>
          </div>
        </div>
      </div>
    </div>
  );
}
