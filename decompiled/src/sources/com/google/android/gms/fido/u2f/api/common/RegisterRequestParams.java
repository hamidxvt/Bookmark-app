package com.google.android.gms.fido.u2f.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
@Deprecated
/* loaded from: classes16.dex */
public class RegisterRequestParams extends RequestParams {
    public static final Parcelable.Creator<RegisterRequestParams> CREATOR = new zzh();
    public static final int MAX_DISPLAY_HINT_LENGTH = 80;
    private final Integer zza;
    private final Double zzb;
    private final Uri zzc;
    private final List zzd;
    private final List zze;
    private final ChannelIdValue zzf;
    private final String zzg;
    private Set zzh;

    /* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
    public static final class Builder {
        Integer zza;
        Double zzb;
        Uri zzc;
        List zzd;
        List zze;
        ChannelIdValue zzf;
        String zzg;

        public RegisterRequestParams build() {
            return new RegisterRequestParams(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
        }

        public Builder setAppId(Uri uri) {
            this.zzc = uri;
            return this;
        }

        public Builder setChannelIdValue(ChannelIdValue channelIdValue) {
            this.zzf = channelIdValue;
            return this;
        }

        public Builder setDisplayHint(String str) {
            this.zzg = str;
            return this;
        }

        public Builder setRegisterRequests(List<RegisterRequest> list) {
            this.zzd = list;
            return this;
        }

        public Builder setRegisteredKeys(List<RegisteredKey> list) {
            this.zze = list;
            return this;
        }

        public Builder setRequestId(Integer num) {
            this.zza = num;
            return this;
        }

        public Builder setTimeoutSeconds(Double d) {
            this.zzb = d;
            return this;
        }
    }

    RegisterRequestParams(Integer num, Double d, Uri uri, List list, List list2, ChannelIdValue channelIdValue, String str) {
        this.zza = num;
        this.zzb = d;
        this.zzc = uri;
        boolean z = false;
        Preconditions.checkArgument((list == null || list.isEmpty()) ? false : true, "empty list of register requests is provided");
        this.zzd = list;
        this.zze = list2;
        this.zzf = channelIdValue;
        Uri uri2 = this.zzc;
        List<RegisterRequest> list3 = this.zzd;
        List<RegisteredKey> list4 = this.zze;
        HashSet hashSet = new HashSet();
        if (uri2 != null) {
            hashSet.add(uri2);
        }
        for (RegisterRequest registerRequest : list3) {
            Preconditions.checkArgument(uri2 == null ? registerRequest.getAppId() != null : true, "register request has null appId and no request appId is provided");
            if (registerRequest.getAppId() != null) {
                hashSet.add(Uri.parse(registerRequest.getAppId()));
            }
        }
        for (RegisteredKey registeredKey : list4) {
            Preconditions.checkArgument(uri2 == null ? registeredKey.getAppId() != null : true, "registered key has null appId and no request appId is provided");
            if (registeredKey.getAppId() != null) {
                hashSet.add(Uri.parse(registeredKey.getAppId()));
            }
        }
        this.zzh = hashSet;
        if (str == null) {
            z = true;
        } else if (str.length() <= 80) {
            z = true;
        }
        Preconditions.checkArgument(z, "Display Hint cannot be longer than 80 characters");
        this.zzg = str;
    }

    public boolean equals(Object o) {
        List list;
        List list2;
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegisterRequestParams)) {
            return false;
        }
        RegisterRequestParams registerRequestParams = (RegisterRequestParams) o;
        return Objects.equal(this.zza, registerRequestParams.zza) && Objects.equal(this.zzb, registerRequestParams.zzb) && Objects.equal(this.zzc, registerRequestParams.zzc) && Objects.equal(this.zzd, registerRequestParams.zzd) && (((list = this.zze) == null && registerRequestParams.zze == null) || (list != null && (list2 = registerRequestParams.zze) != null && list.containsAll(list2) && registerRequestParams.zze.containsAll(this.zze))) && Objects.equal(this.zzf, registerRequestParams.zzf) && Objects.equal(this.zzg, registerRequestParams.zzg);
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    public Set<Uri> getAllAppIds() {
        return this.zzh;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    public Uri getAppId() {
        return this.zzc;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    public ChannelIdValue getChannelIdValue() {
        return this.zzf;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    public String getDisplayHint() {
        return this.zzg;
    }

    public List<RegisterRequest> getRegisterRequests() {
        return this.zzd;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    public List<RegisteredKey> getRegisteredKeys() {
        return this.zze;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    public Integer getRequestId() {
        return this.zza;
    }

    @Override // com.google.android.gms.fido.u2f.api.common.RequestParams
    public Double getTimeoutSeconds() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza, this.zzc, this.zzb, this.zzd, this.zze, this.zzf, this.zzg);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(dest);
        SafeParcelWriter.writeIntegerObject(dest, 2, getRequestId(), false);
        SafeParcelWriter.writeDoubleObject(dest, 3, getTimeoutSeconds(), false);
        SafeParcelWriter.writeParcelable(dest, 4, getAppId(), flags, false);
        SafeParcelWriter.writeTypedList(dest, 5, getRegisterRequests(), false);
        SafeParcelWriter.writeTypedList(dest, 6, getRegisteredKeys(), false);
        SafeParcelWriter.writeParcelable(dest, 7, getChannelIdValue(), flags, false);
        SafeParcelWriter.writeString(dest, 8, getDisplayHint(), false);
        SafeParcelWriter.finishObjectHeader(dest, beginObjectHeader);
    }
}
