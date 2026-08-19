/**
 * scheduler.ts
 * All automated background jobs:
 *  - 12:00 AM  → plan next day's 7 visits per booker
 *  - 11:00 PM  → auto-mark absent if no day start
 *  - 11:59 PM  → auto-payroll deductions for the day
 *  - 10-day / 20-day sample reminders (run daily at 8 AM)
 *
 * Loaded by instrumentation.ts on server start. Runs only in the Node.js runtime.
 */

import { prisma } from "@/lib/prisma";

// ─── Helpers ─────────────────────────────────────────────────────────────────

function today(): Date {
  const d = new Date();
  d.setHours(0, 0, 0, 0);
  return d;
}

function tomorrow(): Date {
  const d = today();
  d.setDate(d.getDate() + 1);
  return d;
}

function isWeekend(d: Date): boolean {
  const day = d.getDay();
  return day === 0 || day === 6; // Sunday or Saturday
}

// ─── 1. Nightly Visit Planning — 12:00 AM ────────────────────────────────────
export async function planNextDayVisits(forToday = false) {
  // When manually triggered (forToday=true), plan for today if no visits exist yet
  // When auto-scheduled at midnight, plan for tomorrow
  const target = forToday ? today() : tomorrow();

  if (isWeekend(target)) {
    console.log("[scheduler] Weekend — skipping visit planning");
    return { planned: 0, skipped: "weekend" };
  }

  console.log(`[scheduler] Planning visits for ${target.toDateString()}`);

  // Ensure all approved bookers are ACTIVE (fixes migrated bookers with null jobStatus)
  await prisma.booker.updateMany({
    where: { adminApproved: "APPROVED", deletedAt: null, jobStatus: { not: "ACTIVE" } },
    data: { jobStatus: "ACTIVE" },
  });

  const activeBookers = await prisma.booker.findMany({
    where: { adminApproved: "APPROVED", deletedAt: null },
    select: { id: true, cityId: true },
    orderBy: { id: "asc" }, // Ensure consistent ordering for debugging
  });

  console.log(`[scheduler] Found ${activeBookers.length} approved bookers`);

  let planned = 0;
  // Track customers assigned today to avoid duplicates across bookers
  const assignedTodayIds: Set<number> = new Set();

  for (const booker of activeBookers) {
    if (booker.id === 88) console.log(`[scheduler] Processing booker 88...`);
    
    const existing = await prisma.visit.count({
      where: { bookerId: booker.id, visitDate: target },
    });
    if (existing >= 7) {
      if (booker.id === 88) console.log(`[scheduler] Booker 88 already has ${existing} visits`);
      continue;
    }

    const recentlyVisited = await prisma.visit.findMany({
      where: {
        bookerId: booker.id,
        visitDate: { gte: new Date(Date.now() - 7 * 24 * 60 * 60 * 1000) },
      },
      select: { customerId: true },
    });
    const recentIds = recentlyVisited.map((v) => v.customerId);

    // STRICT: only assign customers from the officer's own city WITH GPS coords
    // This ensures route map shows correct nearby locations
    if (!booker.cityId) {
      console.log(`[scheduler] Booker ${booker.id} has no city — skipping`);
      continue;
    }

    // Exclude recently visited AND customers already assigned today
    const allExcludeIds = [...recentIds, ...Array.from(assignedTodayIds)];
    const excludeFilter = allExcludeIds.length > 0 ? { id: { notIn: allExcludeIds } } : {};

    // Primary: city customers WITH GPS coordinates (needed for route map)
    let customers = await prisma.customer.findMany({
      where: {
        approvalStatus: "APPROVED",
        deletedAt: null,
        cityId: booker.cityId,
        latitude:  { not: null },
        longitude: { not: null },
        ...excludeFilter,
      },
      orderBy: [{ workingPriority: "asc" }],
      take: 7 - existing,
    });

    if (booker.id === 88) {
      console.log(`[scheduler] Booker 88: found ${customers.length} GPS customers, need ${7 - existing}`);
    }

    // Fallback: city customers WITHOUT GPS (still same city, just no map pin)
    if (customers.length < 7 - existing) {
      const needed = 7 - existing - customers.length;
      const excludeIds = [...customers.map(c => c.id), ...recentIds, ...Array.from(assignedTodayIds)];
      const extra = await prisma.customer.findMany({
        where: {
          approvalStatus: "APPROVED",
          deletedAt: null,
          cityId: booker.cityId,
          ...(excludeIds.length > 0 ? { id: { notIn: excludeIds } } : {}),
        },
        orderBy: [{ workingPriority: "asc" }],
        take: needed,
      });
      customers = [...customers, ...extra];
      if (booker.id === 88) console.log(`[scheduler] Booker 88: found ${extra.length} non-GPS customers`);
    }

    // No customers in city — skip (don't assign random far-away customers)
    if (customers.length === 0) {
      console.log(`[scheduler] No customers in city ${booker.cityId} for booker ${booker.id} — skipping`);
      if (booker.id === 88) console.log(`[scheduler] Booker 88: NO CUSTOMERS FOUND - skipping`);
      continue;
    }

    if (booker.id === 88) console.log(`[scheduler] Booker 88: assigning ${customers.length} total customers`);

    for (const c of customers) {
      await prisma.visit.create({
        data: {
          bookerId: booker.id,
          customerId: c.id,
          visitDate: target,
          status: "PENDING",
        },
      });
      assignedTodayIds.add(c.id);
      planned++;
    }
  }

  console.log(`[scheduler] Planned ${planned} visits for ${target.toDateString()}`);
  return { planned, date: target.toDateString() };
}

