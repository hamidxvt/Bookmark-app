package com.ingenious.androidbookmarksalesupgrade.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.ingenious.androidbookmarksalesupgrade.model.response.Products;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ActivityLog.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\n\u0010\u000bJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000fJB\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0013\u0010\u000f¨\u0006\u001f"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/DeliveredBooksData;", "", "visit_id", "", "booker_id", "", "products", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "grand_total", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Integer;)V", "getVisit_id", "()Ljava/lang/String;", "getBooker_id", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getProducts", "()Ljava/util/List;", "getGrand_total", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Integer;)Lcom/ingenious/androidbookmarksalesupgrade/model/DeliveredBooksData;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes8.dex */
public final /* data */ class DeliveredBooksData {
    private final Integer booker_id;
    private final Integer grand_total;
    private final List<Products> products;
    private final String visit_id;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DeliveredBooksData copy$default(DeliveredBooksData deliveredBooksData, String str, Integer num, List list, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deliveredBooksData.visit_id;
        }
        if ((i & 2) != 0) {
            num = deliveredBooksData.booker_id;
        }
        if ((i & 4) != 0) {
            list = deliveredBooksData.products;
        }
        if ((i & 8) != 0) {
            num2 = deliveredBooksData.grand_total;
        }
        return deliveredBooksData.copy(str, num, list, num2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getVisit_id() {
        return this.visit_id;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getBooker_id() {
        return this.booker_id;
    }

    public final List<Products> component3() {
        return this.products;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getGrand_total() {
        return this.grand_total;
    }

    public final DeliveredBooksData copy(String visit_id, Integer booker_id, List<Products> products, Integer grand_total) {
        Intrinsics.checkNotNullParameter(products, "products");
        return new DeliveredBooksData(visit_id, booker_id, products, grand_total);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeliveredBooksData)) {
            return false;
        }
        DeliveredBooksData deliveredBooksData = (DeliveredBooksData) other;
        return Intrinsics.areEqual(this.visit_id, deliveredBooksData.visit_id) && Intrinsics.areEqual(this.booker_id, deliveredBooksData.booker_id) && Intrinsics.areEqual(this.products, deliveredBooksData.products) && Intrinsics.areEqual(this.grand_total, deliveredBooksData.grand_total);
    }

    public int hashCode() {
        return ((((((this.visit_id == null ? 0 : this.visit_id.hashCode()) * 31) + (this.booker_id == null ? 0 : this.booker_id.hashCode())) * 31) + this.products.hashCode()) * 31) + (this.grand_total != null ? this.grand_total.hashCode() : 0);
    }

    public String toString() {
        return "DeliveredBooksData(visit_id=" + this.visit_id + ", booker_id=" + this.booker_id + ", products=" + this.products + ", grand_total=" + this.grand_total + ")";
    }

    public DeliveredBooksData(String visit_id, Integer booker_id, List<Products> products, Integer grand_total) {
        Intrinsics.checkNotNullParameter(products, "products");
        this.visit_id = visit_id;
        this.booker_id = booker_id;
        this.products = products;
        this.grand_total = grand_total;
    }

    public /* synthetic */ DeliveredBooksData(String str, Integer num, List list, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, list, (i & 8) != 0 ? null : num2);
    }

    public final String getVisit_id() {
        return this.visit_id;
    }

    public final Integer getBooker_id() {
        return this.booker_id;
    }

    public final List<Products> getProducts() {
        return this.products;
    }

    public final Integer getGrand_total() {
        return this.grand_total;
    }
}
