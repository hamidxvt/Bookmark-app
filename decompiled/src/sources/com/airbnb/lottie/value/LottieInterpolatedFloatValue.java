package com.airbnb.lottie.value;

import android.view.animation.Interpolator;
import com.airbnb.lottie.utils.MiscUtils;

/* loaded from: classes.dex */
public class LottieInterpolatedFloatValue extends LottieInterpolatedValue<Float> {
    @Override // com.airbnb.lottie.value.LottieInterpolatedValue, com.airbnb.lottie.value.LottieValueCallback
    public /* bridge */ /* synthetic */ Object getValue(LottieFrameInfo lottieFrameInfo) {
        return super.getValue(lottieFrameInfo);
    }

    public LottieInterpolatedFloatValue(Float startValue, Float endValue) {
        super(startValue, endValue);
    }

    public LottieInterpolatedFloatValue(Float startValue, Float endValue, Interpolator interpolator) {
        super(startValue, endValue, interpolator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.value.LottieInterpolatedValue
    public Float interpolateValue(Float startValue, Float endValue, float progress) {
        return Float.valueOf(MiscUtils.lerp(startValue.floatValue(), endValue.floatValue(), progress));
    }
}
