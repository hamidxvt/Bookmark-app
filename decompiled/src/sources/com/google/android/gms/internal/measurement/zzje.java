package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzje extends zzmf implements zznn {
    private static final zzje zzj;
    private int zzb;
    private int zzd;
    private zzmo zze = zzcv();
    private String zzf = "";
    private String zzg = "";
    private boolean zzh;
    private double zzi;

    static {
        zzje zzjeVar = new zzje();
        zzj = zzjeVar;
        zzmf.zzcp(zzje.class, zzjeVar);
    }

    private zzje() {
    }

    public final List zza() {
        return this.zze;
    }

    public final String zzb() {
        return this.zzf;
    }

    public final boolean zzc() {
        return (this.zzb & 4) != 0;
    }

    public final String zzd() {
        return this.zzg;
    }

    public final boolean zze() {
        return (this.zzb & 8) != 0;
    }

    public final boolean zzf() {
        return this.zzh;
    }

    public final boolean zzg() {
        return (this.zzb & 16) != 0;
    }

    public final double zzh() {
        return this.zzi;
    }

    public final int zzj() {
        int zza = zzjd.zza(this.zzd);
        if (zza == 0) {
            return 1;
        }
        return zza;
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
                return zzcq(zzj, "\u0004\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001᠌\u0000\u0002\u001b\u0003ဈ\u0001\u0004ဈ\u0002\u0005ဇ\u0003\u0006က\u0004", new Object[]{"zzb", "zzd", zzjc.zza, "zze", zzje.class, "zzf", "zzg", "zzh", "zzi"});
            case 3:
                return new zzje();
            case 4:
                return new zzjb(bArr);
            case 5:
                return zzj;
        }
    }
}
