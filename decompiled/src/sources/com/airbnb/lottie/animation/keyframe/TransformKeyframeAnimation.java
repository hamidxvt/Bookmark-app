package com.airbnb.lottie.animation.keyframe;

import android.graphics.Matrix;
import android.graphics.PointF;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.ScaleXY;
import java.util.Collections;

/* loaded from: classes.dex */
public class TransformKeyframeAnimation {
    private BaseKeyframeAnimation<PointF, PointF> anchorPoint;
    private final boolean autoOrient;
    private BaseKeyframeAnimation<?, Float> endOpacity;
    private final Matrix matrix = new Matrix();
    private BaseKeyframeAnimation<Integer, Integer> opacity;
    private BaseKeyframeAnimation<?, PointF> position;
    private BaseKeyframeAnimation<Float, Float> rotation;
    private BaseKeyframeAnimation<ScaleXY, ScaleXY> scale;
    private FloatKeyframeAnimation skew;
    private FloatKeyframeAnimation skewAngle;
    private final Matrix skewMatrix1;
    private final Matrix skewMatrix2;
    private final Matrix skewMatrix3;
    private final float[] skewValues;
    private BaseKeyframeAnimation<?, Float> startOpacity;

    public TransformKeyframeAnimation(AnimatableTransform animatableTransform) {
        this.anchorPoint = animatableTransform.getAnchorPoint() == null ? null : animatableTransform.getAnchorPoint().createAnimation();
        this.position = animatableTransform.getPosition() == null ? null : animatableTransform.getPosition().createAnimation();
        this.scale = animatableTransform.getScale() == null ? null : animatableTransform.getScale().createAnimation();
        this.rotation = animatableTransform.getRotation() == null ? null : animatableTransform.getRotation().createAnimation();
        this.skew = animatableTransform.getSkew() == null ? null : (FloatKeyframeAnimation) animatableTransform.getSkew().createAnimation();
        this.autoOrient = animatableTransform.isAutoOrient();
        if (this.skew != null) {
            this.skewMatrix1 = new Matrix();
            this.skewMatrix2 = new Matrix();
            this.skewMatrix3 = new Matrix();
            this.skewValues = new float[9];
        } else {
            this.skewMatrix1 = null;
            this.skewMatrix2 = null;
            this.skewMatrix3 = null;
            this.skewValues = null;
        }
        this.skewAngle = animatableTransform.getSkewAngle() == null ? null : (FloatKeyframeAnimation) animatableTransform.getSkewAngle().createAnimation();
        if (animatableTransform.getOpacity() != null) {
            this.opacity = animatableTransform.getOpacity().createAnimation();
        }
        if (animatableTransform.getStartOpacity() != null) {
            this.startOpacity = animatableTransform.getStartOpacity().createAnimation();
        } else {
            this.startOpacity = null;
        }
        if (animatableTransform.getEndOpacity() != null) {
            this.endOpacity = animatableTransform.getEndOpacity().createAnimation();
        } else {
            this.endOpacity = null;
        }
    }

    public void addAnimationsToLayer(BaseLayer layer) {
        layer.addAnimation(this.opacity);
        layer.addAnimation(this.startOpacity);
        layer.addAnimation(this.endOpacity);
        layer.addAnimation(this.anchorPoint);
        layer.addAnimation(this.position);
        layer.addAnimation(this.scale);
        layer.addAnimation(this.rotation);
        layer.addAnimation(this.skew);
        layer.addAnimation(this.skewAngle);
    }

    public void addListener(BaseKeyframeAnimation.AnimationListener listener) {
        if (this.opacity != null) {
            this.opacity.addUpdateListener(listener);
        }
        if (this.startOpacity != null) {
            this.startOpacity.addUpdateListener(listener);
        }
        if (this.endOpacity != null) {
            this.endOpacity.addUpdateListener(listener);
        }
        if (this.anchorPoint != null) {
            this.anchorPoint.addUpdateListener(listener);
        }
        if (this.position != null) {
            this.position.addUpdateListener(listener);
        }
        if (this.scale != null) {
            this.scale.addUpdateListener(listener);
        }
        if (this.rotation != null) {
            this.rotation.addUpdateListener(listener);
        }
        if (this.skew != null) {
            this.skew.addUpdateListener(listener);
        }
        if (this.skewAngle != null) {
            this.skewAngle.addUpdateListener(listener);
        }
    }

