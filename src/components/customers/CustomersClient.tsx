"use client";

import { useEffect, useRef, useState } from "react";
import Link from "next/link";
import {
  Plus, Search, Download, Eye, Pencil, RefreshCw,
  Filter, ChevronDown, X, MapPin, Phone, Mail, Globe,
  Building2, User, Tag, Calendar,
} from "lucide-react";

function stripHtml(s: string | null | undefined) {
  if (!s) return "";
  return s.replace(/<[^>]+>/g, " ").replace(/&[a-z]+;/gi, " ").replace(/\s+/g, " ").trim();
}

function ApprovalBadge({ status }: { status: string }) {
  const s = (status ?? "").toUpperCase();
  if (s === "APPROVED")
    return <span className="inline-flex rounded-full border px-2 py-0.5 text-xs font-medium bg-emerald-100 text-emerald-800 border-emerald-200">Approved</span>;
  if (s === "PENDING")
    return <span className="inline-flex rounded-full border px-2 py-0.5 text-xs font-medium bg-amber-100 text-amber-800 border-amber-200">Pending</span>;
  return <span className="inline-flex rounded-full border px-2 py-0.5 text-xs font-medium bg-red-100 text-red-800 border-red-200">Not Approved</span>;
}

function CategoryBadge({ cat }: { cat: string | null | undefined }) {
  if (!cat) return null;
  const colors: Record<string, string> = {
    "A+": "bg-purple-100 text-purple-700 border-purple-200",
    "A":  "bg-blue-100 text-blue-700 border-blue-200",
    "B":  "bg-teal-100 text-teal-700 border-teal-200",
    "BOOKSHOPS": "bg-amber-100 text-amber-700 border-amber-200",
  };
  return (
    <span className={`inline-flex rounded-full border px-2 py-0.5 text-[10px] font-semibold ${colors[cat] ?? "bg-slate-100 text-slate-600 border-slate-200"}`}>
      {cat}
    </span>
  );
}

// ─── Detail Modal ─────────────────────────────────────────────────────────────

function CustomerDetailModal({ customer, onClose }: { customer: any; onClose: () => void }) {
  const row = [
    { icon: Building2, label: "City",    value: customer.city?.name },
    { icon: Tag,       label: "Type",    value: customer.customerType },
    { icon: Tag,       label: "Category",value: customer.category },
    { icon: Phone,     label: "Phone",   value: customer.ownerPhone },
    { icon: Mail,      label: "Email",   value: customer.email },
    { icon: Globe,     label: "Website", value: customer.website },
    { icon: MapPin,    label: "Address", value: customer.address },
    { icon: User,      label: "Contact", value: customer.ownerName },
    { icon: Tag,       label: "Zone",    value: customer.zone },
    { icon: Building2, label: "Exam Board",      value: customer.examinationBoard },
    { icon: Building2, label: "Programme",        value: customer.offeredProgramme },
    { icon: Building2, label: "Total Students",   value: customer.totalStudents },
  ].filter(f => f.value);

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/50 backdrop-blur-sm p-4" onClick={onClose}>
      <div className="w-full max-w-lg rounded-2xl bg-white shadow-2xl overflow-hidden" onClick={e => e.stopPropagation()}>
        {/* Header */}
        <div className="flex items-center justify-between px-6 py-4 bg-gradient-to-r from-[#C8102E] to-[#9B0B22]">
          <div className="flex items-center gap-3">
            <div className="flex h-10 w-10 items-center justify-center rounded-full bg-white/20 text-white font-bold text-lg">
              {(customer.name ?? "C")[0].toUpperCase()}
            </div>
            <div>
              <p className="font-bold text-white text-sm leading-tight">{customer.name}</p>
              <div className="flex items-center gap-1.5 mt-0.5">
                <span className="text-[10px] text-white/70">{customer.customerType}</span>
                {customer.category && <CategoryBadge cat={customer.category} />}
              </div>
            </div>
          </div>
          <button onClick={onClose} className="text-white/70 hover:text-white transition-colors">
            <X className="h-5 w-5" />
          </button>
        </div>

        {/* Status & Joined */}
        <div className="flex items-center gap-4 px-6 py-3 border-b border-slate-100 bg-slate-50/50">
          <ApprovalBadge status={customer.approvalStatus ?? ""} />
          {customer.createdAt && (
            <span className="flex items-center gap-1 text-xs text-slate-400">
              <Calendar className="h-3 w-3" />
              Joined {new Date(customer.createdAt).toLocaleDateString("en-PK", { day:"numeric", month:"short", year:"numeric" })}
            </span>
          )}
        </div>

        {/* Fields */}
        <div className="px-6 py-4 grid grid-cols-2 gap-3 max-h-80 overflow-y-auto">
          {row.map(({ icon: Icon, label, value }) => (
            <div key={label} className="min-w-0">
              <p className="text-[10px] font-semibold text-slate-400 uppercase tracking-wide">{label}</p>
              <div className="flex items-start gap-1.5 mt-0.5">
                <Icon className="h-3.5 w-3.5 text-slate-400 mt-0.5 shrink-0" />
                <p className="text-xs text-slate-700 font-medium break-words">{String(value)}</p>
              </div>
            </div>
          ))}
        </div>

        {/* Actions */}
        <div className="flex gap-3 px-6 py-4 border-t border-slate-100">
          <Link
            href={`/customers/${customer.id}/edit`}
            className="flex-1 text-center rounded-xl bg-[#C8102E] py-2.5 text-sm font-semibold text-white hover:bg-red-700 transition-colors"
          >
            Edit Customer
          </Link>
          <button
            onClick={onClose}
            className="flex-1 rounded-xl border border-slate-200 py-2.5 text-sm font-medium text-slate-600 hover:bg-slate-50 transition-colors"
          >
            Close
          </button>
        </div>
      </div>
    </div>
  );
}

