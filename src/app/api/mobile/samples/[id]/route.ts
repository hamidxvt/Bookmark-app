import { NextResponse } from "next/server";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";
import { prisma } from "@/lib/prisma";

// PATCH /api/mobile/samples/:id — deliver with signature
export async function PATCH(req: Request, { params }: { params: Promise<{ id: string }> }) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  const { id: idStr } = await params;
  const id = Number(idStr);
  if (isNaN(id)) return NextResponse.json({ success: false, error: "Invalid ID" }, { status: 400 });

  const sample = await prisma.sampleRequest.findFirst({
    where: { id, bookerId: user.id },
  });

  if (!sample) return NextResponse.json({ success: false, error: "Sample not found" }, { status: 404 });
  if (sample.status !== "approved") {
    return NextResponse.json({ success: false, error: "Sample must be approved before delivery" }, { status: 400 });
  }

  try {
    const { signatureBase64, customerName, customerId, notes, pdfBase64, quantity } = await req.json();

    const updated = await prisma.sampleRequest.update({
      where: { id },
      data: {
        status: "delivered",
        deliveredAt: new Date(),
        signatureBase64: signatureBase64 ?? null,
        pdfUrl: pdfBase64 ?? null,
        customerName: customerName ?? sample.customerName,
        customerId: customerId ? Number(customerId) : sample.customerId,
        notes: notes ?? sample.notes,
        quantity: quantity ? Number(quantity) : sample.quantity,
      },
    });

    return NextResponse.json({ success: true, data: updated });
  } catch (err) {
    console.error("[samples PATCH]", err);
    return NextResponse.json({ success: false, error: "Failed to update sample" }, { status: 500 });
  }
}

// GET /api/mobile/samples/:id — get single sample details
export async function GET(req: Request, { params }: { params: Promise<{ id: string }> }) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  const { id: idStr } = await params;
  const id = Number(idStr);
  const sample = await prisma.sampleRequest.findFirst({
    where: { id, bookerId: user.id },
    include: { customer: { select: { id: true, name: true, address: true } } },
  });

  if (!sample) return NextResponse.json({ success: false, error: "Not found" }, { status: 404 });

  return NextResponse.json({ success: true, data: sample });
}
