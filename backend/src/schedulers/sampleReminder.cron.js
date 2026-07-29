import prisma from '../config/database.js';
import logger from '../utils/logger.js';

// Runs daily at 9:00 AM — sends reminders for unrecovered samples
export async function runSampleReminders() {
  const now = new Date();
  const day10 = new Date(now.getTime() - 10 * 24 * 60 * 60 * 1000);
  const day20 = new Date(now.getTime() - 20 * 24 * 60 * 60 * 1000);

  logger.info('[Sample Reminders] Checking overdue samples...');

  // 10-day reminders
  const due10 = await prisma.sampleRequest.findMany({
    where: {
      status: 'dispatched',
      reminder10Sent: false,
      dispatchedAt: { lte: day10 },
      recoveredAt: null,
    },
    include: { items: { include: { product: { select: { name: true } } } } },
  });

  for (const sample of due10) {
    await prisma.sampleRequest.update({
      where: { id: sample.id },
      data: { reminder10Sent: true },
    });
    // In production: send FCM push notification or email
    logger.warn(`[Sample] 10-day reminder — Booker ${sample.bookerId}, Request #${sample.id}, PKR ${sample.totalValuePkr}`);
  }

  // 20-day reminders (salary deduction imminent)
  const due20 = await prisma.sampleRequest.findMany({
    where: {
      status: 'dispatched',
      reminder20Sent: false,
      dispatchedAt: { lte: day20 },
      recoveredAt: null,
    },
  });

  for (const sample of due20) {
    await prisma.sampleRequest.update({
      where: { id: sample.id },
      data: { reminder20Sent: true },
    });
    logger.warn(`[Sample] 20-day FINAL reminder — Booker ${sample.bookerId}, Request #${sample.id}. Salary deduction pending.`);
  }

  logger.info(`[Sample Reminders] Done. 10-day: ${due10.length}, 20-day: ${due20.length}`);
}
