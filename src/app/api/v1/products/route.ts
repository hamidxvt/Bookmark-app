import { NextRequest, NextResponse } from 'next/server';
import { prisma } from '@/lib/prisma';

const CACHE_KEY = 'products_cache';
const CACHE_TTL = 3600 * 1000; // 1 hour in milliseconds

interface CachedData {
  timestamp: number;
  data: any[];
}

// In-memory cache
const memoryCache = new Map<string, CachedData>();

export async function GET(req: NextRequest) {
  try {
    const { searchParams } = new URL(req.url);
    const type = searchParams.get('type') || 'products';
    const length = Math.min(parseInt(searchParams.get('length') || '200'), 500);
    const search = searchParams.get('search') || '';

    // Create cache key based on params
    const cacheKey = `${type}:${length}:${search}`;

    // Check memory cache first
    const cached = memoryCache.get(cacheKey);
    if (cached && Date.now() - cached.timestamp < CACHE_TTL) {
      return NextResponse.json({
        success: true,
        data: {
          data: cached.data,
          recordsTotal: cached.data.length,
          cached: true,
          cacheAge: Math.round((Date.now() - cached.timestamp) / 1000)
        }
      });
    }

    // Fetch from database
    const whereClause: any = {};
    if (search) {
      whereClause.OR = [
        { name: { contains: search, mode: 'insensitive' } },
        { isbn: { contains: search, mode: 'insensitive' } },
        { description: { contains: search, mode: 'insensitive' } }
      ];
    }

    const products = await prisma.product.findMany({
      where: whereClause,
      select: {
        id: true,
        name: true,
        isbn: true,
        grade: true,
        segment: true,
        description: true,
        retailPrice: true,
        isFeatured: true,
        image: true,
        brand: { select: { id: true, name: true } },
        series: { select: { id: true, name: true } },
        subject: { select: { id: true, name: true } }
      },
      take: length,
      orderBy: { createdAt: 'desc' }
    });

    // Format as array for legacy compatibility
    const formatted = products.map((p, idx) => [
      p.id,
      p.name,
      p.brand?.name || '',
      p.isbn || '',
      p.grade || '',
      p.retailPrice,
      p.isFeatured ? '✓' : '',
      p.image || ''
    ]);

    // Cache the result
    memoryCache.set(cacheKey, {
      timestamp: Date.now(),
      data: formatted
    });

    return NextResponse.json({
      success: true,
      data: {
        data: formatted,
        recordsTotal: formatted.length,
        cached: false
      }
    });

  } catch (error: any) {
    console.error('[Products API Error]', error);
    return NextResponse.json({
      success: false,
      error: {
        code: 'PRODUCTS_ERROR',
        message: error.message || 'Failed to fetch products'
      }
    }, { status: 500 });
  }
}

// Clear cache periodically
setInterval(() => {
  const now = Date.now();
  for (const [key, value] of memoryCache.entries()) {
    if (now - value.timestamp > CACHE_TTL) {
      memoryCache.delete(key);
    }
  }
}, 60000); // Check every minute
