import { createFileRoute } from "@tanstack/react-router";
import { CheckCircle2, Clock, MapPinned, Sparkles } from "lucide-react";
import { toast } from "sonner";

import { ModulePage } from "@/components/app/ModulePage";
import { visits } from "@/lib/mock-data";

export const Route = createFileRoute("/adhoc-visits")({
  head: () => ({
    meta: [
      { title: "Ad-hoc Visits — Bookmark Field Force Manager" },
      { name: "description", content: "Unplanned visits logged by field officers and awaiting approval." },
      { property: "og:title", content: "Ad-hoc Visits — Bookmark Field Force Manager" },
      { property: "og:description", content: "Unplanned officer visits awaiting review." },
    ],
  }),
  component: () => (
    <ModulePage
      title="Ad-hoc Visits"
      subtitle="Unplanned visits logged in the field"
      stats={[
        { label: "This Month", value: "58", icon: <Sparkles className="h-5 w-5" /> },
        { label: "Approved", value: "44", icon: <CheckCircle2 className="h-5 w-5" /> },
        { label: "Pending", value: "11", icon: <Clock className="h-5 w-5" /> },
        { label: "Geo-verified", value: "52", icon: <MapPinned className="h-5 w-5" /> },
      ]}
      columns={[
        { key: "id", label: "Visit" },
        { key: "customer", label: "Customer" },
        { key: "officer", label: "Officer" },
        { key: "purpose", label: "Purpose" },
        { key: "date", label: "Date" },
        { key: "status", label: "Status", status: true },
      ]}
      rows={visits.slice(0, 12)}
      primaryAction={{ label: "Approve Pending", onClick: () => toast.success("11 ad-hoc visits approved") }}
    />
  ),
});
