import { NextResponse } from "next/server";
import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";
import { prisma } from "@/lib/prisma";

// GET /api/v1/samples — admin list with optional ?status= filter
export async function GET(req: Request) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ success: false, error: "Unauthorized" }, { status: 401 });

  const { searchParams } = new URL(req.url);
  const status = searchParams.get("status");
  const officerId = searchParams.get("officerId");

  const samples = await prisma.sampleRequest.findMany({
    where: {
      ...(status && status !== "all" ? { status } : {}),
      ...(officerId ? { bookerId: Number(officerId) } : {}),
    },
    orderBy: { createdAt: "desc" },
    include: {
      booker: { select: { id: true, name: true, email: true, sampleBudget: true } },
      customer: { select: { id: true, name: true } },
    },
  });

  return NextResponse.json({ success: true, data: samples });
}
