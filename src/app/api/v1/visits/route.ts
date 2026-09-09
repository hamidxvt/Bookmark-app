import { NextResponse } from "next/server";
import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";
import { prisma } from "@/lib/prisma";
import { notifyBooker } from "@/lib/scheduler";
import { validateCityMatch } from "@/lib/visit-assignment";

export async function POST(req: Request) {
  const session = await getServerSession(authOptions);
  if (!session) {
    return NextResponse.json({ success: false, error: "Unauthorized" }, { status: 401 });
  }

  try {
    const { customerId, bookerId, visitDate, notes } = await req.json();

    if (!customerId || !bookerId || !visitDate) {
      return NextResponse.json(
        { success: false, error: "customerId, bookerId, and visitDate are required" },
        { status: 400 },
      );
    }

    const date = new Date(visitDate);
    if (isNaN(date.getTime())) {
      return NextResponse.json({ success: false, error: "Invalid visitDate" }, { status: 400 });
    }
    date.setHours(0, 0, 0, 0);

    const [booker, customer] = await Promise.all([
      prisma.booker.findUnique({
        where: { id: Number(bookerId) },
        select: { id: true, name: true, cityId: true },
      }),
      prisma.customer.findUnique({
        where: { id: Number(customerId) },
        select: { id: true, name: true, cityId: true },
      }),
    ]);

    if (!booker || !customer) {
      return NextResponse.json({ success: false, error: "Officer or customer not found" }, { status: 404 });
    }

    const cityCheck = await validateCityMatch(booker.cityId, customer.cityId);
    if (!cityCheck.ok) {
      return NextResponse.json({ success: false, error: cityCheck.error }, { status: 400 });
    }

    const visit = await prisma.visit.create({
      data: {
        bookerId: booker.id,
        customerId: customer.id,
        visitDate: date,
        notes: notes ?? null,
        status: "PENDING",
      },
    });

    await notifyBooker(
      booker.id,
      "New Visit Assigned",
      `You have a visit scheduled at ${customer.name} on ${date.toLocaleDateString("en-PK")}.${notes ? ` Note: ${notes}` : ""}`,
      { type: "visit_assigned", visitId: String(visit.id) },
    ).catch(() => {});

    return NextResponse.json({ success: true, data: { id: visit.id } });
  } catch (err) {
    console.error("[api/v1/visits POST]", err);
    return NextResponse.json({ success: false, error: "Failed to create visit" }, { status: 500 });
  }
}

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const today = searchParams.get("today") === "1";
    const skip = Number(searchParams.get("start") ?? 0);
    const take = Number(searchParams.get("length") ?? 50);
    const bookerId = searchParams.get("bookerId");
    const status = searchParams.get("status");

    const todayDate = new Date();
    todayDate.setHours(0, 0, 0, 0);

    const where: Record<string, unknown> = {};
    if (today) where.visitDate = todayDate;
    if (bookerId) where.bookerId = parseInt(bookerId);
    if (status) where.status = status.toUpperCase();

    const [records, total] = await Promise.all([
      prisma.visit.findMany({
        where,
        skip,
        take,
        orderBy: { visitDate: "desc" },
        include: {
          booker: { select: { id: true, name: true, email: true } },
          customer: { select: { id: true, name: true, customerType: true, address: true, cityId: true, latitude: true, longitude: true } },
        },
      }),
      prisma.visit.count({ where }),
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
    console.error("[api/v1/visits]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch visits" }, { status: 500 });
  }
}
