import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// GET /api/mobile/customers/[id] — full customer detail with orders/products
export async function GET(
  req: Request,
  { params }: { params: Promise<{ id: string }> }
) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const { id } = await params;
    const customerId = parseInt(id);

    const customer = await prisma.customer.findUnique({
      where: { id: customerId },
      select: {
        id: true,
        name: true,
        customerType: true,
        category: true,
        ownerName: true,
        ownerPhone: true,
        email: true,
        address: true,
        workingPriority: true,
        latitude: true,
        longitude: true,
        city: { select: { id: true, name: true } },
        orders: {
          orderBy: { createdAt: "desc" },
          take: 10,
          select: {
            id: true,
            totalAmount: true,
            status: true,
            createdAt: true,
            items: {
              select: {
                id: true,
                quantity: true,
                unitPrice: true,
                product: { select: { id: true, name: true, retailPrice: true } },
              },
            },
          },
        },
        visits: {
          where: { status: "COMPLETED" },
          orderBy: { visitDate: "desc" },
          take: 5,
          select: {
            id: true,
            visitDate: true,
            notes: true,
            visitReport: true,
          },
        },
      },
    });

    if (!customer) {
      return NextResponse.json({ success: false, error: "Customer not found" }, { status: 404 });
    }

    return NextResponse.json({ success: true, data: customer });
  } catch (err) {
    console.error("[mobile/customers/[id] GET]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
