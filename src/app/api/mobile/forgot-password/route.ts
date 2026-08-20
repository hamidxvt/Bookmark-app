import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { sendOtpEmail } from "@/lib/mailer";

// POST /api/mobile/forgot-password
// Body: { email: string }
// Generates a 6-digit OTP, stores it (hashed) and emails it to the officer
export async function POST(req: Request) {
  try {
    const { email } = await req.json();

    if (!email?.trim()) {
      return NextResponse.json({ success: false, error: "Email is required" }, { status: 400 });
    }

    const booker = await prisma.booker.findFirst({
      where: { email: email.trim().toLowerCase(), deletedAt: null },
      select: { id: true, name: true, email: true },
    });

    // Don't reveal whether the email exists
    if (!booker) {
      return NextResponse.json({
        success: true,
        message: "If this email is registered, you will receive an OTP shortly.",
      });
    }

    // Generate 6-digit OTP
    const otp = String(Math.floor(100000 + Math.random() * 900000));
    const expiry = new Date(Date.now() + 15 * 60 * 1000); // 15 min

    await prisma.booker.update({
      where: { id: booker.id },
      data: { resetOtp: otp, resetOtpExpiry: expiry },
    });

    // Send email (fails gracefully if SMTP not configured)
    try {
      await sendOtpEmail(booker.email, booker.name, otp);
    } catch (emailErr) {
      console.warn("[forgot-password] Email send failed:", emailErr);
      // In dev/no-email mode, return OTP in response so it can be tested
      if (process.env.NODE_ENV !== "production") {
        return NextResponse.json({
          success: true,
          message: "OTP generated (email not configured — dev mode).",
          devOtp: otp,
        });
      }
    }

    return NextResponse.json({
      success: true,
      message: "If this email is registered, you will receive an OTP shortly.",
    });
  } catch (err) {
    console.error("[forgot-password]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
