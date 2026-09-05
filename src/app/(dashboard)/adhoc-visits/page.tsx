"use client";

import { useEffect, useState } from "react";
import { MapPin, Search, RefreshCw, Sparkles, CheckCircle2, Clock } from "lucide-react";
import { EmptyState, SectionCard, StatCard, StatusPill, TableSkeleton } from "@/components/shared/ui-bits";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { cn } from "@/lib/utils";

interface AdHocVisit {
  id: number;
  visitDate: string;
  status: string;
  notes: string | null;
  checkInAt: string | null;
  checkInLat: number | null;
  checkInLng: number | null;
  booker: { id: number; name: string; email: string };
  customer: {
    id: number; name: string; ownerPhone?: string; address: string | null;
    city: { name: string } | null;
  } | null;
}

export default function AdHocVisitsPage() {
  const [visits, setVisits] = useState<AdHocVisit[]>([]);
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState("");
  const [filter, setFilter] = useState("all");

  async function load() {
    setLoading(true);
    try {
      const params = new URLSearchParams();
      if (filter !== "all") params.set("filter", filter);
      const res = await fetch(`/api/v1/adhoc-visits?${params}`).then((r) => r.json());
      setVisits(res.data ?? []);
    } catch (err) { console.error(err); }
    finally { setLoading(false); }
  }

  useEffect(() => { load(); }, [filter]);

  const filtered = visits.filter((v) =>
    !search ||
    v.booker?.name?.toLowerCase().includes(search.toLowerCase()) ||
    v.customer?.name?.toLowerCase().includes(search.toLowerCase())
  );

  const completed = filtered.filter((v) => v.status === "COMPLETED").length;
  const inProgress = filtered.filter((v) => v.status === "IN_PROGRESS").length;

  return (
    <div className="space-y-6 px-6 py-6 lg:px-8">
      <div>
        <h1 className="text-2xl font-bold tracking-tight text-foreground">Ad-hoc Visits</h1>
        <p className="mt-0.5 text-sm text-muted-foreground">Unplanned visits logged by officers in the field</p>
      </div>

      <div className="grid gap-4 sm:grid-cols-3">
        <StatCard label="Total Ad-hoc" value={filtered.length} icon={<Sparkles className="h-5 w-5" />} />
        <StatCard label="Completed" value={completed} icon={<CheckCircle2 className="h-5 w-5" />} />
        <StatCard label="In Progress" value={inProgress} icon={<Clock className="h-5 w-5" />} />
      </div>

      <SectionCard
        title="Ad-hoc Visit Log"
        description={`${filtered.length} visits`}
        action={
          <Button variant="outline" className="rounded-xl" onClick={load} disabled={loading}>
            <RefreshCw className={cn("mr-2 h-4 w-4", loading && "animate-spin")} /> Refresh
          </Button>
        }
      >
        <div className="mb-5 flex flex-wrap items-center gap-3">
          <div className="relative max-w-sm flex-1 min-w-56">
            <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
            <Input
              value={search}
              onChange={(e) => setSearch(e.target.value)}
              placeholder="Search officer or customer…"
              className="h-11 rounded-xl pl-9"
            />
          </div>
          <div className="flex overflow-hidden rounded-xl border border-border text-xs font-medium">
            {["all", "today"].map((opt) => (
              <button
                key={opt}
                onClick={() => setFilter(opt)}
                className={cn(
                  "px-3 py-2 transition-colors",
                  filter === opt ? "bg-primary text-primary-foreground" : "bg-card text-muted-foreground hover:bg-muted/60",
                )}
              >
                {opt === "all" ? "All" : "Today"}
              </button>
            ))}
          </div>
        </div>

        {loading ? (
          <TableSkeleton />
        ) : filtered.length === 0 ? (
          <EmptyState title="No ad-hoc visits found" description="Officers create these from the mobile app during field visits." />
        ) : (
          <div className="overflow-x-auto">
            <Table>
              <TableHeader>
                <TableRow>
                  {["#", "Customer", "Officer", "City", "Date", "Status", "GPS"].map((h) => (
                    <TableHead key={h}>{h}</TableHead>
                  ))}
                </TableRow>
              </TableHeader>
              <TableBody>
                {filtered.map((v, i) => (
                  <TableRow key={v.id} className="hover:bg-muted/50">
                    <TableCell className="text-muted-foreground">{i + 1}</TableCell>
                    <TableCell>
                      <p className="font-medium text-foreground">{v.customer?.name ?? "Unknown"}</p>
                      {v.customer?.ownerPhone && <p className="text-xs text-muted-foreground">{v.customer.ownerPhone}</p>}
                    </TableCell>
                    <TableCell className="text-muted-foreground">{v.booker?.name ?? "—"}</TableCell>
                    <TableCell className="text-muted-foreground">{v.customer?.city?.name ?? "—"}</TableCell>
                    <TableCell className="whitespace-nowrap text-muted-foreground">
                      {new Date(v.visitDate).toLocaleDateString("en-PK", { day: "2-digit", month: "short", year: "numeric" })}
                    </TableCell>
                    <TableCell><StatusPill value={v.status} /></TableCell>
                    <TableCell>
                      {v.checkInLat && v.checkInLng ? (
                        <a
                          href={`https://maps.google.com/?q=${v.checkInLat},${v.checkInLng}`}
                          target="_blank" rel="noreferrer"
                          className="flex items-center gap-1 text-xs font-medium text-primary hover:underline"
                        >
                          <MapPin className="h-3 w-3" /> Map
                        </a>
                      ) : <span className="text-xs text-muted-foreground">—</span>}
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </div>
        )}
      </SectionCard>
    </div>
  );
}
