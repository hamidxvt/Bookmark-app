"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import { cn } from "@/lib/utils";
import {
  LayoutDashboard, Navigation, UserCheck, ClipboardList, Users,
  Globe, Package, BookOpen, MessageSquare, Bell, ChevronDown,
  LogOut, PanelLeftClose, PanelLeftOpen, X,
  Calendar, Clock, AlertTriangle,
} from "lucide-react";
import { useState } from "react";
import { signOut, useSession } from "next-auth/react";
import { useSidebar } from "./SidebarContext";

const NAV = [
  {
    section: "GENERAL",
    items: [
      { icon: LayoutDashboard, label: "Home", href: "/dashboard" },
      {
        icon: Navigation,
        label: "Live Location",
        children: [
          { label: "All Cities", href: "/location" },
          { label: "Karachi", href: "/location/karachi" },
          { label: "Lahore", href: "/location/lahore" },
          { label: "Multan", href: "/location/multan" },
        ],
      },
    ],
  },
  {
    section: "SALES",
    items: [
      {
        icon: UserCheck,
        label: "Sales Team",
        children: [
          { label: "Team List", href: "/bookers" },
          { label: "Add Member", href: "/bookers/add" },
        ],
      },
      {
        icon: ClipboardList,
        label: "Visits",
        children: [
          { label: "All Visits", href: "/visits" },
          { label: "Today's Visits", href: "/visits/today" },
          { label: "Schedule Visit", href: "/visits/add" },
        ],
      },
    ],
  },
  {
    section: "MASTER DATA",
    items: [
      {
        icon: Users,
        label: "Customers",
        children: [
          { label: "Customer List", href: "/customers" },
          { label: "Add Customer", href: "/customers/add" },
        ],
      },
      {
        icon: Globe,
        label: "Locations",
        children: [
          { label: "Cities", href: "/locations/cities" },
          { label: "Zones / Regions", href: "/locations/zones" },
          { label: "Areas", href: "/locations/areas" },
        ],
      },
    ],
  },
  {
    section: "PRODUCTS",
    items: [
      {
        icon: Package,
        label: "Products",
        children: [
          { label: "Products List", href: "/products" },
          { label: "Add Product", href: "/products/add" },
        ],
      },
      {
        icon: BookOpen,
        label: "Catalog",
        children: [
          { label: "Brands", href: "/products/brands" },
          { label: "Subjects", href: "/products/subjects" },
          { label: "Series", href: "/products/series" },
        ],
      },
    ],
  },
  {
    section: "FIELD OPS",
    items: [
      { icon: Clock, label: "Attendance", href: "/attendance" },
      { icon: Calendar, label: "Leave Requests", href: "/leaves" },
      { icon: AlertTriangle, label: "Missed Visits", href: "/missed-visits" },
    ],
  },
  {
    section: "SUPPORT",
    items: [
      { icon: Bell, label: "Requests", href: "/requests" },
      { icon: MessageSquare, label: "Inbox", href: "/inbox" },
    ],
  },
];

