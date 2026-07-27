package com.yalantis.ucrop.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import androidx.webkit.ProxyConfig;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import com.yalantis.ucrop.model.ExifInfo;
import com.yalantis.ucrop.util.BitmapLoadUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;

/* loaded from: classes17.dex */
public class BitmapLoadTask extends AsyncTask<Void, Void, BitmapWorkerResult> {
    private static final int MAX_BITMAP_SIZE = 104857600;
    private static final String TAG = "BitmapWorkerTask";
    private final BitmapLoadCallback mBitmapLoadCallback;
    private final Context mContext;
    private Uri mInputUri;
    private Uri mOutputUri;
    private final int mRequiredHeight;
    private final int mRequiredWidth;

    public static class BitmapWorkerResult {
        Bitmap mBitmapResult;
        Exception mBitmapWorkerException;
        ExifInfo mExifInfo;

        public BitmapWorkerResult(Bitmap bitmapResult, ExifInfo exifInfo) {
            this.mBitmapResult = bitmapResult;
            this.mExifInfo = exifInfo;
        }

        public BitmapWorkerResult(Exception bitmapWorkerException) {
            this.mBitmapWorkerException = bitmapWorkerException;
        }
    }

    public BitmapLoadTask(Context context, Uri inputUri, Uri outputUri, int requiredWidth, int requiredHeight, BitmapLoadCallback loadCallback) {
        this.mContext = context;
        this.mInputUri = inputUri;
        this.mOutputUri = outputUri;
        this.mRequiredWidth = requiredWidth;
        this.mRequiredHeight = requiredHeight;
        this.mBitmapLoadCallback = loadCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public BitmapWorkerResult doInBackground(Void... params) {
        InputStream stream;
        if (this.mInputUri == null) {
            return new BitmapWorkerResult(new NullPointerException("Input Uri cannot be null"));
        }
        try {
            processInputUri();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inSampleSize = BitmapLoadUtils.calculateInSampleSize(options, this.mRequiredWidth, this.mRequiredHeight);
            options.inJustDecodeBounds = false;
            Bitmap decodeSampledBitmap = null;
            boolean decodeAttemptSuccess = false;
            while (!decodeAttemptSuccess) {
                try {
                    stream = this.mContext.getContentResolver().openInputStream(this.mInputUri);
                    try {
                        decodeSampledBitmap = BitmapFactory.decodeStream(stream, null, options);
                    } finally {
                        BitmapLoadUtils.close(stream);
                    }
                } catch (IOException e) {
                    Log.e(TAG, "doInBackground: ImageDecoder.createSource: ", e);
                    return new BitmapWorkerResult(new IllegalArgumentException("Bitmap could not be decoded from the Uri: [" + this.mInputUri + "]", e));
                } catch (OutOfMemoryError error) {
                    Log.e(TAG, "doInBackground: BitmapFactory.decodeFileDescriptor: ", error);
                    options.inSampleSize *= 2;
                }
                if (options.outWidth == -1 || options.outHeight == -1) {
                    return new BitmapWorkerResult(new IllegalArgumentException("Bounds for bitmap could not be retrieved from the Uri: [" + this.mInputUri + "]"));
                }
                BitmapLoadUtils.close(stream);
                if (!checkSize(decodeSampledBitmap, options)) {
                    decodeAttemptSuccess = true;
                }
            }
            if (decodeSampledBitmap == null) {
                return new BitmapWorkerResult(new IllegalArgumentException("Bitmap could not be decoded from the Uri: [" + this.mInputUri + "]"));
            }
            int exifOrientation = BitmapLoadUtils.getExifOrientation(this.mContext, this.mInputUri);
            int exifDegrees = BitmapLoadUtils.exifToDegrees(exifOrientation);
            int exifTranslation = BitmapLoadUtils.exifToTranslation(exifOrientation);
            ExifInfo exifInfo = new ExifInfo(exifOrientation, exifDegrees, exifTranslation);
            Matrix matrix = new Matrix();
            if (exifDegrees != 0) {
                matrix.preRotate(exifDegrees);
            }
            if (exifTranslation != 1) {
                matrix.postScale(exifTranslation, 1.0f);
            }
            if (!matrix.isIdentity()) {
                return new BitmapWorkerResult(BitmapLoadUtils.transformBitmap(decodeSampledBitmap, matrix), exifInfo);
            }
            return new BitmapWorkerResult(decodeSampledBitmap, exifInfo);
        } catch (IOException | NullPointerException e2) {
            return new BitmapWorkerResult(e2);
        }
    }

    private void processInputUri() throws NullPointerException, IOException {
        String inputUriScheme = this.mInputUri.getScheme();
        Log.d(TAG, "Uri scheme: " + inputUriScheme);
        if (ProxyConfig.MATCH_HTTP.equals(inputUriScheme) || ProxyConfig.MATCH_HTTPS.equals(inputUriScheme)) {
            try {
                downloadFile(this.mInputUri, this.mOutputUri);
                return;
            } catch (IOException | NullPointerException e) {
                Log.e(TAG, "Downloading failed", e);
                throw e;
            }
        }
        if (FirebaseAnalytics.Param.CONTENT.equals(inputUriScheme)) {
            try {
                copyFile(this.mInputUri, this.mOutputUri);
                return;
            } catch (IOException | NullPointerException e2) {
                Log.e(TAG, "Copying failed", e2);
                throw e2;
            }
        }
        if (!"file".equals(inputUriScheme)) {
            Log.e(TAG, "Invalid Uri scheme " + inputUriScheme);
            throw new IllegalArgumentException("Invalid Uri scheme" + inputUriScheme);
        }
    }

    private void copyFile(Uri inputUri, Uri outputUri) throws NullPointerException, IOException {
        Log.d(TAG, "copyFile");
        if (outputUri == null) {
            throw new NullPointerException("Output Uri is null - cannot copy image");
        }
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = this.mContext.getContentResolver().openInputStream(inputUri);
            outputStream = new FileOutputStream(new File(outputUri.getPath()));
            if (inputStream == null) {
                throw new NullPointerException("InputStream for given input Uri is null");
            }
            byte[] buffer = new byte[1024];
            while (true) {
                int length = inputStream.read(buffer);
                if (length > 0) {
                    outputStream.write(buffer, 0, length);
                } else {
                    return;
                }
            }
        } finally {
            BitmapLoadUtils.close(outputStream);
            BitmapLoadUtils.close(inputStream);
            this.mInputUri = this.mOutputUri;
        }
    }

