package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzxk extends zzadk implements zzaes {
    private static final zzxk zzb;
    private int zze;
    private zzor zzg;
    private zzwy zzh;
    private byte zzi = 2;
    private String zzf = "";

    static {
        zzxk zzxkVar = new zzxk();
        zzb = zzxkVar;
        zzadk.zzG(zzxk.class, zzxkVar);
    }

    private zzxk() {
    }

    public static zzxj zza() {
        return (zzxj) zzb.zzx();
    }

    static /* synthetic */ void zzd(zzxk zzxkVar, zzwy zzwyVar) {
        zzxkVar.zzh = zzwyVar;
        zzxkVar.zze |= 4;
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzvo zzvoVar = null;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzi);
            case 1:
            default:
                this.zzi = obj == null ? (byte) 0 : (byte) 1;
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0001\u0001ဈ\u0000\u0002ᐉ\u0001\u0003ဉ\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
            case 3:
                return new zzxk();
            case 4:
                return new zzxj(zzvoVar);
            case 5:
                return zzb;
        }
    }
}
