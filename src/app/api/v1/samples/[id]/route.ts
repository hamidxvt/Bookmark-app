import { NextRequest, NextResponse } from 'next/server';
import { prisma } from '@/lib/prisma';

export async function PATCH(req: NextRequest, { params }: { params: Promise<{ id: string }> }) {
  try {
    const { id } = await params;
    const parsedId = parseInt(id);
    const { status, adminNotes } = await req.json();

    const updated = await (prisma as any).sampleRequest.update({
      where: { id: parsedId },
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
