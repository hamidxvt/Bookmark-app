package androidx.datastore.core.okio;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.datastore.core.WriteScope;
import java.io.Closeable;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;
import okio.FileHandle;
import okio.FileSystem;
import okio.Okio;
import okio.Path;

/* compiled from: OkioStorage.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0000H\u0096@¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/datastore/core/okio/OkioWriteScope;", "T", "Landroidx/datastore/core/okio/OkioReadScope;", "Landroidx/datastore/core/WriteScope;", "fileSystem", "Lokio/FileSystem;", "path", "Lokio/Path;", "serializer", "Landroidx/datastore/core/okio/OkioSerializer;", "(Lokio/FileSystem;Lokio/Path;Landroidx/datastore/core/okio/OkioSerializer;)V", "writeData", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core-okio"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class OkioWriteScope<T> extends OkioReadScope<T> implements WriteScope<T> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OkioWriteScope(FileSystem fileSystem, Path path, OkioSerializer<T> serializer) {
        super(fileSystem, path, serializer);
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:0|1|(2:3|(4:5|6|7|8))|72|6|7|8) */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00d5, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00d6, code lost:
    
        r6 = r4;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00db A[Catch: all -> 0x00d5, TRY_LEAVE, TryCatch #2 {all -> 0x00d5, blocks: (B:19:0x00db, B:31:0x00f0, B:47:0x00d1, B:43:0x00c9), top: B:7:0x0029, inners: #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00f0 A[Catch: all -> 0x00d5, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x00d5, blocks: (B:19:0x00db, B:31:0x00f0, B:47:0x00d1, B:43:0x00c9), top: B:7:0x0029, inners: #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002c  */
    @Override // androidx.datastore.core.WriteScope
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object writeData(T t, Continuation<? super Unit> continuation) {
        OkioWriteScope$writeData$1 okioWriteScope$writeData$1;
        int $i$f$use;
        FileHandle $this$use$iv;
        Object result$iv;
        Throwable thrown$iv;
        Object result$iv2;
        FileHandle handle;
        Closeable $this$use$iv2;
        Object result$iv3;
        BufferedSink sink;
        OkioSerializer<T> serializer;
        Throwable thrown$iv2;
        Object result$iv4;
        if (continuation instanceof OkioWriteScope$writeData$1) {
            okioWriteScope$writeData$1 = (OkioWriteScope$writeData$1) continuation;
            if ((okioWriteScope$writeData$1.label & Integer.MIN_VALUE) != 0) {
                okioWriteScope$writeData$1.label -= Integer.MIN_VALUE;
                Object $result = okioWriteScope$writeData$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                $i$f$use = okioWriteScope$writeData$1.label;
                switch ($i$f$use) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        checkClose();
                        FileHandle fileHandle = getFileSystem().openReadWrite(getPath());
                        $this$use$iv = fileHandle;
                        int $i$f$use2 = 0;
                        result$iv = null;
                        thrown$iv = null;
                        try {
                            handle = $this$use$iv;
                            $this$use$iv2 = Okio.buffer(FileHandle.sink$default(handle, 0L, 1, null));
                            try {
                                sink = (BufferedSink) $this$use$iv2;
                                serializer = getSerializer();
                                okioWriteScope$writeData$1.L$0 = $this$use$iv;
                                okioWriteScope$writeData$1.L$1 = handle;
                                okioWriteScope$writeData$1.L$2 = $this$use$iv2;
                                okioWriteScope$writeData$1.label = 1;
                            } catch (Throwable th) {
                                t$iv = th;
                                $i$f$use = 0;
                                result$iv3 = null;
                                thrown$iv2 = t$iv;
                                if ($this$use$iv2 != null) {
                                    try {
                                        $this$use$iv2.close();
                                    } catch (Throwable t$iv) {
                                        ExceptionsKt.addSuppressed(thrown$iv2, t$iv);
                                    }
                                }
                                result$iv4 = result$iv3;
                                if (thrown$iv2 == null) {
                                }
                            }
                        } catch (Throwable th2) {
                            t$iv = th2;
                            thrown$iv = t$iv;
                            if ($this$use$iv != null) {
                                try {
                                    $this$use$iv.close();
                                } catch (Throwable t$iv2) {
                                    ExceptionsKt.addSuppressed(thrown$iv, t$iv2);
                                }
                            }
                            result$iv2 = result$iv;
                            if (thrown$iv == null) {
                            }
                        }
                        if (serializer.writeTo(t, sink, okioWriteScope$writeData$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        $i$f$use = 0;
                        result$iv3 = null;
                        thrown$iv2 = null;
                        handle.flush();
                        result$iv4 = Unit.INSTANCE;
                        if ($this$use$iv2 != null) {
                            try {
                                $this$use$iv2.close();
                            } catch (Throwable th3) {
                                thrown$iv2 = th3;
                            }
                        }
                        if (thrown$iv2 == null) {
                            throw thrown$iv2;
                        }
                        Intrinsics.checkNotNull(result$iv4);
                        result$iv2 = Unit.INSTANCE;
                        if ($this$use$iv != null) {
                            try {
                                $this$use$iv.close();
                            } catch (Throwable t$iv3) {
                                thrown$iv = t$iv3;
                            }
                        }
                        if (thrown$iv == null) {
                            throw thrown$iv;
                        }
                        Intrinsics.checkNotNull(result$iv2);
                        return Unit.INSTANCE;
                    case 1:
                        $i$f$use = 0;
                        thrown$iv2 = null;
                        result$iv3 = null;
                        $this$use$iv2 = (Closeable) okioWriteScope$writeData$1.L$2;
                        handle = (FileHandle) okioWriteScope$writeData$1.L$1;
                        thrown$iv = null;
                        result$iv = null;
                        $this$use$iv = (Closeable) okioWriteScope$writeData$1.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            handle.flush();
                            result$iv4 = Unit.INSTANCE;
                            if ($this$use$iv2 != null) {
                            }
                        } catch (Throwable th4) {
                            t$iv = th4;
                            thrown$iv2 = t$iv;
                            if ($this$use$iv2 != null) {
                            }
                            result$iv4 = result$iv3;
                            if (thrown$iv2 == null) {
                            }
                        }
                        if (thrown$iv2 == null) {
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        okioWriteScope$writeData$1 = new OkioWriteScope$writeData$1(this, continuation);
        Object $result2 = okioWriteScope$writeData$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        $i$f$use = okioWriteScope$writeData$1.label;
        switch ($i$f$use) {
        }
    }
}
