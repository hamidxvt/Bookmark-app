import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import bcrypt from "bcryptjs";

// Seeds admin user + test booker. Safe to call multiple times.
export async function GET() {
  try {
    const adminHash = await bcrypt.hash("Admin@123", 12);
    const officerHash = await bcrypt.hash("Officer@123", 12);

    // Ensure a city exists
    const city = await prisma.city.upsert({
      where: { name: "Karachi" },
      update: {},
      create: { name: "Karachi" },
    });

    // Admin user
    await prisma.user.upsert({
      where: { email: "admin@bookmark.pk" },
      update: {},
      create: {
        name: "Super Admin",
        email: "admin@bookmark.pk",
        password: adminHash,
        role: "SUPER_ADMIN",
      },
    });

    // Test booker (sales officer)
    await prisma.booker.upsert({
      where: { email: "officer@bookmark.pk" },
      update: {},
      create: {
        name: "Test Officer",
        email: "officer@bookmark.pk",
        password: officerHash,
        phone: "03001234567",
        cityId: city.id,
        jobStatus: "ACTIVE",
        adminApproved: "APPROVED",
        visitTargets: 7,
        ratesPerVisit: 3000,
      },
    });

    return NextResponse.json({
      success: true,
      message: "Seeded: admin@bookmark.pk + officer@bookmark.pk",
    });
  } catch (err) {
    console.error("[setup]", err);
    return NextResponse.json({ success: false, error: String(err) }, { status: 500 });
  }
}
