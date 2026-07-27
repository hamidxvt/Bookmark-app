package androidx.collection;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

/* compiled from: ScatterMap.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u00020\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "K", "V", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.collection.MutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1", f = "ScatterMap.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {1511}, m = "invokeSuspend", n = {"$this$iterator", "m$iv", "lastIndex$iv", "i$iv", "slot$iv", "bitCount$iv", "j$iv"}, s = {"L$0", "L$1", "I$0", "I$1", "J$0", "I$2", "I$3"})
/* loaded from: classes.dex */
final class MutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Integer>, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MutableScatterMap<K, V> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1(MutableScatterMap<K, V> mutableScatterMap, Continuation<? super MutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1> continuation) {
        super(2, continuation);
        this.this$0 = mutableScatterMap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1 mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1 = new MutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1(this.this$0, continuation);
        mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.L$0 = obj;
        return mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super Integer> sequenceScope, Continuation<? super Unit> continuation) {
        return ((MutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x00aa, code lost:
    
        if (0 != 0) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0048, code lost:
    
        if (0 <= r9) goto L9;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0078  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x00a6 -> B:7:0x00aa). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0061 -> B:8:0x0076). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00be -> B:20:0x00c2). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        MutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1 mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1;
        Object $result;
        SequenceScope $this$iterator;
        int $i$f$forEachIndexed;
        long[] m$iv;
        int bitCount$iv;
        int i$iv;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = 8;
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1 = this;
                $result = obj;
                $this$iterator = (SequenceScope) mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.L$0;
                ScatterMap this_$iv = mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.this$0;
                $i$f$forEachIndexed = 0;
                m$iv = this_$iv.metadata;
                bitCount$iv = m$iv.length - 2;
                i$iv = 0;
                break;
            case 1:
                mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1 = this;
                $result = obj;
                int $i$f$forEachIndexed2 = 0;
                int j$iv = mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.I$3;
                int bitCount$iv2 = mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.I$2;
                long slot$iv = mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.J$0;
                int i$iv2 = mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.I$1;
                int lastIndex$iv = mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.I$0;
                long[] m$iv2 = (long[]) mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.L$1;
                SequenceScope $this$iterator2 = (SequenceScope) mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.L$0;
                ResultKt.throwOnFailure($result);
                slot$iv >>= 8;
                j$iv++;
                i = 8;
                if (j$iv < bitCount$iv2) {
                    int i2 = i;
                    if (bitCount$iv2 == i2) {
                        $i$f$forEachIndexed = $i$f$forEachIndexed2;
                        i$iv = i$iv2;
                        bitCount$iv = lastIndex$iv;
                        m$iv = m$iv2;
                        $this$iterator = $this$iterator2;
                        if (i$iv != bitCount$iv) {
                            i$iv++;
                            i = i2;
                            long slot$iv2 = m$iv[i$iv];
                            Object $result2 = $result;
                            if ((((~slot$iv2) << 7) & slot$iv2 & (-9187201950435737472L)) == -9187201950435737472L) {
                                i2 = i;
                                $result = $result2;
                                if (i$iv != bitCount$iv) {
                                }
                            } else {
                                $this$iterator2 = $this$iterator;
                                m$iv2 = m$iv;
                                $i$f$forEachIndexed2 = $i$f$forEachIndexed;
                                lastIndex$iv = bitCount$iv;
                                bitCount$iv2 = 8 - ((~(i$iv - bitCount$iv)) >>> 31);
                                j$iv = 0;
                                $result = $result2;
                                i$iv2 = i$iv;
                                slot$iv = slot$iv2;
                                if (j$iv < bitCount$iv2) {
                                    long value$iv$iv = slot$iv & 255;
                                    if (value$iv$iv >= 128) {
                                        break;
                                    } else if (1 != 0) {
                                        int index = (i$iv2 << 3) + j$iv;
                                        Integer boxInt = Boxing.boxInt(index);
                                        mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.L$0 = $this$iterator2;
                                        mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.L$1 = m$iv2;
                                        mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.I$0 = lastIndex$iv;
                                        mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.I$1 = i$iv2;
                                        mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.J$0 = slot$iv;
                                        mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.I$2 = bitCount$iv2;
                                        mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.I$3 = j$iv;
                                        mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1.label = 1;
                                        if ($this$iterator2.yield(boxInt, mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$1) == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                    }
                                    slot$iv >>= 8;
                                    j$iv++;
                                    i = 8;
                                    if (j$iv < bitCount$iv2) {
                                    }
                                }
                            }
                        }
                    }
                    return Unit.INSTANCE;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
