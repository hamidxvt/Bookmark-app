import { createFileRoute, Link, useNavigate } from "@tanstack/react-router";
import { ArrowLeft, Building2, Mail, MapPin, Phone, User } from "lucide-react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { GoogleMapView } from "@/components/app/GoogleMap";
import { toLatLng } from "@/lib/geo";

import { EmptyState, SectionCard, StatusPill } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { customers, initials, officers, samples, visits } from "@/lib/mock-data";

export const Route = createFileRoute("/customer/$id")({
  head: () => ({
    meta: [
      { title: "Customer Detail — Bookmark Field Force Manager" },
      { name: "description", content: "Customer profile with contact details, visit history, officers and samples." },
      { property: "og:title", content: "Customer Detail — Bookmark Field Force Manager" },
      { property: "og:description", content: "Customer profile, visit history and assigned officers." },
    ],
  }),
  component: CustomerPage,
});

function CustomerPage() {
  const { id } = Route.useParams();
  const navigate = useNavigate();
  const customer = customers.find((c) => c.id === id);

  if (!customer) {
    return (
      <AppShell title="Customer not found">
        <EmptyState title="We couldn't find that customer" description="The record may have been deleted." />
        <div className="mt-4">
          <Button className="rounded-xl" onClick={() => navigate({ to: "/customers" })}>
            Back to Customers
          </Button>
        </div>
      </AppShell>
    );
  }

  const custVisits = visits.filter((v) => v.customer === customer.name);
  const custSamples = samples.filter((s) => s.customer === customer.name);
  const assigned = officers.filter((o) => o.name === customer.officer || o.city === customer.city).slice(0, 3);

  return (
    <AppShell title={customer.name} subtitle={`${customer.id} · ${customer.category} · ${customer.city}`}>
      <div className="space-y-6">
        <div className="flex flex-wrap items-center justify-between gap-3">
          <Button variant="ghost" className="rounded-xl" onClick={() => navigate({ to: "/customers" })}>
            <ArrowLeft className="mr-2 h-4 w-4" /> Back to Customers
          </Button>
          <div className="flex gap-2">
            <Button variant="outline" className="rounded-xl" onClick={() => toast.info("Edit form opened")}>
              Edit Customer
            </Button>
            <Button className="rounded-xl" onClick={() => toast.success("Visit scheduled for tomorrow 10:00 AM")}>
              Schedule Visit
            </Button>
          </div>
        </div>

        <div className="grid gap-6 xl:grid-cols-[1fr_1.1fr]">
          <SectionCard title="Customer Information">
            <dl className="grid gap-4 sm:grid-cols-2">
              {[
                ["Category", customer.category],
                ["Type", customer.type],
                ["City", customer.city],
                ["Area", customer.area],
                ["Joined", customer.joined],
                ["Total visits", String(customer.visits)],
              ].map(([k, v]) => (
                <div key={k} className="rounded-xl bg-muted/60 p-3">
                  <dt className="text-xs text-muted-foreground">{k}</dt>
                  <dd className="font-medium">{v}</dd>
                </div>
              ))}
            </dl>
            <div className="mt-5">
              <StatusPill value={customer.status} />
            </div>
            <div className="mt-5 space-y-3 border-t border-border pt-5 text-sm">
              <p className="flex items-center gap-2">
                <User className="h-4 w-4 text-muted-foreground" /> {customer.contact}
              </p>
              <p className="flex items-center gap-2">
                <Phone className="h-4 w-4 text-muted-foreground" /> {customer.phone}
              </p>
              <p className="flex items-center gap-2">
                <Mail className="h-4 w-4 text-muted-foreground" /> {customer.email}
              </p>
              <p className="flex items-center gap-2">
                <MapPin className="h-4 w-4 text-muted-foreground" /> {customer.address}
              </p>
            </div>
          </SectionCard>

          <SectionCard title="Location" description="Mapped position and nearby officers (Google Maps)">
            <GoogleMapView
              className="h-[360px]"
              center={toLatLng(customer.city, customer.pos)}
              zoom={13}
              markers={[
                {
                  id: customer.id,
                  name: customer.name,
                  position: toLatLng(customer.city, customer.pos),
                  kind: "customer",
                  detail: customer.address,
                },
                ...assigned.map((o) => ({
                  id: o.id,
                  name: o.name,
                  position: toLatLng(o.city, o.pos),
                  kind: "officer" as const,
                  status: o.status,
                  detail: o.location,
                })),
              ]}
            />
          </SectionCard>

        </div>

        <div className="grid gap-6 xl:grid-cols-[1.5fr_1fr]">
          <SectionCard title="Visit History">
            {custVisits.length === 0 ? (
              <EmptyState title="No visits recorded yet" description="Schedule the first visit for this customer." />
            ) : (
              <Table>
                <TableHeader>
                  <TableRow>
                    <TableHead>Visit</TableHead>
                    <TableHead>Officer</TableHead>
                    <TableHead>Date</TableHead>
                    <TableHead>Status</TableHead>
                  </TableRow>
                </TableHeader>
                <TableBody>
                  {custVisits.map((v) => (
                    <TableRow key={v.id}>
                      <TableCell className="font-medium">{v.id}</TableCell>
                      <TableCell>{v.officer}</TableCell>
                      <TableCell className="text-muted-foreground">
                        {v.date} · {v.time}
                      </TableCell>
                      <TableCell>
                        <StatusPill value={v.status} />
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            )}
          </SectionCard>

          <div className="space-y-6">
            <SectionCard title="Assigned Officers">
              <ul className="space-y-3">
                {assigned.map((o) => (
                  <li key={o.id} className="flex items-center gap-3 rounded-xl border border-border/70 p-3">
                    <span className="flex h-9 w-9 items-center justify-center rounded-full bg-navy text-xs font-bold text-navy-foreground">
                      {initials(o.name)}
                    </span>
                    <div className="min-w-0 flex-1">
                      <Link to="/officer/$id" params={{ id: o.id }} className="text-sm font-medium hover:text-primary">
                        {o.name}
                      </Link>
                      <p className="text-xs text-muted-foreground">{o.city}</p>
                    </div>
                    <StatusPill value={o.status} />
                  </li>
                ))}
              </ul>
            </SectionCard>

            <SectionCard title="Samples">
              {custSamples.length === 0 ? (
                <EmptyState title="No samples delivered" />
              ) : (
                <ul className="space-y-3">
                  {custSamples.map((s) => (
                    <li key={s.id} className="flex items-center justify-between rounded-xl border border-border/70 p-3">
                      <div className="min-w-0">
                        <p className="truncate text-sm font-medium">{s.product}</p>
                        <p className="text-xs text-muted-foreground">
                          {s.qty} units · {s.date}
                        </p>
                      </div>
                      <StatusPill value={s.status} />
                    </li>
                  ))}
                </ul>
              )}
            </SectionCard>

            <div className="surface flex items-center gap-3 p-5">
              <Building2 className="h-5 w-5 text-primary" />
              <p className="text-sm text-muted-foreground">
                Account owner: <span className="font-medium text-foreground">{customer.officer}</span>
              </p>
            </div>
          </div>
        </div>
      </div>
    </AppShell>
  );
}
