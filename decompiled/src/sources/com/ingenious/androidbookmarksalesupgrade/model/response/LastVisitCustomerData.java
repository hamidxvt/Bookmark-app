package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LastVisitCustomerData.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003JJ\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0005HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R \u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR \u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\f\"\u0004\b\u0019\u0010\u000e¨\u0006&"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/LastVisitCustomerData;", "", "customerId", "", "daysSinceLastVisit", "", "bookerId", "lastVisitDate", "createdAt", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "getCustomerId", "()Ljava/lang/String;", "setCustomerId", "(Ljava/lang/String;)V", "getDaysSinceLastVisit", "()Ljava/lang/Integer;", "setDaysSinceLastVisit", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getBookerId", "setBookerId", "getLastVisitDate", "setLastVisitDate", "getCreatedAt", "setCreatedAt", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/LastVisitCustomerData;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class LastVisitCustomerData {

    @SerializedName("booker_id")
    private Integer bookerId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("customer_id")
    private String customerId;

    @SerializedName("days_since_last_visit")
    private Integer daysSinceLastVisit;

    @SerializedName("last_visit_date")
    private String lastVisitDate;

    public LastVisitCustomerData() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ LastVisitCustomerData copy$default(LastVisitCustomerData lastVisitCustomerData, String str, Integer num, Integer num2, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = lastVisitCustomerData.customerId;
        }
        if ((i & 2) != 0) {
            num = lastVisitCustomerData.daysSinceLastVisit;
        }
        Integer num3 = num;
        if ((i & 4) != 0) {
            num2 = lastVisitCustomerData.bookerId;
        }
        Integer num4 = num2;
        if ((i & 8) != 0) {
            str2 = lastVisitCustomerData.lastVisitDate;
        }
        String str4 = str2;
        if ((i & 16) != 0) {
            str3 = lastVisitCustomerData.createdAt;
        }
        return lastVisitCustomerData.copy(str, num3, num4, str4, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getCustomerId() {
        return this.customerId;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getDaysSinceLastVisit() {
        return this.daysSinceLastVisit;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getBookerId() {
        return this.bookerId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getLastVisitDate() {
        return this.lastVisitDate;
    }

    /* renamed from: component5, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final LastVisitCustomerData copy(String customerId, Integer daysSinceLastVisit, Integer bookerId, String lastVisitDate, String createdAt) {
        return new LastVisitCustomerData(customerId, daysSinceLastVisit, bookerId, lastVisitDate, createdAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LastVisitCustomerData)) {
            return false;
        }
        LastVisitCustomerData lastVisitCustomerData = (LastVisitCustomerData) other;
        return Intrinsics.areEqual(this.customerId, lastVisitCustomerData.customerId) && Intrinsics.areEqual(this.daysSinceLastVisit, lastVisitCustomerData.daysSinceLastVisit) && Intrinsics.areEqual(this.bookerId, lastVisitCustomerData.bookerId) && Intrinsics.areEqual(this.lastVisitDate, lastVisitCustomerData.lastVisitDate) && Intrinsics.areEqual(this.createdAt, lastVisitCustomerData.createdAt);
    }

    public int hashCode() {
        return ((((((((this.customerId == null ? 0 : this.customerId.hashCode()) * 31) + (this.daysSinceLastVisit == null ? 0 : this.daysSinceLastVisit.hashCode())) * 31) + (this.bookerId == null ? 0 : this.bookerId.hashCode())) * 31) + (this.lastVisitDate == null ? 0 : this.lastVisitDate.hashCode())) * 31) + (this.createdAt != null ? this.createdAt.hashCode() : 0);
    }

    public String toString() {
        return "LastVisitCustomerData(customerId=" + this.customerId + ", daysSinceLastVisit=" + this.daysSinceLastVisit + ", bookerId=" + this.bookerId + ", lastVisitDate=" + this.lastVisitDate + ", createdAt=" + this.createdAt + ")";
    }

    public LastVisitCustomerData(String customerId, Integer daysSinceLastVisit, Integer bookerId, String lastVisitDate, String createdAt) {
        this.customerId = customerId;
        this.daysSinceLastVisit = daysSinceLastVisit;
        this.bookerId = bookerId;
        this.lastVisitDate = lastVisitDate;
        this.createdAt = createdAt;
    }

    public /* synthetic */ LastVisitCustomerData(String str, Integer num, Integer num2, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3);
    }

    public final String getCustomerId() {
        return this.customerId;
    }

    public final void setCustomerId(String str) {
        this.customerId = str;
    }

    public final Integer getDaysSinceLastVisit() {
        return this.daysSinceLastVisit;
    }

    public final void setDaysSinceLastVisit(Integer num) {
        this.daysSinceLastVisit = num;
    }

    public final Integer getBookerId() {
        return this.bookerId;
    }

    public final void setBookerId(Integer num) {
        this.bookerId = num;
    }

    public final String getLastVisitDate() {
        return this.lastVisitDate;
    }

    public final void setLastVisitDate(String str) {
        this.lastVisitDate = str;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final void setCreatedAt(String str) {
        this.createdAt = str;
    }
}
