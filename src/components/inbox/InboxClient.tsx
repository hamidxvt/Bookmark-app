"use client";

import { useCallback, useEffect, useRef, useState } from "react";
import Link from "next/link";
import { format } from "date-fns";
import {
  Send,
  CheckCircle2,
  XCircle,
  PlaneTakeoff,
  Package,
  Clock,
  Truck,
  RefreshCw,
} from "lucide-react";
import { toast } from "sonner";
import { formatDateTime, cn, formatPKR } from "@/lib/utils";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Textarea } from "@/components/ui/textarea";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import { EmptyState } from "@/components/shared/ui-bits";

// ─── Mock messaging (existing inbox) ─────────────────────────────────────────

const THREADS = [
  { id: 1, name: "Ahmed Raza", city: "Karachi", online: true, unread: 2, lastMessage: "Order placed at City School", lastTime: new Date(Date.now() - 120000) },
  { id: 2, name: "Sara Malik", city: "Lahore", online: true, unread: 1, lastMessage: "Need updated catalog", lastTime: new Date(Date.now() - 3600000) },
  { id: 3, name: "Ali Hassan", city: "Karachi", online: false, unread: 0, lastMessage: "Customer issue resolved", lastTime: new Date(Date.now() - 86400000) },
  { id: 4, name: "Usman Khan", city: "Multan", online: false, unread: 0, lastMessage: "Leave approved, thanks", lastTime: new Date(Date.now() - 86400000 * 2) },
];

const MOCK_MESSAGES: Record<number, { id: number; sender: "admin" | "booker"; text: string; time: Date }[]> = {
  1: [
    { id: 1, sender: "booker", text: "Sir, visited City School today. They placed an order for 50 Math books.", time: new Date(Date.now() - 3600000 * 3) },
    { id: 2, sender: "admin", text: "Great work Ahmed! What grade level?", time: new Date(Date.now() - 3600000 * 2) },
    { id: 3, sender: "booker", text: "Grade 5 and 6 both. Total Rs. 42,000", time: new Date(Date.now() - 1800000) },
    { id: 4, sender: "booker", text: "Order placed at City School", time: new Date(Date.now() - 120000) },
  ],
  2: [
    { id: 1, sender: "booker", text: "Assalam o Alaikum sir, can you please send the 2025 catalog?", time: new Date(Date.now() - 7200000) },
    { id: 2, sender: "admin", text: "Wa alaikum assalam Sara, I'll send it shortly.", time: new Date(Date.now() - 3600000) },
    { id: 3, sender: "booker", text: "Need updated catalog", time: new Date(Date.now() - 3600000) },
  ],
};

function timeLabel(date: Date) {
  const mins = Math.floor((Date.now() - date.getTime()) / 60000);
  if (mins < 1) return "just now";
  if (mins < 60) return `${mins}m`;
  const hrs = Math.floor(mins / 60);
  if (hrs < 24) return `${hrs}h`;
  return formatDateTime(date);
}

function OfficerAvatar({ name, photo, size = "md" }: { name: string; photo?: string | null; size?: "sm" | "md" }) {
  const dim = size === "sm" ? "h-9 w-9" : "h-10 w-10";
  return (
    <Avatar className={dim}>
      {photo ? <AvatarImage src={photo} alt={name} /> : null}
      <AvatarFallback className="bg-navy text-xs font-bold text-navy-foreground">
        {name[0]?.toUpperCase()}
      </AvatarFallback>
    </Avatar>
  );
}

// ─── Leave & Sample types ─────────────────────────────────────────────────────

interface Leave {
  id: number;
  leaveType: string;
  fromDate: string;
  toDate: string;
  reason: string;
  status: string;
  adminNotes: string | null;
  createdAt: string;
  booker: { id: number; name: string; email: string; phone: string; profilePhoto?: string | null };
}

interface SampleRequest {
  id: number;
  productName: string;
  quantity: number;
  price: number | null;
  notes: string | null;
  status: string;
  adminNotes: string | null;
  createdAt: string;
  booker: { id: number; name: string; email: string; sampleBudget?: number; profilePhoto?: string | null };
  customer: { id: number; name: string } | null;
}

const LEAVE_STATUS: Record<string, string> = {
  pending: "bg-warning/20 text-warning-foreground",
  approved: "bg-success/15 text-success",
  rejected: "bg-destructive/15 text-destructive",
};

