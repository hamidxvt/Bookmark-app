import { Link, useNavigate } from "@tanstack/react-router";
import { Bell, LogOut, RefreshCw, Search, Settings, UserRound } from "lucide-react";
import { useEffect, useState } from "react";
import { toast } from "sonner";

import { Button } from "@/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { Input } from "@/components/ui/input";
import { Popover, PopoverContent, PopoverTrigger } from "@/components/ui/popover";
import { getSession, signOut, type AdminSession } from "@/lib/auth";
import { markAllNotificationsRead, markNotificationRead, useNotifications } from "@/lib/store";
import { cn } from "@/lib/utils";

export function Topbar({
  title,
  subtitle,
  onRefresh,
}: {
  title: string;
  subtitle?: string;
  onRefresh?: () => void;
}) {
  const navigate = useNavigate();
  const [session, setSession] = useState<AdminSession | null>(null);
  const items = useNotifications();
  const [refreshing, setRefreshing] = useState(false);
  const [query, setQuery] = useState("");

  useEffect(() => setSession(getSession()), []);

  const unread = items.filter((i) => i.unread).length;


  const refresh = () => {
    setRefreshing(true);
    onRefresh?.();
    setTimeout(() => {
      setRefreshing(false);
      toast.success("Data refreshed", { description: "Live figures updated just now." });
    }, 700);
  };

  return (
    <header className="sticky top-0 z-20 border-b border-border/70 bg-background/85 backdrop-blur-xl">
      <div className="flex flex-wrap items-center gap-4 px-6 py-4 lg:px-8">
        <div className="min-w-0 flex-1">
          <h1 className="truncate text-2xl font-bold tracking-tight text-foreground">{title}</h1>
          {subtitle ? <p className="mt-0.5 text-sm text-muted-foreground">{subtitle}</p> : null}
        </div>

        <form
          className="relative hidden xl:block"
          onSubmit={(e) => {
            e.preventDefault();
            const q = query.trim();
            if (!q) return;
            navigate({ to: "/customers" });
            toast.success(`Searching for "${q}"`);
          }}
        >
          <Search className="pointer-events-none absolute left-3 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
          <Input
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            placeholder="Search officers, customers, visits…"
            className="h-10 w-72 rounded-xl border-transparent bg-card pl-9 shadow-card"
          />
        </form>


        <Button variant="outline" size="icon" className="h-10 w-10 rounded-xl bg-card" onClick={refresh}>
          <RefreshCw className={cn("h-4 w-4", refreshing && "animate-spin")} />
          <span className="sr-only">Refresh</span>
        </Button>

        <Popover>
          <PopoverTrigger asChild>
            <Button variant="outline" size="icon" className="relative h-10 w-10 rounded-xl bg-card">
              <Bell className="h-4 w-4" />
              {unread > 0 && (
                <span className="absolute -right-1 -top-1 flex h-5 min-w-5 items-center justify-center rounded-full bg-primary px-1 text-[10px] font-bold text-primary-foreground">
                  {unread}
                </span>
              )}
            </Button>
          </PopoverTrigger>
          <PopoverContent align="end" className="w-96 rounded-2xl p-0 shadow-elevated">
            <div className="flex items-center justify-between border-b px-4 py-3">
              <p className="text-sm font-semibold">Notifications</p>
              <button
                className="text-xs font-medium text-primary hover:underline"
                onClick={() => {
                  markAllNotificationsRead();
                  toast.success("All notifications marked as read");
                }}
              >
                Mark all read
              </button>
            </div>
            <div className="max-h-80 overflow-y-auto">
              {items.length === 0 ? (
                <p className="p-6 text-center text-sm text-muted-foreground">You're all caught up.</p>
              ) : (
                items.slice(0, 8).map((n) => (
                  <button
                    key={n.id}
                    onClick={() => {
                      markNotificationRead(n.id);
                      navigate({ to: n.to });
                    }}
                    className={cn(
                      "flex w-full gap-3 border-b px-4 py-3 text-left transition-colors last:border-0 hover:bg-muted/60",
                      n.unread && "bg-primary-soft/60",
                    )}
                  >
                    <span
                      className={cn(
                        "mt-1.5 h-2 w-2 shrink-0 rounded-full",
                        n.unread ? "bg-primary" : "bg-border",
                      )}
                    />
                    <span className="min-w-0">
                      <span className="block text-sm font-medium text-foreground">{n.title}</span>
                      <span className="block truncate text-xs text-muted-foreground">{n.body}</span>
                      <span className="mt-1 block text-[11px] text-muted-foreground/80">{n.time}</span>
                    </span>
                  </button>
                ))
              )}
            </div>
            <div className="border-t px-4 py-3">
              <Link to="/notifications" className="text-xs font-semibold text-primary hover:underline">
                View all notifications →
              </Link>
            </div>
          </PopoverContent>

        </Popover>

        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <button className="flex items-center gap-3 rounded-xl bg-card px-3 py-2 shadow-card transition-shadow hover:shadow-elevated">
              <span className="flex h-8 w-8 items-center justify-center rounded-lg bg-navy text-xs font-bold text-navy-foreground">
                {session ? session.name.slice(0, 2).toUpperCase() : "AD"}
              </span>
              <span className="hidden text-left leading-tight sm:block">
                <span className="block text-sm font-semibold">{session?.name ?? "Admin"}</span>
                <span className="block text-[11px] text-muted-foreground">{session?.role ?? "Super Admin"}</span>
              </span>
            </button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end" className="w-52 rounded-xl">
            <DropdownMenuLabel className="text-xs text-muted-foreground">
              {session?.email ?? "admin@bookmark.com.pk"}
            </DropdownMenuLabel>
            <DropdownMenuSeparator />
            <DropdownMenuItem asChild>
              <Link to="/profile">
                <UserRound className="mr-2 h-4 w-4" /> Profile
              </Link>
            </DropdownMenuItem>

            <DropdownMenuItem asChild>
              <Link to="/settings">
                <Settings className="mr-2 h-4 w-4" /> Settings
              </Link>
            </DropdownMenuItem>
            <DropdownMenuSeparator />
            <DropdownMenuItem
              className="text-destructive focus:text-destructive"
              onClick={() => {
                signOut();
                toast.success("Signed out");
                navigate({ to: "/login", replace: true });
              }}
            >
              <LogOut className="mr-2 h-4 w-4" /> Logout
            </DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      </div>
    </header>
  );
}
