import { Router } from 'express';
import { applyLeave, getLeaveBalance } from '../controllers/leaves.controller.js';
import { auth } from '../middleware/auth.middleware.js';
import { validate } from '../middleware/validate.middleware.js';
import { z } from 'zod';

const router = Router();

router.post('/apply', auth, validate(z.object({
  leaveType: z.enum(['sick', 'casual']),
  startDate: z.string().date(),
  endDate: z.string().date(),
  reason: z.string().optional(),
})), applyLeave);

router.get('/balance', auth, getLeaveBalance);

export default router;
