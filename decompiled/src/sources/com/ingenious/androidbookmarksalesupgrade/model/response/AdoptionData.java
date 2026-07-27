package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdoptionData.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u008d\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\u0018\b\u0002\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00100\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u00101\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00106\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0019\u00107\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010HÆ\u0003J\u0094\u0001\u00108\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\u0018\b\u0002\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010HÆ\u0001¢\u0006\u0002\u00109J\u0013\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010=\u001a\u00020\u0006HÖ\u0001J\t\u0010>\u001a\u00020\u0003HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\"\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\"\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR \u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0014\"\u0004\b!\u0010\u0016R \u0010\t\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0014\"\u0004\b#\u0010\u0016R \u0010\n\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0014\"\u0004\b%\u0010\u0016R \u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0014\"\u0004\b'\u0010\u0016R\"\u0010\f\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b(\u0010\u001a\"\u0004\b)\u0010\u001cR.\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u0006?"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionData;", "", "adoptionName", "", "adoptionDate", "bookerId", "", "customerId", "notes", NotificationCompat.CATEGORY_STATUS, "updatedAt", "createdAt", Constant.VISIT_ID, "adoptionProducts", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionProducts;", "Lkotlin/collections/ArrayList;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/ArrayList;)V", "getAdoptionName", "()Ljava/lang/String;", "setAdoptionName", "(Ljava/lang/String;)V", "getAdoptionDate", "setAdoptionDate", "getBookerId", "()Ljava/lang/Integer;", "setBookerId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getCustomerId", "setCustomerId", "getNotes", "setNotes", "getStatus", "setStatus", "getUpdatedAt", "setUpdatedAt", "getCreatedAt", "setCreatedAt", "getId", "setId", "getAdoptionProducts", "()Ljava/util/ArrayList;", "setAdoptionProducts", "(Ljava/util/ArrayList;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/ArrayList;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/AdoptionData;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class AdoptionData {

    @SerializedName("adoption_date")
    private String adoptionDate;

    @SerializedName("adoption_name")
    private String adoptionName;

    @SerializedName("adoption_products")
    private ArrayList<AdoptionProducts> adoptionProducts;

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

    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    @SerializedName("updated_at")
    private String updatedAt;

    public AdoptionData() {
        this(null, null, null, null, null, null, null, null, null, null, 1023, null);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAdoptionName() {
        return this.adoptionName;
    }

    public final ArrayList<AdoptionProducts> component10() {
        return this.adoptionProducts;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAdoptionDate() {
        return this.adoptionDate;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getBookerId() {
        return this.bookerId;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getCustomerId() {
        return this.customerId;
    }

    /* renamed from: component5, reason: from getter */
    public final String getNotes() {
        return this.notes;
    }

    /* renamed from: component6, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* renamed from: component7, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* renamed from: component8, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component9, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    public final AdoptionData copy(String adoptionName, String adoptionDate, Integer bookerId, Integer customerId, String notes, String status, String updatedAt, String createdAt, Integer id, ArrayList<AdoptionProducts> adoptionProducts) {
        Intrinsics.checkNotNullParameter(adoptionProducts, "adoptionProducts");
        return new AdoptionData(adoptionName, adoptionDate, bookerId, customerId, notes, status, updatedAt, createdAt, id, adoptionProducts);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdoptionData)) {
            return false;
        }
        AdoptionData adoptionData = (AdoptionData) other;
        return Intrinsics.areEqual(this.adoptionName, adoptionData.adoptionName) && Intrinsics.areEqual(this.adoptionDate, adoptionData.adoptionDate) && Intrinsics.areEqual(this.bookerId, adoptionData.bookerId) && Intrinsics.areEqual(this.customerId, adoptionData.customerId) && Intrinsics.areEqual(this.notes, adoptionData.notes) && Intrinsics.areEqual(this.status, adoptionData.status) && Intrinsics.areEqual(this.updatedAt, adoptionData.updatedAt) && Intrinsics.areEqual(this.createdAt, adoptionData.createdAt) && Intrinsics.areEqual(this.id, adoptionData.id) && Intrinsics.areEqual(this.adoptionProducts, adoptionData.adoptionProducts);
    }

    public int hashCode() {
        return ((((((((((((((((((this.adoptionName == null ? 0 : this.adoptionName.hashCode()) * 31) + (this.adoptionDate == null ? 0 : this.adoptionDate.hashCode())) * 31) + (this.bookerId == null ? 0 : this.bookerId.hashCode())) * 31) + (this.customerId == null ? 0 : this.customerId.hashCode())) * 31) + (this.notes == null ? 0 : this.notes.hashCode())) * 31) + (this.status == null ? 0 : this.status.hashCode())) * 31) + (this.updatedAt == null ? 0 : this.updatedAt.hashCode())) * 31) + (this.createdAt == null ? 0 : this.createdAt.hashCode())) * 31) + (this.id != null ? this.id.hashCode() : 0)) * 31) + this.adoptionProducts.hashCode();
    }

    public String toString() {
        return "AdoptionData(adoptionName=" + this.adoptionName + ", adoptionDate=" + this.adoptionDate + ", bookerId=" + this.bookerId + ", customerId=" + this.customerId + ", notes=" + this.notes + ", status=" + this.status + ", updatedAt=" + this.updatedAt + ", createdAt=" + this.createdAt + ", id=" + this.id + ", adoptionProducts=" + this.adoptionProducts + ")";
    }

    public AdoptionData(String adoptionName, String adoptionDate, Integer bookerId, Integer customerId, String notes, String status, String updatedAt, String createdAt, Integer id, ArrayList<AdoptionProducts> adoptionProducts) {
        Intrinsics.checkNotNullParameter(adoptionProducts, "adoptionProducts");
        this.adoptionName = adoptionName;
        this.adoptionDate = adoptionDate;
        this.bookerId = bookerId;
        this.customerId = customerId;
        this.notes = notes;
        this.status = status;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.id = id;
        this.adoptionProducts = adoptionProducts;
    }

    public /* synthetic */ AdoptionData(String str, String str2, Integer num, Integer num2, String str3, String str4, String str5, String str6, Integer num3, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) == 0 ? num3 : null, (i & 512) != 0 ? new ArrayList() : arrayList);
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

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final void setUpdatedAt(String str) {
        this.updatedAt = str;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public final ArrayList<AdoptionProducts> getAdoptionProducts() {
        return this.adoptionProducts;
    }

    public final void setAdoptionProducts(ArrayList<AdoptionProducts> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.adoptionProducts = arrayList;
    }
}
