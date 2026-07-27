package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomersListData.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/CustomersListData;", "", "summary", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Summary;", "customers", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Customers;", "<init>", "(Lcom/ingenious/androidbookmarksalesupgrade/model/response/Summary;Lcom/ingenious/androidbookmarksalesupgrade/model/response/Customers;)V", "getSummary", "()Lcom/ingenious/androidbookmarksalesupgrade/model/response/Summary;", "setSummary", "(Lcom/ingenious/androidbookmarksalesupgrade/model/response/Summary;)V", "getCustomers", "()Lcom/ingenious/androidbookmarksalesupgrade/model/response/Customers;", "setCustomers", "(Lcom/ingenious/androidbookmarksalesupgrade/model/response/Customers;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class CustomersListData {

    @SerializedName("customers")
    private Customers customers;

    @SerializedName("summary")
    private Summary summary;

    /* JADX WARN: Multi-variable type inference failed */
    public CustomersListData() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ CustomersListData copy$default(CustomersListData customersListData, Summary summary, Customers customers, int i, Object obj) {
        if ((i & 1) != 0) {
            summary = customersListData.summary;
        }
        if ((i & 2) != 0) {
            customers = customersListData.customers;
        }
        return customersListData.copy(summary, customers);
    }

    /* renamed from: component1, reason: from getter */
    public final Summary getSummary() {
        return this.summary;
    }

    /* renamed from: component2, reason: from getter */
    public final Customers getCustomers() {
        return this.customers;
    }

    public final CustomersListData copy(Summary summary, Customers customers) {
        return new CustomersListData(summary, customers);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomersListData)) {
            return false;
        }
        CustomersListData customersListData = (CustomersListData) other;
        return Intrinsics.areEqual(this.summary, customersListData.summary) && Intrinsics.areEqual(this.customers, customersListData.customers);
    }

    public int hashCode() {
        return ((this.summary == null ? 0 : this.summary.hashCode()) * 31) + (this.customers != null ? this.customers.hashCode() : 0);
    }

    public String toString() {
        return "CustomersListData(summary=" + this.summary + ", customers=" + this.customers + ")";
    }

    public CustomersListData(Summary summary, Customers customers) {
        this.summary = summary;
        this.customers = customers;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ CustomersListData(Summary summary, Customers customers, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(r0, r1);
        Summary summary2;
        Customers customers2;
        if ((i & 1) == 0) {
            summary2 = summary;
        } else {
            summary2 = new Summary(null, null, null, 7, null);
        }
        if ((i & 2) == 0) {
            customers2 = customers;
        } else {
            customers2 = new Customers(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
        }
    }

    public final Summary getSummary() {
        return this.summary;
    }

    public final void setSummary(Summary summary) {
        this.summary = summary;
    }

    public final Customers getCustomers() {
        return this.customers;
    }

    public final void setCustomers(Customers customers) {
        this.customers = customers;
    }
}
