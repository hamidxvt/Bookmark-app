package com.google.android.gms.common;

/* compiled from: com.google.android.gms:play-services-basement@@18.5.0 */
/* loaded from: classes16.dex */
public final class GooglePlayServicesNotAvailableException extends Exception {
    public final int errorCode;

    public GooglePlayServicesNotAvailableException(int errorCode) {
        this.errorCode = errorCode;
    }
}
