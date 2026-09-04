"use client";

import { Bell, LogOut, RefreshCw, Search, Settings, UserRound, Menu } from "lucide-react";
import { useEffect, useRef, useState } from "react";
import { signOut, useSession } from "next-auth/react";
import { usePathname, useRouter } from "next/navigation";
import Link from "next/link";
import axios from "axios";
import { cn } from "@/lib/utils";
import { useSidebar } from "./SidebarContext";

type Notif = {
  id: string;
  type: "leave" | "missed" | "request";
  title: string;
  subtitle: string;
  href: string;
  time: string;
  unread?: boolean;
};

export default function Header({
  title,
  subtitle,
  onRefresh,
}: {
  title?: string;
  subtitle?: string;
  onRefresh?: () => void;
}) {
  const { data: session } = useSession();
  const { toggleOpen } = useSidebar();
  const router = useRouter();
  const pathname = usePathname();

  const [query, setQuery] = useState("");
  const [refreshing, setRefreshing] = useState(false);
  const [notifOpen, setNotifOpen] = useState(false);
  const [userOpen, setUserOpen] = useState(false);
  const [notifs, setNotifs] = useState<Notif[]>([]);
  const [unread, setUnread] = useState(0);

  const notifRef = useRef<HTMLDivElement>(null);
  const userRef = useRef<HTMLDivElement>(null);

  // derive page title from path when not explicitly passed
  const pageTitle = title ?? (() => {
    const labels: Record<string, string> = {
      dashboard: "Dashboard", bookers: "Sales Team", customers: "Customers",
      visits: "Visits", products: "Products", location: "Live Location",
      "live-activity": "Live Activity", attendance: "Attendance", payroll: "Payroll",
      "missed-visits": "Missed Visits", samples: "Samples", requests: "Support Tickets",
      reports: "Export Data", "data-import": "Data Import", notifications: "Notifications",
      scheduler: "Run Schedulers", profile: "Edit Profile", settings: "Settings", "live-shifts": "Live Shifts",
      "adhoc-visits": "Ad-hoc Visits", "locations": "City Management",
    };
    const seg = pathname.split("/").filter(Boolean)[0] ?? "dashboard";
    return labels[seg] ?? seg.replace(/-/g, " ").replace(/\b\w/g, c => c.toUpperCase());
  })();

  // Close dropdowns on outside click
  useEffect(() => {
    function handler(e: MouseEvent) {
      if (notifRef.current && !notifRef.current.contains(e.target as Node)) setNotifOpen(false);
      if (userRef.current && !userRef.current.contains(e.target as Node)) setUserOpen(false);
    }
    document.addEventListener("mousedown", handler);
    return () => document.removeEventListener("mousedown", handler);
  }, []);

  // Load notifications
  useEffect(() => {
    fetch("/api/v1/notifications")
      .then(r => r.json())
      .then(d => { setNotifs(d.notifications ?? []); setUnread(d.unread ?? 0); })
      .catch(() => {});

    let es: EventSource | null = null;
    try {
      es = new EventSource("/api/v1/notifications/stream");
      es.onmessage = (e) => {
        try { const c = JSON.parse(e.data); setUnread(c.total ?? 0); } catch {}
      };
      es.onerror = () => es?.close();
    } catch {}
    return () => es?.close();
  }, []);

  const refresh = () => {
    setRefreshing(true);
    onRefresh?.();
    setTimeout(() => setRefreshing(false), 700);
  };

  return (
    <header className="sticky top-0 z-20 border-b border-border/70 bg-background/85 backdrop-blur-xl">
      <div className="flex flex-wrap items-center gap-3 px-6 py-4 lg:px-8">
        {/* Mobile hamburger */}
        <button
          onClick={toggleOpen}
          className="flex h-10 w-10 items-center justify-center rounded-xl border border-border bg-card shadow-card transition hover:shadow-elevated lg:hidden"
          aria-label="Open menu"
        >
          <Menu className="h-4.5 w-4.5" />
        </button>

        {/* Title */}
        <div className="min-w-0 flex-1">
          <h1 className="truncate text-2xl font-bold tracking-tight text-foreground">{pageTitle}</h1>
          {subtitle && <p className="mt-0.5 text-sm text-muted-foreground">{subtitle}</p>}
        </div>

        {/* Search */}
        <form
          className="relative hidden xl:block"
          onSubmit={(e) => {
            e.preventDefault();
            const q = query.trim();
            if (!q) return;
            router.push("/customers");
          }}
        >
          <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
          <input
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            placeholder="Search officers, customers, visits…"
            className="h-10 w-72 rounded-xl border border-transparent bg-card pl-9 pr-4 text-sm shadow-card outline-none placeholder:text-muted-foreground focus:border-primary/30 focus:ring-0"
          />
        </form>

        {/* Refresh */}
        <button
          onClick={refresh}
          className="flex h-10 w-10 items-center justify-center rounded-xl border border-border bg-card shadow-card transition hover:shadow-elevated"
          aria-label="Refresh"
        >
          <RefreshCw className={cn("h-4 w-4 text-muted-foreground", refreshing && "animate-spin")} />
        </button>

        {/* Notifications */}
        <div className="relative" ref={notifRef}>
          <button
            onClick={() => setNotifOpen(!notifOpen)}
            className="relative flex h-10 w-10 items-center justify-center rounded-xl border border-border bg-card shadow-card transition hover:shadow-elevated"
            aria-label="Notifications"
          >
            <Bell className="h-4 w-4 text-muted-foreground" />
            {unread > 0 && (
              <span className="absolute -right-1 -top-1 flex min-h-5 min-w-5 items-center justify-center rounded-full bg-primary px-1 text-[10px] font-bold text-primary-foreground">
                {unread > 9 ? "9+" : unread}
              </span>
            )}
          </button>

          {notifOpen && (
            <div className="absolute right-0 mt-2 w-96 overflow-hidden rounded-2xl border border-border bg-card p-0 shadow-elevated z-50">
              <div className="flex items-center justify-between border-b border-border px-4 py-3">
                <p className="text-sm font-semibold text-foreground">Notifications</p>
                <button
                  className="text-xs font-medium text-primary hover:underline"
                  onClick={() => setUnread(0)}
                >
                  Mark all read
                </button>
              </div>
              <div className="max-h-80 overflow-y-auto">
                {notifs.length === 0 ? (
                  <p className="p-6 text-center text-sm text-muted-foreground">You're all caught up.</p>
                ) : (
                  notifs.slice(0, 8).map((n) => (
                    <Link
                      key={n.id}
                      href={n.href}
                      onClick={() => setNotifOpen(false)}
                      className={cn(
                        "flex w-full gap-3 border-b border-border px-4 py-3 text-left transition-colors last:border-0 hover:bg-muted/60",
                        n.unread && "bg-primary-soft/60",
                      )}
                    >
                      <span className={cn("mt-1.5 h-2 w-2 shrink-0 rounded-full", n.unread ? "bg-primary" : "bg-border")} />
                      <span className="min-w-0">
                        <span className="block text-sm font-medium text-foreground">{n.title}</span>
                        <span className="block truncate text-xs text-muted-foreground">{n.subtitle}</span>
                        <span className="mt-1 block text-[11px] text-muted-foreground/80">{n.time}</span>
                      </span>
                    </Link>
                  ))
                )}
              </div>
              <div className="border-t border-border px-4 py-3">
                <Link
                  href="/notifications"
                  onClick={() => setNotifOpen(false)}
                  className="text-xs font-semibold text-primary hover:underline"
                >
                  View all notifications →
                </Link>
              </div>
            </div>
          )}
        </div>

        {/* User dropdown */}
        <div className="relative" ref={userRef}>
          <button
            onClick={() => setUserOpen(!userOpen)}
            className="flex items-center gap-3 rounded-xl bg-card px-3 py-2 shadow-card transition hover:shadow-elevated cursor-pointer"
          >
            <span className="flex h-8 w-8 items-center justify-center rounded-lg bg-navy text-xs font-bold text-navy-foreground">
              {session?.user?.name?.slice(0, 2).toUpperCase() ?? "AD"}
            </span>
            <span className="hidden text-left leading-tight sm:block">
              <span className="block text-sm font-semibold text-foreground">
                {session?.user?.name?.split(" ")[0] ?? "Admin"}
              </span>
              <span className="block text-[11px] text-muted-foreground">Super Admin</span>
            </span>
          </button>

          {userOpen && (
            <div className="absolute right-0 mt-2 w-52 overflow-hidden rounded-xl border border-border bg-card shadow-elevated z-50">
              <div className="border-b border-border px-4 py-2.5">
                <p className="text-xs text-muted-foreground truncate">{session?.user?.email ?? "admin@bookmark.com.pk"}</p>
              </div>
              <div className="py-1">
                <Link
                  href="/profile"
                  onClick={() => setUserOpen(false)}
                  className="flex items-center gap-2.5 px-4 py-2 text-sm text-foreground hover:bg-muted/60 transition-colors"
                >
                  <UserRound className="h-4 w-4 text-muted-foreground" />
                  Profile
                </Link>
                <Link
                  href="/settings"
                  onClick={() => setUserOpen(false)}
                  className="flex items-center gap-2.5 px-4 py-2 text-sm text-foreground hover:bg-muted/60 transition-colors"
                >
                  <Settings className="h-4 w-4 text-muted-foreground" />
                  Settings
                </Link>
              </div>
              <div className="border-t border-border py-1">
                <button
                  onClick={() => signOut({ callbackUrl: "/login" })}
                  className="flex w-full items-center gap-2.5 px-4 py-2 text-sm text-destructive hover:bg-destructive/8 transition-colors cursor-pointer"
                >
                  <LogOut className="h-4 w-4" />
                  Logout
                </button>
              </div>
            </div>
          )}
        </div>
      </div>
    </header>
  );
}
