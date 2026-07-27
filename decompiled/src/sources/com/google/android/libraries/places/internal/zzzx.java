package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzzx extends zzadk implements zzaes {
    private static final zzzx zzb;
    private int zze;
    private int zzf;
    private String zzg = "";
    private String zzh = "";

    static {
        zzzx zzzxVar = new zzzx();
        zzb = zzzxVar;
        zzadk.zzG(zzzx.class, zzzxVar);
    }

    private zzzx() {
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
                return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001\u0003ဈ\u0002", new Object[]{"zze", "zzf", zzvq.zza, "zzg", "zzh"});
            case 3:
                return new zzzx();
            case 4:
                return new zzzw(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
