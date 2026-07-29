/**
 * POST /api/v1/scheduler?job=<jobName>
 * Manually trigger any scheduled job (admin only).
 * 
 * jobs: plan_visits | mark_absent | payroll | sample_reminders
 */

import { NextRequest, NextResponse } from "next/server";
import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";
import {
  planNextDayVisits,
  autoMarkAbsent,
  processPayrollDeductions,
  sendSampleReminders,
} from "@/lib/scheduler";

export async function POST(req: NextRequest) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ error: "Unauthorized" }, { status: 401 });

  const job = req.nextUrl.searchParams.get("job") ?? "all";

  try {
    const results: string[] = [];

    if (job === "plan_visits" || job === "all") {
      await planNextDayVisits();
      results.push("plan_visits: done");
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
