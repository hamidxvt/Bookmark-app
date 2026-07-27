package com.google.android.gms.location;

import android.os.IBinder;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes16.dex */
public final class zzbg extends com.google.android.gms.internal.location.zza implements zzbi {
    zzbg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.ILocationCallback");
    }

    @Override // com.google.android.gms.location.zzbi
    public final void zzd(LocationAvailability locationAvailability) throws RemoteException {
        throw null;
    }

    @Override // com.google.android.gms.location.zzbi
    public final void zze(LocationResult locationResult) throws RemoteException {
        throw null;
    }
}
