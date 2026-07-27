package com.airbnb.lottie.parser;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.collection.SparseArrayCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
class KeyframeParser {
    private static final float MAX_CP_VALUE = 100.0f;
    private static SparseArrayCompat<WeakReference<Interpolator>> pathInterpolatorCache;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    static JsonReader.Options NAMES = JsonReader.Options.of("t", "s", "e", "o", "i", "h", TypedValues.TransitionType.S_TO, "ti");
    static JsonReader.Options INTERPOLATOR_NAMES = JsonReader.Options.of("x", "y");

    KeyframeParser() {
    }

    private static SparseArrayCompat<WeakReference<Interpolator>> pathInterpolatorCache() {
        if (pathInterpolatorCache == null) {
            pathInterpolatorCache = new SparseArrayCompat<>();
        }
        return pathInterpolatorCache;
    }

    private static WeakReference<Interpolator> getInterpolator(int hash) {
        WeakReference<Interpolator> weakReference;
        synchronized (KeyframeParser.class) {
            weakReference = pathInterpolatorCache().get(hash);
        }
        return weakReference;
    }

    private static void putInterpolator(int hash, WeakReference<Interpolator> interpolator) {
        synchronized (KeyframeParser.class) {
            pathInterpolatorCache.put(hash, interpolator);
        }
    }

    static <T> Keyframe<T> parse(JsonReader reader, LottieComposition composition, float scale, ValueParser<T> valueParser, boolean animated, boolean multiDimensional) throws IOException {
        if (animated && multiDimensional) {
            return parseMultiDimensionalKeyframe(composition, reader, scale, valueParser);
        }
        if (animated) {
            return parseKeyframe(composition, reader, scale, valueParser);
        }
        return parseStaticValue(reader, scale, valueParser);
    }

