package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: SingleProcessCoordinator.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000fH\u0096@¢\u0006\u0002\u0010\u0010J\u000e\u0010\u0011\u001a\u00020\u000fH\u0096@¢\u0006\u0002\u0010\u0010J2\u0010\u0012\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u00132\u001c\u0010\u0014\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00130\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0015H\u0096@¢\u0006\u0002\u0010\u0018J8\u0010\u0019\u001a\u0002H\u0013\"\u0004\b\u0000\u0010\u00132\"\u0010\u0014\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00130\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u001aH\u0096@¢\u0006\u0002\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/datastore/core/SingleProcessCoordinator;", "Landroidx/datastore/core/InterProcessCoordinator;", "filePath", "", "(Ljava/lang/String;)V", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "updateNotifications", "Lkotlinx/coroutines/flow/Flow;", "", "getUpdateNotifications", "()Lkotlinx/coroutines/flow/Flow;", "version", "Landroidx/datastore/core/AtomicInt;", "getVersion", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "incrementAndGetVersion", "lock", "T", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryLock", "Lkotlin/Function2;", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class SingleProcessCoordinator implements InterProcessCoordinator {
    private final String filePath;
    private final Mutex mutex;
    private final Flow<Unit> updateNotifications;
    private final AtomicInt version;

    public SingleProcessCoordinator(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        this.filePath = filePath;
        this.mutex = MutexKt.Mutex$default(false, 1, null);
        this.version = new AtomicInt(0);
        this.updateNotifications = FlowKt.flow(new SingleProcessCoordinator$updateNotifications$1(null));
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Flow<Unit> getUpdateNotifications() {
        return this.updateNotifications;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0077 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    @Override // androidx.datastore.core.InterProcessCoordinator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public <T> Object lock(Function1<? super Continuation<? super T>, ? extends Object> function1, Continuation<? super T> continuation) {
        SingleProcessCoordinator$lock$1 singleProcessCoordinator$lock$1;
        SingleProcessCoordinator$lock$1 singleProcessCoordinator$lock$12;
        Function1 block;
        Mutex $this$withLock_u24default$iv;
        Object $this$withLock_u24default$iv2;
        Object invoke;
        Object owner$iv;
        if (continuation instanceof SingleProcessCoordinator$lock$1) {
            singleProcessCoordinator$lock$1 = (SingleProcessCoordinator$lock$1) continuation;
            if ((singleProcessCoordinator$lock$1.label & Integer.MIN_VALUE) != 0) {
                singleProcessCoordinator$lock$1.label -= Integer.MIN_VALUE;
                singleProcessCoordinator$lock$12 = singleProcessCoordinator$lock$1;
                Object $result = singleProcessCoordinator$lock$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                Object owner$iv2 = null;
                switch (singleProcessCoordinator$lock$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        block = function1;
                        Mutex $this$withLock_u24default$iv3 = this.mutex;
                        singleProcessCoordinator$lock$12.L$0 = block;
                        singleProcessCoordinator$lock$12.L$1 = $this$withLock_u24default$iv3;
                        singleProcessCoordinator$lock$12.label = 1;
                        if ($this$withLock_u24default$iv3.lock(null, singleProcessCoordinator$lock$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $this$withLock_u24default$iv = $this$withLock_u24default$iv3;
                        $this$withLock_u24default$iv2 = null;
                        try {
                            singleProcessCoordinator$lock$12.L$0 = $this$withLock_u24default$iv;
                            singleProcessCoordinator$lock$12.L$1 = null;
                            singleProcessCoordinator$lock$12.label = 2;
                            invoke = block.invoke(singleProcessCoordinator$lock$12);
                            if (invoke != coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            Object obj = $this$withLock_u24default$iv2;
                            owner$iv = invoke;
                            owner$iv2 = obj;
                            $this$withLock_u24default$iv.unlock(owner$iv2);
                            return owner$iv;
                        } catch (Throwable th) {
                            th = th;
                            owner$iv2 = $this$withLock_u24default$iv2;
                            $this$withLock_u24default$iv.unlock(owner$iv2);
                            throw th;
                        }
                    case 1:
                        Mutex $this$withLock_u24default$iv4 = (Mutex) singleProcessCoordinator$lock$12.L$1;
                        block = (Function1) singleProcessCoordinator$lock$12.L$0;
                        ResultKt.throwOnFailure($result);
                        $this$withLock_u24default$iv2 = null;
                        $this$withLock_u24default$iv = $this$withLock_u24default$iv4;
                        singleProcessCoordinator$lock$12.L$0 = $this$withLock_u24default$iv;
                        singleProcessCoordinator$lock$12.L$1 = null;
                        singleProcessCoordinator$lock$12.label = 2;
                        invoke = block.invoke(singleProcessCoordinator$lock$12);
                        if (invoke != coroutine_suspended) {
                        }
                        break;
                    case 2:
                        $this$withLock_u24default$iv = (Mutex) singleProcessCoordinator$lock$12.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            owner$iv = $result;
                            $this$withLock_u24default$iv.unlock(owner$iv2);
                            return owner$iv;
                        } catch (Throwable th2) {
                            th = th2;
                            $this$withLock_u24default$iv.unlock(owner$iv2);
                            throw th;
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        singleProcessCoordinator$lock$1 = new SingleProcessCoordinator$lock$1(this, continuation);
        singleProcessCoordinator$lock$12 = singleProcessCoordinator$lock$1;
        Object $result2 = singleProcessCoordinator$lock$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Object owner$iv22 = null;
        switch (singleProcessCoordinator$lock$12.label) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // androidx.datastore.core.InterProcessCoordinator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public <T> Object tryLock(Function2<? super Boolean, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        SingleProcessCoordinator$tryLock$1 singleProcessCoordinator$tryLock$1;
        SingleProcessCoordinator$tryLock$1 singleProcessCoordinator$tryLock$12;
        Mutex $this$withTryLock_u24default$iv;
        Object owner$iv;
        boolean locked$iv;
        boolean locked$iv2;
        Object invoke;
        if (continuation instanceof SingleProcessCoordinator$tryLock$1) {
            singleProcessCoordinator$tryLock$1 = (SingleProcessCoordinator$tryLock$1) continuation;
            if ((singleProcessCoordinator$tryLock$1.label & Integer.MIN_VALUE) != 0) {
                singleProcessCoordinator$tryLock$1.label -= Integer.MIN_VALUE;
                singleProcessCoordinator$tryLock$12 = singleProcessCoordinator$tryLock$1;
                Object $result = singleProcessCoordinator$tryLock$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (singleProcessCoordinator$tryLock$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        $this$withTryLock_u24default$iv = this.mutex;
                        owner$iv = null;
                        locked$iv = $this$withTryLock_u24default$iv.tryLock(null);
                        boolean it = locked$iv;
                        try {
                            Boolean boxBoolean = Boxing.boxBoolean(it);
                            singleProcessCoordinator$tryLock$12.L$0 = $this$withTryLock_u24default$iv;
                            singleProcessCoordinator$tryLock$12.Z$0 = locked$iv;
                            singleProcessCoordinator$tryLock$12.label = 1;
                            invoke = function2.invoke(boxBoolean, singleProcessCoordinator$tryLock$12);
                            if (invoke == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            Object owner$iv2 = owner$iv;
                            if (locked$iv) {
                                $this$withTryLock_u24default$iv.unlock(owner$iv2);
                            }
                            return invoke;
                        } catch (Throwable th) {
                            th = th;
                            locked$iv2 = locked$iv;
                            if (locked$iv2) {
                                $this$withTryLock_u24default$iv.unlock(owner$iv);
                            }
                            throw th;
                        }
                    case 1:
                        locked$iv2 = singleProcessCoordinator$tryLock$12.Z$0;
                        $this$withTryLock_u24default$iv = (Mutex) singleProcessCoordinator$tryLock$12.L$0;
                        owner$iv = null;
                        try {
                            ResultKt.throwOnFailure($result);
                            locked$iv = locked$iv2;
                            invoke = $result;
                            Object owner$iv22 = owner$iv;
                            if (locked$iv) {
                            }
                            return invoke;
                        } catch (Throwable th2) {
                            th = th2;
                            if (locked$iv2) {
                            }
                            throw th;
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        singleProcessCoordinator$tryLock$1 = new SingleProcessCoordinator$tryLock$1(this, continuation);
        singleProcessCoordinator$tryLock$12 = singleProcessCoordinator$tryLock$1;
        Object $result2 = singleProcessCoordinator$tryLock$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (singleProcessCoordinator$tryLock$12.label) {
        }
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Object getVersion(Continuation<? super Integer> continuation) {
        return Boxing.boxInt(this.version.get());
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Object incrementAndGetVersion(Continuation<? super Integer> continuation) {
        return Boxing.boxInt(this.version.incrementAndGet());
    }
}
