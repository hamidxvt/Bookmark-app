package com.github.ybq.android.spinkit.animation.interpolator;

import android.graphics.Path;
import android.view.animation.Interpolator;

/* loaded from: classes16.dex */
public class PathInterpolatorCompat {
    private PathInterpolatorCompat() {
    }

    public static Interpolator create(Path path) {
        return PathInterpolatorCompatApi21.create(path);
    }

    public static Interpolator create(float controlX, float controlY) {
        return PathInterpolatorCompatApi21.create(controlX, controlY);
    }

    public static Interpolator create(float controlX1, float controlY1, float controlX2, float controlY2) {
        return PathInterpolatorCompatApi21.create(controlX1, controlY1, controlX2, controlY2);
    }
}
