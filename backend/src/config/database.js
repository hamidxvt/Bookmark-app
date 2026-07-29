import { PrismaClient } from '@prisma/client';
import logger from '../utils/logger.js';

const prisma = new PrismaClient({
  log: process.env.NODE_ENV === 'development'
    ? [{ emit: 'event', level: 'query' }, { emit: 'event', level: 'error' }]
    : [{ emit: 'event', level: 'error' }],
});

if (process.env.NODE_ENV === 'development') {
  prisma.$on('query', (e) => {
    if (e.duration > 200) logger.warn(`Slow query (${e.duration}ms): ${e.query}`);
  });
}

prisma.$on('error', (e) => logger.error(`Prisma error: ${e.message}`));

export default prisma;
