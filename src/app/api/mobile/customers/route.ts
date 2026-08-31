import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// GET /api/mobile/customers?q=search&limit=20
export async function GET(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  const { searchParams } = new URL(req.url);
  // Accept both "q" and "search" query params for compatibility
  const q = searchParams.get("search") ?? searchParams.get("q") ?? "";
  // Accept both "limit" and "length" query params
  const limit = Math.min(Number(searchParams.get("length") ?? searchParams.get("limit") ?? 20), 50);

  try {
    const customers = await prisma.customer.findMany({
      where: {
        deletedAt: null,
        ...(q ? { name: { contains: q, mode: "insensitive" } } : {}),
      },
      select: {
        id: true,
        name: true,
        customerType: true,
        ownerName: true,
        ownerPhone: true,
        address: true,
        city: { select: { name: true } },
      },
      orderBy: { workingPriority: "asc" },
      take: limit,
    });

    const formattedCustomers = customers.map(c => ({
      id: c.id,
      name: c.name,
      type: c.customerType,
      contact: c.ownerName ?? "",
      phone: c.ownerPhone ?? "",
      address: c.address ?? "",
      // Return city as object so mobile can do c['city']['name']
      city: c.city ? { name: c.city.name } : null,
    }));

    return NextResponse.json({
      success: true,
      data: formattedCustomers,
    });
  } catch (err) {
    console.error("[mobile/customers]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
