package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.File;
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

/* compiled from: FileStorage.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fJ\b\u0010\u0013\u001a\u00020\u000bH\u0002J\b\u0010\u0014\u001a\u00020\u000bH\u0016JX\u0010\u0015\u001a\u0002H\u0016\"\u0004\b\u0001\u0010\u00162B\u0010\u0017\u001a>\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00160\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u0018¢\u0006\u0002\b H\u0096@¢\u0006\u0002\u0010!J=\u0010\"\u001a\u00020\u000b2-\u0010\u0017\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000$\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001f0#¢\u0006\u0002\b H\u0096@¢\u0006\u0002\u0010%J\f\u0010&\u001a\u00020\u000b*\u00020\u0004H\u0002R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Landroidx/datastore/core/FileStorageConnection;", "T", "Landroidx/datastore/core/StorageConnection;", "file", "Ljava/io/File;", "serializer", "Landroidx/datastore/core/Serializer;", "coordinator", "Landroidx/datastore/core/InterProcessCoordinator;", "onClose", "Lkotlin/Function0;", "", "(Ljava/io/File;Landroidx/datastore/core/Serializer;Landroidx/datastore/core/InterProcessCoordinator;Lkotlin/jvm/functions/Function0;)V", "closed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getCoordinator", "()Landroidx/datastore/core/InterProcessCoordinator;", "transactionMutex", "Lkotlinx/coroutines/sync/Mutex;", "checkNotClosed", "close", "readScope", "R", "block", "Lkotlin/Function3;", "Landroidx/datastore/core/ReadScope;", "", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "locked", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeScope", "Lkotlin/Function2;", "Landroidx/datastore/core/WriteScope;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createParentDirectories", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class FileStorageConnection<T> implements StorageConnection<T> {
    private final java.util.concurrent.atomic.AtomicBoolean closed;
    private final InterProcessCoordinator coordinator;
    private final File file;
    private final Function0<Unit> onClose;
    private final Serializer<T> serializer;
    private final Mutex transactionMutex;

    public FileStorageConnection(File file, Serializer<T> serializer, InterProcessCoordinator coordinator, Function0<Unit> onClose) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(coordinator, "coordinator");
        Intrinsics.checkNotNullParameter(onClose, "onClose");
        this.file = file;
        this.serializer = serializer;
        this.coordinator = coordinator;
        this.onClose = onClose;
        this.closed = new java.util.concurrent.atomic.AtomicBoolean(false);
        this.transactionMutex = MutexKt.Mutex$default(false, 1, null);
    }

    @Override // androidx.datastore.core.StorageConnection
    public InterProcessCoordinator getCoordinator() {
        return this.coordinator;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x008d A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0096 A[Catch: all -> 0x00a8, TRY_ENTER, TRY_LEAVE, TryCatch #4 {all -> 0x00a8, blocks: (B:22:0x0096, B:36:0x00a7, B:35:0x00a2, B:43:0x0053, B:31:0x009c), top: B:42:0x0053, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    @Override // androidx.datastore.core.StorageConnection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public <R> Object readScope(Function3<? super ReadScope<T>, ? super Boolean, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super R> continuation) {
        FileStorageConnection$readScope$1 fileStorageConnection$readScope$1;
        FileStorageConnection$readScope$1 fileStorageConnection$readScope$12;
        FileStorageConnection fileStorageConnection;
        boolean lock;
        FileReadScope $this$use$iv;
        Throwable thrown$iv;
        Object invoke;
        Throwable thrown$iv2;
        Throwable thrown$iv3;
        if (continuation instanceof FileStorageConnection$readScope$1) {
            fileStorageConnection$readScope$1 = (FileStorageConnection$readScope$1) continuation;
            if ((fileStorageConnection$readScope$1.label & Integer.MIN_VALUE) != 0) {
                fileStorageConnection$readScope$1.label -= Integer.MIN_VALUE;
                fileStorageConnection$readScope$12 = fileStorageConnection$readScope$1;
                Object $result = fileStorageConnection$readScope$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fileStorageConnection$readScope$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        fileStorageConnection = this;
                        fileStorageConnection.checkNotClosed();
                        lock = Mutex.DefaultImpls.tryLock$default(fileStorageConnection.transactionMutex, null, 1, null);
                        try {
                            $this$use$iv = new FileReadScope(fileStorageConnection.file, fileStorageConnection.serializer);
                            thrown$iv = null;
                            try {
                                FileReadScope fileReadScope = $this$use$iv;
                                Boolean boxBoolean = Boxing.boxBoolean(lock);
                                fileStorageConnection$readScope$12.L$0 = fileStorageConnection;
                                fileStorageConnection$readScope$12.L$1 = $this$use$iv;
                                fileStorageConnection$readScope$12.Z$0 = lock;
                                fileStorageConnection$readScope$12.label = 1;
                                invoke = function3.invoke(fileReadScope, boxBoolean, fileStorageConnection$readScope$12);
                                if (invoke == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                thrown$iv3 = thrown$iv;
                                try {
                                    $this$use$iv.close();
                                } catch (Throwable th) {
                                    thrown$iv3 = th;
                                }
                                if (thrown$iv3 != null) {
                                    return invoke;
                                }
                                throw thrown$iv3;
                            } catch (Throwable th2) {
                                t$iv = th2;
                                thrown$iv2 = t$iv;
                                try {
                                    $this$use$iv.close();
                                    throw thrown$iv2;
                                } catch (Throwable t$iv) {
                                    ExceptionsKt.addSuppressed(thrown$iv2, t$iv);
                                    throw thrown$iv2;
                                }
                            }
                        } finally {
                            if (lock) {
                                Mutex.DefaultImpls.unlock$default(fileStorageConnection.transactionMutex, null, 1, null);
                            }
                        }
                    case 1:
                        lock = fileStorageConnection$readScope$12.Z$0;
                        $this$use$iv = (Closeable) fileStorageConnection$readScope$12.L$1;
                        fileStorageConnection = (FileStorageConnection) fileStorageConnection$readScope$12.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            invoke = $result;
                            thrown$iv = null;
                            thrown$iv3 = thrown$iv;
                            $this$use$iv.close();
                            if (thrown$iv3 != null) {
                            }
                        } catch (Throwable th3) {
                            t$iv = th3;
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
        fileStorageConnection$readScope$1 = new FileStorageConnection$readScope$1(this, continuation);
        fileStorageConnection$readScope$12 = fileStorageConnection$readScope$1;
        Object $result2 = fileStorageConnection$readScope$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fileStorageConnection$readScope$12.label) {
        }
    }

    /* JADX WARN: Not initialized variable reg: 10, insn: 0x014e: INVOKE 
      (r11v0 ?? I:kotlinx.coroutines.sync.Mutex A[D('$this$withLock_u24default$iv' kotlinx.coroutines.sync.Mutex)])
      (r10 I:java.lang.Object A[D('owner$iv' java.lang.Object)])
     INTERFACE call: kotlinx.coroutines.sync.Mutex.unlock(java.lang.Object):void A[MD:(java.lang.Object):void (m)], block:B:68:0x014e */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x014e: INVOKE 
      (r11 I:kotlinx.coroutines.sync.Mutex A[D('$this$withLock_u24default$iv' kotlinx.coroutines.sync.Mutex)])
      (r10 I:java.lang.Object A[D('owner$iv' java.lang.Object)])
     INTERFACE call: kotlinx.coroutines.sync.Mutex.unlock(java.lang.Object):void A[MD:(java.lang.Object):void (m)], block:B:68:0x014e */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x0142: INVOKE (r2 I:boolean) = (r9 I:java.io.File A[D('scratchFile' java.io.File)]) VIRTUAL call: java.io.File.exists():boolean A[Catch: all -> 0x014d, MD:():boolean (c), TRY_ENTER] (LINE:129), block:B:63:0x0142 */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00e8 A[Catch: IOException -> 0x0141, all -> 0x014d, TRY_ENTER, TryCatch #2 {IOException -> 0x0141, blocks: (B:19:0x00e8, B:21:0x00ee, B:24:0x00f7, B:25:0x0121, B:29:0x012e, B:43:0x0140, B:42:0x013b, B:48:0x00b1), top: B:7:0x0029, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x012e A[Catch: IOException -> 0x0141, all -> 0x014d, TRY_ENTER, TRY_LEAVE, TryCatch #2 {IOException -> 0x0141, blocks: (B:19:0x00e8, B:21:0x00ee, B:24:0x00f7, B:25:0x0121, B:29:0x012e, B:43:0x0140, B:42:0x013b, B:48:0x00b1), top: B:7:0x0029, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002c  */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.io.File] */
    @Override // androidx.datastore.core.StorageConnection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object writeScope(Function2<? super WriteScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        FileStorageConnection$writeScope$1 fileStorageConnection$writeScope$1;
        Mutex $this$withLock_u24default$iv;
        Object owner$iv;
        ?? exists;
        Function2 block;
        FileStorageConnection fileStorageConnection;
        Mutex $this$withLock_u24default$iv2;
        Object owner$iv2;
        File scratchFile;
        Closeable $this$use$iv;
        FileWriteScope it;
        Throwable thrown$iv;
        Throwable thrown$iv2;
        try {
            try {
                if (continuation instanceof FileStorageConnection$writeScope$1) {
                    fileStorageConnection$writeScope$1 = (FileStorageConnection$writeScope$1) continuation;
                    if ((fileStorageConnection$writeScope$1.label & Integer.MIN_VALUE) != 0) {
                        fileStorageConnection$writeScope$1.label -= Integer.MIN_VALUE;
                        Object $result = fileStorageConnection$writeScope$1.result;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch (fileStorageConnection$writeScope$1.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                block = function2;
                                checkNotClosed();
                                createParentDirectories(this.file);
                                Mutex $this$withLock_u24default$iv3 = this.transactionMutex;
                                fileStorageConnection$writeScope$1.L$0 = this;
                                fileStorageConnection$writeScope$1.L$1 = block;
                                fileStorageConnection$writeScope$1.L$2 = $this$withLock_u24default$iv3;
                                fileStorageConnection$writeScope$1.label = 1;
                                if ($this$withLock_u24default$iv3.lock(null, fileStorageConnection$writeScope$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                fileStorageConnection = this;
                                $this$withLock_u24default$iv2 = $this$withLock_u24default$iv3;
                                owner$iv2 = null;
                                scratchFile = new File(fileStorageConnection.file.getAbsolutePath() + ".tmp");
                                $this$use$iv = new FileWriteScope(scratchFile, fileStorageConnection.serializer);
                                try {
                                    it = (FileWriteScope) $this$use$iv;
                                    fileStorageConnection$writeScope$1.L$0 = fileStorageConnection;
                                    fileStorageConnection$writeScope$1.L$1 = $this$withLock_u24default$iv2;
                                    fileStorageConnection$writeScope$1.L$2 = scratchFile;
                                    fileStorageConnection$writeScope$1.L$3 = $this$use$iv;
                                    fileStorageConnection$writeScope$1.label = 2;
                                    if (block.invoke(it, fileStorageConnection$writeScope$1) != coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
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
                                    if (scratchFile.exists() && !FileMoves_androidKt.atomicMoveTo(scratchFile, fileStorageConnection.file)) {
                                        throw new IOException("Unable to rename " + scratchFile + " to " + fileStorageConnection.file + ". This likely means that there are multiple instances of DataStore for this file. Ensure that you are only creating a single instance of datastore for this file.");
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
                            case 1:
                                Mutex $this$withLock_u24default$iv4 = (Mutex) fileStorageConnection$writeScope$1.L$2;
                                block = (Function2) fileStorageConnection$writeScope$1.L$1;
                                FileStorageConnection fileStorageConnection2 = (FileStorageConnection) fileStorageConnection$writeScope$1.L$0;
                                ResultKt.throwOnFailure($result);
                                owner$iv2 = null;
                                $this$withLock_u24default$iv2 = $this$withLock_u24default$iv4;
                                fileStorageConnection = fileStorageConnection2;
                                scratchFile = new File(fileStorageConnection.file.getAbsolutePath() + ".tmp");
                                $this$use$iv = new FileWriteScope(scratchFile, fileStorageConnection.serializer);
                                it = (FileWriteScope) $this$use$iv;
                                fileStorageConnection$writeScope$1.L$0 = fileStorageConnection;
                                fileStorageConnection$writeScope$1.L$1 = $this$withLock_u24default$iv2;
                                fileStorageConnection$writeScope$1.L$2 = scratchFile;
                                fileStorageConnection$writeScope$1.L$3 = $this$use$iv;
                                fileStorageConnection$writeScope$1.label = 2;
                                if (block.invoke(it, fileStorageConnection$writeScope$1) != coroutine_suspended) {
                                }
                                break;
                            case 2:
                                thrown$iv = null;
                                $this$use$iv = (Closeable) fileStorageConnection$writeScope$1.L$3;
                                scratchFile = (File) fileStorageConnection$writeScope$1.L$2;
                                owner$iv2 = null;
                                $this$withLock_u24default$iv2 = (Mutex) fileStorageConnection$writeScope$1.L$1;
                                fileStorageConnection = (FileStorageConnection) fileStorageConnection$writeScope$1.L$0;
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
                switch (fileStorageConnection$writeScope$1.label) {
                }
            } catch (IOException ex) {
                if (exists.exists()) {
                    exists.delete();
                }
                throw ex;
            }
        } catch (Throwable ex2) {
            $this$withLock_u24default$iv.unlock(owner$iv);
            throw ex2;
        }
        fileStorageConnection$writeScope$1 = new FileStorageConnection$writeScope$1(this, continuation);
        Object $result2 = fileStorageConnection$writeScope$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }

    @Override // androidx.datastore.core.Closeable
    public void close() {
        this.closed.set(true);
        this.onClose.invoke();
    }

    private final void checkNotClosed() {
        if (this.closed.get()) {
            throw new IllegalStateException("StorageConnection has already been disposed.".toString());
        }
    }

    private final void createParentDirectories(File $this$createParentDirectories) {
        File parent = $this$createParentDirectories.getCanonicalFile().getParentFile();
        if (parent != null) {
            parent.mkdirs();
            if (!parent.isDirectory()) {
                throw new IOException("Unable to create parent directories of " + $this$createParentDirectories);
            }
        }
    }
}
