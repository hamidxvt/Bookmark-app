import { MapPin, Plus, Trash2, Building2 } from "lucide-react";

const CITIES = [
  { id: 1, name: "Karachi", zones: 5, customers: 2615, areas: 12, color: "from-sky-400 to-blue-500" },
  { id: 2, name: "Lahore", zones: 3, customers: 8, areas: 5, color: "from-violet-400 to-purple-500" },
  { id: 3, name: "Multan", zones: 2, customers: 4, areas: 3, color: "from-amber-400 to-orange-500" },
];

export default function CitiesPage() {
  return (
    <div className="space-y-6">
      {/* Page header */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <div className="flex items-center gap-2.5 mb-1">
            <div className="flex h-8 w-8 items-center justify-center rounded-lg bg-sky-100">
              <Building2 className="h-4 w-4 text-sky-600" />
            </div>
            <h1 className="text-xl font-bold text-slate-900">City Master Data</h1>
          </div>
          <p className="text-sm text-slate-500 ml-10.5">Manage cities, their zones and customer distribution</p>
        </div>
        <button className="flex items-center gap-2 rounded-xl bg-[#0a1628] px-4 py-2.5 text-sm font-semibold text-white hover:bg-slate-800 transition-colors shadow-sm cursor-pointer">
          <Plus className="h-4 w-4" />
          Add City
        </button>
      </div>

      {/* Stats strip */}
      <div className="grid grid-cols-3 gap-4">
        {[
          { label: "Total Cities", value: "3", icon: Building2, color: "text-sky-600 bg-sky-50" },
          { label: "Total Zones", value: "10", icon: MapPin, color: "text-violet-600 bg-violet-50" },
          { label: "Total Customers", value: "2,627", icon: MapPin, color: "text-emerald-600 bg-emerald-50" },
        ].map(s => (
          <div key={s.label} className="rounded-2xl bg-white border border-slate-200 p-4 shadow-xs">
            <div className={`flex h-8 w-8 items-center justify-center rounded-lg ${s.color} mb-3`}>
              <s.icon className="h-4 w-4" />
            </div>
            <p className="text-2xl font-bold text-slate-900 tabular-nums">{s.value}</p>
            <p className="text-xs text-slate-500 mt-0.5">{s.label}</p>
          </div>
        ))}
      </div>

      {/* Table */}
      <div className="rounded-2xl bg-white border border-slate-200 shadow-xs overflow-hidden">
        <div className="flex items-center justify-between px-6 py-4 border-b border-slate-100">
          <h2 className="text-sm font-semibold text-slate-800">All Cities</h2>
          <span className="text-xs text-slate-400">{CITIES.length} cities</span>
        </div>
        <div className="overflow-x-auto">
          <table className="w-full">
            <thead>
              <tr className="border-b border-slate-100 bg-slate-50/50">
                {["#", "City", "Zones", "Areas", "Customers", "Actions"].map(h => (
                  <th key={h} className="px-6 py-3.5 text-left text-xs font-semibold uppercase tracking-wide text-slate-400">
                    {h}
                  </th>
                ))}
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-50">
              {CITIES.map((city, i) => (
                <tr key={city.id} className="group hover:bg-slate-50/70 transition-colors">
                  <td className="px-6 py-4 text-sm text-slate-400 tabular-nums">{i + 1}</td>
                  <td className="px-6 py-4">
                    <div className="flex items-center gap-3">
                      <div className={`flex h-9 w-9 shrink-0 items-center justify-center rounded-xl bg-gradient-to-br ${city.color} shadow-sm`}>
                        <span className="text-xs font-bold text-white">{city.name[0]}</span>
                      </div>
                      <div>
                        <p className="text-sm font-semibold text-slate-800">{city.name}</p>
                        <p className="text-xs text-slate-400">Pakistan</p>
                      </div>
                    </div>
                  </td>
                  <td className="px-6 py-4">
                    <span className="inline-flex items-center rounded-full bg-violet-50 px-2.5 py-1 text-xs font-semibold text-violet-700">
                      {city.zones} zones
                    </span>
                  </td>
                  <td className="px-6 py-4">
                    <span className="inline-flex items-center rounded-full bg-slate-100 px-2.5 py-1 text-xs font-semibold text-slate-600">
                      {city.areas} areas
                    </span>
                  </td>
                  <td className="px-6 py-4">
                    <span className="text-sm font-semibold text-slate-800 tabular-nums">
                      {city.customers.toLocaleString()}
                    </span>
                  </td>
                  <td className="px-6 py-4">
                    <button className="flex items-center gap-1.5 rounded-lg border border-red-200 px-3 py-1.5 text-xs font-medium text-red-600 opacity-0 group-hover:opacity-100 hover:bg-red-50 transition-all cursor-pointer">
                      <Trash2 className="h-3.5 w-3.5" />
                      Delete
                    </button>
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
