"use client";

export default function MainContent({ children }: { children: React.ReactNode }) {
  return (
    <div className="flex flex-1 flex-col min-w-0 lg:pl-[260px]">
      {children}
    </div>
  );
}
