import prisma from '../config/database.js';
import { AppError } from '../middleware/errorHandler.js';

export const dayStart = async (req, res, next) => {
  try {
    const { latitude, longitude, isMocked } = req.body;
    const userId = req.user.id;
    const today = new Date();
    today.setHours(0, 0, 0, 0);

    if (isMocked) {
      await prisma.gpsLog.create({
        data: { userId, latitude, longitude, accuracy: 0, isMocked: true },
      });
      throw new AppError('MOCK_LOCATION', 403, 'Mock GPS detected. Action blocked and logged.');
    }

    const existing = await prisma.attendance.findUnique({
      where: { userId_date: { userId, date: today } },
    });
    if (existing?.dayStartTime) {
      throw new AppError('ALREADY_STARTED', 409, 'You have already started your day');
    }

    const attendance = await prisma.attendance.upsert({
      where: { userId_date: { userId, date: today } },
      create: { userId, date: today, dayStartTime: new Date(), dayStartLat: latitude, dayStartLng: longitude, status: 'present' },
      update: { dayStartTime: new Date(), dayStartLat: latitude, dayStartLng: longitude, status: 'present' },
    });

    const todayVisits = await prisma.visit.findMany({
      where: { userId, scheduledDate: today },
      include: { location: true },
      orderBy: { dailySequence: 'asc' },
    });

    res.status(201).json({
      success: true,
      data: {
        attendanceId: attendance.id,
        dayStartTime: attendance.dayStartTime,
        todayVisits: todayVisits.map((v) => ({
          id: v.id, sequence: v.dailySequence, status: v.status,
          locationName: v.location.name,
          lat: v.location.latitude, lng: v.location.longitude,
          carryForwardCnt: v.carryForwardCnt,
        })),
      },
    });
  } catch (err) { next(err); }
};

export const dayEnd = async (req, res, next) => {
  try {
    const { latitude, longitude } = req.body;
    const userId = req.user.id;
    const today = new Date();
    today.setHours(0, 0, 0, 0);

    await prisma.attendance.update({
      where: { userId_date: { userId, date: today } },
      data: { dayEndTime: new Date(), dayEndLat: latitude, dayEndLng: longitude },
    });

    const [completed, missed] = await Promise.all([
      prisma.visit.count({ where: { userId, scheduledDate: today, status: 'completed' } }),
      prisma.visit.count({ where: { userId, scheduledDate: today, status: 'missed' } }),
    ]);

    res.json({ success: true, data: { dayEndTime: new Date(), visitsCompleted: completed, visitsMissed: missed } });
  } catch (err) { next(err); }
};

export const cannotWork = async (req, res, next) => {
  try {
    const { reason, notes } = req.body;
    const userId = req.user.id;
    const today = new Date();
    today.setHours(0, 0, 0, 0);

    await prisma.attendance.upsert({
      where: { userId_date: { userId, date: today } },
      create: { userId, date: today, status: 'cannot_work', cannotWorkReason: `${reason}: ${notes || ''}` },
      update: { status: 'cannot_work', cannotWorkReason: `${reason}: ${notes || ''}` },
    });

    res.json({ success: true, data: { message: 'Cannot-work declaration submitted' } });
  } catch (err) { next(err); }
};
