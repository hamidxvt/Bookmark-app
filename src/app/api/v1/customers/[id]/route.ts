import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

// GET /api/v1/customers/:id — full customer profile with recent visits & samples
export async function GET(_req: Request, { params }: { params: Promise<{ id: string }> }) {
  try {
    const { id } = await params;
    const customerId = parseInt(id);

    const customer = await prisma.customer.findFirst({
      where: { id: customerId, deletedAt: null },
      select: {
        id: true,
        name: true,
        customerType: true,
        category: true,
        ownerName: true,
        ownerPhone: true,
        email: true,
        website: true,
        address: true,
        zone: true,
        workingPriority: true,
        approvalStatus: true,
        examinationBoard: true,
        offeredProgramme: true,
        totalStudents: true,
        reviewMonth: true,
        sessionStarts: true,
        latitude: true,
        longitude: true,
        createdAt: true,
        city: { select: { id: true, name: true } },
        region: { select: { id: true, name: true } },
        area: { select: { id: true, name: true } },
        assignedBooker: { select: { id: true, name: true, phone: true, jobStatus: true } },
      },
    });

    if (!customer) {
      return NextResponse.json({ success: false, error: "Customer not found" }, { status: 404 });
    }

    const [visits, sampleRequests, visitCount] = await Promise.all([
      prisma.visit.findMany({
        where: { customerId },
        orderBy: { visitDate: "desc" },
        take: 10,
        select: {
          id: true,
          visitDate: true,
          checkInAt: true,
          checkOutAt: true,
          status: true,
          booker: { select: { id: true, name: true } },
        },
      }),
      prisma.sampleRequest.findMany({
        where: { customerId },
        orderBy: { createdAt: "desc" },
        take: 10,
        select: {
          id: true,
          productName: true,
          quantity: true,
          status: true,
          createdAt: true,
        },
      }),
      prisma.visit.count({ where: { customerId } }),
    ]);

    return NextResponse.json({
      success: true,
      data: { ...customer, visits, sampleRequests, visitCount },
    });
  } catch (err) {
    console.error("[api/v1/customers/:id]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch customer" }, { status: 500 });
  }
}
