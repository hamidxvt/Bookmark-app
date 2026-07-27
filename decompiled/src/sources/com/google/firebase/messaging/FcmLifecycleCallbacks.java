package com.google.firebase.messaging;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.messaging.Constants;
import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: classes16.dex */
class FcmLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private static final int RECENTLY_LOGGED_MESSAGE_IDS_MAX_SIZE = 10;
    private final Queue<String> recentlyLoggedMessageIds = new ArrayDeque(10);

    FcmLifecycleCallbacks() {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity createdActivity, Bundle instanceState) {
        Intent startingIntent = createdActivity.getIntent();
        if (startingIntent == null) {
            return;
        }
        m382xd8132052(startingIntent);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity pausedActivity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity destroyedActivity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: logNotificationOpen, reason: merged with bridge method [inline-methods] */
    public void m382xd8132052(Intent startingIntent) {
        Bundle analyticsData = null;
        try {
            Bundle extras = startingIntent.getExtras();
            if (extras != null) {
                String messageId = MessagingAnalytics.getMessageId(extras);
                if (!TextUtils.isEmpty(messageId)) {
                    if (this.recentlyLoggedMessageIds.contains(messageId)) {
                        return;
                    } else {
                        this.recentlyLoggedMessageIds.add(messageId);
                    }
                }
                analyticsData = extras.getBundle(Constants.MessageNotificationKeys.ANALYTICS_DATA);
            }
        } catch (RuntimeException e) {
            Log.w(Constants.TAG, "Failed trying to get analytics data from Intent extras.", e);
        }
        if (MessagingAnalytics.shouldUploadScionMetrics(analyticsData)) {
            MessagingAnalytics.logNotificationOpen(analyticsData);
        }
    }
}
