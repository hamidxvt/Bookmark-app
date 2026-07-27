package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzww extends zzadk implements zzaes {
    private static final zzww zzb;
    private int zze;
    private String zzf = "";
    private String zzg = "";
    private int zzh;

    static {
        zzww zzwwVar = new zzww();
        zzb = zzwwVar;
        zzadk.zzG(zzww.class, zzwwVar);
    }

    private zzww() {
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
                return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဌ\u0002", new Object[]{"zze", "zzf", "zzg", "zzh", zzwv.zza});
            case 3:
                return new zzww();
            case 4:
                return new zzwu(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
