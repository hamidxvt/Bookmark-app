package kotlinx.coroutines.flow;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

/* compiled from: Transform.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
final class FlowKt__TransformKt$runningReduce$1$1<T> implements FlowCollector {
    final /* synthetic */ Ref.ObjectRef<Object> $accumulator;
    final /* synthetic */ Function3<T, T, Continuation<? super T>, Object> $operation;
    final /* synthetic */ FlowCollector<T> $this_flow;

    /* JADX WARN: Multi-variable type inference failed */
    FlowKt__TransformKt$runningReduce$1$1(Ref.ObjectRef<Object> objectRef, Function3<? super T, ? super T, ? super Continuation<? super T>, ? extends Object> function3, FlowCollector<? super T> flowCollector) {
        this.$accumulator = objectRef;
        this.$operation = function3;
        this.$this_flow = flowCollector;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t, Continuation<? super Unit> continuation) {
        FlowKt__TransformKt$runningReduce$1$1$emit$1 flowKt__TransformKt$runningReduce$1$1$emit$1;
        FlowKt__TransformKt$runningReduce$1$1 flowKt__TransformKt$runningReduce$1$1;
        Ref.ObjectRef<Object> objectRef;
        T t2;
        Ref.ObjectRef<Object> objectRef2;
        FlowKt__TransformKt$runningReduce$1$1 flowKt__TransformKt$runningReduce$1$12;
        FlowCollector<T> flowCollector;
        T t3;
        if (continuation instanceof FlowKt__TransformKt$runningReduce$1$1$emit$1) {
            flowKt__TransformKt$runningReduce$1$1$emit$1 = (FlowKt__TransformKt$runningReduce$1$1$emit$1) continuation;
            if ((flowKt__TransformKt$runningReduce$1$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                flowKt__TransformKt$runningReduce$1$1$emit$1.label -= Integer.MIN_VALUE;
                Object obj = flowKt__TransformKt$runningReduce$1$1$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (flowKt__TransformKt$runningReduce$1$1$emit$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        flowKt__TransformKt$runningReduce$1$1 = this;
                        objectRef = flowKt__TransformKt$runningReduce$1$1.$accumulator;
                        if (flowKt__TransformKt$runningReduce$1$1.$accumulator.element != NullSurrogateKt.NULL) {
                            Function3<T, T, Continuation<? super T>, Object> function3 = flowKt__TransformKt$runningReduce$1$1.$operation;
                            T t4 = flowKt__TransformKt$runningReduce$1$1.$accumulator.element;
                            flowKt__TransformKt$runningReduce$1$1$emit$1.L$0 = flowKt__TransformKt$runningReduce$1$1;
                            flowKt__TransformKt$runningReduce$1$1$emit$1.L$1 = objectRef;
                            flowKt__TransformKt$runningReduce$1$1$emit$1.label = 1;
                            Object invoke = function3.invoke(t4, t, flowKt__TransformKt$runningReduce$1$1$emit$1);
                            if (invoke == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            t2 = invoke;
                            objectRef2 = objectRef;
                            flowKt__TransformKt$runningReduce$1$12 = flowKt__TransformKt$runningReduce$1$1;
                            FlowKt__TransformKt$runningReduce$1$1 flowKt__TransformKt$runningReduce$1$13 = flowKt__TransformKt$runningReduce$1$12;
                            objectRef = objectRef2;
                            t = t2;
                            flowKt__TransformKt$runningReduce$1$1 = flowKt__TransformKt$runningReduce$1$13;
                        }
                        objectRef.element = t;
                        flowCollector = flowKt__TransformKt$runningReduce$1$1.$this_flow;
                        t3 = flowKt__TransformKt$runningReduce$1$1.$accumulator.element;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.L$0 = null;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.L$1 = null;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.label = 2;
                        if (flowCollector.emit(t3, flowKt__TransformKt$runningReduce$1$1$emit$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    case 1:
                        objectRef2 = (Ref.ObjectRef) flowKt__TransformKt$runningReduce$1$1$emit$1.L$1;
                        FlowKt__TransformKt$runningReduce$1$1 flowKt__TransformKt$runningReduce$1$14 = (FlowKt__TransformKt$runningReduce$1$1) flowKt__TransformKt$runningReduce$1$1$emit$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        flowKt__TransformKt$runningReduce$1$12 = flowKt__TransformKt$runningReduce$1$14;
                        t2 = obj;
                        FlowKt__TransformKt$runningReduce$1$1 flowKt__TransformKt$runningReduce$1$132 = flowKt__TransformKt$runningReduce$1$12;
                        objectRef = objectRef2;
                        t = t2;
                        flowKt__TransformKt$runningReduce$1$1 = flowKt__TransformKt$runningReduce$1$132;
                        objectRef.element = t;
                        flowCollector = flowKt__TransformKt$runningReduce$1$1.$this_flow;
                        t3 = flowKt__TransformKt$runningReduce$1$1.$accumulator.element;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.L$0 = null;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.L$1 = null;
                        flowKt__TransformKt$runningReduce$1$1$emit$1.label = 2;
                        if (flowCollector.emit(t3, flowKt__TransformKt$runningReduce$1$1$emit$1) == coroutine_suspended) {
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
        flowKt__TransformKt$runningReduce$1$1$emit$1 = new FlowKt__TransformKt$runningReduce$1$1$emit$1(this, continuation);
        Object obj2 = flowKt__TransformKt$runningReduce$1$1$emit$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (flowKt__TransformKt$runningReduce$1$1$emit$1.label) {
        }
    }
}
