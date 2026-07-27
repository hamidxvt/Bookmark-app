package androidx.datastore.core;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.File;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileStorage.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H\u0096@¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Landroidx/datastore/core/FileWriteScope;", "T", "Landroidx/datastore/core/FileReadScope;", "Landroidx/datastore/core/WriteScope;", "file", "Ljava/io/File;", "serializer", "Landroidx/datastore/core/Serializer;", "(Ljava/io/File;Landroidx/datastore/core/Serializer;)V", "writeData", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class FileWriteScope<T> extends FileReadScope<T> implements WriteScope<T> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileWriteScope(File file, Serializer<T> serializer) {
        super(file, serializer);
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    @Override // androidx.datastore.core.WriteScope
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object writeData(T t, Continuation<? super Unit> continuation) {
        FileWriteScope$writeData$1 fileWriteScope$writeData$1;
        FileWriteScope$writeData$1 fileWriteScope$writeData$12;
        java.io.Closeable closeable;
        FileOutputStream stream;
        if (continuation instanceof FileWriteScope$writeData$1) {
            fileWriteScope$writeData$1 = (FileWriteScope$writeData$1) continuation;
            if ((fileWriteScope$writeData$1.label & Integer.MIN_VALUE) != 0) {
                fileWriteScope$writeData$1.label -= Integer.MIN_VALUE;
                fileWriteScope$writeData$12 = fileWriteScope$writeData$1;
                Object $result = fileWriteScope$writeData$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (fileWriteScope$writeData$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        checkNotClosed();
                        FileOutputStream fos = new FileOutputStream(getFile());
                        FileOutputStream fileOutputStream = fos;
                        try {
                            FileOutputStream stream2 = fileOutputStream;
                            Serializer<T> serializer = getSerializer();
                            UncloseableOutputStream uncloseableOutputStream = new UncloseableOutputStream(stream2);
                            fileWriteScope$writeData$12.L$0 = fileOutputStream;
                            fileWriteScope$writeData$12.L$1 = stream2;
                            fileWriteScope$writeData$12.label = 1;
                            if (serializer.writeTo(t, uncloseableOutputStream, fileWriteScope$writeData$12) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            stream = stream2;
                            closeable = fileOutputStream;
                            stream.getFD().sync();
                            Unit unit = Unit.INSTANCE;
                            kotlin.io.CloseableKt.closeFinally(closeable, null);
                            return Unit.INSTANCE;
                        } catch (Throwable th) {
                            th = th;
                            closeable = fileOutputStream;
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                kotlin.io.CloseableKt.closeFinally(closeable, th);
                                throw th2;
                            }
                        }
                    case 1:
                        stream = (FileOutputStream) fileWriteScope$writeData$12.L$1;
                        closeable = (java.io.Closeable) fileWriteScope$writeData$12.L$0;
                        try {
                            ResultKt.throwOnFailure($result);
                            stream.getFD().sync();
                            Unit unit2 = Unit.INSTANCE;
                            kotlin.io.CloseableKt.closeFinally(closeable, null);
                            return Unit.INSTANCE;
                        } catch (Throwable th3) {
                            th = th3;
                            throw th;
                        }
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        fileWriteScope$writeData$1 = new FileWriteScope$writeData$1(this, continuation);
        fileWriteScope$writeData$12 = fileWriteScope$writeData$1;
        Object $result2 = fileWriteScope$writeData$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (fileWriteScope$writeData$12.label) {
        }
    }
}
