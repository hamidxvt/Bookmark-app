package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@23.0.0 */
/* loaded from: classes16.dex */
public abstract class zzcz extends zzbm implements zzda {
    public zzcz() {
        super("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
    }

    @Override // com.google.android.gms.internal.measurement.zzbm
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                String readString = parcel.readString();
                String readString2 = parcel.readString();
                Bundle bundle = (Bundle) zzbn.zzb(parcel, Bundle.CREATOR);
                long readLong = parcel.readLong();
                zzbn.zzf(parcel);
                zze(readString, readString2, bundle, readLong);
                parcel2.writeNoException();
                return true;
            case 2:
                int zzf = zzf();
                parcel2.writeNoException();
                parcel2.writeInt(zzf);
                return true;
            default:
                return false;
        }
    }
}
