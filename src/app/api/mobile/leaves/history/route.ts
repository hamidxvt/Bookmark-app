import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

export async function GET(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const requests = await prisma.leaveRequest.findMany({
      where: { bookerId: user.id },
      orderBy: { createdAt: "desc" },
      take: 50,
    });

    const data = requests.map(r => ({
      id: r.id,
      type: r.leaveType ?? "Casual",
      from: r.fromDate.toISOString(),
      to: r.toDate.toISOString(),
      reason: r.reason ?? "",
      status: r.status ?? "pending",
      createdAt: r.createdAt.toISOString(),
    }));

    return NextResponse.json({ success: true, data });
  } catch (err) {
    console.error("[leaves/history]", err);
    return NextResponse.json(
      { success: false, error: "Failed to fetch history" },
      { status: 500 }
    );
  }
}
