"use client";

import { useEffect, useState } from "react";
import {
  Search, RefreshCw, CheckCircle, XCircle, Clock, Package,
  Filter, User, ChevronDown, Eye, X, FileText,
} from "lucide-react";

interface SampleRequest {
  id: number;
  productName: string;
  quantity: number;
  notes: string | null;
  status: string;
  adminNotes: string | null;
  customerName: string | null;
  deliveredAt: string | null;
  createdAt: string;
  booker: { id: number; name: string; email: string };
  customer: { id: number; name: string } | null;
}

function StatusBadge({ status }: { status: string }) {
  const s = status.toLowerCase();
  if (s === "approved") return (
    <span className="inline-flex items-center gap-1 rounded-full px-2 py-0.5 text-xs font-semibold bg-emerald-100 text-emerald-700">
      <CheckCircle className="h-3 w-3" />Approved
    </span>
  );
  if (s === "rejected") return (
    <span className="inline-flex items-center gap-1 rounded-full px-2 py-0.5 text-xs font-semibold bg-red-100 text-red-700">
      <XCircle className="h-3 w-3" />Rejected
    </span>
  );
  if (s === "delivered") return (
    <span className="inline-flex items-center gap-1 rounded-full px-2 py-0.5 text-xs font-semibold bg-blue-100 text-blue-700">
      <Package className="h-3 w-3" />Delivered
    </span>
  );
  return (
    <span className="inline-flex items-center gap-1 rounded-full px-2 py-0.5 text-xs font-semibold bg-amber-100 text-amber-700">
      <Clock className="h-3 w-3" />Pending
    </span>
  );
}

function ReviewModal({ sample, onClose, onDone }: {
  sample: SampleRequest; onClose: () => void; onDone: () => void;
}) {
  const [action, setAction] = useState<"approved" | "rejected">("approved");
  const [notes, setNotes] = useState(sample.adminNotes ?? "");
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState("");

  async function submit() {
    setSaving(true); setError("");
    try {
      const res = await fetch(`/api/v1/samples/${sample.id}`, {
        method: "PATCH",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ status: action, adminNotes: notes }),
      });
      const d = await res.json();
      if (!d.success) throw new Error(d.error?.message ?? "Failed");
      onDone();
    } catch (e: any) {
      setError(e.message);
    } finally {
      setSaving(false);
    }
  }

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50 p-4">
      <div className="bg-white rounded-2xl shadow-2xl w-full max-w-md">
        <div className="flex items-center justify-between px-6 py-4 border-b">
          <h2 className="text-lg font-bold text-gray-900">Review Sample Request</h2>
          <button onClick={onClose} className="p-2 rounded-full hover:bg-gray-100 transition">
            <X className="h-5 w-5 text-gray-500" />
          </button>
        </div>
        <div className="p-6 space-y-4">
          <div className="rounded-xl bg-gray-50 p-4 space-y-2">
            <div className="flex justify-between text-sm">
              <span className="text-gray-500">Officer</span>
              <span className="font-medium text-gray-900">{sample.booker.name}</span>
            </div>
            <div className="flex justify-between text-sm">
              <span className="text-gray-500">Product</span>
              <span className="font-medium text-gray-900">{sample.productName}</span>
            </div>
            <div className="flex justify-between text-sm">
              <span className="text-gray-500">Quantity</span>
              <span className="font-medium text-gray-900">{sample.quantity}</span>
            </div>
            {sample.notes && (
              <div className="flex justify-between text-sm">
                <span className="text-gray-500">Notes</span>
                <span className="font-medium text-gray-900 text-right max-w-[60%]">{sample.notes}</span>
              </div>
            )}
          </div>

          <div>
            <label className="text-sm font-semibold text-gray-700 block mb-2">Decision</label>
            <div className="grid grid-cols-2 gap-3">
              <button
                onClick={() => setAction("approved")}
                className={`flex items-center justify-center gap-2 py-2.5 rounded-xl border-2 text-sm font-semibold transition-all ${
                  action === "approved"
                    ? "border-emerald-500 bg-emerald-50 text-emerald-700"
                    : "border-gray-200 text-gray-500 hover:border-emerald-300"
                }`}
              >
                <CheckCircle className="h-4 w-4" /> Approve
              </button>
              <button
                onClick={() => setAction("rejected")}
                className={`flex items-center justify-center gap-2 py-2.5 rounded-xl border-2 text-sm font-semibold transition-all ${
                  action === "rejected"
                    ? "border-red-500 bg-red-50 text-red-700"
                    : "border-gray-200 text-gray-500 hover:border-red-300"
                }`}
              >
                <XCircle className="h-4 w-4" /> Reject
              </button>
            </div>
          </div>

          <div>
            <label className="text-sm font-semibold text-gray-700 block mb-1.5">Admin Notes (optional)</label>
            <textarea
              value={notes}
              onChange={e => setNotes(e.target.value)}
              rows={3}
              className="w-full border border-gray-200 rounded-xl px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-[#C8102E] resize-none"
              placeholder="Reason for decision..."
            />
          </div>

          {error && <p className="text-sm text-red-600">{error}</p>}
        </div>
        <div className="flex gap-3 px-6 pb-6">
          <button
            onClick={onClose}
            className="flex-1 py-2.5 rounded-xl border border-gray-200 text-sm font-semibold text-gray-600 hover:bg-gray-50 transition"
          >
            Cancel
          </button>
          <button
            onClick={submit}
            disabled={saving}
            className={`flex-1 py-2.5 rounded-xl text-sm font-semibold text-white transition ${
              action === "approved"
                ? "bg-emerald-600 hover:bg-emerald-700 disabled:bg-emerald-300"
                : "bg-[#C8102E] hover:bg-[#a00e25] disabled:bg-red-300"
            }`}
          >
            {saving ? "Saving…" : action === "approved" ? "Approve" : "Reject"}
          </button>
        </div>
      </div>
    </div>
  );
}

