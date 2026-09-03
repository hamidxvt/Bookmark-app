import { createFileRoute, Link } from "@tanstack/react-router";
import { KeyRound, Save, SlidersHorizontal, UserRound } from "lucide-react";
import { useEffect, useState } from "react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { SectionCard } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Switch } from "@/components/ui/switch";

export const Route = createFileRoute("/settings")({
  head: () => ({
    meta: [
      { title: "Settings — Bookmark Field Force Manager" },
      { name: "description", content: "Organisation settings, visit targets, geofencing and Google Maps configuration." },
      { property: "og:title", content: "Settings — Bookmark Field Force Manager" },
      { property: "og:description", content: "Configure targets, geofencing and map integration." },
    ],
  }),
  component: SettingsPage,
});

function SettingsPage() {
  const [target, setTarget] = useState("10");
  const [radius, setRadius] = useState("150");
  const [timezone, setTimezone] = useState("Asia/Karachi");
  const [mapsKey, setMapsKey] = useState("");
  const [hasEnvKey, setHasEnvKey] = useState(false);

  useEffect(() => {
    setHasEnvKey(Boolean(import.meta.env["VITE_GOOGLE_MAPS_API_KEY"]));
    setMapsKey(localStorage.getItem("bookmark_gmaps_key") ?? "");
  }, []);

  return (
    <AppShell title="Settings" subtitle="Organisation, field policy and integrations">
      <div className="space-y-6">
        <SectionCard title="Field Policy" description="Applies to every officer in the field">
          <div className="grid gap-4 sm:grid-cols-3">
            <div className="space-y-2">
              <Label>Daily visit target</Label>
              <Input type="number" value={target} onChange={(e) => setTarget(e.target.value)} className="h-11 rounded-xl" />
            </div>
            <div className="space-y-2">
              <Label>Geofence radius (m)</Label>
              <Input type="number" value={radius} onChange={(e) => setRadius(e.target.value)} className="h-11 rounded-xl" />
            </div>
            <div className="space-y-2">
              <Label>Timezone</Label>
              <Select value={timezone} onValueChange={setTimezone}>
                <SelectTrigger className="h-11 rounded-xl"><SelectValue /></SelectTrigger>
                <SelectContent>
                  <SelectItem value="Asia/Karachi">Asia/Karachi</SelectItem>
                  <SelectItem value="Asia/Dubai">Asia/Dubai</SelectItem>
                  <SelectItem value="UTC">UTC</SelectItem>
                </SelectContent>
              </Select>
            </div>
          </div>
          <ul className="mt-5 space-y-4">
            {[
              ["Require GPS to start shift", true],
              ["Auto-mark missed visits at midnight", true],
              ["Allow ad-hoc visits without approval", false],
            ].map(([label, def]) => (
              <li key={String(label)} className="flex items-center justify-between gap-4 rounded-xl border border-border/70 p-4">
                <p className="flex items-center gap-2 text-sm font-medium"><SlidersHorizontal className="h-4 w-4 text-primary" /> {label}</p>
                <Switch defaultChecked={Boolean(def)} onCheckedChange={(v) => toast.success(`${label} ${v ? "enabled" : "disabled"}`)} />
              </li>
            ))}
          </ul>
          <Button className="mt-5 rounded-xl" onClick={() => toast.success("Field policy saved")}>
            <Save className="mr-2 h-4 w-4" /> Save settings
          </Button>
        </SectionCard>

        <SectionCard title="Google Maps" description="All maps in this dashboard use the Google Maps JavaScript API">
          {hasEnvKey ? (
            <p className="text-sm text-muted-foreground">
              A project-level Google Maps API key is configured. Maps will render automatically.
            </p>
          ) : (
            <div className="grid gap-3 sm:max-w-xl">
              <Label>Google Maps API key</Label>
              <Input value={mapsKey} onChange={(e) => setMapsKey(e.target.value)} placeholder="AIza..." className="h-11 rounded-xl" />
              <p className="text-xs text-muted-foreground">
                Stored locally in this browser so map surfaces can render immediately.
              </p>
              <div className="flex gap-2">
                <Button
                  className="rounded-xl"
                  onClick={() => {
                    if (!mapsKey.trim()) {
                      toast.error("Enter a valid key");
                      return;
                    }
                    localStorage.setItem("bookmark_gmaps_key", mapsKey.trim());
                    toast.success("Maps key saved", { description: "Reload a map page to see live maps." });
                  }}
                >
                  <KeyRound className="mr-2 h-4 w-4" /> Save key
                </Button>
                <Button
                  variant="outline"
                  className="rounded-xl"
                  onClick={() => {
                    localStorage.removeItem("bookmark_gmaps_key");
                    setMapsKey("");
                    toast.success("Maps key removed");
                  }}
                >
                  Remove
                </Button>
              </div>
            </div>
          )}
        </SectionCard>

        <SectionCard title="Account" description="Personal information, security and notifications">
          <Button asChild variant="outline" className="rounded-xl">
            <Link to="/profile">
              <UserRound className="mr-2 h-4 w-4" /> Open my profile
            </Link>
          </Button>
        </SectionCard>
      </div>
    </AppShell>
  );
}
