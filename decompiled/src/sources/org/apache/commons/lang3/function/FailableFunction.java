package org.apache.commons.lang3.function;

import java.lang.Throwable;
import java.util.Objects;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableFunction<T, R, E extends Throwable> {
    public static final FailableFunction NOP = new FailableFunction() { // from class: org.apache.commons.lang3.function.FailableFunction$$ExternalSyntheticLambda1
        @Override // org.apache.commons.lang3.function.FailableFunction
        public final Object apply(Object obj) {
            return FailableFunction.lambda$static$0(obj);
        }
    };

    R apply(T t) throws Throwable;

    static /* synthetic */ Object lambda$static$0(Object t) throws Throwable {
        return null;
    }

    static <T, E extends Throwable> FailableFunction<T, T, E> identity() {
        return new FailableFunction() { // from class: org.apache.commons.lang3.function.FailableFunction$$ExternalSyntheticLambda3
            @Override // org.apache.commons.lang3.function.FailableFunction
            public final Object apply(Object obj) {
                return FailableFunction.lambda$identity$1(obj);
            }
        };
    }

    static /* synthetic */ Object lambda$identity$1(Object t) throws Throwable {
        return t;
    }

    static <T, R, E extends Throwable> FailableFunction<T, R, E> nop() {
        return NOP;
    }

    default <V> FailableFunction<T, V, E> andThen(final FailableFunction<? super R, ? extends V, E> after) {
        Objects.requireNonNull(after);
        return new FailableFunction() { // from class: org.apache.commons.lang3.function.FailableFunction$$ExternalSyntheticLambda2
            @Override // org.apache.commons.lang3.function.FailableFunction
            public final Object apply(Object obj) {
                Object apply;
                apply = after.apply(FailableFunction.this.apply(obj));
                return apply;
            }
        };
    }

    default <V> FailableFunction<V, R, E> compose(final FailableFunction<? super V, ? extends T, E> before) {
        Objects.requireNonNull(before);
        return new FailableFunction() { // from class: org.apache.commons.lang3.function.FailableFunction$$ExternalSyntheticLambda0
            @Override // org.apache.commons.lang3.function.FailableFunction
            public final Object apply(Object obj) {
                Object apply;
                apply = FailableFunction.this.apply(before.apply(obj));
                return apply;
            }
        };
    }
}
