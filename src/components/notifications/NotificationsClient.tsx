"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { Bell, BellOff, CheckCheck, Inbox } from "lucide-react";
import { Button } from "@/components/ui/button";
import { Tabs, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { StatCard, SectionCard, EmptyState, TableSkeleton } from "@/components/shared/ui-bits";
import { cn } from "@/lib/utils";

interface Notification {
  id: string;
  type: "leave" | "missed" | "request";
  title: string;
  subtitle: string;
  href: string;
  time: string;
}

const TABS = ["all", "leave", "missed", "request"] as const;

export default function NotificationsClient() {
  const router = useRouter();
  const [items, setItems] = useState<Notification[]>([]);
  const [unread, setUnread] = useState(0);
  const [loading, setLoading] = useState(true);
  const [tab, setTab] = useState<string>("all");
  const [read, setRead] = useState<Set<string>>(new Set());

  async function load() {
    setLoading(true);
    try {
      const res = await fetch("/api/v1/notifications").then(r => r.json());
      setItems(res.notifications ?? []);
      setUnread(res.unread ?? 0);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => { load(); }, []);

  const list = tab === "all" ? items : items.filter(n => n.type === tab);

  return (
    <div className="space-y-6 px-6 py-6 lg:px-8">
      <div className="grid gap-4 sm:grid-cols-2 xl:grid-cols-3">
        <StatCard label="Total" value={items.length} icon={<Inbox className="h-5 w-5" />} />
        <StatCard label="Unread" value={unread} icon={<Bell className="h-5 w-5" />} />
        <StatCard label="Read" value={read.size} icon={<CheckCheck className="h-5 w-5" />} />
      </div>

      <SectionCard
        title="Inbox"
        description={`${list.length} notifications`}
        action={
          <div className="flex gap-2">
            <Button
              variant="outline"
              className="rounded-xl"
              onClick={() => setRead(new Set(items.map(i => i.id)))}
            >
              <CheckCheck className="h-4 w-4" /> Mark all read
            </Button>
            <Button variant="outline" className="rounded-xl" onClick={() => setItems([])}>
              <BellOff className="h-4 w-4" /> Clear
            </Button>
          </div>
        }
      >
        <Tabs value={tab} onValueChange={setTab} className="mb-5">
          <TabsList className="flex-wrap rounded-xl">
            {TABS.map(t => (
              <TabsTrigger key={t} value={t} className="rounded-lg capitalize">{t}</TabsTrigger>
            ))}
          </TabsList>
        </Tabs>

        {loading ? (
          <TableSkeleton />
        ) : list.length === 0 ? (
          <EmptyState title="No notifications" description="You are all caught up." />
        ) : (
          <ul className="space-y-3">
            {list.map(n => {
              const isUnread = !read.has(n.id);
              return (
                <li key={n.id}>
                  <button
                    onClick={() => {
                      setRead(prev => new Set(prev).add(n.id));
                      router.push(n.href);
                    }}
                    className={cn(
                      "flex w-full items-start gap-3 rounded-2xl border p-4 text-left transition-colors hover:bg-muted/60",
                      isUnread ? "border-primary/30 bg-primary-soft" : "border-border/70",
                    )}
                  >
                    <span className={cn("mt-1.5 h-2 w-2 shrink-0 rounded-full", isUnread ? "bg-primary" : "bg-muted-foreground/40")} />
                    <div className="min-w-0 flex-1">
                      <p className="text-sm font-semibold text-foreground">{n.title}</p>
                      <p className="text-xs text-muted-foreground">{n.subtitle}</p>
                      <p className="mt-1 text-[11px] uppercase tracking-wide text-muted-foreground/70">
                        {n.type} · {new Date(n.time).toLocaleString("en-PK", { day: "2-digit", month: "short", hour: "2-digit", minute: "2-digit" })}
                      </p>
                    </div>
                  </button>
                </li>
              );
            })}
          </ul>
        )}
      </SectionCard>
    </div>
  );
}
