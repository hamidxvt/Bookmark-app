"use client";

import { useEffect, useRef, useState } from "react";
import { Search, RefreshCw, Eye, X, ChevronDown, Download } from "lucide-react";

interface Visit {
  id: number;
  visitDate: string;
  status: string;
  notes: string | null;
  visitReport: string | null;
  checkInAt: string | null;
  checkOutAt: string | null;
  checkInLat: number | null;
  checkInLng: number | null;
  isAdhoc: boolean;
  booker: { id: number; name: string; email: string };
  customer: { id: number; name: string; customerType: string; address: string | null; ownerPhone: string };
}

function VisitBadge({ status }: { status: string }) {
  const s = (status ?? "").toUpperCase();
  const map: Record<string, { label: string; style: string }> = {
    COMPLETED:   { label: "Completed",   style: "bg-emerald-100 text-emerald-700" },
    PENDING:     { label: "Pending",     style: "bg-blue-100 text-blue-700" },
    CANCELLED:   { label: "Cancelled",   style: "bg-red-100 text-red-700" },
    IN_PROGRESS: { label: "In Progress", style: "bg-amber-100 text-amber-700" },
  };
  const key = Object.keys(map).find(k => s.includes(k)) ?? "PENDING";
  return <span className={`rounded-full px-2 py-0.5 text-xs font-medium ${map[key].style}`}>{map[key].label}</span>;
}

