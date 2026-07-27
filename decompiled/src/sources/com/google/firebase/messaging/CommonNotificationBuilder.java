package com.google.firebase.messaging;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.android.gms.cloudmessaging.CloudMessagingReceiver;
import com.google.firebase.messaging.Constants;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes16.dex */
public final class CommonNotificationBuilder {
    private static final String ACTION_RECEIVER = "com.google.android.c2dm.intent.RECEIVE";
    public static final String FCM_FALLBACK_NOTIFICATION_CHANNEL = "fcm_fallback_notification_channel";
    public static final String FCM_FALLBACK_NOTIFICATION_CHANNEL_LABEL = "fcm_fallback_notification_channel_label";
    private static final String FCM_FALLBACK_NOTIFICATION_CHANNEL_NAME_NO_RESOURCE = "Misc";
    private static final int ILLEGAL_RESOURCE_ID = 0;
    public static final String METADATA_DEFAULT_CHANNEL_ID = "com.google.firebase.messaging.default_notification_channel_id";
    public static final String METADATA_DEFAULT_COLOR = "com.google.firebase.messaging.default_notification_color";
    public static final String METADATA_DEFAULT_ICON = "com.google.firebase.messaging.default_notification_icon";
    private static final AtomicInteger requestCodeProvider = new AtomicInteger((int) SystemClock.elapsedRealtime());

    private CommonNotificationBuilder() {
    }

    static DisplayNotificationInfo createNotificationInfo(Context context, NotificationParams params) {
        Bundle manifestMetadata = getManifestMetadata(context.getPackageManager(), context.getPackageName());
        return createNotificationInfo(context, context, params, getOrCreateChannel(context, params.getNotificationChannelId(), manifestMetadata), manifestMetadata);
    }