function SignatureModal({ sample, onClose }: { sample: SampleRequest; onClose: () => void }) {
  if (!sample.signatureBase64) return null;
  return (
    <div className="fixed inset-0 bg-black/60 flex items-center justify-center z-50 p-4">
      <div className="bg-white rounded-2xl shadow-2xl w-full max-w-sm">
        <div className="flex items-center justify-between px-5 py-4 border-b">
          <h2 className="text-base font-bold">Customer Signature</h2>
          <button onClick={onClose} className="p-1.5 rounded-full hover:bg-gray-100">
            <X className="h-4 w-4" />
          </button>
        </div>
        <div className="p-5">
          <p className="text-sm text-gray-500 mb-3">Signed by: <span className="font-medium text-gray-800">{sample.customerName ?? "Unknown"}</span></p>
          <img
            src={`data:image/png;base64,${sample.signatureBase64}`}
            alt="Customer signature"
            className="w-full border rounded-xl bg-gray-50"
          />
        </div>
      </div>
    </div>
  );
}

export default function SamplesClient() {
  const [samples, setSamples] = useState<SampleRequest[]>([]);
  const [loading, setLoading] = useState(true);
  const [statusFilter, setStatusFilter] = useState("all");
  const [search, setSearch] = useState("");
  const [reviewing, setReviewing] = useState<SampleRequest | null>(null);
  const [viewingSig, setViewingSig] = useState<SampleRequest | null>(null);

  async function load(status?: string) {
    setLoading(true);
    try {
      const q = status && status !== "all" ? `?status=${status}` : "";
      const res = await fetch(`/api/v1/samples${q}`);
      const d = await res.json();
      if (d.success) setSamples(d.data ?? []);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => { load(statusFilter); }, [statusFilter]);

  const filtered = samples.filter(s => {
    if (!search) return true;
    const q = search.toLowerCase();
    return (
      s.booker.name.toLowerCase().includes(q) ||
      s.productName.toLowerCase().includes(q) ||
      (s.customerName ?? "").toLowerCase().includes(q)
    );
  });

  const stats = {
    total: samples.length,
    pending: samples.filter(s => s.status === "pending").length,
    approved: samples.filter(s => s.status === "approved").length,
    rejected: samples.filter(s => s.status === "rejected").length,
    delivered: samples.filter(s => s.status === "delivered").length,
  };

  return (
    <div className="p-6 space-y-6">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-2xl font-bold text-gray-900">Sample Management</h1>
          <p className="text-sm text-gray-500 mt-0.5">Review and approve sample requests from field officers</p>
        </div>
        <button onClick={() => load(statusFilter)} className="flex items-center gap-2 px-4 py-2 rounded-xl bg-gray-100 text-gray-700 text-sm font-medium hover:bg-gray-200 transition">
          <RefreshCw className="h-4 w-4" /> Refresh
        </button>
      </div>

      {/* Stats Row */}
      <div className="grid grid-cols-5 gap-4">
        {[
          { label: "Total", value: stats.total, color: "bg-gray-50 text-gray-700" },
          { label: "Pending", value: stats.pending, color: "bg-amber-50 text-amber-700" },
          { label: "Approved", value: stats.approved, color: "bg-emerald-50 text-emerald-700" },
          { label: "Rejected", value: stats.rejected, color: "bg-red-50 text-red-700" },
          { label: "Delivered", value: stats.delivered, color: "bg-blue-50 text-blue-700" },
        ].map(stat => (
          <div key={stat.label} className={`rounded-2xl p-4 ${stat.color}`}>
            <p className="text-2xl font-bold">{stat.value}</p>
            <p className="text-xs font-medium mt-0.5 opacity-80">{stat.label}</p>
          </div>
        ))}
      </div>

      {/* Filters Bar */}
      <div className="flex items-center gap-3 flex-wrap">
        <div className="flex-1 min-w-64 relative">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-gray-400" />
          <input
            value={search}
            onChange={e => setSearch(e.target.value)}
            className="w-full pl-9 pr-4 py-2.5 border border-gray-200 rounded-xl text-sm focus:outline-none focus:ring-2 focus:ring-[#C8102E]"
            placeholder="Search by officer, product, or customer…"
          />
        </div>
        <div className="relative">
          <Filter className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-gray-400" />
          <select
            value={statusFilter}
            onChange={e => setStatusFilter(e.target.value)}
            className="pl-9 pr-8 py-2.5 border border-gray-200 rounded-xl text-sm bg-white focus:outline-none focus:ring-2 focus:ring-[#C8102E] appearance-none cursor-pointer"
          >
            <option value="all">All Status</option>
            <option value="pending">Pending</option>
            <option value="approved">Approved</option>
            <option value="rejected">Rejected</option>
            <option value="delivered">Delivered</option>
          </select>
          <ChevronDown className="absolute right-2.5 top-1/2 -translate-y-1/2 h-4 w-4 text-gray-400 pointer-events-none" />
        </div>
      </div>

      {/* Table */}
      <div className="bg-white rounded-2xl border border-gray-100 shadow-sm overflow-hidden">
        {loading ? (
          <div className="flex items-center justify-center h-40">
            <div className="h-8 w-8 rounded-full border-2 border-[#C8102E] border-t-transparent animate-spin" />
          </div>
        ) : filtered.length === 0 ? (
          <div className="flex flex-col items-center justify-center py-16 text-gray-400">
            <Package className="h-12 w-12 mb-3 opacity-30" />
            <p className="text-sm font-medium">No sample requests found</p>
          </div>
        ) : (
          <div className="overflow-x-auto">
            <table className="w-full text-sm">
              <thead>
                <tr className="bg-gray-50 border-b border-gray-100">
                  {["#", "Officer", "Product", "Qty", "Customer", "Status", "Submitted", "Actions"].map(h => (
                    <th key={h} className="text-left text-xs font-semibold text-gray-500 uppercase tracking-wide px-4 py-3">{h}</th>
                  ))}
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-50">
                {filtered.map(s => (
                  <tr key={s.id} className="hover:bg-gray-50/60 transition-colors">
                    <td className="px-4 py-3 font-mono text-gray-400">#{s.id}</td>
                    <td className="px-4 py-3">
                      <div className="font-semibold text-gray-900">{s.booker.name}</div>
                      <div className="text-xs text-gray-400">{s.booker.email}</div>
                    </td>
                    <td className="px-4 py-3 font-medium text-gray-800">{s.productName}</td>
                    <td className="px-4 py-3 text-gray-600">{s.quantity}</td>
                    <td className="px-4 py-3 text-gray-600">
                      {s.customer?.name ?? s.customerName ?? <span className="text-gray-300">—</span>}
                    </td>
                    <td className="px-4 py-3"><StatusBadge status={s.status} /></td>
                    <td className="px-4 py-3 text-gray-500">
                      {new Date(s.createdAt).toLocaleDateString("en-PK", { day: "2-digit", month: "short", year: "numeric" })}
                    </td>
                    <td className="px-4 py-3">
                      <div className="flex items-center gap-1.5">
                        {s.status === "pending" && (
                          <button
                            onClick={() => setReviewing(s)}
                            className="flex items-center gap-1 px-2.5 py-1.5 rounded-lg bg-[#C8102E] text-white text-xs font-semibold hover:bg-[#a00e25] transition"
                          >
                            <Eye className="h-3 w-3" /> Review
                          </button>
                        )}
                        {s.signatureBase64 && (
                          <button
                            onClick={() => setViewingSig(s)}
                            className="flex items-center gap-1 px-2.5 py-1.5 rounded-lg bg-blue-50 text-blue-700 text-xs font-semibold hover:bg-blue-100 transition"
                          >
                            <FileText className="h-3 w-3" /> Sig
                          </button>
                        )}
                        {!s.signatureBase64 && s.adminNotes && (
                          <span className="text-xs text-gray-400 italic">"{s.adminNotes}"</span>
                        )}
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>

      {reviewing && (
        <ReviewModal
          sample={reviewing}
          onClose={() => setReviewing(null)}
          onDone={() => { setReviewing(null); load(statusFilter); }}
        />
      )}
      {viewingSig && (
        <SignatureModal sample={viewingSig} onClose={() => setViewingSig(null)} />
      )}
    </div>
  );
}
