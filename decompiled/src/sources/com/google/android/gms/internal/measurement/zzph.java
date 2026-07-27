package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzph implements zzpg {
    public static final zzkm zza;

    static {
        zzkg zzb = new zzkg(zzkb.zza("com.google.android.gms.measurement")).zza().zzb();
        zzb.zzd("measurement.client.3p_consent_state_v1", true);
        zza = zzb.zzc("measurement.service.storage_consent_support_version", 203600L);
    }

    @Override // com.google.android.gms.internal.measurement.zzpg
    public final long zza() {
        return ((Long) zza.zzd()).longValue();
    }
}
