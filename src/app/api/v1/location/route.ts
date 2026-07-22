import { NextResponse } from "next/server";
import { getBookerLocations } from "@/lib/staging";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const city = searchParams.get("city") ?? undefined;
    const data = await getBookerLocations(city);
    return NextResponse.json({ success: true, data });
  } catch (err) {
    console.error("[api/location]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch locations" }, { status: 500 });
  }
}
