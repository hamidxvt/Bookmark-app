import { Router } from 'express';
import { dayStart, dayEnd, cannotWork } from '../controllers/workday.controller.js';
import { auth } from '../middleware/auth.middleware.js';
import { validate } from '../middleware/validate.middleware.js';
import { z } from 'zod';

const router = Router();

const locationSchema = z.object({
  latitude: z.number().min(-90).max(90),
  longitude: z.number().min(-180).max(180),
  isMocked: z.boolean(),
  batteryLevel: z.number().int().min(0).max(100).optional(),
});

router.post('/day-start', auth, validate(locationSchema), dayStart);
router.post('/day-end', auth, validate(locationSchema), dayEnd);
router.post('/cannot-work', auth, validate(z.object({
  reason: z.string().min(3),
  notes: z.string().optional(),
})), cannotWork);

export default router;
