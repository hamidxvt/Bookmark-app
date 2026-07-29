import prisma from '../config/database.js';
import { AppError } from '../middleware/errorHandler.js';

const GEOFENCE_METERS = 200;
const MAX_VISITS_PER_DAY = 7;

function haversine(lat1, lon1, lat2, lon2) {
  const R = 6371000;
  const rad = Math.PI / 180;
  const dLat = (lat2 - lat1) * rad;
  const dLon = (lon2 - lon1) * rad;
  const a = Math.sin(dLat / 2) ** 2 +
    Math.cos(lat1 * rad) * Math.cos(lat2 * rad) * Math.sin(dLon / 2) ** 2;
  return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
}

// GET /api/v1/visits/today
export const getTodayVisits = async (req, res, next) => {
  try {
    const bookerId = req.user.id;
    const today = new Date();
    today.setHours(0, 0, 0, 0);
    const tomorrow = new Date(today);
    tomorrow.setDate(tomorrow.getDate() + 1);

    const visits = await prisma.visit.findMany({
      where: { bookerId, visitDate: { gte: today, lt: tomorrow } },
      include: {
        customer: {
          select: {
            id: true, name: true, customerType: true,
            ownerName: true, ownerPhone: true, address: true,
            latitude: true, longitude: true, workingPriority: true,
          },
        },
        missedReason: { select: { status: true } },
      },
      orderBy: [{ dailySequence: 'asc' }, { id: 'asc' }],
    });

    res.json({
      success: true,
      data: {
        date: today.toISOString().slice(0, 10),
        visits: visits.map((v, i) => ({
          id: v.id,
          sequence: v.dailySequence || i + 1,
          customerId: v.customerId,
          customerName: v.customer.name,
          customerType: v.customer.customerType,
          contact: v.customer.ownerName ?? '',
          phone: v.customer.ownerPhone ?? '',
          address: v.customer.address ?? '',
          latitude: v.customer.latitude ? Number(v.customer.latitude) : null,
          longitude: v.customer.longitude ? Number(v.customer.longitude) : null,
          priority: v.priority ?? 'normal',
          status: v.status.toLowerCase(),
          checkInAt: v.checkInAt,
          checkOutAt: v.checkOutAt,
          notes: v.notes ?? '',
          visitReport: v.visitReport ?? '',
          carryForwardCnt: v.carryForwardCnt,
          isAdHoc: v.isAdHoc,
          missedApproval: v.missedReason?.status ?? null,
        })),
      },
    });
  } catch (err) { next(err); }
};

// POST /api/v1/visits/:id/start — check-in with GPS geofence
export const startVisit = async (req, res, next) => {
  try {
    const visitId = parseInt(req.params.id);
    const bookerId = req.user.id;
    const { lat, lng, isMocked } = req.body;

    if (isMocked) throw new AppError('MOCK_LOCATION_DETECTED', 403, 'GPS spoofing detected. Incident logged.');

    const visit = await prisma.visit.findFirst({
      where: { id: visitId, bookerId },
      include: { customer: { select: { latitude: true, longitude: true } } },
    });
    if (!visit) throw new AppError('NOT_FOUND', 404, 'Visit not found');
    if (visit.status === 'COMPLETED') throw new AppError('ALREADY_DONE', 409, 'Visit already completed');

    if (lat && lng && visit.customer.latitude && visit.customer.longitude) {
      const dist = haversine(lat, lng, Number(visit.customer.latitude), Number(visit.customer.longitude));
      if (dist > GEOFENCE_METERS) {
        throw new AppError('OUTSIDE_GEOFENCE', 422, `You must be within ${GEOFENCE_METERS}m. You are ${Math.round(dist)}m away.`);
      }
    }

    const updated = await prisma.visit.update({
      where: { id: visitId },
      data: { status: 'PENDING', checkInAt: new Date(), checkInLat: lat, checkInLng: lng },
    });

    res.json({ success: true, data: { status: updated.status, checkInAt: updated.checkInAt } });
  } catch (err) { next(err); }
};

