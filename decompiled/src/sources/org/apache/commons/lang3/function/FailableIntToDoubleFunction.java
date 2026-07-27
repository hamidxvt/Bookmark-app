package org.apache.commons.lang3.function;

import com.github.mikephil.charting.utils.Utils;
import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableIntToDoubleFunction<E extends Throwable> {
    public static final FailableIntToDoubleFunction NOP = new FailableIntToDoubleFunction() { // from class: org.apache.commons.lang3.function.FailableIntToDoubleFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableIntToDoubleFunction
        public final double applyAsDouble(int i) {
            double d;
            d = Utils.DOUBLE_EPSILON;
            return d;
        }
    };

    double applyAsDouble(int i) throws Throwable;

    static <E extends Throwable> FailableIntToDoubleFunction<E> nop() {
        return NOP;
    }
}
