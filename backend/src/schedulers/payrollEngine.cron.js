import prisma from '../config/database.js';
import logger from '../utils/logger.js';

export async function runPayrollEngine() {
  const now = new Date();
  const lastDayOfMonth = new Date(now.getFullYear(), now.getMonth() + 1, 0).getDate();
  if (now.getDate() !== lastDayOfMonth) return; // Guard: only run on last day

  const month = now.getMonth() + 1;
  const year = now.getFullYear();
  const monthStart = new Date(year, month - 1, 1);
  const monthEnd = new Date(year, month, 0, 23, 59, 59);

  const officers = await prisma.user.findMany({ where: { role: 'sales_officer', isActive: true } });

  for (const officer of officers) {
    try {
      await prisma.$transaction(async (tx) => {
        const presentDays = await tx.attendance.count({
          where: { userId: officer.id, status: 'present', date: { gte: monthStart, lte: monthEnd } },
        });

        const performanceEarned = presentDays * officer.dailyPerformanceRate.toNumber();

        // Penalty: rejected missed visit approvals this month
        const rejectedVisits = await tx.visit.count({
          where: { userId: officer.id, status: 'missed', approvalStatus: 'rejected', scheduledDate: { gte: monthStart, lte: monthEnd } },
        });
        const missedVisitPenalty = rejectedVisits * officer.dailyPerformanceRate.toNumber();

        // Sample deduction: unrecovered after 20 days
        const overduesamples = await tx.sampleRequest.findMany({
          where: { userId: officer.id, status: 'dispatched', reminder20Sent: true, recoveredAt: null },
        });
        const sampleDeduction = overduesamples.reduce((sum, s) => sum + s.totalValuePkr.toNumber(), 0);

        const securityDepositHeld = officer.basicSalary.toNumber() * 0.10;
        const netPayable = officer.basicSalary.toNumber() + performanceEarned - missedVisitPenalty - sampleDeduction - securityDepositHeld;

        await tx.payrollLedger.upsert({
          where: { userId_month_year: { userId: officer.id, month, year } },
          create: {
            userId: officer.id, month, year, presentDays,
            basicSalary: officer.basicSalary, performanceEarned, missedVisitPenalty,
            sampleDeduction, securityDepositHeld, netPayable, isFinalized: true, calculatedAt: new Date(),
          },
          update: {
            presentDays, performanceEarned, missedVisitPenalty, sampleDeduction,
            securityDepositHeld, netPayable, isFinalized: true, calculatedAt: new Date(),
          },
        });

        logger.info(`Payroll finalized: officer ${officer.id}, month ${month}/${year}, net PKR ${netPayable.toFixed(2)}`);
      });
    } catch (e) {
      logger.error(`Payroll failed for officer ${officer.id}: ${e.message}`);
    }
  }
}
