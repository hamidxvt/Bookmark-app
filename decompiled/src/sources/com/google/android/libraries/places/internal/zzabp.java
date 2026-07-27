package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzabp extends zzadk implements zzaes {
    private static final zzabp zzb;
    private int zze;
    private int zzf = 1;
    private int zzg = 1;
    private int zzh;

    static {
        zzabp zzabpVar = new zzabp();
        zzb = zzabpVar;
        zzadk.zzG(zzabp.class, zzabpVar);
    }

    private zzabp() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzabl zzablVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003င\u0002", new Object[]{"zze", "zzf", zzabo.zza, "zzg", zzabn.zza, "zzh"});
            case 3:
                return new zzabp();
            case 4:
                return new zzabm(zzablVar);
            case 5:
                return zzb;
        }
    }
}
