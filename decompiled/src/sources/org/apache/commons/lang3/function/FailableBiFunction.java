package org.apache.commons.lang3.function;

import java.lang.Throwable;
import java.util.Objects;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableBiFunction<T, U, R, E extends Throwable> {
    public static final FailableBiFunction NOP = new FailableBiFunction() { // from class: org.apache.commons.lang3.function.FailableBiFunction$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableBiFunction
        public final Object apply(Object obj, Object obj2) {
            return FailableBiFunction.lambda$static$0(obj, obj2);
        }
    };

    R apply(T t, U u) throws Throwable;

    static /* synthetic */ Object lambda$static$0(Object t, Object u) throws Throwable {
        return null;
    }

    static <T, U, R, E extends Throwable> FailableBiFunction<T, U, R, E> nop() {
        return NOP;
    }

    default <V> FailableBiFunction<T, U, V, E> andThen(final FailableFunction<? super R, ? extends V, E> after) {
        Objects.requireNonNull(after);
        return new FailableBiFunction() { // from class: org.apache.commons.lang3.function.FailableBiFunction$$ExternalSyntheticLambda1
            @Override // org.apache.commons.lang3.function.FailableBiFunction
            public final Object apply(Object obj, Object obj2) {
                Object apply;
                apply = after.apply(FailableBiFunction.this.apply(obj, obj2));
                return apply;
            }
        };
    }
}
