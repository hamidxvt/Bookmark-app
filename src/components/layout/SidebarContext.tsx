"use client";

import { createContext, useContext, useState, useCallback, ReactNode } from "react";

interface SidebarCtx {
  open: boolean;
  collapsed: boolean;
  toggleOpen: () => void;
  toggleCollapsed: () => void;
  closeDrawer: () => void;
}

const Ctx = createContext<SidebarCtx>({
  open: false,
  collapsed: false,
  toggleOpen: () => {},
  toggleCollapsed: () => {},
  closeDrawer: () => {},
});

export function SidebarProvider({ children }: { children: ReactNode }) {
  const [open, setOpen] = useState(false);        // mobile drawer
  const [collapsed, setCollapsed] = useState(false); // desktop collapse

  const toggleOpen = useCallback(() => setOpen(v => !v), []);
  const toggleCollapsed = useCallback(() => setCollapsed(v => !v), []);
  const closeDrawer = useCallback(() => setOpen(false), []);

  return (
    <Ctx.Provider value={{ open, collapsed, toggleOpen, toggleCollapsed, closeDrawer }}>
      {children}
    </Ctx.Provider>
  );
}

export const useSidebar = () => useContext(Ctx);
