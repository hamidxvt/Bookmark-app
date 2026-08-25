import { NextRequest } from "next/server";
import { prisma } from "@/lib/prisma";

export const dynamic = "force-dynamic";
export const runtime = "nodejs";

async function getCounts() {
  const today = new Date(); today.setHours(0, 0, 0, 0);
  const tomorrow = new Date(today); tomorrow.setDate(tomorrow.getDate() + 1);

  const [leaves, requests] = await Promise.all([
    prisma.leaveRequest.count({ where: { status: "PENDING" } }),
    (prisma as any).request.count({ where: { status: "PENDING" } }),
  ]);

  return { leaves, requests, total: leaves + requests };
}

export async function GET(_req: NextRequest) {
  const encoder = new TextEncoder();

  const stream = new ReadableStream({
    async start(controller) {
      let open = true;

      const send = async () => {
        if (!open) return;
        try {
          const counts = await getCounts();
          const data = `data: ${JSON.stringify(counts)}\n\n`;
          controller.enqueue(encoder.encode(data));
        } catch {
          // DB error — skip this tick
        }
      };

      // Send immediately on connect
      await send();

      // Poll every 15 seconds
      const interval = setInterval(send, 15_000);

      // Cleanup when client disconnects
      _req.signal.addEventListener("abort", () => {
        open = false;
        clearInterval(interval);
        try { controller.close(); } catch { /* already closed */ }
      });
    },
  });

  return new Response(stream, {
    headers: {
      "Content-Type":  "text/event-stream",
      "Cache-Control": "no-cache, no-transform",
      "Connection":    "keep-alive",
      "X-Accel-Buffering": "no",
    },
  });
}
