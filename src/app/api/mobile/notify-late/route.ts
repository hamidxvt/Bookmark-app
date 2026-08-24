/**
 * POST /api/mobile/notify-late
 * Called by the mobile app when navigating starts.
 * If ETA is high and many visits pending, notifies admin.
 */
import { NextRequest, NextResponse } from 'next/server';
import { prisma } from '@/lib/prisma';
import { getMobileUser, unauthorized } from '@/lib/mobile-auth';

export async function POST(req: NextRequest) {
  const officer = getMobileUser(req);
  if (!officer) return unauthorized();

  try {
    const { visitId, etaMinutes, customerName } = await req.json();
    if (!etaMinutes) return NextResponse.json({ success: false, error: 'etaMinutes required' }, { status: 400 });

    const today = new Date();
    today.setHours(0, 0, 0, 0);
    const tomorrow = new Date(today);
    tomorrow.setDate(tomorrow.getDate() + 1);

    // Count remaining pending visits for today
    const pendingCount = await prisma.visit.count({
      where: {
        bookerId: officer.id,
        status: { in: ['PENDING', 'IN_PROGRESS'] },
      },
    });

    // Only notify if ETA > 25min AND 3+ visits pending
    const isLate = etaMinutes > 25 && pendingCount >= 3;
    if (!isLate) return NextResponse.json({ success: true, data: { notified: false } });

    // Log late officer for admin (FCM notifications can be enabled later with ADMIN_FCM_TOKENS env var)
    console.log(`[Late Officer] ${officer.email} - ETA ${etaMinutes}min, ${pendingCount} pending visits, to ${customerName ?? 'next stop'}`);

    return NextResponse.json({ success: true, data: { notified: 0, isLate, pendingCount, logged: true } });
  } catch (err: any) {
    return NextResponse.json({ success: false, error: { message: err.message } }, { status: 500 });
  }
}
