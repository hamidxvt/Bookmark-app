package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: DataStoreImpl.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "Landroidx/datastore/core/State;", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readState$2", f = "DataStoreImpl.kt", i = {}, l = {218, 226}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class DataStoreImpl$readState$2<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super State<T>>, Object> {
    final /* synthetic */ boolean $requireLock;
    int label;
    final /* synthetic */ DataStoreImpl<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DataStoreImpl$readState$2(DataStoreImpl<T> dataStoreImpl, boolean z, Continuation<? super DataStoreImpl$readState$2> continuation) {
        super(2, continuation);
        this.this$0 = dataStoreImpl;
        this.$requireLock = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DataStoreImpl$readState$2(this.this$0, this.$requireLock, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super State<T>> continuation) {
        return ((DataStoreImpl$readState$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x005e  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        DataStoreImpl$readState$2 dataStoreImpl$readState$2;
        Object readAndInitOrPropagateAndThrowFailure;
        Object readDataAndUpdateCache;
        Object $result2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    dataStoreImpl$readState$2 = this;
                    if (((DataStoreImpl) dataStoreImpl$readState$2.this$0).inMemoryCache.getCurrentState() instanceof Final) {
                        return ((DataStoreImpl) dataStoreImpl$readState$2.this$0).inMemoryCache.getCurrentState();
                    }
                    dataStoreImpl$readState$2.label = 1;
                    readAndInitOrPropagateAndThrowFailure = dataStoreImpl$readState$2.this$0.readAndInitOrPropagateAndThrowFailure(dataStoreImpl$readState$2);
                    if (readAndInitOrPropagateAndThrowFailure == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    dataStoreImpl$readState$2.label = 2;
                    readDataAndUpdateCache = dataStoreImpl$readState$2.this$0.readDataAndUpdateCache(dataStoreImpl$readState$2.$requireLock, dataStoreImpl$readState$2);
                    if (readDataAndUpdateCache != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    $result2 = $result;
                    $result = readDataAndUpdateCache;
                    return (State) $result;
                case 1:
                    dataStoreImpl$readState$2 = this;
                    ResultKt.throwOnFailure($result);
                    dataStoreImpl$readState$2.label = 2;
                    readDataAndUpdateCache = dataStoreImpl$readState$2.this$0.readDataAndUpdateCache(dataStoreImpl$readState$2.$requireLock, dataStoreImpl$readState$2);
                    if (readDataAndUpdateCache != coroutine_suspended) {
                    }
                    break;
                case 2:
                    ResultKt.throwOnFailure($result);
                    $result2 = $result;
                    return (State) $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable throwable) {
            return new ReadException(throwable, -1);
        }
    }
}
