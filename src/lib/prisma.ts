// Prisma client – lazy-loaded so the app boots even without a running DB.
// Once PostgreSQL is connected, run `npx prisma generate` to get the full client.

let _prisma: any;

export function getPrisma() {
  if (!_prisma) {
    try {
      const { PrismaClient } = require("@prisma/client");
      _prisma = new PrismaClient({
        log: process.env.NODE_ENV === "development" ? ["error", "warn"] : ["error"],
      });
    } catch {
      console.warn("Prisma client not generated yet — run `npx prisma generate`");
      _prisma = null;
    }
  }
  return _prisma;
}

export const prisma = getPrisma();
