package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@23.0.0 */
/* loaded from: classes16.dex */
public class AppMeasurementDynamiteService extends com.google.android.gms.internal.measurement.zzcq {
    zzic zza = null;
    private final Map zzb = new ArrayMap();

    @EnsuresNonNull({"scion"})
    private final void zzb() {
        if (this.zza == null) {
            throw new IllegalStateException("Attempting to perform action before initialize.");
        }
    }

    private final void zzc(com.google.android.gms.internal.measurement.zzcu zzcuVar, String str) {
        zzb();
        this.zza.zzk().zzal(zzcuVar, str);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void beginAdUnitExposure(String adUnitId, long timestamp) throws RemoteException {
        zzb();
        this.zza.zzw().zza(adUnitId, timestamp);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void clearConditionalUserProperty(String userPropertyName, String clearEventName, Bundle clearEventParams) throws RemoteException {
        zzb();
        this.zza.zzj().zzab(userPropertyName, clearEventName, clearEventParams);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void clearMeasurementEnabled(long j) throws RemoteException {
        zzb();
        this.zza.zzj().zzn(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void endAdUnitExposure(String adUnitId, long timestamp) throws RemoteException {
        zzb();
        this.zza.zzw().zzb(adUnitId, timestamp);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void generateEventId(com.google.android.gms.internal.measurement.zzcu receiver) throws RemoteException {
        zzb();
        long zzd = this.zza.zzk().zzd();
        zzb();
        this.zza.zzk().zzam(receiver, zzd);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void getAppInstanceId(com.google.android.gms.internal.measurement.zzcu receiver) throws RemoteException {
        zzb();
        this.zza.zzaW().zzj(new zzi(this, receiver));
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void getCachedAppInstanceId(com.google.android.gms.internal.measurement.zzcu receiver) throws RemoteException {
        zzb();
        zzc(receiver, this.zza.zzj().zzQ());
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void getConditionalUserProperties(String origin, String propertyNamePrefix, com.google.android.gms.internal.measurement.zzcu receiver) throws RemoteException {
        zzb();
        this.zza.zzaW().zzj(new zzm(this, receiver, origin, propertyNamePrefix));
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void getCurrentScreenClass(com.google.android.gms.internal.measurement.zzcu receiver) throws RemoteException {
        zzb();
        zzc(receiver, this.zza.zzj().zzae());
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void getCurrentScreenName(com.google.android.gms.internal.measurement.zzcu receiver) throws RemoteException {
        zzb();
        zzc(receiver, this.zza.zzj().zzad());
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void getGmpAppId(com.google.android.gms.internal.measurement.zzcu receiver) throws RemoteException {
        zzb();
        zzc(receiver, this.zza.zzj().zzaf());
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void getMaxUserProperties(String origin, com.google.android.gms.internal.measurement.zzcu receiver) throws RemoteException {
        zzb();
        this.zza.zzj().zzY(origin);
        zzb();
        this.zza.zzk().zzan(receiver, 25);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void getSessionId(com.google.android.gms.internal.measurement.zzcu receiver) throws RemoteException {
        zzb();
        zzlj zzj = this.zza.zzj();
        zzj.zzu.zzaW().zzj(new zzkm(zzj, receiver));
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void getTestFlag(com.google.android.gms.internal.measurement.zzcu receiver, int type) throws RemoteException {
        zzb();
        switch (type) {
            case 0:
                this.zza.zzk().zzal(receiver, this.zza.zzj().zzj());
                break;
            case 1:
                this.zza.zzk().zzam(receiver, this.zza.zzj().zzk().longValue());
                break;
            case 2:
                zzpp zzk = this.zza.zzk();
                double doubleValue = this.zza.zzj().zzm().doubleValue();
                Bundle bundle = new Bundle();
                bundle.putDouble("r", doubleValue);
                try {
                    receiver.zzb(bundle);
                    break;
                } catch (RemoteException e) {
                    zzk.zzu.zzaV().zze().zzb("Error returning double value to wrapper", e);
                    return;
                }
            case 3:
                this.zza.zzk().zzan(receiver, this.zza.zzj().zzl().intValue());
                break;
            case 4:
                this.zza.zzk().zzap(receiver, this.zza.zzj().zzi().booleanValue());
                break;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void getUserProperties(String origin, String propertyNamePrefix, boolean getInternal, com.google.android.gms.internal.measurement.zzcu receiver) throws RemoteException {
        zzb();
        this.zza.zzaW().zzj(new zzk(this, receiver, origin, propertyNamePrefix, getInternal));
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void initForTests(Map map) throws RemoteException {
        zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void initialize(IObjectWrapper context, com.google.android.gms.internal.measurement.zzdd params, long timestamp) throws RemoteException {
        zzic zzicVar = this.zza;
        if (zzicVar == null) {
            this.zza = zzic.zzy((Context) Preconditions.checkNotNull((Context) ObjectWrapper.unwrap(context)), params, Long.valueOf(timestamp));
        } else {
            zzicVar.zzaV().zze().zza("Attempting to initialize multiple times");
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void isDataCollectionEnabled(com.google.android.gms.internal.measurement.zzcu receiver) throws RemoteException {
        zzb();
        this.zza.zzaW().zzj(new zzn(this, receiver));
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void logEvent(String origin, String name, Bundle params, boolean isInternal, boolean allowInterceptor, long timestamp) throws RemoteException {
        zzb();
        this.zza.zzj().zzC(origin, name, params, isInternal, allowInterceptor, timestamp);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void logEventAndBundle(String packageName, String eventName, Bundle params, com.google.android.gms.internal.measurement.zzcu receiver, long timestamp) throws RemoteException {
        zzb();
        Preconditions.checkNotEmpty(eventName);
        (params != null ? new Bundle(params) : new Bundle()).putString("_o", "app");
        this.zza.zzaW().zzj(new zzj(this, receiver, new zzbg(eventName, new zzbe(params), "app", timestamp), packageName));
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void logHealthData(int priority, String key, IObjectWrapper context1, IObjectWrapper context2, IObjectWrapper context3) throws RemoteException {
        zzb();
        this.zza.zzaV().zzm(priority, true, false, key, context1 == null ? null : ObjectWrapper.unwrap(context1), context2 == null ? null : ObjectWrapper.unwrap(context2), context3 == null ? null : ObjectWrapper.unwrap(context3));
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void onActivityCreated(IObjectWrapper activity, Bundle savedInstanceState, long timestamp) throws RemoteException {
        zzb();
        onActivityCreatedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdf.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(activity))), savedInstanceState, timestamp);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void onActivityCreatedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdf scionActivityInfo, Bundle savedInstanceState, long j) {
        zzb();
        zzky zzkyVar = this.zza.zzj().zza;
        if (zzkyVar != null) {
            this.zza.zzj().zzh();
            zzkyVar.zza(scionActivityInfo, savedInstanceState);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void onActivityDestroyed(IObjectWrapper activity, long timestamp) throws RemoteException {
        zzb();
        onActivityDestroyedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdf.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(activity))), timestamp);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void onActivityDestroyedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdf scionActivityInfo, long j) throws RemoteException {
        zzb();
        zzky zzkyVar = this.zza.zzj().zza;
        if (zzkyVar != null) {
            this.zza.zzj().zzh();
            zzkyVar.zzb(scionActivityInfo);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void onActivityPaused(IObjectWrapper activity, long timestamp) throws RemoteException {
        zzb();
        onActivityPausedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdf.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(activity))), timestamp);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void onActivityPausedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdf scionActivityInfo, long j) throws RemoteException {
        zzb();
        zzky zzkyVar = this.zza.zzj().zza;
        if (zzkyVar != null) {
            this.zza.zzj().zzh();
            zzkyVar.zzc(scionActivityInfo);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void onActivityResumed(IObjectWrapper activity, long timestamp) throws RemoteException {
        zzb();
        onActivityResumedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdf.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(activity))), timestamp);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void onActivityResumedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdf scionActivityInfo, long j) throws RemoteException {
        zzb();
        zzky zzkyVar = this.zza.zzj().zza;
        if (zzkyVar != null) {
            this.zza.zzj().zzh();
            zzkyVar.zzd(scionActivityInfo);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void onActivitySaveInstanceState(IObjectWrapper activity, com.google.android.gms.internal.measurement.zzcu receiver, long timestamp) throws RemoteException {
        zzb();
        onActivitySaveInstanceStateByScionActivityInfo(com.google.android.gms.internal.measurement.zzdf.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(activity))), receiver, timestamp);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void onActivitySaveInstanceStateByScionActivityInfo(com.google.android.gms.internal.measurement.zzdf scionActivityInfo, com.google.android.gms.internal.measurement.zzcu receiver, long j) throws RemoteException {
        zzb();
        zzky zzkyVar = this.zza.zzj().zza;
        Bundle bundle = new Bundle();
        if (zzkyVar != null) {
            this.zza.zzj().zzh();
            zzkyVar.zze(scionActivityInfo, bundle);
        }
        try {
            receiver.zzb(bundle);
        } catch (RemoteException e) {
            this.zza.zzaV().zze().zzb("Error returning bundle value to wrapper", e);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void onActivityStarted(IObjectWrapper activity, long timestamp) throws RemoteException {
        zzb();
        onActivityStartedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdf.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(activity))), timestamp);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void onActivityStartedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdf zzdfVar, long j) throws RemoteException {
        zzb();
        if (this.zza.zzj().zza != null) {
            this.zza.zzj().zzh();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void onActivityStopped(IObjectWrapper activity, long timestamp) throws RemoteException {
        zzb();
        onActivityStoppedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdf.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(activity))), timestamp);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void onActivityStoppedByScionActivityInfo(com.google.android.gms.internal.measurement.zzdf zzdfVar, long j) throws RemoteException {
        zzb();
        if (this.zza.zzj().zza != null) {
            this.zza.zzj().zzh();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void performAction(Bundle bundle, com.google.android.gms.internal.measurement.zzcu receiver, long j) throws RemoteException {
        zzb();
        receiver.zzb(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void registerOnMeasurementEventListener(com.google.android.gms.internal.measurement.zzda listenerProxy) throws RemoteException {
        zzjq zzjqVar;
        zzb();
        Map map = this.zzb;
        synchronized (map) {
            zzjqVar = (zzjq) map.get(Integer.valueOf(listenerProxy.zzf()));
            if (zzjqVar == null) {
                zzjqVar = new zzq(this, listenerProxy);
                map.put(Integer.valueOf(listenerProxy.zzf()), zzjqVar);
            }
        }
        this.zza.zzj().zzW(zzjqVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void resetAnalyticsData(long timestamp) throws RemoteException {
        zzb();
        this.zza.zzj().zzT(timestamp);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void retrieveAndUploadBatches(final com.google.android.gms.internal.measurement.zzcx callback) {
        zzb();
        this.zza.zzj().zzt(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzo
            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                try {
                    callback.zze();
                } catch (RemoteException e) {
                    ((zzic) Preconditions.checkNotNull(AppMeasurementDynamiteService.this.zza)).zzaV().zze().zzb("Failed to call IDynamiteUploadBatchesCallback", e);
                }
            }
        });
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void setConditionalUserProperty(Bundle conditionalUserProperty, long timestamp) throws RemoteException {
        zzb();
        if (conditionalUserProperty == null) {
            this.zza.zzaV().zzb().zza("Conditional user property must not be null");
        } else {
            this.zza.zzj().zzaa(conditionalUserProperty, timestamp);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void setConsent(Bundle bundle, long j) throws RemoteException {
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void setConsentThirdParty(Bundle consentMap, long timestamp) throws RemoteException {
        zzb();
        this.zza.zzj().zzp(consentMap, -20, timestamp);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void setCurrentScreen(IObjectWrapper activity, String screenName, String screenClassOverride, long timestamp) throws RemoteException {
        zzb();
        setCurrentScreenByScionActivityInfo(com.google.android.gms.internal.measurement.zzdf.zza((Activity) Preconditions.checkNotNull((Activity) ObjectWrapper.unwrap(activity))), screenName, screenClassOverride, timestamp);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void setCurrentScreenByScionActivityInfo(com.google.android.gms.internal.measurement.zzdf scionActivityInfo, String screenName, String screenClassOverride, long j) throws RemoteException {
        zzb();
        this.zza.zzs().zzk(scionActivityInfo, screenName, screenClassOverride);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void setDataCollectionEnabled(boolean enabled) throws RemoteException {
        zzb();
        zzlj zzj = this.zza.zzj();
        zzj.zzb();
        zzic zzicVar = zzj.zzu;
        zzj.zzu.zzaW().zzj(new zzjy(zzj, enabled));
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void setDefaultEventParameters(Bundle parameters) {
        zzb();
        final zzlj zzj = this.zza.zzj();
        final Bundle bundle = parameters == null ? new Bundle() : new Bundle(parameters);
        zzj.zzu.zzaW().zzj(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzlf
            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                zzlj.this.zzah(bundle);
            }
        });
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void setEventInterceptor(com.google.android.gms.internal.measurement.zzda interceptor) throws RemoteException {
        zzb();
        zzp zzpVar = new zzp(this, interceptor);
        if (this.zza.zzaW().zze()) {
            this.zza.zzj().zzV(zzpVar);
        } else {
            this.zza.zzaW().zzj(new zzl(this, zzpVar));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void setInstanceIdProvider(com.google.android.gms.internal.measurement.zzdc zzdcVar) throws RemoteException {
        zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void setMeasurementEnabled(boolean enabled, long j) throws RemoteException {
        zzb();
        this.zza.zzj().zzn(Boolean.valueOf(enabled));
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void setMinimumSessionDuration(long j) throws RemoteException {
        zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void setSessionTimeoutDuration(long milliseconds) throws RemoteException {
        zzb();
        zzlj zzj = this.zza.zzj();
        zzic zzicVar = zzj.zzu;
        zzj.zzu.zzaW().zzj(new zzka(zzj, milliseconds));
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void setSgtmDebugInfo(Intent sgtmDebugIntent) throws RemoteException {
        zzb();
        zzlj zzj = this.zza.zzj();
        Uri data = sgtmDebugIntent.getData();
        if (data == null) {
            zzj.zzu.zzaV().zzi().zza("Activity intent has no data. Preview Mode was not enabled.");
            return;
        }
        String queryParameter = data.getQueryParameter("sgtm_debug_enable");
        if (queryParameter == null || !queryParameter.equals("1")) {
            zzic zzicVar = zzj.zzu;
            zzicVar.zzaV().zzi().zza("[sgtm] Preview Mode was not enabled.");
            zzicVar.zzc().zzy(null);
        } else {
            String queryParameter2 = data.getQueryParameter("sgtm_preview_key");
            if (TextUtils.isEmpty(queryParameter2)) {
                return;
            }
            zzic zzicVar2 = zzj.zzu;
            zzicVar2.zzaV().zzi().zzb("[sgtm] Preview Mode was enabled. Using the sgtmPreviewKey: ", queryParameter2);
            zzicVar2.zzc().zzy(queryParameter2);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void setUserId(final String id, long timestamp) throws RemoteException {
        zzb();
        final zzlj zzj = this.zza.zzj();
        if (id != null && TextUtils.isEmpty(id)) {
            zzj.zzu.zzaV().zze().zza("User ID must be non-empty or null");
        } else {
            zzj.zzu.zzaW().zzj(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzlg
                @Override // java.lang.Runnable
                public final /* synthetic */ void run() {
                    zzic zzicVar = zzlj.this.zzu;
                    if (zzicVar.zzv().zzq(id)) {
                        zzicVar.zzv().zzi();
                    }
                }
            });
            zzj.zzL(null, "_id", id, true, timestamp);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void setUserProperty(String origin, String name, IObjectWrapper value, boolean isInternal, long timestamp) throws RemoteException {
        zzb();
        this.zza.zzj().zzL(origin, name, ObjectWrapper.unwrap(value), isInternal, timestamp);
    }

    @Override // com.google.android.gms.internal.measurement.zzcr
    public void unregisterOnMeasurementEventListener(com.google.android.gms.internal.measurement.zzda listenerProxy) throws RemoteException {
        zzjq zzjqVar;
        zzb();
        Map map = this.zzb;
        synchronized (map) {
            zzjqVar = (zzjq) map.remove(Integer.valueOf(listenerProxy.zzf()));
        }
        if (zzjqVar == null) {
            zzjqVar = new zzq(this, listenerProxy);
        }
        this.zza.zzj().zzX(zzjqVar);
    }
}
