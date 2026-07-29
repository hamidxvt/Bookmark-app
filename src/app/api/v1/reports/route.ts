/**
 * GET /api/v1/reports?type=visits|bookers|customers|attendance&from=&to=&cityId=&bookerId=
 * Returns CSV download for admin exports
 */

import { NextRequest, NextResponse } from "next/server";
import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";
import { prisma } from "@/lib/prisma";

function toCSV(rows: Record<string, unknown>[]): string {
  if (rows.length === 0) return "";
  const headers = Object.keys(rows[0]);
  const lines = [
    headers.join(","),
    ...rows.map((row) =>
      headers.map((h) => {
        const val = row[h];
        if (val === null || val === undefined) return "";
        const str = String(val).replace(/"/g, '""');
        return str.includes(",") || str.includes('"') || str.includes("\n")
          ? `"${str}"`
          : str;
      }).join(",")
    ),
  ];
  return lines.join("\n");
}

export async function GET(req: NextRequest) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ error: "Unauthorized" }, { status: 401 });

  const { searchParams } = req.nextUrl;
  const type = searchParams.get("type") ?? "visits";
  const from = searchParams.get("from") ? new Date(searchParams.get("from")!) : undefined;
  const to = searchParams.get("to") ? new Date(searchParams.get("to")!) : undefined;
  const cityId = searchParams.get("cityId") ? parseInt(searchParams.get("cityId")!) : undefined;
  const bookerId = searchParams.get("bookerId") ? parseInt(searchParams.get("bookerId")!) : undefined;

  try {
    let csv = "";
    let filename = `${type}-export.csv`;

    if (type === "visits") {
      const visits = await prisma.visit.findMany({
        where: {
          ...(from || to ? { visitDate: { gte: from, lte: to } } : {}),
          ...(bookerId ? { bookerId } : {}),
          ...(cityId ? { booker: { cityId } } : {}),
        },
        include: {
          booker: { select: { name: true, email: true, phone: true } },
          customer: { select: { name: true, customerType: true, address: true } },
        },
        orderBy: { visitDate: "desc" },
        take: 5000,
      });

      const rows = visits.map((v) => ({
        id: v.id,
        date: v.visitDate.toISOString().split("T")[0],
        officer: v.booker.name,
        officer_email: v.booker.email,
        customer: v.customer.name,
        customer_type: v.customer.customerType,
        status: v.status,
        check_in: v.checkInAt?.toISOString() ?? "",
        check_out: v.checkOutAt?.toISOString() ?? "",
        notes: v.notes ?? "",
        address: v.customer.address ?? "",
      }));

      csv = toCSV(rows);
      filename = `visits-${from?.toISOString().split("T")[0] ?? "all"}-to-${to?.toISOString().split("T")[0] ?? "today"}.csv`;

    } else if (type === "bookers") {
      const bookers = await prisma.booker.findMany({
        where: {
          ...(cityId ? { cityId } : {}),
          deletedAt: null,
        },
        include: { city: { select: { name: true } } },
        orderBy: { name: "asc" },
        take: 2000,
      });

      const rows = bookers.map((b) => ({
        id: b.id,
        name: b.name,
        email: b.email,
        phone: b.phone,
        city: b.city?.name ?? "",
        job_status: b.jobStatus,
        admin_approved: b.adminApproved,
        gps_status: b.gpsStatus,
        created: b.createdAt.toISOString().split("T")[0],
      }));

      csv = toCSV(rows);
      filename = "officers-export.csv";

    } else if (type === "customers") {
      const customers = await prisma.customer.findMany({
        where: {
          ...(cityId ? { cityId } : {}),
          deletedAt: null,
          approvalStatus: "APPROVED",
        },
        include: { city: { select: { name: true } } },
        orderBy: { name: "asc" },
        take: 5000,
      });

      const rows = customers.map((c) => ({
        id: c.id,
        name: c.name,
        type: c.customerType,
        owner_phone: c.ownerPhone,
        address: c.address ?? "",
        city: c.city?.name ?? "",
        priority: c.workingPriority,
        status: c.approvalStatus,
      }));

      csv = toCSV(rows);
      filename = "customers-export.csv";

    } else if (type === "attendance") {
      const records = await prisma.attendance.findMany({
        where: {
          ...(from || to ? { date: { gte: from, lte: to } } : {}),
          ...(bookerId ? { bookerId } : {}),
        },
        include: { booker: { select: { name: true, email: true } } },
        orderBy: [{ date: "desc" }, { bookerId: "asc" }],
        take: 5000,
      });

      const rows = records.map((a) => ({
        date: a.date.toISOString().split("T")[0],
        officer: a.booker.name,
        email: a.booker.email,
        status: a.status,
        start_at: a.startAt?.toISOString() ?? "",
        end_at: a.endAt?.toISOString() ?? "",
        reason: a.cannotReason ?? "",
      }));

      csv = toCSV(rows);
      filename = `attendance-${from?.toISOString().split("T")[0] ?? "all"}.csv`;

    } else if (type === "leaves") {
      const leaves = await prisma.leaveRequest.findMany({
        where: {
          ...(from || to ? { fromDate: { gte: from, lte: to } } : {}),
          ...(bookerId ? { bookerId } : {}),
        },
        include: { booker: { select: { name: true, email: true } } },
        orderBy: { createdAt: "desc" },
        take: 2000,
      });

      const rows = leaves.map((l) => ({
        officer: l.booker.name,
        email: l.booker.email,
        type: l.leaveType,
        from: l.fromDate.toISOString().split("T")[0],
        to: l.toDate.toISOString().split("T")[0],
        status: l.status,
        reason: l.reason,
        admin_notes: l.adminNotes ?? "",
        reviewed_at: l.reviewedAt?.toISOString() ?? "",
      }));

      csv = toCSV(rows);
      filename = "leaves-export.csv";
    }

    return new NextResponse(csv, {
      status: 200,
      headers: {
        "Content-Type": "text/csv; charset=utf-8",
        "Content-Disposition": `attachment; filename="${filename}"`,
      },
    });
  } catch (err: any) {
    return NextResponse.json({ success: false, error: err.message }, { status: 500 });
  }
}
