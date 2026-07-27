package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzsj extends zzadk implements zzaes {
    private static final zzsj zzb;
    private int zze;
    private int zzf;
    private float zzg;
    private float zzh;

    static {
        zzsj zzsjVar = new zzsj();
        zzb = zzsjVar;
        zzadk.zzG(zzsj.class, zzsjVar);
    }

    private zzsj() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzri zzriVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ခ\u0001\u0003ခ\u0002", new Object[]{"zze", "zzf", zzsf.zza, "zzg", "zzh"});
            case 3:
                return new zzsj();
            case 4:
                return new zzsi(zzriVar);
            case 5:
                return zzb;
        }
    }
}
