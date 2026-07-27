package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApprovedVisitsResponse.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0019\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0003J/\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR.\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/ApprovedVisitsResponse;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", "customerName", "", "approvedVisits", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/ApprovedVisitsLists;", "Lkotlin/collections/ArrayList;", "<init>", "(Ljava/lang/String;Ljava/util/ArrayList;)V", "getCustomerName", "()Ljava/lang/String;", "setCustomerName", "(Ljava/lang/String;)V", "getApprovedVisits", "()Ljava/util/ArrayList;", "setApprovedVisits", "(Ljava/util/ArrayList;)V", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class ApprovedVisitsResponse extends GlobalResponse {

    @SerializedName("approved_visits")
    private ArrayList<ApprovedVisitsLists> approvedVisits;

    @SerializedName("customer_name")
    private String customerName;

    /* JADX WARN: Multi-variable type inference failed */
    public ApprovedVisitsResponse() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ApprovedVisitsResponse copy$default(ApprovedVisitsResponse approvedVisitsResponse, String str, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            str = approvedVisitsResponse.customerName;
        }
        if ((i & 2) != 0) {
            arrayList = approvedVisitsResponse.approvedVisits;
        }
        return approvedVisitsResponse.copy(str, arrayList);
    }

    /* renamed from: component1, reason: from getter */
    public final String getCustomerName() {
        return this.customerName;
    }

    public final ArrayList<ApprovedVisitsLists> component2() {
        return this.approvedVisits;
    }

    public final ApprovedVisitsResponse copy(String customerName, ArrayList<ApprovedVisitsLists> approvedVisits) {
        Intrinsics.checkNotNullParameter(approvedVisits, "approvedVisits");
        return new ApprovedVisitsResponse(customerName, approvedVisits);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ApprovedVisitsResponse)) {
            return false;
        }
        ApprovedVisitsResponse approvedVisitsResponse = (ApprovedVisitsResponse) other;
        return Intrinsics.areEqual(this.customerName, approvedVisitsResponse.customerName) && Intrinsics.areEqual(this.approvedVisits, approvedVisitsResponse.approvedVisits);
    }

    public int hashCode() {
        return ((this.customerName == null ? 0 : this.customerName.hashCode()) * 31) + this.approvedVisits.hashCode();
    }

    public String toString() {
        return "ApprovedVisitsResponse(customerName=" + this.customerName + ", approvedVisits=" + this.approvedVisits + ")";
    }

    public /* synthetic */ ApprovedVisitsResponse(String str, ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? new ArrayList() : arrayList);
    }

    public final String getCustomerName() {
        return this.customerName;
    }

    public final void setCustomerName(String str) {
        this.customerName = str;
    }

    public final ArrayList<ApprovedVisitsLists> getApprovedVisits() {
        return this.approvedVisits;
    }

    public final void setApprovedVisits(ArrayList<ApprovedVisitsLists> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.approvedVisits = arrayList;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ApprovedVisitsResponse(String customerName, ArrayList<ApprovedVisitsLists> approvedVisits) {
        super(null, null, null, 7, null);
        Intrinsics.checkNotNullParameter(approvedVisits, "approvedVisits");
        this.customerName = customerName;
        this.approvedVisits = approvedVisits;
    }
}
