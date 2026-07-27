package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzze extends zzadk implements zzaes {
    private static final zzze zzb;
    private int zze;
    private int zzf;
    private int zzg;
    private boolean zzh;

    static {
        zzze zzzeVar = new zzze();
        zzb = zzzeVar;
        zzadk.zzG(zzze.class, zzzeVar);
    }

    private zzze() {
    }

    public static zzzc zza() {
        return (zzzc) zzb.zzx();
    }

    static /* synthetic */ void zzd(zzze zzzeVar, int i) {
        zzzeVar.zzf = 1;
        zzzeVar.zze = 1 | zzzeVar.zze;
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzvo zzvoVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002င\u0001\u0003ဇ\u0002", new Object[]{"zze", "zzf", zzzd.zza, "zzg", "zzh"});
            case 3:
                return new zzze();
            case 4:
                return new zzzc(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
