package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: DataStoreImpl.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0005\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "Landroidx/datastore/core/State;", "T", "", "locked"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$4", f = "DataStoreImpl.kt", i = {0, 1}, l = {306, 309}, m = "invokeSuspend", n = {"locked", "locked"}, s = {"Z$0", "Z$0"})
/* loaded from: classes.dex */
final class DataStoreImpl$readDataAndUpdateCache$4<T> extends SuspendLambda implements Function2<Boolean, Continuation<? super Pair<? extends State<T>, ? extends Boolean>>, Object> {
    final /* synthetic */ int $cachedVersion;
    Object L$0;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ DataStoreImpl<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DataStoreImpl$readDataAndUpdateCache$4(DataStoreImpl<T> dataStoreImpl, int i, Continuation<? super DataStoreImpl$readDataAndUpdateCache$4> continuation) {
        super(2, continuation);
        this.this$0 = dataStoreImpl;
        this.$cachedVersion = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DataStoreImpl$readDataAndUpdateCache$4 dataStoreImpl$readDataAndUpdateCache$4 = new DataStoreImpl$readDataAndUpdateCache$4(this.this$0, this.$cachedVersion, continuation);
        dataStoreImpl$readDataAndUpdateCache$4.Z$0 = ((Boolean) obj).booleanValue();
        return dataStoreImpl$readDataAndUpdateCache$4;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Boolean bool, Object obj) {
        return invoke(bool.booleanValue(), (Continuation) obj);
    }

    public final Object invoke(boolean z, Continuation<? super Pair<? extends State<T>, Boolean>> continuation) {
        return ((DataStoreImpl$readDataAndUpdateCache$4) create(Boolean.valueOf(z), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0092  */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v2, types: [androidx.datastore.core.DataStoreImpl$readDataAndUpdateCache$4] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r4v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ?? r4;
        int i;
        boolean z;
        Object obj2;
        boolean z2;
        boolean z3;
        DataStoreImpl$readDataAndUpdateCache$4<T> dataStoreImpl$readDataAndUpdateCache$4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Continuation<? super Integer> continuation = this.label;
        try {
        } catch (Throwable th) {
            th = th;
        }
        switch (continuation) {
            case 0:
                ResultKt.throwOnFailure(obj);
                boolean z4 = this.Z$0;
                DataStoreImpl<T> dataStoreImpl = this.this$0;
                boolean z5 = z4;
                this.Z$0 = z4;
                this.label = 1;
                Object readDataOrHandleCorruption = dataStoreImpl.readDataOrHandleCorruption(z5, this);
                if (readDataOrHandleCorruption == coroutine_suspended) {
                    return coroutine_suspended;
                }
                continuation = obj;
                obj = readDataOrHandleCorruption;
                z2 = z4;
                r4 = this;
                try {
                    obj = (State) obj;
                } catch (Throwable th2) {
                    boolean z6 = z2;
                    th = th2;
                    obj = continuation;
                    continuation = r4;
                    r4 = z6;
                    if (r4 != 0) {
                        continuation.L$0 = th;
                        continuation.Z$0 = r4;
                        continuation.label = 2;
                        Object version = continuation.this$0.getCoordinator().getVersion(continuation);
                        if (version == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj2 = obj;
                        obj = version;
                        dataStoreImpl$readDataAndUpdateCache$4 = continuation;
                        z3 = r4;
                        i = ((Number) obj).intValue();
                        z = z3;
                        obj = new ReadException(th, i);
                        z2 = z;
                        return TuplesKt.to(obj, Boxing.boxBoolean(z2));
                    }
                    i = continuation.$cachedVersion;
                    z = r4 == true ? 1 : 0;
                    obj = new ReadException(th, i);
                    z2 = z;
                    return TuplesKt.to(obj, Boxing.boxBoolean(z2));
                }
                return TuplesKt.to(obj, Boxing.boxBoolean(z2));
            case 1:
                boolean z7 = this.Z$0;
                ResultKt.throwOnFailure(obj);
                z2 = z7;
                r4 = this;
                continuation = obj;
                obj = (State) obj;
                return TuplesKt.to(obj, Boxing.boxBoolean(z2));
            case 2:
                boolean z8 = this.Z$0;
                Throwable th3 = (Throwable) this.L$0;
                ResultKt.throwOnFailure(obj);
                th = th3;
                z3 = z8;
                dataStoreImpl$readDataAndUpdateCache$4 = this;
                obj2 = obj;
                i = ((Number) obj).intValue();
                z = z3;
                obj = new ReadException(th, i);
                z2 = z;
                return TuplesKt.to(obj, Boxing.boxBoolean(z2));
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
