package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.File;
import java.io.FileInputStream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileStorage.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0004J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\u000e\u0010\u0011\u001a\u00028\u0000H\u0096@¢\u0006\u0002\u0010\u0012R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Landroidx/datastore/core/FileReadScope;", "T", "Landroidx/datastore/core/ReadScope;", "file", "Ljava/io/File;", "serializer", "Landroidx/datastore/core/Serializer;", "(Ljava/io/File;Landroidx/datastore/core/Serializer;)V", "closed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getFile", "()Ljava/io/File;", "getSerializer", "()Landroidx/datastore/core/Serializer;", "checkNotClosed", "", "close", "readData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public class FileReadScope<T> implements ReadScope<T> {
    private final java.util.concurrent.atomic.AtomicBoolean closed;
    private final File file;
    private final Serializer<T> serializer;

    @Override // androidx.datastore.core.ReadScope
    public Object readData(Continuation<? super T> continuation) {
        return readData$suspendImpl(this, continuation);
    }

    public FileReadScope(File file, Serializer<T> serializer) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        this.file = file;
        this.serializer = serializer;
        this.closed = new java.util.concurrent.atomic.AtomicBoolean(false);
    }

    protected final File getFile() {
        return this.file;
    }

    protected final Serializer<T> getSerializer() {
        return this.serializer;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(3:(2:3|(4:5|6|7|8))|7|8) */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0088, code lost:
    
        if (((androidx.datastore.core.FileReadScope) r4).file.exists() != false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x008a, code lost:
    
        r10 = new java.io.FileInputStream(((androidx.datastore.core.FileReadScope) r4).file);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0093, code lost:
    
        r11.L$0 = r10;
        r11.L$1 = null;
        r11.label = 2;
        r6 = ((androidx.datastore.core.FileReadScope) r4).serializer.readFrom(r10, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a7, code lost:
    
        if (r6 == r1) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a9, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00aa, code lost:
    
        r1 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00b1, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00b2, code lost:
    
        r1 = r10;
        r10 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00c1, code lost:
    
        return ((androidx.datastore.core.FileReadScope) r4).serializer.getDefaultValue();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.io.Closeable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static /* synthetic */ <T> Object readData$suspendImpl(FileReadScope<T> fileReadScope, Continuation<? super T> continuation) {
        FileReadScope$readData$1 fileReadScope$readData$1;
        ?? r2;
        FileReadScope<T> fileReadScope2;
        Object readFrom;
        try {
            if (continuation instanceof FileReadScope$readData$1) {
                fileReadScope$readData$1 = (FileReadScope$readData$1) continuation;
                if ((fileReadScope$readData$1.label & Integer.MIN_VALUE) != 0) {
                    fileReadScope$readData$1.label -= Integer.MIN_VALUE;
                    FileReadScope$readData$1 fileReadScope$readData$12 = fileReadScope$readData$1;
                    Object obj = fileReadScope$readData$12.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    r2 = fileReadScope$readData$12.label;
                    switch (r2) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            fileReadScope2 = fileReadScope;
                            fileReadScope2.checkNotClosed();
                            FileInputStream fileInputStream = new FileInputStream(((FileReadScope) fileReadScope2).file);
                            fileReadScope$readData$12.L$0 = fileReadScope2;
                            fileReadScope$readData$12.L$1 = fileInputStream;
                            fileReadScope$readData$12.label = 1;
                            readFrom = ((FileReadScope) fileReadScope2).serializer.readFrom(fileInputStream, fileReadScope$readData$12);
                            if (readFrom == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            r2 = fileInputStream;
                            kotlin.io.CloseableKt.closeFinally(r2, null);
                            return readFrom;
                        case 1:
                            java.io.Closeable closeable = (java.io.Closeable) fileReadScope$readData$12.L$1;
                            fileReadScope2 = (FileReadScope) fileReadScope$readData$12.L$0;
                            ResultKt.throwOnFailure(obj);
                            readFrom = obj;
                            r2 = closeable;
                            kotlin.io.CloseableKt.closeFinally(r2, null);
                            return readFrom;
                        case 2:
                            java.io.Closeable closeable2 = (java.io.Closeable) fileReadScope$readData$12.L$0;
                            try {
                                ResultKt.throwOnFailure(obj);
                                Object readFrom2 = obj;
                                kotlin.io.CloseableKt.closeFinally(closeable2, null);
                                return readFrom2;
                            } catch (Throwable th) {
                                Throwable th2 = th;
                                try {
                                    throw th2;
                                } catch (Throwable th3) {
                                    kotlin.io.CloseableKt.closeFinally(closeable2, th2);
                                    throw th3;
                                }
                            }
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }
            switch (r2) {
            }
        } finally {
        }
        fileReadScope$readData$1 = new FileReadScope$readData$1(fileReadScope, continuation);
        FileReadScope$readData$1 fileReadScope$readData$122 = fileReadScope$readData$1;
        Object obj2 = fileReadScope$readData$122.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        r2 = fileReadScope$readData$122.label;
    }

    @Override // androidx.datastore.core.Closeable
    public void close() {
        this.closed.set(true);
    }

    protected final void checkNotClosed() {
        if (this.closed.get()) {
            throw new IllegalStateException("This scope has already been closed.".toString());
        }
    }
}
