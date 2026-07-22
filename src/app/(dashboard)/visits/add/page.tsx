"use client";

import { useState } from "react";
import { CalendarPlus, Loader2 } from "lucide-react";

const inputCls = "w-full rounded-xl border border-slate-200 bg-white px-4 py-2.5 text-sm text-slate-900 placeholder:text-slate-400 focus:outline-none focus:ring-2 focus:ring-sky-500/20 focus:border-sky-500 transition";
const labelCls = "block text-xs font-semibold text-slate-700 mb-1.5";

const BOOKERS = [
  { id: 1, name: "Ahmed Raza" },
  { id: 2, name: "Sara Malik" },
  { id: 3, name: "Ali Hassan" },
  { id: 4, name: "Usman Khan" },
];

const CUSTOMERS = [
  { id: 1, name: "City School DHA" },
  { id: 2, name: "Karachi Grammar School" },
  { id: 3, name: "Al Barkat Books" },
  { id: 4, name: "LGS Gulberg" },
  { id: 5, name: "DPS Karachi" },
];

export default function ScheduleVisitPage() {
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    setLoading(true);
    setTimeout(() => { setLoading(false); setSuccess(true); }, 1000);
  }

  return (
    <div className="space-y-6 max-w-2xl">
      <div className="flex items-center gap-2.5">
        <div className="flex h-8 w-8 items-center justify-center rounded-lg bg-sky-100">
          <CalendarPlus className="h-4 w-4 text-sky-600" />
        </div>
        <div>
          <h1 className="text-xl font-bold text-slate-900">Schedule Visit</h1>
          <p className="text-xs text-slate-500">Assign a booker to visit a customer</p>
        </div>
      </div>

      {success && (
        <div className="rounded-xl border border-emerald-200 bg-emerald-50 px-4 py-3.5 text-sm text-emerald-700 font-medium">
          Visit scheduled successfully!
        </div>
      )}

      <div className="rounded-2xl bg-white border border-slate-200 shadow-xs p-6">
        <form onSubmit={handleSubmit} className="space-y-5">
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-5">
            <div>
              <label className={labelCls}>Booker</label>
              <select name="booker_id" required className={inputCls}>
                <option value="">Select booker</option>
                {BOOKERS.map(b => <option key={b.id} value={b.id}>{b.name}</option>)}
              </select>
            </div>
            <div>
              <label className={labelCls}>Customer</label>
              <select name="customer_id" required className={inputCls}>
                <option value="">Select customer</option>
                {CUSTOMERS.map(c => <option key={c.id} value={c.id}>{c.name}</option>)}
              </select>
            </div>
          </div>
          <div className="grid grid-cols-1 sm:grid-cols-2 gap-5">
            <div>
              <label className={labelCls}>Visit Date</label>
              <input type="date" name="date" required className={inputCls} />
            </div>
            <div>
              <label className={labelCls}>Priority</label>
              <select name="priority" required className={inputCls}>
                <option value="">Select priority</option>
                <option value="High">High</option>
                <option value="Medium">Medium</option>
                <option value="Low">Low</option>
              </select>
            </div>
          </div>
          <div>
            <label className={labelCls}>Special Instructions</label>
            <textarea name="special_instruction" rows={4} placeholder="Any notes or instructions for this visit…" className={`${inputCls} resize-none`} />
          </div>
          <button type="submit" disabled={loading} className="w-full flex items-center justify-center gap-2 rounded-xl bg-[#0a1628] py-3 text-sm font-semibold text-white hover:bg-slate-800 transition disabled:opacity-60 cursor-pointer">
            {loading ? <><Loader2 className="h-4 w-4 animate-spin" /> Scheduling…</> : "Schedule Visit"}
          </button>
        </form>
      </div>
    </div>
  );
}
