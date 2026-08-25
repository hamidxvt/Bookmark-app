import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const dateStr     = searchParams.get("date");
    const dateFromStr = searchParams.get("dateFrom");
    const dateToStr   = searchParams.get("dateTo");
    const bookerId    = searchParams.get("bookerId");

    let where: Record<string, unknown> = {};

    if (dateFromStr && dateToStr) {
      const from = new Date(dateFromStr); from.setHours(0, 0, 0, 0);
      const to   = new Date(dateToStr);   to.setHours(23, 59, 59, 999);
      where.date = { gte: from, lte: to };
    } else {
      const date = dateStr ? new Date(dateStr) : new Date();
      date.setHours(0, 0, 0, 0);
      where.date = date;
    }

    if (bookerId) where.bookerId = parseInt(bookerId);

    const records = await prisma.attendance.findMany({
      where,
      include: {
        booker: { select: { id: true, name: true, email: true, phone: true } },
      },
      orderBy: [{ date: "desc" }, { startAt: "desc" }],
    });

    return NextResponse.json({ success: true, data: records });
  } catch (err) {
    console.error("[api/v1/attendance]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch attendance" }, { status: 500 });
  }
}
