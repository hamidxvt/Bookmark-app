package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzrv extends zzadk implements zzaes {
    private static final zzrv zzb;
    private int zze;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzm;
    private String zzf = "";
    private zzadr zzl = zzB();

    static {
        zzrv zzrvVar = new zzrv();
        zzb = zzrvVar;
        zzadk.zzG(zzrv.class, zzrvVar);
    }

    private zzrv() {
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
                return zzF(zzb, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0001\u0000\u0001ဈ\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003\u0005င\u0004\u0006င\u0005\u0007\u001b\bင\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", zzrs.class, "zzm"});
            case 3:
                return new zzrv();
            case 4:
                return new zzru(zzriVar);
            case 5:
                return zzb;
        }
    }
}
