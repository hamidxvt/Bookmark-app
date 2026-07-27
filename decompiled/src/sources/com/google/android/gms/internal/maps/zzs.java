package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@19.2.0 */
/* loaded from: classes16.dex */
public final class zzs extends zza implements zzu {
    zzs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IFeatureLayerDelegate");
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final String zzd() throws RemoteException {
        Parcel zzJ = zzJ(6, zza());
        String readString = zzJ.readString();
        zzJ.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final String zze() throws RemoteException {
        Parcel zzJ = zzJ(1, zza());
        String readString = zzJ.readString();
        zzJ.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final void zzf(zzal zzalVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzalVar);
        zzc(4, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final void zzg(zzal zzalVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzalVar);
        zzc(5, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final void zzh(zzat zzatVar) throws RemoteException {
        Parcel zza = zza();
        zzc.zzg(zza, zzatVar);
        zzc(3, zza);
    }

    @Override // com.google.android.gms.internal.maps.zzu
    public final boolean zzi() throws RemoteException {
        Parcel zzJ = zzJ(2, zza());
        boolean zzh = zzc.zzh(zzJ);
        zzJ.recycle();
        return zzh;
    }
}
