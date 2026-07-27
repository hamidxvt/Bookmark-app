package com.ingenious.androidbookmarksalesupgrade.extensions;

import android.content.Context;
import android.net.Uri;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileExt.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"getFileFromUri", "Ljava/io/File;", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "app_debug"}, k = 2, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes15.dex */
public final class FileExtKt {
    public static final File getFileFromUri(Context $this$getFileFromUri, Uri uri) {
        Intrinsics.checkNotNullParameter($this$getFileFromUri, "<this>");
        Intrinsics.checkNotNullParameter(uri, "uri");
        File cacheDir = $this$getFileFromUri.getExternalCacheDir();
        String fileName = "picture_" + System.currentTimeMillis() + ".jpg";
        File tempFile = new File(cacheDir, fileName);
        try {
            InputStream openInputStream = $this$getFileFromUri.getContentResolver().openInputStream(uri);
            if (openInputStream != null) {
                FileOutputStream fileOutputStream = openInputStream;
                try {
                    InputStream inputStream = fileOutputStream;
                    fileOutputStream = new FileOutputStream(tempFile);
                    try {
                        FileOutputStream outputStream = fileOutputStream;
                        byte[] buffer = new byte[4096];
                        while (true) {
                            int it = inputStream.read(buffer);
                            if (it == -1) {
                                break;
                            }
                            outputStream.write(buffer, 0, it);
                        }
                        outputStream.flush();
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(fileOutputStream, null);
                        Unit unit2 = Unit.INSTANCE;
                        CloseableKt.closeFinally(fileOutputStream, null);
                    } finally {
                    }
                } finally {
                }
            }
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
