import { NextRequest, NextResponse } from 'next/server';
import { prisma } from '@/lib/prisma';

export async function PATCH(req: NextRequest, { params }: { params: { id: string } }) {
  try {
    const id = parseInt(params.id);
    const { status, adminNotes } = await req.json();

    const updated = await (prisma as any).sampleRequest.update({
      where: { id },
      data: {
        status,
        adminNotes: adminNotes || null,
        reviewedAt: new Date(),
      },
      include: {
        booker: { select: { id: true, name: true, email: true } },
        customer: { select: { id: true, name: true } },
      },
    });

    return NextResponse.json({ success: true, data: updated });
  } catch (err: any) {
    return NextResponse.json({ success: false, error: { message: err.message } }, { status: 500 });
  }
}