// ─── Export Menu ─────────────────────────────────────────────────────────────

function ExportMenu({ rows }: { rows: any[] }) {
  const [open, setOpen] = useState(false);
  const ref = useRef<HTMLDivElement>(null);
  useEffect(() => {
    const h = (e: MouseEvent) => { if (ref.current && !ref.current.contains(e.target as Node)) setOpen(false); };
    document.addEventListener("mousedown", h);
    return () => document.removeEventListener("mousedown", h);
  }, []);

  function exportCSV() {
    const headers = ["ID","Name","Type","Category","City","Phone","Email","Address","Status","Joined"];
    const csv = [headers.join(","), ...rows.map(r => [
      r.id, `"${r.name??""}"`, r.customerType??"", r.category??"",
      `"${r.city?.name??""}"`, r.ownerPhone??"", r.email??"",
      `"${(r.address??"").replace(/"/g,"'")}"`,
      r.approvalStatus??"", r.createdAt ? new Date(r.createdAt).toLocaleDateString() : "",
    ].join(","))].join("\n");
    const blob = new Blob([csv], { type:"text/csv" });
    const a = document.createElement("a"); a.href = URL.createObjectURL(blob);
    a.download = `customers_${new Date().toISOString().slice(0,10)}.csv`; a.click();
    setOpen(false);
  }

  function exportPDF() {
    const win = window.open("","_blank"); if (!win) return;
    win.document.write(`<html><head><title>Customers</title>
      <style>body{font-family:Arial;font-size:11px}h1{color:#C8102E}
      table{width:100%;border-collapse:collapse}th{background:#f8f9fa;padding:5px;border-bottom:2px solid #dee2e6;text-align:left}
      td{padding:5px;border-bottom:1px solid #f0f0f0}</style></head>
      <body><h1>Customers Report</h1><p style="color:#666">Exported: ${new Date().toLocaleString()}</p>
      <table><thead><tr><th>#</th><th>Name</th><th>Type</th><th>Category</th><th>City</th><th>Phone</th><th>Status</th></tr></thead>
      <tbody>${rows.map((r,i)=>`<tr><td>${i+1}</td><td>${r.name??""}</td><td>${r.customerType??""}</td><td>${r.category??""}</td><td>${r.city?.name??""}</td><td>${r.ownerPhone??""}</td><td>${r.approvalStatus??""}</td></tr>`).join("")}
      </tbody></table></body></html>`);
    win.document.close(); win.focus(); win.print(); setOpen(false);
  }

  return (
    <div className="relative" ref={ref}>
      <button onClick={() => setOpen(!open)} className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50">
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

// ─── Main Component ───────────────────────────────────────────────────────────

export default function CustomersClient() {
  const [rows, setRows]               = useState<any[]>([]);
  const [total, setTotal]             = useState(0);
  const [loading, setLoading]         = useState(true);
  const [search, setSearch]           = useState("");
  const [typeFilter, setTypeFilter]   = useState("all");
  const [statusFilter, setStatusFilter] = useState("all");
  const [page, setPage]               = useState(0);
  const [detail, setDetail]           = useState<any | null>(null);
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
    const matchSearch = !search ||
      name.includes(search.toLowerCase()) ||
      (r.city?.name ?? "").toLowerCase().includes(search.toLowerCase()) ||
      (r.category ?? "").toLowerCase().includes(search.toLowerCase());
    const matchType   = typeFilter === "all" || r.customerType === typeFilter;
    const matchStatus = statusFilter === "all" ||
      (statusFilter === "approved"     && r.approvalStatus === "APPROVED") ||
      (statusFilter === "pending"      && r.approvalStatus === "PENDING") ||
      (statusFilter === "not_approved" && r.approvalStatus === "NOT_APPROVED");
    return matchSearch && matchType && matchStatus;
  });

  const totalPages = Math.ceil(total / PER_PAGE);

  return (
    <div className="space-y-4">
      {/* Toolbar */}
      <div className="flex flex-col gap-3 sm:flex-row sm:items-center sm:justify-between flex-wrap">
        <div className="relative flex-1 min-w-48">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
          <input
            value={search}
            onChange={e => { setSearch(e.target.value); setPage(0); }}
            placeholder="Search by name, city, or category…"
            className="w-full rounded-lg border border-slate-200 bg-white pl-9 pr-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-[#C8102E]/20 focus:border-[#C8102E] transition"
          />
        </div>
        <div className="relative">
          <Filter className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
          <select
            value={typeFilter}
            onChange={e => { setTypeFilter(e.target.value); setPage(0); }}
            className="pl-9 pr-7 py-2 rounded-lg border border-slate-200 bg-white text-sm focus:outline-none appearance-none cursor-pointer"
          >
            <option value="all">All Types</option>
            <option value="SCHOOL">School</option>
            <option value="COLLEGE">College</option>
            <option value="RETAILER">Book Shop</option>
            <option value="SELF">Individual</option>
            <option value="OTHER">Other</option>
          </select>
          <ChevronDown className="absolute right-2 top-1/2 -translate-y-1/2 h-3.5 w-3.5 text-slate-400 pointer-events-none" />
        </div>
        <div className="relative">
          <select
            value={statusFilter}
            onChange={e => { setStatusFilter(e.target.value); setPage(0); }}
            className="px-3 pr-7 py-2 rounded-lg border border-slate-200 bg-white text-sm focus:outline-none appearance-none cursor-pointer"
          >
            <option value="all">All Status</option>
            <option value="approved">Approved</option>
            <option value="pending">Pending</option>
            <option value="not_approved">Not Approved</option>
          </select>
          <ChevronDown className="absolute right-2 top-1/2 -translate-y-1/2 h-3.5 w-3.5 text-slate-400 pointer-events-none" />
        </div>
        <div className="flex gap-2">
          <button onClick={() => load(page)} disabled={loading}
            className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50 transition-colors">
            <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} />
          </button>
          <ExportMenu rows={filtered} />
          <Link href="/customers/add"
            className="flex items-center gap-2 rounded-lg bg-[#C8102E] px-4 py-2 text-sm font-semibold text-white hover:bg-red-700 transition-colors">
            <Plus className="h-4 w-4" /> Add Customer
          </Link>
        </div>
      </div>

      {/* Table */}
      <div className="rounded-2xl bg-white border border-slate-200 shadow-xs overflow-hidden">
        <table className="w-full text-sm">
          <thead>
            <tr className="border-b border-slate-100 bg-slate-50/50">
              {["#", "Customer", "Category", "City", "Status", "Joined", "Actions"].map(h => (
                <th key={h} className="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wide text-slate-500">{h}</th>
              ))}
            </tr>
          </thead>
          <tbody className="divide-y divide-slate-50">
            {loading && [...Array(8)].map((_, i) => (
              <tr key={i}>
                {[...Array(7)].map((_, j) => (
                  <td key={j} className="px-4 py-3">
                    <div className="h-4 rounded bg-slate-100 animate-pulse" style={{ width: j === 1 ? "180px" : "80px" }} />
                  </td>
                ))}
              </tr>
            ))}
            {!loading && filtered.length === 0 && (
              <tr>
                <td colSpan={7} className="px-4 py-10 text-center text-sm text-slate-400">No customers found</td>
              </tr>
            )}
            {!loading && filtered.map((r: any, i: number) => (
              <tr key={r.id ?? i} className="hover:bg-slate-50/50 transition-colors">
                <td className="px-4 py-3 text-slate-400 text-xs">{page * PER_PAGE + i + 1}</td>
                <td className="px-4 py-3">
                  <div className="flex items-center gap-3">
                    <div className="flex h-7 w-7 shrink-0 items-center justify-center rounded-full bg-[#C8102E]/10 text-xs font-bold text-[#C8102E]">
                      {(r.name ?? "C")[0].toUpperCase()}
                    </div>
                    <div>
                      <p className="text-xs font-medium text-slate-800 leading-tight">{stripHtml(r.name)}</p>
                      <p className="text-[10px] text-slate-400 mt-0.5">{r.customerType ?? "Other"}</p>
                    </div>
                  </div>
                </td>
                <td className="px-4 py-3"><CategoryBadge cat={r.category} /></td>
                <td className="px-4 py-3 text-slate-600 text-xs">{stripHtml(r.city?.name) || "—"}</td>
                <td className="px-4 py-3"><ApprovalBadge status={r.approvalStatus ?? ""} /></td>
                <td className="px-4 py-3 text-slate-500 text-xs">{r.createdAt ? new Date(r.createdAt).toLocaleDateString("en-PK") : "—"}</td>
                <td className="px-4 py-3">
                  <div className="flex items-center gap-1">
                    <button
                      onClick={() => setDetail(r)}
                      title="View details"
                      className="p-1.5 rounded-lg text-slate-400 hover:text-[#C8102E] hover:bg-red-50 transition-colors"
                    >
                      <Eye className="h-4 w-4" />
                    </button>
                    <Link
                      href={`/customers/${r.id}/edit`}
                      title="Edit customer"
                      className="p-1.5 rounded-lg text-slate-400 hover:text-blue-600 hover:bg-blue-50 transition-colors"
                    >
                      <Pencil className="h-4 w-4" />
                    </Link>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        {/* Pagination */}
        <div className="flex items-center justify-between border-t border-slate-100 px-4 py-3">
          <p className="text-xs text-slate-500">
            Showing {page * PER_PAGE + 1}–{Math.min((page + 1) * PER_PAGE, total)} of {total.toLocaleString()} customers
          </p>
          <div className="flex items-center gap-1 text-xs text-slate-500">
            <button onClick={() => setPage(p => Math.max(0, p - 1))} disabled={page === 0}
              className="rounded px-2 py-1 hover:bg-slate-100 disabled:opacity-40">← Prev</button>
            <span className="rounded bg-[#C8102E] px-2 py-1 text-white font-medium">{page + 1}</span>
            <span className="px-1">of {totalPages}</span>
            <button onClick={() => setPage(p => Math.min(totalPages - 1, p + 1))} disabled={page >= totalPages - 1}
              className="rounded px-2 py-1 hover:bg-slate-100 disabled:opacity-40">Next →</button>
          </div>
        </div>
      </div>

      {/* Detail Modal */}
      {detail && <CustomerDetailModal customer={detail} onClose={() => setDetail(null)} />}
    </div>
  );
}
