package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzqo implements zzqn {
    public static final zzkm zza;
    public static final zzkm zzb;
    public static final zzkm zzc;
    public static final zzkm zzd;
    public static final zzkm zze;
    public static final zzkm zzf;

    static {
        zzkg zzb2 = new zzkg(zzkb.zza("com.google.android.gms.measurement")).zza().zzb();
        zza = zzb2.zzd("measurement.test.boolean_flag", false);
        zzb = zzb2.zzc("measurement.test.cached_long_flag", -1L);
        zzc = zzb2.zze("measurement.test.double_flag", -3.0d);
        zzd = zzb2.zzc("measurement.test.int_flag", -2L);
        zze = zzb2.zzc("measurement.test.long_flag", -1L);
        zzf = zzb2.zzf("measurement.test.string_flag", "---");
    }

    @Override // com.google.android.gms.internal.measurement.zzqn
    public final boolean zza() {
        return ((Boolean) zza.zzd()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzqn
    public final long zzb() {
        return ((Long) zzb.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzqn
    public final double zzc() {
        return ((Double) zzc.zzd()).doubleValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzqn
    public final long zzd() {
        return ((Long) zzd.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzqn
    public final long zze() {
        return ((Long) zze.zzd()).longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzqn
    public final String zzf() {
        return (String) zzf.zzd();
    }
}
