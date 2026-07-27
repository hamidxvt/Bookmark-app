package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzzv extends zzadk implements zzaes {
    private static final zzzv zzb;
    private int zze;
    private zzor zzf;
    private int zzg;
    private int zzh;
    private int zzj;
    private byte zzk = 2;
    private String zzi = "";

    static {
        zzzv zzzvVar = new zzzv();
        zzb = zzzvVar;
        zzadk.zzG(zzzv.class, zzzvVar);
    }

    private zzzv() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzvo zzvoVar = null;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzk);
            case 1:
            default:
                this.zzk = obj == null ? (byte) 0 : (byte) 1;
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0001\u0001ᐉ\u0000\u0002င\u0001\u0003င\u0002\u0004ဈ\u0003\u0005ဌ\u0004", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", zzzu.zza});
            case 3:
                return new zzzv();
            case 4:
                return new zzzt(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
