import { Router } from 'express';
import { requestSample, recoverSample } from '../controllers/samples.controller.js';
import { auth } from '../middleware/auth.middleware.js';
import { validate } from '../middleware/validate.middleware.js';
import { z } from 'zod';

const router = Router();

router.post('/request', auth, validate(z.object({
  productId: z.number().int().positive(),
  quantity: z.number().int().positive(),
  visitId: z.number().int().optional(),
})), requestSample);

router.post('/:id/recover', auth, recoverSample);

export default router;