// POST /api/v1/visits/:id/complete
export const completeVisit = async (req, res, next) => {
  try {
    const visitId = parseInt(req.params.id);
    const bookerId = req.user.id;
    const { contactPerson, phone, notes, visitType, followUpDate, lat, lng } = req.body;

    const visit = await prisma.visit.findFirst({ where: { id: visitId, bookerId } });
    if (!visit) throw new AppError('NOT_FOUND', 404, 'Visit not found');

    await prisma.$transaction(async (tx) => {
      await tx.visit.update({
        where: { id: visitId },
        data: {
          status: 'COMPLETED',
          checkOutAt: new Date(),
          checkInLat: lat ?? visit.checkInLat,
          checkInLng: lng ?? visit.checkInLng,
          contactPerson: contactPerson ?? null,
          contactPhone: phone ?? null,
          notes: notes ?? null,
          visitType: visitType ?? null,
          visitReport: [
            contactPerson ? `Contact: ${contactPerson}` : null,
            phone ? `Phone: ${phone}` : null,
            visitType ? `Type: ${visitType}` : null,
            notes ?? null,
          ].filter(Boolean).join('\n'),
          followUpDate: followUpDate ? new Date(followUpDate) : null,
        },
      });

      // Auto-schedule follow-up if requested
      if (followUpDate) {
        const fDate = new Date(followUpDate);
        fDate.setHours(0, 0, 0, 0);
        const count = await tx.visit.count({ where: { bookerId, visitDate: fDate } });
        if (count < MAX_VISITS_PER_DAY) {
          await tx.visit.create({
            data: {
              bookerId, customerId: visit.customerId,
              visitDate: fDate, dailySequence: count + 1,
              status: 'PENDING', isAdHoc: false,
              notes: `Follow-up from visit #${visitId}`,
            },
          });
        }
      }
    });

    res.json({ success: true, data: { status: 'COMPLETED', checkOutAt: new Date() }, message: 'Visit completed' });
  } catch (err) { next(err); }
};

// POST /api/v1/visits/:id/mark-missed
export const markMissed = async (req, res, next) => {
  try {
    const visitId = parseInt(req.params.id);
    const bookerId = req.user.id;
    const { reason } = req.body;

    if (!reason?.trim()) throw new AppError('MISSING_REASON', 400, 'Reason is required');

    const visit = await prisma.visit.findFirst({ where: { id: visitId, bookerId } });
    if (!visit) throw new AppError('NOT_FOUND', 404, 'Visit not found');

    await prisma.$transaction([
      prisma.visit.update({
        where: { id: visitId },
        data: { status: 'CANCELLED', carryForwardCnt: { increment: 1 } },
      }),
      prisma.missedVisitReason.upsert({
        where: { visitId },
        create: { visitId, bookerId, reason: reason.trim(), status: 'pending' },
        update: { reason: reason.trim(), status: 'pending' },
      }),
    ]);

    res.json({
      success: true,
      data: { status: 'CANCELLED', carryForwardCnt: visit.carryForwardCnt + 1 },
      message: 'Missed visit recorded, pending admin review',
    });
  } catch (err) { next(err); }
};

// POST /api/v1/visits/:id/edit — same-day edit (notes, contact only)
export const editVisit = async (req, res, next) => {
  try {
    const visitId = parseInt(req.params.id);
    const bookerId = req.user.id;

    const visit = await prisma.visit.findFirst({ where: { id: visitId, bookerId } });
    if (!visit) throw new AppError('NOT_FOUND', 404, 'Visit not found');

    const visitDay = new Date(visit.visitDate);
    visitDay.setHours(23, 59, 59, 999);
    if (new Date() > visitDay) {
      throw new AppError('EDIT_WINDOW_CLOSED', 422, 'Visit details cannot be edited after 11:59 PM on the scheduled date');
    }

    const { notes, contactPerson, phone } = req.body;
    await prisma.visit.update({
      where: { id: visitId },
      data: { notes: notes ?? undefined, contactPerson: contactPerson ?? undefined, contactPhone: phone ?? undefined },
    });

    res.json({ success: true, data: { message: 'Visit updated' } });
  } catch (err) { next(err); }
};

// POST /api/v1/visits/adhoc — create an unplanned visit
export const createAdhoc = async (req, res, next) => {
  try {
    const bookerId = req.user.id;
    const { customerId, notes, visitType } = req.body;

    const today = new Date();
    today.setHours(0, 0, 0, 0);

    // Check day has started
    const attendance = await prisma.attendance.findUnique({
      where: { bookerId_date: { bookerId, date: today } },
    });
    if (!attendance?.startAt) {
      throw new AppError('DAY_NOT_STARTED', 400, 'You must start your day before creating visits');
    }

    const count = await prisma.visit.count({ where: { bookerId, visitDate: today } });
    const visit = await prisma.visit.create({
      data: { bookerId, customerId, visitDate: today, dailySequence: count + 1, status: 'PENDING', notes, visitType, isAdHoc: true },
      include: { customer: { select: { name: true, customerType: true } } },
    });

    res.status(201).json({ success: true, data: visit });
  } catch (err) { next(err); }
};
