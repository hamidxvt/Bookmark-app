import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// GET /api/mobile/customers?q=search&limit=20
export async function GET(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  const { searchParams } = new URL(req.url);
  // Accept both "q" and "search" query params for compatibility
  const q = searchParams.get("search") ?? searchParams.get("q") ?? "";
  // Accept both "limit" and "length" query params
  const limit = Math.min(Number(searchParams.get("length") ?? searchParams.get("limit") ?? 20), 50);

  try {
    const customers = await prisma.customer.findMany({
      where: {
        deletedAt: null,
        ...(q ? { name: { contains: q, mode: "insensitive" } } : {}),
      },
      select: {
        id: true,
        name: true,
        customerType: true,
        ownerName: true,
        ownerPhone: true,
        address: true,
        city: { select: { name: true } },
      },
      orderBy: { workingPriority: "asc" },
      take: limit,
    });

    const formattedCustomers = customers.map(c => ({
      id: c.id,
      name: c.name,
      type: c.customerType,
      contact: c.ownerName ?? "",
      phone: c.ownerPhone ?? "",
      address: c.address ?? "",
      // Return city as object so mobile can do c['city']['name']
      city: c.city ? { name: c.city.name } : null,
    }));

    return NextResponse.json({
      success: true,
      data: formattedCustomers,
    });
  } catch (err) {
    console.error("[mobile/customers GET]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}

function fieldError(field: string, message: string, status = 400) {
  return NextResponse.json({ success: false, error: { field, message } }, { status });
}

// POST /api/mobile/customers — officer creates a new customer (pending approval)
export async function POST(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const body = await req.json();
    const {
      name,
      ownerName,
      ownerPhone,
      phone,
      email,
      address,
      category,
      customerType,
      cityId,
      latitude,
      longitude,
    } = body;

    const trimmedName = String(name ?? "").trim();
    const trimmedPhone = String(ownerPhone ?? phone ?? "").trim();

    if (!trimmedName) return fieldError("name", "Name is required");
    if (!trimmedPhone) return fieldError("phone", "Phone number is required");

    let resolvedCityId = cityId ? parseInt(String(cityId), 10) : NaN;
    if (Number.isNaN(resolvedCityId)) {
      const booker = await prisma.booker.findUnique({
        where: { id: user.id },
        select: { cityId: true },
      });
      resolvedCityId = booker?.cityId ?? NaN;
    }
    if (Number.isNaN(resolvedCityId)) return fieldError("city", "City is required");

    const lat = latitude != null && latitude !== 0 ? Number(latitude) : null;
    const lng = longitude != null && longitude !== 0 ? Number(longitude) : null;

    const customer = await prisma.customer.create({
      data: {
        name: trimmedName,
        ownerName: ownerName?.trim() || null,
        ownerPhone: trimmedPhone,
        email: email?.trim() || null,
        address: address?.trim() || null,
        category: category?.trim() || null,
        customerType: String(customerType ?? "OTHER").toUpperCase() as "SCHOOL" | "COLLEGE" | "SELF" | "RETAILER" | "OTHER",
        cityId: resolvedCityId,
        approvalStatus: "PENDING",
        assignedBookerId: user.id,
        ...(lat != null && !Number.isNaN(lat) ? { latitude: lat } : {}),
        ...(lng != null && !Number.isNaN(lng) ? { longitude: lng } : {}),
      },
      select: { id: true, name: true, ownerPhone: true, approvalStatus: true },
    });

    return NextResponse.json({ success: true, data: customer }, { status: 201 });
  } catch (err) {
    console.error("[mobile/customers POST]", err);
    return NextResponse.json({ success: false, error: { message: "Failed to create customer" } }, { status: 500 });
  }
}
