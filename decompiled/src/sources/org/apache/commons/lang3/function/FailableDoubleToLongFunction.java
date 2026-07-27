package org.apache.commons.lang3.function;

import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableDoubleToLongFunction<E extends Throwable> {
    public static final FailableDoubleToLongFunction NOP = new FailableDoubleToLongFunction() { // from class: org.apache.commons.lang3.function.FailableDoubleToLongFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableDoubleToLongFunction
        public final int applyAsLong(double d) {
            return FailableDoubleToLongFunction.lambda$static$0(d);
        }
    };

    int applyAsLong(double d) throws Throwable;

    static /* synthetic */ int lambda$static$0(double t) throws Throwable {
        return 0;
    }

    static <E extends Throwable> FailableDoubleToLongFunction<E> nop() {
        return NOP;
    }
}
