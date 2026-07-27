package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzzm extends zzadk implements zzaes {
    private static final zzzm zzb;
    private int zze;
    private int zzf;
    private zzwy zzi;
    private zzzv zzj;
    private zzyj zzk;
    private zzxi zzl;
    private zzyh zzm;
    private zzxk zzn;
    private zzyf zzo;
    private zzzx zzp;
    private zzzx zzq;
    private zzyl zzr;
    private zzxu zzs;
    private zzzo zzt;
    private zzzq zzu;
    private zzzb zzv;
    private zzyr zzw;
    private zzzs zzx;
    private byte zzy = 2;
    private String zzg = "";
    private String zzh = "";

    static {
        zzzm zzzmVar = new zzzm();
        zzb = zzzmVar;
        zzadk.zzG(zzzm.class, zzzmVar);
    }

    private zzzm() {
    }

    public static zzzk zza() {
        return (zzzk) zzb.zzx();
    }

    static /* synthetic */ void zzd(zzzm zzzmVar, String str) {
        str.getClass();
        zzzmVar.zze |= 2;
        zzzmVar.zzg = str;
    }

    static /* synthetic */ void zze(zzzm zzzmVar, String str) {
        str.getClass();
        zzzmVar.zze |= 4;
        zzzmVar.zzh = str;
    }

    static /* synthetic */ void zzf(zzzm zzzmVar, zzyh zzyhVar) {
        zzyhVar.getClass();
        zzzmVar.zzm = zzyhVar;
        zzzmVar.zze |= 128;
    }

    static /* synthetic */ void zzg(zzzm zzzmVar, zzxk zzxkVar) {
        zzxkVar.getClass();
        zzzmVar.zzn = zzxkVar;
        zzzmVar.zze |= 256;
    }

    static /* synthetic */ void zzh(zzzm zzzmVar, int i) {
        zzzmVar.zzf = i - 1;
        zzzmVar.zze |= 1;
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzvo zzvoVar = null;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzy);
            case 1:
            default:
                this.zzy = obj == null ? (byte) 0 : (byte) 1;
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0013\u0000\u0001\u0001\u0013\u0013\u0000\u0000\u0004\u0001ဌ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဉ\u0003\u0005ᐉ\u0004\u0006ᐉ\u0005\u0007ᐉ\u0006\bဉ\u0007\tᐉ\b\nဉ\t\u000bဉ\u000b\fဉ\n\rဉ\f\u000eဉ\r\u000fဉ\u000e\u0010ဉ\u000f\u0011ဉ\u0010\u0012ဉ\u0011\u0013ဉ\u0012", new Object[]{"zze", "zzf", zzzl.zza, "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzq", "zzp", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx"});
            case 3:
                return new zzzm();
            case 4:
                return new zzzk(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
