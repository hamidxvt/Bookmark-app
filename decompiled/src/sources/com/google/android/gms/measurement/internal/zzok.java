package com.google.android.gms.measurement.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import androidx.core.app.NotificationCompat;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzok extends zzos {
    private final AlarmManager zza;
    private zzay zzb;
    private Integer zzc;

    protected zzok(zzpg zzpgVar) {
        super(zzpgVar);
        this.zza = (AlarmManager) this.zzu.zzaY().getSystemService(NotificationCompat.CATEGORY_ALARM);
    }

    private final zzay zzf() {
        if (this.zzb == null) {
            this.zzb = new zzoj(this, this.zzg.zzag());
        }
        return this.zzb;
    }

    private final void zzh() {
        JobScheduler jobScheduler = (JobScheduler) this.zzu.zzaY().getSystemService("jobscheduler");
        if (jobScheduler != null) {
            jobScheduler.cancel(zzi());
        }
    }

    private final int zzi() {
        if (this.zzc == null) {
            String packageName = this.zzu.zzaY().getPackageName();
            String.valueOf(packageName);
            this.zzc = Integer.valueOf("measurement".concat(String.valueOf(packageName)).hashCode());
        }
        return this.zzc.intValue();
    }

    private final PendingIntent zzj() {
        Context zzaY = this.zzu.zzaY();
        return PendingIntent.getBroadcast(zzaY, 0, new Intent().setClassName(zzaY, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), com.google.android.gms.internal.measurement.zzcg.zza);
    }

    @Override // com.google.android.gms.measurement.internal.zzos
    protected final boolean zzbb() {
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzj());
        }
        zzh();
        return false;
    }

    public final void zzc(long j) {
        zzaw();
        zzic zzicVar = this.zzu;
        zzicVar.zzaU();
        Context zzaY = zzicVar.zzaY();
        if (!zzpp.zzau(zzaY)) {
            zzicVar.zzaV().zzj().zza("Receiver not registered/enabled");
        }
        if (!zzpp.zzQ(zzaY, false)) {
            zzicVar.zzaV().zzj().zza("Service not registered/enabled");
        }
        zzd();
        zzicVar.zzaV().zzk().zzb("Scheduling upload, millis", Long.valueOf(j));
        zzicVar.zzaZ().elapsedRealtime();
        zzicVar.zzc();
        if (j < Math.max(0L, ((Long) zzfy.zzL.zzb(null)).longValue()) && !zzf().zzc()) {
            zzf().zzb(j);
        }
        zzicVar.zzaU();
        Context zzaY2 = zzicVar.zzaY();
        ComponentName componentName = new ComponentName(zzaY2, "com.google.android.gms.measurement.AppMeasurementJobService");
        int zzi = zzi();
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString("action", "com.google.android.gms.measurement.UPLOAD");
        com.google.android.gms.internal.measurement.zzch.zza(zzaY2, new JobInfo.Builder(zzi, componentName).setMinimumLatency(j).setOverrideDeadline(j + j).setExtras(persistableBundle).build(), "com.google.android.gms", "UploadAlarm");
    }

    public final void zzd() {
        zzaw();
        this.zzu.zzaV().zzk().zza("Unscheduling upload");
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzj());
        }
        zzf().zzd();
        zzh();
    }
}
