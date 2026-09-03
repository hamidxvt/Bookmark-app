import { createFileRoute, Link, useNavigate } from "@tanstack/react-router";
import {
  ArrowLeft,
  Building2,
  CalendarClock,
  Check,
  ImagePlus,
  MapPin,
  Phone,
  Play,
  Save,
  UserRound,
} from "lucide-react";
import { useMemo, useState } from "react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { GoogleMapView, type MapMarker } from "@/components/app/GoogleMap";
import { EmptyState, SectionCard, StatusPill } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Textarea } from "@/components/ui/textarea";
import { buildRoute, toLatLng } from "@/lib/geo";
import { customers, initials, officers } from "@/lib/mock-data";
import { advanceVisit, updateVisit, useVisit, type TimelineStage } from "@/lib/store";
import { cn } from "@/lib/utils";

export const Route = createFileRoute("/visits/$id")({
  head: ({ params }) => ({
    meta: [
      { title: `Visit ${params.id} — Bookmark Field Force Manager` },
      { name: "description", content: "Visit timeline, officer tracking, customer location and field report." },
      { property: "og:title", content: `Visit ${params.id} — Bookmark Field Force Manager` },
      { property: "og:description", content: "Timeline, live route and field report for this customer visit." },
    ],
  }),
  component: VisitDetailPage,
});

const stages: TimelineStage[] = ["Created", "Assigned", "Started", "Reached Location", "Completed"];

