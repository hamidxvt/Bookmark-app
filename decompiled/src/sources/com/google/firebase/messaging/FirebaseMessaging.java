package com.google.firebase.messaging;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.privacysandbox.ads.adservices.adid.AdIdManagerImplCommon$$ExternalSyntheticLambda0;
import com.google.android.datatransport.TransportFactory;
import com.google.android.gms.cloudmessaging.CloudMessage;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Subscriber;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RequestDeduplicator;
import com.google.firebase.messaging.Store;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes16.dex */
public class FirebaseMessaging {
    private static final String EXTRA_DUMMY_P_INTENT = "app";
    static final String GMS_PACKAGE = "com.google.android.gms";

    @Deprecated
    public static final String INSTANCE_ID_SCOPE = "FCM";
    private static final long MIN_DELAY_SEC = 30;
    private static final String SEND_INTENT_ACTION = "com.google.android.gcm.intent.SEND";
    private static final String SUBTYPE_DEFAULT = "";
    static final String TAG = "FirebaseMessaging";
    private static Store store;
    static ScheduledExecutorService syncExecutor;
    private final AutoInit autoInit;
    private final Context context;
    private final Executor fileExecutor;
    private final FirebaseApp firebaseApp;
    private final GmsRpc gmsRpc;
    private final FirebaseInstanceIdInternal iid;
    private final Executor initExecutor;
    private final Application.ActivityLifecycleCallbacks lifecycleCallbacks;
    private final Metadata metadata;
    private final RequestDeduplicator requestDeduplicator;
    private boolean syncScheduledOrRunning;
    private final Task<TopicsSubscriber> topicsSubscriberTask;
    private static final long MAX_DELAY_SEC = TimeUnit.HOURS.toSeconds(8);
    static Provider<TransportFactory> transportFactory = new Provider() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda8
        @Override // com.google.firebase.inject.Provider
        public final Object get() {
            return FirebaseMessaging.lambda$static$0();
        }
    };

    static /* synthetic */ TransportFactory lambda$static$0() {
        return null;
    }

    public static synchronized FirebaseMessaging getInstance() {
        FirebaseMessaging firebaseMessaging;
        synchronized (FirebaseMessaging.class) {
            firebaseMessaging = getInstance(FirebaseApp.getInstance());
        }
        return firebaseMessaging;
    }

    private static synchronized Store getStore(Context context) {
        Store store2;
        synchronized (FirebaseMessaging.class) {
            if (store == null) {
                store = new Store(context);
            }
            store2 = store;
        }
        return store2;
    }

    static synchronized void clearStoreForTest() {
        synchronized (FirebaseMessaging.class) {
            store = null;
        }
    }

    static synchronized FirebaseMessaging getInstance(FirebaseApp firebaseApp) {
        FirebaseMessaging firebaseMessaging;
        synchronized (FirebaseMessaging.class) {
            firebaseMessaging = (FirebaseMessaging) firebaseApp.get(FirebaseMessaging.class);
            Preconditions.checkNotNull(firebaseMessaging, "Firebase Messaging component is not present");
        }
        return firebaseMessaging;
    }

    FirebaseMessaging(FirebaseApp firebaseApp, FirebaseInstanceIdInternal iid, Provider<UserAgentPublisher> userAgentPublisher, Provider<HeartBeatInfo> heartBeatInfo, FirebaseInstallationsApi firebaseInstallationsApi, Provider<TransportFactory> transportFactory2, Subscriber subscriber) {
        this(firebaseApp, iid, userAgentPublisher, heartBeatInfo, firebaseInstallationsApi, transportFactory2, subscriber, new Metadata(firebaseApp.getApplicationContext()));
    }

    FirebaseMessaging(FirebaseApp firebaseApp, FirebaseInstanceIdInternal iid, Provider<UserAgentPublisher> userAgentPublisher, Provider<HeartBeatInfo> heartBeatInfo, FirebaseInstallationsApi firebaseInstallationsApi, Provider<TransportFactory> transportFactory2, Subscriber subscriber, Metadata metadata) {
        this(firebaseApp, iid, transportFactory2, subscriber, metadata, new GmsRpc(firebaseApp, metadata, userAgentPublisher, heartBeatInfo, firebaseInstallationsApi), FcmExecutors.newTaskExecutor(), FcmExecutors.newInitExecutor(), FcmExecutors.newFileIOExecutor());
    }

    FirebaseMessaging(FirebaseApp firebaseApp, FirebaseInstanceIdInternal iid, Provider<TransportFactory> transportFactory2, Subscriber subscriber, Metadata metadata, GmsRpc gmsRpc, Executor taskExecutor, Executor initExecutor, Executor fileExecutor) {
        this.syncScheduledOrRunning = false;
        transportFactory = transportFactory2;
        this.firebaseApp = firebaseApp;
        this.iid = iid;
        this.autoInit = new AutoInit(subscriber);
        this.context = firebaseApp.getApplicationContext();
        this.lifecycleCallbacks = new FcmLifecycleCallbacks();
        this.metadata = metadata;
        this.gmsRpc = gmsRpc;
        this.requestDeduplicator = new RequestDeduplicator(taskExecutor);
        this.initExecutor = initExecutor;
        this.fileExecutor = fileExecutor;
        Context appContext = firebaseApp.getApplicationContext();
        if (appContext instanceof Application) {
            Application app = (Application) appContext;
            app.registerActivityLifecycleCallbacks(this.lifecycleCallbacks);
        } else {
            Log.w("FirebaseMessaging", "Context " + appContext + " was not an application, can't register for lifecycle callbacks. Some notification events may be dropped as a result.");
        }
        if (iid != null) {
            iid.addNewTokenListener(new FirebaseInstanceIdInternal.NewTokenListener() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda3
                @Override // com.google.firebase.iid.internal.FirebaseInstanceIdInternal.NewTokenListener
                public final void onNewToken(String str) {
                    FirebaseMessaging.this.m389lambda$new$1$comgooglefirebasemessagingFirebaseMessaging(str);
                }
            });
        }
        initExecutor.execute(new Runnable() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseMessaging.this.m390lambda$new$2$comgooglefirebasemessagingFirebaseMessaging();
            }
        });
        this.topicsSubscriberTask = TopicsSubscriber.createInstance(this, metadata, gmsRpc, this.context, FcmExecutors.newTopicsSyncExecutor());
        this.topicsSubscriberTask.addOnSuccessListener(initExecutor, new OnSuccessListener() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda5
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                FirebaseMessaging.this.m391lambda$new$3$comgooglefirebasemessagingFirebaseMessaging((TopicsSubscriber) obj);
            }
        });
        initExecutor.execute(new Runnable() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseMessaging.this.m392lambda$new$4$comgooglefirebasemessagingFirebaseMessaging();
            }
        });
    }

    /* renamed from: lambda$new$2$com-google-firebase-messaging-FirebaseMessaging, reason: not valid java name */
    /* synthetic */ void m390lambda$new$2$comgooglefirebasemessagingFirebaseMessaging() {
        if (isAutoInitEnabled()) {
            startSyncIfNecessary();
        }
    }

    /* renamed from: lambda$new$3$com-google-firebase-messaging-FirebaseMessaging, reason: not valid java name */
    /* synthetic */ void m391lambda$new$3$comgooglefirebasemessagingFirebaseMessaging(TopicsSubscriber topicsSubscriber) {
        if (isAutoInitEnabled()) {
            topicsSubscriber.startTopicsSyncIfNecessary();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initializeProxyNotifications, reason: merged with bridge method [inline-methods] */
    public void m392lambda$new$4$comgooglefirebasemessagingFirebaseMessaging() {
        ProxyNotificationInitializer.initialize(this.context);
        ProxyNotificationPreferences.setProxyRetention(this.context, this.gmsRpc, shouldRetainProxyNotifications());
        if (shouldRetainProxyNotifications()) {
            handleProxiedNotificationData();
        }
    }

    private boolean shouldRetainProxyNotifications() {
        ProxyNotificationInitializer.initialize(this.context);
        if (!ProxyNotificationInitializer.isProxyNotificationEnabled(this.context)) {
            return false;
        }
        if (this.firebaseApp.get(AnalyticsConnector.class) != null) {
            return true;
        }
        return MessagingAnalytics.deliveryMetricsExportToBigQueryEnabled() && transportFactory != null;
    }

    private void handleProxiedNotificationData() {
        this.gmsRpc.getProxyNotificationData().addOnSuccessListener(this.initExecutor, new OnSuccessListener() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda12
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                FirebaseMessaging.this.m388x8ede5a30((CloudMessage) obj);
            }
        });
    }

    /* renamed from: lambda$handleProxiedNotificationData$5$com-google-firebase-messaging-FirebaseMessaging, reason: not valid java name */
    /* synthetic */ void m388x8ede5a30(CloudMessage notification) {
        if (notification != null) {
            MessagingAnalytics.logNotificationReceived(notification.getIntent());
            handleProxiedNotificationData();
        }
    }

    public boolean isAutoInitEnabled() {
        return this.autoInit.isEnabled();
    }

    public void setAutoInitEnabled(boolean enable) {
        this.autoInit.setEnabled(enable);
    }

    public boolean deliveryMetricsExportToBigQueryEnabled() {
        return MessagingAnalytics.deliveryMetricsExportToBigQueryEnabled();
    }

    public void setDeliveryMetricsExportToBigQuery(boolean enable) {
        MessagingAnalytics.setDeliveryMetricsExportToBigQuery(enable);
        ProxyNotificationPreferences.setProxyRetention(this.context, this.gmsRpc, shouldRetainProxyNotifications());
    }

    public boolean isNotificationDelegationEnabled() {
        return ProxyNotificationInitializer.isProxyNotificationEnabled(this.context);
    }

    public Task<Void> setNotificationDelegationEnabled(boolean enable) {
        return ProxyNotificationInitializer.setEnableProxyNotification(this.initExecutor, this.context, enable).addOnSuccessListener(new AdIdManagerImplCommon$$ExternalSyntheticLambda0(), new OnSuccessListener() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda10
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                FirebaseMessaging.this.m393x6495830d((Void) obj);
            }
        });
    }

    /* renamed from: lambda$setNotificationDelegationEnabled$6$com-google-firebase-messaging-FirebaseMessaging, reason: not valid java name */
    /* synthetic */ void m393x6495830d(Void listener) {
        ProxyNotificationPreferences.setProxyRetention(this.context, this.gmsRpc, shouldRetainProxyNotifications());
    }

    public Task<String> getToken() {
        if (this.iid != null) {
            return this.iid.getTokenTask();
        }
        final TaskCompletionSource<String> taskCompletionSource = new TaskCompletionSource<>();
        this.initExecutor.execute(new Runnable() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseMessaging.this.m387x6a533e85(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$getToken$7$com-google-firebase-messaging-FirebaseMessaging, reason: not valid java name */
    /* synthetic */ void m387x6a533e85(TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(blockingGetToken());
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    public Task<Void> deleteToken() {
        if (this.iid != null) {
            final TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
            this.initExecutor.execute(new Runnable() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    FirebaseMessaging.this.m385xd5738b77(taskCompletionSource);
                }
            });
            return taskCompletionSource.getTask();
        }
        Store.Token token = getTokenWithoutTriggeringSync();
        if (token == null) {
            return Tasks.forResult(null);
        }
        final TaskCompletionSource<Void> taskCompletionSource2 = new TaskCompletionSource<>();
        ExecutorService executorService = FcmExecutors.newNetworkIOExecutor();
        executorService.execute(new Runnable() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseMessaging.this.m386xd4fd2578(taskCompletionSource2);
            }
        });
        return taskCompletionSource2.getTask();
    }

    /* renamed from: lambda$deleteToken$8$com-google-firebase-messaging-FirebaseMessaging, reason: not valid java name */
    /* synthetic */ void m385xd5738b77(TaskCompletionSource taskCompletionSource) {
        try {
            this.iid.deleteToken(Metadata.getDefaultSenderId(this.firebaseApp), INSTANCE_ID_SCOPE);
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    /* renamed from: lambda$deleteToken$9$com-google-firebase-messaging-FirebaseMessaging, reason: not valid java name */
    /* synthetic */ void m386xd4fd2578(TaskCompletionSource taskCompletionSource) {
        try {
            Tasks.await(this.gmsRpc.deleteToken());
            getStore(this.context).deleteToken(getSubtype(), Metadata.getDefaultSenderId(this.firebaseApp));
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    public Task<Void> subscribeToTopic(final String topic) {
        return this.topicsSubscriberTask.onSuccessTask(new SuccessContinuation() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                Task subscribeToTopic;
                subscribeToTopic = ((TopicsSubscriber) obj).subscribeToTopic(topic);
                return subscribeToTopic;
            }
        });
    }

    public Task<Void> unsubscribeFromTopic(final String topic) {
        return this.topicsSubscriberTask.onSuccessTask(new SuccessContinuation() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda13
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                Task unsubscribeFromTopic;
                unsubscribeFromTopic = ((TopicsSubscriber) obj).unsubscribeFromTopic(topic);
                return unsubscribeFromTopic;
            }
        });
    }

    @Deprecated
    public void send(RemoteMessage message) {
        if (TextUtils.isEmpty(message.getTo())) {
            throw new IllegalArgumentException("Missing 'to'");
        }
        Intent intent = new Intent(SEND_INTENT_ACTION);
        Intent dummyIntent = new Intent();
        dummyIntent.setPackage("com.google.example.invalidpackage");
        intent.putExtra(EXTRA_DUMMY_P_INTENT, PendingIntent.getBroadcast(this.context, 0, dummyIntent, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL));
        intent.setPackage("com.google.android.gms");
        message.populateSendMessageIntent(intent);
        this.context.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }

    Task<TopicsSubscriber> getTopicsSubscriberTask() {
        return this.topicsSubscriberTask;
    }

    public static TransportFactory getTransportFactory() {
        return transportFactory.get();
    }

    static void clearTransportFactoryForTest() {
        transportFactory = new Provider() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda7
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return FirebaseMessaging.lambda$clearTransportFactoryForTest$12();
            }
        };
    }

    static /* synthetic */ TransportFactory lambda$clearTransportFactoryForTest$12() {
        return null;
    }

    boolean isGmsCorePresent() {
        return this.metadata.isGmscorePresent();
    }

    Context getApplicationContext() {
        return this.context;
    }

    synchronized void setSyncScheduledOrRunning(boolean value) {
        this.syncScheduledOrRunning = value;
    }

    synchronized void syncWithDelaySecondsInternal(long delaySeconds) {
        long retryDelaySeconds = Math.min(Math.max(MIN_DELAY_SEC, 2 * delaySeconds), MAX_DELAY_SEC);
        SyncTask syncTask = new SyncTask(this, retryDelaySeconds);
        enqueueTaskWithDelaySeconds(syncTask, delaySeconds);
        this.syncScheduledOrRunning = true;
    }

    void enqueueTaskWithDelaySeconds(Runnable task, long delaySeconds) {
        synchronized (FirebaseMessaging.class) {
            if (syncExecutor == null) {
                syncExecutor = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("TAG"));
            }
            syncExecutor.schedule(task, delaySeconds, TimeUnit.SECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSyncIfNecessary() {
        if (this.iid != null) {
            this.iid.getToken();
            return;
        }
        Store.Token token = getTokenWithoutTriggeringSync();
        if (tokenNeedsRefresh(token)) {
            startSync();
        }
    }

    private synchronized void startSync() {
        if (!this.syncScheduledOrRunning) {
            syncWithDelaySecondsInternal(0L);
        }
    }

    Store.Token getTokenWithoutTriggeringSync() {
        return getStore(this.context).getToken(getSubtype(), Metadata.getDefaultSenderId(this.firebaseApp));
    }

    String blockingGetToken() throws IOException {
        if (this.iid != null) {
            try {
                return (String) Tasks.await(this.iid.getTokenTask());
            } catch (InterruptedException | ExecutionException e) {
                throw new IOException(e);
            }
        }
        final Store.Token cachedToken = getTokenWithoutTriggeringSync();
        if (!tokenNeedsRefresh(cachedToken)) {
            return cachedToken.token;
        }
        final String senderId = Metadata.getDefaultSenderId(this.firebaseApp);
        Task<String> tokenTask = this.requestDeduplicator.getOrStartGetTokenRequest(senderId, new RequestDeduplicator.GetTokenRequest() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda14
            @Override // com.google.firebase.messaging.RequestDeduplicator.GetTokenRequest
            public final Task start() {
                return FirebaseMessaging.this.m384xb7d2b1c4(senderId, cachedToken);
            }
        });
        try {
            return (String) Tasks.await(tokenTask);
        } catch (InterruptedException | ExecutionException e2) {
            throw new IOException(e2);
        }
    }

    /* renamed from: lambda$blockingGetToken$14$com-google-firebase-messaging-FirebaseMessaging, reason: not valid java name */
    /* synthetic */ Task m384xb7d2b1c4(final String senderId, final Store.Token cachedToken) {
        return this.gmsRpc.getToken().onSuccessTask(this.fileExecutor, new SuccessContinuation() { // from class: com.google.firebase.messaging.FirebaseMessaging$$ExternalSyntheticLambda9
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return FirebaseMessaging.this.m383xb84917c3(senderId, cachedToken, (String) obj);
            }
        });
    }

    /* renamed from: lambda$blockingGetToken$13$com-google-firebase-messaging-FirebaseMessaging, reason: not valid java name */
    /* synthetic */ Task m383xb84917c3(String senderId, Store.Token cachedToken, String token) throws Exception {
        getStore(this.context).saveToken(getSubtype(), senderId, token, this.metadata.getAppVersionCode());
        if (cachedToken == null || !token.equals(cachedToken.token)) {
            m389lambda$new$1$comgooglefirebasemessagingFirebaseMessaging(token);
        }
        return Tasks.forResult(token);
    }

    private String getSubtype() {
        if (FirebaseApp.DEFAULT_APP_NAME.equals(this.firebaseApp.getName())) {
            return "";
        }
        return this.firebaseApp.getPersistenceKey();
    }

    boolean tokenNeedsRefresh(Store.Token token) {
        return token == null || token.needsRefresh(this.metadata.getAppVersionCode());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invokeOnTokenRefresh, reason: merged with bridge method [inline-methods] */
    public void m389lambda$new$1$comgooglefirebasemessagingFirebaseMessaging(String token) {
        if (FirebaseApp.DEFAULT_APP_NAME.equals(this.firebaseApp.getName())) {
            if (Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "Invoking onNewToken for app: " + this.firebaseApp.getName());
            }
            Intent messagingIntent = new Intent("com.google.firebase.messaging.NEW_TOKEN");
            messagingIntent.putExtra("token", token);
            new FcmBroadcastProcessor(this.context).process(messagingIntent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class AutoInit {
        private static final String AUTO_INIT_PREF = "auto_init";
        private static final String FCM_PREFERENCES = "com.google.firebase.messaging";
        private static final String MANIFEST_METADATA_AUTO_INIT_ENABLED = "firebase_messaging_auto_init_enabled";
        private Boolean autoInitEnabled;
        private EventHandler<DataCollectionDefaultChange> dataCollectionDefaultChangeEventHandler;
        private boolean initialized;
        private final Subscriber subscriber;

        AutoInit(Subscriber subscriber) {
            this.subscriber = subscriber;
        }

        synchronized void initialize() {
            if (this.initialized) {
                return;
            }
            this.autoInitEnabled = readEnabled();
            if (this.autoInitEnabled == null) {
                this.dataCollectionDefaultChangeEventHandler = new EventHandler() { // from class: com.google.firebase.messaging.FirebaseMessaging$AutoInit$$ExternalSyntheticLambda0
                    @Override // com.google.firebase.events.EventHandler
                    public final void handle(Event event) {
                        FirebaseMessaging.AutoInit.this.m394x1061f0b8(event);
                    }
                };
                this.subscriber.subscribe(DataCollectionDefaultChange.class, this.dataCollectionDefaultChangeEventHandler);
            }
            this.initialized = true;
        }

        /* renamed from: lambda$initialize$0$com-google-firebase-messaging-FirebaseMessaging$AutoInit, reason: not valid java name */
        /* synthetic */ void m394x1061f0b8(Event event) {
            if (isEnabled()) {
                FirebaseMessaging.this.startSyncIfNecessary();
            }
        }

        synchronized boolean isEnabled() {
            boolean isDataCollectionDefaultEnabled;
            initialize();
            if (this.autoInitEnabled == null) {
                isDataCollectionDefaultEnabled = FirebaseMessaging.this.firebaseApp.isDataCollectionDefaultEnabled();
            } else {
                isDataCollectionDefaultEnabled = this.autoInitEnabled.booleanValue();
            }
            return isDataCollectionDefaultEnabled;
        }

        synchronized void setEnabled(boolean enable) {
            initialize();
            if (this.dataCollectionDefaultChangeEventHandler != null) {
                this.subscriber.unsubscribe(DataCollectionDefaultChange.class, this.dataCollectionDefaultChangeEventHandler);
                this.dataCollectionDefaultChangeEventHandler = null;
            }
            SharedPreferences.Editor preferencesEditor = FirebaseMessaging.this.firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.messaging", 0).edit();
            preferencesEditor.putBoolean(AUTO_INIT_PREF, enable);
            preferencesEditor.apply();
            if (enable) {
                FirebaseMessaging.this.startSyncIfNecessary();
            }
            this.autoInitEnabled = Boolean.valueOf(enable);
        }

        private Boolean readEnabled() {
            ApplicationInfo applicationInfo;
            Context applicationContext = FirebaseMessaging.this.firebaseApp.getApplicationContext();
            SharedPreferences preferences = applicationContext.getSharedPreferences("com.google.firebase.messaging", 0);
            if (preferences.contains(AUTO_INIT_PREF)) {
                return Boolean.valueOf(preferences.getBoolean(AUTO_INIT_PREF, false));
            }
            try {
                PackageManager packageManager = applicationContext.getPackageManager();
                if (packageManager != null && (applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128)) != null && applicationInfo.metaData != null && applicationInfo.metaData.containsKey(MANIFEST_METADATA_AUTO_INIT_ENABLED)) {
                    return Boolean.valueOf(applicationInfo.metaData.getBoolean(MANIFEST_METADATA_AUTO_INIT_ENABLED));
                }
                return null;
            } catch (PackageManager.NameNotFoundException e) {
                return null;
            }
        }
    }
}
