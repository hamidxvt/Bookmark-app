package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzwl extends zzadk implements zzaes {
    private static final zzwl zzb;
    private int zze;
    private zzado zzf = zzz();
    private zzadr zzg = zzadk.zzB();
    private String zzh = "";
    private boolean zzi;
    private int zzj;

    static {
        zzwl zzwlVar = new zzwl();
        zzb = zzwlVar;
        zzadk.zzG(zzwl.class, zzwlVar);
    }

    private zzwl() {
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
                return zzF(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001\u0016\u0002\u001a\u0003ဈ\u0000\u0004ဇ\u0001\u0005ဋ\u0002", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
            case 3:
                return new zzwl();
            case 4:
                return new zzwk(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
