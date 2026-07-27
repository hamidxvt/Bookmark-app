package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzoi extends zzadk implements zzaes {
    private static final zzoi zzb;
    private int zze;
    private int zzf = 0;
    private Object zzg;

    static {
        zzoi zzoiVar = new zzoi();
        zzb = zzoiVar;
        zzadk.zzG(zzoi.class, zzoiVar);
    }

    private zzoi() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzoe zzoeVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0003\u0001\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ြ\u0000\u0002ြ\u0000\u0003ြ\u0000", new Object[]{"zzg", "zzf", "zze", zzom.class, zzog.class, zzok.class});
            case 3:
                return new zzoi();
            case 4:
                return new zzoh(zzoeVar);
            case 5:
                return zzb;
        }
    }
}
