"use client";

import { useEffect, useState } from "react";
import Link from "next/link";
import {
  ArrowLeft, Mail, Phone, MapPin, Route as RouteIcon,
  CheckCircle2, Users, CalendarCheck,
} from "lucide-react";
import { Button } from "@/components/ui/button";
import { Progress } from "@/components/ui/progress";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { EmptyState, SectionCard, StatCard, StatusPill } from "@/components/shared/ui-bits";
import { formatDate, formatDateTime } from "@/lib/utils";

interface Booker {
  id: number; name: string; email: string; phone: string;
  designation?: string | null;
  jobStatus: string; adminApproved: string;
  visitTargets: number | null;
  profilePhoto?: string | null;
  createdAt: string;
  city: { id: number; name: string } | null;
  lastLatitude?: number | string | null;
  lastLongitude?: number | string | null;
  lastSeenAt?: string | null;
  gpsStatus?: string;
}

interface Visit {
  id: number; visitDate: string; status: string;
  checkInAt: string | null; checkOutAt: string | null;
  customer: { id: number; name: string; customerType: string } | null;
}

interface AttendanceRow {
  id: number; date: string; startAt: string | null; endAt: string | null; status: string;
}

interface AssignedCustomer {
  id: number; name: string; customerType: string;
  city: { id: number; name: string } | null;
}

function initials(name: string) {
  return (name || "B").split(" ").map((w) => w[0]).join("").toUpperCase().slice(0, 2);
}

function hoursBetween(start: string | null, end: string | null) {
  if (!start || !end) return "—";
  const ms = new Date(end).getTime() - new Date(start).getTime();
  if (ms <= 0) return "—";
  return `${(ms / 3_600_000).toFixed(1)}h`;
}

