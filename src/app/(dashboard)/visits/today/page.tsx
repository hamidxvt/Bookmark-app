import { getVisitStatusBadge } from "@/lib/utils";

export const metadata = { title: "Today's Visits — FFM" };

const PRIORITY_COLORS: Record<string, string> = {
  High: "text-red-600 bg-red-50 border-red-200",
  Medium: "text-amber-600 bg-amber-50 border-amber-200",
  Low: "text-slate-500 bg-slate-50 border-slate-200",
};

const TODAYS_VISITS = [
  { id: 1, customer: "City School DHA", booker: "Ahmed Raza", time: "09:00 AM", priority: "High", status: "PENDING" },
  { id: 2, customer: "Karachi Grammar School", booker: "Ahmed Raza", time: "11:30 AM", priority: "Medium", status: "COMPLETED" },
  { id: 3, customer: "Al Barkat Books", booker: "Ali Hassan", time: "02:00 PM", priority: "Low", status: "PENDING" },
  { id: 4, customer: "Beaconhouse Gulshan", booker: "Sara Malik", time: "03:30 PM", priority: "High", status: "PENDING" },
  { id: 5, customer: "The City School PECHS", booker: "Usman Khan", time: "04:45 PM", priority: "Medium", status: "CANCELLED" },
];

export default function TodaysVisitsPage() {
  const today = new Date().toLocaleDateString("en-PK", {
    weekday: "long", year: "numeric", month: "long", day: "numeric",
  });

  const completed = TODAYS_VISITS.filter(v => v.status === "COMPLETED").length;
  const pending = TODAYS_VISITS.filter(v => v.status === "PENDING").length;

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-xl font-bold text-slate-900">Today's Visits</h1>
        <p className="text-sm text-slate-500 mt-0.5">{today}</p>
      </div>

      <div className="grid grid-cols-3 gap-4">
        {[
          { label: "Total Scheduled", value: TODAYS_VISITS.length, color: "text-blue-700" },
          { label: "Completed", value: completed, color: "text-emerald-700" },
          { label: "Pending", value: pending, color: "text-amber-700" },
        ].map(s => (
          <div key={s.label} className="rounded-2xl bg-white border border-slate-200 p-4 shadow-xs">
            <p className={`text-2xl font-bold tabular-nums ${s.color}`}>{s.value}</p>
            <p className="text-xs text-slate-500 mt-0.5">{s.label}</p>
          </div>
        ))}
      </div>

      <div className="rounded-2xl bg-white border border-slate-200 shadow-xs overflow-hidden">
        <div className="flex items-center justify-between px-6 py-4 border-b border-slate-100">
          <h2 className="text-sm font-semibold text-slate-800">Scheduled for Today ({TODAYS_VISITS.length})</h2>
        </div>
        <div className="overflow-x-auto">
          <table className="w-full">
            <thead>
              <tr className="border-b border-slate-100 bg-slate-50/50">
                {["#", "Customer", "Booker", "Time", "Priority", "Status"].map(h => (
                  <th key={h} className="px-6 py-3.5 text-left text-xs font-semibold uppercase tracking-wide text-slate-400">{h}</th>
                ))}
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-50">
              {TODAYS_VISITS.map((v, i) => (
                <tr key={v.id} className="hover:bg-slate-50/70 transition-colors">
                  <td className="px-6 py-4 text-sm text-slate-400 tabular-nums">{i + 1}</td>
                  <td className="px-6 py-4 text-sm font-semibold text-slate-800">{v.customer}</td>
                  <td className="px-6 py-4 text-sm text-slate-600">{v.booker}</td>
                  <td className="px-6 py-4 text-sm text-slate-600 tabular-nums">{v.time}</td>
                  <td className="px-6 py-4">
                    <span className={`inline-flex rounded-full border px-2.5 py-1 text-xs font-medium ${PRIORITY_COLORS[v.priority]}`}>
                      {v.priority}
                    </span>
                  </td>
                  <td className="px-6 py-4">
                    <span className={`inline-flex rounded-full px-2.5 py-1 text-xs font-medium ${getVisitStatusBadge(v.status)}`}>
                      {v.status.charAt(0) + v.status.slice(1).toLowerCase()}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
