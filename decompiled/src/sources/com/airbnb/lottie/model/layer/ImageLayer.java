package com.airbnb.lottie.model.layer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;

/* loaded from: classes.dex */
public class ImageLayer extends BaseLayer {
    private BaseKeyframeAnimation<ColorFilter, ColorFilter> colorFilterAnimation;
    private final Rect dst;
    private BaseKeyframeAnimation<Bitmap, Bitmap> imageAnimation;
    private final LottieImageAsset lottieImageAsset;
    private final Paint paint;
    private final Rect src;

    ImageLayer(LottieDrawable lottieDrawable, Layer layerModel) {
        super(lottieDrawable, layerModel);
        this.paint = new LPaint(3);
        this.src = new Rect();
        this.dst = new Rect();
        this.lottieImageAsset = lottieDrawable.getLottieImageAssetForId(layerModel.getRefId());
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer
    public void drawLayer(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        Bitmap bitmap = getBitmap();
        if (bitmap == null || bitmap.isRecycled() || this.lottieImageAsset == null) {
            return;
        }
        float density = Utils.dpScale();
        this.paint.setAlpha(parentAlpha);
        if (this.colorFilterAnimation != null) {
            this.paint.setColorFilter(this.colorFilterAnimation.getValue());
        }
        canvas.save();
        canvas.concat(parentMatrix);
        this.src.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        if (this.lottieDrawable.getMaintainOriginalImageBounds()) {
            this.dst.set(0, 0, (int) (this.lottieImageAsset.getWidth() * density), (int) (this.lottieImageAsset.getHeight() * density));
        } else {
            this.dst.set(0, 0, (int) (bitmap.getWidth() * density), (int) (bitmap.getHeight() * density));
        }
        canvas.drawBitmap(bitmap, this.src, this.dst, this.paint);
        canvas.restore();
    }

    @Override // com.airbnb.lottie.model.layer.BaseLayer, com.airbnb.lottie.animation.content.DrawingContent
    public void getBounds(RectF outBounds, Matrix parentMatrix, boolean applyParents) {
        super.getBounds(outBounds, parentMatrix, applyParents);
        if (this.lottieImageAsset != null) {
            float scale = Utils.dpScale();
            outBounds.set(0.0f, 0.0f, this.lottieImageAsset.getWidth() * scale, this.lottieImageAsset.getHeight() * scale);
            this.boundsMatrix.mapRect(outBounds);
        }
    }

    private Bitmap getBitmap() {
        Bitmap callbackBitmap;
        if (this.imageAnimation != null && (callbackBitmap = this.imageAnimation.getValue()) != null) {
            return callbackBitmap;
        }
        String refId = this.layerModel.getRefId();
        Bitmap bitmapFromDrawable = this.lottieDrawable.getBitmapForId(refId);
        if (bitmapFromDrawable != null) {
            return bitmapFromDrawable;
        }
        LottieImageAsset asset = this.lottieImageAsset;
        if (asset != null) {
            return asset.getBitmap();
        }
        return null;
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
        if (property == LottieProperty.IMAGE) {
            if (callback == null) {
                this.imageAnimation = null;
            } else {
                this.imageAnimation = new ValueCallbackKeyframeAnimation(callback);
            }
        }
    }
}
