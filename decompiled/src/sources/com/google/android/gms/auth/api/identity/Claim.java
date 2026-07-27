package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes16.dex */
public final class Claim extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Claim> CREATOR = new zbi();
    private final String zba;
    private final boolean zbb;

    /* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
    public static abstract class Builder {
        public abstract Claim build();

        public abstract Builder setEssential(boolean z);

        public abstract Builder setName(String str);
    }

    public Claim(String name, boolean essential) {
        this.zba = name;
        this.zbb = essential;
    }

    public static Builder builder() {
        return new zbd();
    }

    public boolean equals(Object o) {
        if (o instanceof Claim) {
            Claim claim = (Claim) o;
            if (this.zba.equals(claim.zba) && this.zbb == claim.zbb) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return this.zba;
    }

    public int hashCode() {
        return Objects.hashCode(this.zba, Boolean.valueOf(this.zbb));
    }

    public boolean isEssential() {
        return this.zbb;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(dest);
        SafeParcelWriter.writeString(dest, 1, getName(), false);
        SafeParcelWriter.writeBoolean(dest, 2, isEssential());
        SafeParcelWriter.finishObjectHeader(dest, beginObjectHeader);
    }
}
