import prisma from '../config/database.js';
import logger from '../utils/logger.js';

const MAX_VISITS = 7;

function haversine(lat1, lon1, lat2, lon2) {
  const R = 6371000;
  const rad = Math.PI / 180;
  const dLat = (lat2 - lat1) * rad;
  const dLon = (lon2 - lon1) * rad;
  const a = Math.sin(dLat / 2) ** 2 +
    Math.cos(lat1 * rad) * Math.cos(lat2 * rad) * Math.sin(dLon / 2) ** 2;
  return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
}

export async function runRoutePlanning() {
  const tomorrow = new Date();
  tomorrow.setDate(tomorrow.getDate() + 1);
  tomorrow.setHours(0, 0, 0, 0);

  logger.info(`[Route Planning] Building visit queues for ${tomorrow.toISOString().slice(0, 10)}`);

  const bookers = await prisma.booker.findMany({
    where: { jobStatus: 'ACTIVE', adminApproved: 'APPROVED' },
    select: { id: true, cityId: true, regionId: true },
  });

  for (const booker of bookers) {
    try {
      await buildDailyQueue(booker, tomorrow);
    } catch (e) {
      logger.error(`Route planning failed for booker ${booker.id}: ${e.message}`);
    }
  }

  logger.info(`[Route Planning] Done. Processed ${bookers.length} bookers.`);
}

async function buildDailyQueue(booker, date) {
  const visits = [];

  // 1. Carry-forward approved missed visits (under 5 attempts)
  const carryForward = await prisma.missedVisitReason.findMany({
    where: {
      bookerId: booker.id,
      status: 'approved',
      visit: { carryForwardCnt: { lt: 5 } },
    },
    include: { visit: { include: { customer: true } } },
    take: MAX_VISITS,
  });

  for (const mvr of carryForward) {
    if (visits.length >= MAX_VISITS) break;
    // Create a new visit for tomorrow based on the missed one
    const newVisit = await prisma.visit.create({
      data: {
        bookerId: booker.id,
        customerId: mvr.visit.customerId,
        visitDate: date,
        dailySequence: visits.length + 1,
        status: 'PENDING',
        carryForwardCnt: mvr.visit.carryForwardCnt,
        notes: `Carried forward from visit #${mvr.visitId}`,
      },
      include: { customer: true },
    });
    visits.push(newVisit);
    // Increment carry forward count on original
    await prisma.visit.update({
      where: { id: mvr.visitId },
      data: { carryForwardCnt: { increment: 1 } },
    });
  }

  // 2. Pre-scheduled follow-up visits
  const scheduled = await prisma.visit.findMany({
    where: { bookerId: booker.id, visitDate: date, status: 'PENDING' },
    include: { customer: true },
    take: MAX_VISITS - visits.length,
  });
  visits.push(...scheduled);

  // 3. Fill from customer pool in the booker's region
  if (visits.length < MAX_VISITS && booker.regionId) {
    const sevenDaysAgo = new Date(date.getTime() - 7 * 24 * 60 * 60 * 1000);
    const recentCustomerIds = (await prisma.visit.findMany({
      where: { bookerId: booker.id, visitDate: { gte: sevenDaysAgo } },
      select: { customerId: true },
    })).map(v => v.customerId);

    const alreadyPlanned = visits.map(v => v.customerId);

    const pool = await prisma.customer.findMany({
      where: {
        regionId: booker.regionId,
        approvalStatus: 'APPROVED',
        id: { notIn: [...new Set([...recentCustomerIds, ...alreadyPlanned])] },
        deletedAt: null,
      },
      orderBy: { workingPriority: 'asc' }, // lower = higher priority
      take: MAX_VISITS - visits.length,
    });

    for (const customer of pool) {
      if (visits.length >= MAX_VISITS) break;
      const newVisit = await prisma.visit.create({
        data: {
          bookerId: booker.id,
          customerId: customer.id,
          visitDate: date,
          dailySequence: visits.length + 1,
          status: 'PENDING',
        },
        include: { customer: true },
      });
      visits.push(newVisit);
    }
  }

  // 4. Geo-sort by proximity (nearest-neighbor from first visit)
  if (visits.length > 1) {
    const first = visits[0].customer;
    if (first?.latitude && first?.longitude) {
      visits.sort((a, b) => {
        const aLat = Number(a.customer?.latitude ?? 0);
        const aLng = Number(a.customer?.longitude ?? 0);
        const bLat = Number(b.customer?.latitude ?? 0);
        const bLng = Number(b.customer?.longitude ?? 0);
        return haversine(Number(first.latitude), Number(first.longitude), aLat, aLng) -
               haversine(Number(first.latitude), Number(first.longitude), bLat, bLng);
      });
    }
  }

  // 5. Update sequences
  for (let i = 0; i < visits.length; i++) {
    await prisma.visit.update({ where: { id: visits[i].id }, data: { dailySequence: i + 1 } });
  }

  logger.info(`[Route Planning] Booker ${booker.id}: ${visits.length} visits planned for ${date.toISOString().slice(0, 10)}`);
}
