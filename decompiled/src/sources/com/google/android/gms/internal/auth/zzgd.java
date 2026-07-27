package com.google.android.gms.internal.auth;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
/* loaded from: classes16.dex */
final class zzgd {
    private static final zzgc zza;
    private static final zzgc zzb;

    static {
        zzgc zzgcVar;
        try {
            zzgcVar = (zzgc) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            zzgcVar = null;
        }
        zza = zzgcVar;
        zzb = new zzgc();
    }

    static zzgc zza() {
        return zza;
    }

    static zzgc zzb() {
        return zzb;
    }
}
