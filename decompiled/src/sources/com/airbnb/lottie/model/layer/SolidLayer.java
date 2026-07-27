package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.value.LottieValueCallback;

/* loaded from: classes.dex */
public class SolidLayer extends BaseLayer {
    private BaseKeyframeAnimation<Integer, Integer> colorAnimation;
    private BaseKeyframeAnimation<ColorFilter, ColorFilter> colorFilterAnimation;
    private final Layer layerModel;
    private final Paint paint;
    private final Path path;
    private final float[] points;
    private final RectF rect;

    SolidLayer(LottieDrawable lottieDrawable, Layer layerModel) {
        super(lottieDrawable, layerModel);
        this.rect = new RectF();
        this.paint = new LPaint();
        this.points = new float[8];
        this.path = new Path();
        this.layerModel = layerModel;
        this.paint.setAlpha(0);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(layerModel.getSolidColor());
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public void drawLayer(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        int backgroundAlpha = Color.alpha(this.layerModel.getSolidColor());
        if (backgroundAlpha == 0) {
            return;
        }
        Integer color = this.colorAnimation == null ? null : this.colorAnimation.getValue();
        if (color == null) {
            this.paint.setColor(this.layerModel.getSolidColor());
        } else {
            this.paint.setColor(color.intValue());
        }
        int opacity = this.transform.getOpacity() == null ? 100 : this.transform.getOpacity().getValue().intValue();
        int alpha = (int) ((parentAlpha / 255.0f) * (((backgroundAlpha / 255.0f) * opacity) / 100.0f) * 255.0f);
        this.paint.setAlpha(alpha);
        if (this.colorFilterAnimation != null) {
            this.paint.setColorFilter(this.colorFilterAnimation.getValue());
        }
        if (alpha > 0) {
            this.points[0] = 0.0f;
            this.points[1] = 0.0f;
            this.points[2] = this.layerModel.getSolidWidth();
            this.points[3] = 0.0f;
            this.points[4] = this.layerModel.getSolidWidth();
            this.points[5] = this.layerModel.getSolidHeight();
            this.points[6] = 0.0f;
            this.points[7] = this.layerModel.getSolidHeight();
            parentMatrix.mapPoints(this.points);
            this.path.reset();
            this.path.moveTo(this.points[0], this.points[1]);
            this.path.lineTo(this.points[2], this.points[3]);
            this.path.lineTo(this.points[4], this.points[5]);
            this.path.lineTo(this.points[6], this.points[7]);
            this.path.lineTo(this.points[0], this.points[1]);
            this.path.close();
            canvas.drawPath(this.path, this.paint);
        }
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF outBounds, Matrix parentMatrix, boolean applyParents) {
        super.getBounds(outBounds, parentMatrix, applyParents);
        this.rect.set(0.0f, 0.0f, this.layerModel.getSolidWidth(), this.layerModel.getSolidHeight());
        this.boundsMatrix.mapRect(this.rect);
        outBounds.set(this.rect);
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.model.KeyPathElement
    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        super.addValueCallback(property, callback);
        if (property == LottieProperty.COLOR_FILTER) {
            if (callback == null) {
                this.colorFilterAnimation = null;
                return;
            } else {
                this.colorFilterAnimation = new ValueCallbackKeyframeAnimation(callback);
                return;
            }
        }
        if (property == LottieProperty.COLOR) {
            if (callback == null) {
                this.colorAnimation = null;
                this.paint.setColor(this.layerModel.getSolidColor());
            } else {
                this.colorAnimation = new ValueCallbackKeyframeAnimation(callback);
            }
        }
    }
}
