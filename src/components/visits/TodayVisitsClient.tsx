"use client";

import { useEffect, useState } from "react";
import Link from "next/link";
import { CalendarClock, CheckCircle2, Clock, Eye } from "lucide-react";
import { EmptyState, SectionCard, StatCard, StatusPill, TableSkeleton } from "@/components/shared/ui-bits";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";

interface Visit {
  id: number;
  visitDate: string;
  status: string;
  priority: string | null;
  checkInAt: string | null;
  booker: { id: number; name: string };
  customer: { id: number; name: string };
}

const PRIORITY_STYLE: Record<string, string> = {
  High: "border-destructive/30 bg-destructive/10 text-destructive",
  Medium: "border-warning/30 bg-warning/15 text-warning-foreground",
  Low: "border-border bg-muted text-muted-foreground",
};

export default function TodayVisitsClient() {
  const [rows, setRows] = useState<Visit[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    (async () => {
      try {
        const res = await fetch("/api/v1/visits?today=1&length=200").then((r) => r.json());
        if (res.success) setRows(res.data?.data ?? []);
      } catch (e) { console.error(e); }
      finally { setLoading(false); }
    })();
  }, []);

  const today = new Date().toLocaleDateString("en-PK", {
    weekday: "long", year: "numeric", month: "long", day: "numeric",
  });
  const completed = rows.filter((v) => v.status === "COMPLETED").length;
  const pending = rows.filter((v) => v.status === "PENDING").length;

  return (
    <div className="space-y-6 px-6 py-6 lg:px-8">
      <div>
        <h1 className="text-xl font-bold text-foreground">Today&apos;s Visits</h1>
        <p className="mt-0.5 text-sm text-muted-foreground">{today}</p>
      </div>

      <div className="grid gap-4 sm:grid-cols-3">
        <StatCard label="Total Scheduled" value={rows.length} icon={<CalendarClock className="h-5 w-5" />} />
        <StatCard label="Completed" value={completed} icon={<CheckCircle2 className="h-5 w-5" />} />
        <StatCard label="Pending" value={pending} icon={<Clock className="h-5 w-5" />} />
      </div>

      <SectionCard title="Scheduled for Today" description={`${rows.length} visits`}>
        {loading ? (
          <TableSkeleton />
        ) : rows.length === 0 ? (
          <EmptyState title="No visits scheduled today" description="Nothing on the register for today yet." />
        ) : (
          <div className="overflow-x-auto">
            <Table>
              <TableHeader>
                <TableRow>
                  {["#", "Customer", "Officer", "Check-in", "Priority", "Status", ""].map((h) => (
                    <TableHead key={h}>{h}</TableHead>
                  ))}
                </TableRow>
              </TableHeader>
              <TableBody>
                {rows.map((v, i) => (
                  <TableRow key={v.id} className="hover:bg-muted/50">
                    <TableCell className="text-muted-foreground">{i + 1}</TableCell>
                    <TableCell className="font-medium text-foreground">{v.customer?.name ?? "—"}</TableCell>
                    <TableCell className="text-muted-foreground">{v.booker?.name ?? "—"}</TableCell>
                    <TableCell className="text-muted-foreground">
                      {v.checkInAt ? new Date(v.checkInAt).toLocaleTimeString("en-PK", { hour: "2-digit", minute: "2-digit" }) : "—"}
                    </TableCell>
                    <TableCell>
                      {v.priority ? (
                        <Badge variant="outline" className={PRIORITY_STYLE[v.priority] ?? PRIORITY_STYLE.Low}>{v.priority}</Badge>
                      ) : "—"}
                    </TableCell>
                    <TableCell><StatusPill value={v.status} /></TableCell>
                    <TableCell className="text-right">
                      <Link href={`/visits/${v.id}`}>
                        <Button variant="ghost" size="icon" className="h-9 w-9 rounded-xl"><Eye className="h-4 w-4" /></Button>
                      </Link>
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
