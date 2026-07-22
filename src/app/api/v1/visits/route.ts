import { NextResponse } from "next/server";
import { getVisits, getTodayVisits } from "@/lib/staging";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const today = searchParams.get("today") === "1";
    const start = Number(searchParams.get("start") ?? 0);
    const length = Number(searchParams.get("length") ?? 50);
    const data = today ? await getTodayVisits() : await getVisits({ start, length });
    return NextResponse.json({ success: true, data });
  } catch (err) {
    console.error("[api/visits]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch visits" }, { status: 500 });
  }
}
