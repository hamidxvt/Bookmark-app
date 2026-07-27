package com.squareup.picasso;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/* loaded from: classes17.dex */
public final class OkHttp3Downloader implements Downloader {
    private final okhttp3.Cache cache;
    final Call.Factory client;
    private boolean sharedClient;

    public OkHttp3Downloader(Context context) {
        this(Utils.createDefaultCacheDir(context));
    }

    public OkHttp3Downloader(File cacheDir) {
        this(cacheDir, Utils.calculateDiskCacheSize(cacheDir));
    }

    public OkHttp3Downloader(Context context, long maxSize) {
        this(Utils.createDefaultCacheDir(context), maxSize);
    }

    public OkHttp3Downloader(File cacheDir, long maxSize) {
        this(new OkHttpClient.Builder().cache(new okhttp3.Cache(cacheDir, maxSize)).build());
        this.sharedClient = false;
    }

    public OkHttp3Downloader(OkHttpClient client) {
        this.sharedClient = true;
        this.client = client;
        this.cache = client.cache();
    }

    public OkHttp3Downloader(Call.Factory client) {
        this.sharedClient = true;
        this.client = client;
        this.cache = null;
    }

    @Override // com.squareup.picasso.Downloader
    public Response load(okhttp3.Request request) throws IOException {
        return this.client.newCall(request).execute();
    }

    @Override // com.squareup.picasso.Downloader
    public void shutdown() {
        if (!this.sharedClient && this.cache != null) {
            try {
                this.cache.close();
            } catch (IOException e) {
            }
        }
    }
}
