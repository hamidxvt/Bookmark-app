import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const status = searchParams.get("status");

    const where: Record<string, unknown> = {};
    if (status) {
      const upper = status.toUpperCase();
      if (["PENDING", "RESOLVED", "REJECTED"].includes(upper)) {
        where.status = upper as "PENDING" | "RESOLVED" | "REJECTED";
      }
    }

    const requests = await prisma.request.findMany({
      where,
      include: {
        booker: { select: { id: true, name: true, email: true, phone: true } },
      },
      orderBy: { createdAt: "desc" },
      take: 100,
    });

    return NextResponse.json({
      success: true,
      data: { recordsTotal: requests.length, data: requests },
    });
  } catch (err) {
    console.error("[api/v1/requests]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch requests" }, { status: 500 });
  }
}

export async function PATCH(req: Request) {
  try {
    const { id, status, adminNotes } = await req.json();
    if (!id || !["RESOLVED", "REJECTED"].includes(status?.toUpperCase())) {
      return NextResponse.json({ success: false, error: "Invalid status. Use RESOLVED or REJECTED" }, { status: 400 });
    }

    const request = await prisma.request.update({
      where: { id },
      data: { status: status.toUpperCase() as "RESOLVED" | "REJECTED", adminNotes: adminNotes ?? null },
    });

    return NextResponse.json({ success: true, data: request });
  } catch (err) {
    console.error("[api/v1/requests PATCH]", err);
    return NextResponse.json({ success: false, error: "Failed to update request" }, { status: 500 });
  }
}
