import { NextResponse } from 'next/server';
import { prisma } from '@/lib/prisma';

const CACHE_TTL = 300000; // 5 minutes cache for app version
interface CachedVersion {
  timestamp: number;
  data: any;
}
const memoryCache = new Map<string, CachedVersion>();

/**
 * GET /api/v1/app-version
 * 
 * Returns the latest available app version and update information.
 * Query params:
 *   - current_version_code (optional): Current version code on device (e.g., 1)
 *   - platform (optional): "android" or "ios" (default: android)
 */
export async function GET(req: Request) {
  try {
    const { searchParams } = new URL(req.url);
    const currentVersionCode = parseInt(searchParams.get('current_version_code') || '0');
    const platform = searchParams.get('platform') || 'android';

    // Check cache first
    const cacheKey = `app-version:${platform}`;
    const cached = memoryCache.get(cacheKey);
    if (cached && Date.now() - cached.timestamp < CACHE_TTL) {
      return NextResponse.json({
        success: true,
        data: cached.data,
        cached: true,
        cacheAge: Math.round((Date.now() - cached.timestamp) / 1000),
      });
    }

    // Get latest app release
    const latestRelease = await prisma.appRelease.findFirst({
      orderBy: { versionCode: 'desc' },
      select: {
        id: true,
        versionCode: true,
        versionName: true,
        releaseDate: true,
        downloadUrl: true,
        releaseNotes: true,
        minVersionCode: true,
        isMandatory: true,
        changelogText: true,
      },
    });

    if (!latestRelease) {
      return NextResponse.json({
        success: true,
        data: {
          updateAvailable: false,
          current: {
            versionCode: currentVersionCode,
          },
        },
      });
    }

    // Determine if update is available and mandatory
    const updateAvailable = latestRelease.versionCode > currentVersionCode;
    const forceUpdate = updateAvailable && latestRelease.isMandatory;

    const responseData = {
      updateAvailable,
      forceUpdate,
      current: {
        versionCode: currentVersionCode,
      },
      latest: {
        versionCode: latestRelease.versionCode,
        versionName: latestRelease.versionName,
        releaseDate: latestRelease.releaseDate,
        downloadUrl: latestRelease.downloadUrl,
        releaseNotes: latestRelease.releaseNotes,
        changelogText: latestRelease.changelogText,
        minVersionCode: latestRelease.minVersionCode,
      },
    };

    // Cache the result
    memoryCache.set(cacheKey, {
      timestamp: Date.now(),
      data: responseData,
    });

    return NextResponse.json({
      success: true,
      data: responseData,
      cached: false,
    });
  } catch (error: any) {
    console.error('[api/v1/app-version]', error);
    return NextResponse.json(
      {
        success: false,
        error: {
          code: 'VERSION_CHECK_ERROR',
          message: error.message || 'Failed to check app version',
        },
      },
      { status: 500 }
    );
  }
}

/**
 * POST /api/v1/app-version
 * 
 * Create a new app release
 * Body:
 *   - versionCode: number
 *   - versionName: string (e.g., "1.0.0")
 *   - downloadUrl: string
 *   - releaseNotes: string
 *   - isMandatory: boolean (default: false)
 */
export async function POST(req: Request) {
  try {
    const body = await req.json();
    const { versionCode, versionName, downloadUrl, releaseNotes, isMandatory = false } = body;

    if (!versionCode || !versionName || !downloadUrl) {
      return NextResponse.json(
        {
          success: false,
          error: {
            code: 'MISSING_FIELDS',
            message: 'versionCode, versionName, and downloadUrl are required',
          },
        },
        { status: 400 }
      );
    }

    // Check if version already exists
    const existing = await prisma.appRelease.findUnique({
      where: { versionCode },
    });

    if (existing) {
      return NextResponse.json(
        {
          success: false,
          error: {
            code: 'VERSION_EXISTS',
            message: `Version code ${versionCode} already exists`,
          },
        },
        { status: 409 }
      );
    }

    // Create new release
    const release = await prisma.appRelease.create({
      data: {
        versionCode,
        versionName,
        downloadUrl,
        releaseNotes: releaseNotes || '',
        isMandatory,
        releaseDate: new Date(),
      },
    });

    // Clear cache
    memoryCache.clear();

    return NextResponse.json(
      {
        success: true,
        data: {
          id: release.id,
          versionCode: release.versionCode,
          versionName: release.versionName,
          releaseDate: release.releaseDate,
          downloadUrl: release.downloadUrl,
          releaseNotes: release.releaseNotes,
          isMandatory: release.isMandatory,
        },
      },
      { status: 201 }
    );
  } catch (error: any) {
    console.error('[POST api/v1/app-version]', error);
    return NextResponse.json(
      {
        success: false,
        error: {
          code: 'CREATE_VERSION_ERROR',
          message: error.message || 'Failed to create app release',
        },
      },
      { status: 500 }
    );
  }
}

// Cleanup old cache entries every minute
setInterval(() => {
  const now = Date.now();
  for (const [key, value] of memoryCache.entries()) {
    if (now - value.timestamp > CACHE_TTL) {
      memoryCache.delete(key);
    }
  }
}, 60000);
