import prisma from '../config/database.js';
import { AppError } from '../middleware/errorHandler.js';

// POST /api/v1/workday/day-start
export const dayStart = async (req, res, next) => {
  try {
    const bookerId = req.user.id;
    const { lat, latitude, lng, longitude, cannotReason } = req.body;
    const finalLat = lat ?? latitude;
    const finalLng = lng ?? longitude;

    const today = new Date();
    today.setHours(0, 0, 0, 0);

    const existing = await prisma.attendance.findUnique({
      where: { bookerId_date: { bookerId, date: today } },
    });

    if (existing?.startAt) {
      throw new AppError('ALREADY_STARTED', 409, 'Day already started');
    }

    const attendance = await prisma.attendance.upsert({
      where: { bookerId_date: { bookerId, date: today } },
      create: { bookerId, date: today, startAt: new Date(), startLat: finalLat, startLng: finalLng, status: 'present' },
      update: { startAt: new Date(), startLat: finalLat, startLng: finalLng, status: 'present' },
    });

    await prisma.booker.update({
      where: { id: bookerId },
      data: { gpsStatus: 'ACTIVE', lastLatitude: finalLat, lastLongitude: finalLng, lastSeenAt: new Date() },
    });

    res.json({ success: true, data: attendance, message: 'Day started. Your visit queue is ready.' });
  } catch (err) { next(err); }
};

// POST /api/v1/workday/day-end
export const dayEnd = async (req, res, next) => {
  try {
    const bookerId = req.user.id;
    const { lat, latitude, lng, longitude } = req.body;
    const finalLat = lat ?? latitude;
    const finalLng = lng ?? longitude;

    const today = new Date();
    today.setHours(0, 0, 0, 0);

    const attendance = await prisma.attendance.findUnique({
      where: { bookerId_date: { bookerId, date: today } },
    });

    if (!attendance?.startAt) {
      throw new AppError('NOT_STARTED', 400, 'You have not started your day yet');
    }

    const updated = await prisma.attendance.update({
      where: { bookerId_date: { bookerId, date: today } },
      data: { endAt: new Date(), endLat: finalLat, endLng: finalLng },
    });

    await prisma.booker.update({
      where: { id: bookerId },
      data: { gpsStatus: 'OFFLINE', lastLatitude: finalLat, lastLongitude: finalLng, lastSeenAt: new Date() },
    });

    // Summary of today's work
    const visitsToday = await prisma.visit.groupBy({
      by: ['status'],
      where: { bookerId, visitDate: today },
      _count: { id: true },
    });

    const summary = Object.fromEntries(visitsToday.map(v => [v.status.toLowerCase(), v._count.id]));

    res.json({
      success: true,
      data: { attendance: updated, summary },
      message: 'Day ended. Great work today!',
    });
  } catch (err) { next(err); }
};

// POST /api/v1/workday/cannot-work
export const cannotWork = async (req, res, next) => {
  try {
    const bookerId = req.user.id;
    const { reason } = req.body;

    if (!reason?.trim()) {
      throw new AppError('MISSING_REASON', 400, 'A reason is required');
    }

    const today = new Date();
    today.setHours(0, 0, 0, 0);

    const attendance = await prisma.attendance.upsert({
      where: { bookerId_date: { bookerId, date: today } },
      create: { bookerId, date: today, status: 'cannot_work', cannotReason: reason.trim() },
      update: { status: 'cannot_work', cannotReason: reason.trim() },
    });

    res.json({ success: true, data: attendance, message: 'Declaration submitted. Stay safe.' });
  } catch (err) { next(err); }
};

// GET /api/v1/workday/status — today's attendance status
export const getStatus = async (req, res, next) => {
  try {
    const bookerId = req.user.id;
    const today = new Date();
    today.setHours(0, 0, 0, 0);

    const attendance = await prisma.attendance.findUnique({
      where: { bookerId_date: { bookerId, date: today } },
    });

    const visitsToday = await prisma.visit.groupBy({
      by: ['status'],
      where: { bookerId, visitDate: today },
      _count: { id: true },
    });

    const visitSummary = Object.fromEntries(visitsToday.map(v => [v.status.toLowerCase(), v._count.id]));

    res.json({ success: true, data: { attendance, visitSummary } });
  } catch (err) { next(err); }
};
