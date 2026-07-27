package kotlinx.coroutines.channels;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.selects.SelectImplementation;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.TrySelectDetailedResult;

/* compiled from: BroadcastChannel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "kotlinx.coroutines.channels.BroadcastChannelImpl$registerSelectForSend$2", f = "BroadcastChannel.kt", i = {}, l = {240}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes17.dex */
final class BroadcastChannelImpl$registerSelectForSend$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $element;
    final /* synthetic */ SelectInstance<?> $select;
    int label;
    final /* synthetic */ BroadcastChannelImpl<E> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BroadcastChannelImpl$registerSelectForSend$2(BroadcastChannelImpl<E> broadcastChannelImpl, Object obj, SelectInstance<?> selectInstance, Continuation<? super BroadcastChannelImpl$registerSelectForSend$2> continuation) {
        super(2, continuation);
        this.this$0 = broadcastChannelImpl;
        this.$element = obj;
        this.$select = selectInstance;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BroadcastChannelImpl$registerSelectForSend$2(this.this$0, this.$element, this.$select, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BroadcastChannelImpl$registerSelectForSend$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0064 A[Catch: all -> 0x00b0, TryCatch #0 {all -> 0x00b0, blocks: (B:12:0x005e, B:14:0x0064, B:19:0x0073, B:20:0x0078, B:21:0x0079, B:23:0x0081, B:24:0x0088, B:26:0x00a0, B:27:0x00a7, B:30:0x0084), top: B:11:0x005e }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0081 A[Catch: all -> 0x00b0, TryCatch #0 {all -> 0x00b0, blocks: (B:12:0x005e, B:14:0x0064, B:19:0x0073, B:20:0x0078, B:21:0x0079, B:23:0x0081, B:24:0x0088, B:26:0x00a0, B:27:0x00a7, B:30:0x0084), top: B:11:0x005e }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a0 A[Catch: all -> 0x00b0, TryCatch #0 {all -> 0x00b0, blocks: (B:12:0x005e, B:14:0x0064, B:19:0x0073, B:20:0x0078, B:21:0x0079, B:23:0x0081, B:24:0x0088, B:26:0x00a0, B:27:0x00a7, B:30:0x0084), top: B:11:0x005e }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0084 A[Catch: all -> 0x00b0, TryCatch #0 {all -> 0x00b0, blocks: (B:12:0x005e, B:14:0x0064, B:19:0x0073, B:20:0x0078, B:21:0x0079, B:23:0x0081, B:24:0x0088, B:26:0x00a0, B:27:0x00a7, B:30:0x0084), top: B:11:0x005e }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        Throwable t;
        BroadcastChannelImpl$registerSelectForSend$2 broadcastChannelImpl$registerSelectForSend$2;
        Throwable t2;
        ReentrantLock reentrantLock;
        HashMap hashMap;
        TrySelectDetailedResult trySelectResult;
        HashMap hashMap2;
        HashMap hashMap3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    this.label = 1;
                } catch (Throwable th) {
                    t = th;
                    broadcastChannelImpl$registerSelectForSend$2 = this;
                    if (broadcastChannelImpl$registerSelectForSend$2.this$0.isClosedForSend() || (!(t instanceof ClosedSendChannelException) && broadcastChannelImpl$registerSelectForSend$2.this$0.getSendException() != t)) {
                        throw t;
                    }
                    t2 = null;
                    ReentrantLock $this$withLock$iv = ((BroadcastChannelImpl) broadcastChannelImpl$registerSelectForSend$2.this$0).lock;
                    BroadcastChannelImpl<E> broadcastChannelImpl = broadcastChannelImpl$registerSelectForSend$2.this$0;
                    SelectInstance<?> selectInstance = broadcastChannelImpl$registerSelectForSend$2.$select;
                    reentrantLock = $this$withLock$iv;
                    reentrantLock.lock();
                    try {
                        if (DebugKt.getASSERTIONS_ENABLED()) {
                        }
                        hashMap = ((BroadcastChannelImpl) broadcastChannelImpl).onSendInternalResult;
                        hashMap.put(selectInstance, t2 != null ? Unit.INSTANCE : BufferedChannelKt.getCHANNEL_CLOSED());
                        Intrinsics.checkNotNull(selectInstance, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectImplementation<*>");
                        trySelectResult = ((SelectImplementation) selectInstance).trySelectDetailed(broadcastChannelImpl, Unit.INSTANCE);
                        if (trySelectResult != TrySelectDetailedResult.REREGISTER) {
                        }
                        Unit unit = Unit.INSTANCE;
                        reentrantLock.unlock();
                        return Unit.INSTANCE;
                    } catch (Throwable th2) {
                        reentrantLock.unlock();
                        throw th2;
                    }
                }
                if (this.this$0.send(this.$element, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                broadcastChannelImpl$registerSelectForSend$2 = this;
                t2 = 1;
                ReentrantLock $this$withLock$iv2 = ((BroadcastChannelImpl) broadcastChannelImpl$registerSelectForSend$2.this$0).lock;
                BroadcastChannelImpl<E> broadcastChannelImpl2 = broadcastChannelImpl$registerSelectForSend$2.this$0;
                SelectInstance<?> selectInstance2 = broadcastChannelImpl$registerSelectForSend$2.$select;
                reentrantLock = $this$withLock$iv2;
                reentrantLock.lock();
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    hashMap3 = ((BroadcastChannelImpl) broadcastChannelImpl2).onSendInternalResult;
                    if (!(hashMap3.get(selectInstance2) == null)) {
                        throw new AssertionError();
                    }
                }
                hashMap = ((BroadcastChannelImpl) broadcastChannelImpl2).onSendInternalResult;
                hashMap.put(selectInstance2, t2 != null ? Unit.INSTANCE : BufferedChannelKt.getCHANNEL_CLOSED());
                Intrinsics.checkNotNull(selectInstance2, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectImplementation<*>");
                trySelectResult = ((SelectImplementation) selectInstance2).trySelectDetailed(broadcastChannelImpl2, Unit.INSTANCE);
                if (trySelectResult != TrySelectDetailedResult.REREGISTER) {
                    hashMap2 = ((BroadcastChannelImpl) broadcastChannelImpl2).onSendInternalResult;
                    hashMap2.remove(selectInstance2);
                }
                Unit unit2 = Unit.INSTANCE;
                reentrantLock.unlock();
                return Unit.INSTANCE;
            case 1:
                broadcastChannelImpl$registerSelectForSend$2 = this;
                try {
                    ResultKt.throwOnFailure($result);
                    t2 = 1;
                } catch (Throwable th3) {
                    t = th3;
                    if (broadcastChannelImpl$registerSelectForSend$2.this$0.isClosedForSend()) {
                        break;
                    }
                    throw t;
                }
                ReentrantLock $this$withLock$iv22 = ((BroadcastChannelImpl) broadcastChannelImpl$registerSelectForSend$2.this$0).lock;
                BroadcastChannelImpl<E> broadcastChannelImpl22 = broadcastChannelImpl$registerSelectForSend$2.this$0;
                SelectInstance<?> selectInstance22 = broadcastChannelImpl$registerSelectForSend$2.$select;
                reentrantLock = $this$withLock$iv22;
                reentrantLock.lock();
                if (DebugKt.getASSERTIONS_ENABLED()) {
                }
                hashMap = ((BroadcastChannelImpl) broadcastChannelImpl22).onSendInternalResult;
                hashMap.put(selectInstance22, t2 != null ? Unit.INSTANCE : BufferedChannelKt.getCHANNEL_CLOSED());
                Intrinsics.checkNotNull(selectInstance22, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectImplementation<*>");
                trySelectResult = ((SelectImplementation) selectInstance22).trySelectDetailed(broadcastChannelImpl22, Unit.INSTANCE);
                if (trySelectResult != TrySelectDetailedResult.REREGISTER) {
                }
                Unit unit22 = Unit.INSTANCE;
                reentrantLock.unlock();
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
