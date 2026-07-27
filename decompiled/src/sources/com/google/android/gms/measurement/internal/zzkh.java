package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
final class zzkh implements Runnable {
    final /* synthetic */ long zza;
    final /* synthetic */ zzlj zzb;

    zzkh(zzlj zzljVar, long j) {
        this.zza = j;
        Objects.requireNonNull(zzljVar);
        this.zzb = zzljVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzlj zzljVar = this.zzb;
        zzljVar.zzg();
        zzljVar.zzb();
        zzic zzicVar = zzljVar.zzu;
        zzicVar.zzaV().zzj().zza("Resetting analytics data (FE)");
        zzic zzicVar2 = zzljVar.zzu;
        zzoc zzh = zzicVar2.zzh();
        zzh.zzg();
        zzob zzobVar = zzh.zza;
        zzh.zzb.zzc();
        zzicVar2.zzv().zzi();
        boolean z = !zzljVar.zzu.zzB();
        zzhh zzd = zzicVar.zzd();
        zzd.zzc.zzb(this.zza);
        zzic zzicVar3 = zzd.zzu;
        if (!TextUtils.isEmpty(zzicVar3.zzd().zzq.zza())) {
            zzd.zzq.zzb(null);
        }
        zzd.zzk.zzb(0L);
        zzd.zzl.zzb(0L);
        if (!zzicVar3.zzc().zzt()) {
            zzd.zzn(z);
        }
        zzd.zzr.zzb(null);
        zzd.zzs.zzb(0L);
        zzd.zzt.zzb(null);
        zzicVar2.zzt().zzB();
        zzicVar2.zzh().zza.zza();
        zzljVar.zzc = z;
        zzicVar2.zzt().zzC(new AtomicReference());
    }
}
