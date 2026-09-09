import { NextResponse } from "next/server";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";
import { prisma } from "@/lib/prisma";
import { createEvent } from "@/lib/events";

// GET /api/mobile/samples
// Returns officer's sample requests + their budget summary
export async function GET(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  const [requests, booker] = await Promise.all([
    prisma.sampleRequest.findMany({
      where: { bookerId: user.id },
      orderBy: { createdAt: "desc" },
      include: { customer: { select: { id: true, name: true, address: true } } },
    }),
    prisma.booker.findUnique({
      where: { id: user.id },
      select: { sampleBudget: true },
    }),
  ]);

  const budget = Number(booker?.sampleBudget ?? 300000);
  const usedBudget = requests
    .filter(r => ["approved", "delivered"].includes(r.status))
    .reduce((sum, r) => sum + (r.price ? Number(r.price) * r.quantity : 0), 0);

  return NextResponse.json({
    success: true,
    data: {
      budget: { total: budget, used: usedBudget, remaining: budget - usedBudget },
      samples: requests.map(r => ({
        id: r.id,
        productName: r.productName,
        status: r.status,
        quantity: r.quantity,
        price: r.price ? Number(r.price) : null,
        totalCost: r.price ? Number(r.price) * r.quantity : null,
        notes: r.notes,
        adminNotes: r.adminNotes,
        customerName: r.customer?.name ?? r.customerName,
        customerId: r.customerId,
        deliveredAt: r.deliveredAt,
        createdAt: r.createdAt,
        customer: r.customer,
        pdfUrl: r.pdfUrl ?? null,
      })),
    },
  });
}

// POST /api/mobile/samples
// Create a new sample request
export async function POST(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const { productName, quantity, notes, customerId, customerName, price, items } = await req.json();

    if (!productName) {
      return NextResponse.json({ success: false, error: "Product name is required" }, { status: 400 });
    }

    // Store items breakdown in notes if provided
    const itemsSuffix = items && Array.isArray(items) && items.length > 1
      ? `\n[ITEMS:${JSON.stringify(items)}]`
      : "";

    const request = await prisma.sampleRequest.create({
      data: {
        bookerId: user.id,
        productName: String(productName),
        quantity: Number(quantity ?? 1),
        notes: notes ? `${notes}${itemsSuffix}` : (itemsSuffix || null),
        customerId: customerId ? Number(customerId) : null,
        customerName: customerName ?? null,
        price: price ? Number(price) : null,
        status: "pending",
      },
    });

    const booker = await prisma.booker.findUnique({
      where: { id: user.id },
      select: { name: true },
    });

    await createEvent("sample-request", {
      sampleId: request.id,
      bookerId: user.id,
      bookerName: booker?.name ?? "Officer",
      productName: request.productName,
      quantity: request.quantity,
      message: `${booker?.name ?? "Officer"} requested samples: ${request.productName}`,
    }).catch(() => {});

    return NextResponse.json({ success: true, data: request }, { status: 201 });
  } catch (err) {
    console.error("[samples POST]", err);
    return NextResponse.json({ success: false, error: "Failed to create sample request" }, { status: 500 });
  }
}
