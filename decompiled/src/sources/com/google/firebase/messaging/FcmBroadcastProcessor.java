package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.privacysandbox.ads.adservices.adid.AdIdManagerImplCommon$$ExternalSyntheticLambda0;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* loaded from: classes16.dex */
public class FcmBroadcastProcessor {
    private static final String EXTRA_BINARY_DATA = "rawData";
    private static final String EXTRA_BINARY_DATA_BASE_64 = "gcm.rawData64";
    private static WithinAppServiceConnection fcmServiceConn;
    private static final Object lock = new Object();
    private final Context context;
    private final Executor executor;

    public FcmBroadcastProcessor(Context context) {
        this.context = context;
        this.executor = new AdIdManagerImplCommon$$ExternalSyntheticLambda0();
    }

    public FcmBroadcastProcessor(Context context, ExecutorService executor) {
        this.context = context;
        this.executor = executor;
    }

    public Task<Integer> process(Intent intent) {
        String binaryData64 = intent.getStringExtra(EXTRA_BINARY_DATA_BASE_64);
        if (binaryData64 != null) {
            intent.putExtra("rawData", Base64.decode(binaryData64, 0));
            intent.removeExtra(EXTRA_BINARY_DATA_BASE_64);
        }
        return startMessagingService(this.context, intent);
    }

    public Task<Integer> startMessagingService(final Context context, final Intent intent) {
        boolean subjectToBackgroundCheck = PlatformVersion.isAtLeastO() && context.getApplicationInfo().targetSdkVersion >= 26;
        final boolean isHighPriority = (intent.getFlags() & 268435456) != 0;
        if (subjectToBackgroundCheck && !isHighPriority) {
            return bindToMessagingService(context, intent, isHighPriority);
        }
        Task<Integer> startServiceResult = Tasks.call(this.executor, new Callable() { // from class: com.google.firebase.messaging.FcmBroadcastProcessor$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Integer valueOf;
                valueOf = Integer.valueOf(ServiceStarter.getInstance().startMessagingService(context, intent));
                return valueOf;
            }
        });
        return startServiceResult.continueWithTask(this.executor, new Continuation() { // from class: com.google.firebase.messaging.FcmBroadcastProcessor$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FcmBroadcastProcessor.lambda$startMessagingService$2(context, intent, isHighPriority, task);
            }
        });
    }

    static /* synthetic */ Task lambda$startMessagingService$2(Context context, Intent intent, boolean isHighPriority, Task r) throws Exception {
        if (!PlatformVersion.isAtLeastO() || ((Integer) r.getResult()).intValue() != 402) {
            return r;
        }
        return bindToMessagingService(context, intent, isHighPriority).continueWith(new AdIdManagerImplCommon$$ExternalSyntheticLambda0(), new Continuation() { // from class: com.google.firebase.messaging.FcmBroadcastProcessor$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                Integer valueOf;
                valueOf = Integer.valueOf(TypedValues.CycleType.TYPE_ALPHA);
                return valueOf;
            }
        });
    }

    private static Task<Integer> bindToMessagingService(Context context, Intent intent, boolean isForegroundBroadcast) {
        if (Log.isLoggable(Constants.TAG, 3)) {
            Log.d(Constants.TAG, "Binding to service");
        }
        WithinAppServiceConnection connection = getServiceConnection(context, "com.google.firebase.MESSAGING_EVENT");
        if (isForegroundBroadcast) {
            if (ServiceStarter.getInstance().hasWakeLockPermission(context)) {
                WakeLockHolder.sendWakefulServiceIntent(context, connection, intent);
            } else {
                connection.sendIntent(intent);
            }
            return Tasks.forResult(-1);
        }
        return connection.sendIntent(intent).continueWith(new AdIdManagerImplCommon$$ExternalSyntheticLambda0(), new Continuation() { // from class: com.google.firebase.messaging.FcmBroadcastProcessor$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FcmBroadcastProcessor.lambda$bindToMessagingService$3(task);
            }
        });
    }

    static /* synthetic */ Integer lambda$bindToMessagingService$3(Task t) throws Exception {
        return -1;
    }

    private static WithinAppServiceConnection getServiceConnection(Context context, String action) {
        WithinAppServiceConnection withinAppServiceConnection;
        synchronized (lock) {
            if (fcmServiceConn == null) {
                fcmServiceConn = new WithinAppServiceConnection(context, action);
            }
            withinAppServiceConnection = fcmServiceConn;
        }
        return withinAppServiceConnection;
    }

    public static void reset() {
        synchronized (lock) {
            fcmServiceConn = null;
        }
    }

    public static void setServiceConnection(WithinAppServiceConnection connection) {
        synchronized (lock) {
            fcmServiceConn = connection;
        }
    }
}
