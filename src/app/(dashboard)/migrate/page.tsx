"use client";
import { useState } from "react";

export default function MigratePage() {
  const [loading, setLoading] = useState(false);
  const [log, setLog] = useState<string[]>([]);
  const [done, setDone] = useState(false);
  const [error, setError] = useState("");

  async function runMigration() {
    if (!confirm("This will pull all bookers, customers, visits, products from staging.bookmark.services and insert them into the live database. Run once only. Continue?")) return;

    setLoading(true);
    setLog([]);
    setError("");
    setDone(false);

    try {
      const res = await fetch("/api/v1/migrate", { method: "POST" });
      const json = await res.json();
      setLog(json.log ?? []);
      if (json.success) {
        setDone(true);
      } else {
        setError(json.error ?? "Unknown error");
      }
    } catch (e: any) {
      setError(e.message);
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="p-8 max-w-2xl mx-auto">
      <h1 className="text-2xl font-bold mb-2">Data Migration</h1>
      <p className="text-gray-500 mb-6 text-sm">
        Pulls all existing data from <strong>staging.bookmark.services</strong> and imports it
        into your Railway PostgreSQL database. Run this <strong>once</strong> to populate
        bookers, customers, cities, products, subjects, series, and visits.
      </p>

      {!done && (
        <button
          onClick={runMigration}
          disabled={loading}
          className="px-6 py-3 bg-blue-600 text-white rounded-lg font-semibold hover:bg-blue-700 disabled:opacity-50 disabled:cursor-not-allowed transition"
        >
          {loading ? "⏳ Migrating data, please wait..." : "🚀 Run Migration"}
        </button>
      )}

      {log.length > 0 && (
        <div className="mt-6 bg-gray-900 text-green-400 p-4 rounded-lg font-mono text-sm space-y-1 max-h-96 overflow-y-auto">
          {log.map((line, i) => (
            <div key={i}>{line}</div>
          ))}
        </div>
      )}

      {done && (
        <div className="mt-4 p-4 bg-green-50 border border-green-200 text-green-800 rounded-lg font-semibold">
          ✅ Migration complete! All data is now in your database. Refresh the dashboard to see it.
        </div>
      )}

      {error && (
        <div className="mt-4 p-4 bg-red-50 border border-red-200 text-red-700 rounded-lg">
          <strong>Error:</strong> {error}
          <p className="text-sm mt-1">Check that staging.bookmark.services is reachable and credentials are correct.</p>
        </div>
      )}

      {done && (
        <div className="mt-4 p-4 bg-yellow-50 border border-yellow-200 text-yellow-800 text-sm rounded-lg">
          <strong>Note:</strong> Migrated bookers have a placeholder password. They will need to use
          &quot;Forgot Password&quot; or you can manually set passwords from the admin panel.
        </div>
      )}
    </div>
  );
}
