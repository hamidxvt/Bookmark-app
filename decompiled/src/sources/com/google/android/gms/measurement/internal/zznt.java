package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zznt {
    private final Context zza;

    public zznt(Context context) {
        Preconditions.checkNotNull(context);
        this.zza = context;
    }

    public static final void zzi(Intent intent) {
        if (intent == null) {
            Log.e("FA", "onRebind called with null intent");
            return;
        }
        String action = intent.getAction();
        String.valueOf(action);
        Log.v("FA", "onRebind called. action: ".concat(String.valueOf(action)));
    }

    public static final boolean zzj(Intent intent) {
        if (intent == null) {
            Log.e("FA", "onUnbind called with null intent");
            return true;
        }
        String action = intent.getAction();
        String.valueOf(action);
        Log.v("FA", "onUnbind called for intent. action: ".concat(String.valueOf(action)));
        return true;
    }

    private final void zzk(zzpg zzpgVar, Runnable runnable) {
        zzpgVar.zzaW().zzj(new zzno(this, zzpgVar, runnable));
    }

    public final void zza() {
        String simpleName = this.zza.getClass().getSimpleName();
        String.valueOf(simpleName);
        Log.v("FA", String.valueOf(simpleName).concat(" is starting up."));
    }

    public final void zzb() {
        String simpleName = this.zza.getClass().getSimpleName();
        String.valueOf(simpleName);
        Log.v("FA", String.valueOf(simpleName).concat(" is shutting down."));
    }

    public final int zzc(final Intent intent, int i, final int i2) {
        if (intent == null) {
            Log.w("FA", "AppMeasurementService started with null intent");
            return 2;
        }
        Context context = this.zza;
        zzic zzy = zzic.zzy(context, null, null);
        final zzgu zzaV = zzy.zzaV();
        String action = intent.getAction();
        zzy.zzaU();
        zzaV.zzk().zzc("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zzk(zzpg.zza(context), new Runnable() { // from class: com.google.android.gms.measurement.internal.zzns
                @Override // java.lang.Runnable
                public final /* synthetic */ void run() {
                    zznt.this.zzf(i2, zzaV, intent);
                }
            });
        }
        return 2;
    }

    public final IBinder zzd(Intent intent) {
        if (intent == null) {
            Log.e("FA", "onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzjd(zzpg.zza(this.zza), null);
        }
        String.valueOf(action);
        Log.w("FA", "onBind received unknown action: ".concat(String.valueOf(action)));
        return null;
    }

    public final boolean zze(final JobParameters jobParameters) {
        String string = jobParameters.getExtras().getString("action");
        String.valueOf(string);
        Log.v("FA", "onStartJob received action: ".concat(String.valueOf(string)));
        if (Objects.equals(string, "com.google.android.gms.measurement.UPLOAD")) {
            String str = (String) Preconditions.checkNotNull(string);
            zzpg zza = zzpg.zza(this.zza);
            final zzgu zzaV = zza.zzaV();
            zza.zzaU();
            zzaV.zzk().zzb("Local AppMeasurementJobService called. action", str);
            zzk(zza, new Runnable() { // from class: com.google.android.gms.measurement.internal.zznq
                @Override // java.lang.Runnable
                public final /* synthetic */ void run() {
                    zznt.this.zzg(zzaV, jobParameters);
                }
            });
        }
        if (!Objects.equals(string, "com.google.android.gms.measurement.SCION_UPLOAD")) {
            return true;
        }
        com.google.android.gms.internal.measurement.zzfb.zza(this.zza, null).zzw(new Runnable() { // from class: com.google.android.gms.measurement.internal.zznr
            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                zznt.this.zzh(jobParameters);
            }
        });
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    final /* synthetic */ void zzf(int i, zzgu zzguVar, Intent intent) {
        Context context = this.zza;
        zznp zznpVar = (zznp) context;
        if (zznpVar.zza(i)) {
            zzguVar.zzk().zzb("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i));
            zzic.zzy(context, null, null).zzaV().zzk().zza("Completed wakeful intent.");
            zznpVar.zzc(intent);
        }
    }

    final /* synthetic */ void zzg(zzgu zzguVar, JobParameters jobParameters) {
        zzguVar.zzk().zza("AppMeasurementJobService processed last upload request.");
        ((zznp) this.zza).zzb(jobParameters, false);
    }

    final /* synthetic */ void zzh(JobParameters jobParameters) {
        Log.v("FA", "[sgtm] AppMeasurementJobService processed last Scion upload request.");
        ((zznp) this.zza).zzb(jobParameters, false);
    }
}
