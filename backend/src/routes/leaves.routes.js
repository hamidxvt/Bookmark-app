import { Router } from 'express';
import { applyLeave, getLeaveBalance, getMyLeaves } from '../controllers/leaves.controller.js';
import { authMiddleware } from '../middleware/auth.middleware.js';

const router = Router();

router.use(authMiddleware);

router.get('/balance', getLeaveBalance);
router.get('/my', getMyLeaves);
router.post('/apply', applyLeave);

export default router;
