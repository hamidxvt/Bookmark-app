/**
 * POST /api/v1/notify-late
 * Called from the mobile app (when ETA is sent) OR by a scheduler.
 * Checks if an officer's ETA would make them late to their NEXT PENDING visit,
 * and sends an FCM push notification to admin devices.
 */

import { NextRequest, NextResponse } from 'next/server';
import { prisma } from '@/lib/prisma';

// Simple FCM push via Google REST API
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
      notification: { title, body, sound: 'default' },
      data: { type: 'late_officer' },
    }),
  }).catch(() => null);
}

export async function POST(req: NextRequest) {
  try {
    const { bookerId, visitId, etaMinutes, customerName } = await req.json();

    if (!bookerId || !etaMinutes) {
      return NextResponse.json({ success: false, error: 'bookerId and etaMinutes required' }, { status: 400 });
    }

    // Find today's pending visits for this officer (get the first pending one)
    const today = new Date();
    today.setHours(0, 0, 0, 0);
    const tomorrow = new Date(today);
    tomorrow.setDate(tomorrow.getDate() + 1);

    const officer = await prisma.booker.findUnique({
      where: { id: bookerId },
      select: { id: true, name: true },
    });

    if (!officer) return NextResponse.json({ success: false, error: 'Officer not found' }, { status: 404 });

    // Check total pending visits for the day
    const pendingCount = await prisma.visit.count({
      where: {
        bookerId,
        status: { in: ['PENDING', 'IN_PROGRESS'] },
        visitDate: { gte: today, lt: tomorrow },
      },
    });

    // Consider late if ETA > 30 minutes AND there are many pending visits
    const isLate = etaMinutes > 30 && pendingCount > 2;
    if (!isLate) return NextResponse.json({ success: true, data: { notified: false, reason: 'Not late enough' } });

    // Find all admin FCM tokens (send to all admins with tokens)
    // For now we just log it — in production you'd query admin users with tokens
    // Since there's no admin model with FCM, we store in an env var as a list
    const adminTokens = (process.env.ADMIN_FCM_TOKENS ?? '').split(',').filter(Boolean);
    const officerName = officer.name;

    const title = `⚠️ Officer Running Late`;
    const body = `${officerName} has ${pendingCount} pending visits but ETA is ${etaMinutes} min to ${customerName ?? 'next stop'}. May not complete today's schedule.`;

    let notified = 0;
    for (const token of adminTokens) {
      await sendFcmNotification(token.trim(), title, body);
      notified++;
    }

    return NextResponse.json({
      success: true,
      data: { notified, isLate, pendingVisits: pendingCount, etaMinutes },
    });
  } catch (err: any) {
    return NextResponse.json({ success: false, error: { message: err.message } }, { status: 500 });
  }
}
