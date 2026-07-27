package org.apache.commons.lang3.function;

import java.lang.Throwable;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableToIntFunction<T, E extends Throwable> {
    public static final FailableToIntFunction NOP = new FailableToIntFunction() { // from class: org.apache.commons.lang3.function.FailableToIntFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableToIntFunction
        public final int applyAsInt(Object obj) {
            return FailableToIntFunction.lambda$static$0(obj);
        }
    };

    int applyAsInt(T t) throws Throwable;

    static /* synthetic */ int lambda$static$0(Object t) throws Throwable {
        return 0;
    }

    static <T, E extends Throwable> FailableToIntFunction<T, E> nop() {
        return NOP;
    }
}
