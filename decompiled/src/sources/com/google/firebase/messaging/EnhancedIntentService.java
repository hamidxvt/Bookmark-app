package com.google.firebase.messaging;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.privacysandbox.ads.adservices.adid.AdIdManagerImplCommon$$ExternalSyntheticLambda0;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.messaging.WithinAppServiceBinder;
import java.util.concurrent.ExecutorService;

/* loaded from: classes16.dex */
public abstract class EnhancedIntentService extends Service {
    static final long MESSAGE_TIMEOUT_S = 20;
    private static final String TAG = "EnhancedIntentService";
    private Binder binder;
    private int lastStartId;
    final ExecutorService executor = FcmExecutors.newIntentHandleExecutor();
    private final Object lock = new Object();
    private int runningTasks = 0;

    public abstract void handleIntent(Intent intent);

    @Override // android.app.Service
    public final synchronized IBinder onBind(Intent intent) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Service received bind request");
        }
        if (this.binder == null) {
            this.binder = new WithinAppServiceBinder(new WithinAppServiceBinder.IntentHandler() { // from class: com.google.firebase.messaging.EnhancedIntentService.1
                @Override // com.google.firebase.messaging.WithinAppServiceBinder.IntentHandler
                public Task<Void> handle(Intent intent2) {
                    return EnhancedIntentService.this.processIntent(intent2);
                }
            });
        }
        return this.binder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<Void> processIntent(final Intent intent) {
        if (handleIntentOnMainThread(intent)) {
            return Tasks.forResult(null);
        }
        final TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        this.executor.execute(new Runnable() { // from class: com.google.firebase.messaging.EnhancedIntentService$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                EnhancedIntentService.this.m381x624ce8b2(intent, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$processIntent$0$com-google-firebase-messaging-EnhancedIntentService, reason: not valid java name */
    /* synthetic */ void m381x624ce8b2(Intent intent, TaskCompletionSource taskCompletionSource) {
        try {
            handleIntent(intent);
        } finally {
            taskCompletionSource.setResult(null);
        }
    }

    @Override // android.app.Service
    public final int onStartCommand(final Intent originalIntent, int flags, int startId) {
        synchronized (this.lock) {
            this.lastStartId = startId;
            this.runningTasks++;
        }
        Intent intent = getStartCommandIntent(originalIntent);
        if (intent == null) {
            finishTask(originalIntent);
            return 2;
        }
        Task<Void> task = processIntent(intent);
        if (task.isComplete()) {
            finishTask(originalIntent);
            return 2;
        }
        task.addOnCompleteListener(new AdIdManagerImplCommon$$ExternalSyntheticLambda0(), new OnCompleteListener() { // from class: com.google.firebase.messaging.EnhancedIntentService$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task2) {
                EnhancedIntentService.this.m380x83fa35aa(originalIntent, task2);
            }
        });
        return 3;
    }

    /* renamed from: lambda$onStartCommand$1$com-google-firebase-messaging-EnhancedIntentService, reason: not valid java name */
    /* synthetic */ void m380x83fa35aa(Intent originalIntent, Task unusedTask) {
        finishTask(originalIntent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.executor.shutdown();
        super.onDestroy();
    }

    private void finishTask(Intent originalIntent) {
        if (originalIntent != null) {
            WakeLockHolder.completeWakefulIntent(originalIntent);
        }
        synchronized (this.lock) {
            this.runningTasks--;
            if (this.runningTasks == 0) {
                stopSelfResultHook(this.lastStartId);
            }
        }
    }

    boolean stopSelfResultHook(int startId) {
        return stopSelfResult(startId);
    }

    protected Intent getStartCommandIntent(Intent originalIntent) {
        return originalIntent;
    }

    public boolean handleIntentOnMainThread(Intent intent) {
        return false;
    }
}
