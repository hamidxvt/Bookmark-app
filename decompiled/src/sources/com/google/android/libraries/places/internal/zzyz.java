package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzyz extends zzadk implements zzaes {
    private static final zzyz zzb;
    private int zze;
    private int zzg;
    private boolean zzi;
    private zzadr zzf = zzadk.zzB();
    private String zzh = "";

    static {
        zzyz zzyzVar = new zzyz();
        zzb = zzyzVar;
        zzadk.zzG(zzyz.class, zzyzVar);
    }

    private zzyz() {
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
                return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u001a\u0002ဌ\u0000\u0003ဈ\u0001\u0004ဇ\u0002", new Object[]{"zze", "zzf", "zzg", zzyw.zza, "zzh", "zzi"});
            case 3:
                return new zzyz();
            case 4:
                return new zzyy(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
