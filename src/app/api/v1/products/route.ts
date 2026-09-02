import { NextRequest, NextResponse } from 'next/server';
import { getServerSession } from 'next-auth';
import { authOptions } from '@/lib/auth';
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
        data: cached.data,
        cached: true,
        cacheAge: Math.round((Date.now() - cached.timestamp) / 1000)
      });
    }

    // Handle different types: brands, subjects, series, products
    let data: any[] = [];

    if (type === 'brands') {
      data = await prisma.brand.findMany({
        select: { id: true, name: true },
        orderBy: { name: 'asc' }
      });
    } else if (type === 'subjects') {
      data = await prisma.subject.findMany({
        select: { id: true, name: true },
        orderBy: { name: 'asc' }
      });
    } else if (type === 'series') {
      data = await prisma.series.findMany({
        select: { id: true, name: true },
        orderBy: { name: 'asc' }
      });
    } else {
      // Fetch products
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
      data = products.map((p) => [
        p.id,
        p.name,
        p.brand?.name || '',
        p.isbn || '',
        p.grade || '',
        p.retailPrice,
        p.isFeatured ? '✓' : '',
        p.image || ''
      ]);
    }

    // Cache the result
    memoryCache.set(cacheKey, {
      timestamp: Date.now(),
      data
    });

    return NextResponse.json({
      success: true,
      data,
      cached: false
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

export async function POST(req: NextRequest) {
  const session = await getServerSession(authOptions);
  if (!session) {
    return NextResponse.json(
      { success: false, error: 'Unauthorized' },
      { status: 401 }
    );
  }

  try {
    const body = await req.json();
    const {
      brandId,
      subjectId,
      seriesId,
      name,
      isbn,
      segment,
      grade,
      description,
      retailPrice,
      image,
    } = body;

    if (!brandId || !name || retailPrice === undefined) {
      return NextResponse.json(
        { success: false, error: 'Missing required fields: brandId, name, retailPrice' },
        { status: 400 }
      );
    }

    const product = await prisma.product.create({
      data: {
        brandId,
        subjectId: subjectId || null,
        seriesId: seriesId || null,
        name,
        isbn: isbn || null,
        segment: segment || null,
        grade: grade || null,
        description: description || null,
        retailPrice: parseFloat(retailPrice),
        image: image || null,
      },
      select: {
        id: true,
        name: true,
        isbn: true,
        retailPrice: true,
        brand: { select: { id: true, name: true } },
      },
    });

    // Clear product cache
    memoryCache.delete('products:*');
    for (const key of memoryCache.keys()) {
      if (key.startsWith('products:')) memoryCache.delete(key);
    }

    return NextResponse.json({
      success: true,
      data: product,
    });
  } catch (error: any) {
    console.error('[Product Create Error]', error);
    return NextResponse.json(
      {
        success: false,
        error: {
          code: 'PRODUCT_CREATE_ERROR',
          message: error.message || 'Failed to create product',
        },
      },
      { status: 500 }
    );
  }
}