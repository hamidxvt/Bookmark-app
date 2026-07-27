package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-maps@@19.2.0 */
/* loaded from: classes16.dex */
public abstract class zzh extends com.google.android.gms.internal.maps.zzb implements zzi {
    public zzh() {
        super("com.google.android.gms.maps.internal.IInfoWindowAdapter");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                com.google.android.gms.internal.maps.zzaj zzb = com.google.android.gms.internal.maps.zzai.zzb(parcel.readStrongBinder());
                com.google.android.gms.internal.maps.zzc.zzd(parcel);
                IObjectWrapper zzc = zzc(zzb);
                parcel2.writeNoException();
                com.google.android.gms.internal.maps.zzc.zzg(parcel2, zzc);
                return true;
            case 2:
                com.google.android.gms.internal.maps.zzaj zzb2 = com.google.android.gms.internal.maps.zzai.zzb(parcel.readStrongBinder());
                com.google.android.gms.internal.maps.zzc.zzd(parcel);
                IObjectWrapper zzb3 = zzb(zzb2);
                parcel2.writeNoException();
                com.google.android.gms.internal.maps.zzc.zzg(parcel2, zzb3);
                return true;
            default:
                return false;
        }
    }
}
