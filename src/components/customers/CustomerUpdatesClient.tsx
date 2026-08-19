"use client";

import { useEffect, useState, useCallback } from "react";
import { CheckCircle, XCircle, Clock, RefreshCw, User, FileEdit, ChevronDown, ChevronUp } from "lucide-react";

interface UpdateRequest {
  id: number;
  title: string;
  details: string;
  status: string;
  adminNotes: string | null;
  createdAt: string;
  booker: { id: number; name: string; email: string };
  detail: {
    customerId?: number;
    customerName?: string;
    updates?: Record<string, string>;
    officerNotes?: string;
  };
}

function StatusBadge({ status }: { status: string }) {
  if (status === "RESOLVED") return (
    <span className="inline-flex items-center gap-1 rounded-full border px-2 py-0.5 text-xs font-medium bg-green-50 text-green-700 border-green-200">
      <CheckCircle className="h-3 w-3" /> Approved
    </span>
  );
  if (status === "REJECTED") return (
    <span className="inline-flex items-center gap-1 rounded-full border px-2 py-0.5 text-xs font-medium bg-red-50 text-red-700 border-red-200">
      <XCircle className="h-3 w-3" /> Rejected
    </span>
  );
  return (
    <span className="inline-flex items-center gap-1 rounded-full border px-2 py-0.5 text-xs font-medium bg-amber-50 text-amber-700 border-amber-200">
      <Clock className="h-3 w-3" /> Pending
    </span>
  );
}

function fieldLabel(key: string) {
  const map: Record<string, string> = {
    name: "Customer Name", ownerName: "Owner Name", ownerPhone: "Phone",
    email: "Email", address: "Address", category: "Category",
  };
  return map[key] ?? key;
}