// ─── 2. Auto Mark Absent — 11:00 PM ──────────────────────────────────────────
export async function autoMarkAbsent() {
  const todayDate = today();
  if (isWeekend(todayDate)) return;

  console.log("[scheduler] Auto-marking absent for no day start...");

  const activeBookers = await prisma.booker.findMany({
    where: { jobStatus: "ACTIVE", adminApproved: "APPROVED", deletedAt: null },
    select: { id: true },
  });

  let marked = 0;
  for (const booker of activeBookers) {
    const att = await prisma.attendance.findUnique({
      where: { bookerId_date: { bookerId: booker.id, date: todayDate } },
    });

    if (!att) {
      // No attendance record at all — mark as absent (auto leave deduction)
      await prisma.attendance.create({
        data: {
          bookerId: booker.id,
          date: todayDate,
          status: "absent",
        },
      }).catch(() => {});
      marked++;
    }
  }

  console.log(`[scheduler] Auto-marked ${marked} bookers as absent`);
}

// ─── 3. Auto Payroll Deductions — 11:59 PM ───────────────────────────────────
export async function processPayrollDeductions() {
  const todayDate = today();
  const month = todayDate.getMonth() + 1;
  const year = todayDate.getFullYear();

  console.log("[scheduler] Processing payroll deductions...");

  const bookers = await prisma.booker.findMany({
    where: { jobStatus: "ACTIVE", adminApproved: "APPROVED", deletedAt: null },
    select: { id: true, ratesPerVisit: true },
  });

  const DAILY_PERFORMANCE_RATE = 3000; // PKR 3,000/day performance pay

  for (const booker of bookers) {
    const att = await prisma.attendance.findUnique({
      where: { bookerId_date: { bookerId: booker.id, date: todayDate } },
    });

    // Deduct if absent or did not start day
    if (!att || att.status === "absent") {
      // Absence deduction logged (actual payroll ledger would be calculated monthly)
      console.log(`[scheduler] Booker ${booker.id} absent — deduction PKR ${DAILY_PERFORMANCE_RATE}`);
    }

    // Deduct for rejected missed visit reasons
    const rejectedMissed = await prisma.missedVisitReason.findMany({
      where: {
        bookerId: booker.id,
        status: "rejected",
        createdAt: {
          gte: new Date(year, month - 1, 1),
          lt: new Date(year, month, 1),
        },
      },
    });

    if (rejectedMissed.length > 0) {
      console.log(`[scheduler] Booker ${booker.id}: ${rejectedMissed.length} rejected missed visits this month`);
    }
  }

  console.log("[scheduler] Payroll deductions processed");
}

// ─── 4. Sample Reminders — daily at 8 AM ─────────────────────────────────────
export async function sendSampleReminders() {
  const now = new Date();

  // Find samples distributed 10 days ago or 20 days ago
  const tenDaysAgo = new Date(now.getTime() - 10 * 24 * 60 * 60 * 1000);
  const twentyDaysAgo = new Date(now.getTime() - 20 * 24 * 60 * 60 * 1000);

  const startOf = (d: Date) => new Date(d.getFullYear(), d.getMonth(), d.getDate());
  const endOf = (d: Date) => new Date(d.getFullYear(), d.getMonth(), d.getDate(), 23, 59, 59);

  const pending10 = await prisma.request.findMany({
    where: {
      category: "SAMPLE",
      status: "RESOLVED",
      createdAt: { gte: startOf(tenDaysAgo), lte: endOf(tenDaysAgo) },
    },
    include: { booker: { select: { id: true, name: true, fcmToken: true } } },
  });

  const pending20 = await prisma.request.findMany({
    where: {
      category: "SAMPLE",
      status: "RESOLVED",
      createdAt: { gte: startOf(twentyDaysAgo), lte: endOf(twentyDaysAgo) },
    },
    include: { booker: { select: { id: true, name: true, fcmToken: true } } },
  });

  console.log(`[scheduler] Sample reminders: 10-day=${pending10.length}, 20-day=${pending20.length}`);

  // Send push notifications if FCM is configured
  for (const req of [...pending10, ...pending20]) {
    const days = pending10.includes(req) ? 10 : 20;
    if (req.booker?.fcmToken) {
      await sendPushNotification(
        req.booker.fcmToken,
        "Sample Recovery Reminder",
        `${days} days have passed. Please recover your distributed samples.`,
        { type: "sample_reminder", requestId: String(req.id), days: String(days) }
      ).catch(() => {});
    }
  }
}

// ─── Push Notification helper (FCM) ──────────────────────────────────────────
export async function sendPushNotification(
  fcmToken: string,
  title: string,
  body: string,
  data?: Record<string, string>
): Promise<void> {
  const serverKey = process.env.FCM_SERVER_KEY;
  if (!serverKey || !fcmToken) return;

  const payload = {
    to: fcmToken,
    notification: { title, body, sound: "default" },
    data: data ?? {},
    priority: "high",
  };

  const res = await fetch("https://fcm.googleapis.com/fcm/send", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `key=${serverKey}`,
    },
    body: JSON.stringify(payload),
  });

  if (!res.ok) {
    console.error("[FCM] Failed to send notification:", await res.text());
  }
}

// ─── Notify a booker about leave/visit/sample status change ──────────────────
export async function notifyBooker(
  bookerId: number,
  title: string,
  body: string,
  data?: Record<string, string>
) {
  const booker = await prisma.booker.findUnique({
    where: { id: bookerId },
    select: { fcmToken: true },
  });
  if (booker?.fcmToken) {
    await sendPushNotification(booker.fcmToken, title, body, data).catch(() => {});
  }
}
