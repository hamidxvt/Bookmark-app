/**
 * POST /api/v1/scheduler?job=<jobName>
 * Manually trigger any scheduled job.
 * 
 * jobs: plan_visits | mark_absent | payroll | sample_reminders
 */

import { NextRequest, NextResponse } from "next/server";
import {
  planNextDayVisits,
  autoMarkAbsent,
  processPayrollDeductions,
  sendSampleReminders,
} from "@/lib/scheduler";

export async function POST(req: NextRequest) {

  const job = req.nextUrl.searchParams.get("job") ?? "all";

  try {
    const results: string[] = [];

    if (job === "plan_visits" || job === "all") {
      // When triggered manually, plan for TODAY if no visits exist
      const result = await planNextDayVisits(true);
      results.push(`plan_visits: ${JSON.stringify(result)}`);
    }
    if (job === "mark_absent" || job === "all") {
      await autoMarkAbsent();
      results.push("mark_absent: done");
    }
    if (job === "payroll" || job === "all") {
      await processPayrollDeductions();
      results.push("payroll: done");
    }
    if (job === "sample_reminders" || job === "all") {
      await sendSampleReminders();
      results.push("sample_reminders: done");
    }

    return NextResponse.json({ success: true, results });
  } catch (err: any) {
    return NextResponse.json({ success: false, error: err.message }, { status: 500 });
  }
}