function NavItem({
  icon: Icon,
  label,
  href,
  children,
  collapsed,
  onNavigate,
}: {
  icon: React.ElementType;
  label: string;
  href?: string;
  children?: { label: string; href: string }[];
  collapsed: boolean;
  onNavigate: () => void;
}) {
  const pathname = usePathname();
  const isActive = href
    ? pathname === href || (href !== "/dashboard" && pathname.startsWith(href))
    : children?.some((c) => pathname === c.href || pathname.startsWith(c.href + "/"));
  const [open, setOpen] = useState(!!isActive);

  if (href) {
    return (
      <Link
        href={href}
        onClick={onNavigate}
        title={collapsed ? label : undefined}
        className={cn(
          "group relative flex items-center gap-3 rounded-xl px-3 py-2.5 text-sm font-medium transition-all duration-150 cursor-pointer",
          isActive
            ? "bg-sky-500/15 text-sky-400 shadow-sm"
            : "text-slate-400 hover:bg-white/5 hover:text-slate-200"
        )}
      >
        <Icon className={cn("h-[18px] w-[18px] shrink-0 transition-colors", isActive ? "text-sky-400" : "text-slate-500 group-hover:text-slate-300")} />
        {!collapsed && <span className="flex-1 truncate">{label}</span>}
        {!collapsed && isActive && (
          <span className="h-1.5 w-1.5 rounded-full bg-sky-400 shadow-sky-400/50 shadow-sm" />
        )}
        {/* Tooltip when collapsed */}
        {collapsed && (
          <div className="pointer-events-none absolute left-full ml-3 z-50 hidden group-hover:flex items-center">
            <div className="rounded-lg bg-slate-800 px-3 py-1.5 text-xs font-medium text-white shadow-lg whitespace-nowrap border border-white/10">
              {label}
            </div>
          </div>
        )}
      </Link>
    );
  }

  return (
    <div>
      <button
        onClick={() => { if (!collapsed) setOpen(!open); }}
        title={collapsed ? label : undefined}
        className={cn(
          "group relative flex w-full items-center gap-3 rounded-xl px-3 py-2.5 text-sm font-medium transition-all duration-150 cursor-pointer",
          isActive ? "text-slate-200 bg-white/5" : "text-slate-400 hover:bg-white/5 hover:text-slate-200"
        )}
      >
        <Icon className={cn("h-[18px] w-[18px] shrink-0 transition-colors", isActive ? "text-sky-400" : "text-slate-500 group-hover:text-slate-300")} />
        {!collapsed && (
          <>
            <span className="flex-1 text-left truncate">{label}</span>
            <ChevronDown className={cn("h-3.5 w-3.5 shrink-0 transition-transform duration-200 text-slate-600", open && "rotate-180")} />
          </>
        )}
        {collapsed && (
          <div className="pointer-events-none absolute left-full ml-3 z-50 hidden group-hover:flex items-center">
            <div className="rounded-lg bg-slate-800 px-3 py-1.5 text-xs font-medium text-white shadow-lg whitespace-nowrap border border-white/10">
              {label}
            </div>
          </div>
        )}
      </button>

      {!collapsed && open && (
        <div className="ml-2 mt-0.5 space-y-0.5 border-l-2 border-white/5 pl-4">
          {children?.map((c) => {
            const childActive = pathname === c.href || pathname.startsWith(c.href + "/");
            return (
              <Link
                key={c.href}
                href={c.href}
                onClick={onNavigate}
                className={cn(
                  "flex items-center gap-2 rounded-lg px-3 py-2 text-xs font-medium transition-all duration-150 cursor-pointer",
                  childActive
                    ? "bg-sky-500/10 text-sky-400"
                    : "text-slate-500 hover:bg-white/5 hover:text-slate-300"
                )}
              >
                <span className={cn("h-1 w-1 rounded-full shrink-0", childActive ? "bg-sky-400" : "bg-slate-600")} />
                {c.label}
              </Link>
            );
          })}
        </div>
      )}
    </div>
  );
}

