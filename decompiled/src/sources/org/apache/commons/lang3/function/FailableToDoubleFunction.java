package org.apache.commons.lang3.function;

import com.github.mikephil.charting.utils.Utils;
import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableToDoubleFunction<T, E extends Throwable> {
    public static final FailableToDoubleFunction NOP = new FailableToDoubleFunction() { // from class: org.apache.commons.lang3.function.FailableToDoubleFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableToDoubleFunction
        public final double applyAsDouble(Object obj) {
            double d;
            d = Utils.DOUBLE_EPSILON;
            return d;
        }
    };

    double applyAsDouble(T t) throws Throwable;

    static <T, E extends Throwable> FailableToDoubleFunction<T, E> nop() {
        return NOP;
    }
}
