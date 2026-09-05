import { NextResponse } from "next/server";
import type { NextRequest } from "next/server";
import { getToken } from "next-auth/jwt";

let _seeded = false;

export async function middleware(req: NextRequest) {
  // Seed admin user once per cold start (non-blocking)
  if (!_seeded) {
    _seeded = true;
    fetch(`${req.nextUrl.origin}/api/setup`).catch(() => {});
  }

  const { pathname } = req.nextUrl;

  // Allow public paths
  if (
    pathname.startsWith("/login") ||
    pathname.startsWith("/api/auth") ||
    pathname.startsWith("/api/setup") ||
    pathname.startsWith("/api/mobile") ||
    pathname.startsWith("/_next") ||
    pathname.startsWith("/favicon") ||
    /\.[a-zA-Z0-9]+$/.test(pathname) // static files served from /public (images, robots.txt, etc.)
  ) {
    return NextResponse.next();
  }

  // Allow v1 API without auth (used by admin dashboard frontend)
  if (pathname.startsWith("/api/v1")) {
    return NextResponse.next();
  }

  // Protect dashboard routes
  const token = await getToken({ req, secret: process.env.NEXTAUTH_SECRET ?? "dev-secret-change-me-in-prod-32chars" });
  if (!token) {
    const loginUrl = new URL("/login", req.url);
    loginUrl.searchParams.set("callbackUrl", req.url);
    return NextResponse.redirect(loginUrl);
  }

  return NextResponse.next();
}

export const config = {
  matcher: ["/((?!_next/static|_next/image|favicon.ico).*)"],
};
