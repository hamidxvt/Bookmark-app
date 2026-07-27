package com.airbnb.lottie.model.content;

import com.airbnb.lottie.utils.GammaEvaluator;
import com.airbnb.lottie.utils.MiscUtils;
import java.util.Arrays;

/* loaded from: classes.dex */
public class GradientColor {
    private final int[] colors;
    private final float[] positions;

    public GradientColor(float[] positions, int[] colors) {
        this.positions = positions;
        this.colors = colors;
    }

    public float[] getPositions() {
        return this.positions;
    }

    public int[] getColors() {
        return this.colors;
    }

    public int getSize() {
        return this.colors.length;
    }

    public void lerp(GradientColor gc1, GradientColor gc2, float progress) {
        if (gc1.equals(gc2)) {
            copyFrom(gc1);
            return;
        }
        if (progress <= 0.0f) {
            copyFrom(gc1);
            return;
        }
        if (progress >= 1.0f) {
            copyFrom(gc2);
            return;
        }
        if (gc1.colors.length != gc2.colors.length) {
            throw new IllegalArgumentException("Cannot interpolate between gradients. Lengths vary (" + gc1.colors.length + " vs " + gc2.colors.length + ")");
        }
        for (int i = 0; i < gc1.colors.length; i++) {
            this.positions[i] = MiscUtils.lerp(gc1.positions[i], gc2.positions[i], progress);
            this.colors[i] = GammaEvaluator.evaluate(progress, gc1.colors[i], gc2.colors[i]);
        }
        for (int i2 = gc1.colors.length; i2 < this.positions.length; i2++) {
            this.positions[i2] = this.positions[gc1.colors.length - 1];
            this.colors[i2] = this.colors[gc1.colors.length - 1];
        }
    }

    public GradientColor copyWithPositions(float[] positions) {
        int[] colors = new int[positions.length];
        for (int i = 0; i < positions.length; i++) {
            colors[i] = getColorForPosition(positions[i]);
        }
        return new GradientColor(positions, colors);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GradientColor that = (GradientColor) o;
        if (Arrays.equals(this.positions, that.positions) && Arrays.equals(this.colors, that.colors)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = Arrays.hashCode(this.positions);
        return (result * 31) + Arrays.hashCode(this.colors);
    }

    private int getColorForPosition(float position) {
        int existingIndex = Arrays.binarySearch(this.positions, position);
        if (existingIndex >= 0) {
            return this.colors[existingIndex];
        }
        int insertionPoint = -(existingIndex + 1);
        if (insertionPoint == 0) {
            return this.colors[0];
        }
        if (insertionPoint == this.colors.length - 1) {
            return this.colors[this.colors.length - 1];
        }
        float startPosition = this.positions[insertionPoint - 1];
        float endPosition = this.positions[insertionPoint];
        int startColor = this.colors[insertionPoint - 1];
        int endColor = this.colors[insertionPoint];
        float fraction = (position - startPosition) / (endPosition - startPosition);
        return GammaEvaluator.evaluate(fraction, startColor, endColor);
    }

    private void copyFrom(GradientColor other) {
        for (int i = 0; i < other.colors.length; i++) {
            this.positions[i] = other.positions[i];
            this.colors[i] = other.colors[i];
        }
    }
}
