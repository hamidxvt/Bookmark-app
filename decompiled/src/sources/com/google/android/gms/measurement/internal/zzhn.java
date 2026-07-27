package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
final class zzhn implements com.google.android.gms.internal.measurement.zzr {
    final /* synthetic */ zzht zza;

    zzhn(zzht zzhtVar) {
        Objects.requireNonNull(zzhtVar);
        this.zza = zzhtVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzr
    public final void zza(int i, String str, List list, boolean z, boolean z2) {
        zzgs zzj;
        switch (i - 1) {
            case 0:
                zzj = this.zza.zzu.zzaV().zzj();
                break;
            case 1:
                if (!z) {
                    if (!z2) {
                        zzj = this.zza.zzu.zzaV().zzd();
                        break;
                    } else {
                        zzj = this.zza.zzu.zzaV().zzb();
                        break;
                    }
                } else {
                    zzj = this.zza.zzu.zzaV().zzc();
                    break;
                }
            case 2:
            default:
                zzj = this.zza.zzu.zzaV().zzi();
                break;
            case 3:
                zzj = this.zza.zzu.zzaV().zzk();
                break;
            case 4:
                if (!z) {
                    if (!z2) {
                        zzj = this.zza.zzu.zzaV().zzh();
                        break;
                    } else {
                        zzj = this.zza.zzu.zzaV().zze();
                        break;
                    }
                } else {
                    zzj = this.zza.zzu.zzaV().zzf();
                    break;
                }
        }
        switch (list.size()) {
            case 1:
                zzj.zzb(str, list.get(0));
                break;
            case 2:
                zzj.zzc(str, list.get(0), list.get(1));
                break;
            case 3:
                zzj.zzd(str, list.get(0), list.get(1), list.get(2));
                break;
            default:
                zzj.zza(str);
                break;
        }
    }
}
