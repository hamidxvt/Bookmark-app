import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

export async function DELETE(
  _req: Request,
  { params }: { params: Promise<{ id: string }> }
) {
  try {
    const { id } = await params;
    await prisma.booker.update({
      where: { id: parseInt(id) },
      data: { deletedAt: new Date(), jobStatus: "NOT_ACTIVE" },
    });
    return NextResponse.json({ success: true });
  } catch (err) {
    console.error("[bookers DELETE]", err);
    return NextResponse.json({ success: false, error: "Failed" }, { status: 500 });
  }
}
