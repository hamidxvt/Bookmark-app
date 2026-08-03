"use client";

import { useSession } from "next-auth/react";
import Link from "next/link";
import { Bell, User, ChevronDown, Menu, LogOut, CalendarOff, FileQuestion, Inbox } from "lucide-react";
import { useState, useRef, useEffect } from "react";
import { signOut } from "next-auth/react";
import { useSidebar } from "./SidebarContext";
import { usePathname } from "next/navigation";
import { cn } from "@/lib/utils";

function getBreadcrumbs(pathname: string): { label: string; href?: string }[] {
  const segments = pathname.split("/").filter(Boolean);
  const crumbs: { label: string; href?: string }[] = [{ label: "Home", href: "/dashboard" }];
  let path = "";
  const labels: Record<string, string> = {
    dashboard: "Dashboard", bookers: "Sales Team", customers: "Customers",
    visits: "Visits", products: "Products", requests: "Requests", inbox: "Inbox",
    location: "Live Location", locations: "Locations", cities: "Cities",
    zones: "Zones", areas: "Areas", add: "Add", today: "Today",
    brands: "Brands", subjects: "Subjects", series: "Series", profile: "Profile",
    scheduler: "Run Schedulers", migrate: "Migrate Data", reports: "Export Data",
    attendance: "Attendance", payroll: "Payroll", "leave-requests": "Leave Requests",
    "missed-visits": "Missed Visits",
  };
  for (const seg of segments) {
    path += "/" + seg;
    if (seg === "dashboard") continue;
    crumbs.push({ label: labels[seg] ?? seg, href: path });
  }
  return crumbs;
}

type Notif = {
  id: string;
  type: "leave" | "missed" | "request";
  title: string;
  subtitle: string;
  href: string;
  time: string;
};

const typeIcon = {
  leave: <CalendarOff className="h-3.5 w-3.5 text-amber-500" />,
  missed: <FileQuestion className="h-3.5 w-3.5 text-rose-500" />,
  request: <Inbox className="h-3.5 w-3.5 text-sky-500" />,
};

