import { createFileRoute } from "@tanstack/react-router";
import { CheckCircle2, LifeBuoy, Loader2, Search, TicketPlus } from "lucide-react";
import { useMemo, useState } from "react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { EmptyState, SectionCard, StatCard, StatusPill } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle } from "@/components/ui/dialog";
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from "@/components/ui/dropdown-menu";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Textarea } from "@/components/ui/textarea";
import { officers, tickets as seedTickets } from "@/lib/mock-data";
import { exportCsv } from "@/lib/store";

export const Route = createFileRoute("/support-tickets")({
  head: () => ({
    meta: [
      { title: "Support Tickets — Bookmark Field Force Manager" },
      { name: "description", content: "Field officer support requests with priority, assignment and resolution tracking." },
      { property: "og:title", content: "Support Tickets — Bookmark Field Force Manager" },
      { property: "og:description", content: "Track and resolve field officer support tickets." },
    ],
  }),
  component: TicketsPage,
});

type Ticket = (typeof seedTickets)[number] & { notes?: string };

function TicketsPage() {
  const [rows, setRows] = useState<Ticket[]>(seedTickets);
  const [query, setQuery] = useState("");
  const [status, setStatus] = useState("all");
  const [priority, setPriority] = useState("all");
  const [creating, setCreating] = useState(false);

  const filtered = useMemo(
    () =>
      rows.filter((t) => {
        const q = query.trim().toLowerCase();
        if (q && !`${t.subject} ${t.officer} ${t.id}`.toLowerCase().includes(q)) return false;
        if (status !== "all" && t.status !== status) return false;
        if (priority !== "all" && t.priority !== priority) return false;
        return true;
      }),
    [rows, query, status, priority],
  );

  const setTicket = (id: string, patch: Partial<Ticket>) =>
    setRows((prev) => prev.map((t) => (t.id === id ? { ...t, ...patch } : t)));

  return (
    <AppShell title="Support Tickets" subtitle="Field issues, escalations and resolutions">
      <div className="space-y-6">
        <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-4">
          <StatCard label="Open" value={String(rows.filter((t) => t.status === "open").length)} icon={<LifeBuoy className="h-5 w-5" />} onClick={() => setStatus("open")} />
          <StatCard label="In Progress" value={String(rows.filter((t) => t.status === "in-progress").length)} icon={<Loader2 className="h-5 w-5" />} onClick={() => setStatus("in-progress")} />
          <StatCard label="Resolved" value={String(rows.filter((t) => t.status === "resolved").length)} icon={<CheckCircle2 className="h-5 w-5" />} onClick={() => setStatus("resolved")} />
          <StatCard label="High Priority" value={String(rows.filter((t) => t.priority === "high").length)} icon={<LifeBuoy className="h-5 w-5" />} onClick={() => setPriority("high")} />
        </div>

        <SectionCard
          title="Ticket Queue"
          description={`${filtered.length} of ${rows.length} tickets`}
          action={
            <div className="flex gap-2">
              <Button variant="outline" className="rounded-xl" onClick={() => { exportCsv("tickets.csv", filtered); toast.success("Tickets exported"); }}>
                Export
              </Button>
              <Button className="rounded-xl" onClick={() => setCreating(true)}>
                <TicketPlus className="mr-2 h-4 w-4" /> New ticket
              </Button>
            </div>
          }
        >
          <div className="mb-5 grid gap-3 md:grid-cols-3">
            <div className="relative">
              <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
              <Input value={query} onChange={(e) => setQuery(e.target.value)} placeholder="Search subject or officer" className="h-11 rounded-xl pl-9" />
            </div>
            <Select value={status} onValueChange={setStatus}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Status" /></SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All statuses</SelectItem>
                <SelectItem value="open">Open</SelectItem>
                <SelectItem value="in-progress">In progress</SelectItem>
                <SelectItem value="resolved">Resolved</SelectItem>
              </SelectContent>
            </Select>
            <Select value={priority} onValueChange={setPriority}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Priority" /></SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All priorities</SelectItem>
                <SelectItem value="high">High</SelectItem>
                <SelectItem value="medium">Medium</SelectItem>
                <SelectItem value="low">Low</SelectItem>
              </SelectContent>
            </Select>
          </div>

          {filtered.length === 0 ? (
            <EmptyState title="No tickets" description="Nothing matches the current filters." />
          ) : (
            <div className="overflow-x-auto">
              <Table>
                <TableHeader>
                  <TableRow>
                    {["Ticket", "Subject", "Officer", "Priority", "Created", "Status", ""].map((h) => (
                      <TableHead key={h}>{h}</TableHead>
                    ))}
                  </TableRow>
                </TableHeader>
                <TableBody>
                  {filtered.map((t) => (
                    <TableRow key={t.id} className="transition-colors hover:bg-muted/50">
                      <TableCell className="font-medium">{t.id}</TableCell>
                      <TableCell>{t.subject}</TableCell>
                      <TableCell>{t.officer}</TableCell>
                      <TableCell><StatusPill value={t.priority} /></TableCell>
                      <TableCell>{t.created}</TableCell>
                      <TableCell><StatusPill value={t.status} /></TableCell>
                      <TableCell className="text-right">
                        <DropdownMenu>
                          <DropdownMenuTrigger asChild>
                            <Button variant="ghost" className="rounded-xl text-xs">Actions</Button>
                          </DropdownMenuTrigger>
                          <DropdownMenuContent align="end" className="rounded-xl">
                            <DropdownMenuItem onClick={() => { setTicket(t.id, { status: "in-progress" }); toast.success("Ticket picked up"); }}>
                              Start working
                            </DropdownMenuItem>
                            <DropdownMenuItem onClick={() => { setTicket(t.id, { status: "resolved" }); toast.success("Ticket resolved"); }}>
                              Mark resolved
                            </DropdownMenuItem>
                            <DropdownMenuItem onClick={() => { setTicket(t.id, { priority: "high" }); toast.success("Escalated to high priority"); }}>
                              Escalate
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

      <Dialog open={creating} onOpenChange={setCreating}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle>New support ticket</DialogTitle>
            <DialogDescription>Log an issue reported by a field officer.</DialogDescription>
          </DialogHeader>
          <NewTicketForm
            onSave={(t) => {
              setRows((prev) => [t, ...prev]);
              setCreating(false);
              toast.success("Ticket created");
            }}
          />
        </DialogContent>
      </Dialog>
    </AppShell>
  );
}

function NewTicketForm({ onSave }: { onSave: (t: Ticket) => void }) {
  const [subject, setSubject] = useState("");
  const [officer, setOfficer] = useState(officers[0].name);
  const [priority, setPriority] = useState("medium");
  const [notes, setNotes] = useState("");
  const [error, setError] = useState("");
  return (
    <>
      <div className="grid gap-4">
        <div className="space-y-2">
          <Label>Subject</Label>
          <Input value={subject} onChange={(e) => setSubject(e.target.value)} placeholder="GPS not updating on device" className="h-11 rounded-xl" />
        </div>
        <div className="space-y-2">
          <Label>Officer</Label>
          <Select value={officer} onValueChange={setOfficer}>
            <SelectTrigger className="h-11 rounded-xl"><SelectValue /></SelectTrigger>
            <SelectContent>{officers.map((o) => <SelectItem key={o.id} value={o.name}>{o.name}</SelectItem>)}</SelectContent>
          </Select>
        </div>
        <div className="space-y-2">
          <Label>Priority</Label>
          <Select value={priority} onValueChange={setPriority}>
            <SelectTrigger className="h-11 rounded-xl"><SelectValue /></SelectTrigger>
            <SelectContent>
              <SelectItem value="high">High</SelectItem>
              <SelectItem value="medium">Medium</SelectItem>
              <SelectItem value="low">Low</SelectItem>
            </SelectContent>
          </Select>
        </div>
        <div className="space-y-2">
          <Label>Notes</Label>
          <Textarea value={notes} onChange={(e) => setNotes(e.target.value)} rows={3} className="rounded-xl" />
        </div>
        {error ? <p className="text-sm text-destructive">{error}</p> : null}
      </div>
      <DialogFooter>
        <Button
          className="rounded-xl"
          onClick={() => {
            if (!subject.trim()) {
              setError("Subject is required.");
              return;
            }
            onSave({
              id: `TKT-${Math.floor(Math.random() * 500) + 500}`,
              subject: subject.trim(),
              officer,
              priority,
              status: "open",
              created: new Date().toISOString().slice(0, 10),
              notes,
            });
          }}
        >
          Create ticket
        </Button>
      </DialogFooter>
    </>
  );
}
