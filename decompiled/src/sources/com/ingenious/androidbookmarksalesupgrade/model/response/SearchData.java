package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SearchData.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003JJ\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0003HÖ\u0001J\t\u0010%\u001a\u00020\u0005HÖ\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R \u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R \u0010\b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013¨\u0006&"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/SearchData;", "", Constant.VISIT_ID, "", "businessName", "", "customerType", "shopAddress", "createdAt", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getBusinessName", "()Ljava/lang/String;", "setBusinessName", "(Ljava/lang/String;)V", "getCustomerType", "setCustomerType", "getShopAddress", "setShopAddress", "getCreatedAt", "setCreatedAt", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/SearchData;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class SearchData {

    @SerializedName("business_name")
    private String businessName;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("customerType")
    private String customerType;

    @SerializedName(Constant.VISIT_ID)
    private Integer id;

    @SerializedName("shop_address")
    private String shopAddress;

    public SearchData() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ SearchData copy$default(SearchData searchData, Integer num, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            num = searchData.id;
        }
        if ((i & 2) != 0) {
            str = searchData.businessName;
        }
        String str5 = str;
        if ((i & 4) != 0) {
            str2 = searchData.customerType;
        }
        String str6 = str2;
        if ((i & 8) != 0) {
            str3 = searchData.shopAddress;
        }
        String str7 = str3;
        if ((i & 16) != 0) {
            str4 = searchData.createdAt;
        }
        return searchData.copy(num, str5, str6, str7, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getBusinessName() {
        return this.businessName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getCustomerType() {
        return this.customerType;
    }

    /* renamed from: component4, reason: from getter */
    public final String getShopAddress() {
        return this.shopAddress;
    }

    /* renamed from: component5, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final SearchData copy(Integer id, String businessName, String customerType, String shopAddress, String createdAt) {
        return new SearchData(id, businessName, customerType, shopAddress, createdAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchData)) {
            return false;
        }
        SearchData searchData = (SearchData) other;
        return Intrinsics.areEqual(this.id, searchData.id) && Intrinsics.areEqual(this.businessName, searchData.businessName) && Intrinsics.areEqual(this.customerType, searchData.customerType) && Intrinsics.areEqual(this.shopAddress, searchData.shopAddress) && Intrinsics.areEqual(this.createdAt, searchData.createdAt);
    }

    public int hashCode() {
        return ((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.businessName == null ? 0 : this.businessName.hashCode())) * 31) + (this.customerType == null ? 0 : this.customerType.hashCode())) * 31) + (this.shopAddress == null ? 0 : this.shopAddress.hashCode())) * 31) + (this.createdAt != null ? this.createdAt.hashCode() : 0);
    }

    public String toString() {
        return "SearchData(id=" + this.id + ", businessName=" + this.businessName + ", customerType=" + this.customerType + ", shopAddress=" + this.shopAddress + ", createdAt=" + this.createdAt + ")";
    }

    public SearchData(Integer id, String businessName, String customerType, String shopAddress, String createdAt) {
        this.id = id;
        this.businessName = businessName;
        this.customerType = customerType;
        this.shopAddress = shopAddress;
        this.createdAt = createdAt;
    }

    public /* synthetic */ SearchData(Integer num, String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4);
    }

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public final String getBusinessName() {
        return this.businessName;
    }

    public final void setBusinessName(String str) {
        this.businessName = str;
    }

    public final String getCustomerType() {
        return this.customerType;
    }

    public final void setCustomerType(String str) {
        this.customerType = str;
    }

    public final String getShopAddress() {
        return this.shopAddress;
    }

    public final void setShopAddress(String str) {
        this.shopAddress = str;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final void setCreatedAt(String str) {
        this.createdAt = str;
    }
}
