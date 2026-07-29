import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

export async function PATCH(
  req: Request,
  { params }: { params: Promise<{ id: string }> }
) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const { id } = await params;
    const rid = parseInt(id);
    const existing = await prisma.request.findFirst({
      where: { id: rid, bookerId: user.id },
    });
    if (!existing) {
      return NextResponse.json({ success: false, error: "Not found" }, { status: 404 });
    }
    const updated = await prisma.request.update({
      where: { id: rid },
      data: { status: "RESOLVED" },
    });
    return NextResponse.json({ success: true, data: updated });
  } catch (err) {
    console.error("[mobile/samples/recover]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
