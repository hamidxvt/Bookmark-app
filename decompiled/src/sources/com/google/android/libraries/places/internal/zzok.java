package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzok extends zzadk implements zzaes {
    private static final zzok zzb;
    private int zze;
    private boolean zzf;
    private long zzg;

    static {
        zzok zzokVar = new zzok();
        zzb = zzokVar;
        zzadk.zzG(zzok.class, zzokVar);
    }

    private zzok() {
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
                return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဃ\u0001", new Object[]{"zze", "zzf", "zzg"});
            case 3:
                return new zzok();
            case 4:
                return new zzoj(zzoeVar);
            case 5:
                return zzb;
        }
    }
}
