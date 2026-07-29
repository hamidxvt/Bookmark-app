import { Router } from 'express';
import { getPayrollSummary, finalizePayroll, getMyPayroll } from '../controllers/payroll.controller.js';
import { authMiddleware } from '../middleware/auth.middleware.js';

const router = Router();

router.use(authMiddleware);

router.get('/', getPayrollSummary);          // Admin: GET /api/v1/payroll?month=7&year=2026
router.post('/finalize', finalizePayroll);   // Admin: POST /api/v1/payroll/finalize
router.get('/me', getMyPayroll);            // Booker: GET /api/v1/payroll/me?month=7&year=2026

export default router;
