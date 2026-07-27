package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.datastore.core.DataStoreImpl;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: DataStoreImpl.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$incrementCollector$2$1", f = "DataStoreImpl.kt", i = {}, l = {134, 135}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class DataStoreImpl$incrementCollector$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ DataStoreImpl<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DataStoreImpl$incrementCollector$2$1(DataStoreImpl<T> dataStoreImpl, Continuation<? super DataStoreImpl$incrementCollector$2$1> continuation) {
        super(2, continuation);
        this.this$0 = dataStoreImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DataStoreImpl$incrementCollector$2$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DataStoreImpl$incrementCollector$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0055 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0056  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        DataStoreImpl$incrementCollector$2$1 dataStoreImpl$incrementCollector$2$1;
        DataStoreImpl.InitDataStore initDataStore;
        Flow conflate;
        final DataStoreImpl<T> dataStoreImpl;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                dataStoreImpl$incrementCollector$2$1 = this;
                initDataStore = ((DataStoreImpl) dataStoreImpl$incrementCollector$2$1.this$0).readAndInit;
                dataStoreImpl$incrementCollector$2$1.label = 1;
                if (initDataStore.awaitComplete(dataStoreImpl$incrementCollector$2$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                conflate = FlowKt.conflate(dataStoreImpl$incrementCollector$2$1.this$0.getCoordinator().getUpdateNotifications());
                dataStoreImpl = dataStoreImpl$incrementCollector$2$1.this$0;
                dataStoreImpl$incrementCollector$2$1.label = 2;
                if (conflate.collect(new FlowCollector() { // from class: androidx.datastore.core.DataStoreImpl$incrementCollector$2$1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                        return emit((Unit) value, (Continuation<? super Unit>) $completion);
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:3:0x000e, code lost:
                    
                        r1 = r1.readDataAndUpdateCache(true, r5);
                     */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    public final Object emit(Unit it, Continuation<? super Unit> continuation) {
                        Object readDataAndUpdateCache;
                        State currentState = ((DataStoreImpl) dataStoreImpl).inMemoryCache.getCurrentState();
                        return ((currentState instanceof Final) || readDataAndUpdateCache != IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : readDataAndUpdateCache;
                    }
                }, dataStoreImpl$incrementCollector$2$1) != coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            case 1:
                dataStoreImpl$incrementCollector$2$1 = this;
                ResultKt.throwOnFailure($result);
                conflate = FlowKt.conflate(dataStoreImpl$incrementCollector$2$1.this$0.getCoordinator().getUpdateNotifications());
                dataStoreImpl = dataStoreImpl$incrementCollector$2$1.this$0;
                dataStoreImpl$incrementCollector$2$1.label = 2;
                if (conflate.collect(new FlowCollector() { // from class: androidx.datastore.core.DataStoreImpl$incrementCollector$2$1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                        return emit((Unit) value, (Continuation<? super Unit>) $completion);
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:3:0x000e, code lost:
                    
                        r1 = r1.readDataAndUpdateCache(true, r5);
                     */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    public final Object emit(Unit it, Continuation<? super Unit> continuation) {
                        Object readDataAndUpdateCache;
                        State currentState = ((DataStoreImpl) dataStoreImpl).inMemoryCache.getCurrentState();
                        return ((currentState instanceof Final) || readDataAndUpdateCache != IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? Unit.INSTANCE : readDataAndUpdateCache;
                    }
                }, dataStoreImpl$incrementCollector$2$1) != coroutine_suspended) {
                }
                break;
            case 2:
                ResultKt.throwOnFailure($result);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
