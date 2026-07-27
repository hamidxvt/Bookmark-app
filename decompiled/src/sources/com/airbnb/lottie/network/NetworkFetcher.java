package com.airbnb.lottie.network;

import android.content.Context;
import android.util.Pair;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieResult;
import com.airbnb.lottie.utils.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

/* loaded from: classes.dex */
public class NetworkFetcher {
    private final LottieNetworkFetcher fetcher;
    private final NetworkCache networkCache;

    public NetworkFetcher(NetworkCache networkCache, LottieNetworkFetcher fetcher) {
        this.networkCache = networkCache;
        this.fetcher = fetcher;
    }

    public LottieResult<LottieComposition> fetchSync(Context context, String url, String cacheKey) {
        LottieComposition result = fetchFromCache(context, url, cacheKey);
        if (result != null) {
            return new LottieResult<>(result);
        }
        Logger.debug("Animation for " + url + " not found in cache. Fetching from network.");
        return fetchFromNetwork(context, url, cacheKey);
    }

    private LottieComposition fetchFromCache(Context context, String url, String cacheKey) {
        Pair<FileExtension, InputStream> cacheResult;
        LottieResult<LottieComposition> result;
        if (cacheKey == null || this.networkCache == null || (cacheResult = this.networkCache.fetch(url)) == null) {
            return null;
        }
        FileExtension extension = (FileExtension) cacheResult.first;
        InputStream inputStream = (InputStream) cacheResult.second;
        switch (extension) {
            case ZIP:
                result = LottieCompositionFactory.fromZipStreamSync(context, new ZipInputStream(inputStream), cacheKey);
                break;
            case GZIP:
                try {
                    result = LottieCompositionFactory.fromJsonInputStreamSync(new GZIPInputStream(inputStream), cacheKey);
                    break;
                } catch (IOException e) {
                    result = new LottieResult<>(e);
                    break;
                }
            default:
                result = LottieCompositionFactory.fromJsonInputStreamSync(inputStream, cacheKey);
                break;
        }
        if (result.getValue() == null) {
            return null;
        }
        return result.getValue();
    }

    private LottieResult<LottieComposition> fetchFromNetwork(Context context, String url, String cacheKey) {
        Logger.debug("Fetching " + url);
        LottieFetchResult fetchResult = null;
        try {
            try {
                LottieFetchResult fetchResult2 = this.fetcher.fetchSync(url);
                if (!fetchResult2.isSuccessful()) {
                    LottieResult<LottieComposition> lottieResult = new LottieResult<>(new IllegalArgumentException(fetchResult2.error()));
                    if (fetchResult2 != null) {
                        try {
                            fetchResult2.close();
                        } catch (IOException e) {
                            Logger.warning("LottieFetchResult close failed ", e);
                        }
                    }
                    return lottieResult;
                }
                InputStream inputStream = fetchResult2.bodyByteStream();
                String contentType = fetchResult2.contentType();
                LottieResult<LottieComposition> result = fromInputStream(context, url, inputStream, contentType, cacheKey);
                Logger.debug("Completed fetch from network. Success: " + (result.getValue() != null));
                if (fetchResult2 != null) {
                    try {
                        fetchResult2.close();
                    } catch (IOException e2) {
                        Logger.warning("LottieFetchResult close failed ", e2);
                    }
                }
                return result;
            } catch (Exception e3) {
                LottieResult<LottieComposition> lottieResult2 = new LottieResult<>(e3);
                if (0 != 0) {
                    try {
                        fetchResult.close();
                    } catch (IOException e4) {
                        Logger.warning("LottieFetchResult close failed ", e4);
                    }
                }
                return lottieResult2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    fetchResult.close();
                } catch (IOException e5) {
                    Logger.warning("LottieFetchResult close failed ", e5);
                }
            }
            throw th;
        }
    }

    private LottieResult<LottieComposition> fromInputStream(Context context, String url, InputStream inputStream, String contentType, String cacheKey) throws IOException {
        FileExtension extension;
        LottieResult<LottieComposition> result;
        if (contentType == null) {
            contentType = "application/json";
        }
        if (contentType.contains("application/zip") || contentType.contains("application/x-zip") || contentType.contains("application/x-zip-compressed") || url.split("\\?")[0].endsWith(".lottie")) {
            Logger.debug("Handling zip response.");
            extension = FileExtension.ZIP;
            result = fromZipStream(context, url, inputStream, cacheKey);
        } else if (contentType.contains("application/gzip") || contentType.contains("application/x-gzip") || url.split("\\?")[0].endsWith(".tgs")) {
            Logger.debug("Handling gzip response.");
            extension = FileExtension.GZIP;
            result = fromGzipStream(url, inputStream, cacheKey);
        } else {
            Logger.debug("Received json response.");
            extension = FileExtension.JSON;
            result = fromJsonStream(url, inputStream, cacheKey);
        }
        if (cacheKey != null && result.getValue() != null && this.networkCache != null) {
            this.networkCache.renameTempFile(url, extension);
        }
        return result;
    }

    private LottieResult<LottieComposition> fromZipStream(Context context, String url, InputStream inputStream, String cacheKey) throws IOException {
        if (cacheKey == null || this.networkCache == null) {
            return LottieCompositionFactory.fromZipStreamSync(context, new ZipInputStream(inputStream), (String) null);
        }
        File file = this.networkCache.writeTempCacheFile(url, inputStream, FileExtension.ZIP);
        return LottieCompositionFactory.fromZipStreamSync(context, new ZipInputStream(new FileInputStream(file)), url);
    }

    private LottieResult<LottieComposition> fromGzipStream(String url, InputStream inputStream, String cacheKey) throws IOException {
        if (cacheKey == null || this.networkCache == null) {
            return LottieCompositionFactory.fromJsonInputStreamSync(new GZIPInputStream(inputStream), null);
        }
        File file = this.networkCache.writeTempCacheFile(url, inputStream, FileExtension.GZIP);
        return LottieCompositionFactory.fromJsonInputStreamSync(new GZIPInputStream(new FileInputStream(file)), url);
    }

    private LottieResult<LottieComposition> fromJsonStream(String url, InputStream inputStream, String cacheKey) throws IOException {
        if (cacheKey == null || this.networkCache == null) {
            return LottieCompositionFactory.fromJsonInputStreamSync(inputStream, null);
        }
        File file = this.networkCache.writeTempCacheFile(url, inputStream, FileExtension.JSON);
        return LottieCompositionFactory.fromJsonInputStreamSync(new FileInputStream(file.getAbsolutePath()), url);
    }
}
