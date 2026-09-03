import { createFileRoute, useNavigate } from "@tanstack/react-router";
import { Activity, MapPin, Search, Timer, Users, WifiOff } from "lucide-react";
import { useMemo, useState } from "react";

import { AppShell } from "@/components/app/AppShell";
import { SectionCard, StatCard, StatusPill } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Progress } from "@/components/ui/progress";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { initials, officers } from "@/lib/mock-data";
import { useShift } from "@/lib/store";

export const Route = createFileRoute("/live-shifts")({
  head: () => ({
    meta: [
      { title: "Live Shifts — Bookmark Field Force Manager" },
      { name: "description", content: "Monitor every officer's live shift status, start time, visits and location." },
      { property: "og:title", content: "Live Shifts — Bookmark Field Force Manager" },
      { property: "og:description", content: "Live shift monitoring across the entire field force." },
    ],
  }),
  component: LiveShiftsPage,
});

const cities = ["Karachi", "Lahore", "Rawalpindi", "Islamabad", "Multan"];

function LiveShiftsPage() {
  const shift = useShift();
  const navigate = useNavigate();
  const [query, setQuery] = useState("");
  const [city, setCity] = useState("all");
  const [status, setStatus] = useState("all");

  const list = useMemo(
    () =>
      officers.filter((o) => {
        const q = query.trim().toLowerCase();
        if (q && !`${o.name} ${o.city}`.toLowerCase().includes(q)) return false;
        if (city !== "all" && o.city !== city) return false;
        if (status !== "all" && o.status !== status) return false;
        return true;
      }),
    [query, city, status],
  );

  const onShift = officers.filter((o) => o.status !== "offline").length + (shift.active ? 0 : 0);

  return (
    <AppShell title="Live Shifts" subtitle="Shift monitoring across every territory">
      <div className="space-y-6">
        <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-4">
          <StatCard label="Total Officers" value={String(officers.length)} icon={<Users className="h-5 w-5" />} to="/sales-team" />
          <StatCard label="Shifts Active" value={String(onShift)} icon={<Activity className="h-5 w-5" />} onClick={() => setStatus("active")} />
          <StatCard label="Idle" value={String(officers.filter((o) => o.status === "idle").length)} icon={<Timer className="h-5 w-5" />} onClick={() => setStatus("idle")} />
          <StatCard label="Offline" value={String(officers.filter((o) => o.status === "offline").length)} icon={<WifiOff className="h-5 w-5" />} onClick={() => setStatus("offline")} />
        </div>

        <SectionCard title="Officer Shifts" description={`${list.length} officers`}>
          <div className="mb-5 grid gap-3 md:grid-cols-3">
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
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Shift status" /></SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All statuses</SelectItem>
                <SelectItem value="active">Active</SelectItem>
                <SelectItem value="idle">Idle</SelectItem>
                <SelectItem value="offline">Offline</SelectItem>
              </SelectContent>
            </Select>
          </div>

          <div className="grid gap-4 md:grid-cols-2 xl:grid-cols-3">
            {list.map((o, i) => (
              <button
                key={o.id}
                onClick={() => navigate({ to: "/officer/$id", params: { id: o.id } })}
                className="surface group p-5 text-left transition-all duration-300 hover:-translate-y-1 hover:shadow-elevated"
              >
                <div className="flex items-center gap-3">
                  <span className="flex h-12 w-12 items-center justify-center rounded-xl bg-navy text-sm font-bold text-navy-foreground">
                    {initials(o.name)}
                  </span>
                  <div className="min-w-0 flex-1">
                    <p className="truncate text-sm font-semibold">{o.name}</p>
                    <p className="text-xs text-muted-foreground">{o.city} · {o.area}</p>
                  </div>
                  <StatusPill value={o.status} />
                </div>
                <dl className="mt-4 grid grid-cols-2 gap-3 text-xs">
                  <div>
                    <dt className="text-muted-foreground">Shift start</dt>
                    <dd className="font-semibold">{o.status === "offline" ? "—" : `0${8 + (i % 2)}:${i % 2 ? "42" : "05"} AM`}</dd>
                  </div>
                  <div>
                    <dt className="text-muted-foreground">Visits done</dt>
                    <dd className="font-semibold">{o.todayVisits} / {o.targetVisits}</dd>
                  </div>
                </dl>
                <Progress value={(o.todayVisits / o.targetVisits) * 100} className="mt-3 h-1.5" />
                <p className="mt-3 flex items-center gap-1.5 text-xs text-muted-foreground">
                  <MapPin className="h-3.5 w-3.5" /> {o.location}
                </p>
                <span className="mt-4 inline-block text-xs font-semibold text-primary opacity-0 transition-opacity group-hover:opacity-100">
                  Open tracking →
                </span>
              </button>
            ))}
          </div>
          <div className="mt-5">
            <Button variant="outline" className="rounded-xl" onClick={() => navigate({ to: "/live-location" })}>
              Open live map
            </Button>
          </div>
        </SectionCard>
      </div>
    </AppShell>
  );
}
