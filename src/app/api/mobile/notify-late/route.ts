/**
 * POST /api/mobile/notify-late
 * Called by the mobile app when navigating starts.
 * If ETA is high and many visits pending, notifies admin.
 */
import { NextRequest, NextResponse } from 'next/server';
import { prisma } from '@/lib/prisma';
import { getMobileUser, unauthorized } from '@/lib/mobile-auth';

async function sendFcmNotification(token: string, title: string, body: string) {
  const fcmKey = process.env.FCM_SERVER_KEY;
  if (!fcmKey) return;
  await fetch('https://fcm.googleapis.com/fcm/send', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `key=${fcmKey}`,
    },
    body: JSON.stringify({
      to: token,
      notification: { title, body, sound: 'default', click_action: 'FLUTTER_NOTIFICATION_CLICK' },
      data: { type: 'officer_late' },
    }),
  }).catch(() => null);
}

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

    const adminTokens = (process.env.ADMIN_FCM_TOKENS ?? '').split(',').filter(Boolean);
    const title = `⚠️ Officer May Run Late`;
    const body = `${officer.name} — ETA ${etaMinutes} min to ${customerName ?? 'next stop'} with ${pendingCount} visits remaining today.`;

    for (const token of adminTokens) {
      await sendFcmNotification(token.trim(), title, body);
    }

    return NextResponse.json({ success: true, data: { notified: adminTokens.length, isLate, pendingCount } });
  } catch (err: any) {
    return NextResponse.json({ success: false, error: { message: err.message } }, { status: 500 });
  }
}
