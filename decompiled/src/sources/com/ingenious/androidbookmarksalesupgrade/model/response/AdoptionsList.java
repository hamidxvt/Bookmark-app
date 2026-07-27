package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdoptionsList.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b0\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u007f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u0006\u0010)\u001a\u00020\u0007J\u0006\u0010*\u001a\u00020\u0007J\u0010\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\u000b\u0010.\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0086\u0001\u00105\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u00106J\u0013\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010:\u001a\u00020\u0003HÖ\u0001J\t\u0010;\u001a\u00020\u0007HÖ\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\"\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\"\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0017\u0010\u0011\"\u0004\b\u0018\u0010\u0013R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR \u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001cR \u0010\t\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001cR \u0010\n\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001a\"\u0004\b\"\u0010\u001cR \u0010\u000b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001a\"\u0004\b$\u0010\u001cR\"\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b%\u0010\u0011\"\u0004\b&\u0010\u0013R\"\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b'\u0010\u0011\"\u0004\b(\u0010\u0013¨\u0006<"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionsList;", "", Constant.VISIT_ID, "", "bookerId", "customerId", "adoptionName", "", "adoptionDate", "notes", NotificationCompat.CATEGORY_STATUS, "createdAt", "productCount", "totalQuantity", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getBookerId", "setBookerId", "getCustomerId", "setCustomerId", "getAdoptionName", "()Ljava/lang/String;", "setAdoptionName", "(Ljava/lang/String;)V", "getAdoptionDate", "setAdoptionDate", "getNotes", "setNotes", "getStatus", "setStatus", "getCreatedAt", "setCreatedAt", "getProductCount", "setProductCount", "getTotalQuantity", "setTotalQuantity", "getProductCountf", "getTotalQuantityf", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionsList;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class AdoptionsList {

    @SerializedName("adoption_date")
    private String adoptionDate;

    @SerializedName("adoption_name")
    private String adoptionName;

    @SerializedName("booker_id")
    private Integer bookerId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("customer_id")
    private Integer customerId;

    @SerializedName(Constant.VISIT_ID)
    private Integer id;

    @SerializedName("notes")
    private String notes;

    @SerializedName("product_count")
    private Integer productCount;

    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    @SerializedName("total_quantity")
    private Integer totalQuantity;

    public AdoptionsList() {
        this(null, null, null, null, null, null, null, null, null, null, 1023, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final Integer getTotalQuantity() {
        return this.totalQuantity;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getBookerId() {
        return this.bookerId;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getCustomerId() {
        return this.customerId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getAdoptionName() {
        return this.adoptionName;
    }

    /* renamed from: component5, reason: from getter */
    public final String getAdoptionDate() {
        return this.adoptionDate;
    }

    /* renamed from: component6, reason: from getter */
    public final String getNotes() {
        return this.notes;
    }

    /* renamed from: component7, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* renamed from: component8, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component9, reason: from getter */
    public final Integer getProductCount() {
        return this.productCount;
    }

    public final AdoptionsList copy(Integer id, Integer bookerId, Integer customerId, String adoptionName, String adoptionDate, String notes, String status, String createdAt, Integer productCount, Integer totalQuantity) {
        return new AdoptionsList(id, bookerId, customerId, adoptionName, adoptionDate, notes, status, createdAt, productCount, totalQuantity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdoptionsList)) {
            return false;
        }
        AdoptionsList adoptionsList = (AdoptionsList) other;
        return Intrinsics.areEqual(this.id, adoptionsList.id) && Intrinsics.areEqual(this.bookerId, adoptionsList.bookerId) && Intrinsics.areEqual(this.customerId, adoptionsList.customerId) && Intrinsics.areEqual(this.adoptionName, adoptionsList.adoptionName) && Intrinsics.areEqual(this.adoptionDate, adoptionsList.adoptionDate) && Intrinsics.areEqual(this.notes, adoptionsList.notes) && Intrinsics.areEqual(this.status, adoptionsList.status) && Intrinsics.areEqual(this.createdAt, adoptionsList.createdAt) && Intrinsics.areEqual(this.productCount, adoptionsList.productCount) && Intrinsics.areEqual(this.totalQuantity, adoptionsList.totalQuantity);
    }

    public int hashCode() {
        return ((((((((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.bookerId == null ? 0 : this.bookerId.hashCode())) * 31) + (this.customerId == null ? 0 : this.customerId.hashCode())) * 31) + (this.adoptionName == null ? 0 : this.adoptionName.hashCode())) * 31) + (this.adoptionDate == null ? 0 : this.adoptionDate.hashCode())) * 31) + (this.notes == null ? 0 : this.notes.hashCode())) * 31) + (this.status == null ? 0 : this.status.hashCode())) * 31) + (this.createdAt == null ? 0 : this.createdAt.hashCode())) * 31) + (this.productCount == null ? 0 : this.productCount.hashCode())) * 31) + (this.totalQuantity != null ? this.totalQuantity.hashCode() : 0);
    }

    public String toString() {
        return "AdoptionsList(id=" + this.id + ", bookerId=" + this.bookerId + ", customerId=" + this.customerId + ", adoptionName=" + this.adoptionName + ", adoptionDate=" + this.adoptionDate + ", notes=" + this.notes + ", status=" + this.status + ", createdAt=" + this.createdAt + ", productCount=" + this.productCount + ", totalQuantity=" + this.totalQuantity + ")";
    }

    public AdoptionsList(Integer id, Integer bookerId, Integer customerId, String adoptionName, String adoptionDate, String notes, String status, String createdAt, Integer productCount, Integer totalQuantity) {
        this.id = id;
        this.bookerId = bookerId;
        this.customerId = customerId;
        this.adoptionName = adoptionName;
        this.adoptionDate = adoptionDate;
        this.notes = notes;
        this.status = status;
        this.createdAt = createdAt;
        this.productCount = productCount;
        this.totalQuantity = totalQuantity;
    }

    public /* synthetic */ AdoptionsList(Integer num, Integer num2, Integer num3, String str, String str2, String str3, String str4, String str5, Integer num4, Integer num5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : num3, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? null : num4, (i & 512) == 0 ? num5 : null);
    }

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public final Integer getBookerId() {
        return this.bookerId;
    }

    public final void setBookerId(Integer num) {
        this.bookerId = num;
    }

    public final Integer getCustomerId() {
        return this.customerId;
    }

    public final void setCustomerId(Integer num) {
        this.customerId = num;
    }

    public final String getAdoptionName() {
        return this.adoptionName;
    }

    public final void setAdoptionName(String str) {
        this.adoptionName = str;
    }

    public final String getAdoptionDate() {
        return this.adoptionDate;
    }

    public final void setAdoptionDate(String str) {
        this.adoptionDate = str;
    }

    public final String getNotes() {
        return this.notes;
    }

    public final void setNotes(String str) {
        this.notes = str;
    }

    public final String getStatus() {
        return this.status;
    }

    public final void setStatus(String str) {
        this.status = str;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public final Integer getProductCount() {
        return this.productCount;
    }

    public final void setProductCount(Integer num) {
        this.productCount = num;
    }

    public final Integer getTotalQuantity() {
        return this.totalQuantity;
    }

    public final void setTotalQuantity(Integer num) {
        this.totalQuantity = num;
    }

    public final String getProductCountf() {
        Integer num = this.productCount;
        int count = num != null ? num.intValue() : 0;
        return count + " Books";
    }

    public final String getTotalQuantityf() {
        Integer num = this.totalQuantity;
        int quantity = num != null ? num.intValue() : 0;
        return quantity + " Quantity";
    }
}