function VisitDetailModal({ visit, onClose }: { visit: Visit; onClose: () => void }) {
  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/50 backdrop-blur-sm p-4">
      <div className="w-full max-w-md rounded-2xl bg-white shadow-2xl">
        <div className="flex items-center justify-between border-b border-slate-100 px-5 py-4">
          <h2 className="text-base font-semibold text-slate-800">Visit #{visit.id}</h2>
          <button onClick={onClose} className="rounded-lg p-1.5 hover:bg-slate-100">
            <X className="h-4 w-4 text-slate-500" />
          </button>
        </div>
        <div className="p-5 space-y-4">
          <div className="flex items-center justify-between">
            <VisitBadge status={visit.status} />
            {visit.isAdhoc && <span className="rounded-full bg-purple-100 text-purple-700 text-xs font-medium px-2 py-0.5">Ad-hoc</span>}
          </div>

          <div className="grid grid-cols-2 gap-3 text-sm">
            <div>
              <p className="text-xs text-slate-400 font-medium mb-0.5">Customer</p>
              <p className="font-semibold text-slate-800">{visit.customer?.name ?? "—"}</p>
              <p className="text-xs text-slate-500">{visit.customer?.customerType}</p>
              {visit.customer?.ownerPhone && <p className="text-xs text-slate-500">{visit.customer.ownerPhone}</p>}
            </div>
            <div>
              <p className="text-xs text-slate-400 font-medium mb-0.5">Officer</p>
              <p className="font-semibold text-slate-800">{visit.booker?.name ?? "—"}</p>
              <p className="text-xs text-slate-500">{visit.booker?.email}</p>
            </div>
            <div>
              <p className="text-xs text-slate-400 font-medium mb-0.5">Visit Date</p>
              <p className="text-slate-700">{new Date(visit.visitDate).toLocaleDateString("en-PK", { day: "2-digit", month: "short", year: "numeric" })}</p>
            </div>
            {visit.checkInAt && (
              <div>
                <p className="text-xs text-slate-400 font-medium mb-0.5">Check-in</p>
                <p className="text-slate-700">{new Date(visit.checkInAt).toLocaleTimeString("en-PK", { hour: "2-digit", minute: "2-digit" })}</p>
              </div>
            )}
            {visit.checkOutAt && (
              <div>
                <p className="text-xs text-slate-400 font-medium mb-0.5">Check-out</p>
                <p className="text-slate-700">{new Date(visit.checkOutAt).toLocaleTimeString("en-PK", { hour: "2-digit", minute: "2-digit" })}</p>
              </div>
            )}
            {visit.checkInLat && visit.checkInLng && (
              <div className="col-span-2">
                <p className="text-xs text-slate-400 font-medium mb-0.5">GPS Location</p>
                <a
                  href={`https://maps.google.com/?q=${visit.checkInLat},${visit.checkInLng}`}
                  target="_blank" rel="noreferrer"
                  className="text-xs text-red-600 underline"
                >
                  {Number(visit.checkInLat).toFixed(5)}, {Number(visit.checkInLng).toFixed(5)}
                </a>
              </div>
            )}
          </div>

          {visit.customer?.address && (
            <div>
              <p className="text-xs text-slate-400 font-medium mb-0.5">Address</p>
              <p className="text-sm text-slate-600">{visit.customer.address}</p>
            </div>
          )}

          {(visit.notes || visit.visitReport) && (
            <div>
              <p className="text-xs text-slate-400 font-medium mb-0.5">Notes</p>
              <p className="text-sm text-slate-600 bg-slate-50 rounded-lg px-3 py-2">{visit.visitReport || visit.notes}</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

function ExportMenu({ rows }: { rows: Visit[] }) {
  const [open, setOpen] = useState(false);
  const ref = useRef<HTMLDivElement>(null);

  useEffect(() => {
    function handler(e: MouseEvent) {
      if (ref.current && !ref.current.contains(e.target as Node)) setOpen(false);
    }
    document.addEventListener("mousedown", handler);
    return () => document.removeEventListener("mousedown", handler);
  }, []);

  function exportCSV() {
    const headers = ["ID", "Customer", "Type", "Officer", "Date", "Status", "Notes"];
    const csvRows = [
      headers.join(","),
      ...rows.map(r => [
        r.id,
        `"${r.customer?.name ?? ""}"`,
        r.customer?.customerType ?? "",
        `"${r.booker?.name ?? ""}"`,
        new Date(r.visitDate).toLocaleDateString(),
        r.status,
        `"${(r.notes ?? r.visitReport ?? "").replace(/"/g, "'")}"`,
      ].join(",")),
    ];
    const blob = new Blob([csvRows.join("\n")], { type: "text/csv" });
    const url = URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url; a.download = `visits_${new Date().toISOString().slice(0, 10)}.csv`;
    a.click(); URL.revokeObjectURL(url);
    setOpen(false);
  }

  function exportPDF() {
    const printContent = `
      <html><head><title>Visits Export</title>
      <style>
        body { font-family: Arial, sans-serif; font-size: 12px; }
        h1 { font-size: 16px; color: #C8102E; }
        table { width: 100%; border-collapse: collapse; margin-top: 12px; }
        th { background: #f8f9fa; text-align: left; padding: 6px; border-bottom: 2px solid #dee2e6; font-size: 11px; }
        td { padding: 6px; border-bottom: 1px solid #f0f0f0; font-size: 11px; }
      </style></head>
      <body>
        <h1>Visits Report</h1>
        <p style="color:#666">Exported: ${new Date().toLocaleString()}</p>
        <table>
          <thead><tr><th>#</th><th>Customer</th><th>Officer</th><th>Date</th><th>Status</th><th>Notes</th></tr></thead>
          <tbody>${rows.map((r, i) => `
            <tr>
              <td>${i + 1}</td>
              <td>${r.customer?.name ?? "—"}</td>
              <td>${r.booker?.name ?? "—"}</td>
              <td>${new Date(r.visitDate).toLocaleDateString()}</td>
              <td>${r.status}</td>
              <td>${r.notes ?? r.visitReport ?? "—"}</td>
            </tr>`).join("")}
          </tbody>
        </table>
      </body></html>`;
    const win = window.open("", "_blank");
    if (!win) return;
    win.document.write(printContent);
    win.document.close();
    win.focus();
    win.print();
    setOpen(false);
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

export default function VisitsClient() {
  const [rows, setRows] = useState<Visit[]>([]);
  const [total, setTotal] = useState(0);
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState("");
  const [statusFilter, setStatusFilter] = useState("all");
  const [viewing, setViewing] = useState<Visit | null>(null);

  async function load() {
    setLoading(true);
    try {
      const res = await fetch("/api/v1/visits?length=200").then(r => r.json());
      if (res.success) {
        setRows(res.data?.data ?? []);
        setTotal(res.data?.recordsTotal ?? 0);
      }
    } catch (e) { console.error(e); }
    finally { setLoading(false); }
  }

  useEffect(() => { load(); }, []);

  const filtered = rows.filter(r => {
    const matchSearch = !search ||
      r.customer?.name?.toLowerCase().includes(search.toLowerCase()) ||
      r.booker?.name?.toLowerCase().includes(search.toLowerCase());
    const matchStatus = statusFilter === "all" || r.status === statusFilter;
    return matchSearch && matchStatus;
  });

  const completed = rows.filter(r => r.status === "COMPLETED").length;

  return (
    <div className="space-y-4">
      {viewing && <VisitDetailModal visit={viewing} onClose={() => setViewing(null)} />}

      <div className="flex flex-col gap-3 sm:flex-row sm:items-center sm:justify-between flex-wrap">
        <div className="relative flex-1 min-w-56 max-w-sm">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
          <input value={search} onChange={e => setSearch(e.target.value)} placeholder="Search by customer or officer…"
            className="w-full rounded-lg border border-slate-200 bg-white pl-9 pr-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-[#C8102E]/20 focus:border-[#C8102E] transition" />
        </div>
        <div className="relative">
          <select value={statusFilter} onChange={e => setStatusFilter(e.target.value)}
            className="px-3 pr-8 py-2 rounded-lg border border-slate-200 bg-white text-sm appearance-none cursor-pointer focus:outline-none">
            <option value="all">All Status</option>
            <option value="PENDING">Pending</option>
            <option value="IN_PROGRESS">In Progress</option>
            <option value="COMPLETED">Completed</option>
            <option value="CANCELLED">Cancelled</option>
          </select>
          <ChevronDown className="pointer-events-none absolute right-2.5 top-1/2 -translate-y-1/2 h-3.5 w-3.5 text-slate-400" />
        </div>
        <div className="flex gap-2">
          <button onClick={load} disabled={loading} className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50">
            <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} /> Refresh
          </button>
          <ExportMenu rows={filtered} />
        </div>
      </div>

      <div className="grid grid-cols-3 gap-3">
        {[
          { label: "Total Visits",     value: total,            color: "text-blue-700" },
          { label: "Completed",        value: completed,        color: "text-emerald-700" },
          { label: "Pending / Other",  value: total - completed, color: "text-amber-700" },
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
              {["#", "Customer", "Officer", "Date", "Status", "Notes", ""].map(h => (
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
                <td className="px-4 py-3">
                  <p className="text-xs font-medium text-slate-800">{r.customer?.name ?? "—"}</p>
                  <p className="text-xs text-slate-400">{r.customer?.customerType}</p>
                </td>
                <td className="px-4 py-3 text-xs text-slate-500">{r.booker?.name ?? "—"}</td>
                <td className="px-4 py-3 text-xs text-slate-500 whitespace-nowrap">{new Date(r.visitDate).toLocaleDateString("en-PK", { day: "2-digit", month: "short", year: "numeric" })}</td>
                <td className="px-4 py-3"><VisitBadge status={r.status} /></td>
                <td className="px-4 py-3 text-xs text-slate-400 max-w-[140px] truncate">{r.notes ?? r.visitReport ?? "—"}</td>
                <td className="px-4 py-3">
                  <button onClick={() => setViewing(r)} className="p-1.5 rounded-lg text-slate-400 hover:text-[#C8102E] hover:bg-red-50 transition-colors">
                    <Eye className="h-4 w-4" />
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="border-t border-slate-100 px-4 py-3">
          <p className="text-xs text-slate-500">Showing {filtered.length} of {total.toLocaleString()} visits</p>
        </div>
      </div>
    </div>
  );
}
