package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes16.dex */
public final class zzbj extends com.google.android.gms.internal.location.zza implements zzbl {
    zzbj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.ILocationListener");
    }

    @Override // com.google.android.gms.location.zzbl
    public final void zzd(Location location) throws RemoteException {
        throw null;
    }
}
