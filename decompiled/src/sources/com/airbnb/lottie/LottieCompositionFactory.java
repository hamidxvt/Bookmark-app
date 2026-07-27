package com.airbnb.lottie;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Base64;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.LottieCompositionCache;
import com.airbnb.lottie.network.NetworkCache;
import com.airbnb.lottie.parser.LottieCompositionMoshiParser;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.google.common.base.Ascii;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import okio.BufferedSource;
import okio.Okio;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class LottieCompositionFactory {
    private static final Map<String, LottieTask<LottieComposition>> taskCache = new HashMap();
    private static final Set<LottieTaskIdleListener> taskIdleListeners = new HashSet();
    private static final byte[] ZIP_MAGIC = {80, 75, 3, 4};
    private static final byte[] GZIP_MAGIC = {Ascii.US, -117, 8};

    private LottieCompositionFactory() {
    }

    public static void setMaxCacheSize(int size) {
        LottieCompositionCache.getInstance().resize(size);
    }

    public static void clearCache(Context context) {
        taskCache.clear();
        LottieCompositionCache.getInstance().clear();
        NetworkCache networkCache = L.networkCache(context);
        if (networkCache != null) {
            networkCache.clear();
        }
    }

    public static void registerLottieTaskIdleListener(LottieTaskIdleListener listener) {
        taskIdleListeners.add(listener);
        listener.onIdleChanged(taskCache.size() == 0);
    }

    public static void unregisterLottieTaskIdleListener(LottieTaskIdleListener listener) {
        taskIdleListeners.remove(listener);
    }

    public static LottieTask<LottieComposition> fromUrl(Context context, String url) {
        return fromUrl(context, url, "url_" + url);
    }

    public static LottieTask<LottieComposition> fromUrl(final Context context, final String url, final String cacheKey) {
        return cache(cacheKey, new Callable() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda9
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return LottieCompositionFactory.lambda$fromUrl$0(context, url, cacheKey);
            }
        }, null);
    }

    static /* synthetic */ LottieResult lambda$fromUrl$0(Context context, String url, String cacheKey) throws Exception {
        LottieResult<LottieComposition> result = L.networkFetcher(context).fetchSync(context, url, cacheKey);
        if (cacheKey != null && result.getValue() != null) {
            LottieCompositionCache.getInstance().put(cacheKey, result.getValue());
        }
        return result;
    }

    public static LottieResult<LottieComposition> fromUrlSync(Context context, String url) {
        return fromUrlSync(context, url, url);
    }

    public static LottieResult<LottieComposition> fromUrlSync(Context context, String url, String cacheKey) {
        LottieComposition cachedComposition = cacheKey == null ? null : LottieCompositionCache.getInstance().get(cacheKey);
        if (cachedComposition != null) {
            return new LottieResult<>(cachedComposition);
        }
        LottieResult<LottieComposition> result = L.networkFetcher(context).fetchSync(context, url, cacheKey);
        if (cacheKey != null && result.getValue() != null) {
            LottieCompositionCache.getInstance().put(cacheKey, result.getValue());
        }
        return result;
    }

    public static LottieTask<LottieComposition> fromAsset(Context context, String fileName) {
        String cacheKey = "asset_" + fileName;
        return fromAsset(context, fileName, cacheKey);
    }

    public static LottieTask<LottieComposition> fromAsset(Context context, final String fileName, final String cacheKey) {
        final Context appContext = context.getApplicationContext();
        return cache(cacheKey, new Callable() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LottieResult fromAssetSync;
                fromAssetSync = LottieCompositionFactory.fromAssetSync(appContext, fileName, cacheKey);
                return fromAssetSync;
            }
        }, null);
    }

    public static LottieResult<LottieComposition> fromAssetSync(Context context, String fileName) {
        String cacheKey = "asset_" + fileName;
        return fromAssetSync(context, fileName, cacheKey);
    }

    public static LottieResult<LottieComposition> fromAssetSync(Context context, String fileName, String cacheKey) {
        LottieComposition cachedComposition = cacheKey == null ? null : LottieCompositionCache.getInstance().get(cacheKey);
        if (cachedComposition != null) {
            return new LottieResult<>(cachedComposition);
        }
        try {
            BufferedSource source = Okio.buffer(Okio.source(context.getAssets().open(fileName)));
            if (isZipCompressed(source).booleanValue()) {
                return fromZipStreamSync(context, new ZipInputStream(source.inputStream()), cacheKey);
            }
            if (isGzipCompressed(source).booleanValue()) {
                return fromJsonInputStreamSync(new GZIPInputStream(source.inputStream()), cacheKey);
            }
            return fromJsonInputStreamSync(source.inputStream(), cacheKey);
        } catch (IOException e) {
            return new LottieResult<>((Throwable) e);
        }
    }

    public static LottieTask<LottieComposition> fromRawRes(Context context, int rawRes) {
        return fromRawRes(context, rawRes, rawResCacheKey(context, rawRes));
    }

    public static LottieTask<LottieComposition> fromRawRes(Context context, final int rawRes, final String cacheKey) {
        final WeakReference<Context> contextRef = new WeakReference<>(context);
        final Context appContext = context.getApplicationContext();
        return cache(cacheKey, new Callable() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda7
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return LottieCompositionFactory.lambda$fromRawRes$2(contextRef, appContext, rawRes, cacheKey);
            }
        }, null);
    }

    static /* synthetic */ LottieResult lambda$fromRawRes$2(WeakReference contextRef, Context appContext, int rawRes, String cacheKey) throws Exception {
        Context originalContext = (Context) contextRef.get();
        Context context1 = originalContext != null ? originalContext : appContext;
        return fromRawResSync(context1, rawRes, cacheKey);
    }

    public static LottieResult<LottieComposition> fromRawResSync(Context context, int rawRes) {
        return fromRawResSync(context, rawRes, rawResCacheKey(context, rawRes));
    }

    public static LottieResult<LottieComposition> fromRawResSync(Context context, int rawRes, String cacheKey) {
        LottieComposition cachedComposition = cacheKey == null ? null : LottieCompositionCache.getInstance().get(cacheKey);
        if (cachedComposition != null) {
            return new LottieResult<>(cachedComposition);
        }
        try {
            BufferedSource source = Okio.buffer(Okio.source(context.getResources().openRawResource(rawRes)));
            if (isZipCompressed(source).booleanValue()) {
                return fromZipStreamSync(context, new ZipInputStream(source.inputStream()), cacheKey);
            }
            if (isGzipCompressed(source).booleanValue()) {
                try {
                    return fromJsonInputStreamSync(new GZIPInputStream(source.inputStream()), cacheKey);
                } catch (IOException e) {
                    return new LottieResult<>((Throwable) e);
                }
            }
            return fromJsonInputStreamSync(source.inputStream(), cacheKey);
        } catch (Resources.NotFoundException e2) {
            return new LottieResult<>((Throwable) e2);
        }
    }

    private static String rawResCacheKey(Context context, int resId) {
        return "rawRes" + (isNightMode(context) ? "_night_" : "_day_") + resId;
    }

    private static boolean isNightMode(Context context) {
        int nightModeMasked = context.getResources().getConfiguration().uiMode & 48;
        return nightModeMasked == 32;
    }

    public static LottieTask<LottieComposition> fromJsonInputStream(final InputStream stream, final String cacheKey) {
        return cache(cacheKey, new Callable() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda10
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LottieResult fromJsonInputStreamSync;
                fromJsonInputStreamSync = LottieCompositionFactory.fromJsonInputStreamSync(stream, cacheKey);
                return fromJsonInputStreamSync;
            }
        }, new Runnable() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                Utils.closeQuietly(stream);
            }
        });
    }

    public static LottieTask<LottieComposition> fromJsonInputStream(final InputStream stream, final String cacheKey, final boolean close) {
        return cache(cacheKey, new Callable() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda13
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LottieResult fromJsonInputStreamSync;
                fromJsonInputStreamSync = LottieCompositionFactory.fromJsonInputStreamSync(stream, cacheKey, close);
                return fromJsonInputStreamSync;
            }
        }, new Runnable() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                LottieCompositionFactory.lambda$fromJsonInputStream$6(close, stream);
            }
        });
    }

    static /* synthetic */ void lambda$fromJsonInputStream$6(boolean close, InputStream stream) {
        if (close) {
            Utils.closeQuietly(stream);
        }
    }

    public static LottieResult<LottieComposition> fromJsonInputStreamSync(InputStream stream, String cacheKey) {
        return fromJsonInputStreamSync(stream, cacheKey, true);
    }

    public static LottieResult<LottieComposition> fromJsonInputStreamSync(InputStream stream, String cacheKey, boolean close) {
        return fromJsonReaderSync(JsonReader.of(Okio.buffer(Okio.source(stream))), cacheKey, close);
    }

    @Deprecated
    public static LottieTask<LottieComposition> fromJson(final JSONObject json, final String cacheKey) {
        return cache(cacheKey, new Callable() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda12
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LottieResult fromJsonSync;
                fromJsonSync = LottieCompositionFactory.fromJsonSync(json, cacheKey);
                return fromJsonSync;
            }
        }, null);
    }

    @Deprecated
    public static LottieResult<LottieComposition> fromJsonSync(JSONObject json, String cacheKey) {
        return fromJsonStringSync(json.toString(), cacheKey);
    }

    public static LottieTask<LottieComposition> fromJsonString(final String json, final String cacheKey) {
        return cache(cacheKey, new Callable() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda15
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LottieResult fromJsonStringSync;
                fromJsonStringSync = LottieCompositionFactory.fromJsonStringSync(json, cacheKey);
                return fromJsonStringSync;
            }
        }, null);
    }

    public static LottieResult<LottieComposition> fromJsonStringSync(String json, String cacheKey) {
        ByteArrayInputStream stream = new ByteArrayInputStream(json.getBytes());
        return fromJsonReaderSync(JsonReader.of(Okio.buffer(Okio.source(stream))), cacheKey);
    }

    public static LottieTask<LottieComposition> fromJsonReader(final JsonReader reader, final String cacheKey) {
        return cache(cacheKey, new Callable() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda16
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LottieResult fromJsonReaderSync;
                fromJsonReaderSync = LottieCompositionFactory.fromJsonReaderSync(JsonReader.this, cacheKey);
                return fromJsonReaderSync;
            }
        }, new Runnable() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                Utils.closeQuietly(JsonReader.this);
            }
        });
    }

    public static LottieResult<LottieComposition> fromJsonReaderSync(JsonReader reader, String cacheKey) {
        return fromJsonReaderSync(reader, cacheKey, true);
    }

    public static LottieResult<LottieComposition> fromJsonReaderSync(JsonReader reader, String cacheKey, boolean close) {
        return fromJsonReaderSyncInternal(reader, cacheKey, close);
    }

    private static LottieResult<LottieComposition> fromJsonReaderSyncInternal(JsonReader reader, String cacheKey, boolean close) {
        LottieComposition cachedComposition;
        try {
            if (cacheKey == null) {
                cachedComposition = null;
            } else {
                try {
                    cachedComposition = LottieCompositionCache.getInstance().get(cacheKey);
                } catch (Exception e) {
                    LottieResult<LottieComposition> lottieResult = new LottieResult<>(e);
                    if (close) {
                        Utils.closeQuietly(reader);
                    }
                    return lottieResult;
                }
            }
            if (cachedComposition != null) {
                LottieResult<LottieComposition> lottieResult2 = new LottieResult<>(cachedComposition);
                if (close) {
                    Utils.closeQuietly(reader);
                }
                return lottieResult2;
            }
            LottieComposition composition = LottieCompositionMoshiParser.parse(reader);
            if (cacheKey != null) {
                LottieCompositionCache.getInstance().put(cacheKey, composition);
            }
            LottieResult<LottieComposition> lottieResult3 = new LottieResult<>(composition);
            if (close) {
                Utils.closeQuietly(reader);
            }
            return lottieResult3;
        } catch (Throwable th) {
            if (close) {
                Utils.closeQuietly(reader);
            }
            throw th;
        }
    }

    public static LottieTask<LottieComposition> fromZipStream(ZipInputStream inputStream, String cacheKey) {
        return fromZipStream((Context) null, inputStream, cacheKey);
    }

    public static LottieTask<LottieComposition> fromZipStream(ZipInputStream inputStream, String cacheKey, boolean close) {
        return fromZipStream(null, inputStream, cacheKey, close);
    }

    public static LottieTask<LottieComposition> fromZipStream(final Context context, final ZipInputStream inputStream, final String cacheKey) {
        return cache(cacheKey, new Callable() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda3
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LottieResult fromZipStreamSync;
                fromZipStreamSync = LottieCompositionFactory.fromZipStreamSync(context, inputStream, cacheKey);
                return fromZipStreamSync;
            }
        }, new Runnable() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                Utils.closeQuietly(inputStream);
            }
        });
    }

    public static LottieTask<LottieComposition> fromZipStream(final Context context, final ZipInputStream inputStream, final String cacheKey, boolean close) {
        return cache(cacheKey, new Callable() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LottieResult fromZipStreamSync;
                fromZipStreamSync = LottieCompositionFactory.fromZipStreamSync(context, inputStream, cacheKey);
                return fromZipStreamSync;
            }
        }, close ? new Runnable() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                Utils.closeQuietly(inputStream);
            }
        } : null);
    }

    public static LottieResult<LottieComposition> fromZipStreamSync(ZipInputStream inputStream, String cacheKey) {
        return fromZipStreamSync(inputStream, cacheKey, true);
    }

    public static LottieResult<LottieComposition> fromZipStreamSync(ZipInputStream inputStream, String cacheKey, boolean close) {
        return fromZipStreamSync(null, inputStream, cacheKey, close);
    }

    public static LottieResult<LottieComposition> fromZipStreamSync(Context context, ZipInputStream inputStream, String cacheKey) {
        return fromZipStreamSync(context, inputStream, cacheKey, true);
    }

    public static LottieResult<LottieComposition> fromZipStreamSync(Context context, ZipInputStream inputStream, String cacheKey, boolean close) {
        try {
            return fromZipStreamSyncInternal(context, inputStream, cacheKey);
        } finally {
            if (close) {
                Utils.closeQuietly(inputStream);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:80:0x015e A[Catch: IOException -> 0x019f, TryCatch #9 {IOException -> 0x019f, blocks: (B:17:0x0197, B:41:0x0187, B:78:0x0154, B:80:0x015e, B:81:0x017e, B:77:0x012e), top: B:40:0x0187 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static LottieResult<LottieComposition> fromZipStreamSyncInternal(Context context, ZipInputStream inputStream, String cacheKey) {
        LottieComposition lottieComposition;
        Throwable th;
        Map<String, Bitmap> images = new HashMap<>();
        Map<String, Typeface> fonts = new HashMap<>();
        String str = null;
        if (cacheKey != null) {
            try {
                lottieComposition = LottieCompositionCache.getInstance().get(cacheKey);
            } catch (IOException e) {
                e = e;
                return new LottieResult<>((Throwable) e);
            }
        } else {
            lottieComposition = null;
        }
        LottieComposition cachedComposition = lottieComposition;
        if (cachedComposition != null) {
            return new LottieResult<>(cachedComposition);
        }
        LottieComposition composition = null;
        ZipEntry entry = inputStream.getNextEntry();
        while (entry != null) {
            try {
                String entryName = entry.getName();
                if (entryName.contains("__MACOSX")) {
                    inputStream.closeEntry();
                } else if (entry.getName().equalsIgnoreCase("manifest.json")) {
                    inputStream.closeEntry();
                } else if (entry.getName().contains(".json")) {
                    JsonReader reader = JsonReader.of(Okio.buffer(Okio.source(inputStream)));
                    LottieComposition composition2 = fromJsonReaderSyncInternal(reader, str, false).getValue();
                    composition = composition2;
                } else {
                    if (!entryName.contains(".png") && !entryName.contains(".webp") && !entryName.contains(".jpg")) {
                        if (!entryName.contains(".jpeg")) {
                            if (!entryName.contains(".ttf") && !entryName.contains(".otf")) {
                                inputStream.closeEntry();
                            }
                            String[] splitName = entryName.split("/");
                            String fileName = splitName[splitName.length - 1];
                            String fontFamily = fileName.split("\\.")[0];
                            File tempFile = new File(context.getCacheDir(), fileName);
                            new FileOutputStream(tempFile);
                            try {
                                OutputStream output = new FileOutputStream(tempFile);
                                try {
                                    byte[] buffer = new byte[4096];
                                    while (true) {
                                        try {
                                            int read = inputStream.read(buffer);
                                            ZipEntry entry2 = entry;
                                            if (read == -1) {
                                                break;
                                            }
                                            try {
                                                output.write(buffer, 0, read);
                                                entry = entry2;
                                            } catch (Throwable th2) {
                                                th = th2;
                                                try {
                                                    output.close();
                                                } catch (Throwable th3) {
                                                    th.addSuppressed(th3);
                                                }
                                                throw th;
                                            }
                                        } catch (Throwable th4) {
                                            th = th4;
                                            th = th;
                                            output.close();
                                            throw th;
                                        }
                                    }
                                    output.flush();
                                    try {
                                        output.close();
                                    } catch (Throwable th5) {
                                        e = th5;
                                        Logger.warning("Unable to save font " + fontFamily + " to the temporary file: " + fileName + ". ", e);
                                        Typeface typeface = Typeface.createFromFile(tempFile);
                                        if (!tempFile.delete()) {
                                        }
                                        fonts.put(fontFamily, typeface);
                                        entry = inputStream.getNextEntry();
                                        str = null;
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                }
                            } catch (Throwable th7) {
                                e = th7;
                            }
                            Typeface typeface2 = Typeface.createFromFile(tempFile);
                            if (!tempFile.delete()) {
                                Logger.warning("Failed to delete temp font file " + tempFile.getAbsolutePath() + ".");
                            }
                            fonts.put(fontFamily, typeface2);
                        }
                    }
                    try {
                        String[] splitName2 = entryName.split("/");
                        String name = splitName2[splitName2.length - 1];
                        images.put(name, BitmapFactory.decodeStream(inputStream));
                    } catch (IOException e2) {
                        e = e2;
                        return new LottieResult<>((Throwable) e);
                    }
                }
                entry = inputStream.getNextEntry();
                str = null;
            } catch (IOException e3) {
                e = e3;
            }
        }
        if (composition == null) {
            return new LottieResult<>((Throwable) new IllegalArgumentException("Unable to parse composition"));
        }
        for (Map.Entry<String, Bitmap> e4 : images.entrySet()) {
            LottieImageAsset imageAsset = findImageAssetForFileName(composition, e4.getKey());
            if (imageAsset != null) {
                imageAsset.setBitmap(Utils.resizeBitmapIfNeeded(e4.getValue(), imageAsset.getWidth(), imageAsset.getHeight()));
            }
        }
        for (Map.Entry<String, Typeface> e5 : fonts.entrySet()) {
            boolean found = false;
            for (Font font : composition.getFonts().values()) {
                if (font.getFamily().equals(e5.getKey())) {
                    found = true;
                    font.setTypeface(e5.getValue());
                }
            }
            if (!found) {
                Logger.warning("Parsed font for " + e5.getKey() + " however it was not found in the animation.");
            }
        }
        if (images.isEmpty()) {
            for (Map.Entry<String, LottieImageAsset> entry3 : composition.getImages().entrySet()) {
                LottieImageAsset asset = entry3.getValue();
                if (asset == null) {
                    return null;
                }
                String filename = asset.getFileName();
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inScaled = true;
                opts.inDensity = 160;
                if (filename.startsWith("data:") && filename.indexOf("base64,") > 0) {
                    try {
                        byte[] data = Base64.decode(filename.substring(filename.indexOf(44) + 1), 0);
                        asset.setBitmap(BitmapFactory.decodeByteArray(data, 0, data.length, opts));
                    } catch (IllegalArgumentException e6) {
                        Logger.warning("data URL did not have correct base64 format.", e6);
                        return null;
                    }
                }
            }
        }
        if (cacheKey != null) {
            LottieCompositionCache.getInstance().put(cacheKey, composition);
        }
        return new LottieResult<>(composition);
    }

    private static Boolean isZipCompressed(BufferedSource inputSource) {
        return matchesMagicBytes(inputSource, ZIP_MAGIC);
    }

    private static Boolean isGzipCompressed(BufferedSource inputSource) {
        return matchesMagicBytes(inputSource, GZIP_MAGIC);
    }

    private static Boolean matchesMagicBytes(BufferedSource inputSource, byte[] magic) {
        try {
            BufferedSource peek = inputSource.peek();
            for (byte b : magic) {
                if (peek.readByte() != b) {
                    return false;
                }
            }
            peek.close();
            return true;
        } catch (Exception e) {
            Logger.error("Failed to check zip file header", e);
            return false;
        } catch (NoSuchMethodError e2) {
            return false;
        }
    }

    private static LottieImageAsset findImageAssetForFileName(LottieComposition composition, String fileName) {
        for (LottieImageAsset asset : composition.getImages().values()) {
            if (asset.getFileName().equals(fileName)) {
                return asset;
            }
        }
        return null;
    }

    private static LottieTask<LottieComposition> cache(final String cacheKey, Callable<LottieResult<LottieComposition>> callable, Runnable onCached) {
        LottieTask<LottieComposition> task = null;
        LottieComposition cachedComposition = cacheKey == null ? null : LottieCompositionCache.getInstance().get(cacheKey);
        if (cachedComposition != null) {
            task = new LottieTask<>(cachedComposition);
        }
        if (cacheKey != null && taskCache.containsKey(cacheKey)) {
            LottieTask<LottieComposition> task2 = taskCache.get(cacheKey);
            task = task2;
        }
        if (task != null) {
            if (onCached != null) {
                onCached.run();
            }
            return task;
        }
        LottieTask<LottieComposition> task3 = new LottieTask<>(callable);
        if (cacheKey != null) {
            final AtomicBoolean resultAlreadyCalled = new AtomicBoolean(false);
            task3.addListener(new LottieListener() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda5
                @Override // com.airbnb.lottie.LottieListener
                public final void onResult(Object obj) {
                    LottieCompositionFactory.lambda$cache$15(cacheKey, resultAlreadyCalled, (LottieComposition) obj);
                }
            });
            task3.addFailureListener(new LottieListener() { // from class: com.airbnb.lottie.LottieCompositionFactory$$ExternalSyntheticLambda6
                @Override // com.airbnb.lottie.LottieListener
                public final void onResult(Object obj) {
                    LottieCompositionFactory.lambda$cache$16(cacheKey, resultAlreadyCalled, (Throwable) obj);
                }
            });
            if (!resultAlreadyCalled.get()) {
                taskCache.put(cacheKey, task3);
                if (taskCache.size() == 1) {
                    notifyTaskCacheIdleListeners(false);
                }
            }
        }
        return task3;
    }

    static /* synthetic */ void lambda$cache$15(String cacheKey, AtomicBoolean resultAlreadyCalled, LottieComposition result) {
        taskCache.remove(cacheKey);
        resultAlreadyCalled.set(true);
        if (taskCache.size() == 0) {
            notifyTaskCacheIdleListeners(true);
        }
    }

    static /* synthetic */ void lambda$cache$16(String cacheKey, AtomicBoolean resultAlreadyCalled, Throwable result) {
        taskCache.remove(cacheKey);
        resultAlreadyCalled.set(true);
        if (taskCache.size() == 0) {
            notifyTaskCacheIdleListeners(true);
        }
    }

    private static void notifyTaskCacheIdleListeners(boolean idle) {
        List<LottieTaskIdleListener> listeners = new ArrayList<>(taskIdleListeners);
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).onIdleChanged(idle);
        }
    }
}
