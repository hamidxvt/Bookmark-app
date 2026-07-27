package org.apache.commons.lang3.function;

import java.lang.Throwable;
import java.util.Objects;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableIntPredicate<E extends Throwable> {
    public static final FailableIntPredicate FALSE = new FailableIntPredicate() { // from class: org.apache.commons.lang3.function.FailableIntPredicate$$ExternalSyntheticLambda1
        @Override // org.apache.commons.lang3.function.FailableIntPredicate
        public final boolean test(int i) {
            return FailableIntPredicate.lambda$static$0(i);
        }
    };
    public static final FailableIntPredicate TRUE = new FailableIntPredicate() { // from class: org.apache.commons.lang3.function.FailableIntPredicate$$ExternalSyntheticLambda2
        @Override // org.apache.commons.lang3.function.FailableIntPredicate
        public final boolean test(int i) {
            return FailableIntPredicate.lambda$static$1(i);
        }
    };

    boolean test(int i) throws Throwable;

    static /* synthetic */ boolean lambda$static$0(int t) throws Throwable {
        return false;
    }

    static /* synthetic */ boolean lambda$static$1(int t) throws Throwable {
        return true;
    }

    static <E extends Throwable> FailableIntPredicate<E> falsePredicate() {
        return FALSE;
    }

    static <E extends Throwable> FailableIntPredicate<E> truePredicate() {
        return TRUE;
    }

    default FailableIntPredicate<E> and(final FailableIntPredicate<E> other) {
        Objects.requireNonNull(other);
        return new FailableIntPredicate() { // from class: org.apache.commons.lang3.function.FailableIntPredicate$$ExternalSyntheticLambda0
            @Override // org.apache.commons.lang3.function.FailableIntPredicate
            public final boolean test(int i) {
                return FailableIntPredicate.lambda$and$2(FailableIntPredicate.this, other, i);
            }
        };
    }

    static /* synthetic */ boolean lambda$and$2(FailableIntPredicate _this, FailableIntPredicate other, int t) throws Throwable {
        return _this.test(t) && other.test(t);
    }

    static /* synthetic */ boolean lambda$negate$3(FailableIntPredicate _this, int t) throws Throwable {
        return !_this.test(t);
    }

    default FailableIntPredicate<E> negate() {
        return new FailableIntPredicate() { // from class: org.apache.commons.lang3.function.FailableIntPredicate$$ExternalSyntheticLambda4
            @Override // org.apache.commons.lang3.function.FailableIntPredicate
            public final boolean test(int i) {
                return FailableIntPredicate.lambda$negate$3(FailableIntPredicate.this, i);
            }
        };
    }

    default FailableIntPredicate<E> or(final FailableIntPredicate<E> other) {
        Objects.requireNonNull(other);
        return new FailableIntPredicate() { // from class: org.apache.commons.lang3.function.FailableIntPredicate$$ExternalSyntheticLambda3
            @Override // org.apache.commons.lang3.function.FailableIntPredicate
            public final boolean test(int i) {
                return FailableIntPredicate.lambda$or$4(FailableIntPredicate.this, other, i);
            }
        };
    }

    static /* synthetic */ boolean lambda$or$4(FailableIntPredicate _this, FailableIntPredicate other, int t) throws Throwable {
        return _this.test(t) || other.test(t);
    }
}
