import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import bcrypt from "bcryptjs";

export async function POST(req: Request) {
  try {
    const body = await req.json();
    const {
      name, email, password, phone,
      designation, basicSalary, ratesPerVisit,
      visitTargets, sampleBudget, adminApproved,
      jobStatus, cityId,
    } = body;

    if (!name || !email || !password || !phone) {
      return NextResponse.json(
        { success: false, error: "Name, email, password and phone are required" },
        { status: 400 }
      );
    }

    // Resolve city
    let resolvedCityId: number | null = null;
    if (cityId) {
      resolvedCityId = parseInt(String(cityId));
    } else {
      let city = await prisma.city.findFirst();
      if (!city) city = await prisma.city.create({ data: { name: "Karachi" } });
      resolvedCityId = city.id;
    }

    const hash = await bcrypt.hash(String(password), 10);

    const booker = await prisma.booker.create({
      data: {
        name: String(name),
        email: String(email),
        password: hash,
        phone: String(phone),
        designation: designation ? String(designation) : null,
        basicSalary: basicSalary ? parseFloat(String(basicSalary)) : null,
        ratesPerVisit: ratesPerVisit ? parseFloat(String(ratesPerVisit)) : null,
        visitTargets: visitTargets ? parseInt(String(visitTargets)) : 7,
        sampleBudget: sampleBudget ? parseFloat(String(sampleBudget)) : null,
        adminApproved: String(adminApproved ?? "APPROVED"),
        jobStatus: String(jobStatus ?? "ACTIVE"),
        cityId: resolvedCityId,
      },
    });

    return NextResponse.json({
      success: true,
      data: { id: booker.id, name: booker.name, email: booker.email, cityId: booker.cityId },
      credentials: { email, password },
    });
  } catch (err: unknown) {
    const msg =
      err instanceof Error && err.message.includes("nique")
        ? "Email already exists"
        : "Failed to create officer";
    return NextResponse.json({ success: false, error: msg }, { status: 500 });
  }
}
