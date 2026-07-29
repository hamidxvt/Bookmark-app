"use client";

import { useEffect, useState } from "react";
import { Search, Eye, MapPin, RefreshCw, CheckCircle, Clock, XCircle, Plus, Trash2, X, Navigation } from "lucide-react";

interface Booker {
  id: number;
  name: string;
  email: string;
  phone: string;
  gender: string;
  jobStatus: string;
  adminApproved: string;
  gpsStatus: string;
  lastSeenAt: string | null;
  lastLatitude: number | null;
  lastLongitude: number | null;
  visitTargets: number | null;
  ratesPerVisit: number | null;
  createdAt: string;
  city: { id: number; name: string } | null;
  region: { id: number; name: string } | null;
}

function GpsBadge({ status }: { status: string }) {
  const s = status?.toUpperCase();
  if (s === "ACTIVE") return <span className="flex items-center gap-1.5 text-xs font-medium text-emerald-600"><span className="h-2 w-2 rounded-full bg-emerald-500 animate-pulse" />Active</span>;
  if (s === "IDLE") return <span className="flex items-center gap-1.5 text-xs font-medium text-amber-600"><span className="h-2 w-2 rounded-full bg-amber-500" />Idle</span>;
  return <span className="flex items-center gap-1.5 text-xs font-medium text-slate-400"><span className="h-2 w-2 rounded-full bg-slate-300" />Offline</span>;
}

function ApprovalBadge({ status }: { status: string }) {
  const s = status?.toUpperCase();
  if (s === "APPROVED") return <span className="inline-flex rounded-full border px-2 py-0.5 text-xs font-medium bg-emerald-100 text-emerald-800 border-emerald-200 items-center gap-1"><CheckCircle className="h-3 w-3" />Approved</span>;
  if (s === "PENDING") return <span className="inline-flex rounded-full border px-2 py-0.5 text-xs font-medium bg-amber-100 text-amber-800 border-amber-200 items-center gap-1"><Clock className="h-3 w-3" />Pending</span>;
  return <span className="inline-flex rounded-full border px-2 py-0.5 text-xs font-medium bg-red-100 text-red-800 border-red-200 items-center gap-1"><XCircle className="h-3 w-3" />Rejected</span>;
}

