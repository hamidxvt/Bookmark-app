package com.google.android.gms.maps.model;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@19.2.0 */
/* loaded from: classes16.dex */
public final class PlaceFeature extends Feature {
    private final com.google.android.gms.internal.maps.zzr zza;

    public PlaceFeature(com.google.android.gms.internal.maps.zzr zzrVar) {
        super(zzrVar);
        this.zza = zzrVar;
    }

    public String getPlaceId() {
        try {
            return this.zza.zzg();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
