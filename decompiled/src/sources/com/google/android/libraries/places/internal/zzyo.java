package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzyo extends zzadk implements zzaes {
    private static final zzyo zzb;
    private int zze;
    private zzwy zzf;
    private int zzg;
    private int zzh;
    private zzzg zzi;

    static {
        zzyo zzyoVar = new zzyo();
        zzb = zzyoVar;
        zzadk.zzG(zzyo.class, zzyoVar);
    }

    private zzyo() {
    }

    public static zzym zza() {
        return (zzym) zzb.zzx();
    }

    static /* synthetic */ void zzd(zzyo zzyoVar, int i) {
        zzyoVar.zze |= 4;
        zzyoVar.zzh = i;
    }

    static /* synthetic */ void zze(zzyo zzyoVar, zzzg zzzgVar) {
        zzzgVar.getClass();
        zzyoVar.zzi = zzzgVar;
        zzyoVar.zze |= 8;
    }

    static /* synthetic */ void zzf(zzyo zzyoVar, int i) {
        zzyoVar.zzg = i - 1;
        zzyoVar.zze |= 2;
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
                return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဌ\u0001\u0003င\u0002\u0004ဉ\u0003", new Object[]{"zze", "zzf", "zzg", zzyn.zza, "zzh", "zzi"});
            case 3:
                return new zzyo();
            case 4:
                return new zzym(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
