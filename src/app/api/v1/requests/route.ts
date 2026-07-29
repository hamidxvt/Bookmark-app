import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const status = searchParams.get("status");

    const requests = await prisma.request.findMany({
      where: status ? { status: status.toUpperCase() as "PENDING" | "APPROVED" | "REJECTED" } : {},
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
    if (!id || !["APPROVED", "REJECTED"].includes(status?.toUpperCase())) {
      return NextResponse.json({ success: false, error: "Invalid request" }, { status: 400 });
    }

    const request = await prisma.request.update({
      where: { id },
      data: { status: status.toUpperCase(), adminNotes: adminNotes ?? null },
    });

    return NextResponse.json({ success: true, data: request });
  } catch (err) {
    console.error("[api/v1/requests PATCH]", err);
    return NextResponse.json({ success: false, error: "Failed to update request" }, { status: 500 });
  }
}
