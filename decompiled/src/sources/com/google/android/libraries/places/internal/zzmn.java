package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzmn extends zzadk implements zzaes {
    private static final zzmn zzb;
    private int zze;
    private int zzf;
    private String zzg = "";

    static {
        zzmn zzmnVar = new zzmn();
        zzb = zzmnVar;
        zzadk.zzG(zzmn.class, zzmnVar);
    }

    private zzmn() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzmd zzmdVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", zzmm.zza, "zzg"});
            case 3:
                return new zzmn();
            case 4:
                return new zzml(zzmdVar);
            case 5:
                return zzb;
        }
    }
}
