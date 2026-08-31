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
      monitorOfficerActivity,
    } = await import("@/lib/scheduler");

    // ── Auto-seed XLSX data if not yet imported ───────────────────────────
    // Runs in background so it doesn't block server startup.
    // Triggers whenever A+ category customers are missing (< 10 present).
    setImmediate(async () => {
      try {
        const { prisma } = await import("@/lib/prisma");
        const aplusCount = await prisma.customer.count({ where: { category: "A+" } });
        if (aplusCount < 10) {
          console.log(`[seed] Auto-seed triggered (A+ count: ${aplusCount}) — importing XLSX data…`);
          const { seedCustomers } = await import("@/app/api/v1/seed-data/route");
          // Products seeded inline too
          const { default: path } = await import("path");
          const { default: fs }   = await import("fs");
          const { prisma: db }    = await import("@/lib/prisma");

          // Seed customers
          const cResult = await seedCustomers();
          console.log("[seed] Customers done:", cResult);

          // Seed products
          interface SeedProduct { brand: string; subject?: string|null; series?: string|null; name: string; retailPrice?: number; isbn?: string|null; segment?: string|null; grade?: string|null; description?: string|null; }
          const products: SeedProduct[] = JSON.parse(
            fs.readFileSync(path.join(process.cwd(), "src", "data", "seed-products.json"), "utf-8")
          );
          const brandCache   = new Map<string, number>();
          const subjectCache = new Map<string, number>();
          const seriesCache  = new Map<string, number>();
          let pCreated = 0, pUpdated = 0;
          for (const row of products) {
            if (!row.name) continue;
            const bk = row.brand.toUpperCase();
            if (!brandCache.has(bk)) {
              const b = await db.brand.upsert({ where: { name: row.brand }, update: {}, create: { name: row.brand } });
              brandCache.set(bk, b.id);
            }
            const brandId = brandCache.get(bk)!;
            let subjectId: number | null = null;
            if (row.subject) {
              const sk = row.subject.toUpperCase();
              if (!subjectCache.has(sk)) {
                const s = await db.subject.upsert({ where: { name: row.subject }, update: {}, create: { name: row.subject } });
                subjectCache.set(sk, s.id);
              }
              subjectId = subjectCache.get(sk)!;
            }
            let seriesId: number | null = null;
            if (row.series) {
              const sk = row.series.toUpperCase();
              if (!seriesCache.has(sk)) {
                const s = await db.series.upsert({ where: { name: row.series }, update: {}, create: { name: row.series } });
                seriesCache.set(sk, s.id);
              }
              seriesId = seriesCache.get(sk)!;
            }
            try {
              const ex = await db.product.findFirst({ where: { name: row.name, brandId }, select: { id: true } });
              const p = { brandId, subjectId, seriesId, isbn: row.isbn ?? null, segment: row.segment ?? null, grade: row.grade ? String(row.grade) : null, description: row.description ?? null, retailPrice: row.retailPrice ?? 0 };
              if (ex) { await db.product.update({ where: { id: ex.id }, data: p }); pUpdated++; }
              else     { await db.product.create({ data: { name: row.name, ...p } }); pCreated++; }
            } catch { /* skip duplicates */ }
          }
          console.log(`[seed] Products done: ${pCreated} created, ${pUpdated} updated`);
        } else {
          console.log(`[seed] A+ customers already present (${aplusCount}) — skipping auto-seed`);
        }
      } catch (e) {
        console.error("[seed] Auto-seed failed:", e);
      }
    });

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

    // Every 10 minutes during business hours — monitor officer activity + send smart alerts
    cron.schedule("*/10 * * * *", async () => {
      await monitorOfficerActivity().catch(console.error);
    });

    console.log("[instrumentation] All cron jobs scheduled ✅");
  }
}
