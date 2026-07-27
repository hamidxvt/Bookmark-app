package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzyh extends zzadk implements zzaes {
    private static final zzyh zzb;
    private int zze;
    private zzadr zzf = zzadk.zzB();
    private int zzg;
    private int zzh;
    private zzzg zzi;

    static {
        zzyh zzyhVar = new zzyh();
        zzb = zzyhVar;
        zzadk.zzG(zzyh.class, zzyhVar);
    }

    private zzyh() {
    }

    public static zzyg zza() {
        return (zzyg) zzb.zzx();
    }

    static /* synthetic */ void zzd(zzyh zzyhVar, int i) {
        zzyhVar.zze |= 2;
        zzyhVar.zzh = 1;
    }

    static /* synthetic */ void zze(zzyh zzyhVar, zzzg zzzgVar) {
        zzzgVar.getClass();
        zzyhVar.zzi = zzzgVar;
        zzyhVar.zze |= 4;
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
                return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u001a\u0002ဌ\u0000\u0003ဋ\u0001\u0004ဉ\u0002", new Object[]{"zze", "zzf", "zzg", zzwd.zza, "zzh", "zzi"});
            case 3:
                return new zzyh();
            case 4:
                return new zzyg(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
