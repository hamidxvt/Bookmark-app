import { NextResponse } from "next/server";
import { getProducts, getSubjects, getSeries } from "@/lib/staging";

export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const type = searchParams.get("type") ?? "products";
    const start = Number(searchParams.get("start") ?? 0);
    const length = Number(searchParams.get("length") ?? 100);

    let data;
    if (type === "subjects") data = await getSubjects({ start, length });
    else if (type === "series") data = await getSeries({ start, length });
    else data = await getProducts({ start, length });

    return NextResponse.json({ success: true, data });
  } catch (err) {
    console.error("[api/products]", err);
    return NextResponse.json({ success: false, error: "Failed to fetch products" }, { status: 500 });
  }
}
