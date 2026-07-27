package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;

/* compiled from: DataStoreImpl.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u008a@"}, d2 = {"<anonymous>", "", "T"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$3", f = "DataStoreImpl.kt", i = {}, l = {387, 388, 390}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class DataStoreImpl$readDataOrHandleCorruption$3 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef<T> $newData;
    final /* synthetic */ Ref.IntRef $version;
    Object L$0;
    int label;
    final /* synthetic */ DataStoreImpl<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DataStoreImpl$readDataOrHandleCorruption$3(Ref.ObjectRef<T> objectRef, DataStoreImpl<T> dataStoreImpl, Ref.IntRef intRef, Continuation<? super DataStoreImpl$readDataOrHandleCorruption$3> continuation) {
        super(1, continuation);
        this.$newData = objectRef;
        this.this$0 = dataStoreImpl;
        this.$version = intRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new DataStoreImpl$readDataOrHandleCorruption$3(this.$newData, this.this$0, this.$version, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((DataStoreImpl$readDataOrHandleCorruption$3) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x006a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006b  */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        DataStoreImpl$readDataOrHandleCorruption$3 dataStoreImpl$readDataOrHandleCorruption$3;
        Object obj2;
        Ref.IntRef intRef;
        Object obj3;
        Object obj4;
        Object readDataFromFileOrDefault;
        Object obj5;
        T t;
        Ref.ObjectRef objectRef;
        Ref.IntRef intRef2;
        Object version;
        Ref.IntRef intRef3;
        Object obj6;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r1 = this.label;
        try {
        } catch (CorruptionException e) {
            dataStoreImpl$readDataOrHandleCorruption$3 = r1;
            obj2 = obj;
        }
        switch (r1) {
            case 0:
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef objectRef2 = this.$newData;
                this.L$0 = objectRef2;
                this.label = 1;
                readDataFromFileOrDefault = this.this$0.readDataFromFileOrDefault(this);
                if (readDataFromFileOrDefault == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj5 = obj;
                t = readDataFromFileOrDefault;
                objectRef = objectRef2;
                dataStoreImpl$readDataOrHandleCorruption$3 = this;
                try {
                    objectRef.element = t;
                    intRef2 = dataStoreImpl$readDataOrHandleCorruption$3.$version;
                    dataStoreImpl$readDataOrHandleCorruption$3.L$0 = intRef2;
                    dataStoreImpl$readDataOrHandleCorruption$3.label = 2;
                    version = dataStoreImpl$readDataOrHandleCorruption$3.this$0.getCoordinator().getVersion(dataStoreImpl$readDataOrHandleCorruption$3);
                } catch (CorruptionException e2) {
                    obj2 = obj5;
                    intRef = dataStoreImpl$readDataOrHandleCorruption$3.$version;
                    dataStoreImpl$readDataOrHandleCorruption$3.L$0 = intRef;
                    dataStoreImpl$readDataOrHandleCorruption$3.label = 3;
                    Object writeData$datastore_core_release = dataStoreImpl$readDataOrHandleCorruption$3.this$0.writeData$datastore_core_release(dataStoreImpl$readDataOrHandleCorruption$3.$newData.element, true, dataStoreImpl$readDataOrHandleCorruption$3);
                    if (writeData$datastore_core_release == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj3 = obj2;
                    obj4 = writeData$datastore_core_release;
                    intRef.element = ((Number) obj4).intValue();
                    return Unit.INSTANCE;
                }
                if (version != coroutine_suspended) {
                    return coroutine_suspended;
                }
                intRef3 = intRef2;
                obj6 = version;
                intRef3.element = ((Number) obj6).intValue();
                return Unit.INSTANCE;
            case 1:
                Ref.ObjectRef objectRef3 = (Ref.ObjectRef) this.L$0;
                ResultKt.throwOnFailure(obj);
                objectRef = objectRef3;
                dataStoreImpl$readDataOrHandleCorruption$3 = this;
                obj5 = obj;
                t = obj;
                objectRef.element = t;
                intRef2 = dataStoreImpl$readDataOrHandleCorruption$3.$version;
                dataStoreImpl$readDataOrHandleCorruption$3.L$0 = intRef2;
                dataStoreImpl$readDataOrHandleCorruption$3.label = 2;
                version = dataStoreImpl$readDataOrHandleCorruption$3.this$0.getCoordinator().getVersion(dataStoreImpl$readDataOrHandleCorruption$3);
                if (version != coroutine_suspended) {
                }
                break;
            case 2:
                Ref.IntRef intRef4 = (Ref.IntRef) this.L$0;
                ResultKt.throwOnFailure(obj);
                intRef3 = intRef4;
                dataStoreImpl$readDataOrHandleCorruption$3 = this;
                obj5 = obj;
                obj6 = obj;
                intRef3.element = ((Number) obj6).intValue();
                return Unit.INSTANCE;
            case 3:
                intRef = (Ref.IntRef) this.L$0;
                ResultKt.throwOnFailure(obj);
                obj3 = obj;
                obj4 = obj;
                intRef.element = ((Number) obj4).intValue();
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
