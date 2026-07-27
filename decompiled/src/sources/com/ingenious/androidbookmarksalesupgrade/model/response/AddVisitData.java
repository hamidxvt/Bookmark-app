package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.FrameMetricsAggregator;
import com.google.gson.annotations.SerializedName;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddVisitData.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b,\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010'\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010Jz\u0010/\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u00100J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00104\u001a\u00020\u0003HÖ\u0001J\t\u00105\u001a\u00020\u0005HÖ\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R \u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017R \u0010\b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R \u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0015\"\u0004\b\u001f\u0010\u0017R \u0010\n\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0015\"\u0004\b!\u0010\u0017R \u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0015\"\u0004\b#\u0010\u0017R\"\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b$\u0010\u0010\"\u0004\b%\u0010\u0012¨\u00066"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/AddVisitData;", "", "bookerId", "", "customerId", "", "visitDate", "priority", "type", "visitType", "updatedAt", "createdAt", Constant.VISIT_ID, "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getBookerId", "()Ljava/lang/Integer;", "setBookerId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getCustomerId", "()Ljava/lang/String;", "setCustomerId", "(Ljava/lang/String;)V", "getVisitDate", "setVisitDate", "getPriority", "setPriority", "getType", "setType", "getVisitType", "setVisitType", "getUpdatedAt", "setUpdatedAt", "getCreatedAt", "setCreatedAt", "getId", "setId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/AddVisitData;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class AddVisitData {

    @SerializedName("booker_id")
    private Integer bookerId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("customer_id")
    private String customerId;

    @SerializedName(Constant.VISIT_ID)
    private Integer id;

    @SerializedName("priority")
    private String priority;

    @SerializedName("type")
    private String type;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("visit_date")
    private String visitDate;

    @SerializedName("visit_type")
    private String visitType;

    public AddVisitData() {
        this(null, null, null, null, null, null, null, null, null, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getBookerId() {
        return this.bookerId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCustomerId() {
        return this.customerId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getVisitDate() {
        return this.visitDate;
    }

    /* renamed from: component4, reason: from getter */
    public final String getPriority() {
        return this.priority;
    }

    /* renamed from: component5, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component6, reason: from getter */
    public final String getVisitType() {
        return this.visitType;
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

    public final AddVisitData copy(Integer bookerId, String customerId, String visitDate, String priority, String type, String visitType, String updatedAt, String createdAt, Integer id) {
        return new AddVisitData(bookerId, customerId, visitDate, priority, type, visitType, updatedAt, createdAt, id);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddVisitData)) {
            return false;
        }
        AddVisitData addVisitData = (AddVisitData) other;
        return Intrinsics.areEqual(this.bookerId, addVisitData.bookerId) && Intrinsics.areEqual(this.customerId, addVisitData.customerId) && Intrinsics.areEqual(this.visitDate, addVisitData.visitDate) && Intrinsics.areEqual(this.priority, addVisitData.priority) && Intrinsics.areEqual(this.type, addVisitData.type) && Intrinsics.areEqual(this.visitType, addVisitData.visitType) && Intrinsics.areEqual(this.updatedAt, addVisitData.updatedAt) && Intrinsics.areEqual(this.createdAt, addVisitData.createdAt) && Intrinsics.areEqual(this.id, addVisitData.id);
    }

    public int hashCode() {
        return ((((((((((((((((this.bookerId == null ? 0 : this.bookerId.hashCode()) * 31) + (this.customerId == null ? 0 : this.customerId.hashCode())) * 31) + (this.visitDate == null ? 0 : this.visitDate.hashCode())) * 31) + (this.priority == null ? 0 : this.priority.hashCode())) * 31) + (this.type == null ? 0 : this.type.hashCode())) * 31) + (this.visitType == null ? 0 : this.visitType.hashCode())) * 31) + (this.updatedAt == null ? 0 : this.updatedAt.hashCode())) * 31) + (this.createdAt == null ? 0 : this.createdAt.hashCode())) * 31) + (this.id != null ? this.id.hashCode() : 0);
    }

    public String toString() {
        return "AddVisitData(bookerId=" + this.bookerId + ", customerId=" + this.customerId + ", visitDate=" + this.visitDate + ", priority=" + this.priority + ", type=" + this.type + ", visitType=" + this.visitType + ", updatedAt=" + this.updatedAt + ", createdAt=" + this.createdAt + ", id=" + this.id + ")";
    }

    public AddVisitData(Integer bookerId, String customerId, String visitDate, String priority, String type, String visitType, String updatedAt, String createdAt, Integer id) {
        this.bookerId = bookerId;
        this.customerId = customerId;
        this.visitDate = visitDate;
        this.priority = priority;
        this.type = type;
        this.visitType = visitType;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.id = id;
    }

    public /* synthetic */ AddVisitData(Integer num, String str, String str2, String str3, String str4, String str5, String str6, String str7, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : str5, (i & 64) != 0 ? null : str6, (i & 128) != 0 ? null : str7, (i & 256) == 0 ? num2 : null);
    }

    public final Integer getBookerId() {
        return this.bookerId;
    }

    public final void setBookerId(Integer num) {
        this.bookerId = num;
    }

    public final String getCustomerId() {
        return this.customerId;
    }

    public final void setCustomerId(String str) {
        this.customerId = str;
    }

    public final String getVisitDate() {
        return this.visitDate;
    }

    public final void setVisitDate(String str) {
        this.visitDate = str;
    }

    public final String getPriority() {
        return this.priority;
    }

    public final void setPriority(String str) {
        this.priority = str;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public final String getVisitType() {
        return this.visitType;
    }

    public final void setVisitType(String str) {
        this.visitType = str;
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
}
