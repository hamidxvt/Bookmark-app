import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import bcrypt from "bcryptjs";
import jwt from "jsonwebtoken";

const JWT_SECRET = process.env.JWT_SECRET ?? process.env.NEXTAUTH_SECRET ?? "bookmark-sfa-secret";

export async function OPTIONS() {
  return new Response(null, {
    status: 204,
    headers: {
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Methods": "POST, OPTIONS",
      "Access-Control-Allow-Headers": "Content-Type, Authorization",
    },
  });
}

export async function POST(req: Request) {
  try {
    const { email, password } = await req.json();

    if (!email || !password) {
      return NextResponse.json({ success: false, error: "Email and password are required" }, { status: 400 });
    }

    const booker = await prisma.booker.findUnique({
      where: { email },
      select: {
        id: true, name: true, email: true, phone: true,
        password: true, jobStatus: true, adminApproved: true,
        cityId: true, regionId: true, profilePhoto: true,
        visitTargets: true, ratesPerVisit: true,
      },
    });

    if (!booker) {
      return NextResponse.json({ success: false, error: "Invalid email or password" }, { status: 401 });
    }

    if (booker.adminApproved !== "APPROVED") {
      return NextResponse.json({ success: false, error: "Account pending admin approval" }, { status: 403 });
    }

    if (booker.jobStatus !== "ACTIVE") {
      return NextResponse.json({ success: false, error: "Account is not active" }, { status: 403 });
    }

    const valid = await bcrypt.compare(password, booker.password);
    if (!valid) {
      return NextResponse.json({ success: false, error: "Invalid email or password" }, { status: 401 });
    }

    const token = jwt.sign(
      { id: booker.id, email: booker.email, role: "booker" },
      JWT_SECRET,
      { expiresIn: "30d" }
    );

    const { password: _, ...safeBooker } = booker;

    return NextResponse.json({
      success: true,
      data: {
        token,
        user: {
          ...safeBooker,
          role: "sales_officer",
          leaveBalanceSick: 10,
          leaveBalanceCasual: 18,
        },
      },
    });
  } catch (err) {
    console.error("[mobile/auth]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
