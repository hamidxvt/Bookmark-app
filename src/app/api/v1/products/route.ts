import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const skip = Number(searchParams.get("start") ?? 0);
    const take = Number(searchParams.get("length") ?? 100);
    const type = searchParams.get("type") ?? "products";

    if (type === "subjects") {
      const [data, total] = await Promise.all([
        prisma.subject.findMany({ skip, take, orderBy: { name: "asc" } }),
        prisma.subject.count(),
      ]);
      return NextResponse.json({ success: true, data: { recordsTotal: total, data } });
    }

    if (type === "series") {
      const [data, total] = await Promise.all([
        prisma.series.findMany({
          skip,
          take,
          orderBy: { name: "asc" },
          include: { brand: { select: { id: true, name: true } } },
        }),
        prisma.series.count(),
      ]);
      return NextResponse.json({ success: true, data: { recordsTotal: total, data } });
    }

    const [records, total] = await Promise.all([
      prisma.product.findMany({
        where: { visibility: "PUBLIC" },
        skip,
        take,
        orderBy: { createdAt: "desc" },
        include: {
          brand: { select: { id: true, name: true } },
          series: { select: { id: true, name: true } },
          subject: { select: { id: true, name: true } },
        },
      }),
      prisma.product.count({ where: { visibility: "PUBLIC" } }),
    ]);

    return NextResponse.json({
      success: true,
      data: { recordsTotal: total, recordsFiltered: total, data: records },
    });
  } catch (err) {
    console.error("[api/v1/products]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch products" }, { status: 500 });
  }
}
