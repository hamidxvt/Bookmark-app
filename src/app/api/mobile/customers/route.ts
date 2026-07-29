import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// GET /api/mobile/customers?q=search&limit=20
export async function GET(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  const { searchParams } = new URL(req.url);
  const q = searchParams.get("q") ?? "";
  const limit = Math.min(Number(searchParams.get("limit") ?? 20), 50);

  try {
    const customers = await prisma.customer.findMany({
      where: {
        deletedAt: null,
        approvalStatus: "APPROVED",
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

    return NextResponse.json({
      success: true,
      data: customers.map(c => ({
        id: c.id,
        name: c.name,
        type: c.customerType,
        contact: c.ownerName ?? "",
        phone: c.ownerPhone ?? "",
        address: c.address ?? "",
        city: c.city?.name ?? "",
      })),
    });
  } catch (err) {
    console.error("[mobile/customers]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
