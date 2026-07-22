"use client";

import { SessionProvider } from "next-auth/react";
import type { Session } from "next-auth";
import { SidebarProvider } from "./SidebarContext";

export default function Providers({
  children,
  session,
}: {
  children: React.ReactNode;
  session: Session | null;
}) {
  return (
    <SessionProvider session={session}>
      <SidebarProvider>
        {children}
      </SidebarProvider>
    </SessionProvider>
  );
}
