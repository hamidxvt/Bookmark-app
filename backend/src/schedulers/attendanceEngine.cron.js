import prisma from '../config/database.js';
import logger from '../utils/logger.js';

export async function runAttendanceEngine() {
  const today = new Date();
  today.setHours(0, 0, 0, 0);

  const officers = await prisma.user.findMany({ where: { role: 'sales_officer', isActive: true } });

  for (const officer of officers) {
    const attendance = await prisma.attendance.findUnique({
      where: { userId_date: { userId: officer.id, date: today } },
    });

    if (!attendance || (!attendance.dayStartTime && attendance.status !== 'cannot_work' && attendance.status !== 'leave')) {
      // Mark absent and deduct leave
      await prisma.$transaction(async (tx) => {
        await tx.attendance.upsert({
          where: { userId_date: { userId: officer.id, date: today } },
          create: { userId: officer.id, date: today, status: 'absent' },
          update: { status: 'absent' },
        });

        // Deduct 1 casual leave, then sick, then flag unpaid
        if (officer.leaveBalanceCasual > 0) {
          await tx.user.update({ where: { id: officer.id }, data: { leaveBalanceCasual: { decrement: 1 } } });
        } else if (officer.leaveBalanceSick > 0) {
          await tx.user.update({ where: { id: officer.id }, data: { leaveBalanceSick: { decrement: 1 } } });
        }

        await tx.auditLog.create({
          data: { actorId: 1, action: 'auto_absence_marked', targetType: 'attendance', targetId: officer.id,
            after: { date: today, reason: 'No day-start recorded by 11PM' } },
        });
      });

      logger.info(`Auto-absence marked for officer ${officer.id} on ${today.toISOString().slice(0, 10)}`);
    }
  }
}
