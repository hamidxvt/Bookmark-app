import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import bcrypt from "bcryptjs";

// Called once at boot via middleware to ensure admin user exists.
// Safe to call multiple times — uses upsert.
export async function GET() {
  try {
    const hash = await bcrypt.hash("Admin@123", 12);

    await prisma.user.upsert({
      where: { email: "admin@bookmark.pk" },
      update: {},
      create: {
        name: "Super Admin",
        email: "admin@bookmark.pk",
        password: hash,
        role: "SUPER_ADMIN",
      },
    });

    return NextResponse.json({ success: true, message: "Admin seeded" });
  } catch (err) {
    console.error("[setup]", err);
    return NextResponse.json({ success: false, error: String(err) }, { status: 500 });
  }
}
