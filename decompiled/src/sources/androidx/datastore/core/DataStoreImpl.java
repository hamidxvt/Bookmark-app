package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.datastore.core.Message;
import androidx.datastore.core.UpdatingDataContextElement;
import androidx.datastore.core.handlers.NoOpCorruptionHandler;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.messaging.Constants;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.ExceptionsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: DataStoreImpl.kt */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u0000 V*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002VWBn\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012?\b\u0002\u0010\u0005\u001a9\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00070\u0006\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\u000e\u00103\u001a\u00020\rH\u0082@¢\u0006\u0002\u00104JG\u00105\u001a\u0002H6\"\u0004\b\u0001\u001062\u0006\u00107\u001a\u0002082\u001c\u00109\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H60\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0:H\u0082@\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010;J\u001c\u0010<\u001a\u00020\r2\f\u0010=\u001a\b\u0012\u0004\u0012\u00028\u000002H\u0082@¢\u0006\u0002\u0010>J\u000e\u0010?\u001a\u00020\rH\u0082@¢\u0006\u0002\u00104J\u000e\u0010@\u001a\u00020\rH\u0082@¢\u0006\u0002\u00104J\u001c\u0010A\u001a\b\u0012\u0004\u0012\u00028\u00000B2\u0006\u0010C\u001a\u000208H\u0082@¢\u0006\u0002\u0010DJ\u000e\u0010E\u001a\u00028\u0000H\u0082@¢\u0006\u0002\u00104J\u001c\u0010F\u001a\b\u0012\u0004\u0012\u00028\u00000G2\u0006\u00107\u001a\u000208H\u0082@¢\u0006\u0002\u0010DJ\u001c\u0010H\u001a\b\u0012\u0004\u0012\u00028\u00000B2\u0006\u0010C\u001a\u000208H\u0082@¢\u0006\u0002\u0010DJI\u0010I\u001a\u00028\u000021\u0010J\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(K\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00072\u0006\u0010L\u001a\u00020MH\u0082@¢\u0006\u0002\u0010NJA\u0010O\u001a\u00028\u000021\u0010J\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(K\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0007H\u0096@¢\u0006\u0002\u0010PJ \u0010Q\u001a\u00020\u00152\u0006\u0010R\u001a\u00028\u00002\u0006\u0010S\u001a\u000208H\u0080@¢\u0006\u0004\bT\u0010UR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000!X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010&\u001a\f0'R\b\u0012\u0004\u0012\u00028\u00000\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000)8@X\u0080\u0084\u0002¢\u0006\f\u001a\u0004\b,\u0010-*\u0004\b*\u0010+R\u001a\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000)0/X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000201X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006X"}, d2 = {"Landroidx/datastore/core/DataStoreImpl;", "T", "Landroidx/datastore/core/DataStore;", "storage", "Landroidx/datastore/core/Storage;", "initTasksList", "", "Lkotlin/Function2;", "Landroidx/datastore/core/InitializerApi;", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "api", "Lkotlin/coroutines/Continuation;", "", "", "corruptionHandler", "Landroidx/datastore/core/CorruptionHandler;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroidx/datastore/core/Storage;Ljava/util/List;Landroidx/datastore/core/CorruptionHandler;Lkotlinx/coroutines/CoroutineScope;)V", "collectorCounter", "", "collectorJob", "Lkotlinx/coroutines/Job;", "collectorMutex", "Lkotlinx/coroutines/sync/Mutex;", "coordinator", "Landroidx/datastore/core/InterProcessCoordinator;", "getCoordinator", "()Landroidx/datastore/core/InterProcessCoordinator;", "coordinator$delegate", "Lkotlin/Lazy;", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "Lkotlinx/coroutines/flow/Flow;", "getData", "()Lkotlinx/coroutines/flow/Flow;", "inMemoryCache", "Landroidx/datastore/core/DataStoreInMemoryCache;", "readAndInit", "Landroidx/datastore/core/DataStoreImpl$InitDataStore;", "storageConnection", "Landroidx/datastore/core/StorageConnection;", "getStorageConnection$datastore_core_release$delegate", "(Landroidx/datastore/core/DataStoreImpl;)Ljava/lang/Object;", "getStorageConnection$datastore_core_release", "()Landroidx/datastore/core/StorageConnection;", "storageConnectionDelegate", "Lkotlin/Lazy;", "writeActor", "Landroidx/datastore/core/SimpleActor;", "Landroidx/datastore/core/Message$Update;", "decrementCollector", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doWithWriteFileLock", "R", "hasWriteFileLock", "", "block", "Lkotlin/Function1;", "(ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleUpdate", "update", "(Landroidx/datastore/core/Message$Update;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "incrementCollector", "readAndInitOrPropagateAndThrowFailure", "readDataAndUpdateCache", "Landroidx/datastore/core/State;", "requireLock", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readDataFromFileOrDefault", "readDataOrHandleCorruption", "Landroidx/datastore/core/Data;", "readState", "transformAndWrite", "transform", "t", "callerContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateData", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeData", "newData", "updateCache", "writeData$datastore_core_release", "(Ljava/lang/Object;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "InitDataStore", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class DataStoreImpl<T> implements DataStore<T> {
    private static final String BUG_MESSAGE = "This is a bug in DataStore. Please file a bug at: https://issuetracker.google.com/issues/new?component=907884&template=1466542";
    private int collectorCounter;
    private Job collectorJob;
    private final Mutex collectorMutex;

    /* renamed from: coordinator$delegate, reason: from kotlin metadata */
    private final Lazy coordinator;
    private final CorruptionHandler<T> corruptionHandler;
    private final Flow<T> data;
    private final DataStoreInMemoryCache<T> inMemoryCache;
    private final DataStoreImpl<T>.InitDataStore readAndInit;
    private final CoroutineScope scope;
    private final Storage<T> storage;
    private final Lazy<StorageConnection<T>> storageConnectionDelegate;
    private final SimpleActor<Message.Update<T>> writeActor;

    public DataStoreImpl(Storage<T> storage, List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> initTasksList, CorruptionHandler<T> corruptionHandler, CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(storage, "storage");
        Intrinsics.checkNotNullParameter(initTasksList, "initTasksList");
        Intrinsics.checkNotNullParameter(corruptionHandler, "corruptionHandler");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.storage = storage;
        this.corruptionHandler = corruptionHandler;
        this.scope = scope;
        this.data = FlowKt.flow(new DataStoreImpl$data$1(this, null));
        this.collectorMutex = MutexKt.Mutex$default(false, 1, null);
        this.inMemoryCache = new DataStoreInMemoryCache<>();
        this.readAndInit = new InitDataStore(this, initTasksList);
        this.storageConnectionDelegate = LazyKt.lazy(new Function0<StorageConnection<T>>(this) { // from class: androidx.datastore.core.DataStoreImpl$storageConnectionDelegate$1
            final /* synthetic */ DataStoreImpl<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final StorageConnection<T> invoke() {
                Storage storage2;
                storage2 = ((DataStoreImpl) this.this$0).storage;
                return storage2.createConnection();
            }
        });
        this.coordinator = LazyKt.lazy(new Function0<InterProcessCoordinator>(this) { // from class: androidx.datastore.core.DataStoreImpl$coordinator$2
            final /* synthetic */ DataStoreImpl<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final InterProcessCoordinator invoke() {
                return this.this$0.getStorageConnection$datastore_core_release().getCoordinator();
            }
        });
        this.writeActor = new SimpleActor<>(this.scope, new Function1<Throwable, Unit>(this) { // from class: androidx.datastore.core.DataStoreImpl$writeActor$1
            final /* synthetic */ DataStoreImpl<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable it) {
                Lazy lazy;
                if (it != null) {
                    ((DataStoreImpl) this.this$0).inMemoryCache.tryUpdate(new Final(it));
                }
                lazy = ((DataStoreImpl) this.this$0).storageConnectionDelegate;
                if (lazy.isInitialized()) {
                    this.this$0.getStorageConnection$datastore_core_release().close();
                }
            }
        }, new Function2<Message.Update<T>, Throwable, Unit>() { // from class: androidx.datastore.core.DataStoreImpl$writeActor$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Object p1, Throwable th) {
                invoke((Message.Update) p1, th);
                return Unit.INSTANCE;
            }

            public final void invoke(Message.Update<T> msg, Throwable ex) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                msg.getAck().completeExceptionally(ex == null ? new CancellationException("DataStore scope was cancelled before updateData could complete") : ex);
            }
        }, new DataStoreImpl$writeActor$3(this, null));
    }

    public /* synthetic */ DataStoreImpl(Storage storage, List list, NoOpCorruptionHandler noOpCorruptionHandler, CoroutineScope coroutineScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(storage, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? new NoOpCorruptionHandler() : noOpCorruptionHandler, (i & 8) != 0 ? CoroutineScopeKt.CoroutineScope(Actual_jvmKt.ioDispatcher().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null))) : coroutineScope);
    }

    @Override // androidx.datastore.core.DataStore
    public Flow<T> getData() {
        return this.data;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005f A[Catch: all -> 0x007f, TryCatch #0 {all -> 0x007f, blocks: (B:14:0x0055, B:16:0x005f, B:17:0x0074), top: B:13:0x0055 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object incrementCollector(Continuation<? super Unit> continuation) {
        DataStoreImpl$incrementCollector$1 dataStoreImpl$incrementCollector$1;
        DataStoreImpl$incrementCollector$1 dataStoreImpl$incrementCollector$12;
        DataStoreImpl dataStoreImpl;
        Mutex $this$withLock_u24default$iv;
        Object owner$iv;
        Job launch$default;
        try {
            if (continuation instanceof DataStoreImpl$incrementCollector$1) {
                dataStoreImpl$incrementCollector$1 = (DataStoreImpl$incrementCollector$1) continuation;
                if ((dataStoreImpl$incrementCollector$1.label & Integer.MIN_VALUE) != 0) {
                    dataStoreImpl$incrementCollector$1.label -= Integer.MIN_VALUE;
                    dataStoreImpl$incrementCollector$12 = dataStoreImpl$incrementCollector$1;
                    Object $result = dataStoreImpl$incrementCollector$12.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (dataStoreImpl$incrementCollector$12.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            dataStoreImpl = this;
                            $this$withLock_u24default$iv = dataStoreImpl.collectorMutex;
                            owner$iv = null;
                            dataStoreImpl$incrementCollector$12.L$0 = dataStoreImpl;
                            dataStoreImpl$incrementCollector$12.L$1 = $this$withLock_u24default$iv;
                            dataStoreImpl$incrementCollector$12.label = 1;
                            if ($this$withLock_u24default$iv.lock(null, dataStoreImpl$incrementCollector$12) != coroutine_suspended) {
                                break;
                            } else {
                                return coroutine_suspended;
                            }
                        case 1:
                            owner$iv = null;
                            $this$withLock_u24default$iv = (Mutex) dataStoreImpl$incrementCollector$12.L$1;
                            dataStoreImpl = (DataStoreImpl) dataStoreImpl$incrementCollector$12.L$0;
                            ResultKt.throwOnFailure($result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    dataStoreImpl.collectorCounter++;
                    if (dataStoreImpl.collectorCounter == 1) {
                        launch$default = BuildersKt__Builders_commonKt.launch$default(dataStoreImpl.scope, null, null, new DataStoreImpl$incrementCollector$2$1(dataStoreImpl, null), 3, null);
                        dataStoreImpl.collectorJob = launch$default;
                    }
                    Unit unit = Unit.INSTANCE;
                    $this$withLock_u24default$iv.unlock(owner$iv);
                    return Unit.INSTANCE;
                }
            }
            dataStoreImpl.collectorCounter++;
            if (dataStoreImpl.collectorCounter == 1) {
            }
            Unit unit2 = Unit.INSTANCE;
            $this$withLock_u24default$iv.unlock(owner$iv);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            $this$withLock_u24default$iv.unlock(owner$iv);
            throw th;
        }
        dataStoreImpl$incrementCollector$1 = new DataStoreImpl$incrementCollector$1(this, continuation);
        dataStoreImpl$incrementCollector$12 = dataStoreImpl$incrementCollector$1;
        Object $result2 = dataStoreImpl$incrementCollector$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (dataStoreImpl$incrementCollector$12.label) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005f A[Catch: all -> 0x0074, TryCatch #0 {all -> 0x0074, blocks: (B:14:0x0055, B:16:0x005f, B:18:0x0064, B:19:0x0067, B:20:0x0069), top: B:13:0x0055 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object decrementCollector(Continuation<? super Unit> continuation) {
        DataStoreImpl$decrementCollector$1 dataStoreImpl$decrementCollector$1;
        DataStoreImpl$decrementCollector$1 dataStoreImpl$decrementCollector$12;
        DataStoreImpl dataStoreImpl;
        Mutex $this$withLock_u24default$iv;
        Object owner$iv;
        try {
            if (continuation instanceof DataStoreImpl$decrementCollector$1) {
                dataStoreImpl$decrementCollector$1 = (DataStoreImpl$decrementCollector$1) continuation;
                if ((dataStoreImpl$decrementCollector$1.label & Integer.MIN_VALUE) != 0) {
                    dataStoreImpl$decrementCollector$1.label -= Integer.MIN_VALUE;
                    dataStoreImpl$decrementCollector$12 = dataStoreImpl$decrementCollector$1;
                    Object $result = dataStoreImpl$decrementCollector$12.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (dataStoreImpl$decrementCollector$12.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            dataStoreImpl = this;
                            $this$withLock_u24default$iv = dataStoreImpl.collectorMutex;
                            owner$iv = null;
                            dataStoreImpl$decrementCollector$12.L$0 = dataStoreImpl;
                            dataStoreImpl$decrementCollector$12.L$1 = $this$withLock_u24default$iv;
                            dataStoreImpl$decrementCollector$12.label = 1;
                            if ($this$withLock_u24default$iv.lock(null, dataStoreImpl$decrementCollector$12) != coroutine_suspended) {
                                break;
                            } else {
                                return coroutine_suspended;
                            }
                        case 1:
                            owner$iv = null;
                            $this$withLock_u24default$iv = (Mutex) dataStoreImpl$decrementCollector$12.L$1;
                            dataStoreImpl = (DataStoreImpl) dataStoreImpl$decrementCollector$12.L$0;
                            ResultKt.throwOnFailure($result);
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    dataStoreImpl.collectorCounter--;
                    if (dataStoreImpl.collectorCounter == 0) {
                        Job job = dataStoreImpl.collectorJob;
                        if (job != null) {
                            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                        }
                        dataStoreImpl.collectorJob = null;
                    }
                    Unit unit = Unit.INSTANCE;
                    $this$withLock_u24default$iv.unlock(owner$iv);
                    return Unit.INSTANCE;
                }
            }
            dataStoreImpl.collectorCounter--;
            if (dataStoreImpl.collectorCounter == 0) {
            }
            Unit unit2 = Unit.INSTANCE;
            $this$withLock_u24default$iv.unlock(owner$iv);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            $this$withLock_u24default$iv.unlock(owner$iv);
            throw th;
        }
        dataStoreImpl$decrementCollector$1 = new DataStoreImpl$decrementCollector$1(this, continuation);
        dataStoreImpl$decrementCollector$12 = dataStoreImpl$decrementCollector$1;
        Object $result2 = dataStoreImpl$decrementCollector$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (dataStoreImpl$decrementCollector$12.label) {
        }
    }

    @Override // androidx.datastore.core.DataStore
    public Object updateData(Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        UpdatingDataContextElement parentContextElement = (UpdatingDataContextElement) continuation.getContext().get(UpdatingDataContextElement.Companion.Key.INSTANCE);
        if (parentContextElement != null) {
            parentContextElement.checkNotUpdating(this);
        }
        UpdatingDataContextElement childContextElement = new UpdatingDataContextElement(parentContextElement, this);
        return BuildersKt.withContext(childContextElement, new DataStoreImpl$updateData$2(this, function2, null), continuation);
    }

    public final StorageConnection<T> getStorageConnection$datastore_core_release() {
        return this.storageConnectionDelegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final InterProcessCoordinator getCoordinator() {
        return (InterProcessCoordinator) this.coordinator.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object readState(boolean requireLock, Continuation<? super State<T>> continuation) {
        return BuildersKt.withContext(this.scope.getCoroutineContext(), new DataStoreImpl$readState$2(this, requireLock, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(8:0|1|(2:3|(4:5|6|7|8))|65|6|7|8|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0058, code lost:
    
        r10 = th;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /* JADX WARN: Type inference failed for: r1v3, types: [kotlinx.coroutines.CompletableDeferred] */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object handleUpdate(Message.Update<T> update, Continuation<? super Unit> continuation) {
        DataStoreImpl$handleUpdate$1 dataStoreImpl$handleUpdate$1;
        DataStoreImpl$handleUpdate$1 dataStoreImpl$handleUpdate$12;
        Object m569constructorimpl;
        ?? r1;
        Message.Update<T> update2;
        DataStoreImpl dataStoreImpl;
        State<T> currentState;
        boolean z;
        CompletableDeferred<T> completableDeferred;
        Object transformAndWrite;
        CompletableDeferred<T> completableDeferred2;
        Object obj;
        Object transformAndWrite2;
        CompletableDeferred<T> completableDeferred3;
        if (continuation instanceof DataStoreImpl$handleUpdate$1) {
            dataStoreImpl$handleUpdate$1 = (DataStoreImpl$handleUpdate$1) continuation;
            if ((dataStoreImpl$handleUpdate$1.label & Integer.MIN_VALUE) != 0) {
                dataStoreImpl$handleUpdate$1.label -= Integer.MIN_VALUE;
                dataStoreImpl$handleUpdate$12 = dataStoreImpl$handleUpdate$1;
                Object obj2 = dataStoreImpl$handleUpdate$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (dataStoreImpl$handleUpdate$12.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj2);
                        update2 = update;
                        CompletableDeferred<T> ack = update2.getAck();
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            dataStoreImpl = this;
                            currentState = dataStoreImpl.inMemoryCache.getCurrentState();
                            z = true;
                        } catch (Throwable th) {
                            coroutine_suspended = ack;
                            th = th;
                            Result.Companion companion2 = Result.INSTANCE;
                            m569constructorimpl = Result.m569constructorimpl(ResultKt.createFailure(th));
                            r1 = coroutine_suspended;
                            CompletableDeferredKt.completeWith(r1, m569constructorimpl);
                            return Unit.INSTANCE;
                        }
                        if (currentState instanceof Data) {
                            Function2<T, Continuation<? super T>, Object> transform = update2.getTransform();
                            CoroutineContext callerContext = update2.getCallerContext();
                            dataStoreImpl$handleUpdate$12.L$0 = ack;
                            dataStoreImpl$handleUpdate$12.label = 1;
                            transformAndWrite = dataStoreImpl.transformAndWrite(transform, callerContext, dataStoreImpl$handleUpdate$12);
                            if (transformAndWrite == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            completableDeferred2 = ack;
                            obj = transformAndWrite;
                            coroutine_suspended = completableDeferred2;
                            m569constructorimpl = Result.m569constructorimpl(obj);
                            r1 = coroutine_suspended;
                            CompletableDeferredKt.completeWith(r1, m569constructorimpl);
                            return Unit.INSTANCE;
                        }
                        if (!(currentState instanceof ReadException)) {
                            z = currentState instanceof UnInitialized;
                        }
                        if (!z) {
                            if (currentState instanceof Final) {
                                throw ((Final) currentState).getFinalException();
                            }
                            throw new NoWhenBranchMatchedException();
                        }
                        if (currentState != update2.getLastState()) {
                            Intrinsics.checkNotNull(currentState, "null cannot be cast to non-null type androidx.datastore.core.ReadException<T of androidx.datastore.core.DataStoreImpl.handleUpdate$lambda$2>");
                            throw ((ReadException) currentState).getReadException();
                        }
                        dataStoreImpl$handleUpdate$12.L$0 = update2;
                        dataStoreImpl$handleUpdate$12.L$1 = dataStoreImpl;
                        dataStoreImpl$handleUpdate$12.L$2 = ack;
                        dataStoreImpl$handleUpdate$12.label = 2;
                        if (dataStoreImpl.readAndInitOrPropagateAndThrowFailure(dataStoreImpl$handleUpdate$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        completableDeferred = ack;
                        Function2<T, Continuation<? super T>, Object> transform2 = update2.getTransform();
                        CoroutineContext callerContext2 = update2.getCallerContext();
                        dataStoreImpl$handleUpdate$12.L$0 = completableDeferred;
                        dataStoreImpl$handleUpdate$12.L$1 = null;
                        dataStoreImpl$handleUpdate$12.L$2 = null;
                        dataStoreImpl$handleUpdate$12.label = 3;
                        transformAndWrite2 = dataStoreImpl.transformAndWrite(transform2, callerContext2, dataStoreImpl$handleUpdate$12);
                        if (transformAndWrite2 != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        completableDeferred3 = completableDeferred;
                        obj = transformAndWrite2;
                        coroutine_suspended = completableDeferred3;
                        m569constructorimpl = Result.m569constructorimpl(obj);
                        r1 = coroutine_suspended;
                        CompletableDeferredKt.completeWith(r1, m569constructorimpl);
                        return Unit.INSTANCE;
                    case 1:
                        CompletableDeferred<T> completableDeferred4 = (CompletableDeferred) dataStoreImpl$handleUpdate$12.L$0;
                        ResultKt.throwOnFailure(obj2);
                        transformAndWrite = obj2;
                        completableDeferred2 = completableDeferred4;
                        obj = transformAndWrite;
                        coroutine_suspended = completableDeferred2;
                        m569constructorimpl = Result.m569constructorimpl(obj);
                        r1 = coroutine_suspended;
                        CompletableDeferredKt.completeWith(r1, m569constructorimpl);
                        return Unit.INSTANCE;
                    case 2:
                        completableDeferred = (CompletableDeferred) dataStoreImpl$handleUpdate$12.L$2;
                        dataStoreImpl = (DataStoreImpl) dataStoreImpl$handleUpdate$12.L$1;
                        update2 = (Message.Update) dataStoreImpl$handleUpdate$12.L$0;
                        try {
                            ResultKt.throwOnFailure(obj2);
                            Function2<T, Continuation<? super T>, Object> transform22 = update2.getTransform();
                            CoroutineContext callerContext22 = update2.getCallerContext();
                            dataStoreImpl$handleUpdate$12.L$0 = completableDeferred;
                            dataStoreImpl$handleUpdate$12.L$1 = null;
                            dataStoreImpl$handleUpdate$12.L$2 = null;
                            dataStoreImpl$handleUpdate$12.label = 3;
                            transformAndWrite2 = dataStoreImpl.transformAndWrite(transform22, callerContext22, dataStoreImpl$handleUpdate$12);
                            if (transformAndWrite2 != coroutine_suspended) {
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            coroutine_suspended = completableDeferred;
                            Result.Companion companion22 = Result.INSTANCE;
                            m569constructorimpl = Result.m569constructorimpl(ResultKt.createFailure(th));
                            r1 = coroutine_suspended;
                            CompletableDeferredKt.completeWith(r1, m569constructorimpl);
                            return Unit.INSTANCE;
                        }
                        break;
                    case 3:
                        CompletableDeferred<T> completableDeferred5 = (CompletableDeferred) dataStoreImpl$handleUpdate$12.L$0;
                        ResultKt.throwOnFailure(obj2);
                        transformAndWrite2 = obj2;
                        completableDeferred3 = completableDeferred5;
                        obj = transformAndWrite2;
                        coroutine_suspended = completableDeferred3;
                        m569constructorimpl = Result.m569constructorimpl(obj);
                        r1 = coroutine_suspended;
                        CompletableDeferredKt.completeWith(r1, m569constructorimpl);
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        dataStoreImpl$handleUpdate$1 = new DataStoreImpl$handleUpdate$1(this, continuation);
        dataStoreImpl$handleUpdate$12 = dataStoreImpl$handleUpdate$1;
        Object obj22 = dataStoreImpl$handleUpdate$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (dataStoreImpl$handleUpdate$12.label) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object readAndInitOrPropagateAndThrowFailure(Continuation<? super Unit> continuation) {
        DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1 dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1;
        DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1 dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12;
        DataStoreImpl dataStoreImpl;
        Object version;
        int preReadVersion;
        Throwable throwable;
        int preReadVersion2;
        if (continuation instanceof DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1) {
            dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1 = (DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1) continuation;
            if ((dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.label & Integer.MIN_VALUE) != 0) {
                dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1.label -= Integer.MIN_VALUE;
                dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12 = dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1;
                Object $result = dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        dataStoreImpl = this;
                        InterProcessCoordinator coordinator = dataStoreImpl.getCoordinator();
                        dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12.L$0 = dataStoreImpl;
                        dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12.label = 1;
                        version = coordinator.getVersion(dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12);
                        if (version == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        preReadVersion = ((Number) version).intValue();
                        try {
                            DataStoreImpl<T>.InitDataStore initDataStore = dataStoreImpl.readAndInit;
                            dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12.L$0 = dataStoreImpl;
                            dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12.I$0 = preReadVersion;
                            dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12.label = 2;
                            return initDataStore.runIfNeeded(dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12) != coroutine_suspended ? coroutine_suspended : Unit.INSTANCE;
                        } catch (Throwable th) {
                            throwable = th;
                            preReadVersion2 = preReadVersion;
                            dataStoreImpl.inMemoryCache.tryUpdate(new ReadException(throwable, preReadVersion2));
                            throw throwable;
                        }
                    case 1:
                        dataStoreImpl = (DataStoreImpl) dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12.L$0;
                        ResultKt.throwOnFailure($result);
                        version = $result;
                        preReadVersion = ((Number) version).intValue();
                        DataStoreImpl<T>.InitDataStore initDataStore2 = dataStoreImpl.readAndInit;
                        dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12.L$0 = dataStoreImpl;
                        dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12.I$0 = preReadVersion;
                        dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12.label = 2;
                        if (initDataStore2.runIfNeeded(dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12) != coroutine_suspended) {
                        }
                        break;
                    case 2:
                        preReadVersion2 = dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12.I$0;
                        dataStoreImpl = (DataStoreImpl) dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                        } catch (Throwable th2) {
                            throwable = th2;
                            dataStoreImpl.inMemoryCache.tryUpdate(new ReadException(throwable, preReadVersion2));
                            throw throwable;
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1 = new DataStoreImpl$readAndInitOrPropagateAndThrowFailure$1(this, continuation);
        dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12 = dataStoreImpl$readAndInitOrPropagateAndThrowFailure$1;
        Object $result2 = dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (dataStoreImpl$readAndInitOrPropagateAndThrowFailure$12.label) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object readDataAndUpdateCache(boolean requireLock, Continuation<? super State<T>> continuation) {
        DataStoreImpl$readDataAndUpdateCache$1 dataStoreImpl$readDataAndUpdateCache$1;
        DataStoreImpl$readDataAndUpdateCache$1 dataStoreImpl$readDataAndUpdateCache$12;
        State currentState;
        Object version;
        boolean requireLock2;
        DataStoreImpl dataStoreImpl;
        Object tryLock;
        Object lock;
        Pair pair;
        boolean acquiredLock;
        if (continuation instanceof DataStoreImpl$readDataAndUpdateCache$1) {
            dataStoreImpl$readDataAndUpdateCache$1 = (DataStoreImpl$readDataAndUpdateCache$1) continuation;
            if ((dataStoreImpl$readDataAndUpdateCache$1.label & Integer.MIN_VALUE) != 0) {
                dataStoreImpl$readDataAndUpdateCache$1.label -= Integer.MIN_VALUE;
                dataStoreImpl$readDataAndUpdateCache$12 = dataStoreImpl$readDataAndUpdateCache$1;
                Object $result = dataStoreImpl$readDataAndUpdateCache$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (dataStoreImpl$readDataAndUpdateCache$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        currentState = this.inMemoryCache.getCurrentState();
                        if (currentState instanceof UnInitialized) {
                            throw new IllegalStateException(BUG_MESSAGE.toString());
                        }
                        InterProcessCoordinator coordinator = getCoordinator();
                        dataStoreImpl$readDataAndUpdateCache$12.L$0 = this;
                        dataStoreImpl$readDataAndUpdateCache$12.L$1 = currentState;
                        dataStoreImpl$readDataAndUpdateCache$12.Z$0 = requireLock;
                        dataStoreImpl$readDataAndUpdateCache$12.label = 1;
                        version = coordinator.getVersion(dataStoreImpl$readDataAndUpdateCache$12);
                        if (version == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        requireLock2 = requireLock;
                        dataStoreImpl = this;
                        int latestVersion = ((Number) version).intValue();
                        int cachedVersion = !(currentState instanceof Data) ? currentState.getVersion() : -1;
                        if (!(currentState instanceof Data) && latestVersion == cachedVersion) {
                            return currentState;
                        }
                        if (requireLock2) {
                            InterProcessCoordinator coordinator2 = dataStoreImpl.getCoordinator();
                            DataStoreImpl$readDataAndUpdateCache$4 dataStoreImpl$readDataAndUpdateCache$4 = new DataStoreImpl$readDataAndUpdateCache$4(dataStoreImpl, cachedVersion, null);
                            dataStoreImpl$readDataAndUpdateCache$12.L$0 = dataStoreImpl;
                            dataStoreImpl$readDataAndUpdateCache$12.L$1 = null;
                            dataStoreImpl$readDataAndUpdateCache$12.label = 3;
                            tryLock = coordinator2.tryLock(dataStoreImpl$readDataAndUpdateCache$4, dataStoreImpl$readDataAndUpdateCache$12);
                            if (tryLock == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            pair = (Pair) tryLock;
                            State newState = (State) pair.component1();
                            acquiredLock = ((Boolean) pair.component2()).booleanValue();
                            if (acquiredLock) {
                            }
                            return newState;
                        }
                        InterProcessCoordinator coordinator3 = dataStoreImpl.getCoordinator();
                        DataStoreImpl$readDataAndUpdateCache$3 dataStoreImpl$readDataAndUpdateCache$3 = new DataStoreImpl$readDataAndUpdateCache$3(dataStoreImpl, null);
                        dataStoreImpl$readDataAndUpdateCache$12.L$0 = dataStoreImpl;
                        dataStoreImpl$readDataAndUpdateCache$12.L$1 = null;
                        dataStoreImpl$readDataAndUpdateCache$12.label = 2;
                        lock = coordinator3.lock(dataStoreImpl$readDataAndUpdateCache$3, dataStoreImpl$readDataAndUpdateCache$12);
                        if (lock == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        pair = (Pair) lock;
                        State newState2 = (State) pair.component1();
                        acquiredLock = ((Boolean) pair.component2()).booleanValue();
                        if (acquiredLock) {
                            dataStoreImpl.inMemoryCache.tryUpdate(newState2);
                        }
                        return newState2;
                    case 1:
                        boolean requireLock3 = dataStoreImpl$readDataAndUpdateCache$12.Z$0;
                        State currentState2 = (State) dataStoreImpl$readDataAndUpdateCache$12.L$1;
                        DataStoreImpl dataStoreImpl2 = (DataStoreImpl) dataStoreImpl$readDataAndUpdateCache$12.L$0;
                        ResultKt.throwOnFailure($result);
                        version = $result;
                        requireLock2 = requireLock3;
                        dataStoreImpl = dataStoreImpl2;
                        currentState = currentState2;
                        int latestVersion2 = ((Number) version).intValue();
                        if (!(currentState instanceof Data)) {
                        }
                        if (!(currentState instanceof Data)) {
                        }
                        if (requireLock2) {
                        }
                        break;
                    case 2:
                        dataStoreImpl = (DataStoreImpl) dataStoreImpl$readDataAndUpdateCache$12.L$0;
                        ResultKt.throwOnFailure($result);
                        lock = $result;
                        pair = (Pair) lock;
                        State newState22 = (State) pair.component1();
                        acquiredLock = ((Boolean) pair.component2()).booleanValue();
                        if (acquiredLock) {
                        }
                        return newState22;
                    case 3:
                        dataStoreImpl = (DataStoreImpl) dataStoreImpl$readDataAndUpdateCache$12.L$0;
                        ResultKt.throwOnFailure($result);
                        tryLock = $result;
                        pair = (Pair) tryLock;
                        State newState222 = (State) pair.component1();
                        acquiredLock = ((Boolean) pair.component2()).booleanValue();
                        if (acquiredLock) {
                        }
                        return newState222;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        dataStoreImpl$readDataAndUpdateCache$1 = new DataStoreImpl$readDataAndUpdateCache$1(this, continuation);
        dataStoreImpl$readDataAndUpdateCache$12 = dataStoreImpl$readDataAndUpdateCache$1;
        Object $result2 = dataStoreImpl$readDataAndUpdateCache$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (dataStoreImpl$readDataAndUpdateCache$12.label) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object readDataFromFileOrDefault(Continuation<? super T> continuation) {
        return StorageConnectionKt.readData(getStorageConnection$datastore_core_release(), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object transformAndWrite(Function2<? super T, ? super Continuation<? super T>, ? extends Object> function2, CoroutineContext callerContext, Continuation<? super T> continuation) {
        return getCoordinator().lock(new DataStoreImpl$transformAndWrite$2(this, callerContext, function2, null), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object writeData$datastore_core_release(T t, boolean updateCache, Continuation<? super Integer> continuation) {
        DataStoreImpl$writeData$1 dataStoreImpl$writeData$1;
        DataStoreImpl$writeData$1 dataStoreImpl$writeData$12;
        Ref.IntRef newVersion;
        if (continuation instanceof DataStoreImpl$writeData$1) {
            dataStoreImpl$writeData$1 = (DataStoreImpl$writeData$1) continuation;
            if ((dataStoreImpl$writeData$1.label & Integer.MIN_VALUE) != 0) {
                dataStoreImpl$writeData$1.label -= Integer.MIN_VALUE;
                dataStoreImpl$writeData$12 = dataStoreImpl$writeData$1;
                Object $result = dataStoreImpl$writeData$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (dataStoreImpl$writeData$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        newVersion = new Ref.IntRef();
                        StorageConnection<T> storageConnection$datastore_core_release = getStorageConnection$datastore_core_release();
                        DataStoreImpl$writeData$2 dataStoreImpl$writeData$2 = new DataStoreImpl$writeData$2(newVersion, this, t, updateCache, null);
                        dataStoreImpl$writeData$12.L$0 = newVersion;
                        dataStoreImpl$writeData$12.label = 1;
                        if (storageConnection$datastore_core_release.writeScope(dataStoreImpl$writeData$2, dataStoreImpl$writeData$12) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        newVersion = (Ref.IntRef) dataStoreImpl$writeData$12.L$0;
                        ResultKt.throwOnFailure($result);
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return Boxing.boxInt(newVersion.element);
            }
        }
        dataStoreImpl$writeData$1 = new DataStoreImpl$writeData$1(this, continuation);
        dataStoreImpl$writeData$12 = dataStoreImpl$writeData$1;
        Object $result2 = dataStoreImpl$writeData$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (dataStoreImpl$writeData$12.label) {
        }
        return Boxing.boxInt(newVersion.element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(8:0|1|(2:3|(4:5|6|7|8))|80|6|7|8|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0096, code lost:
    
        r6 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0097, code lost:
    
        r8 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0112, code lost:
    
        r2 = r6;
        r6 = new kotlin.jvm.internal.Ref.ObjectRef();
        r7 = r8.corruptionHandler;
        r12.L$0 = r8;
        r12.L$1 = r2;
        r12.L$2 = r6;
        r12.L$3 = r6;
        r12.Z$0 = r11;
        r12.label = 5;
        r7 = r7.handleCorruption(r2, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x012b, code lost:
    
        if (r7 != r1) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x012d, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x012e, code lost:
    
        r9 = r8;
        r8 = (T) r7;
        r7 = r2;
        r2 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0084, code lost:
    
        r6 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0085, code lost:
    
        r11 = r2;
        r8 = r7;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0156 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x010b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b3 A[Catch: CorruptionException -> 0x0084, TRY_ENTER, TryCatch #2 {CorruptionException -> 0x0084, blocks: (B:49:0x007f, B:51:0x00cf, B:58:0x00b3, B:59:0x00b9), top: B:7:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00ce A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x012d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0028  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object readDataOrHandleCorruption(boolean z, Continuation<? super Data<T>> continuation) {
        DataStoreImpl$readDataOrHandleCorruption$1 dataStoreImpl$readDataOrHandleCorruption$1;
        ?? r2;
        CorruptionException corruptionException;
        Ref.IntRef intRef;
        Ref.ObjectRef objectRef;
        DataStoreImpl dataStoreImpl;
        Object version;
        Object readDataFromFileOrDefault;
        DataStoreImpl dataStoreImpl2;
        boolean z2;
        int hashCode;
        Object version2;
        Object tryLock;
        if (continuation instanceof DataStoreImpl$readDataOrHandleCorruption$1) {
            dataStoreImpl$readDataOrHandleCorruption$1 = (DataStoreImpl$readDataOrHandleCorruption$1) continuation;
            if ((dataStoreImpl$readDataOrHandleCorruption$1.label & Integer.MIN_VALUE) != 0) {
                dataStoreImpl$readDataOrHandleCorruption$1.label -= Integer.MIN_VALUE;
                DataStoreImpl$readDataOrHandleCorruption$1 dataStoreImpl$readDataOrHandleCorruption$12 = dataStoreImpl$readDataOrHandleCorruption$1;
                Object obj = dataStoreImpl$readDataOrHandleCorruption$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                r2 = dataStoreImpl$readDataOrHandleCorruption$12.label;
                switch (r2) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        dataStoreImpl = this;
                        if (!z) {
                            InterProcessCoordinator coordinator = dataStoreImpl.getCoordinator();
                            dataStoreImpl$readDataOrHandleCorruption$12.L$0 = dataStoreImpl;
                            dataStoreImpl$readDataOrHandleCorruption$12.Z$0 = z;
                            dataStoreImpl$readDataOrHandleCorruption$12.label = 3;
                            version = coordinator.getVersion(dataStoreImpl$readDataOrHandleCorruption$12);
                            if (version == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            int intValue = ((Number) version).intValue();
                            InterProcessCoordinator coordinator2 = dataStoreImpl.getCoordinator();
                            DataStoreImpl$readDataOrHandleCorruption$2 dataStoreImpl$readDataOrHandleCorruption$2 = new DataStoreImpl$readDataOrHandleCorruption$2(dataStoreImpl, intValue, null);
                            dataStoreImpl$readDataOrHandleCorruption$12.L$0 = dataStoreImpl;
                            dataStoreImpl$readDataOrHandleCorruption$12.Z$0 = z;
                            dataStoreImpl$readDataOrHandleCorruption$12.label = 4;
                            tryLock = coordinator2.tryLock(dataStoreImpl$readDataOrHandleCorruption$2, dataStoreImpl$readDataOrHandleCorruption$12);
                            if (tryLock == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            return (Data) tryLock;
                        }
                        dataStoreImpl$readDataOrHandleCorruption$12.L$0 = dataStoreImpl;
                        dataStoreImpl$readDataOrHandleCorruption$12.Z$0 = z;
                        dataStoreImpl$readDataOrHandleCorruption$12.label = 1;
                        readDataFromFileOrDefault = dataStoreImpl.readDataFromFileOrDefault(dataStoreImpl$readDataOrHandleCorruption$12);
                        if (readDataFromFileOrDefault == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        dataStoreImpl2 = dataStoreImpl;
                        z2 = z;
                        hashCode = readDataFromFileOrDefault == null ? readDataFromFileOrDefault.hashCode() : 0;
                        InterProcessCoordinator coordinator3 = dataStoreImpl2.getCoordinator();
                        dataStoreImpl$readDataOrHandleCorruption$12.L$0 = dataStoreImpl2;
                        dataStoreImpl$readDataOrHandleCorruption$12.L$1 = readDataFromFileOrDefault;
                        dataStoreImpl$readDataOrHandleCorruption$12.Z$0 = z2;
                        dataStoreImpl$readDataOrHandleCorruption$12.I$0 = hashCode;
                        dataStoreImpl$readDataOrHandleCorruption$12.label = 2;
                        version2 = coordinator3.getVersion(dataStoreImpl$readDataOrHandleCorruption$12);
                        r2 = z2;
                        if (version2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return new Data(readDataFromFileOrDefault, hashCode, ((Number) version2).intValue());
                    case 1:
                        boolean z3 = dataStoreImpl$readDataOrHandleCorruption$12.Z$0;
                        DataStoreImpl dataStoreImpl3 = (DataStoreImpl) dataStoreImpl$readDataOrHandleCorruption$12.L$0;
                        ResultKt.throwOnFailure(obj);
                        readDataFromFileOrDefault = obj;
                        dataStoreImpl2 = dataStoreImpl3;
                        z2 = z3;
                        if (readDataFromFileOrDefault == null) {
                        }
                        InterProcessCoordinator coordinator32 = dataStoreImpl2.getCoordinator();
                        dataStoreImpl$readDataOrHandleCorruption$12.L$0 = dataStoreImpl2;
                        dataStoreImpl$readDataOrHandleCorruption$12.L$1 = readDataFromFileOrDefault;
                        dataStoreImpl$readDataOrHandleCorruption$12.Z$0 = z2;
                        dataStoreImpl$readDataOrHandleCorruption$12.I$0 = hashCode;
                        dataStoreImpl$readDataOrHandleCorruption$12.label = 2;
                        version2 = coordinator32.getVersion(dataStoreImpl$readDataOrHandleCorruption$12);
                        r2 = z2;
                        if (version2 == coroutine_suspended) {
                        }
                        return new Data(readDataFromFileOrDefault, hashCode, ((Number) version2).intValue());
                    case 2:
                        hashCode = dataStoreImpl$readDataOrHandleCorruption$12.I$0;
                        boolean z4 = dataStoreImpl$readDataOrHandleCorruption$12.Z$0;
                        readDataFromFileOrDefault = dataStoreImpl$readDataOrHandleCorruption$12.L$1;
                        dataStoreImpl2 = (DataStoreImpl) dataStoreImpl$readDataOrHandleCorruption$12.L$0;
                        ResultKt.throwOnFailure(obj);
                        version2 = obj;
                        r2 = z4;
                        return new Data(readDataFromFileOrDefault, hashCode, ((Number) version2).intValue());
                    case 3:
                        z = dataStoreImpl$readDataOrHandleCorruption$12.Z$0;
                        dataStoreImpl = (DataStoreImpl) dataStoreImpl$readDataOrHandleCorruption$12.L$0;
                        ResultKt.throwOnFailure(obj);
                        version = obj;
                        int intValue2 = ((Number) version).intValue();
                        InterProcessCoordinator coordinator22 = dataStoreImpl.getCoordinator();
                        DataStoreImpl$readDataOrHandleCorruption$2 dataStoreImpl$readDataOrHandleCorruption$22 = new DataStoreImpl$readDataOrHandleCorruption$2(dataStoreImpl, intValue2, null);
                        dataStoreImpl$readDataOrHandleCorruption$12.L$0 = dataStoreImpl;
                        dataStoreImpl$readDataOrHandleCorruption$12.Z$0 = z;
                        dataStoreImpl$readDataOrHandleCorruption$12.label = 4;
                        tryLock = coordinator22.tryLock(dataStoreImpl$readDataOrHandleCorruption$22, dataStoreImpl$readDataOrHandleCorruption$12);
                        if (tryLock == coroutine_suspended) {
                        }
                        return (Data) tryLock;
                    case 4:
                        z = dataStoreImpl$readDataOrHandleCorruption$12.Z$0;
                        dataStoreImpl = (DataStoreImpl) dataStoreImpl$readDataOrHandleCorruption$12.L$0;
                        ResultKt.throwOnFailure(obj);
                        tryLock = obj;
                        return (Data) tryLock;
                    case 5:
                        z = dataStoreImpl$readDataOrHandleCorruption$12.Z$0;
                        Ref.ObjectRef objectRef2 = (Ref.ObjectRef) dataStoreImpl$readDataOrHandleCorruption$12.L$3;
                        Ref.ObjectRef objectRef3 = (Ref.ObjectRef) dataStoreImpl$readDataOrHandleCorruption$12.L$2;
                        CorruptionException corruptionException2 = (CorruptionException) dataStoreImpl$readDataOrHandleCorruption$12.L$1;
                        DataStoreImpl dataStoreImpl4 = (DataStoreImpl) dataStoreImpl$readDataOrHandleCorruption$12.L$0;
                        ResultKt.throwOnFailure(obj);
                        DataStoreImpl dataStoreImpl5 = dataStoreImpl4;
                        T t = (T) obj;
                        objectRef2.element = t;
                        Ref.IntRef intRef2 = new Ref.IntRef();
                        boolean z5 = z;
                        try {
                            DataStoreImpl$readDataOrHandleCorruption$3 dataStoreImpl$readDataOrHandleCorruption$3 = new DataStoreImpl$readDataOrHandleCorruption$3(objectRef3, dataStoreImpl5, intRef2, null);
                            dataStoreImpl$readDataOrHandleCorruption$12.L$0 = corruptionException2;
                            dataStoreImpl$readDataOrHandleCorruption$12.L$1 = objectRef3;
                            dataStoreImpl$readDataOrHandleCorruption$12.L$2 = intRef2;
                            dataStoreImpl$readDataOrHandleCorruption$12.L$3 = null;
                            dataStoreImpl$readDataOrHandleCorruption$12.label = 6;
                            if (dataStoreImpl5.doWithWriteFileLock(z5, dataStoreImpl$readDataOrHandleCorruption$3, dataStoreImpl$readDataOrHandleCorruption$12) != coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            intRef = intRef2;
                            objectRef = objectRef3;
                            T t2 = objectRef.element;
                            T t3 = objectRef.element;
                            return new Data(t2, t3 != null ? t3.hashCode() : 0, intRef.element);
                        } catch (Throwable th) {
                            th = th;
                            corruptionException = corruptionException2;
                            ExceptionsKt.addSuppressed(corruptionException, th);
                            throw corruptionException;
                        }
                    case 6:
                        intRef = (Ref.IntRef) dataStoreImpl$readDataOrHandleCorruption$12.L$2;
                        objectRef = (Ref.ObjectRef) dataStoreImpl$readDataOrHandleCorruption$12.L$1;
                        corruptionException = (CorruptionException) dataStoreImpl$readDataOrHandleCorruption$12.L$0;
                        try {
                            ResultKt.throwOnFailure(obj);
                            T t22 = objectRef.element;
                            T t32 = objectRef.element;
                            return new Data(t22, t32 != null ? t32.hashCode() : 0, intRef.element);
                        } catch (Throwable th2) {
                            th = th2;
                            ExceptionsKt.addSuppressed(corruptionException, th);
                            throw corruptionException;
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        dataStoreImpl$readDataOrHandleCorruption$1 = new DataStoreImpl$readDataOrHandleCorruption$1(this, continuation);
        DataStoreImpl$readDataOrHandleCorruption$1 dataStoreImpl$readDataOrHandleCorruption$122 = dataStoreImpl$readDataOrHandleCorruption$1;
        Object obj2 = dataStoreImpl$readDataOrHandleCorruption$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = dataStoreImpl$readDataOrHandleCorruption$122.label;
        switch (r2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <R> Object doWithWriteFileLock(boolean hasWriteFileLock, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super R> continuation) {
        if (hasWriteFileLock) {
            return function1.invoke(continuation);
        }
        return getCoordinator().lock(new DataStoreImpl$doWithWriteFileLock$3(function1, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DataStoreImpl.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001BD\u0012=\u0010\u0002\u001a9\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u00040\u0003¢\u0006\u0002\u0010\fJ\u000e\u0010\u000e\u001a\u00020\nH\u0094@¢\u0006\u0002\u0010\u000fRG\u0010\r\u001a;\u00125\u00123\b\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/datastore/core/DataStoreImpl$InitDataStore;", "Landroidx/datastore/core/RunOnce;", "initTasksList", "", "Lkotlin/Function2;", "Landroidx/datastore/core/InitializerApi;", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "api", "Lkotlin/coroutines/Continuation;", "", "", "(Landroidx/datastore/core/DataStoreImpl;Ljava/util/List;)V", "initTasks", "doRun", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    final class InitDataStore extends RunOnce {
        private List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> initTasks;
        final /* synthetic */ DataStoreImpl<T> this$0;

        public InitDataStore(DataStoreImpl this$0, List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> initTasksList) {
            Intrinsics.checkNotNullParameter(initTasksList, "initTasksList");
            this.this$0 = this$0;
            this.initTasks = CollectionsKt.toList(initTasksList);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0036  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x003f  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
        @Override // androidx.datastore.core.RunOnce
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        protected Object doRun(Continuation<? super Unit> continuation) {
            DataStoreImpl$InitDataStore$doRun$1 dataStoreImpl$InitDataStore$doRun$1;
            DataStoreImpl$InitDataStore$doRun$1 dataStoreImpl$InitDataStore$doRun$12;
            Object readDataOrHandleCorruption;
            InitDataStore initDataStore;
            Object lock;
            Data data;
            if (continuation instanceof DataStoreImpl$InitDataStore$doRun$1) {
                dataStoreImpl$InitDataStore$doRun$1 = (DataStoreImpl$InitDataStore$doRun$1) continuation;
                if ((dataStoreImpl$InitDataStore$doRun$1.label & Integer.MIN_VALUE) != 0) {
                    dataStoreImpl$InitDataStore$doRun$1.label -= Integer.MIN_VALUE;
                    dataStoreImpl$InitDataStore$doRun$12 = dataStoreImpl$InitDataStore$doRun$1;
                    Object $result = dataStoreImpl$InitDataStore$doRun$12.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (dataStoreImpl$InitDataStore$doRun$12.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            if (this.initTasks != null) {
                                List<? extends Function2<? super InitializerApi<T>, ? super Continuation<? super Unit>, ? extends Object>> list = this.initTasks;
                                Intrinsics.checkNotNull(list);
                                if (!list.isEmpty()) {
                                    InterProcessCoordinator coordinator = this.this$0.getCoordinator();
                                    DataStoreImpl$InitDataStore$doRun$initData$1 dataStoreImpl$InitDataStore$doRun$initData$1 = new DataStoreImpl$InitDataStore$doRun$initData$1(this.this$0, this, null);
                                    dataStoreImpl$InitDataStore$doRun$12.L$0 = this;
                                    dataStoreImpl$InitDataStore$doRun$12.label = 2;
                                    lock = coordinator.lock(dataStoreImpl$InitDataStore$doRun$initData$1, dataStoreImpl$InitDataStore$doRun$12);
                                    if (lock == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    initDataStore = this;
                                    data = (Data) lock;
                                    Data initData = data;
                                    ((DataStoreImpl) initDataStore.this$0).inMemoryCache.tryUpdate(initData);
                                    return Unit.INSTANCE;
                                }
                            }
                            DataStoreImpl<T> dataStoreImpl = this.this$0;
                            dataStoreImpl$InitDataStore$doRun$12.L$0 = this;
                            dataStoreImpl$InitDataStore$doRun$12.label = 1;
                            readDataOrHandleCorruption = dataStoreImpl.readDataOrHandleCorruption(false, dataStoreImpl$InitDataStore$doRun$12);
                            if (readDataOrHandleCorruption == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            initDataStore = this;
                            data = (Data) readDataOrHandleCorruption;
                            Data initData2 = data;
                            ((DataStoreImpl) initDataStore.this$0).inMemoryCache.tryUpdate(initData2);
                            return Unit.INSTANCE;
                        case 1:
                            initDataStore = (InitDataStore) dataStoreImpl$InitDataStore$doRun$12.L$0;
                            ResultKt.throwOnFailure($result);
                            readDataOrHandleCorruption = $result;
                            data = (Data) readDataOrHandleCorruption;
                            Data initData22 = data;
                            ((DataStoreImpl) initDataStore.this$0).inMemoryCache.tryUpdate(initData22);
                            return Unit.INSTANCE;
                        case 2:
                            initDataStore = (InitDataStore) dataStoreImpl$InitDataStore$doRun$12.L$0;
                            ResultKt.throwOnFailure($result);
                            lock = $result;
                            data = (Data) lock;
                            Data initData222 = data;
                            ((DataStoreImpl) initDataStore.this$0).inMemoryCache.tryUpdate(initData222);
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }
            dataStoreImpl$InitDataStore$doRun$1 = new DataStoreImpl$InitDataStore$doRun$1(this, continuation);
            dataStoreImpl$InitDataStore$doRun$12 = dataStoreImpl$InitDataStore$doRun$1;
            Object $result2 = dataStoreImpl$InitDataStore$doRun$12.result;
            Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (dataStoreImpl$InitDataStore$doRun$12.label) {
            }
        }
    }
}