export default function BookerDetailClient({ bookerId }: { bookerId: string }) {
  const [booker, setBooker] = useState<Booker | null>(null);
  const [visits, setVisits] = useState<Visit[]>([]);
  const [visitsTotal, setVisitsTotal] = useState(0);
  const [completedTotal, setCompletedTotal] = useState(0);
  const [assignedCustomers, setAssignedCustomers] = useState<AssignedCustomer[]>([]);
  const [attendance, setAttendance] = useState<AttendanceRow[]>([]);
  const [loading, setLoading] = useState(true);
  const [notFound, setNotFound] = useState(false);

  useEffect(() => {
    (async () => {
      try {
        const dateTo = new Date();
        const dateFrom = new Date();
        dateFrom.setDate(dateFrom.getDate() - 13);

        const [bRes, vRes, vTotalRes, vCompletedRes, cRes, aRes] = await Promise.all([
          fetch(`/api/v1/bookers/${bookerId}`).then(r => r.json()),
          fetch(`/api/v1/visits?bookerId=${bookerId}&length=100`).then(r => r.json()),
          fetch(`/api/v1/visits?bookerId=${bookerId}&length=1`).then(r => r.json()),
          fetch(`/api/v1/visits?bookerId=${bookerId}&status=COMPLETED&length=1`).then(r => r.json()),
          fetch(`/api/v1/customers?assignedBookerId=${bookerId}&length=200`).then(r => r.json()),
          fetch(`/api/v1/attendance?bookerId=${bookerId}&dateFrom=${dateFrom.toISOString()}&dateTo=${dateTo.toISOString()}`).then(r => r.json()),
        ]);

        if (!bRes.success) { setNotFound(true); return; }
        setBooker(bRes.data);
        setVisits(vRes.data?.data ?? []);
        setVisitsTotal(vTotalRes.data?.recordsTotal ?? 0);
        setCompletedTotal(vCompletedRes.data?.recordsTotal ?? 0);
        setAssignedCustomers(cRes.data?.data ?? []);
        setAttendance(aRes.data ?? []);
      } catch (e) {
        console.error(e);
        setNotFound(true);
      } finally {
        setLoading(false);
      }
    })();
  }, [bookerId]);

  if (loading) {
    return (
      <div className="flex min-h-[60vh] items-center justify-center">
        <div className="flex flex-col items-center gap-3">
          <div className="h-8 w-8 animate-spin rounded-full border-2 border-primary border-t-transparent" />
          <p className="text-sm font-medium text-muted-foreground">Loading officer…</p>
        </div>
      </div>
    );
  }

  if (notFound || !booker) {
    return (
      <div className="space-y-6 px-6 py-6 lg:px-8">
        <EmptyState title="We couldn't find that officer" description="They may have been removed from the team." />
        <Button className="rounded-xl" asChild>
          <Link href="/bookers">Back to Sales Team</Link>
        </Button>
      </div>
    );
  }

  const presentDays = attendance.filter(a => a.status === "present").length;
  const attendanceRate = attendance.length > 0 ? Math.round((presentDays / attendance.length) * 100) : 0;
  const todayVisits = visits.filter(v => new Date(v.visitDate).toDateString() === new Date().toDateString());
  const target = booker.visitTargets ?? 0;

  return (
    <div className="space-y-6 px-6 py-6 lg:px-8">
      <Button variant="ghost" className="rounded-xl" asChild>
        <Link href="/bookers"><ArrowLeft className="mr-2 h-4 w-4" /> Back to Sales Team</Link>
      </Button>

      <div className="surface flex flex-wrap items-center gap-6 p-6">
        <span className="flex h-20 w-20 shrink-0 items-center justify-center overflow-hidden rounded-2xl bg-navy text-2xl font-bold text-navy-foreground">
          {booker.profilePhoto
            ? <img src={booker.profilePhoto} alt={booker.name} className="h-full w-full object-cover" />
            : initials(booker.name)}
        </span>
        <div className="min-w-64 flex-1">
          <div className="flex flex-wrap items-center gap-3">
            <h2 className="text-xl font-bold text-foreground">{booker.name}</h2>
            <StatusPill value={booker.jobStatus === "ACTIVE" ? "active" : "offline"} />
            <StatusPill value={booker.adminApproved} />
          </div>
          <p className="mt-1 text-sm text-muted-foreground">{booker.designation ?? "Field Officer"}</p>
          <div className="mt-3 flex flex-wrap gap-5 text-sm text-muted-foreground">
            <span className="flex items-center gap-1.5"><Mail className="h-4 w-4" /> {booker.email}</span>
            <span className="flex items-center gap-1.5"><Phone className="h-4 w-4" /> {booker.phone}</span>
            <span className="flex items-center gap-1.5"><MapPin className="h-4 w-4" /> {booker.city?.name ?? "—"}</span>
          </div>
        </div>
        <Button className="rounded-xl" asChild>
          <Link href="/live-activity">Track Location</Link>
        </Button>
      </div>

      <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-4">
        <StatCard label="Total Visits" value={visitsTotal} icon={<RouteIcon className="h-5 w-5" />} />
        <StatCard label="Completed Visits" value={completedTotal} icon={<CheckCircle2 className="h-5 w-5" />} />
        <StatCard label="Customers" value={assignedCustomers.length} icon={<Users className="h-5 w-5" />} />
        <StatCard label="Attendance (14d)" value={`${attendanceRate}%`} icon={<CalendarCheck className="h-5 w-5" />} />
      </div>

      <Tabs defaultValue="overview">
        <TabsList className="h-11 rounded-xl bg-muted p-1">
          <TabsTrigger value="overview" className="rounded-lg">Overview</TabsTrigger>
          <TabsTrigger value="visits" className="rounded-lg">Visits</TabsTrigger>
          <TabsTrigger value="attendance" className="rounded-lg">Attendance</TabsTrigger>
          <TabsTrigger value="location" className="rounded-lg">Location History</TabsTrigger>
        </TabsList>

        <TabsContent value="overview" className="mt-5 grid gap-6 xl:grid-cols-2">
          <SectionCard
            title="Today's Progress"
            description={`${todayVisits.length} of ${target || "—"} planned visits`}
          >
            <Progress value={target > 0 ? (todayVisits.length / target) * 100 : 0} className="h-2" />
            <dl className="mt-5 grid grid-cols-2 gap-4 text-sm">
              {[
                ["City", booker.city?.name ?? "—"],
                ["Joined", formatDate(booker.createdAt)],
                ["Designation", booker.designation ?? "—"],
                ["Job Status", booker.jobStatus === "ACTIVE" ? "Active" : "Not Active"],
              ].map(([k, v]) => (
                <div key={k} className="rounded-xl bg-muted/60 p-3">
                  <dt className="text-xs text-muted-foreground">{k}</dt>
                  <dd className="font-medium text-foreground">{v}</dd>
                </div>
              ))}
            </dl>
          </SectionCard>

          <SectionCard title="Assigned Customers" description={`${assignedCustomers.length} account(s) in portfolio`}>
            {assignedCustomers.length === 0 ? (
              <EmptyState title="No customers assigned yet" />
            ) : (
              <ul className="space-y-3">
                {assignedCustomers.slice(0, 6).map((c) => (
                  <li key={c.id} className="flex items-center justify-between rounded-xl border border-border/70 p-3">
                    <span className="text-sm font-medium text-foreground">{c.name}</span>
                    <span className="text-xs text-muted-foreground">{c.customerType} · {c.city?.name ?? "—"}</span>
                  </li>
                ))}
              </ul>
            )}
          </SectionCard>
        </TabsContent>

        <TabsContent value="visits" className="mt-5">
          <SectionCard title="Visit History">
            {visits.length === 0 ? (
              <EmptyState title="No visits recorded" />
            ) : (
              <div className="overflow-x-auto">
                <Table>
                  <TableHeader>
                    <TableRow>
                      <TableHead>Customer</TableHead>
                      <TableHead>Date</TableHead>
                      <TableHead>Check-in</TableHead>
                      <TableHead>Status</TableHead>
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    {visits.map((v) => (
                      <TableRow key={v.id}>
                        <TableCell className="font-medium">{v.customer?.name ?? "—"}</TableCell>
                        <TableCell className="text-muted-foreground">{formatDate(v.visitDate)}</TableCell>
                        <TableCell className="text-muted-foreground">{v.checkInAt ? formatDateTime(v.checkInAt) : "—"}</TableCell>
                        <TableCell><StatusPill value={v.status} /></TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </div>
            )}
          </SectionCard>
        </TabsContent>

        <TabsContent value="attendance" className="mt-5">
          <SectionCard title="Attendance Log" description="Last 14 days">
            {attendance.length === 0 ? (
              <EmptyState title="No attendance records" />
            ) : (
              <div className="overflow-x-auto">
                <Table>
                  <TableHeader>
                    <TableRow>
                      <TableHead>Date</TableHead>
                      <TableHead>Check-in</TableHead>
                      <TableHead>Check-out</TableHead>
                      <TableHead>Hours</TableHead>
                      <TableHead>Status</TableHead>
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    {attendance.map((a) => (
                      <TableRow key={a.id}>
                        <TableCell className="font-medium">{formatDate(a.date)}</TableCell>
                        <TableCell>{a.startAt ? formatDateTime(a.startAt) : "—"}</TableCell>
                        <TableCell>{a.endAt ? formatDateTime(a.endAt) : "—"}</TableCell>
                        <TableCell>{hoursBetween(a.startAt, a.endAt)}</TableCell>
                        <TableCell><StatusPill value={a.status} /></TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </div>
            )}
          </SectionCard>
        </TabsContent>

        <TabsContent value="location" className="mt-5">
          <SectionCard
            title="Last Known Location"
            description={booker.lastSeenAt ? `Last seen ${formatDateTime(booker.lastSeenAt)}` : "No GPS data yet"}
          >
            {booker.lastLatitude && booker.lastLongitude ? (
              <div className="overflow-hidden rounded-xl border border-border/70">
                <iframe
                  title="Officer location"
                  className="h-96 w-full"
                  src={`https://www.openstreetmap.org/export/embed.html?bbox=${Number(booker.lastLongitude) - 0.01},${Number(booker.lastLatitude) - 0.01},${Number(booker.lastLongitude) + 0.01},${Number(booker.lastLatitude) + 0.01}&layer=mapnik&marker=${booker.lastLatitude},${booker.lastLongitude}`}
                />
              </div>
            ) : (
              <EmptyState title="No location data" description="This officer hasn't reported a GPS position yet." />
            )}
          </SectionCard>
        </TabsContent>
      </Tabs>
    </div>
  );
}
