package com.yalantis.ucrop.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.yalantis.ucrop.callback.BitmapCropCallback;
import com.yalantis.ucrop.model.CropParameters;
import com.yalantis.ucrop.model.ExifInfo;
import com.yalantis.ucrop.model.ImageState;
import com.yalantis.ucrop.util.BitmapLoadUtils;
import com.yalantis.ucrop.util.FileUtils;
import com.yalantis.ucrop.util.ImageHeaderParser;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

/* loaded from: classes17.dex */
public class BitmapCropTask extends AsyncTask<Void, Void, Throwable> {
    private static final String TAG = "BitmapCropTask";
    private int cropOffsetX;
    private int cropOffsetY;
    private final Bitmap.CompressFormat mCompressFormat;
    private final int mCompressQuality;
    private final WeakReference<Context> mContext;
    private final BitmapCropCallback mCropCallback;
    private final RectF mCropRect;
    private int mCroppedImageHeight;
    private int mCroppedImageWidth;
    private float mCurrentAngle;
    private final RectF mCurrentImageRect;
    private float mCurrentScale;
    private final ExifInfo mExifInfo;
    private final String mImageInputPath;
    private final String mImageOutputPath;
    private final int mMaxResultImageSizeX;
    private final int mMaxResultImageSizeY;
    private Bitmap mViewBitmap;

