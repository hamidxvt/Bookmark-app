import prisma from '../config/database.js';
import logger from '../utils/logger.js';

export async function runSampleReminders() {
  const now = new Date();

  const dispatched = await prisma.sampleRequest.findMany({
    where: { status: 'dispatched' },
    include: { user: { select: { name: true, email: true } } },
  });

  for (const sample of dispatched) {
    const daysSince = Math.floor((now - new Date(sample.requestedAt)) / (1000 * 60 * 60 * 24));

    if (daysSince >= 20 && !sample.reminder20Sent) {
      await prisma.sampleRequest.update({ where: { id: sample.id }, data: { reminder20Sent: true } });
      logger.warn(`URGENT sample recovery needed: request #${sample.id} for ${sample.user.name} — ${daysSince} days elapsed`);
      // TODO: Fire push notification + email to super_admin
    } else if (daysSince >= 10 && !sample.reminder10Sent) {
      await prisma.sampleRequest.update({ where: { id: sample.id }, data: { reminder10Sent: true } });
      logger.info(`Sample reminder sent for request #${sample.id} — 10 days elapsed`);
      // TODO: Fire push notification to officer + email to city_head
    }
  }
}
