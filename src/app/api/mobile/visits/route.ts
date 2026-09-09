import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";
import { validateCityMatch } from "@/lib/visit-assignment";

type Visit = {
  id: number;
  bookerId: number;
  customerId: number;
  visitDate: Date;
  status: any;
  priority: string | null;
  checkInAt: Date | null;
  checkOutAt: Date | null;
  notes: string | null;
  visitReport: string | null;
  isAdhoc: boolean;
  carryForwardCount: number;
  customer: {
    id: number;
    name: string;
    customerType: string;
    ownerName: string | null;
    ownerPhone: string | null;
    address: string | null;
    latitude: any;
    longitude: any;
    workingPriority: number;
  };
};

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
            id: true, name: true, customerType: true, category: true,
            ownerName: true, ownerPhone: true, email: true,
            address: true, latitude: true, longitude: true,
            workingPriority: true, approvalStatus: true,
          },
        },
      },
      orderBy: [{ priority: "asc" }, { id: "asc" }],
    });

    return NextResponse.json({
      success: true,
      data: visits.map((v: Visit, i: number) => ({
        id: v.id,
        sequence: i + 1,
        customerId: v.customerId,
        customerName: v.customer.name,
        customerType: v.customer.customerType,
        // Flat fields for backward compat
        contact: v.customer.ownerName ?? "",
        phone: v.customer.ownerPhone ?? "",
        address: v.customer.address ?? "",
        latitude: v.customer.latitude ? Number(v.customer.latitude) : null,
        longitude: v.customer.longitude ? Number(v.customer.longitude) : null,
        // Full customer object for richer UI
        customer: {
          id: v.customer.id,
          name: v.customer.name,
          type: v.customer.customerType,
          category: (v.customer as any).category ?? null,
          ownerName: v.customer.ownerName ?? "",
          ownerPhone: v.customer.ownerPhone ?? "",
          email: (v.customer as any).email ?? "",
          address: v.customer.address ?? "",
          latitude: v.customer.latitude ? Number(v.customer.latitude) : null,
          longitude: v.customer.longitude ? Number(v.customer.longitude) : null,
        },
        priority: v.priority ?? "normal",
        status: v.status.toLowerCase(),
        checkInAt: v.checkInAt,
        checkOutAt: v.checkOutAt,
        notes: v.notes ?? "",
        visitReport: v.visitReport ?? "",
        visitDate: v.visitDate,
        isAdhoc: v.isAdhoc ?? false,
        carryForwardCount: v.carryForwardCount ?? 0,
      })),
    });
  } catch (err) {
    console.error("[mobile/visits GET]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}

// POST /api/mobile/visits — create an ad-hoc visit
export async function POST(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const body = await req.json();
    const { customerId, notes } = body;

    if (!customerId) {
      return NextResponse.json({ success: false, error: "customerId is required" }, { status: 400 });
    }

    const customer = await prisma.customer.findFirst({
      where: { id: Number(customerId), deletedAt: null },
      select: {
        id: true,
        name: true,
        cityId: true,
        customerType: true,
        ownerName: true,
        ownerPhone: true,
        address: true,
        latitude: true,
        longitude: true,
        workingPriority: true,
      },
    });
    if (!customer) {
      return NextResponse.json({ success: false, error: "Customer not found" }, { status: 404 });
    }

    const bookerCity = await prisma.booker.findUnique({
      where: { id: user.id },
      select: { cityId: true },
    });
    const cityCheck = await validateCityMatch(bookerCity?.cityId, customer.cityId);
    if (!cityCheck.ok) {
      return NextResponse.json({ success: false, error: cityCheck.error }, { status: 400 });
    }

    const today = new Date();
    today.setHours(0, 0, 0, 0);

    // Prevent duplicate ad-hoc visit for same customer today
    const existing = await prisma.visit.findFirst({
      where: { bookerId: user.id, customerId: Number(customerId), visitDate: today },
    });
    if (existing) {
      return NextResponse.json({ success: false, error: "Visit already planned for this customer today" }, { status: 409 });
    }

    const visit = await prisma.visit.create({
      data: {
        bookerId: user.id,
        customerId: Number(customerId),
        visitDate: today,
        status: "IN_PROGRESS",
        checkInAt: new Date(),
        notes: notes ?? "",
        priority: "normal",
        isAdhoc: true,
      },
    });

    return NextResponse.json({
      success: true,
      data: {
        id: visit.id,
        customerId: customer.id,
        customerName: customer.name,
        customerType: customer.customerType,
        contact: customer.ownerName ?? "",
        phone: customer.ownerPhone ?? "",
        address: customer.address ?? "",
        latitude: customer.latitude ? Number(customer.latitude) : null,
        longitude: customer.longitude ? Number(customer.longitude) : null,
        status: "in_progress",
        checkInAt: visit.checkInAt,
        notes: visit.notes ?? "",
        visitDate: visit.visitDate,
      },
    });
  } catch (err) {
    console.error("[mobile/visits POST]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
