package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes16.dex */
public class ClearTokenRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<ClearTokenRequest> CREATOR = new zbj();
    private final String zba;
    private final String zbb;

    /* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
    public static abstract class Builder {
        public abstract ClearTokenRequest build();

        public abstract Builder setToken(String str);

        public abstract Builder zba(String str);
    }

    ClearTokenRequest(String str, String str2) {
        this.zba = str;
        this.zbb = str2;
    }

    public static Builder builder() {
        return new zbe();
    }

    public boolean equals(Object o) {
        if (o instanceof ClearTokenRequest) {
            ClearTokenRequest clearTokenRequest = (ClearTokenRequest) o;
            if (Objects.equal(this.zba, clearTokenRequest.zba) && Objects.equal(this.zbb, clearTokenRequest.zbb)) {
                return true;
            }
        }
        return false;
    }

    public String getToken() {
        return this.zba;
    }

    public int hashCode() {
        return Objects.hashCode(this.zba, this.zbb);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(dest);
        SafeParcelWriter.writeString(dest, 1, getToken(), false);
        SafeParcelWriter.writeString(dest, 2, this.zbb, false);
        SafeParcelWriter.finishObjectHeader(dest, beginObjectHeader);
    }

    public final String zba() {
        return this.zbb;
    }

    public final Builder zbb() {
        return new zbe(this);
    }
}
