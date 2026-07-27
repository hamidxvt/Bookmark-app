import { Router } from 'express';
import { getOfficers, getLiveTracking, getMissedVisits, approveMissedVisit, getPayroll, approveLeave, approveSample, getLocationHistory } from '../controllers/admin.controller.js';
import { auth } from '../middleware/auth.middleware.js';
import { requireRole } from '../middleware/role.middleware.js';

const router = Router();

const adminAuth = [auth, requireRole(['super_admin', 'city_head', 'coordinator'])];

router.get('/officers', ...adminAuth, getOfficers);
router.get('/tracking/live', ...adminAuth, getLiveTracking);
router.get('/missed-visits', ...adminAuth, getMissedVisits);
router.post('/missed-visits/:id/approve', ...adminAuth, approveMissedVisit);
router.get('/payroll/:month/:year', ...adminAuth, getPayroll);
router.post('/leaves/:id/approve', ...adminAuth, approveLeave);
router.post('/samples/:id/approve', ...adminAuth, approveSample);
router.get('/locations/:id/history', ...adminAuth, getLocationHistory);

export default router;
