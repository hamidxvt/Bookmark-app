"use client";

import { useEffect, useState } from "react";
import Link from "next/link";
import {
  ArrowLeft, Building2, CalendarClock, MapPin, Phone, FileText, Package, Navigation,
} from "lucide-react";
import { SectionCard, StatusPill, EmptyState } from "@/components/shared/ui-bits";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { formatDate, formatDateTime, formatPKR } from "@/lib/utils";

interface VisitDetail {
  id: number;
  visitDate: string;
  checkInAt: string | null;
  checkOutAt: string | null;
  checkInLat: number | null;
  checkInLng: number | null;
  status: string;
  priority: string | null;
  notes: string | null;
  visitReport: string | null;
  isAdhoc: boolean;
  booker: { id: number; name: string; email: string; phone: string; designation: string | null };
  customer: {
    id: number; name: string; customerType: string; ownerName: string | null; ownerPhone: string;
    address: string | null; latitude: number | null; longitude: number | null;
    city: { id: number; name: string } | null;
    area: { id: number; name: string } | null;
  };
  orders: { id: number; orderDate: string; status: string; totalAmount: number }[];
  missedReason: { id: number; reason: string; status: string; adminNote: string | null } | null;
  eta: { eta_minutes: number; distance_km: number | null; updatedAt: string } | null;
}

