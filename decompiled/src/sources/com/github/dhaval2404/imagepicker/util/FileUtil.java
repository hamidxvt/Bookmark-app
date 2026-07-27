package com.github.dhaval2404.imagepicker.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.os.StatFs;
import androidx.documentfile.provider.DocumentFile;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: FileUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u00020\u0006H\u0002J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\fJ\u000e\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011J\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0015\u001a\u00020\u00112\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\"\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00112\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\u001b\u001a\u00020\u0006H\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u000b\u001a\u00020\fH\u0002¨\u0006\u001e"}, d2 = {"Lcom/github/dhaval2404/imagepicker/util/FileUtil;", "", "()V", "getCompressFormat", "Landroid/graphics/Bitmap$CompressFormat;", "extension", "", "getDocumentFile", "Landroidx/documentfile/provider/DocumentFile;", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "getFileName", "getFreeSpace", "", "file", "Ljava/io/File;", "getImageExtension", "uriImage", "getImageFile", "fileDir", "getImageResolution", "Lkotlin/Pair;", "", "getImageSize", "getTempFile", "getTimestamp", "isFileUri", "", "imagepicker_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class FileUtil {
    public static final FileUtil INSTANCE = new FileUtil();

    private FileUtil() {
    }

    public static /* synthetic */ File getImageFile$default(FileUtil fileUtil, File file, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return fileUtil.getImageFile(file, str);
    }

    public final File getImageFile(File fileDir, String extension) {
        Intrinsics.checkNotNullParameter(fileDir, "fileDir");
        String ext = extension != null ? extension : ".jpg";
        try {
            String fileName = getFileName();
            String imageFileName = fileName + ext;
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File file = new File(fileDir, imageFileName);
            file.createNewFile();
            return file;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private final String getFileName() {
        return "IMG_" + getTimestamp();
    }

    private final String getTimestamp() {
        String format = new SimpleDateFormat("yyyyMMdd_HHmmssSSS", Locale.getDefault()).format(new Date());
        Intrinsics.checkNotNullExpressionValue(format, "SimpleDateFormat(timeFor…Default()).format(Date())");
        return format;
    }

    public final long getFreeSpace(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        StatFs stat = new StatFs(file.getPath());
        long availBlocks = stat.getAvailableBlocksLong();
        long blockSize = stat.getBlockSizeLong();
        return availBlocks * blockSize;
    }

    public final Pair<Integer, Integer> getImageResolution(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        InputStream stream = context.getContentResolver().openInputStream(uri);
        BitmapFactory.decodeStream(stream, null, options);
        return new Pair<>(Integer.valueOf(options.outWidth), Integer.valueOf(options.outHeight));
    }

    public final Pair<Integer, Integer> getImageResolution(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        return new Pair<>(Integer.valueOf(options.outWidth), Integer.valueOf(options.outHeight));
    }

    public final long getImageSize(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        DocumentFile documentFile = getDocumentFile(context, uri);
        if (documentFile != null) {
            return documentFile.length();
        }
        return 0L;
    }

    public final File getTempFile(Context context, Uri uri) {
        FileDescriptor fileDescriptor;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        try {
            File destination = new File(context.getCacheDir(), "image_picker.png");
            ParcelFileDescriptor parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
            if (parcelFileDescriptor == null || (fileDescriptor = parcelFileDescriptor.getFileDescriptor()) == null) {
                return null;
            }
            FileChannel src = new FileInputStream(fileDescriptor).getChannel();
            FileChannel dst = new FileOutputStream(destination).getChannel();
            dst.transferFrom(src, 0L, src.size());
            src.close();
            dst.close();
            return destination;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public final DocumentFile getDocumentFile(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        DocumentFile file = (DocumentFile) null;
        if (isFileUri(uri)) {
            String path = FileUriUtils.INSTANCE.getRealPath(context, uri);
            if (path != null) {
                DocumentFile file2 = DocumentFile.fromFile(new File(path));
                return file2;
            }
            return file;
        }
        DocumentFile file3 = DocumentFile.fromSingleUri(context, uri);
        return file3;
    }

    public final Bitmap.CompressFormat getCompressFormat(String extension) {
        Intrinsics.checkNotNullParameter(extension, "extension");
        if (StringsKt.contains((CharSequence) extension, (CharSequence) "png", true)) {
            return Bitmap.CompressFormat.PNG;
        }
        if (StringsKt.contains((CharSequence) extension, (CharSequence) "webp", true)) {
            if (Build.VERSION.SDK_INT >= 30) {
                return Bitmap.CompressFormat.WEBP_LOSSLESS;
            }
            return Bitmap.CompressFormat.WEBP;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    public final String getImageExtension(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        Uri fromFile = Uri.fromFile(file);
        Intrinsics.checkNotNullExpressionValue(fromFile, "Uri.fromFile(file)");
        return getImageExtension(fromFile);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x004c, code lost:
    
        if ((r1.length() == 0) != false) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String getImageExtension(Uri uriImage) {
        Intrinsics.checkNotNullParameter(uriImage, "uriImage");
        String extension = (String) null;
        try {
            String imagePath = uriImage.getPath();
            if (imagePath != null && StringsKt.lastIndexOf$default((CharSequence) imagePath, ".", 0, false, 6, (Object) null) != -1) {
                String substring = imagePath.substring(StringsKt.lastIndexOf$default((CharSequence) imagePath, ".", 0, false, 6, (Object) null) + 1);
                Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.String).substring(startIndex)");
                extension = substring;
            }
        } catch (Exception e) {
            extension = (String) null;
        }
        if (extension != null) {
        }
        extension = "jpg";
        return ClassUtils.PACKAGE_SEPARATOR_CHAR + extension;
    }

    private final boolean isFileUri(Uri uri) {
        return StringsKt.equals("file", uri.getScheme(), true);
    }
}
