package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzvv extends zzadk implements zzaes {
    private static final zzvv zzb;
    private int zze;
    private int zzf;

    static {
        zzvv zzvvVar = new zzvv();
        zzb = zzvvVar;
        zzadk.zzG(zzvv.class, zzvvVar);
    }

    private zzvv() {
    }

    public static zzvu zza() {
        return (zzvu) zzb.zzx();
    }

    static /* synthetic */ void zzd(zzvv zzvvVar, int i) {
        zzvvVar.zze |= 1;
        zzvvVar.zzf = i;
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
                return zzF(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001င\u0000", new Object[]{"zze", "zzf"});
            case 3:
                return new zzvv();
            case 4:
                return new zzvu(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
