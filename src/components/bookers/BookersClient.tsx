"use client";

import { useEffect, useState } from "react";
import {
  Search, Eye, MapPin, RefreshCw, CheckCircle, Clock, XCircle,
  Plus, Trash2, X, Navigation, KeyRound, Pencil, Filter, ChevronDown,
} from "lucide-react";

interface Booker {
  id: number; name: string; email: string; phone: string; gender: string;
  designation?: string | null;
  jobStatus: string; adminApproved: string; gpsStatus: string;
  lastSeenAt: string | null; lastLatitude: number | null; lastLongitude: number | null;
  visitTargets: number | null; ratesPerVisit: number | null;
  basicSalary?: number | null; sampleBudget?: number | null;
  createdAt: string; city: { id: number; name: string } | null;
  region: { id: number; name: string } | null;
}

function stripHtml(s: string | null | undefined): string {
  if (!s) return "—";
  const stripped = s.replace(/<[^>]+>/g, " ").replace(/\s+/g, " ").trim();
  return stripped.length > 0 ? stripped : "—";
}

function GpsBadge({ status }: { status: string }) {
  const s = status?.toUpperCase();
  if (s === "ACTIVE") return <span className="flex items-center gap-1.5 text-xs font-medium text-emerald-600"><span className="h-2 w-2 rounded-full bg-emerald-500 animate-pulse" />Active</span>;
  if (s === "IDLE")   return <span className="flex items-center gap-1.5 text-xs font-medium text-amber-600"><span className="h-2 w-2 rounded-full bg-amber-500" />Idle</span>;
  return <span className="flex items-center gap-1.5 text-xs font-medium text-slate-400"><span className="h-2 w-2 rounded-full bg-slate-300" />Offline</span>;
}

function ApprovalBadge({ status }: { status: string }) {
  const s = status?.toUpperCase();
  if (s === "APPROVED") return <span className="inline-flex rounded-full border px-2 py-0.5 text-xs font-medium bg-emerald-100 text-emerald-800 border-emerald-200 items-center gap-1"><CheckCircle className="h-3 w-3" />Approved</span>;
  if (s === "PENDING")  return <span className="inline-flex rounded-full border px-2 py-0.5 text-xs font-medium bg-amber-100 text-amber-800 border-amber-200 items-center gap-1"><Clock className="h-3 w-3" />Pending</span>;
  return <span className="inline-flex rounded-full border px-2 py-0.5 text-xs font-medium bg-red-100 text-red-800 border-red-200 items-center gap-1"><XCircle className="h-3 w-3" />Not Approved</span>;
}

