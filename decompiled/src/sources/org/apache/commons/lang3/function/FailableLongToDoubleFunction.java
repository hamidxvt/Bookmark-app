package org.apache.commons.lang3.function;

import com.github.mikephil.charting.utils.Utils;
import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableLongToDoubleFunction<E extends Throwable> {
    public static final FailableLongToDoubleFunction NOP = new FailableLongToDoubleFunction() { // from class: org.apache.commons.lang3.function.FailableLongToDoubleFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableLongToDoubleFunction
        public final double applyAsDouble(long j) {
            double d;
            d = Utils.DOUBLE_EPSILON;
            return d;
        }
    };

    double applyAsDouble(long j) throws Throwable;

    static <E extends Throwable> FailableLongToDoubleFunction<E> nop() {
        return NOP;
    }
}
