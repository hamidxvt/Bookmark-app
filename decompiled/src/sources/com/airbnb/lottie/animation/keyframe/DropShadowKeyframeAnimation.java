package com.airbnb.lottie.animation.keyframe;

import android.graphics.Color;
import android.graphics.Paint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.parser.DropShadowEffect;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;

/* loaded from: classes.dex */
public class DropShadowKeyframeAnimation implements BaseKeyframeAnimation.AnimationListener {
    private static final double DEG_TO_RAD = 0.017453292519943295d;
    private final BaseKeyframeAnimation<Integer, Integer> color;
    private final BaseKeyframeAnimation<Float, Float> direction;
    private final BaseKeyframeAnimation<Float, Float> distance;
    private boolean isDirty = true;
    private final BaseKeyframeAnimation.AnimationListener listener;
    private final BaseKeyframeAnimation<Float, Float> opacity;
    private final BaseKeyframeAnimation<Float, Float> radius;

    public DropShadowKeyframeAnimation(BaseKeyframeAnimation.AnimationListener listener, BaseLayer layer, DropShadowEffect dropShadowEffect) {
        this.listener = listener;
        this.color = dropShadowEffect.getColor().createAnimation();
        this.color.addUpdateListener(this);
        layer.addAnimation(this.color);
        this.opacity = dropShadowEffect.getOpacity().createAnimation();
        this.opacity.addUpdateListener(this);
        layer.addAnimation(this.opacity);
        this.direction = dropShadowEffect.getDirection().createAnimation();
        this.direction.addUpdateListener(this);
        layer.addAnimation(this.direction);
        this.distance = dropShadowEffect.getDistance().createAnimation();
        this.distance.addUpdateListener(this);
        layer.addAnimation(this.distance);
        this.radius = dropShadowEffect.getRadius().createAnimation();
        this.radius.addUpdateListener(this);
        layer.addAnimation(this.radius);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation.AnimationListener
    public void onValueChanged() {
        this.isDirty = true;
        this.listener.onValueChanged();
    }

    public void applyTo(Paint paint) {
        if (!this.isDirty) {
            return;
        }
        this.isDirty = false;
        double directionRad = this.direction.getValue().floatValue() * 0.017453292519943295d;
        float distance = this.distance.getValue().floatValue();
        float x = ((float) Math.sin(directionRad)) * distance;
        float y = ((float) Math.cos(3.141592653589793d + directionRad)) * distance;
        int baseColor = this.color.getValue().intValue();
        int opacity = Math.round(this.opacity.getValue().floatValue());
        int color = Color.argb(opacity, Color.red(baseColor), Color.green(baseColor), Color.blue(baseColor));
        float radius = this.radius.getValue().floatValue();
        paint.setShadowLayer(radius, x, y, color);
    }

    public void setColorCallback(LottieValueCallback<Integer> callback) {
        this.color.setValueCallback(callback);
    }

    public void setOpacityCallback(final LottieValueCallback<Float> callback) {
        if (callback == null) {
            this.opacity.setValueCallback(null);
        } else {
            this.opacity.setValueCallback(new LottieValueCallback<Float>() { // from class: com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.airbnb.lottie.value.LottieValueCallback
                public Float getValue(LottieFrameInfo<Float> frameInfo) {
                    Float value = (Float) callback.getValue(frameInfo);
                    if (value == null) {
                        return null;
                    }
                    return Float.valueOf(value.floatValue() * 2.55f);
                }
            });
        }
    }

    public void setDirectionCallback(LottieValueCallback<Float> callback) {
        this.direction.setValueCallback(callback);
    }

    public void setDistanceCallback(LottieValueCallback<Float> callback) {
        this.distance.setValueCallback(callback);
    }

    public void setRadiusCallback(LottieValueCallback<Float> callback) {
        this.radius.setValueCallback(callback);
    }
}
