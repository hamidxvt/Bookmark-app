package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzaa extends zzadk implements zzaes {
    private static final zzaa zzb;
    private int zze;
    private String zzf = "";
    private String zzg = "";
    private boolean zzh;
    private boolean zzi;
    private boolean zzj;

    static {
        zzaa zzaaVar = new zzaa();
        zzb = zzaaVar;
        zzadk.zzG(zzaa.class, zzaaVar);
    }

    private zzaa() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zzy zzyVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဇ\u0002\u0004ဇ\u0003\u0005ဇ\u0004", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
            case 3:
                return new zzaa();
            case 4:
                return new zzz(zzyVar);
            case 5:
                return zzb;
        }
    }
}
