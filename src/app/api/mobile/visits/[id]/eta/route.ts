import { NextResponse } from 'next/server';
import { prisma } from '@/lib/prisma';
import { getMobileUser, unauthorized } from '@/lib/mobile-auth';

export async function POST(req: Request, { params }: { params: Promise<{ id: string }> }) {
  const officer = getMobileUser(req);
  if (!officer) return unauthorized();

  try {
    const { id } = await params;
    const visitId = parseInt(id);
    const body = await req.json();

    const {
      customerName,
      eta_minutes,
      eta_walk_minutes,
      eta_timestamp,
      lat,
      lng,
      destination_lat,
      destination_lng,
      distance_km,
    } = body;

    const eta = await (prisma as any).visitETA.upsert({
      where: { visitId },
      create: {
        visitId,
        customerName: customerName ?? null,
        eta_minutes: eta_minutes ?? 0,
        eta_walk_minutes: eta_walk_minutes ?? null,
        eta_timestamp: eta_timestamp ? new Date(eta_timestamp) : null,
        officer_lat: lat ?? null,
        officer_lng: lng ?? null,
        destination_lat: destination_lat ?? null,
        destination_lng: destination_lng ?? null,
        distance_km: distance_km ?? null,
        navigating_since: new Date(),
      },
      update: {
        customerName: customerName ?? undefined,
        eta_minutes: eta_minutes ?? undefined,
        eta_walk_minutes: eta_walk_minutes ?? undefined,
        eta_timestamp: eta_timestamp ? new Date(eta_timestamp) : undefined,
        officer_lat: lat ?? undefined,
        officer_lng: lng ?? undefined,
        destination_lat: destination_lat ?? undefined,
        destination_lng: destination_lng ?? undefined,
        distance_km: distance_km ?? undefined,
      },
    });

    return NextResponse.json({ success: true, data: eta });
  } catch (err: any) {
    console.error('ETA error:', err);
    return NextResponse.json({ success: false, error: { message: err.message } }, { status: 500 });
  }
}
