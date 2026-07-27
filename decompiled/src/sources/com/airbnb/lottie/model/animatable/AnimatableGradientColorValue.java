package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.GradientColorKeyframeAnimation;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.value.Keyframe;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class AnimatableGradientColorValue extends BaseAnimatableValue<GradientColor, GradientColor> {
    @Override // com.airbnb.lottie.model.animatable.BaseAnimatableValue, com.airbnb.lottie.model.animatable.AnimatableValue
    public /* bridge */ /* synthetic */ List getKeyframes() {
        return super.getKeyframes();
    }

    @Override // com.airbnb.lottie.model.animatable.BaseAnimatableValue, com.airbnb.lottie.model.animatable.AnimatableValue
    public /* bridge */ /* synthetic */ boolean isStatic() {
        return super.isStatic();
    }

    @Override // com.airbnb.lottie.model.animatable.BaseAnimatableValue
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public AnimatableGradientColorValue(List<Keyframe<GradientColor>> keyframes) {
        super((List) ensureInterpolatableKeyframes(keyframes));
    }

    private static List<Keyframe<GradientColor>> ensureInterpolatableKeyframes(List<Keyframe<GradientColor>> keyframes) {
        for (int i = 0; i < keyframes.size(); i++) {
            keyframes.set(i, ensureInterpolatableKeyframe(keyframes.get(i)));
        }
        return keyframes;
    }

    private static Keyframe<GradientColor> ensureInterpolatableKeyframe(Keyframe<GradientColor> keyframe) {
        GradientColor startValue = keyframe.startValue;
        GradientColor endValue = keyframe.endValue;
        if (startValue == null || endValue == null || startValue.getPositions().length == endValue.getPositions().length) {
            return keyframe;
        }
        float[] mergedPositions = mergePositions(startValue.getPositions(), endValue.getPositions());
        return keyframe.copyWith(startValue.copyWithPositions(mergedPositions), endValue.copyWithPositions(mergedPositions));
    }

    static float[] mergePositions(float[] startPositions, float[] endPositions) {
        float[] mergedArray = new float[startPositions.length + endPositions.length];
        System.arraycopy(startPositions, 0, mergedArray, 0, startPositions.length);
        System.arraycopy(endPositions, 0, mergedArray, startPositions.length, endPositions.length);
        Arrays.sort(mergedArray);
        int uniqueValues = 0;
        float lastValue = Float.NaN;
        for (int i = 0; i < mergedArray.length; i++) {
            if (mergedArray[i] != lastValue) {
                mergedArray[uniqueValues] = mergedArray[i];
                uniqueValues++;
                lastValue = mergedArray[i];
            }
        }
        return Arrays.copyOfRange(mergedArray, 0, uniqueValues);
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    public BaseKeyframeAnimation<GradientColor, GradientColor> createAnimation() {
        return new GradientColorKeyframeAnimation(this.keyframes);
    }
}
