package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Pagination.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ>\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\"\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\"\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\u0010\u0010\n\"\u0004\b\u0011\u0010\fR\"\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\r\u001a\u0004\b\u0012\u0010\n\"\u0004\b\u0013\u0010\f¨\u0006 "}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/Pagination;", "", "currentPage", "", "lastPage", "perPage", "total", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getCurrentPage", "()Ljava/lang/Integer;", "setCurrentPage", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getLastPage", "setLastPage", "getPerPage", "setPerPage", "getTotal", "setTotal", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/Pagination;", "equals", "", "other", "hashCode", "toString", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class Pagination {

    @SerializedName("current_page")
    private Integer currentPage;

    @SerializedName("last_page")
    private Integer lastPage;

    @SerializedName("per_page")
    private Integer perPage;

    @SerializedName("total")
    private Integer total;

    public Pagination() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ Pagination copy$default(Pagination pagination, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
        if ((i & 1) != 0) {
            num = pagination.currentPage;
        }
        if ((i & 2) != 0) {
            num2 = pagination.lastPage;
        }
        if ((i & 4) != 0) {
            num3 = pagination.perPage;
        }
        if ((i & 8) != 0) {
            num4 = pagination.total;
        }
        return pagination.copy(num, num2, num3, num4);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getCurrentPage() {
        return this.currentPage;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getLastPage() {
        return this.lastPage;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getPerPage() {
        return this.perPage;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getTotal() {
        return this.total;
    }

    public final Pagination copy(Integer currentPage, Integer lastPage, Integer perPage, Integer total) {
        return new Pagination(currentPage, lastPage, perPage, total);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Pagination)) {
            return false;
        }
        Pagination pagination = (Pagination) other;
        return Intrinsics.areEqual(this.currentPage, pagination.currentPage) && Intrinsics.areEqual(this.lastPage, pagination.lastPage) && Intrinsics.areEqual(this.perPage, pagination.perPage) && Intrinsics.areEqual(this.total, pagination.total);
    }

    public int hashCode() {
        return ((((((this.currentPage == null ? 0 : this.currentPage.hashCode()) * 31) + (this.lastPage == null ? 0 : this.lastPage.hashCode())) * 31) + (this.perPage == null ? 0 : this.perPage.hashCode())) * 31) + (this.total != null ? this.total.hashCode() : 0);
    }

    public String toString() {
        return "Pagination(currentPage=" + this.currentPage + ", lastPage=" + this.lastPage + ", perPage=" + this.perPage + ", total=" + this.total + ")";
    }

    public Pagination(Integer currentPage, Integer lastPage, Integer perPage, Integer total) {
        this.currentPage = currentPage;
        this.lastPage = lastPage;
        this.perPage = perPage;
        this.total = total;
    }

    public /* synthetic */ Pagination(Integer num, Integer num2, Integer num3, Integer num4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : num3, (i & 8) != 0 ? null : num4);
    }

    public final Integer getCurrentPage() {
        return this.currentPage;
    }

    public final void setCurrentPage(Integer num) {
        this.currentPage = num;
    }

    public final Integer getLastPage() {
        return this.lastPage;
    }

    public final void setLastPage(Integer num) {
        this.lastPage = num;
    }

    public final Integer getPerPage() {
        return this.perPage;
    }

    public final void setPerPage(Integer num) {
        this.perPage = num;
    }

    public final Integer getTotal() {
        return this.total;
    }

    public final void setTotal(Integer num) {
        this.total = num;
    }
}
