import { NextResponse } from 'next/server';
import { prisma } from '@/lib/prisma';

// GET /api/v1/officers/:id/eta — latest ETA for a specific officer
export async function GET(_req: Request, { params }: { params: { id: string } }) {
  try {
    const bookerId = parseInt(params.id);

    // Find the most recent navigating visit for this officer
    const latestETA = await (prisma as any).visitETA.findFirst({
      where: {
        visit: { bookerId },
      },
      orderBy: { updatedAt: 'desc' },
      select: {
        visitId: true,
        customerName: true,
        eta_minutes: true,
        eta_walk_minutes: true,
        distance_km: true,
        navigating_since: true,
        updatedAt: true,
      },
    });

    // Also get latest GPS ping for speed
    const latestPing = await (prisma as any).gpsPing.findFirst({
      where: { bookerId },
      orderBy: { createdAt: 'desc' },
      select: {
        speed_kmh: true,
        activity: true,
        createdAt: true,
      },
    });

    return NextResponse.json({
      success: true,
      data: latestETA ? {
        ...latestETA,
        speed_kmh: latestPing?.speed_kmh ?? null,
        activity: latestPing?.activity ?? null,
      } : null,
    });
  } catch (err: any) {
    return NextResponse.json({ success: false, error: { message: err.message } }, { status: 500 });
  }
}
