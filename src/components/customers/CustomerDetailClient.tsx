"use client";

import { useEffect, useState } from "react";
import Link from "next/link";
import {
  ArrowLeft, Building2, Mail, MapPin, Phone, User, Globe,
  CalendarPlus, Pencil,
} from "lucide-react";
import { SectionCard, EmptyState, StatusPill } from "@/components/shared/ui-bits";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { cn } from "@/lib/utils";

const APPROVAL_TONE: Record<string, string> = {
  APPROVED:     "bg-success/15 text-success border-success/25",
  PENDING:      "bg-warning/20 text-warning-foreground border-warning/30",
  NOT_APPROVED: "bg-destructive/15 text-destructive border-destructive/25",
};
const APPROVAL_LABEL: Record<string, string> = {
  APPROVED: "Approved", PENDING: "Pending", NOT_APPROVED: "Not Approved",
};

function ApprovalBadge({ status }: { status: string }) {
  const s = (status ?? "").toUpperCase();
  return (
    <Badge variant="outline" className={cn("rounded-full font-medium", APPROVAL_TONE[s] ?? "bg-muted text-muted-foreground")}>
      {APPROVAL_LABEL[s] ?? "—"}
    </Badge>
  );
}

interface CustomerDetail {
  id: number;
  name: string;
  customerType: string;
  category: string | null;
  ownerName: string | null;
  ownerPhone: string;
  email: string | null;
  website: string | null;
  address: string | null;
  zone: string | null;
  workingPriority: number;
  approvalStatus: string;
  examinationBoard: string | null;
  offeredProgramme: string | null;
  totalStudents: number | null;
  createdAt: string;
  city: { id: number; name: string } | null;
  area: { id: number; name: string } | null;
  assignedBooker: { id: number; name: string; phone: string; jobStatus: string } | null;
  visitCount: number;
  visits: {
    id: number;
    visitDate: string;
    status: string;
    booker: { id: number; name: string };
  }[];
  sampleRequests: {
    id: number;
    productName: string;
    quantity: number;
    status: string;
    createdAt: string;
  }[];
}

