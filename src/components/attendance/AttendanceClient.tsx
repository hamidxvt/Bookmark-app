"use client";

import { useEffect, useState } from "react";
import { RefreshCw, Clock, MapPin, CheckCircle2, XCircle, AlertCircle, Calendar, UserCheck, UserX } from "lucide-react";
import { format } from "date-fns";
import { SectionCard, StatCard, EmptyState, TableSkeleton } from "@/components/shared/ui-bits";
import { Input } from "@/components/ui/input";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { cn } from "@/lib/utils";

interface AttendanceRecord {
  id: number;
  date: string;
  startAt: string | null;
  endAt: string | null;
  startLat: number | null;
  startLng: number | null;
  status: string;
  cannotReason: string | null;
  booker: { id: number; name: string; email: string; phone: string };
}

function duration(start: string, end: string | null) {
  if (!end) return "Active";
  const ms = new Date(end).getTime() - new Date(start).getTime();
  const h = Math.floor(ms / 3600000);
  const m = Math.floor((ms % 3600000) / 60000);
  return `${h}h ${m}m`;
}

const STATUS_META: Record<string, { color: string; icon: typeof CheckCircle2; label: string }> = {
  present:     { color: "bg-success/15 text-success",            icon: CheckCircle2, label: "Present" },
  absent:      { color: "bg-destructive/15 text-destructive",    icon: XCircle,      label: "Absent" },
  cannot_work: { color: "bg-warning/20 text-warning-foreground", icon: AlertCircle,  label: "Cannot Work" },
};

const TODAY = format(new Date(), "yyyy-MM-dd");

