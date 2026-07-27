package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzop extends zzadk implements zzaes {
    private static final zzop zzb;
    private int zze;
    private int zzf;
    private int zzg;
    private byte zzh = 2;

    static {
        zzop zzopVar = new zzop();
        zzb = zzopVar;
        zzadk.zzG(zzop.class, zzopVar);
    }

    private zzop() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzon zzonVar = null;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzh);
            case 1:
            default:
                this.zzh = obj == null ? (byte) 0 : (byte) 1;
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔆ\u0000\u0002ᔆ\u0001", new Object[]{"zze", "zzf", "zzg"});
            case 3:
                return new zzop();
            case 4:
                return new zzoo(zzonVar);
            case 5:
                return zzb;
        }
    }
}
