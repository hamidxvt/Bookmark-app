import { createFileRoute } from "@tanstack/react-router";
import { CalendarCheck, Clock, Download, MapPin, PlaneTakeoff, Search, Timer, UserCheck, UserX } from "lucide-react";
import { useMemo, useState } from "react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { EmptyState, SectionCard, StatCard, StatusPill, TableSkeleton } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { exportCsv, setAttendanceStatus, useAttendance, useShift } from "@/lib/store";

export const Route = createFileRoute("/attendance")({
  head: () => ({
    meta: [
      { title: "Attendance & Shifts — Bookmark Field Force Manager" },
      { name: "description", content: "Daily check-in, check-out, working hours, leave and active shifts for every officer." },
      { property: "og:title", content: "Attendance & Shifts — Bookmark Field Force Manager" },
      { property: "og:description", content: "Attendance, working hours and live shift status for field officers." },
    ],
  }),
  component: AttendancePage,
});

const cities = ["Karachi", "Lahore", "Rawalpindi", "Islamabad", "Multan"];

function AttendancePage() {
  const rows = useAttendance();
  const shift = useShift();
  const [query, setQuery] = useState("");
  const [city, setCity] = useState("all");
  const [status, setStatus] = useState("all");
  const [date, setDate] = useState("");
  const [loading, setLoading] = useState(false);

  const filtered = useMemo(
    () =>
      rows.filter((r) => {
        const q = query.trim().toLowerCase();
        if (q && !r.officer.toLowerCase().includes(q)) return false;
        if (city !== "all" && r.city !== city) return false;
        if (status !== "all" && r.status !== status) return false;
        if (date && r.date !== date) return false;
        return true;
      }),
    [rows, query, city, status, date],
  );

  const count = (s: string) => rows.filter((r) => r.status === s).length;
  const activeShifts = rows.filter((r) => r.status !== "absent" && r.status !== "leave").length + (shift.active ? 1 : 0);
  const refresh = () => {
    setLoading(true);
    setTimeout(() => setLoading(false), 500);
  };

  return (
    <AppShell title="Attendance" subtitle="Check-ins, working hours and shift coverage" onRefresh={refresh}>
      <div className="space-y-6">
        <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-5">
          <StatCard label="Present Today" value={String(count("present"))} icon={<UserCheck className="h-5 w-5" />} onClick={() => setStatus("present")} />
          <StatCard label="Absent" value={String(count("absent"))} icon={<UserX className="h-5 w-5" />} onClick={() => setStatus("absent")} />
          <StatCard label="Late" value={String(count("late"))} icon={<Clock className="h-5 w-5" />} onClick={() => setStatus("late")} />
          <StatCard label="On Leave" value={String(count("leave"))} icon={<PlaneTakeoff className="h-5 w-5" />} onClick={() => setStatus("leave")} />
          <StatCard label="Active Shifts" value={String(activeShifts)} icon={<Timer className="h-5 w-5" />} to="/live-shifts" />
        </div>

        <SectionCard
          title="Attendance Register"
          description={`${filtered.length} of ${rows.length} records`}
          action={
            <div className="flex gap-2">
              <Button
                variant="outline"
                className="rounded-xl"
                onClick={() => {
                  exportCsv("attendance.csv", filtered);
                  toast.success("Attendance exported as CSV");
                }}
              >
                <Download className="mr-2 h-4 w-4" /> Export
              </Button>
              <Button className="rounded-xl" onClick={() => toast.success("Attendance sync triggered", { description: "Device check-ins pulled for today." })}>
                <CalendarCheck className="mr-2 h-4 w-4" /> Sync check-ins
              </Button>
            </div>
          }
        >
          <div className="mb-5 grid gap-3 md:grid-cols-2 xl:grid-cols-4">
            <div className="relative">
              <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
              <Input value={query} onChange={(e) => setQuery(e.target.value)} placeholder="Search officer" className="h-11 rounded-xl pl-9" />
            </div>
            <Select value={city} onValueChange={setCity}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="City" /></SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All cities</SelectItem>
                {cities.map((c) => <SelectItem key={c} value={c}>{c}</SelectItem>)}
              </SelectContent>
            </Select>
            <Select value={status} onValueChange={setStatus}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Status" /></SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All statuses</SelectItem>
                <SelectItem value="present">Present</SelectItem>
                <SelectItem value="late">Late</SelectItem>
                <SelectItem value="absent">Absent</SelectItem>
                <SelectItem value="leave">On leave</SelectItem>
              </SelectContent>
            </Select>
            <Input type="date" value={date} onChange={(e) => setDate(e.target.value)} className="h-11 rounded-xl" />
          </div>

          {loading ? (
            <TableSkeleton />
          ) : filtered.length === 0 ? (
            <EmptyState title="No attendance records" description="Try a different city, status or date." />
          ) : (
            <div className="overflow-x-auto">
              <Table>
                <TableHeader>
                  <TableRow>
                    {["Officer", "Date", "Check In", "Check Out", "Working Hours", "Location", "Status", ""].map((h) => (
                      <TableHead key={h}>{h}</TableHead>
                    ))}
                  </TableRow>
                </TableHeader>
                <TableBody>
                  {filtered.map((r) => (
                    <TableRow key={r.id} className="transition-colors hover:bg-muted/50">
                      <TableCell className="font-medium">{r.officer}</TableCell>
                      <TableCell>{r.date}</TableCell>
                      <TableCell>{r.checkIn}</TableCell>
                      <TableCell>{r.checkOut}</TableCell>
                      <TableCell>{r.hours}</TableCell>
                      <TableCell className="max-w-[220px] truncate text-muted-foreground">
                        <span className="inline-flex items-center gap-1.5"><MapPin className="h-3.5 w-3.5" /> {r.location}</span>
                      </TableCell>
                      <TableCell><StatusPill value={r.status === "leave" ? "pending" : r.status} /></TableCell>
                      <TableCell className="text-right">
                        <DropdownMenu>
                          <DropdownMenuTrigger asChild>
                            <Button variant="ghost" className="rounded-xl text-xs">Update</Button>
                          </DropdownMenuTrigger>
                          <DropdownMenuContent align="end" className="rounded-xl">
                            {(["present", "late", "absent", "leave"] as const).map((s) => (
                              <DropdownMenuItem
                                key={s}
                                onClick={() => {
                                  setAttendanceStatus(r.id, s);
                                  toast.success(`${r.officer} marked ${s}`);
                                }}
                              >
                                Mark {s}
                              </DropdownMenuItem>
                            ))}
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
    </AppShell>
  );
}
