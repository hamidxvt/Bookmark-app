import { Router } from 'express';
import { z } from 'zod';
import { login, changePassword, forceReset } from './auth.controller.js';
import { auth } from '../../middleware/auth.middleware.js';
import { requireRole } from '../../middleware/role.middleware.js';
import { validate } from '../../middleware/validate.middleware.js';

const router = Router();

router.post('/login',
  validate(z.object({ email: z.string().email(), password: z.string().min(6) })),
  login,
);

router.post('/change-password',
  auth,
  validate(z.object({ currentPassword: z.string().min(6), newPassword: z.string().min(8) })),
  changePassword,
);

router.post('/force-reset',
  auth,
  requireRole(['super_admin', 'city_head']),
  validate(z.object({ userId: z.number().int().positive(), newPassword: z.string().min(8) })),
  forceReset,
);

export default router;
