"use client";

import { useEffect, useState } from "react";
import { CheckCircle, XCircle, RefreshCw, MapPin, Calendar } from "lucide-react";
import { format } from "date-fns";

interface MissedVisit {
  id: number;
  reason: string;
  status: string;
  adminNote: string | null;
  createdAt: string;
  booker: { id: number; name: string; email: string };
  visit: {
    id: number;
    visitDate: string;
    customer: { id: number; name: string; customerType: string };
  };
}

const STATUS_COLORS: Record<string, string> = {
  pending: "bg-amber-100 text-amber-700 border-amber-200",
  approved: "bg-emerald-100 text-emerald-700 border-emerald-200",
  rejected: "bg-red-100 text-red-700 border-red-200",
};

const CUSTOMER_TYPE_ICON: Record<string, string> = {
  SCHOOL: "🏫",
  COLLEGE: "🎓",
  RETAILER: "🏪",
  SELF: "👤",
  OTHER: "📍",
};

export default function MissedVisitsClient() {
  const [items, setItems] = useState<MissedVisit[]>([]);
  const [filter, setFilter] = useState("pending");
  const [loading, setLoading] = useState(true);
  const [actionId, setActionId] = useState<number | null>(null);
  const [noteModal, setNoteModal] = useState<{ id: number; action: "approved" | "rejected" } | null>(null);
  const [note, setNote] = useState("");

  async function load() {
    setLoading(true);
    try {
      const res = await fetch(`/api/v1/missed-visits?status=${filter}`).then(r => r.json());
      if (res.success) setItems(res.data);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => { load(); }, [filter]);

  async function handleAction(id: number, status: "approved" | "rejected") {
    setActionId(id);
    try {
      const res = await fetch("/api/v1/missed-visits", {
        method: "PATCH",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ id, status, adminNote: note }),
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

  const pending = items.filter(m => m.status === "pending").length;

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h2 className="text-xl font-bold text-slate-900">Missed Visits</h2>
          <p className="text-sm text-slate-500 mt-0.5">Review booker excuses for missed field visits</p>
        </div>
        <div className="flex items-center gap-2">
          {pending > 0 && (
            <span className="flex h-6 min-w-6 items-center justify-center rounded-full bg-red-500 px-1.5 text-xs font-bold text-white">
              {pending}
            </span>
          )}
          <button onClick={load} disabled={loading}
            className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50 transition-colors">
            <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} />
            Refresh
          </button>
        </div>
      </div>

      <div className="flex gap-1 rounded-xl bg-slate-100 p-1 w-fit">
        {["pending", "approved", "rejected"].map(s => (
          <button key={s} onClick={() => setFilter(s)}
            className={`rounded-lg px-4 py-1.5 text-xs font-medium capitalize transition-all ${
              filter === s ? "bg-white shadow-sm text-slate-900" : "text-slate-500 hover:text-slate-700"
            }`}>
            {s}
          </button>
        ))}
      </div>

      <div className="rounded-2xl border border-slate-200 bg-white shadow-xs overflow-hidden">
        {loading ? (
          <div className="divide-y divide-slate-50">
            {[...Array(4)].map((_, i) => (
              <div key={i} className="flex items-center gap-4 px-6 py-4">
                <div className="h-10 w-10 rounded-xl bg-slate-100 animate-pulse" />
                <div className="flex-1 space-y-2">
                  <div className="h-3 w-52 rounded bg-slate-100 animate-pulse" />
                  <div className="h-2.5 w-36 rounded bg-slate-100 animate-pulse" />
                </div>
              </div>
            ))}
          </div>
        ) : items.length === 0 ? (
          <div className="py-16 text-center">
            <CheckCircle className="mx-auto h-10 w-10 text-slate-200" />
            <p className="mt-3 text-sm text-slate-400">No {filter} missed visit reports</p>
          </div>
        ) : (
          <div className="divide-y divide-slate-50">
            {items.map(item => (
              <div key={item.id} className="flex items-start gap-4 px-6 py-4 hover:bg-slate-50/50 transition-colors">
                <div className="flex h-10 w-10 shrink-0 items-center justify-center rounded-xl bg-slate-100 text-xl">
                  {CUSTOMER_TYPE_ICON[item.visit.customer.customerType] ?? "📍"}
                </div>
                <div className="flex-1 min-w-0">
                  <div className="flex items-center gap-2 flex-wrap">
                    <p className="text-sm font-semibold text-slate-800">{item.visit.customer.name}</p>
                    <span className={`inline-flex items-center rounded-full border px-2 py-0.5 text-xs font-medium capitalize ${STATUS_COLORS[item.status]}`}>
                      {item.status}
                    </span>
                  </div>
                  <div className="flex items-center gap-3 mt-0.5 text-xs text-slate-500">
                    <span className="flex items-center gap-1"><MapPin className="h-3 w-3" />{item.booker.name}</span>
                    <span className="flex items-center gap-1"><Calendar className="h-3 w-3" />{format(new Date(item.visit.visitDate), "MMM d, yyyy")}</span>
                  </div>
                  <p className="text-xs text-slate-600 mt-1.5 bg-slate-50 rounded-lg px-3 py-2 border border-slate-100">
                    {item.reason}
                  </p>
                  {item.adminNote && (
                    <p className="text-xs text-slate-400 mt-1 italic">Admin note: {item.adminNote}</p>
                  )}
                </div>
                <div className="flex items-center gap-2 shrink-0">
                  <p className="text-xs text-slate-400 hidden sm:block">{format(new Date(item.createdAt), "MMM d")}</p>
                  {item.status === "pending" && (
                    <>
                      <button onClick={() => setNoteModal({ id: item.id, action: "approved" })} disabled={actionId === item.id}
                        className="flex items-center gap-1 rounded-lg bg-emerald-50 border border-emerald-200 px-3 py-1.5 text-xs font-medium text-emerald-700 hover:bg-emerald-100 transition-colors disabled:opacity-50">
                        <CheckCircle className="h-3.5 w-3.5" />
                        Approve
                      </button>
                      <button onClick={() => setNoteModal({ id: item.id, action: "rejected" })} disabled={actionId === item.id}
                        className="flex items-center gap-1 rounded-lg bg-red-50 border border-red-200 px-3 py-1.5 text-xs font-medium text-red-700 hover:bg-red-100 transition-colors disabled:opacity-50">
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

      {noteModal && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/40 backdrop-blur-sm p-4">
          <div className="w-full max-w-md rounded-2xl bg-white p-6 shadow-xl">
            <h3 className="text-base font-semibold text-slate-900 mb-1">
              {noteModal.action === "approved" ? "Approve Excuse" : "Reject Excuse"}
            </h3>
            <p className="text-sm text-slate-500 mb-4">
              {noteModal.action === "rejected" ? "Rejecting will deduct the daily performance allowance." : "Approving excuses the missed visit with no penalty."}
            </p>
            <textarea value={note} onChange={e => setNote(e.target.value)}
              placeholder="Optional note for the booker..." rows={3}
              className="w-full rounded-lg border border-slate-200 px-3 py-2 text-sm text-slate-800 focus:outline-none focus:ring-2 focus:ring-[#C8102E] resize-none" />
            <div className="flex justify-end gap-2 mt-4">
              <button onClick={() => { setNoteModal(null); setNote(""); }}
                className="rounded-lg border border-slate-200 px-4 py-2 text-sm font-medium text-slate-600 hover:bg-slate-50">
                Cancel
              </button>
              <button onClick={() => handleAction(noteModal.id, noteModal.action)} disabled={actionId !== null}
                className={`rounded-lg px-4 py-2 text-sm font-medium text-white transition-colors disabled:opacity-50 ${
                  noteModal.action === "approved" ? "bg-emerald-600 hover:bg-emerald-700" : "bg-red-600 hover:bg-red-700"
                }`}>
                Confirm
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
