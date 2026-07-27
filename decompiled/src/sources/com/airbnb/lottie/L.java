package com.airbnb.lottie;

import android.content.Context;
import com.airbnb.lottie.network.DefaultLottieNetworkFetcher;
import com.airbnb.lottie.network.LottieNetworkCacheProvider;
import com.airbnb.lottie.network.LottieNetworkFetcher;
import com.airbnb.lottie.network.NetworkCache;
import com.airbnb.lottie.network.NetworkFetcher;
import com.airbnb.lottie.utils.LottieTrace;
import java.io.File;

/* loaded from: classes.dex */
public class L {
    public static final String TAG = "LOTTIE";
    private static LottieNetworkCacheProvider cacheProvider;
    private static LottieNetworkFetcher fetcher;
    private static ThreadLocal<LottieTrace> lottieTrace;
    private static volatile NetworkCache networkCache;
    private static volatile NetworkFetcher networkFetcher;
    public static boolean DBG = false;
    private static boolean traceEnabled = false;
    private static boolean networkCacheEnabled = true;
    private static boolean disablePathInterpolatorCache = true;
    private static AsyncUpdates defaultAsyncUpdates = AsyncUpdates.AUTOMATIC;

    private L() {
    }

    public static void setTraceEnabled(boolean enabled) {
        if (traceEnabled == enabled) {
            return;
        }
        traceEnabled = enabled;
        if (traceEnabled && lottieTrace == null) {
            lottieTrace = new ThreadLocal<>();
        }
    }

    public static void setNetworkCacheEnabled(boolean enabled) {
        networkCacheEnabled = enabled;
    }

    public static void beginSection(String section) {
        if (!traceEnabled) {
            return;
        }
        getTrace().beginSection(section);
    }

    public static float endSection(String section) {
        if (!traceEnabled) {
            return 0.0f;
        }
        return getTrace().endSection(section);
    }

    private static LottieTrace getTrace() {
        LottieTrace trace = lottieTrace.get();
        if (trace == null) {
            LottieTrace trace2 = new LottieTrace();
            lottieTrace.set(trace2);
            return trace2;
        }
        return trace;
    }

    public static void setFetcher(LottieNetworkFetcher customFetcher) {
        if (fetcher == null && customFetcher == null) {
            return;
        }
        if (fetcher != null && fetcher.equals(customFetcher)) {
            return;
        }
        fetcher = customFetcher;
        networkFetcher = null;
    }

    public static void setCacheProvider(LottieNetworkCacheProvider customProvider) {
        if (cacheProvider == null && customProvider == null) {
            return;
        }
        if (cacheProvider != null && cacheProvider.equals(customProvider)) {
            return;
        }
        cacheProvider = customProvider;
        networkCache = null;
    }

    public static NetworkFetcher networkFetcher(Context context) {
        NetworkFetcher local = networkFetcher;
        if (local == null) {
            synchronized (NetworkFetcher.class) {
                local = networkFetcher;
                if (local == null) {
                    NetworkFetcher networkFetcher2 = new NetworkFetcher(networkCache(context), fetcher != null ? fetcher : new DefaultLottieNetworkFetcher());
                    local = networkFetcher2;
                    networkFetcher = networkFetcher2;
                }
            }
        }
        return local;
    }

    public static NetworkCache networkCache(Context context) {
        if (!networkCacheEnabled) {
            return null;
        }
        final Context appContext = context.getApplicationContext();
        NetworkCache local = networkCache;
        if (local == null) {
            synchronized (NetworkCache.class) {
                local = networkCache;
                if (local == null) {
                    NetworkCache networkCache2 = new NetworkCache(cacheProvider != null ? cacheProvider : new LottieNetworkCacheProvider() { // from class: com.airbnb.lottie.L$$ExternalSyntheticLambda0
                        @Override // com.airbnb.lottie.network.LottieNetworkCacheProvider
                        public final File getCacheDir() {
                            return L.lambda$networkCache$0(appContext);
                        }
                    });
                    local = networkCache2;
                    networkCache = networkCache2;
                }
            }
        }
        return local;
    }

    static /* synthetic */ File lambda$networkCache$0(Context appContext) {
        return new File(appContext.getCacheDir(), "lottie_network_cache");
    }

    public static void setDisablePathInterpolatorCache(boolean disablePathInterpolatorCache2) {
        disablePathInterpolatorCache = disablePathInterpolatorCache2;
    }

    public static boolean getDisablePathInterpolatorCache() {
        return disablePathInterpolatorCache;
    }

    public static void setDefaultAsyncUpdates(AsyncUpdates asyncUpdates) {
        defaultAsyncUpdates = asyncUpdates;
    }

    public static AsyncUpdates getDefaultAsyncUpdates() {
        return defaultAsyncUpdates;
    }
}
