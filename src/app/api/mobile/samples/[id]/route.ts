import { NextResponse } from 'next/server';
import { prisma } from '@/lib/prisma';
import { getMobileUser, unauthorized } from '@/lib/mobile-auth';

export async function PATCH(req: Request, { params }: { params: { id: string } }) {
  const booker = getMobileUser(req);
  if (!booker) return unauthorized();

  try {
    const id = parseInt(params.id);
    const { customerName, signatureBase64, pdfUrl, notes, quantity, customerId } = await req.json();

    const updated = await prisma.sampleRequest.update({
      where: { id, bookerId: booker.id },
      data: {
        customerName: customerName || null,
        signatureBase64: signatureBase64 || null,
        pdfUrl: pdfUrl || null,
        notes: notes || null,
        quantity: quantity ?? undefined,
        customerId: customerId || null,
        deliveredAt: new Date(),
        status: 'delivered',
      },
    });

    return NextResponse.json({ success: true, data: updated });
  } catch (err: any) {
    return NextResponse.json({ success: false, error: { message: err.message } }, { status: 500 });
  }
}
