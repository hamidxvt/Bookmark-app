package com.google.android.gms.internal.p000authapi;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenRequest;
import com.google.android.gms.auth.api.identity.SavePasswordRequest;
import com.google.android.gms.common.api.ApiMetadata;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes16.dex */
public final class zbm extends zba implements IInterface {
    zbm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.identity.internal.ICredentialSavingService");
    }

    public final void zbc(zbs zbsVar, SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest, ApiMetadata apiMetadata) throws RemoteException {
        Parcel zba = zba();
        zbc.zbc(zba, zbsVar);
        zbc.zbb(zba, saveAccountLinkingTokenRequest);
        zbc.zbb(zba, apiMetadata);
        zbb(1, zba);
    }

    public final void zbd(zbu zbuVar, SavePasswordRequest savePasswordRequest, ApiMetadata apiMetadata) throws RemoteException {
        Parcel zba = zba();
        zbc.zbc(zba, zbuVar);
        zbc.zbb(zba, savePasswordRequest);
        zbc.zbb(zba, apiMetadata);
        zbb(2, zba);
    }
}