    private static <T> Keyframe<T> parseKeyframe(LottieComposition composition, JsonReader reader, float scale, ValueParser<T> valueParser) throws IOException {
        Interpolator interpolator;
        PointF cp1 = null;
        PointF cp2 = null;
        float startFrame = 0.0f;
        T startValue = null;
        T endValue = null;
        boolean hold = false;
        reader.beginObject();
        PointF pathCp1 = null;
        PointF pathCp2 = null;
        while (reader.hasNext()) {
            switch (reader.selectName(NAMES)) {
                case 0:
                    startFrame = (float) reader.nextDouble();
                    break;
                case 1:
                    startValue = valueParser.parse(reader, scale);
                    break;
                case 2:
                    endValue = valueParser.parse(reader, scale);
                    break;
                case 3:
                    cp1 = JsonUtils.jsonToPoint(reader, 1.0f);
                    break;
                case 4:
                    cp2 = JsonUtils.jsonToPoint(reader, 1.0f);
                    break;
                case 5:
                    hold = reader.nextInt() == 1;
                    break;
                case 6:
                    pathCp1 = JsonUtils.jsonToPoint(reader, scale);
                    break;
                case 7:
                    pathCp2 = JsonUtils.jsonToPoint(reader, scale);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        if (hold) {
            endValue = startValue;
            interpolator = LINEAR_INTERPOLATOR;
        } else if (cp1 != null && cp2 != null) {
            interpolator = interpolatorFor(cp1, cp2);
        } else {
            interpolator = LINEAR_INTERPOLATOR;
        }
        Keyframe<T> keyframe = new Keyframe<>(composition, startValue, endValue, interpolator, startFrame, null);
        keyframe.pathCp1 = pathCp1;
        keyframe.pathCp2 = pathCp2;
        return keyframe;
    }

    private static <T> Keyframe<T> parseMultiDimensionalKeyframe(LottieComposition composition, JsonReader reader, float scale, ValueParser<T> valueParser) throws IOException {
        Interpolator interpolator;
        T endValue;
        Interpolator xInterpolator;
        Interpolator yInterpolator;
        Keyframe<T> keyframe;
        float yCp2x;
        float yCp2y;
        PointF cp1 = null;
        PointF cp2 = null;
        PointF xCp1 = null;
        PointF xCp2 = null;
        PointF yCp1 = null;
        PointF yCp2 = null;
        float startFrame = 0.0f;
        T startValue = null;
        boolean hold = false;
        Interpolator xInterpolator2 = null;
        Interpolator yInterpolator2 = null;
        reader.beginObject();
        T endValue2 = null;
        PointF pathCp1 = null;
        PointF pathCp2 = null;
        while (reader.hasNext()) {
            Interpolator xInterpolator3 = xInterpolator2;
            switch (reader.selectName(NAMES)) {
                case 0:
                    startFrame = (float) reader.nextDouble();
                    xInterpolator2 = xInterpolator3;
                    yCp2 = yCp2;
                    break;
                case 1:
                    startValue = valueParser.parse(reader, scale);
                    xInterpolator2 = xInterpolator3;
                    break;
                case 2:
                    endValue2 = valueParser.parse(reader, scale);
                    xInterpolator2 = xInterpolator3;
                    break;
                case 3:
                    PointF yCp22 = yCp2;
                    float startFrame2 = startFrame;
                    PointF pathCp12 = pathCp1;
                    PointF pathCp22 = pathCp2;
                    Interpolator yInterpolator3 = yInterpolator2;
                    if (reader.peek() == JsonReader.Token.BEGIN_OBJECT) {
                        reader.beginObject();
                        float xCp1x = 0.0f;
                        float xCp1y = 0.0f;
                        float yCp1x = 0.0f;
                        float yCp1y = 0.0f;
                        while (reader.hasNext()) {
                            switch (reader.selectName(INTERPOLATOR_NAMES)) {
                                case 0:
                                    if (reader.peek() == JsonReader.Token.NUMBER) {
                                        xCp1x = (float) reader.nextDouble();
                                        yCp1x = xCp1x;
                                        break;
                                    } else {
                                        reader.beginArray();
                                        xCp1x = (float) reader.nextDouble();
                                        if (reader.peek() == JsonReader.Token.NUMBER) {
                                            yCp1x = (float) reader.nextDouble();
                                        } else {
                                            yCp1x = xCp1x;
                                        }
                                        reader.endArray();
                                        break;
                                    }
                                case 1:
                                    if (reader.peek() == JsonReader.Token.NUMBER) {
                                        xCp1y = (float) reader.nextDouble();
                                        yCp1y = xCp1y;
                                        break;
                                    } else {
                                        reader.beginArray();
                                        xCp1y = (float) reader.nextDouble();
                                        if (reader.peek() == JsonReader.Token.NUMBER) {
                                            yCp1y = (float) reader.nextDouble();
                                        } else {
                                            yCp1y = xCp1y;
                                        }
                                        reader.endArray();
                                        break;
                                    }
                                default:
                                    reader.skipValue();
                                    break;
                            }
                        }
                        xCp1 = new PointF(xCp1x, xCp1y);
                        yCp1 = new PointF(yCp1x, yCp1y);
                        reader.endObject();
                        xInterpolator2 = xInterpolator3;
                        yInterpolator2 = yInterpolator3;
                        pathCp2 = pathCp22;
                        pathCp1 = pathCp12;
                        startFrame = startFrame2;
                        yCp2 = yCp22;
                        break;
                    } else {
                        cp1 = JsonUtils.jsonToPoint(reader, scale);
                        xInterpolator2 = xInterpolator3;
                        yInterpolator2 = yInterpolator3;
                        pathCp2 = pathCp22;
                        pathCp1 = pathCp12;
                        startFrame = startFrame2;
                        yCp2 = yCp22;
                        break;
                    }
                case 4:
                    Interpolator yInterpolator4 = yInterpolator2;
                    if (reader.peek() == JsonReader.Token.BEGIN_OBJECT) {
                        reader.beginObject();
                        float xCp2x = 0.0f;
                        float xCp2y = 0.0f;
                        PointF pathCp13 = pathCp1;
                        PointF pathCp23 = pathCp2;
                        float yCp2x2 = 0.0f;
                        float yCp2y2 = 0.0f;
                        while (reader.hasNext()) {
                            float startFrame3 = startFrame;
                            switch (reader.selectName(INTERPOLATOR_NAMES)) {
                                case 0:
                                    PointF yCp23 = yCp2;
                                    if (reader.peek() == JsonReader.Token.NUMBER) {
                                        xCp2x = (float) reader.nextDouble();
                                        yCp2x2 = xCp2x;
                                        startFrame = startFrame3;
                                        yCp2 = yCp23;
                                        break;
                                    } else {
                                        reader.beginArray();
                                        xCp2x = (float) reader.nextDouble();
                                        if (reader.peek() == JsonReader.Token.NUMBER) {
                                            yCp2x = (float) reader.nextDouble();
                                        } else {
                                            yCp2x = xCp2x;
                                        }
                                        yCp2x2 = yCp2x;
                                        reader.endArray();
                                        startFrame = startFrame3;
                                        yCp2 = yCp23;
                                        break;
                                    }
                                case 1:
                                    PointF yCp24 = yCp2;
                                    if (reader.peek() == JsonReader.Token.NUMBER) {
                                        xCp2y = (float) reader.nextDouble();
                                        yCp2y2 = xCp2y;
                                        startFrame = startFrame3;
                                        yCp2 = yCp24;
                                        break;
                                    } else {
                                        reader.beginArray();
                                        xCp2y = (float) reader.nextDouble();
                                        if (reader.peek() == JsonReader.Token.NUMBER) {
                                            yCp2y = (float) reader.nextDouble();
                                        } else {
                                            yCp2y = xCp2y;
                                        }
                                        yCp2y2 = yCp2y;
                                        reader.endArray();
                                        startFrame = startFrame3;
                                        yCp2 = yCp24;
                                        break;
                                    }
                                default:
                                    reader.skipValue();
                                    startFrame = startFrame3;
                                    break;
                            }
                        }
                        xCp2 = new PointF(xCp2x, xCp2y);
                        yCp2 = new PointF(yCp2x2, yCp2y2);
                        reader.endObject();
                        xInterpolator2 = xInterpolator3;
                        yInterpolator2 = yInterpolator4;
                        pathCp2 = pathCp23;
                        pathCp1 = pathCp13;
                        break;
                    } else {
                        cp2 = JsonUtils.jsonToPoint(reader, scale);
                        xInterpolator2 = xInterpolator3;
                        yInterpolator2 = yInterpolator4;
                        break;
                    }
                case 5:
                    Interpolator yInterpolator5 = yInterpolator2;
                    hold = reader.nextInt() == 1;
                    xInterpolator2 = xInterpolator3;
                    yInterpolator2 = yInterpolator5;
                    break;
                case 6:
                    pathCp1 = JsonUtils.jsonToPoint(reader, scale);
                    xInterpolator2 = xInterpolator3;
                    break;
                case 7:
                    pathCp2 = JsonUtils.jsonToPoint(reader, scale);
                    xInterpolator2 = xInterpolator3;
                    break;
                default:
                    reader.skipValue();
                    xInterpolator2 = xInterpolator3;
                    break;
            }
        }
        PointF yCp25 = yCp2;
        float startFrame4 = startFrame;
        PointF pathCp14 = pathCp1;
        PointF pathCp24 = pathCp2;
        Interpolator xInterpolator4 = xInterpolator2;
        Interpolator yInterpolator6 = yInterpolator2;
        reader.endObject();
        if (hold) {
            endValue = startValue;
            interpolator = LINEAR_INTERPOLATOR;
            xInterpolator = xInterpolator4;
            yInterpolator = yInterpolator6;
        } else if (cp1 != null && cp2 != null) {
            interpolator = interpolatorFor(cp1, cp2);
            endValue = endValue2;
            xInterpolator = xInterpolator4;
            yInterpolator = yInterpolator6;
        } else if (xCp1 != null && yCp1 != null && xCp2 != null && yCp25 != null) {
            xInterpolator = interpolatorFor(xCp1, xCp2);
            yInterpolator = interpolatorFor(yCp1, yCp25);
            endValue = endValue2;
            interpolator = null;
        } else {
            interpolator = LINEAR_INTERPOLATOR;
            endValue = endValue2;
            xInterpolator = xInterpolator4;
            yInterpolator = yInterpolator6;
        }
        if (xInterpolator != null && yInterpolator != null) {
            keyframe = new Keyframe<>(composition, startValue, endValue, xInterpolator, yInterpolator, startFrame4, null);
        } else {
            keyframe = new Keyframe<>(composition, startValue, endValue, interpolator, startFrame4, null);
        }
        keyframe.pathCp1 = pathCp14;
        keyframe.pathCp2 = pathCp24;
        return keyframe;
    }

    private static Interpolator interpolatorFor(PointF cp1, PointF cp2) {
        Interpolator interpolator = null;
        cp1.x = MiscUtils.clamp(cp1.x, -1.0f, 1.0f);
        cp1.y = MiscUtils.clamp(cp1.y, -100.0f, MAX_CP_VALUE);
        cp2.x = MiscUtils.clamp(cp2.x, -1.0f, 1.0f);
        cp2.y = MiscUtils.clamp(cp2.y, -100.0f, MAX_CP_VALUE);
        int hash = Utils.hashFor(cp1.x, cp1.y, cp2.x, cp2.y);
        WeakReference<Interpolator> interpolatorRef = L.getDisablePathInterpolatorCache() ? null : getInterpolator(hash);
        if (interpolatorRef != null) {
            Interpolator interpolator2 = interpolatorRef.get();
            interpolator = interpolator2;
        }
        if (interpolatorRef == null || interpolator == null) {
            try {
                interpolator = PathInterpolatorCompat.create(cp1.x, cp1.y, cp2.x, cp2.y);
            } catch (IllegalArgumentException e) {
                if ("The Path cannot loop back on itself.".equals(e.getMessage())) {
                    interpolator = PathInterpolatorCompat.create(Math.min(cp1.x, 1.0f), cp1.y, Math.max(cp2.x, 0.0f), cp2.y);
                } else {
                    interpolator = new LinearInterpolator();
                }
            }
            if (!L.getDisablePathInterpolatorCache()) {
                try {
                    putInterpolator(hash, new WeakReference(interpolator));
                } catch (ArrayIndexOutOfBoundsException e2) {
                }
            }
        }
        return interpolator;
    }

    private static <T> Keyframe<T> parseStaticValue(JsonReader reader, float scale, ValueParser<T> valueParser) throws IOException {
        T value = valueParser.parse(reader, scale);
        return new Keyframe<>(value);
    }
}
