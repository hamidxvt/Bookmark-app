package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzwy extends zzadk implements zzaes {
    private static final zzwy zzb;
    private int zze;
    private int zzf;
    private boolean zzj;
    private zzadr zzg = zzadk.zzB();
    private String zzh = "";
    private String zzi = "";
    private zzadr zzk = zzadk.zzB();

    static {
        zzwy zzwyVar = new zzwy();
        zzb = zzwyVar;
        zzadk.zzG(zzwy.class, zzwyVar);
    }

    private zzwy() {
    }

    public static zzwx zza() {
        return (zzwx) zzb.zzx();
    }

    static /* synthetic */ void zzd(zzwy zzwyVar, String str) {
        zzadr zzadrVar = zzwyVar.zzg;
        if (!zzadrVar.zzc()) {
            zzwyVar.zzg = zzadk.zzC(zzadrVar);
        }
        zzwyVar.zzg.add(str);
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
                return zzF(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0002\u0000\u0001င\u0000\u0002\u001a\u0003ဈ\u0001\u0004ဈ\u0002\u0005ဇ\u0003\u0006\u001a", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
            case 3:
                return new zzwy();
            case 4:
                return new zzwx(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
