package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzpk implements zzpj {
    public static final zzkm zza;
    public static final zzkm zzb;

    static {
        zzkg zzb2 = new zzkg(zzkb.zza("com.google.android.gms.measurement")).zza().zzb();
        zzb2.zzd("measurement.set_default_event_parameters_with_backfill.client.dev", false);
        zzb2.zzd("measurement.set_default_event_parameters_with_backfill.service", true);
        zzb2.zzc("measurement.id.set_default_event_parameters.fix_service_request_ordering", 0L);
        zza = zzb2.zzd("measurement.set_default_event_parameters.fix_app_update_logging", true);
        zzb = zzb2.zzd("measurement.set_default_event_parameters.fix_service_request_ordering", false);
        zzb2.zzd("measurement.set_default_event_parameters.fix_subsequent_launches", true);
    }

    @Override // com.google.android.gms.internal.measurement.zzpj
    public final boolean zza() {
        return ((Boolean) zza.zzd()).booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpj
    public final boolean zzb() {
        return ((Boolean) zzb.zzd()).booleanValue();
    }
}
