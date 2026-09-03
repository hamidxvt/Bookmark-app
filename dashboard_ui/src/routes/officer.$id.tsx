import { createFileRoute, Link, useNavigate } from "@tanstack/react-router";
import {
  ArrowLeft,
  CalendarCheck,
  CheckCircle2,
  Mail,
  MapPin,
  Phone,
  Route as RouteIcon,
  Users,
} from "lucide-react";
import { Bar, BarChart, CartesianGrid, ResponsiveContainer, Tooltip, XAxis, YAxis } from "recharts";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { GoogleMapView } from "@/components/app/GoogleMap";
import { toLatLng } from "@/lib/geo";

import { EmptyState, SectionCard, StatCard, StatusPill } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Progress } from "@/components/ui/progress";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { attendance, customers, initials, officers, visits } from "@/lib/mock-data";

export const Route = createFileRoute("/officer/$id")({
  head: () => ({
    meta: [
      { title: "Officer Profile — Bookmark Field Force Manager" },
      { name: "description", content: "Officer performance, visits, attendance and location history." },
      { property: "og:title", content: "Officer Profile — Bookmark Field Force Manager" },
      { property: "og:description", content: "Officer performance, visits and attendance detail." },
    ],
  }),
  component: OfficerPage,
});

function OfficerPage() {
  const { id } = Route.useParams();
  const navigate = useNavigate();
  const officer = officers.find((o) => o.id === id);

  if (!officer) {
    return (
      <AppShell title="Officer not found">
        <EmptyState title="We couldn't find that officer" description="They may have been removed from the team." />
        <div className="mt-4">
          <Button className="rounded-xl" onClick={() => navigate({ to: "/sales-team" })}>
            Back to Sales Team
          </Button>
        </div>
      </AppShell>
    );
  }

  const officerVisits = visits.filter((v) => v.officer === officer.name);
  const officerCustomers = customers.filter((c) => c.officer === officer.name);
  const att = attendance.filter((a) => a.officer === officer.name);
  const perf = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat"].map((d, i) => ({
    day: d,
    visits: 3 + ((i * 5 + officer.todayVisits) % 8),
  }));

  return (
    <AppShell title={officer.name} subtitle={`${officer.id} · ${officer.city} field officer`}>
      <div className="space-y-6">
        <Button variant="ghost" className="rounded-xl" onClick={() => navigate({ to: "/sales-team" })}>
          <ArrowLeft className="mr-2 h-4 w-4" /> Back to Sales Team
        </Button>

        <div className="surface flex flex-wrap items-center gap-6 p-6">
          <span className="flex h-20 w-20 items-center justify-center rounded-2xl bg-navy text-2xl font-bold text-navy-foreground">
            {initials(officer.name)}
          </span>
          <div className="min-w-64 flex-1">
            <div className="flex flex-wrap items-center gap-3">
              <h2 className="text-xl font-bold">{officer.name}</h2>
              <StatusPill value={officer.status} />
              <StatusPill value={officer.approved} />
            </div>
            <div className="mt-3 flex flex-wrap gap-5 text-sm text-muted-foreground">
              <span className="flex items-center gap-1.5">
                <Mail className="h-4 w-4" /> {officer.email}
              </span>
              <span className="flex items-center gap-1.5">
                <Phone className="h-4 w-4" /> {officer.phone}
              </span>
              <span className="flex items-center gap-1.5">
                <MapPin className="h-4 w-4" /> {officer.location}
              </span>
            </div>
          </div>
          <div className="flex gap-2">
            <Button variant="outline" className="rounded-xl" onClick={() => toast.info("Edit form opened")}>
              Edit Officer
            </Button>
            <Button asChild className="rounded-xl">
              <Link to="/live-location">Track Location</Link>
            </Button>
          </div>
        </div>

        <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-4">
          <StatCard label="Total Visits" value={String(officer.totalVisits)} icon={<RouteIcon className="h-5 w-5" />} onClick={() => {}} />
          <StatCard label="Completed Visits" value={String(officer.completedVisits)} icon={<CheckCircle2 className="h-5 w-5" />} onClick={() => {}} />
          <StatCard label="Customers" value={String(officer.customers)} icon={<Users className="h-5 w-5" />} onClick={() => {}} />
          <StatCard label="Attendance" value={`${officer.attendanceRate}%`} icon={<CalendarCheck className="h-5 w-5" />} onClick={() => {}} />
        </div>

        <Tabs defaultValue="overview">
          <TabsList className="h-11 rounded-xl bg-muted p-1">
            {["overview", "visits", "attendance", "location", "performance"].map((t) => (
              <TabsTrigger key={t} value={t} className="rounded-lg capitalize">
                {t === "location" ? "Location History" : t}
              </TabsTrigger>
            ))}
          </TabsList>

          <TabsContent value="overview" className="mt-5 grid gap-6 xl:grid-cols-2">
            <SectionCard title="Today's Progress" description={`${officer.todayVisits} of ${officer.targetVisits} planned visits`}>
              <Progress value={(officer.todayVisits / officer.targetVisits) * 100} className="h-2" />
              <dl className="mt-5 grid grid-cols-2 gap-4 text-sm">
                {[
                  ["Area", officer.area],
                  ["Joined", officer.joined],
                  ["Last seen", officer.lastSeen],
                  ["GPS", officer.gps ? "Enabled" : "Disabled"],
                ].map(([k, v]) => (
                  <div key={k} className="rounded-xl bg-muted/60 p-3">
                    <dt className="text-xs text-muted-foreground">{k}</dt>
                    <dd className="font-medium">{v}</dd>
                  </div>
                ))}
              </dl>
            </SectionCard>
            <SectionCard title="Assigned Customers" description={`${officerCustomers.length} accounts in portfolio`}>
              {officerCustomers.length === 0 ? (
                <EmptyState title="No customers assigned yet" />
              ) : (
                <ul className="space-y-3">
                  {officerCustomers.map((c) => (
                    <li key={c.id} className="flex items-center justify-between rounded-xl border border-border/70 p-3">
                      <Link to="/customer/$id" params={{ id: c.id }} className="text-sm font-medium hover:text-primary">
                        {c.name}
                      </Link>
                      <span className="text-xs text-muted-foreground">
                        {c.category} · {c.city}
                      </span>
                    </li>
                  ))}
                </ul>
              )}
            </SectionCard>
          </TabsContent>

          <TabsContent value="visits" className="mt-5">
            <SectionCard title="Visit History">
              {officerVisits.length === 0 ? (
                <EmptyState title="No visits recorded" />
              ) : (
                <Table>
                  <TableHeader>
                    <TableRow>
                      <TableHead>Visit</TableHead>
                      <TableHead>Customer</TableHead>
                      <TableHead>Date</TableHead>
                      <TableHead>Purpose</TableHead>
                      <TableHead>Status</TableHead>
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    {officerVisits.map((v) => (
                      <TableRow key={v.id}>
                        <TableCell className="font-medium">{v.id}</TableCell>
                        <TableCell>{v.customer}</TableCell>
                        <TableCell className="text-muted-foreground">
                          {v.date} · {v.time}
                        </TableCell>
                        <TableCell>{v.purpose}</TableCell>
                        <TableCell>
                          <StatusPill value={v.status} />
                        </TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              )}
            </SectionCard>
          </TabsContent>

          <TabsContent value="attendance" className="mt-5">
            <SectionCard title="Attendance Log">
              <Table>
                <TableHeader>
                  <TableRow>
                    <TableHead>Officer</TableHead>
                    <TableHead>Check-in</TableHead>
                    <TableHead>Check-out</TableHead>
                    <TableHead>Hours</TableHead>
                    <TableHead>Status</TableHead>
                  </TableRow>
                </TableHeader>
                <TableBody>
                  {att.map((a) => (
                    <TableRow key={a.id}>
                      <TableCell className="font-medium">{a.officer}</TableCell>
                      <TableCell>{a.checkIn}</TableCell>
                      <TableCell>{a.checkOut}</TableCell>
                      <TableCell>{a.hours}</TableCell>
                      <TableCell>
                        <StatusPill value={a.status} />
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </SectionCard>
          </TabsContent>

          <TabsContent value="location" className="mt-5">
            <SectionCard title="Location History" description="Route replay for today (Google Maps)">
              <GoogleMapView
                className="h-[420px]"
                center={toLatLng(officer.city, officer.pos)}
                zoom={13}
                route={[
                  toLatLng(officer.city, officer.pos),
                  ...officerCustomers.slice(0, 4).map((c) => toLatLng(c.city, c.pos)),
                ]}
                markers={[
                  {
                    id: officer.id,
                    name: officer.name,
                    position: toLatLng(officer.city, officer.pos),
                    kind: "officer",
                    status: officer.status,
                    detail: officer.location,
                  },
                  ...officerCustomers.map((c) => ({
                    id: c.id,
                    name: c.name,
                    position: toLatLng(c.city, c.pos),
                    kind: "customer" as const,
                    detail: c.address,
                  })),
                ]}
              />
            </SectionCard>
          </TabsContent>


          <TabsContent value="performance" className="mt-5">
            <SectionCard title="Weekly Performance" description="Visits completed per day">
              <div className="h-[320px]">
                <ResponsiveContainer width="100%" height="100%">
                  <BarChart data={perf} margin={{ left: -20 }}>
                    <CartesianGrid strokeDasharray="4 4" vertical={false} stroke="var(--color-border)" />
                    <XAxis dataKey="day" tickLine={false} axisLine={false} fontSize={12} />
                    <YAxis tickLine={false} axisLine={false} fontSize={12} />
                    <Tooltip
                      contentStyle={{
                        borderRadius: 14,
                        border: "1px solid var(--color-border)",
                        background: "var(--color-card)",
                        fontSize: 12,
                      }}
                    />
                    <Bar dataKey="visits" fill="var(--color-chart-1)" radius={[8, 8, 0, 0]} />
                  </BarChart>
                </ResponsiveContainer>
              </div>
            </SectionCard>
          </TabsContent>
        </Tabs>
      </div>
    </AppShell>
  );
}
