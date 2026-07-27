package kotlinx.coroutines.sync;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: Mutex.kt */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\u001a\u0010\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a?\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0000\u0010\u0005*\u00020\u00012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00050\tH\u0086H\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\n\"\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Mutex", "Lkotlinx/coroutines/sync/Mutex;", "locked", "", "withLock", "T", "owner", "", "action", "Lkotlin/Function0;", "(Lkotlinx/coroutines/sync/Mutex;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "NO_OWNER", "Lkotlinx/coroutines/internal/Symbol;", "ON_LOCK_ALREADY_LOCKED_BY_OWNER", "TRY_LOCK_SUCCESS", "", "TRY_LOCK_FAILED", "TRY_LOCK_ALREADY_LOCKED_BY_OWNER", "HOLDS_LOCK_UNLOCKED", "HOLDS_LOCK_YES", "HOLDS_LOCK_ANOTHER_OWNER", "kotlinx-coroutines-core"}, k = 2, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
public final class MutexKt {
    private static final int HOLDS_LOCK_ANOTHER_OWNER = 2;
    private static final int HOLDS_LOCK_UNLOCKED = 0;
    private static final int HOLDS_LOCK_YES = 1;
    private static final Symbol NO_OWNER = new Symbol("NO_OWNER");
    private static final Symbol ON_LOCK_ALREADY_LOCKED_BY_OWNER = new Symbol("ALREADY_LOCKED_BY_OWNER");
    private static final int TRY_LOCK_ALREADY_LOCKED_BY_OWNER = 2;
    private static final int TRY_LOCK_FAILED = 1;
    private static final int TRY_LOCK_SUCCESS = 0;

    public static /* synthetic */ Mutex Mutex$default(boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return Mutex(z);
    }

    public static final Mutex Mutex(boolean locked) {
        return new MutexImpl(locked);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> Object withLock(Mutex $this$withLock, Object owner, Function0<? extends T> function0, Continuation<? super T> continuation) {
        MutexKt$withLock$1 mutexKt$withLock$1;
        Mutex $this$withLock2;
        Object owner2;
        Function0 action;
        try {
            if (continuation instanceof MutexKt$withLock$1) {
                mutexKt$withLock$1 = (MutexKt$withLock$1) continuation;
                if ((mutexKt$withLock$1.label & Integer.MIN_VALUE) != 0) {
                    mutexKt$withLock$1.label -= Integer.MIN_VALUE;
                    Object $result = mutexKt$withLock$1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (mutexKt$withLock$1.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            mutexKt$withLock$1.L$0 = $this$withLock;
                            mutexKt$withLock$1.L$1 = owner;
                            mutexKt$withLock$1.L$2 = function0;
                            mutexKt$withLock$1.label = 1;
                            if ($this$withLock.lock(owner, mutexKt$withLock$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            $this$withLock2 = $this$withLock;
                            owner2 = owner;
                            action = function0;
                            break;
                        case 1:
                            action = (Function0) mutexKt$withLock$1.L$2;
                            owner2 = mutexKt$withLock$1.L$1;
                            $this$withLock2 = (Mutex) mutexKt$withLock$1.L$0;
                            ResultKt.throwOnFailure($result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    T invoke = action.invoke();
                    InlineMarker.finallyStart(1);
                    $this$withLock2.unlock(owner2);
                    InlineMarker.finallyEnd(1);
                    return invoke;
                }
            }
            T invoke2 = action.invoke();
            InlineMarker.finallyStart(1);
            $this$withLock2.unlock(owner2);
            InlineMarker.finallyEnd(1);
            return invoke2;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            $this$withLock2.unlock(owner2);
            InlineMarker.finallyEnd(1);
            throw th;
        }
        mutexKt$withLock$1 = new MutexKt$withLock$1(continuation);
        Object $result2 = mutexKt$withLock$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (mutexKt$withLock$1.label) {
        }
    }

    public static /* synthetic */ Object withLock$default(Mutex $this$withLock_u24default, Object owner, Function0 action, Continuation $completion, int i, Object obj) {
        if ((i & 1) != 0) {
            owner = null;
        }
        InlineMarker.mark(0);
        $this$withLock_u24default.lock(owner, $completion);
        InlineMarker.mark(1);
        try {
            return action.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            $this$withLock_u24default.unlock(owner);
            InlineMarker.finallyEnd(1);
        }
    }

    private static final <T> Object withLock$$forInline(Mutex $this$withLock, Object owner, Function0<? extends T> function0, Continuation<? super T> continuation) {
        InlineMarker.mark(0);
        $this$withLock.lock(owner, continuation);
        InlineMarker.mark(1);
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            $this$withLock.unlock(owner);
            InlineMarker.finallyEnd(1);
        }
    }
}
