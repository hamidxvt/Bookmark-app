package org.apache.commons.lang3.function;

import java.lang.Throwable;
import java.util.Objects;

/* loaded from: classes17.dex */
public interface FailableLongUnaryOperator<E extends Throwable> {
    public static final FailableLongUnaryOperator NOP = new FailableLongUnaryOperator() { // from class: org.apache.commons.lang3.function.FailableLongUnaryOperator$$ExternalSyntheticLambda2
        @Override // org.apache.commons.lang3.function.FailableLongUnaryOperator
        public final long applyAsLong(long j) {
            return FailableLongUnaryOperator.lambda$static$0(j);
        }
    };

    long applyAsLong(long j) throws Throwable;

    static /* synthetic */ long lambda$static$0(long t) throws Throwable {
        return 0L;
    }

    static <E extends Throwable> FailableLongUnaryOperator<E> identity() {
        return new FailableLongUnaryOperator() { // from class: org.apache.commons.lang3.function.FailableLongUnaryOperator$$ExternalSyntheticLambda0
            @Override // org.apache.commons.lang3.function.FailableLongUnaryOperator
            public final long applyAsLong(long j) {
                return FailableLongUnaryOperator.lambda$identity$1(j);
            }
        };
    }

    static /* synthetic */ long lambda$identity$1(long t) throws Throwable {
        return t;
    }

    static <E extends Throwable> FailableLongUnaryOperator<E> nop() {
        return NOP;
    }

    default FailableLongUnaryOperator<E> andThen(final FailableLongUnaryOperator<E> after) {
        Objects.requireNonNull(after);
        return new FailableLongUnaryOperator() { // from class: org.apache.commons.lang3.function.FailableLongUnaryOperator$$ExternalSyntheticLambda1
            @Override // org.apache.commons.lang3.function.FailableLongUnaryOperator
            public final long applyAsLong(long j) {
                long applyAsLong;
                applyAsLong = after.applyAsLong(FailableLongUnaryOperator.this.applyAsLong(j));
                return applyAsLong;
            }
        };
    }

    default FailableLongUnaryOperator<E> compose(final FailableLongUnaryOperator<E> before) {
        Objects.requireNonNull(before);
        return new FailableLongUnaryOperator() { // from class: org.apache.commons.lang3.function.FailableLongUnaryOperator$$ExternalSyntheticLambda3
            @Override // org.apache.commons.lang3.function.FailableLongUnaryOperator
            public final long applyAsLong(long j) {
                long applyAsLong;
                applyAsLong = FailableLongUnaryOperator.this.applyAsLong(before.applyAsLong(j));
                return applyAsLong;
            }
        };
    }
}
