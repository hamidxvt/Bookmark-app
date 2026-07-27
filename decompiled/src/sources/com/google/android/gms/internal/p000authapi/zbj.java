package com.google.android.gms.internal.p000authapi;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.AuthorizationRequest;
import com.google.android.gms.auth.api.identity.ClearTokenRequest;
import com.google.android.gms.auth.api.identity.RevokeAccessRequest;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.internal.IStatusCallback;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes16.dex */
public final class zbj extends zba implements IInterface {
    zbj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.identity.internal.IAuthorizationService");
    }

    public final void zbc(zbi zbiVar, AuthorizationRequest authorizationRequest, ApiMetadata apiMetadata) throws RemoteException {
        Parcel zba = zba();
        zbc.zbc(zba, zbiVar);
        zbc.zbb(zba, authorizationRequest);
        zbc.zbb(zba, apiMetadata);
        zbb(1, zba);
    }

    public final void zbd(IStatusCallback iStatusCallback, RevokeAccessRequest revokeAccessRequest, ApiMetadata apiMetadata) throws RemoteException {
        Parcel zba = zba();
        zbc.zbc(zba, iStatusCallback);
        zbc.zbb(zba, revokeAccessRequest);
        zbc.zbb(zba, apiMetadata);
        zbb(3, zba);
    }

    public final void zbe(IStatusCallback iStatusCallback, ClearTokenRequest clearTokenRequest, ApiMetadata apiMetadata) throws RemoteException {
        Parcel zba = zba();
        zbc.zbc(zba, iStatusCallback);
        zbc.zbb(zba, clearTokenRequest);
        zbc.zbb(zba, apiMetadata);
        zbb(4, zba);
    }
}
