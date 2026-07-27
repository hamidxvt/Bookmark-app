package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzzj extends zzadk implements zzaes {
    private static final zzzj zzb;
    private int zze;
    private int zzf;
    private long zzg;
    private int zzh;

    static {
        zzzj zzzjVar = new zzzj();
        zzb = zzzjVar;
        zzadk.zzG(zzzj.class, zzzjVar);
    }

    private zzzj() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzvo zzvoVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\b\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဂ\u0001\bဌ\u0002", new Object[]{"zze", "zzf", zzzi.zza, "zzg", "zzh", zzvp.zza});
            case 3:
                return new zzzj();
            case 4:
                return new zzzh(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
