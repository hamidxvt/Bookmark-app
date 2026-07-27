package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzan extends zzadk implements zzaes {
    private static final zzan zzb;
    private int zze;
    private boolean zzf;
    private long zzg;
    private long zzh;
    private int zzi;
    private float zzj;
    private float zzk;
    private boolean zzl;
    private float zzm;
    private double zzn;
    private int zzo;

    static {
        zzan zzanVar = new zzan();
        zzb = zzanVar;
        zzadk.zzG(zzan.class, zzanVar);
    }

    private zzan() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzak zzakVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                zzadn zzadnVar = zzam.zza;
                return zzF(zzb, "\u0001\n\u0000\u0001\u0001\n\n\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဌ\u0003\u0005ခ\u0004\u0006ခ\u0005\u0007ဇ\u0006\bခ\u0007\tက\b\nဌ\t", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", zzadnVar, "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", zzadnVar});
            case 3:
                return new zzan();
            case 4:
                return new zzal(zzakVar);
            case 5:
                return zzb;
        }
    }
}
