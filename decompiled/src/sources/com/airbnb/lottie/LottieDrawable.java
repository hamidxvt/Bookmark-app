package com.airbnb.lottie;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.manager.FontAssetManager;
import com.airbnb.lottie.manager.ImageAssetManager;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.Marker;
import com.airbnb.lottie.model.layer.CompositionLayer;
import com.airbnb.lottie.parser.LayerParser;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.LottieThreadFactory;
import com.airbnb.lottie.utils.LottieValueAnimator;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class LottieDrawable extends Drawable implements Drawable.Callback, Animatable {
    public static final int INFINITE = -1;
    private static final float MAX_DELTA_MS_ASYNC_SET_PROGRESS = 50.0f;
    public static final int RESTART = 1;
    public static final int REVERSE = 2;
    private AsyncUpdates asyncUpdates;
    private Rect canvasClipBounds;
    private RectF canvasClipBoundsRectF;
    private LottieComposition composition;
    private CompositionLayer compositionLayer;
    String defaultFontFileExtension;
    private boolean enableMergePaths;
    FontAssetDelegate fontAssetDelegate;
    private FontAssetManager fontAssetManager;
    private Map<String, Typeface> fontMap;
    private ImageAssetDelegate imageAssetDelegate;
    private ImageAssetManager imageAssetManager;
    private String imageAssetsFolder;
    private Runnable invalidateSelfRunnable;
    private boolean isApplyingOpacityToLayersEnabled;
    private Handler mainThreadHandler;
    private boolean outlineMasksAndMattes;
    private boolean performanceTrackingEnabled;
    private Bitmap softwareRenderingBitmap;
    private Canvas softwareRenderingCanvas;
    private Rect softwareRenderingDstBoundsRect;
    private RectF softwareRenderingDstBoundsRectF;
    private Matrix softwareRenderingOriginalCanvasMatrix;
    private Matrix softwareRenderingOriginalCanvasMatrixInverse;
    private Paint softwareRenderingPaint;
    private Rect softwareRenderingSrcBoundsRect;
    private RectF softwareRenderingTransformedBounds;
    TextDelegate textDelegate;
    private static final boolean invalidateSelfOnMainThread = false;
    private static final List<String> ALLOWED_REDUCED_MOTION_MARKERS = Arrays.asList("reduced motion", "reduced_motion", "reduced-motion", "reducedmotion");
    private static final Executor setProgressExecutor = new ThreadPoolExecutor(0, 2, 35, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new LottieThreadFactory());
    private final LottieValueAnimator animator = new LottieValueAnimator();
    private boolean systemAnimationsEnabled = true;
    private boolean ignoreSystemAnimationsDisabled = false;
    private boolean safeMode = false;
    private OnVisibleAction onVisibleAction = OnVisibleAction.NONE;
    private final ArrayList<LazyCompositionTask> lazyCompositionTasks = new ArrayList<>();
    private boolean maintainOriginalImageBounds = false;
    private boolean clipToCompositionBounds = true;
    private int alpha = 255;
    private boolean clipTextToBoundingBox = false;
    private RenderMode renderMode = RenderMode.AUTOMATIC;
    private boolean useSoftwareRendering = false;
    private final Matrix renderingMatrix = new Matrix();
    private boolean isDirty = false;
    private final ValueAnimator.AnimatorUpdateListener progressUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda13
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            LottieDrawable.this.m192lambda$new$0$comairbnblottieLottieDrawable(valueAnimator);
        }
    };
    private final Semaphore setProgressDrawLock = new Semaphore(1);
    private final Runnable updateProgressRunnable = new Runnable() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda14
        @Override // java.lang.Runnable
        public final void run() {
            LottieDrawable.this.m194lambda$new$2$comairbnblottieLottieDrawable();
        }
    };
    private float lastDrawnProgress = -3.4028235E38f;

    /* JADX INFO: Access modifiers changed from: private */
    interface LazyCompositionTask {
        void run(LottieComposition lottieComposition);
    }

    private enum OnVisibleAction {
        NONE,
        PLAY,
        RESUME
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RepeatMode {
    }

    /* renamed from: lambda$new$0$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m192lambda$new$0$comairbnblottieLottieDrawable(ValueAnimator animation) {
        if (getAsyncUpdatesEnabled()) {
            invalidateSelf();
        } else if (this.compositionLayer != null) {
            this.compositionLayer.setProgress(this.animator.getAnimatedValueAbsolute());
        }
    }

    /* renamed from: lambda$new$2$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m194lambda$new$2$comairbnblottieLottieDrawable() {
        CompositionLayer compositionLayer = this.compositionLayer;
        if (compositionLayer == null) {
            return;
        }
        try {
            this.setProgressDrawLock.acquire();
            compositionLayer.setProgress(this.animator.getAnimatedValueAbsolute());
            if (invalidateSelfOnMainThread && this.isDirty) {
                if (this.mainThreadHandler == null) {
                    this.mainThreadHandler = new Handler(Looper.getMainLooper());
                    this.invalidateSelfRunnable = new Runnable() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda8
                        @Override // java.lang.Runnable
                        public final void run() {
                            LottieDrawable.this.m193lambda$new$1$comairbnblottieLottieDrawable();
                        }
                    };
                }
                this.mainThreadHandler.post(this.invalidateSelfRunnable);
            }
        } catch (InterruptedException e) {
        } catch (Throwable th) {
            this.setProgressDrawLock.release();
            throw th;
        }
        this.setProgressDrawLock.release();
    }

    /* renamed from: lambda$new$1$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m193lambda$new$1$comairbnblottieLottieDrawable() {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public LottieDrawable() {
        this.animator.addUpdateListener(this.progressUpdateListener);
    }

    public boolean hasMasks() {
        return this.compositionLayer != null && this.compositionLayer.hasMasks();
    }

    public boolean hasMatte() {
        return this.compositionLayer != null && this.compositionLayer.hasMatte();
    }

    public boolean enableMergePathsForKitKatAndAbove() {
        return this.enableMergePaths;
    }

    public void enableMergePathsForKitKatAndAbove(boolean enable) {
        if (this.enableMergePaths == enable) {
            return;
        }
        this.enableMergePaths = enable;
        if (this.composition != null) {
            buildCompositionLayer();
        }
    }

    public boolean isMergePathsEnabledForKitKatAndAbove() {
        return this.enableMergePaths;
    }

    public void setClipToCompositionBounds(boolean clipToCompositionBounds) {
        if (clipToCompositionBounds != this.clipToCompositionBounds) {
            this.clipToCompositionBounds = clipToCompositionBounds;
            CompositionLayer compositionLayer = this.compositionLayer;
            if (compositionLayer != null) {
                compositionLayer.setClipToCompositionBounds(clipToCompositionBounds);
            }
            invalidateSelf();
        }
    }

    public boolean getClipToCompositionBounds() {
        return this.clipToCompositionBounds;
    }

    public void setImagesAssetsFolder(String imageAssetsFolder) {
        this.imageAssetsFolder = imageAssetsFolder;
    }

    public String getImageAssetsFolder() {
        return this.imageAssetsFolder;
    }

    public void setMaintainOriginalImageBounds(boolean maintainOriginalImageBounds) {
        this.maintainOriginalImageBounds = maintainOriginalImageBounds;
    }

    public boolean getMaintainOriginalImageBounds() {
        return this.maintainOriginalImageBounds;
    }

    public boolean setComposition(LottieComposition composition) {
        if (this.composition == composition) {
            return false;
        }
        this.isDirty = true;
        clearComposition();
        this.composition = composition;
        buildCompositionLayer();
        this.animator.setComposition(composition);
        setProgress(this.animator.getAnimatedFraction());
        Iterator<LazyCompositionTask> it = new ArrayList(this.lazyCompositionTasks).iterator();
        while (it.hasNext()) {
            LazyCompositionTask t = it.next();
            if (t != null) {
                t.run(composition);
            }
            it.remove();
        }
        this.lazyCompositionTasks.clear();
        composition.setPerformanceTrackingEnabled(this.performanceTrackingEnabled);
        computeRenderMode();
        Drawable.Callback callback = getCallback();
        if (callback instanceof ImageView) {
            ((ImageView) callback).setImageDrawable(null);
            ((ImageView) callback).setImageDrawable(this);
        }
        return true;
    }

    public void setRenderMode(RenderMode renderMode) {
        this.renderMode = renderMode;
        computeRenderMode();
    }

    public AsyncUpdates getAsyncUpdates() {
        AsyncUpdates asyncUpdates = this.asyncUpdates;
        if (asyncUpdates != null) {
            return asyncUpdates;
        }
        return L.getDefaultAsyncUpdates();
    }

    public boolean getAsyncUpdatesEnabled() {
        return getAsyncUpdates() == AsyncUpdates.ENABLED;
    }

    public void setAsyncUpdates(AsyncUpdates asyncUpdates) {
        this.asyncUpdates = asyncUpdates;
    }

    public RenderMode getRenderMode() {
        return this.useSoftwareRendering ? RenderMode.SOFTWARE : RenderMode.HARDWARE;
    }

    private void computeRenderMode() {
        LottieComposition composition = this.composition;
        if (composition == null) {
            return;
        }
        this.useSoftwareRendering = this.renderMode.useSoftwareRendering(Build.VERSION.SDK_INT, composition.hasDashPattern(), composition.getMaskAndMatteCount());
    }

    public void setPerformanceTrackingEnabled(boolean enabled) {
        this.performanceTrackingEnabled = enabled;
        if (this.composition != null) {
            this.composition.setPerformanceTrackingEnabled(enabled);
        }
    }

    public void setOutlineMasksAndMattes(boolean outline) {
        if (this.outlineMasksAndMattes == outline) {
            return;
        }
        this.outlineMasksAndMattes = outline;
        if (this.compositionLayer != null) {
            this.compositionLayer.setOutlineMasksAndMattes(outline);
        }
    }

    public PerformanceTracker getPerformanceTracker() {
        if (this.composition != null) {
            return this.composition.getPerformanceTracker();
        }
        return null;
    }

    public void setApplyingOpacityToLayersEnabled(boolean isApplyingOpacityToLayersEnabled) {
        this.isApplyingOpacityToLayersEnabled = isApplyingOpacityToLayersEnabled;
    }

    @Deprecated
    public void disableExtraScaleModeInFitXY() {
    }

    public boolean isApplyingOpacityToLayersEnabled() {
        return this.isApplyingOpacityToLayersEnabled;
    }

    public boolean getClipTextToBoundingBox() {
        return this.clipTextToBoundingBox;
    }

    public void setClipTextToBoundingBox(boolean clipTextToBoundingBox) {
        if (clipTextToBoundingBox != this.clipTextToBoundingBox) {
            this.clipTextToBoundingBox = clipTextToBoundingBox;
            invalidateSelf();
        }
    }

    private void buildCompositionLayer() {
        LottieComposition composition = this.composition;
        if (composition == null) {
            return;
        }
        this.compositionLayer = new CompositionLayer(this, LayerParser.parse(composition), composition.getLayers(), composition);
        if (this.outlineMasksAndMattes) {
            this.compositionLayer.setOutlineMasksAndMattes(true);
        }
        this.compositionLayer.setClipToCompositionBounds(this.clipToCompositionBounds);
    }

    public void clearComposition() {
        if (this.animator.isRunning()) {
            this.animator.cancel();
            if (!isVisible()) {
                this.onVisibleAction = OnVisibleAction.NONE;
            }
        }
        this.composition = null;
        this.compositionLayer = null;
        this.imageAssetManager = null;
        this.lastDrawnProgress = -3.4028235E38f;
        this.animator.clearComposition();
        invalidateSelf();
    }

    public void setSafeMode(boolean safeMode) {
        this.safeMode = safeMode;
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable.Callback callback;
        if (this.isDirty) {
            return;
        }
        this.isDirty = true;
        if ((!invalidateSelfOnMainThread || Looper.getMainLooper() == Looper.myLooper()) && (callback = getCallback()) != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int alpha) {
        this.alpha = alpha;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.alpha;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Logger.warning("Use addColorFilter instead.");
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    private boolean shouldSetProgressBeforeDrawing() {
        LottieComposition composition = this.composition;
        if (composition == null) {
            return false;
        }
        float lastDrawnProgress = this.lastDrawnProgress;
        float currentProgress = this.animator.getAnimatedValueAbsolute();
        this.lastDrawnProgress = currentProgress;
        float duration = composition.getDuration();
        float deltaProgress = Math.abs(currentProgress - lastDrawnProgress);
        float deltaMs = deltaProgress * duration;
        return deltaMs >= 50.0f;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        CompositionLayer compositionLayer = this.compositionLayer;
        if (compositionLayer == null) {
            return;
        }
        boolean asyncUpdatesEnabled = getAsyncUpdatesEnabled();
        if (asyncUpdatesEnabled) {
            try {
                this.setProgressDrawLock.acquire();
            } catch (InterruptedException e) {
                L.endSection("Drawable#draw");
                if (!asyncUpdatesEnabled) {
                    return;
                }
                this.setProgressDrawLock.release();
                if (compositionLayer.getProgress() == this.animator.getAnimatedValueAbsolute()) {
                    return;
                }
            } catch (Throwable th) {
                L.endSection("Drawable#draw");
                if (asyncUpdatesEnabled) {
                    this.setProgressDrawLock.release();
                    if (compositionLayer.getProgress() != this.animator.getAnimatedValueAbsolute()) {
                        setProgressExecutor.execute(this.updateProgressRunnable);
                    }
                }
                throw th;
            }
        }
        L.beginSection("Drawable#draw");
        if (asyncUpdatesEnabled && shouldSetProgressBeforeDrawing()) {
            setProgress(this.animator.getAnimatedValueAbsolute());
        }
        if (this.safeMode) {
            try {
                if (this.useSoftwareRendering) {
                    renderAndDrawAsBitmap(canvas, compositionLayer);
                } else {
                    drawDirectlyToCanvas(canvas);
                }
            } catch (Throwable e2) {
                Logger.error("Lottie crashed in draw!", e2);
            }
        } else if (this.useSoftwareRendering) {
            renderAndDrawAsBitmap(canvas, compositionLayer);
        } else {
            drawDirectlyToCanvas(canvas);
        }
        this.isDirty = false;
        L.endSection("Drawable#draw");
        if (asyncUpdatesEnabled) {
            this.setProgressDrawLock.release();
            if (compositionLayer.getProgress() == this.animator.getAnimatedValueAbsolute()) {
                return;
            }
            setProgressExecutor.execute(this.updateProgressRunnable);
        }
    }

    public void draw(Canvas canvas, Matrix matrix) {
        CompositionLayer compositionLayer = this.compositionLayer;
        LottieComposition composition = this.composition;
        if (compositionLayer == null || composition == null) {
            return;
        }
        boolean asyncUpdatesEnabled = getAsyncUpdatesEnabled();
        if (asyncUpdatesEnabled) {
            try {
                this.setProgressDrawLock.acquire();
                if (shouldSetProgressBeforeDrawing()) {
                    setProgress(this.animator.getAnimatedValueAbsolute());
                }
            } catch (InterruptedException e) {
                if (asyncUpdatesEnabled) {
                    this.setProgressDrawLock.release();
                    if (compositionLayer.getProgress() == this.animator.getAnimatedValueAbsolute()) {
                        return;
                    }
                } else {
                    return;
                }
            } catch (Throwable th) {
                if (asyncUpdatesEnabled) {
                    this.setProgressDrawLock.release();
                    if (compositionLayer.getProgress() != this.animator.getAnimatedValueAbsolute()) {
                        setProgressExecutor.execute(this.updateProgressRunnable);
                    }
                }
                throw th;
            }
        }
        if (this.useSoftwareRendering) {
            canvas.save();
            canvas.concat(matrix);
            renderAndDrawAsBitmap(canvas, compositionLayer);
            canvas.restore();
        } else {
            compositionLayer.draw(canvas, matrix, this.alpha);
        }
        this.isDirty = false;
        if (asyncUpdatesEnabled) {
            this.setProgressDrawLock.release();
            if (compositionLayer.getProgress() == this.animator.getAnimatedValueAbsolute()) {
                return;
            }
            setProgressExecutor.execute(this.updateProgressRunnable);
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable.Callback callback = getCallback();
        if ((callback instanceof View) && ((View) callback).isInEditMode()) {
            return;
        }
        playAnimation();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        endAnimation();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return isAnimating();
    }

    public void playAnimation() {
        if (this.compositionLayer == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda15
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m195lambda$playAnimation$3$comairbnblottieLottieDrawable(lottieComposition);
                }
            });
            return;
        }
        computeRenderMode();
        if (animationsEnabled() || getRepeatCount() == 0) {
            if (isVisible()) {
                this.animator.playAnimation();
                this.onVisibleAction = OnVisibleAction.NONE;
            } else {
                this.onVisibleAction = OnVisibleAction.PLAY;
            }
        }
        if (!animationsEnabled()) {
            Marker markerForAnimationsDisabled = getMarkerForAnimationsDisabled();
            if (markerForAnimationsDisabled != null) {
                setFrame((int) markerForAnimationsDisabled.startFrame);
            } else {
                setFrame((int) (getSpeed() < 0.0f ? getMinFrame() : getMaxFrame()));
            }
            this.animator.endAnimation();
            if (!isVisible()) {
                this.onVisibleAction = OnVisibleAction.NONE;
            }
        }
    }

    /* renamed from: lambda$playAnimation$3$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m195lambda$playAnimation$3$comairbnblottieLottieDrawable(LottieComposition c) {
        playAnimation();
    }

    private Marker getMarkerForAnimationsDisabled() {
        Marker marker = null;
        for (String markerName : ALLOWED_REDUCED_MOTION_MARKERS) {
            marker = this.composition.getMarker(markerName);
            if (marker != null) {
                break;
            }
        }
        return marker;
    }

    public void endAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.endAnimation();
        if (!isVisible()) {
            this.onVisibleAction = OnVisibleAction.NONE;
        }
    }

    public void resumeAnimation() {
        if (this.compositionLayer == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda9
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m196lambda$resumeAnimation$4$comairbnblottieLottieDrawable(lottieComposition);
                }
            });
            return;
        }
        computeRenderMode();
        if (animationsEnabled() || getRepeatCount() == 0) {
            if (isVisible()) {
                this.animator.resumeAnimation();
                this.onVisibleAction = OnVisibleAction.NONE;
            } else {
                this.onVisibleAction = OnVisibleAction.RESUME;
            }
        }
        if (!animationsEnabled()) {
            setFrame((int) (getSpeed() < 0.0f ? getMinFrame() : getMaxFrame()));
            this.animator.endAnimation();
            if (!isVisible()) {
                this.onVisibleAction = OnVisibleAction.NONE;
            }
        }
    }

    /* renamed from: lambda$resumeAnimation$4$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m196lambda$resumeAnimation$4$comairbnblottieLottieDrawable(LottieComposition c) {
        resumeAnimation();
    }

    public void setMinFrame(final int minFrame) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda5
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m205lambda$setMinFrame$5$comairbnblottieLottieDrawable(minFrame, lottieComposition);
                }
            });
        } else {
            this.animator.setMinFrame(minFrame);
        }
    }

    /* renamed from: lambda$setMinFrame$5$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m205lambda$setMinFrame$5$comairbnblottieLottieDrawable(int minFrame, LottieComposition c) {
        setMinFrame(minFrame);
    }

    public float getMinFrame() {
        return this.animator.getMinFrame();
    }

    public void setMinProgress(final float minProgress) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda16
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m207lambda$setMinProgress$6$comairbnblottieLottieDrawable(minProgress, lottieComposition);
                }
            });
        } else {
            setMinFrame((int) MiscUtils.lerp(this.composition.getStartFrame(), this.composition.getEndFrame(), minProgress));
        }
    }

    /* renamed from: lambda$setMinProgress$6$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m207lambda$setMinProgress$6$comairbnblottieLottieDrawable(float minProgress, LottieComposition c) {
        setMinProgress(minProgress);
    }

    public void setMaxFrame(final int maxFrame) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda4
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m199lambda$setMaxFrame$7$comairbnblottieLottieDrawable(maxFrame, lottieComposition);
                }
            });
        } else {
            this.animator.setMaxFrame(maxFrame + 0.99f);
        }
    }

    /* renamed from: lambda$setMaxFrame$7$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m199lambda$setMaxFrame$7$comairbnblottieLottieDrawable(int maxFrame, LottieComposition c) {
        setMaxFrame(maxFrame);
    }

    public float getMaxFrame() {
        return this.animator.getMaxFrame();
    }

    public void setMaxProgress(final float maxProgress) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda7
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m200lambda$setMaxProgress$8$comairbnblottieLottieDrawable(maxProgress, lottieComposition);
                }
            });
        } else {
            this.animator.setMaxFrame(MiscUtils.lerp(this.composition.getStartFrame(), this.composition.getEndFrame(), maxProgress));
        }
    }

    /* renamed from: lambda$setMaxProgress$8$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m200lambda$setMaxProgress$8$comairbnblottieLottieDrawable(float maxProgress, LottieComposition c) {
        setMaxProgress(maxProgress);
    }

    public void setMinFrame(final String markerName) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda12
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m206lambda$setMinFrame$9$comairbnblottieLottieDrawable(markerName, lottieComposition);
                }
            });
            return;
        }
        Marker marker = this.composition.getMarker(markerName);
        if (marker == null) {
            throw new IllegalArgumentException("Cannot find marker with name " + markerName + ".");
        }
        setMinFrame((int) marker.startFrame);
    }

    /* renamed from: lambda$setMinFrame$9$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m206lambda$setMinFrame$9$comairbnblottieLottieDrawable(String markerName, LottieComposition c) {
        setMinFrame(markerName);
    }

    public void setMaxFrame(final String markerName) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda11
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m198lambda$setMaxFrame$10$comairbnblottieLottieDrawable(markerName, lottieComposition);
                }
            });
            return;
        }
        Marker marker = this.composition.getMarker(markerName);
        if (marker == null) {
            throw new IllegalArgumentException("Cannot find marker with name " + markerName + ".");
        }
        setMaxFrame((int) (marker.startFrame + marker.durationFrames));
    }

    /* renamed from: lambda$setMaxFrame$10$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m198lambda$setMaxFrame$10$comairbnblottieLottieDrawable(String markerName, LottieComposition c) {
        setMaxFrame(markerName);
    }

    public void setMinAndMaxFrame(final String markerName) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda0
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m201lambda$setMinAndMaxFrame$11$comairbnblottieLottieDrawable(markerName, lottieComposition);
                }
            });
            return;
        }
        Marker marker = this.composition.getMarker(markerName);
        if (marker == null) {
            throw new IllegalArgumentException("Cannot find marker with name " + markerName + ".");
        }
        int startFrame = (int) marker.startFrame;
        setMinAndMaxFrame(startFrame, ((int) marker.durationFrames) + startFrame);
    }

    /* renamed from: lambda$setMinAndMaxFrame$11$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m201lambda$setMinAndMaxFrame$11$comairbnblottieLottieDrawable(String markerName, LottieComposition c) {
        setMinAndMaxFrame(markerName);
    }

    public void setMinAndMaxFrame(final String startMarkerName, final String endMarkerName, final boolean playEndMarkerStartFrame) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda10
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m202lambda$setMinAndMaxFrame$12$comairbnblottieLottieDrawable(startMarkerName, endMarkerName, playEndMarkerStartFrame, lottieComposition);
                }
            });
            return;
        }
        Marker startMarker = this.composition.getMarker(startMarkerName);
        if (startMarker == null) {
            throw new IllegalArgumentException("Cannot find marker with name " + startMarkerName + ".");
        }
        int startFrame = (int) startMarker.startFrame;
        Marker endMarker = this.composition.getMarker(endMarkerName);
        if (endMarker == null) {
            throw new IllegalArgumentException("Cannot find marker with name " + endMarkerName + ".");
        }
        int endFrame = (int) (endMarker.startFrame + (playEndMarkerStartFrame ? 1.0f : 0.0f));
        setMinAndMaxFrame(startFrame, endFrame);
    }

    /* renamed from: lambda$setMinAndMaxFrame$12$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m202lambda$setMinAndMaxFrame$12$comairbnblottieLottieDrawable(String startMarkerName, String endMarkerName, boolean playEndMarkerStartFrame, LottieComposition c) {
        setMinAndMaxFrame(startMarkerName, endMarkerName, playEndMarkerStartFrame);
    }

    public void setMinAndMaxFrame(final int minFrame, final int maxFrame) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda3
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m203lambda$setMinAndMaxFrame$13$comairbnblottieLottieDrawable(minFrame, maxFrame, lottieComposition);
                }
            });
        } else {
            this.animator.setMinAndMaxFrames(minFrame, maxFrame + 0.99f);
        }
    }

    /* renamed from: lambda$setMinAndMaxFrame$13$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m203lambda$setMinAndMaxFrame$13$comairbnblottieLottieDrawable(int minFrame, int maxFrame, LottieComposition c) {
        setMinAndMaxFrame(minFrame, maxFrame);
    }

    public void setMinAndMaxProgress(final float minProgress, final float maxProgress) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda2
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m204lambda$setMinAndMaxProgress$14$comairbnblottieLottieDrawable(minProgress, maxProgress, lottieComposition);
                }
            });
        } else {
            setMinAndMaxFrame((int) MiscUtils.lerp(this.composition.getStartFrame(), this.composition.getEndFrame(), minProgress), (int) MiscUtils.lerp(this.composition.getStartFrame(), this.composition.getEndFrame(), maxProgress));
        }
    }

    /* renamed from: lambda$setMinAndMaxProgress$14$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m204lambda$setMinAndMaxProgress$14$comairbnblottieLottieDrawable(float minProgress, float maxProgress, LottieComposition c) {
        setMinAndMaxProgress(minProgress, maxProgress);
    }

    public void reverseAnimationSpeed() {
        this.animator.reverseAnimationSpeed();
    }

    public void setSpeed(float speed) {
        this.animator.setSpeed(speed);
    }

    public float getSpeed() {
        return this.animator.getSpeed();
    }

    public void addAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener updateListener) {
        this.animator.addUpdateListener(updateListener);
    }

    public void removeAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener updateListener) {
        this.animator.removeUpdateListener(updateListener);
    }

    public void removeAllUpdateListeners() {
        this.animator.removeAllUpdateListeners();
        this.animator.addUpdateListener(this.progressUpdateListener);
    }

    public void addAnimatorListener(Animator.AnimatorListener listener) {
        this.animator.addListener(listener);
    }

    public void removeAnimatorListener(Animator.AnimatorListener listener) {
        this.animator.removeListener(listener);
    }

    public void removeAllAnimatorListeners() {
        this.animator.removeAllListeners();
    }

    public void addAnimatorPauseListener(Animator.AnimatorPauseListener listener) {
        this.animator.addPauseListener(listener);
    }

    public void removeAnimatorPauseListener(Animator.AnimatorPauseListener listener) {
        this.animator.removePauseListener(listener);
    }

    public void setFrame(final int frame) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda1
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m197lambda$setFrame$15$comairbnblottieLottieDrawable(frame, lottieComposition);
                }
            });
        } else {
            this.animator.setFrame(frame);
        }
    }

    /* renamed from: lambda$setFrame$15$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m197lambda$setFrame$15$comairbnblottieLottieDrawable(int frame, LottieComposition c) {
        setFrame(frame);
    }

    public int getFrame() {
        return (int) this.animator.getFrame();
    }

    public void setProgress(final float progress) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda17
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m208lambda$setProgress$16$comairbnblottieLottieDrawable(progress, lottieComposition);
                }
            });
            return;
        }
        L.beginSection("Drawable#setProgress");
        this.animator.setFrame(this.composition.getFrameForProgress(progress));
        L.endSection("Drawable#setProgress");
    }

    /* renamed from: lambda$setProgress$16$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m208lambda$setProgress$16$comairbnblottieLottieDrawable(float progress, LottieComposition c) {
        setProgress(progress);
    }

    @Deprecated
    public void loop(boolean loop) {
        this.animator.setRepeatCount(loop ? -1 : 0);
    }

    public void setRepeatMode(int mode) {
        this.animator.setRepeatMode(mode);
    }

    public int getRepeatMode() {
        return this.animator.getRepeatMode();
    }

    public void setRepeatCount(int count) {
        this.animator.setRepeatCount(count);
    }

    public int getRepeatCount() {
        return this.animator.getRepeatCount();
    }

    public boolean isLooping() {
        return this.animator.getRepeatCount() == -1;
    }

    public boolean isAnimating() {
        if (this.animator == null) {
            return false;
        }
        return this.animator.isRunning();
    }

    boolean isAnimatingOrWillAnimateOnVisible() {
        if (isVisible()) {
            return this.animator.isRunning();
        }
        return this.onVisibleAction == OnVisibleAction.PLAY || this.onVisibleAction == OnVisibleAction.RESUME;
    }

    private boolean animationsEnabled() {
        return this.systemAnimationsEnabled || this.ignoreSystemAnimationsDisabled;
    }

    public void setSystemAnimationsAreEnabled(Boolean areEnabled) {
        this.systemAnimationsEnabled = areEnabled.booleanValue();
    }

    public void setIgnoreDisabledSystemAnimations(boolean ignore) {
        this.ignoreSystemAnimationsDisabled = ignore;
    }

    public void setUseCompositionFrameRate(boolean useCompositionFrameRate) {
        this.animator.setUseCompositionFrameRate(useCompositionFrameRate);
    }

    public void setImageAssetDelegate(ImageAssetDelegate assetDelegate) {
        this.imageAssetDelegate = assetDelegate;
        if (this.imageAssetManager != null) {
            this.imageAssetManager.setDelegate(assetDelegate);
        }
    }

    public void setFontAssetDelegate(FontAssetDelegate assetDelegate) {
        this.fontAssetDelegate = assetDelegate;
        if (this.fontAssetManager != null) {
            this.fontAssetManager.setDelegate(assetDelegate);
        }
    }

    public void setFontMap(Map<String, Typeface> fontMap) {
        if (fontMap == this.fontMap) {
            return;
        }
        this.fontMap = fontMap;
        invalidateSelf();
    }

    public void setTextDelegate(TextDelegate textDelegate) {
        this.textDelegate = textDelegate;
    }

    public TextDelegate getTextDelegate() {
        return this.textDelegate;
    }

    public boolean useTextGlyphs() {
        return this.fontMap == null && this.textDelegate == null && this.composition.getCharacters().size() > 0;
    }

    public LottieComposition getComposition() {
        return this.composition;
    }

    public void cancelAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.cancel();
        if (!isVisible()) {
            this.onVisibleAction = OnVisibleAction.NONE;
        }
    }

    public void pauseAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.pauseAnimation();
        if (!isVisible()) {
            this.onVisibleAction = OnVisibleAction.NONE;
        }
    }

    public float getProgress() {
        return this.animator.getAnimatedValueAbsolute();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.composition == null) {
            return -1;
        }
        return this.composition.getBounds().width();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.composition == null) {
            return -1;
        }
        return this.composition.getBounds().height();
    }

    public List<KeyPath> resolveKeyPath(KeyPath keyPath) {
        if (this.compositionLayer == null) {
            Logger.warning("Cannot resolve KeyPath. Composition is not set yet.");
            return Collections.emptyList();
        }
        List<KeyPath> keyPaths = new ArrayList<>();
        this.compositionLayer.resolveKeyPath(keyPath, 0, keyPaths, new KeyPath(new String[0]));
        return keyPaths;
    }

    public <T> void addValueCallback(final KeyPath keyPath, final T property, final LottieValueCallback<T> callback) {
        boolean invalidate;
        if (this.compositionLayer == null) {
            this.lazyCompositionTasks.add(new LazyCompositionTask() { // from class: com.airbnb.lottie.LottieDrawable$$ExternalSyntheticLambda6
                @Override // com.airbnb.lottie.LottieDrawable.LazyCompositionTask
                public final void run(LottieComposition lottieComposition) {
                    LottieDrawable.this.m191lambda$addValueCallback$17$comairbnblottieLottieDrawable(keyPath, property, callback, lottieComposition);
                }
            });
            return;
        }
        if (keyPath == KeyPath.COMPOSITION) {
            this.compositionLayer.addValueCallback(property, callback);
            invalidate = true;
        } else if (keyPath.getResolvedElement() != null) {
            keyPath.getResolvedElement().addValueCallback(property, callback);
            invalidate = true;
        } else {
            List<KeyPath> elements = resolveKeyPath(keyPath);
            for (int i = 0; i < elements.size(); i++) {
                elements.get(i).getResolvedElement().addValueCallback(property, callback);
            }
            invalidate = !elements.isEmpty();
        }
        if (invalidate) {
            invalidateSelf();
            if (property == LottieProperty.TIME_REMAP) {
                setProgress(getProgress());
            }
        }
    }

    /* renamed from: lambda$addValueCallback$17$com-airbnb-lottie-LottieDrawable, reason: not valid java name */
    /* synthetic */ void m191lambda$addValueCallback$17$comairbnblottieLottieDrawable(KeyPath keyPath, Object property, LottieValueCallback callback, LottieComposition c) {
        addValueCallback(keyPath, (KeyPath) property, (LottieValueCallback<KeyPath>) callback);
    }

    public <T> void addValueCallback(KeyPath keyPath, T property, final SimpleLottieValueCallback<T> callback) {
        addValueCallback(keyPath, (KeyPath) property, (LottieValueCallback<KeyPath>) new LottieValueCallback<T>() { // from class: com.airbnb.lottie.LottieDrawable.1
            @Override // com.airbnb.lottie.value.LottieValueCallback
            public T getValue(LottieFrameInfo<T> lottieFrameInfo) {
                return (T) callback.getValue(lottieFrameInfo);
            }
        });
    }

    public Bitmap updateBitmap(String id, Bitmap bitmap) {
        ImageAssetManager bm = getImageAssetManager();
        if (bm == null) {
            Logger.warning("Cannot update bitmap. Most likely the drawable is not added to a View which prevents Lottie from getting a Context.");
            return null;
        }
        Bitmap ret = bm.updateBitmap(id, bitmap);
        invalidateSelf();
        return ret;
    }

    @Deprecated
    public Bitmap getImageAsset(String id) {
        ImageAssetManager bm = getImageAssetManager();
        if (bm != null) {
            return bm.bitmapForId(id);
        }
        LottieImageAsset imageAsset = this.composition == null ? null : this.composition.getImages().get(id);
        if (imageAsset != null) {
            return imageAsset.getBitmap();
        }
        return null;
    }

    public Bitmap getBitmapForId(String id) {
        ImageAssetManager assetManager = getImageAssetManager();
        if (assetManager != null) {
            return assetManager.bitmapForId(id);
        }
        return null;
    }

    public LottieImageAsset getLottieImageAssetForId(String id) {
        LottieComposition composition = this.composition;
        if (composition == null) {
            return null;
        }
        return composition.getImages().get(id);
    }

    private ImageAssetManager getImageAssetManager() {
        if (this.imageAssetManager != null && !this.imageAssetManager.hasSameContext(getContext())) {
            this.imageAssetManager = null;
        }
        if (this.imageAssetManager == null) {
            this.imageAssetManager = new ImageAssetManager(getCallback(), this.imageAssetsFolder, this.imageAssetDelegate, this.composition.getImages());
        }
        return this.imageAssetManager;
    }

    public Typeface getTypeface(Font font) {
        Map<String, Typeface> fontMap = this.fontMap;
        if (fontMap != null) {
            String key = font.getFamily();
            if (fontMap.containsKey(key)) {
                return fontMap.get(key);
            }
            String key2 = font.getName();
            if (fontMap.containsKey(key2)) {
                return fontMap.get(key2);
            }
            String key3 = font.getFamily() + "-" + font.getStyle();
            if (fontMap.containsKey(key3)) {
                return fontMap.get(key3);
            }
        }
        FontAssetManager assetManager = getFontAssetManager();
        if (assetManager != null) {
            return assetManager.getTypeface(font);
        }
        return null;
    }

    private FontAssetManager getFontAssetManager() {
        if (getCallback() == null) {
            return null;
        }
        if (this.fontAssetManager == null) {
            this.fontAssetManager = new FontAssetManager(getCallback(), this.fontAssetDelegate);
            String defaultExtension = this.defaultFontFileExtension;
            if (defaultExtension != null) {
                this.fontAssetManager.setDefaultFontFileExtension(this.defaultFontFileExtension);
            }
        }
        return this.fontAssetManager;
    }

    public void setDefaultFontFileExtension(String extension) {
        this.defaultFontFileExtension = extension;
        FontAssetManager fam = getFontAssetManager();
        if (fam != null) {
            fam.setDefaultFontFileExtension(extension);
        }
    }

    private Context getContext() {
        Drawable.Callback callback = getCallback();
        if (callback == null || !(callback instanceof View)) {
            return null;
        }
        return ((View) callback).getContext();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean visible, boolean restart) {
        boolean wasNotVisibleAlready = !isVisible();
        boolean ret = super.setVisible(visible, restart);
        if (visible) {
            if (this.onVisibleAction == OnVisibleAction.PLAY) {
                playAnimation();
            } else if (this.onVisibleAction == OnVisibleAction.RESUME) {
                resumeAnimation();
            }
        } else if (this.animator.isRunning()) {
            pauseAnimation();
            this.onVisibleAction = OnVisibleAction.RESUME;
        } else if (!wasNotVisibleAlready) {
            this.onVisibleAction = OnVisibleAction.NONE;
        }
        return ret;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable who) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.scheduleDrawable(this, what, when);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable who, Runnable what) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.unscheduleDrawable(this, what);
    }

    private void drawDirectlyToCanvas(Canvas canvas) {
        CompositionLayer compositionLayer = this.compositionLayer;
        LottieComposition composition = this.composition;
        if (compositionLayer == null || composition == null) {
            return;
        }
        this.renderingMatrix.reset();
        Rect bounds = getBounds();
        if (!bounds.isEmpty()) {
            float scaleX = bounds.width() / composition.getBounds().width();
            float scaleY = bounds.height() / composition.getBounds().height();
            this.renderingMatrix.preScale(scaleX, scaleY);
            this.renderingMatrix.preTranslate(bounds.left, bounds.top);
        }
        compositionLayer.draw(canvas, this.renderingMatrix, this.alpha);
    }

    private void renderAndDrawAsBitmap(Canvas originalCanvas, CompositionLayer compositionLayer) {
        if (this.composition == null || compositionLayer == null) {
            return;
        }
        ensureSoftwareRenderingObjectsInitialized();
        originalCanvas.getMatrix(this.softwareRenderingOriginalCanvasMatrix);
        originalCanvas.getClipBounds(this.canvasClipBounds);
        convertRect(this.canvasClipBounds, this.canvasClipBoundsRectF);
        this.softwareRenderingOriginalCanvasMatrix.mapRect(this.canvasClipBoundsRectF);
        convertRect(this.canvasClipBoundsRectF, this.canvasClipBounds);
        if (this.clipToCompositionBounds) {
            this.softwareRenderingTransformedBounds.set(0.0f, 0.0f, getIntrinsicWidth(), getIntrinsicHeight());
        } else {
            compositionLayer.getBounds(this.softwareRenderingTransformedBounds, null, false);
        }
        this.softwareRenderingOriginalCanvasMatrix.mapRect(this.softwareRenderingTransformedBounds);
        Rect bounds = getBounds();
        float scaleX = bounds.width() / getIntrinsicWidth();
        float scaleY = bounds.height() / getIntrinsicHeight();
        scaleRect(this.softwareRenderingTransformedBounds, scaleX, scaleY);
        if (!ignoreCanvasClipBounds()) {
            this.softwareRenderingTransformedBounds.intersect(this.canvasClipBounds.left, this.canvasClipBounds.top, this.canvasClipBounds.right, this.canvasClipBounds.bottom);
        }
        int renderWidth = (int) Math.ceil(this.softwareRenderingTransformedBounds.width());
        int renderHeight = (int) Math.ceil(this.softwareRenderingTransformedBounds.height());
        if (renderWidth <= 0 || renderHeight <= 0) {
            return;
        }
        ensureSoftwareRenderingBitmap(renderWidth, renderHeight);
        if (this.isDirty) {
            this.renderingMatrix.set(this.softwareRenderingOriginalCanvasMatrix);
            this.renderingMatrix.preScale(scaleX, scaleY);
            this.renderingMatrix.postTranslate(-this.softwareRenderingTransformedBounds.left, -this.softwareRenderingTransformedBounds.top);
            this.softwareRenderingBitmap.eraseColor(0);
            compositionLayer.draw(this.softwareRenderingCanvas, this.renderingMatrix, this.alpha);
            this.softwareRenderingOriginalCanvasMatrix.invert(this.softwareRenderingOriginalCanvasMatrixInverse);
            this.softwareRenderingOriginalCanvasMatrixInverse.mapRect(this.softwareRenderingDstBoundsRectF, this.softwareRenderingTransformedBounds);
            convertRect(this.softwareRenderingDstBoundsRectF, this.softwareRenderingDstBoundsRect);
        }
        this.softwareRenderingSrcBoundsRect.set(0, 0, renderWidth, renderHeight);
        originalCanvas.drawBitmap(this.softwareRenderingBitmap, this.softwareRenderingSrcBoundsRect, this.softwareRenderingDstBoundsRect, this.softwareRenderingPaint);
    }

    private void ensureSoftwareRenderingObjectsInitialized() {
        if (this.softwareRenderingCanvas != null) {
            return;
        }
        this.softwareRenderingCanvas = new Canvas();
        this.softwareRenderingTransformedBounds = new RectF();
        this.softwareRenderingOriginalCanvasMatrix = new Matrix();
        this.softwareRenderingOriginalCanvasMatrixInverse = new Matrix();
        this.canvasClipBounds = new Rect();
        this.canvasClipBoundsRectF = new RectF();
        this.softwareRenderingPaint = new LPaint();
        this.softwareRenderingSrcBoundsRect = new Rect();
        this.softwareRenderingDstBoundsRect = new Rect();
        this.softwareRenderingDstBoundsRectF = new RectF();
    }

    private void ensureSoftwareRenderingBitmap(int renderWidth, int renderHeight) {
        if (this.softwareRenderingBitmap == null || this.softwareRenderingBitmap.getWidth() < renderWidth || this.softwareRenderingBitmap.getHeight() < renderHeight) {
            this.softwareRenderingBitmap = Bitmap.createBitmap(renderWidth, renderHeight, Bitmap.Config.ARGB_8888);
            this.softwareRenderingCanvas.setBitmap(this.softwareRenderingBitmap);
            this.isDirty = true;
        } else if (this.softwareRenderingBitmap.getWidth() > renderWidth || this.softwareRenderingBitmap.getHeight() > renderHeight) {
            this.softwareRenderingBitmap = Bitmap.createBitmap(this.softwareRenderingBitmap, 0, 0, renderWidth, renderHeight);
            this.softwareRenderingCanvas.setBitmap(this.softwareRenderingBitmap);
            this.isDirty = true;
        }
    }

    private void convertRect(RectF src, Rect dst) {
        dst.set((int) Math.floor(src.left), (int) Math.floor(src.top), (int) Math.ceil(src.right), (int) Math.ceil(src.bottom));
    }

    private void convertRect(Rect src, RectF dst) {
        dst.set(src.left, src.top, src.right, src.bottom);
    }

    private void scaleRect(RectF rect, float scaleX, float scaleY) {
        rect.set(rect.left * scaleX, rect.top * scaleY, rect.right * scaleX, rect.bottom * scaleY);
    }

    private boolean ignoreCanvasClipBounds() {
        Drawable.Callback callback = getCallback();
        if (!(callback instanceof View)) {
            return false;
        }
        ViewParent parent = ((View) callback).getParent();
        if (parent instanceof ViewGroup) {
            return !((ViewGroup) parent).getClipChildren();
        }
        return false;
    }
}
