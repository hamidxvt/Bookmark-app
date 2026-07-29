"use client";

import { useEffect, useState } from "react";
import { DollarSign, TrendingUp, TrendingDown, RefreshCw, Download, Calculator } from "lucide-react";

const BACKEND_URL = process.env.NEXT_PUBLIC_API_URL ?? "https://bookmark-api.up.railway.app";

interface PayrollRecord {
  bookerId: number;
  bookerName: string;
  presentDays: number;
  basicSalary: number;
  dailyRate: number;
  performanceEarned: number;
  missedVisitPenalty: number;
  sampleDeduction: number;
  securityDepositHeld: number;
  netPayable: number;
  rejectedMissedVisits: number;
}

function pkr(n: number) {
  return `PKR ${Math.round(n).toLocaleString()}`;
}

export default function PayrollClient() {
  const [records, setRecords] = useState<PayrollRecord[]>([]);
  const [loading, setLoading] = useState(true);
  const [finalizing, setFinalizing] = useState(false);
  const now = new Date();
  const [month, setMonth] = useState(now.getMonth() + 1);
  const [year, setYear] = useState(now.getFullYear());

  async function load() {
    setLoading(true);
    try {
      const res = await fetch(
        `${BACKEND_URL}/api/v1/payroll?month=${month}&year=${year}`,
        { headers: { Authorization: `Bearer ${localStorage.getItem("admin_token") ?? ""}` } }
      ).then(r => r.json());
      if (res.success) setRecords(res.data ?? []);
    } catch (e) {
      console.error("[Payroll]", e);
    } finally {
      setLoading(false);
    }
  }

  async function finalize() {
    if (!confirm(`Finalize payroll for ${month}/${year}? This will lock the calculations.`)) return;
    setFinalizing(true);
    try {
      await fetch(`${BACKEND_URL}/api/v1/payroll/finalize`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("admin_token") ?? ""}`,
        },
        body: JSON.stringify({ month, year }),
      });
      await load();
    } finally {
      setFinalizing(false);
    }
  }

  useEffect(() => { load(); }, [month, year]);

  const totalNet = records.reduce((s, r) => s + r.netPayable, 0);
  const totalPenalties = records.reduce((s, r) => s + r.missedVisitPenalty + r.sampleDeduction, 0);

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between flex-wrap gap-3">
        <div>
          <h2 className="text-xl font-bold text-slate-900">Payroll</h2>
          <p className="text-sm text-slate-500 mt-0.5">Monthly salary calculation — 3-part structure</p>
        </div>
        <div className="flex items-center gap-2">
          <select
            value={month}
            onChange={e => setMonth(parseInt(e.target.value))}
            className="rounded-lg border border-slate-200 px-3 py-2 text-sm text-slate-800 focus:outline-none focus:ring-2 focus:ring-teal-500"
          >
            {Array.from({ length: 12 }, (_, i) => (
              <option key={i + 1} value={i + 1}>
                {new Date(2026, i).toLocaleString("default", { month: "long" })}
              </option>
            ))}
          </select>
          <input
            type="number"
            value={year}
            onChange={e => setYear(parseInt(e.target.value))}
            className="w-24 rounded-lg border border-slate-200 px-3 py-2 text-sm text-slate-800 focus:outline-none focus:ring-2 focus:ring-teal-500"
          />
          <button onClick={load} disabled={loading}
            className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50 transition-colors">
            <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} />
          </button>
          <button onClick={finalize} disabled={finalizing || loading}
            className="flex items-center gap-2 rounded-lg bg-teal-600 px-4 py-2 text-xs font-medium text-white hover:bg-teal-700 transition-colors disabled:opacity-50">
            <Calculator className="h-3.5 w-3.5" />
            {finalizing ? "Finalizing..." : "Finalize & Save"}
          </button>
        </div>
      </div>

      {/* Summary */}
      <div className="grid grid-cols-1 gap-4 sm:grid-cols-3">
        <div className="rounded-2xl border border-slate-200 bg-white p-5">
          <div className="flex items-center gap-3 mb-3">
            <div className="flex h-9 w-9 items-center justify-center rounded-xl bg-teal-100">
              <DollarSign className="h-4.5 w-4.5 text-teal-600" />
            </div>
            <p className="text-sm text-slate-500">Total Net Payable</p>
          </div>
          <p className="text-2xl font-bold text-slate-900">{pkr(totalNet)}</p>
          <p className="text-xs text-slate-400 mt-1">For {records.length} bookers</p>
        </div>
        <div className="rounded-2xl border border-slate-200 bg-white p-5">
          <div className="flex items-center gap-3 mb-3">
            <div className="flex h-9 w-9 items-center justify-center rounded-xl bg-red-100">
              <TrendingDown className="h-4.5 w-4.5 text-red-600" />
            </div>
            <p className="text-sm text-slate-500">Total Deductions</p>
          </div>
          <p className="text-2xl font-bold text-red-600">{pkr(totalPenalties)}</p>
          <p className="text-xs text-slate-400 mt-1">Missed visits + samples</p>
        </div>
        <div className="rounded-2xl border border-slate-200 bg-white p-5">
          <div className="flex items-center gap-3 mb-3">
            <div className="flex h-9 w-9 items-center justify-center rounded-xl bg-emerald-100">
              <TrendingUp className="h-4.5 w-4.5 text-emerald-600" />
            </div>
            <p className="text-sm text-slate-500">Avg Performance</p>
          </div>
          <p className="text-2xl font-bold text-emerald-600">
            {records.length > 0 ? pkr(records.reduce((s, r) => s + r.performanceEarned, 0) / records.length) : "—"}
          </p>
          <p className="text-xs text-slate-400 mt-1">Per booker this month</p>
        </div>
      </div>

      {/* Table */}
      <div className="rounded-2xl border border-slate-200 bg-white shadow-xs overflow-hidden">
        <div className="overflow-x-auto">
          <table className="w-full text-sm">
            <thead className="bg-slate-50 border-b border-slate-100">
              <tr>
                {["Booker", "Present Days", "Basic Salary", "Performance", "Penalties", "Security Dep.", "Net Payable"].map(h => (
                  <th key={h} className="px-4 py-3 text-left text-xs font-semibold text-slate-500 uppercase tracking-wide whitespace-nowrap">
                    {h}
                  </th>
                ))}
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-50">
              {loading ? (
                [...Array(5)].map((_, i) => (
                  <tr key={i}>
                    {Array(7).fill(0).map((_, j) => (
                      <td key={j} className="px-4 py-3">
                        <div className="h-3 w-20 rounded bg-slate-100 animate-pulse" />
                      </td>
                    ))}
                  </tr>
                ))
              ) : records.length === 0 ? (
                <tr>
                  <td colSpan={7} className="px-4 py-16 text-center text-sm text-slate-400">
                    No payroll data for {month}/{year}. Click "Finalize & Save" to calculate.
                  </td>
                </tr>
              ) : (
                records.map(r => (
                  <tr key={r.bookerId} className="hover:bg-slate-50/50 transition-colors">
                    <td className="px-4 py-3">
                      <div className="flex items-center gap-2">
                        <div className="flex h-7 w-7 shrink-0 items-center justify-center rounded-full bg-teal-100 text-xs font-bold text-teal-700">
                          {r.bookerName[0]}
                        </div>
                        <span className="font-medium text-slate-800">{r.bookerName}</span>
                      </div>
                    </td>
                    <td className="px-4 py-3 text-slate-600">{r.presentDays} days</td>
                    <td className="px-4 py-3 text-slate-600">{pkr(r.basicSalary)}</td>
                    <td className="px-4 py-3">
                      <span className="text-emerald-700 font-medium">{pkr(r.performanceEarned)}</span>
                      <p className="text-xs text-slate-400">{r.presentDays} × {pkr(r.dailyRate)}</p>
                    </td>
                    <td className="px-4 py-3">
                      {r.missedVisitPenalty + r.sampleDeduction > 0 ? (
                        <div>
                          <span className="text-red-600 font-medium">{pkr(r.missedVisitPenalty + r.sampleDeduction)}</span>
                          <p className="text-xs text-slate-400">
                            {r.rejectedMissedVisits > 0 && `${r.rejectedMissedVisits} missed visits`}
                            {r.sampleDeduction > 0 && ` · Samples`}
                          </p>
                        </div>
                      ) : (
                        <span className="text-slate-400">—</span>
                      )}
                    </td>
                    <td className="px-4 py-3 text-slate-500">{pkr(r.securityDepositHeld)}</td>
                    <td className="px-4 py-3">
                      <span className={`font-bold ${r.netPayable >= 0 ? "text-slate-900" : "text-red-600"}`}>
                        {pkr(r.netPayable)}
                      </span>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
