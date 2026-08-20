import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import bcrypt from "bcryptjs";

// POST /api/mobile/reset-password
// Body: { email, otp, newPassword }
export async function POST(req: Request) {
  try {
    const { email, otp, newPassword } = await req.json();

    if (!email?.trim() || !otp?.trim() || !newPassword?.trim()) {
      return NextResponse.json({ success: false, error: "email, otp and newPassword are required" }, { status: 400 });
    }

    if (newPassword.length < 6) {
      return NextResponse.json({ success: false, error: "Password must be at least 6 characters" }, { status: 400 });
    }

    const booker = await prisma.booker.findFirst({
      where: { email: email.trim().toLowerCase(), deletedAt: null },
      select: { id: true, resetOtp: true, resetOtpExpiry: true },
    });

    if (!booker || !booker.resetOtp || !booker.resetOtpExpiry) {
      return NextResponse.json({ success: false, error: "Invalid or expired OTP" }, { status: 400 });
    }

    if (booker.resetOtp !== otp.trim()) {
      return NextResponse.json({ success: false, error: "Incorrect OTP" }, { status: 400 });
    }

    if (new Date() > booker.resetOtpExpiry) {
      return NextResponse.json({ success: false, error: "OTP has expired. Please request a new one." }, { status: 400 });
    }

    const hashed = await bcrypt.hash(newPassword, 10);

    await prisma.booker.update({
      where: { id: booker.id },
      data: {
        password: hashed,
        resetOtp: null,
        resetOtpExpiry: null,
      },
    });

    return NextResponse.json({ success: true, message: "Password reset successfully. Please log in." });
  } catch (err) {
    console.error("[reset-password]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
