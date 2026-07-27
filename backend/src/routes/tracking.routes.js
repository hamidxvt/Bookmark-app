import { Router } from 'express';
import { ping } from '../controllers/tracking.controller.js';
import { auth } from '../middleware/auth.middleware.js';
import { validate } from '../middleware/validate.middleware.js';
import { z } from 'zod';

const router = Router();

router.post('/ping', auth, validate(z.object({
  latitude: z.number().min(-90).max(90),
  longitude: z.number().min(-180).max(180),
  accuracy: z.number().positive(),
  isMocked: z.boolean(),
  batteryLevel: z.number().int().optional(),
  recordedAt: z.string().datetime().optional(),
})), ping);

export default router;
