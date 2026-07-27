package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
final class zzmp implements Runnable {
    final /* synthetic */ zzbg zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcu zzc;
    final /* synthetic */ zznl zzd;

    zzmp(zznl zznlVar, zzbg zzbgVar, String str, com.google.android.gms.internal.measurement.zzcu zzcuVar) {
        this.zza = zzbgVar;
        this.zzb = str;
        this.zzc = zzcuVar;
        Objects.requireNonNull(zznlVar);
        this.zzd = zznlVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        com.google.android.gms.internal.measurement.zzcu zzcuVar;
        zzpp zzk;
        zznl zznlVar;
        zzgb zzZ;
        byte[] bArr = null;
        try {
            zznlVar = this.zzd;
            zzZ = zznlVar.zzZ();
        } catch (RemoteException e) {
            e = e;
        } catch (Throwable th) {
            th = th;
            zznl zznlVar2 = this.zzd;
            zznlVar2.zzu.zzk().zzao(this.zzc, bArr);
            throw th;
        }
        if (zzZ == null) {
            zzic zzicVar = zznlVar.zzu;
            zzicVar.zzaV().zzb().zza("Discarding data. Failed to send event to service to bundle");
            zzk = zzicVar.zzk();
            zzcuVar = this.zzc;
            zzk.zzao(zzcuVar, bArr);
        }
        bArr = zzZ.zzk(this.zza, this.zzb);
        try {
            try {
                zznlVar.zzV();
            } catch (RemoteException e2) {
                e = e2;
                this.zzd.zzu.zzaV().zzb().zzb("Failed to send event to the service to bundle", e);
                zznl zznlVar3 = this.zzd;
                zzcuVar = this.zzc;
                zzk = zznlVar3.zzu.zzk();
                zzk.zzao(zzcuVar, bArr);
            }
            zznl zznlVar32 = this.zzd;
            zzcuVar = this.zzc;
            zzk = zznlVar32.zzu.zzk();
            zzk.zzao(zzcuVar, bArr);
        } catch (Throwable th2) {
            th = th2;
            zznl zznlVar22 = this.zzd;
            zznlVar22.zzu.zzk().zzao(this.zzc, bArr);
            throw th;
        }
    }
}
