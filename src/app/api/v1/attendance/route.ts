import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const dateStr = searchParams.get("date");
    const bookerId = searchParams.get("bookerId");

    const date = dateStr ? new Date(dateStr) : new Date();
    date.setHours(0, 0, 0, 0);

    const where: Record<string, unknown> = { date };
    if (bookerId) where.bookerId = parseInt(bookerId);

    const records = await prisma.attendance.findMany({
      where,
      include: {
        booker: { select: { id: true, name: true, email: true, phone: true } },
      },
      orderBy: { startAt: "desc" },
    });

    return NextResponse.json({ success: true, data: records });
  } catch (err) {
    console.error("[api/v1/attendance]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch attendance" }, { status: 500 });
  }
}
