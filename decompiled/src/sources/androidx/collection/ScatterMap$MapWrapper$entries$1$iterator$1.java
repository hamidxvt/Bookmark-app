package androidx.collection;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* compiled from: ScatterMap.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010&\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "K", "V", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.collection.ScatterMap$MapWrapper$entries$1$iterator$1", f = "ScatterMap.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {TypedValues.TransitionType.TYPE_FROM}, m = "invokeSuspend", n = {"$this$iterator", "m$iv", "lastIndex$iv", "i$iv", "slot$iv", "bitCount$iv", "j$iv"}, s = {"L$0", "L$2", "I$0", "I$1", "J$0", "I$2", "I$3"})
/* loaded from: classes.dex */
final class ScatterMap$MapWrapper$entries$1$iterator$1<K, V> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Map.Entry<? extends K, ? extends V>>, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ ScatterMap<K, V> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ScatterMap$MapWrapper$entries$1$iterator$1(ScatterMap<K, V> scatterMap, Continuation<? super ScatterMap$MapWrapper$entries$1$iterator$1> continuation) {
        super(2, continuation);
        this.this$0 = scatterMap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ScatterMap$MapWrapper$entries$1$iterator$1 scatterMap$MapWrapper$entries$1$iterator$1 = new ScatterMap$MapWrapper$entries$1$iterator$1(this.this$0, continuation);
        scatterMap$MapWrapper$entries$1$iterator$1.L$0 = obj;
        return scatterMap$MapWrapper$entries$1$iterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super Map.Entry<? extends K, ? extends V>> sequenceScope, Continuation<? super Unit> continuation) {
        return ((ScatterMap$MapWrapper$entries$1$iterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x004a, code lost:
    
        if (0 <= r9) goto L9;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x007d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x00be -> B:7:0x00c7). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x00c3 -> B:7:0x00c7). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0063 -> B:8:0x007b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00e6 -> B:22:0x00eb). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ScatterMap$MapWrapper$entries$1$iterator$1 scatterMap$MapWrapper$entries$1$iterator$1;
        Object $result;
        SequenceScope $this$iterator;
        ScatterMap<K, V> scatterMap;
        int $i$f$forEachIndexed;
        long[] m$iv;
        int bitCount$iv;
        int i$iv;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                scatterMap$MapWrapper$entries$1$iterator$1 = this;
                $result = obj;
                $this$iterator = (SequenceScope) scatterMap$MapWrapper$entries$1$iterator$1.L$0;
                ScatterMap this_$iv = scatterMap$MapWrapper$entries$1$iterator$1.this$0;
                scatterMap = scatterMap$MapWrapper$entries$1$iterator$1.this$0;
                $i$f$forEachIndexed = 0;
                m$iv = this_$iv.metadata;
                bitCount$iv = m$iv.length - 2;
                i$iv = 0;
                break;
            case 1:
                scatterMap$MapWrapper$entries$1$iterator$1 = this;
                Object $result2 = obj;
                int $i$f$forEachIndexed2 = 0;
                int j$iv = scatterMap$MapWrapper$entries$1$iterator$1.I$3;
                int bitCount$iv2 = scatterMap$MapWrapper$entries$1$iterator$1.I$2;
                long slot$iv = scatterMap$MapWrapper$entries$1$iterator$1.J$0;
                int i$iv2 = scatterMap$MapWrapper$entries$1$iterator$1.I$1;
                int lastIndex$iv = scatterMap$MapWrapper$entries$1$iterator$1.I$0;
                long[] m$iv2 = (long[]) scatterMap$MapWrapper$entries$1$iterator$1.L$2;
                ScatterMap<K, V> scatterMap2 = (ScatterMap) scatterMap$MapWrapper$entries$1$iterator$1.L$1;
                SequenceScope $this$iterator2 = (SequenceScope) scatterMap$MapWrapper$entries$1$iterator$1.L$0;
                ResultKt.throwOnFailure($result2);
                slot$iv >>= 8;
                j$iv++;
                if (j$iv < bitCount$iv2) {
                    long value$iv$iv = slot$iv & 255;
                    if (value$iv$iv < 128) {
                        int index = (i$iv2 << 3) + j$iv;
                        Object $result3 = $result2;
                        int $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                        MapEntry mapEntry = new MapEntry(scatterMap2.keys[index], scatterMap2.values[index]);
                        scatterMap$MapWrapper$entries$1$iterator$1.L$0 = $this$iterator2;
                        scatterMap$MapWrapper$entries$1$iterator$1.L$1 = scatterMap2;
                        scatterMap$MapWrapper$entries$1$iterator$1.L$2 = m$iv2;
                        scatterMap$MapWrapper$entries$1$iterator$1.I$0 = lastIndex$iv;
                        scatterMap$MapWrapper$entries$1$iterator$1.I$1 = i$iv2;
                        scatterMap$MapWrapper$entries$1$iterator$1.J$0 = slot$iv;
                        scatterMap$MapWrapper$entries$1$iterator$1.I$2 = bitCount$iv2;
                        scatterMap$MapWrapper$entries$1$iterator$1.I$3 = j$iv;
                        scatterMap$MapWrapper$entries$1$iterator$1.label = 1;
                        if ($this$iterator2.yield(mapEntry, scatterMap$MapWrapper$entries$1$iterator$1) == coroutine_suspended) {
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
                        $this$iterator = $this$iterator2;
                        i$iv = i$iv2;
                        bitCount$iv = lastIndex$iv;
                        m$iv = m$iv2;
                        scatterMap = scatterMap2;
                        $i$f$forEachIndexed = $i$f$forEachIndexed4;
                        $result = $result4;
                        if (i$iv != bitCount$iv) {
                            i$iv++;
                            long slot$iv2 = m$iv[i$iv];
                            Object $result5 = $result;
                            if ((((~slot$iv2) << 7) & slot$iv2 & (-9187201950435737472L)) == -9187201950435737472L) {
                                $result = $result5;
                                if (i$iv != bitCount$iv) {
                                }
                            } else {
                                m$iv2 = m$iv;
                                scatterMap2 = scatterMap;
                                $i$f$forEachIndexed2 = $i$f$forEachIndexed;
                                lastIndex$iv = bitCount$iv;
                                bitCount$iv2 = 8 - ((~(i$iv - bitCount$iv)) >>> 31);
                                j$iv = 0;
                                $this$iterator2 = $this$iterator;
                                $result2 = $result5;
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