    public void setProgress(float progress) {
        if (this.opacity != null) {
            this.opacity.setProgress(progress);
        }
        if (this.startOpacity != null) {
            this.startOpacity.setProgress(progress);
        }
        if (this.endOpacity != null) {
            this.endOpacity.setProgress(progress);
        }
        if (this.anchorPoint != null) {
            this.anchorPoint.setProgress(progress);
        }
        if (this.position != null) {
            this.position.setProgress(progress);
        }
        if (this.scale != null) {
            this.scale.setProgress(progress);
        }
        if (this.rotation != null) {
            this.rotation.setProgress(progress);
        }
        if (this.skew != null) {
            this.skew.setProgress(progress);
        }
        if (this.skewAngle != null) {
            this.skewAngle.setProgress(progress);
        }
    }

    public BaseKeyframeAnimation<?, Integer> getOpacity() {
        return this.opacity;
    }

    public BaseKeyframeAnimation<?, Float> getStartOpacity() {
        return this.startOpacity;
    }

    public BaseKeyframeAnimation<?, Float> getEndOpacity() {
        return this.endOpacity;
    }

    public Matrix getMatrix() {
        float rotationValue;
        PointF anchorPointValue;
        ScaleXY scaleTransform;
        PointF positionValue;
        this.matrix.reset();
        BaseKeyframeAnimation<?, PointF> position = this.position;
        if (position != null && (positionValue = position.getValue()) != null && (positionValue.x != 0.0f || positionValue.y != 0.0f)) {
            this.matrix.preTranslate(positionValue.x, positionValue.y);
        }
        if (this.autoOrient) {
            if (position != null) {
                float currentProgress = position.getProgress();
                PointF startPosition = position.getValue();
                float startX = startPosition.x;
                float startY = startPosition.y;
                position.setProgress(1.0E-4f + currentProgress);
                PointF nextPosition = position.getValue();
                position.setProgress(currentProgress);
                double rotationValue2 = Math.toDegrees(Math.atan2(nextPosition.y - startY, nextPosition.x - startX));
                this.matrix.preRotate((float) rotationValue2);
            }
        } else {
            BaseKeyframeAnimation<Float, Float> rotation = this.rotation;
            if (rotation != null) {
                if (rotation instanceof ValueCallbackKeyframeAnimation) {
                    rotationValue = rotation.getValue().floatValue();
                } else {
                    rotationValue = ((FloatKeyframeAnimation) rotation).getFloatValue();
                }
                if (rotationValue != 0.0f) {
                    this.matrix.preRotate(rotationValue);
                }
            }
        }
        FloatKeyframeAnimation skew = this.skew;
        if (skew != null) {
            float mCos = this.skewAngle == null ? 0.0f : (float) Math.cos(Math.toRadians((-this.skewAngle.getFloatValue()) + 90.0f));
            float mSin = this.skewAngle == null ? 1.0f : (float) Math.sin(Math.toRadians((-this.skewAngle.getFloatValue()) + 90.0f));
            float aTan = (float) Math.tan(Math.toRadians(skew.getFloatValue()));
            clearSkewValues();
            this.skewValues[0] = mCos;
            this.skewValues[1] = mSin;
            this.skewValues[3] = -mSin;
            this.skewValues[4] = mCos;
            this.skewValues[8] = 1.0f;
            this.skewMatrix1.setValues(this.skewValues);
            clearSkewValues();
            this.skewValues[0] = 1.0f;
            this.skewValues[3] = aTan;
            this.skewValues[4] = 1.0f;
            this.skewValues[8] = 1.0f;
            this.skewMatrix2.setValues(this.skewValues);
            clearSkewValues();
            this.skewValues[0] = mCos;
            this.skewValues[1] = -mSin;
            this.skewValues[3] = mSin;
            this.skewValues[4] = mCos;
            this.skewValues[8] = 1.0f;
            this.skewMatrix3.setValues(this.skewValues);
            this.skewMatrix2.preConcat(this.skewMatrix1);
            this.skewMatrix3.preConcat(this.skewMatrix2);
            this.matrix.preConcat(this.skewMatrix3);
        }
        BaseKeyframeAnimation<ScaleXY, ScaleXY> scale = this.scale;
        if (scale != null && (scaleTransform = scale.getValue()) != null && (scaleTransform.getScaleX() != 1.0f || scaleTransform.getScaleY() != 1.0f)) {
            this.matrix.preScale(scaleTransform.getScaleX(), scaleTransform.getScaleY());
        }
        BaseKeyframeAnimation<PointF, PointF> anchorPoint = this.anchorPoint;
        if (anchorPoint != null && (anchorPointValue = anchorPoint.getValue()) != null && (anchorPointValue.x != 0.0f || anchorPointValue.y != 0.0f)) {
            this.matrix.preTranslate(-anchorPointValue.x, -anchorPointValue.y);
        }
        return this.matrix;
    }

