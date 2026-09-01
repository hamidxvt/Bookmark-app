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

// Cleanup old cache entries every minute
setInterval(() => {
  const now = Date.now();
  for (const [key, value] of memoryCache.entries()) {
    if (now - value.timestamp > CACHE_TTL) {
      memoryCache.delete(key);
    }
  }
}, 60000);
