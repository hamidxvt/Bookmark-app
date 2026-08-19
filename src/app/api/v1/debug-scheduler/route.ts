import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

/**
 * GET /api/v1/debug-scheduler?bookerId=88
 * Debug why a booker isn't getting visits
 */
export async function GET(req: Request) {
  const url = new URL(req.url);
  const bookerId = parseInt(url.searchParams.get("bookerId") || "88");

  const log: string[] = [];

  try {
    // Get the booker
    const booker = await prisma.booker.findUnique({
      where: { id: bookerId },
      select: { id: true, cityId: true, adminApproved: true, jobStatus: true },
    });

    if (!booker) {
      return NextResponse.json({ error: `Booker ${bookerId} not found` }, { status: 404 });
    }

    log.push(`Booker ${bookerId}: ${JSON.stringify(booker)}`);

    if (!booker.cityId) {
      log.push("ERROR: Booker has no cityId");
      return NextResponse.json({ error: "No cityId", log });
    }

    // Count customers with GPS in this city
    const withGps = await prisma.customer.count({
      where: {
        cityId: booker.cityId,
        approvalStatus: "APPROVED",
        latitude: { not: null },
        longitude: { not: null },
      },
    });
    log.push(`Customers with GPS in city ${booker.cityId}: ${withGps}`);

    // Count all customers in this city
    const total = await prisma.customer.count({
      where: {
        cityId: booker.cityId,
        approvalStatus: "APPROVED",
      },
    });
    log.push(`Total approved customers in city ${booker.cityId}: ${total}`);

    // Get recently visited customers (past 7 days)
    const recently = await prisma.visit.findMany({
      where: {
        bookerId,
        visitDate: { gte: new Date(Date.now() - 7 * 24 * 60 * 60 * 1000) },
      },
      select: { customerId: true },
    });
    log.push(`Recently visited customers (past 7 days): ${recently.length}`);

    // Try to get customers as the scheduler would
    const target = new Date();
    target.setHours(0, 0, 0, 0);

    const existing = await prisma.visit.count({
      where: { bookerId, visitDate: target },
    });
    log.push(`Existing visits for today: ${existing}`);

    const recentIds = recently.map((v) => v.customerId);
    const excludeFilter = recentIds.length > 0 ? { id: { notIn: recentIds } } : {};

    const customers = await prisma.customer.findMany({
      where: {
        approvalStatus: "APPROVED",
        deletedAt: null,
        cityId: booker.cityId,
        latitude: { not: null },
        longitude: { not: null },
        ...excludeFilter,
      },
      orderBy: [{ workingPriority: "asc" }],
      take: 7 - existing,
    });

    log.push(`Found ${customers.length} customers with GPS (excluding recently visited)`);

    if (customers.length < 7 - existing) {
      const needed = 7 - existing - customers.length;
      const excludeIds = [...customers.map((c) => c.id), ...recentIds];
      const extra = await prisma.customer.findMany({
        where: {
          approvalStatus: "APPROVED",
          deletedAt: null,
          cityId: booker.cityId,
          ...(excludeIds.length > 0 ? { id: { notIn: excludeIds } } : {}),
        },
        orderBy: [{ workingPriority: "asc" }],
        take: needed,
      });
      log.push(`Found ${extra.length} additional customers without GPS`);
    }

    return NextResponse.json({
      success: true,
      booker,
      log,
    });
  } catch (error) {
    return NextResponse.json(
      { success: false, error: String(error), log },
      { status: 500 }
    );
  }
}
