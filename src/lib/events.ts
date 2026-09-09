import type { Prisma } from "@prisma/client";
import { prisma } from "@/lib/prisma";

export type EventType = "leave-request" | "sample-request" | "visit-complete" | "visit-adhoc";

export interface AdminEventPayload {
  id?: number;
  bookerId?: number;
  bookerName?: string;
  message?: string;
  [key: string]: unknown;
}

export interface BroadcastEvent {
  type: EventType;
  id: number;
  payload: AdminEventPayload;
  createdAt: string;
}

type SSEClient = {
  send: (data: string) => void;
};

const clients = new Set<SSEClient>();

export function addSSEClient(client: SSEClient) {
  clients.add(client);
  return () => clients.delete(client);
}

export function broadcastEvent(event: BroadcastEvent) {
  const data = JSON.stringify(event);
  for (const client of clients) {
    try {
      client.send(data);
    } catch {
      clients.delete(client);
    }
  }
}

export async function createEvent(type: EventType, payload: AdminEventPayload = {}) {
  const record = await prisma.event.create({
    data: { type, payload: payload as Prisma.InputJsonValue },
  });

  const event: BroadcastEvent = {
    type,
    id: record.id,
    payload,
    createdAt: record.createdAt.toISOString(),
  };
  broadcastEvent(event);
  return record;
}

export async function getPendingCounts() {
  const [leaves, samples] = await Promise.all([
    prisma.leaveRequest.count({ where: { status: "pending" } }),
    prisma.sampleRequest.count({ where: { status: "pending" } }),
  ]);
  return { leaves, samples, total: leaves + samples };
}
