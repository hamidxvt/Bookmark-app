import prisma from '../config/database.js';
import { AppError } from '../middleware/errorHandler.js';
import logger from '../utils/logger.js';

// ── Helpers ──────────────────────────────────────────────────────────────────

async function calculatePayrollForBooker(bookerId, month, year, tx) {
  const db = tx ?? prisma;
  const monthStart = new Date(year, month - 1, 1);
  const monthEnd = new Date(year, month, 0, 23, 59, 59);

  const booker = await db.booker.findUnique({
    where: { id: bookerId },
    select: { id: true, name: true, basicSalary: true, ratesPerVisit: true, annualSampleLimit: true },
  });
  if (!booker) throw new AppError('NOT_FOUND', 404, 'Booker not found');

  const dailyRate = Number(booker.ratesPerVisit ?? 3000);
  const basic = Number(booker.basicSalary ?? 0);

  // Present days
  const presentDays = await db.attendance.count({
    where: { bookerId, status: 'present', date: { gte: monthStart, lte: monthEnd } },
  });

  // Performance earned = present days × daily rate
  const performanceEarned = presentDays * dailyRate;

  // Penalty: rejected missed visits this month
  const rejectedMissed = await db.missedVisitReason.count({
    where: {
      bookerId,
      status: 'rejected',
      visit: { visitDate: { gte: monthStart, lte: monthEnd } },
    },
  });
  const missedVisitPenalty = rejectedMissed * dailyRate;

  // Sample deductions: unreturned after 20 days
  const overduesamples = await db.sampleRequest.findMany({
    where: { bookerId, reminder20Sent: true, recoveredAt: null, status: { not: 'pending' } },
  });
  const sampleDeduction = overduesamples.reduce((sum, s) => sum + Number(s.totalValuePkr), 0);

  // Security deposit = 10% of basic
  const securityDepositHeld = basic * 0.10;

  const netPayable = basic + performanceEarned - missedVisitPenalty - sampleDeduction - securityDepositHeld;

  return {
    bookerId,
    bookerName: booker.name,
    month,
    year,
    presentDays,
    basicSalary: basic,
    dailyRate,
    performanceEarned,
    rejectedMissedVisits: rejectedMissed,
    missedVisitPenalty,
    sampleDeduction,
    securityDepositHeld,
    netPayable,
  };
}

// ── Controllers ──────────────────────────────────────────────────────────────

// GET /api/v1/payroll?month=7&year=2026&bookerId=1
export const getPayrollSummary = async (req, res, next) => {
  try {
    const month = parseInt(req.query.month ?? new Date().getMonth() + 1);
    const year = parseInt(req.query.year ?? new Date().getFullYear());
    const bookerId = req.query.bookerId ? parseInt(req.query.bookerId) : null;

    if (bookerId) {
      const data = await calculatePayrollForBooker(bookerId, month, year);
      return res.json({ success: true, data });
    }

    // Return all bookers for this month
    const bookers = await prisma.booker.findMany({
      where: { jobStatus: 'ACTIVE', adminApproved: 'APPROVED' },
      select: { id: true },
    });

    const results = await Promise.all(
      bookers.map(b => calculatePayrollForBooker(b.id, month, year).catch(e => {
        logger.error(`Payroll calc error for booker ${b.id}: ${e.message}`);
        return null;
      }))
    );

    res.json({ success: true, data: results.filter(Boolean), month, year });
  } catch (err) { next(err); }
};

// POST /api/v1/payroll/finalize — calculate and save payroll for all officers
export const finalizePayroll = async (req, res, next) => {
  try {
    const { month, year } = req.body;
    const m = parseInt(month ?? new Date().getMonth() + 1);
    const y = parseInt(year ?? new Date().getFullYear());

    const bookers = await prisma.booker.findMany({
      where: { jobStatus: 'ACTIVE', adminApproved: 'APPROVED' },
      select: { id: true },
    });

    const results = [];
    for (const booker of bookers) {
      try {
        const data = await calculatePayrollForBooker(booker.id, m, y);
        const saved = await prisma.payrollLedger.upsert({
          where: { bookerId_month_year: { bookerId: booker.id, month: m, year: y } },
          create: { ...data, isFinalized: true, calculatedAt: new Date() },
          update: { ...data, isFinalized: true, calculatedAt: new Date() },
        });
        results.push(saved);
      } catch (e) {
        logger.error(`Payroll finalize error for booker ${booker.id}: ${e.message}`);
      }
    }

    res.json({ success: true, data: results, message: `Payroll finalized for ${results.length} bookers` });
  } catch (err) { next(err); }
};

// GET /api/v1/payroll/me — current user's payroll slip
export const getMyPayroll = async (req, res, next) => {
  try {
    const bookerId = req.user.id;
    const month = parseInt(req.query.month ?? new Date().getMonth() + 1);
    const year = parseInt(req.query.year ?? new Date().getFullYear());

    // Check if finalized first
    const saved = await prisma.payrollLedger.findUnique({
      where: { bookerId_month_year: { bookerId, month, year } },
    });

    if (saved) {
      return res.json({ success: true, data: saved });
    }

    // Live calculation
    const data = await calculatePayrollForBooker(bookerId, month, year);
    res.json({ success: true, data, isLive: true });
  } catch (err) { next(err); }
};
