package org.apache.commons.lang3.function;

import java.lang.Throwable;
import java.util.Objects;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableLongPredicate<E extends Throwable> {
    public static final FailableLongPredicate FALSE = new FailableLongPredicate() { // from class: org.apache.commons.lang3.function.FailableLongPredicate$$ExternalSyntheticLambda3
        @Override // org.apache.commons.lang3.function.FailableLongPredicate
        public final boolean test(long j) {
            return FailableLongPredicate.lambda$static$0(j);
        }
    };
    public static final FailableLongPredicate TRUE = new FailableLongPredicate() { // from class: org.apache.commons.lang3.function.FailableLongPredicate$$ExternalSyntheticLambda4
        @Override // org.apache.commons.lang3.function.FailableLongPredicate
        public final boolean test(long j) {
            return FailableLongPredicate.lambda$static$1(j);
        }
    };

    boolean test(long j) throws Throwable;

    static /* synthetic */ boolean lambda$static$0(long t) throws Throwable {
        return false;
    }

    static /* synthetic */ boolean lambda$static$1(long t) throws Throwable {
        return true;
    }

    static <E extends Throwable> FailableLongPredicate<E> falsePredicate() {
        return FALSE;
    }

    static <E extends Throwable> FailableLongPredicate<E> truePredicate() {
        return TRUE;
    }

    default FailableLongPredicate<E> and(final FailableLongPredicate<E> other) {
        Objects.requireNonNull(other);
        return new FailableLongPredicate() { // from class: org.apache.commons.lang3.function.FailableLongPredicate$$ExternalSyntheticLambda0
            @Override // org.apache.commons.lang3.function.FailableLongPredicate
            public final boolean test(long j) {
                return FailableLongPredicate.lambda$and$2(FailableLongPredicate.this, other, j);
            }
        };
    }

    static /* synthetic */ boolean lambda$and$2(FailableLongPredicate _this, FailableLongPredicate other, long t) throws Throwable {
        return _this.test(t) && other.test(t);
    }

    static /* synthetic */ boolean lambda$negate$3(FailableLongPredicate _this, long t) throws Throwable {
        return !_this.test(t);
    }

    default FailableLongPredicate<E> negate() {
        return new FailableLongPredicate() { // from class: org.apache.commons.lang3.function.FailableLongPredicate$$ExternalSyntheticLambda1
            @Override // org.apache.commons.lang3.function.FailableLongPredicate
            public final boolean test(long j) {
                return FailableLongPredicate.lambda$negate$3(FailableLongPredicate.this, j);
            }
        };
    }

    default FailableLongPredicate<E> or(final FailableLongPredicate<E> other) {
        Objects.requireNonNull(other);
        return new FailableLongPredicate() { // from class: org.apache.commons.lang3.function.FailableLongPredicate$$ExternalSyntheticLambda2
            @Override // org.apache.commons.lang3.function.FailableLongPredicate
            public final boolean test(long j) {
                return FailableLongPredicate.lambda$or$4(FailableLongPredicate.this, other, j);
            }
        };
    }

    static /* synthetic */ boolean lambda$or$4(FailableLongPredicate _this, FailableLongPredicate other, long t) throws Throwable {
        return _this.test(t) || other.test(t);
    }
}
