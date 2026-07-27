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

/* compiled from: ProfileData.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\bY\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0083\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010N\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u000b\u0010O\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010T\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u000b\u0010U\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010V\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u00102J\u0010\u0010W\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u00102J\u000b\u0010X\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010Z\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u0010\u0010[\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u000b\u0010\\\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010^\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u008a\u0002\u0010c\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010dJ\u0006\u0010e\u001a\u00020\u0003J\u0013\u0010f\u001a\u00020g2\b\u0010h\u001a\u0004\u0018\u00010iHÖ\u0003J\t\u0010j\u001a\u00020\u0003HÖ\u0001J\t\u0010k\u001a\u00020\u0005HÖ\u0001J\u0016\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020o2\u0006\u0010p\u001a\u00020\u0003R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\"\"\u0004\b&\u0010$R \u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\"\"\u0004\b(\u0010$R \u0010\b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\"\"\u0004\b*\u0010$R \u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\"\"\u0004\b,\u0010$R\"\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b-\u0010\u001d\"\u0004\b.\u0010\u001fR \u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\"\"\u0004\b0\u0010$R\"\u0010\f\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u00105\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\"\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u00105\u001a\u0004\b6\u00102\"\u0004\b7\u00104R \u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\"\"\u0004\b9\u0010$R \u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\"\"\u0004\b;\u0010$R\"\u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b<\u0010\u001d\"\u0004\b=\u0010\u001fR\"\u0010\u0012\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b>\u0010\u001d\"\u0004\b?\u0010\u001fR \u0010\u0013\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\"\"\u0004\bA\u0010$R \u0010\u0014\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\"\"\u0004\bC\u0010$R \u0010\u0015\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\"\"\u0004\bE\u0010$R \u0010\u0016\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\"\"\u0004\bG\u0010$R \u0010\u0017\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010\"\"\u0004\bI\u0010$R \u0010\u0018\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\"\"\u0004\bK\u0010$R \u0010\u0019\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\"\"\u0004\bM\u0010$¨\u0006q"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/ProfileData;", "Landroid/os/Parcelable;", Constant.VISIT_ID, "", AppMeasurementSdk.ConditionalUserProperty.NAME, "", "phone", "email", "address", "image", "adminApproved", "region", "latitude", "", "longitude", "jobStatus", "rating", "newUpdates", "priceUpdates", "createdAt", "updatedAt", "role", "numberOfInvoices", "outstandingBalance", "city", "memberSince", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getPhone", "setPhone", "getEmail", "setEmail", "getAddress", "setAddress", "getImage", "setImage", "getAdminApproved", "setAdminApproved", "getRegion", "setRegion", "getLatitude", "()Ljava/lang/Double;", "setLatitude", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "getLongitude", "setLongitude", "getJobStatus", "setJobStatus", "getRating", "setRating", "getNewUpdates", "setNewUpdates", "getPriceUpdates", "setPriceUpdates", "getCreatedAt", "setCreatedAt", "getUpdatedAt", "setUpdatedAt", "getRole", "setRole", "getNumberOfInvoices", "setNumberOfInvoices", "getOutstandingBalance", "setOutstandingBalance", "getCity", "setCity", "getMemberSince", "setMemberSince", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/ProfileData;", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", "flags", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class ProfileData implements Parcelable {
    public static final Parcelable.Creator<ProfileData> CREATOR = new Creator();

    @SerializedName("address")
    private String address;

    @SerializedName("admin_approved")
    private Integer adminApproved;

    @SerializedName("city")
    private String city;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("email")
    private String email;

    @SerializedName(Constant.VISIT_ID)
    private Integer id;

    @SerializedName("image")
    private String image;

    @SerializedName("job_status")
    private String jobStatus;

    @SerializedName("latitude")
    private Double latitude;

    @SerializedName("longitude")
    private Double longitude;

    @SerializedName("member_since")
    private String memberSince;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    private String name;

    @SerializedName("new_updates")
    private Integer newUpdates;

    @SerializedName("number_of_invoices")
    private String numberOfInvoices;

    @SerializedName("outstanding_balance")
    private String outstandingBalance;

    @SerializedName("phone")
    private String phone;

    @SerializedName("price_updates")
    private Integer priceUpdates;

    @SerializedName("rating")
    private String rating;

    @SerializedName("region")
    private String region;

    @SerializedName("role")
    private String role;

    @SerializedName("updated_at")
    private String updatedAt;

    /* compiled from: ProfileData.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Creator implements Parcelable.Creator<ProfileData> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ProfileData createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ProfileData(parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readString(), parcel.readInt() == 0 ? null : Double.valueOf(parcel.readDouble()), parcel.readInt() == 0 ? null : Double.valueOf(parcel.readDouble()), parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final ProfileData[] newArray(int i) {
            return new ProfileData[i];
        }
    }

    public ProfileData() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 2097151, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final Double getLongitude() {
        return this.longitude;
    }

    /* renamed from: component11, reason: from getter */
    public final String getJobStatus() {
        return this.jobStatus;
    }

    /* renamed from: component12, reason: from getter */
    public final String getRating() {
        return this.rating;
    }

    /* renamed from: component13, reason: from getter */
    public final Integer getNewUpdates() {
        return this.newUpdates;
    }

    /* renamed from: component14, reason: from getter */
    public final Integer getPriceUpdates() {
        return this.priceUpdates;
    }

    /* renamed from: component15, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component16, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* renamed from: component17, reason: from getter */
    public final String getRole() {
        return this.role;
    }

    /* renamed from: component18, reason: from getter */
    public final String getNumberOfInvoices() {
        return this.numberOfInvoices;
    }

    /* renamed from: component19, reason: from getter */
    public final String getOutstandingBalance() {
        return this.outstandingBalance;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component20, reason: from getter */
    public final String getCity() {
        return this.city;
    }

    /* renamed from: component21, reason: from getter */
    public final String getMemberSince() {
        return this.memberSince;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPhone() {
        return this.phone;
    }

    /* renamed from: component4, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    /* renamed from: component5, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* renamed from: component6, reason: from getter */
    public final String getImage() {
        return this.image;
    }

    /* renamed from: component7, reason: from getter */
    public final Integer getAdminApproved() {
        return this.adminApproved;
    }

    /* renamed from: component8, reason: from getter */
    public final String getRegion() {
        return this.region;
    }

    /* renamed from: component9, reason: from getter */
    public final Double getLatitude() {
        return this.latitude;
    }

    public final ProfileData copy(Integer id, String name, String phone, String email, String address, String image, Integer adminApproved, String region, Double latitude, Double longitude, String jobStatus, String rating, Integer newUpdates, Integer priceUpdates, String createdAt, String updatedAt, String role, String numberOfInvoices, String outstandingBalance, String city, String memberSince) {
        return new ProfileData(id, name, phone, email, address, image, adminApproved, region, latitude, longitude, jobStatus, rating, newUpdates, priceUpdates, createdAt, updatedAt, role, numberOfInvoices, outstandingBalance, city, memberSince);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProfileData)) {
            return false;
        }
        ProfileData profileData = (ProfileData) other;
        return Intrinsics.areEqual(this.id, profileData.id) && Intrinsics.areEqual(this.name, profileData.name) && Intrinsics.areEqual(this.phone, profileData.phone) && Intrinsics.areEqual(this.email, profileData.email) && Intrinsics.areEqual(this.address, profileData.address) && Intrinsics.areEqual(this.image, profileData.image) && Intrinsics.areEqual(this.adminApproved, profileData.adminApproved) && Intrinsics.areEqual(this.region, profileData.region) && Intrinsics.areEqual((Object) this.latitude, (Object) profileData.latitude) && Intrinsics.areEqual((Object) this.longitude, (Object) profileData.longitude) && Intrinsics.areEqual(this.jobStatus, profileData.jobStatus) && Intrinsics.areEqual(this.rating, profileData.rating) && Intrinsics.areEqual(this.newUpdates, profileData.newUpdates) && Intrinsics.areEqual(this.priceUpdates, profileData.priceUpdates) && Intrinsics.areEqual(this.createdAt, profileData.createdAt) && Intrinsics.areEqual(this.updatedAt, profileData.updatedAt) && Intrinsics.areEqual(this.role, profileData.role) && Intrinsics.areEqual(this.numberOfInvoices, profileData.numberOfInvoices) && Intrinsics.areEqual(this.outstandingBalance, profileData.outstandingBalance) && Intrinsics.areEqual(this.city, profileData.city) && Intrinsics.areEqual(this.memberSince, profileData.memberSince);
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.phone == null ? 0 : this.phone.hashCode())) * 31) + (this.email == null ? 0 : this.email.hashCode())) * 31) + (this.address == null ? 0 : this.address.hashCode())) * 31) + (this.image == null ? 0 : this.image.hashCode())) * 31) + (this.adminApproved == null ? 0 : this.adminApproved.hashCode())) * 31) + (this.region == null ? 0 : this.region.hashCode())) * 31) + (this.latitude == null ? 0 : this.latitude.hashCode())) * 31) + (this.longitude == null ? 0 : this.longitude.hashCode())) * 31) + (this.jobStatus == null ? 0 : this.jobStatus.hashCode())) * 31) + (this.rating == null ? 0 : this.rating.hashCode())) * 31) + (this.newUpdates == null ? 0 : this.newUpdates.hashCode())) * 31) + (this.priceUpdates == null ? 0 : this.priceUpdates.hashCode())) * 31) + (this.createdAt == null ? 0 : this.createdAt.hashCode())) * 31) + (this.updatedAt == null ? 0 : this.updatedAt.hashCode())) * 31) + (this.role == null ? 0 : this.role.hashCode())) * 31) + (this.numberOfInvoices == null ? 0 : this.numberOfInvoices.hashCode())) * 31) + (this.outstandingBalance == null ? 0 : this.outstandingBalance.hashCode())) * 31) + (this.city == null ? 0 : this.city.hashCode())) * 31) + (this.memberSince != null ? this.memberSince.hashCode() : 0);
    }

    public String toString() {
        return "ProfileData(id=" + this.id + ", name=" + this.name + ", phone=" + this.phone + ", email=" + this.email + ", address=" + this.address + ", image=" + this.image + ", adminApproved=" + this.adminApproved + ", region=" + this.region + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", jobStatus=" + this.jobStatus + ", rating=" + this.rating + ", newUpdates=" + this.newUpdates + ", priceUpdates=" + this.priceUpdates + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", role=" + this.role + ", numberOfInvoices=" + this.numberOfInvoices + ", outstandingBalance=" + this.outstandingBalance + ", city=" + this.city + ", memberSince=" + this.memberSince + ")";
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public ProfileData(Integer id, String name, String phone, String email, String address, String image, Integer adminApproved, String region, Double latitude, Double longitude, String jobStatus, String rating, Integer newUpdates, Integer priceUpdates, String createdAt, String updatedAt, String role, String numberOfInvoices, String outstandingBalance, String city, String memberSince) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.image = image;
        this.adminApproved = adminApproved;
        this.region = region;
        this.latitude = latitude;
        this.longitude = longitude;
        this.jobStatus = jobStatus;
        this.rating = rating;
        this.newUpdates = newUpdates;
        this.priceUpdates = priceUpdates;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.role = role;
        this.numberOfInvoices = numberOfInvoices;
        this.outstandingBalance = outstandingBalance;
        this.city = city;
        this.memberSince = memberSince;
    }

    public /* synthetic */ ProfileData(Integer num, String str, String str2, String str3, String str4, String str5, Integer num2, String str6, Double d, Double d2, String str7, String str8, Integer num3, Integer num4, String str9, String str10, String str11, String str12, String str13, String str14, String str15, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : str5, (i & 64) != 0 ? null : num2, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? null : d, (i & 512) != 0 ? null : d2, (i & 1024) != 0 ? null : str7, (i & 2048) != 0 ? null : str8, (i & 4096) != 0 ? null : num3, (i & 8192) != 0 ? null : num4, (i & 16384) != 0 ? null : str9, (i & 32768) != 0 ? null : str10, (i & 65536) != 0 ? null : str11, (i & 131072) != 0 ? null : str12, (i & 262144) != 0 ? null : str13, (i & 524288) != 0 ? null : str14, (i & 1048576) != 0 ? null : str15);
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

    public final String getPhone() {
        return this.phone;
    }

    public final void setPhone(String str) {
        this.phone = str;
    }

    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(String str) {
        this.email = str;
    }

    public final String getAddress() {
        return this.address;
    }

    public final void setAddress(String str) {
        this.address = str;
    }

    public final String getImage() {
        return this.image;
    }

    public final void setImage(String str) {
        this.image = str;
    }

    public final Integer getAdminApproved() {
        return this.adminApproved;
    }

    public final void setAdminApproved(Integer num) {
        this.adminApproved = num;
    }

    public final String getRegion() {
        return this.region;
    }

    public final void setRegion(String str) {
        this.region = str;
    }

    public final Double getLatitude() {
        return this.latitude;
    }

    public final void setLatitude(Double d) {
        this.latitude = d;
    }

    public final Double getLongitude() {
        return this.longitude;
    }

    public final void setLongitude(Double d) {
        this.longitude = d;
    }

    public final String getJobStatus() {
        return this.jobStatus;
    }

    public final void setJobStatus(String str) {
        this.jobStatus = str;
    }

    public final String getRating() {
        return this.rating;
    }

    public final void setRating(String str) {
        this.rating = str;
    }

    public final Integer getNewUpdates() {
        return this.newUpdates;
    }

    public final void setNewUpdates(Integer num) {
        this.newUpdates = num;
    }

    public final Integer getPriceUpdates() {
        return this.priceUpdates;
    }

    public final void setPriceUpdates(Integer num) {
        this.priceUpdates = num;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final void setUpdatedAt(String str) {
        this.updatedAt = str;
    }

    public final String getRole() {
        return this.role;
    }

    public final void setRole(String str) {
        this.role = str;
    }

    public final String getNumberOfInvoices() {
        return this.numberOfInvoices;
    }

    public final void setNumberOfInvoices(String str) {
        this.numberOfInvoices = str;
    }

    public final String getOutstandingBalance() {
        return this.outstandingBalance;
    }

    public final void setOutstandingBalance(String str) {
        this.outstandingBalance = str;
    }

    public final String getCity() {
        return this.city;
    }

    public final void setCity(String str) {
        this.city = str;
    }

    public final String getMemberSince() {
        return this.memberSince;
    }

    public final void setMemberSince(String str) {
        this.memberSince = str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        Integer num = this.id;
        if (num == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num.intValue());
        }
        dest.writeString(this.name);
        dest.writeString(this.phone);
        dest.writeString(this.email);
        dest.writeString(this.address);
        dest.writeString(this.image);
        Integer num2 = this.adminApproved;
        if (num2 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num2.intValue());
        }
        dest.writeString(this.region);
        Double d = this.latitude;
        if (d == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeDouble(d.doubleValue());
        }
        Double d2 = this.longitude;
        if (d2 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeDouble(d2.doubleValue());
        }
        dest.writeString(this.jobStatus);
        dest.writeString(this.rating);
        Integer num3 = this.newUpdates;
        if (num3 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num3.intValue());
        }
        Integer num4 = this.priceUpdates;
        if (num4 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num4.intValue());
        }
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeString(this.role);
        dest.writeString(this.numberOfInvoices);
        dest.writeString(this.outstandingBalance);
        dest.writeString(this.city);
        dest.writeString(this.memberSince);
    }
}
