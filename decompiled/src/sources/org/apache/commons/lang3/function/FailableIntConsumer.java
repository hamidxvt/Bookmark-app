package org.apache.commons.lang3.function;

import java.lang.Throwable;
import java.util.Objects;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableIntConsumer<E extends Throwable> {
    public static final FailableIntConsumer NOP = new FailableIntConsumer() { // from class: org.apache.commons.lang3.function.FailableIntConsumer$$ExternalSyntheticLambda1
        @Override // org.apache.commons.lang3.function.FailableIntConsumer
        public final void accept(int i) {
            FailableIntConsumer.lambda$static$0(i);
        }
    };

    void accept(int i) throws Throwable;

    static /* synthetic */ void lambda$static$0(int t) throws Throwable {
    }

    static <E extends Throwable> FailableIntConsumer<E> nop() {
        return NOP;
    }

    default FailableIntConsumer<E> andThen(final FailableIntConsumer<E> after) {
        Objects.requireNonNull(after);
        return new FailableIntConsumer() { // from class: org.apache.commons.lang3.function.FailableIntConsumer$$ExternalSyntheticLambda0
            @Override // org.apache.commons.lang3.function.FailableIntConsumer
            public final void accept(int i) {
                FailableIntConsumer.lambda$andThen$1(FailableIntConsumer.this, after, i);
            }
        };
    }

    static /* synthetic */ void lambda$andThen$1(FailableIntConsumer _this, FailableIntConsumer after, int t) throws Throwable {
        _this.accept(t);
        after.accept(t);
    }
}
