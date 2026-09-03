import { createFileRoute } from "@tanstack/react-router";
import { Boxes, Download, PackageCheck, Search, Send, Truck } from "lucide-react";
import { useMemo, useState } from "react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { EmptyState, SectionCard, StatCard, StatusPill, TableSkeleton } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle } from "@/components/ui/dialog";
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from "@/components/ui/dropdown-menu";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { customers, officers } from "@/lib/mock-data";
import {
  assignSample,
  deliverSample,
  exportCsv,
  pushNotification,
  updateSampleQty,
  useSamples,
  type SampleRecord,
} from "@/lib/store";

export const Route = createFileRoute("/samples")({
  head: () => ({
    meta: [
      { title: "Samples — Bookmark Field Force Manager" },
      { name: "description", content: "Track available, assigned and delivered book samples across the field force." },
      { property: "og:title", content: "Samples — Bookmark Field Force Manager" },
      { property: "og:description", content: "Sample stock, assignments and delivery tracking." },
    ],
  }),
  component: SamplesPage,
});

function SamplesPage() {
  const samples = useSamples();
  const [query, setQuery] = useState("");
  const [status, setStatus] = useState("all");
  const [officer, setOfficer] = useState("all");
  const [loading, setLoading] = useState(false);
  const [assigning, setAssigning] = useState<SampleRecord | null>(null);
  const [qtyFor, setQtyFor] = useState<SampleRecord | null>(null);

  const filtered = useMemo(
    () =>
      samples.filter((s) => {
        const q = query.trim().toLowerCase();
        if (q && !`${s.product} ${s.customer} ${s.id}`.toLowerCase().includes(q)) return false;
        if (status !== "all" && s.status !== status) return false;
        if (officer !== "all" && s.officer !== officer) return false;
        return true;
      }),
    [samples, query, status, officer],
  );

  const count = (s: SampleRecord["status"]) => samples.filter((x) => x.status === s).length;
  const refresh = () => {
    setLoading(true);
    setTimeout(() => setLoading(false), 500);
  };

  return (
    <AppShell title="Samples" subtitle="Sample stock, assignments and deliveries" onRefresh={refresh}>
      <div className="space-y-6">
        <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-4">
          <StatCard label="Available" value={String(count("available"))} icon={<Boxes className="h-5 w-5" />} onClick={() => setStatus("available")} />
          <StatCard label="Assigned" value={String(count("assigned"))} icon={<Send className="h-5 w-5" />} onClick={() => setStatus("assigned")} />
          <StatCard label="Delivered" value={String(count("delivered"))} icon={<PackageCheck className="h-5 w-5" />} onClick={() => setStatus("delivered")} />
          <StatCard label="Total Units" value={String(samples.reduce((a, s) => a + s.qty, 0))} icon={<Truck className="h-5 w-5" />} onClick={() => setStatus("all")} />
        </div>

        <SectionCard
          title="Sample Register"
          description={`${filtered.length} of ${samples.length} records`}
          action={
            <Button
              variant="outline"
              className="rounded-xl"
              onClick={() => {
                exportCsv("samples.csv", filtered);
                toast.success("Samples exported as CSV");
              }}
            >
              <Download className="mr-2 h-4 w-4" /> Export
            </Button>
          }
        >
          <div className="mb-5 grid gap-3 md:grid-cols-3">
            <div className="relative">
              <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
              <Input value={query} onChange={(e) => setQuery(e.target.value)} placeholder="Search product or customer" className="h-11 rounded-xl pl-9" />
            </div>
            <Select value={officer} onValueChange={setOfficer}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Officer" /></SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All officers</SelectItem>
                {officers.map((o) => <SelectItem key={o.id} value={o.name}>{o.name}</SelectItem>)}
              </SelectContent>
            </Select>
            <Select value={status} onValueChange={setStatus}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Status" /></SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All statuses</SelectItem>
                <SelectItem value="available">Available</SelectItem>
                <SelectItem value="assigned">Assigned</SelectItem>
                <SelectItem value="delivered">Delivered</SelectItem>
              </SelectContent>
            </Select>
          </div>

          {loading ? (
            <TableSkeleton />
          ) : filtered.length === 0 ? (
            <EmptyState title="No samples found" description="Adjust the filters to see more sample records." />
          ) : (
            <div className="overflow-x-auto">
              <Table>
                <TableHeader>
                  <TableRow>
                    {["Sample", "Product", "Customer", "Officer", "Qty", "Date", "Status", ""].map((h) => (
                      <TableHead key={h}>{h}</TableHead>
                    ))}
                  </TableRow>
                </TableHeader>
                <TableBody>
                  {filtered.map((s) => (
                    <TableRow key={s.id} className="transition-colors hover:bg-muted/50">
                      <TableCell className="font-medium">{s.id}</TableCell>
                      <TableCell>{s.product}</TableCell>
                      <TableCell>{s.customer || "—"}</TableCell>
                      <TableCell>{s.officer || "—"}</TableCell>
                      <TableCell>{s.qty}</TableCell>
                      <TableCell>{s.date}</TableCell>
                      <TableCell><StatusPill value={s.status === "available" ? "in-stock" : s.status} /></TableCell>
                      <TableCell className="text-right">
                        <DropdownMenu>
                          <DropdownMenuTrigger asChild>
                            <Button variant="ghost" className="rounded-xl text-xs">Actions</Button>
                          </DropdownMenuTrigger>
                          <DropdownMenuContent align="end" className="rounded-xl">
                            <DropdownMenuItem onClick={() => setAssigning(s)}>Assign sample</DropdownMenuItem>
                            <DropdownMenuItem onClick={() => setQtyFor(s)}>Update quantity</DropdownMenuItem>
                            <DropdownMenuItem
                              disabled={s.status === "delivered"}
                              onClick={() => {
                                deliverSample(s.id);
                                pushNotification({
                                  type: "visit",
                                  title: "Sample delivered",
                                  body: `${s.qty} × ${s.product} delivered to ${s.customer || "customer"}.`,
                                  to: "/samples",
                                });
                                toast.success("Delivery recorded");
                              }}
                            >
                              Track delivery
                            </DropdownMenuItem>
                          </DropdownMenuContent>
                        </DropdownMenu>
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </div>
          )}
        </SectionCard>
      </div>

      <Dialog open={!!assigning} onOpenChange={(o) => !o && setAssigning(null)}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle>Assign sample</DialogTitle>
            <DialogDescription>{assigning?.product}</DialogDescription>
          </DialogHeader>
          {assigning && (
            <AssignSampleForm
              record={assigning}
              onSave={(customer, off, qty) => {
                assignSample(assigning.id, customer, off, qty);
                pushNotification({ type: "request", title: "Sample assigned", body: `${qty} × ${assigning.product} assigned to ${off}.`, to: "/samples" });
                setAssigning(null);
                toast.success("Sample assigned");
              }}
            />
          )}
        </DialogContent>
      </Dialog>

      <Dialog open={!!qtyFor} onOpenChange={(o) => !o && setQtyFor(null)}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle>Update quantity</DialogTitle>
            <DialogDescription>{qtyFor?.product}</DialogDescription>
          </DialogHeader>
          {qtyFor && (
            <QtyForm
              value={qtyFor.qty}
              onSave={(qty) => {
                updateSampleQty(qtyFor.id, qty);
                setQtyFor(null);
                toast.success("Quantity updated");
              }}
            />
          )}
        </DialogContent>
      </Dialog>
    </AppShell>
  );
}

function AssignSampleForm({
  record,
  onSave,
}: {
  record: SampleRecord;
  onSave: (customer: string, officer: string, qty: number) => void;
}) {
  const [customer, setCustomer] = useState(record.customer || customers[0].name);
  const [officer, setOfficer] = useState(record.officer || officers[0].name);
  const [qty, setQty] = useState(String(record.qty || 4));
  return (
    <>
      <div className="grid gap-4">
        <div className="space-y-2">
          <Label>Customer</Label>
          <Select value={customer} onValueChange={setCustomer}>
            <SelectTrigger className="h-11 rounded-xl"><SelectValue /></SelectTrigger>
            <SelectContent>{customers.map((c) => <SelectItem key={c.id} value={c.name}>{c.name}</SelectItem>)}</SelectContent>
          </Select>
        </div>
        <div className="space-y-2">
          <Label>Officer</Label>
          <Select value={officer} onValueChange={setOfficer}>
            <SelectTrigger className="h-11 rounded-xl"><SelectValue /></SelectTrigger>
            <SelectContent>{officers.map((o) => <SelectItem key={o.id} value={o.name}>{o.name}</SelectItem>)}</SelectContent>
          </Select>
        </div>
        <div className="space-y-2">
          <Label>Quantity</Label>
          <Input type="number" min={1} value={qty} onChange={(e) => setQty(e.target.value)} className="h-11 rounded-xl" />
        </div>
      </div>
      <DialogFooter>
        <Button className="rounded-xl" onClick={() => onSave(customer, officer, Number(qty) || 1)}>Assign</Button>
      </DialogFooter>
    </>
  );
}

function QtyForm({ value, onSave }: { value: number; onSave: (qty: number) => void }) {
  const [qty, setQty] = useState(String(value));
  return (
    <>
      <div className="space-y-2">
        <Label>Quantity</Label>
        <Input type="number" min={0} value={qty} onChange={(e) => setQty(e.target.value)} className="h-11 rounded-xl" />
      </div>
      <DialogFooter>
        <Button className="rounded-xl" onClick={() => onSave(Number(qty) || 0)}>Save</Button>
      </DialogFooter>
    </>
  );
}