function VisitDetailPage() {
  const { id } = Route.useParams();
  const visit = useVisit(id);
  const navigate = useNavigate();

  const customer = customers.find((c) => c.id === visit?.customerId) ?? customers[0];
  const officer = officers.find((o) => o.id === visit?.officerId) ?? officers[0];

  const [notes, setNotes] = useState(visit?.notes ?? "");
  const [followUp, setFollowUp] = useState(visit?.followUp ?? "");
  const [productsText, setProductsText] = useState((visit?.productsDiscussed ?? []).join(", "));
  const [samplesQty, setSamplesQty] = useState(String(visit?.samplesDelivered ?? 0));
  const [images, setImages] = useState<string[]>(visit?.images ?? []);

  const markers: MapMarker[] = useMemo(() => {
    const cust = toLatLng(customer.city, customer.pos);
    const off = toLatLng(officer.city, officer.pos);
    return [
      { id: customer.id, name: customer.name, position: cust, kind: "customer", detail: customer.address },
      { id: officer.id, name: `${officer.name} (live)`, position: off, kind: "officer", status: officer.status, detail: officer.location },
    ];
  }, [customer, officer]);

  const route = useMemo(
    () => buildRoute(toLatLng(officer.city, officer.pos), toLatLng(customer.city, customer.pos)),
    [customer, officer],
  );

  if (!visit) {
    return (
      <AppShell title="Visit not found" subtitle="This visit no longer exists">
        <EmptyState title="Visit not found" description="It may have been deleted. Return to the visit register." />
        <div className="mt-4">
          <Button className="rounded-xl" onClick={() => navigate({ to: "/visits" })}>
            Back to Visits
          </Button>
        </div>
      </AppShell>
    );
  }

  const nextStage = stages.find((s) => !visit.timeline.find((t) => t.stage === s)?.at);

  const saveReport = () => {
    updateVisit(visit.id, {
      notes,
      followUp,
      productsDiscussed: productsText.split(",").map((p) => p.trim()).filter(Boolean),
      samplesDelivered: Number(samplesQty) || 0,
      images,
    });
    toast.success("Visit report saved");
  };

  return (
    <AppShell title={`Visit ${visit.id}`} subtitle={`${visit.purpose} · ${visit.date} at ${visit.time}`}>
      <div className="space-y-6">
        <div className="flex flex-wrap items-center gap-3">
          <Button variant="outline" className="rounded-xl" onClick={() => navigate({ to: "/visits" })}>
            <ArrowLeft className="mr-2 h-4 w-4" /> All visits
          </Button>
          <StatusPill value={visit.status} />
          {nextStage ? (
            <Button
              className="rounded-xl"
              onClick={() => {
                advanceVisit(visit.id, nextStage, new Date().toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" }));
                toast.success(`Marked "${nextStage}"`);
              }}
            >
              {nextStage === "Completed" ? <Check className="mr-2 h-4 w-4" /> : <Play className="mr-2 h-4 w-4" />}
              Mark {nextStage}
            </Button>
          ) : null}
        </div>

        <div className="grid gap-6 xl:grid-cols-3">
          <SectionCard title="Customer" description="Account details">
            <div className="space-y-3 text-sm">
              <div className="flex items-center gap-3">
                <span className="flex h-11 w-11 items-center justify-center rounded-xl bg-primary-soft text-primary">
                  <Building2 className="h-5 w-5" />
                </span>
                <div>
                  <p className="font-semibold">{customer.name}</p>
                  <p className="text-xs text-muted-foreground">{customer.category}</p>
                </div>
              </div>
              <p className="flex items-center gap-2 text-muted-foreground"><Phone className="h-4 w-4" /> {customer.phone}</p>
              <p className="flex items-center gap-2 text-muted-foreground"><MapPin className="h-4 w-4" /> {customer.address}</p>
              <p className="text-muted-foreground">{customer.city} · {customer.area}</p>
              <Link to="/customer/$id" params={{ id: customer.id }} className="inline-block text-sm font-medium text-primary hover:underline">
                Open customer profile →
              </Link>
            </div>
          </SectionCard>

          <SectionCard title="Assigned Officer" description="Field ownership">
            <div className="space-y-3 text-sm">
              <div className="flex items-center gap-3">
                <span className="flex h-11 w-11 items-center justify-center rounded-xl bg-navy text-xs font-bold text-navy-foreground">
                  {initials(officer.name)}
                </span>
                <div>
                  <p className="font-semibold">{officer.name}</p>
                  <p className="text-xs text-muted-foreground">{officer.city} · {officer.area}</p>
                </div>
              </div>
              <p className="flex items-center gap-2 text-muted-foreground"><Phone className="h-4 w-4" /> {officer.phone}</p>
              <StatusPill value={officer.status} />
              <p className="text-muted-foreground">{officer.todayVisits} of {officer.targetVisits} visits today</p>
              <Link to="/officer/$id" params={{ id: officer.id }} className="inline-block text-sm font-medium text-primary hover:underline">
                Open officer profile →
              </Link>
            </div>
          </SectionCard>

          <SectionCard title="Visit Timeline" description="Lifecycle checkpoints">
            <ol className="relative space-y-5 pl-6">
              <span className="absolute left-[7px] top-2 h-[calc(100%-16px)] w-px bg-border" />
              {visit.timeline.map((t) => (
                <li key={t.stage} className="relative">
                  <span
                    className={cn(
                      "absolute -left-6 top-1 flex h-4 w-4 items-center justify-center rounded-full border-2",
                      t.at ? "border-primary bg-primary" : "border-border bg-card",
                    )}
                  >
                    {t.at ? <Check className="h-2.5 w-2.5 text-primary-foreground" /> : null}
                  </span>
                  <p className={cn("text-sm font-medium", !t.at && "text-muted-foreground")}>{t.stage}</p>
                  <p className="text-xs text-muted-foreground">{t.at ?? "Pending"}</p>
                </li>
              ))}
            </ol>
          </SectionCard>
        </div>

        <SectionCard title="Location & Route" description="Customer location, officer live position and route (Google Maps)">
          <GoogleMapView className="h-[420px]" markers={markers} route={route} zoom={13} />
        </SectionCard>

        <SectionCard
          title="Visit Report"
          description="Notes, imagery, products and follow-up"
          action={
            <Button className="rounded-xl" onClick={saveReport}>
              <Save className="mr-2 h-4 w-4" /> Save report
            </Button>
          }
        >
          <div className="grid gap-5 lg:grid-cols-2">
            <div className="space-y-2">
              <Label>Notes</Label>
              <Textarea
                value={notes}
                onChange={(e) => setNotes(e.target.value)}
                placeholder="Meeting summary, objections, next steps…"
                className="min-h-32 rounded-xl"
              />
            </div>
            <div className="space-y-4">
              <div className="space-y-2">
                <Label>Products discussed (comma separated)</Label>
                <Input value={productsText} onChange={(e) => setProductsText(e.target.value)} className="h-11 rounded-xl" />
              </div>
              <div className="grid gap-4 sm:grid-cols-2">
                <div className="space-y-2">
                  <Label>Samples delivered</Label>
                  <Input type="number" min={0} value={samplesQty} onChange={(e) => setSamplesQty(e.target.value)} className="h-11 rounded-xl" />
                </div>
                <div className="space-y-2">
                  <Label>Follow-up date</Label>
                  <Input type="date" value={followUp} onChange={(e) => setFollowUp(e.target.value)} className="h-11 rounded-xl" />
                </div>
              </div>
            </div>
            <div className="space-y-2 lg:col-span-2">
              <Label>Visit images</Label>
              <div className="flex flex-wrap items-center gap-3">
                {images.map((src, i) => (
                  <img key={i} src={src} alt={`Visit evidence ${i + 1}`} className="h-24 w-24 rounded-xl object-cover shadow-card" />
                ))}
                <label className="flex h-24 w-24 cursor-pointer flex-col items-center justify-center gap-1 rounded-xl border border-dashed border-border text-xs text-muted-foreground transition-colors hover:border-primary hover:text-primary">
                  <ImagePlus className="h-5 w-5" />
                  Upload
                  <input
                    type="file"
                    accept="image/*"
                    multiple
                    className="hidden"
                    onChange={(e) => {
                      const files = Array.from(e.target.files ?? []);
                      if (files.length === 0) return;
                      setImages((prev) => [...prev, ...files.map((f) => URL.createObjectURL(f))]);
                      toast.success(`${files.length} image(s) attached`);
                    }}
                  />
                </label>
              </div>
            </div>
            <p className="flex items-center gap-2 text-xs text-muted-foreground lg:col-span-2">
              <CalendarClock className="h-3.5 w-3.5" /> Duration logged: {visit.duration}
            </p>
          </div>
        </SectionCard>
      </div>
    </AppShell>
  );
}
