package com.google.android.gms.internal.location;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes16.dex */
final class zzbv extends zzbt {
    private final zzbx zza;

    zzbv(zzbx zzbxVar, int i) {
        super(zzbxVar.size(), i);
        this.zza = zzbxVar;
    }

    @Override // com.google.android.gms.internal.location.zzbt
    protected final Object zza(int i) {
        return this.zza.get(i);
    }
}
