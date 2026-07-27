package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzgj extends zzmf implements zznn {
    private static final zzgj zzh;
    private int zzb;
    private String zzd = "";
    private boolean zze;
    private boolean zzf;
    private int zzg;

    static {
        zzgj zzgjVar = new zzgj();
        zzh = zzgjVar;
        zzmf.zzcp(zzgj.class, zzgjVar);
    }

    private zzgj() {
    }

    public final String zza() {
        return this.zzd;
    }

    public final boolean zzb() {
        return (this.zzb & 2) != 0;
    }

    public final boolean zzc() {
        return this.zze;
    }

    public final boolean zzd() {
        return (this.zzb & 4) != 0;
    }

    public final boolean zze() {
        return this.zzf;
    }

    public final boolean zzf() {
        return (this.zzb & 8) != 0;
    }

    public final int zzg() {
        return this.zzg;
    }

    final /* synthetic */ void zzh(String str) {
        str.getClass();
        this.zzb |= 1;
        this.zzd = str;
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
                return zzcq(zzh, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004င\u0003", new Object[]{"zzb", "zzd", "zze", "zzf", "zzg"});
            case 3:
                return new zzgj();
            case 4:
                return new zzgi(bArr);
            case 5:
                return zzh;
        }
    }
}
