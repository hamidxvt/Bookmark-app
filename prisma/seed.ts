import { PrismaClient } from '@prisma/client';

const prisma = new PrismaClient();

async function main() {
  console.log('🌱 Seeding database...');

  // Find or create cities
  let quetta = await prisma.city.findFirst({ where: { name: 'Quetta' } });
  if (!quetta) {
    quetta = await prisma.city.create({
      data: {
        name: 'Quetta',
        latitude: 30.1798,
        longitude: 66.9750,
        geofenceRadius: 5000,
      },
    });
    console.log('✓ Created Quetta city');
  }

  let karachi = await prisma.city.findFirst({ where: { name: 'Karachi' } });
  if (!karachi) {
    karachi = await prisma.city.create({
      data: {
        name: 'Karachi',
        latitude: 24.8607,
        longitude: 67.0011,
        geofenceRadius: 5000,
      },
    });
    console.log('✓ Created Karachi city');
  }

  // Sample customer data
  const quettaCustomers = [
    {
      name: 'Bookmark Store Quetta',
      address: 'Zarghoon Road, Quetta',
      type: 'A+',
      latitude: 30.1850,
      longitude: 66.9700,
      city: quetta.id,
    },
    {
      name: 'Education Hub Quetta',
      address: 'Liaquat Road, Quetta',
      type: 'A',
      latitude: 30.1800,
      longitude: 66.9800,
      city: quetta.id,
    },
    {
      name: 'Student Corner Quetta',
      address: 'Shahbaz Road, Quetta',
      type: 'B',
      latitude: 30.1750,
      longitude: 66.9650,
      city: quetta.id,
    },
    {
      name: 'Knowledge Shop Quetta',
      address: 'Jinnah Road, Quetta',
      type: 'A+',
      latitude: 30.1900,
      longitude: 66.9850,
      city: quetta.id,
    },
    {
      name: 'School Book Distributor Quetta',
      address: 'Zarghoon Avenue, Quetta',
      type: 'B',
      latitude: 30.1780,
      longitude: 66.9750,
      city: quetta.id,
    },
  ];

  const karachiCustomers = [
    {
      name: 'Bookmark Mega Store Karachi',
      address: 'Saddar, Karachi',
      type: 'A+',
      latitude: 24.8650,
      longitude: 67.0050,
      city: karachi.id,
    },
    {
      name: 'City Library Karachi',
      address: 'Clifton, Karachi',
      type: 'A',
      latitude: 24.7900,
      longitude: 67.0200,
      city: karachi.id,
    },
    {
      name: 'Educational Books Karachi',
      address: 'Defence, Karachi',
      type: 'B',
      latitude: 24.8400,
      longitude: 67.0300,
      city: karachi.id,
    },
    {
      name: 'Student Supply Hub Karachi',
      address: 'Gulshan-e-Iqbal, Karachi',
      type: 'A+',
      latitude: 24.9000,
      longitude: 67.0500,
      city: karachi.id,
    },
    {
      name: 'Academic Bookshop Karachi',
      address: 'F.B. Area, Karachi',
      type: 'B',
      latitude: 24.8500,
      longitude: 67.0100,
      city: karachi.id,
    },
  ];

  // Seed Quetta customers
  for (const customer of quettaCustomers) {
    const existing = await prisma.customer.findFirst({
      where: { name: customer.name },
    });

    if (!existing) {
      await prisma.customer.create({
        data: {
          name: customer.name,
          address: customer.address,
          customerType: customer.type,
          latitude: customer.latitude,
          longitude: customer.longitude,
          cityId: customer.city,
          approvalStatus: 'APPROVED',
          phone: `+92${Math.floor(Math.random() * 9000000000 + 1000000000)}`,
          email: `${customer.name.toLowerCase().replace(/\s+/g, '.')}@test.com`,
        },
      });
      console.log(`✓ Created customer: ${customer.name}`);
    }
  }

  // Seed Karachi customers
  for (const customer of karachiCustomers) {
    const existing = await prisma.customer.findFirst({
      where: { name: customer.name },
    });

    if (!existing) {
      await prisma.customer.create({
        data: {
          name: customer.name,
          address: customer.address,
          customerType: customer.type,
          latitude: customer.latitude,
          longitude: customer.longitude,
          cityId: customer.city,
          approvalStatus: 'APPROVED',
          phone: `+92${Math.floor(Math.random() * 9000000000 + 1000000000)}`,
          email: `${customer.name.toLowerCase().replace(/\s+/g, '.')}@test.com`,
        },
      });
      console.log(`✓ Created customer: ${customer.name}`);
    }
  }

  console.log('✅ Seeding completed!');
}

main()
  .catch((e) => {
    console.error('❌ Seed error:', e);
    process.exit(1);
  })
  .finally(async () => {
    await prisma.$disconnect();
  });
