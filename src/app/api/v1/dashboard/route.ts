import { NextResponse } from "next/server";
import { getDashboardStats } from "@/lib/staging";

export async function GET() {
  try {
    const stats = await getDashboardStats();
    return NextResponse.json({ success: true, data: stats });
  } catch (err) {
    console.error("[api/dashboard]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch stats" }, { status: 500 });
  }
}
