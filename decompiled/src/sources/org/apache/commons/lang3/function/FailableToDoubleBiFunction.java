package org.apache.commons.lang3.function;

import com.github.mikephil.charting.utils.Utils;
import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableToDoubleBiFunction<T, U, E extends Throwable> {
    public static final FailableToDoubleBiFunction NOP = new FailableToDoubleBiFunction() { // from class: org.apache.commons.lang3.function.FailableToDoubleBiFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableToDoubleBiFunction
        public final double applyAsDouble(Object obj, Object obj2) {
            double d;
            d = Utils.DOUBLE_EPSILON;
            return d;
        }
    };

    double applyAsDouble(T t, U u) throws Throwable;

    static <T, U, E extends Throwable> FailableToDoubleBiFunction<T, U, E> nop() {
        return NOP;
    }
}
