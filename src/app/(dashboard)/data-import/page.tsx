"use client";

import { useEffect, useState } from "react";
import { Database, Package, Users, CheckCircle, XCircle, Loader2, RefreshCw } from "lucide-react";

interface SeedResult {
  created: number;
  updated: number;
  skipped: number;
  total: number;
}

interface DbStats {
  customers: number;
  products: number;
}

export default function DataImportPage() {
  const [stats, setStats]     = useState<DbStats | null>(null);
  const [loading, setLoading] = useState<string | null>(null);
  const [results, setResults] = useState<Record<string, SeedResult | string>>({});

  async function fetchStats() {
    const res = await fetch("/api/v1/seed-data").then(r => r.json());
    if (res.success) setStats(res.data);
  }

  useEffect(() => { fetchStats(); }, []);

  async function runSeed(type: "customers" | "products" | "all") {
    if (!confirm(`Import ${type === "all" ? "all data" : type} from XLSX files? This may take 1-2 minutes.`)) return;
    setLoading(type);
    try {
      const res = await fetch(`/api/v1/seed-data?type=${type}`, { method: "POST" }).then(r => r.json());
      if (res.success) {
        setResults(prev => ({ ...prev, ...res.data }));
        fetchStats();
      } else {
        setResults(prev => ({ ...prev, [type]: `Error: ${res.error}` }));
      }
    } catch (e: any) {
      setResults(prev => ({ ...prev, [type]: `Error: ${e.message}` }));
    } finally {
      setLoading(null);
    }
  }

  const JOBS = [
    {
      key: "customers" as const,
      label: "Import Customers",
      description: "7,406 customers from Master Data Final.xlsx — Schools (A+/A/B), Booksellers, Distributors",
      icon: Users,
      count: stats?.customers,
      countLabel: "in database",
    },
    {
      key: "products" as const,
      label: "Import Products",
      description: "461 products from Master Product Final.xlsx — all brands, subjects, and series",
      icon: Package,
      count: stats?.products,
      countLabel: "in database",
    },
    {
      key: "all" as const,
      label: "Import Everything",
      description: "Run all imports at once (customers + products). Safe to re-run — updates existing records.",
      icon: Database,
      accent: true,
    },
  ];

  return (
    <div className="space-y-6 max-w-3xl px-6 py-6 lg:px-8">
      {/* Header */}
      <div>
        <div className="flex items-center gap-2.5 mb-1">
          <div className="flex h-8 w-8 items-center justify-center rounded-lg bg-primary">
            <Database className="h-4 w-4 text-primary-foreground" />
          </div>
          <h1 className="text-xl font-bold text-foreground">Data Import</h1>
        </div>
        <p className="text-sm text-muted-foreground ml-10.5">
          Import master data from XLSX files into the database. All imports are idempotent — re-running updates existing records.
        </p>
      </div>

      {/* Category mapping note */}
      <div className="rounded-xl border border-info/25 bg-info/10 px-4 py-3 text-sm text-info-foreground">
        <strong className="font-semibold">Category mapping applied:</strong>
        <span className="ml-2">TYPE - A → <strong>A+</strong> · TYPE - B → <strong>A</strong> · TYPE - C → <strong>B</strong> · BOOKSHOPS → <strong>BOOKSHOPS</strong></span>
      </div>

      {/* DB Stats */}
      {stats && (
        <div className="grid grid-cols-2 gap-4">
          {[
            { label: "Customers in DB", value: stats.customers, icon: Users },
            { label: "Products in DB",  value: stats.products,  icon: Package },
          ].map(s => (
            <div key={s.label} className="surface p-4 flex items-center gap-3">
              <div className="flex h-9 w-9 items-center justify-center rounded-xl bg-primary-soft">
                <s.icon className="h-4.5 w-4.5 text-primary" />
              </div>
              <div>
                <p className="text-2xl font-bold text-foreground tabular-nums">{s.value.toLocaleString()}</p>
                <p className="text-xs text-muted-foreground">{s.label}</p>
              </div>
            </div>
          ))}
        </div>
      )}

      {/* Import jobs */}
      <div className="space-y-3">
        {JOBS.map(({ key, label, description, icon: Icon, count, countLabel, accent }) => {
          const result = results[key];
          const isLoading = loading === key;
          return (
            <div
              key={key}
              className={`rounded-xl border p-5 ${accent ? "bg-muted border-border" : "surface border-transparent"}`}
            >
              <div className="flex items-start justify-between gap-4">
                <div className="flex-1 min-w-0">
                  <div className="flex items-center gap-2 mb-1">
                    <div className={`flex h-7 w-7 items-center justify-center rounded-lg ${accent ? "bg-primary" : "bg-muted"}`}>
                      <Icon className={`h-3.5 w-3.5 ${accent ? "text-primary-foreground" : "text-muted-foreground"}`} />
                    </div>
                    <p className="font-semibold text-foreground">{label}</p>
                    {count !== undefined && (
                      <span className="text-xs bg-success/15 text-success px-2 py-0.5 rounded-full font-medium">
                        {count.toLocaleString()} {countLabel}
                      </span>
                    )}
                  </div>
                  <p className="text-sm text-muted-foreground ml-9">{description}</p>

                  {/* Result */}
                  {result && typeof result === "object" && (
                    <div className="mt-3 ml-9 flex items-center gap-2">
                      <CheckCircle className="h-4 w-4 text-success shrink-0" />
                      <span className="text-sm text-success font-medium">
                        {result.created} created · {result.updated} updated · {result.skipped} skipped
                        <span className="text-muted-foreground ml-1">(of {result.total} total)</span>
                      </span>
                    </div>
                  )}
                  {result && typeof result === "string" && (
                    <div className="mt-3 ml-9 flex items-center gap-2">
                      <XCircle className="h-4 w-4 text-destructive shrink-0" />
                      <span className="text-sm text-destructive">{result}</span>
                    </div>
                  )}
                </div>

                <button
                  onClick={() => runSeed(key)}
                  disabled={loading !== null}
                  className={`flex items-center gap-2 rounded-xl px-4 py-2.5 text-sm font-semibold transition-colors disabled:opacity-50 shrink-0 ${
                    accent ? "bg-primary text-primary-foreground hover:opacity-90" : "bg-navy text-navy-foreground hover:opacity-90"
                  }`}
                >
                  {isLoading ? (
                    <><Loader2 className="h-4 w-4 animate-spin" /> Importing…</>
                  ) : (
                    <><RefreshCw className="h-4 w-4" /> Run Import</>
                  )}
                </button>
              </div>
            </div>
          );
        })}
      </div>

      {/* Warning */}
      <div className="rounded-xl border border-warning/30 bg-warning/15 px-4 py-3 text-xs text-warning-foreground">
        <strong>Note:</strong> The first import may take 2–5 minutes due to the large dataset.
        The page will show results when complete. Do not close the tab while importing.
      </div>
    </div>
  );
}
