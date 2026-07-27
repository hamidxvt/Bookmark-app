package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@23.0.0 */
/* loaded from: classes16.dex */
final class zzok extends zzoi {
    zzok() {
    }

    @Override // com.google.android.gms.internal.measurement.zzoi
    final /* bridge */ /* synthetic */ Object zza(Object obj) {
        zzmf zzmfVar = (zzmf) obj;
        zzoj zzojVar = zzmfVar.zzc;
        if (zzojVar != zzoj.zza()) {
            return zzojVar;
        }
        zzoj zzb = zzoj.zzb();
        zzmfVar.zzc = zzb;
        return zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzoi
    final void zzb(Object obj) {
        ((zzmf) obj).zzc.zzd();
    }
}
