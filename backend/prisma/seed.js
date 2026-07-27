import prisma from '../config/database.js';
import bcrypt from 'bcryptjs';

async function main() {
  console.log('Seeding database...');

  const city = await prisma.city.upsert({
    where: { id: 1 },
    update: {},
    create: { id: 1, name: 'Karachi' },
  });

  const area = await prisma.area.upsert({
    where: { id: 1 },
    update: {},
    create: { id: 1, name: 'South', cityId: city.id },
  });

  const adminPass = await bcrypt.hash('Admin@123', 12);
  const admin = await prisma.user.upsert({
    where: { email: 'admin@bookmark.pk' },
    update: {},
    create: {
      name: 'Super Admin', email: 'admin@bookmark.pk', password: adminPass,
      role: 'super_admin', cityId: city.id,
    },
  });

  const officerPass = await bcrypt.hash('Officer@123', 12);
  await prisma.user.upsert({
    where: { email: 'officer@bookmark.pk' },
    update: {},
    create: {
      name: 'Test Officer', email: 'officer@bookmark.pk', password: officerPass,
      role: 'sales_officer', cityId: city.id, areaId: area.id,
      basicSalary: 30000, dailyPerformanceRate: 3000, annualSampleLimitPkr: 50000,
    },
  });

  await prisma.product.createMany({
    skipDuplicates: true,
    data: [
      { id: 1, name: 'English Grammar Grade 5', pricePkr: 500 },
      { id: 2, name: 'Science Workbook Grade 8', pricePkr: 750 },
      { id: 3, name: 'Mathematics Activity Book', pricePkr: 600 },
    ],
  });

  await prisma.location.createMany({
    skipDuplicates: true,
    data: [
      { name: 'City Grammar School', type: 'school', areaId: area.id, latitude: 24.8607, longitude: 67.0011, priority: 'high' },
      { name: 'Sindh Book Depot', type: 'bookshop', areaId: area.id, latitude: 24.8550, longitude: 67.0100, priority: 'medium' },
    ],
  });

  console.log('Seed complete. Login: admin@bookmark.pk / Admin@123 | officer@bookmark.pk / Officer@123');
}

main().catch(console.error).finally(() => prisma.$disconnect());
