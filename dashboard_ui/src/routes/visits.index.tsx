import { createFileRoute, useNavigate } from "@tanstack/react-router";
import {
  CalendarClock,
  CheckCircle2,
  Clock,
  Download,
  Eye,
  MoreHorizontal,
  Pencil,
  Plus,
  Route as RouteIcon,
  Search,
  Timer,
  Trash2,
  UserCog,
  XCircle,
} from "lucide-react";
import { useMemo, useState } from "react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { EmptyState, SectionCard, StatCard, StatusPill, TableSkeleton } from "@/components/app/ui-bits";
import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
} from "@/components/ui/alert-dialog";
import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Textarea } from "@/components/ui/textarea";
import { customers, officers } from "@/lib/mock-data";
import {
  addVisit,
  assignVisitOfficer,
  deleteVisit,
  exportCsv,
  pushNotification,
  rescheduleVisit,
  updateVisit,
  useVisits,
  type VisitRecord,
} from "@/lib/store";

export const Route = createFileRoute("/visits/")({
  head: () => ({
    meta: [
      { title: "Visits — Bookmark Field Force Manager" },
      { name: "description", content: "Plan, assign, reschedule and track every field visit across all territories." },
      { property: "og:title", content: "Visits — Bookmark Field Force Manager" },
      { property: "og:description", content: "Plan, assign and track field visits in real time." },
    ],
  }),
  component: VisitsPage,
});

const cities = ["Karachi", "Lahore", "Rawalpindi", "Islamabad", "Multan"];

