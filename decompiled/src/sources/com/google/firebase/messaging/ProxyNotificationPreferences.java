package com.google.firebase.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.privacysandbox.ads.adservices.adid.AdIdManagerImplCommon$$ExternalSyntheticLambda0;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.tasks.OnSuccessListener;

/* loaded from: classes16.dex */
final class ProxyNotificationPreferences {
    private static final String FCM_PREFERENCES = "com.google.firebase.messaging";

    private ProxyNotificationPreferences() {
    }

    private static SharedPreferences getPreference(Context context) {
        Context appContext = context.getApplicationContext();
        if (appContext == null) {
            appContext = context;
        }
        return appContext.getSharedPreferences("com.google.firebase.messaging", 0);
    }

    static void setProxyNotificationsInitialized(Context context, boolean isInitialized) {
        SharedPreferences.Editor preferencesEditor = getPreference(context).edit();
        preferencesEditor.putBoolean("proxy_notification_initialized", isInitialized);
        preferencesEditor.apply();
    }

    static void setProxyRetention(final Context context, GmsRpc gmsRpc, final boolean retention) {
        if (!PlatformVersion.isAtLeastQ()) {
            return;
        }
        SharedPreferences preferences = getPreference(context);
        if (!isProxyNotificationRetentionSet(preferences, retention)) {
            gmsRpc.setRetainProxiedNotifications(retention).addOnSuccessListener(new AdIdManagerImplCommon$$ExternalSyntheticLambda0(), new OnSuccessListener() { // from class: com.google.firebase.messaging.ProxyNotificationPreferences$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    ProxyNotificationPreferences.setProxyRetentionPreferences(context, retention);
                }
            });
        }
    }

    static boolean isProxyNotificationInitialized(Context context) {
        return getPreference(context).getBoolean("proxy_notification_initialized", false);
    }

    static boolean isProxyNotificationRetentionSet(SharedPreferences preferences, boolean retention) {
        return preferences.contains("proxy_retention") && preferences.getBoolean("proxy_retention", false) == retention;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setProxyRetentionPreferences(Context context, boolean retention) {
        SharedPreferences.Editor preferencesEditor = getPreference(context).edit();
        preferencesEditor.putBoolean("proxy_retention", retention);
        preferencesEditor.apply();
    }
}
