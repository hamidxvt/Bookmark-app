package com.airbnb.lottie.animation.keyframe;

import android.graphics.PointF;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.Collections;

/* loaded from: classes.dex */
public class SplitDimensionPathKeyframeAnimation extends BaseKeyframeAnimation<PointF, PointF> {
    private final PointF point;
    private final PointF pointWithCallbackValues;
    private final BaseKeyframeAnimation<Float, Float> xAnimation;
    protected LottieValueCallback<Float> xValueCallback;
    private final BaseKeyframeAnimation<Float, Float> yAnimation;
    protected LottieValueCallback<Float> yValueCallback;

    public SplitDimensionPathKeyframeAnimation(BaseKeyframeAnimation<Float, Float> xAnimation, BaseKeyframeAnimation<Float, Float> yAnimation) {
        super(Collections.emptyList());
        this.point = new PointF();
        this.pointWithCallbackValues = new PointF();
        this.xAnimation = xAnimation;
        this.yAnimation = yAnimation;
        setProgress(getProgress());
    }

    public void setXValueCallback(LottieValueCallback<Float> xValueCallback) {
        if (this.xValueCallback != null) {
            this.xValueCallback.setAnimation(null);
        }
        this.xValueCallback = xValueCallback;
        if (xValueCallback != null) {
            xValueCallback.setAnimation(this);
        }
    }

    public void setYValueCallback(LottieValueCallback<Float> yValueCallback) {
        if (this.yValueCallback != null) {
            this.yValueCallback.setAnimation(null);
        }
        this.yValueCallback = yValueCallback;
        if (yValueCallback != null) {
            yValueCallback.setAnimation(this);
        }
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public void setProgress(float progress) {
        this.xAnimation.setProgress(progress);
        this.yAnimation.setProgress(progress);
        this.point.set(this.xAnimation.getValue().floatValue(), this.yAnimation.getValue().floatValue());
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).onValueChanged();
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public PointF getValue() {
        return getValue((Keyframe<PointF>) null, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public PointF getValue(Keyframe<PointF> keyframe, float keyframeProgress) {
        Keyframe<Float> yKeyframe;
        Keyframe<Float> xKeyframe;
        Float xCallbackValue = null;
        Float yCallbackValue = null;
        if (this.xValueCallback != null && (xKeyframe = this.xAnimation.getCurrentKeyframe()) != null) {
            float progress = this.xAnimation.getInterpolatedCurrentKeyframeProgress();
            Float endFrame = xKeyframe.endFrame;
            xCallbackValue = this.xValueCallback.getValueInternal(xKeyframe.startFrame, endFrame == null ? xKeyframe.startFrame : endFrame.floatValue(), xKeyframe.startValue, xKeyframe.endValue, keyframeProgress, keyframeProgress, progress);
        }
        if (this.yValueCallback != null && (yKeyframe = this.yAnimation.getCurrentKeyframe()) != null) {
            float progress2 = this.yAnimation.getInterpolatedCurrentKeyframeProgress();
            Float endFrame2 = yKeyframe.endFrame;
            yCallbackValue = this.yValueCallback.getValueInternal(yKeyframe.startFrame, endFrame2 == null ? yKeyframe.startFrame : endFrame2.floatValue(), yKeyframe.startValue, yKeyframe.endValue, keyframeProgress, keyframeProgress, progress2);
        }
        if (xCallbackValue == null) {
            this.pointWithCallbackValues.set(this.point.x, 0.0f);
        } else {
            this.pointWithCallbackValues.set(xCallbackValue.floatValue(), 0.0f);
        }
        if (yCallbackValue == null) {
            this.pointWithCallbackValues.set(this.pointWithCallbackValues.x, this.point.y);
        } else {
            this.pointWithCallbackValues.set(this.pointWithCallbackValues.x, yCallbackValue.floatValue());
        }
        return this.pointWithCallbackValues;
    }
}