// ── Create / Edit Officer Modal ────────────────────────────────────────────────
function OfficerModal({
  booker, onClose, onSaved,
}: { booker?: Booker | null; onClose: () => void; onSaved: () => void }) {
  const isEdit = !!booker;
  const [cities, setCities] = useState<Array<{ id: number; name: string }>>([]);
  const [form, setForm] = useState({
    name: booker?.name ?? "", email: booker?.email ?? "",
    password: "", phone: booker?.phone ?? "",
    designation: booker?.designation ?? "",
    basicSalary: booker?.basicSalary ?? "", ratesPerVisit: booker?.ratesPerVisit ?? "",
    visitTargets: booker?.visitTargets ?? "", sampleBudget: booker?.sampleBudget ?? 300000,
    adminApproved: booker?.adminApproved ?? "PENDING",
    jobStatus: booker?.jobStatus ?? "NOT_ACTIVE",
    cityId: booker?.city?.id ?? "",
  });
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    fetch("/api/v1/cities").then(r => r.json()).then(d => {
      if (d.success && d.data) setCities(d.data);
    }).catch(() => {});
  }, []);

  const set = (k: string, v: string | number) => setForm(p => ({ ...p, [k]: v }));

  async function submit(e: React.FormEvent) {
    e.preventDefault();
    setSaving(true); setError("");
    try {
      const url  = isEdit ? `/api/v1/bookers/${booker!.id}` : "/api/v1/bookers/create";
      const method = isEdit ? "PATCH" : "POST";
      const body: Record<string, unknown> = {
        name: form.name, email: form.email, phone: form.phone,
        designation: form.designation || null,
        basicSalary: form.basicSalary || null,
        ratesPerVisit: form.ratesPerVisit || null,
        visitTargets: form.visitTargets || null,
        sampleBudget: form.sampleBudget,
        adminApproved: form.adminApproved,
        jobStatus: form.jobStatus,
        cityId: form.cityId ? parseInt(String(form.cityId)) : null,
      };
      if (!isEdit && form.password) body.password = form.password;
      const res = await fetch(url, {
        method, headers: { "Content-Type": "application/json" }, body: JSON.stringify(body),
      }).then(r => r.json());
      if (res.success) { onSaved(); onClose(); }
      else setError(res.error ?? "Failed");
    } finally { setSaving(false); }
  }

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/50 backdrop-blur-sm p-4">
      <div className="w-full max-w-lg rounded-2xl bg-white shadow-2xl max-h-[90vh] overflow-y-auto">
        <div className="sticky top-0 bg-white flex items-center justify-between border-b border-slate-100 px-6 py-4 z-10">
          <h2 className="text-base font-semibold text-slate-800">{isEdit ? `Edit: ${booker!.name}` : "Add New Officer"}</h2>
          <button onClick={onClose} className="rounded-lg p-1.5 hover:bg-slate-100"><X className="h-4 w-4 text-slate-500" /></button>
        </div>
        <form onSubmit={submit} className="p-6 space-y-4">
          {error && <p className="rounded-lg bg-red-50 border border-red-200 px-3 py-2 text-xs text-red-600">{error}</p>}

          <div className="grid grid-cols-2 gap-4">
            {[
              { label: "Full Name", key: "name", type: "text", full: true },
              { label: "Email", key: "email", type: "email" },
              { label: "Phone", key: "phone", type: "text" },
              { label: "Designation", key: "designation", type: "text", placeholder: "e.g. Sales Officer" },
            ].map(f => (
              <div key={f.key} className={(f as any).full ? "col-span-2" : ""}>
                <label className="block text-xs font-medium text-slate-600 mb-1">{f.label}</label>
                <input type={f.type} value={(form as any)[f.key]}
                  required={f.key !== "designation"}
                  placeholder={(f as any).placeholder}
                  onChange={e => set(f.key, e.target.value)}
                  className="w-full rounded-lg border border-slate-200 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-red-500/20 focus:border-red-500" />
              </div>
            ))}

            {!isEdit && (
              <div className="col-span-2">
                <label className="block text-xs font-medium text-slate-600 mb-1">Password</label>
                <input type="password" value={form.password} required={!isEdit}
                  onChange={e => set("password", e.target.value)} placeholder="Min 6 characters"
                  className="w-full rounded-lg border border-slate-200 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-[#C8102E]/20 focus:border-[#C8102E]" />
              </div>
            )}
          </div>

          <div className="border-t border-slate-100 pt-4">
            <p className="text-xs font-semibold text-slate-500 mb-3 uppercase tracking-wide">Salary & Targets</p>
            <div className="grid grid-cols-2 gap-4">
              {[
                { label: "Basic Salary (PKR/mo)", key: "basicSalary" },
                { label: "Rate Per Visit (PKR)", key: "ratesPerVisit" },
                { label: "Daily Visit Target", key: "visitTargets" },
                { label: "Sample Budget (PKR/yr)", key: "sampleBudget" },
              ].map(f => (
                <div key={f.key}>
                  <label className="block text-xs font-medium text-slate-600 mb-1">{f.label}</label>
                  <input type="number" value={(form as any)[f.key]}
                    onChange={e => set(f.key, e.target.value)}
                    className="w-full rounded-lg border border-slate-200 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-[#C8102E]/20 focus:border-[#C8102E]" />
                </div>
              ))}
            </div>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs font-medium text-slate-600 mb-1">Approval Status</label>
              <select value={form.adminApproved} onChange={e => set("adminApproved", e.target.value)}
                className="w-full rounded-lg border border-slate-200 px-3 py-2 text-sm focus:outline-none">
                <option value="PENDING">Pending</option>
                <option value="APPROVED">Approved</option>
                <option value="NOT_APPROVED">Not Approved</option>
              </select>
            </div>
            <div>
              <label className="block text-xs font-medium text-slate-600 mb-1">Job Status</label>
              <select value={form.jobStatus} onChange={e => set("jobStatus", e.target.value)}
                className="w-full rounded-lg border border-slate-200 px-3 py-2 text-sm focus:outline-none">
                <option value="ACTIVE">Active</option>
                <option value="NOT_ACTIVE">Not Active</option>
              </select>
            </div>
          </div>

          <div>
            <label className="block text-xs font-medium text-slate-600 mb-1">Assigned City</label>
            <select value={form.cityId} onChange={e => set("cityId", e.target.value)}
              className="w-full rounded-lg border border-slate-200 px-3 py-2 text-sm focus:outline-none">
              <option value="">— Select City —</option>
              {cities.map(c => (
                <option key={c.id} value={c.id}>{c.name}</option>
              ))}
            </select>
          </div>

          <div className="flex gap-3 pt-2">
            <button type="button" onClick={onClose} className="flex-1 rounded-lg border border-slate-200 py-2.5 text-sm font-medium text-slate-600 hover:bg-slate-50">Cancel</button>
            <button type="submit" disabled={saving} className="flex-1 rounded-lg bg-[#C8102E] py-2.5 text-sm font-medium text-white hover:bg-[#9B0B22] disabled:opacity-50 transition-colors">
              {saving ? "Saving…" : isEdit ? "Save Changes" : "Create Officer"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

// ── Reset Password Modal ───────────────────────────────────────────────────────
function ResetPasswordModal({ booker, onClose }: { booker: Booker; onClose: () => void }) {
  const [password, setPassword] = useState("");
  const [confirm, setConfirm]   = useState("");
  const [saving, setSaving]     = useState(false);
  const [success, setSuccess]   = useState(false);
  const [error, setError]       = useState("");

  async function submit(e: React.FormEvent) {
    e.preventDefault();
    if (password !== confirm) { setError("Passwords do not match"); return; }
    if (password.length < 6)  { setError("Minimum 6 characters"); return; }
    setSaving(true); setError("");
    try {
      const res = await fetch(`/api/v1/bookers/${booker.id}`, {
        method: "PATCH", headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ newPassword: password }),
      }).then(r => r.json());
      if (res.success) setSuccess(true);
      else setError(res.error ?? "Failed");
    } finally { setSaving(false); }
  }

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/50 backdrop-blur-sm p-4">
      <div className="w-full max-w-sm rounded-2xl bg-white shadow-2xl">
        <div className="flex items-center justify-between border-b border-slate-100 px-6 py-4">
          <div>
            <h2 className="text-base font-semibold text-slate-800">Reset Password</h2>
            <p className="text-xs text-slate-400 mt-0.5">{booker.name} · {booker.email}</p>
          </div>
          <button onClick={onClose} className="rounded-lg p-1.5 hover:bg-slate-100"><X className="h-4 w-4 text-slate-500" /></button>
        </div>
        <div className="p-6">
          {success ? (
            <div className="text-center space-y-3">
              <div className="flex h-12 w-12 mx-auto items-center justify-center rounded-full bg-emerald-100">
                <CheckCircle className="h-6 w-6 text-emerald-600" />
              </div>
              <p className="font-semibold text-slate-800">Password Updated!</p>
              <p className="text-xs text-slate-500">The officer can now log in with the new password.</p>
              <button onClick={onClose} className="w-full rounded-lg bg-[#0f1e3c] py-2.5 text-sm font-medium text-white">Done</button>
            </div>
          ) : (
            <form onSubmit={submit} className="space-y-4">
              {error && <p className="rounded-lg bg-red-50 border border-red-200 px-3 py-2 text-xs text-red-600">{error}</p>}
              <div>
                <label className="block text-xs font-medium text-slate-600 mb-1">New Password</label>
                <input type="password" value={password} onChange={e => setPassword(e.target.value)} required
                  placeholder="Min 6 characters"
                  className="w-full rounded-lg border border-slate-200 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-[#C8102E]/20 focus:border-[#C8102E]" />
              </div>
              <div>
                <label className="block text-xs font-medium text-slate-600 mb-1">Confirm Password</label>
                <input type="password" value={confirm} onChange={e => setConfirm(e.target.value)} required
                  placeholder="Repeat password"
                  className="w-full rounded-lg border border-slate-200 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-[#C8102E]/20 focus:border-[#C8102E]" />
              </div>
              <div className="flex gap-3 pt-1">
                <button type="button" onClick={onClose} className="flex-1 rounded-lg border border-slate-200 py-2.5 text-sm font-medium text-slate-600 hover:bg-slate-50">Cancel</button>
                <button type="submit" disabled={saving} className="flex-1 rounded-lg bg-amber-500 py-2.5 text-sm font-medium text-white hover:bg-amber-600 disabled:opacity-50">
                  {saving ? "Saving…" : "Reset Password"}
                </button>
              </div>
            </form>
          )}
        </div>
      </div>
    </div>
  );
}

