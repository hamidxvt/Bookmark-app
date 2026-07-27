package com.google.android.gms.internal.auth;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
/* loaded from: classes16.dex */
public final class zzhs extends zzev implements zzfy {
    private static final zzhs zzb;
    private zzez zzd = zzev.zzf();

    static {
        zzhs zzhsVar = new zzhs();
        zzb = zzhsVar;
        zzev.zzk(zzhs.class, zzhsVar);
    }

    private zzhs() {
    }

    public static zzhs zzp(byte[] bArr) throws zzfb {
        return (zzhs) zzev.zzd(zzb, bArr);
    }

    public final List zzq() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.auth.zzev
    protected final Object zzn(int i, Object obj, Object obj2) {
        zzhq zzhqVar = null;
        switch (i - 1) {
            case 0:
                return (byte) 1;
            case 1:
            default:
                return null;
            case 2:
                return zzh(zzb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"zzd"});
            case 3:
                return new zzhs();
            case 4:
                return new zzhr(zzhqVar);
            case 5:
                return zzb;
        }
    }
}
