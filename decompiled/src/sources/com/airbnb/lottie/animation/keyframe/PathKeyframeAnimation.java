package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

/* loaded from: classes.dex */
public class PathKeyframeAnimation extends KeyframeAnimation<PointF> {
    private final PathMeasure pathMeasure;
    private PathKeyframe pathMeasureKeyframe;
    private final PointF point;
    private final float[] pos;
    private final float[] tangent;

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public /* bridge */ /* synthetic */ Object getValue(Keyframe keyframe, float f) {
        return getValue((Keyframe<PointF>) keyframe, f);
    }

    public PathKeyframeAnimation(List<? extends Keyframe<PointF>> keyframes) {
        super(keyframes);
        this.point = new PointF();
        this.pos = new float[2];
        this.tangent = new float[2];
        this.pathMeasure = new PathMeasure();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public PointF getValue(Keyframe<PointF> keyframe, float keyframeProgress) {
        PointF value;
        PathKeyframe pathKeyframe = (PathKeyframe) keyframe;
        Path path = pathKeyframe.getPath();
        if (path == null) {
            return keyframe.startValue;
        }
        if (this.valueCallback != null && (value = (PointF) this.valueCallback.getValueInternal(pathKeyframe.startFrame, pathKeyframe.endFrame.floatValue(), (PointF) pathKeyframe.startValue, (PointF) pathKeyframe.endValue, getLinearCurrentKeyframeProgress(), keyframeProgress, getProgress())) != null) {
            return value;
        }
        if (this.pathMeasureKeyframe != pathKeyframe) {
            this.pathMeasure.setPath(path, false);
            this.pathMeasureKeyframe = pathKeyframe;
        }
        float length = this.pathMeasure.getLength();
        float distance = keyframeProgress * length;
        this.pathMeasure.getPosTan(distance, this.pos, this.tangent);
        this.point.set(this.pos[0], this.pos[1]);
        if (distance < 0.0f) {
            this.point.offset(this.tangent[0] * distance, this.tangent[1] * distance);
        } else if (distance > length) {
            this.point.offset(this.tangent[0] * (distance - length), this.tangent[1] * (distance - length));
        }
        return this.point;
    }
}
