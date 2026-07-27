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
import kotlin.jvm.internal.Ref;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: DataStoreImpl.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "T", "Landroidx/datastore/core/WriteScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$writeData$2", f = "DataStoreImpl.kt", i = {0}, l = {352, 353}, m = "invokeSuspend", n = {"$this$writeScope"}, s = {"L$0"})
/* loaded from: classes.dex */
final class DataStoreImpl$writeData$2<T> extends SuspendLambda implements Function2<WriteScope<T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ T $newData;
    final /* synthetic */ Ref.IntRef $newVersion;
    final /* synthetic */ boolean $updateCache;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ DataStoreImpl<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DataStoreImpl$writeData$2(Ref.IntRef intRef, DataStoreImpl<T> dataStoreImpl, T t, boolean z, Continuation<? super DataStoreImpl$writeData$2> continuation) {
        super(2, continuation);
        this.$newVersion = intRef;
        this.this$0 = dataStoreImpl;
        this.$newData = t;
        this.$updateCache = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DataStoreImpl$writeData$2 dataStoreImpl$writeData$2 = new DataStoreImpl$writeData$2(this.$newVersion, this.this$0, this.$newData, this.$updateCache, continuation);
        dataStoreImpl$writeData$2.L$0 = obj;
        return dataStoreImpl$writeData$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(WriteScope<T> writeScope, Continuation<? super Unit> continuation) {
        return ((DataStoreImpl$writeData$2) create(writeScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x006a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0071  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        Object $result2;
        WriteScope $this$writeScope;
        Ref.IntRef intRef;
        DataStoreImpl$writeData$2 dataStoreImpl$writeData$2;
        DataStoreImpl$writeData$2 dataStoreImpl$writeData$22;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                WriteScope $this$writeScope2 = (WriteScope) this.L$0;
                Ref.IntRef intRef2 = this.$newVersion;
                this.L$0 = $this$writeScope2;
                this.L$1 = intRef2;
                this.label = 1;
                Object incrementAndGetVersion = this.this$0.getCoordinator().incrementAndGetVersion(this);
                if (incrementAndGetVersion == coroutine_suspended) {
                    return coroutine_suspended;
                }
                $result2 = $result;
                $result = incrementAndGetVersion;
                $this$writeScope = $this$writeScope2;
                intRef = intRef2;
                dataStoreImpl$writeData$2 = this;
                intRef.element = ((Number) $result).intValue();
                dataStoreImpl$writeData$2.L$0 = null;
                dataStoreImpl$writeData$2.L$1 = null;
                dataStoreImpl$writeData$2.label = 2;
                if ($this$writeScope.writeData(dataStoreImpl$writeData$2.$newData, dataStoreImpl$writeData$2) != coroutine_suspended) {
                    return coroutine_suspended;
                }
                dataStoreImpl$writeData$22 = dataStoreImpl$writeData$2;
                if (dataStoreImpl$writeData$22.$updateCache) {
                    DataStoreInMemoryCache dataStoreInMemoryCache = ((DataStoreImpl) dataStoreImpl$writeData$22.this$0).inMemoryCache;
                    T t = dataStoreImpl$writeData$22.$newData;
                    T t2 = dataStoreImpl$writeData$22.$newData;
                    dataStoreInMemoryCache.tryUpdate(new Data(t, t2 != null ? t2.hashCode() : 0, dataStoreImpl$writeData$22.$newVersion.element));
                }
                return Unit.INSTANCE;
            case 1:
                Ref.IntRef intRef3 = (Ref.IntRef) this.L$1;
                WriteScope $this$writeScope3 = (WriteScope) this.L$0;
                ResultKt.throwOnFailure($result);
                $this$writeScope = $this$writeScope3;
                intRef = intRef3;
                dataStoreImpl$writeData$2 = this;
                $result2 = $result;
                intRef.element = ((Number) $result).intValue();
                dataStoreImpl$writeData$2.L$0 = null;
                dataStoreImpl$writeData$2.L$1 = null;
                dataStoreImpl$writeData$2.label = 2;
                if ($this$writeScope.writeData(dataStoreImpl$writeData$2.$newData, dataStoreImpl$writeData$2) != coroutine_suspended) {
                }
                break;
            case 2:
                dataStoreImpl$writeData$22 = this;
                ResultKt.throwOnFailure($result);
                if (dataStoreImpl$writeData$22.$updateCache) {
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
