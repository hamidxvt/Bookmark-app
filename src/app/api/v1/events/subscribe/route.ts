import { NextRequest } from "next/server";
import { addSSEClient, getPendingCounts } from "@/lib/events";

export const dynamic = "force-dynamic";
export const runtime = "nodejs";

export async function GET(req: NextRequest) {
  const encoder = new TextEncoder();

  const stream = new ReadableStream({
    async start(controller) {
      let open = true;

      const send = (data: unknown) => {
        if (!open) return;
        try {
          controller.enqueue(encoder.encode(`data: ${JSON.stringify(data)}\n\n`));
        } catch {
          open = false;
        }
      };

      const remove = addSSEClient({
        send: (raw) => send(JSON.parse(raw)),
      });

      // Initial pending counts + connection ack
      try {
        const counts = await getPendingCounts();
        send({ kind: "connected", counts });
      } catch {
        send({ kind: "connected", counts: { leaves: 0, samples: 0, total: 0 } });
      }

      const heartbeat = setInterval(() => {
        if (!open) return;
        try {
          controller.enqueue(encoder.encode(": heartbeat\n\n"));
        } catch {
          open = false;
        }
      }, 30_000);

      req.signal.addEventListener("abort", () => {
        open = false;
        clearInterval(heartbeat);
        remove();
        try {
          controller.close();
        } catch {
          /* already closed */
        }
      });
    },
  });

  return new Response(stream, {
    headers: {
      "Content-Type": "text/event-stream",
      "Cache-Control": "no-cache, no-transform",
      Connection: "keep-alive",
      "X-Accel-Buffering": "no",
    },
  });
}
