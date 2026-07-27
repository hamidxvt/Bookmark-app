package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzal;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-maps@@19.2.0 */
/* loaded from: classes16.dex */
public final class FeatureLayer {
    private final com.google.android.gms.internal.maps.zzu zza;
    private StyleFactory zzb;
    private final Map zzc = new HashMap();

    /* compiled from: com.google.android.gms:play-services-maps@@19.2.0 */
    public interface OnFeatureClickListener {
        void onFeatureClick(FeatureClickEvent featureClickEvent);
    }

    /* compiled from: com.google.android.gms:play-services-maps@@19.2.0 */
    public interface StyleFactory {
        FeatureStyle getStyle(Feature feature);
    }

    public FeatureLayer(com.google.android.gms.internal.maps.zzu zzuVar) {
        this.zza = (com.google.android.gms.internal.maps.zzu) Preconditions.checkNotNull(zzuVar);
    }

    public String getDatasetId() {
        try {
            return this.zza.zzd();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public StyleFactory getFeatureStyle() {
        return this.zzb;
    }

    @FeatureType
    public String getFeatureType() {
        try {
            return this.zza.zze();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isAvailable() {
        try {
            return this.zza.zzi();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void removeOnFeatureClickListener(OnFeatureClickListener listener) {
        try {
            Map map = this.zzc;
            if (map.containsKey(listener)) {
                this.zza.zzg((zzal) map.get(listener));
                map.remove(listener);
            }
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setFeatureStyle(StyleFactory styleFactory) {
        this.zzb = styleFactory;
        if (styleFactory == null) {
            try {
                this.zza.zzh(null);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        } else {
            try {
                this.zza.zzh(new zzd(this, styleFactory));
            } catch (RemoteException e2) {
                throw new RuntimeRemoteException(e2);
            }
        }
    }

    public final void addOnFeatureClickListener(OnFeatureClickListener listener) {
        try {
            zze zzeVar = new zze(this, listener);
            this.zzc.put(listener, zzeVar);
            this.zza.zzf(zzeVar);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
