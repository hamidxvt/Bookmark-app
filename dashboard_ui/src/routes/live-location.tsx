import { createFileRoute } from "@tanstack/react-router";
import { Clock, MapPinned, Navigation, Satellite, Users } from "lucide-react";
import { useEffect, useState } from "react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { GoogleMapView, type MapMarker } from "@/components/app/GoogleMap";
import { toLatLng } from "@/lib/geo";

import { SectionCard, StatCard, StatusPill } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { customers, initials, officers, recentActivity } from "@/lib/mock-data";

export const Route = createFileRoute("/live-location")({
  head: () => ({
    meta: [
      { title: "Live Location — Bookmark Field Force Manager" },
      { name: "description", content: "GPS tracking dashboard with officer markers, customer locations and routes." },
      { property: "og:title", content: "Live Location — Bookmark Field Force Manager" },
      { property: "og:description", content: "GPS tracking with live officer movement and routes." },
    ],
  }),
  component: LiveLocationPage,
});

function LiveLocationPage() {
  const [tick, setTick] = useState(0);
  const [updated, setUpdated] = useState("just now");
  const [selected, setSelected] = useState<string | null>(null);

  useEffect(() => {
    const t = setInterval(() => {
      setTick((v) => v + 1);
      setUpdated("just now");
    }, 5000);
    return () => clearInterval(t);
  }, []);

  const points: MapMarker[] = [
    ...officers.map((o, i) => {
      const base = toLatLng(o.city, o.pos);
      const drift = o.status === "active" ? 0.0025 : 0;
      return {
        id: o.id,
        name: `${o.name} · ${o.city}`,
        position: {
          lat: base.lat + Math.sin(tick / 2 + i) * drift,
          lng: base.lng + Math.cos(tick / 2 + i) * drift,
        },
        kind: "officer" as const,
        status: o.status,
        detail: o.location,
      };
    }),
    ...customers.slice(0, 14).map((c) => ({
      id: c.id,
      name: c.name,
      position: toLatLng(c.city, c.pos),
      kind: "customer" as const,
      detail: c.address,
    })),
  ];


  const selectedOfficer = officers.find((o) => o.id === selected);

  return (
    <AppShell
      title="Live Location"
      subtitle="Real-time GPS tracking across all territories"
      onRefresh={() => setUpdated("just now")}
    >
      <div className="space-y-6">
        <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-4">
          <StatCard label="Officers Online" value={String(officers.filter((o) => o.status !== "offline").length)} icon={<Users className="h-5 w-5" />} onClick={() => {}} />
          <StatCard label="Active GPS" value={String(officers.filter((o) => o.gps).length)} icon={<Satellite className="h-5 w-5" />} onClick={() => {}} />
          <StatCard label="On Map" value={String(points.length)} icon={<MapPinned className="h-5 w-5" />} onClick={() => {}} />
          <StatCard label="Last Updated" value={updated} icon={<Clock className="h-5 w-5" />} onClick={() => setUpdated("just now")} />
        </div>

        <div className="grid gap-6 xl:grid-cols-[1.7fr_1fr]">
          <SectionCard
            title="Field Map"
            description="Officer markers, customer locations and live routes"
            action={
              <Button
                variant="outline"
                className="rounded-xl"
                onClick={() => toast.success("Route snapshot saved to reports")}
              >
                <Navigation className="mr-2 h-4 w-4" /> Save snapshot
              </Button>
            }
          >
            <GoogleMapView className="h-[520px]" markers={points} onSelect={setSelected} zoom={6} />
            {selectedOfficer ? (
              <div className="mt-4 flex flex-wrap items-center gap-4 rounded-2xl bg-muted/60 p-4">
                <span className="flex h-10 w-10 items-center justify-center rounded-xl bg-navy text-xs font-bold text-navy-foreground">
                  {initials(selectedOfficer.name)}
                </span>
                <div className="flex-1">
                  <p className="text-sm font-semibold">{selectedOfficer.name}</p>
                  <p className="text-xs text-muted-foreground">{selectedOfficer.location}</p>
                </div>
                <StatusPill value={selectedOfficer.status} />
                <span className="text-xs text-muted-foreground">
                  {selectedOfficer.todayVisits} visits today
                </span>
              </div>
            ) : (
              <p className="mt-4 text-xs text-muted-foreground">
                Tip: click a marker to inspect that officer's live status.
              </p>
            )}
          </SectionCard>

          <SectionCard title="Activity Feed" description="Streaming field events">
            <ul className="space-y-4">
              {recentActivity.concat(recentActivity.slice(0, 3)).map((a, i) => (
                <li key={i} className="flex gap-3 rounded-xl border border-border/70 p-3">
                  <span className="mt-1 h-2 w-2 shrink-0 rounded-full bg-primary" />
                  <div>
                    <p className="text-sm font-medium">{a.title}</p>
                    <p className="text-xs text-muted-foreground">{a.detail}</p>
                    <p className="mt-0.5 text-[11px] text-muted-foreground/80">
                      {a.by} · {a.time}
                    </p>
                  </div>
                </li>
              ))}
            </ul>
          </SectionCard>
        </div>
      </div>
    </AppShell>
  );
}
