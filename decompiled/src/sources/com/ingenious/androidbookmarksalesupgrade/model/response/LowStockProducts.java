package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LowStockProducts.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\"\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010#\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010$\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010%\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001cJV\u0010&\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010'J\u0013\u0010(\u001a\u00020\n2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020\u0007HÖ\u0001J\t\u0010+\u001a\u00020\u0003HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R\"\u0010\t\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\t\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006,"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/LowStockProducts;", "", "productName", "", FirebaseAnalytics.Param.PRICE, "image", FirebaseAnalytics.Param.QUANTITY, "", "stockAvailable", "isSelected", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;)V", "getProductName", "()Ljava/lang/String;", "setProductName", "(Ljava/lang/String;)V", "getPrice", "setPrice", "getImage", "setImage", "getQuantity", "()Ljava/lang/Integer;", "setQuantity", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getStockAvailable", "setStockAvailable", "()Ljava/lang/Boolean;", "setSelected", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/LowStockProducts;", "equals", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class LowStockProducts {

    @SerializedName("image")
    private String image;

    @SerializedName("isSelected")
    private Boolean isSelected;

    @SerializedName(FirebaseAnalytics.Param.PRICE)
    private String price;

    @SerializedName("product_name")
    private String productName;

    @SerializedName(FirebaseAnalytics.Param.QUANTITY)
    private Integer quantity;

    @SerializedName("stock_available")
    private Integer stockAvailable;

    public LowStockProducts() {
        this(null, null, null, null, null, null, 63, null);
    }

    public static /* synthetic */ LowStockProducts copy$default(LowStockProducts lowStockProducts, String str, String str2, String str3, Integer num, Integer num2, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = lowStockProducts.productName;
        }
        if ((i & 2) != 0) {
            str2 = lowStockProducts.price;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = lowStockProducts.image;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            num = lowStockProducts.quantity;
        }
        Integer num3 = num;
        if ((i & 16) != 0) {
            num2 = lowStockProducts.stockAvailable;
        }
        Integer num4 = num2;
        if ((i & 32) != 0) {
            bool = lowStockProducts.isSelected;
        }
        return lowStockProducts.copy(str, str4, str5, num3, num4, bool);
    }

    /* renamed from: component1, reason: from getter */
    public final String getProductName() {
        return this.productName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPrice() {
        return this.price;
    }

    /* renamed from: component3, reason: from getter */
    public final String getImage() {
        return this.image;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getQuantity() {
        return this.quantity;
    }

    /* renamed from: component5, reason: from getter */
    public final Integer getStockAvailable() {
        return this.stockAvailable;
    }

    /* renamed from: component6, reason: from getter */
    public final Boolean getIsSelected() {
        return this.isSelected;
    }

    public final LowStockProducts copy(String productName, String price, String image, Integer quantity, Integer stockAvailable, Boolean isSelected) {
        return new LowStockProducts(productName, price, image, quantity, stockAvailable, isSelected);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LowStockProducts)) {
            return false;
        }
        LowStockProducts lowStockProducts = (LowStockProducts) other;
        return Intrinsics.areEqual(this.productName, lowStockProducts.productName) && Intrinsics.areEqual(this.price, lowStockProducts.price) && Intrinsics.areEqual(this.image, lowStockProducts.image) && Intrinsics.areEqual(this.quantity, lowStockProducts.quantity) && Intrinsics.areEqual(this.stockAvailable, lowStockProducts.stockAvailable) && Intrinsics.areEqual(this.isSelected, lowStockProducts.isSelected);
    }

    public int hashCode() {
        return ((((((((((this.productName == null ? 0 : this.productName.hashCode()) * 31) + (this.price == null ? 0 : this.price.hashCode())) * 31) + (this.image == null ? 0 : this.image.hashCode())) * 31) + (this.quantity == null ? 0 : this.quantity.hashCode())) * 31) + (this.stockAvailable == null ? 0 : this.stockAvailable.hashCode())) * 31) + (this.isSelected != null ? this.isSelected.hashCode() : 0);
    }

    public String toString() {
        return "LowStockProducts(productName=" + this.productName + ", price=" + this.price + ", image=" + this.image + ", quantity=" + this.quantity + ", stockAvailable=" + this.stockAvailable + ", isSelected=" + this.isSelected + ")";
    }

    public LowStockProducts(String productName, String price, String image, Integer quantity, Integer stockAvailable, Boolean isSelected) {
        this.productName = productName;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.stockAvailable = stockAvailable;
        this.isSelected = isSelected;
    }

    public /* synthetic */ LowStockProducts(String str, String str2, String str3, Integer num, Integer num2, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : num2, (i & 32) != 0 ? null : bool);
    }

    public final String getProductName() {
        return this.productName;
    }

    public final void setProductName(String str) {
        this.productName = str;
    }

    public final String getPrice() {
        return this.price;
    }

    public final void setPrice(String str) {
        this.price = str;
    }

    public final String getImage() {
        return this.image;
    }

    public final void setImage(String str) {
        this.image = str;
    }

    public final Integer getQuantity() {
        return this.quantity;
    }

    public final void setQuantity(Integer num) {
        this.quantity = num;
    }

    public final Integer getStockAvailable() {
        return this.stockAvailable;
    }

    public final void setStockAvailable(Integer num) {
        this.stockAvailable = num;
    }

    public final Boolean isSelected() {
        return this.isSelected;
    }

    public final void setSelected(Boolean bool) {
        this.isSelected = bool;
    }
}
