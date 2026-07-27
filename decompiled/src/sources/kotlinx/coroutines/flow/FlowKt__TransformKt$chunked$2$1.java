package kotlinx.coroutines.flow;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Ref;

/* compiled from: Transform.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes17.dex */
final class FlowKt__TransformKt$chunked$2$1<T> implements FlowCollector {
    final /* synthetic */ Ref.ObjectRef<ArrayList<T>> $result;
    final /* synthetic */ int $size;
    final /* synthetic */ FlowCollector<List<? extends T>> $this_flow;

    /* JADX WARN: Multi-variable type inference failed */
    FlowKt__TransformKt$chunked$2$1(Ref.ObjectRef<ArrayList<T>> objectRef, int i, FlowCollector<? super List<? extends T>> flowCollector) {
        this.$result = objectRef;
        this.$size = i;
        this.$this_flow = flowCollector;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object emit(T t, Continuation<? super Unit> continuation) {
        FlowKt__TransformKt$chunked$2$1$emit$1 flowKt__TransformKt$chunked$2$1$emit$1;
        FlowKt__TransformKt$chunked$2$1<T> flowKt__TransformKt$chunked$2$1;
        if (continuation instanceof FlowKt__TransformKt$chunked$2$1$emit$1) {
            flowKt__TransformKt$chunked$2$1$emit$1 = (FlowKt__TransformKt$chunked$2$1$emit$1) continuation;
            if ((flowKt__TransformKt$chunked$2$1$emit$1.label & Integer.MIN_VALUE) != 0) {
                flowKt__TransformKt$chunked$2$1$emit$1.label -= Integer.MIN_VALUE;
                Object obj = flowKt__TransformKt$chunked$2$1$emit$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (flowKt__TransformKt$chunked$2$1$emit$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        ArrayList arrayList = (T) ((ArrayList) ((ArrayList<T>) this.$result.element));
                        ArrayList arrayList2 = arrayList;
                        if (arrayList == null) {
                            T t2 = (T) new ArrayList(this.$size);
                            this.$result.element = t2;
                            arrayList2 = t2;
                        }
                        arrayList2.add(t);
                        if (arrayList2.size() == this.$size) {
                            FlowCollector<List<? extends T>> flowCollector = this.$this_flow;
                            flowKt__TransformKt$chunked$2$1$emit$1.L$0 = this;
                            flowKt__TransformKt$chunked$2$1$emit$1.label = 1;
                            if (flowCollector.emit(arrayList2, flowKt__TransformKt$chunked$2$1$emit$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            flowKt__TransformKt$chunked$2$1 = this;
                            flowKt__TransformKt$chunked$2$1.$result.element = null;
                        }
                        return Unit.INSTANCE;
                    case 1:
                        flowKt__TransformKt$chunked$2$1 = (FlowKt__TransformKt$chunked$2$1) flowKt__TransformKt$chunked$2$1$emit$1.L$0;
                        ResultKt.throwOnFailure(obj);
                        flowKt__TransformKt$chunked$2$1.$result.element = null;
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        flowKt__TransformKt$chunked$2$1$emit$1 = new FlowKt__TransformKt$chunked$2$1$emit$1(this, continuation);
        Object obj2 = flowKt__TransformKt$chunked$2$1$emit$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (flowKt__TransformKt$chunked$2$1$emit$1.label) {
        }
    }
}
