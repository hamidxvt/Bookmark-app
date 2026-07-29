/**
 * POST /api/mobile/fcm
 * Register or update FCM token for a booker (called on app start / login)
 */

import { NextRequest, NextResponse } from "next/server";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";
import { prisma } from "@/lib/prisma";

export async function POST(req: NextRequest) {
  const user = await getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const { fcmToken } = await req.json();
    if (!fcmToken || typeof fcmToken !== "string") {
      return NextResponse.json({ success: false, error: "fcmToken required" }, { status: 400 });
    }

    await prisma.booker.update({
      where: { id: user.id },
      data: { fcmToken: fcmToken.trim() },
    });

    return NextResponse.json({ success: true });
  } catch (err: any) {
    return NextResponse.json({ success: false, error: err.message }, { status: 500 });
  }
}
