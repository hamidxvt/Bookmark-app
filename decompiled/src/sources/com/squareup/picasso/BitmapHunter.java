package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.NetworkInfo;
import com.budiyev.android.codescanner.BarcodeUtils;
import com.squareup.picasso.NetworkRequestHandler;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

/* loaded from: classes17.dex */
class BitmapHunter implements Runnable {
    Action action;
    List<Action> actions;
    final Cache cache;
    final Request data;
    final Dispatcher dispatcher;
    Exception exception;
    int exifOrientation;
    Future<?> future;
    final String key;
    Picasso.LoadedFrom loadedFrom;
    final int memoryPolicy;
    int networkPolicy;
    final Picasso picasso;
    Picasso.Priority priority;
    final RequestHandler requestHandler;
    Bitmap result;
    int retryCount;
    final int sequence = SEQUENCE_GENERATOR.incrementAndGet();
    final Stats stats;
    private static final Object DECODE_LOCK = new Object();
    private static final ThreadLocal<StringBuilder> NAME_BUILDER = new ThreadLocal<StringBuilder>() { // from class: com.squareup.picasso.BitmapHunter.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public StringBuilder initialValue() {
            return new StringBuilder("Picasso-");
        }
    };
    private static final AtomicInteger SEQUENCE_GENERATOR = new AtomicInteger();
    private static final RequestHandler ERRORING_HANDLER = new RequestHandler() { // from class: com.squareup.picasso.BitmapHunter.2
        @Override // com.squareup.picasso.RequestHandler
        public boolean canHandleRequest(Request data) {
            return true;
        }

        @Override // com.squareup.picasso.RequestHandler
        public RequestHandler.Result load(Request request, int networkPolicy) throws IOException {
            throw new IllegalStateException("Unrecognized type of request: " + request);
        }
    };

    BitmapHunter(Picasso picasso, Dispatcher dispatcher, Cache cache, Stats stats, Action action, RequestHandler requestHandler) {
        this.picasso = picasso;
        this.dispatcher = dispatcher;
        this.cache = cache;
        this.stats = stats;
        this.action = action;
        this.key = action.getKey();
        this.data = action.getRequest();
        this.priority = action.getPriority();
        this.memoryPolicy = action.getMemoryPolicy();
        this.networkPolicy = action.getNetworkPolicy();
        this.requestHandler = requestHandler;
        this.retryCount = requestHandler.getRetryCount();
    }

    static Bitmap decodeStream(Source source, Request request) throws IOException {
        BufferedSource bufferedSource = Okio.buffer(source);
        boolean isWebPFile = Utils.isWebPFile(bufferedSource);
        boolean z = request.purgeable;
        BitmapFactory.Options options = RequestHandler.createBitmapOptions(request);
        boolean calculateSize = RequestHandler.requiresInSampleSize(options);
        if (isWebPFile || 0 != 0) {
            byte[] bytes = bufferedSource.readByteArray();
            if (calculateSize) {
                BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
                RequestHandler.calculateInSampleSize(request.targetWidth, request.targetHeight, options, request);
            }
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        }
        InputStream stream = bufferedSource.inputStream();
        if (calculateSize) {
            MarkableInputStream markStream = new MarkableInputStream(stream);
            stream = markStream;
            markStream.allowMarksToExpire(false);
            long mark = markStream.savePosition(1024);
            BitmapFactory.decodeStream(stream, null, options);
            RequestHandler.calculateInSampleSize(request.targetWidth, request.targetHeight, options, request);
            markStream.reset(mark);
            markStream.allowMarksToExpire(true);
        }
        Bitmap bitmap = BitmapFactory.decodeStream(stream, null, options);
        if (bitmap == null) {
            throw new IOException("Failed to decode stream.");
        }
        return bitmap;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                try {
                    updateThreadName(this.data);
                    if (this.picasso.loggingEnabled) {
                        Utils.log("Hunter", "executing", Utils.getLogIdsForHunter(this));
                    }
                    this.result = hunt();
                    if (this.result == null) {
                        this.dispatcher.dispatchFailed(this);
                    } else {
                        this.dispatcher.dispatchComplete(this);
                    }
                } catch (NetworkRequestHandler.ResponseException e) {
                    if (!NetworkPolicy.isOfflineOnly(e.networkPolicy) || e.code != 504) {
                        this.exception = e;
                    }
                    this.dispatcher.dispatchFailed(this);
                } catch (Exception e2) {
                    this.exception = e2;
                    this.dispatcher.dispatchFailed(this);
                }
            } catch (IOException e3) {
                this.exception = e3;
                this.dispatcher.dispatchRetry(this);
            } catch (OutOfMemoryError e4) {
                StringWriter writer = new StringWriter();
                this.stats.createSnapshot().dump(new PrintWriter(writer));
                this.exception = new RuntimeException(writer.toString(), e4);
                this.dispatcher.dispatchFailed(this);
            }
        } finally {
            Thread.currentThread().setName("Picasso-Idle");
        }
    }

    Bitmap hunt() throws IOException {
        Bitmap bitmap = null;
        if (MemoryPolicy.shouldReadFromMemoryCache(this.memoryPolicy) && (bitmap = this.cache.get(this.key)) != null) {
            this.stats.dispatchCacheHit();
            this.loadedFrom = Picasso.LoadedFrom.MEMORY;
            if (this.picasso.loggingEnabled) {
                Utils.log("Hunter", "decoded", this.data.logId(), "from cache");
            }
            return bitmap;
        }
        this.networkPolicy = this.retryCount == 0 ? NetworkPolicy.OFFLINE.index : this.networkPolicy;
        RequestHandler.Result result = this.requestHandler.load(this.data, this.networkPolicy);
        if (result != null) {
            this.loadedFrom = result.getLoadedFrom();
            this.exifOrientation = result.getExifOrientation();
            bitmap = result.getBitmap();
            if (bitmap == null) {
                Source source = result.getSource();
                try {
                    bitmap = decodeStream(source, this.data);
                } finally {
                    try {
                        source.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        if (bitmap != null) {
            if (this.picasso.loggingEnabled) {
                Utils.log("Hunter", "decoded", this.data.logId());
            }
            this.stats.dispatchBitmapDecoded(bitmap);
            if (this.data.needsTransformation() || this.exifOrientation != 0) {
                synchronized (DECODE_LOCK) {
                    if (this.data.needsMatrixTransform() || this.exifOrientation != 0) {
                        bitmap = transformResult(this.data, bitmap, this.exifOrientation);
                        if (this.picasso.loggingEnabled) {
                            Utils.log("Hunter", "transformed", this.data.logId());
                        }
                    }
                    if (this.data.hasCustomTransformations()) {
                        bitmap = applyCustomTransformations(this.data.transformations, bitmap);
                        if (this.picasso.loggingEnabled) {
                            Utils.log("Hunter", "transformed", this.data.logId(), "from custom transformations");
                        }
                    }
                }
                if (bitmap != null) {
                    this.stats.dispatchBitmapTransformed(bitmap);
                }
            }
        }
        return bitmap;
    }

    void attach(Action action) {
        boolean loggingEnabled = this.picasso.loggingEnabled;
        Request request = action.request;
        if (this.action == null) {
            this.action = action;
            if (loggingEnabled) {
                if (this.actions == null || this.actions.isEmpty()) {
                    Utils.log("Hunter", "joined", request.logId(), "to empty hunter");
                    return;
                } else {
                    Utils.log("Hunter", "joined", request.logId(), Utils.getLogIdsForHunter(this, "to "));
                    return;
                }
            }
            return;
        }
        if (this.actions == null) {
            this.actions = new ArrayList(3);
        }
        this.actions.add(action);
        if (loggingEnabled) {
            Utils.log("Hunter", "joined", request.logId(), Utils.getLogIdsForHunter(this, "to "));
        }
        Picasso.Priority actionPriority = action.getPriority();
        if (actionPriority.ordinal() > this.priority.ordinal()) {
            this.priority = actionPriority;
        }
    }

    void detach(Action action) {
        boolean detached = false;
        if (this.action == action) {
            this.action = null;
            detached = true;
        } else if (this.actions != null) {
            detached = this.actions.remove(action);
        }
        if (detached && action.getPriority() == this.priority) {
            this.priority = computeNewPriority();
        }
        if (this.picasso.loggingEnabled) {
            Utils.log("Hunter", "removed", action.request.logId(), Utils.getLogIdsForHunter(this, "from "));
        }
    }

    private Picasso.Priority computeNewPriority() {
        Picasso.Priority newPriority = Picasso.Priority.LOW;
        boolean hasMultiple = (this.actions == null || this.actions.isEmpty()) ? false : true;
        boolean hasAny = this.action != null || hasMultiple;
        if (!hasAny) {
            return newPriority;
        }
        if (this.action != null) {
            newPriority = this.action.getPriority();
        }
        if (hasMultiple) {
            int n = this.actions.size();
            for (int i = 0; i < n; i++) {
                Picasso.Priority actionPriority = this.actions.get(i).getPriority();
                if (actionPriority.ordinal() > newPriority.ordinal()) {
                    newPriority = actionPriority;
                }
            }
        }
        return newPriority;
    }

    boolean cancel() {
        if (this.action == null) {
            return (this.actions == null || this.actions.isEmpty()) && this.future != null && this.future.cancel(false);
        }
        return false;
    }

    boolean isCancelled() {
        return this.future != null && this.future.isCancelled();
    }

    boolean shouldRetry(boolean airplaneMode, NetworkInfo info) {
        boolean hasRetries = this.retryCount > 0;
        if (!hasRetries) {
            return false;
        }
        this.retryCount--;
        return this.requestHandler.shouldRetry(airplaneMode, info);
    }

    boolean supportsReplay() {
        return this.requestHandler.supportsReplay();
    }

    Bitmap getResult() {
        return this.result;
    }

    String getKey() {
        return this.key;
    }

    int getMemoryPolicy() {
        return this.memoryPolicy;
    }

    Request getData() {
        return this.data;
    }

    Action getAction() {
        return this.action;
    }

    Picasso getPicasso() {
        return this.picasso;
    }

    List<Action> getActions() {
        return this.actions;
    }

    Exception getException() {
        return this.exception;
    }

    Picasso.LoadedFrom getLoadedFrom() {
        return this.loadedFrom;
    }

    Picasso.Priority getPriority() {
        return this.priority;
    }

    static void updateThreadName(Request data) {
        String name = data.getName();
        StringBuilder builder = NAME_BUILDER.get();
        builder.ensureCapacity("Picasso-".length() + name.length());
        builder.replace("Picasso-".length(), builder.length(), name);
        Thread.currentThread().setName(builder.toString());
    }

    static BitmapHunter forRequest(Picasso picasso, Dispatcher dispatcher, Cache cache, Stats stats, Action action) {
        Request request = action.getRequest();
        List<RequestHandler> requestHandlers = picasso.getRequestHandlers();
        int count = requestHandlers.size();
        for (int i = 0; i < count; i++) {
            RequestHandler requestHandler = requestHandlers.get(i);
            if (requestHandler.canHandleRequest(request)) {
                return new BitmapHunter(picasso, dispatcher, cache, stats, action, requestHandler);
            }
        }
        return new BitmapHunter(picasso, dispatcher, cache, stats, action, ERRORING_HANDLER);
    }

    static Bitmap applyCustomTransformations(List<Transformation> transformations, Bitmap result) {
        int count = transformations.size();
        for (int i = 0; i < count; i++) {
            final Transformation transformation = transformations.get(i);
            try {
                Bitmap newResult = transformation.transform(result);
                if (newResult == null) {
                    final StringBuilder builder = new StringBuilder().append("Transformation ").append(transformation.key()).append(" returned null after ").append(i).append(" previous transformation(s).\n\nTransformation list:\n");
                    for (Transformation t : transformations) {
                        builder.append(t.key()).append('\n');
                    }
                    Picasso.HANDLER.post(new Runnable() { // from class: com.squareup.picasso.BitmapHunter.4
                        @Override // java.lang.Runnable
                        public void run() {
                            throw new NullPointerException(builder.toString());
                        }
                    });
                    return null;
                }
                if (newResult == result && result.isRecycled()) {
                    Picasso.HANDLER.post(new Runnable() { // from class: com.squareup.picasso.BitmapHunter.5
                        @Override // java.lang.Runnable
                        public void run() {
                            throw new IllegalStateException("Transformation " + Transformation.this.key() + " returned input Bitmap but recycled it.");
                        }
                    });
                    return null;
                }
                if (newResult != result && !result.isRecycled()) {
                    Picasso.HANDLER.post(new Runnable() { // from class: com.squareup.picasso.BitmapHunter.6
                        @Override // java.lang.Runnable
                        public void run() {
                            throw new IllegalStateException("Transformation " + Transformation.this.key() + " mutated input Bitmap but failed to recycle the original.");
                        }
                    });
                    return null;
                }
                result = newResult;
            } catch (RuntimeException e) {
                Picasso.HANDLER.post(new Runnable() { // from class: com.squareup.picasso.BitmapHunter.3
                    @Override // java.lang.Runnable
                    public void run() {
                        throw new RuntimeException("Transformation " + Transformation.this.key() + " crashed with exception.", e);
                    }
                });
                return null;
            }
        }
        return result;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x02e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static Bitmap transformResult(Request data, Bitmap result, int exifOrientation) {
        int inWidth;
        int inHeight;
        boolean onlyScaleDown;
        int drawX;
        int drawY;
        int drawWidth;
        int drawHeight;
        Matrix matrix;
        Matrix matrix2;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        int inWidth2;
        int inHeight2;
        float widthRatio;
        float f9;
        float f10;
        float scaleY;
        float scaleX;
        int drawWidth2;
        int drawHeight2;
        int drawX2;
        int drawY2;
        int drawY3;
        Bitmap newResult;
        int inWidth3 = result.getWidth();
        int inHeight3 = result.getHeight();
        boolean onlyScaleDown2 = data.onlyScaleDown;
        Matrix matrix3 = new Matrix();
        if (!data.needsMatrixTransform() && exifOrientation == 0) {
            drawX = 0;
            drawY = 0;
            drawWidth = inWidth3;
            drawHeight = inHeight3;
            matrix2 = matrix3;
        } else {
            int targetWidth = data.targetWidth;
            int targetHeight = data.targetHeight;
            float targetRotation = data.rotationDegrees;
            if (targetRotation == 0.0f) {
                inWidth = inWidth3;
                inHeight = inHeight3;
                onlyScaleDown = onlyScaleDown2;
                drawX = 0;
                drawY = 0;
                drawWidth = inWidth3;
                drawHeight = inHeight3;
                matrix = matrix3;
            } else {
                double cosR = Math.cos(Math.toRadians(targetRotation));
                double sinR = Math.sin(Math.toRadians(targetRotation));
                drawX = 0;
                if (data.hasRotationPivot) {
                    drawY = 0;
                    matrix3.setRotate(targetRotation, data.rotationPivotX, data.rotationPivotY);
                    drawWidth = inWidth3;
                    drawHeight = inHeight3;
                    double x1T = (data.rotationPivotX * (1.0d - cosR)) + (data.rotationPivotY * sinR);
                    double y1T = (data.rotationPivotY * (1.0d - cosR)) - (data.rotationPivotX * sinR);
                    double x2T = (data.targetWidth * cosR) + x1T;
                    onlyScaleDown = onlyScaleDown2;
                    inHeight = inHeight3;
                    double y2T = (data.targetWidth * sinR) + y1T;
                    inWidth = inWidth3;
                    matrix = matrix3;
                    double x3T = ((data.targetWidth * cosR) + x1T) - (data.targetHeight * sinR);
                    double y3T = (data.targetWidth * sinR) + y1T + (data.targetHeight * cosR);
                    double x4T = x1T - (data.targetHeight * sinR);
                    double y4T = (data.targetHeight * cosR) + y1T;
                    double maxX = Math.max(x4T, Math.max(x3T, Math.max(x1T, x2T)));
                    double maxX2 = Math.min(x1T, x2T);
                    double minX = Math.min(x4T, Math.min(x3T, maxX2));
                    double maxY = Math.max(y4T, Math.max(y3T, Math.max(y1T, y2T)));
                    double minY = Math.min(y4T, Math.min(y3T, Math.min(y1T, y2T)));
                    int targetWidth2 = (int) Math.floor(maxX - minX);
                    targetHeight = (int) Math.floor(maxY - minY);
                    targetWidth = targetWidth2;
                } else {
                    inWidth = inWidth3;
                    inHeight = inHeight3;
                    onlyScaleDown = onlyScaleDown2;
                    drawY = 0;
                    drawWidth = inWidth3;
                    drawHeight = inHeight3;
                    matrix = matrix3;
                    matrix.setRotate(targetRotation);
                    double x2T2 = data.targetWidth * cosR;
                    double y2T2 = data.targetWidth * sinR;
                    double x3T2 = (data.targetWidth * cosR) - (data.targetHeight * sinR);
                    double y3T2 = (data.targetWidth * sinR) + (data.targetHeight * cosR);
                    double x4T2 = -(data.targetHeight * sinR);
                    double y4T2 = data.targetHeight * cosR;
                    double maxX3 = Math.max(x4T2, Math.max(x3T2, Math.max(com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON, x2T2)));
                    double maxX4 = Math.min(com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON, x2T2);
                    double minX2 = Math.min(x4T2, Math.min(x3T2, maxX4));
                    double maxY2 = Math.max(y4T2, Math.max(y3T2, Math.max(com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON, y2T2)));
                    double minY2 = Math.min(y4T2, Math.min(y3T2, Math.min(com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON, y2T2)));
                    int targetWidth3 = (int) Math.floor(maxX3 - minX2);
                    targetHeight = (int) Math.floor(maxY2 - minY2);
                    targetWidth = targetWidth3;
                }
            }
            if (exifOrientation == 0) {
                matrix2 = matrix;
            } else {
                int exifRotation = getExifRotation(exifOrientation);
                int exifTranslation = getExifTranslation(exifOrientation);
                if (exifRotation == 0) {
                    matrix2 = matrix;
                } else {
                    matrix2 = matrix;
                    matrix2.preRotate(exifRotation);
                    if (exifRotation == 90 || exifRotation == 270) {
                        int tmpHeight = targetHeight;
                        int targetHeight2 = targetWidth;
                        targetHeight = targetHeight2;
                        targetWidth = tmpHeight;
                    }
                }
                if (exifTranslation != 1) {
                    matrix2.postScale(exifTranslation, 1.0f);
                }
            }
            if (!data.centerCrop) {
                boolean onlyScaleDown3 = onlyScaleDown;
                int inHeight4 = inHeight;
                int inWidth4 = inWidth;
                if (data.centerInside) {
                    if (targetWidth != 0) {
                        f5 = targetWidth;
                        f6 = inWidth4;
                    } else {
                        f5 = targetHeight;
                        f6 = inHeight4;
                    }
                    float widthRatio2 = f5 / f6;
                    if (targetHeight != 0) {
                        f7 = targetHeight;
                        f8 = inHeight4;
                    } else {
                        f7 = targetWidth;
                        f8 = inWidth4;
                    }
                    float heightRatio = f7 / f8;
                    float scale = widthRatio2 < heightRatio ? widthRatio2 : heightRatio;
                    if (shouldResize(onlyScaleDown3, inWidth4, inHeight4, targetWidth, targetHeight)) {
                        matrix2.preScale(scale, scale);
                    }
                } else if ((targetWidth != 0 || targetHeight != 0) && (targetWidth != inWidth4 || targetHeight != inHeight4)) {
                    if (targetWidth != 0) {
                        f = targetWidth;
                        f2 = inWidth4;
                    } else {
                        f = targetHeight;
                        f2 = inHeight4;
                    }
                    float sx = f / f2;
                    if (targetHeight != 0) {
                        f3 = targetHeight;
                        f4 = inHeight4;
                    } else {
                        f3 = targetWidth;
                        f4 = inWidth4;
                    }
                    float sy = f3 / f4;
                    if (shouldResize(onlyScaleDown3, inWidth4, inHeight4, targetWidth, targetHeight)) {
                        matrix2.preScale(sx, sy);
                    }
                }
            } else {
                if (targetWidth != 0) {
                    inWidth2 = inWidth;
                    widthRatio = targetWidth / inWidth2;
                    inHeight2 = inHeight;
                } else {
                    inWidth2 = inWidth;
                    inHeight2 = inHeight;
                    widthRatio = targetHeight / inHeight2;
                }
                if (targetHeight != 0) {
                    f9 = targetHeight;
                    f10 = inHeight2;
                } else {
                    f9 = targetWidth;
                    f10 = inWidth2;
                }
                float heightRatio2 = f9 / f10;
                if (widthRatio > heightRatio2) {
                    int newSize = (int) Math.ceil(inHeight2 * (heightRatio2 / widthRatio));
                    if ((data.centerCropGravity & 48) == 48) {
                        drawY3 = 0;
                    } else {
                        int drawY4 = data.centerCropGravity;
                        if ((drawY4 & 80) == 80) {
                            drawY3 = inHeight2 - newSize;
                        } else {
                            int drawY5 = inHeight2 - newSize;
                            drawY3 = drawY5 / 2;
                        }
                    }
                    scaleX = widthRatio;
                    scaleY = targetHeight / newSize;
                    drawY = drawY3;
                    drawHeight2 = newSize;
                    drawWidth2 = drawWidth;
                } else if (widthRatio < heightRatio2) {
                    drawWidth2 = (int) Math.ceil(inWidth2 * (widthRatio / heightRatio2));
                    if ((data.centerCropGravity & 3) == 3) {
                        drawX2 = 0;
                    } else {
                        int drawX3 = data.centerCropGravity;
                        if ((drawX3 & 5) == 5) {
                            drawX2 = inWidth2 - drawWidth2;
                        } else {
                            int drawX4 = inWidth2 - drawWidth2;
                            drawX2 = drawX4 / 2;
                        }
                    }
                    scaleX = targetWidth / drawWidth2;
                    scaleY = heightRatio2;
                    drawX = drawX2;
                    drawHeight2 = drawHeight;
                } else {
                    int drawWidth3 = inWidth2;
                    scaleY = heightRatio2;
                    scaleX = heightRatio2;
                    drawX = 0;
                    drawWidth2 = drawWidth3;
                    drawHeight2 = drawHeight;
                }
                if (shouldResize(onlyScaleDown, inWidth2, inHeight2, targetWidth, targetHeight)) {
                    matrix2.preScale(scaleX, scaleY);
                }
                drawY2 = drawY;
                newResult = Bitmap.createBitmap(result, drawX, drawY2, drawWidth2, drawHeight2, matrix2, true);
                if (newResult != result) {
                    return result;
                }
                result.recycle();
                return newResult;
            }
        }
        drawY2 = drawY;
        drawWidth2 = drawWidth;
        drawHeight2 = drawHeight;
        newResult = Bitmap.createBitmap(result, drawX, drawY2, drawWidth2, drawHeight2, matrix2, true);
        if (newResult != result) {
        }
    }

    private static boolean shouldResize(boolean onlyScaleDown, int inWidth, int inHeight, int targetWidth, int targetHeight) {
        return !onlyScaleDown || (targetWidth != 0 && inWidth > targetWidth) || (targetHeight != 0 && inHeight > targetHeight);
    }

    static int getExifRotation(int orientation) {
        switch (orientation) {
            case 3:
            case 4:
                return BarcodeUtils.ROTATION_180;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return BarcodeUtils.ROTATION_270;
            default:
                return 0;
        }
    }

    static int getExifTranslation(int orientation) {
        switch (orientation) {
            case 2:
            case 4:
            case 5:
            case 7:
                return -1;
            case 3:
            case 6:
            default:
                return 1;
        }
    }
}
