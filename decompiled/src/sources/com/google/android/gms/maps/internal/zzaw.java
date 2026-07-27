package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@19.2.0 */
/* loaded from: classes16.dex */
public abstract class zzaw extends com.google.android.gms.internal.maps.zzb implements zzax {
    public zzaw() {
        super("com.google.android.gms.maps.internal.IOnMarkerDragListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                com.google.android.gms.internal.maps.zzaj zzb = com.google.android.gms.internal.maps.zzai.zzb(parcel.readStrongBinder());
                com.google.android.gms.internal.maps.zzc.zzd(parcel);
                zzd(zzb);
                break;
            case 2:
                com.google.android.gms.internal.maps.zzaj zzb2 = com.google.android.gms.internal.maps.zzai.zzb(parcel.readStrongBinder());
                com.google.android.gms.internal.maps.zzc.zzd(parcel);
                zzb(zzb2);
                break;
            case 3:
                com.google.android.gms.internal.maps.zzaj zzb3 = com.google.android.gms.internal.maps.zzai.zzb(parcel.readStrongBinder());
                com.google.android.gms.internal.maps.zzc.zzd(parcel);
                zzc(zzb3);
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
