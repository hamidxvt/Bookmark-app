import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const skip = Number(searchParams.get("start") ?? 0);
    const take = Number(searchParams.get("length") ?? 50);
    const cityId = searchParams.get("cityId");
    const type = searchParams.get("type");

    const where: Record<string, unknown> = { deletedAt: null };
    if (cityId) where.cityId = parseInt(cityId);
    if (type) where.customerType = type.toUpperCase();

    const [records, total] = await Promise.all([
      prisma.customer.findMany({
        where,
        skip,
        take,
        orderBy: { createdAt: "desc" },
        select: {
          id: true,
          name: true,
          customerType: true,
          category: true,
          ownerName: true,
          ownerPhone: true,
          email: true,
          address: true,
          workingPriority: true,
          approvalStatus: true,
          latitude: true,
          longitude: true,
          createdAt: true,
          city: { select: { id: true, name: true } },
          area: { select: { id: true, name: true } },
        },
      }),
      prisma.customer.count({ where }),
    ]);

    return NextResponse.json({
      success: true,
      data: {
        recordsTotal: total,
        recordsFiltered: total,
        data: records,
      },
    });
  } catch (err) {
    console.error("[api/v1/customers]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch customers" }, { status: 500 });
  }
}

export async function POST(req: Request) {
  try {
    const body = await req.json();
    const {
      name, ownerName, ownerPhone, email, website,
      address, zone, cityId, category, customerType,
      workingPriority,
      examinationBoard, offeredProgramme, totalStudents,
      reviewMonth, sessionStarts,
    } = body;

    if (!name?.trim()) return NextResponse.json({ success: false, error: { message: "Name is required" } }, { status: 400 });
    if (!ownerPhone?.trim()) return NextResponse.json({ success: false, error: { message: "Contact number is required" } }, { status: 400 });
    if (!cityId) return NextResponse.json({ success: false, error: { message: "City is required" } }, { status: 400 });

    const customer = await (prisma as any).customer.create({
      data: {
        name: name.trim(),
        ownerName: ownerName?.trim() || null,
        ownerPhone: ownerPhone.trim(),
        email: email?.trim() || null,
        website: website?.trim() || null,
        address: address?.trim() || null,
        zone: zone || null,
        cityId: parseInt(String(cityId)),
        category: category || null,
        customerType: customerType ?? "OTHER",
        workingPriority: workingPriority ? parseInt(String(workingPriority)) : 3,
        approvalStatus: "PENDING",
        examinationBoard: examinationBoard || null,
        offeredProgramme: offeredProgramme || null,
        totalStudents: totalStudents ? parseInt(String(totalStudents)) : null,
        reviewMonth: reviewMonth ? new Date(reviewMonth) : null,
        sessionStarts: sessionStarts ? new Date(sessionStarts) : null,
      },
    });

    return NextResponse.json({ success: true, data: customer }, { status: 201 });
  } catch (err: any) {
    console.error("[api/v1/customers POST]", err);
    return NextResponse.json({ success: false, error: { message: err.message ?? "Failed to create customer" } }, { status: 500 });
  }
}
