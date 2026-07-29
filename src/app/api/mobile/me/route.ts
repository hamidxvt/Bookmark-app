import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// GET /api/mobile/me — get current booker profile
export async function GET(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const booker = await prisma.booker.findUnique({
      where: { id: user.id },
      select: {
        id: true, name: true, email: true, phone: true,
        profilePhoto: true, cityId: true, regionId: true,
        visitTargets: true, ratesPerVisit: true, gpsStatus: true,
      },
    });

    if (!booker) {
      return NextResponse.json({ success: false, error: "Booker not found" }, { status: 404 });
    }

    return NextResponse.json({
      success: true,
      data: {
        ...booker,
        role: "sales_officer",
        leaveBalanceSick: 10,
        leaveBalanceCasual: 18,
      },
    });
  } catch (err) {
    console.error("[mobile/me]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
