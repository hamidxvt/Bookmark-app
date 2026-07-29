"use client";

import { useEffect, useState } from "react";
import { CheckCircle, XCircle, Clock, RefreshCw, Filter } from "lucide-react";
import { format } from "date-fns";

interface Leave {
  id: number;
  leaveType: string;
  fromDate: string;
  toDate: string;
  reason: string;
  status: string;
  adminNotes: string | null;
  createdAt: string;
  booker: { id: number; name: string; email: string; phone: string };
}

const STATUS_COLORS: Record<string, string> = {
  pending: "bg-amber-100 text-amber-700 border-amber-200",
  approved: "bg-emerald-100 text-emerald-700 border-emerald-200",
  rejected: "bg-red-100 text-red-700 border-red-200",
};

function days(from: string, to: string) {
  const d = Math.ceil((new Date(to).getTime() - new Date(from).getTime()) / 86400000) + 1;
  return d === 1 ? "1 day" : `${d} days`;
}

export default function LeavesClient() {
  const [leaves, setLeaves] = useState<Leave[]>([]);
  const [filter, setFilter] = useState("pending");
  const [loading, setLoading] = useState(true);
  const [actionId, setActionId] = useState<number | null>(null);
  const [note, setNote] = useState("");
  const [noteModal, setNoteModal] = useState<{ id: number; action: "approved" | "rejected" } | null>(null);

  async function load() {
    setLoading(true);
    try {
      const res = await fetch(`/api/v1/leaves?status=${filter}`).then(r => r.json());
      if (res.success) setLeaves(res.data);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => { load(); }, [filter]);

  async function handleAction(id: number, status: "approved" | "rejected") {
    setActionId(id);
    try {
      const res = await fetch("/api/v1/leaves", {
        method: "PATCH",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ id, status, adminNotes: note }),
      }).then(r => r.json());
      if (res.success) {
        setNoteModal(null);
        setNote("");
        load();
      }
    } finally {
      setActionId(null);
    }
  }

  const pending = leaves.filter(l => l.status === "pending").length;

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h2 className="text-xl font-bold text-slate-900">Leave Requests</h2>
          <p className="text-sm text-slate-500 mt-0.5">
            Review and approve leave applications from bookers
          </p>
        </div>
        <div className="flex items-center gap-2">
          {pending > 0 && (
            <span className="flex h-6 min-w-6 items-center justify-center rounded-full bg-red-500 px-1.5 text-xs font-bold text-white">
              {pending}
            </span>
          )}
          <button
            onClick={load}
            disabled={loading}
            className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50 transition-colors"
          >
            <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} />
            Refresh
          </button>
        </div>
      </div>

      {/* Filter tabs */}
      <div className="flex gap-1 rounded-xl bg-slate-100 p-1 w-fit">
        {["pending", "approved", "rejected"].map(s => (
          <button
            key={s}
            onClick={() => setFilter(s)}
            className={`rounded-lg px-4 py-1.5 text-xs font-medium capitalize transition-all ${
              filter === s ? "bg-white shadow-sm text-slate-900" : "text-slate-500 hover:text-slate-700"
            }`}
          >
            {s}
          </button>
        ))}
        <button
          onClick={() => setFilter("")}
          className={`flex items-center gap-1 rounded-lg px-4 py-1.5 text-xs font-medium transition-all ${
            filter === "" ? "bg-white shadow-sm text-slate-900" : "text-slate-500 hover:text-slate-700"
          }`}
        >
          <Filter className="h-3 w-3" />
          All
        </button>
      </div>

      {/* Table */}
      <div className="rounded-2xl border border-slate-200 bg-white shadow-xs overflow-hidden">
        {loading ? (
          <div className="divide-y divide-slate-50">
            {[...Array(5)].map((_, i) => (
              <div key={i} className="flex items-center gap-4 px-6 py-4">
                <div className="h-10 w-10 rounded-full bg-slate-100 animate-pulse" />
                <div className="flex-1 space-y-2">
                  <div className="h-3 w-48 rounded bg-slate-100 animate-pulse" />
                  <div className="h-2.5 w-32 rounded bg-slate-100 animate-pulse" />
                </div>
                <div className="h-6 w-20 rounded-full bg-slate-100 animate-pulse" />
              </div>
            ))}
          </div>
        ) : leaves.length === 0 ? (
          <div className="py-16 text-center">
            <CheckCircle className="mx-auto h-10 w-10 text-slate-200" />
            <p className="mt-3 text-sm text-slate-400">No {filter || ""} leave requests</p>
          </div>
        ) : (
          <div className="divide-y divide-slate-50">
            {leaves.map(leave => (
              <div key={leave.id} className="flex items-start gap-4 px-6 py-4 hover:bg-slate-50/50 transition-colors">
                <div className="flex h-10 w-10 shrink-0 items-center justify-center rounded-full bg-violet-100 text-sm font-bold text-violet-700">
                  {leave.booker.name[0]}
                </div>
                <div className="flex-1 min-w-0">
                  <div className="flex items-center gap-2 flex-wrap">
                    <p className="text-sm font-semibold text-slate-800">{leave.booker.name}</p>
                    <span className={`inline-flex items-center rounded-full border px-2 py-0.5 text-xs font-medium capitalize ${STATUS_COLORS[leave.status]}`}>
                      {leave.status}
                    </span>
                    <span className="rounded-full bg-slate-100 px-2 py-0.5 text-xs text-slate-600 capitalize">
                      {leave.leaveType} leave
                    </span>
                  </div>
                  <p className="text-xs text-slate-500 mt-0.5">
                    {format(new Date(leave.fromDate), "MMM d")} — {format(new Date(leave.toDate), "MMM d, yyyy")}
                    {" · "}{days(leave.fromDate, leave.toDate)}
                  </p>
                  <p className="text-xs text-slate-500 mt-1 line-clamp-2">{leave.reason}</p>
                  {leave.adminNotes && (
                    <p className="text-xs text-slate-400 mt-1 italic">Admin note: {leave.adminNotes}</p>
                  )}
                </div>
                <div className="flex items-center gap-2 shrink-0">
                  <p className="text-xs text-slate-400 hidden sm:block">
                    {format(new Date(leave.createdAt), "MMM d")}
                  </p>
                  {leave.status === "pending" && (
                    <>
                      <button
                        onClick={() => setNoteModal({ id: leave.id, action: "approved" })}
                        disabled={actionId === leave.id}
                        className="flex items-center gap-1 rounded-lg bg-emerald-50 border border-emerald-200 px-3 py-1.5 text-xs font-medium text-emerald-700 hover:bg-emerald-100 transition-colors disabled:opacity-50"
                      >
                        <CheckCircle className="h-3.5 w-3.5" />
                        Approve
                      </button>
                      <button
                        onClick={() => setNoteModal({ id: leave.id, action: "rejected" })}
                        disabled={actionId === leave.id}
                        className="flex items-center gap-1 rounded-lg bg-red-50 border border-red-200 px-3 py-1.5 text-xs font-medium text-red-700 hover:bg-red-100 transition-colors disabled:opacity-50"
                      >
                        <XCircle className="h-3.5 w-3.5" />
                        Reject
                      </button>
                    </>
                  )}
                </div>
              </div>
            ))}
          </div>
        )}
      </div>

      {/* Note modal */}
      {noteModal && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/40 backdrop-blur-sm p-4">
          <div className="w-full max-w-md rounded-2xl bg-white p-6 shadow-xl">
            <h3 className="text-base font-semibold text-slate-900 mb-1">
              {noteModal.action === "approved" ? "Approve Leave" : "Reject Leave"}
            </h3>
            <p className="text-sm text-slate-500 mb-4">Add an optional note for the booker.</p>
            <textarea
              value={note}
              onChange={e => setNote(e.target.value)}
              placeholder="Optional admin note..."
              rows={3}
              className="w-full rounded-lg border border-slate-200 px-3 py-2 text-sm text-slate-800 focus:outline-none focus:ring-2 focus:ring-teal-500 resize-none"
            />
            <div className="flex justify-end gap-2 mt-4">
              <button
                onClick={() => { setNoteModal(null); setNote(""); }}
                className="rounded-lg border border-slate-200 px-4 py-2 text-sm font-medium text-slate-600 hover:bg-slate-50"
              >
                Cancel
              </button>
              <button
                onClick={() => handleAction(noteModal.id, noteModal.action)}
                disabled={actionId !== null}
                className={`rounded-lg px-4 py-2 text-sm font-medium text-white transition-colors disabled:opacity-50 ${
                  noteModal.action === "approved"
                    ? "bg-emerald-600 hover:bg-emerald-700"
                    : "bg-red-600 hover:bg-red-700"
                }`}
              >
                {noteModal.action === "approved" ? "Approve" : "Reject"}
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
