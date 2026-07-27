package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
final class zzpe {
    private final zzpg zza;
    private int zzb = 1;
    private long zzc = zzd();

    public zzpe(zzpg zzpgVar) {
        this.zza = zzpgVar;
    }

    private final long zzd() {
        zzpg zzpgVar = this.zza;
        Preconditions.checkNotNull(zzpgVar);
        long longValue = ((Long) zzfy.zzu.zzb(null)).longValue();
        long longValue2 = ((Long) zzfy.zzv.zzb(null)).longValue();
        for (int i = 1; i < this.zzb; i++) {
            longValue += longValue;
            if (longValue >= longValue2) {
                break;
            }
        }
        return zzpgVar.zzaZ().currentTimeMillis() + Math.min(longValue, longValue2);
    }

    public final void zza() {
        this.zzb++;
        this.zzc = zzd();
    }

    public final boolean zzb() {
        return this.zza.zzaZ().currentTimeMillis() >= this.zzc;
    }

    final /* synthetic */ long zzc() {
        return this.zzc;
    }
}
