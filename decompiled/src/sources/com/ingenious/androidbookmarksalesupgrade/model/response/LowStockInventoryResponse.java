package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LowStockInventoryResponse.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0003J'\u0010\f\u001a\u00020\u00002\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R2\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0007¨\u0006\u0015"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/LowStockInventoryResponse;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/GlobalResponse;", "lowStockProducts", "Ljava/util/ArrayList;", "Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "Lkotlin/collections/ArrayList;", "<init>", "(Ljava/util/ArrayList;)V", "getLowStockProducts", "()Ljava/util/ArrayList;", "setLowStockProducts", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class LowStockInventoryResponse extends GlobalResponse {

    @SerializedName("low_stock_products")
    private ArrayList<Products> lowStockProducts;

    /* JADX WARN: Multi-variable type inference failed */
    public LowStockInventoryResponse() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LowStockInventoryResponse copy$default(LowStockInventoryResponse lowStockInventoryResponse, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = lowStockInventoryResponse.lowStockProducts;
        }
        return lowStockInventoryResponse.copy(arrayList);
    }

    public final ArrayList<Products> component1() {
        return this.lowStockProducts;
    }

    public final LowStockInventoryResponse copy(ArrayList<Products> lowStockProducts) {
        return new LowStockInventoryResponse(lowStockProducts);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LowStockInventoryResponse) && Intrinsics.areEqual(this.lowStockProducts, ((LowStockInventoryResponse) other).lowStockProducts);
    }

    public int hashCode() {
        if (this.lowStockProducts == null) {
            return 0;
        }
        return this.lowStockProducts.hashCode();
    }

    public String toString() {
        return "LowStockInventoryResponse(lowStockProducts=" + this.lowStockProducts + ")";
    }

    public /* synthetic */ LowStockInventoryResponse(ArrayList arrayList, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : arrayList);
    }

    public final ArrayList<Products> getLowStockProducts() {
        return this.lowStockProducts;
    }

    public final void setLowStockProducts(ArrayList<Products> arrayList) {
        this.lowStockProducts = arrayList;
    }

    public LowStockInventoryResponse(ArrayList<Products> arrayList) {
        super(null, null, null, 7, null);
        this.lowStockProducts = arrayList;
    }
}
