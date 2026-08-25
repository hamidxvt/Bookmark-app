import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const skip = Number(searchParams.get("start") ?? 0);
    const take = Number(searchParams.get("length") ?? 100);
    const type = searchParams.get("type") ?? "products";

    if (type === "brands") {
      const [data, total] = await Promise.all([
        prisma.brand.findMany({ skip, take, orderBy: { name: "asc" } }),
        prisma.brand.count(),
      ]);
      return NextResponse.json({ success: true, data: { recordsTotal: total, data } });
    }

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

export async function POST(req: Request) {
  try {
    const body = await req.json();
    const { name, brandId, seriesId, subjectId, isbn, grade, segment, description, retailPrice, image } = body;

    if (!name?.trim()) return NextResponse.json({ success: false, error: { message: "Product name required" } }, { status: 400 });
    if (!brandId) return NextResponse.json({ success: false, error: { message: "Brand is required" } }, { status: 400 });

    const product = await prisma.product.create({
      data: {
        name: name.trim(),
        brandId: parseInt(String(brandId)),
        seriesId: seriesId ? parseInt(String(seriesId)) : null,
        subjectId: subjectId ? parseInt(String(subjectId)) : null,
        isbn: isbn?.trim() || null,
        grade: grade || null,
        segment: segment || null,
        description: description?.trim() || null,
        retailPrice: retailPrice ? parseFloat(String(retailPrice)) : 0,
        image: image || null,
        visibility: "PUBLIC",
      },
      include: {
        brand: { select: { id: true, name: true } },
        series: { select: { id: true, name: true } },
        subject: { select: { id: true, name: true } },
      },
    });

    return NextResponse.json({ success: true, data: product }, { status: 201 });
  } catch (err: any) {
    console.error("[api/v1/products POST]", err);
    return NextResponse.json({ success: false, error: { message: err.message ?? "Failed" } }, { status: 500 });
  }
}