function CreateModal({ onClose, onCreated }: { onClose: () => void; onCreated: () => void }) {
  const [form, setForm] = useState({ name: "", email: "", password: "", phone: "" });
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState("");

  async function submit(e: React.FormEvent) {
    e.preventDefault();
    setSaving(true);
    setError("");
    try {
      const res = await fetch("/api/v1/bookers/create", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      }).then(r => r.json());
      if (res.success) { onCreated(); onClose(); }
      else setError(res.error ?? "Failed");
    } finally { setSaving(false); }
  }

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/50 backdrop-blur-sm p-4">
      <div className="w-full max-w-md rounded-2xl bg-white shadow-2xl">
        <div className="flex items-center justify-between border-b border-slate-100 px-6 py-4">
          <h2 className="text-base font-semibold text-slate-800">Add New Officer</h2>
          <button onClick={onClose} className="rounded-lg p-1.5 hover:bg-slate-100"><X className="h-4 w-4 text-slate-500" /></button>
        </div>
        <form onSubmit={submit} className="p-6 space-y-4">
          {error && <p className="rounded-lg bg-red-50 border border-red-200 px-3 py-2 text-xs text-red-600">{error}</p>}
          {[
            { label: "Full Name", key: "name", type: "text", placeholder: "Ahmed Khan" },
            { label: "Email", key: "email", type: "email", placeholder: "officer@bookmark.pk" },
            { label: "Password", key: "password", type: "password", placeholder: "Min 8 characters" },
            { label: "Phone", key: "phone", type: "text", placeholder: "03001234567" },
          ].map(f => (
            <div key={f.key}>
              <label className="block text-xs font-medium text-slate-600 mb-1">{f.label}</label>
              <input
                type={f.type}
                placeholder={f.placeholder}
                value={(form as any)[f.key]}
                onChange={e => setForm(p => ({ ...p, [f.key]: e.target.value }))}
                required
                className="w-full rounded-lg border border-slate-200 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-teal-500/20 focus:border-teal-500 transition"
              />
            </div>
          ))}
          <div className="flex gap-3 pt-2">
            <button type="button" onClick={onClose} className="flex-1 rounded-lg border border-slate-200 py-2 text-sm font-medium text-slate-600 hover:bg-slate-50">Cancel</button>
            <button type="submit" disabled={saving} className="flex-1 rounded-lg bg-teal-600 py-2 text-sm font-medium text-white hover:bg-teal-700 disabled:opacity-50">
              {saving ? "Creating…" : "Create Officer"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

function TrackModal({ booker, onClose }: { booker: Booker; onClose: () => void }) {
  const hasCoords = booker.lastLatitude && booker.lastLongitude;
  const mapUrl = hasCoords
    ? `https://www.openstreetmap.org/export/embed.html?bbox=${booker.lastLongitude! - 0.01},${booker.lastLatitude! - 0.01},${booker.lastLongitude! + 0.01},${booker.lastLatitude! + 0.01}&layer=mapnik&marker=${booker.lastLatitude},${booker.lastLongitude}`
    : null;

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/50 backdrop-blur-sm p-4">
      <div className="w-full max-w-lg rounded-2xl bg-white shadow-2xl">
        <div className="flex items-center justify-between border-b border-slate-100 px-6 py-4">
          <div>
            <h2 className="text-base font-semibold text-slate-800">Tracking: {booker.name}</h2>
            <p className="text-xs text-slate-400 mt-0.5">{booker.email}</p>
          </div>
          <button onClick={onClose} className="rounded-lg p-1.5 hover:bg-slate-100"><X className="h-4 w-4 text-slate-500" /></button>
        </div>
        <div className="p-6 space-y-4">
          <div className="grid grid-cols-2 gap-3 text-xs">
            <div className="rounded-xl bg-slate-50 p-3"><p className="text-slate-400 mb-1">GPS Status</p><GpsBadge status={booker.gpsStatus} /></div>
            <div className="rounded-xl bg-slate-50 p-3"><p className="text-slate-400 mb-1">Last Seen</p><p className="font-medium text-slate-700">{booker.lastSeenAt ? new Date(booker.lastSeenAt).toLocaleTimeString() : "Never"}</p></div>
            <div className="rounded-xl bg-slate-50 p-3 col-span-2">
              <p className="text-slate-400 mb-1">Coordinates</p>
              <p className="font-mono font-medium text-slate-700">{hasCoords ? `${booker.lastLatitude}, ${booker.lastLongitude}` : "No location data"}</p>
            </div>
          </div>
          {mapUrl ? (
            <iframe src={mapUrl} className="w-full h-56 rounded-xl border border-slate-200" title="Officer location" />
          ) : (
            <div className="h-56 rounded-xl bg-slate-50 flex items-center justify-center text-slate-400 text-sm">No GPS data yet — officer needs to start day</div>
          )}
          {hasCoords && (
            <a href={`https://www.openstreetmap.org/?mlat=${booker.lastLatitude}&mlon=${booker.lastLongitude}`} target="_blank" rel="noopener noreferrer"
              className="flex items-center justify-center gap-2 w-full rounded-lg bg-teal-600 py-2 text-sm font-medium text-white hover:bg-teal-700">
              <Navigation className="h-4 w-4" /> Open in Maps
            </a>
          )}
        </div>
      </div>
    </div>
  );
}

