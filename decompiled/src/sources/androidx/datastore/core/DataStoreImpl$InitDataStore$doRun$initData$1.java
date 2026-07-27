package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.datastore.core.DataStoreImpl;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: DataStoreImpl.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroidx/datastore/core/Data;", "T"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "androidx.datastore.core.DataStoreImpl$InitDataStore$doRun$initData$1", f = "DataStoreImpl.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2}, l = {437, 458, 546, 468}, m = "invokeSuspend", n = {"updateLock", "initializationComplete", "currentData", "updateLock", "initializationComplete", "currentData", "api", "initializationComplete", "currentData", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2"})
/* loaded from: classes.dex */
final class DataStoreImpl$InitDataStore$doRun$initData$1<T> extends SuspendLambda implements Function1<Continuation<? super Data<T>>, Object> {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ DataStoreImpl<T> this$0;
    final /* synthetic */ DataStoreImpl<T>.InitDataStore this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DataStoreImpl$InitDataStore$doRun$initData$1(DataStoreImpl<T> dataStoreImpl, DataStoreImpl<T>.InitDataStore initDataStore, Continuation<? super DataStoreImpl$InitDataStore$doRun$initData$1> continuation) {
        super(1, continuation);
        this.this$0 = dataStoreImpl;
        this.this$1 = initDataStore;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new DataStoreImpl$InitDataStore$doRun$initData$1(this.this$0, this.this$1, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Data<T>> continuation) {
        return ((DataStoreImpl$InitDataStore$doRun$initData$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0156 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x011d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00fc  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        DataStoreImpl$InitDataStore$doRun$initData$1<T> dataStoreImpl$InitDataStore$doRun$initData$1;
        Mutex mutex;
        Ref.BooleanRef booleanRef;
        Ref.ObjectRef objectRef;
        Object obj2;
        Object obj3;
        Ref.ObjectRef objectRef2;
        List list;
        Ref.BooleanRef booleanRef2;
        Ref.ObjectRef objectRef3;
        DataStoreImpl$InitDataStore$doRun$initData$1$api$1 dataStoreImpl$InitDataStore$doRun$initData$1$api$1;
        Object obj4;
        Ref.ObjectRef objectRef4;
        Iterator<T> it;
        Mutex mutex2;
        Mutex mutex3;
        Object obj5;
        Ref.BooleanRef booleanRef3;
        Object obj6;
        int hashCode;
        Object version;
        Object obj7;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                dataStoreImpl$InitDataStore$doRun$initData$1 = this;
                Mutex Mutex$default = MutexKt.Mutex$default(false, 1, null);
                Ref.BooleanRef booleanRef4 = new Ref.BooleanRef();
                Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
                dataStoreImpl$InitDataStore$doRun$initData$1.L$0 = Mutex$default;
                dataStoreImpl$InitDataStore$doRun$initData$1.L$1 = booleanRef4;
                dataStoreImpl$InitDataStore$doRun$initData$1.L$2 = objectRef5;
                dataStoreImpl$InitDataStore$doRun$initData$1.L$3 = objectRef5;
                dataStoreImpl$InitDataStore$doRun$initData$1.label = 1;
                Object readDataOrHandleCorruption = dataStoreImpl$InitDataStore$doRun$initData$1.this$0.readDataOrHandleCorruption(true, dataStoreImpl$InitDataStore$doRun$initData$1);
                if (readDataOrHandleCorruption == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutex = Mutex$default;
                booleanRef = booleanRef4;
                objectRef = objectRef5;
                obj2 = obj;
                obj3 = readDataOrHandleCorruption;
                objectRef2 = objectRef;
                objectRef2.element = (T) ((Data) obj3).getValue();
                DataStoreImpl$InitDataStore$doRun$initData$1$api$1 dataStoreImpl$InitDataStore$doRun$initData$1$api$12 = new DataStoreImpl$InitDataStore$doRun$initData$1$api$1(mutex, booleanRef, objectRef, dataStoreImpl$InitDataStore$doRun$initData$1.this$0);
                list = ((DataStoreImpl.InitDataStore) dataStoreImpl$InitDataStore$doRun$initData$1.this$1).initTasks;
                if (list == null) {
                    Ref.BooleanRef booleanRef5 = booleanRef;
                    dataStoreImpl$InitDataStore$doRun$initData$1$api$1 = dataStoreImpl$InitDataStore$doRun$initData$1$api$12;
                    obj4 = obj2;
                    booleanRef2 = booleanRef5;
                    Mutex mutex4 = mutex;
                    objectRef4 = objectRef;
                    it = list.iterator();
                    mutex2 = mutex4;
                    while (it.hasNext()) {
                        Function2 function2 = (Function2) it.next();
                        dataStoreImpl$InitDataStore$doRun$initData$1.L$0 = mutex2;
                        dataStoreImpl$InitDataStore$doRun$initData$1.L$1 = booleanRef2;
                        dataStoreImpl$InitDataStore$doRun$initData$1.L$2 = objectRef4;
                        dataStoreImpl$InitDataStore$doRun$initData$1.L$3 = dataStoreImpl$InitDataStore$doRun$initData$1$api$1;
                        dataStoreImpl$InitDataStore$doRun$initData$1.L$4 = it;
                        dataStoreImpl$InitDataStore$doRun$initData$1.label = 2;
                        if (function2.invoke(dataStoreImpl$InitDataStore$doRun$initData$1$api$1, dataStoreImpl$InitDataStore$doRun$initData$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    obj2 = obj4;
                    objectRef3 = objectRef4;
                    mutex = mutex2;
                    ((DataStoreImpl.InitDataStore) dataStoreImpl$InitDataStore$doRun$initData$1.this$1).initTasks = null;
                    mutex3 = mutex;
                    obj5 = null;
                    dataStoreImpl$InitDataStore$doRun$initData$1.L$0 = booleanRef2;
                    dataStoreImpl$InitDataStore$doRun$initData$1.L$1 = objectRef3;
                    dataStoreImpl$InitDataStore$doRun$initData$1.L$2 = mutex3;
                    dataStoreImpl$InitDataStore$doRun$initData$1.L$3 = null;
                    dataStoreImpl$InitDataStore$doRun$initData$1.L$4 = null;
                    dataStoreImpl$InitDataStore$doRun$initData$1.label = 3;
                    if (mutex3.lock(null, dataStoreImpl$InitDataStore$doRun$initData$1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    booleanRef3 = booleanRef2;
                    try {
                        booleanRef3.element = true;
                        Unit unit = Unit.INSTANCE;
                        mutex3.unlock(obj5);
                        obj6 = objectRef3.element;
                        T t = objectRef3.element;
                        hashCode = t != null ? t.hashCode() : 0;
                        dataStoreImpl$InitDataStore$doRun$initData$1.L$0 = obj6;
                        dataStoreImpl$InitDataStore$doRun$initData$1.L$1 = null;
                        dataStoreImpl$InitDataStore$doRun$initData$1.L$2 = null;
                        dataStoreImpl$InitDataStore$doRun$initData$1.I$0 = hashCode;
                        dataStoreImpl$InitDataStore$doRun$initData$1.label = 4;
                        version = dataStoreImpl$InitDataStore$doRun$initData$1.this$0.getCoordinator().getVersion(dataStoreImpl$InitDataStore$doRun$initData$1);
                        if (version != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj7 = version;
                        return new Data(obj6, hashCode, ((Number) obj7).intValue());
                    } catch (Throwable th) {
                        mutex3.unlock(obj5);
                        throw th;
                    }
                }
                booleanRef2 = booleanRef;
                objectRef3 = objectRef;
                ((DataStoreImpl.InitDataStore) dataStoreImpl$InitDataStore$doRun$initData$1.this$1).initTasks = null;
                mutex3 = mutex;
                obj5 = null;
                dataStoreImpl$InitDataStore$doRun$initData$1.L$0 = booleanRef2;
                dataStoreImpl$InitDataStore$doRun$initData$1.L$1 = objectRef3;
                dataStoreImpl$InitDataStore$doRun$initData$1.L$2 = mutex3;
                dataStoreImpl$InitDataStore$doRun$initData$1.L$3 = null;
                dataStoreImpl$InitDataStore$doRun$initData$1.L$4 = null;
                dataStoreImpl$InitDataStore$doRun$initData$1.label = 3;
                if (mutex3.lock(null, dataStoreImpl$InitDataStore$doRun$initData$1) == coroutine_suspended) {
                }
            case 1:
                dataStoreImpl$InitDataStore$doRun$initData$1 = this;
                obj3 = obj;
                Ref.ObjectRef objectRef6 = (Ref.ObjectRef) dataStoreImpl$InitDataStore$doRun$initData$1.L$3;
                Ref.ObjectRef objectRef7 = (Ref.ObjectRef) dataStoreImpl$InitDataStore$doRun$initData$1.L$2;
                Ref.BooleanRef booleanRef6 = (Ref.BooleanRef) dataStoreImpl$InitDataStore$doRun$initData$1.L$1;
                Mutex mutex5 = (Mutex) dataStoreImpl$InitDataStore$doRun$initData$1.L$0;
                ResultKt.throwOnFailure(obj3);
                mutex = mutex5;
                booleanRef = booleanRef6;
                objectRef = objectRef7;
                objectRef2 = objectRef6;
                obj2 = obj3;
                objectRef2.element = (T) ((Data) obj3).getValue();
                DataStoreImpl$InitDataStore$doRun$initData$1$api$1 dataStoreImpl$InitDataStore$doRun$initData$1$api$122 = new DataStoreImpl$InitDataStore$doRun$initData$1$api$1(mutex, booleanRef, objectRef, dataStoreImpl$InitDataStore$doRun$initData$1.this$0);
                list = ((DataStoreImpl.InitDataStore) dataStoreImpl$InitDataStore$doRun$initData$1.this$1).initTasks;
                if (list == null) {
                }
                break;
            case 2:
                dataStoreImpl$InitDataStore$doRun$initData$1 = this;
                obj4 = obj;
                it = (Iterator) dataStoreImpl$InitDataStore$doRun$initData$1.L$4;
                dataStoreImpl$InitDataStore$doRun$initData$1$api$1 = (DataStoreImpl$InitDataStore$doRun$initData$1$api$1) dataStoreImpl$InitDataStore$doRun$initData$1.L$3;
                objectRef4 = (Ref.ObjectRef) dataStoreImpl$InitDataStore$doRun$initData$1.L$2;
                booleanRef2 = (Ref.BooleanRef) dataStoreImpl$InitDataStore$doRun$initData$1.L$1;
                mutex2 = (Mutex) dataStoreImpl$InitDataStore$doRun$initData$1.L$0;
                ResultKt.throwOnFailure(obj4);
                while (it.hasNext()) {
                }
                obj2 = obj4;
                objectRef3 = objectRef4;
                mutex = mutex2;
                ((DataStoreImpl.InitDataStore) dataStoreImpl$InitDataStore$doRun$initData$1.this$1).initTasks = null;
                mutex3 = mutex;
                obj5 = null;
                dataStoreImpl$InitDataStore$doRun$initData$1.L$0 = booleanRef2;
                dataStoreImpl$InitDataStore$doRun$initData$1.L$1 = objectRef3;
                dataStoreImpl$InitDataStore$doRun$initData$1.L$2 = mutex3;
                dataStoreImpl$InitDataStore$doRun$initData$1.L$3 = null;
                dataStoreImpl$InitDataStore$doRun$initData$1.L$4 = null;
                dataStoreImpl$InitDataStore$doRun$initData$1.label = 3;
                if (mutex3.lock(null, dataStoreImpl$InitDataStore$doRun$initData$1) == coroutine_suspended) {
                }
                break;
            case 3:
                dataStoreImpl$InitDataStore$doRun$initData$1 = this;
                obj5 = null;
                mutex3 = (Mutex) dataStoreImpl$InitDataStore$doRun$initData$1.L$2;
                objectRef3 = (Ref.ObjectRef) dataStoreImpl$InitDataStore$doRun$initData$1.L$1;
                booleanRef3 = (Ref.BooleanRef) dataStoreImpl$InitDataStore$doRun$initData$1.L$0;
                ResultKt.throwOnFailure(obj);
                booleanRef3.element = true;
                Unit unit2 = Unit.INSTANCE;
                mutex3.unlock(obj5);
                obj6 = objectRef3.element;
                T t2 = objectRef3.element;
                if (t2 != null) {
                }
                dataStoreImpl$InitDataStore$doRun$initData$1.L$0 = obj6;
                dataStoreImpl$InitDataStore$doRun$initData$1.L$1 = null;
                dataStoreImpl$InitDataStore$doRun$initData$1.L$2 = null;
                dataStoreImpl$InitDataStore$doRun$initData$1.I$0 = hashCode;
                dataStoreImpl$InitDataStore$doRun$initData$1.label = 4;
                version = dataStoreImpl$InitDataStore$doRun$initData$1.this$0.getCoordinator().getVersion(dataStoreImpl$InitDataStore$doRun$initData$1);
                if (version != coroutine_suspended) {
                }
                break;
            case 4:
                obj7 = obj;
                hashCode = this.I$0;
                obj6 = this.L$0;
                ResultKt.throwOnFailure(obj7);
                return new Data(obj6, hashCode, ((Number) obj7).intValue());
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
