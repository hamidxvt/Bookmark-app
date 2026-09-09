"use client";

import { useEffect, useMemo, useState } from "react";
import { useRouter } from "next/navigation";
import { CalendarPlus, Check, Loader2, Search, Users } from "lucide-react";
import { toast } from "sonner";
import { SectionCard } from "@/components/shared/ui-bits";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Textarea } from "@/components/ui/textarea";
import { Checkbox } from "@/components/ui/checkbox";
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
import { cn } from "@/lib/utils";

interface Booker {
  id: number;
  name: string;
}

interface Customer {
  id: number;
  name: string;
}

function MultiSelectList({
  label,
  items,
  selected,
  onToggle,
  onToggleAll,
  search,
  onSearchChange,
}: {
  label: string;
  items: { id: number; name: string }[];
  selected: Set<number>;
  onToggle: (id: number) => void;
  onToggleAll: () => void;
  search: string;
  onSearchChange: (v: string) => void;
}) {
  const filtered = useMemo(() => {
    const q = search.trim().toLowerCase();
    if (!q) return items;
    return items.filter((i) => i.name.toLowerCase().includes(q));
  }, [items, search]);

  const allFilteredSelected =
    filtered.length > 0 && filtered.every((i) => selected.has(i.id));

  return (
    <div className="space-y-2">
      <div className="flex items-center justify-between">
        <Label>{label}</Label>
        <span className="text-xs text-muted-foreground">{selected.size} selected</span>
      </div>
      <div className="relative">
        <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
        <Input
          value={search}
          onChange={(e) => onSearchChange(e.target.value)}
          placeholder={`Search ${label.toLowerCase()}…`}
          className="h-10 rounded-xl pl-9"
        />
      </div>
      <div className="rounded-xl border border-border bg-muted/30">
        <button
          type="button"
          onClick={onToggleAll}
          className="flex w-full items-center gap-3 border-b border-border px-3 py-2.5 text-left text-xs font-semibold text-muted-foreground hover:bg-muted/50"
        >
          <Checkbox checked={allFilteredSelected} />
          {allFilteredSelected ? "Deselect all" : "Select all visible"}
        </button>
        <div className="max-h-52 overflow-y-auto divide-y divide-border">
          {filtered.length === 0 ? (
            <p className="px-3 py-6 text-center text-xs text-muted-foreground">No matches</p>
          ) : (
            filtered.map((item) => (
              <label
                key={item.id}
                className={cn(
                  "flex cursor-pointer items-center gap-3 px-3 py-2.5 transition-colors hover:bg-muted/50",
                  selected.has(item.id) && "bg-primary-soft/40",
                )}
              >
                <Checkbox
                  checked={selected.has(item.id)}
                  onCheckedChange={() => onToggle(item.id)}
                />
                <span className="text-sm text-foreground">{item.name}</span>
              </label>
            ))
          )}
        </div>
      </div>
    </div>
  );
}

