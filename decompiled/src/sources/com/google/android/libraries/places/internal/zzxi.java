package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzxi extends zzadk implements zzaes {
    private static final zzxi zzb;
    private int zze;
    private zzop zzg;
    private byte zzl = 2;
    private String zzf = "";
    private String zzh = "";
    private zzadr zzi = zzadk.zzB();
    private String zzj = "";
    private String zzk = "";

    static {
        zzxi zzxiVar = new zzxi();
        zzb = zzxiVar;
        zzadk.zzG(zzxi.class, zzxiVar);
    }

    private zzxi() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzvo zzvoVar = null;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzl);
            case 1:
            default:
                this.zzl = obj == null ? (byte) 0 : (byte) 1;
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0001\u0001ဈ\u0000\u0002ᐉ\u0001\u0003ဈ\u0002\u0004\u001a\u0005ဈ\u0003\u0006ဈ\u0004", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
            case 3:
                return new zzxi();
            case 4:
                return new zzxh(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
