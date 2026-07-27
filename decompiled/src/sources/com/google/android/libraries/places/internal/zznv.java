package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zznv extends zzadk implements zzaes {
    private static final zznv zzb;
    private int zze;
    private zznr zzf;
    private zzadr zzg = zzB();
    private int zzh;
    private int zzi;

    static {
        zznv zznvVar = new zznv();
        zzb = zznvVar;
        zzadk.zzG(zznv.class, zznvVar);
    }

    private zznv() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zznn zznnVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001ဉ\u0000\u0002\u001b\u0003ဌ\u0001\u0004ဌ\u0002", new Object[]{"zze", "zzf", "zzg", zznp.class, "zzh", zznu.zza, "zzi", zznt.zza});
            case 3:
                return new zznv();
            case 4:
                return new zzns(zznnVar);
            case 5:
                return zzb;
        }
    }
}
