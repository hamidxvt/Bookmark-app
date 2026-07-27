import { Router } from 'express';
import { getTodayVisits, startVisit, completeVisit, editVisit, markMissed, createAdhoc } from '../controllers/visits.controller.js';
import { auth } from '../middleware/auth.middleware.js';
import { validate } from '../middleware/validate.middleware.js';
import { z } from 'zod';

const router = Router();

const completeSchema = z.object({
  contactPerson: z.string().min(2),
  designation: z.string().min(2),
  phone: z.string().min(7),
  notes: z.string().min(5),
  visitType: z.enum(['sales_call', 'follow_up', 'introduction', 'collection', 'cold_call']),
  sampleDistributed: z.number().int().min(0),
  followUpDate: z.string().optional(),
});

router.get('/today', auth, getTodayVisits);
router.post('/:id/start', auth, validate(z.object({
  arrivalLat: z.number(),
  arrivalLng: z.number(),
  isMocked: z.boolean(),
})), startVisit);
router.post('/:id/complete', auth, validate(completeSchema), completeVisit);
router.post('/:id/edit', auth, validate(z.object({ notes: z.string().optional(), contactPerson: z.string().optional() })), editVisit);
router.post('/:id/mark-missed', auth, validate(z.object({
  reason: z.string().min(10),
  photoUrl: z.string().url(),
})), markMissed);
router.post('/adhoc', auth, validate(z.object({
  locationId: z.number().int().positive(),
  notes: z.string(),
  visitType: z.string(),
})), createAdhoc);

export default router;
