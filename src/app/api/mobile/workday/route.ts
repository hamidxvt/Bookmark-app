import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";
import { getMobileUser, unauthorized } from "@/lib/mobile-auth";

// POST /api/mobile/workday  { action: "start"|"end", lat, lng }
export async function POST(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const body = await req.json();
    const { action, lat, lng, cannotReason } = body;

    if (!["start", "end", "cannot_work"].includes(action)) {
      return NextResponse.json({ success: false, error: "Invalid action" }, { status: 400 });
    }

    const today = new Date();
    today.setHours(0, 0, 0, 0);

    // Find or create today's attendance record
    let attendance = await prisma.attendance.findUnique({
      where: { bookerId_date: { bookerId: user.id, date: today } },
    });

    if (action === "start") {
      if (attendance?.startAt) {
        return NextResponse.json({ success: false, error: "Day already started" }, { status: 409 });
      }
      attendance = await prisma.attendance.upsert({
        where: { bookerId_date: { bookerId: user.id, date: today } },
        create: {
          bookerId: user.id,
          date: today,
          startAt: new Date(),
          startLat: lat,
          startLng: lng,
          status: "present",
        },
        update: {
          startAt: new Date(),
          startLat: lat,
          startLng: lng,
          status: "present",
        },
      });

      // Update booker GPS status
      await prisma.booker.update({
        where: { id: user.id },
        data: { gpsStatus: "ACTIVE", lastLatitude: lat, lastLongitude: lng, lastSeenAt: new Date() },
      });

      return NextResponse.json({ success: true, data: attendance, message: "Day started successfully" });
    }

    if (action === "cannot_work") {
      attendance = await prisma.attendance.upsert({
        where: { bookerId_date: { bookerId: user.id, date: today } },
        create: {
          bookerId: user.id,
          date: today,
          status: "cannot_work",
          cannotReason: cannotReason ?? "No reason provided",
        },
        update: {
          status: "cannot_work",
          cannotReason: cannotReason ?? "No reason provided",
        },
      });
      return NextResponse.json({ success: true, data: attendance, message: "Cannot work declaration saved" });
    }

    // action === "end"
    if (!attendance?.startAt) {
      return NextResponse.json({ success: false, error: "Day not started yet" }, { status: 400 });
    }

    attendance = await prisma.attendance.update({
      where: { bookerId_date: { bookerId: user.id, date: today } },
      data: {
        endAt: new Date(),
        endLat: lat,
        endLng: lng,
      },
    });

    await prisma.booker.update({
      where: { id: user.id },
      data: { gpsStatus: "OFFLINE", lastLatitude: lat, lastLongitude: lng, lastSeenAt: new Date() },
    });

    return NextResponse.json({ success: true, data: attendance, message: "Day ended successfully" });
  } catch (err) {
    console.error("[mobile/workday]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}

// GET /api/mobile/workday — get today's attendance status
export async function GET(req: Request) {
  const user = getMobileUser(req);
  if (!user) return unauthorized();

  try {
    const today = new Date();
    today.setHours(0, 0, 0, 0);

    const attendance = await prisma.attendance.findUnique({
      where: { bookerId_date: { bookerId: user.id, date: today } },
    });

    return NextResponse.json({ success: true, data: attendance });
  } catch (err) {
    console.error("[mobile/workday GET]", err);
    return NextResponse.json({ success: false, error: "Server error" }, { status: 500 });
  }
}
