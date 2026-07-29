import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

export async function GET(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const requests = await prisma.request.findMany({
      where: { bookerId: user.id },
      orderBy: { createdAt: "desc" },
      take: 50,
    });

    const data = requests.map((r) => ({
      id: r.id,
      productName: r.title,
      institutionName: r.details?.slice(0, 60) ?? null,
      quantity: 1,
      totalValue: 0,
      status: r.status.toLowerCase(),
      isRecovered: r.status === "RESOLVED",
      createdAt: r.createdAt,
    }));

    const active = data.filter((r) => r.status !== "resolved");
    const budgetUsed = active.length * 500;

    return NextResponse.json({
      success: true,
      data: {
        requests: data,
        budgetUsed,
        budgetTotal: 50000,
      },
    });
  } catch (err) {
    console.error("[mobile/samples GET]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}

export async function POST(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const { productId, quantity, notes } = await req.json();

    const request = await prisma.request.create({
      data: {
        bookerId: user.id,
        title: `Sample Request #${productId ?? "?"}`,
        category: "sample",
        details: notes ?? `Quantity: ${quantity ?? 1}`,
        status: "PENDING",
      },
    });

    return NextResponse.json({ success: true, data: request });
  } catch (err) {
    console.error("[mobile/samples POST]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
