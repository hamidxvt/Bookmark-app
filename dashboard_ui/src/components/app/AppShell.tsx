import type { ReactNode } from "react";

import { AppSidebar } from "@/components/app/Sidebar";
import { Topbar } from "@/components/app/Topbar";

export function AppShell({
  title,
  subtitle,
  onRefresh,
  children,
}: {
  title: string;
  subtitle?: string;
  onRefresh?: () => void;
  children: ReactNode;
}) {
  return (
    <div className="min-h-screen bg-background">
      <AppSidebar />
      <div className="lg:pl-[260px]">
        <Topbar title={title} subtitle={subtitle} onRefresh={onRefresh} />
        <main className="px-6 py-6 lg:px-8">{children}</main>
      </div>
    </div>
  );
}
