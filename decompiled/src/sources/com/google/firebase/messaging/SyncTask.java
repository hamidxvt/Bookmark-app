package com.google.firebase.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes16.dex */
class SyncTask implements Runnable {
    private final FirebaseMessaging firebaseMessaging;
    private final long nextDelaySeconds;
    ExecutorService processorExecutor = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(), new NamedThreadFactory("firebase-iid-executor"));
    private final PowerManager.WakeLock syncWakeLock;

    public SyncTask(FirebaseMessaging firebaseMessaging, long nextDelaySeconds) {
        this.firebaseMessaging = firebaseMessaging;
        this.nextDelaySeconds = nextDelaySeconds;
        PowerManager pm = (PowerManager) getContext().getSystemService("power");
        this.syncWakeLock = pm.newWakeLock(1, "fiid-sync");
        this.syncWakeLock.setReferenceCounted(false);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
            this.syncWakeLock.acquire();
        }
        try {
            try {
                this.firebaseMessaging.setSyncScheduledOrRunning(true);
            } catch (IOException e) {
                Log.e(Constants.TAG, "Topic sync or token retrieval failed on hard failure exceptions: " + e.getMessage() + ". Won't retry the operation.");
                this.firebaseMessaging.setSyncScheduledOrRunning(false);
                if (!ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
                    return;
                }
            }
            if (!this.firebaseMessaging.isGmsCorePresent()) {
                this.firebaseMessaging.setSyncScheduledOrRunning(false);
                if (ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
                    this.syncWakeLock.release();
                    return;
                }
                return;
            }
            if (ServiceStarter.getInstance().hasAccessNetworkStatePermission(getContext()) && !isDeviceConnected()) {
                ConnectivityChangeReceiver receiver = new ConnectivityChangeReceiver(this);
                receiver.registerReceiver();
                if (ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
                    this.syncWakeLock.release();
                    return;
                }
                return;
            }
            if (maybeRefreshToken()) {
                this.firebaseMessaging.setSyncScheduledOrRunning(false);
            } else {
                this.firebaseMessaging.syncWithDelaySecondsInternal(this.nextDelaySeconds);
            }
            if (!ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
                return;
            }
            this.syncWakeLock.release();
        } catch (Throwable th) {
            if (ServiceStarter.getInstance().hasWakeLockPermission(getContext())) {
                this.syncWakeLock.release();
            }
            throw th;
        }
    }

    boolean maybeRefreshToken() throws IOException {
        try {
            String newToken = this.firebaseMessaging.blockingGetToken();
            if (newToken == null) {
                Log.e(Constants.TAG, "Token retrieval failed: null");
                return false;
            }
            if (Log.isLoggable(Constants.TAG, 3)) {
                Log.d(Constants.TAG, "Token successfully retrieved");
                return true;
            }
            return true;
        } catch (IOException e) {
            if (GmsRpc.isErrorMessageForRetryableError(e.getMessage())) {
                Log.w(Constants.TAG, "Token retrieval failed: " + e.getMessage() + ". Will retry token retrieval");
                return false;
            }
            if (e.getMessage() == null) {
                Log.w(Constants.TAG, "Token retrieval failed without exception message. Will retry token retrieval");
                return false;
            }
            throw e;
        } catch (SecurityException e2) {
            Log.w(Constants.TAG, "Token retrieval failed with SecurityException. Will retry token retrieval");
            return false;
        }
    }

    Context getContext() {
        return this.firebaseMessaging.getApplicationContext();
    }

    boolean isDeviceConnected() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService("connectivity");
        NetworkInfo networkInfo = cm != null ? cm.getActiveNetworkInfo() : null;
        return networkInfo != null && networkInfo.isConnected();
    }

    static class ConnectivityChangeReceiver extends BroadcastReceiver {
        private Context receiverContext;
        private SyncTask task;

        public ConnectivityChangeReceiver(SyncTask task) {
            this.task = task;
        }

        public void registerReceiver() {
            if (SyncTask.isDebugLogEnabled()) {
                Log.d(Constants.TAG, "Connectivity change received registered");
            }
            IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
            if (this.task != null) {
                this.receiverContext = this.task.getContext();
                this.receiverContext.registerReceiver(this, intentFilter);
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (this.task == null || !this.task.isDeviceConnected()) {
                return;
            }
            if (SyncTask.isDebugLogEnabled()) {
                Log.d(Constants.TAG, "Connectivity changed. Starting background sync.");
            }
            this.task.firebaseMessaging.enqueueTaskWithDelaySeconds(this.task, 0L);
            if (this.receiverContext != null) {
                this.receiverContext.unregisterReceiver(this);
            }
            this.task = null;
        }
    }

    static boolean isDebugLogEnabled() {
        return Log.isLoggable(Constants.TAG, 3);
    }
}
