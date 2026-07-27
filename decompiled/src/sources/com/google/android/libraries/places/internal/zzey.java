package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzey {
    public static zzlg zza(zzya zzyaVar) {
        zzle zza = zzlg.zza();
        zza.zzb(1);
        zza.zza(zzyaVar);
        return (zzlg) zza.zzt();
    }

    public static zzxv zzb(zzet zzetVar) {
        int i;
        switch (zzetVar.zzc() - 1) {
            case 1:
                i = 4;
                break;
            default:
                i = 2;
                break;
        }
        zzxv zza = zzya.zza();
        zzli zza2 = zzln.zza();
        zza2.zza(zzetVar.zzb());
        zza2.zzb(zzetVar.zza());
        zza.zzb((zzln) zza2.zzt());
        zza.zzf(true);
        zza.zzk(i);
        zza.zzi("2.6.0");
        return zza;
    }
}
