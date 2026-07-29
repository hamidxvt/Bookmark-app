import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import bcrypt from "bcryptjs";

export async function POST(req: Request) {
  try {
    const { name, email, password, phone } = await req.json();
    if (!name || !email || !password || !phone) {
      return NextResponse.json({ success: false, error: "Name, email, password and phone are required" }, { status: 400 });
    }

    const hash = await bcrypt.hash(password, 10);
    let city = await prisma.city.findFirst();
    if (!city) city = await prisma.city.create({ data: { name: "Karachi" } });

    const booker = await prisma.booker.create({
      data: {
        name,
        email,
        password: hash,
        phone,
        cityId: city.id,
        jobStatus: "ACTIVE",
        adminApproved: "APPROVED",
        visitTargets: 7,
      },
    });

    return NextResponse.json({
      success: true,
      data: { id: booker.id, name: booker.name, email: booker.email },
      credentials: { email, password },
    });
  } catch (err: unknown) {
    const msg = err instanceof Error && err.message.includes("nique") ? "Email already exists" : "Failed to create officer";
    return NextResponse.json({ success: false, error: msg }, { status: 500 });
  }
}
