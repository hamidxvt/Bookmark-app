import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import bcrypt from "bcrypt";

/**
 * POST /api/v1/setup-admin
 * Creates the default admin account if it doesn't exist
 * 
 * Body: { email?: string, password?: string, name?: string }
 * Defaults: admin@gmail.com / admin#123 / Admin User
 */
export async function POST(req: Request) {
  try {
    const body = await req.json().catch(() => ({}));
    const email = body.email || "admin@gmail.com";
    const password = body.password || "admin#123";
    const name = body.name || "Admin User";

    // Check if admin already exists
    const existing = await prisma.user.findUnique({ where: { email } });
    if (existing) {
      return NextResponse.json({
        success: true,
        message: "Admin account already exists",
        email,
      });
    }

    // Hash password
    const hashedPassword = await bcrypt.hash(password, 10);

    // Create admin
    const admin = await prisma.user.create({
      data: {
        email,
        password: hashedPassword,
        name,
        role: "ADMIN",
      },
    });

    return NextResponse.json({
      success: true,
      message: "Admin account created successfully",
      admin: {
        id: admin.id,
        email: admin.email,
        name: admin.name,
        role: admin.role,
      },
    });
  } catch (err) {
    console.error("[setup-admin]", err);
    return NextResponse.json(
      { success: false, error: String(err) },
      { status: 500 }
    );
  }
}
