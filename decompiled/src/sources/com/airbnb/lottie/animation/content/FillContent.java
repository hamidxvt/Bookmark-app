package com.airbnb.lottie.animation.content;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.core.graphics.PaintCompat;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ColorKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.ShapeFill;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class FillContent implements DrawingContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    private BaseKeyframeAnimation<Float, Float> blurAnimation;
    float blurMaskFilterRadius;
    private final BaseKeyframeAnimation<Integer, Integer> colorAnimation;
    private BaseKeyframeAnimation<ColorFilter, ColorFilter> colorFilterAnimation;
    private DropShadowKeyframeAnimation dropShadowAnimation;
    private final boolean hidden;
    private final BaseLayer layer;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<Integer, Integer> opacityAnimation;
    private final Path path = new Path();
    private final Paint paint = new LPaint(1);
    private final List<PathContent> paths = new ArrayList();

    public FillContent(LottieDrawable lottieDrawable, BaseLayer layer, ShapeFill fill) {
        this.layer = layer;
        this.name = fill.getName();
        this.hidden = fill.isHidden();
        this.lottieDrawable = lottieDrawable;
        if (layer.getBlurEffect() != null) {
            this.blurAnimation = layer.getBlurEffect().getBlurriness().createAnimation();
            this.blurAnimation.addUpdateListener(this);
            layer.addAnimation(this.blurAnimation);
        }
        if (layer.getDropShadowEffect() != null) {
            this.dropShadowAnimation = new DropShadowKeyframeAnimation(this, layer, layer.getDropShadowEffect());
        }
        if (fill.getColor() == null || fill.getOpacity() == null) {
            this.colorAnimation = null;
            this.opacityAnimation = null;
            return;
        }
        PaintCompat.setBlendMode(this.paint, layer.getBlendMode().toNativeBlendMode());
        this.path.setFillType(fill.getFillType());
        this.colorAnimation = fill.getColor().createAnimation();
        this.colorAnimation.addUpdateListener(this);
        layer.addAnimation(this.colorAnimation);
        this.opacityAnimation = fill.getOpacity().createAnimation();
        this.opacityAnimation.addUpdateListener(this);
        layer.addAnimation(this.opacityAnimation);
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

    @Override // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    @Override // com.airbnb.lottie.animation.content.DrawingContent
    public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        if (this.hidden) {
            return;
        }
        L.beginSection("FillContent#draw");
        int color = ((ColorKeyframeAnimation) this.colorAnimation).getIntValue();
        int alpha = (int) ((((parentAlpha / 255.0f) * this.opacityAnimation.getValue().intValue()) / 100.0f) * 255.0f);
        this.paint.setColor((MiscUtils.clamp(alpha, 0, 255) << 24) | (16777215 & color));
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
        this.path.reset();
        for (int i = 0; i < this.paths.size(); i++) {
            this.path.addPath(this.paths.get(i).getPath(), parentMatrix);
        }
        canvas.drawPath(this.path, this.paint);
        L.endSection("FillContent#draw");
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

    @Override // com.airbnb.lottie.model.KeyPathElement
    public void resolveKeyPath(KeyPath keyPath, int depth, List<KeyPath> accumulator, KeyPath currentPartialKeyPath) {
        MiscUtils.resolveKeyPath(keyPath, depth, accumulator, currentPartialKeyPath, this);
    }

    @Override // com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        if (property == LottieProperty.COLOR) {
            this.colorAnimation.setValueCallback(callback);
            return;
        }
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
