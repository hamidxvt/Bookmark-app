package com.ingenious.androidbookmarksalesupgrade.model.request;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddCustomerRequest.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003JO\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\u001e\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001e\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR\u001e\u0010\b\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000fR\u001e\u0010\t\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000f¨\u0006*"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddCustomerRequest;", "", "schoolName", "", "principalName", "phone", FirebaseAnalytics.Param.LOCATION, "customerType", "shopAddressLatitude", "shopAddressLongitude", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getSchoolName", "()Ljava/lang/String;", "setSchoolName", "(Ljava/lang/String;)V", "getPrincipalName", "setPrincipalName", "getPhone", "setPhone", "getLocation", "setLocation", "getCustomerType", "setCustomerType", "getShopAddressLatitude", "setShopAddressLatitude", "getShopAddressLongitude", "setShopAddressLongitude", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes11.dex */
public final /* data */ class AddCustomerRequest {

    @SerializedName("customerType")
    private String customerType;

    @SerializedName(FirebaseAnalytics.Param.LOCATION)
    private String location;

    @SerializedName("phone")
    private String phone;

    @SerializedName("principalName")
    private String principalName;

    @SerializedName("schoolName")
    private String schoolName;

    @SerializedName("shopAddressLatitude")
    private String shopAddressLatitude;

    @SerializedName("shopAddressLongitude")
    private String shopAddressLongitude;

    public AddCustomerRequest() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public static /* synthetic */ AddCustomerRequest copy$default(AddCustomerRequest addCustomerRequest, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
        if ((i & 1) != 0) {
            str = addCustomerRequest.schoolName;
        }
        if ((i & 2) != 0) {
            str2 = addCustomerRequest.principalName;
        }
        String str8 = str2;
        if ((i & 4) != 0) {
            str3 = addCustomerRequest.phone;
        }
        String str9 = str3;
        if ((i & 8) != 0) {
            str4 = addCustomerRequest.location;
        }
        String str10 = str4;
        if ((i & 16) != 0) {
            str5 = addCustomerRequest.customerType;
        }
        String str11 = str5;
        if ((i & 32) != 0) {
            str6 = addCustomerRequest.shopAddressLatitude;
        }
        String str12 = str6;
        if ((i & 64) != 0) {
            str7 = addCustomerRequest.shopAddressLongitude;
        }
        return addCustomerRequest.copy(str, str8, str9, str10, str11, str12, str7);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSchoolName() {
        return this.schoolName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPrincipalName() {
        return this.principalName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPhone() {
        return this.phone;
    }

    /* renamed from: component4, reason: from getter */
    public final String getLocation() {
        return this.location;
    }

    /* renamed from: component5, reason: from getter */
    public final String getCustomerType() {
        return this.customerType;
    }

    /* renamed from: component6, reason: from getter */
    public final String getShopAddressLatitude() {
        return this.shopAddressLatitude;
    }

    /* renamed from: component7, reason: from getter */
    public final String getShopAddressLongitude() {
        return this.shopAddressLongitude;
    }

    public final AddCustomerRequest copy(String schoolName, String principalName, String phone, String location, String customerType, String shopAddressLatitude, String shopAddressLongitude) {
        Intrinsics.checkNotNullParameter(schoolName, "schoolName");
        Intrinsics.checkNotNullParameter(principalName, "principalName");
        Intrinsics.checkNotNullParameter(phone, "phone");
        Intrinsics.checkNotNullParameter(location, "location");
        Intrinsics.checkNotNullParameter(customerType, "customerType");
        Intrinsics.checkNotNullParameter(shopAddressLatitude, "shopAddressLatitude");
        Intrinsics.checkNotNullParameter(shopAddressLongitude, "shopAddressLongitude");
        return new AddCustomerRequest(schoolName, principalName, phone, location, customerType, shopAddressLatitude, shopAddressLongitude);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddCustomerRequest)) {
            return false;
        }
        AddCustomerRequest addCustomerRequest = (AddCustomerRequest) other;
        return Intrinsics.areEqual(this.schoolName, addCustomerRequest.schoolName) && Intrinsics.areEqual(this.principalName, addCustomerRequest.principalName) && Intrinsics.areEqual(this.phone, addCustomerRequest.phone) && Intrinsics.areEqual(this.location, addCustomerRequest.location) && Intrinsics.areEqual(this.customerType, addCustomerRequest.customerType) && Intrinsics.areEqual(this.shopAddressLatitude, addCustomerRequest.shopAddressLatitude) && Intrinsics.areEqual(this.shopAddressLongitude, addCustomerRequest.shopAddressLongitude);
    }

    public int hashCode() {
        return (((((((((((this.schoolName.hashCode() * 31) + this.principalName.hashCode()) * 31) + this.phone.hashCode()) * 31) + this.location.hashCode()) * 31) + this.customerType.hashCode()) * 31) + this.shopAddressLatitude.hashCode()) * 31) + this.shopAddressLongitude.hashCode();
    }

    public String toString() {
        return "AddCustomerRequest(schoolName=" + this.schoolName + ", principalName=" + this.principalName + ", phone=" + this.phone + ", location=" + this.location + ", customerType=" + this.customerType + ", shopAddressLatitude=" + this.shopAddressLatitude + ", shopAddressLongitude=" + this.shopAddressLongitude + ")";
    }

    public AddCustomerRequest(String schoolName, String principalName, String phone, String location, String customerType, String shopAddressLatitude, String shopAddressLongitude) {
        Intrinsics.checkNotNullParameter(schoolName, "schoolName");
        Intrinsics.checkNotNullParameter(principalName, "principalName");
        Intrinsics.checkNotNullParameter(phone, "phone");
        Intrinsics.checkNotNullParameter(location, "location");
        Intrinsics.checkNotNullParameter(customerType, "customerType");
        Intrinsics.checkNotNullParameter(shopAddressLatitude, "shopAddressLatitude");
        Intrinsics.checkNotNullParameter(shopAddressLongitude, "shopAddressLongitude");
        this.schoolName = schoolName;
        this.principalName = principalName;
        this.phone = phone;
        this.location = location;
        this.customerType = customerType;
        this.shopAddressLatitude = shopAddressLatitude;
        this.shopAddressLongitude = shopAddressLongitude;
    }

    public /* synthetic */ AddCustomerRequest(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5, (i & 32) != 0 ? "" : str6, (i & 64) != 0 ? "" : str7);
    }

    public final String getSchoolName() {
        return this.schoolName;
    }

    public final void setSchoolName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.schoolName = str;
    }

    public final String getPrincipalName() {
        return this.principalName;
    }

    public final void setPrincipalName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.principalName = str;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final void setPhone(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.phone = str;
    }

    public final String getLocation() {
        return this.location;
    }

    public final void setLocation(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.location = str;
    }

    public final String getCustomerType() {
        return this.customerType;
    }

    public final void setCustomerType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.customerType = str;
    }

    public final String getShopAddressLatitude() {
        return this.shopAddressLatitude;
    }

    public final void setShopAddressLatitude(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.shopAddressLatitude = str;
    }

    public final String getShopAddressLongitude() {
        return this.shopAddressLongitude;
    }

    public final void setShopAddressLongitude(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.shopAddressLongitude = str;
    }
}
