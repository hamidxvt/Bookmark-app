package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fido.u2f.api.common.ProtocolVersion;
import com.google.android.gms.internal.fido.zzaj;
import com.google.android.gms.internal.fido.zzak;
import com.google.android.gms.internal.fido.zzbf;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
@Deprecated
/* loaded from: classes16.dex */
public class RegisterResponseData extends ResponseData {
    public static final Parcelable.Creator<RegisterResponseData> CREATOR = new zzi();
    private final byte[] zza;
    private final ProtocolVersion zzb;
    private final String zzc;

    public RegisterResponseData(byte[] registerData) {
        this.zza = (byte[]) Preconditions.checkNotNull(registerData);
        this.zzb = ProtocolVersion.V1;
        this.zzc = null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RegisterResponseData)) {
            return false;
        }
        RegisterResponseData registerResponseData = (RegisterResponseData) obj;
        return Objects.equal(this.zzb, registerResponseData.zzb) && Arrays.equals(this.zza, registerResponseData.zza) && Objects.equal(this.zzc, registerResponseData.zzc);
    }

    public String getClientDataString() {
        return this.zzc;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.zzb;
    }

    public byte[] getRegisterData() {
        return this.zza;
    }

    public int getVersionCode() {
        ProtocolVersion protocolVersion = ProtocolVersion.UNKNOWN;
        switch (this.zzb.ordinal()) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return -1;
        }
    }

    public int hashCode() {
        return Objects.hashCode(this.zzb, Integer.valueOf(Arrays.hashCode(this.zza)), this.zzc);
    }

    @Override // com.google.android.gms.fido.u2f.api.common.ResponseData
    public JSONObject toJsonObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("registrationData", Base64.encodeToString(this.zza, 11));
            jSONObject.put("version", this.zzb.toString());
            String str = this.zzc;
            if (str != null) {
                jSONObject.put(SignResponseData.JSON_RESPONSE_DATA_CLIENT_DATA, Base64.encodeToString(str.getBytes(), 11));
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        zzaj zza = zzak.zza(this);
        zza.zzb("protocolVersion", this.zzb);
        zzbf zzd = zzbf.zzd();
        byte[] bArr = this.zza;
        zza.zzb("registerData", zzd.zze(bArr, 0, bArr.length));
        String str = this.zzc;
        if (str != null) {
            zza.zzb("clientDataString", str);
        }
        return zza.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(dest);
        SafeParcelWriter.writeByteArray(dest, 2, getRegisterData(), false);
        SafeParcelWriter.writeString(dest, 3, this.zzb.toString(), false);
        SafeParcelWriter.writeString(dest, 4, getClientDataString(), false);
        SafeParcelWriter.finishObjectHeader(dest, beginObjectHeader);
    }

    public RegisterResponseData(byte[] registerData, ProtocolVersion protocolVersion, String clientDataString) {
        this.zza = (byte[]) Preconditions.checkNotNull(registerData);
        this.zzb = (ProtocolVersion) Preconditions.checkNotNull(protocolVersion);
        Preconditions.checkArgument(protocolVersion != ProtocolVersion.UNKNOWN);
        if (protocolVersion != ProtocolVersion.V1) {
            this.zzc = (String) Preconditions.checkNotNull(clientDataString);
        } else {
            Preconditions.checkArgument(clientDataString == null);
            this.zzc = null;
        }
    }

    RegisterResponseData(byte[] bArr, String str, String str2) {
        this.zza = bArr;
        try {
            this.zzb = ProtocolVersion.fromString(str);
            this.zzc = str2;
        } catch (ProtocolVersion.UnsupportedProtocolException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
