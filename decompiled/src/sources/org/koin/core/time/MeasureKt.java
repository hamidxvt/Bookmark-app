package org.koin.core.time;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.TimeMark;
import kotlin.time.TimeSource;
import kotlin.time.TimedValue;

/* compiled from: Measure.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u001a&\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00010\u0006\"\u0004\b\u0000\u0010\u00072\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0003¨\u0006\b"}, d2 = {"measureDuration", "", "code", "Lkotlin/Function0;", "", "measureDurationForResult", "Lkotlin/Pair;", "T", "koin-core"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class MeasureKt {
    public static final double measureDuration(Function0<Unit> code) {
        Intrinsics.checkNotNullParameter(code, "code");
        TimeSource $this$measureTime$iv$iv = TimeSource.Monotonic.INSTANCE;
        TimeMark mark$iv$iv = $this$measureTime$iv$iv.markNow();
        code.invoke();
        return Duration.toDouble-impl(mark$iv$iv.mo1904elapsedNowUwyO8pc(), TimeUnit.MILLISECONDS);
    }

    public static final <T> Pair<T, Double> measureDurationForResult(Function0<? extends T> code) {
        Intrinsics.checkNotNullParameter(code, "code");
        TimeSource $this$measureTimedValue$iv$iv = TimeSource.Monotonic.INSTANCE;
        TimeMark mark$iv$iv = $this$measureTimedValue$iv$iv.markNow();
        Object result$iv$iv = code.invoke();
        TimedValue result = new TimedValue(result$iv$iv, mark$iv$iv.mo1904elapsedNowUwyO8pc(), null);
        return new Pair<>(result.getValue(), Double.valueOf(Duration.toDouble-impl(result.m2045getDurationUwyO8pc(), TimeUnit.MILLISECONDS)));
    }
}
