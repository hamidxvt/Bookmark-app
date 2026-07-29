import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const status = searchParams.get("status") ?? undefined;

    const reasons = await prisma.missedVisitReason.findMany({
      where: status ? { status } : {},
      include: {
        booker: { select: { id: true, name: true, email: true } },
        visit: {
          select: {
            id: true, visitDate: true,
            customer: { select: { id: true, name: true, customerType: true } },
          },
        },
      },
      orderBy: { createdAt: "desc" },
      take: 100,
    });

    return NextResponse.json({ success: true, data: reasons });
  } catch (err) {
    console.error("[api/v1/missed-visits]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch missed visits" }, { status: 500 });
  }
}

export async function PATCH(req: Request) {
  try {
    const { id, status, adminNote } = await req.json();

    if (!id || !["approved", "rejected"].includes(status)) {
      return NextResponse.json({ success: false, error: "Invalid request" }, { status: 400 });
    }

    const reason = await prisma.missedVisitReason.update({
      where: { id },
      data: { status, adminNote: adminNote ?? null },
    });

    return NextResponse.json({ success: true, data: reason });
  } catch (err) {
    console.error("[api/v1/missed-visits PATCH]", err);
    return NextResponse.json({ success: false, error: "Failed to update" }, { status: 500 });
  }
}
