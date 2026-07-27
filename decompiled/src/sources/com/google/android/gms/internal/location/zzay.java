package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes16.dex */
final class zzay extends com.google.android.gms.location.zzbh {
    private final ListenerHolder zza;

    zzay(ListenerHolder listenerHolder) {
        this.zza = listenerHolder;
    }

    public final synchronized void zzc() {
        this.zza.clear();
    }

    @Override // com.google.android.gms.location.zzbi
    public final void zzd(LocationAvailability locationAvailability) {
        this.zza.notifyListener(new zzax(this, locationAvailability));
    }

    @Override // com.google.android.gms.location.zzbi
    public final void zze(LocationResult locationResult) {
        this.zza.notifyListener(new zzaw(this, locationResult));
    }
}
