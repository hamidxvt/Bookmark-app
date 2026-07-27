package org.apache.commons.lang3.function;

import java.lang.Throwable;
import java.util.Objects;

@FunctionalInterface
/* loaded from: classes17.dex */
public interface FailableDoublePredicate<E extends Throwable> {
    public static final FailableDoublePredicate FALSE = new FailableDoublePredicate() { // from class: org.apache.commons.lang3.function.FailableDoublePredicate$$ExternalSyntheticLambda3
        @Override // org.apache.commons.lang3.function.FailableDoublePredicate
        public final boolean test(double d) {
            return FailableDoublePredicate.lambda$static$0(d);
        }
    };
    public static final FailableDoublePredicate TRUE = new FailableDoublePredicate() { // from class: org.apache.commons.lang3.function.FailableDoublePredicate$$ExternalSyntheticLambda4
        @Override // org.apache.commons.lang3.function.FailableDoublePredicate
        public final boolean test(double d) {
            return FailableDoublePredicate.lambda$static$1(d);
        }
    };

    boolean test(double d) throws Throwable;

    static /* synthetic */ boolean lambda$static$0(double t) throws Throwable {
        return false;
    }

    static /* synthetic */ boolean lambda$static$1(double t) throws Throwable {
        return true;
    }

    static <E extends Throwable> FailableDoublePredicate<E> falsePredicate() {
        return FALSE;
    }

    static <E extends Throwable> FailableDoublePredicate<E> truePredicate() {
        return TRUE;
    }

    default FailableDoublePredicate<E> and(final FailableDoublePredicate<E> other) {
        Objects.requireNonNull(other);
        return new FailableDoublePredicate() { // from class: org.apache.commons.lang3.function.FailableDoublePredicate$$ExternalSyntheticLambda1
            @Override // org.apache.commons.lang3.function.FailableDoublePredicate
            public final boolean test(double d) {
                return FailableDoublePredicate.lambda$and$2(FailableDoublePredicate.this, other, d);
            }
        };
    }

    static /* synthetic */ boolean lambda$and$2(FailableDoublePredicate _this, FailableDoublePredicate other, double t) throws Throwable {
        return _this.test(t) && other.test(t);
    }

    static /* synthetic */ boolean lambda$negate$3(FailableDoublePredicate _this, double t) throws Throwable {
        return !_this.test(t);
    }

    default FailableDoublePredicate<E> negate() {
        return new FailableDoublePredicate() { // from class: org.apache.commons.lang3.function.FailableDoublePredicate$$ExternalSyntheticLambda2
            @Override // org.apache.commons.lang3.function.FailableDoublePredicate
            public final boolean test(double d) {
                return FailableDoublePredicate.lambda$negate$3(FailableDoublePredicate.this, d);
            }
        };
    }

    default FailableDoublePredicate<E> or(final FailableDoublePredicate<E> other) {
        Objects.requireNonNull(other);
        return new FailableDoublePredicate() { // from class: org.apache.commons.lang3.function.FailableDoublePredicate$$ExternalSyntheticLambda0
            @Override // org.apache.commons.lang3.function.FailableDoublePredicate
            public final boolean test(double d) {
                return FailableDoublePredicate.lambda$or$4(FailableDoublePredicate.this, other, d);
            }
        };
    }

    static /* synthetic */ boolean lambda$or$4(FailableDoublePredicate _this, FailableDoublePredicate other, double t) throws Throwable {
        return _this.test(t) || other.test(t);
    }
}
