import prisma from '../config/database.js';
import { AppError } from '../middleware/errorHandler.js';

export const applyLeave = async (req, res, next) => {
  try {
    const { leaveType, startDate, endDate, reason } = req.body;
    const userId = req.user.id;
    const user = await prisma.user.findUnique({ where: { id: userId } });

    const start = new Date(startDate);
    const end = new Date(endDate);
    const days = Math.round((end - start) / (1000 * 60 * 60 * 24)) + 1;

    const balance = leaveType === 'sick' ? user.leaveBalanceSick : user.leaveBalanceCasual;
    if (days > balance) {
      throw new AppError('INSUFFICIENT_LEAVE', 422, `Not enough ${leaveType} leave balance. Available: ${balance} days, requested: ${days}`);
    }

    const leave = await prisma.leaveRequest.create({
      data: { userId, leaveType, startDate: start, endDate: end, days, reason },
    });

    res.status(201).json({
      success: true,
      data: { id: leave.id, days, [`remaining${leaveType.charAt(0).toUpperCase() + leaveType.slice(1)}`]: balance - days },
    });
  } catch (err) { next(err); }
};

export const getLeaveBalance = async (req, res, next) => {
  try {
    const user = await prisma.user.findUnique({
      where: { id: req.user.id },
      select: { leaveBalanceSick: true, leaveBalanceCasual: true },
    });
    res.json({ success: true, data: { sick: user.leaveBalanceSick, casual: user.leaveBalanceCasual, total: user.leaveBalanceSick + user.leaveBalanceCasual } });
  } catch (err) { next(err); }
};
