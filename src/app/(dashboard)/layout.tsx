import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";
import { redirect } from "next/navigation";
import Sidebar from "@/components/layout/Sidebar";
import Header from "@/components/layout/Header";
import Providers from "@/components/layout/Providers";
import MainContent from "@/components/layout/MainContent";

export default async function DashboardLayout({ children }: { children: React.ReactNode }) {
  const session = await getServerSession(authOptions);
  if (!session) redirect("/login");

  return (
    <Providers session={session}>
      <div className="flex min-h-screen bg-slate-50">
        <Sidebar />
        <MainContent>
          <Header />
          <main className="flex-1 p-4 sm:p-6">
            {children}
          </main>
        </MainContent>
      </div>
    </Providers>
  );
}
