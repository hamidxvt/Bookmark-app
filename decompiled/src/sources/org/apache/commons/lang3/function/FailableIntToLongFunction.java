package org.apache.commons.lang3.function;

import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableIntToLongFunction<E extends Throwable> {
    public static final FailableIntToLongFunction NOP = new FailableIntToLongFunction() { // from class: org.apache.commons.lang3.function.FailableIntToLongFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableIntToLongFunction
        public final long applyAsLong(int i) {
            return FailableIntToLongFunction.lambda$static$0(i);
        }
    };

    long applyAsLong(int i) throws Throwable;

    static /* synthetic */ long lambda$static$0(int t) throws Throwable {
        return 0L;
    }

    static <E extends Throwable> FailableIntToLongFunction<E> nop() {
        return NOP;
    }
}
