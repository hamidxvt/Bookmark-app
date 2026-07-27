package com.google.firebase.installations;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.components.Lazy;
import com.google.firebase.heartbeatinfo.HeartBeatController;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsException;
import com.google.firebase.installations.internal.FidListener;
import com.google.firebase.installations.internal.FidListenerHandle;
import com.google.firebase.installations.local.IidStore;
import com.google.firebase.installations.local.PersistedInstallation;
import com.google.firebase.installations.local.PersistedInstallationEntry;
import com.google.firebase.installations.remote.FirebaseInstallationServiceClient;
import com.google.firebase.installations.remote.InstallationResponse;
import com.google.firebase.installations.remote.TokenResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes16.dex */
public class FirebaseInstallations implements FirebaseInstallationsApi {
    private static final String API_KEY_VALIDATION_MSG = "Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.Please refer to https://firebase.google.com/support/privacy/init-options.";
    private static final String APP_ID_VALIDATION_MSG = "Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.";
    private static final String AUTH_ERROR_MSG = "Installation ID could not be validated with the Firebase servers (maybe it was deleted). Firebase Installations will need to create a new Installation ID and auth token. Please retry your last request.";
    private static final String CHIME_FIREBASE_APP_NAME = "CHIME_ANDROID_SDK";
    private static final int CORE_POOL_SIZE = 0;
    private static final long KEEP_ALIVE_TIME_IN_SECONDS = 30;
    private static final String LOCKFILE_NAME_GENERATE_FID = "generatefid.lock";
    private static final int MAXIMUM_POOL_SIZE = 1;
    private static final String PROJECT_ID_VALIDATION_MSG = "Please set your Project ID. A valid Firebase Project ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.";
    private final ExecutorService backgroundExecutor;
    private String cachedFid;
    private final RandomFidGenerator fidGenerator;
    private Set<FidListener> fidListeners;
    private final FirebaseApp firebaseApp;
    private final Lazy<IidStore> iidStore;
    private final List<StateListener> listeners;
    private final Object lock;
    private final Executor networkExecutor;
    private final PersistedInstallation persistedInstallation;
    private final FirebaseInstallationServiceClient serviceClient;
    private final Utils utils;
    private static final Object lockGenerateFid = new Object();
    private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() { // from class: com.google.firebase.installations.FirebaseInstallations.1
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r) {
            return new Thread(r, String.format("firebase-installations-executor-%d", Integer.valueOf(this.mCount.getAndIncrement())));
        }
    };

    FirebaseInstallations(final FirebaseApp firebaseApp, Provider<HeartBeatController> heartBeatProvider, ExecutorService backgroundExecutor, Executor networkExecutor) {
        this(backgroundExecutor, networkExecutor, firebaseApp, new FirebaseInstallationServiceClient(firebaseApp.getApplicationContext(), heartBeatProvider), new PersistedInstallation(firebaseApp), Utils.getInstance(), new Lazy(new Provider() { // from class: com.google.firebase.installations.FirebaseInstallations$$ExternalSyntheticLambda0
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return FirebaseInstallations.lambda$new$0(FirebaseApp.this);
            }
        }), new RandomFidGenerator());
    }

    static /* synthetic */ IidStore lambda$new$0(FirebaseApp firebaseApp) {
        return new IidStore(firebaseApp);
    }

    FirebaseInstallations(ExecutorService backgroundExecutor, Executor networkExecutor, FirebaseApp firebaseApp, FirebaseInstallationServiceClient serviceClient, PersistedInstallation persistedInstallation, Utils utils, Lazy<IidStore> iidStore, RandomFidGenerator fidGenerator) {
        this.lock = new Object();
        this.fidListeners = new HashSet();
        this.listeners = new ArrayList();
        this.firebaseApp = firebaseApp;
        this.serviceClient = serviceClient;
        this.persistedInstallation = persistedInstallation;
        this.utils = utils;
        this.iidStore = iidStore;
        this.fidGenerator = fidGenerator;
        this.backgroundExecutor = backgroundExecutor;
        this.networkExecutor = networkExecutor;
    }

    private void preConditionChecks() {
        Preconditions.checkNotEmpty(getApplicationId(), APP_ID_VALIDATION_MSG);
        Preconditions.checkNotEmpty(getProjectIdentifier(), PROJECT_ID_VALIDATION_MSG);
        Preconditions.checkNotEmpty(getApiKey(), API_KEY_VALIDATION_MSG);
        Preconditions.checkArgument(Utils.isValidAppIdFormat(getApplicationId()), APP_ID_VALIDATION_MSG);
        Preconditions.checkArgument(Utils.isValidApiKeyFormat(getApiKey()), API_KEY_VALIDATION_MSG);
    }

    String getProjectIdentifier() {
        return this.firebaseApp.getOptions().getProjectId();
    }

    public static FirebaseInstallations getInstance() {
        FirebaseApp defaultFirebaseApp = FirebaseApp.getInstance();
        return getInstance(defaultFirebaseApp);
    }

    public static FirebaseInstallations getInstance(FirebaseApp app) {
        Preconditions.checkArgument(app != null, "Null is not a valid value of FirebaseApp.");
        return (FirebaseInstallations) app.get(FirebaseInstallationsApi.class);
    }

    String getApplicationId() {
        return this.firebaseApp.getOptions().getApplicationId();
    }

    String getApiKey() {
        return this.firebaseApp.getOptions().getApiKey();
    }

    String getName() {
        return this.firebaseApp.getName();
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    public Task<String> getId() {
        preConditionChecks();
        String fid = getCacheFid();
        if (fid != null) {
            return Tasks.forResult(fid);
        }
        Task<String> task = addGetIdListener();
        this.backgroundExecutor.execute(new Runnable() { // from class: com.google.firebase.installations.FirebaseInstallations$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseInstallations.this.m378xc18eb11d();
            }
        });
        return task;
    }

    /* renamed from: lambda$getId$1$com-google-firebase-installations-FirebaseInstallations, reason: not valid java name */
    /* synthetic */ void m378xc18eb11d() {
        m379x7147f7aa(false);
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    public Task<InstallationTokenResult> getToken(final boolean forceRefresh) {
        preConditionChecks();
        Task<InstallationTokenResult> task = addGetAuthTokenListener();
        this.backgroundExecutor.execute(new Runnable() { // from class: com.google.firebase.installations.FirebaseInstallations$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseInstallations.this.m379x7147f7aa(forceRefresh);
            }
        });
        return task;
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    public Task<Void> delete() {
        return Tasks.call(this.backgroundExecutor, new Callable() { // from class: com.google.firebase.installations.FirebaseInstallations$$ExternalSyntheticLambda3
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Void deleteFirebaseInstallationId;
                deleteFirebaseInstallationId = FirebaseInstallations.this.deleteFirebaseInstallationId();
                return deleteFirebaseInstallationId;
            }
        });
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    public synchronized FidListenerHandle registerFidListener(final FidListener listener) {
        this.fidListeners.add(listener);
        return new FidListenerHandle() { // from class: com.google.firebase.installations.FirebaseInstallations.2
            @Override // com.google.firebase.installations.internal.FidListenerHandle
            public void unregister() {
                synchronized (FirebaseInstallations.this) {
                    FirebaseInstallations.this.fidListeners.remove(listener);
                }
            }
        };
    }

    private Task<String> addGetIdListener() {
        TaskCompletionSource<String> taskCompletionSource = new TaskCompletionSource<>();
        StateListener l = new GetIdListener(taskCompletionSource);
        addStateListeners(l);
        return taskCompletionSource.getTask();
    }

    private Task<InstallationTokenResult> addGetAuthTokenListener() {
        TaskCompletionSource<InstallationTokenResult> taskCompletionSource = new TaskCompletionSource<>();
        StateListener l = new GetAuthTokenListener(this.utils, taskCompletionSource);
        addStateListeners(l);
        return taskCompletionSource.getTask();
    }

    private void addStateListeners(StateListener l) {
        synchronized (this.lock) {
            this.listeners.add(l);
        }
    }

    private void triggerOnStateReached(PersistedInstallationEntry persistedInstallationEntry) {
        synchronized (this.lock) {
            Iterator<StateListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                StateListener l = it.next();
                boolean doneListening = l.onStateReached(persistedInstallationEntry);
                if (doneListening) {
                    it.remove();
                }
            }
        }
    }

    private void triggerOnException(Exception exception) {
        synchronized (this.lock) {
            Iterator<StateListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                StateListener l = it.next();
                boolean doneListening = l.onException(exception);
                if (doneListening) {
                    it.remove();
                }
            }
        }
    }

    private synchronized void updateCacheFid(String cachedFid) {
        this.cachedFid = cachedFid;
    }

    private synchronized String getCacheFid() {
        return this.cachedFid;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doRegistrationOrRefresh, reason: merged with bridge method [inline-methods] */
    public final void m379x7147f7aa(final boolean forceRefresh) {
        PersistedInstallationEntry prefs = getPrefsWithGeneratedIdMultiProcessSafe();
        if (forceRefresh) {
            prefs = prefs.withClearedAuthToken();
        }
        triggerOnStateReached(prefs);
        this.networkExecutor.execute(new Runnable() { // from class: com.google.firebase.installations.FirebaseInstallations$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FirebaseInstallations.this.m377x5a306a82(forceRefresh);
            }
        });
    }

    private IidStore getIidStore() {
        return this.iidStore.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004c  */
    /* renamed from: doNetworkCallIfNecessary, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void m377x5a306a82(boolean forceRefresh) {
        PersistedInstallationEntry updatedPrefs;
        PersistedInstallationEntry prefs;
        PersistedInstallationEntry prefs2 = getMultiProcessSafePrefs();
        try {
            if (!prefs2.isErrored() && !prefs2.isUnregistered()) {
                if (!forceRefresh && !this.utils.isAuthTokenExpired(prefs2)) {
                    return;
                }
                updatedPrefs = fetchAuthTokenFromServer(prefs2);
                insertOrUpdatePrefs(updatedPrefs);
                updateFidListener(prefs2, updatedPrefs);
                prefs = updatedPrefs;
                if (prefs.isRegistered()) {
                    updateCacheFid(prefs.getFirebaseInstallationId());
                }
                if (!prefs.isErrored()) {
                    triggerOnException(new FirebaseInstallationsException(FirebaseInstallationsException.Status.BAD_CONFIG));
                    return;
                } else if (prefs.isNotGenerated()) {
                    triggerOnException(new IOException(AUTH_ERROR_MSG));
                    return;
                } else {
                    triggerOnStateReached(prefs);
                    return;
                }
            }
            updatedPrefs = registerFidWithServer(prefs2);
            insertOrUpdatePrefs(updatedPrefs);
            updateFidListener(prefs2, updatedPrefs);
            prefs = updatedPrefs;
            if (prefs.isRegistered()) {
            }
            if (!prefs.isErrored()) {
            }
        } catch (FirebaseInstallationsException e) {
            triggerOnException(e);
        }
    }

    private synchronized void updateFidListener(PersistedInstallationEntry prefs, PersistedInstallationEntry updatedPrefs) {
        if (this.fidListeners.size() != 0 && !TextUtils.equals(prefs.getFirebaseInstallationId(), updatedPrefs.getFirebaseInstallationId())) {
            for (FidListener listener : this.fidListeners) {
                listener.onFidChanged(updatedPrefs.getFirebaseInstallationId());
            }
        }
    }

    private void insertOrUpdatePrefs(PersistedInstallationEntry prefs) {
        synchronized (lockGenerateFid) {
            CrossProcessLock lock = CrossProcessLock.acquire(this.firebaseApp.getApplicationContext(), LOCKFILE_NAME_GENERATE_FID);
            try {
                this.persistedInstallation.insertOrUpdatePersistedInstallationEntry(prefs);
            } finally {
                if (lock != null) {
                    lock.releaseAndClose();
                }
            }
        }
    }

    private PersistedInstallationEntry getPrefsWithGeneratedIdMultiProcessSafe() {
        PersistedInstallationEntry prefs;
        synchronized (lockGenerateFid) {
            CrossProcessLock lock = CrossProcessLock.acquire(this.firebaseApp.getApplicationContext(), LOCKFILE_NAME_GENERATE_FID);
            try {
                prefs = this.persistedInstallation.readPersistedInstallationEntryValue();
                if (prefs.isNotGenerated()) {
                    String fid = readExistingIidOrCreateFid(prefs);
                    prefs = this.persistedInstallation.insertOrUpdatePersistedInstallationEntry(prefs.withUnregisteredFid(fid));
                }
            } finally {
                if (lock != null) {
                    lock.releaseAndClose();
                }
            }
        }
        return prefs;
    }

    private String readExistingIidOrCreateFid(PersistedInstallationEntry prefs) {
        if ((!this.firebaseApp.getName().equals(CHIME_FIREBASE_APP_NAME) && !this.firebaseApp.isDefaultApp()) || !prefs.shouldAttemptMigration()) {
            return this.fidGenerator.createRandomFid();
        }
        String fid = getIidStore().readIid();
        if (TextUtils.isEmpty(fid)) {
            return this.fidGenerator.createRandomFid();
        }
        return fid;
    }

    private PersistedInstallationEntry registerFidWithServer(PersistedInstallationEntry prefs) throws FirebaseInstallationsException {
        String iidToken = null;
        if (prefs.getFirebaseInstallationId() != null && prefs.getFirebaseInstallationId().length() == 11) {
            iidToken = getIidStore().readToken();
        }
        InstallationResponse response = this.serviceClient.createFirebaseInstallation(getApiKey(), prefs.getFirebaseInstallationId(), getProjectIdentifier(), getApplicationId(), iidToken);
        switch (response.getResponseCode()) {
            case OK:
                return prefs.withRegisteredFid(response.getFid(), response.getRefreshToken(), this.utils.currentTimeInSecs(), response.getAuthToken().getToken(), response.getAuthToken().getTokenExpirationTimestamp());
            case BAD_CONFIG:
                return prefs.withFisError("BAD CONFIG");
            default:
                throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
        }
    }

    private PersistedInstallationEntry fetchAuthTokenFromServer(PersistedInstallationEntry prefs) throws FirebaseInstallationsException {
        TokenResult tokenResult = this.serviceClient.generateAuthToken(getApiKey(), prefs.getFirebaseInstallationId(), getProjectIdentifier(), prefs.getRefreshToken());
        switch (tokenResult.getResponseCode()) {
            case OK:
                return prefs.withAuthToken(tokenResult.getToken(), tokenResult.getTokenExpirationTimestamp(), this.utils.currentTimeInSecs());
            case BAD_CONFIG:
                return prefs.withFisError("BAD CONFIG");
            case AUTH_ERROR:
                updateCacheFid(null);
                return prefs.withNoGeneratedFid();
            default:
                throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Void deleteFirebaseInstallationId() throws FirebaseInstallationsException {
        updateCacheFid(null);
        PersistedInstallationEntry entry = getMultiProcessSafePrefs();
        if (entry.isRegistered()) {
            this.serviceClient.deleteFirebaseInstallation(getApiKey(), entry.getFirebaseInstallationId(), getProjectIdentifier(), entry.getRefreshToken());
        }
        insertOrUpdatePrefs(entry.withNoGeneratedFid());
        return null;
    }

    private PersistedInstallationEntry getMultiProcessSafePrefs() {
        PersistedInstallationEntry prefs;
        synchronized (lockGenerateFid) {
            CrossProcessLock lock = CrossProcessLock.acquire(this.firebaseApp.getApplicationContext(), LOCKFILE_NAME_GENERATE_FID);
            try {
                prefs = this.persistedInstallation.readPersistedInstallationEntryValue();
            } finally {
                if (lock != null) {
                    lock.releaseAndClose();
                }
            }
        }
        return prefs;
    }
}
