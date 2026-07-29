import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// GET /api/mobile/visits — today's visits for logged-in booker
export async function GET(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const today = new Date();
    today.setHours(0, 0, 0, 0);
    const tomorrow = new Date(today);
    tomorrow.setDate(tomorrow.getDate() + 1);

    const visits = await prisma.visit.findMany({
      where: {
        bookerId: user.id,
        visitDate: { gte: today, lt: tomorrow },
      },
      include: {
        customer: {
          select: {
            id: true, name: true, customerType: true, ownerName: true,
            ownerPhone: true, address: true,
            latitude: true, longitude: true,
            workingPriority: true,
          },
        },
      },
      orderBy: [{ priority: "asc" }, { id: "asc" }],
    });

    return NextResponse.json({
      success: true,
      data: visits.map((v, i) => ({
        id: v.id,
        sequence: i + 1,
        customerId: v.customerId,
        customerName: v.customer.name,
        customerType: v.customer.customerType,
        contact: v.customer.ownerName ?? "",
        phone: v.customer.ownerPhone ?? "",
        address: v.customer.address ?? "",
        latitude: v.customer.latitude ? Number(v.customer.latitude) : null,
        longitude: v.customer.longitude ? Number(v.customer.longitude) : null,
        priority: v.priority ?? "normal",
        status: v.status.toLowerCase(),
        checkInAt: v.checkInAt,
        checkOutAt: v.checkOutAt,
        notes: v.notes ?? "",
        visitReport: v.visitReport ?? "",
        visitDate: v.visitDate,
      })),
    });
  } catch (err) {
    console.error("[mobile/visits GET]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
