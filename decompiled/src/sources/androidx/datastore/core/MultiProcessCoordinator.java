package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.datastore.core.SharedCounter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.apache.commons.lang3.time.DateUtils;

/* compiled from: MultiProcessCoordinator.android.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u0000 42\u00020\u0001:\u00014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\bH\u0002J\u000e\u0010\"\u001a\u00020#H\u0096@¢\u0006\u0002\u0010$J\u000e\u0010%\u001a\u00020#H\u0096@¢\u0006\u0002\u0010$J2\u0010&\u001a\u0002H'\"\u0004\b\u0000\u0010'2\u001c\u0010(\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0*\u0012\u0006\u0012\u0004\u0018\u00010+0)H\u0096@¢\u0006\u0002\u0010,J8\u0010-\u001a\u0002H'\"\u0004\b\u0000\u0010'2\"\u0010(\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020/\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0*\u0012\u0006\u0012\u0004\u0018\u00010+0.H\u0096@¢\u0006\u0002\u00100J:\u00101\u001a\u0002H'\"\u0004\b\u0000\u0010'2$\b\u0004\u0010(\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0*\u0012\u0006\u0012\u0004\u0018\u00010+0.H\u0082H¢\u0006\u0002\u00100J\f\u00102\u001a\u00020\u001d*\u00020\u0005H\u0002J\f\u00103\u001a\u00020\u001d*\u00020\u0005H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0013\u0010\fR\u001b\u0010\u0016\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\u001a\u0004\b\u0019\u0010\u001a*\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u00065"}, d2 = {"Landroidx/datastore/core/MultiProcessCoordinator;", "Landroidx/datastore/core/InterProcessCoordinator;", "context", "Lkotlin/coroutines/CoroutineContext;", "file", "Ljava/io/File;", "(Lkotlin/coroutines/CoroutineContext;Ljava/io/File;)V", "LOCK_ERROR_MESSAGE", "", "LOCK_SUFFIX", "VERSION_SUFFIX", "getFile", "()Ljava/io/File;", "inMemoryMutex", "Lkotlinx/coroutines/sync/Mutex;", "lazySharedCounter", "Lkotlin/Lazy;", "Landroidx/datastore/core/SharedCounter;", "lockFile", "getLockFile", "lockFile$delegate", "Lkotlin/Lazy;", "sharedCounter", "getSharedCounter$delegate", "(Landroidx/datastore/core/MultiProcessCoordinator;)Ljava/lang/Object;", "getSharedCounter", "()Landroidx/datastore/core/SharedCounter;", "updateNotifications", "Lkotlinx/coroutines/flow/Flow;", "", "getUpdateNotifications", "()Lkotlinx/coroutines/flow/Flow;", "fileWithSuffix", "suffix", "getVersion", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "incrementAndGetVersion", "lock", "T", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryLock", "Lkotlin/Function2;", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withLazyCounter", "createIfNotExists", "createParentDirectories", "Companion", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class MultiProcessCoordinator implements InterProcessCoordinator {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DEADLOCK_ERROR_MESSAGE = "Resource deadlock would occur";
    private static final long INITIAL_WAIT_MILLIS = 10;
    private static final long MAX_WAIT_MILLIS = DateUtils.MILLIS_PER_MINUTE;
    private final String LOCK_ERROR_MESSAGE;
    private final String LOCK_SUFFIX;
    private final String VERSION_SUFFIX;
    private final CoroutineContext context;
    private final File file;
    private final Mutex inMemoryMutex;
    private final Lazy<SharedCounter> lazySharedCounter;

    /* renamed from: lockFile$delegate, reason: from kotlin metadata */
    private final Lazy lockFile;
    private final Flow<Unit> updateNotifications;

    public MultiProcessCoordinator(CoroutineContext context, File file) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(file, "file");
        this.context = context;
        this.file = file;
        this.updateNotifications = MulticastFileObserver.INSTANCE.observe(this.file);
        this.LOCK_SUFFIX = ".lock";
        this.VERSION_SUFFIX = ".version";
        this.LOCK_ERROR_MESSAGE = "fcntl failed: EAGAIN";
        this.inMemoryMutex = MutexKt.Mutex$default(false, 1, null);
        this.lockFile = LazyKt.lazy(new Function0<File>() { // from class: androidx.datastore.core.MultiProcessCoordinator$lockFile$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                String str;
                File lockFile;
                MultiProcessCoordinator multiProcessCoordinator = MultiProcessCoordinator.this;
                str = MultiProcessCoordinator.this.LOCK_SUFFIX;
                lockFile = multiProcessCoordinator.fileWithSuffix(str);
                MultiProcessCoordinator.this.createIfNotExists(lockFile);
                return lockFile;
            }
        });
        this.lazySharedCounter = LazyKt.lazy(new Function0<SharedCounter>() { // from class: androidx.datastore.core.MultiProcessCoordinator$lazySharedCounter$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SharedCounter invoke() {
                SharedCounter.INSTANCE.loadLib();
                SharedCounter.Companion companion = SharedCounter.INSTANCE;
                final MultiProcessCoordinator multiProcessCoordinator = MultiProcessCoordinator.this;
                return companion.create$datastore_core_release(new Function0<File>() { // from class: androidx.datastore.core.MultiProcessCoordinator$lazySharedCounter$1.1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final File invoke() {
                        String str;
                        File versionFile;
                        MultiProcessCoordinator multiProcessCoordinator2 = MultiProcessCoordinator.this;
                        str = MultiProcessCoordinator.this.VERSION_SUFFIX;
                        versionFile = multiProcessCoordinator2.fileWithSuffix(str);
                        MultiProcessCoordinator.this.createIfNotExists(versionFile);
                        return versionFile;
                    }
                });
            }
        });
    }

    protected final File getFile() {
        return this.file;
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Flow<Unit> getUpdateNotifications() {
        return this.updateNotifications;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00e6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x010a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00e1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00cd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /* JADX WARN: Type inference failed for: r9v10, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v15, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v8, types: [java.lang.Object] */
    @Override // androidx.datastore.core.InterProcessCoordinator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public <T> Object lock(Function1<? super Continuation<? super T>, ? extends Object> function1, Continuation<? super T> continuation) {
        MultiProcessCoordinator$lock$1 multiProcessCoordinator$lock$1;
        MultiProcessCoordinator multiProcessCoordinator;
        Function1<? super Continuation<? super T>, ? extends Object> function12;
        Mutex mutex;
        Object obj;
        int i;
        FileOutputStream fileOutputStream;
        Throwable th;
        int i2;
        FileLock fileLock;
        int i3;
        ?? r9;
        FileLock fileLock2;
        Object exclusiveFileLockWithRetryIfDeadlock;
        Object invoke;
        FileOutputStream fileOutputStream2;
        if (continuation instanceof MultiProcessCoordinator$lock$1) {
            multiProcessCoordinator$lock$1 = (MultiProcessCoordinator$lock$1) continuation;
            if ((multiProcessCoordinator$lock$1.label & Integer.MIN_VALUE) != 0) {
                multiProcessCoordinator$lock$1.label -= Integer.MIN_VALUE;
                Object obj2 = multiProcessCoordinator$lock$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                Object obj3 = null;
                switch (multiProcessCoordinator$lock$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj2);
                        multiProcessCoordinator = this;
                        Mutex mutex2 = multiProcessCoordinator.inMemoryMutex;
                        multiProcessCoordinator$lock$1.L$0 = multiProcessCoordinator;
                        multiProcessCoordinator$lock$1.L$1 = function1;
                        multiProcessCoordinator$lock$1.L$2 = mutex2;
                        multiProcessCoordinator$lock$1.label = 1;
                        if (mutex2.lock(null, multiProcessCoordinator$lock$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        function12 = function1;
                        mutex = mutex2;
                        obj = null;
                        i = 0;
                        try {
                            fileOutputStream = new FileOutputStream(multiProcessCoordinator.getLockFile());
                            try {
                                FileOutputStream fileOutputStream3 = fileOutputStream;
                                i2 = 0;
                                fileLock = null;
                                try {
                                    Companion companion = INSTANCE;
                                    multiProcessCoordinator$lock$1.L$0 = function12;
                                    multiProcessCoordinator$lock$1.L$1 = mutex;
                                    multiProcessCoordinator$lock$1.L$2 = fileOutputStream;
                                    multiProcessCoordinator$lock$1.label = 2;
                                    exclusiveFileLockWithRetryIfDeadlock = companion.getExclusiveFileLockWithRetryIfDeadlock(fileOutputStream3, multiProcessCoordinator$lock$1);
                                    if (exclusiveFileLockWithRetryIfDeadlock != coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    r9 = fileOutputStream;
                                    try {
                                        fileLock2 = (FileLock) exclusiveFileLockWithRetryIfDeadlock;
                                        try {
                                            multiProcessCoordinator$lock$1.L$0 = mutex;
                                            multiProcessCoordinator$lock$1.L$1 = r9;
                                            multiProcessCoordinator$lock$1.L$2 = fileLock2;
                                            multiProcessCoordinator$lock$1.label = 3;
                                            invoke = function12.invoke(multiProcessCoordinator$lock$1);
                                            fileOutputStream2 = r9;
                                            if (invoke == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            if (fileLock2 != null) {
                                                try {
                                                    fileLock2.release();
                                                } catch (Throwable th2) {
                                                    th = th2;
                                                    fileOutputStream = fileOutputStream2;
                                                    try {
                                                        throw th;
                                                    } finally {
                                                    }
                                                }
                                            }
                                            try {
                                                kotlin.io.CloseableKt.closeFinally(fileOutputStream2, null);
                                                mutex.unlock(obj);
                                                return invoke;
                                            } catch (Throwable th3) {
                                                th = th3;
                                                mutex.unlock(obj);
                                                throw th;
                                            }
                                        } catch (Throwable th4) {
                                            th = th4;
                                            obj3 = obj;
                                            i3 = i;
                                            if (fileLock2 != null) {
                                            }
                                            throw th;
                                        }
                                    } catch (Throwable th5) {
                                        th = th5;
                                        fileLock2 = fileLock;
                                        obj3 = obj;
                                        i3 = i;
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                    obj3 = obj;
                                    i3 = 0;
                                    r9 = fileOutputStream;
                                    fileLock2 = null;
                                    if (fileLock2 != null) {
                                    }
                                    throw th;
                                }
                            } catch (Throwable th7) {
                                th = th7;
                                throw th;
                            }
                        } catch (Throwable th8) {
                            th = th8;
                            mutex.unlock(obj);
                            throw th;
                        }
                        break;
                    case 1:
                        obj = null;
                        Mutex mutex3 = (Mutex) multiProcessCoordinator$lock$1.L$2;
                        Function1<? super Continuation<? super T>, ? extends Object> function13 = (Function1) multiProcessCoordinator$lock$1.L$1;
                        multiProcessCoordinator = (MultiProcessCoordinator) multiProcessCoordinator$lock$1.L$0;
                        ResultKt.throwOnFailure(obj2);
                        mutex = mutex3;
                        function12 = function13;
                        i = 0;
                        fileOutputStream = new FileOutputStream(multiProcessCoordinator.getLockFile());
                        FileOutputStream fileOutputStream32 = fileOutputStream;
                        i2 = 0;
                        fileLock = null;
                        Companion companion2 = INSTANCE;
                        multiProcessCoordinator$lock$1.L$0 = function12;
                        multiProcessCoordinator$lock$1.L$1 = mutex;
                        multiProcessCoordinator$lock$1.L$2 = fileOutputStream;
                        multiProcessCoordinator$lock$1.label = 2;
                        exclusiveFileLockWithRetryIfDeadlock = companion2.getExclusiveFileLockWithRetryIfDeadlock(fileOutputStream32, multiProcessCoordinator$lock$1);
                        if (exclusiveFileLockWithRetryIfDeadlock != coroutine_suspended) {
                        }
                        break;
                    case 2:
                        i3 = 0;
                        r9 = (java.io.Closeable) multiProcessCoordinator$lock$1.L$2;
                        mutex = (Mutex) multiProcessCoordinator$lock$1.L$1;
                        function12 = (Function1) multiProcessCoordinator$lock$1.L$0;
                        try {
                            ResultKt.throwOnFailure(obj2);
                            exclusiveFileLockWithRetryIfDeadlock = obj2;
                            fileLock = null;
                            i2 = 0;
                            i = 0;
                            obj = null;
                            r9 = r9;
                            fileLock2 = (FileLock) exclusiveFileLockWithRetryIfDeadlock;
                            multiProcessCoordinator$lock$1.L$0 = mutex;
                            multiProcessCoordinator$lock$1.L$1 = r9;
                            multiProcessCoordinator$lock$1.L$2 = fileLock2;
                            multiProcessCoordinator$lock$1.label = 3;
                            invoke = function12.invoke(multiProcessCoordinator$lock$1);
                            fileOutputStream2 = r9;
                            if (invoke == coroutine_suspended) {
                            }
                            if (fileLock2 != null) {
                            }
                            kotlin.io.CloseableKt.closeFinally(fileOutputStream2, null);
                            mutex.unlock(obj);
                            return invoke;
                        } catch (Throwable th9) {
                            th = th9;
                            fileLock2 = null;
                            obj3 = null;
                            if (fileLock2 != null) {
                            }
                            throw th;
                        }
                    case 3:
                        i3 = 0;
                        fileLock2 = (FileLock) multiProcessCoordinator$lock$1.L$2;
                        r9 = (java.io.Closeable) multiProcessCoordinator$lock$1.L$1;
                        mutex = (Mutex) multiProcessCoordinator$lock$1.L$0;
                        try {
                            ResultKt.throwOnFailure(obj2);
                            invoke = obj2;
                            obj = null;
                            fileOutputStream2 = r9;
                            if (fileLock2 != null) {
                            }
                            kotlin.io.CloseableKt.closeFinally(fileOutputStream2, null);
                            mutex.unlock(obj);
                            return invoke;
                        } catch (Throwable th10) {
                            th = th10;
                            if (fileLock2 != null) {
                                try {
                                    fileLock2.release();
                                } catch (Throwable th11) {
                                    fileOutputStream = r9;
                                    obj = obj3;
                                    th = th11;
                                    throw th;
                                }
                            }
                            throw th;
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        multiProcessCoordinator$lock$1 = new MultiProcessCoordinator$lock$1(this, continuation);
        Object obj22 = multiProcessCoordinator$lock$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Object obj32 = null;
        switch (multiProcessCoordinator$lock$1.label) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0120 A[Catch: all -> 0x00f6, TRY_LEAVE, TryCatch #10 {all -> 0x00f6, blocks: (B:93:0x00ed, B:97:0x0107, B:99:0x010d, B:105:0x0120), top: B:92:0x00ed }] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x014b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0185 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0107 A[Catch: all -> 0x00f6, TryCatch #10 {all -> 0x00f6, blocks: (B:93:0x00ed, B:97:0x0107, B:99:0x010d, B:105:0x0120), top: B:92:0x00ed }] */
    /* JADX WARN: Type inference failed for: r10v9, types: [java.io.Closeable] */
    @Override // androidx.datastore.core.InterProcessCoordinator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public <T> Object tryLock(Function2<? super Boolean, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        MultiProcessCoordinator$tryLock$1 multiProcessCoordinator$tryLock$1;
        Mutex mutex;
        boolean z;
        Mutex mutex2;
        Object invoke;
        Throwable th;
        int i;
        boolean z2;
        int i2;
        FileInputStream fileInputStream;
        FileLock fileLock;
        boolean z3;
        boolean z4;
        FileLock fileLock2;
        boolean z5;
        Object invoke2;
        FileInputStream fileInputStream2;
        if (continuation instanceof MultiProcessCoordinator$tryLock$1) {
            multiProcessCoordinator$tryLock$1 = (MultiProcessCoordinator$tryLock$1) continuation;
            if ((multiProcessCoordinator$tryLock$1.label & Integer.MIN_VALUE) != 0) {
                multiProcessCoordinator$tryLock$1.label -= Integer.MIN_VALUE;
                Object obj = multiProcessCoordinator$tryLock$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                Object obj2 = null;
                switch (multiProcessCoordinator$tryLock$1.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        mutex = this.inMemoryMutex;
                        boolean tryLock = mutex.tryLock(null);
                        if (tryLock) {
                            try {
                                FileInputStream fileInputStream3 = new FileInputStream(getLockFile());
                                try {
                                    try {
                                        fileLock2 = fileInputStream3.getChannel().tryLock(0L, Long.MAX_VALUE, true);
                                        z4 = true;
                                    } catch (IOException e) {
                                        try {
                                            String message = e.getMessage();
                                            if (message != null) {
                                                try {
                                                    if (StringsKt.startsWith$default(message, this.LOCK_ERROR_MESSAGE, false, 2, (Object) null)) {
                                                        z3 = true;
                                                        if (z3) {
                                                            String message2 = e.getMessage();
                                                            if (message2 != null) {
                                                                z4 = true;
                                                                if (StringsKt.startsWith$default(message2, DEADLOCK_ERROR_MESSAGE, false, 2, (Object) null)) {
                                                                    z5 = true;
                                                                    if (z5) {
                                                                        throw e;
                                                                    }
                                                                }
                                                            } else {
                                                                z4 = true;
                                                            }
                                                            z5 = false;
                                                            if (z5) {
                                                            }
                                                        } else {
                                                            z4 = true;
                                                        }
                                                        fileLock2 = null;
                                                    }
                                                } catch (Throwable th2) {
                                                    th = th2;
                                                    obj2 = null;
                                                    i = 0;
                                                    z2 = tryLock;
                                                    i2 = 0;
                                                    fileInputStream = fileInputStream3;
                                                    fileLock = null;
                                                    if (fileLock != null) {
                                                    }
                                                    throw th;
                                                }
                                            }
                                            z3 = false;
                                            if (z3) {
                                            }
                                            fileLock2 = null;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            obj2 = null;
                                            i = 0;
                                            z2 = tryLock;
                                            i2 = 0;
                                            fileInputStream = fileInputStream3;
                                            fileLock = null;
                                        }
                                    } catch (Throwable th4) {
                                        th = th4;
                                        obj2 = null;
                                        i = 0;
                                        z2 = tryLock;
                                        i2 = 0;
                                        fileInputStream = fileInputStream3;
                                        fileLock = null;
                                        if (fileLock != null) {
                                        }
                                        throw th;
                                    }
                                    try {
                                        Boolean boxBoolean = Boxing.boxBoolean(fileLock2 != null ? z4 : false);
                                        multiProcessCoordinator$tryLock$1.L$0 = mutex;
                                        multiProcessCoordinator$tryLock$1.L$1 = fileInputStream3;
                                        multiProcessCoordinator$tryLock$1.L$2 = fileLock2;
                                        multiProcessCoordinator$tryLock$1.Z$0 = tryLock;
                                        multiProcessCoordinator$tryLock$1.label = 2;
                                        invoke2 = function2.invoke(boxBoolean, multiProcessCoordinator$tryLock$1);
                                        if (invoke2 == coroutine_suspended) {
                                            return coroutine_suspended;
                                        }
                                        obj2 = null;
                                        i = 0;
                                        z2 = tryLock;
                                        i2 = 0;
                                        fileInputStream2 = fileInputStream3;
                                        fileLock = fileLock2;
                                        if (fileLock != null) {
                                            try {
                                                fileLock.release();
                                            } catch (Throwable th5) {
                                                mutex2 = mutex;
                                                fileInputStream3 = fileInputStream2;
                                                z = z2;
                                                obj2 = obj2;
                                                th = th5;
                                                try {
                                                    throw th;
                                                } catch (Throwable th6) {
                                                    try {
                                                        kotlin.io.CloseableKt.closeFinally(fileInputStream3, th);
                                                        throw th6;
                                                    } catch (Throwable th7) {
                                                        th = th7;
                                                    }
                                                }
                                            }
                                        }
                                        try {
                                            kotlin.io.CloseableKt.closeFinally(fileInputStream2, null);
                                            if (z2) {
                                                mutex.unlock(obj2);
                                            }
                                            return invoke2;
                                        } catch (Throwable th8) {
                                            th = th8;
                                            mutex2 = mutex;
                                            z = z2;
                                        }
                                    } catch (Throwable th9) {
                                        th = th9;
                                        obj2 = null;
                                        i = 0;
                                        z2 = tryLock;
                                        i2 = 0;
                                        fileInputStream = fileInputStream3;
                                        fileLock = fileLock2;
                                        if (fileLock != null) {
                                            try {
                                                fileLock.release();
                                            } catch (Throwable th10) {
                                                th = th10;
                                                z = z2;
                                                fileInputStream3 = fileInputStream;
                                                mutex2 = mutex;
                                                throw th;
                                            }
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th11) {
                                    th = th11;
                                    obj2 = null;
                                    z = tryLock;
                                    mutex2 = mutex;
                                    throw th;
                                }
                            } catch (Throwable th12) {
                                th = th12;
                                obj2 = null;
                                z = tryLock;
                                mutex2 = mutex;
                            }
                        } else {
                            try {
                                Boolean boxBoolean2 = Boxing.boxBoolean(false);
                                multiProcessCoordinator$tryLock$1.L$0 = mutex;
                                multiProcessCoordinator$tryLock$1.Z$0 = tryLock;
                                multiProcessCoordinator$tryLock$1.label = 1;
                                invoke = function2.invoke(boxBoolean2, multiProcessCoordinator$tryLock$1);
                                if (invoke == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                obj2 = null;
                                z = tryLock;
                                mutex2 = mutex;
                                if (z) {
                                    mutex2.unlock(obj2);
                                }
                                return invoke;
                            } catch (Throwable th13) {
                                th = th13;
                                obj2 = null;
                                z = tryLock;
                                mutex2 = mutex;
                            }
                        }
                        if (z) {
                            mutex2.unlock(obj2);
                        }
                        throw th;
                    case 1:
                        z = multiProcessCoordinator$tryLock$1.Z$0;
                        mutex2 = (Mutex) multiProcessCoordinator$tryLock$1.L$0;
                        try {
                            ResultKt.throwOnFailure(obj);
                            invoke = obj;
                            if (z) {
                            }
                            return invoke;
                        } catch (Throwable th14) {
                            th = th14;
                            break;
                        }
                        break;
                    case 2:
                        i2 = 0;
                        i = 0;
                        z2 = multiProcessCoordinator$tryLock$1.Z$0;
                        fileLock = (FileLock) multiProcessCoordinator$tryLock$1.L$2;
                        ?? r10 = (java.io.Closeable) multiProcessCoordinator$tryLock$1.L$1;
                        mutex = (Mutex) multiProcessCoordinator$tryLock$1.L$0;
                        try {
                            ResultKt.throwOnFailure(obj);
                            invoke2 = obj;
                            fileInputStream2 = r10;
                            if (fileLock != null) {
                            }
                            kotlin.io.CloseableKt.closeFinally(fileInputStream2, null);
                            if (z2) {
                            }
                            return invoke2;
                        } catch (Throwable th15) {
                            th = th15;
                            fileInputStream = r10;
                            if (fileLock != null) {
                            }
                            throw th;
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        multiProcessCoordinator$tryLock$1 = new MultiProcessCoordinator$tryLock$1(this, continuation);
        Object obj3 = multiProcessCoordinator$tryLock$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Object obj22 = null;
        switch (multiProcessCoordinator$tryLock$1.label) {
        }
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Object getVersion(Continuation<? super Integer> continuation) {
        if (!this.lazySharedCounter.isInitialized()) {
            return BuildersKt.withContext(this.context, new MultiProcessCoordinator$getVersion$$inlined$withLazyCounter$1(this, null), continuation);
        }
        SharedCounter it = getSharedCounter();
        return Boxing.boxInt(it.getValue());
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Object incrementAndGetVersion(Continuation<? super Integer> continuation) {
        if (!this.lazySharedCounter.isInitialized()) {
            return BuildersKt.withContext(this.context, new MultiProcessCoordinator$incrementAndGetVersion$$inlined$withLazyCounter$1(this, null), continuation);
        }
        SharedCounter it = getSharedCounter();
        return Boxing.boxInt(it.incrementAndGetValue());
    }

    private final File getLockFile() {
        return (File) this.lockFile.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SharedCounter getSharedCounter() {
        return this.lazySharedCounter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File fileWithSuffix(String suffix) {
        return new File(this.file.getAbsolutePath() + suffix);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createIfNotExists(File $this$createIfNotExists) {
        createParentDirectories($this$createIfNotExists);
        if (!$this$createIfNotExists.exists()) {
            $this$createIfNotExists.createNewFile();
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

    private final <T> Object withLazyCounter(Function2<? super SharedCounter, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        if (this.lazySharedCounter.isInitialized()) {
            return function2.invoke(getSharedCounter(), continuation);
        }
        CoroutineContext coroutineContext = this.context;
        MultiProcessCoordinator$withLazyCounter$2 multiProcessCoordinator$withLazyCounter$2 = new MultiProcessCoordinator$withLazyCounter$2(function2, this, null);
        InlineMarker.mark(0);
        Object withContext = BuildersKt.withContext(coroutineContext, multiProcessCoordinator$withLazyCounter$2, continuation);
        InlineMarker.mark(1);
        return withContext;
    }

    /* compiled from: MultiProcessCoordinator.android.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0082@¢\u0006\u0002\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/datastore/core/MultiProcessCoordinator$Companion;", "", "()V", "DEADLOCK_ERROR_MESSAGE", "", "INITIAL_WAIT_MILLIS", "", "MAX_WAIT_MILLIS", "getExclusiveFileLockWithRetryIfDeadlock", "Ljava/nio/channels/FileLock;", "lockFileStream", "Ljava/io/FileOutputStream;", "(Ljava/io/FileOutputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:11:0x002e  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x008a  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0038  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0083 -> B:12:0x0086). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object getExclusiveFileLockWithRetryIfDeadlock(FileOutputStream lockFileStream, Continuation<? super FileLock> continuation) {
            MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1 multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1;
            MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1 multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$12;
            long backoff;
            if (continuation instanceof MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1) {
                multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1 = (MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1) continuation;
                if ((multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1.label & Integer.MIN_VALUE) != 0) {
                    multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1.label -= Integer.MIN_VALUE;
                    multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$12 = multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1;
                    Object $result = multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$12.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$12.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);
                            backoff = MultiProcessCoordinator.INITIAL_WAIT_MILLIS;
                            if (backoff > MultiProcessCoordinator.MAX_WAIT_MILLIS) {
                                try {
                                } catch (IOException ex) {
                                    String message = ex.getMessage();
                                    boolean z = false;
                                    if (message != null && StringsKt.contains$default((CharSequence) message, (CharSequence) MultiProcessCoordinator.DEADLOCK_ERROR_MESSAGE, false, 2, (Object) null)) {
                                        z = true;
                                    }
                                    if (!z) {
                                        throw ex;
                                    }
                                    multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$12.L$0 = lockFileStream;
                                    multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$12.J$0 = backoff;
                                    multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$12.label = 1;
                                    if (DelayKt.delay(backoff, multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$12) == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                }
                                FileLock lock = lockFileStream.getChannel().lock(0L, Long.MAX_VALUE, false);
                                Intrinsics.checkNotNullExpressionValue(lock, "lockFileStream.getChanne…LUE, /* shared= */ false)");
                                return lock;
                            }
                            FileLock lock2 = lockFileStream.getChannel().lock(0L, Long.MAX_VALUE, false);
                            Intrinsics.checkNotNullExpressionValue(lock2, "lockFileStream.getChanne…LUE, /* shared= */ false)");
                            return lock2;
                        case 1:
                            backoff = multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$12.J$0;
                            lockFileStream = (FileOutputStream) multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$12.L$0;
                            ResultKt.throwOnFailure($result);
                            backoff *= 2;
                            if (backoff > MultiProcessCoordinator.MAX_WAIT_MILLIS) {
                            }
                            break;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }
            multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1 = new MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1(this, continuation);
            multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$12 = multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1;
            Object $result2 = multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$12.result;
            Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (multiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$12.label) {
            }
        }
    }
}
