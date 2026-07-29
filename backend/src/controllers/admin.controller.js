import prisma from '../config/database.js';
import { AppError } from '../middleware/errorHandler.js';

// ── Dashboard stats ───────────────────────────────────────────────────────────

export const getStats = async (req, res, next) => {
  try {
    const today = new Date();
    today.setHours(0, 0, 0, 0);

    const [totalBookers, activeBookers, totalCustomers, totalVisits, visitsToday, pendingLeaves, pendingMissed] =
      await prisma.$transaction([
        prisma.booker.count({ where: { adminApproved: 'APPROVED', deletedAt: null } }),
        prisma.booker.count({ where: { jobStatus: 'ACTIVE', gpsStatus: 'ACTIVE' } }),
        prisma.customer.count({ where: { deletedAt: null } }),
        prisma.visit.count(),
        prisma.visit.count({ where: { visitDate: { gte: today } } }),
        prisma.leaveRequest.count({ where: { status: 'pending' } }),
        prisma.missedVisitReason.count({ where: { status: 'pending' } }),
      ]);

    res.json({
      success: true,
      data: {
        totalBookers, activeBookers, totalCustomers, totalVisits, visitsToday,
        pendingLeaves, pendingMissed,
      },
    });
  } catch (err) { next(err); }
};

// ── Leave approvals ───────────────────────────────────────────────────────────

export const getLeaves = async (req, res, next) => {
  try {
    const { status } = req.query;
    const leaves = await prisma.leaveRequest.findMany({
      where: status ? { status } : {},
      include: { booker: { select: { id: true, name: true, email: true, phone: true } } },
      orderBy: { createdAt: 'desc' },
      take: 100,
    });
    res.json({ success: true, data: leaves });
  } catch (err) { next(err); }
};

export const reviewLeave = async (req, res, next) => {
  try {
    const id = parseInt(req.params.id);
    const { status, adminNotes } = req.body;

    if (!['approved', 'rejected'].includes(status)) {
      throw new AppError('INVALID_STATUS', 400, 'Status must be approved or rejected');
    }

    const leave = await prisma.leaveRequest.findUnique({ where: { id } });
    if (!leave) throw new AppError('NOT_FOUND', 404, 'Leave request not found');

    // If approving, deduct leave balance
    if (status === 'approved' && leave.status !== 'approved') {
      const field = leave.leaveType === 'sick' ? 'leaveBalanceSick' : 'leaveBalanceCasual';
      await prisma.booker.update({
        where: { id: leave.bookerId },
        data: { [field]: { decrement: leave.days } },
      });
    }

    const updated = await prisma.leaveRequest.update({
      where: { id },
      data: { status, adminNotes: adminNotes ?? null, reviewedBy: req.user?.id ?? null, reviewedAt: new Date() },
    });

    res.json({ success: true, data: updated });
  } catch (err) { next(err); }
};

// ── Missed visit approvals ────────────────────────────────────────────────────

export const getMissedVisits = async (req, res, next) => {
  try {
    const { status } = req.query;
    const reasons = await prisma.missedVisitReason.findMany({
      where: status ? { status } : {},
      include: {
        booker: { select: { id: true, name: true, email: true } },
        visit: {
          select: {
            id: true, visitDate: true,
            customer: { select: { id: true, name: true, customerType: true } },
          },
        },
      },
      orderBy: { createdAt: 'desc' },
      take: 100,
    });
    res.json({ success: true, data: reasons });
  } catch (err) { next(err); }
};

export const reviewMissedVisit = async (req, res, next) => {
  try {
    const id = parseInt(req.params.id);
    const { status, adminNote } = req.body;

    if (!['approved', 'rejected'].includes(status)) {
      throw new AppError('INVALID_STATUS', 400, 'Status must be approved or rejected');
    }

    const reason = await prisma.missedVisitReason.update({
      where: { id },
      data: { status, adminNote: adminNote ?? null },
    });

    res.json({ success: true, data: reason });
  } catch (err) { next(err); }
};

// ── Booker management ─────────────────────────────────────────────────────────

export const getBookers = async (req, res, next) => {
  try {
    const { status, cityId } = req.query;
    const bookers = await prisma.booker.findMany({
      where: {
        deletedAt: null,
        ...(status && { adminApproved: status }),
        ...(cityId && { cityId: parseInt(cityId) }),
      },
      select: {
        id: true, name: true, email: true, phone: true,
        jobStatus: true, adminApproved: true, cityId: true, regionId: true,
        visitTargets: true, ratesPerVisit: true, basicSalary: true,
        gpsStatus: true, lastSeenAt: true,
        city: { select: { name: true } },
        region: { select: { name: true } },
      },
      orderBy: { createdAt: 'desc' },
    });
    res.json({ success: true, data: bookers });
  } catch (err) { next(err); }
};

export const approveBooker = async (req, res, next) => {
  try {
    const id = parseInt(req.params.id);
    const { approved, jobStatus } = req.body;

    const booker = await prisma.booker.update({
      where: { id },
      data: {
        adminApproved: approved ? 'APPROVED' : 'NOT_APPROVED',
        ...(jobStatus && { jobStatus }),
      },
    });

    res.json({ success: true, data: booker });
  } catch (err) { next(err); }
};

// ── Attendance overview ───────────────────────────────────────────────────────

export const getAttendance = async (req, res, next) => {
  try {
    const date = req.query.date ? new Date(req.query.date) : new Date();
    date.setHours(0, 0, 0, 0);

    const records = await prisma.attendance.findMany({
      where: { date },
      include: { booker: { select: { id: true, name: true, email: true, phone: true } } },
      orderBy: { startAt: 'desc' },
    });

    res.json({ success: true, data: records });
  } catch (err) { next(err); }
};
