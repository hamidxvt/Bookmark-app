"use client";

import { useEffect, useState } from "react";
import Link from "next/link";
import { Plus, Search, RefreshCw, Package } from "lucide-react";

function stripHtml(s: string) {
  return (s ?? "").replace(/<[^>]+>/g, " ").replace(/\s+/g, " ").trim();
}

export default function ProductsClient() {
  const [rows, setRows] = useState<any[]>([]);
  const [total, setTotal] = useState(0);
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState("");

  async function load() {
    setLoading(true);
    try {
      const res = await fetch("/api/v1/products?type=products&length=200").then(r => r.json());
      if (res.success) {
        setRows(res.data?.data ?? []);
        setTotal(res.data?.recordsTotal ?? 0);
      }
    } catch (e) { console.error(e); }
    finally { setLoading(false); }
  }

  useEffect(() => { load(); }, []);

  const filtered = rows.filter(r => {
    const name = stripHtml(r[1] ?? "").toLowerCase();
    return !search || name.includes(search.toLowerCase());
  });

  return (
    <div className="space-y-4">
      <div className="flex flex-col gap-3 sm:flex-row sm:items-center sm:justify-between">
        <div className="relative flex-1 max-w-sm">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
          <input value={search} onChange={e => setSearch(e.target.value)} placeholder="Search products…"
            className="w-full rounded-lg border border-slate-200 bg-white pl-9 pr-4 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-teal-500/20 focus:border-teal-500 transition" />
        </div>
        <div className="flex gap-2">
          <button onClick={load} disabled={loading} className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50">
            <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} />
          </button>
          <Link href="/products/add" className="flex items-center gap-2 rounded-lg bg-[#0f1e3c] px-4 py-2 text-sm font-semibold text-white hover:bg-[#1a3060] transition-colors">
            <Plus className="h-4 w-4" /> Add Product
          </Link>
        </div>
      </div>

      <div className="rounded-2xl bg-white border border-slate-200 shadow-xs overflow-hidden">
        <table className="w-full text-sm">
          <thead>
            <tr className="border-b border-slate-100 bg-slate-50/50">
              {["#", "Product", "Brand", "Grade", "Subject", "Series"].map(h => (
                <th key={h} className="px-4 py-3 text-left text-xs font-semibold uppercase tracking-wide text-slate-500">{h}</th>
              ))}
            </tr>
          </thead>
          <tbody className="divide-y divide-slate-50">
            {loading && [...Array(8)].map((_, i) => (
              <tr key={i}>{[...Array(6)].map((_, j) => (
                <td key={j} className="px-4 py-3"><div className="h-4 rounded bg-slate-100 animate-pulse" style={{ width: j === 1 ? "180px" : "80px" }} /></td>
              ))}</tr>
            ))}
            {!loading && filtered.map((r: any, i: number) => {
              // DataTable columns: 0=index, 1=product HTML, 2=brand, 3=grade, 4=subject, 5=series, 6=action
              const productHtml = r[1] ?? "";
              const product = stripHtml(productHtml);
              const brand = stripHtml(r[2] ?? "");
              const grade = stripHtml(r[3] ?? "");
              const subject = stripHtml(r[4] ?? "");
              const series = stripHtml(r[5] ?? "");

              return (
                <tr key={i} className="hover:bg-slate-50/50 transition-colors">
                  <td className="px-4 py-3 text-slate-400 text-xs">{i + 1}</td>
                  <td className="px-4 py-3">
                    <div className="flex items-center gap-3">
                      <div className="flex h-8 w-8 shrink-0 items-center justify-center rounded-lg bg-amber-100">
                        <Package className="h-4 w-4 text-amber-600" />
                      </div>
                      <span className="text-xs font-medium text-slate-800 max-w-[200px] truncate" title={product}>{product}</span>
                    </div>
                  </td>
                  <td className="px-4 py-3 text-slate-600 text-xs">{brand || "—"}</td>
                  <td className="px-4 py-3 text-slate-600 text-xs">{grade || "—"}</td>
                  <td className="px-4 py-3 text-slate-600 text-xs">{subject || "—"}</td>
                  <td className="px-4 py-3 text-slate-500 text-xs">{series || "—"}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
        <div className="border-t border-slate-100 px-4 py-3">
          <p className="text-xs text-slate-500">Showing {filtered.length} of {total.toLocaleString()} products · Live data</p>
        </div>
      </div>
    </div>
  );
}
