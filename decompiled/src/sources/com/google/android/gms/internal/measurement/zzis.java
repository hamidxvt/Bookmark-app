package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@23.0.0 */
/* loaded from: classes16.dex */
public final class zzis extends zzmf implements zznn {
    private static final zzis zzg;
    private int zzb;
    private int zzd;
    private int zze;
    private int zzf;

    static {
        zzis zzisVar = new zzis();
        zzg = zzisVar;
        zzmf.zzcp(zzis.class, zzisVar);
    }

    private zzis() {
    }

    public static zzil zzb() {
        return (zzil) zzg.zzck();
    }

    public static zzis zzc() {
        return zzg;
    }

    public final zzin zza() {
        zzin zzb = zzin.zzb(this.zze);
        return zzb == null ? zzin.CLIENT_UPLOAD_ELIGIBILITY_UNKNOWN : zzb;
    }

    final /* synthetic */ void zzd(zzin zzinVar) {
        this.zze = zzinVar.zza();
        this.zzb |= 2;
    }

    public final int zzf() {
        int zza = zzir.zza(this.zzd);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    public final int zzg() {
        int zza = zzip.zza(this.zzf);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    final /* synthetic */ void zzh(int i) {
        this.zzd = i - 1;
        this.zzb |= 1;
    }

    final /* synthetic */ void zzi(int i) {
        this.zzf = i - 1;
        this.zzb |= 4;
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
                return zzcq(zzg, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003᠌\u0002", new Object[]{"zzb", "zzd", zziq.zza, "zze", zzim.zza, "zzf", zzio.zza});
            case 3:
                return new zzis();
            case 4:
                return new zzil(bArr);
            case 5:
                return zzg;
        }
    }
}
