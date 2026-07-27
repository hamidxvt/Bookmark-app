package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzabe extends zzadk implements zzaes {
    private static final zzabe zzb;
    private zzadr zze = zzB();

    static {
        zzabe zzabeVar = new zzabe();
        zzb = zzabeVar;
        zzadk.zzG(zzabe.class, zzabeVar);
    }

    private zzabe() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzabc zzabcVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzabp.class});
            case 3:
                return new zzabe();
            case 4:
                return new zzabd(zzabcVar);
            case 5:
                return zzb;
        }
    }
}
