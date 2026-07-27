package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
final class zzmj implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcu zzb;
    final /* synthetic */ zznl zzc;

    zzmj(zznl zznlVar, zzr zzrVar, com.google.android.gms.internal.measurement.zzcu zzcuVar) {
        this.zza = zzrVar;
        this.zzb = zzcuVar;
        Objects.requireNonNull(zznlVar);
        this.zzc = zznlVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        com.google.android.gms.internal.measurement.zzcu zzcuVar;
        zzpp zzk;
        zznl zznlVar;
        zzic zzicVar;
        String str = null;
        try {
            try {
                zznlVar = this.zzc;
                zzicVar = zznlVar.zzu;
            } catch (Throwable th) {
                th = th;
                zznl zznlVar2 = this.zzc;
                zznlVar2.zzu.zzk().zzal(this.zzb, null);
                throw th;
            }
        } catch (RemoteException e) {
            e = e;
        } catch (Throwable th2) {
            th = th2;
            zznl zznlVar22 = this.zzc;
            zznlVar22.zzu.zzk().zzal(this.zzb, null);
            throw th;
        }
        if (zzicVar.zzd().zzl().zzo(zzjk.ANALYTICS_STORAGE)) {
            zzgb zzZ = zznlVar.zzZ();
            if (zzZ != null) {
                zzr zzrVar = this.zza;
                Preconditions.checkNotNull(zzrVar);
                str = zzZ.zzm(zzrVar);
                if (str != null) {
                    try {
                        zznlVar.zzu.zzj().zzR(str);
                        zzicVar.zzd().zze.zzb(str);
                    } catch (RemoteException e2) {
                        e = e2;
                        this.zzc.zzu.zzaV().zzb().zzb("Failed to get app instance id", e);
                        zznl zznlVar3 = this.zzc;
                        zzcuVar = this.zzb;
                        zzk = zznlVar3.zzu.zzk();
                        zzk.zzal(zzcuVar, str);
                    }
                }
                zznlVar.zzV();
                zznl zznlVar32 = this.zzc;
                zzcuVar = this.zzb;
                zzk = zznlVar32.zzu.zzk();
                zzk.zzal(zzcuVar, str);
            }
            zzicVar.zzaV().zzb().zza("Failed to get app instance id");
        } else {
            zzicVar.zzaV().zzh().zza("Analytics storage consent denied; will not get app instance id");
            zznlVar.zzu.zzj().zzR(null);
            zzicVar.zzd().zze.zzb(null);
        }
        zzk = zzicVar.zzk();
        zzcuVar = this.zzb;
        zzk.zzal(zzcuVar, str);
    }
}
