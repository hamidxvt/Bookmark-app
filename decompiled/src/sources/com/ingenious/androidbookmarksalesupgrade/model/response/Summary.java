package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Summary.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ2\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\"\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/Summary;", "", "totalCustomers", "", "totalSchools", "totalBookshops", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getTotalCustomers", "()Ljava/lang/Integer;", "setTotalCustomers", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getTotalSchools", "setTotalSchools", "getTotalBookshops", "setTotalBookshops", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/Summary;", "equals", "", "other", "hashCode", "toString", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class Summary {

    @SerializedName("total_bookshops")
    private Integer totalBookshops;

    @SerializedName("total_customers")
    private Integer totalCustomers;

    @SerializedName("total_schools")
    private Integer totalSchools;

    public Summary() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ Summary copy$default(Summary summary, Integer num, Integer num2, Integer num3, int i, Object obj) {
        if ((i & 1) != 0) {
            num = summary.totalCustomers;
        }
        if ((i & 2) != 0) {
            num2 = summary.totalSchools;
        }
        if ((i & 4) != 0) {
            num3 = summary.totalBookshops;
        }
        return summary.copy(num, num2, num3);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getTotalCustomers() {
        return this.totalCustomers;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getTotalSchools() {
        return this.totalSchools;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getTotalBookshops() {
        return this.totalBookshops;
    }

    public final Summary copy(Integer totalCustomers, Integer totalSchools, Integer totalBookshops) {
        return new Summary(totalCustomers, totalSchools, totalBookshops);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Summary)) {
            return false;
        }
        Summary summary = (Summary) other;
        return Intrinsics.areEqual(this.totalCustomers, summary.totalCustomers) && Intrinsics.areEqual(this.totalSchools, summary.totalSchools) && Intrinsics.areEqual(this.totalBookshops, summary.totalBookshops);
    }

    public int hashCode() {
        return ((((this.totalCustomers == null ? 0 : this.totalCustomers.hashCode()) * 31) + (this.totalSchools == null ? 0 : this.totalSchools.hashCode())) * 31) + (this.totalBookshops != null ? this.totalBookshops.hashCode() : 0);
    }

    public String toString() {
        return "Summary(totalCustomers=" + this.totalCustomers + ", totalSchools=" + this.totalSchools + ", totalBookshops=" + this.totalBookshops + ")";
    }

    public Summary(Integer totalCustomers, Integer totalSchools, Integer totalBookshops) {
        this.totalCustomers = totalCustomers;
        this.totalSchools = totalSchools;
        this.totalBookshops = totalBookshops;
    }

    public /* synthetic */ Summary(Integer num, Integer num2, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : num3);
    }

    public final Integer getTotalCustomers() {
        return this.totalCustomers;
    }

    public final void setTotalCustomers(Integer num) {
        this.totalCustomers = num;
    }

    public final Integer getTotalSchools() {
        return this.totalSchools;
    }

    public final void setTotalSchools(Integer num) {
        this.totalSchools = num;
    }

    public final Integer getTotalBookshops() {
        return this.totalBookshops;
    }

    public final void setTotalBookshops(Integer num) {
        this.totalBookshops = num;
    }
}
