package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzsm extends zzadk implements zzaes {
    private static final zzsm zzb;
    private int zze;
    private boolean zzf;
    private boolean zzg;

    static {
        zzsm zzsmVar = new zzsm();
        zzb = zzsmVar;
        zzadk.zzG(zzsm.class, zzsmVar);
    }

    private zzsm() {
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
                return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001", new Object[]{"zze", "zzf", "zzg"});
            case 3:
                return new zzsm();
            case 4:
                return new zzsl(zzriVar);
            case 5:
                return zzb;
        }
    }
}
