package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
@Deprecated
/* loaded from: classes16.dex */
public class GetSignInIntentRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GetSignInIntentRequest> CREATOR = new zbm();
    private final String zba;
    private final String zbb;
    private final String zbc;
    private final String zbd;
    private final boolean zbe;
    private final int zbf;
    private final List zbg;

    /* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
    public static final class Builder {
        private String zba;
        private String zbb;
        private String zbc;
        private List zbd;
        private String zbe;
        private boolean zbf;
        private int zbg;

        public GetSignInIntentRequest build() {
            return new GetSignInIntentRequest(this.zba, this.zbb, this.zbc, this.zbe, this.zbf, this.zbg, this.zbd);
        }

        public Builder filterByHostedDomain(String str) {
            this.zbb = str;
            return this;
        }

        public Builder setClaims(List<Claim> list) {
            this.zbd = list;
            return this;
        }

        public Builder setNonce(String str) {
            this.zbe = str;
            return this;
        }

        @Deprecated
        public Builder setRequestVerifiedPhoneNumber(boolean z) {
            this.zbf = z;
            return this;
        }

        public Builder setServerClientId(String serverClientId) {
            Preconditions.checkNotNull(serverClientId);
            this.zba = serverClientId;
            return this;
        }

        public final Builder zba(String str) {
            this.zbc = str;
            return this;
        }

        public final Builder zbb(int i) {
            this.zbg = i;
            return this;
        }
    }

    GetSignInIntentRequest(String str, String str2, String str3, String str4, boolean z, int i, List list) {
        Preconditions.checkNotNull(str);
        this.zba = str;
        this.zbb = str2;
        this.zbc = str3;
        this.zbd = str4;
        this.zbe = z;
        this.zbf = i;
        this.zbg = list;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder zba(GetSignInIntentRequest getSignInIntentRequest) {
        Preconditions.checkNotNull(getSignInIntentRequest);
        Builder builder = builder();
        builder.setServerClientId(getSignInIntentRequest.getServerClientId());
        builder.setNonce(getSignInIntentRequest.getNonce());
        builder.filterByHostedDomain(getSignInIntentRequest.getHostedDomainFilter());
        builder.setRequestVerifiedPhoneNumber(getSignInIntentRequest.zbe);
        builder.zbb(getSignInIntentRequest.zbf);
        builder.setClaims(getSignInIntentRequest.getClaims());
        String str = getSignInIntentRequest.zbc;
        if (str != null) {
            builder.zba(str);
        }
        return builder;
    }

    public boolean equals(Object o) {
        if (!(o instanceof GetSignInIntentRequest)) {
            return false;
        }
        GetSignInIntentRequest getSignInIntentRequest = (GetSignInIntentRequest) o;
        return Objects.equal(this.zba, getSignInIntentRequest.zba) && Objects.equal(this.zbd, getSignInIntentRequest.zbd) && Objects.equal(this.zbb, getSignInIntentRequest.zbb) && Objects.equal(Boolean.valueOf(this.zbe), Boolean.valueOf(getSignInIntentRequest.zbe)) && this.zbf == getSignInIntentRequest.zbf && Objects.equal(this.zbg, getSignInIntentRequest.zbg);
    }

    public List<Claim> getClaims() {
        return this.zbg;
    }

    public String getHostedDomainFilter() {
        return this.zbb;
    }

    public String getNonce() {
        return this.zbd;
    }

    public String getServerClientId() {
        return this.zba;
    }

    public int hashCode() {
        return Objects.hashCode(this.zba, this.zbb, this.zbd, Boolean.valueOf(this.zbe), Integer.valueOf(this.zbf), this.zbg);
    }

    @Deprecated
    public boolean requestVerifiedPhoneNumber() {
        return this.zbe;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(dest);
        SafeParcelWriter.writeString(dest, 1, getServerClientId(), false);
        SafeParcelWriter.writeString(dest, 2, getHostedDomainFilter(), false);
        SafeParcelWriter.writeString(dest, 3, this.zbc, false);
        SafeParcelWriter.writeString(dest, 4, getNonce(), false);
        SafeParcelWriter.writeBoolean(dest, 5, requestVerifiedPhoneNumber());
        SafeParcelWriter.writeInt(dest, 6, this.zbf);
        SafeParcelWriter.writeTypedList(dest, 7, getClaims(), false);
        SafeParcelWriter.finishObjectHeader(dest, beginObjectHeader);
    }
}
