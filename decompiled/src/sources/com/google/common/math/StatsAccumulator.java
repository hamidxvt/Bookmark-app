package com.google.common.math;

import com.github.mikephil.charting.utils.Utils;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
import java.util.Iterator;

@ElementTypesAreNonnullByDefault
/* loaded from: classes16.dex */
public final class StatsAccumulator {
    private long count = 0;
    private double mean = Utils.DOUBLE_EPSILON;
    private double sumOfSquaresOfDeltas = Utils.DOUBLE_EPSILON;
    private double min = Double.NaN;
    private double max = Double.NaN;

    public void add(double value) {
        if (this.count == 0) {
            this.count = 1L;
            this.mean = value;
            this.min = value;
            this.max = value;
            if (!Doubles.isFinite(value)) {
                this.sumOfSquaresOfDeltas = Double.NaN;
                return;
            }
            return;
        }
        this.count++;
        if (Doubles.isFinite(value) && Doubles.isFinite(this.mean)) {
            double delta = value - this.mean;
            this.mean += delta / this.count;
            this.sumOfSquaresOfDeltas += (value - this.mean) * delta;
        } else {
            this.mean = calculateNewMeanNonFinite(this.mean, value);
            this.sumOfSquaresOfDeltas = Double.NaN;
        }
        this.min = Math.min(this.min, value);
        this.max = Math.max(this.max, value);
    }

    public void addAll(Iterable<? extends Number> values) {
        for (Number value : values) {
            add(value.doubleValue());
        }
    }

    public void addAll(Iterator<? extends Number> values) {
        while (values.hasNext()) {
            add(values.next().doubleValue());
        }
    }

    public void addAll(double... values) {
        for (double value : values) {
            add(value);
        }
    }

    public void addAll(int... values) {
        for (int value : values) {
            add(value);
        }
    }

    public void addAll(long... values) {
        for (long value : values) {
            add(value);
        }
    }

    public void addAll(Stats values) {
        if (values.count() == 0) {
            return;
        }
        merge(values.count(), values.mean(), values.sumOfSquaresOfDeltas(), values.min(), values.max());
    }

    public void addAll(StatsAccumulator values) {
        if (values.count() == 0) {
            return;
        }
        merge(values.count(), values.mean(), values.sumOfSquaresOfDeltas(), values.min(), values.max());
    }

    private void merge(long otherCount, double otherMean, double otherSumOfSquaresOfDeltas, double otherMin, double otherMax) {
        if (this.count != 0) {
            this.count += otherCount;
            if (!Doubles.isFinite(this.mean) || !Doubles.isFinite(otherMean)) {
                this.mean = calculateNewMeanNonFinite(this.mean, otherMean);
                this.sumOfSquaresOfDeltas = Double.NaN;
            } else {
                double delta = otherMean - this.mean;
                this.mean += (otherCount * delta) / this.count;
                this.sumOfSquaresOfDeltas += ((otherMean - this.mean) * delta * otherCount) + otherSumOfSquaresOfDeltas;
            }
            this.min = Math.min(this.min, otherMin);
            this.max = Math.max(this.max, otherMax);
            return;
        }
        this.count = otherCount;
        this.mean = otherMean;
        this.sumOfSquaresOfDeltas = otherSumOfSquaresOfDeltas;
        this.min = otherMin;
        this.max = otherMax;
    }

    public Stats snapshot() {
        return new Stats(this.count, this.mean, this.sumOfSquaresOfDeltas, this.min, this.max);
    }

    public long count() {
        return this.count;
    }

    public double mean() {
        Preconditions.checkState(this.count != 0);
        return this.mean;
    }

    public final double sum() {
        return this.mean * this.count;
    }

    public final double populationVariance() {
        Preconditions.checkState(this.count != 0);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        if (this.count == 1) {
            return Utils.DOUBLE_EPSILON;
        }
        return DoubleUtils.ensureNonNegative(this.sumOfSquaresOfDeltas) / this.count;
    }

    public final double populationStandardDeviation() {
        return Math.sqrt(populationVariance());
    }

    public final double sampleVariance() {
        Preconditions.checkState(this.count > 1);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        return DoubleUtils.ensureNonNegative(this.sumOfSquaresOfDeltas) / (this.count - 1);
    }

    public final double sampleStandardDeviation() {
        return Math.sqrt(sampleVariance());
    }

    public double min() {
        Preconditions.checkState(this.count != 0);
        return this.min;
    }

    public double max() {
        Preconditions.checkState(this.count != 0);
        return this.max;
    }

    double sumOfSquaresOfDeltas() {
        return this.sumOfSquaresOfDeltas;
    }

    static double calculateNewMeanNonFinite(double previousMean, double value) {
        if (Doubles.isFinite(previousMean)) {
            return value;
        }
        if (Doubles.isFinite(value) || previousMean == value) {
            return previousMean;
        }
        return Double.NaN;
    }
}
