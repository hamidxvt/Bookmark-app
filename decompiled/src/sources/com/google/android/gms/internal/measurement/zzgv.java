package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzgv extends zzmf implements zznn {
    private static final zzgv zzi;
    private int zzb;
    private int zzg;
    private String zzd = "";
    private String zze = "";
    private String zzf = "";
    private String zzh = "";

    static {
        zzgv zzgvVar = new zzgv();
        zzi = zzgvVar;
        zzmf.zzcp(zzgv.class, zzgvVar);
    }

    private zzgv() {
    }

    public static zzgv zze() {
        return zzi;
    }

    public final String zza() {
        return this.zzd;
    }

    public final String zzb() {
        return this.zze;
    }

    public final int zzc() {
        return this.zzg;
    }

    public final String zzd() {
        return this.zzh;
    }

    @Override // com.google.android.gms.internal.measurement.zzmf
    protected final Object zzl(int i, Object obj, Object obj2) {
        byte[] bArr = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                throw null;
            case 2:
                return zzcq(zzi, "\u0004\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004င\u0003\u0005ဈ\u0004", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg", "zzh"});
            case 3:
                return new zzgv();
            case 4:
                return new zzgu(bArr);
            case 5:
                return zzi;
        }
    }
}