    private void clearSkewValues() {
        for (int i = 0; i < 9; i++) {
            this.skewValues[i] = 0.0f;
        }
    }

    public Matrix getMatrixForRepeater(float amount) {
        PointF position = this.position == null ? null : this.position.getValue();
        ScaleXY scale = this.scale == null ? null : this.scale.getValue();
        this.matrix.reset();
        if (position != null) {
            this.matrix.preTranslate(position.x * amount, position.y * amount);
        }
        if (scale != null) {
            this.matrix.preScale((float) Math.pow(scale.getScaleX(), amount), (float) Math.pow(scale.getScaleY(), amount));
        }
        if (this.rotation != null) {
            float rotation = this.rotation.getValue().floatValue();
            PointF anchorPoint = this.anchorPoint != null ? this.anchorPoint.getValue() : null;
            this.matrix.preRotate(rotation * amount, anchorPoint == null ? 0.0f : anchorPoint.x, anchorPoint != null ? anchorPoint.y : 0.0f);
        }
        return this.matrix;
    }

    public <T> boolean applyValueCallback(T property, LottieValueCallback<T> callback) {
        if (property == LottieProperty.TRANSFORM_ANCHOR_POINT) {
            if (this.anchorPoint == null) {
                this.anchorPoint = new ValueCallbackKeyframeAnimation(callback, new PointF());
                return true;
            }
            this.anchorPoint.setValueCallback(callback);
            return true;
        }
        if (property == LottieProperty.TRANSFORM_POSITION) {
            if (this.position == null) {
                this.position = new ValueCallbackKeyframeAnimation(callback, new PointF());
                return true;
            }
            this.position.setValueCallback(callback);
            return true;
        }
        if (property == LottieProperty.TRANSFORM_POSITION_X && (this.position instanceof SplitDimensionPathKeyframeAnimation)) {
            ((SplitDimensionPathKeyframeAnimation) this.position).setXValueCallback(callback);
            return true;
        }
        if (property == LottieProperty.TRANSFORM_POSITION_Y && (this.position instanceof SplitDimensionPathKeyframeAnimation)) {
            ((SplitDimensionPathKeyframeAnimation) this.position).setYValueCallback(callback);
            return true;
        }
        if (property == LottieProperty.TRANSFORM_SCALE) {
            if (this.scale == null) {
                this.scale = new ValueCallbackKeyframeAnimation(callback, new ScaleXY());
                return true;
            }
            this.scale.setValueCallback(callback);
            return true;
        }
        if (property == LottieProperty.TRANSFORM_ROTATION) {
            if (this.rotation == null) {
                this.rotation = new ValueCallbackKeyframeAnimation(callback, Float.valueOf(0.0f));
                return true;
            }
            this.rotation.setValueCallback(callback);
            return true;
        }
        if (property == LottieProperty.TRANSFORM_OPACITY) {
            if (this.opacity == null) {
                this.opacity = new ValueCallbackKeyframeAnimation(callback, 100);
                return true;
            }
            this.opacity.setValueCallback(callback);
            return true;
        }
        if (property == LottieProperty.TRANSFORM_START_OPACITY) {
            if (this.startOpacity == null) {
                this.startOpacity = new ValueCallbackKeyframeAnimation(callback, Float.valueOf(100.0f));
                return true;
            }
            this.startOpacity.setValueCallback(callback);
            return true;
        }
        if (property == LottieProperty.TRANSFORM_END_OPACITY) {
            if (this.endOpacity == null) {
                this.endOpacity = new ValueCallbackKeyframeAnimation(callback, Float.valueOf(100.0f));
                return true;
            }
            this.endOpacity.setValueCallback(callback);
            return true;
        }
        if (property == LottieProperty.TRANSFORM_SKEW) {
            if (this.skew == null) {
                this.skew = new FloatKeyframeAnimation(Collections.singletonList(new Keyframe(Float.valueOf(0.0f))));
            }
            this.skew.setValueCallback(callback);
            return true;
        }
        if (property == LottieProperty.TRANSFORM_SKEW_ANGLE) {
            if (this.skewAngle == null) {
                this.skewAngle = new FloatKeyframeAnimation(Collections.singletonList(new Keyframe(Float.valueOf(0.0f))));
            }
            this.skewAngle.setValueCallback(callback);
            return true;
        }
        return false;
    }
}
