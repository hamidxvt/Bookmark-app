import { prisma } from "../src/lib/prisma";

async function main() {
  console.log("🌱 Seeding test locations...");

  const locations = [
    // Quetta locations (5)
    {
      name: "Quetta - Cantonment",
      latitude: 30.1798,
      longitude: 67.0064,
      geofenceRadius: 500,
      city: "Quetta",
    },
    {
      name: "Quetta - Aabadgar",
      latitude: 30.2106,
      longitude: 67.0281,
      geofenceRadius: 500,
      city: "Quetta",
    },
    {
      name: "Quetta - Satellite Town",
      latitude: 30.1524,
      longitude: 67.0457,
      geofenceRadius: 500,
      city: "Quetta",
    },
    {
      name: "Quetta - Arbab Khamoosh Road",
      latitude: 30.2094,
      longitude: 67.0102,
      geofenceRadius: 500,
      city: "Quetta",
    },
    {
      name: "Quetta - Alamdar Road",
      latitude: 30.1923,
      longitude: 67.0223,
      geofenceRadius: 500,
      city: "Quetta",
    },
    // Karachi locations (5)
    {
      name: "Karachi - Defence",
      latitude: 24.7936,
      longitude: 67.0521,
      geofenceRadius: 500,
      city: "Karachi",
    },
    {
      name: "Karachi - Clifton",
      latitude: 24.7786,
      longitude: 67.0301,
      geofenceRadius: 500,
      city: "Karachi",
    },
    {
      name: "Karachi - DHA",
      latitude: 24.8255,
      longitude: 67.0273,
      geofenceRadius: 500,
      city: "Karachi",
    },
    {
      name: "Karachi - Downtown",
      latitude: 24.8516,
      longitude: 67.0095,
      geofenceRadius: 500,
      city: "Karachi",
    },
    {
      name: "Karachi - Gulshan-e-Iqbal",
      latitude: 24.9253,
      longitude: 67.2406,
      geofenceRadius: 500,
      city: "Karachi",
    },
  ];

  let created = 0;

  for (const loc of locations) {
    const existing = await prisma.city.findFirst({
      where: { name: loc.name },
    });

    if (existing) {
      console.log(`⏭️  Skipping "${loc.name}" (already exists)`);
    } else {
      await prisma.city.create({
        data: {
          name: loc.name,
          latitude: loc.latitude,
          longitude: loc.longitude,
          geofenceRadius: loc.geofenceRadius,
        },
      });
      console.log(`✅ Created: ${loc.name}`);
      created++;
    }
  }

  console.log(`\n🎉 Seeding complete! Created ${created} new locations.`);
}

main()
  .catch((e) => {
    console.error("❌ Seed error:", e);
    process.exit(1);
  })
  .finally(async () => {
    await prisma.$disconnect();
  });
