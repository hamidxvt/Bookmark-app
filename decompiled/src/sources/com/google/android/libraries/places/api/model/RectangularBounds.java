package com.google.android.libraries.places.api.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* compiled from: com.google.android.libraries.places:places@@2.6.0 */
/* loaded from: classes16.dex */
public abstract class RectangularBounds implements LocationBias, LocationRestriction {
    public static RectangularBounds newInstance(LatLng southwest, LatLng northeast) {
        return newInstance(new LatLngBounds(southwest, northeast));
    }

    public abstract LatLng getNortheast();

    public abstract LatLng getSouthwest();

    public static RectangularBounds newInstance(LatLngBounds bounds) {
        zzv zzvVar = new zzv();
        zzvVar.zzb(bounds.southwest);
        zzvVar.zza(bounds.northeast);
        return zzvVar.zzc();
    }
}
