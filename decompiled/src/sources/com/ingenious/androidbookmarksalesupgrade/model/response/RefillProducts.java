package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RefillProducts.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/RefillProducts;", "", "productId", "", FirebaseAnalytics.Param.QUANTITY, "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getProductId", "()Ljava/lang/String;", "setProductId", "(Ljava/lang/String;)V", "getQuantity", "setQuantity", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class RefillProducts {

    @SerializedName("product_id")
    private String productId;

    @SerializedName(FirebaseAnalytics.Param.QUANTITY)
    private String quantity;

    /* JADX WARN: Multi-variable type inference failed */
    public RefillProducts() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ RefillProducts copy$default(RefillProducts refillProducts, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = refillProducts.productId;
        }
        if ((i & 2) != 0) {
            str2 = refillProducts.quantity;
        }
        return refillProducts.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getProductId() {
        return this.productId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getQuantity() {
        return this.quantity;
    }

    public final RefillProducts copy(String productId, String quantity) {
        return new RefillProducts(productId, quantity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RefillProducts)) {
            return false;
        }
        RefillProducts refillProducts = (RefillProducts) other;
        return Intrinsics.areEqual(this.productId, refillProducts.productId) && Intrinsics.areEqual(this.quantity, refillProducts.quantity);
    }

    public int hashCode() {
        return ((this.productId == null ? 0 : this.productId.hashCode()) * 31) + (this.quantity != null ? this.quantity.hashCode() : 0);
    }

    public String toString() {
        return "RefillProducts(productId=" + this.productId + ", quantity=" + this.quantity + ")";
    }

    public RefillProducts(String productId, String quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public /* synthetic */ RefillProducts(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
    }

    public final String getProductId() {
        return this.productId;
    }

    public final void setProductId(String str) {
        this.productId = str;
    }

    public final String getQuantity() {
        return this.quantity;
    }

    public final void setQuantity(String str) {
        this.quantity = str;
    }
}
