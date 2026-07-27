import cron from 'node-cron';
import logger from '../utils/logger.js';
import { runRoutePlanning } from './routePlanning.cron.js';
import { runAttendanceEngine } from './attendanceEngine.cron.js';
import { runSampleReminders } from './sampleReminder.cron.js';
import { runPayrollEngine } from './payrollEngine.cron.js';

export function startSchedulers() {
  // 12:00 AM — build tomorrow's visit routes for all officers
  cron.schedule('0 0 * * *', async () => {
    logger.info('[Scheduler] Route Planning Engine started');
    try { await runRoutePlanning(); logger.info('[Scheduler] Route Planning Engine completed'); }
    catch (e) { logger.error('[Scheduler] Route Planning Engine failed:', e.message); }
  });

  // 11:00 PM — scan for missing attendance, auto-log absences
  cron.schedule('0 23 * * *', async () => {
    logger.info('[Scheduler] Attendance Engine started');
    try { await runAttendanceEngine(); logger.info('[Scheduler] Attendance Engine completed'); }
    catch (e) { logger.error('[Scheduler] Attendance Engine failed:', e.message); }
  });

  // 9:00 AM daily — send sample recovery reminders
  cron.schedule('0 9 * * *', async () => {
    logger.info('[Scheduler] Sample Reminders started');
    try { await runSampleReminders(); logger.info('[Scheduler] Sample Reminders completed'); }
    catch (e) { logger.error('[Scheduler] Sample Reminders failed:', e.message); }
  });

  // 11:30 PM on day 28–31 — run payroll if last day of month
  cron.schedule('30 23 28-31 * *', async () => {
    logger.info('[Scheduler] Payroll Engine started');
    try { await runPayrollEngine(); logger.info('[Scheduler] Payroll Engine completed'); }
    catch (e) { logger.error('[Scheduler] Payroll Engine failed:', e.message); }
  });

  logger.info('[Scheduler] All schedulers registered');
}
