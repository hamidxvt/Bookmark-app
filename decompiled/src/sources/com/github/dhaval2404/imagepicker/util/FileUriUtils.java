package com.github.dhaval2404.imagepicker.util;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: FileUriUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J;\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00042\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0002\u0010\fJ\u001a\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\u0018"}, d2 = {"Lcom/github/dhaval2404/imagepicker/util/FileUriUtils;", "", "()V", "getDataColumn", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "selection", "selectionArgs", "", "(Landroid/content/Context;Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/String;", "getDownloadDocument", "getFilePath", "getMediaDocument", "getPathFromLocalUri", "getPathFromRemoteUri", "getRealPath", "isDownloadsDocument", "", "isExternalStorageDocument", "isGooglePhotosUri", "isMediaDocument", "imagepicker_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class FileUriUtils {
    public static final FileUriUtils INSTANCE = new FileUriUtils();

    private FileUriUtils() {
    }

    public final String getRealPath(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        String path = getPathFromLocalUri(context, uri);
        if (path == null) {
            return getPathFromRemoteUri(context, uri);
        }
        return path;
    }

    private final String getPathFromLocalUri(Context context, Uri uri) {
        Collection $this$dropLastWhile$iv;
        if (1 == 0 || !DocumentsContract.isDocumentUri(context, uri)) {
            String scheme = uri.getScheme();
            Intrinsics.checkNotNull(scheme);
            if (StringsKt.equals(FirebaseAnalytics.Param.CONTENT, scheme, true)) {
                return isGooglePhotosUri(uri) ? uri.getLastPathSegment() : getDataColumn(context, uri, null, null);
            }
            String scheme2 = uri.getScheme();
            Intrinsics.checkNotNull(scheme2);
            if (StringsKt.equals("file", scheme2, true)) {
                return uri.getPath();
            }
        } else {
            if (isExternalStorageDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                Intrinsics.checkNotNullExpressionValue(docId, "docId");
                List $this$dropLastWhile$iv2 = new Regex(":").split(docId, 0);
                if (!$this$dropLastWhile$iv2.isEmpty()) {
                    ListIterator iterator$iv = $this$dropLastWhile$iv2.listIterator($this$dropLastWhile$iv2.size());
                    while (iterator$iv.hasPrevious()) {
                        String it = iterator$iv.previous();
                        String it2 = it.length() == 0 ? 1 : null;
                        if (it2 == null) {
                            $this$dropLastWhile$iv = CollectionsKt.take($this$dropLastWhile$iv2, iterator$iv.nextIndex() + 1);
                            break;
                        }
                    }
                }
                $this$dropLastWhile$iv = CollectionsKt.emptyList();
                Collection $this$toTypedArray$iv = $this$dropLastWhile$iv;
                Object[] array = $this$toTypedArray$iv.toArray(new String[0]);
                if (array == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                String[] split = (String[]) array;
                String type = split[0];
                if (StringsKt.equals("primary", type, true)) {
                    return split.length > 1 ? Environment.getExternalStorageDirectory().toString() + "/" + split[1] : Environment.getExternalStorageDirectory().toString() + "/";
                }
                String path = "storage/" + StringsKt.replace$default(docId, ":", "/", false, 4, (Object) null);
                return new File(path).exists() ? path : "/storage/sdcard/" + split[1];
            }
            if (isDownloadsDocument(uri)) {
                return getDownloadDocument(context, uri);
            }
            if (isMediaDocument(uri)) {
                return getMediaDocument(context, uri);
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0032, code lost:
    
        if (r1 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003d, code lost:
    
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0041, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003b, code lost:
    
        if (r1 == null) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = (Cursor) null;
        String[] projection = {"_data"};
        try {
            try {
                ContentResolver contentResolver = context.getContentResolver();
                Intrinsics.checkNotNull(uri);
                cursor = contentResolver.query(uri, projection, selection, selectionArgs, null);
                if (cursor != null && cursor.moveToFirst()) {
                    int index = cursor.getColumnIndexOrThrow("_data");
                    String string = cursor.getString(index);
                    cursor.close();
                    return string;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private final String getDownloadDocument(Context context, Uri uri) {
        String fileName = getFilePath(context, uri);
        if (fileName != null) {
            String path = Environment.getExternalStorageDirectory().toString() + "/Download/" + fileName;
            if (new File(path).exists()) {
                return path;
            }
        }
        String id = DocumentsContract.getDocumentId(uri);
        Intrinsics.checkNotNullExpressionValue(id, "id");
        if (StringsKt.contains$default((CharSequence) id, (CharSequence) ":", false, 2, (Object) null)) {
            id = (String) StringsKt.split$default((CharSequence) id, new String[]{":"}, false, 0, 6, (Object) null).get(1);
        }
        Uri parse = Uri.parse("content://downloads/public_downloads");
        Long valueOf = Long.valueOf(id);
        Intrinsics.checkNotNullExpressionValue(valueOf, "java.lang.Long.valueOf(id)");
        Uri contentUri = ContentUris.withAppendedId(parse, valueOf.longValue());
        Intrinsics.checkNotNullExpressionValue(contentUri, "ContentUris.withAppended…ong.valueOf(id)\n        )");
        return getDataColumn(context, contentUri, null, null);
    }

    private final String getMediaDocument(Context context, Uri uri) {
        Collection $this$dropLastWhile$iv;
        String docId = DocumentsContract.getDocumentId(uri);
        Intrinsics.checkNotNullExpressionValue(docId, "docId");
        List $this$dropLastWhile$iv2 = new Regex(":").split(docId, 0);
        if (!$this$dropLastWhile$iv2.isEmpty()) {
            ListIterator iterator$iv = $this$dropLastWhile$iv2.listIterator($this$dropLastWhile$iv2.size());
            while (iterator$iv.hasPrevious()) {
                String it = iterator$iv.previous();
                String it2 = it.length() == 0 ? 1 : null;
                if (it2 == null) {
                    $this$dropLastWhile$iv = CollectionsKt.take($this$dropLastWhile$iv2, iterator$iv.nextIndex() + 1);
                    break;
                }
            }
        }
        $this$dropLastWhile$iv = CollectionsKt.emptyList();
        Collection $this$toTypedArray$iv = $this$dropLastWhile$iv;
        Object[] array = $this$toTypedArray$iv.toArray(new String[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        String[] split = (String[]) array;
        String type = split[0];
        Uri contentUri = (Uri) null;
        if (Intrinsics.areEqual("image", type)) {
            contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else if (Intrinsics.areEqual("video", type)) {
            contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else if (Intrinsics.areEqual("audio", type)) {
            contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        }
        String[] selectionArgs = {split[1]};
        return getDataColumn(context, contentUri, "_id=?", selectionArgs);
    }

    private final String getFilePath(Context context, Uri uri) {
        Cursor cursor = (Cursor) null;
        String[] projection = {"_display_name"};
        try {
            cursor = context.getContentResolver().query(uri, projection, null, null, null);
            if (cursor == null || !cursor.moveToFirst()) {
                return null;
            }
            int index = cursor.getColumnIndexOrThrow("_display_name");
            String string = cursor.getString(index);
            cursor.close();
            return string;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String getPathFromRemoteUri(Context context, Uri uri) {
        File file = (File) null;
        InputStream inputStream = (InputStream) null;
        OutputStream outputStream = (OutputStream) null;
        boolean success = false;
        try {
            String extension = FileUtil.INSTANCE.getImageExtension(uri);
            inputStream = context.getContentResolver().openInputStream(uri);
            FileUtil fileUtil = FileUtil.INSTANCE;
            File cacheDir = context.getCacheDir();
            Intrinsics.checkNotNullExpressionValue(cacheDir, "context.cacheDir");
            file = fileUtil.getImageFile(cacheDir, extension);
        } catch (IOException e) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e3) {
                    success = false;
                    if (!success) {
                    }
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                }
            }
            if (outputStream == null) {
                throw th;
            }
            try {
                outputStream.close();
                throw th;
            } catch (IOException e5) {
                throw th;
            }
        }
        if (file == null) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e6) {
                }
            }
            return null;
        }
        outputStream = new FileOutputStream(file);
        if (inputStream != null) {
            ByteStreamsKt.copyTo(inputStream, outputStream, 4096);
            success = true;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e7) {
            }
        }
        try {
            outputStream.close();
        } catch (IOException e8) {
            success = false;
            if (!success) {
            }
        }
        if (!success) {
            return null;
        }
        Intrinsics.checkNotNull(file);
        return file.getPath();
    }

    private final boolean isExternalStorageDocument(Uri uri) {
        return Intrinsics.areEqual("com.android.externalstorage.documents", uri.getAuthority());
    }

    private final boolean isDownloadsDocument(Uri uri) {
        return Intrinsics.areEqual("com.android.providers.downloads.documents", uri.getAuthority());
    }

    private final boolean isMediaDocument(Uri uri) {
        return Intrinsics.areEqual("com.android.providers.media.documents", uri.getAuthority());
    }

    private final boolean isGooglePhotosUri(Uri uri) {
        return Intrinsics.areEqual("com.google.android.apps.photos.content", uri.getAuthority());
    }
}
