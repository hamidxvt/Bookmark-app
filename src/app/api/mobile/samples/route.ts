import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// GET /api/mobile/samples — officer's sample requests + budget
export async function GET(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const booker = await prisma.booker.findUnique({
      where: { id: user.id },
      select: { sampleBudget: true },
    });

    const sampleBudgetTotal = booker?.sampleBudget ? Number(booker.sampleBudget) : 300000;

    const requests = await prisma.request.findMany({
      where: { bookerId: user.id, category: "SAMPLE" },
      orderBy: { createdAt: "desc" },
      take: 100,
    });

    const data = requests.map((r) => {
      // Parse stored data: title = "ProductName | Qty:N | Val:XXXX"
      const parts = r.title?.split(" | ") ?? [];
      const productName = parts[0] ?? r.title ?? "Sample";
      const qty = parseInt(parts[1]?.replace("Qty:", "") ?? "1") || 1;
      const val = parseFloat(parts[2]?.replace("Val:", "") ?? "0") || 0;

      return {
        id: r.id,
        productName,
        institutionName: r.details?.split("\n")[0] ?? null,
        quantity: qty,
        totalValue: val,
        status: r.status.toLowerCase(),
        isRecovered: r.status === "RESOLVED",
        daysSinceCreated: Math.floor((Date.now() - new Date(r.createdAt).getTime()) / 86400000),
        createdAt: r.createdAt,
      };
    });

    // Budget used = sum of approved/resolved sample values
    const budgetUsed = data
      .filter(r => r.status === "approved" || r.status === "resolved")
      .reduce((sum, r) => sum + r.totalValue, 0);

    return NextResponse.json({
      success: true,
      data: {
        requests: data,
        budgetUsed,
        budgetTotal: sampleBudgetTotal,
        budgetRemaining: sampleBudgetTotal - budgetUsed,
      },
    });
  } catch (err) {
    console.error("[mobile/samples GET]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}

// POST /api/mobile/samples — submit a sample request
export async function POST(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const { productId, productName, quantity, institutionName, notes, estimatedValue } = await req.json();

    if (!productName && !productId) {
      return NextResponse.json({ success: false, error: "Product required" }, { status: 400 });
    }

    const qty = Number(quantity) || 1;
    let pName = productName ?? "";
    let pValue = Number(estimatedValue) || 0;

    // Look up product if ID given
    if (productId) {
      const product = await prisma.product.findUnique({
        where: { id: Number(productId) },
        select: { name: true, price: true },
      });
      if (product) {
        pName = product.name;
        pValue = pValue || (Number(product.price) * qty);
      }
    }

    const title = `${pName} | Qty:${qty} | Val:${pValue}`;

    const request = await prisma.request.create({
      data: {
        bookerId: user.id,
        title,
        category: "SAMPLE",
        details: [institutionName, notes].filter(Boolean).join("\n"),
        status: "PENDING",
      },
    });

    return NextResponse.json({
      success: true,
      data: {
        id: request.id,
        productName: pName,
        quantity: qty,
        totalValue: pValue,
        status: "pending",
      },
    });
  } catch (err) {
    console.error("[mobile/samples POST]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
