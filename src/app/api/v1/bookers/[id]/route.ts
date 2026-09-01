import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import bcrypt from "bcryptjs";
import { getServerSession } from "next-auth";
import { authOptions } from "@/lib/auth";

// GET /api/v1/bookers/[id] — get single officer
export async function GET(
  _req: Request,
  { params }: { params: Promise<{ id: string }> }
) {
  const { id } = await params;
  try {
    const booker = await prisma.booker.findUnique({
      where: { id: parseInt(id) },
      select: {
        id: true, name: true, email: true, phone: true, gender: true, designation: true,
        jobStatus: true, adminApproved: true, cityId: true,
        visitTargets: true, ratesPerVisit: true,
        basicSalary: true, securityDepositPct: true,
        sampleBudget: true, rewardPoints: true,
        profilePhoto: true,
        city: { select: { id: true, name: true } },
        createdAt: true,
      },
    });
    if (!booker) return NextResponse.json({ success: false, error: "Not found" }, { status: 404 });
    return NextResponse.json({ success: true, data: booker });
  } catch (err) {
    console.error("[bookers GET id]", err);
    return NextResponse.json({ success: false, error: "Failed" }, { status: 500 });
  }
}

// PATCH /api/v1/bookers/[id] — update officer (name, email, status, salary, reset password)
export async function PATCH(
  req: Request,
  { params }: { params: Promise<{ id: string }> }
) {
  const session = await getServerSession(authOptions);
  if (!session) return NextResponse.json({ error: "Unauthorized" }, { status: 401 });

  const { id } = await params;
  try {
    const body = await req.json();
    const {
      name, email, phone, cityId, designation,
      jobStatus, adminApproved,
      visitTargets, ratesPerVisit,
      basicSalary, securityDepositPct, sampleBudget,
      newPassword,  // admin setting a new password for officer
      profilePhoto,
    } = body;

    const data: Record<string, unknown> = {};
    if (name !== undefined)              data.name = name;
    if (email !== undefined)             data.email = email;
    if (phone !== undefined)             data.phone = phone;
    if (designation !== undefined)       data.designation = designation;
    if (cityId !== undefined)            data.cityId = cityId ? Number(cityId) : null;
    if (jobStatus !== undefined)         data.jobStatus = jobStatus;
    if (adminApproved !== undefined)     data.adminApproved = adminApproved;
    if (visitTargets !== undefined)      data.visitTargets = visitTargets ? Number(visitTargets) : null;
    if (ratesPerVisit !== undefined)     data.ratesPerVisit = ratesPerVisit ? Number(ratesPerVisit) : null;
    if (basicSalary !== undefined)       data.basicSalary = basicSalary ? Number(basicSalary) : null;
    if (securityDepositPct !== undefined) data.securityDepositPct = securityDepositPct ? Number(securityDepositPct) : null;
    if (sampleBudget !== undefined)      data.sampleBudget = sampleBudget ? Number(sampleBudget) : 300000;
    if (profilePhoto !== undefined)      data.profilePhoto = profilePhoto ?? null;

    // Password reset by admin
    if (newPassword && typeof newPassword === "string" && newPassword.length >= 6) {
      data.password = await bcrypt.hash(newPassword, 10);
    }

    const updated = await prisma.booker.update({
      where: { id: parseInt(id) },
      data,
      select: { id: true, name: true, email: true, jobStatus: true },
    });

    return NextResponse.json({ success: true, data: updated });
  } catch (err) {
    console.error("[bookers PATCH id]", err);
    return NextResponse.json({ success: false, error: "Failed to update officer" }, { status: 500 });
  }
}

// DELETE /api/v1/bookers/[id] — soft delete
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
