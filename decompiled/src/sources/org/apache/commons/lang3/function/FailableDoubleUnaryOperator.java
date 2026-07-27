package org.apache.commons.lang3.function;

import com.github.mikephil.charting.utils.Utils;
import java.lang.Throwable;
import java.util.Objects;

/* loaded from: classes17.dex */
public interface FailableDoubleUnaryOperator<E extends Throwable> {
    public static final FailableDoubleUnaryOperator NOP = new FailableDoubleUnaryOperator() { // from class: org.apache.commons.lang3.function.FailableDoubleUnaryOperator$$ExternalSyntheticLambda1
        @Override // org.apache.commons.lang3.function.FailableDoubleUnaryOperator
        public final double applyAsDouble(double d) {
            double d2;
            d2 = Utils.DOUBLE_EPSILON;
            return d2;
        }
    };

    double applyAsDouble(double d) throws Throwable;

    static <E extends Throwable> FailableDoubleUnaryOperator<E> identity() {
        return new FailableDoubleUnaryOperator() { // from class: org.apache.commons.lang3.function.FailableDoubleUnaryOperator$$ExternalSyntheticLambda0
            @Override // org.apache.commons.lang3.function.FailableDoubleUnaryOperator
            public final double applyAsDouble(double d) {
                return FailableDoubleUnaryOperator.lambda$identity$1(d);
            }
        };
    }

    static /* synthetic */ double lambda$identity$1(double t) throws Throwable {
        return t;
    }

    static <E extends Throwable> FailableDoubleUnaryOperator<E> nop() {
        return NOP;
    }

    default FailableDoubleUnaryOperator<E> andThen(final FailableDoubleUnaryOperator<E> after) {
        Objects.requireNonNull(after);
        return new FailableDoubleUnaryOperator() { // from class: org.apache.commons.lang3.function.FailableDoubleUnaryOperator$$ExternalSyntheticLambda3
            @Override // org.apache.commons.lang3.function.FailableDoubleUnaryOperator
            public final double applyAsDouble(double d) {
                double applyAsDouble;
                applyAsDouble = after.applyAsDouble(FailableDoubleUnaryOperator.this.applyAsDouble(d));
                return applyAsDouble;
            }
        };
    }

    default FailableDoubleUnaryOperator<E> compose(final FailableDoubleUnaryOperator<E> before) {
        Objects.requireNonNull(before);
        return new FailableDoubleUnaryOperator() { // from class: org.apache.commons.lang3.function.FailableDoubleUnaryOperator$$ExternalSyntheticLambda2
            @Override // org.apache.commons.lang3.function.FailableDoubleUnaryOperator
            public final double applyAsDouble(double d) {
                double applyAsDouble;
                applyAsDouble = FailableDoubleUnaryOperator.this.applyAsDouble(before.applyAsDouble(d));
                return applyAsDouble;
            }
        };
    }
}
