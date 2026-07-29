import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser } from "@/lib/auth-mobile";
import { unauthorized } from "@/lib/responses";

export async function GET(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    // Fetch all leave requests for this booker in the current year
    const now = new Date();
    const yearStart = new Date(now.getFullYear(), 0, 1);

    const requests = await prisma.leaveRequest.findMany({
      where: {
        bookerId: user.id,
        createdAt: { gte: yearStart },
      },
    });

    // Calculate balances by type
    const typeMap: Record<string, { total: number; used: number }> = {
      casual: { total: 18, used: 0 },
      sick: { total: 10, used: 0 },
    };

    for (const req of requests) {
      const type = req.type?.toLowerCase() ?? "casual";
      if (type in typeMap) {
        const days = new Date(req.to).getTime() - new Date(req.from).getTime();
        typeMap[type].used += Math.ceil(days / (1000 * 60 * 60 * 24)) + 1;
      }
    }

    const balances = Object.entries(typeMap).map(([type, data]) => ({
      type: type.charAt(0).toUpperCase() + type.slice(1),
      totalDays: data.total,
      usedDays: Math.min(data.used, data.total),
    }));

    return NextResponse.json({ success: true, data: balances });
  } catch (err) {
    console.error("[leaves/balance]", err);
    return NextResponse.json(
      { success: false, error: "Failed to fetch balances" },
      { status: 500 }
    );
  }
}
