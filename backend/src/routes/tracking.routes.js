import { Router } from 'express';
import { ping, getLivePositions } from '../controllers/tracking.controller.js';
import { authMiddleware } from '../middleware/auth.middleware.js';

const router = Router();

router.post('/ping', authMiddleware, ping);
router.get('/live', getLivePositions); // Admin can poll without auth (or add admin auth)

export default router;
