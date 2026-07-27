package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzgc extends zzmf implements zznn {
    private static final zzgc zzf;
    private int zzb;
    private String zzd = "";
    private String zze = "";

    static {
        zzgc zzgcVar = new zzgc();
        zzf = zzgcVar;
        zzmf.zzcp(zzgc.class, zzgcVar);
    }

    private zzgc() {
    }

    public final String zza() {
        return this.zzd;
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
                return zzcq(zzf, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzb", "zzd", "zze"});
            case 3:
                return new zzgc();
            case 4:
                return new zzgb(bArr);
            case 5:
                return zzf;
        }
    }
}
