package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzor extends zzadk implements zzaes {
    private static final zzor zzb;
    private int zze;
    private zzop zzf;
    private zzop zzg;
    private byte zzh = 2;

    static {
        zzor zzorVar = new zzor();
        zzb = zzorVar;
        zzadk.zzG(zzor.class, zzorVar);
    }

    private zzor() {
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
                return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᐉ\u0000\u0002ᐉ\u0001", new Object[]{"zze", "zzf", "zzg"});
            case 3:
                return new zzor();
            case 4:
                return new zzoq(zzonVar);
            case 5:
                return zzb;
        }
    }
}
