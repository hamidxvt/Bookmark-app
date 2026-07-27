package androidx.collection;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* compiled from: ScatterMap.kt */
@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0010)\n\u0002\u0010'\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010(\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00020\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u0096\u0002J\u0015\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR,\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0014"}, d2 = {"androidx/collection/MutableScatterMap$MutableMapWrapper$entries$1$iterator$1", "", "", "current", "", "getCurrent", "()I", "setCurrent", "(I)V", "iterator", "", "getIterator", "()Ljava/util/Iterator;", "setIterator", "(Ljava/util/Iterator;)V", "hasNext", "", "next", "remove", "", "collection"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class MutableScatterMap$MutableMapWrapper$entries$1$iterator$1<K, V> implements Iterator<Map.Entry<K, V>>, KMutableIterator {
    private int current = -1;
    private Iterator<? extends Map.Entry<K, V>> iterator;
    final /* synthetic */ MutableScatterMap<K, V> this$0;

    MutableScatterMap$MutableMapWrapper$entries$1$iterator$1(MutableScatterMap<K, V> mutableScatterMap) {
        this.this$0 = mutableScatterMap;
        this.iterator = SequencesKt.iterator(new AnonymousClass1(mutableScatterMap, this, null));
    }

    public final Iterator<Map.Entry<K, V>> getIterator() {
        return this.iterator;
    }

    public final void setIterator(Iterator<? extends Map.Entry<K, V>> it) {
        Intrinsics.checkNotNullParameter(it, "<set-?>");
        this.iterator = it;
    }

    public final int getCurrent() {
        return this.current;
    }

    public final void setCurrent(int i) {
        this.current = i;
    }

    /* compiled from: ScatterMap.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010'\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "K", "V", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "androidx.collection.MutableScatterMap$MutableMapWrapper$entries$1$iterator$1$1", f = "ScatterMap.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {1328}, m = "invokeSuspend", n = {"$this$iterator", "m$iv", "lastIndex$iv", "i$iv", "slot$iv", "bitCount$iv", "j$iv"}, s = {"L$0", "L$3", "I$0", "I$1", "J$0", "I$2", "I$3"})
    /* renamed from: androidx.collection.MutableScatterMap$MutableMapWrapper$entries$1$iterator$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Map.Entry<K, V>>, Continuation<? super Unit>, Object> {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ MutableScatterMap<K, V> this$0;
        final /* synthetic */ MutableScatterMap$MutableMapWrapper$entries$1$iterator$1<K, V> this$1;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(MutableScatterMap<K, V> mutableScatterMap, MutableScatterMap$MutableMapWrapper$entries$1$iterator$1<K, V> mutableScatterMap$MutableMapWrapper$entries$1$iterator$1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = mutableScatterMap;
            this.this$1 = mutableScatterMap$MutableMapWrapper$entries$1$iterator$1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.this$1, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(SequenceScope<? super Map.Entry<K, V>> sequenceScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0052, code lost:
        
            if (0 <= r9) goto L9;
         */
        /* JADX WARN: Removed duplicated region for block: B:19:0x00e3  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0103  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0087  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x00cf -> B:7:0x00da). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x00d6 -> B:7:0x00da). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x006b -> B:8:0x0085). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00fb -> B:22:0x0101). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            AnonymousClass1 anonymousClass1;
            Object $result;
            SequenceScope $this$iterator;
            MutableScatterMap$MutableMapWrapper$entries$1$iterator$1<K, V> mutableScatterMap$MutableMapWrapper$entries$1$iterator$1;
            MutableScatterMap<K, V> mutableScatterMap;
            int $i$f$forEachIndexed;
            long[] m$iv;
            int bitCount$iv;
            int i$iv;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    anonymousClass1 = this;
                    $result = obj;
                    $this$iterator = (SequenceScope) anonymousClass1.L$0;
                    ScatterMap this_$iv = anonymousClass1.this$0;
                    mutableScatterMap$MutableMapWrapper$entries$1$iterator$1 = anonymousClass1.this$1;
                    mutableScatterMap = anonymousClass1.this$0;
                    $i$f$forEachIndexed = 0;
                    m$iv = this_$iv.metadata;
                    bitCount$iv = m$iv.length - 2;
                    i$iv = 0;
                    break;
                case 1:
                    anonymousClass1 = this;
                    Object $result2 = obj;
                    int $i$f$forEachIndexed2 = 0;
                    int j$iv = anonymousClass1.I$3;
                    int bitCount$iv2 = anonymousClass1.I$2;
                    long slot$iv = anonymousClass1.J$0;
                    int i$iv2 = anonymousClass1.I$1;
                    int lastIndex$iv = anonymousClass1.I$0;
                    long[] m$iv2 = (long[]) anonymousClass1.L$3;
                    MutableScatterMap<K, V> mutableScatterMap2 = (MutableScatterMap) anonymousClass1.L$2;
                    MutableScatterMap$MutableMapWrapper$entries$1$iterator$1<K, V> mutableScatterMap$MutableMapWrapper$entries$1$iterator$12 = (MutableScatterMap$MutableMapWrapper$entries$1$iterator$1) anonymousClass1.L$1;
                    SequenceScope $this$iterator2 = (SequenceScope) anonymousClass1.L$0;
                    ResultKt.throwOnFailure($result2);
                    slot$iv >>= 8;
                    j$iv++;
                    if (j$iv < bitCount$iv2) {
                        long value$iv$iv = slot$iv & 255;
                        if (value$iv$iv < 128) {
                            int index = (i$iv2 << 3) + j$iv;
                            mutableScatterMap$MutableMapWrapper$entries$1$iterator$12.setCurrent(index);
                            Object $result3 = $result2;
                            int $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                            MutableMapEntry mutableMapEntry = new MutableMapEntry(mutableScatterMap2.keys, mutableScatterMap2.values, mutableScatterMap$MutableMapWrapper$entries$1$iterator$12.getCurrent());
                            anonymousClass1.L$0 = $this$iterator2;
                            anonymousClass1.L$1 = mutableScatterMap$MutableMapWrapper$entries$1$iterator$12;
                            anonymousClass1.L$2 = mutableScatterMap2;
                            anonymousClass1.L$3 = m$iv2;
                            anonymousClass1.I$0 = lastIndex$iv;
                            anonymousClass1.I$1 = i$iv2;
                            anonymousClass1.J$0 = slot$iv;
                            anonymousClass1.I$2 = bitCount$iv2;
                            anonymousClass1.I$3 = j$iv;
                            anonymousClass1.label = 1;
                            if ($this$iterator2.yield(mutableMapEntry, anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $result2 = $result3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                        }
                        slot$iv >>= 8;
                        j$iv++;
                        if (j$iv < bitCount$iv2) {
                            Object $result4 = $result2;
                            int $i$f$forEachIndexed4 = $i$f$forEachIndexed2;
                            if (bitCount$iv2 != 8) {
                                return Unit.INSTANCE;
                            }
                            mutableScatterMap$MutableMapWrapper$entries$1$iterator$1 = mutableScatterMap$MutableMapWrapper$entries$1$iterator$12;
                            i$iv = i$iv2;
                            bitCount$iv = lastIndex$iv;
                            m$iv = m$iv2;
                            mutableScatterMap = mutableScatterMap2;
                            $i$f$forEachIndexed = $i$f$forEachIndexed4;
                            $this$iterator = $this$iterator2;
                            $result = $result4;
                            if (i$iv != bitCount$iv) {
                                i$iv++;
                                long slot$iv2 = m$iv[i$iv];
                                Object obj2 = coroutine_suspended;
                                if ((((~slot$iv2) << 7) & slot$iv2 & (-9187201950435737472L)) == -9187201950435737472L) {
                                    coroutine_suspended = obj2;
                                    if (i$iv != bitCount$iv) {
                                    }
                                } else {
                                    m$iv2 = m$iv;
                                    mutableScatterMap2 = mutableScatterMap;
                                    lastIndex$iv = bitCount$iv;
                                    bitCount$iv2 = 8 - ((~(i$iv - bitCount$iv)) >>> 31);
                                    $result2 = $result;
                                    $this$iterator2 = $this$iterator;
                                    mutableScatterMap$MutableMapWrapper$entries$1$iterator$12 = mutableScatterMap$MutableMapWrapper$entries$1$iterator$1;
                                    $i$f$forEachIndexed2 = $i$f$forEachIndexed;
                                    coroutine_suspended = obj2;
                                    j$iv = 0;
                                    i$iv2 = i$iv;
                                    slot$iv = slot$iv2;
                                    if (j$iv < bitCount$iv2) {
                                    }
                                }
                            }
                            return Unit.INSTANCE;
                        }
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public Map.Entry<K, V> next() {
        return this.iterator.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        if (this.current != -1) {
            this.this$0.removeValueAt(this.current);
            this.current = -1;
        }
    }
}
