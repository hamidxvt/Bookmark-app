package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/* compiled from: com.google.android.gms:play-services-maps@@19.2.0 */
/* loaded from: classes16.dex */
final class zzb extends com.google.android.gms.maps.internal.zzaw {
    final /* synthetic */ GoogleMap.OnMarkerDragListener zza;

    zzb(GoogleMap googleMap, GoogleMap.OnMarkerDragListener onMarkerDragListener) {
        this.zza = onMarkerDragListener;
    }

    @Override // com.google.android.gms.maps.internal.zzax
    public final void zzb(com.google.android.gms.internal.maps.zzaj zzajVar) {
        this.zza.onMarkerDrag(new Marker(zzajVar));
    }

    @Override // com.google.android.gms.maps.internal.zzax
    public final void zzc(com.google.android.gms.internal.maps.zzaj zzajVar) {
        this.zza.onMarkerDragEnd(new Marker(zzajVar));
    }

    @Override // com.google.android.gms.maps.internal.zzax
    public final void zzd(com.google.android.gms.internal.maps.zzaj zzajVar) {
        this.zza.onMarkerDragStart(new Marker(zzajVar));
    }
}
