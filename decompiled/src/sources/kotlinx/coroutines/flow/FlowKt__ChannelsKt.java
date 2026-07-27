package kotlinx.coroutines.flow;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.ChannelFlowKt;

/* compiled from: Channels.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a,\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u0006\u001a6\u0010\u0007\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0082@¢\u0006\u0004\b\n\u0010\u000b\u001a\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\r\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0005\u001a\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\r\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0005\u001a$\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011¨\u0006\u0012"}, d2 = {"emitAll", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "channel", "Lkotlinx/coroutines/channels/ReceiveChannel;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitAllImpl", "consume", "", "emitAllImpl$FlowKt__ChannelsKt", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlinx/coroutines/channels/ReceiveChannel;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveAsFlow", "Lkotlinx/coroutines/flow/Flow;", "consumeAsFlow", "produceIn", "scope", "Lkotlinx/coroutines/CoroutineScope;", "kotlinx-coroutines-core"}, k = 5, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE, xs = "kotlinx/coroutines/flow/FlowKt")
/* loaded from: classes17.dex */
final /* synthetic */ class FlowKt__ChannelsKt {
    public static final <T> Object emitAll(FlowCollector<? super T> flowCollector, ReceiveChannel<? extends T> receiveChannel, Continuation<? super Unit> continuation) {
        Object emitAllImpl$FlowKt__ChannelsKt = emitAllImpl$FlowKt__ChannelsKt(flowCollector, receiveChannel, true, continuation);
        return emitAllImpl$FlowKt__ChannelsKt == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? emitAllImpl$FlowKt__ChannelsKt : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x008f A[Catch: all -> 0x00b6, TRY_LEAVE, TryCatch #1 {all -> 0x00b6, blocks: (B:20:0x0087, B:22:0x008f), top: B:19:0x0087 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00ad A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /* JADX WARN: Type inference failed for: r3v0, types: [int] */
    /* JADX WARN: Type inference failed for: r3v1, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v2, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Object, kotlinx.coroutines.flow.FlowCollector] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r8v0, types: [kotlinx.coroutines.flow.FlowCollector, kotlinx.coroutines.flow.FlowCollector<? super T>] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [boolean] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00a5 -> B:15:0x006c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object emitAllImpl$FlowKt__ChannelsKt(FlowCollector<? super T> flowCollector, ReceiveChannel<? extends T> receiveChannel, boolean z, Continuation<? super Unit> continuation) {
        FlowKt__ChannelsKt$emitAllImpl$1 flowKt__ChannelsKt$emitAllImpl$1;
        ?? r3;
        Throwable th;
        ChannelIterator it;
        ChannelIterator channelIterator;
        Throwable th2;
        Object obj;
        ReceiveChannel receiveChannel2;
        Object obj2;
        Object obj3;
        Object obj4;
        Object hasNext;
        try {
            if (continuation instanceof FlowKt__ChannelsKt$emitAllImpl$1) {
                flowKt__ChannelsKt$emitAllImpl$1 = (FlowKt__ChannelsKt$emitAllImpl$1) continuation;
                if ((flowKt__ChannelsKt$emitAllImpl$1.label & Integer.MIN_VALUE) != 0) {
                    flowKt__ChannelsKt$emitAllImpl$1.label -= Integer.MIN_VALUE;
                    Object obj5 = flowKt__ChannelsKt$emitAllImpl$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    r3 = flowKt__ChannelsKt$emitAllImpl$1.label;
                    switch (r3) {
                        case 0:
                            ResultKt.throwOnFailure(obj5);
                            r3 = receiveChannel;
                            FlowKt.ensureActive(flowCollector);
                            th = null;
                            try {
                                it = r3.iterator();
                                r3 = r3;
                                obj4 = flowCollector;
                                flowKt__ChannelsKt$emitAllImpl$1.L$0 = obj4;
                                flowKt__ChannelsKt$emitAllImpl$1.L$1 = r3;
                                flowKt__ChannelsKt$emitAllImpl$1.L$2 = it;
                                flowKt__ChannelsKt$emitAllImpl$1.Z$0 = z;
                                flowKt__ChannelsKt$emitAllImpl$1.label = 1;
                                hasNext = it.hasNext(flowKt__ChannelsKt$emitAllImpl$1);
                                if (hasNext == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                obj = obj4;
                                boolean z2 = (FlowCollector<? super T>) (z ? 1 : 0);
                                th2 = th;
                                channelIterator = it;
                                receiveChannel2 = r3;
                                obj2 = coroutine_suspended;
                                obj3 = obj5;
                                obj5 = hasNext;
                                flowCollector = z2;
                                try {
                                    if (((Boolean) obj5).booleanValue()) {
                                        return Unit.INSTANCE;
                                    }
                                    Object next = channelIterator.next();
                                    flowKt__ChannelsKt$emitAllImpl$1.L$0 = obj;
                                    flowKt__ChannelsKt$emitAllImpl$1.L$1 = receiveChannel2;
                                    flowKt__ChannelsKt$emitAllImpl$1.L$2 = channelIterator;
                                    flowKt__ChannelsKt$emitAllImpl$1.Z$0 = (boolean) flowCollector;
                                    flowKt__ChannelsKt$emitAllImpl$1.label = 2;
                                    if (obj.emit(next, flowKt__ChannelsKt$emitAllImpl$1) == obj2) {
                                        return obj2;
                                    }
                                    obj5 = obj3;
                                    coroutine_suspended = obj2;
                                    r3 = receiveChannel2;
                                    it = channelIterator;
                                    th = th2;
                                    z = (??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) flowCollector;
                                    obj4 = (FlowCollector<? super T>) obj;
                                    flowKt__ChannelsKt$emitAllImpl$1.L$0 = obj4;
                                    flowKt__ChannelsKt$emitAllImpl$1.L$1 = r3;
                                    flowKt__ChannelsKt$emitAllImpl$1.L$2 = it;
                                    flowKt__ChannelsKt$emitAllImpl$1.Z$0 = z;
                                    flowKt__ChannelsKt$emitAllImpl$1.label = 1;
                                    hasNext = it.hasNext(flowKt__ChannelsKt$emitAllImpl$1);
                                    if (hasNext == coroutine_suspended) {
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    r3 = receiveChannel2;
                                    Throwable th4 = th;
                                    try {
                                        throw th;
                                    } finally {
                                        if (flowCollector != 0) {
                                            ChannelsKt.cancelConsumed(r3, th4);
                                        }
                                    }
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                flowCollector = (FlowCollector<? super T>) (z ? 1 : 0);
                                Throwable th42 = th;
                                throw th;
                            }
                        case 1:
                            boolean z3 = flowKt__ChannelsKt$emitAllImpl$1.Z$0;
                            channelIterator = (ChannelIterator) flowKt__ChannelsKt$emitAllImpl$1.L$2;
                            th2 = null;
                            ReceiveChannel receiveChannel3 = (ReceiveChannel) flowKt__ChannelsKt$emitAllImpl$1.L$1;
                            FlowCollector flowCollector2 = (FlowCollector) flowKt__ChannelsKt$emitAllImpl$1.L$0;
                            ResultKt.throwOnFailure(obj5);
                            obj = flowCollector2;
                            receiveChannel2 = receiveChannel3;
                            obj2 = coroutine_suspended;
                            obj3 = obj5;
                            flowCollector = z3;
                            if (((Boolean) obj5).booleanValue()) {
                            }
                            break;
                        case 2:
                            boolean z4 = flowKt__ChannelsKt$emitAllImpl$1.Z$0;
                            ChannelIterator channelIterator2 = (ChannelIterator) flowKt__ChannelsKt$emitAllImpl$1.L$2;
                            ReceiveChannel receiveChannel4 = (ReceiveChannel) flowKt__ChannelsKt$emitAllImpl$1.L$1;
                            FlowCollector flowCollector3 = (FlowCollector) flowKt__ChannelsKt$emitAllImpl$1.L$0;
                            ResultKt.throwOnFailure(obj5);
                            z = z4;
                            Object obj6 = (FlowCollector<? super T>) flowCollector3;
                            it = channelIterator2;
                            th = null;
                            r3 = receiveChannel4;
                            obj4 = obj6;
                            flowKt__ChannelsKt$emitAllImpl$1.L$0 = obj4;
                            flowKt__ChannelsKt$emitAllImpl$1.L$1 = r3;
                            flowKt__ChannelsKt$emitAllImpl$1.L$2 = it;
                            flowKt__ChannelsKt$emitAllImpl$1.Z$0 = z;
                            flowKt__ChannelsKt$emitAllImpl$1.label = 1;
                            hasNext = it.hasNext(flowKt__ChannelsKt$emitAllImpl$1);
                            if (hasNext == coroutine_suspended) {
                            }
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }
            switch (r3) {
            }
        } catch (Throwable th6) {
            th = th6;
        }
        flowKt__ChannelsKt$emitAllImpl$1 = new FlowKt__ChannelsKt$emitAllImpl$1(continuation);
        Object obj52 = flowKt__ChannelsKt$emitAllImpl$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r3 = flowKt__ChannelsKt$emitAllImpl$1.label;
    }

    public static final <T> Flow<T> receiveAsFlow(ReceiveChannel<? extends T> receiveChannel) {
        return new ChannelAsFlow(receiveChannel, false, null, 0, null, 28, null);
    }

    public static final <T> Flow<T> consumeAsFlow(ReceiveChannel<? extends T> receiveChannel) {
        return new ChannelAsFlow(receiveChannel, true, null, 0, null, 28, null);
    }

    public static final <T> ReceiveChannel<T> produceIn(Flow<? extends T> flow, CoroutineScope scope) {
        return ChannelFlowKt.asChannelFlow(flow).produceImpl(scope);
    }
}