function VisitsPage() {
  const visits = useVisits();
  const navigate = useNavigate();

  const [query, setQuery] = useState("");
  const [officer, setOfficer] = useState("all");
  const [city, setCity] = useState("all");
  const [date, setDate] = useState("");
  const [status, setStatus] = useState("all");
  const [loading, setLoading] = useState(false);

  const [editing, setEditing] = useState<VisitRecord | null>(null);
  const [assigning, setAssigning] = useState<VisitRecord | null>(null);
  const [rescheduling, setRescheduling] = useState<VisitRecord | null>(null);
  const [deleting, setDeleting] = useState<VisitRecord | null>(null);
  const [creating, setCreating] = useState(false);

  const filtered = useMemo(
    () =>
      visits.filter((v) => {
        const q = query.trim().toLowerCase();
        if (q && !`${v.customer} ${v.id} ${v.officer}`.toLowerCase().includes(q)) return false;
        if (officer !== "all" && v.officerId !== officer) return false;
        if (city !== "all" && v.city !== city) return false;
        if (date && v.date !== date) return false;
        if (status !== "all" && v.status !== status) return false;
        return true;
      }),
    [visits, query, officer, city, date, status],
  );

  const count = (s: string) => visits.filter((v) => v.status === s).length;
  const refresh = () => {
    setLoading(true);
    setTimeout(() => setLoading(false), 550);
  };

  return (
    <AppShell title="Visits" subtitle="Every planned and executed field visit" onRefresh={refresh}>
      <div className="space-y-6">
        <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-5">
          <StatCard label="Today's Visits" value="97" icon={<CalendarClock className="h-5 w-5" />} onClick={() => setDate("")} />
          <StatCard label="Completed" value={String(count("completed"))} icon={<CheckCircle2 className="h-5 w-5" />} onClick={() => setStatus("completed")} />
          <StatCard label="Pending" value={String(count("pending"))} icon={<Clock className="h-5 w-5" />} onClick={() => setStatus("pending")} />
          <StatCard label="Missed" value={String(count("missed"))} icon={<XCircle className="h-5 w-5" />} onClick={() => setStatus("missed")} />
          <StatCard label="In Progress" value={String(count("in-progress"))} icon={<Timer className="h-5 w-5" />} onClick={() => setStatus("in-progress")} />
        </div>

        <SectionCard
          title="Visit Register"
          description={`${filtered.length} of ${visits.length} visits`}
          action={
            <div className="flex gap-2">
              <Button
                variant="outline"
                className="rounded-xl"
                onClick={() => {
                  exportCsv("visits.csv", filtered.map(({ timeline, images, productsDiscussed, ...r }) => r));
                  toast.success("Visits exported as CSV");
                }}
              >
                <Download className="mr-2 h-4 w-4" /> Export
              </Button>
              <Button className="rounded-xl" onClick={() => setCreating(true)}>
                <Plus className="mr-2 h-4 w-4" /> Create Visit
              </Button>
            </div>
          }
        >
          <div className="mb-5 grid gap-3 md:grid-cols-2 xl:grid-cols-5">
            <div className="relative">
              <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
              <Input
                value={query}
                onChange={(e) => setQuery(e.target.value)}
                placeholder="Search customer or visit ID"
                className="h-11 rounded-xl pl-9"
              />
            </div>
            <Select value={officer} onValueChange={setOfficer}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Officer" /></SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All officers</SelectItem>
                {officers.map((o) => (
                  <SelectItem key={o.id} value={o.id}>{o.name}</SelectItem>
                ))}
              </SelectContent>
            </Select>
            <Select value={city} onValueChange={setCity}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="City" /></SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All cities</SelectItem>
                {cities.map((c) => (
                  <SelectItem key={c} value={c}>{c}</SelectItem>
                ))}
              </SelectContent>
            </Select>
            <Input type="date" value={date} onChange={(e) => setDate(e.target.value)} className="h-11 rounded-xl" />
            <Select value={status} onValueChange={setStatus}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Status" /></SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All statuses</SelectItem>
                <SelectItem value="completed">Completed</SelectItem>
                <SelectItem value="pending">Pending</SelectItem>
                <SelectItem value="in-progress">In progress</SelectItem>
                <SelectItem value="missed">Missed</SelectItem>
              </SelectContent>
            </Select>
          </div>

          {loading ? (
            <TableSkeleton />
          ) : filtered.length === 0 ? (
            <EmptyState title="No visits match these filters" description="Adjust the search, officer, city, date or status filters." />
          ) : (
            <div className="overflow-x-auto">
              <Table>
                <TableHeader>
                  <TableRow>
                    {["Visit ID", "Customer", "Officer", "City", "Date", "Time", "Status", ""].map((h) => (
                      <TableHead key={h}>{h}</TableHead>
                    ))}
                  </TableRow>
                </TableHeader>
                <TableBody>
                  {filtered.map((v) => (
                    <TableRow key={v.id} className="transition-colors hover:bg-muted/50">
                      <TableCell className="font-medium">{v.id}</TableCell>
                      <TableCell>{v.customer}</TableCell>
                      <TableCell>{v.officer}</TableCell>
                      <TableCell>{v.city}</TableCell>
                      <TableCell>{v.date}</TableCell>
                      <TableCell>{v.time}</TableCell>
                      <TableCell><StatusPill value={v.status} /></TableCell>
                      <TableCell className="text-right">
                        <DropdownMenu>
                          <DropdownMenuTrigger asChild>
                            <Button variant="ghost" size="icon" className="h-9 w-9 rounded-xl">
                              <MoreHorizontal className="h-4 w-4" />
                            </Button>
                          </DropdownMenuTrigger>
                          <DropdownMenuContent align="end" className="w-48 rounded-xl">
                            <DropdownMenuItem onClick={() => navigate({ to: "/visits/$id", params: { id: v.id } })}>
                              <Eye className="mr-2 h-4 w-4" /> View
                            </DropdownMenuItem>
                            <DropdownMenuItem onClick={() => setEditing(v)}>
                              <Pencil className="mr-2 h-4 w-4" /> Edit
                            </DropdownMenuItem>
                            <DropdownMenuItem onClick={() => setAssigning(v)}>
                              <UserCog className="mr-2 h-4 w-4" /> Assign Officer
                            </DropdownMenuItem>
                            <DropdownMenuItem onClick={() => setRescheduling(v)}>
                              <CalendarClock className="mr-2 h-4 w-4" /> Reschedule
                            </DropdownMenuItem>
                            <DropdownMenuItem className="text-destructive focus:text-destructive" onClick={() => setDeleting(v)}>
                              <Trash2 className="mr-2 h-4 w-4" /> Delete
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

      {/* Edit */}
      <Dialog open={!!editing} onOpenChange={(o) => !o && setEditing(null)}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle>Edit visit {editing?.id}</DialogTitle>
            <DialogDescription>Update the purpose and notes for this visit.</DialogDescription>
          </DialogHeader>
          {editing && (
            <EditForm
              visit={editing}
              onSave={(purpose, notes) => {
                updateVisit(editing.id, { purpose, notes });
                setEditing(null);
                toast.success("Visit updated");
              }}
            />
          )}
        </DialogContent>
      </Dialog>

      {/* Assign */}
      <Dialog open={!!assigning} onOpenChange={(o) => !o && setAssigning(null)}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle>Assign officer</DialogTitle>
            <DialogDescription>Reassign {assigning?.id} to another field officer.</DialogDescription>
          </DialogHeader>
          <AssignForm
            current={assigning?.officerId ?? ""}
            onSave={(officerId) => {
              if (!assigning) return;
              assignVisitOfficer(assigning.id, officerId);
              pushNotification({
                type: "visit",
                title: "Visit reassigned",
                body: `${assigning.id} assigned to ${officers.find((o) => o.id === officerId)?.name}.`,
                to: "/visits",
              });
              setAssigning(null);
              toast.success("Officer assigned");
            }}
          />
        </DialogContent>
      </Dialog>

      {/* Reschedule */}
      <Dialog open={!!rescheduling} onOpenChange={(o) => !o && setRescheduling(null)}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle>Reschedule visit</DialogTitle>
            <DialogDescription>Pick a new date and time for {rescheduling?.id}.</DialogDescription>
          </DialogHeader>
          <RescheduleForm
            date={rescheduling?.date ?? ""}
            time={rescheduling?.time ?? ""}
            onSave={(d, t) => {
              if (!rescheduling) return;
              rescheduleVisit(rescheduling.id, d, t);
              setRescheduling(null);
              toast.success("Visit rescheduled", { description: `${d} at ${t}` });
            }}
          />
        </DialogContent>
      </Dialog>

      {/* Create */}
      <Dialog open={creating} onOpenChange={setCreating}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle>Create visit</DialogTitle>
            <DialogDescription>Schedule a new customer visit and assign an officer.</DialogDescription>
          </DialogHeader>
          <CreateForm
            onSave={(payload) => {
              const id = addVisit(payload);
              setCreating(false);
              toast.success("Visit created", {
                description: `${id} scheduled for ${payload.date}`,
                action: { label: "Open", onClick: () => navigate({ to: "/visits/$id", params: { id } }) },
              });
            }}
          />
        </DialogContent>
      </Dialog>

      {/* Delete */}
      <AlertDialog open={!!deleting} onOpenChange={(o) => !o && setDeleting(null)}>
        <AlertDialogContent className="rounded-2xl">
          <AlertDialogHeader>
            <AlertDialogTitle>Delete {deleting?.id}?</AlertDialogTitle>
            <AlertDialogDescription>
              This removes the visit from the register. Reports already generated are unaffected.
            </AlertDialogDescription>
          </AlertDialogHeader>
          <AlertDialogFooter>
            <AlertDialogCancel className="rounded-xl">Cancel</AlertDialogCancel>
            <AlertDialogAction
              className="rounded-xl bg-destructive text-destructive-foreground hover:bg-destructive/90"
              onClick={() => {
                if (!deleting) return;
                deleteVisit(deleting.id);
                toast.success("Visit deleted");
                setDeleting(null);
              }}
            >
              Delete
            </AlertDialogAction>
          </AlertDialogFooter>
        </AlertDialogContent>
      </AlertDialog>
    </AppShell>
  );
}

function EditForm({ visit, onSave }: { visit: VisitRecord; onSave: (purpose: string, notes: string) => void }) {
  const [purpose, setPurpose] = useState(visit.purpose);
  const [notes, setNotes] = useState(visit.notes);
  return (
    <>
      <div className="space-y-4">
        <div className="space-y-2">
          <Label>Purpose</Label>
          <Input value={purpose} onChange={(e) => setPurpose(e.target.value)} className="h-11 rounded-xl" />
        </div>
        <div className="space-y-2">
          <Label>Notes</Label>
          <Textarea value={notes} onChange={(e) => setNotes(e.target.value)} className="min-h-24 rounded-xl" />
        </div>
      </div>
      <DialogFooter>
        <Button className="rounded-xl" onClick={() => onSave(purpose.trim() || visit.purpose, notes)}>
          Save changes
        </Button>
      </DialogFooter>
    </>
  );
}

function AssignForm({ current, onSave }: { current: string; onSave: (officerId: string) => void }) {
  const [value, setValue] = useState(current);
  return (
    <>
      <div className="space-y-2">
        <Label>Field officer</Label>
        <Select value={value} onValueChange={setValue}>
          <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Select officer" /></SelectTrigger>
          <SelectContent>
            {officers.map((o) => (
              <SelectItem key={o.id} value={o.id}>
                {o.name} · {o.city} · {o.status}
              </SelectItem>
            ))}
          </SelectContent>
        </Select>
      </div>
      <DialogFooter>
        <Button className="rounded-xl" disabled={!value} onClick={() => onSave(value)}>
          Assign
        </Button>
      </DialogFooter>
    </>
  );
}

function RescheduleForm({ date, time, onSave }: { date: string; time: string; onSave: (d: string, t: string) => void }) {
  const [d, setD] = useState(date);
  const [t, setT] = useState(time);
  return (
    <>
      <div className="grid gap-4 sm:grid-cols-2">
        <div className="space-y-2">
          <Label>Date</Label>
          <Input type="date" value={d} onChange={(e) => setD(e.target.value)} className="h-11 rounded-xl" />
        </div>
        <div className="space-y-2">
          <Label>Time</Label>
          <Input type="time" value={t} onChange={(e) => setT(e.target.value)} className="h-11 rounded-xl" />
        </div>
      </div>
      <DialogFooter>
        <Button className="rounded-xl" disabled={!d || !t} onClick={() => onSave(d, t)}>
          Reschedule
        </Button>
      </DialogFooter>
    </>
  );
}

function CreateForm({ onSave }: { onSave: (v: Omit<VisitRecord, "id" | "timeline">) => void }) {
  const [customerId, setCustomerId] = useState(customers[0].id);
  const [officerId, setOfficerId] = useState(officers[0].id);
  const [date, setDate] = useState("2026-09-05");
  const [time, setTime] = useState("10:00");
  const [purpose, setPurpose] = useState("Order follow-up");
  const [error, setError] = useState("");

  const submit = () => {
    if (!date || !time || !purpose.trim()) {
      setError("Date, time and purpose are required.");
      return;
    }
    const customer = customers.find((c) => c.id === customerId)!;
    const officer = officers.find((o) => o.id === officerId)!;
    onSave({
      customer: customer.name,
      customerId: customer.id,
      officer: officer.name,
      officerId: officer.id,
      city: customer.city,
      date,
      time,
      purpose,
      status: "pending",
      duration: "—",
      notes: "",
      images: [],
      productsDiscussed: [],
      samplesDelivered: 0,
      followUp: "",
    });
  };

  return (
    <>
      <div className="grid gap-4 sm:grid-cols-2">
        <div className="space-y-2 sm:col-span-2">
          <Label>Customer</Label>
          <Select value={customerId} onValueChange={setCustomerId}>
            <SelectTrigger className="h-11 rounded-xl"><SelectValue /></SelectTrigger>
            <SelectContent>
              {customers.map((c) => (
                <SelectItem key={c.id} value={c.id}>{c.name} · {c.city}</SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>
        <div className="space-y-2 sm:col-span-2">
          <Label>Officer</Label>
          <Select value={officerId} onValueChange={setOfficerId}>
            <SelectTrigger className="h-11 rounded-xl"><SelectValue /></SelectTrigger>
            <SelectContent>
              {officers.map((o) => (
                <SelectItem key={o.id} value={o.id}>{o.name} · {o.city}</SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>
        <div className="space-y-2">
          <Label>Date</Label>
          <Input type="date" value={date} onChange={(e) => setDate(e.target.value)} className="h-11 rounded-xl" />
        </div>
        <div className="space-y-2">
          <Label>Time</Label>
          <Input type="time" value={time} onChange={(e) => setTime(e.target.value)} className="h-11 rounded-xl" />
        </div>
        <div className="space-y-2 sm:col-span-2">
          <Label>Purpose</Label>
          <Input value={purpose} onChange={(e) => setPurpose(e.target.value)} className="h-11 rounded-xl" />
        </div>
        {error ? <p className="text-sm text-destructive sm:col-span-2">{error}</p> : null}
      </div>
      <DialogFooter>
        <Button className="rounded-xl" onClick={submit}>
          <RouteIcon className="mr-2 h-4 w-4" /> Create visit
        </Button>
      </DialogFooter>
    </>
  );
}
