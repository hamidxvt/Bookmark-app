package com.google.firebase.heartbeatinfo;

import android.content.Context;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import com.google.android.gms.measurement.internal.zzpk$$ExternalSyntheticBackport0;
import com.google.firebase.datastorage.JavaDataStorage;
import com.google.firebase.datastorage.JavaDataStorageKt;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* loaded from: classes16.dex */
class HeartBeatInfoStorage {
    private static final String HEARTBEAT_PREFERENCES_NAME = "FirebaseHeartBeat";
    private static final int HEART_BEAT_COUNT_LIMIT = 30;
    private static final String PREFERENCES_NAME = "FirebaseAppHeartBeat";
    private final JavaDataStorage firebaseDataStore;
    private static HeartBeatInfoStorage instance = null;
    private static final Preferences.Key<Long> GLOBAL = PreferencesKeys.longKey("fire-global");
    private static final Preferences.Key<Long> HEART_BEAT_COUNT_TAG = PreferencesKeys.longKey("fire-count");
    private static final Preferences.Key<String> LAST_STORED_DATE = PreferencesKeys.stringKey("last-used-date");

    public HeartBeatInfoStorage(Context applicationContext, String persistenceKey) {
        this.firebaseDataStore = new JavaDataStorage(applicationContext, HEARTBEAT_PREFERENCES_NAME + persistenceKey);
    }

    HeartBeatInfoStorage(JavaDataStorage javaDataStorage) {
        this.firebaseDataStore = javaDataStorage;
    }

    int getHeartBeatCount() {
        return ((Long) this.firebaseDataStore.getSync(HEART_BEAT_COUNT_TAG, 0L)).intValue();
    }

