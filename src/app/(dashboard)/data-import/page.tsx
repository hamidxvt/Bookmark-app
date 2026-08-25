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
    <div className="space-y-6 max-w-3xl">
      {/* Header */}
      <div>
        <div className="flex items-center gap-2.5 mb-1">
          <div className="flex h-8 w-8 items-center justify-center rounded-lg bg-[#C8102E]">
            <Database className="h-4 w-4 text-white" />
          </div>
          <h1 className="text-xl font-bold text-slate-900">Data Import</h1>
        </div>
        <p className="text-sm text-slate-500 ml-10.5">
          Import master data from XLSX files into the database. All imports are idempotent — re-running updates existing records.
        </p>
      </div>

      {/* Category mapping note */}
      <div className="rounded-xl border border-blue-200 bg-blue-50 px-4 py-3 text-sm text-blue-700">
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
            <div key={s.label} className="rounded-xl border border-slate-200 bg-white p-4 shadow-sm flex items-center gap-3">
              <div className="flex h-9 w-9 items-center justify-center rounded-xl bg-[#C8102E]/10">
                <s.icon className="h-4.5 w-4.5 text-[#C8102E]" />
              </div>
              <div>
                <p className="text-2xl font-bold text-slate-900 tabular-nums">{s.value.toLocaleString()}</p>
                <p className="text-xs text-slate-500">{s.label}</p>
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
              className={`rounded-xl border p-5 ${accent ? "bg-slate-50 border-slate-300" : "bg-white border-slate-200"} shadow-sm`}
            >
              <div className="flex items-start justify-between gap-4">
                <div className="flex-1 min-w-0">
                  <div className="flex items-center gap-2 mb-1">
                    <div className={`flex h-7 w-7 items-center justify-center rounded-lg ${accent ? "bg-[#C8102E]" : "bg-slate-100"}`}>
                      <Icon className={`h-3.5 w-3.5 ${accent ? "text-white" : "text-slate-600"}`} />
                    </div>
                    <p className="font-semibold text-slate-800">{label}</p>
                    {count !== undefined && (
                      <span className="text-xs bg-emerald-100 text-emerald-700 px-2 py-0.5 rounded-full font-medium">
                        {count.toLocaleString()} {countLabel}
                      </span>
                    )}
                  </div>
                  <p className="text-sm text-slate-500 ml-9">{description}</p>

                  {/* Result */}
                  {result && typeof result === "object" && (
                    <div className="mt-3 ml-9 flex items-center gap-2">
                      <CheckCircle className="h-4 w-4 text-emerald-500 shrink-0" />
                      <span className="text-sm text-emerald-700 font-medium">
                        {result.created} created · {result.updated} updated · {result.skipped} skipped
                        <span className="text-slate-400 ml-1">(of {result.total} total)</span>
                      </span>
                    </div>
                  )}
                  {result && typeof result === "string" && (
                    <div className="mt-3 ml-9 flex items-center gap-2">
                      <XCircle className="h-4 w-4 text-red-500 shrink-0" />
                      <span className="text-sm text-red-600">{result}</span>
                    </div>
                  )}
                </div>

                <button
                  onClick={() => runSeed(key)}
                  disabled={loading !== null}
                  className={`flex items-center gap-2 rounded-xl px-4 py-2.5 text-sm font-semibold text-white transition-colors disabled:opacity-50 shrink-0 ${
                    accent ? "bg-[#C8102E] hover:bg-red-700" : "bg-slate-700 hover:bg-slate-800"
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
      <div className="rounded-xl border border-amber-200 bg-amber-50 px-4 py-3 text-xs text-amber-700">
        <strong>Note:</strong> The first import may take 2–5 minutes due to the large dataset.
        The page will show results when complete. Do not close the tab while importing.
      </div>
    </div>
  );
}
