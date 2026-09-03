import { createFileRoute } from "@tanstack/react-router";
import { Building2, MapPin, Users } from "lucide-react";
import { toast } from "sonner";

import { ModulePage } from "@/components/app/ModulePage";
import { cityList } from "@/lib/mock-data";

export const Route = createFileRoute("/city-management")({
  head: () => ({
    meta: [
      { title: "City Management — Bookmark Field Force Manager" },
      { name: "description", content: "Manage cities, areas and territory coverage for the Bookmark field force." },
      { property: "og:title", content: "City Management — Bookmark Field Force Manager" },
      { property: "og:description", content: "Manage cities, areas and territory coverage." },
    ],
  }),
  component: () => (
    <ModulePage
      title="City Management"
      subtitle="Territories, areas and coverage"
      stats={[
        { label: "Cities", value: String(cityList.length), icon: <Building2 className="h-5 w-5" /> },
        { label: "Areas", value: String(cityList.reduce((a, c) => a + c.areas, 0)), icon: <MapPin className="h-5 w-5" /> },
        { label: "Officers Deployed", value: "16", icon: <Users className="h-5 w-5" /> },
        { label: "Customers Covered", value: "7,352", icon: <Building2 className="h-5 w-5" /> },
      ]}
      columns={[
        { key: "city", label: "City" },
        { key: "province", label: "Province" },
        { key: "areas", label: "Areas" },
        { key: "officers", label: "Officers" },
        { key: "customers", label: "Customers" },
        { key: "status", label: "Status", status: true },
      ]}
      rows={cityList}
      primaryAction={{ label: "Add City", onClick: () => toast.success("City added to the network") }}
    />
  ),
});