export default function AttendanceClient() {
  const [records,  setRecords]  = useState<AttendanceRecord[]>([]);
  const [dateFrom, setDateFrom] = useState(TODAY);
  const [dateTo,   setDateTo]   = useState(TODAY);
  const [loading,  setLoading]  = useState(true);
  const [search,   setSearch]   = useState("");
  const [mode,     setMode]     = useState<"single" | "range">("single");

  async function load() {
    setLoading(true);
    try {
      const params = mode === "range"
        ? `?dateFrom=${dateFrom}&dateTo=${dateTo}`
        : `?date=${dateFrom}`;
      const res = await fetch(`/api/v1/attendance${params}`).then(r => r.json());
      if (res.success) setRecords(res.data);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => { load(); }, [dateFrom, dateTo, mode]);

  const filtered = records.filter(r =>
    !search || r.booker.name.toLowerCase().includes(search.toLowerCase())
  );

  const presentCount    = filtered.filter(r => r.status === "present").length;
  const absentCount     = filtered.filter(r => r.status === "absent").length;
  const cannotWorkCount = filtered.filter(r => r.status === "cannot_work").length;

  return (
    <div className="space-y-6 px-6 py-6 lg:px-8">
      {/* KPI row */}
      <div className="grid gap-4 sm:grid-cols-3">
        <StatCard label="Present" value={presentCount} icon={<UserCheck className="h-5 w-5" />} />
        <StatCard label="Absent" value={absentCount} icon={<UserX className="h-5 w-5" />} />
        <StatCard label="Cannot Work" value={cannotWorkCount} icon={<AlertCircle className="h-5 w-5" />} />
      </div>

      <SectionCard
        title={
          mode === "range"
            ? `${format(new Date(dateFrom + "T00:00:00"), "MMM d")} – ${format(new Date(dateTo + "T00:00:00"), "MMM d, yyyy")}`
            : format(new Date(dateFrom + "T00:00:00"), "EEEE, MMMM d, yyyy")
        }
        description={`${filtered.length} records`}
        action={
          <div className="flex flex-wrap items-center gap-2">
            <div className="flex rounded-xl bg-muted p-1">
              <button
                onClick={() => setMode("single")}
                className={cn(
                  "rounded-lg px-3 py-1.5 text-xs font-semibold transition-all duration-200",
                  mode === "single" ? "bg-card text-foreground shadow-card" : "text-muted-foreground hover:text-foreground",
                )}
              >
                Single Day
              </button>
              <button
                onClick={() => setMode("range")}
                className={cn(
                  "rounded-lg px-3 py-1.5 text-xs font-semibold transition-all duration-200",
                  mode === "range" ? "bg-card text-foreground shadow-card" : "text-muted-foreground hover:text-foreground",
                )}
              >
                Date Range
              </button>
            </div>

            <div className="flex items-center gap-1.5">
              <Calendar className="h-4 w-4 text-muted-foreground" />
              <input
                type="date"
                value={dateFrom}
                onChange={e => setDateFrom(e.target.value)}
                className="h-10 rounded-xl border border-border bg-card px-2.5 text-sm text-foreground shadow-card outline-none focus:border-primary/30"
              />
            </div>

            {mode === "range" && (
              <>
                <span className="text-sm text-muted-foreground">to</span>
                <input
                  type="date"
                  value={dateTo}
                  onChange={e => setDateTo(e.target.value)}
                  min={dateFrom}
                  className="h-10 rounded-xl border border-border bg-card px-2.5 text-sm text-foreground shadow-card outline-none focus:border-primary/30"
                />
              </>
            )}

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
        <div className="relative mb-5 max-w-sm">
          <Input
            value={search}
            onChange={e => setSearch(e.target.value)}
            placeholder="Search officer name…"
            className="h-11 rounded-xl"
          />
        </div>

        {loading ? (
          <TableSkeleton />
        ) : filtered.length === 0 ? (
          <EmptyState title="No attendance records" description="No attendance records for this period." />
        ) : (
          <div className="overflow-x-auto">
            <Table>
              <TableHeader>
                <TableRow>
                  {["Officer", "Status", "Check In", "Check Out", "Duration", "Location"].map(h => (
                    <TableHead key={h}>{h}</TableHead>
                  ))}
                </TableRow>
              </TableHeader>
              <TableBody>
                {filtered.map(r => {
                  const meta = STATUS_META[r.status] ?? STATUS_META.absent;
                  const Icon = meta.icon;
                  return (
                    <TableRow key={r.id} className="transition-colors hover:bg-muted/50">
                      <TableCell className="font-medium">
                        <div className="flex items-center gap-3">
                          <span className="flex h-9 w-9 shrink-0 items-center justify-center rounded-full bg-navy text-xs font-bold text-navy-foreground">
                            {r.booker.name[0]}
                          </span>
                          <div className="min-w-0">
                            <p className="truncate text-foreground">{r.booker.name}</p>
                            {mode === "range" && <p className="text-xs text-muted-foreground">{format(new Date(r.date), "MMM d")}</p>}
                          </div>
                        </div>
                      </TableCell>
                      <TableCell>
                        <span className={cn("inline-flex items-center gap-1.5 rounded-full px-2.5 py-1 text-xs font-semibold", meta.color)}>
                          <Icon className="h-3 w-3" /> {meta.label}
                        </span>
                        {r.cannotReason && <p className="mt-1 text-xs text-warning-foreground">{r.cannotReason}</p>}
                      </TableCell>
                      <TableCell>{r.startAt ? format(new Date(r.startAt), "h:mm a") : "—"}</TableCell>
                      <TableCell>{r.endAt ? format(new Date(r.endAt), "h:mm a") : "—"}</TableCell>
                      <TableCell className="text-muted-foreground">{r.startAt ? duration(r.startAt, r.endAt) : "—"}</TableCell>
                      <TableCell>
                        {r.startLat ? (
                          <a
                            href={`https://maps.google.com/?q=${r.startLat},${r.startLng}`}
                            target="_blank"
                            rel="noreferrer"
                            className="inline-flex items-center gap-1 text-primary hover:underline"
                          >
                            <MapPin className="h-3.5 w-3.5" /> View
                          </a>
                        ) : (
                          <span className="text-muted-foreground">—</span>
                        )}
                      </TableCell>
                    </TableRow>
                  );
                })}
              </TableBody>
            </Table>
          </div>
        )}
      </SectionCard>
    </div>
  );
}
