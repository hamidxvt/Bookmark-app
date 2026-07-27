package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.List;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
final class zzmc implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzr zzc;
    final /* synthetic */ boolean zzd;
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcu zze;
    final /* synthetic */ zznl zzf;

    zzmc(zznl zznlVar, String str, String str2, zzr zzrVar, boolean z, com.google.android.gms.internal.measurement.zzcu zzcuVar) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzrVar;
        this.zzd = z;
        this.zze = zzcuVar;
        Objects.requireNonNull(zznlVar);
        this.zzf = zznlVar;
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x00b2: MOVE (r0 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:44:0x00b1 */
    @Override // java.lang.Runnable
    public final void run() {
        Bundle bundle;
        RemoteException e;
        Bundle bundle2;
        Bundle bundle3 = new Bundle();
        try {
            try {
                zznl zznlVar = this.zzf;
                zzgb zzZ = zznlVar.zzZ();
                if (zzZ == null) {
                    zzic zzicVar = zznlVar.zzu;
                    zzicVar.zzaV().zzb().zzc("Failed to get user properties; not connected to service", this.zza, this.zzb);
                    zzicVar.zzk().zzaq(this.zze, bundle3);
                    return;
                }
                zzr zzrVar = this.zzc;
                Preconditions.checkNotNull(zzrVar);
                List<zzpl> zzp = zzZ.zzp(this.zza, this.zzb, this.zzd, zzrVar);
                int i = zzpp.zza;
                bundle = new Bundle();
                if (zzp != null) {
                    for (zzpl zzplVar : zzp) {
                        String str = zzplVar.zze;
                        if (str != null) {
                            bundle.putString(zzplVar.zzb, str);
                        } else {
                            Long l = zzplVar.zzd;
                            if (l != null) {
                                bundle.putLong(zzplVar.zzb, l.longValue());
                            } else {
                                Double d = zzplVar.zzg;
                                if (d != null) {
                                    bundle.putDouble(zzplVar.zzb, d.doubleValue());
                                }
                            }
                        }
                    }
                }
                try {
                    zznlVar.zzV();
                    zzic zzicVar2 = zznlVar.zzu;
                    zzicVar2.zzk().zzaq(this.zze, bundle);
                } catch (RemoteException e2) {
                    e = e2;
                    this.zzf.zzu.zzaV().zzb().zzc("Failed to get user properties; remote exception", this.zza, e);
                    zznl zznlVar2 = this.zzf;
                    zznlVar2.zzu.zzk().zzaq(this.zze, bundle);
                }
            } catch (Throwable th) {
                th = th;
                bundle3 = bundle2;
                zznl zznlVar3 = this.zzf;
                zznlVar3.zzu.zzk().zzaq(this.zze, bundle3);
                throw th;
            }
        } catch (RemoteException e3) {
            bundle = bundle3;
            e = e3;
        } catch (Throwable th2) {
            th = th2;
            zznl zznlVar32 = this.zzf;
            zznlVar32.zzu.zzk().zzaq(this.zze, bundle3);
            throw th;
        }
    }
}