function SidebarContent({ collapsed, onNavigate }: { collapsed: boolean; onNavigate: () => void }) {
  const { data: session } = useSession();

  return (
    <div className="flex h-full flex-col">
      {/* Logo */}
      <div className={cn(
        "flex h-16 shrink-0 items-center border-b border-white/5 transition-all duration-300",
        collapsed ? "justify-center px-3" : "gap-3 px-5"
      )}>
        <div className="flex h-9 w-9 shrink-0 items-center justify-center rounded-xl bg-gradient-to-br from-sky-400 to-blue-600 shadow-lg shadow-sky-500/20">
          <BookOpen className="h-4.5 w-4.5 text-white" />
        </div>
        {!collapsed && (
          <div className="min-w-0 flex-1">
            <p className="text-sm font-bold text-white leading-none truncate">Bookmark FFM</p>
            <p className="text-[10px] text-slate-500 mt-0.5">Field Force Manager</p>
          </div>
        )}
      </div>

      {/* Navigation */}
      <nav className="flex-1 overflow-y-auto overflow-x-hidden py-3 px-2.5 space-y-4 scrollbar-thin scrollbar-track-transparent scrollbar-thumb-white/5">
        {NAV.map((s) => (
          <div key={s.section}>
            {!collapsed && (
              <p className="mb-1.5 px-3 text-[9px] font-bold uppercase tracking-widest text-slate-600">
                {s.section}
              </p>
            )}
            {collapsed && <div className="mb-1.5 h-px bg-white/5 mx-2" />}
            <div className="space-y-0.5">
              {s.items.map((item) => (
                <NavItem key={item.label} {...item} collapsed={collapsed} onNavigate={onNavigate} />
              ))}
            </div>
          </div>
        ))}
      </nav>

      {/* User + Signout */}
      <div className="shrink-0 border-t border-white/5 p-2.5 space-y-1">
        {!collapsed && (
          <div className="flex items-center gap-3 rounded-xl px-3 py-2.5 mb-1">
            <div className="flex h-8 w-8 shrink-0 items-center justify-center rounded-lg bg-gradient-to-br from-sky-400 to-blue-600 text-xs font-bold text-white">
              {session?.user?.name?.[0]?.toUpperCase() ?? "A"}
            </div>
            <div className="min-w-0">
              <p className="text-xs font-semibold text-slate-300 truncate">{session?.user?.name ?? "Admin"}</p>
              <p className="text-[10px] text-slate-600 truncate">{session?.user?.email ?? ""}</p>
            </div>
          </div>
        )}
        <button
          onClick={() => signOut({ callbackUrl: "/login" })}
          title={collapsed ? "Sign out" : undefined}
          className={cn(
            "group relative flex w-full items-center gap-3 rounded-xl px-3 py-2.5 text-sm font-medium text-slate-500 hover:bg-red-500/10 hover:text-red-400 transition-all duration-150 cursor-pointer",
            collapsed && "justify-center"
          )}
        >
          <LogOut className="h-4 w-4 shrink-0" />
          {!collapsed && <span>Sign out</span>}
          {collapsed && (
            <div className="pointer-events-none absolute left-full ml-3 z-50 hidden group-hover:flex items-center">
              <div className="rounded-lg bg-slate-800 px-3 py-1.5 text-xs font-medium text-white shadow-lg whitespace-nowrap border border-white/10">
                Sign out
              </div>
            </div>
          )}
        </button>
      </div>
    </div>
  );
}

export default function Sidebar() {
  const { open, collapsed, toggleCollapsed, closeDrawer } = useSidebar();

  const sidebarWidth = collapsed ? "w-[70px]" : "w-60";

  return (
    <>
      {/* Mobile overlay backdrop */}
      {open && (
        <div
          className="fixed inset-0 z-40 bg-black/60 backdrop-blur-sm lg:hidden"
          onClick={closeDrawer}
        />
      )}

      {/* Mobile drawer */}
      <aside className={cn(
        "fixed inset-y-0 left-0 z-50 flex w-64 flex-col bg-[#0a1628] border-r border-white/5 shadow-2xl shadow-black/50 transition-transform duration-300 ease-in-out lg:hidden",
        open ? "translate-x-0" : "-translate-x-full"
      )}>
        {/* Mobile close button */}
        <button
          onClick={closeDrawer}
          className="absolute top-4 right-4 flex h-7 w-7 items-center justify-center rounded-lg text-slate-500 hover:bg-white/10 hover:text-slate-300 transition-colors z-10"
        >
          <X className="h-4 w-4" />
        </button>
        <SidebarContent collapsed={false} onNavigate={closeDrawer} />
      </aside>

      {/* Desktop sidebar */}
      <aside className={cn(
        "hidden lg:flex flex-col fixed inset-y-0 left-0 z-40 bg-[#0a1628] border-r border-white/5 transition-all duration-300 ease-in-out",
        sidebarWidth
      )}>
        {/* Collapse toggle */}
        <button
          onClick={toggleCollapsed}
          className="absolute -right-3 top-20 z-50 flex h-6 w-6 items-center justify-center rounded-full border border-slate-700 bg-[#0a1628] text-slate-500 hover:text-slate-300 hover:border-slate-500 transition-all shadow-md cursor-pointer"
          title={collapsed ? "Expand sidebar" : "Collapse sidebar"}
        >
          {collapsed
            ? <PanelLeftOpen className="h-3 w-3" />
            : <PanelLeftClose className="h-3 w-3" />
          }
        </button>
        <SidebarContent collapsed={collapsed} onNavigate={() => {}} />
      </aside>
    </>
  );
}
