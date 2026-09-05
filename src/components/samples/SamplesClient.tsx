"use client";

import { useEffect, useState } from "react";
import {
  Search, RefreshCw, CheckCircle, XCircle, Clock, Package,
  Eye, X, FileText, Boxes, Truck,
} from "lucide-react";

import { EmptyState, SectionCard, StatCard, TableSkeleton } from "@/components/shared/ui-bits";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import {
  Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Textarea } from "@/components/ui/textarea";
import { cn, formatPKR } from "@/lib/utils";

interface SampleRequest {
  id: number;
  productName: string;
  quantity: number;
  price: number | null;
  notes: string | null;
  status: string;
  adminNotes: string | null;
  customerName: string | null;
  signatureBase64: string | null;
  pdfUrl: string | null;
  deliveredAt: string | null;
  createdAt: string;
  booker: { id: number; name: string; email: string; sampleBudget?: number };
  customer: { id: number; name: string } | null;
}

const STATUS_TONE: Record<string, string> = {
  approved: "bg-success/15 text-success",
  rejected: "bg-destructive/15 text-destructive",
  delivered: "bg-info/15 text-info-foreground",
  pending: "bg-warning/20 text-warning-foreground",
};

const STATUS_ICON: Record<string, typeof CheckCircle> = {
  approved: CheckCircle,
  rejected: XCircle,
  delivered: Package,
  pending: Clock,
};

