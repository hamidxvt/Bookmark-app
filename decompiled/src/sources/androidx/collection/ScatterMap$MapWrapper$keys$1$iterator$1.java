package androidx.collection;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* JADX INFO: Add missing generic type declarations: [K] */
/* compiled from: ScatterMap.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "K", "V", "Lkotlin/sequences/SequenceScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.collection.ScatterMap$MapWrapper$keys$1$iterator$1", f = "ScatterMap.kt", i = {0, 0, 0, 0, 0, 0, 0, 0}, l = {726}, m = "invokeSuspend", n = {"$this$iterator", "k$iv", "m$iv$iv", "lastIndex$iv$iv", "i$iv$iv", "slot$iv$iv", "bitCount$iv$iv", "j$iv$iv"}, s = {"L$0", "L$1", "L$2", "I$0", "I$1", "J$0", "I$2", "I$3"})
/* loaded from: classes.dex */
final class ScatterMap$MapWrapper$keys$1$iterator$1<K> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super K>, Continuation<? super Unit>, Object> {
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
    ScatterMap$MapWrapper$keys$1$iterator$1(ScatterMap<K, V> scatterMap, Continuation<? super ScatterMap$MapWrapper$keys$1$iterator$1> continuation) {
        super(2, continuation);
        this.this$0 = scatterMap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ScatterMap$MapWrapper$keys$1$iterator$1 scatterMap$MapWrapper$keys$1$iterator$1 = new ScatterMap$MapWrapper$keys$1$iterator$1(this.this$0, continuation);
        scatterMap$MapWrapper$keys$1$iterator$1.L$0 = obj;
        return scatterMap$MapWrapper$keys$1$iterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super K> sequenceScope, Continuation<? super Unit> continuation) {
        return ((ScatterMap$MapWrapper$keys$1$iterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0054, code lost:
    
        if (0 <= r8) goto L9;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0084  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x00b9 -> B:7:0x00c3). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x00c1 -> B:7:0x00c3). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x006b -> B:8:0x0082). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00dd -> B:22:0x00e6). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        ScatterMap$MapWrapper$keys$1$iterator$1 scatterMap$MapWrapper$keys$1$iterator$1;
        Object $result;
        SequenceScope $this$iterator;
        Object[] $result2;
        Object[] k$iv;
        long[] m$iv$iv;
        int lastIndex$iv$iv;
        int i$iv$iv;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                obj2 = coroutine_suspended;
                ResultKt.throwOnFailure(obj);
                scatterMap$MapWrapper$keys$1$iterator$1 = this;
                $result = obj;
                $this$iterator = (SequenceScope) scatterMap$MapWrapper$keys$1$iterator$1.L$0;
                ScatterMap this_$iv = scatterMap$MapWrapper$keys$1$iterator$1.this$0;
                $result2 = null;
                k$iv = this_$iv.keys;
                m$iv$iv = this_$iv.metadata;
                lastIndex$iv$iv = m$iv$iv.length - 2;
                i$iv$iv = 0;
                break;
            case 1:
                ScatterMap$MapWrapper$keys$1$iterator$1 scatterMap$MapWrapper$keys$1$iterator$12 = this;
                Object $result3 = obj;
                Object[] k$iv2 = null;
                int j$iv$iv = scatterMap$MapWrapper$keys$1$iterator$12.I$3;
                int bitCount$iv$iv = scatterMap$MapWrapper$keys$1$iterator$12.I$2;
                long slot$iv$iv = scatterMap$MapWrapper$keys$1$iterator$12.J$0;
                int i$iv$iv2 = scatterMap$MapWrapper$keys$1$iterator$12.I$1;
                int lastIndex$iv$iv2 = scatterMap$MapWrapper$keys$1$iterator$12.I$0;
                m$iv$iv = (long[]) scatterMap$MapWrapper$keys$1$iterator$12.L$2;
                Object[] k$iv3 = (Object[]) scatterMap$MapWrapper$keys$1$iterator$12.L$1;
                SequenceScope $this$iterator2 = (SequenceScope) scatterMap$MapWrapper$keys$1$iterator$12.L$0;
                ResultKt.throwOnFailure($result3);
                Object obj3 = coroutine_suspended;
                slot$iv$iv >>= 8;
                j$iv$iv++;
                if (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (value$iv$iv$iv < 128) {
                        int index$iv = (i$iv$iv2 << 3) + j$iv$iv;
                        Object key = k$iv3[index$iv];
                        scatterMap$MapWrapper$keys$1$iterator$12.L$0 = $this$iterator2;
                        scatterMap$MapWrapper$keys$1$iterator$12.L$1 = k$iv3;
                        scatterMap$MapWrapper$keys$1$iterator$12.L$2 = m$iv$iv;
                        scatterMap$MapWrapper$keys$1$iterator$12.I$0 = lastIndex$iv$iv2;
                        scatterMap$MapWrapper$keys$1$iterator$12.I$1 = i$iv$iv2;
                        scatterMap$MapWrapper$keys$1$iterator$12.J$0 = slot$iv$iv;
                        scatterMap$MapWrapper$keys$1$iterator$12.I$2 = bitCount$iv$iv;
                        scatterMap$MapWrapper$keys$1$iterator$12.I$3 = j$iv$iv;
                        Object[] k$iv4 = k$iv3;
                        scatterMap$MapWrapper$keys$1$iterator$12.label = 1;
                        if ($this$iterator2.yield(key, scatterMap$MapWrapper$keys$1$iterator$12) == obj3) {
                            return obj3;
                        }
                        k$iv3 = k$iv4;
                    }
                    slot$iv$iv >>= 8;
                    j$iv$iv++;
                    if (j$iv$iv < bitCount$iv$iv) {
                        Object[] k$iv5 = k$iv3;
                        if (bitCount$iv$iv == 8) {
                            $this$iterator = $this$iterator2;
                            obj2 = obj3;
                            i$iv$iv = i$iv$iv2;
                            lastIndex$iv$iv = lastIndex$iv$iv2;
                            Object[] k$iv6 = k$iv5;
                            if (i$iv$iv == lastIndex$iv$iv) {
                                i$iv$iv++;
                                scatterMap$MapWrapper$keys$1$iterator$1 = scatterMap$MapWrapper$keys$1$iterator$12;
                                $result = $result3;
                                $result2 = k$iv2;
                                k$iv = k$iv6;
                                long slot$iv$iv2 = m$iv$iv[i$iv$iv];
                                ScatterMap$MapWrapper$keys$1$iterator$1 scatterMap$MapWrapper$keys$1$iterator$13 = scatterMap$MapWrapper$keys$1$iterator$1;
                                if ((((~slot$iv$iv2) << 7) & slot$iv$iv2 & (-9187201950435737472L)) == -9187201950435737472L) {
                                    k$iv6 = k$iv;
                                    k$iv2 = $result2;
                                    $result3 = $result;
                                    scatterMap$MapWrapper$keys$1$iterator$12 = scatterMap$MapWrapper$keys$1$iterator$13;
                                    if (i$iv$iv == lastIndex$iv$iv) {
                                        scatterMap$MapWrapper$keys$1$iterator$1 = scatterMap$MapWrapper$keys$1$iterator$12;
                                        $result = $result3;
                                        $result2 = k$iv2;
                                    }
                                } else {
                                    int bitCount$iv$iv2 = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                                    lastIndex$iv$iv2 = lastIndex$iv$iv;
                                    i$iv$iv2 = i$iv$iv;
                                    slot$iv$iv = slot$iv$iv2;
                                    bitCount$iv$iv = bitCount$iv$iv2;
                                    j$iv$iv = 0;
                                    $this$iterator2 = $this$iterator;
                                    k$iv3 = k$iv;
                                    obj3 = obj2;
                                    k$iv2 = $result2;
                                    $result3 = $result;
                                    scatterMap$MapWrapper$keys$1$iterator$12 = scatterMap$MapWrapper$keys$1$iterator$13;
                                    if (j$iv$iv < bitCount$iv$iv) {
                                    }
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
