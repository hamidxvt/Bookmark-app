import { prisma } from "../src/lib/prisma";

async function main() {
  console.log("🌱 Seeding 20 test locations (10 Quetta + 10 Karachi)...");

  const locations = [
    // Quetta locations (10)
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
      name: "Quetta - Arbab Khamoosh",
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
    {
      name: "Quetta - Zarghoon Road",
      latitude: 30.1945,
      longitude: 67.0189,
      geofenceRadius: 500,
    },
    {
      name: "Quetta - Jinnah Road",
      latitude: 30.1872,
      longitude: 67.0124,
      geofenceRadius: 500,
    },
    {
      name: "Quetta - Model Town",
      latitude: 30.1650,
      longitude: 67.0321,
      geofenceRadius: 500,
    },
    {
      name: "Quetta - Hazara Town",
      latitude: 30.1412,
      longitude: 66.9680,
      geofenceRadius: 500,
    },
    {
      name: "Quetta - Samungli Road",
      latitude: 30.2240,
      longitude: 67.0015,
      geofenceRadius: 500,
    },
    // Karachi locations (10)
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
    {
      name: "Karachi - North Nazimabad",
      latitude: 24.9372,
      longitude: 67.0423,
      geofenceRadius: 500,
    },
    {
      name: "Karachi - PECHS",
      latitude: 24.8686,
      longitude: 67.0620,
      geofenceRadius: 500,
    },
    {
      name: "Karachi - Bahadurabad",
      latitude: 24.8820,
      longitude: 67.0678,
      geofenceRadius: 500,
    },
    {
      name: "Karachi - Tariq Road",
      latitude: 24.8715,
      longitude: 67.0599,
      geofenceRadius: 500,
    },
    {
      name: "Karachi - Malir Cantonment",
      latitude: 24.9080,
      longitude: 67.2021,
      geofenceRadius: 500,
    },
  ];

  let created = 0;

  for (const loc of locations) {
    const existing = await prisma.city.findFirst({
      where: { name: loc.name },
    });

    if (existing) {
      console.log(`⏭️  ${loc.name}`);
    } else {
      await prisma.city.create({
        data: {
          name: loc.name,
          latitude: loc.latitude,
          longitude: loc.longitude,
          geofenceRadius: loc.geofenceRadius,
        },
      });
      console.log(`✅ ${loc.name}`);
      created++;
    }
  }

  console.log(`\n🎉 Done! Created ${created} new locations (10 Quetta + 10 Karachi = 20 total)`);
}

main()
  .catch((e) => {
    console.error("❌ Seed error:", e);
    process.exit(1);
  })
  .finally(async () => {
    await prisma.$disconnect();
  });
