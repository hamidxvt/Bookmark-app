package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzxd extends zzadk implements zzaes {
    private static final zzxd zzb;
    private int zze;
    private zzwy zzh;
    private zzor zzi;
    private int zzl;
    private int zzm;
    private int zzo;
    private byte zzp = 2;
    private String zzf = "";
    private String zzg = "";
    private int zzj = 1;
    private String zzk = "";
    private String zzn = "";

    static {
        zzxd zzxdVar = new zzxd();
        zzb = zzxdVar;
        zzadk.zzG(zzxd.class, zzxdVar);
    }

    private zzxd() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzvo zzvoVar = null;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzp);
            case 1:
            default:
                this.zzp = obj == null ? (byte) 0 : (byte) 1;
                return null;
            case 2:
                return zzF(zzb, "\u0001\n\u0000\u0001\u0001\n\n\u0000\u0000\u0001\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဉ\u0002\u0004ᐉ\u0003\u0005ဌ\u0004\u0006ဈ\u0005\u0007ဌ\u0006\bင\u0007\tဈ\b\nဌ\t", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", zzxa.zza, "zzk", "zzl", zzxc.zza, "zzm", "zzn", "zzo", zzxb.zza});
            case 3:
                return new zzxd();
            case 4:
                return new zzwz(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
