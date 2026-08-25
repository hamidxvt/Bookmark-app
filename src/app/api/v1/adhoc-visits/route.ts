import { NextRequest, NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET(req: NextRequest) {
  try {
    const search   = req.nextUrl.searchParams.get("search") ?? "";
    const filter   = req.nextUrl.searchParams.get("filter") ?? "all";

    const where: Record<string, unknown> = { isAdhoc: true };

    if (filter === "today") {
      const today    = new Date(); today.setHours(0, 0, 0, 0);
      const tomorrow = new Date(today); tomorrow.setDate(tomorrow.getDate() + 1);
      where.visitDate = { gte: today, lt: tomorrow };
    }

    const visits = await (prisma as any).visit.findMany({
      where,
      include: {
        booker:   { select: { id: true, name: true, email: true } },
        customer: { select: { id: true, name: true, ownerPhone: true, address: true, city: { select: { name: true } } } },
      },
      orderBy: { createdAt: "desc" },
      take: 200,
    });

    const filtered = search
      ? visits.filter((v: any) =>
          v.booker?.name?.toLowerCase().includes(search.toLowerCase()) ||
          v.customer?.name?.toLowerCase().includes(search.toLowerCase())
        )
      : visits;

    return NextResponse.json({ success: true, data: filtered });
  } catch (error) {
    console.error("Failed to fetch ad-hoc visits:", error);
    return NextResponse.json({ success: false, error: { message: "Failed to fetch visits" } }, { status: 500 });
  }
}
