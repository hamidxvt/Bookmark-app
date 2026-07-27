package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
final class zzkm implements Runnable {
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcu zza;
    final /* synthetic */ zzlj zzb;

    zzkm(zzlj zzljVar, com.google.android.gms.internal.measurement.zzcu zzcuVar) {
        this.zza = zzcuVar;
        Objects.requireNonNull(zzljVar);
        this.zzb = zzljVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Long valueOf;
        zzlj zzljVar = this.zzb;
        zzic zzicVar = zzljVar.zzu.zzh().zzu;
        if (zzicVar.zzd().zzl().zzo(zzjk.ANALYTICS_STORAGE)) {
            valueOf = !zzicVar.zzd().zzp(zzicVar.zzaZ().currentTimeMillis()) ? zzicVar.zzd().zzl.zza() == 0 ? null : Long.valueOf(zzicVar.zzd().zzl.zza()) : null;
        } else {
            zzicVar.zzaV().zzh().zza("Analytics storage consent denied; will not get session id");
            valueOf = null;
        }
        if (valueOf != null) {
            zzljVar.zzu.zzk().zzam(this.zza, valueOf.longValue());
            return;
        }
        try {
            this.zza.zzb(null);
        } catch (RemoteException e) {
            this.zzb.zzu.zzaV().zzb().zzb("getSessionId failed with exception", e);
        }
    }
}
