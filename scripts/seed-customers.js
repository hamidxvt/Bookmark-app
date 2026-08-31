const { PrismaClient } = require('@prisma/client');

const prisma = new PrismaClient();

async function main() {
  console.log('🌱 Seeding 10 test customers...');

  try {
    // Find or create Quetta city
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
    } else {
      console.log('✓ Quetta city already exists');
    }

    // Find or create Karachi city
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
    } else {
      console.log('✓ Karachi city already exists');
    }

    // Quetta customers
    const quettaCustomers = [
      {
        name: 'Bookmark Store Quetta',
        address: 'Zarghoon Road, Quetta',
        type: 'A+',
        latitude: 30.1850,
        longitude: 66.9700,
      },
      {
        name: 'Education Hub Quetta',
        address: 'Liaquat Road, Quetta',
        type: 'A',
        latitude: 30.1800,
        longitude: 66.9800,
      },
      {
        name: 'Student Corner Quetta',
        address: 'Shahbaz Road, Quetta',
        type: 'B',
        latitude: 30.1750,
        longitude: 66.9650,
      },
      {
        name: 'Knowledge Shop Quetta',
        address: 'Jinnah Road, Quetta',
        type: 'A+',
        latitude: 30.1900,
        longitude: 66.9850,
      },
      {
        name: 'School Book Distributor Quetta',
        address: 'Zarghoon Avenue, Quetta',
        type: 'B',
        latitude: 30.1780,
        longitude: 66.9750,
      },
      {
        name: 'Academic Excellence Quetta',
        address: 'Arbab Road, Quetta',
        type: 'A',
        latitude: 30.1920,
        longitude: 66.9600,
      },
      {
        name: 'Books & More Quetta',
        address: 'Gulberg Road, Quetta',
        type: 'B',
        latitude: 30.1700,
        longitude: 66.9900,
      },
      {
        name: 'Star Education Quetta',
        address: 'Kuchlak Road, Quetta',
        type: 'A+',
        latitude: 30.1650,
        longitude: 66.9550,
      },
      {
        name: 'Premier Academy Store Quetta',
        address: 'Aabadgir Road, Quetta',
        type: 'A',
        latitude: 30.1950,
        longitude: 66.9750,
      },
      {
        name: 'Student Needs Quetta',
        address: 'Breweryroad, Quetta',
        type: 'B',
        latitude: 30.1820,
        longitude: 66.9820,
      },
    ];

    // Karachi customers
    const karachiCustomers = [
      {
        name: 'Bookmark Mega Store Karachi',
        address: 'Saddar, Karachi',
        type: 'A+',
        latitude: 24.8650,
        longitude: 67.0050,
      },
      {
        name: 'City Library Karachi',
        address: 'Clifton, Karachi',
        type: 'A',
        latitude: 24.7900,
        longitude: 67.0200,
      },
      {
        name: 'Educational Books Karachi',
        address: 'Defence, Karachi',
        type: 'B',
        latitude: 24.8400,
        longitude: 67.0300,
      },
      {
        name: 'Student Supply Hub Karachi',
        address: 'Gulshan-e-Iqbal, Karachi',
        type: 'A+',
        latitude: 24.9000,
        longitude: 67.0500,
      },
      {
        name: 'Academic Bookshop Karachi',
        address: 'F.B. Area, Karachi',
        type: 'B',
        latitude: 24.8500,
        longitude: 67.0100,
      },
      {
        name: 'Bright Future Store Karachi',
        address: 'North Nazimabad, Karachi',
        type: 'A+',
        latitude: 24.9200,
        longitude: 67.0150,
      },
      {
        name: 'Students Paradise Karachi',
        address: 'Gulsan-e-Hadeed, Karachi',
        type: 'B',
        latitude: 24.8800,
        longitude: 67.0400,
      },
      {
        name: 'Knowledge Center Karachi',
        address: 'Mohammadi, Karachi',
        type: 'A',
        latitude: 24.9100,
        longitude: 67.0250,
      },
      {
        name: 'Premier Books Karachi',
        address: 'Korangi, Karachi',
        type: 'A+',
        latitude: 24.8300,
        longitude: 67.0600,
      },
      {
        name: 'Learning Hub Karachi',
        address: 'Liaquatabad, Karachi',
        type: 'B',
        latitude: 24.8950,
        longitude: 67.0320,
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
            cityId: quetta.id,
            approvalStatus: 'APPROVED',
            phone: `+92${Math.floor(Math.random() * 9000000000 + 1000000000)}`,
            email: `${customer.name.toLowerCase().replace(/\s+/g, '.')}@test.com`,
          },
        });
        console.log(`✓ Created: ${customer.name}`);
      } else {
        console.log(`→ Skipped: ${customer.name} (already exists)`);
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
            cityId: karachi.id,
            approvalStatus: 'APPROVED',
            phone: `+92${Math.floor(Math.random() * 9000000000 + 1000000000)}`,
            email: `${customer.name.toLowerCase().replace(/\s+/g, '.')}@test.com`,
          },
        });
        console.log(`✓ Created: ${customer.name}`);
      } else {
        console.log(`→ Skipped: ${customer.name} (already exists)`);
      }
    }

    console.log('\n✅ Seeding completed! 20 test customers created (10 in Quetta + 10 in Karachi).');
  } catch (error) {
    console.error('❌ Error:', error.message);
    process.exit(1);
  } finally {
    await prisma.$disconnect();
  }
}

main();
