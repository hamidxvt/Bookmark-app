package com.ingenious.androidbookmarksalesupgrade.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SampleBookModel.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/MonthModel;", "", "month", "", "products", "", "Lcom/ingenious/androidbookmarksalesupgrade/model/SampleBookModel;", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "getMonth", "()Ljava/lang/String;", "getProducts", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes8.dex */
public final /* data */ class MonthModel {
    private final String month;
    private final List<SampleBookModel> products;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MonthModel copy$default(MonthModel monthModel, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = monthModel.month;
        }
        if ((i & 2) != 0) {
            list = monthModel.products;
        }
        return monthModel.copy(str, list);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMonth() {
        return this.month;
    }

    public final List<SampleBookModel> component2() {
        return this.products;
    }

    public final MonthModel copy(String month, List<SampleBookModel> products) {
        Intrinsics.checkNotNullParameter(month, "month");
        Intrinsics.checkNotNullParameter(products, "products");
        return new MonthModel(month, products);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MonthModel)) {
            return false;
        }
        MonthModel monthModel = (MonthModel) other;
        return Intrinsics.areEqual(this.month, monthModel.month) && Intrinsics.areEqual(this.products, monthModel.products);
    }

    public int hashCode() {
        return (this.month.hashCode() * 31) + this.products.hashCode();
    }

    public String toString() {
        return "MonthModel(month=" + this.month + ", products=" + this.products + ")";
    }

    public MonthModel(String month, List<SampleBookModel> products) {
        Intrinsics.checkNotNullParameter(month, "month");
        Intrinsics.checkNotNullParameter(products, "products");
        this.month = month;
        this.products = products;
    }

    public final String getMonth() {
        return this.month;
    }

    public final List<SampleBookModel> getProducts() {
        return this.products;
    }
}
