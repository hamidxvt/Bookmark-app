package com.google.android.gms.internal.measurement;

import com.github.mikephil.charting.utils.Utils;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzf {
    final zzaw zza = new zzaw();
    final zzg zzc = new zzg(null, this.zza);
    final zzg zzb = this.zzc.zzc();
    final zzj zzd = new zzj();

    public zzf() {
        this.zzc.zze("require", new zzw(this.zzd));
        this.zzd.zza("internal.platform", zze.zza);
        this.zzc.zze("runtime.counter", new zzah(Double.valueOf(Utils.DOUBLE_EPSILON)));
    }

    public final zzao zza(zzg zzgVar, zzje... zzjeVarArr) {
        zzao zzaoVar = zzao.zzf;
        for (zzje zzjeVar : zzjeVarArr) {
            zzaoVar = zzi.zzb(zzjeVar);
            zzh.zzl(this.zzc);
            if ((zzaoVar instanceof zzap) || (zzaoVar instanceof zzan)) {
                zzaoVar = this.zza.zzb(zzgVar, zzaoVar);
            }
        }
        return zzaoVar;
    }
}
