import prisma from '../config/database.js';

export const getOfficers = async (req, res, next) => {
  try {
    const where = { role: 'sales_officer', isActive: true };
    if (req.user.role === 'city_head') where.cityId = req.user.cityId;

    const officers = await prisma.user.findMany({ where, select: { id: true, name: true, email: true, cityId: true, areaId: true } });
    res.json({ success: true, data: { officers } });
  } catch (err) { next(err); }
};

export const getLiveTracking = async (req, res, next) => {
  try {
    const fiveMinAgo = new Date(Date.now() - 5 * 60 * 1000);
    const logs = await prisma.gpsLog.findMany({
      where: { recordedAt: { gte: fiveMinAgo }, isMocked: false },
      orderBy: { recordedAt: 'desc' },
      distinct: ['userId'],
      include: { user: { select: { name: true } } },
    });

    const officers = logs.map((l) => ({
      userId: l.userId, name: l.user.name,
      lastPing: l.recordedAt, lat: l.latitude, lng: l.longitude, batteryLevel: l.batteryLevel,
    }));

    res.json({ success: true, data: { officers } });
  } catch (err) { next(err); }
};

export const getMissedVisits = async (req, res, next) => {
  try {
    const where = { status: 'missed', approvalStatus: 'pending' };
    if (req.user.role === 'city_head') {
      where.user = { cityId: req.user.cityId };
    }
    const visits = await prisma.visit.findMany({
      where, include: { user: { select: { name: true } }, location: { select: { name: true } } },
      orderBy: { updatedAt: 'desc' },
    });
    res.json({ success: true, data: { visits } });
  } catch (err) { next(err); }
};

export const approveMissedVisit = async (req, res, next) => {
  try {
    const id = parseInt(req.params.id);
    const { approved, comment } = req.body;
    const status = approved ? 'approved' : 'rejected';

    await prisma.$transaction(async (tx) => {
      await tx.visit.update({ where: { id }, data: { approvalStatus: status, approvedById: req.user.id } });
      if (!approved) {
        const visit = await tx.visit.findUnique({ where: { id }, include: { user: true } });
        await tx.user.update({
          where: { id: visit.userId },
          data: { },  // Penalty handled by payroll engine
        });
      }
      await tx.auditLog.create({
        data: { actorId: req.user.id, action: `missed_visit_${status}`, targetType: 'visit', targetId: id, after: { comment }, ipAddress: req.ip },
      });
    });

    res.json({ success: true, data: { approvalStatus: status } });
  } catch (err) { next(err); }
};

export const getPayroll = async (req, res, next) => {
  try {
    const month = parseInt(req.params.month);
    const year = parseInt(req.params.year);
    const where = { month, year };
    if (req.user.role === 'city_head') where.user = { cityId: req.user.cityId };

    const ledgers = await prisma.payrollLedger.findMany({
      where, include: { user: { select: { name: true, email: true } } },
    });
    res.json({ success: true, data: { month, year, ledgers } });
  } catch (err) { next(err); }
};

export const approveLeave = async (req, res, next) => {
  try {
    const id = parseInt(req.params.id);
    const { approved } = req.body;

    await prisma.$transaction(async (tx) => {
      const leave = await tx.leaveRequest.findUnique({ where: { id } });
      const status = approved ? 'approved' : 'rejected';
      await tx.leaveRequest.update({ where: { id }, data: { status, approvedById: req.user.id } });

      if (approved) {
        const field = leave.leaveType === 'sick' ? 'leaveBalanceSick' : 'leaveBalanceCasual';
        await tx.user.update({ where: { id: leave.userId }, data: { [field]: { decrement: leave.days } } });
      }

      await tx.auditLog.create({
        data: { actorId: req.user.id, action: `leave_${status}`, targetType: 'leave_request', targetId: id, ipAddress: req.ip },
      });
    });

    res.json({ success: true, data: { message: `Leave ${approved ? 'approved' : 'rejected'}` } });
  } catch (err) { next(err); }
};

export const approveSample = async (req, res, next) => {
  try {
    const id = parseInt(req.params.id);
    const { approved } = req.body;
    const status = approved ? 'approved' : 'rejected';
    await prisma.sampleRequest.update({ where: { id }, data: { status, approvedById: req.user.id } });
    res.json({ success: true, data: { status } });
  } catch (err) { next(err); }
};

export const getLocationHistory = async (req, res, next) => {
  try {
    const locationId = parseInt(req.params.id);
    const visits = await prisma.visit.findMany({
      where: { locationId, status: 'completed' },
      include: { user: { select: { name: true } } },
      orderBy: { scheduledDate: 'desc' },
    });
    res.json({ success: true, data: { visits } });
  } catch (err) { next(err); }
};
