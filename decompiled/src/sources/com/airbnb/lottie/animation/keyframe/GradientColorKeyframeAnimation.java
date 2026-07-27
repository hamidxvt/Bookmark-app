package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

/* loaded from: classes.dex */
public class GradientColorKeyframeAnimation extends KeyframeAnimation<GradientColor> {
    private final GradientColor gradientColor;

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* bridge */ /* synthetic */ Object getValue(Keyframe keyframe, float f) {
        return getValue((Keyframe<GradientColor>) keyframe, f);
    }

    public GradientColorKeyframeAnimation(List<Keyframe<GradientColor>> keyframes) {
        super(keyframes);
        int size = 0;
        for (int i = 0; i < keyframes.size(); i++) {
            GradientColor startValue = keyframes.get(i).startValue;
            if (startValue != null) {
                size = Math.max(size, startValue.getSize());
            }
        }
        this.gradientColor = new GradientColor(new float[size], new int[size]);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    GradientColor getValue(Keyframe<GradientColor> keyframe, float keyframeProgress) {
        this.gradientColor.lerp(keyframe.startValue, keyframe.endValue, keyframeProgress);
        return this.gradientColor;
    }
}
