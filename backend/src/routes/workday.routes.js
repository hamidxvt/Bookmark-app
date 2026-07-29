import { Router } from 'express';
import { dayStart, dayEnd, cannotWork, getStatus } from '../controllers/workday.controller.js';
import { authMiddleware } from '../middleware/auth.middleware.js';

const router = Router();

router.use(authMiddleware);

router.post('/day-start', dayStart);
router.post('/day-end', dayEnd);
router.post('/cannot-work', cannotWork);
router.get('/status', getStatus);

export default router;
