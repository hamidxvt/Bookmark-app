import { NextRequest, NextResponse } from 'next/server';
import { prisma } from '@/lib/prisma';

const ADMIN_SECRET = process.env.ADMIN_SECRET || 'admin-secret-key';

export async function POST(req: NextRequest) {
  try {
    const secret = req.headers.get('x-admin-secret');
    
    if (secret !== ADMIN_SECRET) {
      return NextResponse.json(
        { success: false, error: 'Unauthorized' },
        { status: 401 }
      );
    }

    const locations = [
      // Quetta locations (5)
      {
        name: "Quetta - Cantonment",
        latitude: 30.1798,
        longitude: 67.0064,
        geofenceRadius: 500,
      },
      {
        name: "Quetta - Aabadgar",
        latitude: 30.2106,
        longitude: 67.0281,
        geofenceRadius: 500,
      },
      {
        name: "Quetta - Satellite Town",
        latitude: 30.1524,
        longitude: 67.0457,
        geofenceRadius: 500,
      },
      {
        name: "Quetta - Arbab Khamoosh Road",
        latitude: 30.2094,
        longitude: 67.0102,
        geofenceRadius: 500,
      },
      {
        name: "Quetta - Alamdar Road",
        latitude: 30.1923,
        longitude: 67.0223,
        geofenceRadius: 500,
      },
      // Karachi locations (5)
      {
        name: "Karachi - Defence",
        latitude: 24.7936,
        longitude: 67.0521,
        geofenceRadius: 500,
      },
      {
        name: "Karachi - Clifton",
        latitude: 24.7786,
        longitude: 67.0301,
        geofenceRadius: 500,
      },
      {
        name: "Karachi - DHA",
        latitude: 24.8255,
        longitude: 67.0273,
        geofenceRadius: 500,
      },
      {
        name: "Karachi - Downtown",
        latitude: 24.8516,
        longitude: 67.0095,
        geofenceRadius: 500,
      },
      {
        name: "Karachi - Gulshan-e-Iqbal",
        latitude: 24.9253,
        longitude: 67.2406,
        geofenceRadius: 500,
      },
    ];

    const results = [];
    let created = 0;
    let skipped = 0;

    for (const loc of locations) {
      const existing = await prisma.city.findFirst({
        where: { name: loc.name },
      });

      if (existing) {
        results.push({ name: loc.name, status: 'skipped', reason: 'already exists' });
        skipped++;
      } else {
        const city = await prisma.city.create({
          data: {
            name: loc.name,
            latitude: loc.latitude,
            longitude: loc.longitude,
            geofenceRadius: loc.geofenceRadius,
          },
        });
        results.push({ name: loc.name, status: 'created', id: city.id });
        created++;
      }
    }

    return NextResponse.json({
      success: true,
      summary: {
        total: locations.length,
        created,
        skipped,
      },
      results,
      message: `✅ Seeded ${created} new locations, ${skipped} already existed`,
    });
  } catch (error: any) {
    console.error('[Seed API Error]', error);
    return NextResponse.json(
      {
        success: false,
        error: error.message || 'Seeding failed',
      },
      { status: 500 }
    );
  }
}
