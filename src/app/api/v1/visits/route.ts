import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const today = searchParams.get("today") === "1";
    const skip = Number(searchParams.get("start") ?? 0);
    const take = Number(searchParams.get("length") ?? 50);
    const bookerId = searchParams.get("bookerId");
    const status = searchParams.get("status");

    const todayDate = new Date();
    todayDate.setHours(0, 0, 0, 0);

    const where: Record<string, unknown> = {};
    if (today) where.visitDate = todayDate;
    if (bookerId) where.bookerId = parseInt(bookerId);
    if (status) where.status = status.toUpperCase();

    const [records, total] = await Promise.all([
      prisma.visit.findMany({
        where,
        skip,
        take,
        orderBy: { visitDate: "desc" },
        include: {
          booker: { select: { id: true, name: true, email: true } },
          customer: { select: { id: true, name: true, customerType: true, address: true, cityId: true } },
        },
      }),
      prisma.visit.count({ where }),
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
    console.error("[api/v1/visits]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch visits" }, { status: 500 });
  }
}
