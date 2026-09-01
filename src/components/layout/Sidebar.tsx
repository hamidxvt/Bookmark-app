"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import { cn } from "@/lib/utils";
import {
  LayoutDashboard, Navigation, UserCheck, ClipboardList, Users,
  Package, ChevronDown, LogOut, PanelLeftClose, PanelLeftOpen, X,
  Clock, AlertTriangle, Banknote, Download, Zap, MapPin, MessageSquare, Database, Activity,
} from "lucide-react";
import { useState } from "react";
import { signOut } from "next-auth/react";
import { useSidebar } from "./SidebarContext";

const NAV = [
  {
    section: "GENERAL",
    items: [
      { icon: LayoutDashboard, label: "Home",          href: "/dashboard"     },
      { icon: Activity,        label: "Live Activity", href: "/live-activity" },
      { icon: Navigation,      label: "Live Location", href: "/location"      },
    ],
  },
  {
    section: "SALES",
    items: [
      { icon: UserCheck,    label: "Sales Team", href: "/bookers" },
      { icon: ClipboardList, label: "Visits",    href: "/visits"  },
    ],
  },
  {
    section: "LOCATIONS & MANAGEMENT",
    items: [
      {
        icon: MapPin,
        label: "City Management",
        href: "/locations/cities",
      },
      {
        icon: Users,
        label: "Customers",
        children: [
          { label: "Customer List",    href: "/customers"          },
          { label: "Add Customer",     href: "/customers/add"      },
          { label: "Update Requests",  href: "/customer-updates"   },
        ],
      },
      {
        icon: Package,
        label: "Products",
        children: [
          { label: "Products List", href: "/products"     },
          { label: "Add Product",   href: "/products/add" },
          { label: "Brands",        href: "/products/brands"   },
          { label: "Subjects",      href: "/products/subjects" },
          { label: "Series",        href: "/products/series"   },
        ],
      },
    ],
  },
  {
    section: "FIELD OPS",
    items: [
      { icon: Clock,         label: "Attendance",    href: "/attendance"   },
      { icon: AlertTriangle, label: "Missed Visits", href: "/missed-visits" },
      { icon: Navigation,    label: "Ad-hoc Visits", href: "/adhoc-visits"  },
      { icon: Package,       label: "Samples",       href: "/samples"      },
      { icon: Banknote,      label: "Payroll",       href: "/payroll"      },
    ],
  },
  {
    section: "REPORTS",
    items: [
      {
        icon: Download,
        label: "Export Data",
        children: [
          { label: "Visits",     href: "/reports" },
          { label: "Officers",   href: "/reports" },
          { label: "Customers",  href: "/reports" },
          { label: "Attendance", href: "/reports" },
        ],
      },
    ],
  },
  {
    section: "ADMIN",
    items: [
      { icon: Zap,            label: "Run Schedulers",  href: "/scheduler"    },
      { icon: MessageSquare,  label: "Support Tickets", href: "/requests"     },
      { icon: Database,       label: "Data Import",     href: "/data-import"  },
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
            ? "bg-white/20 text-white shadow-sm"
            : "text-red-200 hover:bg-white/10 hover:text-white"
        )}
      >
        <Icon className={cn("h-[18px] w-[18px] shrink-0 transition-colors", isActive ? "text-white" : "text-red-300 group-hover:text-white")} />
        {!collapsed && <span className="flex-1 truncate">{label}</span>}
        {!collapsed && isActive && (
          <span className="h-1.5 w-1.5 rounded-full bg-white shadow-white/50 shadow-sm" />
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
          isActive ? "text-white bg-white/10" : "text-red-200 hover:bg-white/10 hover:text-white"
        )}
      >
        <Icon className={cn("h-[18px] w-[18px] shrink-0 transition-colors", isActive ? "text-white" : "text-red-300 group-hover:text-white")} />
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
                    ? "bg-white/15 text-white font-semibold"
                    : "text-red-200/70 hover:bg-white/10 hover:text-white"
                )}
              >
                <span className={cn("h-1 w-1 rounded-full shrink-0", childActive ? "bg-white" : "bg-red-300/40")} />
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
  return (
    <div className="flex h-full flex-col">
      {/* Logo */}
      <div className={cn(
        "flex h-16 shrink-0 items-center border-b border-white/5 transition-all duration-300",
        collapsed ? "justify-center px-3" : "gap-3 px-5"
      )}>
        <div className="h-9 w-9 shrink-0 rounded-lg overflow-hidden shadow-md flex items-center justify-center bg-white p-1">
          {/* eslint-disable-next-line @next/next/no-img-element */}
          <img src="/bookmark-logo.png" alt="Bookmark" className="h-full w-full object-contain" />
        </div>
        {!collapsed && (
          <div className="min-w-0 flex-1">
            <p className="text-sm font-black text-white leading-none truncate tracking-widest">BOOKMARK</p>
            <p className="text-[9px] text-red-200/70 font-semibold mt-0.5 tracking-wider uppercase">Field Force Manager</p>
          </div>
        )}
      </div>

      {/* Navigation */}
      <nav className="flex-1 overflow-y-auto overflow-x-hidden py-3 px-2.5 space-y-4 scrollbar-thin scrollbar-track-transparent scrollbar-thumb-white/5">
        {NAV.map((s) => (
          <div key={s.section}>
            {!collapsed && (
              <p className="mb-1.5 px-3 text-[9px] font-bold uppercase tracking-widest text-red-200/60">
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

      {/* Signout */}
      <div className="shrink-0 border-t border-white/5 p-2.5">
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
        "fixed inset-y-0 left-0 z-50 flex w-64 flex-col bg-[#9B0B22] border-r border-white/10 shadow-2xl shadow-black/50 transition-transform duration-300 ease-in-out lg:hidden",
        open ? "translate-x-0" : "-translate-x-full"
      )}>
        {/* Mobile close button */}
        <button
          onClick={closeDrawer}
          className="absolute top-4 right-4 flex h-7 w-7 items-center justify-center rounded-lg text-white/60 hover:bg-white/10 hover:text-white transition-colors z-10"
        >
          <X className="h-4 w-4" />
        </button>
        <SidebarContent collapsed={false} onNavigate={closeDrawer} />
      </aside>

      {/* Desktop sidebar */}
      <aside className={cn(
        "hidden lg:flex flex-col fixed inset-y-0 left-0 z-40 bg-[#9B0B22] border-r border-white/10 transition-all duration-300 ease-in-out",
        sidebarWidth
      )}>
        {/* Collapse toggle */}
        <button
          onClick={toggleCollapsed}
          className="absolute -right-3 top-20 z-50 flex h-6 w-6 items-center justify-center rounded-full border border-red-300/30 bg-[#9B0B22] text-white/60 hover:text-white hover:border-red-200/50 transition-all shadow-md cursor-pointer"
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
