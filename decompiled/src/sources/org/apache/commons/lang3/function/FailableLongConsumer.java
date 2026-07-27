package org.apache.commons.lang3.function;

import java.lang.Throwable;
import java.util.Objects;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableLongConsumer<E extends Throwable> {
    public static final FailableLongConsumer NOP = new FailableLongConsumer() { // from class: org.apache.commons.lang3.function.FailableLongConsumer$$ExternalSyntheticLambda0
        @Override // org.apache.commons.lang3.function.FailableLongConsumer
        public final void accept(long j) {
            FailableLongConsumer.lambda$static$0(j);
        }
    };

    void accept(long j) throws Throwable;

    static /* synthetic */ void lambda$static$0(long t) throws Throwable {
    }

    static <E extends Throwable> FailableLongConsumer<E> nop() {
        return NOP;
    }

    default FailableLongConsumer<E> andThen(final FailableLongConsumer<E> after) {
        Objects.requireNonNull(after);
        return new FailableLongConsumer() { // from class: org.apache.commons.lang3.function.FailableLongConsumer$$ExternalSyntheticLambda1
            @Override // org.apache.commons.lang3.function.FailableLongConsumer
            public final void accept(long j) {
                FailableLongConsumer.lambda$andThen$1(FailableLongConsumer.this, after, j);
            }
        };
    }

    static /* synthetic */ void lambda$andThen$1(FailableLongConsumer _this, FailableLongConsumer after, long t) throws Throwable {
        _this.accept(t);
        after.accept(t);
    }
}
