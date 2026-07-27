package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzgk implements zzgj {
    private final zzet zza;
    private final zzex zzb;

    public zzgk(zzex zzexVar, zzet zzetVar, byte[] bArr) {
        this.zzb = zzexVar;
        this.zza = zzetVar;
    }

    @Override // com.google.android.libraries.places.internal.zzgj
    public final void zza(zzgi zzgiVar) {
        zzxm zza = zzxp.zza();
        zza.zzg(zzgiVar.zzz());
        zza.zzd(zzgiVar.zzx());
        zza.zze(zzgiVar.zzy());
        zza.zzj(zzgiVar.zzd());
        zza.zzc(zzgiVar.zzb());
        zza.zzb(zzgiVar.zza());
        zza.zzk(zzgiVar.zze());
        zza.zzh(zzgiVar.zzk().length());
        zza.zzl(zzgiVar.zzg());
        zza.zzf(zzgiVar.zzc());
        zza.zzi(zzgiVar.zzA());
        zza.zza(zzgiVar.zzf());
        if (zzgiVar.zzi() == zzfj.FRAGMENT) {
            zza.zzn(2);
        } else if (zzgiVar.zzi() == zzfj.INTENT) {
            zza.zzn(3);
        } else {
            zza.zzn(1);
        }
        if (zzgiVar.zzj() == AutocompleteActivityMode.FULLSCREEN) {
            zza.zzm(2);
        } else if (zzgiVar.zzj() == AutocompleteActivityMode.OVERLAY) {
            zza.zzm(1);
        }
        zzxp zzxpVar = (zzxp) zza.zzt();
        zzxv zzb = zzey.zzb(this.zza);
        zzb.zzl(10);
        zzb.zzc(zzxpVar);
        this.zzb.zza(zzey.zza((zzya) zzb.zzt()));
    }
}
