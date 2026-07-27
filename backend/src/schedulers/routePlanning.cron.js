import prisma from '../config/database.js';
import { haversine } from '../utils/geoDistance.js';
import logger from '../utils/logger.js';

const MAX_VISITS = 7;

export async function runRoutePlanning() {
  const tomorrow = new Date();
  tomorrow.setDate(tomorrow.getDate() + 1);
  tomorrow.setHours(0, 0, 0, 0);

  const officers = await prisma.user.findMany({
    where: { role: 'sales_officer', isActive: true },
  });

  for (const officer of officers) {
    try {
      await buildDailyQueue(officer, tomorrow);
    } catch (e) {
      logger.error(`Route planning failed for officer ${officer.id}: ${e.message}`);
    }
  }
}

async function buildDailyQueue(officer, date) {
  // Delete any stale planned visits for tomorrow
  await prisma.visit.deleteMany({ where: { userId: officer.id, scheduledDate: date, status: 'planned' } });

  const visits = [];

  // 1. Coordinator-assigned priority visits
  const assigned = await prisma.visit.findMany({
    where: { userId: officer.id, scheduledDate: date, status: 'planned', isAdHoc: false },
    include: { location: true },
  });
  visits.push(...assigned);

  // 2. Approved carry-forward missed visits (attempts < 5)
  const carryForward = await prisma.visit.findMany({
    where: { userId: officer.id, status: 'missed', approvalStatus: 'approved', carryForwardCnt: { lt: 5 } },
    include: { location: true },
    take: MAX_VISITS - visits.length,
  });
  for (const v of carryForward) {
    await prisma.visit.update({
      where: { id: v.id },
      data: { scheduledDate: date, status: 'planned', carryForwardCnt: { increment: 1 } },
    });
    visits.push({ ...v, scheduledDate: date });
  }

  // 3. Fill remaining from area pool
  if (visits.length < MAX_VISITS && officer.areaId) {
    const sevenDaysAgo = new Date(date.getTime() - 7 * 24 * 60 * 60 * 1000);
    const recentLocationIds = (await prisma.visit.findMany({
      where: { userId: officer.id, scheduledDate: { gte: sevenDaysAgo } },
      select: { locationId: true },
    })).map((v) => v.locationId);

    const pool = await prisma.location.findMany({
      where: { areaId: officer.areaId, isActive: true, id: { notIn: recentLocationIds } },
    });

    const schools = pool.filter((l) => l.type === 'school');
    const bookshops = pool.filter((l) => l.type === 'bookshop');
    const highSchools = schools.filter((l) => l.priority === 'high').slice(0, 2);
    const medSchools = schools.filter((l) => l.priority === 'medium').slice(0, 2);
    const shops = bookshops.slice(0, 2);

    for (const loc of [...highSchools, ...medSchools, ...shops]) {
      if (visits.length >= MAX_VISITS) break;
      const newVisit = await prisma.visit.create({
        data: { userId: officer.id, locationId: loc.id, scheduledDate: date, dailySequence: 0, status: 'planned' },
        include: { location: true },
      });
      visits.push(newVisit);
    }
  }

  // 4. Order by geographic proximity (nearest-neighbor from officer's home area centroid)
  const ordered = visits.slice(0, MAX_VISITS);
  if (ordered.length > 1) {
    ordered.sort((a, b) => {
      const refLat = ordered[0].location?.latitude ?? 0;
      const refLng = ordered[0].location?.longitude ?? 0;
      return haversine(refLat, refLng, a.location.latitude, a.location.longitude) -
             haversine(refLat, refLng, b.location.latitude, b.location.longitude);
    });
  }

  // 5. Assign final sequences
  for (let i = 0; i < ordered.length; i++) {
    await prisma.visit.update({ where: { id: ordered[i].id }, data: { dailySequence: i + 1 } });
  }

  logger.info(`Built ${ordered.length} visits for officer ${officer.id} on ${date.toISOString().slice(0, 10)}`);
}
