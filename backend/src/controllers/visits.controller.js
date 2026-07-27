import prisma from '../config/database.js';
import { AppError } from '../middleware/errorHandler.js';
import { haversine } from '../utils/geoDistance.js';

const GEOFENCE_METERS = 200;

export const getTodayVisits = async (req, res, next) => {
  try {
    const userId = req.user.id;
    const today = new Date();
    today.setHours(0, 0, 0, 0);

    const visits = await prisma.visit.findMany({
      where: { userId, scheduledDate: today },
      include: { location: { select: { id: true, name: true, type: true, priority: true, latitude: true, longitude: true, contactName: true, contactPhone: true } } },
      orderBy: { dailySequence: 'asc' },
    });

    res.json({ success: true, data: { date: today.toISOString().slice(0, 10), visits } });
  } catch (err) { next(err); }
};

export const startVisit = async (req, res, next) => {
  try {
    const visitId = parseInt(req.params.id);
    const { arrivalLat, arrivalLng, isMocked } = req.body;

    if (isMocked) throw new AppError('MOCK_LOCATION_DETECTED', 403, 'GPS spoofing detected. Incident logged.');

    const visit = await prisma.visit.findFirst({ where: { id: visitId, userId: req.user.id }, include: { location: true } });
    if (!visit) throw new AppError('NOT_FOUND', 404, 'Visit not found');

    const dist = haversine(arrivalLat, arrivalLng, visit.location.latitude, visit.location.longitude);
    if (dist > GEOFENCE_METERS) {
      throw new AppError('OUTSIDE_GEOFENCE', 422, `You must be within ${GEOFENCE_METERS}m of the location. You are ${Math.round(dist)}m away.`);
    }

    const updated = await prisma.visit.update({
      where: { id: visitId },
      data: { status: 'in_progress', arrivalTime: new Date(), arrivalLat, arrivalLng },
    });

    res.json({ success: true, data: { status: updated.status, arrivalTime: updated.arrivalTime } });
  } catch (err) { next(err); }
};

export const completeVisit = async (req, res, next) => {
  try {
    const visitId = parseInt(req.params.id);
    const { contactPerson, designation, phone, notes, visitType, sampleDistributed, followUpDate } = req.body;

    const visit = await prisma.visit.findFirst({ where: { id: visitId, userId: req.user.id } });
    if (!visit) throw new AppError('NOT_FOUND', 404, 'Visit not found');

    await prisma.$transaction(async (tx) => {
      await tx.visit.update({
        where: { id: visitId },
        data: { status: 'completed', completionTime: new Date(), contactPerson, designation, phone, notes, visitType, sampleDistributed },
      });

      if (followUpDate) {
        const followUp = new Date(followUpDate);
        const count = await tx.visit.count({ where: { userId: req.user.id, scheduledDate: followUp } });
        if (count < 7) {
          await tx.visit.create({
            data: { userId: req.user.id, locationId: visit.locationId, scheduledDate: followUp, dailySequence: count + 1, status: 'planned', isAdHoc: false },
          });
        }
      }
    });

    res.json({ success: true, data: { status: 'completed', completionTime: new Date() } });
  } catch (err) { next(err); }
};

export const editVisit = async (req, res, next) => {
  try {
    const visitId = parseInt(req.params.id);
    const visit = await prisma.visit.findFirst({ where: { id: visitId, userId: req.user.id } });
    if (!visit) throw new AppError('NOT_FOUND', 404, 'Visit not found');

    const scheduled = new Date(visit.scheduledDate);
    const endOfDay = new Date(scheduled);
    endOfDay.setHours(23, 59, 59, 999);

    if (new Date() > endOfDay) {
      throw new AppError('EDIT_WINDOW_CLOSED', 422, 'Visit details cannot be edited after 11:59 PM on the scheduled date');
    }

    await prisma.visit.update({ where: { id: visitId }, data: req.body });
    res.json({ success: true, data: { message: 'Visit updated' } });
  } catch (err) { next(err); }
};

export const markMissed = async (req, res, next) => {
  try {
    const visitId = parseInt(req.params.id);
    const { reason, photoUrl } = req.body;

    const visit = await prisma.visit.findFirst({ where: { id: visitId, userId: req.user.id } });
    if (!visit) throw new AppError('NOT_FOUND', 404, 'Visit not found');

    const updated = await prisma.visit.update({
      where: { id: visitId },
      data: { status: 'missed', missedReason: reason, photoUrl, carryForwardCnt: visit.carryForwardCnt + 1, approvalStatus: 'pending' },
    });

    res.json({ success: true, data: { status: 'missed', carryForwardCnt: updated.carryForwardCnt, approvalStatus: 'pending' } });
  } catch (err) { next(err); }
};

export const createAdhoc = async (req, res, next) => {
  try {
    const { locationId, notes, visitType } = req.body;
    const userId = req.user.id;
    const today = new Date();
    today.setHours(0, 0, 0, 0);

    const count = await prisma.visit.count({ where: { userId, scheduledDate: today } });
    const visit = await prisma.visit.create({
      data: { userId, locationId, scheduledDate: today, dailySequence: count + 1, status: 'planned', notes, visitType, isAdHoc: true },
    });

    res.status(201).json({ success: true, data: visit });
  } catch (err) { next(err); }
};
