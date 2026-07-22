import { NextResponse } from "next/server";
import { getCustomers } from "@/lib/staging";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const start = Number(searchParams.get("start") ?? 0);
    const length = Number(searchParams.get("length") ?? 50);
    const data = await getCustomers({ start, length });
    return NextResponse.json({ success: true, data });
  } catch (err) {
    console.error("[api/customers]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch customers" }, { status: 500 });
  }
}