    public BitmapCropTask(Context context, Bitmap viewBitmap, ImageState imageState, CropParameters cropParameters, BitmapCropCallback cropCallback) {
        this.mContext = new WeakReference<>(context);
        this.mViewBitmap = viewBitmap;
        this.mCropRect = imageState.getCropRect();
        this.mCurrentImageRect = imageState.getCurrentImageRect();
        this.mCurrentScale = imageState.getCurrentScale();
        this.mCurrentAngle = imageState.getCurrentAngle();
        this.mMaxResultImageSizeX = cropParameters.getMaxResultImageSizeX();
        this.mMaxResultImageSizeY = cropParameters.getMaxResultImageSizeY();
        this.mCompressFormat = cropParameters.getCompressFormat();
        this.mCompressQuality = cropParameters.getCompressQuality();
        this.mImageInputPath = cropParameters.getImageInputPath();
        this.mImageOutputPath = cropParameters.getImageOutputPath();
        this.mExifInfo = cropParameters.getExifInfo();
        this.mCropCallback = cropCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Throwable doInBackground(Void... params) {
        if (this.mViewBitmap == null) {
            return new NullPointerException("ViewBitmap is null");
        }
        if (this.mViewBitmap.isRecycled()) {
            return new NullPointerException("ViewBitmap is recycled");
        }
        if (this.mCurrentImageRect.isEmpty()) {
            return new NullPointerException("CurrentImageRect is empty");
        }
        try {
            crop();
            this.mViewBitmap = null;
            return null;
        } catch (Throwable throwable) {
            return throwable;
        }
    }

    private boolean crop() throws IOException {
        if (this.mMaxResultImageSizeX > 0 && this.mMaxResultImageSizeY > 0) {
            float cropWidth = this.mCropRect.width() / this.mCurrentScale;
            float cropHeight = this.mCropRect.height() / this.mCurrentScale;
            if (cropWidth > this.mMaxResultImageSizeX || cropHeight > this.mMaxResultImageSizeY) {
                float scaleX = this.mMaxResultImageSizeX / cropWidth;
                float scaleY = this.mMaxResultImageSizeY / cropHeight;
                float resizeScale = Math.min(scaleX, scaleY);
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(this.mViewBitmap, Math.round(this.mViewBitmap.getWidth() * resizeScale), Math.round(this.mViewBitmap.getHeight() * resizeScale), false);
                if (this.mViewBitmap != resizedBitmap) {
                    this.mViewBitmap.recycle();
                }
                this.mViewBitmap = resizedBitmap;
                this.mCurrentScale /= resizeScale;
            }
        }
        if (this.mCurrentAngle != 0.0f) {
            Matrix tempMatrix = new Matrix();
            tempMatrix.setRotate(this.mCurrentAngle, this.mViewBitmap.getWidth() / 2, this.mViewBitmap.getHeight() / 2);
            Bitmap rotatedBitmap = Bitmap.createBitmap(this.mViewBitmap, 0, 0, this.mViewBitmap.getWidth(), this.mViewBitmap.getHeight(), tempMatrix, true);
            if (this.mViewBitmap != rotatedBitmap) {
                this.mViewBitmap.recycle();
            }
            this.mViewBitmap = rotatedBitmap;
        }
        this.cropOffsetX = Math.round((this.mCropRect.left - this.mCurrentImageRect.left) / this.mCurrentScale);
        this.cropOffsetY = Math.round((this.mCropRect.top - this.mCurrentImageRect.top) / this.mCurrentScale);
        this.mCroppedImageWidth = Math.round(this.mCropRect.width() / this.mCurrentScale);
        this.mCroppedImageHeight = Math.round(this.mCropRect.height() / this.mCurrentScale);
        boolean shouldCrop = shouldCrop(this.mCroppedImageWidth, this.mCroppedImageHeight);
        Log.i(TAG, "Should crop: " + shouldCrop);
        if (shouldCrop) {
            ExifInterface originalExif = new ExifInterface(this.mImageInputPath);
            saveImage(Bitmap.createBitmap(this.mViewBitmap, this.cropOffsetX, this.cropOffsetY, this.mCroppedImageWidth, this.mCroppedImageHeight));
            if (this.mCompressFormat.equals(Bitmap.CompressFormat.JPEG)) {
                ImageHeaderParser.copyExif(originalExif, this.mCroppedImageWidth, this.mCroppedImageHeight, this.mImageOutputPath);
                return true;
            }
            return true;
        }
        FileUtils.copyFile(this.mImageInputPath, this.mImageOutputPath);
        return false;
    }

    private void saveImage(Bitmap croppedBitmap) throws FileNotFoundException {
        Context context = this.mContext.get();
        if (context == null) {
            return;
        }
        OutputStream outputStream = null;
        ByteArrayOutputStream outStream = null;
        try {
            try {
                outputStream = new FileOutputStream(new File(this.mImageOutputPath), false);
                outStream = new ByteArrayOutputStream();
                croppedBitmap.compress(this.mCompressFormat, this.mCompressQuality, outStream);
                outputStream.write(outStream.toByteArray());
                croppedBitmap.recycle();
            } catch (IOException exc) {
                Log.e(TAG, exc.getLocalizedMessage());
            }
        } finally {
            BitmapLoadUtils.close(outputStream);
            BitmapLoadUtils.close(outStream);
        }
    }

    private boolean shouldCrop(int width, int height) {
        int pixelError = 1 + Math.round(Math.max(width, height) / 1000.0f);
        return (this.mMaxResultImageSizeX > 0 && this.mMaxResultImageSizeY > 0) || Math.abs(this.mCropRect.left - this.mCurrentImageRect.left) > ((float) pixelError) || Math.abs(this.mCropRect.top - this.mCurrentImageRect.top) > ((float) pixelError) || Math.abs(this.mCropRect.bottom - this.mCurrentImageRect.bottom) > ((float) pixelError) || Math.abs(this.mCropRect.right - this.mCurrentImageRect.right) > ((float) pixelError) || this.mCurrentAngle != 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Throwable t) {
        if (this.mCropCallback != null) {
            if (t == null) {
                Uri uri = Uri.fromFile(new File(this.mImageOutputPath));
                this.mCropCallback.onBitmapCropped(uri, this.cropOffsetX, this.cropOffsetY, this.mCroppedImageWidth, this.mCroppedImageHeight);
            } else {
                this.mCropCallback.onCropFailure(t);
            }
        }
    }
}
