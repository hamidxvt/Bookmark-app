package com.airbnb.lottie;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.Marker;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class LottieComposition {
    private Rect bounds;
    private SparseArrayCompat<FontCharacter> characters;
    private float endFrame;
    private Map<String, Font> fonts;
    private float frameRate;
    private boolean hasDashPattern;
    private Map<String, LottieImageAsset> images;
    private float imagesDpScale;
    private LongSparseArray<Layer> layerMap;
    private List<Layer> layers;
    private List<Marker> markers;
    private Map<String, List<Layer>> precomps;
    private float startFrame;
    private final PerformanceTracker performanceTracker = new PerformanceTracker();
    private final HashSet<String> warnings = new HashSet<>();
    private int maskAndMatteCount = 0;

    public void init(Rect bounds, float startFrame, float endFrame, float frameRate, List<Layer> layers, LongSparseArray<Layer> layerMap, Map<String, List<Layer>> precomps, Map<String, LottieImageAsset> images, float imagesDpScale, SparseArrayCompat<FontCharacter> characters, Map<String, Font> fonts, List<Marker> markers) {
        this.bounds = bounds;
        this.startFrame = startFrame;
        this.endFrame = endFrame;
        this.frameRate = frameRate;
        this.layers = layers;
        this.layerMap = layerMap;
        this.precomps = precomps;
        this.images = images;
        this.imagesDpScale = imagesDpScale;
        this.characters = characters;
        this.fonts = fonts;
        this.markers = markers;
    }

    public void addWarning(String warning) {
        Logger.warning(warning);
        this.warnings.add(warning);
    }

    public void setHasDashPattern(boolean hasDashPattern) {
        this.hasDashPattern = hasDashPattern;
    }

    public void incrementMatteOrMaskCount(int amount) {
        this.maskAndMatteCount += amount;
    }

    public boolean hasDashPattern() {
        return this.hasDashPattern;
    }

    public int getMaskAndMatteCount() {
        return this.maskAndMatteCount;
    }

    public ArrayList<String> getWarnings() {
        return new ArrayList<>(Arrays.asList((String[]) this.warnings.toArray(new String[this.warnings.size()])));
    }

    public void setPerformanceTrackingEnabled(boolean enabled) {
        this.performanceTracker.setEnabled(enabled);
    }

    public PerformanceTracker getPerformanceTracker() {
        return this.performanceTracker;
    }

    public Layer layerModelForId(long id) {
        return this.layerMap.get(id);
    }

    public Rect getBounds() {
        return this.bounds;
    }

    public float getDuration() {
        return (long) ((getDurationFrames() / this.frameRate) * 1000.0f);
    }

    public float getStartFrame() {
        return this.startFrame;
    }

    public float getEndFrame() {
        return this.endFrame;
    }

    public float getFrameForProgress(float progress) {
        return MiscUtils.lerp(this.startFrame, this.endFrame, progress);
    }

    public float getProgressForFrame(float frame) {
        float framesSinceStart = frame - this.startFrame;
        float frameRange = this.endFrame - this.startFrame;
        return framesSinceStart / frameRange;
    }

    public float getFrameRate() {
        return this.frameRate;
    }

    public List<Layer> getLayers() {
        return this.layers;
    }

    public List<Layer> getPrecomps(String id) {
        return this.precomps.get(id);
    }

    public SparseArrayCompat<FontCharacter> getCharacters() {
        return this.characters;
    }

    public Map<String, Font> getFonts() {
        return this.fonts;
    }

    public List<Marker> getMarkers() {
        return this.markers;
    }

    public Marker getMarker(String markerName) {
        int size = this.markers.size();
        for (int i = 0; i < size; i++) {
            Marker marker = this.markers.get(i);
            if (marker.matchesName(markerName)) {
                return marker;
            }
        }
        return null;
    }

    public boolean hasImages() {
        return !this.images.isEmpty();
    }

    public Map<String, LottieImageAsset> getImages() {
        float dpScale = Utils.dpScale();
        if (dpScale != this.imagesDpScale) {
            Set<Map.Entry<String, LottieImageAsset>> entries = this.images.entrySet();
            for (Map.Entry<String, LottieImageAsset> entry : entries) {
                this.images.put(entry.getKey(), entry.getValue().copyWithScale(this.imagesDpScale / dpScale));
            }
        }
        this.imagesDpScale = dpScale;
        return this.images;
    }

    public float getDurationFrames() {
        return this.endFrame - this.startFrame;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LottieComposition:\n");
        for (Layer layer : this.layers) {
            sb.append(layer.toString("\t"));
        }
        return sb.toString();
    }

    @Deprecated
    public static class Factory {
        private Factory() {
        }

        @Deprecated
        public static Cancellable fromAssetFileName(Context context, String fileName, OnCompositionLoadedListener l) {
            ListenerAdapter listener = new ListenerAdapter(l);
            LottieCompositionFactory.fromAsset(context, fileName).addListener(listener);
            return listener;
        }

        @Deprecated
        public static Cancellable fromRawFile(Context context, int resId, OnCompositionLoadedListener l) {
            ListenerAdapter listener = new ListenerAdapter(l);
            LottieCompositionFactory.fromRawRes(context, resId).addListener(listener);
            return listener;
        }

        @Deprecated
        public static Cancellable fromInputStream(InputStream stream, OnCompositionLoadedListener l) {
            ListenerAdapter listener = new ListenerAdapter(l);
            LottieCompositionFactory.fromJsonInputStream(stream, null).addListener(listener);
            return listener;
        }

        @Deprecated
        public static Cancellable fromJsonString(String jsonString, OnCompositionLoadedListener l) {
            ListenerAdapter listener = new ListenerAdapter(l);
            LottieCompositionFactory.fromJsonString(jsonString, null).addListener(listener);
            return listener;
        }

        @Deprecated
        public static Cancellable fromJsonReader(JsonReader reader, OnCompositionLoadedListener l) {
            ListenerAdapter listener = new ListenerAdapter(l);
            LottieCompositionFactory.fromJsonReader(reader, null).addListener(listener);
            return listener;
        }

        @Deprecated
        public static LottieComposition fromFileSync(Context context, String fileName) {
            return LottieCompositionFactory.fromAssetSync(context, fileName).getValue();
        }

        @Deprecated
        public static LottieComposition fromInputStreamSync(InputStream stream) {
            return LottieCompositionFactory.fromJsonInputStreamSync(stream, null).getValue();
        }

        @Deprecated
        public static LottieComposition fromInputStreamSync(InputStream stream, boolean close) {
            if (close) {
                Logger.warning("Lottie now auto-closes input stream!");
            }
            return LottieCompositionFactory.fromJsonInputStreamSync(stream, null).getValue();
        }

        @Deprecated
        public static LottieComposition fromJsonSync(Resources res, JSONObject json) {
            return LottieCompositionFactory.fromJsonSync(json, null).getValue();
        }

        @Deprecated
        public static LottieComposition fromJsonSync(String json) {
            return LottieCompositionFactory.fromJsonStringSync(json, null).getValue();
        }

        @Deprecated
        public static LottieComposition fromJsonSync(JsonReader reader) {
            return LottieCompositionFactory.fromJsonReaderSync(reader, null).getValue();
        }

        private static final class ListenerAdapter implements LottieListener<LottieComposition>, Cancellable {
            private boolean cancelled;
            private final OnCompositionLoadedListener listener;

            private ListenerAdapter(OnCompositionLoadedListener listener) {
                this.cancelled = false;
                this.listener = listener;
            }

            @Override // com.airbnb.lottie.LottieListener
            public void onResult(LottieComposition composition) {
                if (this.cancelled) {
                    return;
                }
                this.listener.onCompositionLoaded(composition);
            }

            @Override // com.airbnb.lottie.Cancellable
            public void cancel() {
                this.cancelled = true;
            }
        }
    }
}