const SAMPLE_STATUS: Record<string, string> = {
  pending: "bg-warning/20 text-warning-foreground",
  approved: "bg-success/15 text-success",
  rejected: "bg-destructive/15 text-destructive",
  delivered: "bg-info/15 text-info-foreground",
};

function leaveDays(from: string, to: string) {
  const d = Math.ceil((new Date(to).getTime() - new Date(from).getTime()) / 86400000) + 1;
  return d === 1 ? "1 day" : `${d} days`;
}

function sampleBudgetRemaining(sample: SampleRequest, allSamples: SampleRequest[]) {
  const budget = Number(sample.booker.sampleBudget ?? 300000);
  const used = allSamples
    .filter(
      (s) =>
        s.booker.id === sample.booker.id &&
        ["approved", "delivered"].includes(s.status) &&
        s.id !== sample.id,
    )
    .reduce((sum, s) => sum + (s.price ? Number(s.price) * s.quantity : 0), 0);
  const thisCost = sample.price ? Number(sample.price) * sample.quantity : 0;
  return budget - used - (sample.status === "pending" ? thisCost : 0);
}

// ─── Messages panel ───────────────────────────────────────────────────────────

function MessagesPanel() {
  const [activeThread, setActiveThread] = useState(1);
  const [input, setInput] = useState("");
  const [messages, setMessages] = useState(MOCK_MESSAGES);
  const bottomRef = useRef<HTMLDivElement>(null);

  const thread = THREADS.find((t) => t.id === activeThread)!;
  const msgs = messages[activeThread] ?? [];

  useEffect(() => {
    bottomRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [activeThread, msgs.length]);

  function send() {
    if (!input.trim()) return;
    setMessages((prev) => ({
      ...prev,
      [activeThread]: [
        ...(prev[activeThread] ?? []),
        { id: Date.now(), sender: "admin", text: input.trim(), time: new Date() },
      ],
    }));
    setInput("");
  }

  return (
    <div className="flex h-[calc(100vh-120px)] min-h-[480px]">
      <div className="w-72 shrink-0 border-r border-border bg-card flex flex-col">
        <div className="p-4 border-b border-border">
          <p className="text-sm font-semibold text-foreground">Messages</p>
          <p className="text-xs text-muted-foreground">{THREADS.filter((t) => t.unread > 0).length} unread</p>
        </div>
        <div className="flex-1 overflow-y-auto divide-y divide-border">
          {THREADS.map((t) => (
            <button
              key={t.id}
              onClick={() => setActiveThread(t.id)}
              className={`w-full flex items-start gap-3 px-4 py-3.5 text-left transition-colors hover:bg-muted/50 ${activeThread === t.id ? "bg-primary-soft border-r-2 border-primary" : ""}`}
            >
              <div className="relative shrink-0">
                <div className="flex h-9 w-9 items-center justify-center rounded-full bg-primary text-xs font-bold text-primary-foreground">
                  {t.name[0]}
                </div>
                {t.online && <span className="absolute -bottom-0.5 -right-0.5 h-2.5 w-2.5 rounded-full border-2 border-card bg-success" />}
              </div>
              <div className="min-w-0 flex-1">
                <div className="flex items-center justify-between mb-0.5">
                  <p className="text-xs font-semibold text-foreground truncate">{t.name}</p>
                  <p className="text-[10px] text-muted-foreground shrink-0 ml-1">{timeLabel(t.lastTime)}</p>
                </div>
                <p className="text-xs text-muted-foreground truncate">{t.lastMessage}</p>
              </div>
              {t.unread > 0 && (
                <span className="shrink-0 flex h-4 min-w-4 items-center justify-center rounded-full bg-primary px-1 text-[10px] font-bold text-primary-foreground">
                  {t.unread}
                </span>
              )}
            </button>
          ))}
        </div>
      </div>

      <div className="flex flex-1 flex-col bg-muted/40">
        <div className="flex items-center gap-3 border-b border-border bg-card px-5 py-3">
          <div className="relative">
            <div className="flex h-9 w-9 items-center justify-center rounded-full bg-primary text-xs font-bold text-primary-foreground">
              {thread.name[0]}
            </div>
            {thread.online && <span className="absolute -bottom-0.5 -right-0.5 h-2.5 w-2.5 rounded-full border-2 border-card bg-success" />}
          </div>
          <div>
            <p className="text-sm font-semibold text-foreground">{thread.name}</p>
            <p className="text-xs text-muted-foreground">{thread.city} · {thread.online ? "Online" : "Offline"}</p>
          </div>
        </div>

        <div className="flex-1 overflow-y-auto px-5 py-4 space-y-4">
          {msgs.map((m) => (
            <div key={m.id} className={`flex ${m.sender === "admin" ? "justify-end" : "justify-start"}`}>
              <div className={`max-w-xs rounded-2xl px-4 py-2.5 ${
                m.sender === "admin"
                  ? "bg-navy text-navy-foreground rounded-br-sm"
                  : "surface text-foreground rounded-bl-sm"
              }`}>
                <p className="text-sm leading-relaxed">{m.text}</p>
                <p className={`text-[10px] mt-1 ${m.sender === "admin" ? "text-navy-foreground/60" : "text-muted-foreground"}`}>
                  {timeLabel(m.time)}
                </p>
              </div>
            </div>
          ))}
          <div ref={bottomRef} />
        </div>

        <div className="border-t border-border bg-card px-4 py-3">
          <div className="flex items-center gap-3">
            <input
              value={input}
              onChange={(e) => setInput(e.target.value)}
              onKeyDown={(e) => e.key === "Enter" && !e.shiftKey && send()}
              placeholder={`Message ${thread.name}…`}
              className="flex-1 rounded-full border border-border bg-muted px-4 py-2.5 text-sm text-foreground placeholder:text-muted-foreground focus:outline-none focus:ring-2 focus:ring-primary/20 focus:border-primary transition"
            />
            <button
              onClick={send}
              disabled={!input.trim()}
              className="flex h-10 w-10 items-center justify-center rounded-full bg-primary text-primary-foreground hover:opacity-90 disabled:opacity-40 transition-colors"
            >
              <Send className="h-4 w-4" />
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

// ─── Leave requests panel ─────────────────────────────────────────────────────

function LeaveRequestsPanel({ refreshKey }: { refreshKey: number }) {
  const [leaves, setLeaves] = useState<Leave[]>([]);
  const [loading, setLoading] = useState(true);
  const [actionId, setActionId] = useState<number | null>(null);
  const [note, setNote] = useState("");
  const [noteModal, setNoteModal] = useState<{ id: number; action: "approved" | "rejected" } | null>(null);

  const load = useCallback(async () => {
    setLoading(true);
    try {
      const res = await fetch("/api/v1/leaves?status=pending").then((r) => r.json());
      if (res.success) {
        const sorted = (res.data as Leave[]).sort(
          (a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime(),
        );
        setLeaves(sorted);
      }
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    load();
  }, [load, refreshKey]);

  async function handleAction(id: number, status: "approved" | "rejected") {
    setActionId(id);
    try {
      const res = await fetch("/api/v1/leaves", {
        method: "PATCH",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ id, status, adminNotes: note }),
      }).then((r) => r.json());
      if (res.success) {
        toast.success(`Leave ${status}`);
        setNoteModal(null);
        setNote("");
        load();
      } else {
        toast.error(res.error ?? "Failed to update leave");
      }
    } finally {
      setActionId(null);
    }
  }

  return (
    <div className="h-[calc(100vh-120px)] min-h-[480px] overflow-y-auto px-6 py-4">
      <div className="mb-4 flex items-center justify-between">
        <div>
          <p className="text-sm font-semibold text-foreground">Pending leave requests</p>
          <p className="text-xs text-muted-foreground">Newest first · auto-refreshes on mobile submissions</p>
        </div>
        <Button variant="outline" size="sm" className="rounded-xl" onClick={load} disabled={loading}>
          <RefreshCw className={cn("mr-2 h-4 w-4", loading && "animate-spin")} /> Refresh
        </Button>
      </div>

      {loading && leaves.length === 0 ? (
        <p className="py-12 text-center text-sm text-muted-foreground">Loading…</p>
      ) : leaves.length === 0 ? (
        <EmptyState title="No pending leave requests" description="New requests from the mobile app will appear here." />
      ) : (
        <div className="divide-y divide-border rounded-xl border border-border bg-card">
          {leaves.map((leave) => (
            <div key={leave.id} className="flex items-start gap-4 p-4">
              <Link href={`/bookers/${leave.booker.id}`}>
                <OfficerAvatar name={leave.booker.name} photo={leave.booker.profilePhoto} />
              </Link>
              <div className="min-w-0 flex-1">
                <div className="flex flex-wrap items-center gap-2">
                  <Link href={`/bookers/${leave.booker.id}`} className="text-sm font-semibold text-foreground hover:underline">
                    {leave.booker.name}
                  </Link>
                  <Badge className={cn("capitalize font-semibold", LEAVE_STATUS[leave.status] ?? "bg-muted")}>
                    {leave.status}
                  </Badge>
                  <Badge variant="outline" className="gap-1 capitalize">
                    <PlaneTakeoff className="h-3 w-3" /> {leave.leaveType}
                  </Badge>
                </div>
                <p className="mt-1 text-xs text-muted-foreground">
                  {format(new Date(leave.fromDate), "MMM d")} — {format(new Date(leave.toDate), "MMM d, yyyy")}
                  {" · "}{leaveDays(leave.fromDate, leave.toDate)}
                </p>
                <p className="mt-1 text-sm text-foreground">{leave.reason}</p>
                <p className="mt-1 text-[11px] text-muted-foreground">
                  Submitted {format(new Date(leave.createdAt), "MMM d, yyyy h:mm a")}
                </p>
              </div>
              <div className="flex shrink-0 flex-col gap-2 sm:flex-row">
                <Button
                  variant="outline"
                  size="sm"
                  className="rounded-lg border-success/30 bg-success/10 text-success hover:bg-success/15"
                  disabled={actionId === leave.id}
                  onClick={() => setNoteModal({ id: leave.id, action: "approved" })}
                >
                  <CheckCircle2 className="mr-1 h-3.5 w-3.5" /> Approve
                </Button>
                <Button
                  variant="outline"
                  size="sm"
                  className="rounded-lg border-destructive/30 bg-destructive/10 text-destructive hover:bg-destructive/15"
                  disabled={actionId === leave.id}
                  onClick={() => setNoteModal({ id: leave.id, action: "rejected" })}
                >
                  <XCircle className="mr-1 h-3.5 w-3.5" /> Reject
                </Button>
              </div>
            </div>
          ))}
        </div>
      )}

      <Dialog open={!!noteModal} onOpenChange={(o) => { if (!o) { setNoteModal(null); setNote(""); } }}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle>{noteModal?.action === "approved" ? "Approve leave?" : "Reject leave?"}</DialogTitle>
            <DialogDescription>
              The officer will receive a push notification with your decision.
            </DialogDescription>
          </DialogHeader>
          <Textarea
            value={note}
            onChange={(e) => setNote(e.target.value)}
            placeholder="Optional admin note…"
            rows={3}
            className="rounded-xl"
          />
          <DialogFooter>
            <Button variant="outline" className="rounded-xl" onClick={() => { setNoteModal(null); setNote(""); }}>
              Cancel
            </Button>
            <Button
              className={cn("rounded-xl", noteModal?.action === "rejected" && "bg-destructive text-destructive-foreground hover:bg-destructive/90")}
              disabled={actionId !== null}
              onClick={() => noteModal && handleAction(noteModal.id, noteModal.action)}
            >
              Confirm {noteModal?.action === "approved" ? "Approve" : "Reject"}
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}

// ─── Sample requests panel ────────────────────────────────────────────────────

function SampleReviewDialog({
  sample,
  allSamples,
  onClose,
  onDone,
}: {
  sample: SampleRequest;
  allSamples: SampleRequest[];
  onClose: () => void;
  onDone: () => void;
}) {
  const [action, setAction] = useState<"approved" | "rejected">("approved");
  const [quantity, setQuantity] = useState(String(sample.quantity));
  const [notes, setNotes] = useState("");
  const [saving, setSaving] = useState(false);

  const remaining = sampleBudgetRemaining(sample, allSamples);

  async function submit() {
    setSaving(true);
    try {
      const res = await fetch(`/api/v1/samples/${sample.id}`, {
        method: "PATCH",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          status: action,
          adminNotes: notes || undefined,
          quantity: Number(quantity) || sample.quantity,
        }),
      });
      const d = await res.json();
      if (!d.success) throw new Error(d.error ?? "Failed");
      toast.success(`Sample ${action}`);
      onDone();
    } catch (e: unknown) {
      toast.error(e instanceof Error ? e.message : "Update failed");
    } finally {
      setSaving(false);
    }
  }

  return (
    <Dialog open onOpenChange={(o) => !o && onClose()}>
      <DialogContent className="rounded-2xl sm:max-w-md">
        <DialogHeader>
          <DialogTitle>Review sample request</DialogTitle>
          <DialogDescription>Adjust quantity if needed before approving.</DialogDescription>
        </DialogHeader>
        <div className="space-y-4">
          <div className="space-y-2 rounded-xl bg-muted/50 p-4 text-sm">
            <div className="flex justify-between">
              <span className="text-muted-foreground">Product</span>
              <span className="font-medium">{sample.productName}</span>
            </div>
            <div className="flex justify-between">
              <span className="text-muted-foreground">Budget remaining</span>
              <span className={cn("font-medium", remaining < 0 ? "text-destructive" : "text-success")}>
                {formatPKR(Math.max(0, remaining))}
              </span>
            </div>
          </div>
          <div className="space-y-1.5">
            <Label>Quantity</Label>
            <Input
              type="number"
              min={1}
              value={quantity}
              onChange={(e) => setQuantity(e.target.value)}
              className="rounded-xl"
            />
          </div>
          <div className="grid grid-cols-2 gap-3">
            <button
              type="button"
              onClick={() => setAction("approved")}
              className={cn(
                "flex items-center justify-center gap-2 rounded-xl border-2 py-2.5 text-sm font-semibold",
                action === "approved" ? "border-success bg-success/10 text-success" : "border-border text-muted-foreground",
              )}
            >
              <CheckCircle2 className="h-4 w-4" /> Approve
            </button>
            <button
              type="button"
              onClick={() => setAction("rejected")}
              className={cn(
                "flex items-center justify-center gap-2 rounded-xl border-2 py-2.5 text-sm font-semibold",
                action === "rejected" ? "border-destructive bg-destructive/10 text-destructive" : "border-border text-muted-foreground",
              )}
            >
              <XCircle className="h-4 w-4" /> Reject
            </button>
          </div>
          <Textarea
            value={notes}
            onChange={(e) => setNotes(e.target.value)}
            placeholder="Admin notes (optional)…"
            rows={2}
            className="rounded-xl"
          />
        </div>
        <DialogFooter>
          <Button variant="outline" className="rounded-xl" onClick={onClose}>Cancel</Button>
          <Button
            className={cn("rounded-xl", action === "rejected" && "bg-destructive text-destructive-foreground")}
            onClick={submit}
            disabled={saving}
          >
            {saving ? "Saving…" : `Confirm ${action === "approved" ? "Approve" : "Reject"}`}
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}

function SampleRequestsPanel({ refreshKey }: { refreshKey: number }) {
  const [samples, setSamples] = useState<SampleRequest[]>([]);
  const [loading, setLoading] = useState(true);
  const [reviewing, setReviewing] = useState<SampleRequest | null>(null);
  const [delivering, setDelivering] = useState<SampleRequest | null>(null);
  const [actionId, setActionId] = useState<number | null>(null);

  const load = useCallback(async () => {
    setLoading(true);
    try {
      const res = await fetch("/api/v1/samples?status=pending").then((r) => r.json());
      if (res.success) {
        const sorted = (res.data as SampleRequest[]).sort(
          (a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime(),
        );
        setSamples(sorted);
      }
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    load();
  }, [load, refreshKey]);

  async function markDelivered(sample: SampleRequest) {
    setActionId(sample.id);
    try {
      const res = await fetch(`/api/v1/samples/${sample.id}`, {
        method: "PATCH",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ status: "delivered" }),
      }).then((r) => r.json());
      if (res.success) {
        toast.success("Marked as delivered");
        setDelivering(null);
        load();
      } else {
        toast.error(res.error ?? "Failed");
      }
    } finally {
      setActionId(null);
    }
  }

  // Also load approved for deliver action
  const [approvedSamples, setApprovedSamples] = useState<SampleRequest[]>([]);
  useEffect(() => {
    fetch("/api/v1/samples?status=approved")
      .then((r) => r.json())
      .then((res) => { if (res.success) setApprovedSamples(res.data ?? []); })
      .catch(() => {});
  }, [refreshKey, samples]);

  const pendingList = samples;
  const approvedList = approvedSamples.sort(
    (a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime(),
  );

  function renderSampleRow(sample: SampleRequest, showDeliver = false) {
    const remaining = sampleBudgetRemaining(sample, [...samples, ...approvedSamples]);
    const cost = sample.price ? Number(sample.price) * sample.quantity : null;

    return (
      <div key={sample.id} className="flex items-start gap-4 p-4">
        <Link href={`/bookers/${sample.booker.id}`}>
          <OfficerAvatar name={sample.booker.name} photo={sample.booker.profilePhoto} />
        </Link>
        <div className="min-w-0 flex-1">
          <div className="flex flex-wrap items-center gap-2">
            <Link href={`/bookers/${sample.booker.id}`} className="text-sm font-semibold text-foreground hover:underline">
              {sample.booker.name}
            </Link>
            <Badge className={cn("capitalize font-semibold", SAMPLE_STATUS[sample.status] ?? "bg-muted")}>
              {sample.status}
            </Badge>
          </div>
          <p className="mt-1 text-sm font-medium text-foreground">{sample.productName}</p>
          <p className="text-xs text-muted-foreground">
            Qty: {sample.quantity}
            {cost != null && ` · ${formatPKR(cost)}`}
            {" · "}Budget left: {formatPKR(Math.max(0, remaining))}
          </p>
          {sample.customer && (
            <p className="text-xs text-muted-foreground">Customer: {sample.customer.name}</p>
          )}
          <p className="mt-1 text-[11px] text-muted-foreground">
            {format(new Date(sample.createdAt), "MMM d, yyyy h:mm a")}
          </p>
        </div>
        <div className="flex shrink-0 flex-col gap-2 sm:flex-row">
          {sample.status === "pending" && (
            <>
              <Button size="sm" className="rounded-lg text-xs" onClick={() => setReviewing(sample)}>
                Review
              </Button>
            </>
          )}
          {showDeliver && sample.status === "approved" && (
            <Button
              size="sm"
              variant="outline"
              className="rounded-lg text-xs"
              disabled={actionId === sample.id}
              onClick={() => setDelivering(sample)}
            >
              <Truck className="mr-1 h-3 w-3" /> Deliver
            </Button>
          )}
        </div>
      </div>
    );
  }

  return (
    <div className="h-[calc(100vh-120px)] min-h-[480px] overflow-y-auto px-6 py-4">
      <div className="mb-4 flex items-center justify-between">
        <div>
          <p className="text-sm font-semibold text-foreground">Sample requests</p>
          <p className="text-xs text-muted-foreground">Pending review and approved awaiting delivery</p>
        </div>
        <Button variant="outline" size="sm" className="rounded-xl" onClick={load} disabled={loading}>
          <RefreshCw className={cn("mr-2 h-4 w-4", loading && "animate-spin")} /> Refresh
        </Button>
      </div>

      {loading && pendingList.length === 0 ? (
        <p className="py-12 text-center text-sm text-muted-foreground">Loading…</p>
      ) : pendingList.length === 0 && approvedList.length === 0 ? (
        <EmptyState title="No sample requests" description="New requests from the mobile app will appear here." />
      ) : (
        <div className="space-y-6">
          {pendingList.length > 0 && (
            <div className="rounded-xl border border-border bg-card divide-y divide-border">
              <div className="flex items-center gap-2 px-4 py-2.5 border-b border-border bg-muted/30">
                <Clock className="h-4 w-4 text-warning-foreground" />
                <span className="text-xs font-semibold text-foreground">Pending ({pendingList.length})</span>
              </div>
              {pendingList.map((s) => renderSampleRow(s))}
            </div>
          )}
          {approvedList.length > 0 && (
            <div className="rounded-xl border border-border bg-card divide-y divide-border">
              <div className="flex items-center gap-2 px-4 py-2.5 border-b border-border bg-muted/30">
                <Package className="h-4 w-4 text-success" />
                <span className="text-xs font-semibold text-foreground">Approved — mark delivered ({approvedList.length})</span>
              </div>
              {approvedList.map((s) => renderSampleRow(s, true))}
            </div>
          )}
        </div>
      )}

      {reviewing && (
        <SampleReviewDialog
          sample={reviewing}
          allSamples={[...samples, ...approvedSamples]}
          onClose={() => setReviewing(null)}
          onDone={() => { setReviewing(null); load(); }}
        />
      )}

      <Dialog open={!!delivering} onOpenChange={(o) => !o && setDelivering(null)}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle>Mark as delivered?</DialogTitle>
            <DialogDescription>
              Confirm that {delivering?.productName} was delivered to the customer.
            </DialogDescription>
          </DialogHeader>
          <DialogFooter>
            <Button variant="outline" className="rounded-xl" onClick={() => setDelivering(null)}>Cancel</Button>
            <Button
              className="rounded-xl"
              disabled={actionId !== null}
              onClick={() => delivering && markDelivered(delivering)}
            >
              Confirm Delivered
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}

// ─── Main inbox with tabs + SSE ───────────────────────────────────────────────

export default function InboxClient() {
  const [activeTab, setActiveTab] = useState("inbox");
  const [leaveCount, setLeaveCount] = useState(0);
  const [sampleCount, setSampleCount] = useState(0);
  const [refreshKey, setRefreshKey] = useState(0);

  const refreshCounts = useCallback(async () => {
    try {
      const [leavesRes, samplesRes] = await Promise.all([
        fetch("/api/v1/leaves?status=pending").then((r) => r.json()),
        fetch("/api/v1/samples?status=pending").then((r) => r.json()),
      ]);
      if (leavesRes.success) setLeaveCount((leavesRes.data as unknown[]).length);
      if (samplesRes.success) setSampleCount((samplesRes.data as unknown[]).length);
    } catch {
      /* ignore */
    }
  }, []);

  useEffect(() => {
    refreshCounts();
  }, [refreshCounts, refreshKey]);

  useEffect(() => {
    let es: EventSource | null = null;
    try {
      es = new EventSource("/api/v1/events/subscribe");
      es.onmessage = (e) => {
        try {
          const data = JSON.parse(e.data);

          if (data.kind === "connected" && data.counts) {
            setLeaveCount(data.counts.leaves ?? 0);
            setSampleCount(data.counts.samples ?? 0);
            return;
          }

          const type = data.type as string | undefined;
          if (!type) return;

          setRefreshKey((k) => k + 1);

          const name = (data.payload?.bookerName as string) ?? "An officer";
          if (type === "leave-request") {
            toast.info("New leave request", { description: `${name} submitted a leave request` });
            setActiveTab("leaves");
          } else if (type === "sample-request") {
            toast.info("New sample request", {
              description: `${name} requested ${data.payload?.productName ?? "samples"}`,
            });
            setActiveTab("samples");
          } else if (type === "visit-complete") {
            toast.success("Visit completed", {
              description: data.payload?.message as string ?? `${name} completed a visit`,
            });
          }
        } catch {
          /* ignore parse errors */
        }
      };
      es.onerror = () => es?.close();
    } catch {
      /* SSE not supported */
    }
    return () => es?.close();
  }, []);

  function TabBadge({ count }: { count: number }) {
    if (count <= 0) return null;
    return (
      <span className="ml-1.5 flex h-5 min-w-5 items-center justify-center rounded-full bg-primary px-1 text-[10px] font-bold text-primary-foreground">
        {count > 99 ? "99+" : count}
      </span>
    );
  }

  return (
    <Tabs value={activeTab} onValueChange={setActiveTab} className="flex flex-col">
      <div className="border-b border-border bg-card px-6 py-3">
        <TabsList className="rounded-xl">
          <TabsTrigger value="inbox" className="rounded-lg">Inbox</TabsTrigger>
          <TabsTrigger value="leaves" className="rounded-lg">
            Leave Requests
            <TabBadge count={leaveCount} />
          </TabsTrigger>
          <TabsTrigger value="samples" className="rounded-lg">
            Sample Requests
            <TabBadge count={sampleCount} />
          </TabsTrigger>
        </TabsList>
      </div>

      <TabsContent value="inbox" className="mt-0">
        <MessagesPanel />
      </TabsContent>
      <TabsContent value="leaves" className="mt-0">
        <LeaveRequestsPanel refreshKey={refreshKey} />
      </TabsContent>
      <TabsContent value="samples" className="mt-0">
        <SampleRequestsPanel refreshKey={refreshKey} />
      </TabsContent>
    </Tabs>
  );
}
