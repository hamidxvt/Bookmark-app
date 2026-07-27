package com.github.dhaval2404.imagepicker.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0002J.\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\"\u0010\u0016\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u0011H\u0002J\u0010\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0019H\u0002¨\u0006\u001a"}, d2 = {"Lcom/github/dhaval2404/imagepicker/util/ImageUtil;", "", "()V", "calculateInSampleSize", "", "options", "Landroid/graphics/BitmapFactory$Options;", "reqWidth", "reqHeight", "canUseForInBitmap", "", "candidate", "Landroid/graphics/Bitmap;", "targetOptions", "compressImage", "Ljava/io/File;", "imageFile", "", "compressFormat", "Landroid/graphics/Bitmap$CompressFormat;", "destinationPath", "", "decodeSampledBitmapFromFile", "getBytesPerPixel", "config", "Landroid/graphics/Bitmap$Config;", "imagepicker_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class ImageUtil {
    public static final ImageUtil INSTANCE = new ImageUtil();

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Bitmap.Config.values().length];

        static {
            $EnumSwitchMapping$0[Bitmap.Config.ARGB_8888.ordinal()] = 1;
            $EnumSwitchMapping$0[Bitmap.Config.RGB_565.ordinal()] = 2;
            $EnumSwitchMapping$0[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            $EnumSwitchMapping$0[Bitmap.Config.ALPHA_8.ordinal()] = 4;
        }
    }

    private ImageUtil() {
    }

    public final File compressImage(File imageFile, float reqWidth, float reqHeight, Bitmap.CompressFormat compressFormat, String destinationPath) throws IOException {
        Intrinsics.checkNotNullParameter(imageFile, "imageFile");
        Intrinsics.checkNotNullParameter(compressFormat, "compressFormat");
        Intrinsics.checkNotNullParameter(destinationPath, "destinationPath");
        FileOutputStream fileOutputStream = (FileOutputStream) null;
        File file = new File(destinationPath).getParentFile();
        if (file != null && !file.exists()) {
            file.mkdirs();
        }
        try {
            fileOutputStream = new FileOutputStream(destinationPath);
            Bitmap decodeSampledBitmapFromFile = decodeSampledBitmapFromFile(imageFile, reqWidth, reqHeight);
            if (decodeSampledBitmapFromFile != null) {
                decodeSampledBitmapFromFile.compress(compressFormat, 100, fileOutputStream);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            return new File(destinationPath);
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            throw th;
        }
    }

    private final Bitmap decodeSampledBitmapFromFile(File imageFile, float reqWidth, float reqHeight) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = reqWidth / reqHeight;
        if (actualHeight > reqHeight || actualWidth > reqWidth) {
            if (imgRatio < maxRatio) {
                actualWidth = (int) (actualWidth * (reqHeight / actualHeight));
                actualHeight = (int) reqHeight;
            } else if (imgRatio > maxRatio) {
                actualHeight = (int) (actualHeight * (reqWidth / actualWidth));
                actualWidth = (int) reqWidth;
            } else {
                actualHeight = (int) reqHeight;
                actualWidth = (int) reqWidth;
            }
        }
        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);
        options.inJustDecodeBounds = false;
        if (bmp != null && canUseForInBitmap(bmp, options)) {
            options.inMutable = true;
            options.inBitmap = bmp;
        }
        options.inTempStorage = new byte[16384];
        try {
            bmp = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }
        Bitmap scaledBitmap = (Bitmap) null;
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception2) {
            exception2.printStackTrace();
        }
        float ratioX = actualWidth / options.outWidth;
        float ratioY = actualHeight / options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;
        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);
        Intrinsics.checkNotNull(scaledBitmap);
        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        Intrinsics.checkNotNull(bmp);
        canvas.drawBitmap(bmp, middleX - (bmp.getWidth() / 2), middleY - (bmp.getHeight() / 2), new Paint(2));
        bmp.recycle();
        Matrix matrix = new Matrix();
        return Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
    }

    private final int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private final boolean canUseForInBitmap(Bitmap candidate, BitmapFactory.Options targetOptions) {
        int width = targetOptions.outWidth / targetOptions.inSampleSize;
        int height = targetOptions.outHeight / targetOptions.inSampleSize;
        Bitmap.Config config = candidate.getConfig();
        Intrinsics.checkNotNullExpressionValue(config, "candidate.config");
        int byteCount = width * height * getBytesPerPixel(config);
        return byteCount <= candidate.getAllocationByteCount();
    }

    private final int getBytesPerPixel(Bitmap.Config config) {
        switch (WhenMappings.$EnumSwitchMapping$0[config.ordinal()]) {
            case 1:
                return 4;
            case 2:
            case 3:
                return 2;
            case 4:
            default:
                return 1;
        }
    }
}
