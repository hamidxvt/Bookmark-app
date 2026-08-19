import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";

// GET /api/v1/customer-updates — list all customer update requests
export async function GET(req: Request) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ error: "Unauthorized" }, { status: 401 });

  try {
    const { searchParams } = new URL(req.url);
    const status = searchParams.get("status") ?? "PENDING";

    const requests = await prisma.request.findMany({
      where: { category: "customer_update", status: status as "PENDING" | "RESOLVED" | "REJECTED" },
      orderBy: { createdAt: "desc" },
      select: {
        id: true,
        title: true,
        details: true,
        status: true,
        adminNotes: true,
        createdAt: true,
        booker: { select: { id: true, name: true, email: true } },
      },
    });

    // Parse the JSON details
    const parsed = requests.map(r => {
      let detail: Record<string, unknown> = {};
      try { detail = JSON.parse(r.details); } catch { /* ignore */ }
      return { ...r, detail };
    });

    return NextResponse.json({ success: true, data: parsed, total: parsed.length });
  } catch (err) {
    console.error("[v1/customer-updates GET]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
