import { NextRequest, NextResponse } from 'next/server';
import { prisma } from '@/lib/prisma';

export async function GET(req: NextRequest) {
  try {
    const filter = req.nextUrl.searchParams.get('filter') || 'all';
    const where: any = {};

    if (filter === 'today') {
      const today = new Date();
      today.setHours(0, 0, 0, 0);
      const tomorrow = new Date(today);
      tomorrow.setDate(tomorrow.getDate() + 1);
      where.createdAt = { gte: today, lt: tomorrow };
    }

    // Fetch from Visit table (ad-hoc visits are visits with notes containing 'Ad-hoc')
    const visits = await (prisma as any).visit.findMany({
      where: {
        notes: { contains: 'Ad-hoc' },
        ...where,
      },
      include: {
        booker: { select: { id: true, name: true, email: true } },
        customer: { select: { id: true, name: true, city: true, phone: true } },
      },
      orderBy: { createdAt: 'desc' },
      take: 100,
    });

    return NextResponse.json({ success: true, data: visits });
  } catch (error) {
    console.error('Failed to fetch ad-hoc visits:', error);
    return NextResponse.json(
      { success: false, error: { message: 'Failed to fetch visits' } },
      { status: 500 }
    );
  }
}

export async function POST(req: NextRequest) {
  try {
    const body = await req.json();
    const { bookerId, customerId, customerName, notes } = body;

    if (!bookerId || !customerName) {
      return NextResponse.json(
        { success: false, error: { message: 'Missing required fields' } },
        { status: 400 }
      );
    }

    const visit = await (prisma as any).visit.create({
      data: {
        bookerId,
        customerId: customerId || null,
        locationName: customerName,
        notes: `Ad-hoc visit - ${notes || ''}`,
        visitDate: new Date(),
        status: 'PLANNED',
      },
      include: {
        booker: { select: { id: true, name: true, email: true } },
        customer: { select: { id: true, name: true, city: true, phone: true } },
      },
    });

    return NextResponse.json({ success: true, data: visit }, { status: 201 });
  } catch (error) {
    console.error('Failed to create ad-hoc visit:', error);
    return NextResponse.json(
      { success: false, error: { message: 'Failed to create visit' } },
      { status: 500 }
    );
  }
}
