package org.apache.commons.lang3.compare;

import java.util.function.Predicate;

/* loaded from: classes17.dex */
public class ComparableUtils {

    public static class ComparableCheckBuilder<A extends Comparable<A>> {
        private final A a;

        private ComparableCheckBuilder(A a) {
            this.a = a;
        }

        public boolean between(A b, A c) {
            return betweenOrdered(b, c) || betweenOrdered(c, b);
        }

        public boolean betweenExclusive(A b, A c) {
            return betweenOrderedExclusive(b, c) || betweenOrderedExclusive(c, b);
        }

        private boolean betweenOrdered(A b, A c) {
            return greaterThanOrEqualTo(b) && lessThanOrEqualTo(c);
        }

        private boolean betweenOrderedExclusive(A b, A c) {
            return greaterThan(b) && lessThan(c);
        }

        public boolean equalTo(A b) {
            return this.a.compareTo(b) == 0;
        }

        public boolean greaterThan(A b) {
            return this.a.compareTo(b) > 0;
        }

        public boolean greaterThanOrEqualTo(A b) {
            return this.a.compareTo(b) >= 0;
        }

        public boolean lessThan(A b) {
            return this.a.compareTo(b) < 0;
        }

        public boolean lessThanOrEqualTo(A b) {
            return this.a.compareTo(b) <= 0;
        }
    }

    public static <A extends Comparable<A>> Predicate<A> between(final A b, final A c) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.ComparableUtils$$ExternalSyntheticLambda2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean between;
                between = ComparableUtils.is((Comparable) obj).between(b, c);
                return between;
            }
        };
    }

    public static <A extends Comparable<A>> Predicate<A> betweenExclusive(final A b, final A c) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.ComparableUtils$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean betweenExclusive;
                betweenExclusive = ComparableUtils.is((Comparable) obj).betweenExclusive(b, c);
                return betweenExclusive;
            }
        };
    }

    public static <A extends Comparable<A>> Predicate<A> ge(final A b) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.ComparableUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean greaterThanOrEqualTo;
                greaterThanOrEqualTo = ComparableUtils.is((Comparable) obj).greaterThanOrEqualTo(b);
                return greaterThanOrEqualTo;
            }
        };
    }

    public static <A extends Comparable<A>> Predicate<A> gt(final A b) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.ComparableUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean greaterThan;
                greaterThan = ComparableUtils.is((Comparable) obj).greaterThan(b);
                return greaterThan;
            }
        };
    }

    public static <A extends Comparable<A>> ComparableCheckBuilder<A> is(A a) {
        return new ComparableCheckBuilder<>(a);
    }

    public static <A extends Comparable<A>> Predicate<A> le(final A b) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.ComparableUtils$$ExternalSyntheticLambda5
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean lessThanOrEqualTo;
                lessThanOrEqualTo = ComparableUtils.is((Comparable) obj).lessThanOrEqualTo(b);
                return lessThanOrEqualTo;
            }
        };
    }

    public static <A extends Comparable<A>> Predicate<A> lt(final A b) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.ComparableUtils$$ExternalSyntheticLambda4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean lessThan;
                lessThan = ComparableUtils.is((Comparable) obj).lessThan(b);
                return lessThan;
            }
        };
    }

    private ComparableUtils() {
    }
}
