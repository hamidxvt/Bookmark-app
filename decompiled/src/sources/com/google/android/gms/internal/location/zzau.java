package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.OnTokenCanceledListener;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes16.dex */
final class zzau extends LocationCallback {
    final /* synthetic */ zzao zza;
    final /* synthetic */ OnTokenCanceledListener zzb;

    zzau(zzbe zzbeVar, zzao zzaoVar, OnTokenCanceledListener onTokenCanceledListener) {
        this.zza = zzaoVar;
        this.zzb = onTokenCanceledListener;
    }

    @Override // com.google.android.gms.location.LocationCallback
    public final void onLocationAvailability(LocationAvailability locationAvailability) {
    }

    @Override // com.google.android.gms.location.LocationCallback
    public final void onLocationResult(LocationResult locationResult) {
        try {
            this.zza.zzb(Status.RESULT_SUCCESS, locationResult.getLastLocation());
            this.zzb.onCanceled();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
