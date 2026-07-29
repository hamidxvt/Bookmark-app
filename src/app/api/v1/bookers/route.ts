import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const skip = Number(searchParams.get("start") ?? 0);
    const take = Number(searchParams.get("length") ?? 50);
    const status = searchParams.get("status");
    const cityId = searchParams.get("cityId");

    const where: Record<string, unknown> = {};
    if (status) where.adminApproved = status.toUpperCase();
    if (cityId) where.cityId = parseInt(cityId);

    const [records, total] = await Promise.all([
      prisma.booker.findMany({
        where,
        skip,
        take,
        orderBy: { createdAt: "desc" },
        select: {
          id: true,
          name: true,
          email: true,
          phone: true,
          gender: true,
          jobStatus: true,
          adminApproved: true,
          gpsStatus: true,
          lastSeenAt: true,
          lastLatitude: true,
          lastLongitude: true,
          visitTargets: true,
          ratesPerVisit: true,
          createdAt: true,
          city: { select: { id: true, name: true } },
          region: { select: { id: true, name: true } },
        },
      }),
      prisma.booker.count({ where }),
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
    console.error("[api/v1/bookers]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch bookers" }, { status: 500 });
  }
}

export async function PATCH(req: Request) {
  try {
    const { id, adminApproved, jobStatus } = await req.json();
    if (!id) return NextResponse.json({ success: false, error: "Missing id" }, { status: 400 });

    const data: Record<string, unknown> = {};
    if (adminApproved) data.adminApproved = adminApproved;
    if (jobStatus) data.jobStatus = jobStatus;

    const booker = await prisma.booker.update({ where: { id }, data });
    return NextResponse.json({ success: true, data: booker });
  } catch (err) {
    console.error("[api/v1/bookers PATCH]", err);
    return NextResponse.json({ success: false, error: "Failed to update booker" }, { status: 500 });
  }
}
