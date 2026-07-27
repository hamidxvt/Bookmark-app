package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzmq extends zzadk implements zzaes {
    private static final zzmq zzb;
    private int zze;
    private zzmf zzf;
    private int zzg;
    private int zzh;

    static {
        zzmq zzmqVar = new zzmq();
        zzb = zzmqVar;
        zzadk.zzG(zzmq.class, zzmqVar);
    }

    private zzmq() {
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
                return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဌ\u0001\u0003င\u0002", new Object[]{"zze", "zzf", "zzg", zzmp.zza, "zzh"});
            case 3:
                return new zzmq();
            case 4:
                return new zzmo(zzmdVar);
            case 5:
                return zzb;
        }
    }
}
