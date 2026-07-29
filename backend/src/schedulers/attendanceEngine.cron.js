import prisma from '../config/database.js';
import logger from '../utils/logger.js';

// Runs at 11:00 PM daily — marks missing attendance as absent
export async function runAttendanceEngine() {
  const today = new Date();
  today.setHours(0, 0, 0, 0);

  logger.info(`[Attendance Engine] Checking for missing attendance on ${today.toISOString().slice(0, 10)}`);

  // Get all active bookers
  const bookers = await prisma.booker.findMany({
    where: { jobStatus: 'ACTIVE', adminApproved: 'APPROVED' },
    select: { id: true },
  });

  // Get those who already have an attendance record today
  const presentIds = (await prisma.attendance.findMany({
    where: { date: today },
    select: { bookerId: true },
  })).map(a => a.bookerId);

  // Absent bookers = all active - those with records
  const absentIds = bookers
    .map(b => b.id)
    .filter(id => !presentIds.includes(id));

  if (absentIds.length === 0) {
    logger.info('[Attendance Engine] All bookers checked in today.');
    return;
  }

  // Create absent records
  await prisma.attendance.createMany({
    data: absentIds.map(bookerId => ({
      bookerId,
      date: today,
      status: 'absent',
    })),
    skipDuplicates: true,
  });

  logger.info(`[Attendance Engine] Marked ${absentIds.length} bookers as absent.`);
}
