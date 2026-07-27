package com.airbnb.lottie.animation.content;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.IntegerKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseStrokeContent implements BaseKeyframeAnimation.AnimationListener, KeyPathElementContent, DrawingContent {
    private BaseKeyframeAnimation<Float, Float> blurAnimation;
    private BaseKeyframeAnimation<ColorFilter, ColorFilter> colorFilterAnimation;
    private final List<BaseKeyframeAnimation<?, Float>> dashPatternAnimations;
    private final BaseKeyframeAnimation<?, Float> dashPatternOffsetAnimation;
    private final float[] dashPatternValues;
    private DropShadowKeyframeAnimation dropShadowAnimation;
    protected final BaseLayer layer;
    private final LottieDrawable lottieDrawable;
    private final BaseKeyframeAnimation<?, Integer> opacityAnimation;
    private final BaseKeyframeAnimation<?, Float> widthAnimation;
    private final PathMeasure pm = new PathMeasure();
    private final Path path = new Path();
    private final Path trimPathPath = new Path();
    private final RectF rect = new RectF();
    private final List<PathGroup> pathGroups = new ArrayList();
    final Paint paint = new LPaint(1);
    float blurMaskFilterRadius = 0.0f;

    BaseStrokeContent(LottieDrawable lottieDrawable, BaseLayer layer, Paint.Cap cap, Paint.Join join, float miterLimit, AnimatableIntegerValue opacity, AnimatableFloatValue width, List<AnimatableFloatValue> dashPattern, AnimatableFloatValue offset) {
        this.lottieDrawable = lottieDrawable;
        this.layer = layer;
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeCap(cap);
        this.paint.setStrokeJoin(join);
        this.paint.setStrokeMiter(miterLimit);
        this.opacityAnimation = opacity.createAnimation();
        this.widthAnimation = width.createAnimation();
        if (offset == null) {
            this.dashPatternOffsetAnimation = null;
        } else {
            this.dashPatternOffsetAnimation = offset.createAnimation();
        }
        this.dashPatternAnimations = new ArrayList(dashPattern.size());
        this.dashPatternValues = new float[dashPattern.size()];
        for (int i = 0; i < dashPattern.size(); i++) {
            this.dashPatternAnimations.add(dashPattern.get(i).createAnimation());
        }
        layer.addAnimation(this.opacityAnimation);
        layer.addAnimation(this.widthAnimation);
        for (int i2 = 0; i2 < this.dashPatternAnimations.size(); i2++) {
            layer.addAnimation(this.dashPatternAnimations.get(i2));
        }
        if (this.dashPatternOffsetAnimation != null) {
            layer.addAnimation(this.dashPatternOffsetAnimation);
        }
        this.opacityAnimation.addUpdateListener(this);
        this.widthAnimation.addUpdateListener(this);
        for (int i3 = 0; i3 < dashPattern.size(); i3++) {
            this.dashPatternAnimations.get(i3).addUpdateListener(this);
        }
        if (this.dashPatternOffsetAnimation != null) {
            this.dashPatternOffsetAnimation.addUpdateListener(this);
        }
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
        TrimPathContent trimPathContentBefore = null;
        for (int i = contentsBefore.size() - 1; i >= 0; i--) {
            Content content = contentsBefore.get(i);
            if ((content instanceof TrimPathContent) && ((TrimPathContent) content).getType() == ShapeTrimPath.Type.INDIVIDUALLY) {
                trimPathContentBefore = (TrimPathContent) content;
            }
        }
        if (trimPathContentBefore != null) {
            trimPathContentBefore.addListener(this);
        }
        PathGroup currentPathGroup = null;
        for (int i2 = contentsAfter.size() - 1; i2 >= 0; i2--) {
            Content content2 = contentsAfter.get(i2);
            if ((content2 instanceof TrimPathContent) && ((TrimPathContent) content2).getType() == ShapeTrimPath.Type.INDIVIDUALLY) {
                if (currentPathGroup != null) {
                    this.pathGroups.add(currentPathGroup);
                }
                currentPathGroup = new PathGroup((TrimPathContent) content2);
                ((TrimPathContent) content2).addListener(this);
            } else if (content2 instanceof PathContent) {
                if (currentPathGroup == null) {
                    currentPathGroup = new PathGroup(trimPathContentBefore);
                }
                currentPathGroup.paths.add((PathContent) content2);
            }
        }
        if (currentPathGroup != null) {
            this.pathGroups.add(currentPathGroup);
        }
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        L.beginSection("StrokeContent#draw");
        if (Utils.hasZeroScaleAxis(parentMatrix)) {
            L.endSection("StrokeContent#draw");
            return;
        }
        int alpha = (int) ((((parentAlpha / 255.0f) * ((IntegerKeyframeAnimation) this.opacityAnimation).getIntValue()) / 100.0f) * 255.0f);
        this.paint.setAlpha(MiscUtils.clamp(alpha, 0, 255));
        this.paint.setStrokeWidth(((FloatKeyframeAnimation) this.widthAnimation).getFloatValue() * Utils.getScale(parentMatrix));
        if (this.paint.getStrokeWidth() <= 0.0f) {
            L.endSection("StrokeContent#draw");
            return;
        }
        applyDashPatternIfNeeded(parentMatrix);
        if (this.colorFilterAnimation != null) {
            this.paint.setColorFilter(this.colorFilterAnimation.getValue());
        }
        if (this.blurAnimation != null) {
            float blurRadius = this.blurAnimation.getValue().floatValue();
            if (blurRadius == 0.0f) {
                this.paint.setMaskFilter(null);
            } else if (blurRadius != this.blurMaskFilterRadius) {
                BlurMaskFilter blur = this.layer.getBlurMaskFilter(blurRadius);
                this.paint.setMaskFilter(blur);
            }
            this.blurMaskFilterRadius = blurRadius;
        }
        if (this.dropShadowAnimation != null) {
            this.dropShadowAnimation.applyTo(this.paint);
        }
        for (int i = 0; i < this.pathGroups.size(); i++) {
            PathGroup pathGroup = this.pathGroups.get(i);
            if (pathGroup.trimPath != null) {
                applyTrimPath(canvas, pathGroup, parentMatrix);
            } else {
                L.beginSection("StrokeContent#buildPath");
                this.path.reset();
                for (int j = pathGroup.paths.size() - 1; j >= 0; j--) {
                    this.path.addPath(((PathContent) pathGroup.paths.get(j)).getPath(), parentMatrix);
                }
                L.endSection("StrokeContent#buildPath");
                L.beginSection("StrokeContent#drawPath");
                canvas.drawPath(this.path, this.paint);
                L.endSection("StrokeContent#drawPath");
            }
        }
        L.endSection("StrokeContent#draw");
    }

    private void applyTrimPath(Canvas canvas, PathGroup pathGroup, Matrix parentMatrix) {
        float animStartValue;
        float startValue;
        float endValue;
        float startValue2;
        Matrix matrix = parentMatrix;
        L.beginSection("StrokeContent#applyTrimPath");
        if (pathGroup.trimPath == null) {
            L.endSection("StrokeContent#applyTrimPath");
            return;
        }
        this.path.reset();
        for (int j = pathGroup.paths.size() - 1; j >= 0; j--) {
            this.path.addPath(((PathContent) pathGroup.paths.get(j)).getPath(), matrix);
        }
        float animStartValue2 = pathGroup.trimPath.getStart().getValue().floatValue() / 100.0f;
        float animEndValue = pathGroup.trimPath.getEnd().getValue().floatValue() / 100.0f;
        float animOffsetValue = pathGroup.trimPath.getOffset().getValue().floatValue() / 360.0f;
        if (animStartValue2 >= 0.01f || animEndValue <= 0.99f) {
            boolean z = false;
            this.pm.setPath(this.path, false);
            float totalLength = this.pm.getLength();
            while (this.pm.nextContour()) {
                totalLength += this.pm.getLength();
            }
            float offsetLength = totalLength * animOffsetValue;
            float startLength = (totalLength * animStartValue2) + offsetLength;
            float endLength = Math.min((totalLength * animEndValue) + offsetLength, (startLength + totalLength) - 1.0f);
            float currentLength = 0.0f;
            int j2 = pathGroup.paths.size() - 1;
            while (j2 >= 0) {
                this.trimPathPath.set(((PathContent) pathGroup.paths.get(j2)).getPath());
                this.trimPathPath.transform(matrix);
                this.pm.setPath(this.trimPathPath, z);
                float length = this.pm.getLength();
                if (endLength <= totalLength || endLength - totalLength >= currentLength + length || currentLength >= endLength - totalLength) {
                    animStartValue = animStartValue2;
                    if (currentLength + length >= startLength && currentLength <= endLength) {
                        if (currentLength + length <= endLength && startLength < currentLength) {
                            canvas.drawPath(this.trimPathPath, this.paint);
                        } else {
                            if (startLength < currentLength) {
                                startValue = 0.0f;
                            } else {
                                float startValue3 = startLength - currentLength;
                                startValue = startValue3 / length;
                            }
                            if (endLength > currentLength + length) {
                                endValue = 1.0f;
                            } else {
                                float endValue2 = endLength - currentLength;
                                endValue = endValue2 / length;
                            }
                            Utils.applyTrimPathIfNeeded(this.trimPathPath, startValue, endValue, 0.0f);
                            canvas.drawPath(this.trimPathPath, this.paint);
                        }
                    }
                } else {
                    if (startLength > totalLength) {
                        startValue2 = (startLength - totalLength) / length;
                    } else {
                        startValue2 = 0.0f;
                    }
                    float endValue3 = Math.min((endLength - totalLength) / length, 1.0f);
                    animStartValue = animStartValue2;
                    Utils.applyTrimPathIfNeeded(this.trimPathPath, startValue2, endValue3, 0.0f);
                    canvas.drawPath(this.trimPathPath, this.paint);
                }
                currentLength += length;
                j2--;
                matrix = parentMatrix;
                animStartValue2 = animStartValue;
                z = false;
            }
            L.endSection("StrokeContent#applyTrimPath");
            return;
        }
        canvas.drawPath(this.path, this.paint);
        L.endSection("StrokeContent#applyTrimPath");
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF outBounds, Matrix parentMatrix, boolean applyParents) {
        L.beginSection("StrokeContent#getBounds");
        this.path.reset();
        for (int i = 0; i < this.pathGroups.size(); i++) {
            PathGroup pathGroup = this.pathGroups.get(i);
            for (int j = 0; j < pathGroup.paths.size(); j++) {
                this.path.addPath(((PathContent) pathGroup.paths.get(j)).getPath(), parentMatrix);
            }
        }
        this.path.computeBounds(this.rect, false);
        float width = ((FloatKeyframeAnimation) this.widthAnimation).getFloatValue();
        this.rect.set(this.rect.left - (width / 2.0f), this.rect.top - (width / 2.0f), this.rect.right + (width / 2.0f), this.rect.bottom + (width / 2.0f));
        outBounds.set(this.rect);
        outBounds.set(outBounds.left - 1.0f, outBounds.top - 1.0f, outBounds.right + 1.0f, outBounds.bottom + 1.0f);
        L.endSection("StrokeContent#getBounds");
    }

    private void applyDashPatternIfNeeded(Matrix parentMatrix) {
        L.beginSection("StrokeContent#applyDashPattern");
        if (this.dashPatternAnimations.isEmpty()) {
            L.endSection("StrokeContent#applyDashPattern");
            return;
        }
        float scale = Utils.getScale(parentMatrix);
        for (int i = 0; i < this.dashPatternAnimations.size(); i++) {
            this.dashPatternValues[i] = this.dashPatternAnimations.get(i).getValue().floatValue();
            if (i % 2 == 0) {
                if (this.dashPatternValues[i] < 1.0f) {
                    this.dashPatternValues[i] = 1.0f;
                }
            } else if (this.dashPatternValues[i] < 0.1f) {
                this.dashPatternValues[i] = 0.1f;
            }
            float[] fArr = this.dashPatternValues;
            fArr[i] = fArr[i] * scale;
        }
        float offset = this.dashPatternOffsetAnimation == null ? 0.0f : this.dashPatternOffsetAnimation.getValue().floatValue() * scale;
        this.paint.setPathEffect(new DashPathEffect(this.dashPatternValues, offset));
        L.endSection("StrokeContent#applyDashPattern");
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath, int depth, List<KeyPath> accumulator, KeyPath currentPartialKeyPath) {
        MiscUtils.resolveKeyPath(keyPath, depth, accumulator, currentPartialKeyPath, this);
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        if (property == LottieProperty.OPACITY) {
            this.opacityAnimation.setValueCallback(callback);
            return;
        }
        if (property == LottieProperty.STROKE_WIDTH) {
            this.widthAnimation.setValueCallback(callback);
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

    private static final class PathGroup {
        private final List<PathContent> paths;
        private final TrimPathContent trimPath;

        private PathGroup(TrimPathContent trimPath) {
            this.paths = new ArrayList();
            this.trimPath = trimPath;
        }
    }
}
