/**
 * instrumentation.ts
 * Next.js server instrumentation hook — runs once when the server starts.
 * Uses node-cron to schedule background jobs.
 */

export async function register() {
  // Only run cron jobs in the Node.js runtime (not Edge)
  if (process.env.NEXT_RUNTIME === "nodejs") {
    const { default: cron } = await import("node-cron");
    const {
      planNextDayVisits,
      autoMarkAbsent,
      processPayrollDeductions,
      sendSampleReminders,
    } = await import("@/lib/scheduler");

    // 12:00 AM — Plan next day's 7 visits per active booker
    cron.schedule("0 0 * * *", async () => {
      console.log("[cron] 12:00 AM — Planning next day visits...");
      await planNextDayVisits().catch(console.error);
    });

    // 11:00 PM — Auto-mark absent if no day start recorded
    cron.schedule("0 23 * * *", async () => {
      console.log("[cron] 11:00 PM — Auto marking absent...");
      await autoMarkAbsent().catch(console.error);
    });

    // 11:59 PM — Process payroll deductions
    cron.schedule("59 23 * * *", async () => {
      console.log("[cron] 11:59 PM — Processing payroll deductions...");
      await processPayrollDeductions().catch(console.error);
    });

    // 8:00 AM — Send sample recovery reminders (10-day and 20-day)
    cron.schedule("0 8 * * *", async () => {
      console.log("[cron] 8:00 AM — Sending sample reminders...");
      await sendSampleReminders().catch(console.error);
    });

    console.log("[instrumentation] All cron jobs scheduled ✅");
  }
}
