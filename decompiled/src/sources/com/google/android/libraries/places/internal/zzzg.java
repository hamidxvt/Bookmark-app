package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzzg extends zzadk implements zzaes {
    private static final zzzg zzb;
    private zzadr zze = zzadk.zzB();

    static {
        zzzg zzzgVar = new zzzg();
        zzb = zzzgVar;
        zzadk.zzG(zzzg.class, zzzgVar);
    }

    private zzzg() {
    }

    public static zzzf zza() {
        return (zzzf) zzb.zzx();
    }

    static /* synthetic */ void zzd(zzzg zzzgVar, Iterable iterable) {
        zzadr zzadrVar = zzzgVar.zze;
        if (!zzadrVar.zzc()) {
            zzzgVar.zze = zzadk.zzC(zzadrVar);
        }
        zzacc.zzt(iterable, zzzgVar.zze);
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
                return zzF(zzb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"zze"});
            case 3:
                return new zzzg();
            case 4:
                return new zzzf(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
