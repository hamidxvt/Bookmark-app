package androidx.work;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.work.Operation;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: Operation.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0004"}, d2 = {"await", "Landroidx/work/Operation$State$SUCCESS;", "Landroidx/work/Operation;", "(Landroidx/work/Operation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "work-runtime-ktx_release"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class OperationKt {
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object await(Operation $this$await, Continuation<? super Operation.State.SUCCESS> continuation) {
        OperationKt$await$1 operationKt$await$1;
        OperationKt$await$1 operationKt$await$12;
        Object obj;
        Object obj2;
        int $i$f$await;
        if (continuation instanceof OperationKt$await$1) {
            operationKt$await$1 = (OperationKt$await$1) continuation;
            if ((operationKt$await$1.label & Integer.MIN_VALUE) != 0) {
                operationKt$await$1.label -= Integer.MIN_VALUE;
                operationKt$await$12 = operationKt$await$1;
                Object $result = operationKt$await$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (operationKt$await$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        ListenableFuture result = $this$await.getResult();
                        Intrinsics.checkNotNullExpressionValue(result, "result");
                        if (result.isDone()) {
                            try {
                                obj = result.get();
                                Intrinsics.checkNotNullExpressionValue(obj, "result.await()");
                                return obj;
                            } catch (ExecutionException e$iv) {
                                Throwable cause = e$iv.getCause();
                                if (cause == null) {
                                    throw e$iv;
                                }
                                throw cause;
                            }
                        }
                        operationKt$await$12.L$0 = result;
                        operationKt$await$12.label = 1;
                        CancellableContinuationImpl cancellable$iv$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(operationKt$await$12), 1);
                        cancellable$iv$iv.initCancellability();
                        CancellableContinuationImpl cancellableContinuation$iv = cancellable$iv$iv;
                        result.addListener(new ListenableFutureKt$await$2$1(cancellableContinuation$iv, result), DirectExecutor.INSTANCE);
                        cancellableContinuation$iv.invokeOnCancellation(new ListenableFutureKt$await$2$2(result));
                        Object result2 = cancellable$iv$iv.getResult();
                        if (result2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                            DebugProbesKt.probeCoroutineSuspended(operationKt$await$12);
                        }
                        if (result2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj2 = result2;
                        $i$f$await = 0;
                        obj = obj2;
                        Intrinsics.checkNotNullExpressionValue(obj, "result.await()");
                        return obj;
                    case 1:
                        $i$f$await = 0;
                        ResultKt.throwOnFailure($result);
                        obj2 = $result;
                        obj = obj2;
                        Intrinsics.checkNotNullExpressionValue(obj, "result.await()");
                        return obj;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        operationKt$await$1 = new OperationKt$await$1(continuation);
        operationKt$await$12 = operationKt$await$1;
        Object $result2 = operationKt$await$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (operationKt$await$12.label) {
        }
    }

    private static final Object await$$forInline(Operation $this$await, Continuation<? super Operation.State.SUCCESS> continuation) {
        Object obj;
        ListenableFuture result = $this$await.getResult();
        Intrinsics.checkNotNullExpressionValue(result, "result");
        ListenableFuture $this$await$iv = result;
        if ($this$await$iv.isDone()) {
            try {
                obj = $this$await$iv.get();
            } catch (ExecutionException e$iv) {
                Throwable cause = e$iv.getCause();
                if (cause != null) {
                    throw cause;
                }
                throw e$iv;
            }
        } else {
            InlineMarker.mark(0);
            CancellableContinuationImpl cancellable$iv$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellable$iv$iv.initCancellability();
            CancellableContinuationImpl cancellableContinuation$iv = cancellable$iv$iv;
            $this$await$iv.addListener(new ListenableFutureKt$await$2$1(cancellableContinuation$iv, $this$await$iv), DirectExecutor.INSTANCE);
            cancellableContinuation$iv.invokeOnCancellation(new ListenableFutureKt$await$2$2($this$await$iv));
            Unit unit = Unit.INSTANCE;
            Object result2 = cancellable$iv$iv.getResult();
            if (result2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            InlineMarker.mark(1);
            obj = result2;
        }
        Intrinsics.checkNotNullExpressionValue(obj, "result.await()");
        return obj;
    }
}
