package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: DataStoreImpl.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\tJ\u000e\u0010\n\u001a\u00020\u0005H¤@¢\u0006\u0002\u0010\tJ\u000e\u0010\u000b\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\tR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/datastore/core/RunOnce;", "", "()V", "didRun", "Lkotlinx/coroutines/CompletableDeferred;", "", "runMutex", "Lkotlinx/coroutines/sync/Mutex;", "awaitComplete", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doRun", "runIfNeeded", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public abstract class RunOnce {
    private final Mutex runMutex = MutexKt.Mutex$default(false, 1, null);
    private final CompletableDeferred<Unit> didRun = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);

    protected abstract Object doRun(Continuation<? super Unit> continuation);

    public final Object awaitComplete(Continuation<? super Unit> continuation) {
        Object await = this.didRun.await(continuation);
        return await == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? await : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0080 A[Catch: all -> 0x00ac, TRY_LEAVE, TryCatch #1 {all -> 0x00ac, blocks: (B:24:0x0078, B:26:0x0080), top: B:23:0x0078 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0086 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object runIfNeeded(Continuation<? super Unit> continuation) {
        RunOnce$runIfNeeded$1 runOnce$runIfNeeded$1;
        RunOnce$runIfNeeded$1 runOnce$runIfNeeded$12;
        Mutex $this$withLock_u24default$iv;
        RunOnce runOnce;
        Mutex owner$iv;
        Mutex $this$withLock_u24default$iv2;
        Mutex $this$withLock_u24default$iv3;
        if (continuation instanceof RunOnce$runIfNeeded$1) {
            runOnce$runIfNeeded$1 = (RunOnce$runIfNeeded$1) continuation;
            if ((runOnce$runIfNeeded$1.label & Integer.MIN_VALUE) != 0) {
                runOnce$runIfNeeded$1.label -= Integer.MIN_VALUE;
                runOnce$runIfNeeded$12 = runOnce$runIfNeeded$1;
                Object $result = runOnce$runIfNeeded$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (runOnce$runIfNeeded$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        if (this.didRun.isCompleted()) {
                            return Unit.INSTANCE;
                        }
                        $this$withLock_u24default$iv = this.runMutex;
                        runOnce$runIfNeeded$12.L$0 = this;
                        runOnce$runIfNeeded$12.L$1 = $this$withLock_u24default$iv;
                        runOnce$runIfNeeded$12.label = 1;
                        if ($this$withLock_u24default$iv.lock(null, runOnce$runIfNeeded$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        runOnce = this;
                        owner$iv = null;
                        try {
                            if (!runOnce.didRun.isCompleted()) {
                                Unit unit = Unit.INSTANCE;
                                $this$withLock_u24default$iv.unlock(owner$iv);
                                return unit;
                            }
                            try {
                                runOnce$runIfNeeded$12.L$0 = runOnce;
                                runOnce$runIfNeeded$12.L$1 = $this$withLock_u24default$iv;
                                runOnce$runIfNeeded$12.label = 2;
                                if (runOnce.doRun(runOnce$runIfNeeded$12) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                Mutex mutex = owner$iv;
                                $this$withLock_u24default$iv2 = $this$withLock_u24default$iv;
                                $this$withLock_u24default$iv3 = mutex;
                                runOnce.didRun.complete(Unit.INSTANCE);
                                $this$withLock_u24default$iv2.unlock($this$withLock_u24default$iv3);
                                return Unit.INSTANCE;
                            } catch (Throwable th) {
                                th = th;
                                Mutex mutex2 = owner$iv;
                                $this$withLock_u24default$iv2 = $this$withLock_u24default$iv;
                                $this$withLock_u24default$iv3 = mutex2;
                                $this$withLock_u24default$iv2.unlock($this$withLock_u24default$iv3);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            Mutex mutex3 = owner$iv;
                            $this$withLock_u24default$iv2 = $this$withLock_u24default$iv;
                            $this$withLock_u24default$iv3 = mutex3;
                        }
                    case 1:
                        $this$withLock_u24default$iv = (Mutex) runOnce$runIfNeeded$12.L$1;
                        RunOnce runOnce2 = (RunOnce) runOnce$runIfNeeded$12.L$0;
                        ResultKt.throwOnFailure($result);
                        runOnce = runOnce2;
                        owner$iv = null;
                        if (!runOnce.didRun.isCompleted()) {
                        }
                        break;
                    case 2:
                        $this$withLock_u24default$iv3 = null;
                        $this$withLock_u24default$iv2 = (Mutex) runOnce$runIfNeeded$12.L$1;
                        runOnce = (RunOnce) runOnce$runIfNeeded$12.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            runOnce.didRun.complete(Unit.INSTANCE);
                            $this$withLock_u24default$iv2.unlock($this$withLock_u24default$iv3);
                            return Unit.INSTANCE;
                        } catch (Throwable th3) {
                            th = th3;
                            $this$withLock_u24default$iv2.unlock($this$withLock_u24default$iv3);
                            throw th;
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        runOnce$runIfNeeded$1 = new RunOnce$runIfNeeded$1(this, continuation);
        runOnce$runIfNeeded$12 = runOnce$runIfNeeded$1;
        Object $result2 = runOnce$runIfNeeded$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (runOnce$runIfNeeded$12.label) {
        }
    }
}