export default function Header() {
  const { data: session } = useSession();
  const { toggleOpen } = useSidebar();
  const [userOpen, setUserOpen] = useState(false);
  const [bellOpen, setBellOpen] = useState(false);
  const [notifs, setNotifs] = useState<Notif[]>([]);
  const [unread, setUnread] = useState(0);
  const pathname = usePathname();
  const crumbs = getBreadcrumbs(pathname);
  const dropRef = useRef<HTMLDivElement>(null);
  const bellRef = useRef<HTMLDivElement>(null);

  // Close dropdowns on outside click
  useEffect(() => {
    function handler(e: MouseEvent) {
      if (dropRef.current && !dropRef.current.contains(e.target as Node)) setUserOpen(false);
      if (bellRef.current && !bellRef.current.contains(e.target as Node)) setBellOpen(false);
    }
    document.addEventListener("mousedown", handler);
    return () => document.removeEventListener("mousedown", handler);
  }, []);

  // Fetch notifications on mount and every 60s
  useEffect(() => {
    const load = async () => {
      try {
        const res = await fetch("/api/v1/notifications");
        if (!res.ok) return;
        const data = await res.json();
        setNotifs(data.notifications ?? []);
        setUnread(data.unread ?? 0);
      } catch {}
    };
    load();
    const iv = setInterval(load, 60_000);
    return () => clearInterval(iv);
  }, []);

  const pageTitle = crumbs[crumbs.length - 1]?.label ?? "Dashboard";

  return (
    <header className="sticky top-0 z-30 flex h-16 items-center gap-4 border-b border-slate-200/80 bg-white/90 backdrop-blur-md px-4 sm:px-6">
      {/* Hamburger — mobile */}
      <button
        onClick={toggleOpen}
        className="flex h-9 w-9 items-center justify-center rounded-xl border border-slate-200 text-slate-600 hover:bg-slate-100 transition-colors lg:hidden"
        aria-label="Open menu"
      >
        <Menu className="h-4.5 w-4.5" />
      </button>

      {/* Breadcrumbs */}
      <div className="hidden sm:flex items-center gap-1.5 text-sm min-w-0 flex-1">
        {crumbs.map((c, i) => (
          <span key={i} className="flex items-center gap-1.5 min-w-0">
            {i > 0 && <span className="text-slate-300 text-xs">/</span>}
            {c.href && i < crumbs.length - 1 ? (
              <Link href={c.href} className="text-slate-400 hover:text-slate-700 transition-colors truncate">
                {c.label}
              </Link>
            ) : (
              <span className="font-semibold text-slate-800 truncate">{c.label}</span>
            )}
          </span>
        ))}
      </div>

      {/* Mobile: page title */}
      <span className="sm:hidden flex-1 text-sm font-semibold text-slate-800 truncate">{pageTitle}</span>

      {/* Right side actions */}
      <div className="flex items-center gap-1.5 ml-auto">

        {/* Notification Bell */}
        <div className="relative" ref={bellRef}>
          <button
            onClick={() => setBellOpen(!bellOpen)}
            className="relative flex h-9 w-9 items-center justify-center rounded-xl border border-slate-200 text-slate-500 hover:bg-slate-50 hover:text-slate-700 transition-colors"
            aria-label="Notifications"
          >
            <Bell className="h-4 w-4" />
            {unread > 0 && (
              <span className="absolute -top-0.5 -right-0.5 flex h-4 w-4 items-center justify-center rounded-full bg-red-500 text-[9px] font-bold text-white">
                {unread > 9 ? "9+" : unread}
              </span>
            )}
          </button>

          {bellOpen && (
            <div className="absolute right-0 mt-2 w-80 rounded-2xl border border-slate-200 bg-white shadow-xl shadow-slate-200/80 z-50 animate-in fade-in slide-in-from-top-1 duration-150 overflow-hidden">
              <div className="px-4 py-3 border-b border-slate-100 flex items-center justify-between">
                <p className="text-sm font-semibold text-slate-800">Pending Actions</p>
                {unread > 0 && (
                  <span className="rounded-full bg-red-100 px-2 py-0.5 text-[10px] font-semibold text-red-600">
                    {unread} new
                  </span>
                )}
              </div>
              <div className="max-h-72 overflow-y-auto divide-y divide-slate-50">
                {notifs.length === 0 ? (
                  <p className="px-4 py-6 text-center text-xs text-slate-400">All caught up! No pending actions.</p>
                ) : (
                  notifs.slice(0, 8).map((n) => (
                    <Link
                      key={n.id}
                      href={n.href}
                      onClick={() => setBellOpen(false)}
                      className="flex items-start gap-3 px-4 py-3 hover:bg-slate-50 transition-colors"
                    >
                      <div className="mt-0.5 flex h-6 w-6 items-center justify-center rounded-lg bg-slate-100 flex-shrink-0">
                        {typeIcon[n.type]}
                      </div>
                      <div className="min-w-0">
                        <p className="text-xs font-medium text-slate-800 truncate">{n.title}</p>
                        <p className="text-[10px] text-slate-400 truncate mt-0.5">{n.subtitle}</p>
                      </div>
                    </Link>
                  ))
                )}
              </div>
              {notifs.length > 0 && (
                <div className="px-4 py-2.5 border-t border-slate-100">
                  <Link
                    href="/leave-requests"
                    onClick={() => setBellOpen(false)}
                    className="text-xs text-teal-600 hover:text-teal-700 font-medium"
                  >
                    View all pending →
                  </Link>
                </div>
              )}
            </div>
          )}
        </div>

        {/* User dropdown */}
        <div className="relative" ref={dropRef}>
          <button
            onClick={() => setUserOpen(!userOpen)}
            className="flex items-center gap-2.5 rounded-xl border border-slate-200 pl-1.5 pr-3 py-1.5 hover:bg-slate-50 transition-colors cursor-pointer"
          >
            <div className="flex h-7 w-7 items-center justify-center rounded-lg bg-gradient-to-br from-[#1A3A5C] to-[#2D5F8A] text-xs font-bold text-white shadow-sm">
              {session?.user?.name?.[0]?.toUpperCase() ?? "A"}
            </div>
            <div className="hidden sm:block text-left">
              <p className="text-xs font-semibold text-slate-800 leading-none">
                {session?.user?.name?.split(" ")[0] ?? "Admin"}
              </p>
              <p className="text-[10px] text-slate-500 mt-0.5">Administrator</p>
            </div>
            <ChevronDown className={cn("h-3.5 w-3.5 text-slate-400 transition-transform duration-200", userOpen && "rotate-180")} />
          </button>

          {userOpen && (
            <div className="absolute right-0 mt-2 w-56 rounded-2xl border border-slate-200 bg-white shadow-xl shadow-slate-200/80 py-1.5 z-50 animate-in fade-in slide-in-from-top-1 duration-150">
              <div className="px-4 py-2.5 border-b border-slate-100">
                <p className="text-xs font-semibold text-slate-800">{session?.user?.name}</p>
                <p className="text-[10px] text-slate-400 truncate mt-0.5">{session?.user?.email}</p>
              </div>
              <div className="py-1">
                <Link
                  href="/profile"
                  onClick={() => setUserOpen(false)}
                  className="flex items-center gap-2.5 px-4 py-2 text-sm text-slate-700 hover:bg-slate-50 hover:text-slate-900 transition-colors cursor-pointer"
                >
                  <User className="h-4 w-4 text-slate-400" />
                  Edit Profile
                </Link>
              </div>
              <div className="border-t border-slate-100 pt-1">
                <button
                  onClick={() => signOut({ callbackUrl: "/login" })}
                  className="flex w-full items-center gap-2.5 px-4 py-2 text-sm text-red-600 hover:bg-red-50 hover:text-red-700 transition-colors cursor-pointer"
                >
                  <LogOut className="h-4 w-4" />
                  Sign out
                </button>
              </div>
            </div>
          )}
        </div>
      </div>
    </header>
  );
}
