import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import bcrypt from "bcryptjs";

// Seeds admin user + test booker. Safe to call multiple times.
export async function GET() {
  try {
    const adminHash = await bcrypt.hash("Admin@123", 12);
    const officerHash = await bcrypt.hash("Officer@123", 12);

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

    // Find or create city
    let city = await prisma.city.findFirst({ where: { name: "Karachi" } });
    if (!city) {
      city = await prisma.city.create({ data: { name: "Karachi" } });
    }

    // Test booker (sales officer)
    await prisma.booker.upsert({
      where: { email: "officer@bookmark.pk" },
      update: {
        jobStatus: "ACTIVE",
        adminApproved: "APPROVED",
      },
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
      message: "Seeded: admin@bookmark.pk / Admin@123  +  officer@bookmark.pk / Officer@123",
    });
  } catch (err) {
    console.error("[setup]", err);
    return NextResponse.json({ success: false, error: String(err) }, { status: 500 });
  }
}
