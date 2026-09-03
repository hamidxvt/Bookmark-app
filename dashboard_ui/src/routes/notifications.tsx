import { createFileRoute, useNavigate } from "@tanstack/react-router";
import { Bell, BellOff, CheckCheck, Inbox } from "lucide-react";
import { useState } from "react";
import { toast } from "sonner";

import { AppShell } from "@/components/app/AppShell";
import { EmptyState, SectionCard, StatCard } from "@/components/app/ui-bits";
import { Button } from "@/components/ui/button";
import { Tabs, TabsList, TabsTrigger } from "@/components/ui/tabs";
import {
  clearNotifications,
  markAllNotificationsRead,
  markNotificationRead,
  useNotifications,
} from "@/lib/store";
import { cn } from "@/lib/utils";

export const Route = createFileRoute("/notifications")({
  head: () => ({
    meta: [
      { title: "Notifications — Bookmark Field Force Manager" },
      { name: "description", content: "Visit assignments, attendance alerts, leave and customer requests in one inbox." },
      { property: "og:title", content: "Notifications — Bookmark Field Force Manager" },
      { property: "og:description", content: "Field force notification centre and alerts inbox." },
    ],
  }),
  component: NotificationsPage,
});

const tabs = ["all", "visit", "attendance", "leave", "customer", "request", "system"] as const;

function NotificationsPage() {
  const items = useNotifications();
  const navigate = useNavigate();
  const [tab, setTab] = useState<string>("all");

  const list = tab === "all" ? items : items.filter((n) => n.type === tab);
  const unread = items.filter((n) => n.unread).length;

  return (
    <AppShell title="Notifications" subtitle="Everything happening across the field force">
      <div className="space-y-6">
        <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-3">
          <StatCard label="Total" value={String(items.length)} icon={<Inbox className="h-5 w-5" />} onClick={() => setTab("all")} />
          <StatCard label="Unread" value={String(unread)} icon={<Bell className="h-5 w-5" />} onClick={() => setTab("all")} />
          <StatCard label="Read" value={String(items.length - unread)} icon={<CheckCheck className="h-5 w-5" />} onClick={() => setTab("all")} />
        </div>

        <SectionCard
          title="Inbox"
          description={`${list.length} notifications`}
          action={
            <div className="flex gap-2">
              <Button variant="outline" className="rounded-xl" onClick={() => { markAllNotificationsRead(); toast.success("All marked as read"); }}>
                <CheckCheck className="mr-2 h-4 w-4" /> Mark all read
              </Button>
              <Button variant="outline" className="rounded-xl" onClick={() => { clearNotifications(); toast.success("Inbox cleared"); }}>
                <BellOff className="mr-2 h-4 w-4" /> Clear
              </Button>
            </div>
          }
        >
          <Tabs value={tab} onValueChange={setTab} className="mb-5">
            <TabsList className="flex-wrap rounded-xl">
              {tabs.map((t) => (
                <TabsTrigger key={t} value={t} className="rounded-lg capitalize">{t}</TabsTrigger>
              ))}
            </TabsList>
          </Tabs>

          {list.length === 0 ? (
            <EmptyState title="No notifications" description="You are all caught up." />
          ) : (
            <ul className="space-y-3">
              {list.map((n) => (
                <li key={n.id}>
                  <button
                    onClick={() => {
                      markNotificationRead(n.id);
                      navigate({ to: n.to });
                    }}
                    className={cn(
                      "flex w-full items-start gap-3 rounded-2xl border p-4 text-left transition-colors hover:bg-muted/60",
                      n.unread ? "border-primary/30 bg-primary/[0.04]" : "border-border/70",
                    )}
                  >
                    <span className={cn("mt-1.5 h-2 w-2 shrink-0 rounded-full", n.unread ? "bg-primary" : "bg-muted-foreground/40")} />
                    <div className="min-w-0 flex-1">
                      <p className="text-sm font-semibold">{n.title}</p>
                      <p className="text-xs text-muted-foreground">{n.body}</p>
                      <p className="mt-1 text-[11px] uppercase tracking-wide text-muted-foreground/70">{n.type} · {n.time}</p>
                    </div>
                  </button>
                </li>
              ))}
            </ul>
          )}
        </SectionCard>
      </div>
    </AppShell>
  );
}
