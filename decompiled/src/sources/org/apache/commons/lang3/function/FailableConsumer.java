package org.apache.commons.lang3.function;

import java.lang.Throwable;
import java.util.Objects;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableConsumer<T, E extends Throwable> {
    public static final FailableConsumer NOP = new FailableConsumer() { // from class: org.apache.commons.lang3.function.FailableConsumer$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableConsumer
        public final void accept(Object obj) {
            FailableConsumer.lambda$static$0(obj);
        }
    };

    void accept(T t) throws Throwable;

    static /* synthetic */ void lambda$static$0(Object t) throws Throwable {
    }

    static <T, E extends Throwable> FailableConsumer<T, E> nop() {
        return NOP;
    }

    default FailableConsumer<T, E> andThen(final FailableConsumer<? super T, E> after) {
        Objects.requireNonNull(after);
        return new FailableConsumer() { // from class: org.apache.commons.lang3.function.FailableConsumer$$ExternalSyntheticLambda1
            @Override // org.apache.commons.lang3.function.FailableConsumer
            public final void accept(Object obj) {
                FailableConsumer.lambda$andThen$1(FailableConsumer.this, after, obj);
            }
        };
    }

    static /* synthetic */ void lambda$andThen$1(FailableConsumer _this, FailableConsumer after, Object t) throws Throwable {
        _this.accept(t);
        after.accept(t);
    }
}
