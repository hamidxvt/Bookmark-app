package kotlin.collections;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequenceScope;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: SlidingWindow.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H\n"}, d2 = {"<anonymous>", "", "T", "Lkotlin/sequences/SequenceScope;", ""}, k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "kotlin.collections.SlidingWindowKt$windowedIterator$1", f = "SlidingWindow.kt", i = {0, 0, 0, 2, 2, 3, 3}, l = {34, 40, 49, ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE, 58}, m = "invokeSuspend", n = {"$this$iterator", "buffer", "gap", "$this$iterator", "buffer", "$this$iterator", "buffer"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes17.dex */
final class SlidingWindowKt$windowedIterator$1<T> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super List<? extends T>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Iterator<T> $iterator;
    final /* synthetic */ boolean $partialWindows;
    final /* synthetic */ boolean $reuseBuffer;
    final /* synthetic */ int $size;
    final /* synthetic */ int $step;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SlidingWindowKt$windowedIterator$1(int i, int i2, Iterator<? extends T> it, boolean z, boolean z2, Continuation<? super SlidingWindowKt$windowedIterator$1> continuation) {
        super(2, continuation);
        this.$size = i;
        this.$step = i2;
        this.$iterator = it;
        this.$reuseBuffer = z;
        this.$partialWindows = z2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$1 = new SlidingWindowKt$windowedIterator$1(this.$size, this.$step, this.$iterator, this.$reuseBuffer, this.$partialWindows, continuation);
        slidingWindowKt$windowedIterator$1.L$0 = obj;
        return slidingWindowKt$windowedIterator$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super List<? extends T>> sequenceScope, Continuation<? super Unit> continuation) {
        return ((SlidingWindowKt$windowedIterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00de A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00ae  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x016a -> B:10:0x016d). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0131 -> B:26:0x0134). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x00a3 -> B:46:0x00a6). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        RingBuffer buffer;
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$1;
        SequenceScope sequenceScope;
        Iterator<T> it;
        int skip;
        SlidingWindowKt$windowedIterator$1 slidingWindowKt$windowedIterator$12;
        SequenceScope $this$iterator;
        int gap;
        ArrayList buffer2;
        Iterator<T> it2;
        RingBuffer buffer3;
        SequenceScope sequenceScope2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                SequenceScope $this$iterator2 = (SequenceScope) this.L$0;
                int bufferInitialCapacity = RangesKt.coerceAtMost(this.$size, 1024);
                int gap2 = this.$step - this.$size;
                if (gap2 >= 0) {
                    ArrayList buffer4 = new ArrayList(bufferInitialCapacity);
                    skip = 0;
                    slidingWindowKt$windowedIterator$12 = this;
                    $this$iterator = $this$iterator2;
                    gap = gap2;
                    buffer2 = buffer4;
                    it2 = this.$iterator;
                    while (it2.hasNext()) {
                        T next = it2.next();
                        if (skip > 0) {
                            skip--;
                        } else {
                            buffer2.add(next);
                            if (buffer2.size() == slidingWindowKt$windowedIterator$12.$size) {
                                slidingWindowKt$windowedIterator$12.L$0 = $this$iterator;
                                slidingWindowKt$windowedIterator$12.L$1 = buffer2;
                                slidingWindowKt$windowedIterator$12.L$2 = it2;
                                slidingWindowKt$windowedIterator$12.I$0 = gap;
                                slidingWindowKt$windowedIterator$12.label = 1;
                                if ($this$iterator.yield(buffer2, slidingWindowKt$windowedIterator$12) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                if (slidingWindowKt$windowedIterator$12.$reuseBuffer) {
                                    buffer2 = new ArrayList(slidingWindowKt$windowedIterator$12.$size);
                                } else {
                                    buffer2.clear();
                                }
                                skip = gap;
                                while (it2.hasNext()) {
                                }
                            }
                        }
                    }
                    if (!buffer2.isEmpty() && (slidingWindowKt$windowedIterator$12.$partialWindows || buffer2.size() == slidingWindowKt$windowedIterator$12.$size)) {
                        slidingWindowKt$windowedIterator$12.L$0 = null;
                        slidingWindowKt$windowedIterator$12.L$1 = null;
                        slidingWindowKt$windowedIterator$12.L$2 = null;
                        slidingWindowKt$windowedIterator$12.label = 2;
                        if ($this$iterator.yield(buffer2, slidingWindowKt$windowedIterator$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
                buffer = new RingBuffer(bufferInitialCapacity);
                slidingWindowKt$windowedIterator$1 = this;
                sequenceScope = $this$iterator2;
                it = this.$iterator;
                while (it.hasNext()) {
                    buffer.add((RingBuffer) it.next());
                    if (buffer.isFull()) {
                        if (buffer.size() >= slidingWindowKt$windowedIterator$1.$size) {
                            List arrayList = slidingWindowKt$windowedIterator$1.$reuseBuffer ? buffer : new ArrayList(buffer);
                            slidingWindowKt$windowedIterator$1.L$0 = sequenceScope;
                            slidingWindowKt$windowedIterator$1.L$1 = buffer;
                            slidingWindowKt$windowedIterator$1.L$2 = it;
                            slidingWindowKt$windowedIterator$1.label = 3;
                            if (sequenceScope.yield(arrayList, slidingWindowKt$windowedIterator$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            buffer.removeFirst(slidingWindowKt$windowedIterator$1.$step);
                            while (it.hasNext()) {
                            }
                        } else {
                            buffer = buffer.expanded(slidingWindowKt$windowedIterator$1.$size);
                        }
                    }
                }
                if (slidingWindowKt$windowedIterator$1.$partialWindows) {
                    buffer3 = buffer;
                    sequenceScope2 = sequenceScope;
                    if (buffer3.size() <= slidingWindowKt$windowedIterator$1.$step) {
                        List arrayList2 = slidingWindowKt$windowedIterator$1.$reuseBuffer ? buffer3 : new ArrayList(buffer3);
                        slidingWindowKt$windowedIterator$1.L$0 = sequenceScope2;
                        slidingWindowKt$windowedIterator$1.L$1 = buffer3;
                        slidingWindowKt$windowedIterator$1.L$2 = null;
                        slidingWindowKt$windowedIterator$1.label = 4;
                        if (sequenceScope2.yield(arrayList2, slidingWindowKt$windowedIterator$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        buffer3.removeFirst(slidingWindowKt$windowedIterator$1.$step);
                        if (buffer3.size() <= slidingWindowKt$windowedIterator$1.$step) {
                            if (!buffer3.isEmpty()) {
                                slidingWindowKt$windowedIterator$1.L$0 = null;
                                slidingWindowKt$windowedIterator$1.L$1 = null;
                                slidingWindowKt$windowedIterator$1.L$2 = null;
                                slidingWindowKt$windowedIterator$1.label = 5;
                                if (sequenceScope2.yield(buffer3, slidingWindowKt$windowedIterator$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            }
                        }
                    }
                }
                return Unit.INSTANCE;
            case 1:
                gap = this.I$0;
                it2 = (Iterator) this.L$2;
                buffer2 = (ArrayList) this.L$1;
                SequenceScope $this$iterator3 = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure($result);
                slidingWindowKt$windowedIterator$12 = this;
                $this$iterator = $this$iterator3;
                if (slidingWindowKt$windowedIterator$12.$reuseBuffer) {
                }
                skip = gap;
                while (it2.hasNext()) {
                }
                if (!buffer2.isEmpty()) {
                    slidingWindowKt$windowedIterator$12.L$0 = null;
                    slidingWindowKt$windowedIterator$12.L$1 = null;
                    slidingWindowKt$windowedIterator$12.L$2 = null;
                    slidingWindowKt$windowedIterator$12.label = 2;
                    if ($this$iterator.yield(buffer2, slidingWindowKt$windowedIterator$12) == coroutine_suspended) {
                    }
                    break;
                }
                return Unit.INSTANCE;
            case 2:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            case 3:
                it = (Iterator) this.L$2;
                buffer = (RingBuffer) this.L$1;
                sequenceScope = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure($result);
                slidingWindowKt$windowedIterator$1 = this;
                buffer.removeFirst(slidingWindowKt$windowedIterator$1.$step);
                while (it.hasNext()) {
                }
                if (slidingWindowKt$windowedIterator$1.$partialWindows) {
                }
                return Unit.INSTANCE;
            case 4:
                buffer3 = (RingBuffer) this.L$1;
                sequenceScope2 = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure($result);
                slidingWindowKt$windowedIterator$1 = this;
                buffer3.removeFirst(slidingWindowKt$windowedIterator$1.$step);
                if (buffer3.size() <= slidingWindowKt$windowedIterator$1.$step) {
                }
                break;
            case 5:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
