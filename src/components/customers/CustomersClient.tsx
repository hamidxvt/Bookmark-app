"use client";

import { useEffect, useRef, useState } from "react";
import Link from "next/link";
import {
  Plus, Search, Download, Eye, Pencil, RefreshCw, ChevronDown,
} from "lucide-react";
import { SectionCard, EmptyState, TableSkeleton } from "@/components/shared/ui-bits";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { Input } from "@/components/ui/input";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { cn } from "@/lib/utils";

function stripHtml(s: string | null | undefined) {
  if (!s) return "";
  return s.replace(/<[^>]+>/g, " ").replace(/&[a-z]+;/gi, " ").replace(/\s+/g, " ").trim();
}

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

const CATEGORY_TONE: Record<string, string> = {
  "A+": "bg-info/15 text-info-foreground border-info/25",
  "A":  "bg-info/10 text-info-foreground border-info/20",
  "B":  "bg-success/10 text-success border-success/20",
  "BOOKSHOPS": "bg-warning/15 text-warning-foreground border-warning/25",
};

function CategoryBadge({ cat }: { cat: string | null | undefined }) {
  if (!cat) return null;
  return (
    <Badge variant="outline" className={cn("rounded-full text-[10px] font-semibold", CATEGORY_TONE[cat] ?? "bg-muted text-muted-foreground")}>
      {cat}
    </Badge>
  );
}

// ─── Export Menu ─────────────────────────────────────────────────────────────

function ExportMenu({ rows }: { rows: any[] }) {
  const [open, setOpen] = useState(false);
  const ref = useRef<HTMLDivElement>(null);
  useEffect(() => {
    const h = (e: MouseEvent) => { if (ref.current && !ref.current.contains(e.target as Node)) setOpen(false); };
    document.addEventListener("mousedown", h);
    return () => document.removeEventListener("mousedown", h);
  }, []);

  function exportCSV() {
    const headers = ["ID","Name","Type","Category","City","Phone","Email","Address","Status","Joined"];
    const csv = [headers.join(","), ...rows.map(r => [
      r.id, `"${r.name??""}"`, r.customerType??"", r.category??"",
      `"${r.city?.name??""}"`, r.ownerPhone??"", r.email??"",
      `"${(r.address??"").replace(/"/g,"'")}"`,
      r.approvalStatus??"", r.createdAt ? new Date(r.createdAt).toLocaleDateString() : "",
    ].join(","))].join("\n");
    const blob = new Blob([csv], { type:"text/csv" });
    const a = document.createElement("a"); a.href = URL.createObjectURL(blob);
    a.download = `customers_${new Date().toISOString().slice(0,10)}.csv`; a.click();
    setOpen(false);
  }

  function exportPDF() {
    const win = window.open("","_blank"); if (!win) return;
    win.document.write(`<html><head><title>Customers</title>
      <style>body{font-family:Arial;font-size:11px}h1{color:#C8102E}
      table{width:100%;border-collapse:collapse}th{background:#f8f9fa;padding:5px;border-bottom:2px solid #dee2e6;text-align:left}
      td{padding:5px;border-bottom:1px solid #f0f0f0}</style></head>
      <body><h1>Customers Report</h1><p style="color:#666">Exported: ${new Date().toLocaleString()}</p>
      <table><thead><tr><th>#</th><th>Name</th><th>Type</th><th>Category</th><th>City</th><th>Phone</th><th>Status</th></tr></thead>
      <tbody>${rows.map((r,i)=>`<tr><td>${i+1}</td><td>${r.name??""}</td><td>${r.customerType??""}</td><td>${r.category??""}</td><td>${r.city?.name??""}</td><td>${r.ownerPhone??""}</td><td>${r.approvalStatus??""}</td></tr>`).join("")}
      </tbody></table></body></html>`);
    win.document.close(); win.focus(); win.print(); setOpen(false);
  }

  return (
    <div className="relative" ref={ref}>
      <Button variant="outline" className="rounded-xl" onClick={() => setOpen(!open)}>
        <Download className="mr-2 h-4 w-4" /> Export <ChevronDown className="ml-1 h-3.5 w-3.5" />
      </Button>
      {open && (
        <div className="absolute right-0 top-full z-10 mt-1 w-40 overflow-hidden rounded-xl border border-border bg-card shadow-elevated">
          <button onClick={exportCSV} className="w-full px-3 py-2 text-left text-sm text-foreground hover:bg-muted/60">Export CSV</button>
          <button onClick={exportPDF} className="w-full px-3 py-2 text-left text-sm text-foreground hover:bg-muted/60">Export PDF</button>
        </div>
      )}
    </div>
  );
}

