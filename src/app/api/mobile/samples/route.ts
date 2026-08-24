import { NextResponse } from 'next/server';
import { prisma } from '@/lib/prisma';
import { getMobileUser, unauthorized } from '@/lib/mobile-auth';

export async function GET(req: Request) {
  const booker = getMobileUser(req);
  if (!booker) return unauthorized();

  const samples = await prisma.sampleRequest.findMany({
    where: { bookerId: booker.id },
    orderBy: { createdAt: 'desc' },
    include: { customer: { select: { id: true, name: true } } },
  });

  return NextResponse.json({ success: true, data: samples });
}

export async function POST(req: Request) {
  const booker = getMobileUser(req);
  if (!booker) return unauthorized();

  try {
    const { productName, quantity, notes, customerId, customerName } = await req.json();

    const sample = await prisma.sampleRequest.create({
      data: {
        bookerId: booker.id,
        productName,
        quantity: quantity ?? 1,
        notes: notes || null,
        customerId: customerId || null,
        customerName: customerName || null,
        status: 'pending',
      },
    });

    return NextResponse.json({ success: true, data: sample }, { status: 201 });
  } catch (err: any) {
    return NextResponse.json({ success: false, error: { message: err.message } }, { status: 500 });
  }
}
