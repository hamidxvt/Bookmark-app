package kotlinx.coroutines.stream;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: Stream.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u001c\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0096@¢\u0006\u0002\u0010\rR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\t\u0010\u0007\u001a\u00020\bX\u0082\u0004¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/stream/StreamFlow;", "T", "Lkotlinx/coroutines/flow/Flow;", "stream", "Ljava/util/stream/Stream;", "<init>", "(Ljava/util/stream/Stream;)V", "consumed", "Lkotlinx/atomicfu/AtomicBoolean;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
final class StreamFlow<T> implements Flow<T> {
    private static final /* synthetic */ AtomicIntegerFieldUpdater consumed$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(StreamFlow.class, "consumed$volatile");
    private volatile /* synthetic */ int consumed$volatile = 0;
    private final Stream<T> stream;

    private final /* synthetic */ int getConsumed$volatile() {
        return this.consumed$volatile;
    }

    private final /* synthetic */ void setConsumed$volatile(int i) {
        this.consumed$volatile = i;
    }

    public StreamFlow(Stream<T> stream) {
        this.stream = stream;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005c A[Catch: all -> 0x0079, TRY_LEAVE, TryCatch #0 {all -> 0x0079, blocks: (B:13:0x0039, B:15:0x0056, B:17:0x005c, B:28:0x004d), top: B:7:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        StreamFlow$collect$1 streamFlow$collect$1;
        StreamFlow streamFlow;
        FlowCollector collector;
        Iterator<T> it;
        try {
            if (continuation instanceof StreamFlow$collect$1) {
                streamFlow$collect$1 = (StreamFlow$collect$1) continuation;
                if ((streamFlow$collect$1.label & Integer.MIN_VALUE) != 0) {
                    streamFlow$collect$1.label -= Integer.MIN_VALUE;
                    Object $result = streamFlow$collect$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (streamFlow$collect$1.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            streamFlow = this;
                            if (!consumed$volatile$FU.compareAndSet(streamFlow, 0, 1)) {
                                throw new IllegalStateException("Stream.consumeAsFlow can be collected only once".toString());
                            }
                            collector = flowCollector;
                            it = streamFlow.stream.iterator();
                            break;
                        case 1:
                            it = (Iterator) streamFlow$collect$1.L$2;
                            collector = (FlowCollector) streamFlow$collect$1.L$1;
                            streamFlow = (StreamFlow) streamFlow$collect$1.L$0;
                            ResultKt.throwOnFailure($result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    while (it.hasNext()) {
                        T next = it.next();
                        streamFlow$collect$1.L$0 = streamFlow;
                        streamFlow$collect$1.L$1 = collector;
                        streamFlow$collect$1.L$2 = it;
                        streamFlow$collect$1.label = 1;
                        if (collector.emit(next, streamFlow$collect$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    streamFlow.stream.close();
                    return Unit.INSTANCE;
                }
            }
            switch (streamFlow$collect$1.label) {
            }
            while (it.hasNext()) {
            }
            streamFlow.stream.close();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            streamFlow.stream.close();
            throw th;
        }
        streamFlow$collect$1 = new StreamFlow$collect$1(this, continuation);
        Object $result2 = streamFlow$collect$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }
}
