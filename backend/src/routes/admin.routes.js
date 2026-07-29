import { Router } from 'express';
import {
  getStats, getLeaves, reviewLeave,
  getMissedVisits, reviewMissedVisit,
  getBookers, approveBooker, getAttendance,
} from '../controllers/admin.controller.js';
import { authMiddleware } from '../middleware/auth.middleware.js';

const router = Router();

// All admin routes require auth
router.use(authMiddleware);

router.get('/stats', getStats);
router.get('/attendance', getAttendance);
router.get('/bookers', getBookers);
router.patch('/bookers/:id/approve', approveBooker);
router.get('/leaves', getLeaves);
router.patch('/leaves/:id', reviewLeave);
router.get('/missed-visits', getMissedVisits);
router.patch('/missed-visits/:id', reviewMissedVisit);

export default router;
