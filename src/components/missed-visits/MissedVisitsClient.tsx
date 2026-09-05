"use client";

import { useEffect, useState } from "react";
import { CheckCircle, XCircle, RefreshCw, MapPin, Calendar, AlertTriangle } from "lucide-react";
import { format } from "date-fns";
import { EmptyState, SectionCard, StatCard } from "@/components/shared/ui-bits";
import { Button } from "@/components/ui/button";
import { Textarea } from "@/components/ui/textarea";
import {
  Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle,
} from "@/components/ui/dialog";
import { cn } from "@/lib/utils";

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
  pending: "bg-warning/15 text-warning-foreground border-warning/30",
  approved: "bg-success/15 text-success border-success/25",
  rejected: "bg-destructive/10 text-destructive border-destructive/25",
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
      const res = await fetch(`/api/v1/missed-visits?status=${filter}`).then((r) => r.json());
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
      }).then((r) => r.json());
      if (res.success) {
        setNoteModal(null);
        setNote("");
        load();
      }
    } finally {
      setActionId(null);
    }
  }

  const pending = items.filter((m) => m.status === "pending").length;

  return (
    <div className="space-y-6 px-6 py-6 lg:px-8">
      <div className="flex flex-wrap items-center justify-between gap-3">
        <div>
          <h1 className="text-xl font-bold text-foreground">Missed Visits</h1>
          <p className="mt-0.5 text-sm text-muted-foreground">Review officer excuses for missed field visits</p>
        </div>
        <Button variant="outline" className="rounded-xl" onClick={load} disabled={loading}>
          <RefreshCw className={cn("mr-2 h-4 w-4", loading && "animate-spin")} /> Refresh
        </Button>
      </div>

      <div className="grid gap-4 sm:grid-cols-3">
        <StatCard label="Pending Review" value={pending} icon={<AlertTriangle className="h-5 w-5" />} />
        <StatCard label="Showing" value={items.length} icon={<Calendar className="h-5 w-5" />} />
        <StatCard label="Filter" value={filter.charAt(0).toUpperCase() + filter.slice(1)} icon={<CheckCircle className="h-5 w-5" />} />
      </div>

      <SectionCard title="Excuse Reports" description={`${items.length} ${filter} reports`}>
        <div className="mb-5 flex w-fit gap-1 rounded-xl bg-muted p-1">
          {["pending", "approved", "rejected"].map((s) => (
            <button
              key={s}
              onClick={() => setFilter(s)}
              className={cn(
                "rounded-lg px-4 py-1.5 text-xs font-medium capitalize transition-all",
                filter === s ? "bg-card text-foreground shadow-card" : "text-muted-foreground hover:text-foreground",
              )}
            >
              {s}
            </button>
          ))}
        </div>

        {loading ? (
          <div className="space-y-3">
            {[...Array(4)].map((_, i) => (
              <div key={i} className="flex items-center gap-4 px-2 py-3">
                <div className="h-10 w-10 animate-pulse rounded-xl bg-muted" />
                <div className="flex-1 space-y-2">
                  <div className="h-3 w-52 animate-pulse rounded bg-muted" />
                  <div className="h-2.5 w-36 animate-pulse rounded bg-muted" />
                </div>
              </div>
            ))}
          </div>
        ) : items.length === 0 ? (
          <EmptyState title={`No ${filter} missed visit reports`} />
        ) : (
          <div className="divide-y divide-border">
            {items.map((item) => (
              <div key={item.id} className="flex items-start gap-4 py-4 transition-colors hover:bg-muted/40">
                <div className="flex h-10 w-10 shrink-0 items-center justify-center rounded-xl bg-muted text-xl">
                  {CUSTOMER_TYPE_ICON[item.visit.customer.customerType] ?? "📍"}
                </div>
                <div className="min-w-0 flex-1">
                  <div className="flex flex-wrap items-center gap-2">
                    <p className="text-sm font-semibold text-foreground">{item.visit.customer.name}</p>
                    <span className={cn("inline-flex items-center rounded-full border px-2 py-0.5 text-xs font-medium capitalize", STATUS_COLORS[item.status])}>
                      {item.status}
                    </span>
                  </div>
                  <div className="mt-0.5 flex items-center gap-3 text-xs text-muted-foreground">
                    <span className="flex items-center gap-1"><MapPin className="h-3 w-3" />{item.booker.name}</span>
                    <span className="flex items-center gap-1"><Calendar className="h-3 w-3" />{format(new Date(item.visit.visitDate), "MMM d, yyyy")}</span>
                  </div>
                  <p className="mt-1.5 rounded-lg border border-border bg-muted/40 px-3 py-2 text-xs text-foreground">
                    {item.reason}
                  </p>
                  {item.adminNote && (
                    <p className="mt-1 text-xs italic text-muted-foreground">Admin note: {item.adminNote}</p>
                  )}
                </div>
                <div className="flex shrink-0 items-center gap-2">
                  <p className="hidden text-xs text-muted-foreground sm:block">{format(new Date(item.createdAt), "MMM d")}</p>
                  {item.status === "pending" && (
                    <>
                      <Button
                        size="sm" variant="outline" disabled={actionId === item.id}
                        className="rounded-lg border-success/30 bg-success/10 text-success hover:bg-success/20"
                        onClick={() => setNoteModal({ id: item.id, action: "approved" })}
                      >
                        <CheckCircle className="mr-1 h-3.5 w-3.5" /> Approve
                      </Button>
                      <Button
                        size="sm" variant="outline" disabled={actionId === item.id}
                        className="rounded-lg border-destructive/30 bg-destructive/10 text-destructive hover:bg-destructive/20"
                        onClick={() => setNoteModal({ id: item.id, action: "rejected" })}
                      >
                        <XCircle className="mr-1 h-3.5 w-3.5" /> Reject
                      </Button>
                    </>
                  )}
                </div>
              </div>
            ))}
          </div>
        )}
      </SectionCard>

      <Dialog open={!!noteModal} onOpenChange={(o) => { if (!o) { setNoteModal(null); setNote(""); } }}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle>{noteModal?.action === "approved" ? "Approve Excuse" : "Reject Excuse"}</DialogTitle>
            <DialogDescription>
              {noteModal?.action === "rejected"
                ? "Rejecting will deduct the daily performance allowance."
                : "Approving excuses the missed visit with no penalty."}
            </DialogDescription>
          </DialogHeader>
          <Textarea
            value={note}
            onChange={(e) => setNote(e.target.value)}
            placeholder="Optional note for the officer…"
            rows={3}
            className="resize-none rounded-xl"
          />
          <DialogFooter>
            <Button variant="outline" className="rounded-xl" onClick={() => { setNoteModal(null); setNote(""); }}>
              Cancel
            </Button>
            <Button
              disabled={actionId !== null}
              className={cn(
                "rounded-xl",
                noteModal?.action === "approved" ? "bg-success text-success-foreground hover:bg-success/90" : "bg-destructive text-destructive-foreground hover:bg-destructive/90",
              )}
              onClick={() => noteModal && handleAction(noteModal.id, noteModal.action)}
            >
              Confirm
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
