import { NextResponse } from "next/server";
import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";
import { prisma } from "@/lib/prisma";

export async function GET() {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ error: "Unauthorized" }, { status: 401 });

  const [pendingLeaves, pendingSamples, pendingMissedVisits, pendingRequests] = await Promise.all([
    prisma.leaveRequest.findMany({
      where: { status: "pending" },
      orderBy: { createdAt: "desc" },
      take: 10,
      include: { booker: { select: { name: true } } },
    }),
    prisma.sampleRequest.findMany({
      where: { status: "pending" },
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
      href: "/inbox",
      time: l.createdAt,
      unread: true,
    })),
    ...pendingSamples.map((s) => ({
      id: `sample-${s.id}`,
      type: "request" as const,
      title: `Sample request from ${s.booker.name}`,
      subtitle: s.productName,
      href: "/inbox",
      time: s.createdAt,
      unread: true,
    })),
    ...pendingMissedVisits.map((m) => ({
      id: `missed-${m.id}`,
      type: "missed" as const,
      title: `Missed visit: ${m.visit.customer.name}`,
      subtitle: `By ${m.visit.booker.name}`,
      href: "/missed-visits",
      time: m.createdAt,
      unread: true,
    })),
    ...pendingRequests.map((r) => ({
      id: `req-${r.id}`,
      type: "request" as const,
      title: `Support: ${r.title}`,
      subtitle: `By ${r.booker.name} · ${r.category}`,
      href: "/requests",
      time: r.createdAt,
      unread: true,
    })),
  ].sort((a, b) => new Date(b.time).getTime() - new Date(a.time).getTime());

  const unread =
    pendingLeaves.length + pendingSamples.length + pendingMissedVisits.length + pendingRequests.length;

  return NextResponse.json({
    total: notifications.length,
    unread,
    notifications,
  });
}