    private void downloadFile(Uri inputUri, Uri outputUri) throws NullPointerException, IOException {
        Log.d(TAG, "downloadFile");
        if (outputUri == null) {
            throw new NullPointerException("Output Uri is null - cannot download image");
        }
        OkHttpClient client = new OkHttpClient();
        BufferedSource source = null;
        Sink sink = null;
        Response response = null;
        try {
            Request request = new Request.Builder().url(inputUri.toString()).build();
            response = client.newCall(request).execute();
            source = response.body().getSource();
            OutputStream outputStream = this.mContext.getContentResolver().openOutputStream(outputUri);
            if (outputStream != null) {
                sink = Okio.sink(outputStream);
                source.readAll(sink);
                return;
            }
            throw new NullPointerException("OutputStream for given output Uri is null");
        } finally {
            BitmapLoadUtils.close(source);
            BitmapLoadUtils.close(sink);
            if (response != null) {
                BitmapLoadUtils.close(response.body());
            }
            client.dispatcher().cancelAll();
            this.mInputUri = this.mOutputUri;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(BitmapWorkerResult result) {
        if (result.mBitmapWorkerException == null) {
            this.mBitmapLoadCallback.onBitmapLoaded(result.mBitmapResult, result.mExifInfo, this.mInputUri.getPath(), this.mOutputUri == null ? null : this.mOutputUri.getPath());
        } else {
            this.mBitmapLoadCallback.onFailure(result.mBitmapWorkerException);
        }
    }

    private boolean checkSize(Bitmap bitmap, BitmapFactory.Options options) {
        int bitmapSize = bitmap != null ? bitmap.getByteCount() : 0;
        if (bitmapSize <= MAX_BITMAP_SIZE) {
            return false;
        }
        options.inSampleSize *= 2;
        return true;
    }
}
