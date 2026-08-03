import { NextResponse } from "next/server";
import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";
import { prisma } from "@/lib/prisma";

export async function GET() {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ error: "Unauthorized" }, { status: 401 });

  const [pendingLeaves, pendingMissedVisits, pendingRequests] = await Promise.all([
    prisma.leaveRequest.findMany({
      where: { status: "PENDING" },
      orderBy: { createdAt: "desc" },
      take: 10,
      include: { booker: { select: { name: true } } },
    }),
    prisma.missedVisitReason.findMany({
      where: { status: "pending" },
      orderBy: { createdAt: "desc" },
      take: 10,
      include: {
        visit: {
          include: {
            booker: { select: { name: true } },
            customer: { select: { name: true } },
          },
        },
      },
    }),
    prisma.request.findMany({
      where: { status: "PENDING" },
      orderBy: { createdAt: "desc" },
      take: 5,
      include: { booker: { select: { name: true } } },
    }),
  ]);

  const notifications = [
    ...pendingLeaves.map((l) => ({
      id: `leave-${l.id}`,
      type: "leave" as const,
      title: `Leave request from ${l.booker.name}`,
      subtitle: `${l.leaveType} · ${new Date(l.fromDate).toLocaleDateString("en-PK")}`,
      href: "/leave-requests",
      time: l.createdAt,
    })),
    ...pendingMissedVisits.map((m) => ({
      id: `missed-${m.id}`,
      type: "missed" as const,
      title: `Missed visit: ${m.visit.customer.name}`,
      subtitle: `By ${m.visit.booker.name}`,
      href: "/missed-visits",
      time: m.createdAt,
    })),
    ...pendingRequests.map((r) => ({
      id: `req-${r.id}`,
      type: "request" as const,
      title: `Support: ${r.title}`,
      subtitle: `By ${r.booker.name} · ${r.category}`,
      href: "/requests",
      time: r.createdAt,
    })),
  ].sort((a, b) => new Date(b.time).getTime() - new Date(a.time).getTime());

  return NextResponse.json({
    total: notifications.length,
    unread: pendingLeaves.length + pendingMissedVisits.length + pendingRequests.length,
    notifications,
  });
}
