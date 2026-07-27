package org.apache.commons.lang3.function;

import java.lang.Throwable;
import java.util.Objects;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableBiPredicate<T, U, E extends Throwable> {
    public static final FailableBiPredicate FALSE = new FailableBiPredicate() { // from class: org.apache.commons.lang3.function.FailableBiPredicate$$ExternalSyntheticLambda3
        @Override // org.apache.commons.lang3.function.FailableBiPredicate
        public final boolean test(Object obj, Object obj2) {
            return FailableBiPredicate.lambda$static$0(obj, obj2);
        }
    };
    public static final FailableBiPredicate TRUE = new FailableBiPredicate() { // from class: org.apache.commons.lang3.function.FailableBiPredicate$$ExternalSyntheticLambda4
        @Override // org.apache.commons.lang3.function.FailableBiPredicate
        public final boolean test(Object obj, Object obj2) {
            return FailableBiPredicate.lambda$static$1(obj, obj2);
        }
    };

    boolean test(T t, U u) throws Throwable;

    static /* synthetic */ boolean lambda$static$0(Object t, Object u) throws Throwable {
        return false;
    }

    static /* synthetic */ boolean lambda$static$1(Object t, Object u) throws Throwable {
        return true;
    }

    static <T, U, E extends Throwable> FailableBiPredicate<T, U, E> falsePredicate() {
        return FALSE;
    }

    static <T, U, E extends Throwable> FailableBiPredicate<T, U, E> truePredicate() {
        return TRUE;
    }

    default FailableBiPredicate<T, U, E> and(final FailableBiPredicate<? super T, ? super U, E> other) {
        Objects.requireNonNull(other);
        return new FailableBiPredicate() { // from class: org.apache.commons.lang3.function.FailableBiPredicate$$ExternalSyntheticLambda1
            @Override // org.apache.commons.lang3.function.FailableBiPredicate
            public final boolean test(Object obj, Object obj2) {
                return FailableBiPredicate.lambda$and$2(FailableBiPredicate.this, other, obj, obj2);
            }
        };
    }

    static /* synthetic */ boolean lambda$and$2(FailableBiPredicate _this, FailableBiPredicate other, Object t, Object u) throws Throwable {
        return _this.test(t, u) && other.test(t, u);
    }

    static /* synthetic */ boolean lambda$negate$3(FailableBiPredicate _this, Object t, Object u) throws Throwable {
        return !_this.test(t, u);
    }

    default FailableBiPredicate<T, U, E> negate() {
        return new FailableBiPredicate() { // from class: org.apache.commons.lang3.function.FailableBiPredicate$$ExternalSyntheticLambda0
            @Override // org.apache.commons.lang3.function.FailableBiPredicate
            public final boolean test(Object obj, Object obj2) {
                return FailableBiPredicate.lambda$negate$3(FailableBiPredicate.this, obj, obj2);
            }
        };
    }

    default FailableBiPredicate<T, U, E> or(final FailableBiPredicate<? super T, ? super U, E> other) {
        Objects.requireNonNull(other);
        return new FailableBiPredicate() { // from class: org.apache.commons.lang3.function.FailableBiPredicate$$ExternalSyntheticLambda2
            @Override // org.apache.commons.lang3.function.FailableBiPredicate
            public final boolean test(Object obj, Object obj2) {
                return FailableBiPredicate.lambda$or$4(FailableBiPredicate.this, other, obj, obj2);
            }
        };
    }

    static /* synthetic */ boolean lambda$or$4(FailableBiPredicate _this, FailableBiPredicate other, Object t, Object u) throws Throwable {
        return _this.test(t, u) || other.test(t, u);
    }
}
