package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzsv extends zzadk implements zzaes {
    private static final zzsv zzb;
    private int zze;
    private String zzf = "";
    private int zzg;
    private long zzh;

    static {
        zzsv zzsvVar = new zzsv();
        zzb = zzsvVar;
        zzadk.zzG(zzsv.class, zzsvVar);
    }

    private zzsv() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzss zzssVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဌ\u0001\u0003ဂ\u0002", new Object[]{"zze", "zzf", "zzg", zzsu.zza, "zzh"});
            case 3:
                return new zzsv();
            case 4:
                return new zzst(zzssVar);
            case 5:
                return zzb;
        }
    }
}
