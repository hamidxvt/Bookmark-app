package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes16.dex */
final class zzav extends zzah {
    final /* synthetic */ zzao zza;

    zzav(zzbe zzbeVar, zzao zzaoVar) {
        this.zza = zzaoVar;
    }

    @Override // com.google.android.gms.internal.location.zzai
    public final void zzb(zzaa zzaaVar) throws RemoteException {
        if (zzaaVar.getStatus().isSuccess()) {
            return;
        }
        this.zza.zzb(zzaaVar.getStatus(), null);
    }

    @Override // com.google.android.gms.internal.location.zzai
    public final void zzc() throws RemoteException {
        this.zza.zzb(Status.RESULT_SUCCESS, null);
    }
}
