package com.airbnb.lottie.animation.content;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.model.content.GradientFill;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class GradientFillContent implements DrawingContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    private static final int CACHE_STEPS_MS = 32;
    private BaseKeyframeAnimation<Float, Float> blurAnimation;
    private final int cacheSteps;
    private final BaseKeyframeAnimation<GradientColor, GradientColor> colorAnimation;
    private ValueCallbackKeyframeAnimation colorCallbackAnimation;
    private BaseKeyframeAnimation<ColorFilter, ColorFilter> colorFilterAnimation;
    private DropShadowKeyframeAnimation dropShadowAnimation;
    private final BaseKeyframeAnimation<PointF, PointF> endPointAnimation;
    private final boolean hidden;
    private final BaseLayer layer;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<Integer, Integer> opacityAnimation;
    private final BaseKeyframeAnimation<PointF, PointF> startPointAnimation;
    private final GradientType type;
    private final LongSparseArray<LinearGradient> linearGradientCache = new LongSparseArray<>();
    private final LongSparseArray<RadialGradient> radialGradientCache = new LongSparseArray<>();
    private final Path path = new Path();
    private final Paint paint = new LPaint(1);
    private final RectF boundsRect = new RectF();
    private final List<PathContent> paths = new ArrayList();
    float blurMaskFilterRadius = 0.0f;

    public GradientFillContent(LottieDrawable lottieDrawable, LottieComposition composition, BaseLayer layer, GradientFill fill) {
        this.layer = layer;
        this.name = fill.getName();
        this.hidden = fill.isHidden();
        this.lottieDrawable = lottieDrawable;
        this.type = fill.getGradientType();
        this.path.setFillType(fill.getFillType());
        this.cacheSteps = (int) (composition.getDuration() / 32.0f);
        this.colorAnimation = fill.getGradientColor().createAnimation();
        this.colorAnimation.addUpdateListener(this);
        layer.addAnimation(this.colorAnimation);
        this.opacityAnimation = fill.getOpacity().createAnimation();
        this.opacityAnimation.addUpdateListener(this);
        layer.addAnimation(this.opacityAnimation);
        this.startPointAnimation = fill.getStartPoint().createAnimation();
        this.startPointAnimation.addUpdateListener(this);
        layer.addAnimation(this.startPointAnimation);
        this.endPointAnimation = fill.getEndPoint().createAnimation();
        this.endPointAnimation.addUpdateListener(this);
        layer.addAnimation(this.endPointAnimation);
        if (layer.getBlurEffect() != null) {
            this.blurAnimation = layer.getBlurEffect().getBlurriness().createAnimation();
            this.blurAnimation.addUpdateListener(this);
            layer.addAnimation(this.blurAnimation);
        }
        if (layer.getDropShadowEffect() != null) {
            this.dropShadowAnimation = new DropShadowKeyframeAnimation(this, layer, layer.getDropShadowEffect());
        }
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public void setContents(List<Content> contentsBefore, List<Content> contentsAfter) {
        for (int i = 0; i < contentsAfter.size(); i++) {
            Content content = contentsAfter.get(i);
            if (content instanceof PathContent) {
                this.paths.add((PathContent) content);
            }
        }
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        Shader shader;
        if (this.hidden) {
            return;
        }
        L.beginSection("GradientFillContent#draw");
        this.path.reset();
        for (int i = 0; i < this.paths.size(); i++) {
            this.path.addPath(this.paths.get(i).getPath(), parentMatrix);
        }
        this.path.computeBounds(this.boundsRect, false);
        if (this.type == GradientType.LINEAR) {
            shader = getLinearGradient();
        } else {
            shader = getRadialGradient();
        }
        shader.setLocalMatrix(parentMatrix);
        this.paint.setShader(shader);
        if (this.colorFilterAnimation != null) {
            this.paint.setColorFilter(this.colorFilterAnimation.getValue());
        }
        if (this.blurAnimation != null) {
            float blurRadius = this.blurAnimation.getValue().floatValue();
            if (blurRadius == 0.0f) {
                this.paint.setMaskFilter(null);
            } else if (blurRadius != this.blurMaskFilterRadius) {
                BlurMaskFilter blur = new BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL);
                this.paint.setMaskFilter(blur);
            }
            this.blurMaskFilterRadius = blurRadius;
        }
        if (this.dropShadowAnimation != null) {
            this.dropShadowAnimation.applyTo(this.paint);
        }
        int alpha = (int) ((((parentAlpha / 255.0f) * this.opacityAnimation.getValue().intValue()) / 100.0f) * 255.0f);
        this.paint.setAlpha(MiscUtils.clamp(alpha, 0, 255));
        canvas.drawPath(this.path, this.paint);
        L.endSection("GradientFillContent#draw");
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF outBounds, Matrix parentMatrix, boolean applyParents) {
        this.path.reset();
        for (int i = 0; i < this.paths.size(); i++) {
            this.path.addPath(this.paths.get(i).getPath(), parentMatrix);
        }
        this.path.computeBounds(outBounds, false);
        outBounds.set(outBounds.left - 1.0f, outBounds.top - 1.0f, outBounds.right + 1.0f, outBounds.bottom + 1.0f);
    }

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    private LinearGradient getLinearGradient() {
        int gradientHash = getGradientHash();
        LinearGradient gradient = this.linearGradientCache.get(gradientHash);
        if (gradient != null) {
            return gradient;
        }
        PointF startPoint = this.startPointAnimation.getValue();
        PointF endPoint = this.endPointAnimation.getValue();
        GradientColor gradientColor = this.colorAnimation.getValue();
        int[] colors = applyDynamicColorsIfNeeded(gradientColor.getColors());
        float[] positions = gradientColor.getPositions();
        LinearGradient gradient2 = new LinearGradient(startPoint.x, startPoint.y, endPoint.x, endPoint.y, colors, positions, Shader.TileMode.CLAMP);
        this.linearGradientCache.put(gradientHash, gradient2);
        return gradient2;
    }

    private RadialGradient getRadialGradient() {
        float r;
        int gradientHash = getGradientHash();
        RadialGradient gradient = this.radialGradientCache.get(gradientHash);
        if (gradient != null) {
            return gradient;
        }
        PointF startPoint = this.startPointAnimation.getValue();
        PointF endPoint = this.endPointAnimation.getValue();
        GradientColor gradientColor = this.colorAnimation.getValue();
        int[] colors = applyDynamicColorsIfNeeded(gradientColor.getColors());
        float[] positions = gradientColor.getPositions();
        float x0 = startPoint.x;
        float y0 = startPoint.y;
        float x1 = endPoint.x;
        float y1 = endPoint.y;
        float r2 = (float) Math.hypot(x1 - x0, y1 - y0);
        if (r2 > 0.0f) {
            r = r2;
        } else {
            r = 0.001f;
        }
        RadialGradient gradient2 = new RadialGradient(x0, y0, r, colors, positions, Shader.TileMode.CLAMP);
        this.radialGradientCache.put(gradientHash, gradient2);
        return gradient2;
    }

    private int getGradientHash() {
        int startPointProgress = Math.round(this.startPointAnimation.getProgress() * this.cacheSteps);
        int endPointProgress = Math.round(this.endPointAnimation.getProgress() * this.cacheSteps);
        int colorProgress = Math.round(this.colorAnimation.getProgress() * this.cacheSteps);
        int hash = 17;
        if (startPointProgress != 0) {
            hash = 17 * 31 * startPointProgress;
        }
        if (endPointProgress != 0) {
            hash = hash * 31 * endPointProgress;
        }
        if (colorProgress != 0) {
            return hash * 31 * colorProgress;
        }
        return hash;
    }

    private int[] applyDynamicColorsIfNeeded(int[] colors) {
        if (this.colorCallbackAnimation != null) {
            Integer[] dynamicColors = (Integer[]) this.colorCallbackAnimation.getValue();
            if (colors.length == dynamicColors.length) {
                for (int i = 0; i < colors.length; i++) {
                    colors[i] = dynamicColors[i].intValue();
                }
            } else {
                colors = new int[dynamicColors.length];
                for (int i2 = 0; i2 < dynamicColors.length; i2++) {
                    colors[i2] = dynamicColors[i2].intValue();
                }
            }
        }
        return colors;
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath, int depth, List<KeyPath> accumulator, KeyPath currentPartialKeyPath) {
        MiscUtils.resolveKeyPath(keyPath, depth, accumulator, currentPartialKeyPath, this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        if (property == LottieProperty.OPACITY) {
            this.opacityAnimation.setValueCallback(callback);
            return;
        }
        if (property == LottieProperty.COLOR_FILTER) {
            if (this.colorFilterAnimation != null) {
                this.layer.removeAnimation(this.colorFilterAnimation);
            }
            if (callback == null) {
                this.colorFilterAnimation = null;
                return;
            }
            this.colorFilterAnimation = new ValueCallbackKeyframeAnimation(callback);
            this.colorFilterAnimation.addUpdateListener(this);
            this.layer.addAnimation(this.colorFilterAnimation);
            return;
        }
        if (property == LottieProperty.GRADIENT_COLOR) {
            if (this.colorCallbackAnimation != null) {
                this.layer.removeAnimation(this.colorCallbackAnimation);
            }
            if (callback == null) {
                this.colorCallbackAnimation = null;
                return;
            }
            this.linearGradientCache.clear();
            this.radialGradientCache.clear();
            this.colorCallbackAnimation = new ValueCallbackKeyframeAnimation(callback);
            this.colorCallbackAnimation.addUpdateListener(this);
            this.layer.addAnimation(this.colorCallbackAnimation);
            return;
        }
        if (property == LottieProperty.BLUR_RADIUS) {
            if (this.blurAnimation != null) {
                this.blurAnimation.setValueCallback(callback);
                return;
            }
            this.blurAnimation = new ValueCallbackKeyframeAnimation(callback);
            this.blurAnimation.addUpdateListener(this);
            this.layer.addAnimation(this.blurAnimation);
            return;
        }
        if (property == LottieProperty.DROP_SHADOW_COLOR && this.dropShadowAnimation != null) {
            this.dropShadowAnimation.setColorCallback(callback);
            return;
        }
        if (property == LottieProperty.DROP_SHADOW_OPACITY && this.dropShadowAnimation != null) {
            this.dropShadowAnimation.setOpacityCallback(callback);
            return;
        }
        if (property == LottieProperty.DROP_SHADOW_DIRECTION && this.dropShadowAnimation != null) {
            this.dropShadowAnimation.setDirectionCallback(callback);
            return;
        }
        if (property == LottieProperty.DROP_SHADOW_DISTANCE && this.dropShadowAnimation != null) {
            this.dropShadowAnimation.setDistanceCallback(callback);
        } else if (property == LottieProperty.DROP_SHADOW_RADIUS && this.dropShadowAnimation != null) {
            this.dropShadowAnimation.setRadiusCallback(callback);
        }
    }
}
