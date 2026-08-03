import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// GET /api/mobile/products?q=search&limit=20
export async function GET(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  const { searchParams } = new URL(req.url);
  const q = searchParams.get("q") ?? "";
  const limit = Math.min(Number(searchParams.get("limit") ?? 20), 50);

  try {
    const products = await prisma.product.findMany({
      where: {
        visibility: "PUBLIC",
        ...(q ? { name: { contains: q, mode: "insensitive" } } : {}),
      },
      select: {
        id: true,
        name: true,
        price: true,
        brand: { select: { name: true } },
        series: { select: { name: true } },
      },
      orderBy: { isFeatured: "desc" },
      take: limit,
    });

    return NextResponse.json({
      success: true,
      data: products.map(p => ({
        id: p.id,
        name: p.name,
        price: p.price ? Number(p.price) : 0,
        brand: p.brand?.name ?? "",
        series: p.series?.name ?? "",
      })),
    });
  } catch (err) {
    console.error("[mobile/products]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
