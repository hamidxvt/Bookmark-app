package com.google.firebase.messaging;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* loaded from: classes16.dex */
public class ImageDownload implements Closeable {
    private static final int MAX_IMAGE_SIZE_BYTES = 1048576;
    private volatile Future<?> future;
    private Task<Bitmap> task;
    private final URL url;

    public static ImageDownload create(String imageUrl) {
        if (TextUtils.isEmpty(imageUrl)) {
            return null;
        }
        try {
            return new ImageDownload(new URL(imageUrl));
        } catch (MalformedURLException e) {
            Log.w(Constants.TAG, "Not downloading image, bad URL: " + imageUrl);
            return null;
        }
    }

    private ImageDownload(URL url) {
        this.url = url;
    }

    public void start(ExecutorService executor) {
        final TaskCompletionSource<Bitmap> taskCompletionSource = new TaskCompletionSource<>();
        this.future = executor.submit(new Runnable() { // from class: com.google.firebase.messaging.ImageDownload$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ImageDownload.this.m396lambda$start$0$comgooglefirebasemessagingImageDownload(taskCompletionSource);
            }
        });
        this.task = taskCompletionSource.getTask();
    }

    /* renamed from: lambda$start$0$com-google-firebase-messaging-ImageDownload, reason: not valid java name */
    /* synthetic */ void m396lambda$start$0$comgooglefirebasemessagingImageDownload(TaskCompletionSource taskCompletionSource) {
        try {
            Bitmap bitmap = blockingDownload();
            taskCompletionSource.setResult(bitmap);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    public Task<Bitmap> getTask() {
        return (Task) Preconditions.checkNotNull(this.task);
    }

    public Bitmap blockingDownload() throws IOException {
        if (Log.isLoggable(Constants.TAG, 4)) {
            Log.i(Constants.TAG, "Starting download of: " + this.url);
        }
        byte[] imageBytes = blockingDownloadBytes();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        if (bitmap == null) {
            throw new IOException("Failed to decode image: " + this.url);
        }
        if (Log.isLoggable(Constants.TAG, 3)) {
            Log.d(Constants.TAG, "Successfully downloaded image: " + this.url);
        }
        return bitmap;
    }

    private byte[] blockingDownloadBytes() throws IOException {
        URLConnection connection = this.url.openConnection();
        int contentLength = connection.getContentLength();
        if (contentLength > 1048576) {
            throw new IOException("Content-Length exceeds max size of 1048576");
        }
        InputStream connectionInputStream = connection.getInputStream();
        try {
            byte[] bytes = ByteStreams.toByteArray(ByteStreams.limit(connectionInputStream, 1048577L));
            if (connectionInputStream != null) {
                connectionInputStream.close();
            }
            if (Log.isLoggable(Constants.TAG, 2)) {
                Log.v(Constants.TAG, "Downloaded " + bytes.length + " bytes from " + this.url);
            }
            if (bytes.length > 1048576) {
                throw new IOException("Image exceeds max size of 1048576");
            }
            return bytes;
        } catch (Throwable th) {
            if (connectionInputStream != null) {
                try {
                    connectionInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.future.cancel(true);
    }
}
