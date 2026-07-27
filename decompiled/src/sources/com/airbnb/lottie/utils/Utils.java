package com.airbnb.lottie.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.RectF;
import android.provider.Settings;
import com.airbnb.lottie.L;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.content.TrimPathContent;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import java.io.Closeable;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;
import javax.net.ssl.SSLException;

/* loaded from: classes.dex */
public final class Utils {
    public static final int SECOND_IN_NANOS = 1000000000;
    private static final ThreadLocal<PathMeasure> threadLocalPathMeasure = new ThreadLocal<PathMeasure>() { // from class: com.airbnb.lottie.utils.Utils.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public PathMeasure initialValue() {
            return new PathMeasure();
        }
    };
    private static final ThreadLocal<Path> threadLocalTempPath = new ThreadLocal<Path>() { // from class: com.airbnb.lottie.utils.Utils.2
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public Path initialValue() {
            return new Path();
        }
    };
    private static final ThreadLocal<Path> threadLocalTempPath2 = new ThreadLocal<Path>() { // from class: com.airbnb.lottie.utils.Utils.3
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public Path initialValue() {
            return new Path();
        }
    };
    private static final ThreadLocal<float[]> threadLocalPoints = new ThreadLocal<float[]>() { // from class: com.airbnb.lottie.utils.Utils.4
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public float[] initialValue() {
            return new float[4];
        }
    };
    private static final float INV_SQRT_2 = (float) (Math.sqrt(2.0d) / 2.0d);

    private Utils() {
    }

    public static Path createPath(PointF startPoint, PointF endPoint, PointF cp1, PointF cp2) {
        Path path = new Path();
        path.moveTo(startPoint.x, startPoint.y);
        if (cp1 != null && cp2 != null && (cp1.length() != 0.0f || cp2.length() != 0.0f)) {
            path.cubicTo(cp1.x + startPoint.x, cp1.y + startPoint.y, cp2.x + endPoint.x, cp2.y + endPoint.y, endPoint.x, endPoint.y);
        } else {
            path.lineTo(endPoint.x, endPoint.y);
        }
        return path;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception e) {
            }
        }
    }

    public static float getScale(Matrix matrix) {
        float[] points = threadLocalPoints.get();
        points[0] = 0.0f;
        points[1] = 0.0f;
        points[2] = INV_SQRT_2;
        points[3] = INV_SQRT_2;
        matrix.mapPoints(points);
        float dx = points[2] - points[0];
        float dy = points[3] - points[1];
        return (float) Math.hypot(dx, dy);
    }

    public static boolean hasZeroScaleAxis(Matrix matrix) {
        float[] points = threadLocalPoints.get();
        points[0] = 0.0f;
        points[1] = 0.0f;
        points[2] = 37394.73f;
        points[3] = 39575.234f;
        matrix.mapPoints(points);
        if (points[0] != points[2] && points[1] != points[3]) {
            return false;
        }
        return true;
    }

    public static void applyTrimPathIfNeeded(Path path, TrimPathContent trimPath) {
        if (trimPath == null || trimPath.isHidden()) {
            return;
        }
        float start = ((FloatKeyframeAnimation) trimPath.getStart()).getFloatValue();
        float end = ((FloatKeyframeAnimation) trimPath.getEnd()).getFloatValue();
        float offset = ((FloatKeyframeAnimation) trimPath.getOffset()).getFloatValue();
        applyTrimPathIfNeeded(path, start / 100.0f, end / 100.0f, offset / 360.0f);
    }

    public static void applyTrimPathIfNeeded(Path path, float startValue, float endValue, float offsetValue) {
        L.beginSection("applyTrimPathIfNeeded");
        PathMeasure pathMeasure = threadLocalPathMeasure.get();
        Path tempPath = threadLocalTempPath.get();
        Path tempPath2 = threadLocalTempPath2.get();
        pathMeasure.setPath(path, false);
        float length = pathMeasure.getLength();
        if (startValue == 1.0f && endValue == 0.0f) {
            L.endSection("applyTrimPathIfNeeded");
            return;
        }
        if (length < 1.0f || Math.abs((endValue - startValue) - 1.0f) < 0.01d) {
            L.endSection("applyTrimPathIfNeeded");
            return;
        }
        float start = length * startValue;
        float end = length * endValue;
        float offset = offsetValue * length;
        float newStart = Math.min(start, end) + offset;
        float newEnd = Math.max(start, end) + offset;
        if (newStart >= length && newEnd >= length) {
            newStart = MiscUtils.floorMod(newStart, length);
            newEnd = MiscUtils.floorMod(newEnd, length);
        }
        if (newStart < 0.0f) {
            newStart = MiscUtils.floorMod(newStart, length);
        }
        if (newEnd < 0.0f) {
            newEnd = MiscUtils.floorMod(newEnd, length);
        }
        if (newStart == newEnd) {
            path.reset();
            L.endSection("applyTrimPathIfNeeded");
            return;
        }
        if (newStart >= newEnd) {
            newStart -= length;
        }
        tempPath.reset();
        pathMeasure.getSegment(newStart, newEnd, tempPath, true);
        if (newEnd > length) {
            tempPath2.reset();
            pathMeasure.getSegment(0.0f, newEnd % length, tempPath2, true);
            tempPath.addPath(tempPath2);
        } else if (newStart < 0.0f) {
            tempPath2.reset();
            pathMeasure.getSegment(length + newStart, length, tempPath2, true);
            tempPath.addPath(tempPath2);
        }
        path.set(tempPath);
        L.endSection("applyTrimPathIfNeeded");
    }

    public static boolean isAtLeastVersion(int major, int minor, int patch, int minMajor, int minMinor, int minPatch) {
        if (major < minMajor) {
            return false;
        }
        if (major > minMajor) {
            return true;
        }
        if (minor < minMinor) {
            return false;
        }
        if (minor <= minMinor && patch < minPatch) {
            return false;
        }
        return true;
    }

    public static int hashFor(float a, float b, float c, float d) {
        int result = 17;
        if (a != 0.0f) {
            result = (int) (17 * 31 * a);
        }
        if (b != 0.0f) {
            result = (int) (result * 31 * b);
        }
        if (c != 0.0f) {
            result = (int) (result * 31 * c);
        }
        if (d != 0.0f) {
            return (int) (result * 31 * d);
        }
        return result;
    }

    public static float dpScale() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    public static float getAnimationScale(Context context) {
        return Settings.Global.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f);
    }

    public static Bitmap resizeBitmapIfNeeded(Bitmap bitmap, int width, int height) {
        if (bitmap.getWidth() == width && bitmap.getHeight() == height) {
            return bitmap;
        }
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        bitmap.recycle();
        return resizedBitmap;
    }

    public static boolean isNetworkException(Throwable e) {
        return (e instanceof SocketException) || (e instanceof ClosedChannelException) || (e instanceof InterruptedIOException) || (e instanceof ProtocolException) || (e instanceof SSLException) || (e instanceof UnknownHostException) || (e instanceof UnknownServiceException);
    }

    public static void saveLayerCompat(Canvas canvas, RectF rect, Paint paint) {
        saveLayerCompat(canvas, rect, paint, 31);
    }

    public static void saveLayerCompat(Canvas canvas, RectF rect, Paint paint, int flag) {
        L.beginSection("Utils#saveLayer");
        canvas.saveLayer(rect, paint);
        L.endSection("Utils#saveLayer");
    }

    public static Bitmap renderPath(Path path) {
        RectF bounds = new RectF();
        path.computeBounds(bounds, false);
        Bitmap bitmap = Bitmap.createBitmap((int) bounds.right, (int) bounds.bottom, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new LPaint();
        paint.setAntiAlias(true);
        paint.setColor(-16776961);
        canvas.drawPath(path, paint);
        return bitmap;
    }
}