// ─── Main Component ───────────────────────────────────────────────────────────

export default function CustomersClient() {
  const [rows, setRows]               = useState<any[]>([]);
  const [total, setTotal]             = useState(0);
  const [loading, setLoading]         = useState(true);
  const [search, setSearch]           = useState("");
  const [typeFilter, setTypeFilter]   = useState("all");
  const [statusFilter, setStatusFilter] = useState("all");
  const [page, setPage]               = useState(0);
  const PER_PAGE = 50;

  async function load(p = 0) {
    setLoading(true);
    try {
      const res = await fetch(`/api/v1/customers?start=${p * PER_PAGE}&length=${PER_PAGE}`).then(r => r.json());
      if (res.success) {
        setRows(res.data?.data ?? []);
        setTotal(res.data?.recordsTotal ?? 0);
      }
    } catch (e) { console.error(e); }
    finally { setLoading(false); }
  }

  useEffect(() => { load(page); }, [page]);

  const filtered = rows.filter(r => {
    const name = (r.name ?? "").toLowerCase();
    const matchSearch = !search ||
      name.includes(search.toLowerCase()) ||
      (r.city?.name ?? "").toLowerCase().includes(search.toLowerCase()) ||
      (r.category ?? "").toLowerCase().includes(search.toLowerCase());
    const matchType   = typeFilter === "all" || r.customerType === typeFilter;
    const matchStatus = statusFilter === "all" ||
      (statusFilter === "approved"     && r.approvalStatus === "APPROVED") ||
      (statusFilter === "pending"      && r.approvalStatus === "PENDING") ||
      (statusFilter === "not_approved" && r.approvalStatus === "NOT_APPROVED");
    return matchSearch && matchType && matchStatus;
  });

  const totalPages = Math.max(1, Math.ceil(total / PER_PAGE));

  return (
    <div className="space-y-6">
      <SectionCard
        title="Customer Directory"
        description={`${total.toLocaleString()} records`}
        action={
          <div className="flex flex-wrap gap-2">
            <Button variant="outline" size="icon" className="rounded-xl" onClick={() => load(page)} disabled={loading}>
              <RefreshCw className={cn("h-4 w-4", loading && "animate-spin")} />
            </Button>
            <ExportMenu rows={filtered} />
            <Button className="rounded-xl" asChild>
              <Link href="/customers/add">
                <Plus className="mr-2 h-4 w-4" /> Add Customer
              </Link>
            </Button>
          </div>
        }
      >
        <div className="mb-5 flex flex-wrap gap-3">
          <div className="relative min-w-64 flex-1">
            <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
            <Input
              value={search}
              onChange={e => { setSearch(e.target.value); setPage(0); }}
              placeholder="Search by name, city, or category…"
              className="h-11 rounded-xl pl-9"
            />
          </div>
          <Select value={typeFilter} onValueChange={v => { setTypeFilter(v); setPage(0); }}>
            <SelectTrigger className="h-11 w-44 rounded-xl">
              <SelectValue placeholder="Type" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="all">All Types</SelectItem>
              <SelectItem value="SCHOOL">School</SelectItem>
              <SelectItem value="COLLEGE">College</SelectItem>
              <SelectItem value="RETAILER">Book Shop</SelectItem>
              <SelectItem value="SELF">Individual</SelectItem>
              <SelectItem value="OTHER">Other</SelectItem>
            </SelectContent>
          </Select>
          <Select value={statusFilter} onValueChange={v => { setStatusFilter(v); setPage(0); }}>
            <SelectTrigger className="h-11 w-40 rounded-xl">
              <SelectValue placeholder="Status" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="all">All Status</SelectItem>
              <SelectItem value="approved">Approved</SelectItem>
              <SelectItem value="pending">Pending</SelectItem>
              <SelectItem value="not_approved">Not Approved</SelectItem>
            </SelectContent>
          </Select>
        </div>

        {loading ? (
          <TableSkeleton />
        ) : filtered.length === 0 ? (
          <EmptyState title="No customers found" description="Adjust the search or clear the filters." />
        ) : (
          <div className="overflow-x-auto">
            <Table>
              <TableHeader>
                <TableRow>
                  <TableHead>Customer</TableHead>
                  <TableHead>Category</TableHead>
                  <TableHead>City</TableHead>
                  <TableHead>Status</TableHead>
                  <TableHead>Joined</TableHead>
                  <TableHead className="text-right">Actions</TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                {filtered.map((r: any) => (
                  <TableRow key={r.id} className="transition-colors hover:bg-muted/50">
                    <TableCell>
                      <div className="flex items-center gap-3">
                        <div className="flex h-8 w-8 shrink-0 items-center justify-center rounded-full bg-primary-soft text-xs font-bold text-primary">
                          {(r.name ?? "C")[0].toUpperCase()}
                        </div>
                        <div className="min-w-0">
                          <Link href={`/customers/${r.id}`} className="truncate font-medium hover:text-primary hover:underline">
                            {stripHtml(r.name)}
                          </Link>
                          <p className="text-xs text-muted-foreground">{r.customerType ?? "Other"}</p>
                        </div>
                      </div>
                    </TableCell>
                    <TableCell><CategoryBadge cat={r.category} /></TableCell>
                    <TableCell>{stripHtml(r.city?.name) || "—"}</TableCell>
                    <TableCell><ApprovalBadge status={r.approvalStatus ?? ""} /></TableCell>
                    <TableCell className="text-sm text-muted-foreground">
                      {r.createdAt ? new Date(r.createdAt).toLocaleDateString("en-PK") : "—"}
                    </TableCell>
                    <TableCell className="text-right">
                      <DropdownMenu>
                        <DropdownMenuTrigger asChild>
                          <Button variant="ghost" size="icon" className="h-9 w-9 rounded-lg">
                            <ChevronDown className="h-4 w-4" />
                          </Button>
                        </DropdownMenuTrigger>
                        <DropdownMenuContent align="end" className="rounded-xl">
                          <DropdownMenuItem asChild>
                            <Link href={`/customers/${r.id}`}>
                              <Eye className="mr-2 h-4 w-4" /> View
                            </Link>
                          </DropdownMenuItem>
                          <DropdownMenuItem asChild>
                            <Link href={`/customers/${r.id}/edit`}>
                              <Pencil className="mr-2 h-4 w-4" /> Edit
                            </Link>
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

        <div className="mt-5 flex items-center justify-between border-t border-border pt-4">
          <p className="text-xs text-muted-foreground">
            Showing {page * PER_PAGE + 1}–{Math.min((page + 1) * PER_PAGE, total)} of {total.toLocaleString()} customers
          </p>
          <div className="flex items-center gap-1 text-xs text-muted-foreground">
            <Button variant="ghost" size="sm" className="rounded-lg" onClick={() => setPage(p => Math.max(0, p - 1))} disabled={page === 0}>
              ← Prev
            </Button>
            <span className="rounded-lg bg-primary px-2.5 py-1 font-medium text-primary-foreground">{page + 1}</span>
            <span className="px-1">of {totalPages}</span>
            <Button variant="ghost" size="sm" className="rounded-lg" onClick={() => setPage(p => Math.min(totalPages - 1, p + 1))} disabled={page >= totalPages - 1}>
              Next →
            </Button>
          </div>
        </div>
      </SectionCard>
    </div>
  );
}
