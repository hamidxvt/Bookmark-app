package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdd;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzic;
import com.google.android.gms.measurement.internal.zzjh;
import com.google.android.gms.measurement.internal.zzjp;
import com.google.android.gms.measurement.internal.zzjq;
import com.google.android.gms.measurement.internal.zzlk;
import com.google.android.gms.measurement.internal.zzlt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
@Deprecated
/* loaded from: classes16.dex */
public class AppMeasurement {
    public static final String CRASH_ORIGIN = "crash";
    public static final String FCM_ORIGIN = "fcm";
    public static final String FIAM_ORIGIN = "fiam";
    private static volatile AppMeasurement zza;
    private final zzc zzb;

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
    public interface EventInterceptor extends zzjp {
        @Override // com.google.android.gms.measurement.internal.zzjp
        void interceptEvent(String str, String str2, Bundle bundle, long j);
    }

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
    public interface OnEventListener extends zzjq {
        @Override // com.google.android.gms.measurement.internal.zzjq
        void onEvent(String str, String str2, Bundle bundle, long j);
    }

    public AppMeasurement(zzic zzicVar) {
        this.zzb = new zza(zzicVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0036 A[Catch: all -> 0x0058, TRY_ENTER, TryCatch #2 {, blocks: (B:6:0x0007, B:10:0x000c, B:12:0x0012, B:14:0x0036, B:15:0x003e, B:21:0x0056), top: B:5:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x003e A[Catch: all -> 0x0058, TryCatch #2 {, blocks: (B:6:0x0007, B:10:0x000c, B:12:0x0012, B:14:0x0036, B:15:0x003e, B:21:0x0056), top: B:5:0x0007 }] */
    @Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static AppMeasurement getInstance(Context context) {
        zzlk zzlkVar;
        if (zza == null) {
            synchronized (AppMeasurement.class) {
                if (zza == null) {
                    try {
                        try {
                            zzlkVar = (zzlk) Class.forName("com.google.firebase.analytics.FirebaseAnalytics").getDeclaredMethod("getScionFrontendApiImplementation", Context.class, Bundle.class).invoke(null, context, null);
                        } catch (Exception e) {
                            zzlkVar = null;
                            if (zzlkVar == null) {
                            }
                            return zza;
                        }
                    } catch (ClassNotFoundException e2) {
                    }
                    if (zzlkVar == null) {
                        zza = new AppMeasurement(zzlkVar);
                    } else {
                        zza = new AppMeasurement(zzic.zzy(context, new zzdd(0L, 0L, true, null, null), null));
                    }
                }
            }
        }
        return zza;
    }

    public void beginAdUnitExposure(String adUnitId) {
        this.zzb.zzm(adUnitId);
    }

    public void clearConditionalUserProperty(String userPropertyName, String clearEventName, Bundle clearEventParams) {
        this.zzb.zzp(userPropertyName, clearEventName, clearEventParams);
    }

    public void endAdUnitExposure(String adUnitId) {
        this.zzb.zzn(adUnitId);
    }

    public long generateEventId() {
        return this.zzb.zzl();
    }

    public String getAppInstanceId() {
        return this.zzb.zzj();
    }

    public Boolean getBoolean() {
        return this.zzb.zzs();
    }

    public List<ConditionalUserProperty> getConditionalUserProperties(String origin, String propertyNamePrefix) {
        List zzq = this.zzb.zzq(origin, propertyNamePrefix);
        ArrayList arrayList = new ArrayList(zzq == null ? 0 : zzq.size());
        Iterator it = zzq.iterator();
        while (it.hasNext()) {
            arrayList.add(new ConditionalUserProperty((Bundle) it.next()));
        }
        return arrayList;
    }

    public String getCurrentScreenClass() {
        return this.zzb.zzi();
    }

    public String getCurrentScreenName() {
        return this.zzb.zzh();
    }

    public Double getDouble() {
        return this.zzb.zzw();
    }

    public String getGmpAppId() {
        return this.zzb.zzk();
    }

    public Integer getInteger() {
        return this.zzb.zzt();
    }

    public Long getLong() {
        return this.zzb.zzv();
    }

    public int getMaxUserProperties(String origin) {
        return this.zzb.zzr(origin);
    }

    public String getString() {
        return this.zzb.zzu();
    }

    protected Map<String, Object> getUserProperties(String origin, String propertyNamePrefix, boolean includeInternal) {
        return this.zzb.zzd(origin, propertyNamePrefix, includeInternal);
    }

    public void logEventInternal(String origin, String name, Bundle params) {
        this.zzb.zza(origin, name, params);
    }

    public void logEventInternalNoInterceptor(String origin, String name, Bundle params, long timestampInMillis) {
        this.zzb.zzb(origin, name, params, timestampInMillis);
    }

    public void registerOnMeasurementEventListener(OnEventListener listener) {
        this.zzb.zzf(listener);
    }

    public void setConditionalUserProperty(ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        Bundle bundle = new Bundle();
        String str = conditionalUserProperty.mAppId;
        if (str != null) {
            bundle.putString("app_id", str);
        }
        String str2 = conditionalUserProperty.mOrigin;
        if (str2 != null) {
            bundle.putString("origin", str2);
        }
        String str3 = conditionalUserProperty.mName;
        if (str3 != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, str3);
        }
        Object obj = conditionalUserProperty.mValue;
        if (obj != null) {
            zzjh.zza(bundle, obj);
        }
        String str4 = conditionalUserProperty.mTriggerEventName;
        if (str4 != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, str4);
        }
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, conditionalUserProperty.mTriggerTimeout);
        String str5 = conditionalUserProperty.mTimedOutEventName;
        if (str5 != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, str5);
        }
        Bundle bundle2 = conditionalUserProperty.mTimedOutEventParams;
        if (bundle2 != null) {
            bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, bundle2);
        }
        String str6 = conditionalUserProperty.mTriggeredEventName;
        if (str6 != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, str6);
        }
        Bundle bundle3 = conditionalUserProperty.mTriggeredEventParams;
        if (bundle3 != null) {
            bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, bundle3);
        }
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, conditionalUserProperty.mTimeToLive);
        String str7 = conditionalUserProperty.mExpiredEventName;
        if (str7 != null) {
            bundle.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str7);
        }
        Bundle bundle4 = conditionalUserProperty.mExpiredEventParams;
        if (bundle4 != null) {
            bundle.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle4);
        }
        zzc zzcVar = this.zzb;
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, conditionalUserProperty.mCreationTimestamp);
        bundle.putBoolean(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, conditionalUserProperty.mActive);
        bundle.putLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, conditionalUserProperty.mTriggeredTimestamp);
        zzcVar.zzo(bundle);
    }

    public void setEventInterceptor(EventInterceptor interceptor) {
        this.zzb.zze(interceptor);
    }

    public void unregisterOnMeasurementEventListener(OnEventListener listener) {
        this.zzb.zzg(listener);
    }

    public AppMeasurement(zzlk zzlkVar) {
        this.zzb = new zzb(zzlkVar);
    }

    public Map<String, Object> getUserProperties(boolean includeInternal) {
        return this.zzb.zzc(includeInternal);
    }

    /* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
    public static class ConditionalUserProperty {
        public boolean mActive;
        public String mAppId;
        public long mCreationTimestamp;
        public String mExpiredEventName;
        public Bundle mExpiredEventParams;
        public String mName;
        public String mOrigin;
        public long mTimeToLive;
        public String mTimedOutEventName;
        public Bundle mTimedOutEventParams;
        public String mTriggerEventName;
        public long mTriggerTimeout;
        public String mTriggeredEventName;
        public Bundle mTriggeredEventParams;
        public long mTriggeredTimestamp;
        public Object mValue;

        public ConditionalUserProperty() {
        }

        ConditionalUserProperty(Bundle bundle) {
            Preconditions.checkNotNull(bundle);
            this.mAppId = (String) zzjh.zzb(bundle, "app_id", String.class, null);
            this.mOrigin = (String) zzjh.zzb(bundle, "origin", String.class, null);
            this.mName = (String) zzjh.zzb(bundle, AppMeasurementSdk.ConditionalUserProperty.NAME, String.class, null);
            this.mValue = zzjh.zzb(bundle, "value", Object.class, null);
            this.mTriggerEventName = (String) zzjh.zzb(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
            this.mTriggerTimeout = ((Long) zzjh.zzb(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L)).longValue();
            this.mTimedOutEventName = (String) zzjh.zzb(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
            this.mTimedOutEventParams = (Bundle) zzjh.zzb(bundle, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
            this.mTriggeredEventName = (String) zzjh.zzb(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
            this.mTriggeredEventParams = (Bundle) zzjh.zzb(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
            this.mTimeToLive = ((Long) zzjh.zzb(bundle, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L)).longValue();
            this.mExpiredEventName = (String) zzjh.zzb(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
            this.mExpiredEventParams = (Bundle) zzjh.zzb(bundle, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
            this.mActive = ((Boolean) zzjh.zzb(bundle, AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.class, false)).booleanValue();
            this.mCreationTimestamp = ((Long) zzjh.zzb(bundle, AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.class, 0L)).longValue();
            this.mTriggeredTimestamp = ((Long) zzjh.zzb(bundle, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.class, 0L)).longValue();
        }

        public ConditionalUserProperty(ConditionalUserProperty other) {
            Preconditions.checkNotNull(other);
            this.mAppId = other.mAppId;
            this.mOrigin = other.mOrigin;
            this.mCreationTimestamp = other.mCreationTimestamp;
            this.mName = other.mName;
            Object obj = other.mValue;
            if (obj != null) {
                this.mValue = zzlt.zzb(obj);
                if (this.mValue == null) {
                    this.mValue = other.mValue;
                }
            }
            this.mActive = other.mActive;
            this.mTriggerEventName = other.mTriggerEventName;
            this.mTriggerTimeout = other.mTriggerTimeout;
            this.mTimedOutEventName = other.mTimedOutEventName;
            Bundle bundle = other.mTimedOutEventParams;
            if (bundle != null) {
                this.mTimedOutEventParams = new Bundle(bundle);
            }
            this.mTriggeredEventName = other.mTriggeredEventName;
            Bundle bundle2 = other.mTriggeredEventParams;
            if (bundle2 != null) {
                this.mTriggeredEventParams = new Bundle(bundle2);
            }
            this.mTriggeredTimestamp = other.mTriggeredTimestamp;
            this.mTimeToLive = other.mTimeToLive;
            this.mExpiredEventName = other.mExpiredEventName;
            Bundle bundle3 = other.mExpiredEventParams;
            if (bundle3 != null) {
                this.mExpiredEventParams = new Bundle(bundle3);
            }
        }
    }
}
