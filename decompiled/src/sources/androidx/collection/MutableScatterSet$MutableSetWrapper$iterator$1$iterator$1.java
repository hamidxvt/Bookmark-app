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

/* JADX INFO: Add missing generic type declarations: [E] */
/* compiled from: ScatterSet.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "E", "Lkotlin/sequences/SequenceScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.collection.MutableScatterSet$MutableSetWrapper$iterator$1$iterator$1", f = "ScatterSet.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {1060}, m = "invokeSuspend", n = {"$this$iterator", "m$iv", "lastIndex$iv", "i$iv", "slot$iv", "bitCount$iv", "j$iv"}, s = {"L$0", "L$3", "I$0", "I$1", "J$0", "I$2", "I$3"})
/* loaded from: classes.dex */
final class MutableScatterSet$MutableSetWrapper$iterator$1$iterator$1<E> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super E>, Continuation<? super Unit>, Object> {
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
    final /* synthetic */ MutableScatterSet<E> this$0;
    final /* synthetic */ MutableScatterSet$MutableSetWrapper$iterator$1<E> this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MutableScatterSet$MutableSetWrapper$iterator$1$iterator$1(MutableScatterSet<E> mutableScatterSet, MutableScatterSet$MutableSetWrapper$iterator$1<E> mutableScatterSet$MutableSetWrapper$iterator$1, Continuation<? super MutableScatterSet$MutableSetWrapper$iterator$1$iterator$1> continuation) {
        super(2, continuation);
        this.this$0 = mutableScatterSet;
        this.this$1 = mutableScatterSet$MutableSetWrapper$iterator$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MutableScatterSet$MutableSetWrapper$iterator$1$iterator$1 mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1 = new MutableScatterSet$MutableSetWrapper$iterator$1$iterator$1(this.this$0, this.this$1, continuation);
        mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.L$0 = obj;
        return mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super E> sequenceScope, Continuation<? super Unit> continuation) {
        return ((MutableScatterSet$MutableSetWrapper$iterator$1$iterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0053, code lost:
    
        if (0 <= r9) goto L9;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0088  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x00c4 -> B:7:0x00c9). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x00c7 -> B:7:0x00c9). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x006c -> B:8:0x0086). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00e7 -> B:22:0x00ed). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        MutableScatterSet$MutableSetWrapper$iterator$1$iterator$1 mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1;
        Object $result;
        SequenceScope $this$iterator;
        MutableScatterSet$MutableSetWrapper$iterator$1<E> mutableScatterSet$MutableSetWrapper$iterator$1;
        MutableScatterSet<E> mutableScatterSet;
        int $i$f$forEachIndex;
        long[] m$iv;
        int bitCount$iv;
        int i$iv;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1 = this;
                $result = obj;
                $this$iterator = (SequenceScope) mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.L$0;
                ScatterSet this_$iv = mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.this$0;
                mutableScatterSet$MutableSetWrapper$iterator$1 = mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.this$1;
                mutableScatterSet = mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.this$0;
                $i$f$forEachIndex = 0;
                m$iv = this_$iv.metadata;
                bitCount$iv = m$iv.length - 2;
                i$iv = 0;
                break;
            case 1:
                mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1 = this;
                Object $result2 = obj;
                int $i$f$forEachIndex2 = 0;
                int j$iv = mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.I$3;
                int bitCount$iv2 = mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.I$2;
                long slot$iv = mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.J$0;
                int i$iv2 = mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.I$1;
                int lastIndex$iv = mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.I$0;
                long[] m$iv2 = (long[]) mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.L$3;
                MutableScatterSet<E> mutableScatterSet2 = (MutableScatterSet) mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.L$2;
                MutableScatterSet$MutableSetWrapper$iterator$1<E> mutableScatterSet$MutableSetWrapper$iterator$12 = (MutableScatterSet$MutableSetWrapper$iterator$1) mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.L$1;
                SequenceScope $this$iterator2 = (SequenceScope) mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.L$0;
                ResultKt.throwOnFailure($result2);
                slot$iv >>= 8;
                j$iv++;
                if (j$iv < bitCount$iv2) {
                    long value$iv$iv = slot$iv & 255;
                    if (value$iv$iv < 128) {
                        int index = (i$iv2 << 3) + j$iv;
                        mutableScatterSet$MutableSetWrapper$iterator$12.setCurrent(index);
                        Object $result3 = $result2;
                        Object obj2 = mutableScatterSet2.elements[index];
                        mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.L$0 = $this$iterator2;
                        mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.L$1 = mutableScatterSet$MutableSetWrapper$iterator$12;
                        mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.L$2 = mutableScatterSet2;
                        mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.L$3 = m$iv2;
                        mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.I$0 = lastIndex$iv;
                        mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.I$1 = i$iv2;
                        mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.J$0 = slot$iv;
                        mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.I$2 = bitCount$iv2;
                        mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.I$3 = j$iv;
                        mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1.label = 1;
                        if ($this$iterator2.yield(obj2, mutableScatterSet$MutableSetWrapper$iterator$1$iterator$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $result2 = $result3;
                    }
                    slot$iv >>= 8;
                    j$iv++;
                    if (j$iv < bitCount$iv2) {
                        Object $result4 = $result2;
                        if (bitCount$iv2 != 8) {
                            return Unit.INSTANCE;
                        }
                        $i$f$forEachIndex = $i$f$forEachIndex2;
                        i$iv = i$iv2;
                        bitCount$iv = lastIndex$iv;
                        m$iv = m$iv2;
                        mutableScatterSet = mutableScatterSet2;
                        mutableScatterSet$MutableSetWrapper$iterator$1 = mutableScatterSet$MutableSetWrapper$iterator$12;
                        $this$iterator = $this$iterator2;
                        $result = $result4;
                        if (i$iv != bitCount$iv) {
                            i$iv++;
                            long slot$iv2 = m$iv[i$iv];
                            Object obj3 = coroutine_suspended;
                            if ((((~slot$iv2) << 7) & slot$iv2 & (-9187201950435737472L)) == -9187201950435737472L) {
                                coroutine_suspended = obj3;
                                if (i$iv != bitCount$iv) {
                                }
                            } else {
                                m$iv2 = m$iv;
                                mutableScatterSet2 = mutableScatterSet;
                                lastIndex$iv = bitCount$iv;
                                bitCount$iv2 = 8 - ((~(i$iv - bitCount$iv)) >>> 31);
                                $result2 = $result;
                                $this$iterator2 = $this$iterator;
                                mutableScatterSet$MutableSetWrapper$iterator$12 = mutableScatterSet$MutableSetWrapper$iterator$1;
                                $i$f$forEachIndex2 = $i$f$forEachIndex;
                                coroutine_suspended = obj3;
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
