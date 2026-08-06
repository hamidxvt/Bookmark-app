import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

// PATCH /api/v1/cities/[id] — update city
export async function PATCH(req: Request, { params }: { params: Promise<{ id: string }> }) {
  const { id } = await params;
  try {
    const body = await req.json();
    const { name, latitude, longitude, geofenceRadius, isActive } = body;

    const city = await prisma.city.update({
      where: { id: parseInt(id) },
      data: {
        ...(name && { name: name.trim().toUpperCase() }),
        ...(latitude !== undefined && { latitude: latitude ? parseFloat(latitude) : null }),
        ...(longitude !== undefined && { longitude: longitude ? parseFloat(longitude) : null }),
        ...(geofenceRadius !== undefined && { geofenceRadius: parseInt(geofenceRadius) }),
        ...(isActive !== undefined && { isActive }),
      },
    });

    return NextResponse.json({ success: true, data: city });
  } catch (err) {
    console.error("[v1/cities PATCH]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}

// DELETE /api/v1/cities/[id] — delete city (only if no bookers/customers)
export async function DELETE(_req: Request, { params }: { params: Promise<{ id: string }> }) {
  const { id } = await params;
  try {
    const cid = parseInt(id);
    const count = await prisma.booker.count({ where: { cityId: cid, deletedAt: null } });
    if (count > 0) {
      return NextResponse.json(
        { success: false, error: `Cannot delete: ${count} officer(s) assigned to this city` },
        { status: 400 }
      );
    }
    await prisma.city.delete({ where: { id: cid } });
    return NextResponse.json({ success: true });
  } catch (err) {
    console.error("[v1/cities DELETE]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
