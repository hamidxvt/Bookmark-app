package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zznr extends zzadk implements zzaes {
    private static final zznr zzb;
    private int zze;
    private zzado zzf = zzz();
    private zzado zzg = zzz();
    private int zzh;
    private int zzi;
    private long zzj;
    private int zzk;

    static {
        zznr zznrVar = new zznr();
        zzb = zznrVar;
        zzadk.zzG(zznr.class, zznrVar);
    }

    private zznr() {
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
                return zzF(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0002\u0000\u0001\u0016\u0002\u0016\u0003င\u0000\u0004င\u0001\u0005ဂ\u0002\u0006င\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
            case 3:
                return new zznr();
            case 4:
                return new zznq(zznnVar);
            case 5:
                return zzb;
        }
    }
}
