import { NextRequest, NextResponse } from 'next/server';
import { prisma } from '@/lib/prisma';

export async function PATCH(req: NextRequest, { params }: { params: Promise<{ id: string }> }) {
  try {
    const { id } = await params;
    const parsedId = parseInt(id);
    const { status, adminNotes } = await req.json();

    // Fetch the sample request first
    const sample = await (prisma as any).sampleRequest.findUnique({
      where: { id: parsedId },
      include: { booker: true },
    });

    if (!sample) {
      return NextResponse.json(
        { success: false, error: { message: 'Sample not found' } },
        { status: 404 }
      );
    }

    // If approving, deduct from sample budget
    let updateData: any = {
      status,
      adminNotes: adminNotes || null,
      reviewedAt: new Date(),
    };

    if (status === 'approved' && sample.price) {
      const booker = await (prisma as any).booker.findUnique({
        where: { id: sample.bookerId },
      });

      const totalCost = parseFloat(sample.price) * sample.quantity;
      const newBudget = parseFloat(booker.sampleBudget || 0) - totalCost;

      // Update booker budget
      await (prisma as any).booker.update({
        where: { id: sample.bookerId },
        data: { sampleBudget: Math.max(0, newBudget) },
      });
    }

    const updated = await (prisma as any).sampleRequest.update({
      where: { id: parsedId },
      data: updateData,
      include: {
        booker: { select: { id: true, name: true, email: true, sampleBudget: true } },
        customer: { select: { id: true, name: true } },
      },
    });

    return NextResponse.json({ success: true, data: updated });
  } catch (err: any) {
    return NextResponse.json({ success: false, error: { message: err.message } }, { status: 500 });
  }
}
