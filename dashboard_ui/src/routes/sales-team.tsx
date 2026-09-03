import { createFileRoute, Link, useNavigate } from "@tanstack/react-router";
import {
  Eye,
  MapPin,
  MoreHorizontal,
  Pencil,
  Plus,
  Satellite,
  Search,
  Trash2,
  UserCheck,
  Users,
} from "lucide-react";
import { useMemo, useState } from "react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { EmptyState, SectionCard, StatCard, StatusPill, TableSkeleton } from "@/components/app/ui-bits";
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
import { initials, officers as seedOfficers } from "@/lib/mock-data";

export const Route = createFileRoute("/sales-team")({
  head: () => ({
    meta: [
      { title: "Sales Team — Bookmark Field Force Manager" },
      { name: "description", content: "Manage Bookmark field officers, GPS status and approvals." },
      { property: "og:title", content: "Sales Team — Bookmark Field Force Manager" },
      { property: "og:description", content: "Manage field officers, GPS status and approvals." },
    ],
  }),
  component: SalesTeamPage,
});

function SalesTeamPage() {
  const navigate = useNavigate();
  const [list, setList] = useState(seedOfficers);
  const [query, setQuery] = useState("");
  const [gps, setGps] = useState("all");
  const [approval, setApproval] = useState("all");
  const [loading, setLoading] = useState(false);
  const [addOpen, setAddOpen] = useState(false);
  const [deleteId, setDeleteId] = useState<string | null>(null);
  const [form, setForm] = useState({ name: "", email: "", phone: "", city: "Karachi" });

  const filtered = useMemo(
    () =>
      list.filter((o) => {
        const q = query.trim().toLowerCase();
        const matchQ =
          !q ||
          [o.name, o.email, o.phone, o.city].some((f) => f.toLowerCase().includes(q));
        const matchGps = gps === "all" || (gps === "on" ? o.gps : !o.gps);
        const matchApproval = approval === "all" || o.approved === approval;
        return matchQ && matchGps && matchApproval;
      }),
    [list, query, gps, approval],
  );

  const refresh = () => {
    setLoading(true);
    setTimeout(() => setLoading(false), 600);
  };

  const addOfficer = () => {
    if (!form.name.trim() || !form.email.trim()) {
      toast.error("Name and email are required");
      return;
    }
    const id = `OFF-${String(list.length + 1).padStart(3, "0")}`;
    setList((prev) => [
      {
        ...seedOfficers[0],
        id,
        name: form.name.trim(),
        email: form.email.trim(),
        phone: form.phone.trim() || "+92 300 0000000",
        city: form.city,
        status: "idle",
        approved: "pending",
        todayVisits: 0,
        totalVisits: 0,
        completedVisits: 0,
      },
      ...prev,
    ]);
    setAddOpen(false);
    setForm({ name: "", email: "", phone: "", city: "Karachi" });
    toast.success("Officer added", { description: "Approval request sent to the regional manager." });
  };

  return (
    <AppShell title="Sales Team" subtitle="Field officers, coverage and GPS health" onRefresh={refresh}>
      <div className="space-y-6">
        <div className="grid gap-4 sm:grid-cols-3">
          <StatCard label="Total Officers" value={String(list.length)} icon={<Users className="h-5 w-5" />} onClick={refresh} />
          <StatCard
            label="Active"
            value={String(list.filter((o) => o.status === "active").length)}
            icon={<Satellite className="h-5 w-5" />}
            to="/live-activity"
          />
          <StatCard
            label="Pending Approval"
            value={String(list.filter((o) => o.approved === "pending").length)}
            icon={<UserCheck className="h-5 w-5" />}
            onClick={() => setApproval("pending")}
          />
        </div>

        <SectionCard
          title="All Officers"
          description={`${filtered.length} of ${list.length} officers`}
          action={
            <Button className="rounded-xl" onClick={() => setAddOpen(true)}>
              <Plus className="mr-2 h-4 w-4" /> Add Officer
            </Button>
          }
        >
          <div className="mb-5 flex flex-wrap gap-3">
            <div className="relative min-w-64 flex-1">
              <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
              <Input
                value={query}
                onChange={(e) => setQuery(e.target.value)}
                placeholder="Search name, email, phone or city"
                className="h-11 rounded-xl pl-9"
              />
            </div>
            <Select value={gps} onValueChange={setGps}>
              <SelectTrigger className="h-11 w-40 rounded-xl">
                <SelectValue placeholder="GPS status" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All GPS</SelectItem>
                <SelectItem value="on">GPS On</SelectItem>
                <SelectItem value="off">GPS Off</SelectItem>
              </SelectContent>
            </Select>
            <Select value={approval} onValueChange={setApproval}>
              <SelectTrigger className="h-11 w-44 rounded-xl">
                <SelectValue placeholder="Approval status" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All approvals</SelectItem>
                <SelectItem value="approved">Approved</SelectItem>
                <SelectItem value="pending">Pending</SelectItem>
              </SelectContent>
            </Select>
          </div>

          {loading ? (
            <TableSkeleton />
          ) : filtered.length === 0 ? (
            <EmptyState title="No officers found" description="Try a different search term or reset the filters." />
          ) : (
            <div className="overflow-x-auto">
              <Table>
                <TableHeader>
                  <TableRow>
                    <TableHead>Officer</TableHead>
                    <TableHead>Email</TableHead>
                    <TableHead>City</TableHead>
                    <TableHead>Phone</TableHead>
                    <TableHead>GPS</TableHead>
                    <TableHead>Status</TableHead>
                    <TableHead className="text-right">Actions</TableHead>
                  </TableRow>
                </TableHeader>
                <TableBody>
                  {filtered.map((o) => (
                    <TableRow key={o.id} className="transition-colors hover:bg-muted/50">
                      <TableCell>
                        <div className="flex items-center gap-3">
                          <span className="flex h-9 w-9 items-center justify-center rounded-full bg-navy text-xs font-bold text-navy-foreground">
                            {initials(o.name)}
                          </span>
                          <div>
                            <Link
                              to="/officer/$id"
                              params={{ id: o.id }}
                              className="font-medium hover:text-primary hover:underline"
                            >
                              {o.name}
                            </Link>
                            <p className="text-xs text-muted-foreground">{o.id}</p>
                          </div>
                        </div>
                      </TableCell>
                      <TableCell className="text-sm text-muted-foreground">{o.email}</TableCell>
                      <TableCell>{o.city}</TableCell>
                      <TableCell className="text-sm text-muted-foreground">{o.phone}</TableCell>
                      <TableCell>
                        <StatusPill value={o.gps ? "active" : "offline"} />
                      </TableCell>
                      <TableCell>
                        <StatusPill value={o.approved} />
                      </TableCell>
                      <TableCell className="text-right">
                        <DropdownMenu>
                          <DropdownMenuTrigger asChild>
                            <Button variant="ghost" size="icon" className="h-9 w-9 rounded-lg">
                              <MoreHorizontal className="h-4 w-4" />
                            </Button>
                          </DropdownMenuTrigger>
                          <DropdownMenuContent align="end" className="rounded-xl">
                            <DropdownMenuItem onClick={() => navigate({ to: "/officer/$id", params: { id: o.id } })}>
                              <Eye className="mr-2 h-4 w-4" /> View
                            </DropdownMenuItem>
                            <DropdownMenuItem onClick={() => toast.info(`Editing ${o.name}`)}>
                              <Pencil className="mr-2 h-4 w-4" /> Edit
                            </DropdownMenuItem>
                            <DropdownMenuItem onClick={() => navigate({ to: "/live-location" })}>
                              <MapPin className="mr-2 h-4 w-4" /> Track Location
                            </DropdownMenuItem>
                            <DropdownMenuItem
                              className="text-destructive focus:text-destructive"
                              onClick={() => setDeleteId(o.id)}
                            >
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

      <Dialog open={addOpen} onOpenChange={setAddOpen}>
        <DialogContent className="rounded-2xl sm:max-w-lg">
          <DialogHeader>
            <DialogTitle>Add Field Officer</DialogTitle>
            <DialogDescription>New officers start as pending until approved.</DialogDescription>
          </DialogHeader>
          <div className="grid gap-4">
            <div className="space-y-2">
              <Label>Full name</Label>
              <Input value={form.name} onChange={(e) => setForm({ ...form, name: e.target.value })} className="rounded-xl" />
            </div>
            <div className="space-y-2">
              <Label>Email</Label>
              <Input value={form.email} onChange={(e) => setForm({ ...form, email: e.target.value })} className="rounded-xl" />
            </div>
            <div className="grid gap-4 sm:grid-cols-2">
              <div className="space-y-2">
                <Label>Phone</Label>
                <Input value={form.phone} onChange={(e) => setForm({ ...form, phone: e.target.value })} className="rounded-xl" />
              </div>
              <div className="space-y-2">
                <Label>City</Label>
                <Select value={form.city} onValueChange={(v) => setForm({ ...form, city: v })}>
                  <SelectTrigger className="rounded-xl">
                    <SelectValue />
                  </SelectTrigger>
                  <SelectContent>
                    {["Karachi", "Lahore", "Rawalpindi", "Islamabad", "Multan"].map((c) => (
                      <SelectItem key={c} value={c}>
                        {c}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
            </div>
          </div>
          <DialogFooter>
            <Button variant="outline" className="rounded-xl" onClick={() => setAddOpen(false)}>
              Cancel
            </Button>
            <Button className="rounded-xl" onClick={addOfficer}>
              Save Officer
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>

      <Dialog open={!!deleteId} onOpenChange={(o) => !o && setDeleteId(null)}>
        <DialogContent className="rounded-2xl sm:max-w-md">
          <DialogHeader>
            <DialogTitle>Delete officer?</DialogTitle>
            <DialogDescription>
              This removes the officer and unassigns their customers. This cannot be undone.
            </DialogDescription>
          </DialogHeader>
          <DialogFooter>
            <Button variant="outline" className="rounded-xl" onClick={() => setDeleteId(null)}>
              Cancel
            </Button>
            <Button
              variant="destructive"
              className="rounded-xl"
              onClick={() => {
                setList((prev) => prev.filter((o) => o.id !== deleteId));
                setDeleteId(null);
                toast.success("Officer deleted");
              }}
            >
              Delete
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </AppShell>
  );
}
