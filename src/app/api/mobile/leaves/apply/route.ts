import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser } from "@/lib/auth-mobile";
import { unauthorized } from "@/lib/responses";

export async function POST(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const body = await req.json();
    const { type, from, to, reason } = body;

    if (!type || !from || !to || !reason) {
      return NextResponse.json(
        { success: false, error: "Missing required fields" },
        { status: 400 }
      );
    }

    const request = await prisma.leaveRequest.create({
      data: {
        bookerId: user.id,
        type: type.charAt(0).toUpperCase() + type.slice(1).toLowerCase(),
        from: new Date(from),
        to: new Date(to),
        reason,
        status: "pending",
      },
    });

    return NextResponse.json({
      success: true,
      data: {
        id: request.id,
        message: "Leave request submitted for approval",
      },
    });
  } catch (err) {
    console.error("[leaves/apply]", err);
    return NextResponse.json(
      { success: false, error: "Failed to submit leave request" },
      { status: 500 }
    );
  }
}
