import prisma from '../config/database.js';
import { AppError } from '../middleware/errorHandler.js';
import logger from '../utils/logger.js';

const MAX_SPEED_KMH = 150;

export const ping = async (req, res, next) => {
  try {
    const { latitude, longitude, accuracy, isMocked, batteryLevel, recordedAt } = req.body;
    const userId = req.user.id;

    if (isMocked) {
      await prisma.gpsLog.create({ data: { userId, latitude, longitude, accuracy, isMocked: true, batteryLevel } });
      logger.warn(`Mock GPS detected for user ${userId}`);
      throw new AppError('MOCK_LOCATION_DETECTED', 403, 'GPS spoofing detected. Incident logged.');
    }

    // Velocity anomaly check
    const last = await prisma.gpsLog.findFirst({ where: { userId }, orderBy: { recordedAt: 'desc' } });
    if (last) {
      const { haversine } = await import('../utils/geoDistance.js');
      const dist = haversine(last.latitude, last.longitude, latitude, longitude);
      const elapsed = (new Date() - new Date(last.recordedAt)) / 1000;
      const speedKmh = (dist / elapsed) * 3.6;
      if (speedKmh > MAX_SPEED_KMH) {
        logger.warn(`Speed anomaly for user ${userId}: ${speedKmh.toFixed(1)} km/h`);
      }
    }

    await prisma.gpsLog.create({
      data: { userId, latitude, longitude, accuracy, isMocked: false, batteryLevel, recordedAt: recordedAt ? new Date(recordedAt) : new Date() },
    });

    res.json({ success: true });
  } catch (err) { next(err); }
};
