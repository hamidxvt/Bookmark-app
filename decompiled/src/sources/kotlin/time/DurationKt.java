package kotlin.time;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Marker;

/* compiled from: Duration.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0015\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¢\u0006\u0002\u0010\u0005\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¢\u0006\u0002\u0010\u0007\u001a\u0019\u0010\u0000\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¢\u0006\u0002\u0010\t\u001a\u001c\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0001H\u0087\n¢\u0006\u0004\b\f\u0010\r\u001a\u001c\u0010\n\u001a\u00020\u0001*\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0001H\u0087\n¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u001d\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002¢\u0006\u0002\u0010\u0015\u001a\u0010\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0002\u001a)\u0010\u0017\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00022\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00140\u001aH\u0082\b\u001a)\u0010\u001c\u001a\u00020\u0002*\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00022\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00140\u001aH\u0082\b\u001a\u0010\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u0006H\u0002\u001a\u0010\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u0006H\u0002\u001a\u0015\u0010%\u001a\u00020\u00012\u0006\u0010&\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010'\u001a\u0015\u0010(\u001a\u00020\u00012\u0006\u0010)\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010'\u001a\u001d\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u0002H\u0002¢\u0006\u0002\u0010-\u001a\u0015\u0010.\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010'\u001a\u0015\u0010/\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010'\"\u000e\u0010\u001d\u001a\u00020\u0002X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001f\u001a\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010 \u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"toDuration", "Lkotlin/time/Duration;", "", "unit", "Lkotlin/time/DurationUnit;", "(ILkotlin/time/DurationUnit;)J", "", "(JLkotlin/time/DurationUnit;)J", "", "(DLkotlin/time/DurationUnit;)J", "times", TypedValues.TransitionType.S_DURATION, "times-mvk6XK0", "(IJ)J", "times-kIfJnKk", "(DJ)J", "parseDuration", "value", "", "strictIso", "", "(Ljava/lang/String;Z)J", "parseOverLongIsoComponent", "substringWhile", "startIndex", "predicate", "Lkotlin/Function1;", "", "skipWhile", "NANOS_IN_MILLIS", "MAX_NANOS", "MAX_MILLIS", "MAX_NANOS_IN_MILLIS", "nanosToMillis", "nanos", "millisToNanos", "millis", "durationOfNanos", "normalNanos", "(J)J", "durationOfMillis", "normalMillis", "durationOf", "normalValue", "unitDiscriminator", "(JI)J", "durationOfNanosNormalized", "durationOfMillisNormalized", "kotlin-stdlib"}, k = 2, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class DurationKt {
    public static final long MAX_MILLIS = 4611686018427387903L;
    public static final long MAX_NANOS = 4611686018426999999L;
    private static final long MAX_NANOS_IN_MILLIS = 4611686018426L;
    public static final int NANOS_IN_MILLIS = 1000000;

    public static final long toDuration(int $this$toDuration, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (unit.compareTo(DurationUnit.SECONDS) <= 0) {
            return durationOfNanos(DurationUnitKt.convertDurationUnitOverflow($this$toDuration, unit, DurationUnit.NANOSECONDS));
        }
        return toDuration($this$toDuration, unit);
    }

    public static final long toDuration(long $this$toDuration, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        long maxNsInUnit = DurationUnitKt.convertDurationUnitOverflow(MAX_NANOS, DurationUnit.NANOSECONDS, unit);
        boolean z = false;
        if ((-maxNsInUnit) <= $this$toDuration && $this$toDuration <= maxNsInUnit) {
            z = true;
        }
        if (z) {
            return durationOfNanos(DurationUnitKt.convertDurationUnitOverflow($this$toDuration, unit, DurationUnit.NANOSECONDS));
        }
        long millis = DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.MILLISECONDS);
        return durationOfMillis(RangesKt.coerceIn(millis, -4611686018427387903L, MAX_MILLIS));
    }

    public static final long toDuration(double $this$toDuration, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        double valueInNs = DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.NANOSECONDS);
        if (Double.isNaN(valueInNs)) {
            throw new IllegalArgumentException("Duration value cannot be NaN.".toString());
        }
        long nanos = MathKt.roundToLong(valueInNs);
        boolean z = false;
        if (-4611686018426999999L <= nanos && nanos < 4611686018427000000L) {
            z = true;
        }
        if (z) {
            long millis = durationOfNanos(nanos);
            return millis;
        }
        long millis2 = MathKt.roundToLong(DurationUnitKt.convertDurationUnit($this$toDuration, unit, DurationUnit.MILLISECONDS));
        return durationOfMillisNormalized(millis2);
    }

    /* renamed from: times-mvk6XK0, reason: not valid java name */
    private static final long m2011timesmvk6XK0(int $this$times_u2dmvk6XK0, long duration) {
        return Duration.m1945timesUwyO8pc(duration, $this$times_u2dmvk6XK0);
    }

    /* renamed from: times-kIfJnKk, reason: not valid java name */
    private static final long m2010timeskIfJnKk(double $this$times_u2dkIfJnKk, long duration) {
        return Duration.m1944timesUwyO8pc(duration, $this$times_u2dkIfJnKk);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0277 A[LOOP:4: B:134:0x024c->B:146:0x0277, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0280 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0342 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0303 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00bc A[LOOP:1: B:26:0x007b->B:38:0x00bc, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00d7 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final long parseDuration(String value, boolean strictIso) {
        int index;
        boolean isNegative;
        boolean afterFirst;
        boolean allowSpaces;
        DurationUnit unit;
        int dotIndex;
        boolean z;
        String nonDigitSymbols;
        String infinityString;
        boolean hasSign;
        boolean isNegative2;
        boolean z2;
        int length = value.length();
        if (length == 0) {
            throw new IllegalArgumentException("The string is empty");
        }
        long result = Duration.INSTANCE.m2005getZEROUwyO8pc();
        String infinityString2 = "Infinity";
        switch (value.charAt(0)) {
            case '+':
            case '-':
                int index2 = 0 + 1;
                index = index2;
                break;
            case ',':
            default:
                index = 0;
                break;
        }
        boolean hasSign2 = index > 0;
        boolean isNegative3 = hasSign2 && StringsKt.startsWith$default((CharSequence) value, '-', false, 2, (Object) null);
        if (length <= index) {
            throw new IllegalArgumentException("No components");
        }
        if (value.charAt(index) == 'P') {
            int index3 = index + 1;
            if (index3 == length) {
                throw new IllegalArgumentException();
            }
            String nonDigitSymbols2 = "+-.";
            boolean isTimeComponent = false;
            DurationUnit prevUnit = null;
            while (index3 < length) {
                if (value.charAt(index3) == 'T') {
                    if (isTimeComponent || (index3 = index3 + 1) == length) {
                        throw new IllegalArgumentException();
                    }
                    isTimeComponent = true;
                } else {
                    int $i$f$substringWhile = 0;
                    String $this$skipWhile$iv$iv = value;
                    int i$iv$iv = index3;
                    while (true) {
                        int $i$f$substringWhile2 = $i$f$substringWhile;
                        int $i$f$substringWhile3 = $this$skipWhile$iv$iv.length();
                        if (i$iv$iv < $i$f$substringWhile3) {
                            isNegative2 = isNegative3;
                            String $this$skipWhile$iv$iv2 = $this$skipWhile$iv$iv;
                            char it = $this$skipWhile$iv$iv2.charAt(i$iv$iv);
                            if ('0' <= it && it < ':') {
                                nonDigitSymbols = nonDigitSymbols2;
                                infinityString = infinityString2;
                                hasSign = hasSign2;
                            } else {
                                nonDigitSymbols = nonDigitSymbols2;
                                infinityString = infinityString2;
                                hasSign = hasSign2;
                                if (!StringsKt.contains$default((CharSequence) nonDigitSymbols2, it, false, 2, (Object) null)) {
                                    z2 = false;
                                    if (!z2) {
                                        i$iv$iv++;
                                        hasSign2 = hasSign;
                                        infinityString2 = infinityString;
                                        $i$f$substringWhile = $i$f$substringWhile2;
                                        isNegative3 = isNegative2;
                                        $this$skipWhile$iv$iv = $this$skipWhile$iv$iv2;
                                        nonDigitSymbols2 = nonDigitSymbols;
                                    }
                                }
                            }
                            z2 = true;
                            if (!z2) {
                            }
                        } else {
                            nonDigitSymbols = nonDigitSymbols2;
                            infinityString = infinityString2;
                            hasSign = hasSign2;
                            isNegative2 = isNegative3;
                        }
                    }
                    Intrinsics.checkNotNull(value, "null cannot be cast to non-null type java.lang.String");
                    String component = value.substring(index3, i$iv$iv);
                    Intrinsics.checkNotNullExpressionValue(component, "substring(...)");
                    if (component.length() == 0) {
                        throw new IllegalArgumentException();
                    }
                    int index4 = index3 + component.length();
                    String str = value;
                    if (!(index4 >= 0 && index4 < str.length())) {
                        throw new IllegalArgumentException("Missing unit for value " + component);
                    }
                    char unitChar = str.charAt(index4);
                    index3 = index4 + 1;
                    DurationUnit unit2 = DurationUnitKt.durationUnitByIsoChar(unitChar, isTimeComponent);
                    DurationUnit prevUnit2 = prevUnit;
                    if (prevUnit2 != null && prevUnit2.compareTo(unit2) <= 0) {
                        throw new IllegalArgumentException("Unexpected order of duration components");
                    }
                    prevUnit = unit2;
                    int dotIndex2 = StringsKt.indexOf$default((CharSequence) component, ClassUtils.PACKAGE_SEPARATOR_CHAR, 0, false, 6, (Object) null);
                    if (unit2 == DurationUnit.SECONDS && dotIndex2 > 0) {
                        Intrinsics.checkNotNull(component, "null cannot be cast to non-null type java.lang.String");
                        String whole = component.substring(0, dotIndex2);
                        Intrinsics.checkNotNullExpressionValue(whole, "substring(...)");
                        long result2 = Duration.m1943plusLRDsOJo(result, toDuration(parseOverLongIsoComponent(whole), unit2));
                        Intrinsics.checkNotNull(component, "null cannot be cast to non-null type java.lang.String");
                        String substring = component.substring(dotIndex2);
                        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                        result = Duration.m1943plusLRDsOJo(result2, toDuration(Double.parseDouble(substring), unit2));
                        hasSign2 = hasSign;
                        infinityString2 = infinityString;
                        isNegative3 = isNegative2;
                        nonDigitSymbols2 = nonDigitSymbols;
                    } else {
                        result = Duration.m1943plusLRDsOJo(result, toDuration(parseOverLongIsoComponent(component), unit2));
                        hasSign2 = hasSign;
                        infinityString2 = infinityString;
                        isNegative3 = isNegative2;
                        nonDigitSymbols2 = nonDigitSymbols;
                    }
                }
            }
            isNegative = isNegative3;
        } else {
            isNegative = isNegative3;
            if (!strictIso) {
                if (StringsKt.regionMatches(value, index, "Infinity", 0, Math.max(length - index, "Infinity".length()), true)) {
                    result = Duration.INSTANCE.m2003getINFINITEUwyO8pc();
                } else {
                    DurationUnit prevUnit3 = null;
                    boolean afterFirst2 = false;
                    boolean allowSpaces2 = !hasSign2;
                    if (hasSign2 && value.charAt(index) == '(' && StringsKt.last(value) == ')') {
                        allowSpaces2 = true;
                        index++;
                        length--;
                        if (index == length) {
                            throw new IllegalArgumentException("No components");
                        }
                    }
                    while (index < length) {
                        if (afterFirst2 && allowSpaces2) {
                            int i$iv = index;
                            while (i$iv < value.length()) {
                                boolean afterFirst3 = afterFirst2;
                                if ((value.charAt(i$iv) == ' ' ? (char) 1 : (char) 0) != 0) {
                                    i$iv++;
                                    afterFirst2 = afterFirst3;
                                } else {
                                    index = i$iv;
                                }
                            }
                            index = i$iv;
                        }
                        boolean afterFirst4 = true;
                        int i$iv$iv2 = index;
                        while (true) {
                            afterFirst = afterFirst4;
                            if (i$iv$iv2 < value.length()) {
                                char it2 = value.charAt(i$iv$iv2);
                                allowSpaces = allowSpaces2;
                                if ('0' <= it2 && it2 < ':') {
                                    z = true;
                                    if (((!z || it2 == '.') ? (char) 1 : (char) 0) == 0) {
                                        i$iv$iv2++;
                                        afterFirst4 = afterFirst;
                                        allowSpaces2 = allowSpaces;
                                    }
                                }
                                z = false;
                                if (((!z || it2 == '.') ? (char) 1 : (char) 0) == 0) {
                                }
                            } else {
                                allowSpaces = allowSpaces2;
                            }
                        }
                        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type java.lang.String");
                        String component2 = value.substring(index, i$iv$iv2);
                        Intrinsics.checkNotNullExpressionValue(component2, "substring(...)");
                        if (component2.length() == 0) {
                            throw new IllegalArgumentException();
                        }
                        int index5 = index + component2.length();
                        int $i$f$substringWhile4 = 0;
                        int i$iv$iv3 = index5;
                        while (i$iv$iv3 < value.length()) {
                            char it3 = value.charAt(i$iv$iv3);
                            int $i$f$substringWhile5 = $i$f$substringWhile4;
                            if ('a' <= it3 && it3 < '{') {
                                i$iv$iv3++;
                                $i$f$substringWhile4 = $i$f$substringWhile5;
                            } else {
                                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type java.lang.String");
                                String unitName = value.substring(index5, i$iv$iv3);
                                Intrinsics.checkNotNullExpressionValue(unitName, "substring(...)");
                                index = index5 + unitName.length();
                                unit = DurationUnitKt.durationUnitByShortName(unitName);
                                if (prevUnit3 == null && prevUnit3.compareTo(unit) <= 0) {
                                    throw new IllegalArgumentException("Unexpected order of duration components");
                                }
                                prevUnit3 = unit;
                                dotIndex = StringsKt.indexOf$default((CharSequence) component2, ClassUtils.PACKAGE_SEPARATOR_CHAR, 0, false, 6, (Object) null);
                                if (dotIndex <= 0) {
                                    Intrinsics.checkNotNull(component2, "null cannot be cast to non-null type java.lang.String");
                                    String whole2 = component2.substring(0, dotIndex);
                                    Intrinsics.checkNotNullExpressionValue(whole2, "substring(...)");
                                    long result3 = Duration.m1943plusLRDsOJo(result, toDuration(Long.parseLong(whole2), unit));
                                    Intrinsics.checkNotNull(component2, "null cannot be cast to non-null type java.lang.String");
                                    String substring2 = component2.substring(dotIndex);
                                    Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
                                    result = Duration.m1943plusLRDsOJo(result3, toDuration(Double.parseDouble(substring2), unit));
                                    if (index < length) {
                                        throw new IllegalArgumentException("Fractional component must be last");
                                    }
                                    afterFirst2 = afterFirst;
                                    allowSpaces2 = allowSpaces;
                                } else {
                                    result = Duration.m1943plusLRDsOJo(result, toDuration(Long.parseLong(component2), unit));
                                    afterFirst2 = afterFirst;
                                    allowSpaces2 = allowSpaces;
                                }
                            }
                        }
                        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type java.lang.String");
                        String unitName2 = value.substring(index5, i$iv$iv3);
                        Intrinsics.checkNotNullExpressionValue(unitName2, "substring(...)");
                        index = index5 + unitName2.length();
                        unit = DurationUnitKt.durationUnitByShortName(unitName2);
                        if (prevUnit3 == null) {
                        }
                        prevUnit3 = unit;
                        dotIndex = StringsKt.indexOf$default((CharSequence) component2, ClassUtils.PACKAGE_SEPARATOR_CHAR, 0, false, 6, (Object) null);
                        if (dotIndex <= 0) {
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException();
            }
        }
        return isNegative ? Duration.m1958unaryMinusUwyO8pc(result) : result;
    }

    private static final long parseOverLongIsoComponent(String value) {
        Iterable $this$all$iv;
        int length = value.length();
        int startIndex = 0;
        if (length > 0 && StringsKt.contains$default((CharSequence) "+-", value.charAt(0), false, 2, (Object) null)) {
            startIndex = 0 + 1;
        }
        if (length - startIndex > 16) {
            Iterable $this$all$iv2 = new IntRange(startIndex, StringsKt.getLastIndex(value));
            if (!($this$all$iv2 instanceof Collection) || !((Collection) $this$all$iv2).isEmpty()) {
                Iterator it = $this$all$iv2.iterator();
                while (true) {
                    if (it.hasNext()) {
                        int element$iv = ((IntIterator) it).nextInt();
                        char charAt = value.charAt(element$iv);
                        int it2 = ('0' > charAt || charAt >= ':') ? 0 : 1;
                        if (it2 == 0) {
                            $this$all$iv = null;
                            break;
                        }
                    } else {
                        $this$all$iv = 1;
                        break;
                    }
                }
            } else {
                $this$all$iv = 1;
            }
            if ($this$all$iv != null) {
                return value.charAt(0) == '-' ? Long.MIN_VALUE : Long.MAX_VALUE;
            }
        }
        return StringsKt.startsWith$default(value, Marker.ANY_NON_NULL_MARKER, false, 2, (Object) null) ? Long.parseLong(StringsKt.drop(value, 1)) : Long.parseLong(value);
    }

    private static final String substringWhile(String $this$substringWhile, int startIndex, Function1<? super Character, Boolean> function1) {
        int i$iv = startIndex;
        while (i$iv < $this$substringWhile.length() && function1.invoke(Character.valueOf($this$substringWhile.charAt(i$iv))).booleanValue()) {
            i$iv++;
        }
        Intrinsics.checkNotNull($this$substringWhile, "null cannot be cast to non-null type java.lang.String");
        String substring = $this$substringWhile.substring(startIndex, i$iv);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring;
    }

    private static final int skipWhile(String $this$skipWhile, int startIndex, Function1<? super Character, Boolean> function1) {
        int i = startIndex;
        while (i < $this$skipWhile.length() && function1.invoke(Character.valueOf($this$skipWhile.charAt(i))).booleanValue()) {
            i++;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long nanosToMillis(long nanos) {
        return nanos / NANOS_IN_MILLIS;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long millisToNanos(long millis) {
        return NANOS_IN_MILLIS * millis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfNanos(long normalNanos) {
        return Duration.m1914constructorimpl(normalNanos << 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfMillis(long normalMillis) {
        return Duration.m1914constructorimpl((normalMillis << 1) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOf(long normalValue, int unitDiscriminator) {
        return Duration.m1914constructorimpl((normalValue << 1) + unitDiscriminator);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfNanosNormalized(long nanos) {
        boolean z = false;
        if (-4611686018426999999L <= nanos && nanos < 4611686018427000000L) {
            z = true;
        }
        if (z) {
            return durationOfNanos(nanos);
        }
        return durationOfMillis(nanosToMillis(nanos));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long durationOfMillisNormalized(long millis) {
        boolean z = false;
        if (-4611686018426L <= millis && millis < 4611686018427L) {
            z = true;
        }
        if (z) {
            return durationOfNanos(millisToNanos(millis));
        }
        return durationOfMillis(RangesKt.coerceIn(millis, -4611686018427387903L, MAX_MILLIS));
    }
}
