import { NextRequest, NextResponse } from 'next/server';
import { prisma } from '@/lib/prisma';

export async function DELETE(req: NextRequest, { params }: { params: Promise<{ id: string }> }) {
  try {
    const { id } = await params;

    await (prisma as any).visit.delete({
      where: { id: parseInt(id) },
    });

    return NextResponse.json({ success: true });
  } catch (error) {
    console.error('Failed to delete ad-hoc visit:', error);
    return NextResponse.json(
      { success: false, error: { message: 'Failed to delete visit' } },
      { status: 500 }
    );
  }
}
