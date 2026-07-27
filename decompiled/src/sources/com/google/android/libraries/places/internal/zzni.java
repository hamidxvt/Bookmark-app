package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzni extends zzadk implements zzaes {
    private static final zzni zzb;
    private int zze;
    private zznk zzg;
    private zznm zzh;
    private zzabx zzi;
    private byte zzj = 2;
    private int zzf = 1;

    static {
        zzni zzniVar = new zzni();
        zzb = zzniVar;
        zzadk.zzG(zzni.class, zzniVar);
    }

    private zzni() {
    }

    @Override // com.google.android.libraries.places.internal.zzadk
    protected final Object zzb(int i, Object obj, Object obj2) {
        zznf zznfVar = null;
        switch (i - 1) {
            case 0:
                return Byte.valueOf(this.zzj);
            case 1:
            default:
                this.zzj = obj == null ? (byte) 0 : (byte) 1;
                return null;
            case 2:
                return zzF(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0001\u0001ဌ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ᐉ\u0003", new Object[]{"zze", "zzf", zznh.zza, "zzg", "zzh", "zzi"});
            case 3:
                return new zzni();
            case 4:
                return new zzng(zznfVar);
            case 5:
                return zzb;
        }
    }
}
