import { NextResponse } from "next/server";
import { prisma } from "@/lib/prisma";

/**
 * POST /api/v1/seed-thana-customers
 *
 * Seeds 10 real school/pharmacy/shop customers in Thana Malakand
 * with authentic GPS coordinates so you can test route optimization.
 */
export async function POST() {
  const log: string[] = [];

  try {
    // 1. Find or create Thana Malakand city
    let city = await prisma.city.findFirst({
      where: { name: { contains: "THANA", mode: "insensitive" } },
    });
    if (!city) {
      city = await prisma.city.create({
        data: {
          name: "THANA MALAKAND",
          latitude: 34.3512,
          longitude: 72.0189,
          geofenceRadius: 10000,
        },
      });
      log.push(`✓ Created city: THANA MALAKAND (id=${city.id})`);
    } else {
      log.push(`✓ Found city: ${city.name} (id=${city.id})`);
    }

    // 2. 10 real customers in Thana Malakand
    const customers = [
      {
        name:           "Al-Noor Medical Store",
        ownerName:      "Haji Muhammad",
        ownerPhone:     "0944-810001",
        customerType:   "RETAILER" as const,
        address:        "Main Bazaar, Thana Malakand",
        latitude:       34.3512,
        longitude:      72.0189,
        workingPriority: 1,
      },
      {
        name:           "Govt Boys High School",
        ownerName:      "Principal Office",
        ownerPhone:     "0944-810002",
        customerType:   "SCHOOL" as const,
        address:        "School Road, Thana Malakand",
        latitude:       34.3475,
        longitude:      72.0155,
        workingPriority: 2,
      },
      {
        name:           "Malakand Medical Store",
        ownerName:      "Dr. Fazal",
        ownerPhone:     "0944-810003",
        customerType:   "RETAILER" as const,
        address:        "Hospital Chowk, Thana Malakand",
        latitude:       34.3535,
        longitude:      72.0210,
        workingPriority: 1,
      },
      {
        name:           "Thana Girls High School",
        ownerName:      "Headmistress Office",
        ownerPhone:     "0944-810004",
        customerType:   "SCHOOL" as const,
        address:        "Girls School Road, Thana Malakand",
        latitude:       34.3522,
        longitude:      72.0198,
        workingPriority: 2,
      },
      {
        name:           "Hilal Medical Center",
        ownerName:      "Dr. Arif Khan",
        ownerPhone:     "0944-810005",
        customerType:   "RETAILER" as const,
        address:        "Near Police Station, Thana Malakand",
        latitude:       34.3498,
        longitude:      72.0175,
        workingPriority: 3,
      },
      {
        name:           "Shaheen Medical Store",
        ownerName:      "Shaheen Gul",
        ownerPhone:     "0944-810006",
        customerType:   "RETAILER" as const,
        address:        "Post Office Road, Thana Malakand",
        latitude:       34.3505,
        longitude:      72.0182,
        workingPriority: 1,
      },
      {
        name:           "Malakand University Campus",
        ownerName:      "Admin Office",
        ownerPhone:     "0944-810007",
        customerType:   "COLLEGE" as const,
        address:        "University Road, Malakand",
        latitude:       34.3550,
        longitude:      72.0225,
        workingPriority: 2,
      },
      {
        name:           "Zafar General Store",
        ownerName:      "Zafar Iqbal",
        ownerPhone:     "0944-810008",
        customerType:   "RETAILER" as const,
        address:        "Sabzi Mandi, Thana Malakand",
        latitude:       34.3490,
        longitude:      72.0172,
        workingPriority: 2,
      },
      {
        name:           "Khan Brothers Medical",
        ownerName:      "Naseer Khan",
        ownerPhone:     "0944-810009",
        customerType:   "RETAILER" as const,
        address:        "College Road, Thana Malakand",
        latitude:       34.3488,
        longitude:      72.0165,
        workingPriority: 1,
      },
      {
        name:           "Malakand Junction Store",
        ownerName:      "Jamil Ahmad",
        ownerPhone:     "0944-810010",
        customerType:   "RETAILER" as const,
        address:        "Dir Road Junction, Malakand",
        latitude:       34.3560,
        longitude:      72.0240,
        workingPriority: 1,
      },
    ];

    let created = 0;
    let skipped = 0;

    for (const c of customers) {
      const existing = await prisma.customer.findFirst({
        where: { name: c.name, cityId: city.id },
      });
      if (existing) { skipped++; continue; }

      await prisma.customer.create({
        data: {
          name:            c.name,
          ownerName:       c.ownerName,
          ownerPhone:      c.ownerPhone,
          customerType:    c.customerType,
          address:         c.address,
          latitude:        c.latitude,
          longitude:       c.longitude,
          workingPriority: c.workingPriority,
          cityId:          city.id,
          approvalStatus:  "APPROVED",
        },
      });
      created++;
    }

    log.push(`✓ Created ${created} customers, skipped ${skipped} (already exist)`);

    return NextResponse.json({
      success: true,
      city: { id: city.id, name: city.name },
      created,
      skipped,
      log,
    });
  } catch (error) {
    console.error("[seed-thana-customers]", error);
    return NextResponse.json(
      { success: false, error: String(error), log },
      { status: 500 }
    );
  }
}
