package com.github.dhaval2404.imagepicker.provider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.dhaval2404.imagepicker.ImagePickerActivity;
import com.github.dhaval2404.imagepicker.R;
import com.github.dhaval2404.imagepicker.util.ExifDataCopier;
import com.github.dhaval2404.imagepicker.util.FileUtil;
import com.github.dhaval2404.imagepicker.util.ImageUtil;
import com.google.android.material.internal.ViewUtils;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: CompressionProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u0015\n\u0002\b\u0004\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\nH\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0006H\u0002J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0006H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u0006H\u0002J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0002J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0002J\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0003R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/github/dhaval2404/imagepicker/provider/CompressionProvider;", "Lcom/github/dhaval2404/imagepicker/provider/BaseProvider;", "activity", "Lcom/github/dhaval2404/imagepicker/ImagePickerActivity;", "(Lcom/github/dhaval2404/imagepicker/ImagePickerActivity;)V", "mFileDir", "Ljava/io/File;", "mMaxFileSize", "", "mMaxHeight", "", "mMaxWidth", "applyCompression", "file", "attempt", "compress", "", "uri", "Landroid/net/Uri;", "getSizeDiff", "handleResult", "isCompressEnabled", "", "isCompressionRequired", "resolutionList", "", "", "startCompression", "startCompressionWorker", "Companion", "imagepicker_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class CompressionProvider extends BaseProvider {
    private static final String TAG = CompressionProvider.class.getSimpleName();
    private final File mFileDir;
    private final long mMaxFileSize;
    private final int mMaxHeight;
    private final int mMaxWidth;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CompressionProvider(ImagePickerActivity activity) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intent intent = activity.getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "activity.intent");
        Bundle bundle = intent.getExtras();
        bundle = bundle == null ? new Bundle() : bundle;
        Intrinsics.checkNotNullExpressionValue(bundle, "activity.intent.extras ?: Bundle()");
        this.mMaxWidth = bundle.getInt(ImagePicker.EXTRA_MAX_WIDTH, 0);
        this.mMaxHeight = bundle.getInt(ImagePicker.EXTRA_MAX_HEIGHT, 0);
        this.mMaxFileSize = bundle.getLong(ImagePicker.EXTRA_IMAGE_MAX_SIZE, 0L);
        String fileDir = bundle.getString(ImagePicker.EXTRA_SAVE_DIRECTORY);
        this.mFileDir = getFileDir(fileDir);
    }

    private final boolean isCompressEnabled() {
        return this.mMaxFileSize > 0;
    }

    private final boolean isCompressionRequired(File file) {
        boolean status = isCompressEnabled() && getSizeDiff(file) > 0;
        if (!status && this.mMaxWidth > 0 && this.mMaxHeight > 0) {
            Pair resolution = FileUtil.INSTANCE.getImageResolution(file);
            return resolution.getFirst().intValue() > this.mMaxWidth || resolution.getSecond().intValue() > this.mMaxHeight;
        }
        return status;
    }

    public final boolean isCompressionRequired(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        boolean status = isCompressEnabled() && getSizeDiff(uri) > 0;
        if (!status && this.mMaxWidth > 0 && this.mMaxHeight > 0) {
            Pair resolution = FileUtil.INSTANCE.getImageResolution(this, uri);
            return resolution.getFirst().intValue() > this.mMaxWidth || resolution.getSecond().intValue() > this.mMaxHeight;
        }
        return status;
    }

    private final long getSizeDiff(File file) {
        return file.length() - this.mMaxFileSize;
    }

    private final long getSizeDiff(Uri uri) {
        long length = FileUtil.INSTANCE.getImageSize(this, uri);
        return length - this.mMaxFileSize;
    }

    public final void compress(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        startCompressionWorker(uri);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.github.dhaval2404.imagepicker.provider.CompressionProvider$startCompressionWorker$1] */
    private final void startCompressionWorker(Uri uri) {
        new AsyncTask<Uri, Void, File>() { // from class: com.github.dhaval2404.imagepicker.provider.CompressionProvider$startCompressionWorker$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public File doInBackground(Uri... params) {
                File startCompression;
                Intrinsics.checkNotNullParameter(params, "params");
                File file = FileUtil.INSTANCE.getTempFile(CompressionProvider.this, params[0]);
                if (file != null) {
                    startCompression = CompressionProvider.this.startCompression(file);
                    return startCompression;
                }
                return null;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(File file) {
                super.onPostExecute((CompressionProvider$startCompressionWorker$1) file);
                if (file != null) {
                    CompressionProvider.this.handleResult(file);
                } else {
                    CompressionProvider.this.setError(R.string.error_failed_to_compress_image);
                }
            }
        }.execute(uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File startCompression(File file) {
        int i;
        File newFile = (File) null;
        int attempt = 0;
        int lastAttempt = 0;
        do {
            if (newFile != null) {
                newFile.delete();
            }
            newFile = applyCompression(file, attempt);
            if (newFile == null) {
                if (attempt <= 0) {
                    return null;
                }
                return applyCompression(file, lastAttempt);
            }
            lastAttempt = attempt;
            if (this.mMaxFileSize > 0) {
                long diff = getSizeDiff(newFile);
                if (diff > 1048576) {
                    i = 3;
                } else {
                    i = diff > ((long) 512000) ? 2 : 1;
                }
                attempt += i;
            } else {
                attempt++;
            }
        } while (isCompressionRequired(newFile));
        ExifDataCopier.INSTANCE.copyExif(file, newFile);
        return newFile;
    }

    private final File applyCompression(File file, int attempt) {
        List resList = resolutionList();
        if (attempt >= resList.size()) {
            return null;
        }
        int[] resolution = resList.get(attempt);
        int maxWidth = resolution[0];
        int maxHeight = resolution[1];
        if (this.mMaxWidth > 0 && this.mMaxHeight > 0 && (maxWidth > this.mMaxWidth || maxHeight > this.mMaxHeight)) {
            maxHeight = this.mMaxHeight;
            maxWidth = this.mMaxWidth;
        }
        Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
        String absolutePath = file.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "file.absolutePath");
        if (StringsKt.endsWith$default(absolutePath, ".png", false, 2, (Object) null)) {
            format = Bitmap.CompressFormat.PNG;
        }
        String extension = FileUtil.INSTANCE.getImageExtension(file);
        File compressFile = FileUtil.INSTANCE.getImageFile(this.mFileDir, extension);
        if (compressFile == null) {
            return null;
        }
        String absolutePath2 = compressFile.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath2, "compressFile.absolutePath");
        return ImageUtil.INSTANCE.compressImage(file, maxWidth, maxHeight, format, absolutePath2);
    }

    private final List<int[]> resolutionList() {
        return CollectionsKt.listOf((Object[]) new int[][]{new int[]{2448, 3264}, new int[]{2008, 3032}, new int[]{1944, 2580}, new int[]{1680, 2240}, new int[]{1536, 2048}, new int[]{1200, 1600}, new int[]{1024, 1392}, new int[]{960, 1280}, new int[]{ViewUtils.EDGE_TO_EDGE_FLAGS, 1024}, new int[]{600, 800}, new int[]{480, 640}, new int[]{240, 320}, new int[]{120, 160}, new int[]{60, 80}, new int[]{30, 40}});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleResult(File file) {
        ImagePickerActivity activity = getActivity();
        Uri fromFile = Uri.fromFile(file);
        Intrinsics.checkNotNullExpressionValue(fromFile, "Uri.fromFile(file)");
        activity.setCompressedImage(fromFile);
    }
}
