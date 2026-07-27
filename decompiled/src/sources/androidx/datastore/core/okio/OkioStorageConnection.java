package androidx.datastore.core.okio;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.datastore.core.Closeable;
import androidx.datastore.core.InterProcessCoordinator;
import androidx.datastore.core.ReadScope;
import androidx.datastore.core.StorageConnection;
import androidx.datastore.core.WriteScope;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okio.FileSystem;
import okio.Path;

/* compiled from: OkioStorage.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B9\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eJ\b\u0010\u0015\u001a\u00020\rH\u0002J\b\u0010\u0016\u001a\u00020\rH\u0016JX\u0010\u0017\u001a\u0002H\u0018\"\u0004\b\u0001\u0010\u00182B\u0010\u0019\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001b\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180 \u0012\u0006\u0012\u0004\u0018\u00010!0\u001a¢\u0006\u0002\b\"H\u0096@¢\u0006\u0002\u0010#J=\u0010$\u001a\u00020\r2-\u0010\u0019\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000&\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0 \u0012\u0006\u0012\u0004\u0018\u00010!0%¢\u0006\u0002\b\"H\u0096@¢\u0006\u0002\u0010'R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Landroidx/datastore/core/okio/OkioStorageConnection;", "T", "Landroidx/datastore/core/StorageConnection;", "fileSystem", "Lokio/FileSystem;", "path", "Lokio/Path;", "serializer", "Landroidx/datastore/core/okio/OkioSerializer;", "coordinator", "Landroidx/datastore/core/InterProcessCoordinator;", "onClose", "Lkotlin/Function0;", "", "(Lokio/FileSystem;Lokio/Path;Landroidx/datastore/core/okio/OkioSerializer;Landroidx/datastore/core/InterProcessCoordinator;Lkotlin/jvm/functions/Function0;)V", "closed", "Landroidx/datastore/core/okio/AtomicBoolean;", "getCoordinator", "()Landroidx/datastore/core/InterProcessCoordinator;", "transactionMutex", "Lkotlinx/coroutines/sync/Mutex;", "checkNotClosed", "close", "readScope", "R", "block", "Lkotlin/Function3;", "Landroidx/datastore/core/ReadScope;", "", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "locked", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeScope", "Lkotlin/Function2;", "Landroidx/datastore/core/WriteScope;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core-okio"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class OkioStorageConnection<T> implements StorageConnection<T> {
    private final AtomicBoolean closed;
    private final InterProcessCoordinator coordinator;
    private final FileSystem fileSystem;
    private final Function0<Unit> onClose;
    private final Path path;
    private final OkioSerializer<T> serializer;
    private final Mutex transactionMutex;

    public OkioStorageConnection(FileSystem fileSystem, Path path, OkioSerializer<T> serializer, InterProcessCoordinator coordinator, Function0<Unit> onClose) {
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(coordinator, "coordinator");
        Intrinsics.checkNotNullParameter(onClose, "onClose");
        this.fileSystem = fileSystem;
        this.path = path;
        this.serializer = serializer;
        this.coordinator = coordinator;
        this.onClose = onClose;
        this.closed = new AtomicBoolean(false);
        this.transactionMutex = MutexKt.Mutex$default(false, 1, null);
    }

    @Override // androidx.datastore.core.StorageConnection
    public InterProcessCoordinator getCoordinator() {
        return this.coordinator;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0089 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0092 A[Catch: all -> 0x00a4, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x00a4, blocks: (B:21:0x0092, B:34:0x00a3, B:33:0x009e, B:41:0x0052, B:29:0x0098), top: B:40:0x0052, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    @Override // androidx.datastore.core.StorageConnection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public <R> Object readScope(Function3<? super ReadScope<T>, ? super Boolean, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super R> continuation) {
        OkioStorageConnection$readScope$1 okioStorageConnection$readScope$1;
        OkioStorageConnection$readScope$1 okioStorageConnection$readScope$12;
        OkioStorageConnection okioStorageConnection;
        boolean lock;
        OkioReadScope $this$use$iv;
        Throwable th;
        Object invoke;
        Throwable thrown$iv;
        if (continuation instanceof OkioStorageConnection$readScope$1) {
            okioStorageConnection$readScope$1 = (OkioStorageConnection$readScope$1) continuation;
            if ((okioStorageConnection$readScope$1.label & Integer.MIN_VALUE) != 0) {
                okioStorageConnection$readScope$1.label -= Integer.MIN_VALUE;
                okioStorageConnection$readScope$12 = okioStorageConnection$readScope$1;
                Object $result = okioStorageConnection$readScope$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (okioStorageConnection$readScope$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        okioStorageConnection = this;
                        okioStorageConnection.checkNotClosed();
                        lock = Mutex.DefaultImpls.tryLock$default(okioStorageConnection.transactionMutex, null, 1, null);
                        try {
                            $this$use$iv = new OkioReadScope(okioStorageConnection.fileSystem, okioStorageConnection.path, okioStorageConnection.serializer);
                            th = null;
                            try {
                                OkioReadScope okioReadScope = $this$use$iv;
                                Boolean boxBoolean = Boxing.boxBoolean(lock);
                                okioStorageConnection$readScope$12.L$0 = okioStorageConnection;
                                okioStorageConnection$readScope$12.L$1 = $this$use$iv;
                                okioStorageConnection$readScope$12.Z$0 = lock;
                                okioStorageConnection$readScope$12.label = 1;
                                invoke = function3.invoke(okioReadScope, boxBoolean, okioStorageConnection$readScope$12);
                                if (invoke == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                try {
                                    $this$use$iv.close();
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                                if (th != null) {
                                    return invoke;
                                }
                                throw th;
                            } catch (Throwable th3) {
                                t$iv = th3;
                                thrown$iv = t$iv;
                                try {
                                    $this$use$iv.close();
                                    throw thrown$iv;
                                } catch (Throwable t$iv) {
                                    ExceptionsKt.addSuppressed(thrown$iv, t$iv);
                                    throw thrown$iv;
                                }
                            }
                        } finally {
                            if (lock) {
                                Mutex.DefaultImpls.unlock$default(okioStorageConnection.transactionMutex, null, 1, null);
                            }
                        }
                    case 1:
                        lock = okioStorageConnection$readScope$12.Z$0;
                        $this$use$iv = (Closeable) okioStorageConnection$readScope$12.L$1;
                        okioStorageConnection = (OkioStorageConnection) okioStorageConnection$readScope$12.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            invoke = $result;
                            th = null;
                            $this$use$iv.close();
                            if (th != null) {
                            }
                        } catch (Throwable th4) {
                            t$iv = th4;
                            thrown$iv = t$iv;
                            $this$use$iv.close();
                            throw thrown$iv;
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        okioStorageConnection$readScope$1 = new OkioStorageConnection$readScope$1(this, continuation);
        okioStorageConnection$readScope$12 = okioStorageConnection$readScope$1;
        Object $result2 = okioStorageConnection$readScope$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (okioStorageConnection$readScope$12.label) {
        }
    }

    /* JADX WARN: Not initialized variable reg: 10, insn: 0x0154: INVOKE 
      (r11v0 ?? I:kotlinx.coroutines.sync.Mutex A[D('$this$withLock_u24default$iv' kotlinx.coroutines.sync.Mutex)])
      (r10 I:java.lang.Object A[D('owner$iv' java.lang.Object)])
     INTERFACE call: kotlinx.coroutines.sync.Mutex.unlock(java.lang.Object):void A[MD:(java.lang.Object):void (m)], block:B:75:0x0154 */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x0154: INVOKE 
      (r11 I:kotlinx.coroutines.sync.Mutex A[D('$this$withLock_u24default$iv' kotlinx.coroutines.sync.Mutex)])
      (r10 I:java.lang.Object A[D('owner$iv' java.lang.Object)])
     INTERFACE call: kotlinx.coroutines.sync.Mutex.unlock(java.lang.Object):void A[MD:(java.lang.Object):void (m)], block:B:75:0x0154 */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x013d: MOVE (r7 I:??[OBJECT, ARRAY]) = (r9 I:??[OBJECT, ARRAY] A[D('scratchPath' okio.Path)]), block:B:78:0x013c */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0107 A[Catch: IOException -> 0x013b, all -> 0x0153, TRY_ENTER, TryCatch #3 {all -> 0x0153, blocks: (B:20:0x0107, B:22:0x010f, B:23:0x0117, B:26:0x0122, B:57:0x0141, B:60:0x014a, B:64:0x0152, B:39:0x013a, B:38:0x0135, B:42:0x00a3, B:44:0x00c1), top: B:7:0x002a }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0122 A[Catch: IOException -> 0x013b, all -> 0x0153, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x0153, blocks: (B:20:0x0107, B:22:0x010f, B:23:0x0117, B:26:0x0122, B:57:0x0141, B:60:0x014a, B:64:0x0152, B:39:0x013a, B:38:0x0135, B:42:0x00a3, B:44:0x00c1), top: B:7:0x002a }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00ec A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002d  */
    @Override // androidx.datastore.core.StorageConnection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object writeScope(Function2<? super WriteScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        OkioStorageConnection$writeScope$1 okioStorageConnection$writeScope$1;
        Path scratchPath;
        Path scratchPath2;
        Mutex $this$withLock_u24default$iv;
        Object owner$iv;
        Function2 block;
        Path parentDir;
        OkioStorageConnection okioStorageConnection;
        Mutex $this$withLock_u24default$iv2;
        Object owner$iv2;
        Closeable $this$use$iv;
        OkioWriteScope it;
        Path scratchPath3;
        Throwable thrown$iv;
        Throwable thrown$iv2;
        try {
            try {
                if (continuation instanceof OkioStorageConnection$writeScope$1) {
                    okioStorageConnection$writeScope$1 = (OkioStorageConnection$writeScope$1) continuation;
                    if ((okioStorageConnection$writeScope$1.label & Integer.MIN_VALUE) != 0) {
                        okioStorageConnection$writeScope$1.label -= Integer.MIN_VALUE;
                        Object $result = okioStorageConnection$writeScope$1.result;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (okioStorageConnection$writeScope$1.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                block = function2;
                                checkNotClosed();
                                Path parent = this.path.parent();
                                if (parent == null) {
                                    throw new IllegalStateException("must have a parent path".toString());
                                }
                                parentDir = parent;
                                this.fileSystem.createDirectories(parentDir, false);
                                Mutex $this$withLock_u24default$iv3 = this.transactionMutex;
                                okioStorageConnection$writeScope$1.L$0 = this;
                                okioStorageConnection$writeScope$1.L$1 = block;
                                okioStorageConnection$writeScope$1.L$2 = parentDir;
                                okioStorageConnection$writeScope$1.L$3 = $this$withLock_u24default$iv3;
                                okioStorageConnection$writeScope$1.label = 1;
                                if ($this$withLock_u24default$iv3.lock(null, okioStorageConnection$writeScope$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                okioStorageConnection = this;
                                $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                owner$iv2 = null;
                                scratchPath2 = parentDir.resolve(okioStorageConnection.path.name() + ".tmp");
                                try {
                                    okioStorageConnection.fileSystem.delete(scratchPath2, false);
                                    $this$use$iv = new OkioWriteScope(okioStorageConnection.fileSystem, scratchPath2, okioStorageConnection.serializer);
                                    try {
                                        it = (OkioWriteScope) $this$use$iv;
                                        okioStorageConnection$writeScope$1.L$0 = okioStorageConnection;
                                        okioStorageConnection$writeScope$1.L$1 = $this$withLock_u24default$iv2;
                                        okioStorageConnection$writeScope$1.L$2 = scratchPath2;
                                        okioStorageConnection$writeScope$1.L$3 = $this$use$iv;
                                        okioStorageConnection$writeScope$1.label = 2;
                                        if (block.invoke(it, okioStorageConnection$writeScope$1) != coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        scratchPath3 = scratchPath2;
                                        thrown$iv = null;
                                        Unit unit = Unit.INSTANCE;
                                        try {
                                            $this$use$iv.close();
                                        } catch (Throwable t$iv) {
                                            thrown$iv = t$iv;
                                        }
                                        if (thrown$iv == null) {
                                            throw thrown$iv;
                                        }
                                        if (okioStorageConnection.fileSystem.exists(scratchPath3)) {
                                            okioStorageConnection.fileSystem.atomicMove(scratchPath3, okioStorageConnection.path);
                                        }
                                        Unit unit2 = Unit.INSTANCE;
                                        $this$withLock_u24default$iv2.unlock(owner$iv2);
                                        return Unit.INSTANCE;
                                    } catch (Throwable th) {
                                        t$iv = th;
                                        thrown$iv2 = t$iv;
                                        try {
                                            $this$use$iv.close();
                                            throw thrown$iv2;
                                        } catch (Throwable t$iv2) {
                                            ExceptionsKt.addSuppressed(thrown$iv2, t$iv2);
                                            throw thrown$iv2;
                                        }
                                    }
                                } catch (IOException e) {
                                    e = e;
                                    IOException ex = e;
                                    if (!okioStorageConnection.fileSystem.exists(scratchPath2)) {
                                        throw ex;
                                    }
                                    try {
                                        okioStorageConnection.fileSystem.delete(scratchPath2);
                                        throw ex;
                                    } catch (IOException e2) {
                                        throw ex;
                                    }
                                }
                            case 1:
                                Mutex $this$withLock_u24default$iv4 = (Mutex) okioStorageConnection$writeScope$1.L$3;
                                parentDir = (Path) okioStorageConnection$writeScope$1.L$2;
                                block = (Function2) okioStorageConnection$writeScope$1.L$1;
                                OkioStorageConnection okioStorageConnection2 = (OkioStorageConnection) okioStorageConnection$writeScope$1.L$0;
                                ResultKt.throwOnFailure($result);
                                $this$withLock_u24default$iv2 = $this$withLock_u24default$iv4;
                                okioStorageConnection = okioStorageConnection2;
                                owner$iv2 = null;
                                scratchPath2 = parentDir.resolve(okioStorageConnection.path.name() + ".tmp");
                                okioStorageConnection.fileSystem.delete(scratchPath2, false);
                                $this$use$iv = new OkioWriteScope(okioStorageConnection.fileSystem, scratchPath2, okioStorageConnection.serializer);
                                it = (OkioWriteScope) $this$use$iv;
                                okioStorageConnection$writeScope$1.L$0 = okioStorageConnection;
                                okioStorageConnection$writeScope$1.L$1 = $this$withLock_u24default$iv2;
                                okioStorageConnection$writeScope$1.L$2 = scratchPath2;
                                okioStorageConnection$writeScope$1.L$3 = $this$use$iv;
                                okioStorageConnection$writeScope$1.label = 2;
                                if (block.invoke(it, okioStorageConnection$writeScope$1) != coroutine_suspended) {
                                }
                                break;
                            case 2:
                                thrown$iv = null;
                                $this$use$iv = (Closeable) okioStorageConnection$writeScope$1.L$3;
                                scratchPath3 = (Path) okioStorageConnection$writeScope$1.L$2;
                                owner$iv2 = null;
                                $this$withLock_u24default$iv2 = (Mutex) okioStorageConnection$writeScope$1.L$1;
                                okioStorageConnection = (OkioStorageConnection) okioStorageConnection$writeScope$1.L$0;
                                try {
                                    ResultKt.throwOnFailure($result);
                                    Unit unit3 = Unit.INSTANCE;
                                    $this$use$iv.close();
                                    if (thrown$iv == null) {
                                    }
                                } catch (Throwable th2) {
                                    t$iv = th2;
                                    thrown$iv2 = t$iv;
                                    $this$use$iv.close();
                                    throw thrown$iv2;
                                }
                                break;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                    }
                }
                switch (okioStorageConnection$writeScope$1.label) {
                }
            } catch (Throwable th3) {
                $this$withLock_u24default$iv.unlock(owner$iv);
                throw th3;
            }
        } catch (IOException e3) {
            e = e3;
            scratchPath2 = scratchPath;
        }
        okioStorageConnection$writeScope$1 = new OkioStorageConnection$writeScope$1(this, continuation);
        Object $result2 = okioStorageConnection$writeScope$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }

    private final void checkNotClosed() {
        if (this.closed.get()) {
            throw new IllegalStateException("StorageConnection has already been disposed.".toString());
        }
    }

    @Override // androidx.datastore.core.Closeable
    public void close() {
        this.closed.set(true);
        this.onClose.invoke();
    }
}
