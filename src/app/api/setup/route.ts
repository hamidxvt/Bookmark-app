import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import bcrypt from "bcryptjs";

// Seeds admin user + test booker. Safe to call multiple times.
export async function GET() {
  const log: string[] = [];
  try {
    log.push("start");
    const adminHash = await bcrypt.hash("Admin@123", 12);
    const officerHash = await bcrypt.hash("Officer@123", 12);
    log.push("hashed");

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
    log.push("admin upserted");

    // Find or create city
    let city = await prisma.city.findFirst({ where: { name: "Karachi" } });
    if (!city) {
      city = await prisma.city.create({ data: { name: "Karachi" } });
    }
    log.push(`city: ${city?.id}`);

    // Test booker
    await prisma.booker.upsert({
      where: { email: "officer@bookmark.pk" },
      update: { jobStatus: "ACTIVE", adminApproved: "APPROVED" },
      create: {
        name: "Test Officer",
        email: "officer@bookmark.pk",
        password: officerHash,
        phone: "03001234567",
        cityId: city.id,
        jobStatus: "ACTIVE",
        adminApproved: "APPROVED",
        visitTargets: 7,
      },
    });
    log.push("booker upserted");

    return NextResponse.json({
      success: true,
      message: "Seeded: admin@bookmark.pk / Admin@123  +  officer@bookmark.pk / Officer@123",
      log,
    });
  } catch (err: unknown) {
    const msg = err instanceof Error ? err.message : String(err);
    console.error("[setup]", msg, "\nLog so far:", log);
    return NextResponse.json({ success: false, error: msg, log }, { status: 500 });
  }
}
