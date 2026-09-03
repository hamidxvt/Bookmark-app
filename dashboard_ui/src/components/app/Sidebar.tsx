import { Link, useRouterState } from "@tanstack/react-router";
import {
  Activity,
  BarChart3,
  Bell,
  Boxes,
  Building2,
  CalendarCheck,
  ChevronDown,
  ClipboardList,
  Download,
  FlaskConical,
  LayoutDashboard,
  LifeBuoy,
  MapPin,
  PlayCircle,
  Radio,
  Route as RouteIcon,
  Settings,
  Timer,
  Upload,
  UserRound,
  Users,
  Wallet,
} from "lucide-react";

import { useState } from "react";

import logo from "@/assets/bookmark-logo.png.asset.json";
import { cn } from "@/lib/utils";

type Item = { label: string; to: string; icon: React.ElementType; badge?: string };

const groups: { title: string; items: Item[] }[] = [
  {
    title: "General",
    items: [
      { label: "Dashboard", to: "/dashboard", icon: LayoutDashboard },
      { label: "Live Activity", to: "/live-activity", icon: Activity },
      { label: "Live Location", to: "/live-location", icon: MapPin },
      { label: "Notifications", to: "/notifications", icon: Bell },
    ],
  },
  {
    title: "Sales",
    items: [
      { label: "Sales Team", to: "/sales-team", icon: Users },
      { label: "Visits", to: "/visits", icon: RouteIcon },
    ],
  },
  {
    title: "Locations & Management",
    items: [
      { label: "City Management", to: "/city-management", icon: Building2 },
      { label: "Customers", to: "/customers", icon: UserRound },
      { label: "Products", to: "/products", icon: Boxes },
    ],
  },
  {
    title: "Field Operations",
    items: [
      { label: "My Shift", to: "/shift", icon: PlayCircle },
      { label: "Live Shifts", to: "/live-shifts", icon: Radio },
      { label: "Attendance", to: "/attendance", icon: CalendarCheck },
      { label: "Missed Visits", to: "/missed-visits", icon: ClipboardList, badge: "0" },
      { label: "Ad-hoc Visits", to: "/adhoc-visits", icon: Timer },
      { label: "Samples", to: "/samples", icon: FlaskConical },
      { label: "Payroll", to: "/payroll", icon: Wallet },
    ],
  },
  {
    title: "Reports",
    items: [{ label: "Export Data", to: "/export-data", icon: Download }],
  },

  {
    title: "Admin",
    items: [
      { label: "Run Schedulers", to: "/run-schedulers", icon: BarChart3 },
      { label: "Support Tickets", to: "/support-tickets", icon: LifeBuoy, badge: "3" },
      { label: "Data Import", to: "/data-import", icon: Upload },
      { label: "Settings", to: "/settings", icon: Settings },
    ],
  },
];


export function AppSidebar() {
  const pathname = useRouterState({ select: (s) => s.location.pathname });
  const [collapsed, setCollapsed] = useState<Record<string, boolean>>({});

  return (
    <aside className="fixed inset-y-0 left-0 z-30 hidden w-[260px] flex-col bg-sidebar text-sidebar-foreground lg:flex">
      <div className="flex items-center gap-3 px-6 py-6">
        <div className="flex h-11 w-11 items-center justify-center rounded-xl bg-white/95 shadow-brand">
          <img src={logo.url} alt="Bookmark" className="h-7 w-7 object-contain" />
        </div>
        <div className="leading-tight">
          <p className="text-sm font-bold tracking-[0.18em] text-white">BOOKMARK</p>
          <p className="text-[11px] text-sidebar-foreground/70">Field Force Manager</p>
        </div>
      </div>

      <nav className="scrollbar-slim flex-1 space-y-5 overflow-y-auto px-3 pb-6">
        {groups.map((group) => {
          const isOpen = !collapsed[group.title];
          return (
            <div key={group.title}>
              <button
                onClick={() => setCollapsed((c) => ({ ...c, [group.title]: !!isOpen }))}
                className="flex w-full items-center justify-between px-3 py-2 text-[10px] font-semibold uppercase tracking-[0.16em] text-sidebar-foreground/45 transition-colors hover:text-sidebar-foreground/80"
              >
                {group.title}
                <ChevronDown
                  className={cn("h-3.5 w-3.5 transition-transform duration-300", !isOpen && "-rotate-90")}
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
                    {group.items.map((item) => {
                      const active = pathname === item.to;
                      return (
                        <li key={item.to}>
                          <Link
                            to={item.to}
                            className={cn(
                              "group relative flex items-center gap-3 rounded-xl px-3 py-2.5 text-sm font-medium transition-all duration-200",
                              active
                                ? "bg-primary text-primary-foreground shadow-brand"
                                : "text-sidebar-foreground/80 hover:translate-x-0.5 hover:bg-sidebar-accent hover:text-sidebar-accent-foreground",
                            )}
                          >
                            <span
                              className={cn(
                                "absolute left-0 top-1/2 h-6 w-1 -translate-y-1/2 rounded-r-full bg-primary-foreground transition-all duration-300",
                                active ? "opacity-100" : "scale-y-0 opacity-0",
                              )}
                            />
                            <item.icon className="h-4 w-4 shrink-0" />
                            <span className="flex-1 truncate">{item.label}</span>
                            {item.badge ? (
                              <span
                                className={cn(
                                  "rounded-full px-2 py-0.5 text-[10px] font-semibold",
                                  active ? "bg-white/20" : "bg-sidebar-accent text-sidebar-accent-foreground",
                                )}
                              >
                                {item.badge}
                              </span>
                            ) : null}
                          </Link>
                        </li>
                      );
                    })}
                  </ul>
                </div>
              </div>
            </div>
          );
        })}
      </nav>

      <div className="mx-3 mb-4 rounded-2xl bg-sidebar-accent/70 p-4">
        <p className="text-xs font-semibold text-white">System healthy</p>
        <p className="mt-1 text-[11px] text-sidebar-foreground/70">
          GPS sync 99.2% · Last scheduler 02:00 AM
        </p>
      </div>
    </aside>
  );
}
