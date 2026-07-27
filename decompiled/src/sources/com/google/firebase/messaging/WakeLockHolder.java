package com.google.firebase.messaging;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.stats.WakeLock;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.TimeUnit;

/* loaded from: classes16.dex */
final class WakeLockHolder {
    private static final String EXTRA_WAKEFUL_INTENT = "com.google.firebase.iid.WakeLockHolder.wakefulintent";
    static final long WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS = TimeUnit.MINUTES.toMillis(1);
    private static final Object syncObject = new Object();
    private static WakeLock wakeLock;

    WakeLockHolder() {
    }

    private static void checkAndInitWakeLock(Context context) {
        if (wakeLock == null) {
            wakeLock = new WakeLock(context, 1, "wake:com.google.firebase.iid.WakeLockHolder");
            wakeLock.setReferenceCounted(true);
        }
    }

    static ComponentName startWakefulService(Context context, Intent intent) {
        synchronized (syncObject) {
            checkAndInitWakeLock(context);
            boolean isWakeLockAlreadyAcquired = isWakefulIntent(intent);
            setAsWakefulIntent(intent, true);
            ComponentName comp = context.startService(intent);
            if (comp == null) {
                return null;
            }
            if (!isWakeLockAlreadyAcquired) {
                wakeLock.acquire(WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS);
            }
            return comp;
        }
    }

    static void sendWakefulServiceIntent(Context context, WithinAppServiceConnection connection, final Intent intent) {
        synchronized (syncObject) {
            checkAndInitWakeLock(context);
            boolean isWakeLockAlreadyAcquired = isWakefulIntent(intent);
            setAsWakefulIntent(intent, true);
            if (!isWakeLockAlreadyAcquired) {
                wakeLock.acquire(WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS);
            }
            connection.sendIntent(intent).addOnCompleteListener(new OnCompleteListener() { // from class: com.google.firebase.messaging.WakeLockHolder$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    WakeLockHolder.completeWakefulIntent(intent);
                }
            });
        }
    }

    private static void setAsWakefulIntent(Intent intent, boolean isWakeful) {
        intent.putExtra(EXTRA_WAKEFUL_INTENT, isWakeful);
    }

    static boolean isWakefulIntent(Intent intent) {
        return intent.getBooleanExtra(EXTRA_WAKEFUL_INTENT, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void completeWakefulIntent(Intent intent) {
        synchronized (syncObject) {
            if (wakeLock != null && isWakefulIntent(intent)) {
                setAsWakefulIntent(intent, false);
                wakeLock.release();
            }
        }
    }

    static void acquireWakeLock(Intent intent, long millis) {
        synchronized (syncObject) {
            if (wakeLock != null) {
                setAsWakefulIntent(intent, true);
                wakeLock.acquire(millis);
            }
        }
    }

    static void initWakeLock(Context context) {
        synchronized (syncObject) {
            checkAndInitWakeLock(context);
        }
    }

    static void reset() {
        synchronized (syncObject) {
            wakeLock = null;
        }
    }
}
