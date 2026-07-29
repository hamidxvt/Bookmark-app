import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const skip = Number(searchParams.get("start") ?? 0);
    const take = Number(searchParams.get("length") ?? 50);
    const cityId = searchParams.get("cityId");
    const type = searchParams.get("type");

    const where: Record<string, unknown> = { deletedAt: null };
    if (cityId) where.cityId = parseInt(cityId);
    if (type) where.customerType = type.toUpperCase();

    const [records, total] = await Promise.all([
      prisma.customer.findMany({
        where,
        skip,
        take,
        orderBy: { createdAt: "desc" },
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
          approvalStatus: true,
          latitude: true,
          longitude: true,
          createdAt: true,
          city: { select: { id: true, name: true } },
          area: { select: { id: true, name: true } },
        },
      }),
      prisma.customer.count({ where }),
    ]);

    return NextResponse.json({
      success: true,
      data: {
        recordsTotal: total,
        recordsFiltered: total,
        data: records,
      },
    });
  } catch (err) {
    console.error("[api/v1/customers]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch customers" }, { status: 500 });
  }
}
