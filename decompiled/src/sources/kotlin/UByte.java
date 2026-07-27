package kotlin;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;

/* compiled from: UByte.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\n\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087@\u0018\u0000 s2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001sB\u0011\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0000H\u0097\n¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b\u000e\u0010\u000fJ\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\u0017\u0010\fJ\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b\u0018\u0010\u000fJ\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u0019\u0010\u0012J\u0018\u0010\u0016\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b\u001a\u0010\u001bJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\u001d\u0010\fJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b\u001e\u0010\u000fJ\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b\u001f\u0010\u0012J\u0018\u0010\u001c\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b \u0010\u001bJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b\"\u0010\fJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b#\u0010\u000fJ\u0018\u0010!\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b$\u0010\u0012J\u0018\u0010!\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b%\u0010\u001bJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b'\u0010\fJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b(\u0010\u000fJ\u0018\u0010&\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b)\u0010\u0012J\u0018\u0010&\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b*\u0010\u001bJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b,\u0010\fJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\n¢\u0006\u0004\b-\u0010\u000fJ\u0018\u0010+\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\n¢\u0006\u0004\b.\u0010\u0012J\u0018\u0010+\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\n¢\u0006\u0004\b/\u0010\u001bJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b1\u0010\fJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\rH\u0087\b¢\u0006\u0004\b2\u0010\u000fJ\u0018\u00100\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b3\u0010\u0012J\u0018\u00100\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b4\u0010\u001bJ\u0018\u00105\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b6\u00107J\u0018\u00105\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\rH\u0087\b¢\u0006\u0004\b8\u00109J\u0018\u00105\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b:\u0010\u0012J\u0018\u00105\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\b;\u0010\u001bJ\u0010\u0010<\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b=\u0010\u0005J\u0010\u0010>\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\b?\u0010\u0005J\u0018\u0010@\u001a\u00020A2\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bB\u0010CJ\u0018\u0010D\u001a\u00020A2\u0006\u0010\n\u001a\u00020\u0000H\u0087\n¢\u0006\u0004\bE\u0010CJ\u0018\u0010F\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\bG\u00107J\u0018\u0010H\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\bI\u00107J\u0018\u0010J\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0000H\u0087\f¢\u0006\u0004\bK\u00107J\u0010\u0010L\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\bM\u0010\u0005J\u0010\u0010N\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bO\u0010\u0005J\u0010\u0010P\u001a\u00020QH\u0087\b¢\u0006\u0004\bR\u0010SJ\u0010\u0010T\u001a\u00020\tH\u0087\b¢\u0006\u0004\bU\u0010VJ\u0010\u0010W\u001a\u00020XH\u0087\b¢\u0006\u0004\bY\u0010ZJ\u0010\u0010[\u001a\u00020\u0000H\u0087\b¢\u0006\u0004\b\\\u0010\u0005J\u0010\u0010]\u001a\u00020\rH\u0087\b¢\u0006\u0004\b^\u0010SJ\u0010\u0010_\u001a\u00020\u0010H\u0087\b¢\u0006\u0004\b`\u0010VJ\u0010\u0010a\u001a\u00020\u0013H\u0087\b¢\u0006\u0004\bb\u0010ZJ\u0010\u0010c\u001a\u00020dH\u0087\b¢\u0006\u0004\be\u0010fJ\u0010\u0010g\u001a\u00020hH\u0087\b¢\u0006\u0004\bi\u0010jJ\u000f\u0010k\u001a\u00020lH\u0016¢\u0006\u0004\bm\u0010nJ\u0013\u0010o\u001a\u00020p2\b\u0010\n\u001a\u0004\u0018\u00010qHÖ\u0003J\t\u0010r\u001a\u00020\tHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006t"}, d2 = {"Lkotlin/UByte;", "", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "", "constructor-impl", "(B)B", "getData$annotations", "()V", "compareTo", "", "other", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "plus", "plus-7apg3OU", "plus-xj2QHRw", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "(BJ)J", "minus", "minus-7apg3OU", "minus-xj2QHRw", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "times", "times-7apg3OU", "times-xj2QHRw", "times-WZ4Q5Ns", "times-VKZWuLQ", "div", "div-7apg3OU", "div-xj2QHRw", "div-WZ4Q5Ns", "div-VKZWuLQ", "rem", "rem-7apg3OU", "rem-xj2QHRw", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "floorDiv", "floorDiv-7apg3OU", "floorDiv-xj2QHRw", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "mod", "mod-7apg3OU", "(BB)B", "mod-xj2QHRw", "(BS)S", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "inc", "inc-w2LRezQ", "dec", "dec-w2LRezQ", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-7apg3OU", "and", "and-7apg3OU", "or", "or-7apg3OU", "xor", "xor-7apg3OU", "inv", "inv-w2LRezQ", "toByte", "toByte-impl", "toShort", "", "toShort-impl", "(B)S", "toInt", "toInt-impl", "(B)I", "toLong", "", "toLong-impl", "(B)J", "toUByte", "toUByte-w2LRezQ", "toUShort", "toUShort-Mh2AYeg", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toFloat", "", "toFloat-impl", "(B)F", "toDouble", "", "toDouble-impl", "(B)D", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "equals", "", "", "hashCode", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@JvmInline
/* loaded from: classes17.dex */
public final class UByte implements Comparable<UByte> {
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UByte m581boximpl(byte b) {
        return new UByte(b);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static byte m587constructorimpl(byte b) {
        return b;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m593equalsimpl(byte b, Object obj) {
        return (obj instanceof UByte) && b == ((UByte) obj).getData();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m594equalsimpl0(byte b, byte b2) {
        return b == b2;
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m599hashCodeimpl(byte b) {
        return Byte.hashCode(b);
    }

    public boolean equals(Object other) {
        return m593equalsimpl(this.data, other);
    }

    public int hashCode() {
        return m599hashCodeimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ byte getData() {
        return this.data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UByte uByte) {
        return Intrinsics.compare(getData() & 255, uByte.getData() & 255);
    }

    private /* synthetic */ UByte(byte data) {
        this.data = data;
    }

    /* renamed from: compareTo-7apg3OU, reason: not valid java name */
    private int m582compareTo7apg3OU(byte other) {
        return Intrinsics.compare(getData() & 255, other & 255);
    }

    /* renamed from: compareTo-7apg3OU, reason: not valid java name */
    private static int m583compareTo7apg3OU(byte arg0, byte other) {
        return Intrinsics.compare(arg0 & 255, other & 255);
    }

    /* renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private static final int m586compareToxj2QHRw(byte arg0, short other) {
        return Intrinsics.compare(arg0 & 255, 65535 & other);
    }

    /* renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private static final int m585compareToWZ4Q5Ns(byte arg0, int other) {
        return Integer.compareUnsigned(UInt.m664constructorimpl(arg0 & 255), other);
    }

    /* renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private static final int m584compareToVKZWuLQ(byte arg0, long other) {
        return Long.compareUnsigned(ULong.m743constructorimpl(arg0 & 255), other);
    }

    /* renamed from: plus-7apg3OU, reason: not valid java name */
    private static final int m611plus7apg3OU(byte arg0, byte other) {
        return UInt.m664constructorimpl(UInt.m664constructorimpl(arg0 & 255) + UInt.m664constructorimpl(other & 255));
    }

    /* renamed from: plus-xj2QHRw, reason: not valid java name */
    private static final int m614plusxj2QHRw(byte arg0, short other) {
        return UInt.m664constructorimpl(UInt.m664constructorimpl(arg0 & 255) + UInt.m664constructorimpl(65535 & other));
    }

    /* renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    private static final int m613plusWZ4Q5Ns(byte arg0, int other) {
        return UInt.m664constructorimpl(UInt.m664constructorimpl(arg0 & 255) + other);
    }

    /* renamed from: plus-VKZWuLQ, reason: not valid java name */
    private static final long m612plusVKZWuLQ(byte arg0, long other) {
        return ULong.m743constructorimpl(ULong.m743constructorimpl(arg0 & 255) + other);
    }

    /* renamed from: minus-7apg3OU, reason: not valid java name */
    private static final int m602minus7apg3OU(byte arg0, byte other) {
        return UInt.m664constructorimpl(UInt.m664constructorimpl(arg0 & 255) - UInt.m664constructorimpl(other & 255));
    }

    /* renamed from: minus-xj2QHRw, reason: not valid java name */
    private static final int m605minusxj2QHRw(byte arg0, short other) {
        return UInt.m664constructorimpl(UInt.m664constructorimpl(arg0 & 255) - UInt.m664constructorimpl(65535 & other));
    }

    /* renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    private static final int m604minusWZ4Q5Ns(byte arg0, int other) {
        return UInt.m664constructorimpl(UInt.m664constructorimpl(arg0 & 255) - other);
    }

    /* renamed from: minus-VKZWuLQ, reason: not valid java name */
    private static final long m603minusVKZWuLQ(byte arg0, long other) {
        return ULong.m743constructorimpl(ULong.m743constructorimpl(arg0 & 255) - other);
    }

    /* renamed from: times-7apg3OU, reason: not valid java name */
    private static final int m621times7apg3OU(byte arg0, byte other) {
        return UInt.m664constructorimpl(UInt.m664constructorimpl(arg0 & 255) * UInt.m664constructorimpl(other & 255));
    }

    /* renamed from: times-xj2QHRw, reason: not valid java name */
    private static final int m624timesxj2QHRw(byte arg0, short other) {
        return UInt.m664constructorimpl(UInt.m664constructorimpl(arg0 & 255) * UInt.m664constructorimpl(65535 & other));
    }

    /* renamed from: times-WZ4Q5Ns, reason: not valid java name */
    private static final int m623timesWZ4Q5Ns(byte arg0, int other) {
        return UInt.m664constructorimpl(UInt.m664constructorimpl(arg0 & 255) * other);
    }

    /* renamed from: times-VKZWuLQ, reason: not valid java name */
    private static final long m622timesVKZWuLQ(byte arg0, long other) {
        return ULong.m743constructorimpl(ULong.m743constructorimpl(arg0 & 255) * other);
    }

    /* renamed from: div-7apg3OU, reason: not valid java name */
    private static final int m589div7apg3OU(byte arg0, byte other) {
        return Integer.divideUnsigned(UInt.m664constructorimpl(arg0 & 255), UInt.m664constructorimpl(other & 255));
    }

    /* renamed from: div-xj2QHRw, reason: not valid java name */
    private static final int m592divxj2QHRw(byte arg0, short other) {
        return Integer.divideUnsigned(UInt.m664constructorimpl(arg0 & 255), UInt.m664constructorimpl(65535 & other));
    }

    /* renamed from: div-WZ4Q5Ns, reason: not valid java name */
    private static final int m591divWZ4Q5Ns(byte arg0, int other) {
        return Integer.divideUnsigned(UInt.m664constructorimpl(arg0 & 255), other);
    }

    /* renamed from: div-VKZWuLQ, reason: not valid java name */
    private static final long m590divVKZWuLQ(byte arg0, long other) {
        return Long.divideUnsigned(ULong.m743constructorimpl(arg0 & 255), other);
    }

    /* renamed from: rem-7apg3OU, reason: not valid java name */
    private static final int m617rem7apg3OU(byte arg0, byte other) {
        return Integer.remainderUnsigned(UInt.m664constructorimpl(arg0 & 255), UInt.m664constructorimpl(other & 255));
    }

    /* renamed from: rem-xj2QHRw, reason: not valid java name */
    private static final int m620remxj2QHRw(byte arg0, short other) {
        return Integer.remainderUnsigned(UInt.m664constructorimpl(arg0 & 255), UInt.m664constructorimpl(65535 & other));
    }

    /* renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    private static final int m619remWZ4Q5Ns(byte arg0, int other) {
        return Integer.remainderUnsigned(UInt.m664constructorimpl(arg0 & 255), other);
    }

    /* renamed from: rem-VKZWuLQ, reason: not valid java name */
    private static final long m618remVKZWuLQ(byte arg0, long other) {
        return Long.remainderUnsigned(ULong.m743constructorimpl(arg0 & 255), other);
    }

    /* renamed from: floorDiv-7apg3OU, reason: not valid java name */
    private static final int m595floorDiv7apg3OU(byte arg0, byte other) {
        return Integer.divideUnsigned(UInt.m664constructorimpl(arg0 & 255), UInt.m664constructorimpl(other & 255));
    }

    /* renamed from: floorDiv-xj2QHRw, reason: not valid java name */
    private static final int m598floorDivxj2QHRw(byte arg0, short other) {
        return Integer.divideUnsigned(UInt.m664constructorimpl(arg0 & 255), UInt.m664constructorimpl(65535 & other));
    }

    /* renamed from: floorDiv-WZ4Q5Ns, reason: not valid java name */
    private static final int m597floorDivWZ4Q5Ns(byte arg0, int other) {
        return Integer.divideUnsigned(UInt.m664constructorimpl(arg0 & 255), other);
    }

    /* renamed from: floorDiv-VKZWuLQ, reason: not valid java name */
    private static final long m596floorDivVKZWuLQ(byte arg0, long other) {
        return Long.divideUnsigned(ULong.m743constructorimpl(arg0 & 255), other);
    }

    /* renamed from: mod-7apg3OU, reason: not valid java name */
    private static final byte m606mod7apg3OU(byte arg0, byte other) {
        return m587constructorimpl((byte) Integer.remainderUnsigned(UInt.m664constructorimpl(arg0 & 255), UInt.m664constructorimpl(other & 255)));
    }

    /* renamed from: mod-xj2QHRw, reason: not valid java name */
    private static final short m609modxj2QHRw(byte arg0, short other) {
        return UShort.m850constructorimpl((short) Integer.remainderUnsigned(UInt.m664constructorimpl(arg0 & 255), UInt.m664constructorimpl(65535 & other)));
    }

    /* renamed from: mod-WZ4Q5Ns, reason: not valid java name */
    private static final int m608modWZ4Q5Ns(byte arg0, int other) {
        return Integer.remainderUnsigned(UInt.m664constructorimpl(arg0 & 255), other);
    }

    /* renamed from: mod-VKZWuLQ, reason: not valid java name */
    private static final long m607modVKZWuLQ(byte arg0, long other) {
        return Long.remainderUnsigned(ULong.m743constructorimpl(arg0 & 255), other);
    }

    /* renamed from: inc-w2LRezQ, reason: not valid java name */
    private static final byte m600incw2LRezQ(byte arg0) {
        return m587constructorimpl((byte) (arg0 + 1));
    }

    /* renamed from: dec-w2LRezQ, reason: not valid java name */
    private static final byte m588decw2LRezQ(byte arg0) {
        return m587constructorimpl((byte) (arg0 - 1));
    }

    /* renamed from: rangeTo-7apg3OU, reason: not valid java name */
    private static final UIntRange m615rangeTo7apg3OU(byte arg0, byte other) {
        return new UIntRange(UInt.m664constructorimpl(arg0 & 255), UInt.m664constructorimpl(other & 255), null);
    }

    /* renamed from: rangeUntil-7apg3OU, reason: not valid java name */
    private static final UIntRange m616rangeUntil7apg3OU(byte arg0, byte other) {
        return URangesKt.m1845untilJ1ME1BU(UInt.m664constructorimpl(arg0 & 255), UInt.m664constructorimpl(other & 255));
    }

    /* renamed from: and-7apg3OU, reason: not valid java name */
    private static final byte m580and7apg3OU(byte arg0, byte other) {
        return m587constructorimpl((byte) (arg0 & other));
    }

    /* renamed from: or-7apg3OU, reason: not valid java name */
    private static final byte m610or7apg3OU(byte arg0, byte other) {
        return m587constructorimpl((byte) (arg0 | other));
    }

    /* renamed from: xor-7apg3OU, reason: not valid java name */
    private static final byte m636xor7apg3OU(byte arg0, byte other) {
        return m587constructorimpl((byte) (arg0 ^ other));
    }

    /* renamed from: inv-w2LRezQ, reason: not valid java name */
    private static final byte m601invw2LRezQ(byte arg0) {
        return m587constructorimpl((byte) (~arg0));
    }

    /* renamed from: toByte-impl, reason: not valid java name */
    private static final byte m625toByteimpl(byte arg0) {
        return arg0;
    }

    /* renamed from: toShort-impl, reason: not valid java name */
    private static final short m630toShortimpl(byte arg0) {
        return (short) (arg0 & 255);
    }

    /* renamed from: toInt-impl, reason: not valid java name */
    private static final int m628toIntimpl(byte arg0) {
        return arg0 & 255;
    }

    /* renamed from: toLong-impl, reason: not valid java name */
    private static final long m629toLongimpl(byte arg0) {
        return arg0 & 255;
    }

    /* renamed from: toUByte-w2LRezQ, reason: not valid java name */
    private static final byte m632toUBytew2LRezQ(byte arg0) {
        return arg0;
    }

    /* renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    private static final short m635toUShortMh2AYeg(byte arg0) {
        return UShort.m850constructorimpl((short) (arg0 & 255));
    }

    /* renamed from: toUInt-pVg5ArA, reason: not valid java name */
    private static final int m633toUIntpVg5ArA(byte arg0) {
        return UInt.m664constructorimpl(arg0 & 255);
    }

    /* renamed from: toULong-s-VKNKU, reason: not valid java name */
    private static final long m634toULongsVKNKU(byte arg0) {
        return ULong.m743constructorimpl(arg0 & 255);
    }

    /* renamed from: toFloat-impl, reason: not valid java name */
    private static final float m627toFloatimpl(byte arg0) {
        return (float) UnsignedKt.uintToDouble(arg0 & 255);
    }

    /* renamed from: toDouble-impl, reason: not valid java name */
    private static final double m626toDoubleimpl(byte arg0) {
        return UnsignedKt.uintToDouble(arg0 & 255);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m631toStringimpl(byte arg0) {
        return String.valueOf(arg0 & 255);
    }

    public String toString() {
        return m631toStringimpl(this.data);
    }
}
