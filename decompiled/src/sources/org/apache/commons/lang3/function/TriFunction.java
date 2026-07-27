package org.apache.commons.lang3.function;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);

    default <W> TriFunction<T, U, V, W> andThen(final Function<? super R, ? extends W> after) {
        Objects.requireNonNull(after);
        return new TriFunction() { // from class: org.apache.commons.lang3.function.TriFunction$$ExternalSyntheticLambda0
            @Override // org.apache.commons.lang3.function.TriFunction
            public final Object apply(Object obj, Object obj2, Object obj3) {
                Object apply;
                apply = after.apply(TriFunction.this.apply(obj, obj2, obj3));
                return apply;
            }
        };
    }
}
