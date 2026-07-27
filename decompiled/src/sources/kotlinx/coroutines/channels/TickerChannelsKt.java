package kotlinx.coroutines.channels;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.AbstractTimeSource;
import kotlinx.coroutines.AbstractTimeSourceKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.EventLoop_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: TickerChannels.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007\u001a,\u0010\n\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\fH\u0082@¢\u0006\u0002\u0010\r\u001a,\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\fH\u0082@¢\u0006\u0002\u0010\r¨\u0006\u000f"}, d2 = {"ticker", "Lkotlinx/coroutines/channels/ReceiveChannel;", "", "delayMillis", "", "initialDelayMillis", "context", "Lkotlin/coroutines/CoroutineContext;", "mode", "Lkotlinx/coroutines/channels/TickerMode;", "fixedPeriodTicker", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "(JJLkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fixedDelayTicker", "kotlinx-coroutines-core"}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class TickerChannelsKt {
    public static /* synthetic */ ReceiveChannel ticker$default(long j, long j2, CoroutineContext coroutineContext, TickerMode tickerMode, int i, Object obj) {
        if ((i & 2) != 0) {
            j2 = j;
        }
        if ((i & 4) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 8) != 0) {
            tickerMode = TickerMode.FIXED_PERIOD;
        }
        return ticker(j, j2, coroutineContext, tickerMode);
    }

    public static final ReceiveChannel<Unit> ticker(long delayMillis, long initialDelayMillis, CoroutineContext context, TickerMode mode) {
        if (!(delayMillis >= 0)) {
            throw new IllegalArgumentException(("Expected non-negative delay, but has " + delayMillis + " ms").toString());
        }
        if (!(initialDelayMillis >= 0)) {
            throw new IllegalArgumentException(("Expected non-negative initial delay, but has " + initialDelayMillis + " ms").toString());
        }
        return ProduceKt.produce(GlobalScope.INSTANCE, Dispatchers.getUnconfined().plus(context), 0, new TickerChannelsKt$ticker$3(mode, delayMillis, initialDelayMillis, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00a2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00f5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00df -> B:12:0x008f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00f6 -> B:12:0x008f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object fixedPeriodTicker(long delayMillis, long initialDelayMillis, SendChannel<? super Unit> sendChannel, Continuation<? super Unit> continuation) {
        TickerChannelsKt$fixedPeriodTicker$1 tickerChannelsKt$fixedPeriodTicker$1;
        long delayMillis2;
        long delayNs;
        long delayNs2;
        long deadline;
        long deadline2;
        long nextDelay;
        long delayNs3;
        Unit unit;
        if (continuation instanceof TickerChannelsKt$fixedPeriodTicker$1) {
            tickerChannelsKt$fixedPeriodTicker$1 = (TickerChannelsKt$fixedPeriodTicker$1) continuation;
            if ((tickerChannelsKt$fixedPeriodTicker$1.label & Integer.MIN_VALUE) != 0) {
                tickerChannelsKt$fixedPeriodTicker$1.label -= Integer.MIN_VALUE;
                Object $result = tickerChannelsKt$fixedPeriodTicker$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (tickerChannelsKt$fixedPeriodTicker$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        AbstractTimeSource abstractTimeSource = AbstractTimeSourceKt.timeSource;
                        long deadline3 = (abstractTimeSource != null ? abstractTimeSource.nanoTime() : System.nanoTime()) + EventLoop_commonKt.delayToNanos(initialDelayMillis);
                        tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel;
                        tickerChannelsKt$fixedPeriodTicker$1.J$0 = delayMillis;
                        tickerChannelsKt$fixedPeriodTicker$1.J$1 = deadline3;
                        tickerChannelsKt$fixedPeriodTicker$1.label = 1;
                        if (DelayKt.delay(initialDelayMillis, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        delayMillis2 = delayMillis;
                        delayNs = deadline3;
                        delayNs2 = EventLoop_commonKt.delayToNanos(delayMillis2);
                        long deadline4 = delayNs + delayNs2;
                        unit = Unit.INSTANCE;
                        tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel;
                        tickerChannelsKt$fixedPeriodTicker$1.J$0 = deadline4;
                        tickerChannelsKt$fixedPeriodTicker$1.J$1 = delayNs2;
                        tickerChannelsKt$fixedPeriodTicker$1.label = 2;
                        if (sendChannel.send(unit, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        deadline2 = deadline4;
                        deadline = delayNs2;
                        AbstractTimeSource abstractTimeSource2 = AbstractTimeSourceKt.timeSource;
                        long now = abstractTimeSource2 == null ? abstractTimeSource2.nanoTime() : System.nanoTime();
                        nextDelay = RangesKt.coerceAtLeast(deadline2 - now, 0L);
                        if (nextDelay == 0 || deadline == 0) {
                            delayNs3 = EventLoop_commonKt.delayNanosToMillis(nextDelay);
                            tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel;
                            tickerChannelsKt$fixedPeriodTicker$1.J$0 = deadline2;
                            tickerChannelsKt$fixedPeriodTicker$1.J$1 = deadline;
                            tickerChannelsKt$fixedPeriodTicker$1.label = 4;
                            if (DelayKt.delay(delayNs3, tickerChannelsKt$fixedPeriodTicker$1) != coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            delayNs2 = deadline;
                            delayNs = deadline2;
                        } else {
                            long adjustedDelay = deadline - ((now - deadline2) % deadline);
                            long deadline5 = now + adjustedDelay;
                            long now2 = EventLoop_commonKt.delayNanosToMillis(adjustedDelay);
                            tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel;
                            tickerChannelsKt$fixedPeriodTicker$1.J$0 = deadline5;
                            tickerChannelsKt$fixedPeriodTicker$1.J$1 = deadline;
                            tickerChannelsKt$fixedPeriodTicker$1.label = 3;
                            if (DelayKt.delay(now2, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            delayNs2 = deadline;
                            delayNs = deadline5;
                        }
                        long deadline42 = delayNs + delayNs2;
                        unit = Unit.INSTANCE;
                        tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel;
                        tickerChannelsKt$fixedPeriodTicker$1.J$0 = deadline42;
                        tickerChannelsKt$fixedPeriodTicker$1.J$1 = delayNs2;
                        tickerChannelsKt$fixedPeriodTicker$1.label = 2;
                        if (sendChannel.send(unit, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                        }
                        break;
                    case 1:
                        delayNs = tickerChannelsKt$fixedPeriodTicker$1.J$1;
                        delayMillis2 = tickerChannelsKt$fixedPeriodTicker$1.J$0;
                        sendChannel = (SendChannel) tickerChannelsKt$fixedPeriodTicker$1.L$0;
                        ResultKt.throwOnFailure($result);
                        delayNs2 = EventLoop_commonKt.delayToNanos(delayMillis2);
                        long deadline422 = delayNs + delayNs2;
                        unit = Unit.INSTANCE;
                        tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel;
                        tickerChannelsKt$fixedPeriodTicker$1.J$0 = deadline422;
                        tickerChannelsKt$fixedPeriodTicker$1.J$1 = delayNs2;
                        tickerChannelsKt$fixedPeriodTicker$1.label = 2;
                        if (sendChannel.send(unit, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                        }
                        break;
                    case 2:
                        deadline = tickerChannelsKt$fixedPeriodTicker$1.J$1;
                        deadline2 = tickerChannelsKt$fixedPeriodTicker$1.J$0;
                        sendChannel = (SendChannel) tickerChannelsKt$fixedPeriodTicker$1.L$0;
                        ResultKt.throwOnFailure($result);
                        AbstractTimeSource abstractTimeSource22 = AbstractTimeSourceKt.timeSource;
                        if (abstractTimeSource22 == null) {
                        }
                        nextDelay = RangesKt.coerceAtLeast(deadline2 - now, 0L);
                        if (nextDelay == 0) {
                            break;
                        }
                        delayNs3 = EventLoop_commonKt.delayNanosToMillis(nextDelay);
                        tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel;
                        tickerChannelsKt$fixedPeriodTicker$1.J$0 = deadline2;
                        tickerChannelsKt$fixedPeriodTicker$1.J$1 = deadline;
                        tickerChannelsKt$fixedPeriodTicker$1.label = 4;
                        if (DelayKt.delay(delayNs3, tickerChannelsKt$fixedPeriodTicker$1) != coroutine_suspended) {
                        }
                        break;
                    case 3:
                        long delayNs4 = tickerChannelsKt$fixedPeriodTicker$1.J$1;
                        long deadline6 = tickerChannelsKt$fixedPeriodTicker$1.J$0;
                        sendChannel = (SendChannel) tickerChannelsKt$fixedPeriodTicker$1.L$0;
                        ResultKt.throwOnFailure($result);
                        delayNs2 = delayNs4;
                        delayNs = deadline6;
                        long deadline4222 = delayNs + delayNs2;
                        unit = Unit.INSTANCE;
                        tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel;
                        tickerChannelsKt$fixedPeriodTicker$1.J$0 = deadline4222;
                        tickerChannelsKt$fixedPeriodTicker$1.J$1 = delayNs2;
                        tickerChannelsKt$fixedPeriodTicker$1.label = 2;
                        if (sendChannel.send(unit, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                        }
                        break;
                    case 4:
                        long delayNs5 = tickerChannelsKt$fixedPeriodTicker$1.J$1;
                        long deadline7 = tickerChannelsKt$fixedPeriodTicker$1.J$0;
                        sendChannel = (SendChannel) tickerChannelsKt$fixedPeriodTicker$1.L$0;
                        ResultKt.throwOnFailure($result);
                        delayNs2 = delayNs5;
                        delayNs = deadline7;
                        long deadline42222 = delayNs + delayNs2;
                        unit = Unit.INSTANCE;
                        tickerChannelsKt$fixedPeriodTicker$1.L$0 = sendChannel;
                        tickerChannelsKt$fixedPeriodTicker$1.J$0 = deadline42222;
                        tickerChannelsKt$fixedPeriodTicker$1.J$1 = delayNs2;
                        tickerChannelsKt$fixedPeriodTicker$1.label = 2;
                        if (sendChannel.send(unit, tickerChannelsKt$fixedPeriodTicker$1) == coroutine_suspended) {
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        tickerChannelsKt$fixedPeriodTicker$1 = new TickerChannelsKt$fixedPeriodTicker$1(continuation);
        Object $result2 = tickerChannelsKt$fixedPeriodTicker$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (tickerChannelsKt$fixedPeriodTicker$1.label) {
        }
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007a A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0078 -> B:12:0x005d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final java.lang.Object fixedDelayTicker(long r4, long r6, kotlinx.coroutines.channels.SendChannel<? super kotlin.Unit> r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1
            if (r0 == 0) goto L14
            r0 = r9
            kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1 r0 = (kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1 r0 = new kotlinx.coroutines.channels.TickerChannelsKt$fixedDelayTicker$1
            r0.<init>(r9)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L4a;
                case 1: goto L40;
                case 2: goto L36;
                case 3: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2c:
            long r4 = r0.J$0
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r6 = (kotlinx.coroutines.channels.SendChannel) r6
            kotlin.ResultKt.throwOnFailure(r1)
            goto L7b
        L36:
            long r4 = r0.J$0
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r6 = (kotlinx.coroutines.channels.SendChannel) r6
            kotlin.ResultKt.throwOnFailure(r1)
            goto L6d
        L40:
            long r4 = r0.J$0
            java.lang.Object r6 = r0.L$0
            kotlinx.coroutines.channels.SendChannel r6 = (kotlinx.coroutines.channels.SendChannel) r6
            kotlin.ResultKt.throwOnFailure(r1)
            goto L5c
        L4a:
            kotlin.ResultKt.throwOnFailure(r1)
            r0.L$0 = r8
            r0.J$0 = r4
            r3 = 1
            r0.label = r3
            java.lang.Object r6 = kotlinx.coroutines.DelayKt.delay(r6, r0)
            if (r6 != r2) goto L5b
            return r2
        L5b:
            r6 = r8
        L5c:
        L5d:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            r0.L$0 = r6
            r0.J$0 = r4
            r8 = 2
            r0.label = r8
            java.lang.Object r7 = r6.send(r7, r0)
            if (r7 != r2) goto L6d
            return r2
        L6d:
            r0.L$0 = r6
            r0.J$0 = r4
            r7 = 3
            r0.label = r7
            java.lang.Object r7 = kotlinx.coroutines.DelayKt.delay(r4, r0)
            if (r7 != r2) goto L7b
            return r2
        L7b:
            goto L5d
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.TickerChannelsKt.fixedDelayTicker(long, long, kotlinx.coroutines.channels.SendChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
