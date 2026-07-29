import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import bcrypt from "bcryptjs";

export async function DELETE(
  _req: Request,
  { params }: { params: { id: string } }
) {
  try {
    const id = parseInt(params.id);
    await prisma.booker.update({
      where: { id },
      data: { deletedAt: new Date(), jobStatus: "NOT_ACTIVE", adminApproved: "REJECTED" },
    });
    return NextResponse.json({ success: true });
  } catch (err) {
    console.error("[bookers DELETE]", err);
    return NextResponse.json({ success: false, error: "Failed" }, { status: 500 });
  }
}

export async function POST(req: Request) {
  try {
    const body = await req.json();
    const { name, email, password, phone, cityId } = body;
    if (!name || !email || !password || !phone) {
      return NextResponse.json({ success: false, error: "Missing required fields" }, { status: 400 });
    }

    const hash = await bcrypt.hash(password, 10);

    let city = cityId ? await prisma.city.findFirst({ where: { id: parseInt(cityId) } }) : null;
    if (!city) {
      city = await prisma.city.findFirst() ?? await prisma.city.create({ data: { name: "Karachi" } });
    }

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

    return NextResponse.json({ success: true, data: { id: booker.id, name: booker.name, email: booker.email } });
  } catch (err: unknown) {
    const msg = err instanceof Error && err.message.includes("Unique") ? "Email already exists" : "Failed to create";
    return NextResponse.json({ success: false, error: msg }, { status: 500 });
  }
}