    public static DisplayNotificationInfo createNotificationInfo(Context callingContext, Context appContext, NotificationParams params, String channelId, Bundle manifestMetadata) {
        String pkgName = appContext.getPackageName();
        Resources appResources = appContext.getResources();
        PackageManager appPackageManager = appContext.getPackageManager();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(appContext, channelId);
        String title = params.getPossiblyLocalizedString(appResources, pkgName, Constants.MessageNotificationKeys.TITLE);
        if (!TextUtils.isEmpty(title)) {
            builder.setContentTitle(title);
        }
        String body = params.getPossiblyLocalizedString(appResources, pkgName, Constants.MessageNotificationKeys.BODY);
        if (!TextUtils.isEmpty(body)) {
            builder.setContentText(body);
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(body));
        }
        int smallIcon = getSmallIcon(appPackageManager, appResources, pkgName, params.getString(Constants.MessageNotificationKeys.ICON), manifestMetadata);
        builder.setSmallIcon(smallIcon);
        Uri sound = getSound(pkgName, params, appResources);
        if (sound != null) {
            builder.setSound(sound);
        }
        builder.setContentIntent(createContentIntent(callingContext, params, pkgName, appPackageManager));
        PendingIntent deleteIntent = createDeleteIntent(callingContext, appContext, params);
        if (deleteIntent != null) {
            builder.setDeleteIntent(deleteIntent);
        }
        Integer color = getColor(appContext, params.getString(Constants.MessageNotificationKeys.COLOR), manifestMetadata);
        if (color != null) {
            builder.setColor(color.intValue());
        }
        boolean sticky = params.getBoolean(Constants.MessageNotificationKeys.STICKY);
        builder.setAutoCancel(!sticky);
        boolean localOnly = params.getBoolean(Constants.MessageNotificationKeys.LOCAL_ONLY);
        builder.setLocalOnly(localOnly);
        String ticker = params.getString(Constants.MessageNotificationKeys.TICKER);
        if (ticker != null) {
            builder.setTicker(ticker);
        }
        Integer notificationPriority = params.getNotificationPriority();
        if (notificationPriority != null) {
            builder.setPriority(notificationPriority.intValue());
        }
        Integer visibility = params.getVisibility();
        if (visibility != null) {
            builder.setVisibility(visibility.intValue());
        }
        Integer notificationCount = params.getNotificationCount();
        if (notificationCount != null) {
            builder.setNumber(notificationCount.intValue());
        }
        Long eventTime = params.getLong(Constants.MessageNotificationKeys.EVENT_TIME);
        if (eventTime != null) {
            builder.setShowWhen(true);
            builder.setWhen(eventTime.longValue());
        }
        long[] vibrateTimings = params.getVibrateTimings();
        if (vibrateTimings != null) {
            builder.setVibrate(vibrateTimings);
        }
        int[] lightSettings = params.getLightSettings();
        if (lightSettings != null) {
            builder.setLights(lightSettings[0], lightSettings[1], lightSettings[2]);
        }
        builder.setDefaults(getConsolidatedDefaults(params));
        return new DisplayNotificationInfo(builder, getTag(params), 0);
    }

    private static int getConsolidatedDefaults(NotificationParams params) {
        int result = 0;
        if (params.getBoolean(Constants.MessageNotificationKeys.DEFAULT_SOUND)) {
            result = 0 | 1;
        }
        if (params.getBoolean(Constants.MessageNotificationKeys.DEFAULT_VIBRATE_TIMINGS)) {
            result |= 2;
        }
        if (params.getBoolean(Constants.MessageNotificationKeys.DEFAULT_LIGHT_SETTINGS)) {
            return result | 4;
        }
        return result;
    }

    private static boolean isValidIcon(Resources resources, int resId) {
        if (Build.VERSION.SDK_INT != 26) {
            return true;
        }
        try {
            Drawable icon = resources.getDrawable(resId, null);
            if (!(icon instanceof AdaptiveIconDrawable)) {
                return true;
            }
            Log.e(Constants.TAG, "Adaptive icons cannot be used in notifications. Ignoring icon id: " + resId);
            return false;
        } catch (Resources.NotFoundException e) {
            Log.e(Constants.TAG, "Couldn't find resource " + resId + ", treating it as an invalid icon");
            return false;
        }
    }

    private static int getSmallIcon(PackageManager packageManager, Resources resources, String pkgName, String resourceKey, Bundle manifestMetadata) {
        if (!TextUtils.isEmpty(resourceKey)) {
            int iconId = resources.getIdentifier(resourceKey, "drawable", pkgName);
            if (iconId != 0 && isValidIcon(resources, iconId)) {
                return iconId;
            }
            int iconId2 = resources.getIdentifier(resourceKey, "mipmap", pkgName);
            if (iconId2 != 0 && isValidIcon(resources, iconId2)) {
                return iconId2;
            }
            Log.w(Constants.TAG, "Icon resource " + resourceKey + " not found. Notification will use default icon.");
        }
        int iconId3 = manifestMetadata.getInt(METADATA_DEFAULT_ICON, 0);
        if (iconId3 == 0 || !isValidIcon(resources, iconId3)) {
            try {
                iconId3 = packageManager.getApplicationInfo(pkgName, 0).icon;
            } catch (PackageManager.NameNotFoundException e) {
                Log.w(Constants.TAG, "Couldn't get own application info: " + e);
            }
        }
        if (iconId3 == 0 || !isValidIcon(resources, iconId3)) {
            return android.R.drawable.sym_def_app_icon;
        }
        return iconId3;
    }

    private static Integer getColor(Context context, String color, Bundle manifestMetadata) {
        if (!TextUtils.isEmpty(color)) {
            try {
                return Integer.valueOf(Color.parseColor(color));
            } catch (IllegalArgumentException e) {
                Log.w(Constants.TAG, "Color is invalid: " + color + ". Notification will use default color.");
            }
        }
        int colorResourceId = manifestMetadata.getInt(METADATA_DEFAULT_COLOR, 0);
        if (colorResourceId != 0) {
            try {
                return Integer.valueOf(ContextCompat.getColor(context, colorResourceId));
            } catch (Resources.NotFoundException e2) {
                Log.w(Constants.TAG, "Cannot find the color resource referenced in AndroidManifest.");
                return null;
            }
        }
        return null;
    }

    private static Uri getSound(String pkgName, NotificationParams params, Resources resources) {
        String soundName = params.getSoundResourceName();
        if (TextUtils.isEmpty(soundName)) {
            return null;
        }
        if (!"default".equals(soundName)) {
            int soundId = resources.getIdentifier(soundName, "raw", pkgName);
            if (soundId != 0) {
                return Uri.parse("android.resource://" + pkgName + "/raw/" + soundName);
            }
        }
        return RingtoneManager.getDefaultUri(2);
    }

    private static PendingIntent createContentIntent(Context context, NotificationParams params, String pkgName, PackageManager pm) {
        Intent intent = createTargetIntent(pkgName, params, pm);
        if (intent == null) {
            return null;
        }
        intent.addFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        intent.putExtras(params.paramsWithReservedKeysRemoved());
        if (shouldUploadMetrics(params)) {
            intent.putExtra(Constants.MessageNotificationKeys.ANALYTICS_DATA, params.paramsForAnalyticsIntent());
        }
        return PendingIntent.getActivity(context, generatePendingIntentRequestCode(), intent, getPendingIntentFlags(1073741824));
    }

    private static Intent createTargetIntent(String pkgName, NotificationParams params, PackageManager pm) {
        String action = params.getString(Constants.MessageNotificationKeys.CLICK_ACTION);
        if (!TextUtils.isEmpty(action)) {
            Intent intent = new Intent(action);
            intent.setPackage(pkgName);
            intent.setFlags(268435456);
            return intent;
        }
        Uri link = params.getLink();
        if (link != null) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setPackage(pkgName);
            intent2.setData(link);
            return intent2;
        }
        Intent intent3 = pm.getLaunchIntentForPackage(pkgName);
        if (intent3 == null) {
            Log.w(Constants.TAG, "No activity found to launch app");
        }
        return intent3;
    }

    private static Bundle getManifestMetadata(PackageManager pm, String packageName) {
        try {
            ApplicationInfo info = pm.getApplicationInfo(packageName, 128);
            if (info != null && info.metaData != null) {
                return info.metaData;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(Constants.TAG, "Couldn't get own application info: " + e);
        }
        return Bundle.EMPTY;
    }

    public static String getOrCreateChannel(Context context, String msgChannel, Bundle manifestMetadata) {
        String defaultChannelName;
        try {
            if (context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).targetSdkVersion < 26) {
                return null;
            }
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
            if (!TextUtils.isEmpty(msgChannel)) {
                if (notificationManager.getNotificationChannel(msgChannel) != null) {
                    return msgChannel;
                }
                Log.w(Constants.TAG, "Notification Channel requested (" + msgChannel + ") has not been created by the app. Manifest configuration, or default, value will be used.");
            }
            String manifestChannel = manifestMetadata.getString(METADATA_DEFAULT_CHANNEL_ID);
            if (!TextUtils.isEmpty(manifestChannel)) {
                if (notificationManager.getNotificationChannel(manifestChannel) != null) {
                    return manifestChannel;
                }
                Log.w(Constants.TAG, "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
            } else {
                Log.w(Constants.TAG, "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
            }
            if (notificationManager.getNotificationChannel(FCM_FALLBACK_NOTIFICATION_CHANNEL) == null) {
                int channelLabelResourceId = context.getResources().getIdentifier(FCM_FALLBACK_NOTIFICATION_CHANNEL_LABEL, TypedValues.Custom.S_STRING, context.getPackageName());
                if (channelLabelResourceId == 0) {
                    Log.e(Constants.TAG, "String resource \"fcm_fallback_notification_channel_label\" is not found. Using default string channel name.");
                    defaultChannelName = FCM_FALLBACK_NOTIFICATION_CHANNEL_NAME_NO_RESOURCE;
                } else {
                    defaultChannelName = context.getString(channelLabelResourceId);
                }
                notificationManager.createNotificationChannel(new NotificationChannel(FCM_FALLBACK_NOTIFICATION_CHANNEL, defaultChannelName, 3));
            }
            return FCM_FALLBACK_NOTIFICATION_CHANNEL;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    private static int generatePendingIntentRequestCode() {
        return requestCodeProvider.incrementAndGet();
    }

    private static int getPendingIntentFlags(int baseFlags) {
        return 67108864 | baseFlags;
    }

    private static PendingIntent createDeleteIntent(Context callingContext, Context appContext, NotificationParams params) {
        if (!shouldUploadMetrics(params)) {
            return null;
        }
        Intent dismissIntent = new Intent(CloudMessagingReceiver.IntentActionKeys.NOTIFICATION_DISMISS).putExtras(params.paramsForAnalyticsIntent());
        return createMessagingPendingIntent(callingContext, appContext, dismissIntent);
    }

    private static PendingIntent createMessagingPendingIntent(Context callingContext, Context appContext, Intent intent) {
        return PendingIntent.getBroadcast(callingContext, generatePendingIntentRequestCode(), new Intent(ACTION_RECEIVER).setPackage(appContext.getPackageName()).putExtra(CloudMessagingReceiver.IntentKeys.WRAPPED_INTENT, intent), getPendingIntentFlags(1073741824));
    }

    static boolean shouldUploadMetrics(NotificationParams params) {
        return params.getBoolean(Constants.AnalyticsKeys.ENABLED);
    }

    private static String getTag(NotificationParams params) {
        String tag = params.getString(Constants.MessageNotificationKeys.TAG);
        if (!TextUtils.isEmpty(tag)) {
            return tag;
        }
        return "FCM-Notification:" + SystemClock.uptimeMillis();
    }

    public static class DisplayNotificationInfo {
        public final int id;
        public final NotificationCompat.Builder notificationBuilder;
        public final String tag;

        DisplayNotificationInfo(NotificationCompat.Builder notificationBuilder, String tag, int id) {
            this.notificationBuilder = notificationBuilder;
            this.tag = tag;
            this.id = id;
        }
    }
}
