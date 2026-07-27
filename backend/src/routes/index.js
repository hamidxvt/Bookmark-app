import { Router } from 'express';
import authRoutes from './auth.routes.js';
import workdayRoutes from './workday.routes.js';
import visitsRoutes from './visits.routes.js';
import trackingRoutes from './tracking.routes.js';
import samplesRoutes from './samples.routes.js';
import leavesRoutes from './leaves.routes.js';
import adminRoutes from './admin.routes.js';

const router = Router();

router.use('/auth', authRoutes);
router.use('/workday', workdayRoutes);
router.use('/visits', visitsRoutes);
router.use('/tracking', trackingRoutes);
router.use('/samples', samplesRoutes);
router.use('/leaves', leavesRoutes);
router.use('/admin', adminRoutes);

export default router;
