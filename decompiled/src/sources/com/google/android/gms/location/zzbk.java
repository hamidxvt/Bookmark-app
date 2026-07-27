package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes16.dex */
public abstract class zzbk extends com.google.android.gms.internal.location.zzb implements zzbl {
    public zzbk() {
        super("com.google.android.gms.location.ILocationListener");
    }

    public static zzbl zzb(IBinder iBinder) {
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationListener");
        return queryLocalInterface instanceof zzbl ? (zzbl) queryLocalInterface : new zzbj(iBinder);
    }

    @Override // com.google.android.gms.internal.location.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzd((Location) com.google.android.gms.internal.location.zzc.zza(parcel, Location.CREATOR));
        return true;
    }
}
