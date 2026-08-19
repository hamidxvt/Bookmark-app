import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";

// PATCH /api/v1/customer-updates/[id] — approve or reject a customer update request
export async function PATCH(
  req: Request,
  { params }: { params: Promise<{ id: string }> }
) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ error: "Unauthorized" }, { status: 401 });

  try {
    const { id } = await params;
    const requestId = parseInt(id);
    const body = await req.json();
    const { action, adminNotes } = body; // action: "approve" | "reject"

    const request = await prisma.request.findUnique({
      where: { id: requestId },
      select: { id: true, details: true, status: true, category: true },
    });

    if (!request || request.category !== "customer_update") {
      return NextResponse.json({ success: false, error: "Not found" }, { status: 404 });
    }
    if (request.status !== "PENDING") {
      return NextResponse.json({ success: false, error: "Already resolved" }, { status: 409 });
    }

    if (action === "approve") {
      // Parse the details and apply to the customer
      let detail: Record<string, unknown> = {};
      try { detail = JSON.parse(request.details); } catch { /* ignore */ }

      const customerId = detail.customerId as number;
      const updates = detail.updates as Record<string, string> | undefined;

      if (customerId && updates && Object.keys(updates).length > 0) {
        const customerData: Record<string, unknown> = {};
        if (updates.name)       customerData.name = updates.name;
        if (updates.ownerName)  customerData.ownerName = updates.ownerName;
        if (updates.ownerPhone) customerData.ownerPhone = updates.ownerPhone;
        if (updates.email)      customerData.email = updates.email;
        if (updates.address)    customerData.address = updates.address;
        if (updates.category)   customerData.category = updates.category;

        await prisma.customer.update({
          where: { id: customerId },
          data: customerData,
        });
      }

      await prisma.request.update({
        where: { id: requestId },
        data: { status: "RESOLVED", adminNotes: adminNotes ?? "Approved and applied." },
      });

      return NextResponse.json({ success: true, message: "Changes approved and applied to customer." });
    }

    if (action === "reject") {
      await prisma.request.update({
        where: { id: requestId },
        data: { status: "REJECTED", adminNotes: adminNotes ?? "Rejected by admin." },
      });
      return NextResponse.json({ success: true, message: "Request rejected." });
    }

    return NextResponse.json({ success: false, error: "Invalid action" }, { status: 400 });
  } catch (err) {
    console.error("[v1/customer-updates PATCH]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
