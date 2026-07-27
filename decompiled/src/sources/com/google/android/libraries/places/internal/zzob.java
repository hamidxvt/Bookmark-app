package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzob extends zzadk implements zzaes {
    private static final zzob zzb;
    private int zze;
    private int zzf = 1;
    private zzod zzg;
    private zzny zzh;

    static {
        zzob zzobVar = new zzob();
        zzb = zzobVar;
        zzadk.zzG(zzob.class, zzobVar);
    }

    private zzob() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zznw zznwVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zze", "zzf", zzoa.zza, "zzg", "zzh"});
            case 3:
                return new zzob();
            case 4:
                return new zznz(zznwVar);
            case 5:
                return zzb;
        }
    }
}
