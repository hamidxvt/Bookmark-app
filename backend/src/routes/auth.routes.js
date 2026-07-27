import { Router } from 'express';
import { login, changePassword, forceReset } from '../controllers/auth.controller.js';
import { auth } from '../middleware/auth.middleware.js';
import { requireRole } from '../middleware/role.middleware.js';
import { validate } from '../middleware/validate.middleware.js';
import { z } from 'zod';

const router = Router();

const loginSchema = z.object({
  email: z.string().email(),
  password: z.string().min(6),
});

const changePasswordSchema = z.object({
  currentPassword: z.string().min(6),
  newPassword: z.string().min(8),
});

const forceResetSchema = z.object({
  userId: z.number().int().positive(),
  newPassword: z.string().min(8),
});

router.post('/login', validate(loginSchema), login);
router.post('/change-password', auth, validate(changePasswordSchema), changePassword);
router.post('/force-reset', auth, requireRole(['super_admin', 'city_head']), validate(forceResetSchema), forceReset);

export default router;
