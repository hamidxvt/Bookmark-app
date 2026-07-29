import { Router } from 'express';
import {
  getMySamples, requestSamples, markRecovered,
  adminGetSamples, adminApproveSample,
} from '../controllers/samples.controller.js';
import { authMiddleware } from '../middleware/auth.middleware.js';

const router = Router();

router.use(authMiddleware);

// Booker routes
router.get('/my', getMySamples);
router.post('/request', requestSamples);
router.post('/:id/recover', markRecovered);

// Admin routes
router.get('/admin', adminGetSamples);
router.patch('/:id/approve', adminApproveSample);

export default router;
