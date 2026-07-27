package com.ingenious.androidbookmarksalesupgrade.model.request;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AddVisitRequest.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001e\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001e\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\r¨\u0006\""}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/request/AddVisitRequest;", "", "customerType", "", "customerId", "visitDate", "purpose", "priority", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCustomerType", "()Ljava/lang/String;", "setCustomerType", "(Ljava/lang/String;)V", "getCustomerId", "setCustomerId", "getVisitDate", "setVisitDate", "getPurpose", "setPurpose", "getPriority", "setPriority", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes11.dex */
public final /* data */ class AddVisitRequest {

    @SerializedName("customer_id")
    private String customerId;

    @SerializedName("customer_type")
    private String customerType;

    @SerializedName("priority")
    private String priority;

    @SerializedName("purpose")
    private String purpose;

    @SerializedName("visit_date")
    private String visitDate;

    public AddVisitRequest() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ AddVisitRequest copy$default(AddVisitRequest addVisitRequest, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = addVisitRequest.customerType;
        }
        if ((i & 2) != 0) {
            str2 = addVisitRequest.customerId;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = addVisitRequest.visitDate;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = addVisitRequest.purpose;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = addVisitRequest.priority;
        }
        return addVisitRequest.copy(str, str6, str7, str8, str5);
    }

    /* renamed from: component1, reason: from getter */
    public final String getCustomerType() {
        return this.customerType;
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
    public final String getPurpose() {
        return this.purpose;
    }

    /* renamed from: component5, reason: from getter */
    public final String getPriority() {
        return this.priority;
    }

    public final AddVisitRequest copy(String customerType, String customerId, String visitDate, String purpose, String priority) {
        Intrinsics.checkNotNullParameter(customerType, "customerType");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(visitDate, "visitDate");
        Intrinsics.checkNotNullParameter(purpose, "purpose");
        Intrinsics.checkNotNullParameter(priority, "priority");
        return new AddVisitRequest(customerType, customerId, visitDate, purpose, priority);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddVisitRequest)) {
            return false;
        }
        AddVisitRequest addVisitRequest = (AddVisitRequest) other;
        return Intrinsics.areEqual(this.customerType, addVisitRequest.customerType) && Intrinsics.areEqual(this.customerId, addVisitRequest.customerId) && Intrinsics.areEqual(this.visitDate, addVisitRequest.visitDate) && Intrinsics.areEqual(this.purpose, addVisitRequest.purpose) && Intrinsics.areEqual(this.priority, addVisitRequest.priority);
    }

    public int hashCode() {
        return (((((((this.customerType.hashCode() * 31) + this.customerId.hashCode()) * 31) + this.visitDate.hashCode()) * 31) + this.purpose.hashCode()) * 31) + this.priority.hashCode();
    }

    public String toString() {
        return "AddVisitRequest(customerType=" + this.customerType + ", customerId=" + this.customerId + ", visitDate=" + this.visitDate + ", purpose=" + this.purpose + ", priority=" + this.priority + ")";
    }

    public AddVisitRequest(String customerType, String customerId, String visitDate, String purpose, String priority) {
        Intrinsics.checkNotNullParameter(customerType, "customerType");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(visitDate, "visitDate");
        Intrinsics.checkNotNullParameter(purpose, "purpose");
        Intrinsics.checkNotNullParameter(priority, "priority");
        this.customerType = customerType;
        this.customerId = customerId;
        this.visitDate = visitDate;
        this.purpose = purpose;
        this.priority = priority;
    }

    public /* synthetic */ AddVisitRequest(String str, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5);
    }

    public final String getCustomerType() {
        return this.customerType;
    }

    public final void setCustomerType(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.customerType = str;
    }

    public final String getCustomerId() {
        return this.customerId;
    }

    public final void setCustomerId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.customerId = str;
    }

    public final String getVisitDate() {
        return this.visitDate;
    }

    public final void setVisitDate(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.visitDate = str;
    }

    public final String getPurpose() {
        return this.purpose;
    }

    public final void setPurpose(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.purpose = str;
    }

    public final String getPriority() {
        return this.priority;
    }

    public final void setPriority(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.priority = str;
    }
}
