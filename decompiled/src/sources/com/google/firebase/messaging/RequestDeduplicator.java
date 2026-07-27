package com.google.firebase.messaging;

import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import java.util.concurrent.Executor;

/* loaded from: classes16.dex */
class RequestDeduplicator {
    private final Executor executor;
    private final Map<String, Task<String>> getTokenRequests = new ArrayMap();

    interface GetTokenRequest {
        Task<String> start();
    }

    RequestDeduplicator(Executor executor) {
        this.executor = executor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    synchronized Task<String> getOrStartGetTokenRequest(final String authorizedEntity, GetTokenRequest request) {
        Task<String> ongoingTask = this.getTokenRequests.get(authorizedEntity);
        if (ongoingTask != null) {
            if (Log.isLoggable(Constants.TAG, 3)) {
                Log.d(Constants.TAG, "Joining ongoing request for: " + authorizedEntity);
            }
            return ongoingTask;
        }
        if (Log.isLoggable(Constants.TAG, 3)) {
            Log.d(Constants.TAG, "Making new request for: " + authorizedEntity);
        }
        Task continueWithTask = request.start().continueWithTask(this.executor, new Continuation() { // from class: com.google.firebase.messaging.RequestDeduplicator$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return RequestDeduplicator.this.m397x7161fc54(authorizedEntity, task);
            }
        });
        this.getTokenRequests.put(authorizedEntity, continueWithTask);
        return continueWithTask;
    }

    /* renamed from: lambda$getOrStartGetTokenRequest$0$com-google-firebase-messaging-RequestDeduplicator, reason: not valid java name */
    /* synthetic */ Task m397x7161fc54(String authorizedEntity, Task task) throws Exception {
        synchronized (this) {
            this.getTokenRequests.remove(authorizedEntity);
        }
        return task;
    }
}
