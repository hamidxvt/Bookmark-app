import { createFileRoute, Link } from "@tanstack/react-router";
import { Clock, MapPin, PlayCircle, Smartphone, Square, Target } from "lucide-react";
import { useEffect, useState } from "react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { GoogleMapView, type MapMarker } from "@/components/app/GoogleMap";
import { SectionCard, StatCard, StatusPill } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Progress } from "@/components/ui/progress";
import { toLatLng } from "@/lib/geo";
import { officers } from "@/lib/mock-data";
import { endShift, pushNotification, startShift, useShift, useVisits } from "@/lib/store";

export const Route = createFileRoute("/shift")({
  head: () => ({
    meta: [
      { title: "My Shift — Bookmark Field Force Manager" },
      { name: "description", content: "Start and end your field shift with GPS capture, live timer and visit progress." },
      { property: "og:title", content: "My Shift — Bookmark Field Force Manager" },
      { property: "og:description", content: "Start shift, capture GPS and track visit progress in real time." },
    ],
  }),
  component: ShiftPage,
});

const me = officers[0];

const fmt = (ms: number) => {
  const s = Math.floor(ms / 1000);
  return [Math.floor(s / 3600), Math.floor((s % 3600) / 60), s % 60]
    .map((n) => String(n).padStart(2, "0"))
    .join(":");
};

function ShiftPage() {
  const shift = useShift();
  const visits = useVisits();
  const [now, setNow] = useState(Date.now());
  const [coords, setCoords] = useState<string | null>(null);
  const [locating, setLocating] = useState(false);

  useEffect(() => {
    const t = setInterval(() => setNow(Date.now()), 1000);
    return () => clearInterval(t);
  }, []);

  const myVisits = visits.filter((v) => v.officerId === me.id);
  const done = myVisits.filter((v) => v.status === "completed").length || shift.visitsDone;
  const planned = Math.max(myVisits.length, shift.visitsPlanned);
  const position = toLatLng(me.city, me.pos);
  const markers: MapMarker[] = [
    { id: me.id, name: `${me.name} (you)`, position, kind: "officer", status: shift.active ? "active" : "offline", detail: me.location },
  ];

  const captureLocation = () =>
    new Promise<string>((resolve) => {
      setLocating(true);
      if (typeof navigator === "undefined" || !navigator.geolocation) {
        setLocating(false);
        resolve(me.location);
        return;
      }
      navigator.geolocation.getCurrentPosition(
        (p) => {
          setLocating(false);
          const label = `${p.coords.latitude.toFixed(5)}, ${p.coords.longitude.toFixed(5)}`;
          setCoords(label);
          resolve(label);
        },
        () => {
          setLocating(false);
          resolve(me.location);
        },
        { timeout: 6000 },
      );
    });

  const onStart = async () => {
    const location = await captureLocation();
    const device = typeof navigator !== "undefined" ? navigator.userAgent.split(")")[0].replace("Mozilla/5.0 (", "") : "Unknown device";
    startShift(location, device);
    pushNotification({ type: "attendance", title: "Shift started", body: `${me.name} started a shift at ${location}.`, to: "/live-shifts" });
    toast.success("Shift started", { description: `GPS locked · ${location}` });
  };

  const onEnd = async () => {
    const location = await captureLocation();
    const hours = endShift(location);
    pushNotification({ type: "attendance", title: "Shift ended", body: `${me.name} ended shift after ${hours} h.`, to: "/attendance" });
    toast.success("Shift ended", { description: `${hours} hours logged` });
  };

  return (
    <AppShell title="My Shift" subtitle={`${me.name} · ${me.city} field officer`}>
      <div className="space-y-6">
        <section className="surface flex flex-wrap items-center justify-between gap-6 p-6">
          <div>
            <div className="flex items-center gap-3">
              <h2 className="text-xl font-bold">{shift.active ? "Shift in progress" : "You are offline"}</h2>
              <StatusPill value={shift.active ? "active" : "offline"} />
            </div>
            <p className="mt-1 text-sm text-muted-foreground">
              {shift.active
                ? `Started ${new Date(shift.startedAt ?? Date.now()).toLocaleTimeString()} · ${shift.startLocation}`
                : "Start your shift to begin GPS tracking and receive visit assignments."}
            </p>
            {shift.active ? (
              <p className="mt-4 font-mono text-4xl font-bold tracking-tight text-primary">
                {fmt(now - (shift.startedAt ?? now))}
              </p>
            ) : shift.totalHours ? (
              <p className="mt-4 text-sm text-muted-foreground">
                Last shift: {shift.totalHours} h · ended at {shift.endLocation}
              </p>
            ) : null}
          </div>
          {shift.active ? (
            <Button size="lg" variant="outline" className="h-16 rounded-2xl px-10 text-base font-bold" onClick={onEnd} disabled={locating}>
              <Square className="mr-2 h-5 w-5" /> END SHIFT
            </Button>
          ) : (
            <Button size="lg" className="h-16 rounded-2xl px-12 text-base font-bold shadow-brand" onClick={onStart} disabled={locating}>
              <PlayCircle className="mr-2 h-5 w-5" /> {locating ? "Capturing GPS…" : "START SHIFT"}
            </Button>
          )}
        </section>

        <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-4">
          <StatCard label="Working Timer" value={shift.active ? fmt(now - (shift.startedAt ?? now)) : "00:00:00"} icon={<Clock className="h-5 w-5" />} onClick={() => {}} />
          <StatCard label="Completed Visits" value={String(done)} icon={<Target className="h-5 w-5" />} to="/visits" />
          <StatCard label="Remaining Visits" value={String(Math.max(0, planned - done))} icon={<Target className="h-5 w-5" />} to="/visits" />
          <StatCard label="Device" value={shift.device ? "Registered" : "Not captured"} icon={<Smartphone className="h-5 w-5" />} onClick={() => toast.info(shift.device ?? "Start a shift to register the device")} />
        </div>

        <div className="grid gap-6 xl:grid-cols-[1.6fr_1fr]">
          <SectionCard title="Current Location" description="Live GPS position (Google Maps)">
            <GoogleMapView className="h-[380px]" markers={markers} center={position} zoom={14} />
            <p className="mt-3 flex items-center gap-2 text-xs text-muted-foreground">
              <MapPin className="h-3.5 w-3.5" /> {coords ?? shift.startLocation ?? me.location}
            </p>
          </SectionCard>

          <SectionCard title="Today's Plan" description={`${done} of ${planned} visits completed`}>
            <Progress value={planned ? (done / planned) * 100 : 0} className="h-2" />
            <ul className="mt-5 space-y-3">
              {myVisits.slice(0, 6).map((v) => (
                <li key={v.id} className="flex items-center justify-between gap-3 rounded-xl border border-border/70 p-3">
                  <div className="min-w-0">
                    <p className="truncate text-sm font-medium">{v.customer}</p>
                    <p className="text-xs text-muted-foreground">{v.time} · {v.purpose}</p>
                  </div>
                  <Link to="/visits/$id" params={{ id: v.id }} className="text-xs font-medium text-primary hover:underline">
                    Open
                  </Link>
                </li>
              ))}
            </ul>
          </SectionCard>
        </div>
      </div>
    </AppShell>
  );
}