function StatusBadge({ status }: { status: string }) {
  const s = status.toLowerCase();
  const Icon = STATUS_ICON[s] ?? Clock;
  return (
    <Badge className={cn("gap-1 font-semibold capitalize", STATUS_TONE[s] ?? "bg-muted text-muted-foreground")}>
      <Icon className="h-3 w-3" /> {s}
    </Badge>
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
    <Dialog open onOpenChange={(o) => !o && onClose()}>
      <DialogContent className="rounded-2xl sm:max-w-md">
        <DialogHeader>
          <DialogTitle>Review Sample Request</DialogTitle>
          <DialogDescription>Approve or reject this sample request from the field.</DialogDescription>
        </DialogHeader>

        <div className="space-y-4">
          <div className="space-y-2 rounded-xl bg-muted/50 p-4">
            <div className="flex justify-between text-sm">
              <span className="text-muted-foreground">Officer</span>
              <span className="font-medium text-foreground">{sample.booker.name}</span>
            </div>
            <div className="flex justify-between text-sm">
              <span className="text-muted-foreground">Product</span>
              <span className="font-medium text-foreground">{sample.productName}</span>
            </div>
            <div className="flex justify-between text-sm">
              <span className="text-muted-foreground">Quantity</span>
              <span className="font-medium text-foreground">{sample.quantity}</span>
            </div>
            {sample.notes && (
              <div className="flex justify-between text-sm">
                <span className="text-muted-foreground">Notes</span>
                <span className="max-w-[60%] text-right font-medium text-foreground">{sample.notes}</span>
              </div>
            )}
          </div>

          <div>
            <Label className="mb-2 block">Decision</Label>
            <div className="grid grid-cols-2 gap-3">
              <button
                type="button"
                onClick={() => setAction("approved")}
                className={cn(
                  "flex items-center justify-center gap-2 rounded-xl border-2 py-2.5 text-sm font-semibold transition-all",
                  action === "approved"
                    ? "border-success bg-success/10 text-success"
                    : "border-border text-muted-foreground hover:border-success/40",
                )}
              >
                <CheckCircle className="h-4 w-4" /> Approve
              </button>
              <button
                type="button"
                onClick={() => setAction("rejected")}
                className={cn(
                  "flex items-center justify-center gap-2 rounded-xl border-2 py-2.5 text-sm font-semibold transition-all",
                  action === "rejected"
                    ? "border-destructive bg-destructive/10 text-destructive"
                    : "border-border text-muted-foreground hover:border-destructive/40",
                )}
              >
                <XCircle className="h-4 w-4" /> Reject
              </button>
            </div>
          </div>

          <div className="space-y-1.5">
            <Label>Admin Notes (optional)</Label>
            <Textarea
              value={notes}
              onChange={(e) => setNotes(e.target.value)}
              rows={3}
              className="rounded-xl"
              placeholder="Reason for decision…"
            />
          </div>

          {error && <p className="text-sm text-destructive">{error}</p>}
        </div>

        <DialogFooter className="gap-3 sm:gap-3">
          <Button variant="outline" className="flex-1 rounded-xl" onClick={onClose}>
            Cancel
          </Button>
          <Button
            className={cn(
              "flex-1 rounded-xl",
              action === "rejected" && "bg-destructive text-destructive-foreground hover:bg-destructive/80",
            )}
            onClick={submit}
            disabled={saving}
          >
            {saving ? "Saving…" : action === "approved" ? "Approve" : "Reject"}
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}

function SignatureModal({ sample, onClose }: { sample: SampleRequest; onClose: () => void }) {
  if (!sample.signatureBase64) return null;
  return (
    <Dialog open onOpenChange={(o) => !o && onClose()}>
      <DialogContent className="rounded-2xl sm:max-w-sm">
        <DialogHeader>
          <DialogTitle>Customer Signature</DialogTitle>
          <DialogDescription>
            Signed by <span className="font-medium text-foreground">{sample.customerName ?? "Unknown"}</span>
          </DialogDescription>
        </DialogHeader>
        <img
          src={`data:image/png;base64,${sample.signatureBase64}`}
          alt="Customer signature"
          className="w-full rounded-xl border border-border bg-muted/40"
        />
      </DialogContent>
    </Dialog>
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
    <div className="space-y-6 px-6 py-6 lg:px-8">
      <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-4">
        <StatCard label="Total Requests" value={stats.total} icon={<Boxes className="h-5 w-5" />} />
        <StatCard label="Pending" value={stats.pending} icon={<Clock className="h-5 w-5" />} />
        <StatCard label="Approved" value={stats.approved} icon={<CheckCircle className="h-5 w-5" />} />
        <StatCard label="Delivered" value={stats.delivered} icon={<Truck className="h-5 w-5" />} />
      </div>

      <SectionCard
        title="Sample Requests"
        description={`${filtered.length} of ${samples.length} records`}
        action={
          <Button variant="outline" className="rounded-xl" onClick={() => load(statusFilter)} disabled={loading}>
            <RefreshCw className={cn("mr-2 h-4 w-4", loading && "animate-spin")} /> Refresh
          </Button>
        }
      >
        <div className="mb-5 grid gap-3 md:grid-cols-[1fr_220px]">
          <div className="relative">
            <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
            <Input
              value={search}
              onChange={(e) => setSearch(e.target.value)}
              placeholder="Search by officer, product, or customer…"
              className="h-11 rounded-xl pl-9"
            />
          </div>
          <Select value={statusFilter} onValueChange={setStatusFilter}>
            <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Status" /></SelectTrigger>
            <SelectContent>
              <SelectItem value="all">All Status</SelectItem>
              <SelectItem value="pending">Pending</SelectItem>
              <SelectItem value="approved">Approved</SelectItem>
              <SelectItem value="rejected">Rejected</SelectItem>
              <SelectItem value="delivered">Delivered</SelectItem>
            </SelectContent>
          </Select>
        </div>

        {loading ? (
          <TableSkeleton />
        ) : filtered.length === 0 ? (
          <EmptyState title="No sample requests found" description="Adjust the filters to see more records." />
        ) : (
          <div className="overflow-x-auto">
            <Table>
              <TableHeader>
                <TableRow>
                  {["#", "Officer", "Product", "Qty", "Price", "Customer", "Status", "Submitted", "Actions"].map((h) => (
                    <TableHead key={h}>{h}</TableHead>
                  ))}
                </TableRow>
              </TableHeader>
              <TableBody>
                {filtered.map((s) => (
                  <TableRow key={s.id} className="transition-colors hover:bg-muted/50">
                    <TableCell className="font-mono text-xs text-muted-foreground">#{s.id}</TableCell>
                    <TableCell>
                      <div className="font-semibold text-foreground">{s.booker.name}</div>
                      <div className="text-xs text-muted-foreground">{s.booker.email}</div>
                    </TableCell>
                    <TableCell className="font-medium">{s.productName}</TableCell>
                    <TableCell>{s.quantity}</TableCell>
                    <TableCell className="font-medium">
                      {s.price ? formatPKR(parseFloat(s.price.toString()) * s.quantity) : "—"}
                    </TableCell>
                    <TableCell>
                      {s.customer?.name ?? s.customerName ?? <span className="text-muted-foreground">—</span>}
                    </TableCell>
                    <TableCell><StatusBadge status={s.status} /></TableCell>
                    <TableCell className="text-muted-foreground">
                      {new Date(s.createdAt).toLocaleDateString("en-PK", { day: "2-digit", month: "short", year: "numeric" })}
                    </TableCell>
                    <TableCell>
                      <div className="flex items-center gap-1.5">
                        {s.status === "pending" && (
                          <Button size="sm" className="rounded-lg text-xs" onClick={() => setReviewing(s)}>
                            <Eye className="mr-1 h-3 w-3" /> Review
                          </Button>
                        )}
                        {s.signatureBase64 && (
                          <Button
                            size="sm"
                            variant="outline"
                            className="rounded-lg text-xs"
                            onClick={() => setViewingSig(s)}
                          >
                            <FileText className="mr-1 h-3 w-3" /> Sig
                          </Button>
                        )}
                        {!s.signatureBase64 && s.adminNotes && (
                          <span className="text-xs italic text-muted-foreground">"{s.adminNotes}"</span>
                        )}
                      </div>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </div>
        )}
      </SectionCard>

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
