package org.apache.commons.lang3.function;

import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableDoubleFunction<R, E extends Throwable> {
    public static final FailableDoubleFunction NOP = new FailableDoubleFunction() { // from class: org.apache.commons.lang3.function.FailableDoubleFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableDoubleFunction
        public final Object apply(double d) {
            return FailableDoubleFunction.lambda$static$0(d);
        }
    };

    R apply(double d) throws Throwable;

    static /* synthetic */ Object lambda$static$0(double t) throws Throwable {
        return null;
    }

    static <R, E extends Throwable> FailableDoubleFunction<R, E> nop() {
        return NOP;
    }
}
