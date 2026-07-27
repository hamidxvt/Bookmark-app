package kotlinx.coroutines.channels;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.selects.SelectClause1;

/* compiled from: Channels.common.kt */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u001a$\u0010\u0002\u001a\u0004\u0018\u0001H\u0003\"\b\b\u0000\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0087@¢\u0006\u0002\u0010\u0006\u001a$\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00030\b\"\b\b\u0000\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0007\u001aP\u0010\t\u001a\u0002H\n\"\u0004\b\u0000\u0010\u0003\"\u0004\b\u0001\u0010\n*\b\u0012\u0004\u0012\u0002H\u00030\u00052\u001d\u0010\u000b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0005\u0012\u0004\u0012\u0002H\n0\f¢\u0006\u0002\b\rH\u0086\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u000e\u001a2\u0010\u000f\u001a\u00020\u0010\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00052\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u00100\fH\u0086H¢\u0006\u0002\u0010\u0012\u001a$\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0014\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0086@¢\u0006\u0002\u0010\u0006\u001a\u001a\u0010\u0015\u001a\u00020\u0010*\u0006\u0012\u0002\b\u00030\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"DEFAULT_CLOSE_MESSAGE", "", "receiveOrNull", "E", "", "Lkotlinx/coroutines/channels/ReceiveChannel;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onReceiveOrNull", "Lkotlinx/coroutines/selects/SelectClause1;", "consume", "R", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "consumeEach", "", "action", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toList", "", "cancelConsumed", "cause", "", "kotlinx-coroutines-core"}, k = 5, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE, xs = "kotlinx/coroutines/channels/ChannelsKt")
/* loaded from: classes17.dex */
final /* synthetic */ class ChannelsKt__Channels_commonKt {
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in the favour of 'receiveCatching'", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
    public static final /* synthetic */ Object receiveOrNull(ReceiveChannel $this$receiveOrNull, Continuation $completion) {
        Intrinsics.checkNotNull($this$receiveOrNull, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveChannel<E of kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.receiveOrNull?>");
        return $this$receiveOrNull.receiveOrNull($completion);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in the favour of 'onReceiveCatching'")
    public static final /* synthetic */ SelectClause1 onReceiveOrNull(ReceiveChannel $this$onReceiveOrNull) {
        Intrinsics.checkNotNull($this$onReceiveOrNull, "null cannot be cast to non-null type kotlinx.coroutines.channels.ReceiveChannel<E of kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt.onReceiveOrNull?>");
        return $this$onReceiveOrNull.getOnReceiveOrNull();
    }

    public static final <E, R> R consume(ReceiveChannel<? extends E> receiveChannel, Function1<? super ReceiveChannel<? extends E>, ? extends R> function1) {
        try {
            R invoke = function1.invoke(receiveChannel);
            InlineMarker.finallyStart(1);
            ChannelsKt.cancelConsumed(receiveChannel, null);
            InlineMarker.finallyEnd(1);
            return invoke;
        } finally {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007f A[Catch: all -> 0x009e, TryCatch #3 {all -> 0x009e, blocks: (B:16:0x0077, B:18:0x007f, B:24:0x008e), top: B:15:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x008e A[Catch: all -> 0x009e, TRY_LEAVE, TryCatch #3 {all -> 0x009e, blocks: (B:16:0x0077, B:18:0x007f, B:24:0x008e), top: B:15:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x006e -> B:15:0x0077). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <E> Object consumeEach(ReceiveChannel<? extends E> receiveChannel, Function1<? super E, Unit> function1, Continuation<? super Unit> continuation) {
        ChannelsKt__Channels_commonKt$consumeEach$1 channelsKt__Channels_commonKt$consumeEach$1;
        ReceiveChannel $this$consume$iv;
        Object $result;
        Function1 action;
        ReceiveChannel $this$consume$iv2;
        Throwable cause$iv;
        ChannelIterator channelIterator;
        int i;
        Object obj;
        if (continuation instanceof ChannelsKt__Channels_commonKt$consumeEach$1) {
            channelsKt__Channels_commonKt$consumeEach$1 = (ChannelsKt__Channels_commonKt$consumeEach$1) continuation;
            if ((channelsKt__Channels_commonKt$consumeEach$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__Channels_commonKt$consumeEach$1.label -= Integer.MIN_VALUE;
                Object e = channelsKt__Channels_commonKt$consumeEach$1.result;
                Object $result2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__Channels_commonKt$consumeEach$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(e);
                        $this$consume$iv = receiveChannel;
                        Throwable cause$iv2 = null;
                        try {
                            ChannelIterator it = $this$consume$iv.iterator();
                            int $i$f$consumeEach = 0;
                            Function1 action2 = function1;
                            channelsKt__Channels_commonKt$consumeEach$1.L$0 = action2;
                            channelsKt__Channels_commonKt$consumeEach$1.L$1 = $this$consume$iv;
                            channelsKt__Channels_commonKt$consumeEach$1.L$2 = it;
                            channelsKt__Channels_commonKt$consumeEach$1.label = 1;
                            Object hasNext = it.hasNext(channelsKt__Channels_commonKt$consumeEach$1);
                            if (hasNext != $result2) {
                                return $result2;
                            }
                            Object obj2 = $result2;
                            $result = e;
                            e = hasNext;
                            action = action2;
                            $this$consume$iv2 = $this$consume$iv;
                            cause$iv = cause$iv2;
                            channelIterator = it;
                            i = $i$f$consumeEach;
                            obj = obj2;
                            try {
                                if (((Boolean) e).booleanValue()) {
                                    Unit unit = Unit.INSTANCE;
                                    InlineMarker.finallyStart(1);
                                    ChannelsKt.cancelConsumed($this$consume$iv2, cause$iv);
                                    InlineMarker.finallyEnd(1);
                                    return Unit.INSTANCE;
                                }
                                action.invoke(channelIterator.next());
                                e = $result;
                                $result2 = obj;
                                $i$f$consumeEach = i;
                                it = channelIterator;
                                cause$iv2 = cause$iv;
                                $this$consume$iv = $this$consume$iv2;
                                action2 = action;
                                channelsKt__Channels_commonKt$consumeEach$1.L$0 = action2;
                                channelsKt__Channels_commonKt$consumeEach$1.L$1 = $this$consume$iv;
                                channelsKt__Channels_commonKt$consumeEach$1.L$2 = it;
                                channelsKt__Channels_commonKt$consumeEach$1.label = 1;
                                Object hasNext2 = it.hasNext(channelsKt__Channels_commonKt$consumeEach$1);
                                if (hasNext2 != $result2) {
                                }
                            } catch (Throwable th) {
                                $this$consume$iv = $this$consume$iv2;
                                e$iv = th;
                                Throwable cause$iv3 = e$iv;
                                try {
                                    throw e$iv;
                                } catch (Throwable e$iv) {
                                    InlineMarker.finallyStart(1);
                                    ChannelsKt.cancelConsumed($this$consume$iv, cause$iv3);
                                    InlineMarker.finallyEnd(1);
                                    throw e$iv;
                                }
                            }
                        } catch (Throwable th2) {
                            e$iv = th2;
                            Throwable cause$iv32 = e$iv;
                            throw e$iv;
                        }
                    case 1:
                        ChannelIterator channelIterator2 = (ChannelIterator) channelsKt__Channels_commonKt$consumeEach$1.L$2;
                        $this$consume$iv = (ReceiveChannel) channelsKt__Channels_commonKt$consumeEach$1.L$1;
                        Function1 action3 = (Function1) channelsKt__Channels_commonKt$consumeEach$1.L$0;
                        try {
                            ResultKt.throwOnFailure(e);
                            action = action3;
                            $this$consume$iv2 = $this$consume$iv;
                            cause$iv = null;
                            channelIterator = channelIterator2;
                            i = 0;
                            obj = $result2;
                            $result = e;
                            if (((Boolean) e).booleanValue()) {
                            }
                        } catch (Throwable th3) {
                            e$iv = th3;
                            Throwable cause$iv322 = e$iv;
                            throw e$iv;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__Channels_commonKt$consumeEach$1 = new ChannelsKt__Channels_commonKt$consumeEach$1(continuation);
        Object e2 = channelsKt__Channels_commonKt$consumeEach$1.result;
        Object $result22 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__Channels_commonKt$consumeEach$1.label) {
        }
    }

    private static final <E> Object consumeEach$$forInline(ReceiveChannel<? extends E> receiveChannel, Function1<? super E, Unit> function1, Continuation<? super Unit> continuation) {
        try {
            ReceiveChannel<? extends E> $this$consumeEach_u24lambda_u240 = receiveChannel;
            ChannelIterator<? extends E> it = $this$consumeEach_u24lambda_u240.iterator();
            while (true) {
                InlineMarker.mark(3);
                InlineMarker.mark(0);
                Object hasNext = it.hasNext(null);
                InlineMarker.mark(1);
                if (!((Boolean) hasNext).booleanValue()) {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannel, null);
                    InlineMarker.finallyEnd(1);
                    return Unit.INSTANCE;
                }
                Object e = it.next();
                function1.invoke(e);
            }
        } finally {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x009b A[Catch: all -> 0x00bf, TryCatch #4 {all -> 0x00bf, blocks: (B:16:0x0093, B:18:0x009b, B:33:0x00b0), top: B:15:0x0093 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0084 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b0 A[Catch: all -> 0x00bf, TRY_LEAVE, TryCatch #4 {all -> 0x00bf, blocks: (B:16:0x0093, B:18:0x009b, B:33:0x00b0), top: B:15:0x0093 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0085 -> B:15:0x0093). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <E> Object toList(ReceiveChannel<? extends E> receiveChannel, Continuation<? super List<? extends E>> continuation) {
        ChannelsKt__Channels_commonKt$toList$1 channelsKt__Channels_commonKt$toList$1;
        ChannelsKt__Channels_commonKt$toList$1 channelsKt__Channels_commonKt$toList$12;
        ReceiveChannel $this$consume$iv$iv;
        Object $result;
        List list;
        List list2;
        ReceiveChannel $this$consume$iv$iv2;
        Throwable cause$iv$iv;
        ChannelIterator channelIterator;
        int i;
        List list3;
        int $i$f$consume;
        int $i$f$consumeEach;
        if (continuation instanceof ChannelsKt__Channels_commonKt$toList$1) {
            channelsKt__Channels_commonKt$toList$1 = (ChannelsKt__Channels_commonKt$toList$1) continuation;
            if ((channelsKt__Channels_commonKt$toList$1.label & Integer.MIN_VALUE) != 0) {
                channelsKt__Channels_commonKt$toList$1.label -= Integer.MIN_VALUE;
                channelsKt__Channels_commonKt$toList$12 = channelsKt__Channels_commonKt$toList$1;
                Object e$iv = channelsKt__Channels_commonKt$toList$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (channelsKt__Channels_commonKt$toList$12.label) {
                    case 0:
                        ResultKt.throwOnFailure(e$iv);
                        List $this$toList_u24lambda_u242 = CollectionsKt.createListBuilder();
                        $this$consume$iv$iv = receiveChannel;
                        Throwable cause$iv$iv2 = null;
                        try {
                            List $this$toList_u24lambda_u2422 = $this$toList_u24lambda_u242;
                            List $this$toList_u24lambda_u2423 = null;
                            int $i$f$consume2 = 0;
                            int $i$f$consume3 = 0;
                            List $this$toList_u24lambda_u2424 = $this$toList_u24lambda_u242;
                            int $i$f$consumeEach2 = 0;
                            ChannelIterator it = $this$consume$iv$iv.iterator();
                            try {
                                channelsKt__Channels_commonKt$toList$12.L$0 = $this$toList_u24lambda_u2424;
                                channelsKt__Channels_commonKt$toList$12.L$1 = $this$toList_u24lambda_u2422;
                                channelsKt__Channels_commonKt$toList$12.L$2 = $this$consume$iv$iv;
                                channelsKt__Channels_commonKt$toList$12.L$3 = it;
                                channelsKt__Channels_commonKt$toList$12.label = 1;
                                Object hasNext = it.hasNext(channelsKt__Channels_commonKt$toList$12);
                                if (hasNext != coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                int i2 = $i$f$consume2;
                                $result = e$iv;
                                e$iv = hasNext;
                                list = $this$toList_u24lambda_u2424;
                                list2 = $this$toList_u24lambda_u2422;
                                $this$consume$iv$iv2 = $this$consume$iv$iv;
                                cause$iv$iv = cause$iv$iv2;
                                channelIterator = it;
                                i = $i$f$consume3;
                                list3 = $this$toList_u24lambda_u2423;
                                $i$f$consume = $i$f$consumeEach2;
                                $i$f$consumeEach = i2;
                                try {
                                    if (((Boolean) e$iv).booleanValue()) {
                                        Unit unit = Unit.INSTANCE;
                                        ChannelsKt.cancelConsumed($this$consume$iv$iv2, cause$iv$iv);
                                        return CollectionsKt.build(list);
                                    }
                                    list2.add(channelIterator.next());
                                    e$iv = $result;
                                    $i$f$consume2 = $i$f$consumeEach;
                                    $i$f$consumeEach2 = $i$f$consume;
                                    $this$toList_u24lambda_u2423 = list3;
                                    $i$f$consume3 = i;
                                    it = channelIterator;
                                    cause$iv$iv2 = cause$iv$iv;
                                    $this$consume$iv$iv = $this$consume$iv$iv2;
                                    $this$toList_u24lambda_u2422 = list2;
                                    $this$toList_u24lambda_u2424 = list;
                                    channelsKt__Channels_commonKt$toList$12.L$0 = $this$toList_u24lambda_u2424;
                                    channelsKt__Channels_commonKt$toList$12.L$1 = $this$toList_u24lambda_u2422;
                                    channelsKt__Channels_commonKt$toList$12.L$2 = $this$consume$iv$iv;
                                    channelsKt__Channels_commonKt$toList$12.L$3 = it;
                                    channelsKt__Channels_commonKt$toList$12.label = 1;
                                    Object hasNext2 = it.hasNext(channelsKt__Channels_commonKt$toList$12);
                                    if (hasNext2 != coroutine_suspended) {
                                    }
                                } catch (Throwable th) {
                                    e$iv$iv = th;
                                    $this$consume$iv$iv = $this$consume$iv$iv2;
                                    Throwable cause$iv$iv3 = e$iv$iv;
                                    try {
                                        throw e$iv$iv;
                                    } catch (Throwable e$iv$iv) {
                                        ChannelsKt.cancelConsumed($this$consume$iv$iv, cause$iv$iv3);
                                        throw e$iv$iv;
                                    }
                                }
                            } catch (Throwable th2) {
                                e$iv$iv = th2;
                                Throwable cause$iv$iv32 = e$iv$iv;
                                throw e$iv$iv;
                            }
                        } catch (Throwable th3) {
                            e$iv$iv = th3;
                            Throwable cause$iv$iv322 = e$iv$iv;
                            throw e$iv$iv;
                        }
                    case 1:
                        ChannelIterator channelIterator2 = (ChannelIterator) channelsKt__Channels_commonKt$toList$12.L$3;
                        $this$consume$iv$iv = (ReceiveChannel) channelsKt__Channels_commonKt$toList$12.L$2;
                        List $this$toList_u24lambda_u2425 = (List) channelsKt__Channels_commonKt$toList$12.L$1;
                        List list4 = (List) channelsKt__Channels_commonKt$toList$12.L$0;
                        try {
                            ResultKt.throwOnFailure(e$iv);
                            list = list4;
                            list2 = $this$toList_u24lambda_u2425;
                            $this$consume$iv$iv2 = $this$consume$iv$iv;
                            cause$iv$iv = null;
                            channelIterator = channelIterator2;
                            i = 0;
                            list3 = null;
                            $i$f$consume = 0;
                            $i$f$consumeEach = 0;
                            $result = e$iv;
                            if (((Boolean) e$iv).booleanValue()) {
                            }
                        } catch (Throwable th4) {
                            e$iv$iv = th4;
                            Throwable cause$iv$iv3222 = e$iv$iv;
                            throw e$iv$iv;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        channelsKt__Channels_commonKt$toList$1 = new ChannelsKt__Channels_commonKt$toList$1(continuation);
        channelsKt__Channels_commonKt$toList$12 = channelsKt__Channels_commonKt$toList$1;
        Object e$iv2 = channelsKt__Channels_commonKt$toList$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (channelsKt__Channels_commonKt$toList$12.label) {
        }
    }

    public static final void cancelConsumed(ReceiveChannel<?> receiveChannel, Throwable cause) {
        if (cause != null) {
            r0 = cause instanceof CancellationException ? (CancellationException) cause : null;
            if (r0 == null) {
                r0 = ExceptionsKt.CancellationException("Channel was consumed, consumer had failed", cause);
            }
        }
        receiveChannel.cancel(r0);
    }
}
