package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
final class zznx implements Runnable {
    final long zza;
    final long zzb;
    final /* synthetic */ zzny zzc;

    zznx(zzny zznyVar, long j, long j2) {
        Objects.requireNonNull(zznyVar);
        this.zzc = zznyVar;
        this.zza = j;
        this.zzb = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza.zzu.zzaW().zzj(new Runnable() { // from class: com.google.android.gms.measurement.internal.zznw
            @Override // java.lang.Runnable
            public final /* synthetic */ void run() {
                zznx zznxVar = zznx.this;
                zzoc zzocVar = zznxVar.zzc.zza;
                zzocVar.zzg();
                zzic zzicVar = zzocVar.zzu;
                zzicVar.zzaV().zzj().zza("Application going to the background");
                zzicVar.zzd().zzn.zzb(true);
                zzocVar.zzh(true);
                if (!zzicVar.zzc().zzv()) {
                    long j = zznxVar.zzb;
                    zzoa zzoaVar = zzocVar.zzb;
                    zzoaVar.zzd(false, false, j);
                    zzoaVar.zzb(j);
                }
                zzicVar.zzaV().zzi().zzb("Application backgrounded at: timestamp_millis", Long.valueOf(zznxVar.zza));
                zzic zzicVar2 = zzocVar.zzu;
                zzlj zzj = zzicVar2.zzj();
                zzj.zzg();
                zzic zzicVar3 = zzj.zzu;
                zzj.zzb();
                zznl zzt = zzicVar3.zzt();
                zzt.zzg();
                zzt.zzb();
                if (!zzt.zzK() || zzt.zzu.zzk().zzah() >= 242600) {
                    zzicVar3.zzt().zzF();
                }
                if (zzicVar.zzc().zzp(null, zzfy.zzaN)) {
                    long zzl = zzicVar.zzk().zzaa(zzicVar.zzaY().getPackageName(), zzicVar.zzc().zzz()) ? 1000L : zzicVar.zzc().zzl(zzicVar.zzaY().getPackageName(), zzfy.zzD);
                    zzicVar.zzaV().zzk().zzb("[sgtm] Scheduling batch upload with minimum latency in millis", Long.valueOf(zzl));
                    zzicVar2.zzx().zzh(zzl);
                }
            }
        });
    }
}
