package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzax {
    static zzax zza = null;
    private final zzay zzb;

    private zzax(zzay zzayVar) {
        this.zzb = zzayVar;
    }

    public static zzax zza() {
        zza = new zzax(new zzaw());
        return zza;
    }
}
