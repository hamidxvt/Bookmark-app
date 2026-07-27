package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzyl extends zzadk implements zzaes {
    private static final zzyl zzb;

    static {
        zzyl zzylVar = new zzyl();
        zzb = zzylVar;
        zzadk.zzG(zzyl.class, zzylVar);
    }

    private zzyl() {
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
                return zzF(zzb, "\u0001\u0000", null);
            case 3:
                return new zzyl();
            case 4:
                return new zzyk(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
