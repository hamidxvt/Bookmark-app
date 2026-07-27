package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public final class zzagl implements zzagm {
    private static final Object zza = new Object();
    private volatile zzagm zzb;
    private volatile Object zzc = zza;

    private zzagl(zzagm zzagmVar) {
        this.zzb = zzagmVar;
    }

    public static zzagm zza(zzagm zzagmVar) {
        return new zzagl(zzagmVar);
    }

    @Override // com.google.android.libraries.places.internal.zzagm
    public final Object zzb() {
        Object obj = this.zzc;
        if (obj != zza) {
            return obj;
        }
        if (this.zzb == null) {
            return this.zzc;
        }
        zzav zzavVar = new zzav();
        this.zzc = zzavVar;
        this.zzb = null;
        return zzavVar;
    }
}
