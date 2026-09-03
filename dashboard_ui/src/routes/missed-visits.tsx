import { createFileRoute } from "@tanstack/react-router";
import { AlertTriangle, Clock, RotateCcw, XCircle } from "lucide-react";
import { toast } from "sonner";

import { ModulePage } from "@/components/app/ModulePage";
import { visits } from "@/lib/mock-data";

export const Route = createFileRoute("/missed-visits")({
  head: () => ({
    meta: [
      { title: "Missed Visits — Bookmark Field Force Manager" },
      { name: "description", content: "Review missed and rescheduled field visits and their recovery status." },
      { property: "og:title", content: "Missed Visits — Bookmark Field Force Manager" },
      { property: "og:description", content: "Review missed field visits and recovery actions." },
    ],
  }),
  component: () => {
    const rows = visits.filter((v) => v.status !== "completed");
    return (
      <ModulePage
        title="Missed Visits"
        subtitle="Exceptions that need follow-up"
        stats={[
          { label: "Missed", value: "14", icon: <XCircle className="h-5 w-5" /> },
          { label: "Pending Review", value: "6", icon: <Clock className="h-5 w-5" /> },
          { label: "Rescheduled", value: "8", icon: <RotateCcw className="h-5 w-5" /> },
          { label: "Escalations", value: "2", icon: <AlertTriangle className="h-5 w-5" /> },
        ]}
        columns={[
          { key: "id", label: "Visit" },
          { key: "customer", label: "Customer" },
          { key: "officer", label: "Officer" },
          { key: "city", label: "City" },
          { key: "date", label: "Date" },
          { key: "status", label: "Status", status: true },
        ]}
        rows={rows}
        primaryAction={{ label: "Reschedule All", onClick: () => toast.success("All missed visits rescheduled") }}
      />
    );
  },
});
