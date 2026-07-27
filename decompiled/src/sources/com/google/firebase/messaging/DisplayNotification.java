package com.google.firebase.messaging;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.CommonNotificationBuilder;
import com.google.firebase.messaging.Constants;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes16.dex */
class DisplayNotification {
    private static final int IMAGE_DOWNLOAD_TIMEOUT_SECONDS = 5;
    private final Context context;
    private final ExecutorService networkIoExecutor;
    private final NotificationParams params;

    public DisplayNotification(Context context, NotificationParams params, ExecutorService networkIoExecutor) {
        this.networkIoExecutor = networkIoExecutor;
        this.context = context;
        this.params = params;
    }

    private boolean isAppForeground() {
        KeyguardManager keyguardManager = (KeyguardManager) this.context.getSystemService("keyguard");
        if (keyguardManager.inKeyguardRestrictedInputMode()) {
            return false;
        }
        if (!PlatformVersion.isAtLeastLollipop()) {
            SystemClock.sleep(10L);
        }
        int pid = Process.myPid();
        ActivityManager am = (ActivityManager) this.context.getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> appProcesses = am.getRunningAppProcesses();
        if (appProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo process : appProcesses) {
                if (process.pid == pid) {
                    return process.importance == 100;
                }
            }
        }
        return false;
    }

    boolean handleNotification() {
        if (this.params.getBoolean(Constants.MessageNotificationKeys.NO_UI)) {
            return true;
        }
        if (isAppForeground()) {
            return false;
        }
        ImageDownload imageDownload = startImageDownloadInBackground();
        CommonNotificationBuilder.DisplayNotificationInfo notificationInfo = CommonNotificationBuilder.createNotificationInfo(this.context, this.params);
        waitForAndApplyImageDownload(notificationInfo.notificationBuilder, imageDownload);
        showNotification(notificationInfo);
        return true;
    }

    private ImageDownload startImageDownloadInBackground() {
        String imageUrl = this.params.getString(Constants.MessageNotificationKeys.IMAGE_URL);
        ImageDownload imageDownload = ImageDownload.create(imageUrl);
        if (imageDownload != null) {
            imageDownload.start(this.networkIoExecutor);
        }
        return imageDownload;
    }

    private void waitForAndApplyImageDownload(NotificationCompat.Builder n, ImageDownload imageDownload) {
        if (imageDownload == null) {
            return;
        }
        try {
            Bitmap bitmap = (Bitmap) Tasks.await(imageDownload.getTask(), 5L, TimeUnit.SECONDS);
            n.setLargeIcon(bitmap);
            n.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon((Bitmap) null));
        } catch (InterruptedException e) {
            Log.w(Constants.TAG, "Interrupted while downloading image, showing notification without it");
            imageDownload.close();
            Thread.currentThread().interrupt();
        } catch (ExecutionException e2) {
            Log.w(Constants.TAG, "Failed to download image: " + e2.getCause());
        } catch (TimeoutException e3) {
            Log.w(Constants.TAG, "Failed to download image in time, showing notification without it");
            imageDownload.close();
        }
    }

    private void showNotification(CommonNotificationBuilder.DisplayNotificationInfo info) {
        if (Log.isLoggable(Constants.TAG, 3)) {
            Log.d(Constants.TAG, "Showing notification");
        }
        NotificationManager notificationManager = (NotificationManager) this.context.getSystemService("notification");
        notificationManager.notify(info.tag, info.id, info.notificationBuilder.build());
    }
}
