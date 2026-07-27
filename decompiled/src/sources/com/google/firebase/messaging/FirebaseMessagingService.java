package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.cloudmessaging.CloudMessage;
import com.google.android.gms.cloudmessaging.Rpc;
import com.google.firebase.messaging.Constants;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutorService;

/* loaded from: classes16.dex */
public class FirebaseMessagingService extends EnhancedIntentService {
    public static final String ACTION_DIRECT_BOOT_REMOTE_INTENT = "com.google.firebase.messaging.RECEIVE_DIRECT_BOOT";
    static final String ACTION_NEW_TOKEN = "com.google.firebase.messaging.NEW_TOKEN";
    static final String ACTION_REMOTE_INTENT = "com.google.android.c2dm.intent.RECEIVE";
    static final String EXTRA_TOKEN = "token";
    private static final int RECENTLY_RECEIVED_MESSAGE_IDS_MAX_SIZE = 10;
    private static final Queue<String> recentlyReceivedMessageIds = new ArrayDeque(10);
    private Rpc rpc;

    public void onMessageReceived(RemoteMessage message) {
    }

    public void onDeletedMessages() {
    }

    @Deprecated
    public void onMessageSent(String msgId) {
    }

    @Deprecated
    public void onSendError(String msgId, Exception exception) {
    }

    public void onNewToken(String token) {
    }

    @Override // com.google.firebase.messaging.EnhancedIntentService
    protected Intent getStartCommandIntent(Intent originalIntent) {
        return ServiceStarter.getInstance().getMessagingEvent();
    }

    @Override // com.google.firebase.messaging.EnhancedIntentService
    public void handleIntent(Intent intent) {
        String action = intent.getAction();
        if (ACTION_REMOTE_INTENT.equals(action) || ACTION_DIRECT_BOOT_REMOTE_INTENT.equals(action)) {
            handleMessageIntent(intent);
        } else if (ACTION_NEW_TOKEN.equals(action)) {
            onNewToken(intent.getStringExtra(EXTRA_TOKEN));
        } else {
            Log.d(Constants.TAG, "Unknown intent action: " + intent.getAction());
        }
    }

    private void handleMessageIntent(Intent intent) {
        String messageId = intent.getStringExtra(Constants.MessagePayloadKeys.MSGID);
        if (!alreadyReceivedMessage(messageId)) {
            passMessageIntentToSdk(intent);
        }
        getRpc(this).messageHandled(new CloudMessage(intent));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void passMessageIntentToSdk(Intent intent) {
        char c;
        String messageType = intent.getStringExtra(Constants.MessagePayloadKeys.MESSAGE_TYPE);
        if (messageType == null) {
            messageType = Constants.MessageTypes.MESSAGE;
        }
        switch (messageType.hashCode()) {
            case -2062414158:
                if (messageType.equals(Constants.MessageTypes.DELETED)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 102161:
                if (messageType.equals(Constants.MessageTypes.MESSAGE)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 814694033:
                if (messageType.equals(Constants.MessageTypes.SEND_ERROR)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 814800675:
                if (messageType.equals(Constants.MessageTypes.SEND_EVENT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                MessagingAnalytics.logNotificationReceived(intent);
                dispatchMessage(intent);
                break;
            case 1:
                onDeletedMessages();
                break;
            case 2:
                onMessageSent(intent.getStringExtra(Constants.MessagePayloadKeys.MSGID));
                break;
            case 3:
                onSendError(getMessageId(intent), new SendException(intent.getStringExtra(Constants.IPC_BUNDLE_KEY_SEND_ERROR)));
                break;
            default:
                Log.w(Constants.TAG, "Received message with unknown type: " + messageType);
                break;
        }
    }

    private void dispatchMessage(Intent intent) {
        Bundle data = intent.getExtras();
        if (data == null) {
            data = new Bundle();
        }
        data.remove("androidx.content.wakelockid");
        if (NotificationParams.isNotification(data)) {
            NotificationParams params = new NotificationParams(data);
            ExecutorService executor = FcmExecutors.newNetworkIOExecutor();
            DisplayNotification displayNotification = new DisplayNotification(this, params, executor);
            try {
                if (displayNotification.handleNotification()) {
                    return;
                }
                executor.shutdown();
                if (MessagingAnalytics.shouldUploadScionMetrics(intent)) {
                    MessagingAnalytics.logNotificationForeground(intent);
                }
            } finally {
                executor.shutdown();
            }
        }
        onMessageReceived(new RemoteMessage(data));
    }

    private boolean alreadyReceivedMessage(String messageId) {
        if (TextUtils.isEmpty(messageId)) {
            return false;
        }
        if (recentlyReceivedMessageIds.contains(messageId)) {
            if (Log.isLoggable(Constants.TAG, 3)) {
                Log.d(Constants.TAG, "Received duplicate message: " + messageId);
                return true;
            }
            return true;
        }
        if (recentlyReceivedMessageIds.size() >= 10) {
            recentlyReceivedMessageIds.remove();
        }
        recentlyReceivedMessageIds.add(messageId);
        return false;
    }

    private String getMessageId(Intent intent) {
        String messageId = intent.getStringExtra(Constants.MessagePayloadKeys.MSGID);
        if (messageId == null) {
            return intent.getStringExtra(Constants.MessagePayloadKeys.MSGID_SERVER);
        }
        return messageId;
    }

    private Rpc getRpc(Context context) {
        if (this.rpc == null) {
            this.rpc = new Rpc(context.getApplicationContext());
        }
        return this.rpc;
    }

    static void resetForTesting() {
        recentlyReceivedMessageIds.clear();
    }

    void setRpcForTesting(Rpc rpc) {
        this.rpc = rpc;
    }
}
