import { NextResponse } from "next/server";
import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";
import { prisma } from "@/lib/prisma";

// PATCH /api/v1/samples/:id — admin approve / reject
export async function PATCH(req: Request, { params }: { params: { id: string } }) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ success: false, error: "Unauthorized" }, { status: 401 });

  const id = Number(params.id);
  if (isNaN(id)) return NextResponse.json({ success: false, error: "Invalid ID" }, { status: 400 });

  try {
    const { status, adminNotes } = await req.json();
    if (!["approved", "rejected"].includes(status)) {
      return NextResponse.json({ success: false, error: "Invalid status. Must be approved or rejected." }, { status: 400 });
    }

    const updated = await prisma.sampleRequest.update({
      where: { id },
      data: {
        status,
        adminNotes: adminNotes ?? null,
        reviewedAt: new Date(),
      },
      include: {
        booker: { select: { id: true, name: true, email: true } },
        customer: { select: { id: true, name: true } },
      },
    });

    return NextResponse.json({ success: true, data: updated });
  } catch (err: any) {
    if (err.code === "P2025") {
      return NextResponse.json({ success: false, error: "Sample request not found" }, { status: 404 });
    }
    console.error("[admin samples PATCH]", err);
    return NextResponse.json({ success: false, error: "Update failed" }, { status: 500 });
  }
}