export default function ScheduleVisitPage() {
  const router = useRouter();
  const [bookers, setBookers] = useState<Booker[]>([]);
  const [customers, setCustomers] = useState<Customer[]>([]);
  const [selectedBookers, setSelectedBookers] = useState<Set<number>>(new Set());
  const [selectedCustomers, setSelectedCustomers] = useState<Set<number>>(new Set());
  const [bookerSearch, setBookerSearch] = useState("");
  const [customerSearch, setCustomerSearch] = useState("");
  const [visitDate, setVisitDate] = useState("");
  const [notes, setNotes] = useState("");
  const [loading, setLoading] = useState(false);
  const [confirmOpen, setConfirmOpen] = useState(false);
  const [fetching, setFetching] = useState(true);

  const visitCount = selectedBookers.size * selectedCustomers.size;

  useEffect(() => {
    Promise.all([
      fetch("/api/v1/bookers?length=500").then((r) => r.json()),
      fetch("/api/v1/customers?length=500").then((r) => r.json()),
    ])
      .then(([bookersRes, customersRes]) => {
        if (bookersRes.success) setBookers(bookersRes.data?.data ?? []);
        if (customersRes.success) setCustomers(customersRes.data?.data ?? []);
      })
      .catch(() => toast.error("Failed to load officers or customers"))
      .finally(() => setFetching(false));
  }, []);

  function toggleBooker(id: number) {
    setSelectedBookers((prev) => {
      const next = new Set(prev);
      if (next.has(id)) next.delete(id);
      else next.add(id);
      return next;
    });
  }

  function toggleCustomer(id: number) {
    setSelectedCustomers((prev) => {
      const next = new Set(prev);
      if (next.has(id)) next.delete(id);
      else next.add(id);
      return next;
    });
  }

  function toggleAllBookers() {
    const q = bookerSearch.trim().toLowerCase();
    const visible = q ? bookers.filter((b) => b.name.toLowerCase().includes(q)) : bookers;
    const allSelected = visible.every((b) => selectedBookers.has(b.id));
    setSelectedBookers((prev) => {
      const next = new Set(prev);
      visible.forEach((b) => {
        if (allSelected) next.delete(b.id);
        else next.add(b.id);
      });
      return next;
    });
  }

  function toggleAllCustomers() {
    const q = customerSearch.trim().toLowerCase();
    const visible = q ? customers.filter((c) => c.name.toLowerCase().includes(q)) : customers;
    const allSelected = visible.every((c) => selectedCustomers.has(c.id));
    setSelectedCustomers((prev) => {
      const next = new Set(prev);
      visible.forEach((c) => {
        if (allSelected) next.delete(c.id);
        else next.add(c.id);
      });
      return next;
    });
  }

  function handleSubmit(e: React.FormEvent) {
    e.preventDefault();
    if (!visitDate || selectedBookers.size === 0 || selectedCustomers.size === 0) {
      toast.error("Select at least one officer, one customer, and a visit date");
      return;
    }
    setConfirmOpen(true);
  }

  async function createVisits() {
    setLoading(true);
    const pairs: { bookerId: number; customerId: number }[] = [];
    selectedBookers.forEach((bookerId) => {
      selectedCustomers.forEach((customerId) => {
        pairs.push({ bookerId, customerId });
      });
    });

    let created = 0;
    const errors: string[] = [];

    for (const pair of pairs) {
      try {
        const res = await fetch("/api/v1/visits", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            bookerId: pair.bookerId,
            customerId: pair.customerId,
            visitDate,
            notes: notes.trim() || undefined,
          }),
        });
        const data = await res.json();
        if (data.success) created++;
        else errors.push(data.error ?? "Unknown error");
      } catch {
        errors.push("Network error");
      }
    }

    setLoading(false);
    setConfirmOpen(false);

    if (created > 0) {
      toast.success(`${created} visit${created === 1 ? "" : "s"} scheduled`, {
        description: "Officers have been notified.",
        action: {
          label: "View visits",
          onClick: () => router.push("/visits/today"),
        },
      });
      setSelectedBookers(new Set());
      setSelectedCustomers(new Set());
      setNotes("");
    }
    if (errors.length > 0) {
      toast.error(`${errors.length} visit${errors.length === 1 ? "" : "s"} failed`, {
        description: errors[0],
      });
    }
  }

  const bookerNames = bookers.filter((b) => selectedBookers.has(b.id)).map((b) => b.name);
  const customerNames = customers.filter((c) => selectedCustomers.has(c.id)).map((c) => c.name);

  return (
    <div className="max-w-3xl space-y-6 px-6 py-6 lg:px-8">
      <div className="flex items-center gap-2.5">
        <div className="flex h-8 w-8 items-center justify-center rounded-lg bg-primary-soft">
          <CalendarPlus className="h-4 w-4 text-primary" />
        </div>
        <div>
          <h1 className="text-xl font-bold text-foreground">Schedule Visit</h1>
          <p className="text-xs text-muted-foreground">
            Assign one or more officers to visit selected customers
          </p>
        </div>
      </div>

      <SectionCard
        title="Visit Details"
        description={
          fetching
            ? "Loading…"
            : visitCount > 0
              ? `${visitCount} visit${visitCount === 1 ? "" : "s"} will be created`
              : "Select officers and customers below"
        }
      >
        <form onSubmit={handleSubmit} className="space-y-5">
          <div className="grid grid-cols-1 gap-5 lg:grid-cols-2">
            <MultiSelectList
              label="Officers"
              items={bookers}
              selected={selectedBookers}
              onToggle={toggleBooker}
              onToggleAll={toggleAllBookers}
              search={bookerSearch}
              onSearchChange={setBookerSearch}
            />
            <MultiSelectList
              label="Customers"
              items={customers}
              selected={selectedCustomers}
              onToggle={toggleCustomer}
              onToggleAll={toggleAllCustomers}
              search={customerSearch}
              onSearchChange={setCustomerSearch}
            />
          </div>

          <div className="space-y-2">
            <Label htmlFor="visit-date">Visit Date</Label>
            <Input
              id="visit-date"
              type="date"
              value={visitDate}
              onChange={(e) => setVisitDate(e.target.value)}
              required
              className="h-11 rounded-xl"
            />
          </div>

          <div className="space-y-2">
            <Label htmlFor="notes">Notes (optional)</Label>
            <Textarea
              id="notes"
              value={notes}
              onChange={(e) => setNotes(e.target.value)}
              rows={3}
              placeholder="Instructions or context for these visits…"
              className="rounded-xl resize-none"
            />
          </div>

          {visitCount > 0 && (
            <div className="flex items-center gap-2 rounded-xl border border-primary/20 bg-primary-soft/30 px-4 py-3 text-sm text-foreground">
              <Users className="h-4 w-4 shrink-0 text-primary" />
              <span>
                Bulk create: {selectedBookers.size} officer{selectedBookers.size !== 1 ? "s" : ""} ×{" "}
                {selectedCustomers.size} customer{selectedCustomers.size !== 1 ? "s" : ""} ={" "}
                <strong>{visitCount}</strong> visit{visitCount !== 1 ? "s" : ""}
              </span>
            </div>
          )}

          <Button
            type="submit"
            disabled={loading || fetching || visitCount === 0 || !visitDate}
            className="w-full rounded-xl py-6 text-sm font-semibold"
          >
            {loading ? (
              <>
                <Loader2 className="mr-2 h-4 w-4 animate-spin" /> Creating…
              </>
            ) : (
              <>
                <Check className="mr-2 h-4 w-4" /> Review &amp; Schedule
              </>
            )}
          </Button>
        </form>
      </SectionCard>

      <AlertDialog open={confirmOpen} onOpenChange={setConfirmOpen}>
        <AlertDialogContent className="rounded-2xl">
          <AlertDialogHeader>
            <AlertDialogTitle>Confirm visit creation</AlertDialogTitle>
            <AlertDialogDescription asChild>
              <div className="space-y-3 text-left text-sm text-muted-foreground">
                <p>
                  You are about to schedule <strong className="text-foreground">{visitCount}</strong>{" "}
                  visit{visitCount !== 1 ? "s" : ""} on{" "}
                  <strong className="text-foreground">
                    {visitDate ? new Date(visitDate + "T12:00:00").toLocaleDateString("en-PK", {
                      weekday: "short",
                      day: "numeric",
                      month: "short",
                      year: "numeric",
                    }) : "—"}
                  </strong>
                  .
                </p>
                <div>
                  <p className="font-medium text-foreground">Officers ({bookerNames.length})</p>
                  <p className="line-clamp-2">{bookerNames.join(", ") || "—"}</p>
                </div>
                <div>
                  <p className="font-medium text-foreground">Customers ({customerNames.length})</p>
                  <p className="line-clamp-2">{customerNames.join(", ") || "—"}</p>
                </div>
                {notes.trim() && (
                  <div>
                    <p className="font-medium text-foreground">Notes</p>
                    <p className="line-clamp-3">{notes}</p>
                  </div>
                )}
              </div>
            </AlertDialogDescription>
          </AlertDialogHeader>
          <AlertDialogFooter>
            <AlertDialogCancel disabled={loading} className="rounded-xl">
              Cancel
            </AlertDialogCancel>
            <AlertDialogAction
              disabled={loading}
              className="rounded-xl"
              onClick={(e) => {
                e.preventDefault();
                createVisits();
              }}
            >
              {loading ? "Creating…" : `Create ${visitCount} visit${visitCount !== 1 ? "s" : ""}`}
            </AlertDialogAction>
          </AlertDialogFooter>
        </AlertDialogContent>
      </AlertDialog>
    </div>
  );
}
