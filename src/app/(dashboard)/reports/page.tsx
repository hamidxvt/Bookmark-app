"use client";
import { useState } from "react";
import { Download } from "lucide-react";

const REPORT_TYPES = [
  { type: "visits", label: "Visits Report", description: "All visit records with officer, customer, status, notes" },
  { type: "bookers", label: "Officers Report", description: "All sales officers with contact info and status" },
  { type: "customers", label: "Customers Report", description: "All schools/shops with city and priority" },
  { type: "attendance", label: "Attendance Report", description: "Day start/end records and absent entries" },
  { type: "leaves", label: "Leave Requests", description: "All leave applications with approval status" },
];

export default function ReportsPage() {
  const [from, setFrom] = useState(() => {
    const d = new Date();
    d.setDate(1);
    return d.toISOString().split("T")[0];
  });
  const [to, setTo] = useState(() => new Date().toISOString().split("T")[0]);
  const [loading, setLoading] = useState<string | null>(null);

  function download(type: string) {
    setLoading(type);
    const params = new URLSearchParams({ type, from, to });
    const url = `/api/v1/reports?${params}`;

    // Create anchor and trigger download
    const a = document.createElement("a");
    a.href = url;
    a.download = `${type}-export.csv`;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);

    setTimeout(() => setLoading(null), 2000);
  }

  return (
    <div className="p-8 max-w-3xl mx-auto">
      <h1 className="text-2xl font-bold mb-1">Export Data</h1>
      <p className="text-gray-500 text-sm mb-6">Download CSV files for any report below. Use the date range to filter.</p>

      {/* Date range */}
      <div className="flex gap-4 mb-8 p-4 bg-gray-50 rounded-xl border border-gray-200">
        <div>
          <label className="text-xs text-gray-500 block mb-1">From</label>
          <input
            type="date"
            value={from}
            onChange={(e) => setFrom(e.target.value)}
            className="border rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>
        <div>
          <label className="text-xs text-gray-500 block mb-1">To</label>
          <input
            type="date"
            value={to}
            onChange={(e) => setTo(e.target.value)}
            className="border rounded-lg px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
        </div>
      </div>

      <div className="space-y-3">
        {REPORT_TYPES.map(({ type, label, description }) => (
          <div
            key={type}
            className="flex items-center justify-between p-4 bg-white border border-gray-200 rounded-xl shadow-sm hover:border-blue-300 transition"
          >
            <div>
              <p className="font-semibold text-gray-800">{label}</p>
              <p className="text-sm text-gray-500">{description}</p>
            </div>
            <button
              onClick={() => download(type)}
              disabled={loading === type}
              className="flex items-center gap-2 px-4 py-2 bg-blue-600 text-white text-sm font-medium rounded-lg hover:bg-blue-700 disabled:opacity-50 transition"
            >
              <Download size={15} />
              {loading === type ? "Downloading..." : "Download CSV"}
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}
