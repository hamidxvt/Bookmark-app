package kotlinx.coroutines.flow;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;

/* compiled from: Transform.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
final class FlowKt__TransformKt$runningFold$1$1<T> implements FlowCollector {
    final /* synthetic */ Ref.ObjectRef<R> $accumulator;
    final /* synthetic */ Function3<R, T, Continuation<? super R>, Object> $operation;
    final /* synthetic */ FlowCollector<R> $this_flow;

    /* JADX WARN: Multi-variable type inference failed */
    FlowKt__TransformKt$runningFold$1$1(Ref.ObjectRef<R> objectRef, Function3<? super R, ? super T, ? super Continuation<? super R>, ? extends Object> function3, FlowCollector<? super R> flowCollector) {
        this.$accumulator = objectRef;
        this.$operation = function3;
        this.$this_flow = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0072 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t, Continuation<? super Unit> continuation) {
        FlowKt__TransformKt$runningFold$1$1$emit$1 flowKt__TransformKt$runningFold$1$1$emit$1;
        T t2;
        Ref.ObjectRef objectRef;
        FlowKt__TransformKt$runningFold$1$1<T> flowKt__TransformKt$runningFold$1$1;
        FlowCollector<R> flowCollector;
        T t3;
        if (continuation instanceof FlowKt__TransformKt$runningFold$1$1$emit$1) {
            flowKt__TransformKt$runningFold$1$1$emit$1 = (FlowKt__TransformKt$runningFold$1$1$emit$1) continuation;
            if ((flowKt__TransformKt$runningFold$1$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                flowKt__TransformKt$runningFold$1$1$emit$1.label -= Integer.MIN_VALUE;
                Object obj = flowKt__TransformKt$runningFold$1$1$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (flowKt__TransformKt$runningFold$1$1$emit$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        Ref.ObjectRef objectRef2 = this.$accumulator;
                        Function3<R, T, Continuation<? super R>, Object> function3 = this.$operation;
                        T t4 = this.$accumulator.element;
                        flowKt__TransformKt$runningFold$1$1$emit$1.L$0 = this;
                        flowKt__TransformKt$runningFold$1$1$emit$1.L$1 = objectRef2;
                        flowKt__TransformKt$runningFold$1$1$emit$1.label = 1;
                        Object invoke = function3.invoke(t4, t, flowKt__TransformKt$runningFold$1$1$emit$1);
                        if (invoke == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        t2 = (T) invoke;
                        objectRef = objectRef2;
                        flowKt__TransformKt$runningFold$1$1 = this;
                        objectRef.element = t2;
                        flowCollector = flowKt__TransformKt$runningFold$1$1.$this_flow;
                        t3 = flowKt__TransformKt$runningFold$1$1.$accumulator.element;
                        flowKt__TransformKt$runningFold$1$1$emit$1.L$0 = null;
                        flowKt__TransformKt$runningFold$1$1$emit$1.L$1 = null;
                        flowKt__TransformKt$runningFold$1$1$emit$1.label = 2;
                        if (flowCollector.emit(t3, flowKt__TransformKt$runningFold$1$1$emit$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    case 1:
                        objectRef = (Ref.ObjectRef) flowKt__TransformKt$runningFold$1$1$emit$1.L$1;
                        FlowKt__TransformKt$runningFold$1$1<T> flowKt__TransformKt$runningFold$1$12 = (FlowKt__TransformKt$runningFold$1$1) flowKt__TransformKt$runningFold$1$1$emit$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        flowKt__TransformKt$runningFold$1$1 = flowKt__TransformKt$runningFold$1$12;
                        t2 = (T) obj;
                        objectRef.element = t2;
                        flowCollector = flowKt__TransformKt$runningFold$1$1.$this_flow;
                        t3 = flowKt__TransformKt$runningFold$1$1.$accumulator.element;
                        flowKt__TransformKt$runningFold$1$1$emit$1.L$0 = null;
                        flowKt__TransformKt$runningFold$1$1$emit$1.L$1 = null;
                        flowKt__TransformKt$runningFold$1$1$emit$1.label = 2;
                        if (flowCollector.emit(t3, flowKt__TransformKt$runningFold$1$1$emit$1) == coroutine_suspended) {
                        }
                        return Unit.INSTANCE;
                    case 2:
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        flowKt__TransformKt$runningFold$1$1$emit$1 = new FlowKt__TransformKt$runningFold$1$1$emit$1(this, continuation);
        Object obj2 = flowKt__TransformKt$runningFold$1$1$emit$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (flowKt__TransformKt$runningFold$1$1$emit$1.label) {
        }
    }
}
