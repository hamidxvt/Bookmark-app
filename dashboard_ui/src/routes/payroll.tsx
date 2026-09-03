import { createFileRoute } from "@tanstack/react-router";
import { BadgeCheck, Download, Gift, Search, Wallet } from "lucide-react";
import { useMemo, useState } from "react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { EmptyState, SectionCard, StatCard, StatusPill, TableSkeleton } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle } from "@/components/ui/dialog";
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from "@/components/ui/dropdown-menu";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { approvePayroll, exportCsv, markPayrollPaid, usePayroll, type PayrollRecord } from "@/lib/store";

export const Route = createFileRoute("/payroll")({
  head: () => ({
    meta: [
      { title: "Payroll — Bookmark Field Force Manager" },
      { name: "description", content: "Salaries, visit commission, incentives, deductions and payout approvals." },
      { property: "og:title", content: "Payroll — Bookmark Field Force Manager" },
      { property: "og:description", content: "Field force payroll with commission and incentive calculations." },
    ],
  }),
  component: PayrollPage,
});

const pkr = (n: number) => `PKR ${n.toLocaleString()}`;

function PayrollPage() {
  const rows = usePayroll();
  const [query, setQuery] = useState("");
  const [month, setMonth] = useState("all");
  const [status, setStatus] = useState("all");
  const [loading, setLoading] = useState(false);
  const [viewing, setViewing] = useState<PayrollRecord | null>(null);

  const months = [...new Set(rows.map((r) => r.month))];
  const filtered = useMemo(
    () =>
      rows.filter((r) => {
        const q = query.trim().toLowerCase();
        if (q && !r.officer.toLowerCase().includes(q)) return false;
        if (month !== "all" && r.month !== month) return false;
        if (status !== "all" && r.status !== status) return false;
        return true;
      }),
    [rows, query, month, status],
  );

  const total = rows.reduce((a, r) => a + r.final, 0);
  const commission = rows.reduce((a, r) => a + r.commission, 0);
  const incentives = rows.reduce((a, r) => a + r.incentives, 0);
  const pending = rows.filter((r) => r.status !== "paid").reduce((a, r) => a + r.final, 0);
  const refresh = () => {
    setLoading(true);
    setTimeout(() => setLoading(false), 500);
  };

  return (
    <AppShell title="Payroll" subtitle="Salary, commission and incentive processing" onRefresh={refresh}>
      <div className="space-y-6">
        <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-4">
          <StatCard label="Total Payroll" value={pkr(total)} icon={<Wallet className="h-5 w-5" />} onClick={refresh} />
          <StatCard label="Commission" value={pkr(commission)} icon={<BadgeCheck className="h-5 w-5" />} onClick={refresh} />
          <StatCard label="Incentives" value={pkr(incentives)} icon={<Gift className="h-5 w-5" />} onClick={refresh} />
          <StatCard label="Pending Payments" value={pkr(pending)} icon={<Wallet className="h-5 w-5" />} onClick={() => setStatus("pending")} />
        </div>

        <SectionCard
          title="Payroll Register"
          description={`${filtered.length} of ${rows.length} records`}
          action={
            <div className="flex gap-2">
              <Button
                variant="outline"
                className="rounded-xl"
                onClick={() => {
                  exportCsv("payroll.csv", filtered);
                  toast.success("Payroll exported as CSV");
                }}
              >
                <Download className="mr-2 h-4 w-4" /> Export
              </Button>
              <Button
                className="rounded-xl"
                onClick={() => {
                  filtered.filter((r) => r.status === "pending").forEach((r) => approvePayroll(r.id));
                  toast.success("All pending payroll approved");
                }}
              >
                <BadgeCheck className="mr-2 h-4 w-4" /> Approve all pending
              </Button>
            </div>
          }
        >
          <div className="mb-5 grid gap-3 md:grid-cols-3">
            <div className="relative">
              <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
              <Input value={query} onChange={(e) => setQuery(e.target.value)} placeholder="Search officer" className="h-11 rounded-xl pl-9" />
            </div>
            <Select value={month} onValueChange={setMonth}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Month" /></SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All months</SelectItem>
                {months.map((m) => <SelectItem key={m} value={m}>{m}</SelectItem>)}
              </SelectContent>
            </Select>
            <Select value={status} onValueChange={setStatus}>
              <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Status" /></SelectTrigger>
              <SelectContent>
                <SelectItem value="all">All statuses</SelectItem>
                <SelectItem value="pending">Pending</SelectItem>
                <SelectItem value="approved">Approved</SelectItem>
                <SelectItem value="paid">Paid</SelectItem>
              </SelectContent>
            </Select>
          </div>

          {loading ? (
            <TableSkeleton />
          ) : filtered.length === 0 ? (
            <EmptyState title="No payroll records" description="Try a different month or status." />
          ) : (
            <div className="overflow-x-auto">
              <Table>
                <TableHeader>
                  <TableRow>
                    {["Officer", "Month", "Salary", "Visits", "Commission", "Incentives", "Deductions", "Final", "Status", ""].map((h) => (
                      <TableHead key={h}>{h}</TableHead>
                    ))}
                  </TableRow>
                </TableHeader>
                <TableBody>
                  {filtered.map((r) => (
                    <TableRow key={r.id} className="transition-colors hover:bg-muted/50">
                      <TableCell className="font-medium">{r.officer}</TableCell>
                      <TableCell>{r.month}</TableCell>
                      <TableCell>{pkr(r.salary)}</TableCell>
                      <TableCell>{r.completedVisits}</TableCell>
                      <TableCell>{pkr(r.commission)}</TableCell>
                      <TableCell>{pkr(r.incentives)}</TableCell>
                      <TableCell className="text-destructive">-{pkr(r.deductions)}</TableCell>
                      <TableCell className="font-semibold">{pkr(r.final)}</TableCell>
                      <TableCell><StatusPill value={r.status === "paid" ? "completed" : r.status} /></TableCell>
                      <TableCell className="text-right">
                        <DropdownMenu>
                          <DropdownMenuTrigger asChild>
                            <Button variant="ghost" className="rounded-xl text-xs">Actions</Button>
                          </DropdownMenuTrigger>
                          <DropdownMenuContent align="end" className="rounded-xl">
                            <DropdownMenuItem onClick={() => setViewing(r)}>View slip</DropdownMenuItem>
                            <DropdownMenuItem disabled={r.status !== "pending"} onClick={() => { approvePayroll(r.id); toast.success(`${r.officer} payroll approved`); }}>
                              Approve
                            </DropdownMenuItem>
                            <DropdownMenuItem disabled={r.status === "paid"} onClick={() => { markPayrollPaid(r.id); toast.success("Marked as paid"); }}>
                              Mark paid
                            </DropdownMenuItem>
                            <DropdownMenuItem onClick={() => { exportCsv(`${r.id}.csv`, [r]); toast.success("Slip exported"); }}>
                              Export slip
                            </DropdownMenuItem>
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

      <Dialog open={!!viewing} onOpenChange={(o) => !o && setViewing(null)}>
        <DialogContent className="rounded-2xl">
          <DialogHeader>
            <DialogTitle>Salary slip · {viewing?.officer}</DialogTitle>
            <DialogDescription>{viewing?.month}</DialogDescription>
          </DialogHeader>
          {viewing && (
            <dl className="space-y-2 text-sm">
              {[
                ["Base salary", pkr(viewing.salary)],
                ["Completed visits", String(viewing.completedVisits)],
                ["Commission", pkr(viewing.commission)],
                ["Incentives", pkr(viewing.incentives)],
                ["Deductions", `-${pkr(viewing.deductions)}`],
              ].map(([k, v]) => (
                <div key={k} className="flex justify-between border-b border-border/60 pb-2">
                  <dt className="text-muted-foreground">{k}</dt>
                  <dd className="font-medium">{v}</dd>
                </div>
              ))}
              <div className="flex justify-between pt-1 text-base font-bold">
                <dt>Final amount</dt>
                <dd className="text-primary">{pkr(viewing.final)}</dd>
              </div>
            </dl>
          )}
          <DialogFooter>
            <Button variant="outline" className="rounded-xl" onClick={() => { if (viewing) exportCsv(`${viewing.id}.csv`, [viewing]); toast.success("Slip exported"); }}>
              Export
            </Button>
            <Button
              className="rounded-xl"
              onClick={() => {
                if (viewing) approvePayroll(viewing.id);
                setViewing(null);
                toast.success("Payroll approved");
              }}
            >
              Approve
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </AppShell>
  );
}
