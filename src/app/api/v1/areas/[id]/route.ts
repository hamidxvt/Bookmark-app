import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

// PATCH /api/v1/areas/[id]
export async function PATCH(req: Request, { params }: { params: Promise<{ id: string }> }) {
  const { id } = await params;
  try {
    const body = await req.json();
    const { name, latitude, longitude, geofenceRadius, address } = body;

    const area = await prisma.area.update({
      where: { id: parseInt(id) },
      data: {
        ...(name      && { name: name.trim() }),
        ...(latitude  !== undefined && { latitude:  latitude  ? parseFloat(latitude)  : null }),
        ...(longitude !== undefined && { longitude: longitude ? parseFloat(longitude) : null }),
        ...(geofenceRadius !== undefined && { geofenceRadius: parseInt(geofenceRadius) }),
        ...(address   !== undefined && { address: address?.trim() ?? null }),
      },
    });

    return NextResponse.json({ success: true, data: area });
  } catch (err) {
    console.error("[v1/areas PATCH]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}

// DELETE /api/v1/areas/[id]
export async function DELETE(_req: Request, { params }: { params: Promise<{ id: string }> }) {
  const { id } = await params;
  try {
    await prisma.area.delete({ where: { id: parseInt(id) } });
    return NextResponse.json({ success: true });
  } catch (err) {
    console.error("[v1/areas DELETE]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