export default function CustomerDetailClient({ id }: { id: string }) {
  const [customer, setCustomer] = useState<CustomerDetail | null>(null);
  const [loading, setLoading] = useState(true);
  const [notFound, setNotFound] = useState(false);

  useEffect(() => {
    let cancelled = false;
    (async () => {
      try {
        const res = await fetch(`/api/v1/customers/${id}`).then(r => r.json());
        if (cancelled) return;
        if (res.success) setCustomer(res.data);
        else setNotFound(true);
      } catch (e) {
        console.error(e);
        if (!cancelled) setNotFound(true);
      } finally {
        if (!cancelled) setLoading(false);
      }
    })();
    return () => { cancelled = true; };
  }, [id]);

  if (loading) {
    return (
      <div className="flex min-h-[60vh] items-center justify-center">
        <div className="flex flex-col items-center gap-3">
          <div className="h-8 w-8 animate-spin rounded-full border-2 border-primary border-t-transparent" />
          <p className="text-sm font-medium text-muted-foreground">Loading customer…</p>
        </div>
      </div>
    );
  }

  if (notFound || !customer) {
    return (
      <div className="space-y-4">
        <EmptyState title="We couldn't find that customer" description="The record may have been deleted." />
        <Button className="rounded-xl" asChild>
          <Link href="/customers">Back to Customers</Link>
        </Button>
      </div>
    );
  }

  const info: [string, string][] = [
    ["Category", customer.category ?? "—"],
    ["Type", customer.customerType],
    ["City", customer.city?.name ?? "—"],
    ["Area", customer.area?.name ?? "—"],
    ["Zone", customer.zone ?? "—"],
    ["Total visits", String(customer.visitCount)],
  ];
  if (customer.customerType === "SCHOOL" || customer.customerType === "COLLEGE") {
    if (customer.examinationBoard) info.push(["Exam Board", customer.examinationBoard]);
    if (customer.offeredProgramme) info.push(["Programme", customer.offeredProgramme]);
    if (customer.totalStudents) info.push(["Students", String(customer.totalStudents)]);
  }

  return (
    <div className="space-y-6">
      <div className="flex flex-wrap items-center justify-between gap-3">
        <Button variant="ghost" className="rounded-xl" asChild>
          <Link href="/customers">
            <ArrowLeft className="mr-2 h-4 w-4" /> Back to Customers
          </Link>
        </Button>
        <div className="flex gap-2">
          <Button variant="outline" className="rounded-xl" asChild>
            <Link href={`/customers/${customer.id}/edit`}>
              <Pencil className="mr-2 h-4 w-4" /> Edit Customer
            </Link>
          </Button>
          <Button className="rounded-xl" asChild>
            <Link href="/visits/add">
              <CalendarPlus className="mr-2 h-4 w-4" /> Schedule Visit
            </Link>
          </Button>
        </div>
      </div>

      <div>
        <h1 className="text-2xl font-bold text-foreground">{customer.name}</h1>
        <p className="text-sm text-muted-foreground">
          #{customer.id} · {customer.category ?? customer.customerType} · {customer.city?.name ?? "—"}
        </p>
      </div>

      <div className="grid gap-6 xl:grid-cols-2">
        <SectionCard title="Customer Information">
          <dl className="grid gap-4 sm:grid-cols-2">
            {info.map(([k, v]) => (
              <div key={k} className="rounded-xl bg-muted/60 p-3">
                <dt className="text-xs text-muted-foreground">{k}</dt>
                <dd className="font-medium text-foreground">{v}</dd>
              </div>
            ))}
          </dl>
          <div className="mt-5">
            <ApprovalBadge status={customer.approvalStatus} />
          </div>
          <div className="mt-5 space-y-3 border-t border-border pt-5 text-sm">
            {customer.ownerName && (
              <p className="flex items-center gap-2 text-foreground">
                <User className="h-4 w-4 text-muted-foreground" /> {customer.ownerName}
              </p>
            )}
            <p className="flex items-center gap-2 text-foreground">
              <Phone className="h-4 w-4 text-muted-foreground" /> {customer.ownerPhone}
            </p>
            {customer.email && (
              <p className="flex items-center gap-2 text-foreground">
                <Mail className="h-4 w-4 text-muted-foreground" /> {customer.email}
              </p>
            )}
            {customer.website && (
              <p className="flex items-center gap-2 text-foreground">
                <Globe className="h-4 w-4 text-muted-foreground" /> {customer.website}
              </p>
            )}
            {customer.address && (
              <p className="flex items-center gap-2 text-foreground">
                <MapPin className="h-4 w-4 text-muted-foreground" /> {customer.address}
              </p>
            )}
          </div>
        </SectionCard>

        <SectionCard title="Assigned Officer">
          {customer.assignedBooker ? (
            <div className="flex items-center gap-3 rounded-xl border border-border/70 p-4">
              <span className="flex h-11 w-11 items-center justify-center rounded-full bg-navy text-sm font-bold text-navy-foreground">
                {customer.assignedBooker.name.split(" ").map(w => w[0]).join("").toUpperCase().slice(0, 2)}
              </span>
              <div className="min-w-0 flex-1">
                <p className="truncate text-sm font-semibold text-foreground">{customer.assignedBooker.name}</p>
                <p className="text-xs text-muted-foreground">{customer.assignedBooker.phone}</p>
              </div>
              <StatusPill value={customer.assignedBooker.jobStatus} />
            </div>
          ) : (
            <EmptyState title="No officer assigned" description="This customer has no dedicated field officer yet." />
          )}
          <div className="mt-4 flex items-center gap-3 rounded-xl bg-muted/60 p-4">
            <Building2 className="h-5 w-5 text-primary" />
            <p className="text-sm text-muted-foreground">
              Priority level: <span className="font-medium text-foreground">{customer.workingPriority}</span>
            </p>
          </div>
        </SectionCard>
      </div>

      <div className="grid gap-6 xl:grid-cols-[1.5fr_1fr]">
        <SectionCard title="Visit History" description="Most recent visits">
          {customer.visits.length === 0 ? (
            <EmptyState title="No visits recorded yet" description="Schedule the first visit for this customer." />
          ) : (
            <Table>
              <TableHeader>
                <TableRow>
                  <TableHead>Officer</TableHead>
                  <TableHead>Date</TableHead>
                  <TableHead>Status</TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                {customer.visits.map((v) => (
                  <TableRow key={v.id}>
                    <TableCell className="font-medium">{v.booker?.name ?? "—"}</TableCell>
                    <TableCell className="text-muted-foreground">
                      {new Date(v.visitDate).toLocaleDateString("en-PK", { day: "2-digit", month: "short", year: "numeric" })}
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

        <SectionCard title="Samples">
          {customer.sampleRequests.length === 0 ? (
            <EmptyState title="No samples delivered" />
          ) : (
            <ul className="space-y-3">
              {customer.sampleRequests.map((s) => (
                <li key={s.id} className="flex items-center justify-between rounded-xl border border-border/70 p-3">
                  <div className="min-w-0">
                    <p className="truncate text-sm font-medium text-foreground">{s.productName}</p>
                    <p className="text-xs text-muted-foreground">
                      {s.quantity} units · {new Date(s.createdAt).toLocaleDateString("en-PK", { day: "2-digit", month: "short" })}
                    </p>
                  </div>
                  <StatusPill value={s.status} />
                </li>
              ))}
            </ul>
          )}
        </SectionCard>
      </div>
    </div>
  );
}
