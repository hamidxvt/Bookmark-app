package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzyj extends zzadk implements zzaes {
    private static final zzyj zzb;
    private int zze;
    private zzop zzf;
    private byte zzg = 2;

    static {
        zzyj zzyjVar = new zzyj();
        zzb = zzyjVar;
        zzadk.zzG(zzyj.class, zzyjVar);
    }

    private zzyj() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzvo zzvoVar = null;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzg);
            case 1:
            default:
                this.zzg = obj == null ? (byte) 0 : (byte) 1;
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0001\u0001ᐉ\u0000", new Object[]{"zze", "zzf"});
            case 3:
                return new zzyj();
            case 4:
                return new zzyi(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
