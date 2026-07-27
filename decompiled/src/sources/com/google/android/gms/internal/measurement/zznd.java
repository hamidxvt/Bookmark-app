package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@23.0.0 */
/* loaded from: classes16.dex */
final class zznd implements zzny {
    private static final zznk zzb = new zznb();
    private final zznk zza;

    public zznd() {
        zznk zznkVar = zzb;
        int i = zznu.zza;
        zznc zzncVar = new zznc(zzma.zza(), zznkVar);
        byte[] bArr = zzmp.zzb;
        this.zza = zzncVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzny
    public final zznx zza(Class cls) {
        zzls zzlsVar;
        int i = zznz.zza;
        if (!zzmf.class.isAssignableFrom(cls)) {
            int i2 = zznu.zza;
        }
        zznj zzc = this.zza.zzc(cls);
        if (zzc.zza()) {
            int i3 = zznu.zza;
            return zznq.zzg(zznz.zzA(), zzlu.zza(), zzc.zzb());
        }
        int i4 = zznu.zza;
        zznr zza = zzns.zza();
        zzmy zza2 = zzmz.zza();
        zzoi zzA = zznz.zzA();
        switch (zzc.zzc() - 1) {
            case 1:
                zzlsVar = null;
                break;
            default:
                zzlsVar = zzlu.zza();
                break;
        }
        return zznp.zzl(cls, zzc, zza, zza2, zzA, zzlsVar, zzni.zza());
    }
}
