package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: DataStoreImpl.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Landroidx/datastore/core/Data;", "T", "locked", ""}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readDataOrHandleCorruption$2", f = "DataStoreImpl.kt", i = {0, 1}, l = {370, 371}, m = "invokeSuspend", n = {"locked", Constants.ScionAnalytics.MessageType.DATA_MESSAGE}, s = {"Z$0", "L$0"})
/* loaded from: classes.dex */
final class DataStoreImpl$readDataOrHandleCorruption$2<T> extends SuspendLambda implements Function2<Boolean, Continuation<? super Data<T>>, Object> {
    final /* synthetic */ int $preLockVersion;
    Object L$0;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ DataStoreImpl<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DataStoreImpl$readDataOrHandleCorruption$2(DataStoreImpl<T> dataStoreImpl, int i, Continuation<? super DataStoreImpl$readDataOrHandleCorruption$2> continuation) {
        super(2, continuation);
        this.this$0 = dataStoreImpl;
        this.$preLockVersion = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DataStoreImpl$readDataOrHandleCorruption$2 dataStoreImpl$readDataOrHandleCorruption$2 = new DataStoreImpl$readDataOrHandleCorruption$2(this.this$0, this.$preLockVersion, continuation);
        dataStoreImpl$readDataOrHandleCorruption$2.Z$0 = ((Boolean) obj).booleanValue();
        return dataStoreImpl$readDataOrHandleCorruption$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Boolean bool, Object obj) {
        return invoke(bool.booleanValue(), (Continuation) obj);
    }

    public final Object invoke(boolean z, Continuation<? super Data<T>> continuation) {
        return ((DataStoreImpl$readDataOrHandleCorruption$2) create(Boolean.valueOf(z), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0062  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object data) {
        Object readDataFromFileOrDefault;
        boolean locked;
        DataStoreImpl$readDataOrHandleCorruption$2 dataStoreImpl$readDataOrHandleCorruption$2;
        Object data2;
        int version;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(data);
                boolean locked2 = this.Z$0;
                this.Z$0 = locked2;
                this.label = 1;
                readDataFromFileOrDefault = this.this$0.readDataFromFileOrDefault(this);
                if (readDataFromFileOrDefault == coroutine_suspended) {
                    return coroutine_suspended;
                }
                data = readDataFromFileOrDefault;
                locked = locked2;
                dataStoreImpl$readDataOrHandleCorruption$2 = this;
                if (locked) {
                    int i = dataStoreImpl$readDataOrHandleCorruption$2.$preLockVersion;
                    data2 = data;
                    version = i;
                    return new Data(data2, data2 != null ? data2.hashCode() : 0, version);
                }
                dataStoreImpl$readDataOrHandleCorruption$2.L$0 = data;
                dataStoreImpl$readDataOrHandleCorruption$2.label = 2;
                Object version2 = dataStoreImpl$readDataOrHandleCorruption$2.this$0.getCoordinator().getVersion(dataStoreImpl$readDataOrHandleCorruption$2);
                if (version2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                data2 = data;
                data = version2;
                version = ((Number) data).intValue();
                return new Data(data2, data2 != null ? data2.hashCode() : 0, version);
            case 1:
                boolean locked3 = this.Z$0;
                ResultKt.throwOnFailure(data);
                locked = locked3;
                dataStoreImpl$readDataOrHandleCorruption$2 = this;
                if (locked) {
                }
                break;
            case 2:
                Object data3 = this.L$0;
                ResultKt.throwOnFailure(data);
                data2 = data3;
                version = ((Number) data).intValue();
                return new Data(data2, data2 != null ? data2.hashCode() : 0, version);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
