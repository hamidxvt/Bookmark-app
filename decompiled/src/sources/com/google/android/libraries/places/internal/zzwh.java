package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzwh extends zzadk implements zzaes {
    private static final zzwh zzb;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzwh zzwhVar = new zzwh();
        zzb = zzwhVar;
        zzadk.zzG(zzwh.class, zzwhVar);
    }

    private zzwh() {
    }

    public static zzwg zza() {
        return (zzwg) zzb.zzx();
    }

    static /* synthetic */ void zzd(zzwh zzwhVar, int i) {
        zzwhVar.zze |= 1;
        zzwhVar.zzf = 1;
    }

    static /* synthetic */ void zze(zzwh zzwhVar, int i) {
        zzwhVar.zze |= 2;
        zzwhVar.zzg = i;
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
                return zzF(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001", new Object[]{"zze", "zzf", "zzg"});
            case 3:
                return new zzwh();
            case 4:
                return new zzwg(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
