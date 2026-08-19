import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// POST /api/mobile/customers/[id]/update-request
// Officer submits proposed edits to a customer's info — admin must approve
export async function POST(
  req: Request,
  { params }: { params: Promise<{ id: string }> }
) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const { id } = await params;
    const customerId = parseInt(id);
    const body = await req.json();
    const { name, ownerName, ownerPhone, email, address, category, notes } = body;

    // Verify customer exists
    const customer = await prisma.customer.findUnique({
      where: { id: customerId },
      select: { id: true, name: true },
    });
    if (!customer) {
      return NextResponse.json({ success: false, error: "Customer not found" }, { status: 404 });
    }

    // Build the proposed changes object (only include changed fields)
    const updates: Record<string, string> = {};
    if (name)       updates.name = name;
    if (ownerName)  updates.ownerName = ownerName;
    if (ownerPhone) updates.ownerPhone = ownerPhone;
    if (email)      updates.email = email;
    if (address)    updates.address = address;
    if (category)   updates.category = category;

    if (Object.keys(updates).length === 0) {
      return NextResponse.json({ success: false, error: "No changes submitted" }, { status: 400 });
    }

    // Store as a Request with category "customer_update"
    const request = await prisma.request.create({
      data: {
        bookerId: user.id,
        title: `Update Request: ${customer.name}`,
        category: "customer_update",
        details: JSON.stringify({
          customerId,
          customerName: customer.name,
          updates,
          officerNotes: notes ?? "",
        }),
        status: "PENDING",
      },
    });

    return NextResponse.json({
      success: true,
      data: { requestId: request.id },
      message: "Update request submitted. Admin will review shortly.",
    });
  } catch (err) {
    console.error("[mobile/customers/update-request]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
