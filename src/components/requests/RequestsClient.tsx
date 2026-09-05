"use client";

import { useEffect, useState } from "react";
import { CheckCircle, XCircle, Clock, RefreshCw, Tag } from "lucide-react";

const STATUS_CFG: Record<string, { icon: React.ElementType; style: string; label: string }> = {
  PENDING:  { icon: Clock,       style: "bg-warning/20 text-warning-foreground border-warning/30", label: "Pending"  },
  RESOLVED: { icon: CheckCircle, style: "bg-success/15 text-success border-success/25",             label: "Resolved" },
  REJECTED: { icon: XCircle,     style: "bg-destructive/10 text-destructive border-destructive/25", label: "Rejected" },
};

interface Request {
  id: number;
  title: string;
  category: string | null;
  details: string | null;
  status: "PENDING" | "RESOLVED" | "REJECTED";
  adminNotes: string | null;
  createdAt: string;
  booker: { id: number; name: string; email: string; phone: string | null } | null;
}

export default function RequestsClient() {
  const [rows, setRows]       = useState<Request[]>([]);
  const [total, setTotal]     = useState(0);
  const [loading, setLoading] = useState(true);
  const [patching, setPatching] = useState<number | null>(null);

  async function load() {
    setLoading(true);
    try {
      const res = await fetch("/api/v1/requests").then(r => r.json());
      if (res.success) {
        const data: Request[] = res.data?.data ?? [];
        setRows(data);
        setTotal(res.data?.recordsTotal ?? 0);
      }
    } catch (e) { console.error(e); }
    finally { setLoading(false); }
  }

  async function changeStatus(id: number, status: "RESOLVED" | "REJECTED") {
    setPatching(id);
    try {
      await fetch("/api/v1/requests", {
        method: "PATCH",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ id, status }),
      });
      await load();
    } finally {
      setPatching(null);
    }
  }

  useEffect(() => { load(); }, []);

  const pending  = rows.filter(r => r.status === "PENDING").length;
  const resolved = rows.filter(r => r.status === "RESOLVED").length;
  const rejected = rows.filter(r => r.status === "REJECTED").length;

  return (
    <div className="space-y-4">
      <div className="flex justify-end">
        <button
          onClick={load}
          disabled={loading}
          className="flex items-center gap-1.5 rounded-lg border border-border bg-card px-3 py-2 text-xs font-medium text-muted-foreground hover:bg-muted"
        >
          <RefreshCw className={`h-3.5 w-3.5 ${loading ? "animate-spin" : ""}`} /> Refresh
        </button>
      </div>

      <div className="grid grid-cols-3 gap-3">
        {[
          { label: "Pending",  value: loading ? "…" : pending,  color: "text-warning-foreground" },
          { label: "Resolved", value: loading ? "…" : resolved, color: "text-success"             },
          { label: "Rejected", value: loading ? "…" : rejected, color: "text-destructive"         },
        ].map(s => (
          <div key={s.label} className="surface p-4">
            <p className={`text-2xl font-bold ${s.color} tabular-nums`}>{s.value}</p>
            <p className="text-xs text-muted-foreground mt-0.5">{s.label}</p>
          </div>
        ))}
      </div>

      <div className="surface overflow-hidden">
        <div className="flex items-center justify-between px-5 py-4 border-b border-border">
          <h3 className="text-sm font-semibold text-foreground">Support Tickets</h3>
          <span className="text-xs text-muted-foreground">{total.toLocaleString()} total</span>
        </div>

        {loading ? (
          <div className="divide-y divide-border">
            {[...Array(5)].map((_, i) => (
              <div key={i} className="px-5 py-4 space-y-2">
                <div className="h-3 w-32 rounded bg-muted animate-pulse" />
                <div className="h-4 w-64 rounded bg-muted animate-pulse" />
                <div className="h-3 w-full max-w-md rounded bg-muted animate-pulse" />
              </div>
            ))}
          </div>
        ) : (
          <div className="divide-y divide-border">
            {rows.length === 0 && (
              <p className="px-5 py-8 text-center text-sm text-muted-foreground">No requests found</p>
            )}
            {rows.map((r, i) => {
              const cfg  = STATUS_CFG[r.status] ?? STATUS_CFG.PENDING;
              const Icon = cfg.icon;
              return (
                <div key={r.id} className="px-5 py-4 hover:bg-muted/50 transition-colors">
                  <div className="flex items-start justify-between gap-4">
                    <div className="flex-1 min-w-0">
                      {/* Header row: index · officer name · category */}
                      <div className="flex items-center gap-2 mb-1 flex-wrap">
                        <span className="text-xs font-semibold text-primary">#{i + 1}</span>
                        {r.booker && (
                          <>
                            <span className="text-xs text-muted-foreground">·</span>
                            <span className="text-xs text-foreground font-medium">{r.booker.name}</span>
                          </>
                        )}
                        {r.category && (
                          <>
                            <span className="text-xs text-muted-foreground">·</span>
                            <span className="inline-flex items-center gap-1 text-[10px] bg-muted text-muted-foreground rounded-full px-2 py-0.5">
                              <Tag className="h-2.5 w-2.5" />{r.category}
                            </span>
                          </>
                        )}
                        <span className="text-[10px] text-muted-foreground ml-auto">
                          {new Date(r.createdAt).toLocaleDateString("en-PK", { day: "numeric", month: "short", year: "numeric" })}
                        </span>
                      </div>

                      {/* Title */}
                      <p className="font-semibold text-foreground text-sm">
                        {r.title || <span className="text-muted-foreground italic">No title</span>}
                      </p>

                      {/* Details */}
                      {r.details && (
                        <p className="text-xs text-muted-foreground mt-1 line-clamp-2">{r.details}</p>
                      )}

                      {/* Admin notes */}
                      {r.adminNotes && (
                        <p className="text-[10px] text-info-foreground mt-1 bg-info/15 rounded px-2 py-1">
                          Admin note: {r.adminNotes}
                        </p>
                      )}
                    </div>

                    <div className="flex flex-col items-end gap-2 shrink-0">
                      <span className={`inline-flex items-center gap-1.5 rounded-full border px-2.5 py-1 text-xs font-medium ${cfg.style}`}>
                        <Icon className="h-3 w-3" />
                        {cfg.label}
                      </span>
                      {r.status === "PENDING" && (
                        <div className="flex gap-1.5">
                          <button
                            disabled={patching === r.id}
                            onClick={() => changeStatus(r.id, "RESOLVED")}
                            className="text-xs rounded-md bg-success text-success-foreground px-2.5 py-1 hover:opacity-90 transition-colors disabled:opacity-50"
                          >
                            Resolve
                          </button>
                          <button
                            disabled={patching === r.id}
                            onClick={() => changeStatus(r.id, "REJECTED")}
                            className="text-xs rounded-md bg-destructive/10 text-destructive px-2.5 py-1 hover:bg-destructive/20 transition-colors disabled:opacity-50"
                          >
                            Reject
                          </button>
                        </div>
                      )}
                    </div>
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
