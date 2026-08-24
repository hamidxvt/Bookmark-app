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

    // Build data object with proper null handling
    const createData: any = {
      name: String(name),
      email: String(email),
      password: hash,
      phone: String(phone),
      adminApproved: adminApproved || "APPROVED",
      jobStatus: jobStatus || "ACTIVE",
      visitTargets: visitTargets ? parseInt(String(visitTargets)) : 7,
      cityId: resolvedCityId,
    };

    // Only add optional fields if provided
    if (designation) createData.designation = String(designation);
    if (basicSalary) createData.basicSalary = parseFloat(String(basicSalary));
    if (ratesPerVisit) createData.ratesPerVisit = parseFloat(String(ratesPerVisit));
    if (sampleBudget) createData.sampleBudget = parseFloat(String(sampleBudget));

    const booker = await prisma.booker.create({
      data: createData,
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