function RequestCard({ req, onRefresh }: { req: UpdateRequest; onRefresh: () => void }) {
  const [expanded, setExpanded] = useState(false);
  const [acting, setActing] = useState(false);
  const [notes, setNotes] = useState("");
  const [error, setError] = useState("");

  async function act(action: "approve" | "reject") {
    setActing(true); setError("");
    try {
      const res = await fetch(`/api/v1/customer-updates/${req.id}`, {
        method: "PATCH",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ action, adminNotes: notes }),
      }).then(r => r.json());
      if (res.success) { onRefresh(); }
      else setError(res.error ?? "Failed");
    } catch { setError("Network error"); }
    finally { setActing(false); }
  }

  const updates = req.detail?.updates ?? {};
  const hasUpdates = Object.keys(updates).length > 0;

  return (
    <div className="bg-white rounded-2xl border border-slate-200 overflow-hidden shadow-sm hover:shadow-md transition-shadow">
      {/* Header */}
      <div
        className="flex items-start gap-3 p-4 cursor-pointer select-none"
        onClick={() => setExpanded(v => !v)}
      >
        <div className="flex h-10 w-10 shrink-0 items-center justify-center rounded-xl bg-red-50 border border-red-100">
          <FileEdit className="h-5 w-5 text-red-600" />
        </div>
        <div className="flex-1 min-w-0">
          <div className="flex items-center justify-between gap-2 flex-wrap">
            <p className="text-sm font-semibold text-slate-800 truncate">{req.detail?.customerName ?? req.title}</p>
            <StatusBadge status={req.status} />
          </div>
          <div className="flex items-center gap-2 mt-1 flex-wrap">
            <span className="flex items-center gap-1 text-xs text-slate-500">
              <User className="h-3 w-3" /> {req.booker.name}
            </span>
            <span className="text-slate-300">·</span>
            <span className="text-xs text-slate-400">
              {new Date(req.createdAt).toLocaleDateString("en-PK", { day: "numeric", month: "short", year: "numeric" })}
            </span>
            <span className="text-slate-300">·</span>
            <span className="text-xs text-slate-400">{Object.keys(updates).length} field(s) changed</span>
          </div>
        </div>
        {expanded ? <ChevronUp className="h-4 w-4 text-slate-400 shrink-0 mt-1" /> : <ChevronDown className="h-4 w-4 text-slate-400 shrink-0 mt-1" />}
      </div>

      {/* Expanded detail */}
      {expanded && (
        <div className="border-t border-slate-100 p-4 space-y-4">
          {/* Proposed changes table */}
          {hasUpdates && (
            <div>
              <p className="text-xs font-semibold text-slate-500 uppercase tracking-wide mb-2">Proposed Changes</p>
              <div className="rounded-xl border border-slate-200 overflow-hidden">
                <table className="w-full text-sm">
                  <thead className="bg-slate-50">
                    <tr>
                      <th className="text-left px-3 py-2 text-xs font-medium text-slate-500 w-1/3">Field</th>
                      <th className="text-left px-3 py-2 text-xs font-medium text-slate-500">New Value</th>
                    </tr>
                  </thead>
                  <tbody className="divide-y divide-slate-100">
                    {Object.entries(updates).map(([k, v]) => (
                      <tr key={k} className="hover:bg-slate-50">
                        <td className="px-3 py-2 text-slate-500 text-xs font-medium">{fieldLabel(k)}</td>
                        <td className="px-3 py-2 text-slate-800 font-medium">{v}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          )}

          {/* Officer notes */}
          {req.detail?.officerNotes && (
            <div className="rounded-xl bg-blue-50 border border-blue-100 px-3 py-2">
              <p className="text-xs font-semibold text-blue-600 mb-1">Officer Notes</p>
              <p className="text-sm text-blue-800">{req.detail.officerNotes}</p>
            </div>
          )}

          {/* Admin action */}
          {req.status === "PENDING" && (
            <div className="space-y-3">
              <div>
                <label className="block text-xs font-medium text-slate-500 mb-1">Admin Notes (optional)</label>
                <textarea
                  value={notes}
                  onChange={e => setNotes(e.target.value)}
                  rows={2}
                  placeholder="Add a note for the officer..."
                  className="w-full rounded-xl border border-slate-200 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-red-500/20 focus:border-red-400 resize-none"
                />
              </div>
              {error && <p className="text-xs text-red-600 bg-red-50 px-3 py-2 rounded-lg border border-red-100">{error}</p>}
              <div className="flex gap-3">
                <button
                  onClick={() => act("approve")}
                  disabled={acting}
                  className="flex-1 flex items-center justify-center gap-2 rounded-xl bg-green-600 hover:bg-green-700 disabled:opacity-50 text-white text-sm font-semibold px-4 py-2.5 transition-colors"
                >
                  <CheckCircle className="h-4 w-4" />
                  {acting ? "Applying…" : "Approve & Apply"}
                </button>
                <button
                  onClick={() => act("reject")}
                  disabled={acting}
                  className="flex-1 flex items-center justify-center gap-2 rounded-xl bg-red-600 hover:bg-red-700 disabled:opacity-50 text-white text-sm font-semibold px-4 py-2.5 transition-colors"
                >
                  <XCircle className="h-4 w-4" />
                  {acting ? "Rejecting…" : "Reject"}
                </button>
              </div>
            </div>
          )}

          {/* Resolved state */}
          {req.status !== "PENDING" && req.adminNotes && (
            <div className={`rounded-xl px-3 py-2 border ${req.status === "RESOLVED" ? "bg-green-50 border-green-100" : "bg-red-50 border-red-100"}`}>
              <p className={`text-xs font-semibold mb-1 ${req.status === "RESOLVED" ? "text-green-600" : "text-red-600"}`}>Admin Response</p>
              <p className={`text-sm ${req.status === "RESOLVED" ? "text-green-800" : "text-red-800"}`}>{req.adminNotes}</p>
            </div>
          )}
        </div>
      )}
    </div>
  );
}

export default function CustomerUpdatesClient() {
  const [requests, setRequests] = useState<UpdateRequest[]>([]);
  const [loading, setLoading] = useState(true);
  const [tab, setTab] = useState<"PENDING" | "RESOLVED" | "REJECTED">("PENDING");

  const load = useCallback(async () => {
    setLoading(true);
    try {
      const res = await fetch(`/api/v1/customer-updates?status=${tab}`).then(r => r.json());
      if (res.success) setRequests(res.data ?? []);
    } catch { /* ignore */ }
    finally { setLoading(false); }
  }, [tab]);

  useEffect(() => { load(); }, [load]);

  const counts = {
    PENDING: tab === "PENDING" ? requests.length : "…",
    RESOLVED: tab === "RESOLVED" ? requests.length : "…",
    REJECTED: tab === "REJECTED" ? requests.length : "…",
  };

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex items-center justify-between flex-wrap gap-3">
        <div>
          <h1 className="text-xl font-bold text-slate-900">Customer Update Requests</h1>
          <p className="text-sm text-slate-500 mt-0.5">Officers submit changes — you approve or reject them here.</p>
        </div>
        <button
          onClick={load}
          className="flex items-center gap-2 rounded-xl border border-slate-200 bg-white px-4 py-2 text-sm font-medium text-slate-600 hover:bg-slate-50 transition-colors shadow-sm"
        >
          <RefreshCw className={`h-4 w-4 ${loading ? "animate-spin" : ""}`} /> Refresh
        </button>
      </div>

      {/* Tabs */}
      <div className="flex gap-1 rounded-xl bg-slate-100 p-1 w-fit">
        {(["PENDING", "RESOLVED", "REJECTED"] as const).map(t => (
          <button
            key={t}
            onClick={() => setTab(t)}
            className={`rounded-lg px-4 py-1.5 text-sm font-medium transition-all ${
              tab === t
                ? "bg-white text-slate-900 shadow-sm"
                : "text-slate-500 hover:text-slate-700"
            }`}
          >
            {t === "PENDING" ? "Pending" : t === "RESOLVED" ? "Approved" : "Rejected"}
            {tab === t && typeof counts[t] === "number" && (
              <span className="ml-1.5 rounded-full bg-red-100 text-red-700 px-1.5 py-0.5 text-xs font-bold">
                {counts[t]}
              </span>
            )}
          </button>
        ))}
      </div>

      {/* Content */}
      {loading ? (
        <div className="space-y-3">
          {[1, 2, 3].map(i => (
            <div key={i} className="h-20 rounded-2xl bg-slate-100 animate-pulse" />
          ))}
        </div>
      ) : requests.length === 0 ? (
        <div className="flex flex-col items-center justify-center py-20 text-center">
          <div className="rounded-2xl bg-slate-100 p-5 mb-4">
            <FileEdit className="h-10 w-10 text-slate-400" />
          </div>
          <p className="text-slate-600 font-medium">No {tab.toLowerCase()} requests</p>
          <p className="text-slate-400 text-sm mt-1">
            {tab === "PENDING"
              ? "Officers haven't submitted any updates yet."
              : "Nothing here yet."}
          </p>
        </div>
      ) : (
        <div className="space-y-3">
          {requests.map(r => (
            <RequestCard key={r.id} req={r} onRefresh={load} />
          ))}
        </div>
      )}
    </div>
  );
}
