import cron from 'node-cron';
import { runRoutePlanning } from './routePlanning.cron.js';
import { runAttendanceEngine } from './attendanceEngine.cron.js';
import { runSampleReminders } from './sampleReminder.cron.js';
import { runPayrollEngine } from './payrollEngine.cron.js';
import logger from '../utils/logger.js';

export function startSchedulers() {
  // Route Planning — midnight every day
  cron.schedule('0 0 * * *', async () => {
    logger.info('[Cron] Route planning triggered');
    await runRoutePlanning().catch(e => logger.error('[Cron] Route planning error:', e));
  }, { timezone: 'Asia/Karachi' });

  // Attendance Engine — 11:00 PM every day
  cron.schedule('0 23 * * *', async () => {
    logger.info('[Cron] Attendance engine triggered');
    await runAttendanceEngine().catch(e => logger.error('[Cron] Attendance error:', e));
  }, { timezone: 'Asia/Karachi' });

  // Sample Reminders — 9:00 AM every day
  cron.schedule('0 9 * * *', async () => {
    logger.info('[Cron] Sample reminders triggered');
    await runSampleReminders().catch(e => logger.error('[Cron] Sample reminders error:', e));
  }, { timezone: 'Asia/Karachi' });

  // Payroll Engine — 11:55 PM every day (only processes on last day of month)
  cron.schedule('55 23 * * *', async () => {
    logger.info('[Cron] Payroll engine triggered');
    await runPayrollEngine().catch(e => logger.error('[Cron] Payroll error:', e));
  }, { timezone: 'Asia/Karachi' });

  logger.info('[Schedulers] All 4 schedulers started (timezone: Asia/Karachi)');
}
