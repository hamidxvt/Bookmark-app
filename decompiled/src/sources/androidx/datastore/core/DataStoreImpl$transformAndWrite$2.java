package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: DataStoreImpl.kt */
@Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001H\u008a@"}, d2 = {"<anonymous>", "T"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$transformAndWrite$2", f = "DataStoreImpl.kt", i = {1, 2}, l = {330, 331, 337}, m = "invokeSuspend", n = {"curData", "newData"}, s = {"L$0", "L$0"})
/* loaded from: classes.dex */
final class DataStoreImpl$transformAndWrite$2<T> extends SuspendLambda implements Function1<Continuation<? super T>, Object> {
    final /* synthetic */ CoroutineContext $callerContext;
    final /* synthetic */ Function2<T, Continuation<? super T>, Object> $transform;
    Object L$0;
    int label;
    final /* synthetic */ DataStoreImpl<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    DataStoreImpl$transformAndWrite$2(DataStoreImpl<T> dataStoreImpl, CoroutineContext coroutineContext, Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super DataStoreImpl$transformAndWrite$2> continuation) {
        super(1, continuation);
        this.this$0 = dataStoreImpl;
        this.$callerContext = coroutineContext;
        this.$transform = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new DataStoreImpl$transformAndWrite$2(this.this$0, this.$callerContext, this.$transform, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super T> continuation) {
        return ((DataStoreImpl$transformAndWrite$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0060  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        DataStoreImpl$transformAndWrite$2<T> dataStoreImpl$transformAndWrite$2;
        Object withContext;
        Data data;
        DataStoreImpl$transformAndWrite$2<T> dataStoreImpl$transformAndWrite$22;
        Object obj3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                Object readDataOrHandleCorruption = this.this$0.readDataOrHandleCorruption(true, this);
                if (readDataOrHandleCorruption == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj2 = obj;
                obj = readDataOrHandleCorruption;
                dataStoreImpl$transformAndWrite$2 = this;
                Data data2 = (Data) obj;
                dataStoreImpl$transformAndWrite$2.L$0 = data2;
                dataStoreImpl$transformAndWrite$2.label = 2;
                withContext = BuildersKt.withContext(dataStoreImpl$transformAndWrite$2.$callerContext, new DataStoreImpl$transformAndWrite$2$newData$1(dataStoreImpl$transformAndWrite$2.$transform, data2, null), dataStoreImpl$transformAndWrite$2);
                if (withContext != coroutine_suspended) {
                    return coroutine_suspended;
                }
                data = data2;
                obj = withContext;
                data.checkHashCode();
                if (!Intrinsics.areEqual(data.getValue(), obj)) {
                    return obj;
                }
                dataStoreImpl$transformAndWrite$2.L$0 = obj;
                dataStoreImpl$transformAndWrite$2.label = 3;
                if (((DataStoreImpl<T>) dataStoreImpl$transformAndWrite$2.this$0).writeData$datastore_core_release(obj, true, dataStoreImpl$transformAndWrite$2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                dataStoreImpl$transformAndWrite$22 = dataStoreImpl$transformAndWrite$2;
                Object obj4 = obj2;
                obj3 = obj;
                obj = obj4;
                return obj3;
            case 1:
                ResultKt.throwOnFailure(obj);
                dataStoreImpl$transformAndWrite$2 = this;
                obj2 = obj;
                Data data22 = (Data) obj;
                dataStoreImpl$transformAndWrite$2.L$0 = data22;
                dataStoreImpl$transformAndWrite$2.label = 2;
                withContext = BuildersKt.withContext(dataStoreImpl$transformAndWrite$2.$callerContext, new DataStoreImpl$transformAndWrite$2$newData$1(dataStoreImpl$transformAndWrite$2.$transform, data22, null), dataStoreImpl$transformAndWrite$2);
                if (withContext != coroutine_suspended) {
                }
                break;
            case 2:
                Data data3 = (Data) this.L$0;
                ResultKt.throwOnFailure(obj);
                data = data3;
                dataStoreImpl$transformAndWrite$2 = this;
                obj2 = obj;
                data.checkHashCode();
                if (!Intrinsics.areEqual(data.getValue(), obj)) {
                }
                break;
            case 3:
                dataStoreImpl$transformAndWrite$22 = this;
                obj3 = dataStoreImpl$transformAndWrite$22.L$0;
                ResultKt.throwOnFailure(obj);
                return obj3;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
