import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

/**
 * DELETE /api/v1/visits/clear-today
 * Deletes all PENDING visits for today so scheduler can re-plan cleanly.
 */
export async function DELETE() {
  try {
    const today = new Date(); today.setHours(0, 0, 0, 0);
    const tomorrow = new Date(today); tomorrow.setDate(tomorrow.getDate() + 1);

    const deleted = await prisma.visit.deleteMany({
      where: {
        visitDate: { gte: today, lt: tomorrow },
        status: "PENDING",
      },
    });

    return NextResponse.json({
      success: true,
      deleted: deleted.count,
      message: `Cleared ${deleted.count} pending visits for today. Now run the scheduler to re-plan.`,
    });
  } catch (error) {
    return NextResponse.json({ success: false, error: String(error) }, { status: 500 });
  }
}
