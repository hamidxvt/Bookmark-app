"use client";

import { useSidebar } from "./SidebarContext";
import { cn } from "@/lib/utils";

export default function MainContent({ children }: { children: React.ReactNode }) {
  const { collapsed } = useSidebar();

  return (
    <div className={cn(
      "flex flex-1 flex-col min-w-0 transition-all duration-300 ease-in-out",
      // On desktop, offset by sidebar width
      collapsed ? "lg:pl-[70px]" : "lg:pl-60"
    )}>
      {children}
    </div>
  );
}
