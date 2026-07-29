import prisma from '../config/database.js';
import { AppError } from '../middleware/errorHandler.js';
import logger from '../utils/logger.js';

const MAX_SPEED_KMH = 150;

function haversine(lat1, lon1, lat2, lon2) {
  const R = 6371000;
  const rad = Math.PI / 180;
  const dLat = (lat2 - lat1) * rad;
  const dLon = (lon2 - lon1) * rad;
  const a = Math.sin(dLat / 2) ** 2 +
    Math.cos(lat1 * rad) * Math.cos(lat2 * rad) * Math.sin(dLon / 2) ** 2;
  return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
}

export const ping = async (req, res, next) => {
  try {
    const { lat, lng, latitude, longitude, accuracy, isMocked, batteryLevel } = req.body;
    const bookerId = req.user.id;

    const finalLat = lat ?? latitude;
    const finalLng = lng ?? longitude;

    if (!finalLat || !finalLng) {
      throw new AppError('MISSING_COORDS', 400, 'lat and lng are required');
    }

    if (isMocked) {
      await prisma.gpsPing.create({
        data: { bookerId, latitude: finalLat, longitude: finalLng, accuracy, batteryLevel, isMocked: true },
      });
      logger.warn(`Mock GPS detected for booker ${bookerId}`);
      throw new AppError('MOCK_LOCATION_DETECTED', 403, 'GPS spoofing detected. Incident logged.');
    }

    // Speed anomaly check
    const last = await prisma.gpsPing.findFirst({
      where: { bookerId },
      orderBy: { createdAt: 'desc' },
    });

    if (last) {
      const dist = haversine(Number(last.latitude), Number(last.longitude), finalLat, finalLng);
      const elapsedMs = Date.now() - last.createdAt.getTime();
      const speedKmh = (dist / (elapsedMs / 1000)) * 3.6;
      if (speedKmh > MAX_SPEED_KMH) {
        logger.warn(`Speed anomaly for booker ${bookerId}: ${speedKmh.toFixed(1)} km/h`);
      }
    }

    // Save ping + update booker last seen
    const [gpsPing, booker] = await prisma.$transaction([
      prisma.gpsPing.create({
        data: { bookerId, latitude: finalLat, longitude: finalLng, accuracy, batteryLevel, isMocked: false },
      }),
      prisma.booker.update({
        where: { id: bookerId },
        data: { lastLatitude: finalLat, lastLongitude: finalLng, lastSeenAt: new Date(), gpsStatus: 'ACTIVE' },
        select: { id: true, name: true, cityId: true, lastLatitude: true, lastLongitude: true, lastSeenAt: true, gpsStatus: true },
      }),
    ]);

    // ── Real-time broadcast via Socket.io ─────────────────────────────────────
    const io = req.app.get('io');
    if (io) {
      const payload = {
        bookerId,
        name: booker.name,
        lat: Number(finalLat),
        lng: Number(finalLng),
        batteryLevel,
        ts: gpsPing.createdAt,
      };
      // Broadcast to admin viewers watching this city or all
      io.to(`city:${booker.cityId}`).emit('booker:location', payload);
      io.to('all').emit('booker:location', payload);
    }

    res.json({ success: true });
  } catch (err) { next(err); }
};

// GET /api/v1/tracking/live — returns all currently active booker positions
export const getLivePositions = async (req, res, next) => {
  try {
    const bookers = await prisma.booker.findMany({
      where: { gpsStatus: { in: ['ACTIVE', 'IDLE'] }, lastLatitude: { not: null } },
      select: {
        id: true, name: true, phone: true, cityId: true, gpsStatus: true,
        lastLatitude: true, lastLongitude: true, lastSeenAt: true,
        city: { select: { name: true } },
      },
    });

    res.json({
      success: true,
      data: bookers.map(b => ({
        id: b.id,
        name: b.name,
        phone: b.phone,
        city: b.city?.name ?? '',
        status: b.gpsStatus,
        lat: b.lastLatitude ? Number(b.lastLatitude) : null,
        lng: b.lastLongitude ? Number(b.lastLongitude) : null,
        lastSeen: b.lastSeenAt,
      })),
    });
  } catch (err) { next(err); }
};