export default function VisitDetailClient({ id }: { id: string }) {
  const [visit, setVisit] = useState<VisitDetail | null>(null);
  const [loading, setLoading] = useState(true);
  const [notFound, setNotFound] = useState(false);

  useEffect(() => {
    let cancelled = false;
    (async () => {
      try {
        const res = await fetch(`/api/v1/visits/${id}`).then((r) => r.json());
        if (cancelled) return;
        if (res.success) setVisit(res.data);
        else setNotFound(true);
      } catch {
        if (!cancelled) setNotFound(true);
      } finally {
        if (!cancelled) setLoading(false);
      }
    })();
    return () => { cancelled = true; };
  }, [id]);

  if (loading) {
    return (
      <div className="flex min-h-[50vh] items-center justify-center px-6 py-6 lg:px-8">
        <div className="h-8 w-8 animate-spin rounded-full border-2 border-primary border-t-transparent" />
      </div>
    );
  }

  if (notFound || !visit) {
    return (
      <div className="space-y-4 px-6 py-6 lg:px-8">
        <Link href="/visits">
          <Button variant="outline" className="rounded-xl"><ArrowLeft className="mr-2 h-4 w-4" /> All visits</Button>
        </Link>
        <EmptyState title="Visit not found" description="It may have been deleted or the link is incorrect." />
      </div>
    );
  }

  return (
    <div className="space-y-6 px-6 py-6 lg:px-8">
      <div className="flex flex-wrap items-center gap-3">
        <Link href="/visits">
          <Button variant="outline" className="rounded-xl"><ArrowLeft className="mr-2 h-4 w-4" /> All visits</Button>
        </Link>
        <StatusPill value={visit.status} />
        {visit.isAdhoc && <Badge variant="outline" className="rounded-full border-primary/30 bg-primary-soft text-primary">Ad-hoc</Badge>}
        <span className="text-sm text-muted-foreground">Visit #{visit.id} · {formatDate(visit.visitDate)}</span>
      </div>

      <div className="grid gap-6 xl:grid-cols-3">
        <SectionCard title="Customer" description="Account details">
          <div className="space-y-3 text-sm">
            <div className="flex items-center gap-3">
              <span className="flex h-11 w-11 items-center justify-center rounded-xl bg-primary-soft text-primary">
                <Building2 className="h-5 w-5" />
              </span>
              <div>
                <p className="font-semibold text-foreground">{visit.customer.name}</p>
                <p className="text-xs text-muted-foreground">{visit.customer.customerType}</p>
              </div>
            </div>
            <p className="flex items-center gap-2 text-muted-foreground"><Phone className="h-4 w-4" /> {visit.customer.ownerPhone}</p>
            {visit.customer.address && (
              <p className="flex items-center gap-2 text-muted-foreground"><MapPin className="h-4 w-4" /> {visit.customer.address}</p>
            )}
            <p className="text-muted-foreground">{visit.customer.city?.name ?? "—"} {visit.customer.area ? `· ${visit.customer.area.name}` : ""}</p>
            <Link href={`/customers`} className="inline-block text-sm font-medium text-primary hover:underline">
              Open customer list →
            </Link>
          </div>
        </SectionCard>

        <SectionCard title="Assigned Officer" description="Field ownership">
          <div className="space-y-3 text-sm">
            <div className="flex items-center gap-3">
              <span className="flex h-11 w-11 items-center justify-center rounded-xl bg-navy text-xs font-bold text-navy-foreground">
                {visit.booker.name.split(" ").map((w) => w[0]).join("").toUpperCase().slice(0, 2)}
              </span>
              <div>
                <p className="font-semibold text-foreground">{visit.booker.name}</p>
                <p className="text-xs text-muted-foreground">{visit.booker.designation ?? "Field Officer"}</p>
              </div>
            </div>
            <p className="flex items-center gap-2 text-muted-foreground"><Phone className="h-4 w-4" /> {visit.booker.phone}</p>
            <p className="text-muted-foreground">{visit.booker.email}</p>
            <Link href="/bookers" className="inline-block text-sm font-medium text-primary hover:underline">
              Open sales team →
            </Link>
          </div>
        </SectionCard>

        <SectionCard title="Visit Timing" description="Check-in / check-out log">
          <div className="space-y-3 text-sm">
            <div className="flex items-center gap-2 text-muted-foreground">
              <CalendarClock className="h-4 w-4" />
              <span>Scheduled: {formatDate(visit.visitDate)}</span>
            </div>
            <div className="flex items-center gap-2 text-muted-foreground">
              <CalendarClock className="h-4 w-4" />
              <span>Check-in: {visit.checkInAt ? formatDateTime(visit.checkInAt) : "Not checked in"}</span>
            </div>
            <div className="flex items-center gap-2 text-muted-foreground">
              <CalendarClock className="h-4 w-4" />
              <span>Check-out: {visit.checkOutAt ? formatDateTime(visit.checkOutAt) : "Not checked out"}</span>
            </div>
            {visit.checkInLat && visit.checkInLng && (
              <a
                href={`https://maps.google.com/?q=${visit.checkInLat},${visit.checkInLng}`}
                target="_blank" rel="noreferrer"
                className="inline-flex items-center gap-2 text-sm font-medium text-primary hover:underline"
              >
                <Navigation className="h-4 w-4" /> View GPS check-in location
              </a>
            )}
            {visit.eta && (
              <p className="text-xs text-muted-foreground">
                Last ETA: {visit.eta.eta_minutes} min{visit.eta.distance_km ? ` · ${visit.eta.distance_km.toFixed(1)} km` : ""}
              </p>
            )}
          </div>
        </SectionCard>
      </div>

      <SectionCard title="Visit Report" description="Notes and field report submitted by the officer">
        {visit.notes || visit.visitReport ? (
          <div className="flex items-start gap-3 rounded-xl bg-muted/40 p-4 text-sm text-foreground">
            <FileText className="mt-0.5 h-4 w-4 shrink-0 text-muted-foreground" />
            <p>{visit.visitReport || visit.notes}</p>
          </div>
        ) : (
          <EmptyState title="No report submitted yet" description="The officer hasn't logged notes for this visit." />
        )}
      </SectionCard>

      {visit.missedReason && (
        <SectionCard title="Missed Visit Reason" description="Excuse submitted by the officer">
          <div className="space-y-2 text-sm">
            <StatusPill value={visit.missedReason.status} />
            <p className="rounded-xl bg-muted/40 p-4 text-foreground">{visit.missedReason.reason}</p>
            {visit.missedReason.adminNote && (
              <p className="text-xs italic text-muted-foreground">Admin note: {visit.missedReason.adminNote}</p>
            )}
          </div>
        </SectionCard>
      )}

      {visit.orders.length > 0 && (
        <SectionCard title="Orders from this visit" description={`${visit.orders.length} order(s)`}>
          <ul className="divide-y divide-border">
            {visit.orders.map((o) => (
              <li key={o.id} className="flex items-center justify-between py-3 text-sm">
                <span className="flex items-center gap-2 text-foreground">
                  <Package className="h-4 w-4 text-muted-foreground" /> Order #{o.id} · {formatDate(o.orderDate)}
                </span>
                <span className="flex items-center gap-3">
                  <StatusPill value={o.status} />
                  <span className="font-semibold text-foreground">{formatPKR(o.totalAmount)}</span>
                </span>
              </li>
            ))}
          </ul>
        </SectionCard>
      )}
    </div>
  );
}