export default function BookersClient() {
  const [rows, setRows] = useState<Booker[]>([]);
  const [total, setTotal] = useState(0);
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState("");
  const [approving, setApproving] = useState<number | null>(null);
  const [deleting, setDeleting] = useState<number | null>(null);
  const [showCreate, setShowCreate] = useState(false);
  const [tracking, setTracking] = useState<Booker | null>(null);

  async function load() {
    setLoading(true);
    try {
      const res = await fetch("/api/v1/bookers?length=100").then(r => r.json());
      if (res.success) { setRows(res.data?.data ?? []); setTotal(res.data?.recordsTotal ?? 0); }
    } catch (e) { console.error(e); }
    finally { setLoading(false); }
  }

  async function approveBooker(id: number) {
    setApproving(id);
    try {
      await fetch("/api/v1/bookers", { method: "PATCH", headers: { "Content-Type": "application/json" }, body: JSON.stringify({ id, adminApproved: "APPROVED", jobStatus: "ACTIVE" }) });
      await load();
    } finally { setApproving(null); }
  }

  async function deleteBooker(id: number, name: string) {
    if (!confirm(`Remove ${name} from the system? This will deactivate their account.`)) return;
    setDeleting(id);
    try {
      await fetch(`/api/v1/bookers/${id}`, { method: "DELETE" });
      await load();
    } finally { setDeleting(null); }
  }

  useEffect(() => { load(); }, []);

  const filtered = rows.filter(r =>
    !search ||
    r.name?.toLowerCase().includes(search.toLowerCase()) ||
    r.email?.toLowerCase().includes(search.toLowerCase()) ||
    r.phone?.includes(search)
  );

  const active = rows.filter(r => r.gpsStatus === "ACTIVE").length;
  const pending = rows.filter(r => r.adminApproved === "PENDING").length;

  return (
    <div className="space-y-4">
      {showCreate && <CreateModal onClose={() => setShowCreate(false)} onCreated={load} />}
      {tracking && <TrackModal booker={tracking} onClose={() => setTracking(null)} />}

      <div className="flex flex-col gap-3 sm:flex-row sm:items-center sm:justify-between">
        <div className="relative flex-1 max-w-sm">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
          <input value={search} onChange={e => setSearch(e.target.value)} placeholder="Search by name, email, phone…"
            className="w-full rounded-lg border border-slate-200 bg-white pl-9 pr-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-teal-500/20 focus:border-teal-500 transition" />
        </div>
        <div className="flex gap-2">
          <button onClick={load} disabled={loading} className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50">
            <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} /> Refresh
          </button>
          <button onClick={() => setShowCreate(true)} className="flex items-center gap-1.5 rounded-lg bg-teal-600 px-3 py-2 text-xs font-medium text-white hover:bg-teal-700">
            <Plus className="h-3.5 w-3.5" /> Add Officer
          </button>
        </div>
      </div>

      <div className="grid grid-cols-3 gap-3">
        {[
          { label: "Total Officers", value: total, color: "text-blue-700" },
          { label: "Active Now", value: active, color: "text-emerald-700" },
          { label: "Pending Approval", value: pending, color: "text-amber-700" },
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
              {["#", "Officer", "City", "Phone", "GPS", "Status", "Actions"].map(h => (
                <th key={h} className="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wide text-slate-500">{h}</th>
              ))}
            </tr>
          </thead>
          <tbody className="divide-y divide-slate-50">
            {loading && [...Array(5)].map((_, i) => (
              <tr key={i}>{[...Array(7)].map((_, j) => (
                <td key={j} className="px-4 py-3"><div className="h-4 rounded bg-slate-100 animate-pulse" style={{ width: j === 1 ? "160px" : "80px" }} /></td>
              ))}</tr>
            ))}
            {!loading && filtered.length === 0 && (
              <tr><td colSpan={7} className="px-4 py-8 text-center text-sm text-slate-400">No officers found · <button onClick={() => setShowCreate(true)} className="text-teal-600 underline">Add one</button></td></tr>
            )}
            {!loading && filtered.map((r, i) => (
              <tr key={r.id} className="hover:bg-slate-50/50 transition-colors">
                <td className="px-4 py-3 text-slate-400 text-xs">{i + 1}</td>
                <td className="px-4 py-3">
                  <div className="flex items-center gap-3">
                    <div className="flex h-8 w-8 shrink-0 items-center justify-center rounded-full bg-gradient-to-br from-teal-400 to-cyan-500 text-xs font-bold text-white">
                      {r.name?.[0]?.toUpperCase() ?? "B"}
                    </div>
                    <div>
                      <p className="text-xs font-medium text-slate-800">{r.name}</p>
                      <p className="text-xs text-slate-400">{r.email}</p>
                    </div>
                  </div>
                </td>
                <td className="px-4 py-3 text-xs text-slate-500">{r.city?.name ?? "—"}</td>
                <td className="px-4 py-3 text-xs text-slate-500">{r.phone}</td>
                <td className="px-4 py-3"><GpsBadge status={r.gpsStatus} /></td>
                <td className="px-4 py-3"><ApprovalBadge status={r.adminApproved} /></td>
                <td className="px-4 py-3">
                  <div className="flex items-center gap-1">
                    <button onClick={() => setTracking(r)} className="p-1.5 rounded-lg text-slate-400 hover:text-teal-600 hover:bg-teal-50 transition-colors" title="Track GPS">
                      <MapPin className="h-4 w-4" />
                    </button>
                    <button className="p-1.5 rounded-lg text-slate-400 hover:text-blue-600 hover:bg-blue-50 transition-colors" title="View Profile">
                      <Eye className="h-4 w-4" />
                    </button>
                    {r.adminApproved === "PENDING" && (
                      <button onClick={() => approveBooker(r.id)} disabled={approving === r.id}
                        className="rounded-lg bg-emerald-600 px-2 py-1 text-xs font-medium text-white hover:bg-emerald-700 disabled:opacity-50">
                        {approving === r.id ? "…" : "Approve"}
                      </button>
                    )}
                    <button onClick={() => deleteBooker(r.id, r.name)} disabled={deleting === r.id}
                      className="p-1.5 rounded-lg text-slate-400 hover:text-red-600 hover:bg-red-50 transition-colors" title="Remove officer">
                      {deleting === r.id ? <span className="text-xs">…</span> : <Trash2 className="h-4 w-4" />}
                    </button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="border-t border-slate-100 px-4 py-3">
          <p className="text-xs text-slate-500">Showing {filtered.length} of {total} officers · <span className="text-teal-600 cursor-pointer" onClick={() => setShowCreate(true)}>+ Add New</span></p>
        </div>
      </div>
    </div>
  );
}
