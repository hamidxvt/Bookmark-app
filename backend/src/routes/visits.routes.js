import { Router } from 'express';
import {
  getTodayVisits, startVisit, completeVisit,
  markMissed, editVisit, createAdhoc,
} from '../controllers/visits.controller.js';
import { authMiddleware } from '../middleware/auth.middleware.js';

const router = Router();

router.use(authMiddleware);

router.get('/today', getTodayVisits);
router.post('/adhoc', createAdhoc);
router.post('/:id/start', startVisit);
router.post('/:id/complete', completeVisit);
router.post('/:id/mark-missed', markMissed);
router.patch('/:id/edit', editVisit);

export default router;
