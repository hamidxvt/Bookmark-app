"use client";

import { useEffect, useState } from "react";
import { CheckCircle2, XCircle, RefreshCw, Filter, PlaneTakeoff } from "lucide-react";
import { format } from "date-fns";
import { SectionCard, EmptyState, TableSkeleton } from "@/components/shared/ui-bits";
import { Button } from "@/components/ui/button";
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle } from "@/components/ui/dialog";
import { Textarea } from "@/components/ui/textarea";
import { cn } from "@/lib/utils";

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
  pending:  "bg-warning/20 text-warning-foreground",
  approved: "bg-success/15 text-success",
  rejected: "bg-destructive/15 text-destructive",
};

const FILTERS = ["pending", "approved", "rejected"] as const;

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
    <div className="space-y-6 px-6 py-6 lg:px-8">
      <SectionCard
        title="Leave Requests"
        description={pending > 0 ? `${pending} pending review` : "Review and approve leave applications from bookers"}
        action={
          <div className="flex items-center gap-2">
            <div className="flex rounded-xl bg-muted p-1">
              {FILTERS.map(s => (
                <button
                  key={s}
                  onClick={() => setFilter(s)}
                  className={cn(
                    "rounded-lg px-3 py-1.5 text-xs font-semibold capitalize transition-all duration-200",
                    filter === s ? "bg-card text-foreground shadow-card" : "text-muted-foreground hover:text-foreground",
                  )}
                >
                  {s}
                </button>
              ))}
              <button
                onClick={() => setFilter("")}
                className={cn(
                  "flex items-center gap-1 rounded-lg px-3 py-1.5 text-xs font-semibold transition-all duration-200",
                  filter === "" ? "bg-card text-foreground shadow-card" : "text-muted-foreground hover:text-foreground",
                )}
              >
                <Filter className="h-3 w-3" /> All
              </button>
            </div>
            <button
              onClick={load}
              disabled={loading}
              className="flex h-10 w-10 items-center justify-center rounded-xl border border-border bg-card shadow-card transition hover:shadow-elevated"
              aria-label="Refresh"
            >
              <RefreshCw className={cn("h-4 w-4 text-muted-foreground", loading && "animate-spin")} />
            </button>
          </div>
        }
      >
        {loading ? (
          <TableSkeleton />
        ) : leaves.length === 0 ? (
          <EmptyState title={`No ${filter || ""} leave requests`.trim()} description="Nothing to review right now." />
        ) : (
          <div className="divide-y divide-border">
            {leaves.map(leave => (
              <div key={leave.id} className="flex items-start gap-4 py-4 first:pt-0 last:pb-0">
                <span className="flex h-10 w-10 shrink-0 items-center justify-center rounded-full bg-navy text-sm font-bold text-navy-foreground">
                  {leave.booker.name[0]}
                </span>
                <div className="min-w-0 flex-1">
                  <div className="flex flex-wrap items-center gap-2">
                    <p className="text-sm font-semibold text-foreground">{leave.booker.name}</p>
                    <span className={cn("inline-flex items-center rounded-full px-2.5 py-0.5 text-xs font-semibold capitalize", STATUS_COLORS[leave.status])}>
                      {leave.status}
                    </span>
                    <span className="inline-flex items-center gap-1 rounded-full bg-primary-soft px-2.5 py-0.5 text-xs font-medium capitalize text-primary">
                      <PlaneTakeoff className="h-3 w-3" /> {leave.leaveType} leave
                    </span>
                  </div>
                  <p className="mt-1 text-xs text-muted-foreground">
                    {format(new Date(leave.fromDate), "MMM d")} — {format(new Date(leave.toDate), "MMM d, yyyy")}
                    {" · "}{days(leave.fromDate, leave.toDate)}
                  </p>
                  <p className="mt-1 line-clamp-2 text-xs text-muted-foreground">{leave.reason}</p>
                  {leave.adminNotes && (
                    <p className="mt-1 text-xs italic text-muted-foreground/80">Admin note: {leave.adminNotes}</p>
                  )}
                </div>
                <div className="flex shrink-0 items-center gap-2">
                  <p className="hidden text-xs text-muted-foreground sm:block">
                    {format(new Date(leave.createdAt), "MMM d")}
                  </p>
                  {leave.status === "pending" && (
                    <>
                      <Button
                        variant="outline"
                        className="h-8 rounded-lg border-success/30 bg-success/10 px-3 text-xs font-medium text-success hover:bg-success/15"
                        disabled={actionId === leave.id}
                        onClick={() => setNoteModal({ id: leave.id, action: "approved" })}
                      >
                        <CheckCircle2 className="mr-1 h-3.5 w-3.5" /> Approve
                      </Button>
                      <Button
                        variant="outline"
                        className="h-8 rounded-lg border-destructive/30 bg-destructive/10 px-3 text-xs font-medium text-destructive hover:bg-destructive/15"
                        disabled={actionId === leave.id}
                        onClick={() => setNoteModal({ id: leave.id, action: "rejected" })}
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

      <Dialog open={!!noteModal} onOpenChange={(o: boolean) => { if (!o) { setNoteModal(null); setNote(""); } }}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle>{noteModal?.action === "approved" ? "Approve Leave" : "Reject Leave"}</DialogTitle>
            <DialogDescription>Add an optional note for the booker.</DialogDescription>
          </DialogHeader>
          <Textarea
            value={note}
            onChange={e => setNote(e.target.value)}
            placeholder="Optional admin note…"
            rows={3}
            className="rounded-xl"
          />
          <DialogFooter>
            <Button variant="outline" className="rounded-xl" onClick={() => { setNoteModal(null); setNote(""); }}>
              Cancel
            </Button>
            <Button
              className={cn(
                "rounded-xl",
                noteModal?.action === "rejected" && "bg-destructive text-destructive-foreground hover:bg-destructive/90",
              )}
              disabled={actionId !== null}
              onClick={() => noteModal && handleAction(noteModal.id, noteModal.action)}
            >
              {noteModal?.action === "approved" ? "Approve" : "Reject"}
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
