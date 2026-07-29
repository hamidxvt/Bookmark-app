"use client";

import { useEffect, useState } from "react";
import { RefreshCw, Clock, MapPin, CheckCircle, XCircle, AlertCircle } from "lucide-react";
import { format } from "date-fns";

interface AttendanceRecord {
  id: number;
  date: string;
  startAt: string | null;
  endAt: string | null;
  startLat: number | null;
  startLng: number | null;
  status: string;
  cannotReason: string | null;
  booker: { id: number; name: string; email: string; phone: string };
}

function duration(start: string, end: string | null) {
  if (!end) return "Active";
  const ms = new Date(end).getTime() - new Date(start).getTime();
  const h = Math.floor(ms / 3600000);
  const m = Math.floor((ms % 3600000) / 60000);
  return `${h}h ${m}m`;
}

const STATUS_STYLES: Record<string, { color: string; icon: typeof CheckCircle; label: string }> = {
  present: { color: "bg-emerald-100 text-emerald-700", icon: CheckCircle, label: "Present" },
  absent: { color: "bg-red-100 text-red-700", icon: XCircle, label: "Absent" },
  cannot_work: { color: "bg-amber-100 text-amber-700", icon: AlertCircle, label: "Cannot Work" },
};

export default function AttendanceClient() {
  const [records, setRecords] = useState<AttendanceRecord[]>([]);
  const [date, setDate] = useState(format(new Date(), "yyyy-MM-dd"));
  const [loading, setLoading] = useState(true);

  async function load() {
    setLoading(true);
    try {
      const res = await fetch(`/api/v1/attendance?date=${date}`).then(r => r.json());
      if (res.success) setRecords(res.data);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => { load(); }, [date]);

  const presentCount = records.filter(r => r.status === "present").length;
  const absentCount = records.filter(r => r.status === "absent").length;
  const cannotWorkCount = records.filter(r => r.status === "cannot_work").length;

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between flex-wrap gap-3">
        <div>
          <h2 className="text-xl font-bold text-slate-900">Attendance</h2>
          <p className="text-sm text-slate-500 mt-0.5">Daily check-in/check-out log for all bookers</p>
        </div>
        <div className="flex items-center gap-2">
          <input
            type="date"
            value={date}
            onChange={e => setDate(e.target.value)}
            className="rounded-lg border border-slate-200 px-3 py-2 text-sm text-slate-800 focus:outline-none focus:ring-2 focus:ring-teal-500"
          />
          <button onClick={load} disabled={loading}
            className="flex items-center gap-1.5 rounded-lg border border-slate-200 bg-white px-3 py-2 text-xs font-medium text-slate-600 hover:bg-slate-50 transition-colors">
            <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} />
          </button>
        </div>
      </div>

      {/* Summary Cards */}
      <div className="grid grid-cols-3 gap-4">
        {[
          { label: "Present", count: presentCount, color: "text-emerald-600 bg-emerald-50 border-emerald-100" },
          { label: "Absent", count: absentCount, color: "text-red-600 bg-red-50 border-red-100" },
          { label: "Cannot Work", count: cannotWorkCount, color: "text-amber-600 bg-amber-50 border-amber-100" },
        ].map(s => (
          <div key={s.label} className={`rounded-2xl border p-4 ${s.color}`}>
            <p className="text-2xl font-bold tabular-nums">{s.count}</p>
            <p className="text-xs font-medium mt-0.5 opacity-80">{s.label}</p>
          </div>
        ))}
      </div>

      {/* Records */}
      <div className="rounded-2xl border border-slate-200 bg-white shadow-xs overflow-hidden">
        <div className="px-6 py-4 border-b border-slate-100">
          <h3 className="text-sm font-semibold text-slate-800">
            {format(new Date(date + "T00:00:00"), "EEEE, MMMM d, yyyy")}
          </h3>
        </div>
        {loading ? (
          <div className="divide-y divide-slate-50">
            {[...Array(5)].map((_, i) => (
              <div key={i} className="flex items-center gap-4 px-6 py-4">
                <div className="h-9 w-9 rounded-full bg-slate-100 animate-pulse" />
                <div className="flex-1 space-y-2">
                  <div className="h-3 w-40 rounded bg-slate-100 animate-pulse" />
                  <div className="h-2.5 w-28 rounded bg-slate-100 animate-pulse" />
                </div>
              </div>
            ))}
          </div>
        ) : records.length === 0 ? (
          <div className="py-16 text-center">
            <Clock className="mx-auto h-10 w-10 text-slate-200" />
            <p className="mt-3 text-sm text-slate-400">No attendance records for this date</p>
          </div>
        ) : (
          <div className="divide-y divide-slate-50">
            {records.map(r => {
              const s = STATUS_STYLES[r.status] ?? STATUS_STYLES.absent;
              const Icon = s.icon;
              return (
                <div key={r.id} className="flex items-center gap-4 px-6 py-3.5 hover:bg-slate-50/50 transition-colors">
                  <div className="flex h-9 w-9 shrink-0 items-center justify-center rounded-full bg-slate-100 text-sm font-bold text-slate-600">
                    {r.booker.name[0]}
                  </div>
                  <div className="flex-1 min-w-0">
                    <div className="flex items-center gap-2">
                      <p className="text-sm font-semibold text-slate-800">{r.booker.name}</p>
                      <span className={`inline-flex items-center gap-1 rounded-full px-2 py-0.5 text-xs font-medium ${s.color}`}>
                        <Icon className="h-3 w-3" />
                        {s.label}
                      </span>
                    </div>
                    {r.cannotReason && (
                      <p className="text-xs text-amber-600 mt-0.5">{r.cannotReason}</p>
                    )}
                  </div>
                  <div className="text-right shrink-0 space-y-0.5">
                    {r.startAt && (
                      <div className="flex items-center justify-end gap-1 text-xs text-slate-500">
                        <Clock className="h-3 w-3 text-emerald-500" />
                        <span>In: {format(new Date(r.startAt), "h:mm a")}</span>
                      </div>
                    )}
                    {r.endAt && (
                      <div className="flex items-center justify-end gap-1 text-xs text-slate-500">
                        <Clock className="h-3 w-3 text-slate-400" />
                        <span>Out: {format(new Date(r.endAt), "h:mm a")}</span>
                      </div>
                    )}
                    {r.startAt && (
                      <p className="text-xs text-slate-400">
                        {duration(r.startAt, r.endAt)}
                      </p>
                    )}
                    {r.startLat && (
                      <div className="flex items-center justify-end gap-1 text-xs text-slate-400">
                        <MapPin className="h-3 w-3" />
                        <a
                          href={`https://maps.google.com/?q=${r.startLat},${r.startLng}`}
                          target="_blank"
                          rel="noreferrer"
                          className="hover:text-teal-600 underline"
                        >
                          View Map
                        </a>
                      </div>
                    )}
                  </div>
                </div>
              );
            })}
          </div>
        )}
      </div>
    </div>
  );
}
