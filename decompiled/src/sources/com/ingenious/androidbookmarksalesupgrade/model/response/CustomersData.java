package com.ingenious.androidbookmarksalesupgrade.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomersData.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b5\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u008b\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0012J\u000b\u0010-\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0092\u0001\u00107\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u00108J\u0006\u00109\u001a\u00020\u0003J\u0013\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010=HÖ\u0003J\t\u0010>\u001a\u00020\u0003HÖ\u0001J\t\u0010?\u001a\u00020\u0005HÖ\u0001J\u0016\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\u0003R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R \u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u0019R \u0010\b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R \u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0017\"\u0004\b!\u0010\u0019R \u0010\n\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0017\"\u0004\b#\u0010\u0019R \u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0017\"\u0004\b%\u0010\u0019R \u0010\f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0017\"\u0004\b'\u0010\u0019R \u0010\r\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0017\"\u0004\b)\u0010\u0019R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0017\"\u0004\b+\u0010\u0019¨\u0006E"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/CustomersData;", "Landroid/os/Parcelable;", Constant.VISIT_ID, "", AppMeasurementSdk.ConditionalUserProperty.NAME, "", "type", "admin", "address", "phone", "daysAgo", "lat", "lng", "addby", "profileName", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getType", "setType", "getAdmin", "setAdmin", "getAddress", "setAddress", "getPhone", "setPhone", "getDaysAgo", "setDaysAgo", "getLat", "setLat", "getLng", "setLng", "getAddby", "setAddby", "getProfileName", "setProfileName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/CustomersData;", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", "flags", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class CustomersData implements Parcelable {
    public static final Parcelable.Creator<CustomersData> CREATOR = new Creator();

    @SerializedName("addby")
    private String addby;

    @SerializedName("address")
    private String address;

    @SerializedName("admin")
    private String admin;

    @SerializedName("days_ago")
    private String daysAgo;

    @SerializedName(Constant.VISIT_ID)
    private Integer id;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private String lng;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    private String name;

    @SerializedName("phone")
    private String phone;
    private String profileName;

    @SerializedName("type")
    private String type;

    /* compiled from: CustomersData.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Creator implements Parcelable.Creator<CustomersData> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final CustomersData createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new CustomersData(parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final CustomersData[] newArray(int i) {
            return new CustomersData[i];
        }
    }

    public CustomersData() {
        this(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getAddby() {
        return this.addby;
    }

    /* renamed from: component11, reason: from getter */
    public final String getProfileName() {
        return this.profileName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component4, reason: from getter */
    public final String getAdmin() {
        return this.admin;
    }

    /* renamed from: component5, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* renamed from: component6, reason: from getter */
    public final String getPhone() {
        return this.phone;
    }

    /* renamed from: component7, reason: from getter */
    public final String getDaysAgo() {
        return this.daysAgo;
    }

    /* renamed from: component8, reason: from getter */
    public final String getLat() {
        return this.lat;
    }

    /* renamed from: component9, reason: from getter */
    public final String getLng() {
        return this.lng;
    }

    public final CustomersData copy(Integer id, String name, String type, String admin, String address, String phone, String daysAgo, String lat, String lng, String addby, String profileName) {
        return new CustomersData(id, name, type, admin, address, phone, daysAgo, lat, lng, addby, profileName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomersData)) {
            return false;
        }
        CustomersData customersData = (CustomersData) other;
        return Intrinsics.areEqual(this.id, customersData.id) && Intrinsics.areEqual(this.name, customersData.name) && Intrinsics.areEqual(this.type, customersData.type) && Intrinsics.areEqual(this.admin, customersData.admin) && Intrinsics.areEqual(this.address, customersData.address) && Intrinsics.areEqual(this.phone, customersData.phone) && Intrinsics.areEqual(this.daysAgo, customersData.daysAgo) && Intrinsics.areEqual(this.lat, customersData.lat) && Intrinsics.areEqual(this.lng, customersData.lng) && Intrinsics.areEqual(this.addby, customersData.addby) && Intrinsics.areEqual(this.profileName, customersData.profileName);
    }

    public int hashCode() {
        return ((((((((((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.type == null ? 0 : this.type.hashCode())) * 31) + (this.admin == null ? 0 : this.admin.hashCode())) * 31) + (this.address == null ? 0 : this.address.hashCode())) * 31) + (this.phone == null ? 0 : this.phone.hashCode())) * 31) + (this.daysAgo == null ? 0 : this.daysAgo.hashCode())) * 31) + (this.lat == null ? 0 : this.lat.hashCode())) * 31) + (this.lng == null ? 0 : this.lng.hashCode())) * 31) + (this.addby == null ? 0 : this.addby.hashCode())) * 31) + (this.profileName != null ? this.profileName.hashCode() : 0);
    }

    public String toString() {
        return "CustomersData(id=" + this.id + ", name=" + this.name + ", type=" + this.type + ", admin=" + this.admin + ", address=" + this.address + ", phone=" + this.phone + ", daysAgo=" + this.daysAgo + ", lat=" + this.lat + ", lng=" + this.lng + ", addby=" + this.addby + ", profileName=" + this.profileName + ")";
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public CustomersData(Integer id, String name, String type, String admin, String address, String phone, String daysAgo, String lat, String lng, String addby, String profileName) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.admin = admin;
        this.address = address;
        this.phone = phone;
        this.daysAgo = daysAgo;
        this.lat = lat;
        this.lng = lng;
        this.addby = addby;
        this.profileName = profileName;
    }

    public /* synthetic */ CustomersData(Integer num, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : str5, (i & 64) != 0 ? null : str6, (i & 128) != 0 ? null : str7, (i & 256) != 0 ? null : str8, (i & 512) != 0 ? null : str9, (i & 1024) == 0 ? str10 : null);
    }

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public final String getAdmin() {
        return this.admin;
    }

    public final void setAdmin(String str) {
        this.admin = str;
    }

    public final String getAddress() {
        return this.address;
    }

    public final void setAddress(String str) {
        this.address = str;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final void setPhone(String str) {
        this.phone = str;
    }

    public final String getDaysAgo() {
        return this.daysAgo;
    }

    public final void setDaysAgo(String str) {
        this.daysAgo = str;
    }

    public final String getLat() {
        return this.lat;
    }

    public final void setLat(String str) {
        this.lat = str;
    }

    public final String getLng() {
        return this.lng;
    }

    public final void setLng(String str) {
        this.lng = str;
    }

    public final String getAddby() {
        return this.addby;
    }

    public final void setAddby(String str) {
        this.addby = str;
    }

    public final String getProfileName() {
        return this.profileName;
    }

    public final void setProfileName(String str) {
        this.profileName = str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel dest, int flags) {
        int intValue;
        Intrinsics.checkNotNullParameter(dest, "dest");
        Integer num = this.id;
        if (num == null) {
            intValue = 0;
        } else {
            dest.writeInt(1);
            intValue = num.intValue();
        }
        dest.writeInt(intValue);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.admin);
        dest.writeString(this.address);
        dest.writeString(this.phone);
        dest.writeString(this.daysAgo);
        dest.writeString(this.lat);
        dest.writeString(this.lng);
        dest.writeString(this.addby);
        dest.writeString(this.profileName);
    }
}
