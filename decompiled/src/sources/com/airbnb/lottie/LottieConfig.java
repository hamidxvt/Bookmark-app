package com.airbnb.lottie;

import com.airbnb.lottie.network.LottieNetworkCacheProvider;
import com.airbnb.lottie.network.LottieNetworkFetcher;
import java.io.File;

/* loaded from: classes.dex */
public class LottieConfig {
    final LottieNetworkCacheProvider cacheProvider;
    final AsyncUpdates defaultAsyncUpdates;
    final boolean disablePathInterpolatorCache;
    final boolean enableNetworkCache;
    final boolean enableSystraceMarkers;
    final LottieNetworkFetcher networkFetcher;

    private LottieConfig(LottieNetworkFetcher networkFetcher, LottieNetworkCacheProvider cacheProvider, boolean enableSystraceMarkers, boolean enableNetworkCache, boolean disablePathInterpolatorCache, AsyncUpdates defaultAsyncUpdates) {
        this.networkFetcher = networkFetcher;
        this.cacheProvider = cacheProvider;
        this.enableSystraceMarkers = enableSystraceMarkers;
        this.enableNetworkCache = enableNetworkCache;
        this.disablePathInterpolatorCache = disablePathInterpolatorCache;
        this.defaultAsyncUpdates = defaultAsyncUpdates;
    }

    public static final class Builder {
        private LottieNetworkCacheProvider cacheProvider;
        private LottieNetworkFetcher networkFetcher;
        private boolean enableSystraceMarkers = false;
        private boolean enableNetworkCache = true;
        private boolean disablePathInterpolatorCache = true;
        private AsyncUpdates defaultAsyncUpdates = AsyncUpdates.AUTOMATIC;

        public Builder setNetworkFetcher(LottieNetworkFetcher fetcher) {
            this.networkFetcher = fetcher;
            return this;
        }

        public Builder setNetworkCacheDir(final File file) {
            if (this.cacheProvider != null) {
                throw new IllegalStateException("There is already a cache provider!");
            }
            this.cacheProvider = new LottieNetworkCacheProvider() { // from class: com.airbnb.lottie.LottieConfig.Builder.1
                @Override // com.airbnb.lottie.network.LottieNetworkCacheProvider
                public File getCacheDir() {
                    if (!file.isDirectory()) {
                        throw new IllegalArgumentException("cache file must be a directory");
                    }
                    return file;
                }
            };
            return this;
        }

        public Builder setNetworkCacheProvider(final LottieNetworkCacheProvider fileCacheProvider) {
            if (this.cacheProvider != null) {
                throw new IllegalStateException("There is already a cache provider!");
            }
            this.cacheProvider = new LottieNetworkCacheProvider() { // from class: com.airbnb.lottie.LottieConfig.Builder.2
                @Override // com.airbnb.lottie.network.LottieNetworkCacheProvider
                public File getCacheDir() {
                    File file = fileCacheProvider.getCacheDir();
                    if (!file.isDirectory()) {
                        throw new IllegalArgumentException("cache file must be a directory");
                    }
                    return file;
                }
            };
            return this;
        }

        public Builder setEnableSystraceMarkers(boolean enable) {
            this.enableSystraceMarkers = enable;
            return this;
        }

        public Builder setEnableNetworkCache(boolean enable) {
            this.enableNetworkCache = enable;
            return this;
        }

        public Builder setDisablePathInterpolatorCache(boolean disable) {
            this.disablePathInterpolatorCache = disable;
            return this;
        }

        public Builder setDefaultAsyncUpdates(AsyncUpdates asyncUpdates) {
            this.defaultAsyncUpdates = asyncUpdates;
            return this;
        }

        public LottieConfig build() {
            return new LottieConfig(this.networkFetcher, this.cacheProvider, this.enableSystraceMarkers, this.enableNetworkCache, this.disablePathInterpolatorCache, this.defaultAsyncUpdates);
        }
    }
}
