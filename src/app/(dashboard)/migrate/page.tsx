"use client";
import { useState } from "react";

export default function MigratePage() {
  const [loading, setLoading] = useState(false);
  const [log, setLog] = useState<string[]>([]);
  const [done, setDone] = useState(false);
  const [error, setError] = useState("");
  const [cleaning, setCleaning] = useState(false);

  async function runMigration() {
    if (!confirm("This will clear all existing data and import fresh from staging. Continue?")) return;
    setLoading(true); setLog([]); setError(""); setDone(false);
    try {
      const res = await fetch("/api/v1/migrate-from-staging", { method: "POST" });
      const json = await res.json();
      setLog(json.log ?? []);
      if (json.success) setDone(true);
      else setError(json.error ?? "Unknown error");
    } catch (e: any) { setError(e.message); }
    finally { setLoading(false); }
  }

  async function runCleanup() {
    if (!confirm("Run DB cleanup? This fixes HTML city names, activates all bookers, and fixes data. Safe to run.")) return;
    setCleaning(true); setLog([]); setError("");
    try {
      const res = await fetch("/api/v1/cleanup", { method: "POST" });
      const json = await res.json();
      setLog(json.log ?? []);
      if (!json.success) setError(json.error ?? "Unknown error");
    } catch (e: any) { setError(e.message); }
    finally { setCleaning(false); }
  }

  return (
    <div className="p-8 max-w-2xl mx-auto">
      <h1 className="text-2xl font-bold mb-2">Data Migration</h1>
      <p className="text-gray-500 mb-6 text-sm">
        Pulls all existing data from <strong>staging.bookmark.services</strong> and imports it
        into your Railway PostgreSQL database.
      </p>

      <div className="flex gap-3 mb-4">
        {!done && (
          <button onClick={runMigration} disabled={loading || cleaning}
            className="px-6 py-3 bg-blue-600 text-white rounded-lg font-semibold hover:bg-blue-700 disabled:opacity-50 transition">
            {loading ? "⏳ Migrating..." : "🚀 Run Migration"}
          </button>
        )}
        <button onClick={runCleanup} disabled={loading || cleaning}
          className="px-6 py-3 bg-green-600 text-white rounded-lg font-semibold hover:bg-green-700 disabled:opacity-50 transition">
          {cleaning ? "⏳ Cleaning..." : "🧹 Fix DB (Clean HTML + Activate Officers)"}
        </button>
      </div>

      {log.length > 0 && (
        <div className="mt-4 bg-gray-900 text-green-400 p-4 rounded-lg font-mono text-sm space-y-1 max-h-96 overflow-y-auto">
          {log.map((line, i) => <div key={i}>{line}</div>)}
        </div>
      )}

      {done && (
        <div className="mt-4 p-4 bg-green-50 border border-green-200 text-green-800 rounded-lg font-semibold">
          ✅ Migration complete! Now click <strong>&quot;Fix DB&quot;</strong> to clean up HTML city names.
        </div>
      )}

      {error && (
        <div className="mt-4 p-4 bg-red-50 border border-red-200 text-red-700 rounded-lg">
          <strong>Error:</strong> {error}
        </div>
      )}
    </div>
  );
}
