import prisma from '../config/database.js';
import { AppError } from '../middleware/errorHandler.js';

// GET /api/v1/leaves/balance
export const getLeaveBalance = async (req, res, next) => {
  try {
    const bookerId = req.user.id;
    const booker = await prisma.booker.findUnique({
      where: { id: bookerId },
      select: { leaveBalanceSick: true, leaveBalanceCasual: true },
    });

    res.json({
      success: true,
      data: {
        sick: { total: 10, remaining: booker.leaveBalanceSick },
        casual: { total: 18, remaining: booker.leaveBalanceCasual },
        totalRemaining: booker.leaveBalanceSick + booker.leaveBalanceCasual,
      },
    });
  } catch (err) { next(err); }
};

// GET /api/v1/leaves/my — my leave history
export const getMyLeaves = async (req, res, next) => {
  try {
    const bookerId = req.user.id;
    const year = parseInt(req.query.year ?? new Date().getFullYear());
    const yearStart = new Date(`${year}-01-01`);

    const [balance, leaves] = await Promise.all([
      prisma.booker.findUnique({
        where: { id: bookerId },
        select: { leaveBalanceSick: true, leaveBalanceCasual: true },
      }),
      prisma.leaveRequest.findMany({
        where: { bookerId, fromDate: { gte: yearStart } },
        orderBy: { createdAt: 'desc' },
        take: 50,
      }),
    ]);

    res.json({
      success: true,
      data: {
        balance: {
          sickRemaining: balance.leaveBalanceSick,
          casualRemaining: balance.leaveBalanceCasual,
        },
        history: leaves,
      },
    });
  } catch (err) { next(err); }
};

// POST /api/v1/leaves/apply
export const applyLeave = async (req, res, next) => {
  try {
    const bookerId = req.user.id;
    const { leaveType, fromDate, toDate, reason } = req.body;

    if (!leaveType || !fromDate || !toDate || !reason) {
      throw new AppError('MISSING_FIELDS', 400, 'All fields are required');
    }
    if (!['sick', 'casual'].includes(leaveType)) {
      throw new AppError('INVALID_TYPE', 400, 'Leave type must be sick or casual');
    }

    const from = new Date(fromDate);
    const to = new Date(toDate);
    if (from > to) throw new AppError('INVALID_DATES', 400, 'From date must be before to date');

    const days = Math.ceil((to - from) / 86400000) + 1;

    const booker = await prisma.booker.findUnique({
      where: { id: bookerId },
      select: { leaveBalanceSick: true, leaveBalanceCasual: true },
    });

    const available = leaveType === 'sick' ? booker.leaveBalanceSick : booker.leaveBalanceCasual;
    if (days > available) {
      throw new AppError(
        'INSUFFICIENT_LEAVE',
        422,
        `Insufficient ${leaveType} leave. You have ${available} days remaining, requested ${days}`
      );
    }

    const leave = await prisma.leaveRequest.create({
      data: { bookerId, leaveType, fromDate: from, toDate: to, days, reason, status: 'pending' },
    });

    res.status(201).json({
      success: true,
      data: {
        id: leave.id,
        days,
        remainingAfterApproval: available - days,
        message: 'Leave request submitted, pending admin approval',
      },
    });
  } catch (err) { next(err); }
};
