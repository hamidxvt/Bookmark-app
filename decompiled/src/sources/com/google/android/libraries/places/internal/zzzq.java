package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzzq extends zzadk implements zzaes {
    private static final zzzq zzb;
    private zzadr zze = zzadk.zzB();

    static {
        zzzq zzzqVar = new zzzq();
        zzb = zzzqVar;
        zzadk.zzG(zzzq.class, zzzqVar);
    }

    private zzzq() {
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
                return zzF(zzb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"zze"});
            case 3:
                return new zzzq();
            case 4:
                return new zzzp(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
