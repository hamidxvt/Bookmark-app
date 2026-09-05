"use client";

import { useEffect, useMemo, useState } from "react";
import Link from "next/link";
import { Search, Eye, Download, CalendarClock, CheckCircle2, Clock, XCircle, Timer } from "lucide-react";
import { EmptyState, SectionCard, StatCard, StatusPill, TableSkeleton } from "@/components/shared/ui-bits";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import {
  DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";

interface Visit {
  id: number;
  visitDate: string;
  status: string;
  notes: string | null;
  visitReport: string | null;
  checkInAt: string | null;
  checkOutAt: string | null;
  isAdhoc: boolean;
  booker: { id: number; name: string; email: string };
  customer: { id: number; name: string; customerType: string; address: string | null };
}

function exportCSV(rows: Visit[]) {
  const headers = ["ID", "Customer", "Type", "Officer", "Date", "Status", "Notes"];
  const csvRows = [
    headers.join(","),
    ...rows.map((r) => [
      r.id,
      `"${r.customer?.name ?? ""}"`,
      r.customer?.customerType ?? "",
      `"${r.booker?.name ?? ""}"`,
      new Date(r.visitDate).toLocaleDateString(),
      r.status,
      `"${(r.notes ?? r.visitReport ?? "").replace(/"/g, "'")}"`,
    ].join(",")),
  ];
  const blob = new Blob([csvRows.join("\n")], { type: "text/csv" });
  const url = URL.createObjectURL(blob);
  const a = document.createElement("a");
  a.href = url; a.download = `visits_${new Date().toISOString().slice(0, 10)}.csv`;
  a.click(); URL.revokeObjectURL(url);
}

function exportPDF(rows: Visit[]) {
  const printContent = `
    <html><head><title>Visits Export</title>
    <style>
      body { font-family: Arial, sans-serif; font-size: 12px; }
      h1 { font-size: 16px; color: #C8102E; }
      table { width: 100%; border-collapse: collapse; margin-top: 12px; }
      th { background: #f8f9fa; text-align: left; padding: 6px; border-bottom: 2px solid #dee2e6; font-size: 11px; }
      td { padding: 6px; border-bottom: 1px solid #f0f0f0; font-size: 11px; }
    </style></head>
    <body>
      <h1>Visits Report</h1>
      <p style="color:#666">Exported: ${new Date().toLocaleString()}</p>
      <table>
        <thead><tr><th>#</th><th>Customer</th><th>Officer</th><th>Date</th><th>Status</th><th>Notes</th></tr></thead>
        <tbody>${rows.map((r, i) => `
          <tr>
            <td>${i + 1}</td>
            <td>${r.customer?.name ?? "—"}</td>
            <td>${r.booker?.name ?? "—"}</td>
            <td>${new Date(r.visitDate).toLocaleDateString()}</td>
            <td>${r.status}</td>
            <td>${r.notes ?? r.visitReport ?? "—"}</td>
          </tr>`).join("")}
        </tbody>
      </table>
    </body></html>`;
  const win = window.open("", "_blank");
  if (!win) return;
  win.document.write(printContent);
  win.document.close();
  win.focus();
  win.print();
}

export default function VisitsClient() {
  const [rows, setRows] = useState<Visit[]>([]);
  const [total, setTotal] = useState(0);
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState("");
  const [statusFilter, setStatusFilter] = useState("all");
  const [bookerFilter, setBookerFilter] = useState("all");

  async function load() {
    setLoading(true);
    try {
      const res = await fetch("/api/v1/visits?length=200").then((r) => r.json());
      if (res.success) {
        setRows(res.data?.data ?? []);
        setTotal(res.data?.recordsTotal ?? 0);
      }
    } catch (e) { console.error(e); }
    finally { setLoading(false); }
  }

  useEffect(() => { load(); }, []);

  const bookers = useMemo(() => {
    const map = new Map<number, string>();
    rows.forEach((r) => { if (r.booker) map.set(r.booker.id, r.booker.name); });
    return Array.from(map, ([id, name]) => ({ id, name }));
  }, [rows]);

  const filtered = rows.filter((r) => {
    const matchSearch = !search ||
      r.customer?.name?.toLowerCase().includes(search.toLowerCase()) ||
      r.booker?.name?.toLowerCase().includes(search.toLowerCase());
    const matchStatus = statusFilter === "all" || r.status === statusFilter;
    const matchBooker = bookerFilter === "all" || String(r.booker?.id) === bookerFilter;
    return matchSearch && matchStatus && matchBooker;
  });

  const count = (s: string) => rows.filter((r) => r.status === s).length;

  return (
    <div className="space-y-6 px-6 py-6 lg:px-8">
      <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-5">
        <StatCard label="Total Visits" value={total} icon={<CalendarClock className="h-5 w-5" />} />
        <StatCard label="Completed" value={count("COMPLETED")} icon={<CheckCircle2 className="h-5 w-5" />} />
        <StatCard label="Pending" value={count("PENDING")} icon={<Clock className="h-5 w-5" />} />
        <StatCard label="In Progress" value={count("IN_PROGRESS")} icon={<Timer className="h-5 w-5" />} />
        <StatCard label="Cancelled" value={count("CANCELLED")} icon={<XCircle className="h-5 w-5" />} />
      </div>

      <SectionCard
        title="Visit Register"
        description={`${filtered.length} of ${total} visits`}
        action={
          <DropdownMenu>
            <DropdownMenuTrigger asChild>
              <Button variant="outline" className="rounded-xl">
                <Download className="mr-2 h-4 w-4" /> Export
              </Button>
            </DropdownMenuTrigger>
            <DropdownMenuContent align="end">
              <DropdownMenuItem onClick={() => exportCSV(filtered)}>Export CSV</DropdownMenuItem>
              <DropdownMenuItem onClick={() => exportPDF(filtered)}>Export PDF</DropdownMenuItem>
            </DropdownMenuContent>
          </DropdownMenu>
        }
      >
        <div className="mb-5 grid gap-3 md:grid-cols-3">
          <div className="relative">
            <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
            <Input
              value={search}
              onChange={(e) => setSearch(e.target.value)}
              placeholder="Search by customer or officer…"
              className="h-11 rounded-xl pl-9"
            />
          </div>
          <Select value={bookerFilter} onValueChange={setBookerFilter}>
            <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Officer" /></SelectTrigger>
            <SelectContent>
              <SelectItem value="all">All officers</SelectItem>
              {bookers.map((b) => (
                <SelectItem key={b.id} value={String(b.id)}>{b.name}</SelectItem>
              ))}
            </SelectContent>
          </Select>
          <Select value={statusFilter} onValueChange={setStatusFilter}>
            <SelectTrigger className="h-11 rounded-xl"><SelectValue placeholder="Status" /></SelectTrigger>
            <SelectContent>
              <SelectItem value="all">All statuses</SelectItem>
              <SelectItem value="PENDING">Pending</SelectItem>
              <SelectItem value="IN_PROGRESS">In progress</SelectItem>
              <SelectItem value="COMPLETED">Completed</SelectItem>
              <SelectItem value="CANCELLED">Cancelled</SelectItem>
            </SelectContent>
          </Select>
        </div>

        {loading ? (
          <TableSkeleton />
        ) : filtered.length === 0 ? (
          <EmptyState title="No visits match these filters" description="Adjust the search, officer or status filters." />
        ) : (
          <div className="overflow-x-auto">
            <Table>
              <TableHeader>
                <TableRow>
                  {["#", "Customer", "Officer", "Date", "Status", "Notes", ""].map((h) => (
                    <TableHead key={h}>{h}</TableHead>
                  ))}
                </TableRow>
              </TableHeader>
              <TableBody>
                {filtered.map((r, i) => (
                  <TableRow key={r.id} className="transition-colors hover:bg-muted/50">
                    <TableCell className="text-muted-foreground">{i + 1}</TableCell>
                    <TableCell>
                      <p className="font-medium text-foreground">{r.customer?.name ?? "—"}</p>
                      <p className="text-xs text-muted-foreground">{r.customer?.customerType}{r.isAdhoc ? " · Ad-hoc" : ""}</p>
                    </TableCell>
                    <TableCell className="text-muted-foreground">{r.booker?.name ?? "—"}</TableCell>
                    <TableCell className="whitespace-nowrap text-muted-foreground">
                      {new Date(r.visitDate).toLocaleDateString("en-PK", { day: "2-digit", month: "short", year: "numeric" })}
                    </TableCell>
                    <TableCell><StatusPill value={r.status} /></TableCell>
                    <TableCell className="max-w-[160px] truncate text-muted-foreground">{r.notes ?? r.visitReport ?? "—"}</TableCell>
                    <TableCell className="text-right">
                      <Link href={`/visits/${r.id}`}>
                        <Button variant="ghost" size="icon" className="h-9 w-9 rounded-xl">
                          <Eye className="h-4 w-4" />
                        </Button>
                      </Link>
                    </TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </div>
        )}
      </SectionCard>
    </div>
  );
}
