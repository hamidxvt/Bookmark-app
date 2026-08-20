import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// GET /api/mobile/visits/[id] — fetch a single visit with customer detail
export async function GET(
  req: Request,
  { params }: { params: Promise<{ id: string }> }
) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const { id } = await params;
    const visitId = parseInt(id);

    const visit = await prisma.visit.findUnique({
      where: { id: visitId },
      include: {
        customer: {
          select: {
            id: true, name: true, customerType: true, category: true,
            ownerName: true, ownerPhone: true, email: true,
            address: true, latitude: true, longitude: true,
            workingPriority: true,
          },
        },
      },
    });

    if (!visit) {
      return NextResponse.json({ success: false, error: "Visit not found" }, { status: 404 });
    }

    if (visit.bookerId !== user.id) {
      return NextResponse.json({ success: false, error: "Not your visit" }, { status: 403 });
    }

    return NextResponse.json({
      success: true,
      data: {
        id: visit.id,
        customerId: visit.customerId,
        customerName: visit.customer.name,
        customerType: visit.customer.customerType,
        contact: visit.customer.ownerName ?? "",
        phone: visit.customer.ownerPhone ?? "",
        address: visit.customer.address ?? "",
        latitude: visit.customer.latitude ? Number(visit.customer.latitude) : null,
        longitude: visit.customer.longitude ? Number(visit.customer.longitude) : null,
        customer: {
          id: visit.customer.id,
          name: visit.customer.name,
          type: visit.customer.customerType,
          category: visit.customer.category ?? null,
          ownerName: visit.customer.ownerName ?? "",
          ownerPhone: visit.customer.ownerPhone ?? "",
          email: visit.customer.email ?? "",
          address: visit.customer.address ?? "",
          latitude: visit.customer.latitude ? Number(visit.customer.latitude) : null,
          longitude: visit.customer.longitude ? Number(visit.customer.longitude) : null,
        },
        priority: visit.priority ?? "normal",
        status: visit.status.toLowerCase(),
        checkInAt: visit.checkInAt,
        checkOutAt: visit.checkOutAt,
        notes: visit.notes ?? "",
        visitReport: visit.visitReport ?? "",
        visitDate: visit.visitDate,
        isAdhoc: visit.isAdhoc ?? false,
      },
    });
  } catch (err) {
    console.error("[mobile/visits/[id] GET]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
