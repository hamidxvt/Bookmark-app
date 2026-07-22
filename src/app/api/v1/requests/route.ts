import { NextResponse } from "next/server";
import { getRequests } from "@/lib/staging";

export async function GET() {
  try {
    const data = await getRequests({ length: 100 });
    return NextResponse.json({ success: true, data });
  } catch (err) {
    console.error("[api/requests]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch requests" }, { status: 500 });
  }
}
