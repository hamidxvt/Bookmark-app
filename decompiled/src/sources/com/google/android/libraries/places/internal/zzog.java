package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzog extends zzadk implements zzaes {
    private static final zzog zzb;

    static {
        zzog zzogVar = new zzog();
        zzb = zzogVar;
        zzadk.zzG(zzog.class, zzogVar);
    }

    private zzog() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzoe zzoeVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0000", null);
            case 3:
                return new zzog();
            case 4:
                return new zzof(zzoeVar);
            case 5:
                return zzb;
        }
    }
}
