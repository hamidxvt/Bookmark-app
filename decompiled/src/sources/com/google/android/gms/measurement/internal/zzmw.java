package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
final class zzmw implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzr zzc;
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcu zzd;
    final /* synthetic */ zznl zze;

    zzmw(zznl zznlVar, String str, String str2, zzr zzrVar, com.google.android.gms.internal.measurement.zzcu zzcuVar) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzrVar;
        this.zzd = zzcuVar;
        Objects.requireNonNull(zznlVar);
        this.zze = zznlVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        com.google.android.gms.internal.measurement.zzcu zzcuVar;
        zzpp zzk;
        zznl zznlVar;
        zzgb zzZ;
        ArrayList arrayList = new ArrayList();
        try {
            try {
                zznlVar = this.zze;
                zzZ = zznlVar.zzZ();
            } catch (Throwable th) {
                th = th;
                zznl zznlVar2 = this.zze;
                zznlVar2.zzu.zzk().zzar(this.zzd, arrayList);
                throw th;
            }
        } catch (RemoteException e) {
            e = e;
        } catch (Throwable th2) {
            th = th2;
            zznl zznlVar22 = this.zze;
            zznlVar22.zzu.zzk().zzar(this.zzd, arrayList);
            throw th;
        }
        if (zzZ == null) {
            zzic zzicVar = zznlVar.zzu;
            zzicVar.zzaV().zzb().zzc("Failed to get conditional properties; not connected to service", this.zza, this.zzb);
            zzk = zzicVar.zzk();
            zzcuVar = this.zzd;
            zzk.zzar(zzcuVar, arrayList);
        }
        zzr zzrVar = this.zzc;
        Preconditions.checkNotNull(zzrVar);
        arrayList = zzpp.zzas(zzZ.zzr(this.zza, this.zzb, zzrVar));
        try {
            zznlVar.zzV();
        } catch (RemoteException e2) {
            e = e2;
            this.zze.zzu.zzaV().zzb().zzd("Failed to get conditional properties; remote exception", this.zza, this.zzb, e);
            zznl zznlVar3 = this.zze;
            zzcuVar = this.zzd;
            zzk = zznlVar3.zzu.zzk();
            zzk.zzar(zzcuVar, arrayList);
        }
        zznl zznlVar32 = this.zze;
        zzcuVar = this.zzd;
        zzk = zznlVar32.zzu.zzk();
        zzk.zzar(zzcuVar, arrayList);
    }
}
