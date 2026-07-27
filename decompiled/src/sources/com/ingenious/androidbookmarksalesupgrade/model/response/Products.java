package com.ingenious.androidbookmarksalesupgrade.model.response;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.ingenious.androidbookmarksalesupgrade.utils.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Products.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\ba\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BË\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001c\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b \u0010!J\u0010\u0010^\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010#J\u000b\u0010_\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010`\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010#J\u000b\u0010a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010i\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010#J\u000b\u0010j\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010k\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010n\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010o\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010p\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010q\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010s\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010t\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010u\u001a\u0004\u0018\u00010\u001cHÆ\u0003¢\u0006\u0002\u0010UJ\u000b\u0010v\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010w\u001a\u0004\u0018\u00010\u001cHÆ\u0003¢\u0006\u0002\u0010UJ\u000b\u0010x\u001a\u0004\u0018\u00010\u0005HÆ\u0003JÒ\u0002\u0010y\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010zJ\u0006\u0010{\u001a\u00020\u0003J\u0013\u0010|\u001a\u00020\u001c2\b\u0010}\u001a\u0004\u0018\u00010~HÖ\u0003J\t\u0010\u007f\u001a\u00020\u0003HÖ\u0001J\n\u0010\u0080\u0001\u001a\u00020\u0005HÖ\u0001J\u001b\u0010\u0081\u0001\u001a\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\u0007\u0010\u0085\u0001\u001a\u00020\u0003R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010&\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010&\u001a\u0004\b+\u0010#\"\u0004\b,\u0010%R \u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010(\"\u0004\b.\u0010*R \u0010\b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010(\"\u0004\b0\u0010*R \u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010(\"\u0004\b2\u0010*R \u0010\n\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010(\"\u0004\b4\u0010*R \u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010(\"\u0004\b6\u0010*R \u0010\f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010(\"\u0004\b8\u0010*R \u0010\r\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010(\"\u0004\b:\u0010*R \u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010(\"\u0004\b<\u0010*R\"\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010&\u001a\u0004\b=\u0010#\"\u0004\b>\u0010%R \u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010(\"\u0004\b@\u0010*R \u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010(\"\u0004\bB\u0010*R \u0010\u0012\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010(\"\u0004\bD\u0010*R \u0010\u0013\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010(\"\u0004\bF\u0010*R \u0010\u0014\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010(\"\u0004\bH\u0010*R \u0010\u0015\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010(\"\u0004\bJ\u0010*R \u0010\u0016\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010(\"\u0004\bL\u0010*R \u0010\u0017\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010(\"\u0004\bN\u0010*R \u0010\u0018\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010(\"\u0004\bP\u0010*R \u0010\u0019\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010(\"\u0004\bR\u0010*R \u0010\u001a\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010(\"\u0004\bT\u0010*R\u001e\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u0010\n\u0002\u0010X\u001a\u0004\b\u001b\u0010U\"\u0004\bV\u0010WR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010(\"\u0004\bZ\u0010*R\u001e\u0010\u001e\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u0010\n\u0002\u0010X\u001a\u0004\b\u001e\u0010U\"\u0004\b[\u0010WR\u001c\u0010\u001f\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010(\"\u0004\b]\u0010*¨\u0006\u0086\u0001"}, d2 = {"Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "Landroid/os/Parcelable;", Constant.VISIT_ID, "", "productId", "", "refillProductsId", "orderId", "customerId", "title", "description", "stockAvailable", "stockAvailablee", "image", FirebaseAnalytics.Param.PRICE, "totalAmount", FirebaseAnalytics.Param.QUANTITY, "total", "companyPrice", "priceForView", "quantityForView", "companyPriceForDisplay", "brand", "series", "subject", "productName", "grade", "isSelected", "", "updatedPrice", "isBottom", "updateQuantity", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getProductId", "()Ljava/lang/String;", "setProductId", "(Ljava/lang/String;)V", "getRefillProductsId", "setRefillProductsId", "getOrderId", "setOrderId", "getCustomerId", "setCustomerId", "getTitle", "setTitle", "getDescription", "setDescription", "getStockAvailable", "setStockAvailable", "getStockAvailablee", "setStockAvailablee", "getImage", "setImage", "getPrice", "setPrice", "getTotalAmount", "setTotalAmount", "getQuantity", "setQuantity", "getTotal", "setTotal", "getCompanyPrice", "setCompanyPrice", "getPriceForView", "setPriceForView", "getQuantityForView", "setQuantityForView", "getCompanyPriceForDisplay", "setCompanyPriceForDisplay", "getBrand", "setBrand", "getSeries", "setSeries", "getSubject", "setSubject", "getProductName", "setProductName", "getGrade", "setGrade", "()Ljava/lang/Boolean;", "setSelected", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getUpdatedPrice", "setUpdatedPrice", "setBottom", "getUpdateQuantity", "setUpdateQuantity", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/ingenious/androidbookmarksalesupgrade/model/response/Products;", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", "flags", "app_debug"}, k = 1, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes10.dex */
public final /* data */ class Products implements Parcelable {
    public static final Parcelable.Creator<Products> CREATOR = new Creator();

    @SerializedName("brand")
    private String brand;

    @SerializedName("companyPrice")
    private String companyPrice;

    @SerializedName("companyPriceForDisplay")
    private String companyPriceForDisplay;

    @SerializedName("customerId")
    private String customerId;

    @SerializedName("description")
    private String description;

    @SerializedName("grade")
    private String grade;

    @SerializedName(Constant.VISIT_ID)
    private Integer id;

    @SerializedName("image")
    private String image;
    private Boolean isBottom;
    private Boolean isSelected;

    @SerializedName("orderId")
    private String orderId;

    @SerializedName(FirebaseAnalytics.Param.PRICE)
    private String price;

    @SerializedName("priceForView")
    private String priceForView;

    @SerializedName("productId")
    private String productId;

    @SerializedName("product_name")
    private String productName;

    @SerializedName(FirebaseAnalytics.Param.QUANTITY)
    private String quantity;

    @SerializedName("quantityForView")
    private String quantityForView;

    @SerializedName("product_id")
    private Integer refillProductsId;

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

    @SerializedName("total")
    private String total;

    @SerializedName("total_amount")
    private Integer totalAmount;
    private String updateQuantity;
    private String updatedPrice;

    /* compiled from: Products.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Creator implements Parcelable.Creator<Products> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final Products createFromParcel(Parcel parcel) {
            Boolean valueOf;
            Boolean valueOf2;
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            Integer valueOf3 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            String readString = parcel.readString();
            Integer valueOf4 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            String readString5 = parcel.readString();
            String readString6 = parcel.readString();
            String readString7 = parcel.readString();
            String readString8 = parcel.readString();
            String readString9 = parcel.readString();
            Integer valueOf5 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            String readString10 = parcel.readString();
            String readString11 = parcel.readString();
            String readString12 = parcel.readString();
            String readString13 = parcel.readString();
            String readString14 = parcel.readString();
            String readString15 = parcel.readString();
            String readString16 = parcel.readString();
            String readString17 = parcel.readString();
            String readString18 = parcel.readString();
            String readString19 = parcel.readString();
            String readString20 = parcel.readString();
            if (parcel.readInt() == 0) {
                valueOf = null;
            } else {
                valueOf = Boolean.valueOf(parcel.readInt() != 0);
            }
            String readString21 = parcel.readString();
            if (parcel.readInt() == 0) {
                valueOf2 = null;
            } else {
                valueOf2 = Boolean.valueOf(parcel.readInt() != 0);
            }
            return new Products(valueOf3, readString, valueOf4, readString2, readString3, readString4, readString5, readString6, readString7, readString8, readString9, valueOf5, readString10, readString11, readString12, readString13, readString14, readString15, readString16, readString17, readString18, readString19, readString20, valueOf, readString21, valueOf2, parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final Products[] newArray(int i) {
            return new Products[i];
        }
    }

    public Products() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 134217727, null);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getImage() {
        return this.image;
    }

    /* renamed from: component11, reason: from getter */
    public final String getPrice() {
        return this.price;
    }

    /* renamed from: component12, reason: from getter */
    public final Integer getTotalAmount() {
        return this.totalAmount;
    }

    /* renamed from: component13, reason: from getter */
    public final String getQuantity() {
        return this.quantity;
    }

    /* renamed from: component14, reason: from getter */
    public final String getTotal() {
        return this.total;
    }

    /* renamed from: component15, reason: from getter */
    public final String getCompanyPrice() {
        return this.companyPrice;
    }

    /* renamed from: component16, reason: from getter */
    public final String getPriceForView() {
        return this.priceForView;
    }

    /* renamed from: component17, reason: from getter */
    public final String getQuantityForView() {
        return this.quantityForView;
    }

    /* renamed from: component18, reason: from getter */
    public final String getCompanyPriceForDisplay() {
        return this.companyPriceForDisplay;
    }

    /* renamed from: component19, reason: from getter */
    public final String getBrand() {
        return this.brand;
    }

    /* renamed from: component2, reason: from getter */
    public final String getProductId() {
        return this.productId;
    }

    /* renamed from: component20, reason: from getter */
    public final String getSeries() {
        return this.series;
    }

    /* renamed from: component21, reason: from getter */
    public final String getSubject() {
        return this.subject;
    }

    /* renamed from: component22, reason: from getter */
    public final String getProductName() {
        return this.productName;
    }

    /* renamed from: component23, reason: from getter */
    public final String getGrade() {
        return this.grade;
    }

    /* renamed from: component24, reason: from getter */
    public final Boolean getIsSelected() {
        return this.isSelected;
    }

    /* renamed from: component25, reason: from getter */
    public final String getUpdatedPrice() {
        return this.updatedPrice;
    }

    /* renamed from: component26, reason: from getter */
    public final Boolean getIsBottom() {
        return this.isBottom;
    }

    /* renamed from: component27, reason: from getter */
    public final String getUpdateQuantity() {
        return this.updateQuantity;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getRefillProductsId() {
        return this.refillProductsId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getOrderId() {
        return this.orderId;
    }

    /* renamed from: component5, reason: from getter */
    public final String getCustomerId() {
        return this.customerId;
    }

    /* renamed from: component6, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component7, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* renamed from: component8, reason: from getter */
    public final String getStockAvailable() {
        return this.stockAvailable;
    }

    /* renamed from: component9, reason: from getter */
    public final String getStockAvailablee() {
        return this.stockAvailablee;
    }

    public final Products copy(Integer id, String productId, Integer refillProductsId, String orderId, String customerId, String title, String description, String stockAvailable, String stockAvailablee, String image, String price, Integer totalAmount, String quantity, String total, String companyPrice, String priceForView, String quantityForView, String companyPriceForDisplay, String brand, String series, String subject, String productName, String grade, Boolean isSelected, String updatedPrice, Boolean isBottom, String updateQuantity) {
        return new Products(id, productId, refillProductsId, orderId, customerId, title, description, stockAvailable, stockAvailablee, image, price, totalAmount, quantity, total, companyPrice, priceForView, quantityForView, companyPriceForDisplay, brand, series, subject, productName, grade, isSelected, updatedPrice, isBottom, updateQuantity);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Products)) {
            return false;
        }
        Products products = (Products) other;
        return Intrinsics.areEqual(this.id, products.id) && Intrinsics.areEqual(this.productId, products.productId) && Intrinsics.areEqual(this.refillProductsId, products.refillProductsId) && Intrinsics.areEqual(this.orderId, products.orderId) && Intrinsics.areEqual(this.customerId, products.customerId) && Intrinsics.areEqual(this.title, products.title) && Intrinsics.areEqual(this.description, products.description) && Intrinsics.areEqual(this.stockAvailable, products.stockAvailable) && Intrinsics.areEqual(this.stockAvailablee, products.stockAvailablee) && Intrinsics.areEqual(this.image, products.image) && Intrinsics.areEqual(this.price, products.price) && Intrinsics.areEqual(this.totalAmount, products.totalAmount) && Intrinsics.areEqual(this.quantity, products.quantity) && Intrinsics.areEqual(this.total, products.total) && Intrinsics.areEqual(this.companyPrice, products.companyPrice) && Intrinsics.areEqual(this.priceForView, products.priceForView) && Intrinsics.areEqual(this.quantityForView, products.quantityForView) && Intrinsics.areEqual(this.companyPriceForDisplay, products.companyPriceForDisplay) && Intrinsics.areEqual(this.brand, products.brand) && Intrinsics.areEqual(this.series, products.series) && Intrinsics.areEqual(this.subject, products.subject) && Intrinsics.areEqual(this.productName, products.productName) && Intrinsics.areEqual(this.grade, products.grade) && Intrinsics.areEqual(this.isSelected, products.isSelected) && Intrinsics.areEqual(this.updatedPrice, products.updatedPrice) && Intrinsics.areEqual(this.isBottom, products.isBottom) && Intrinsics.areEqual(this.updateQuantity, products.updateQuantity);
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.productId == null ? 0 : this.productId.hashCode())) * 31) + (this.refillProductsId == null ? 0 : this.refillProductsId.hashCode())) * 31) + (this.orderId == null ? 0 : this.orderId.hashCode())) * 31) + (this.customerId == null ? 0 : this.customerId.hashCode())) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.stockAvailable == null ? 0 : this.stockAvailable.hashCode())) * 31) + (this.stockAvailablee == null ? 0 : this.stockAvailablee.hashCode())) * 31) + (this.image == null ? 0 : this.image.hashCode())) * 31) + (this.price == null ? 0 : this.price.hashCode())) * 31) + (this.totalAmount == null ? 0 : this.totalAmount.hashCode())) * 31) + (this.quantity == null ? 0 : this.quantity.hashCode())) * 31) + (this.total == null ? 0 : this.total.hashCode())) * 31) + (this.companyPrice == null ? 0 : this.companyPrice.hashCode())) * 31) + (this.priceForView == null ? 0 : this.priceForView.hashCode())) * 31) + (this.quantityForView == null ? 0 : this.quantityForView.hashCode())) * 31) + (this.companyPriceForDisplay == null ? 0 : this.companyPriceForDisplay.hashCode())) * 31) + (this.brand == null ? 0 : this.brand.hashCode())) * 31) + (this.series == null ? 0 : this.series.hashCode())) * 31) + (this.subject == null ? 0 : this.subject.hashCode())) * 31) + (this.productName == null ? 0 : this.productName.hashCode())) * 31) + (this.grade == null ? 0 : this.grade.hashCode())) * 31) + (this.isSelected == null ? 0 : this.isSelected.hashCode())) * 31) + (this.updatedPrice == null ? 0 : this.updatedPrice.hashCode())) * 31) + (this.isBottom == null ? 0 : this.isBottom.hashCode())) * 31) + (this.updateQuantity != null ? this.updateQuantity.hashCode() : 0);
    }

    public String toString() {
        return "Products(id=" + this.id + ", productId=" + this.productId + ", refillProductsId=" + this.refillProductsId + ", orderId=" + this.orderId + ", customerId=" + this.customerId + ", title=" + this.title + ", description=" + this.description + ", stockAvailable=" + this.stockAvailable + ", stockAvailablee=" + this.stockAvailablee + ", image=" + this.image + ", price=" + this.price + ", totalAmount=" + this.totalAmount + ", quantity=" + this.quantity + ", total=" + this.total + ", companyPrice=" + this.companyPrice + ", priceForView=" + this.priceForView + ", quantityForView=" + this.quantityForView + ", companyPriceForDisplay=" + this.companyPriceForDisplay + ", brand=" + this.brand + ", series=" + this.series + ", subject=" + this.subject + ", productName=" + this.productName + ", grade=" + this.grade + ", isSelected=" + this.isSelected + ", updatedPrice=" + this.updatedPrice + ", isBottom=" + this.isBottom + ", updateQuantity=" + this.updateQuantity + ")";
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public Products(Integer id, String productId, Integer refillProductsId, String orderId, String customerId, String title, String description, String stockAvailable, String stockAvailablee, String image, String price, Integer totalAmount, String quantity, String total, String companyPrice, String priceForView, String quantityForView, String companyPriceForDisplay, String brand, String series, String subject, String productName, String grade, Boolean isSelected, String updatedPrice, Boolean isBottom, String updateQuantity) {
        this.id = id;
        this.productId = productId;
        this.refillProductsId = refillProductsId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.title = title;
        this.description = description;
        this.stockAvailable = stockAvailable;
        this.stockAvailablee = stockAvailablee;
        this.image = image;
        this.price = price;
        this.totalAmount = totalAmount;
        this.quantity = quantity;
        this.total = total;
        this.companyPrice = companyPrice;
        this.priceForView = priceForView;
        this.quantityForView = quantityForView;
        this.companyPriceForDisplay = companyPriceForDisplay;
        this.brand = brand;
        this.series = series;
        this.subject = subject;
        this.productName = productName;
        this.grade = grade;
        this.isSelected = isSelected;
        this.updatedPrice = updatedPrice;
        this.isBottom = isBottom;
        this.updateQuantity = updateQuantity;
    }

    public /* synthetic */ Products(Integer num, String str, Integer num2, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Integer num3, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, Boolean bool, String str21, Boolean bool2, String str22, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? null : str7, (i & 512) != 0 ? null : str8, (i & 1024) != 0 ? null : str9, (i & 2048) != 0 ? null : num3, (i & 4096) != 0 ? null : str10, (i & 8192) != 0 ? null : str11, (i & 16384) != 0 ? null : str12, (i & 32768) != 0 ? null : str13, (i & 65536) != 0 ? null : str14, (i & 131072) != 0 ? null : str15, (i & 262144) != 0 ? null : str16, (i & 524288) != 0 ? null : str17, (i & 1048576) != 0 ? null : str18, (i & 2097152) != 0 ? null : str19, (i & 4194304) != 0 ? null : str20, (i & 8388608) != 0 ? false : bool, (i & 16777216) != 0 ? "" : str21, (i & 33554432) != 0 ? false : bool2, (i & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) == 0 ? str22 : "");
    }

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public final String getProductId() {
        return this.productId;
    }

    public final void setProductId(String str) {
        this.productId = str;
    }

    public final Integer getRefillProductsId() {
        return this.refillProductsId;
    }

    public final void setRefillProductsId(Integer num) {
        this.refillProductsId = num;
    }

    public final String getOrderId() {
        return this.orderId;
    }

    public final void setOrderId(String str) {
        this.orderId = str;
    }

    public final String getCustomerId() {
        return this.customerId;
    }

    public final void setCustomerId(String str) {
        this.customerId = str;
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

    public final String getImage() {
        return this.image;
    }

    public final void setImage(String str) {
        this.image = str;
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

    public final String getTotal() {
        return this.total;
    }

    public final void setTotal(String str) {
        this.total = str;
    }

    public final String getCompanyPrice() {
        return this.companyPrice;
    }

    public final void setCompanyPrice(String str) {
        this.companyPrice = str;
    }

    public final String getPriceForView() {
        return this.priceForView;
    }

    public final void setPriceForView(String str) {
        this.priceForView = str;
    }

    public final String getQuantityForView() {
        return this.quantityForView;
    }

    public final void setQuantityForView(String str) {
        this.quantityForView = str;
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

    public final String getProductName() {
        return this.productName;
    }

    public final void setProductName(String str) {
        this.productName = str;
    }

    public final String getGrade() {
        return this.grade;
    }

    public final void setGrade(String str) {
        this.grade = str;
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

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        Integer num = this.id;
        if (num == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num.intValue());
        }
        dest.writeString(this.productId);
        Integer num2 = this.refillProductsId;
        if (num2 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num2.intValue());
        }
        dest.writeString(this.orderId);
        dest.writeString(this.customerId);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.stockAvailable);
        dest.writeString(this.stockAvailablee);
        dest.writeString(this.image);
        dest.writeString(this.price);
        Integer num3 = this.totalAmount;
        if (num3 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num3.intValue());
        }
        dest.writeString(this.quantity);
        dest.writeString(this.total);
        dest.writeString(this.companyPrice);
        dest.writeString(this.priceForView);
        dest.writeString(this.quantityForView);
        dest.writeString(this.companyPriceForDisplay);
        dest.writeString(this.brand);
        dest.writeString(this.series);
        dest.writeString(this.subject);
        dest.writeString(this.productName);
        dest.writeString(this.grade);
        Boolean bool = this.isSelected;
        if (bool == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(bool.booleanValue() ? 1 : 0);
        }
        dest.writeString(this.updatedPrice);
        Boolean bool2 = this.isBottom;
        if (bool2 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(bool2.booleanValue() ? 1 : 0);
        }
        dest.writeString(this.updateQuantity);
    }
}
