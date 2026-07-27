package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzbn;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-maps@@19.2.0 */
/* loaded from: classes16.dex */
public final class DatasetFeature extends Feature {
    private final com.google.android.gms.internal.maps.zzr zza;

    public DatasetFeature(com.google.android.gms.internal.maps.zzr zzrVar) {
        super(zzrVar);
        this.zza = zzrVar;
    }

    public Map<String, String> getDatasetAttributes() {
        try {
            return zzbn.zzc(this.zza.zzh().entrySet());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getDatasetId() {
        try {
            return this.zza.zze();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
