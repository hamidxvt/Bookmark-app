package com.google.android.gms.fido.u2f.api.messagebased;

import com.google.android.gms.fido.u2f.api.messagebased.RequestType;

/* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
@Deprecated
/* loaded from: classes16.dex */
public enum ResponseType {
    REGISTER("u2f_register_response"),
    SIGN("u2f_sign_response");

    private final String zzb;

    ResponseType(String str) {
        this.zzb = str;
    }

    public static ResponseType getResponseTypeForRequestType(RequestType requestType) throws RequestType.UnsupportedRequestTypeException {
        if (requestType == null) {
            throw new RequestType.UnsupportedRequestTypeException(null);
        }
        switch (requestType) {
            case REGISTER:
                return REGISTER;
            case SIGN:
                return SIGN;
            default:
                throw new RequestType.UnsupportedRequestTypeException(requestType.toString());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.zzb;
    }
}
