package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TodayRecommendedBookerProducts.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003JJ\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0005HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R \u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR \u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\f\"\u0004\b\u0019\u0010\u000e¨\u0006&"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/TodayRecommendedBookerProducts;", "", "productName", "", "quantityAssigned", "", "stockAvailable", FirebaseAnalytics.Param.PRICE, "image", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "getProductName", "()Ljava/lang/String;", "setProductName", "(Ljava/lang/String;)V", "getQuantityAssigned", "()Ljava/lang/Integer;", "setQuantityAssigned", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getStockAvailable", "setStockAvailable", "getPrice", "setPrice", "getImage", "setImage", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/TodayRecommendedBookerProducts;", "equals", "", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class TodayRecommendedBookerProducts {

    @SerializedName("image")
    private String image;

    @SerializedName(FirebaseAnalytics.Param.PRICE)
    private String price;

    @SerializedName("product_name")
    private String productName;

    @SerializedName("quantity_assigned")
    private Integer quantityAssigned;

    @SerializedName("stock_available")
    private Integer stockAvailable;

    public TodayRecommendedBookerProducts() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ TodayRecommendedBookerProducts copy$default(TodayRecommendedBookerProducts todayRecommendedBookerProducts, String str, Integer num, Integer num2, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = todayRecommendedBookerProducts.productName;
        }
        if ((i & 2) != 0) {
            num = todayRecommendedBookerProducts.quantityAssigned;
        }
        Integer num3 = num;
        if ((i & 4) != 0) {
            num2 = todayRecommendedBookerProducts.stockAvailable;
        }
        Integer num4 = num2;
        if ((i & 8) != 0) {
            str2 = todayRecommendedBookerProducts.price;
        }
        String str4 = str2;
        if ((i & 16) != 0) {
            str3 = todayRecommendedBookerProducts.image;
        }
        return todayRecommendedBookerProducts.copy(str, num3, num4, str4, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getProductName() {
        return this.productName;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getQuantityAssigned() {
        return this.quantityAssigned;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getStockAvailable() {
        return this.stockAvailable;
    }

    /* renamed from: component4, reason: from getter */
    public final String getPrice() {
        return this.price;
    }

    /* renamed from: component5, reason: from getter */
    public final String getImage() {
        return this.image;
    }

    public final TodayRecommendedBookerProducts copy(String productName, Integer quantityAssigned, Integer stockAvailable, String price, String image) {
        return new TodayRecommendedBookerProducts(productName, quantityAssigned, stockAvailable, price, image);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TodayRecommendedBookerProducts)) {
            return false;
        }
        TodayRecommendedBookerProducts todayRecommendedBookerProducts = (TodayRecommendedBookerProducts) other;
        return Intrinsics.areEqual(this.productName, todayRecommendedBookerProducts.productName) && Intrinsics.areEqual(this.quantityAssigned, todayRecommendedBookerProducts.quantityAssigned) && Intrinsics.areEqual(this.stockAvailable, todayRecommendedBookerProducts.stockAvailable) && Intrinsics.areEqual(this.price, todayRecommendedBookerProducts.price) && Intrinsics.areEqual(this.image, todayRecommendedBookerProducts.image);
    }

    public int hashCode() {
        return ((((((((this.productName == null ? 0 : this.productName.hashCode()) * 31) + (this.quantityAssigned == null ? 0 : this.quantityAssigned.hashCode())) * 31) + (this.stockAvailable == null ? 0 : this.stockAvailable.hashCode())) * 31) + (this.price == null ? 0 : this.price.hashCode())) * 31) + (this.image != null ? this.image.hashCode() : 0);
    }

    public String toString() {
        return "TodayRecommendedBookerProducts(productName=" + this.productName + ", quantityAssigned=" + this.quantityAssigned + ", stockAvailable=" + this.stockAvailable + ", price=" + this.price + ", image=" + this.image + ")";
    }

    public TodayRecommendedBookerProducts(String productName, Integer quantityAssigned, Integer stockAvailable, String price, String image) {
        this.productName = productName;
        this.quantityAssigned = quantityAssigned;
        this.stockAvailable = stockAvailable;
        this.price = price;
        this.image = image;
    }

    public /* synthetic */ TodayRecommendedBookerProducts(String str, Integer num, Integer num2, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3);
    }

    public final String getProductName() {
        return this.productName;
    }

    public final void setProductName(String str) {
        this.productName = str;
    }

    public final Integer getQuantityAssigned() {
        return this.quantityAssigned;
    }

    public final void setQuantityAssigned(Integer num) {
        this.quantityAssigned = num;
    }

    public final Integer getStockAvailable() {
        return this.stockAvailable;
    }

    public final void setStockAvailable(Integer num) {
        this.stockAvailable = num;
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
}
