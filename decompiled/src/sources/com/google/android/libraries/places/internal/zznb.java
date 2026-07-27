package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zznb extends zzadk implements zzaes {
    private static final zznb zzb;
    private int zze;
    private int zzf;
    private long zzg;
    private long zzh;

    static {
        zznb zznbVar = new zznb();
        zzb = zznbVar;
        zzadk.zzG(zznb.class, zznbVar);
    }

    private zznb() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzmw zzmwVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဃ\u0001\u0003ဃ\u0002", new Object[]{"zze", "zzf", zzna.zza, "zzg", "zzh"});
            case 3:
                return new zznb();
            case 4:
                return new zzmz(zzmwVar);
            case 5:
                return zzb;
        }
    }
}
