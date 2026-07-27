package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzuf extends zzadk implements zzaes {
    private static final zzuf zzb;
    private int zze;
    private int zzf;

    static {
        zzuf zzufVar = new zzuf();
        zzb = zzufVar;
        zzadk.zzG(zzuf.class, zzufVar);
    }

    private zzuf() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzsz zzszVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"zze", "zzf"});
            case 3:
                return new zzuf();
            case 4:
                return new zzue(zzszVar);
            case 5:
                return zzb;
        }
    }
}
