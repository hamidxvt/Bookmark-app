"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import {
  Activity, BarChart3, Bell, Boxes, Building2, CalendarCheck,
  ChevronDown, ClipboardList, Download, FlaskConical, LayoutDashboard,
  LifeBuoy, MapPin, PlayCircle, Route as RouteIcon, Settings,
  Timer, Upload, UserRound, Users, Wallet, X, Menu,
} from "lucide-react";
import { useState } from "react";
import { signOut } from "next-auth/react";
import { cn } from "@/lib/utils";
import { useSidebar } from "./SidebarContext";

type Item = { label: string; href: string; icon: React.ElementType; badge?: string };

const groups: { title: string; items: Item[] }[] = [
  {
    title: "General",
    items: [
      { label: "Dashboard",    href: "/dashboard",     icon: LayoutDashboard },
      { label: "Live Activity", href: "/live-activity", icon: Activity },
      { label: "Live Location", href: "/location",      icon: MapPin },
      { label: "Inbox", href: "/inbox", icon: Bell },
    ],
  },
  {
    title: "Sales",
    items: [
      { label: "Sales Team", href: "/bookers", icon: Users },
      { label: "Visits",         href: "/visits",     icon: RouteIcon },
      { label: "Schedule Visit", href: "/visits/add", icon: CalendarCheck },
    ],
  },
  {
    title: "Locations & Management",
    items: [
      { label: "City Management", href: "/locations/cities", icon: Building2 },
      { label: "Customers",       href: "/customers",        icon: UserRound },
      { label: "Products",        href: "/products",         icon: Boxes },
    ],
  },
  {
    title: "Field Operations",
    items: [
      { label: "Attendance",    href: "/attendance",    icon: CalendarCheck },
      { label: "Missed Visits", href: "/missed-visits", icon: ClipboardList },
      { label: "Ad-hoc Visits", href: "/adhoc-visits",  icon: Timer },
      { label: "Samples",       href: "/samples",       icon: FlaskConical },
      { label: "Payroll",       href: "/payroll",       icon: Wallet },
    ],
  },
  {
    title: "Reports",
    items: [{ label: "Export Data", href: "/reports", icon: Download }],
  },
  {
    title: "Admin",
    items: [
      { label: "Run Schedulers",  href: "/scheduler",   icon: BarChart3 },
      { label: "Support Tickets", href: "/requests",    icon: LifeBuoy },
      { label: "Data Import",     href: "/data-import", icon: Upload },
      { label: "Settings",        href: "/settings",    icon: Settings },
    ],
  },
];

function NavItem({
  item,
  onNavigate,
}: {
  item: Item;
  onNavigate: () => void;
}) {
  const pathname = usePathname();
  const active =
    pathname === item.href ||
    (item.href !== "/dashboard" && pathname.startsWith(item.href));

  return (
    <li>
      <Link
        href={item.href}
        onClick={onNavigate}
        className={cn(
          "group relative flex items-center gap-3 rounded-xl px-3 py-2.5 text-sm font-medium transition-all duration-200",
          active
            ? "bg-primary text-primary-foreground shadow-brand"
            : "text-sidebar-foreground/80 hover:translate-x-0.5 hover:bg-sidebar-accent hover:text-sidebar-accent-foreground",
        )}
      >
        {/* Left indicator bar */}
        <span
          className={cn(
            "absolute left-0 top-1/2 h-6 w-1 -translate-y-1/2 rounded-r-full bg-primary-foreground transition-all duration-300",
            active ? "opacity-100" : "scale-y-0 opacity-0",
          )}
        />
        <item.icon className="h-4 w-4 shrink-0" />
        <span className="flex-1 truncate">{item.label}</span>
        {item.badge && (
          <span
            className={cn(
              "rounded-full px-2 py-0.5 text-[10px] font-semibold",
              active
                ? "bg-white/20 text-white"
                : "bg-sidebar-accent text-sidebar-accent-foreground",
            )}
          >
            {item.badge}
          </span>
        )}
      </Link>
    </li>
  );
}

