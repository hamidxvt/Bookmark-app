package androidx.datastore.core.okio;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.datastore.core.ReadScope;
import java.io.Closeable;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;
import okio.FileSystem;
import okio.Okio;
import okio.Path;

/* compiled from: OkioStorage.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0002\u0010\tJ\b\u0010\u0012\u001a\u00020\u0013H\u0004J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u000e\u0010\u0015\u001a\u00028\u0000H\u0096@¢\u0006\u0002\u0010\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0005\u001a\u00020\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0017"}, d2 = {"Landroidx/datastore/core/okio/OkioReadScope;", "T", "Landroidx/datastore/core/ReadScope;", "fileSystem", "Lokio/FileSystem;", "path", "Lokio/Path;", "serializer", "Landroidx/datastore/core/okio/OkioSerializer;", "(Lokio/FileSystem;Lokio/Path;Landroidx/datastore/core/okio/OkioSerializer;)V", "closed", "Landroidx/datastore/core/okio/AtomicBoolean;", "getFileSystem", "()Lokio/FileSystem;", "getPath", "()Lokio/Path;", "getSerializer", "()Landroidx/datastore/core/okio/OkioSerializer;", "checkClose", "", "close", "readData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core-okio"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public class OkioReadScope<T> implements ReadScope<T> {
    private final AtomicBoolean closed;
    private final FileSystem fileSystem;
    private final Path path;
    private final OkioSerializer<T> serializer;

    @Override // androidx.datastore.core.ReadScope
    public Object readData(Continuation<? super T> continuation) {
        return readData$suspendImpl(this, continuation);
    }

    public OkioReadScope(FileSystem fileSystem, Path path, OkioSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        this.fileSystem = fileSystem;
        this.path = path;
        this.serializer = serializer;
        this.closed = new AtomicBoolean(false);
    }

    protected final FileSystem getFileSystem() {
        return this.fileSystem;
    }

    protected final Path getPath() {
        return this.path;
    }

    protected final OkioSerializer<T> getSerializer() {
        return this.serializer;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:0|1|(2:3|(4:5|6|7|8))|89|6|7|8) */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0113, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0117, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0119, code lost:
    
        kotlin.ExceptionsKt.addSuppressed(r4, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00c9, code lost:
    
        if (r7.fileSystem.exists(r7.path) != false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00cb, code lost:
    
        r14 = r7.fileSystem;
        r2 = r7.path;
        r5 = okio.Okio.buffer(r14.source(r2));
        r6 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x00de, code lost:
    
        r8 = r5;
        r11 = r7.serializer;
        r15.L$0 = r5;
        r15.L$1 = null;
        r15.label = 2;
        r3 = r11.readFrom(r8, r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x00f1, code lost:
    
        if (r3 == r1) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00f3, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x00f4, code lost:
    
        r4 = r3;
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x010a, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x010b, code lost:
    
        r3 = null;
        r2 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:?, code lost:
    
        return r7.serializer.getDefaultValue();
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0113 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b9 A[Catch: FileNotFoundException -> 0x00c0, TryCatch #0 {FileNotFoundException -> 0x00c0, blocks: (B:45:0x00b9, B:47:0x00bf, B:63:0x00b1, B:65:0x0063, B:60:0x00ab), top: B:7:0x0023, inners: #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00bf A[Catch: FileNotFoundException -> 0x00c0, TRY_LEAVE, TryCatch #0 {FileNotFoundException -> 0x00c0, blocks: (B:45:0x00b9, B:47:0x00bf, B:63:0x00b1, B:65:0x0063, B:60:0x00ab), top: B:7:0x0023, inners: #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0099 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static /* synthetic */ <T> Object readData$suspendImpl(OkioReadScope<T> okioReadScope, Continuation<? super T> continuation) {
        OkioReadScope$readData$1 okioReadScope$readData$1;
        OkioReadScope$readData$1 okioReadScope$readData$12;
        Object result$iv$iv;
        Throwable thrown$iv$iv;
        OkioReadScope $this;
        Closeable $this$use$iv$iv;
        Object result$iv$iv2;
        Throwable t$iv$iv;
        Throwable t$iv$iv2;
        int i;
        Object readFrom;
        OkioReadScope $this2;
        Closeable $this$use$iv$iv2;
        Closeable $this$use$iv$iv3;
        if (continuation instanceof OkioReadScope$readData$1) {
            okioReadScope$readData$1 = (OkioReadScope$readData$1) continuation;
            if ((okioReadScope$readData$1.label & Integer.MIN_VALUE) != 0) {
                okioReadScope$readData$1.label -= Integer.MIN_VALUE;
                okioReadScope$readData$12 = okioReadScope$readData$1;
                Object $result = okioReadScope$readData$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                Object result$iv$iv3 = null;
                switch (okioReadScope$readData$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        $this = okioReadScope;
                        $this.checkClose();
                        FileSystem this_$iv = $this.fileSystem;
                        Path file$iv = $this.path;
                        $this$use$iv$iv = Okio.buffer(this_$iv.source(file$iv));
                        result$iv$iv2 = null;
                        t$iv$iv = null;
                        try {
                            BufferedSource $this$readData_u24lambda_u240 = (BufferedSource) $this$use$iv$iv;
                            OkioSerializer<T> okioSerializer = $this.serializer;
                            okioReadScope$readData$12.L$0 = $this;
                            okioReadScope$readData$12.L$1 = $this$use$iv$iv;
                            okioReadScope$readData$12.label = 1;
                            readFrom = okioSerializer.readFrom($this$readData_u24lambda_u240, okioReadScope$readData$12);
                        } catch (Throwable thrown$iv$iv2) {
                            t$iv$iv2 = thrown$iv$iv2;
                            i = 0;
                            Throwable thrown$iv$iv3 = t$iv$iv2;
                            if ($this$use$iv$iv != null) {
                                try {
                                    $this$use$iv$iv.close();
                                } catch (Throwable t$iv$iv3) {
                                    ExceptionsKt.addSuppressed(thrown$iv$iv3, t$iv$iv3);
                                }
                            }
                            t$iv$iv = thrown$iv$iv3;
                            if (t$iv$iv == null) {
                            }
                        }
                        if (readFrom == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $this2 = $this;
                        $this$use$iv$iv2 = $this$use$iv$iv;
                        $this$use$iv$iv3 = null;
                        result$iv$iv2 = readFrom;
                        if ($this$use$iv$iv2 != null) {
                            try {
                                $this$use$iv$iv2.close();
                            } catch (Throwable th) {
                                t$iv$iv = th;
                            }
                        }
                        if (t$iv$iv == null) {
                            throw t$iv$iv;
                        }
                        Intrinsics.checkNotNull(result$iv$iv2);
                        return result$iv$iv2;
                    case 1:
                        i = 0;
                        $this$use$iv$iv = (Closeable) okioReadScope$readData$12.L$1;
                        $this = (OkioReadScope) okioReadScope$readData$12.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            readFrom = $result;
                            $this2 = $this;
                            t$iv$iv = null;
                            $this$use$iv$iv2 = $this$use$iv$iv;
                            $this$use$iv$iv3 = null;
                            result$iv$iv2 = readFrom;
                            if ($this$use$iv$iv2 != null) {
                            }
                        } catch (Throwable th2) {
                            t$iv$iv2 = th2;
                            result$iv$iv2 = null;
                            Throwable thrown$iv$iv32 = t$iv$iv2;
                            if ($this$use$iv$iv != null) {
                            }
                            t$iv$iv = thrown$iv$iv32;
                            if (t$iv$iv == null) {
                            }
                        }
                        if (t$iv$iv == null) {
                        }
                        break;
                    case 2:
                        BufferedSource $this$use$iv$iv4 = (Closeable) okioReadScope$readData$12.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            Throwable thrown$iv$iv4 = null;
                            Object obj = $result;
                            thrown$iv$iv = thrown$iv$iv4;
                            result$iv$iv = obj;
                            if ($this$use$iv$iv4 != null) {
                                try {
                                    $this$use$iv$iv4.close();
                                } catch (Throwable th3) {
                                    thrown$iv$iv = th3;
                                }
                            }
                        } catch (Throwable th4) {
                            Throwable t$iv$iv4 = th4;
                            Throwable thrown$iv$iv5 = t$iv$iv4;
                            if ($this$use$iv$iv4 != null) {
                            }
                            result$iv$iv = result$iv$iv3;
                            thrown$iv$iv = thrown$iv$iv5;
                            if (thrown$iv$iv != null) {
                            }
                        }
                        if (thrown$iv$iv != null) {
                            throw thrown$iv$iv;
                        }
                        Intrinsics.checkNotNull(result$iv$iv);
                        return result$iv$iv;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        okioReadScope$readData$1 = new OkioReadScope$readData$1(okioReadScope, continuation);
        okioReadScope$readData$12 = okioReadScope$readData$1;
        Object $result2 = okioReadScope$readData$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        Object result$iv$iv32 = null;
        switch (okioReadScope$readData$12.label) {
        }
    }

    @Override // androidx.datastore.core.Closeable
    public void close() {
        this.closed.set(true);
    }

    protected final void checkClose() {
        if (this.closed.get()) {
            throw new IllegalStateException("This scope has already been closed.".toString());
        }
    }
}
