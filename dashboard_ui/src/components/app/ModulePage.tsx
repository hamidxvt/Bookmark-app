import { Download, Plus, Search } from "lucide-react";
import { useMemo, useState, type ReactNode } from "react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { EmptyState, SectionCard, StatCard, StatusPill, TableSkeleton } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";

export type Column = { key: string; label: string; status?: boolean };

export function ModulePage({
  title,
  subtitle,
  stats,
  columns,
  rows,
  primaryAction,
  extra,
}: {
  title: string;
  subtitle: string;
  stats: { label: string; value: string; icon: ReactNode }[];
  columns: Column[];
  rows: Record<string, string | number>[];
  primaryAction?: { label: string; onClick: () => void };
  extra?: ReactNode;
}) {
  const [query, setQuery] = useState("");
  const [loading, setLoading] = useState(false);

  const filtered = useMemo(() => {
    const q = query.trim().toLowerCase();
    if (!q) return rows;
    return rows.filter((r) => Object.values(r).some((v) => String(v).toLowerCase().includes(q)));
  }, [rows, query]);

  const refresh = () => {
    setLoading(true);
    setTimeout(() => setLoading(false), 550);
  };

  return (
    <AppShell title={title} subtitle={subtitle} onRefresh={refresh}>
      <div className="space-y-6">
        {stats.length > 0 && (
          <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-4">
            {stats.map((s) => (
              <StatCard key={s.label} label={s.label} value={s.value} icon={s.icon} onClick={refresh} />
            ))}
          </div>
        )}

        {extra}

        <SectionCard
          title={title}
          description={`${filtered.length} records`}
          action={
            <div className="flex gap-2">
              <Button
                variant="outline"
                className="rounded-xl"
                onClick={() => toast.success("Export started", { description: `${title} will be emailed as CSV.` })}
              >
                <Download className="mr-2 h-4 w-4" /> Export
              </Button>
              {primaryAction ? (
                <Button className="rounded-xl" onClick={primaryAction.onClick}>
                  <Plus className="mr-2 h-4 w-4" /> {primaryAction.label}
                </Button>
              ) : null}
            </div>
          }
        >
          <div className="relative mb-5 max-w-sm">
            <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
            <Input
              value={query}
              onChange={(e) => setQuery(e.target.value)}
              placeholder="Search records"
              className="h-11 rounded-xl pl-9"
            />
          </div>

          {loading ? (
            <TableSkeleton />
          ) : filtered.length === 0 ? (
            <EmptyState title="Nothing to show" description="No records match your search." />
          ) : (
            <div className="overflow-x-auto">
              <Table>
                <TableHeader>
                  <TableRow>
                    {columns.map((c) => (
                      <TableHead key={c.key}>{c.label}</TableHead>
                    ))}
                  </TableRow>
                </TableHeader>
                <TableBody>
                  {filtered.map((r, i) => (
                    <TableRow key={i} className="transition-colors hover:bg-muted/50">
                      {columns.map((c) => (
                        <TableCell key={c.key} className={c.key === columns[0].key ? "font-medium" : undefined}>
                          {c.status ? <StatusPill value={String(r[c.key])} /> : String(r[c.key])}
                        </TableCell>
                      ))}
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </div>
          )}
        </SectionCard>
      </div>
    </AppShell>
  );
}
