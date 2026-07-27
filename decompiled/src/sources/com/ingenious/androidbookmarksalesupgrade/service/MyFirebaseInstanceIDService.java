package com.ingenious.androidbookmarksalesupgrade.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViews;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ingenious.androidbookmarksalesupgrade.R;
import com.ingenious.androidbookmarksalesupgrade.ui.activity.HomeActivity;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MyFirebaseInstanceIDService.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0016\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\nJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\nH\u0003¨\u0006\r"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/service/MyFirebaseInstanceIDService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "<init>", "()V", "onMessageReceived", "", "message", "Lcom/google/firebase/messaging/RemoteMessage;", "generateNotification", "title", "", "getRemoteView", "Landroid/widget/RemoteViews;", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final class MyFirebaseInstanceIDService extends FirebaseMessagingService {
    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage message) {
        Intrinsics.checkNotNullParameter(message, "message");
        super.onMessageReceived(message);
        Map<String, String> data = message.getData();
        Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
        data.isEmpty();
        if (message.getNotification() != null) {
            RemoteMessage.Notification notification = message.getNotification();
            Intrinsics.checkNotNull(notification);
            String valueOf = String.valueOf(notification.getTitle());
            RemoteMessage.Notification notification2 = message.getNotification();
            Intrinsics.checkNotNull(notification2);
            generateNotification(valueOf, String.valueOf(notification2.getBody()));
        }
    }

    public final void generateNotification(String title, String message) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        Intent intent = new Intent(this, (Class<?>) HomeActivity.class);
        intent.addFlags(AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), MyFirebaseInstanceIDServiceKt.CHANNEL_ID_2).setSmallIcon(R.drawable.logo).setVibrate(new long[]{1000, 1000, 1000, 1000}).setOnlyAlertOnce(true).setContentIntent(pendingIntent);
        Intrinsics.checkNotNullExpressionValue(builder, "setContentIntent(...)");
        NotificationCompat.Builder builder2 = builder.setContent(getRemoteView(title, message));
        Object systemService = getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        NotificationChannel notificationChannel = new NotificationChannel(MyFirebaseInstanceIDServiceKt.CHANNEL_ID_2, MyFirebaseInstanceIDServiceKt.CHANNEL_NAME_2, 4);
        notificationManager.createNotificationChannel(notificationChannel);
        if (Build.VERSION.SDK_INT >= 33) {
            if (ActivityCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") == 0) {
                notificationManager.notify(0, builder2.build());
                return;
            }
            return;
        }
        notificationManager.notify(0, builder2.build());
    }

    private final RemoteViews getRemoteView(String title, String message) {
        RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.notification_layout);
        remoteView.setTextViewText(R.id.notification_title, title);
        remoteView.setTextViewText(R.id.notification_message, message);
        remoteView.setImageViewResource(R.id.notification_logo, R.drawable.logo);
        return remoteView;
    }
}
