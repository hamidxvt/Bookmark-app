import prisma from '../config/database.js';
import logger from '../utils/logger.js';

// Runs on last day of each month at 11:55 PM
export async function runPayrollEngine() {
  const now = new Date();
  const lastDay = new Date(now.getFullYear(), now.getMonth() + 1, 0).getDate();

  // Only run on the last day of the month
  if (now.getDate() !== lastDay) return;

  const month = now.getMonth() + 1;
  const year = now.getFullYear();
  const monthStart = new Date(year, month - 1, 1);
  const monthEnd = new Date(year, month, 0, 23, 59, 59);

  logger.info(`[Payroll Engine] Finalizing payroll for ${month}/${year}`);

  const bookers = await prisma.booker.findMany({
    where: { jobStatus: 'ACTIVE', adminApproved: 'APPROVED' },
    select: { id: true, basicSalary: true, ratesPerVisit: true },
  });

  for (const booker of bookers) {
    try {
      await prisma.$transaction(async (tx) => {
        const dailyRate = Number(booker.ratesPerVisit ?? 3000);
        const basic = Number(booker.basicSalary ?? 0);

        const presentDays = await tx.attendance.count({
          where: { bookerId: booker.id, status: 'present', date: { gte: monthStart, lte: monthEnd } },
        });

        const performanceEarned = presentDays * dailyRate;

        const rejectedMissed = await tx.missedVisitReason.count({
          where: {
            bookerId: booker.id,
            status: 'rejected',
            visit: { visitDate: { gte: monthStart, lte: monthEnd } },
          },
        });
        const missedVisitPenalty = rejectedMissed * dailyRate;

        const overduesamples = await tx.sampleRequest.findMany({
          where: { bookerId: booker.id, reminder20Sent: true, recoveredAt: null },
        });
        const sampleDeduction = overduesamples.reduce((sum, s) => sum + Number(s.totalValuePkr), 0);

        const securityDepositHeld = basic * 0.10;
        const netPayable = basic + performanceEarned - missedVisitPenalty - sampleDeduction - securityDepositHeld;

        await tx.payrollLedger.upsert({
          where: { bookerId_month_year: { bookerId: booker.id, month, year } },
          create: {
            bookerId: booker.id, month, year, presentDays,
            basicSalary: basic, performanceEarned, missedVisitPenalty,
            sampleDeduction, securityDepositHeld, netPayable,
            isFinalized: true, calculatedAt: new Date(),
          },
          update: {
            presentDays, performanceEarned, missedVisitPenalty,
            sampleDeduction, securityDepositHeld, netPayable,
            isFinalized: true, calculatedAt: new Date(),
          },
        });

        // Reset annual leave balance on December
        if (month === 12) {
          await tx.booker.update({
            where: { id: booker.id },
            data: { leaveBalanceSick: 10, leaveBalanceCasual: 18 },
          });
        }

        logger.info(`[Payroll] Booker ${booker.id}: Basic ${basic} + Perf ${performanceEarned.toFixed(0)} - Penalties ${(missedVisitPenalty + sampleDeduction).toFixed(0)} = Net PKR ${netPayable.toFixed(0)}`);
      });
    } catch (e) {
      logger.error(`[Payroll] Failed for booker ${booker.id}: ${e.message}`);
    }
  }

  logger.info(`[Payroll Engine] Done. Finalized for ${bookers.length} bookers.`);
}
