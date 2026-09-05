import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET(_req: Request, { params }: { params: Promise<{ id: string }> }) {
  try {
    const { id } = await params;
    const visitId = parseInt(id);

    const visit = await prisma.visit.findUnique({
      where: { id: visitId },
      include: {
        booker: { select: { id: true, name: true, email: true, phone: true, designation: true, profilePhoto: true } },
        customer: {
          select: {
            id: true, name: true, customerType: true, ownerName: true, ownerPhone: true,
            address: true, latitude: true, longitude: true,
            city: { select: { id: true, name: true } },
            area: { select: { id: true, name: true } },
          },
        },
        orders: {
          select: { id: true, orderDate: true, status: true, totalAmount: true },
          orderBy: { orderDate: "desc" },
        },
        missedReason: true,
        eta: true,
      },
    });

    if (!visit) {
      return NextResponse.json({ success: false, error: "Visit not found" }, { status: 404 });
    }

    return NextResponse.json({ success: true, data: visit });
  } catch (err) {
    console.error("[api/v1/visits/[id]]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch visit" }, { status: 500 });
  }
}
