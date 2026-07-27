package com.google.android.gms.auth.api.identity;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.p000authapi.zbbi;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes16.dex */
public class RevokeAccessRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<RevokeAccessRequest> CREATOR = new zbr();
    private final zbbi zba;
    private final Account zbb;
    private final String zbc;

    /* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
    public static abstract class Builder {
        public abstract RevokeAccessRequest build();

        public abstract Builder setAccount(Account account);

        public abstract Builder setScopes(List<Scope> list);

        public abstract Builder zba(String str);
    }

    RevokeAccessRequest(List list, Account account, String str) {
        this.zba = zbbi.zbi(list);
        this.zbb = account;
        this.zbc = str;
    }

    public static Builder builder() {
        return new zbf();
    }

    public boolean equals(Object o) {
        if (!(o instanceof RevokeAccessRequest)) {
            return false;
        }
        RevokeAccessRequest revokeAccessRequest = (RevokeAccessRequest) o;
        zbbi zbbiVar = this.zba;
        int size = zbbiVar.size();
        zbbi zbbiVar2 = revokeAccessRequest.zba;
        return size == zbbiVar2.size() && zbbiVar.containsAll(zbbiVar2) && Objects.equal(this.zbb, revokeAccessRequest.zbb) && Objects.equal(this.zbc, revokeAccessRequest.zbc);
    }

    public Account getAccount() {
        return this.zbb;
    }

    public List<Scope> getScopes() {
        return this.zba;
    }

    public int hashCode() {
        return Objects.hashCode(this.zba, this.zbb, this.zbc);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(dest);
        SafeParcelWriter.writeTypedList(dest, 1, getScopes(), false);
        SafeParcelWriter.writeParcelable(dest, 2, getAccount(), flags, false);
        SafeParcelWriter.writeString(dest, 3, this.zbc, false);
        SafeParcelWriter.finishObjectHeader(dest, beginObjectHeader);
    }

    public final String zba() {
        return this.zbc;
    }

    public final Builder zbb() {
        return new zbf(this);
    }
}
