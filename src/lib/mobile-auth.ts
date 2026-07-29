import jwt from "jsonwebtoken";

const JWT_SECRET = process.env.JWT_SECRET ?? process.env.NEXTAUTH_SECRET ?? "bookmark-sfa-secret";

export interface MobileUser {
  id: number;
  email: string;
  role: string;
}

export function getMobileUser(req: Request): MobileUser | null {
  try {
    const auth = req.headers.get("authorization") ?? "";
    const token = auth.startsWith("Bearer ") ? auth.slice(7) : null;
    if (!token) return null;
    const payload = jwt.verify(token, JWT_SECRET) as MobileUser;
    return payload;
  } catch {
    return null;
  }
}

export function unauthorized() {
  return Response.json({ success: false, error: "Unauthorized" }, { status: 401 });
}