// ── Track Modal ────────────────────────────────────────────────────────────────
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
            <div className="rounded-xl bg-slate-50 p-3 col-span-2"><p className="text-slate-400 mb-1">Coordinates</p><p className="font-mono font-medium text-slate-700">{hasCoords ? `${booker.lastLatitude}, ${booker.lastLongitude}` : "No location data"}</p></div>
          </div>
          {mapUrl ? (
            <iframe src={mapUrl} className="w-full h-56 rounded-xl border border-slate-200" title="Officer location" />
          ) : (
            <div className="h-40 rounded-xl bg-slate-50 flex items-center justify-center text-slate-400 text-sm">No GPS data — officer needs to start day</div>
          )}
          {hasCoords && (
            <a href={`https://www.openstreetmap.org/?mlat=${booker.lastLatitude}&mlon=${booker.lastLongitude}`} target="_blank" rel="noopener noreferrer"
              className="flex items-center justify-center gap-2 w-full rounded-lg bg-[#C8102E] py-2 text-sm font-medium text-white hover:bg-[#C8102E]">
              <Navigation className="h-4 w-4" /> Open in Maps
            </a>
          )}
        </div>
      </div>
    </div>
  );
}

// ── Main Component ─────────────────────────────────────────────────────────────
export default function BookersClient() {
  const [rows, setRows]         = useState<Booker[]>([]);
  const [total, setTotal]       = useState(0);
  const [loading, setLoading]   = useState(true);
  const [search, setSearch]     = useState("");
  const [gpsFilter, setGpsFilter]       = useState("all");
  const [approvalFilter, setApprovalFilter] = useState("all");
  const [approving, setApproving] = useState<number | null>(null);
  const [deleting, setDeleting]   = useState<number | null>(null);
  const [showCreate, setShowCreate] = useState(false);
  const [editing, setEditing]       = useState<Booker | null>(null);
  const [resetting, setResetting]   = useState<Booker | null>(null);
  const [tracking, setTracking]     = useState<Booker | null>(null);

  async function load() {
    setLoading(true);
    try {
      const res = await fetch("/api/v1/bookers?length=200").then(r => r.json());
      if (res.success) { setRows(res.data?.data ?? []); setTotal(res.data?.recordsTotal ?? 0); }
    } catch (e) { console.error(e); }
    finally { setLoading(false); }
  }

  async function approveBooker(id: number) {
    setApproving(id);
    try {
      await fetch(`/api/v1/bookers/${id}`, { method: "PATCH", headers: { "Content-Type": "application/json" }, body: JSON.stringify({ adminApproved: "APPROVED", jobStatus: "ACTIVE" }) });
      await load();
    } finally { setApproving(null); }
  }

  async function deleteBooker(id: number, name: string) {
    if (!confirm(`Remove ${name}? This will deactivate their account.`)) return;
    setDeleting(id);
    try { await fetch(`/api/v1/bookers/${id}`, { method: "DELETE" }); await load(); }
    finally { setDeleting(null); }
  }

  useEffect(() => { load(); }, []);

  const filtered = rows.filter(r => {
    const matchSearch = !search ||
      r.name?.toLowerCase().includes(search.toLowerCase()) ||
      r.email?.toLowerCase().includes(search.toLowerCase()) ||
      r.phone?.includes(search) ||
      (r.city?.name ?? "").toLowerCase().includes(search.toLowerCase()) ||
      (r.designation ?? "").toLowerCase().includes(search.toLowerCase());
    const matchGps = gpsFilter === "all" || r.gpsStatus === gpsFilter;
    const matchApproval = approvalFilter === "all" || r.adminApproved === approvalFilter;
    return matchSearch && matchGps && matchApproval;
  });

  const active  = rows.filter(r => r.gpsStatus === "ACTIVE").length;
  const pending = rows.filter(r => r.adminApproved === "PENDING").length;

  return (
    <div className="space-y-4">
      {showCreate && <OfficerModal onClose={() => setShowCreate(false)} onSaved={load} />}
      {editing    && <OfficerModal booker={editing} onClose={() => setEditing(null)} onSaved={load} />}
      {resetting  && <ResetPasswordModal booker={resetting} onClose={() => setResetting(null)} />}
      {tracking   && <TrackModal booker={tracking} onClose={() => setTracking(null)} />}

      <div className="flex flex-col gap-3 sm:flex-row sm:items-center sm:justify-between flex-wrap">
        <div className="relative flex-1 min-w-56">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
          <input value={search} onChange={e => setSearch(e.target.value)} placeholder="Search by name, email, phone, city…"
            className="w-full rounded-lg border border-slate-200 bg-white pl-9 pr-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-[#C8102E]/20 focus:border-[#C8102E] transition" />
        </div>
        <div className="relative">
          <Filter className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
          <select value={gpsFilter} onChange={e => setGpsFilter(e.target.value)}
            className="pl-9 pr-7 py-2 rounded-lg border border-slate-200 bg-white text-sm focus:outline-none appearance-none cursor-pointer">
            <option value="all">All GPS</option>
            <option value="ACTIVE">Active</option>
            <option value="IDLE">Idle</option>
            <option value="OFFLINE">Offline</option>
          </select>
          <ChevronDown className="absolute right-2 top-1/2 -translate-y-1/2 h-3.5 w-3.5 text-slate-400 pointer-events-none" />
        </div>
        <div className="relative">
          <select value={approvalFilter} onChange={e => setApprovalFilter(e.target.value)}
            className="px-3 pr-7 py-2 rounded-lg border border-slate-200 bg-white text-sm focus:outline-none appearance-none cursor-pointer">
            <option value="all">All Status</option>
            <option value="APPROVED">Approved</option>
            <option value="PENDING">Pending</option>
            <option value="NOT_APPROVED">Not Approved</option>
          </select>
          <ChevronDown className="absolute right-2 top-1/2 -translate-y-1/2 h-3.5 w-3.5 text-slate-400 pointer-events-none" />
        </div>
        <div className="flex gap-2">
          <button onClick={load} disabled={loading} className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50">
            <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} /> Refresh
          </button>
          <button onClick={() => setShowCreate(true)} className="flex items-center gap-1.5 rounded-lg bg-[#0f1e3c] px-3 py-2 text-xs font-medium text-white hover:bg-[#1a3060]">
            <Plus className="h-3.5 w-3.5" /> Add Officer
          </button>
        </div>
      </div>

      <div className="grid grid-cols-3 gap-3">
        {[
          { label: "Total Officers", value: total,   color: "text-blue-700"    },
          { label: "Active Now",     value: active,  color: "text-emerald-700" },
          { label: "Need Approval",  value: pending, color: "text-amber-700"   },
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
              <tr><td colSpan={7} className="px-4 py-8 text-center text-sm text-slate-400">
                No officers found · <button onClick={() => setShowCreate(true)} className="text-[#C8102E] underline">Add one</button>
              </td></tr>
            )}
            {!loading && filtered.map((r, i) => (
              <tr key={r.id} className="hover:bg-slate-50/50 transition-colors">
                <td className="px-4 py-3 text-slate-400 text-xs">{i + 1}</td>
                <td className="px-4 py-3">
                  <div className="flex items-center gap-3">
                    <div className="flex h-8 w-8 shrink-0 items-center justify-center rounded-full bg-gradient-to-br from-[#0f1e3c] to-[#1a5276] text-xs font-bold text-white">
                      {r.name?.[0]?.toUpperCase() ?? "B"}
                    </div>
                    <div>
                      <p className="text-xs font-medium text-slate-800">{r.name}</p>
                      <p className="text-xs text-slate-400">{r.email}</p>
                    </div>
                  </div>
                </td>
                <td className="px-4 py-3 text-xs text-slate-500">{stripHtml(r.city?.name)}</td>
                <td className="px-4 py-3 text-xs text-slate-500">{r.phone}</td>
                <td className="px-4 py-3"><GpsBadge status={r.gpsStatus} /></td>
                <td className="px-4 py-3"><ApprovalBadge status={r.adminApproved} /></td>
                <td className="px-4 py-3">
                  <div className="flex items-center gap-1">
                    <button onClick={() => setTracking(r)} title="Track GPS"
                      className="p-1.5 rounded-lg text-slate-400 hover:text-[#C8102E] hover:bg-[#C8102E] transition-colors">
                      <MapPin className="h-3.5 w-3.5" />
                    </button>
                    <button onClick={() => setEditing(r)} title="Edit Officer"
                      className="p-1.5 rounded-lg text-slate-400 hover:text-blue-600 hover:bg-blue-50 transition-colors">
                      <Pencil className="h-3.5 w-3.5" />
                    </button>
                    <button onClick={() => setResetting(r)} title="Reset Password"
                      className="p-1.5 rounded-lg text-slate-400 hover:text-amber-600 hover:bg-amber-50 transition-colors">
                      <KeyRound className="h-3.5 w-3.5" />
                    </button>
                    {r.adminApproved === "PENDING" && (
                      <button onClick={() => approveBooker(r.id)} disabled={approving === r.id}
                        className="rounded-lg bg-emerald-600 px-2 py-1 text-xs font-medium text-white hover:bg-emerald-700 disabled:opacity-50">
                        {approving === r.id ? "…" : "Approve"}
                      </button>
                    )}
                    <button onClick={() => deleteBooker(r.id, r.name)} disabled={deleting === r.id} title="Remove officer"
                      className="p-1.5 rounded-lg text-slate-400 hover:text-red-600 hover:bg-red-50 transition-colors">
                      {deleting === r.id ? <span className="text-xs">…</span> : <Trash2 className="h-3.5 w-3.5" />}
                    </button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="border-t border-slate-100 px-4 py-3 flex items-center justify-between">
          <p className="text-xs text-slate-500">Showing {filtered.length} of {total} officers</p>
          <button onClick={() => setShowCreate(true)} className="text-xs font-medium text-[#C8102E] hover:text-[#C8102E]">+ Add New Officer</button>
        </div>
      </div>
    </div>
  );
}
