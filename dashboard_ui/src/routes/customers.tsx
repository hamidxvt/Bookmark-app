import { createFileRoute, Link, useNavigate } from "@tanstack/react-router";
import { BookOpen, Eye, GraduationCap, MoreHorizontal, Pencil, Plus, Search, Store, Trash2, Users } from "lucide-react";
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
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { customers as seed } from "@/lib/mock-data";

export const Route = createFileRoute("/customers")({
  head: () => ({
    meta: [
      { title: "Customers — Bookmark Field Force Manager" },
      { name: "description", content: "Manage schools, bookshops, retailers and individual Bookmark customers." },
      { property: "og:title", content: "Customers — Bookmark Field Force Manager" },
      { property: "og:description", content: "Manage schools, bookshops and retail customers." },
    ],
  }),
  component: CustomersPage,
});

function CustomersPage() {
  const navigate = useNavigate();
  const [list, setList] = useState(seed);
  const [query, setQuery] = useState("");
  const [type, setType] = useState("all");
  const [status, setStatus] = useState("all");
  const [loading, setLoading] = useState(false);
  const [deleteId, setDeleteId] = useState<string | null>(null);

  const filtered = useMemo(
    () =>
      list.filter((c) => {
        const q = query.trim().toLowerCase();
        const matchQ = !q || [c.name, c.city, c.category].some((f) => f.toLowerCase().includes(q));
        return matchQ && (type === "all" || c.type === type) && (status === "all" || c.status === status);
      }),
    [list, query, type, status],
  );

  const refresh = () => {
    setLoading(true);
    setTimeout(() => setLoading(false), 600);
  };

  return (
    <AppShell title="Customers" subtitle="Schools, bookshops, retailers and individuals" onRefresh={refresh}>
      <div className="space-y-6">
        <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-4">
          <StatCard label="Total Customers" value="7,352" trend="+184 this month" icon={<Users className="h-5 w-5" />} onClick={refresh} />
          <StatCard label="Schools" value="2,914" icon={<GraduationCap className="h-5 w-5" />} onClick={() => setType("School")} />
          <StatCard label="Bookshops" value="3,102" icon={<BookOpen className="h-5 w-5" />} onClick={() => setType("Shop")} />
          <StatCard label="Retailers" value="1,336" icon={<Store className="h-5 w-5" />} onClick={() => setType("Shop")} />
        </div>

        <SectionCard
          title="Customer Directory"
          description={`${filtered.length} of ${list.length} records`}
          action={
            <Button className="rounded-xl" onClick={() => navigate({ to: "/add-customer" })}>
              <Plus className="mr-2 h-4 w-4" /> Add Customer
            </Button>
          }
        >
          <div className="mb-5 flex flex-wrap gap-3">
            <div className="relative min-w-64 flex-1">
              <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
              <Input
                value={query}
                onChange={(e) => setQuery(e.target.value)}
                placeholder="Search name, city or category"
                className="h-11 rounded-xl pl-9"
              />
            </div>
            <Select value={type} onValueChange={setType}>
              <SelectTrigger className="h-11 w-40 rounded-xl">
                <SelectValue placeholder="Type" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All types</SelectItem>
                <SelectItem value="School">School</SelectItem>
                <SelectItem value="Shop">Shop</SelectItem>
                <SelectItem value="Individual">Individual</SelectItem>
              </SelectContent>
            </Select>
            <Select value={status} onValueChange={setStatus}>
              <SelectTrigger className="h-11 w-40 rounded-xl">
                <SelectValue placeholder="Status" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All statuses</SelectItem>
                <SelectItem value="active">Active</SelectItem>
                <SelectItem value="pending">Pending</SelectItem>
                <SelectItem value="inactive">Inactive</SelectItem>
              </SelectContent>
            </Select>
          </div>

          {loading ? (
            <TableSkeleton />
          ) : filtered.length === 0 ? (
            <EmptyState title="No customers match your filters" description="Adjust the search or clear the filters." />
          ) : (
            <div className="overflow-x-auto">
              <Table>
                <TableHeader>
                  <TableRow>
                    <TableHead>Customer</TableHead>
                    <TableHead>Category</TableHead>
                    <TableHead>City</TableHead>
                    <TableHead>Status</TableHead>
                    <TableHead>Joined</TableHead>
                    <TableHead className="text-right">Actions</TableHead>
                  </TableRow>
                </TableHeader>
                <TableBody>
                  {filtered.map((c) => (
                    <TableRow key={c.id} className="transition-colors hover:bg-muted/50">
                      <TableCell>
                        <Link to="/customer/$id" params={{ id: c.id }} className="font-medium hover:text-primary hover:underline">
                          {c.name}
                        </Link>
                        <p className="text-xs text-muted-foreground">{c.id} · {c.officer}</p>
                      </TableCell>
                      <TableCell>{c.category}</TableCell>
                      <TableCell>{c.city}</TableCell>
                      <TableCell>
                        <StatusPill value={c.status} />
                      </TableCell>
                      <TableCell className="text-sm text-muted-foreground">{c.joined}</TableCell>
                      <TableCell className="text-right">
                        <DropdownMenu>
                          <DropdownMenuTrigger asChild>
                            <Button variant="ghost" size="icon" className="h-9 w-9 rounded-lg">
                              <MoreHorizontal className="h-4 w-4" />
                            </Button>
                          </DropdownMenuTrigger>
                          <DropdownMenuContent align="end" className="rounded-xl">
                            <DropdownMenuItem onClick={() => navigate({ to: "/customer/$id", params: { id: c.id } })}>
                              <Eye className="mr-2 h-4 w-4" /> View
                            </DropdownMenuItem>
                            <DropdownMenuItem onClick={() => toast.info(`Editing ${c.name}`)}>
                              <Pencil className="mr-2 h-4 w-4" /> Edit
                            </DropdownMenuItem>
                            <DropdownMenuItem className="text-destructive focus:text-destructive" onClick={() => setDeleteId(c.id)}>
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

      <Dialog open={!!deleteId} onOpenChange={(o) => !o && setDeleteId(null)}>
        <DialogContent className="rounded-2xl sm:max-w-md">
          <DialogHeader>
            <DialogTitle>Delete customer?</DialogTitle>
            <DialogDescription>Visit history for this customer will be archived.</DialogDescription>
          </DialogHeader>
          <DialogFooter>
            <Button variant="outline" className="rounded-xl" onClick={() => setDeleteId(null)}>
              Cancel
            </Button>
            <Button
              variant="destructive"
              className="rounded-xl"
              onClick={() => {
                setList((prev) => prev.filter((c) => c.id !== deleteId));
                setDeleteId(null);
                toast.success("Customer deleted");
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
