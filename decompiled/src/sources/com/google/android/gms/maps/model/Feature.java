package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-maps@@19.2.0 */
/* loaded from: classes16.dex */
public abstract class Feature {
    private final com.google.android.gms.internal.maps.zzr zza;

    Feature(com.google.android.gms.internal.maps.zzr zzrVar) {
        this.zza = zzrVar;
    }

    static Feature zza(com.google.android.gms.internal.maps.zzr zzrVar) {
        Preconditions.checkNotNull(zzrVar);
        try {
            int zzd = zzrVar.zzd();
            if (zzd == 1) {
                return new PlaceFeature(zzrVar);
            }
            if (zzd == 2) {
                return new DatasetFeature(zzrVar);
            }
            return null;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    @FeatureType
    public String getFeatureType() {
        try {
            return this.zza.zzf();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
