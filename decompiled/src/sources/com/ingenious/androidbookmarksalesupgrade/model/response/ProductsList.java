package com.ingenious.androidbookmarksalesupgrade.model.response;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProductsList.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\bH\b\u0086\b\u0018\u00002\u00020\u0001Bß\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000b\u0010D\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010O\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000b\u0010P\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010Q\u001a\u0004\u0018\u00010\u0013HÆ\u0003¢\u0006\u0002\u0010:J\u000b\u0010R\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010S\u001a\u0004\u0018\u00010\u0013HÆ\u0003¢\u0006\u0002\u0010:J\u000b\u0010T\u001a\u0004\u0018\u00010\u0005HÆ\u0003Jæ\u0001\u0010U\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010VJ\u0013\u0010W\u001a\u00020\u00132\b\u0010X\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010Y\u001a\u00020\u0003HÖ\u0001J\t\u0010Z\u001a\u00020\u0005HÖ\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001f\"\u0004\b#\u0010!R \u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001f\"\u0004\b%\u0010!R \u0010\b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001f\"\u0004\b'\u0010!R \u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001f\"\u0004\b)\u0010!R \u0010\n\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001f\"\u0004\b+\u0010!R \u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u001f\"\u0004\b-\u0010!R \u0010\f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001f\"\u0004\b/\u0010!R \u0010\r\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001f\"\u0004\b1\u0010!R \u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u001f\"\u0004\b3\u0010!R \u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u001f\"\u0004\b5\u0010!R\"\u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b6\u0010\u001a\"\u0004\b7\u0010\u001cR \u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u001f\"\u0004\b9\u0010!R\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u0010\n\u0002\u0010=\u001a\u0004\b\u0012\u0010:\"\u0004\b;\u0010<R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u001f\"\u0004\b?\u0010!R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u0010\n\u0002\u0010=\u001a\u0004\b\u0015\u0010:\"\u0004\b@\u0010<R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u001f\"\u0004\bB\u0010!¨\u0006["}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/ProductsList;", "", Constant.VISIT_ID, "", "title", "", "description", "image", "companyPrice", "companyPriceForDisplay", "brand", "series", "subject", "stockAvailable", "stockAvailablee", FirebaseAnalytics.Param.PRICE, "totalAmount", FirebaseAnalytics.Param.QUANTITY, "isSelected", "", "updatedPrice", "isBottom", "updateQuantity", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "getDescription", "setDescription", "getImage", "setImage", "getCompanyPrice", "setCompanyPrice", "getCompanyPriceForDisplay", "setCompanyPriceForDisplay", "getBrand", "setBrand", "getSeries", "setSeries", "getSubject", "setSubject", "getStockAvailable", "setStockAvailable", "getStockAvailablee", "setStockAvailablee", "getPrice", "setPrice", "getTotalAmount", "setTotalAmount", "getQuantity", "setQuantity", "()Ljava/lang/Boolean;", "setSelected", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getUpdatedPrice", "setUpdatedPrice", "setBottom", "getUpdateQuantity", "setUpdateQuantity", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/ProductsList;", "equals", "other", "hashCode", "toString", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class ProductsList {

    @SerializedName("brand")
    private String brand;

    @SerializedName("companyPrice")
    private String companyPrice;

    @SerializedName("companyPriceForDisplay")
    private String companyPriceForDisplay;

    @SerializedName("description")
    private String description;

    @SerializedName(Constant.VISIT_ID)
    private Integer id;

    @SerializedName("image")
    private String image;
    private Boolean isBottom;
    private Boolean isSelected;

    @SerializedName(FirebaseAnalytics.Param.PRICE)
    private String price;

    @SerializedName(FirebaseAnalytics.Param.QUANTITY)
    private String quantity;

    @SerializedName("series")
    private String series;

    @SerializedName("stockAvailable")
    private String stockAvailable;

    @SerializedName("stock_available")
    private String stockAvailablee;

    @SerializedName("subject")
    private String subject;

    @SerializedName("title")
    private String title;

    @SerializedName("total_amount")
    private Integer totalAmount;
    private String updateQuantity;
    private String updatedPrice;

    public ProductsList() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 262143, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getStockAvailable() {
        return this.stockAvailable;
    }

    /* renamed from: component11, reason: from getter */
    public final String getStockAvailablee() {
        return this.stockAvailablee;
    }

    /* renamed from: component12, reason: from getter */
    public final String getPrice() {
        return this.price;
    }

    /* renamed from: component13, reason: from getter */
    public final Integer getTotalAmount() {
        return this.totalAmount;
    }

    /* renamed from: component14, reason: from getter */
    public final String getQuantity() {
        return this.quantity;
    }

    /* renamed from: component15, reason: from getter */
    public final Boolean getIsSelected() {
        return this.isSelected;
    }

    /* renamed from: component16, reason: from getter */
    public final String getUpdatedPrice() {
        return this.updatedPrice;
    }

    /* renamed from: component17, reason: from getter */
    public final Boolean getIsBottom() {
        return this.isBottom;
    }

    /* renamed from: component18, reason: from getter */
    public final String getUpdateQuantity() {
        return this.updateQuantity;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* renamed from: component4, reason: from getter */
    public final String getImage() {
        return this.image;
    }

    /* renamed from: component5, reason: from getter */
    public final String getCompanyPrice() {
        return this.companyPrice;
    }

    /* renamed from: component6, reason: from getter */
    public final String getCompanyPriceForDisplay() {
        return this.companyPriceForDisplay;
    }

    /* renamed from: component7, reason: from getter */
    public final String getBrand() {
        return this.brand;
    }

    /* renamed from: component8, reason: from getter */
    public final String getSeries() {
        return this.series;
    }

    /* renamed from: component9, reason: from getter */
    public final String getSubject() {
        return this.subject;
    }

    public final ProductsList copy(Integer id, String title, String description, String image, String companyPrice, String companyPriceForDisplay, String brand, String series, String subject, String stockAvailable, String stockAvailablee, String price, Integer totalAmount, String quantity, Boolean isSelected, String updatedPrice, Boolean isBottom, String updateQuantity) {
        return new ProductsList(id, title, description, image, companyPrice, companyPriceForDisplay, brand, series, subject, stockAvailable, stockAvailablee, price, totalAmount, quantity, isSelected, updatedPrice, isBottom, updateQuantity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProductsList)) {
            return false;
        }
        ProductsList productsList = (ProductsList) other;
        return Intrinsics.areEqual(this.id, productsList.id) && Intrinsics.areEqual(this.title, productsList.title) && Intrinsics.areEqual(this.description, productsList.description) && Intrinsics.areEqual(this.image, productsList.image) && Intrinsics.areEqual(this.companyPrice, productsList.companyPrice) && Intrinsics.areEqual(this.companyPriceForDisplay, productsList.companyPriceForDisplay) && Intrinsics.areEqual(this.brand, productsList.brand) && Intrinsics.areEqual(this.series, productsList.series) && Intrinsics.areEqual(this.subject, productsList.subject) && Intrinsics.areEqual(this.stockAvailable, productsList.stockAvailable) && Intrinsics.areEqual(this.stockAvailablee, productsList.stockAvailablee) && Intrinsics.areEqual(this.price, productsList.price) && Intrinsics.areEqual(this.totalAmount, productsList.totalAmount) && Intrinsics.areEqual(this.quantity, productsList.quantity) && Intrinsics.areEqual(this.isSelected, productsList.isSelected) && Intrinsics.areEqual(this.updatedPrice, productsList.updatedPrice) && Intrinsics.areEqual(this.isBottom, productsList.isBottom) && Intrinsics.areEqual(this.updateQuantity, productsList.updateQuantity);
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.image == null ? 0 : this.image.hashCode())) * 31) + (this.companyPrice == null ? 0 : this.companyPrice.hashCode())) * 31) + (this.companyPriceForDisplay == null ? 0 : this.companyPriceForDisplay.hashCode())) * 31) + (this.brand == null ? 0 : this.brand.hashCode())) * 31) + (this.series == null ? 0 : this.series.hashCode())) * 31) + (this.subject == null ? 0 : this.subject.hashCode())) * 31) + (this.stockAvailable == null ? 0 : this.stockAvailable.hashCode())) * 31) + (this.stockAvailablee == null ? 0 : this.stockAvailablee.hashCode())) * 31) + (this.price == null ? 0 : this.price.hashCode())) * 31) + (this.totalAmount == null ? 0 : this.totalAmount.hashCode())) * 31) + (this.quantity == null ? 0 : this.quantity.hashCode())) * 31) + (this.isSelected == null ? 0 : this.isSelected.hashCode())) * 31) + (this.updatedPrice == null ? 0 : this.updatedPrice.hashCode())) * 31) + (this.isBottom == null ? 0 : this.isBottom.hashCode())) * 31) + (this.updateQuantity != null ? this.updateQuantity.hashCode() : 0);
    }

    public String toString() {
        return "ProductsList(id=" + this.id + ", title=" + this.title + ", description=" + this.description + ", image=" + this.image + ", companyPrice=" + this.companyPrice + ", companyPriceForDisplay=" + this.companyPriceForDisplay + ", brand=" + this.brand + ", series=" + this.series + ", subject=" + this.subject + ", stockAvailable=" + this.stockAvailable + ", stockAvailablee=" + this.stockAvailablee + ", price=" + this.price + ", totalAmount=" + this.totalAmount + ", quantity=" + this.quantity + ", isSelected=" + this.isSelected + ", updatedPrice=" + this.updatedPrice + ", isBottom=" + this.isBottom + ", updateQuantity=" + this.updateQuantity + ")";
    }

    public ProductsList(Integer id, String title, String description, String image, String companyPrice, String companyPriceForDisplay, String brand, String series, String subject, String stockAvailable, String stockAvailablee, String price, Integer totalAmount, String quantity, Boolean isSelected, String updatedPrice, Boolean isBottom, String updateQuantity) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.companyPrice = companyPrice;
        this.companyPriceForDisplay = companyPriceForDisplay;
        this.brand = brand;
        this.series = series;
        this.subject = subject;
        this.stockAvailable = stockAvailable;
        this.stockAvailablee = stockAvailablee;
        this.price = price;
        this.totalAmount = totalAmount;
        this.quantity = quantity;
        this.isSelected = isSelected;
        this.updatedPrice = updatedPrice;
        this.isBottom = isBottom;
        this.updateQuantity = updateQuantity;
    }

    public /* synthetic */ ProductsList(Integer num, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, Integer num2, String str12, Boolean bool, String str13, Boolean bool2, String str14, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : str5, (i & 64) != 0 ? null : str6, (i & 128) != 0 ? null : str7, (i & 256) != 0 ? null : str8, (i & 512) != 0 ? null : str9, (i & 1024) != 0 ? null : str10, (i & 2048) != 0 ? null : str11, (i & 4096) != 0 ? null : num2, (i & 8192) == 0 ? str12 : null, (i & 16384) != 0 ? false : bool, (i & 32768) != 0 ? "" : str13, (i & 65536) != 0 ? false : bool2, (i & 131072) == 0 ? str14 : "");
    }

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(String str) {
        this.description = str;
    }

    public final String getImage() {
        return this.image;
    }

    public final void setImage(String str) {
        this.image = str;
    }

    public final String getCompanyPrice() {
        return this.companyPrice;
    }

    public final void setCompanyPrice(String str) {
        this.companyPrice = str;
    }

    public final String getCompanyPriceForDisplay() {
        return this.companyPriceForDisplay;
    }

    public final void setCompanyPriceForDisplay(String str) {
        this.companyPriceForDisplay = str;
    }

    public final String getBrand() {
        return this.brand;
    }

    public final void setBrand(String str) {
        this.brand = str;
    }

    public final String getSeries() {
        return this.series;
    }

    public final void setSeries(String str) {
        this.series = str;
    }

    public final String getSubject() {
        return this.subject;
    }

    public final void setSubject(String str) {
        this.subject = str;
    }

    public final String getStockAvailable() {
        return this.stockAvailable;
    }

    public final void setStockAvailable(String str) {
        this.stockAvailable = str;
    }

    public final String getStockAvailablee() {
        return this.stockAvailablee;
    }

    public final void setStockAvailablee(String str) {
        this.stockAvailablee = str;
    }

    public final String getPrice() {
        return this.price;
    }

    public final void setPrice(String str) {
        this.price = str;
    }

    public final Integer getTotalAmount() {
        return this.totalAmount;
    }

    public final void setTotalAmount(Integer num) {
        this.totalAmount = num;
    }

    public final String getQuantity() {
        return this.quantity;
    }

    public final void setQuantity(String str) {
        this.quantity = str;
    }

    public final Boolean isSelected() {
        return this.isSelected;
    }

    public final void setSelected(Boolean bool) {
        this.isSelected = bool;
    }

    public final String getUpdatedPrice() {
        return this.updatedPrice;
    }

    public final void setUpdatedPrice(String str) {
        this.updatedPrice = str;
    }

    public final Boolean isBottom() {
        return this.isBottom;
    }

    public final void setBottom(Boolean bool) {
        this.isBottom = bool;
    }

    public final String getUpdateQuantity() {
        return this.updateQuantity;
    }

    public final void setUpdateQuantity(String str) {
        this.updateQuantity = str;
    }
}
