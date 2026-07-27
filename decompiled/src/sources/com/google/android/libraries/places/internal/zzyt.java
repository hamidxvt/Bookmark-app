package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzyt extends zzadk implements zzaes {
    private static final zzyt zzb;
    private int zze;
    private int zzf;
    private int zzh;
    private int zzi;
    private long zzj;
    private boolean zzl;
    private zzadr zzg = zzadk.zzB();
    private String zzk = "";

    static {
        zzyt zzytVar = new zzyt();
        zzb = zzytVar;
        zzadk.zzG(zzyt.class, zzytVar);
    }

    private zzyt() {
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
                return zzF(zzb, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0001\u0000\u0001ဌ\u0000\u0002\u001a\u0003င\u0001\u0004ဌ\u0002\u0005ဂ\u0003\u0006ဈ\u0004\u0007ဇ\u0005", new Object[]{"zze", "zzf", zzwm.zza, "zzg", "zzh", "zzi", zzyw.zza, "zzj", "zzk", "zzl"});
            case 3:
                return new zzyt();
            case 4:
                return new zzys(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