    synchronized void deleteAllHeartBeats() {
        this.firebaseDataStore.editSync(new Function1() { // from class: com.google.firebase.heartbeatinfo.HeartBeatInfoStorage$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HeartBeatInfoStorage.this.m374xe97e9b5a((MutablePreferences) obj);
            }
        });
    }

    /* renamed from: lambda$deleteAllHeartBeats$0$com-google-firebase-heartbeatinfo-HeartBeatInfoStorage, reason: not valid java name */
    /* synthetic */ Unit m374xe97e9b5a(MutablePreferences pref) {
        Set m;
        long counter = 0;
        for (Map.Entry<Preferences.Key<?>, Object> entry : pref.asMap().entrySet()) {
            if (entry.getValue() instanceof Set) {
                Preferences.Key<?> key = entry.getKey();
                Set<String> dates = (Set) entry.getValue();
                String today = getFormattedDate(System.currentTimeMillis());
                if (dates.contains(today)) {
                    m = zzpk$$ExternalSyntheticBackport0.m(new Object[]{today});
                    pref.set(key, m);
                    counter++;
                } else {
                    pref.remove(key);
                }
            }
        }
        if (counter == 0) {
            pref.remove(HEART_BEAT_COUNT_TAG);
            return null;
        }
        pref.set(HEART_BEAT_COUNT_TAG, Long.valueOf(counter));
        return null;
    }

    synchronized List<HeartBeatResult> getAllHeartBeats() {
        ArrayList<HeartBeatResult> heartBeatResults;
        heartBeatResults = new ArrayList<>();
        String today = getFormattedDate(System.currentTimeMillis());
        for (Map.Entry<Preferences.Key<?>, Object> entry : this.firebaseDataStore.getAllSync().entrySet()) {
            if (entry.getValue() instanceof Set) {
                Set<String> dates = new HashSet<>((Set) entry.getValue());
                dates.remove(today);
                if (!dates.isEmpty()) {
                    heartBeatResults.add(HeartBeatResult.create(entry.getKey().getName(), new ArrayList(dates)));
                }
            }
        }
        updateGlobalHeartBeat(System.currentTimeMillis());
        return heartBeatResults;
    }

    private synchronized Preferences.Key<Set<String>> getStoredUserAgentString(MutablePreferences preferences, String dateString) {
        for (Map.Entry<Preferences.Key<?>, Object> entry : preferences.asMap().entrySet()) {
            if (entry.getValue() instanceof Set) {
                Set<String> dateSet = (Set) entry.getValue();
                for (String date : dateSet) {
                    if (dateString.equals(date)) {
                        return PreferencesKeys.stringSetKey(entry.getKey().getName());
                    }
                }
            }
        }
        return null;
    }

    private synchronized void updateStoredUserAgent(MutablePreferences preferences, Preferences.Key<Set<String>> userAgent, String dateString) {
        removeStoredDate(preferences, dateString);
        HashSet hashSet = new HashSet((Collection) JavaDataStorageKt.getOrDefault(preferences, userAgent, new HashSet()));
        hashSet.add(dateString);
        preferences.set(userAgent, hashSet);
    }

    private synchronized void removeStoredDate(MutablePreferences preferences, String dateString) {
        Preferences.Key<Set<String>> userAgent = getStoredUserAgentString(preferences, dateString);
        if (userAgent == null) {
            return;
        }
        HashSet hashSet = new HashSet((Collection) JavaDataStorageKt.getOrDefault(preferences, userAgent, new HashSet()));
        hashSet.remove(dateString);
        if (hashSet.isEmpty()) {
            preferences.remove(userAgent);
        } else {
            preferences.set(userAgent, hashSet);
        }
    }

    synchronized void postHeartBeatCleanUp() {
        final String dateString = getFormattedDate(System.currentTimeMillis());
        this.firebaseDataStore.editSync(new Function1() { // from class: com.google.firebase.heartbeatinfo.HeartBeatInfoStorage$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HeartBeatInfoStorage.this.m375x44e9f764(dateString, (MutablePreferences) obj);
            }
        });
    }

    /* renamed from: lambda$postHeartBeatCleanUp$1$com-google-firebase-heartbeatinfo-HeartBeatInfoStorage, reason: not valid java name */
    /* synthetic */ Unit m375x44e9f764(String dateString, MutablePreferences pref) {
        pref.set(LAST_STORED_DATE, dateString);
        removeStoredDate(pref, dateString);
        return null;
    }

    private synchronized String getFormattedDate(long millis) {
        LocalDateTime ldt;
        Instant instant = new Date(millis).toInstant();
        ldt = instant.atOffset(ZoneOffset.UTC).toLocalDateTime();
        return ldt.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    synchronized void storeHeartBeat(long millis, final String userAgentString) {
        final String dateString = getFormattedDate(millis);
        final Preferences.Key<Set<String>> userAgent = PreferencesKeys.stringSetKey(userAgentString);
        this.firebaseDataStore.editSync(new Function1() { // from class: com.google.firebase.heartbeatinfo.HeartBeatInfoStorage$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HeartBeatInfoStorage.this.m376x76ccc570(dateString, userAgentString, userAgent, (MutablePreferences) obj);
            }
        });
    }

    /* renamed from: lambda$storeHeartBeat$2$com-google-firebase-heartbeatinfo-HeartBeatInfoStorage, reason: not valid java name */
    /* synthetic */ Unit m376x76ccc570(String dateString, String userAgentString, Preferences.Key userAgent, MutablePreferences pref) {
        String lastDateString = (String) JavaDataStorageKt.getOrDefault(pref, LAST_STORED_DATE, "");
        if (lastDateString.equals(dateString)) {
            Preferences.Key<Set<String>> storedUserAgent = getStoredUserAgentString(pref, dateString);
            if (storedUserAgent == null || storedUserAgent.getName().equals(userAgentString)) {
                return null;
            }
            updateStoredUserAgent(pref, userAgent, dateString);
            return null;
        }
        long heartBeatCount = ((Long) JavaDataStorageKt.getOrDefault(pref, HEART_BEAT_COUNT_TAG, 0L)).longValue();
        if (heartBeatCount + 1 == 30) {
            heartBeatCount = cleanUpStoredHeartBeats(pref);
        }
        HashSet hashSet = new HashSet((Collection) JavaDataStorageKt.getOrDefault(pref, userAgent, new HashSet()));
        hashSet.add(dateString);
        pref.set(userAgent, hashSet);
        pref.set(HEART_BEAT_COUNT_TAG, Long.valueOf(heartBeatCount + 1));
        pref.set(LAST_STORED_DATE, dateString);
        return null;
    }

    private synchronized long cleanUpStoredHeartBeats(MutablePreferences preferences) {
        long heartBeatCount;
        heartBeatCount = ((Long) JavaDataStorageKt.getOrDefault(preferences, HEART_BEAT_COUNT_TAG, 0L)).longValue();
        String lowestDate = null;
        String userAgentString = "";
        Set<String> userAgentDateSet = new HashSet<>();
        for (Map.Entry<Preferences.Key<?>, Object> entry : preferences.asMap().entrySet()) {
            if (entry.getValue() instanceof Set) {
                Set<String> dateSet = (Set) entry.getValue();
                for (String date : dateSet) {
                    if (lowestDate == null || lowestDate.compareTo(date) > 0) {
                        userAgentDateSet = dateSet;
                        lowestDate = date;
                        userAgentString = entry.getKey().getName();
                    }
                }
            }
        }
        HashSet hashSet = new HashSet(userAgentDateSet);
        hashSet.remove(lowestDate);
        preferences.set(PreferencesKeys.stringSetKey(userAgentString), hashSet);
        preferences.set(HEART_BEAT_COUNT_TAG, Long.valueOf(heartBeatCount - 1));
        return heartBeatCount - 1;
    }

    synchronized long getLastGlobalHeartBeat() {
        return ((Long) this.firebaseDataStore.getSync(GLOBAL, -1L)).longValue();
    }

    synchronized void updateGlobalHeartBeat(final long millis) {
        this.firebaseDataStore.editSync(new Function1() { // from class: com.google.firebase.heartbeatinfo.HeartBeatInfoStorage$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HeartBeatInfoStorage.lambda$updateGlobalHeartBeat$3(millis, (MutablePreferences) obj);
            }
        });
    }

    static /* synthetic */ Unit lambda$updateGlobalHeartBeat$3(long millis, MutablePreferences pref) {
        pref.set(GLOBAL, Long.valueOf(millis));
        return null;
    }

    synchronized boolean isSameDateUtc(long base, long target) {
        return getFormattedDate(base).equals(getFormattedDate(target));
    }

    synchronized boolean shouldSendSdkHeartBeat(Preferences.Key<Long> heartBeatTag, long millis) {
        if (isSameDateUtc(((Long) this.firebaseDataStore.getSync(heartBeatTag, -1L)).longValue(), millis)) {
            return false;
        }
        this.firebaseDataStore.putSync(heartBeatTag, Long.valueOf(millis));
        return true;
    }

    synchronized boolean shouldSendGlobalHeartBeat(long millis) {
        return shouldSendSdkHeartBeat(GLOBAL, millis);
    }
}
