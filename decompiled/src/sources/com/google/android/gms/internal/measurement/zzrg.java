package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzrg implements zzrf {
    public static final zzkm zza;

    static {
        zzkg zzb = new zzkg(zzkb.zza("com.google.android.gms.measurement")).zza().zzb();
        zza = zzb.zzd("measurement.client.sessions.enable_fix_background_engagement", false);
        zzb.zzd("measurement.client.sessions.enable_pause_engagement_in_background", true);
        zzb.zzc("measurement.id.client.sessions.enable_fix_background_engagement", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzrf
    public final boolean zza() {
        return ((Boolean) zza.zzd()).booleanValue();
    }
}