function SidebarContent({ onNavigate }: { onNavigate: () => void }) {
  const [collapsed, setCollapsed] = useState<Record<string, boolean>>({});

  return (
    <div className="flex h-full flex-col">
      {/* Logo */}
      <div className="flex items-center gap-3 px-6 py-6">
        <div className="flex h-11 w-11 items-center justify-center rounded-xl bg-white/95 shadow-brand">
          {/* eslint-disable-next-line @next/next/no-img-element */}
          <img src="/bookmark-logo.png" alt="Bookmark" className="h-7 w-7 object-contain" />
        </div>
        <div className="leading-tight">
          <p className="text-sm font-bold tracking-[0.18em] text-white">BOOKMARK</p>
          <p className="text-[11px] text-sidebar-foreground/70">Field Force Manager</p>
        </div>
      </div>

      {/* Navigation */}
      <nav className="scrollbar-slim flex-1 space-y-5 overflow-y-auto px-3 pb-6">
        {groups.map((group) => {
          const isOpen = !collapsed[group.title];
          return (
            <div key={group.title}>
              <button
                onClick={() =>
                  setCollapsed((c) => ({ ...c, [group.title]: !!isOpen }))
                }
                className="flex w-full items-center justify-between px-3 py-2 text-[10px] font-semibold uppercase tracking-[0.16em] text-sidebar-foreground/45 transition-colors hover:text-sidebar-foreground/80"
              >
                {group.title}
                <ChevronDown
                  className={cn(
                    "h-3.5 w-3.5 transition-transform duration-300",
                    !isOpen && "-rotate-90",
                  )}
                />
              </button>
              <div
                className={cn(
                  "grid transition-all duration-300 ease-out",
                  isOpen ? "grid-rows-[1fr] opacity-100" : "grid-rows-[0fr] opacity-0",
                )}
              >
                <div className="overflow-hidden">
                  <ul className="space-y-1 pt-1">
                    {group.items.map((item) => (
                      <NavItem key={item.href} item={item} onNavigate={onNavigate} />
                    ))}
                  </ul>
                </div>
              </div>
            </div>
          );
        })}
      </nav>

      {/* System health footer */}
      <div className="mx-3 mb-4 rounded-2xl bg-sidebar-accent/70 p-4">
        <p className="text-xs font-semibold text-white">System healthy</p>
        <p className="mt-1 text-[11px] text-sidebar-foreground/70">
          GPS sync active · Railway deployed
        </p>
      </div>

      {/* Sign out */}
      <div className="shrink-0 border-t border-sidebar-border/50 p-3">
        <button
          onClick={() => signOut({ callbackUrl: "/login" })}
          className="flex w-full items-center gap-3 rounded-xl px-3 py-2.5 text-sm font-medium text-sidebar-foreground/60 transition-colors hover:bg-destructive/20 hover:text-destructive cursor-pointer"
        >
          <svg className="h-4 w-4 shrink-0" fill="none" viewBox="0 0 24 24" stroke="currentColor" strokeWidth={2}>
            <path strokeLinecap="round" strokeLinejoin="round" d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1" />
          </svg>
          Sign out
        </button>
      </div>
    </div>
  );
}

export default function Sidebar() {
  const { open, closeDrawer } = useSidebar();

  return (
    <>
      {/* Mobile backdrop */}
      {open && (
        <div
          className="fixed inset-0 z-40 bg-black/60 backdrop-blur-sm lg:hidden"
          onClick={closeDrawer}
        />
      )}

      {/* Mobile drawer */}
      <aside
        className={cn(
          "fixed inset-y-0 left-0 z-50 flex w-[260px] flex-col bg-sidebar transition-transform duration-300 ease-in-out lg:hidden",
          open ? "translate-x-0" : "-translate-x-full",
        )}
      >
        <button
          onClick={closeDrawer}
          className="absolute right-4 top-4 flex h-7 w-7 items-center justify-center rounded-lg text-white/60 hover:bg-white/10 hover:text-white transition-colors z-10"
        >
          <X className="h-4 w-4" />
        </button>
        <SidebarContent onNavigate={closeDrawer} />
      </aside>

      {/* Desktop sidebar */}
      <aside className="fixed inset-y-0 left-0 z-40 hidden w-[260px] flex-col bg-sidebar lg:flex">
        <SidebarContent onNavigate={() => {}} />
      </aside>
    </>
  );
}
