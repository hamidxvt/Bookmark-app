import { NextResponse } from 'next/server';
import { prisma } from '@/lib/prisma';

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const status = searchParams.get('status');
    const bookerId = searchParams.get('bookerId');

    const where: Record<string, unknown> = {};
    if (status) where.status = status;
    if (bookerId) where.bookerId = parseInt(bookerId);

    const samples = await (prisma as any).sampleRequest.findMany({
      where,
      orderBy: { createdAt: 'desc' },
      take: 100,
      include: {
        booker: { select: { id: true, name: true, email: true } },
        customer: { select: { id: true, name: true } },
      },
    });

    return NextResponse.json({ success: true, data: samples });
  } catch (err: any) {
    return NextResponse.json({ success: false, error: { message: err.message } }, { status: 500 });
  }
}
