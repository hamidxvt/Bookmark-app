package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyResponse;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
/* loaded from: classes16.dex */
public abstract class zzbf extends zzb implements zzbg {
    public zzbf() {
        super("com.google.android.gms.auth.api.internal.IAuthCallbacks");
    }

    @Override // com.google.android.gms.internal.auth.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                ProxyResponse proxyResponse = (ProxyResponse) zzc.zza(parcel, ProxyResponse.CREATOR);
                zzc.zzb(parcel);
                zzb(proxyResponse);
                break;
            case 2:
                String readString = parcel.readString();
                zzc.zzb(parcel);
                zzc(readString);
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
